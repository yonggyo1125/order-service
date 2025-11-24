package org.spartahub.orderservice.infrastructure.slack;

import lombok.extern.slf4j.Slf4j;
import org.spartahub.orderservice.domain.MessageSend;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Slf4j
@Service
@RefreshScope
public class SlackMessageSend implements MessageSend {
    @Value("${slack.channel.code}")
    private String channelCode;

    @Override
    public boolean send(String message) {
        ResponseEntity<Void> response = RestClient.builder()
                .baseUrl("https://hooks.slack.com/services/" + channelCode)
                .build()
                .post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Map.of("text", message))
                .retrieve()
                .toBodilessEntity();

        boolean result = response.getStatusCode().is2xxSuccessful();
        log.info("slack message sent: message - {}, result - {}", message, result ? "성공" : "실패");

        return result;
    }
}
