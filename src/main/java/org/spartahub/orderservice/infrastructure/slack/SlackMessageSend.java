package org.spartahub.orderservice.infrastructure.slack;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.spartahub.orderservice.domain.MessageSend;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RefreshScope
public class SlackMessageSend implements MessageSend {
    @Value("${slack.token}")
    private String token;

    @Override
    public boolean send(List<String> ids, String message) {
        if (ids != null) {

            RestClient client = RestClient.builder()
                            .baseUrl("https://slack.com/api")
                            .build();
            // Channel ID 처리 S
            ResponseEntity<JsonNode> response = client.post()
                        .uri("/conversations.open")
                    .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Map.of("users", String.join(",", ids)))
                    .retrieve()
                    .toEntity(JsonNode.class);
            JsonNode node = response.getBody();
            if (!response.getStatusCode().is2xxSuccessful() || node.get("ok") == null || !Boolean.parseBoolean(node.get("ok").textValue())) return false;

            String channelId = node.get("channel").get("id").textValue();
            // Channel ID 처리 E

            // 메세지 발송 처리 S
            response = client.post()
                    .uri("/chat.postMessage")
                    .header("Authorization", "Bearer " + token)
                    .body(Map.of("channel", channelId, "text", message, "as_user", true))
                    .contentType(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .toEntity(JsonNode.class);

            return response.getStatusCode().is2xxSuccessful() && node.get("ok") != null && Boolean.parseBoolean(node.get("ok").textValue());
            // 메세지 발송 처리 E
        }


        return false;
    }

    //    @Override
//    public boolean send(List<String> slackIds, String message) {
//        ResponseEntity<Void> response = RestClient.builder()
//                .baseUrl("https://hooks.slack.com/services/" + channelCode)
//                .build()
//                .post()
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(Map.of("text", message))
//                .retrieve()
//                .toBodilessEntity();
//
//        boolean result = response.getStatusCode().is2xxSuccessful();
//        log.info("slack message sent: message - {}, result - {}", message, result ? "성공" : "실패");
//
//        return result;
//    }
}
