package org.spartahub.orderservice.infrastructure.delivery;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.spartahub.orderservice.domain.DeadLineMessage;
import org.spartahub.orderservice.domain.DeliveryDeadLineMessage;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GeminiDeliveryDeadLineMessage implements DeliveryDeadLineMessage {
    private Resource template;

    private final ChatClient.Builder builder;

    @PostConstruct
    public void setup() {
        // 프롬프트 템플릿
        template = new ClassPathResource("system.txt");
    }

    @Override
    public String makeMessage(DeadLineMessage message) {
        ChatClient client = builder.build();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Map<String, Object> params = new HashMap<>();
        params.put("order_no", message.orderNo().toString());
        params.put("orderer_name", message.ordererName());
        params.put("orderer_email", message.ordererEmail());
        params.put("estimate_time", message.estimateTime() + "시간");
        params.put("order_date", formatter.format(message.orderDate()));
        params.put("order_products", message.orderProducts());
        params.put("order_memo", message.orderMemo());
        params.put("start_hub", message.startHub());
        params.put("stopover_hub", message.stopoverHub());
        params.put("arrival_address", message.arrivalAddress());
        params.put("staff", message.staffName());
        params.put("staff_email", message.staffEmail());

        return client.prompt()
                .user(s -> s.text(template, StandardCharsets.UTF_8)
                        .params(params))
                .call()
                .content();
    }
}
