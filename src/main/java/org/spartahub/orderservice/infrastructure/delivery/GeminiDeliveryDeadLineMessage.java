package org.spartahub.orderservice.infrastructure.delivery;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.spartahub.orderservice.domain.DeliveryDeadLineMessage;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
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
    public String makeMessage() {
        ChatClient client = builder.build();

        Map<String, Object> params = new HashMap<>();
        params.put("order_no", 1);
        params.put("orderer_name", "김말숙");
        params.put("orderer_email", "msk@seafood.world");
        params.put("estimate_time", "36시간");
        params.put("order_date", "2025-12-08 10:00:00");
        params.put("order_products", "마른 오징어 50박스");
        params.put("order_memo", "12월 12일 3시까지는 보내주세요!");
        params.put("start_hub", "경기 북부 센터");
        params.put("stopover_hub", "대전광역시 센터, 부산광역시 센터");
        params.put("arrival_address", "부산시 사하구 낙동대로 1번길 1 해산물월드");
        params.put("staff", "고길동");
        params.put("staff_email", "kdk@sparta.world");

        return client.prompt()
                .user(s -> s.text(template, StandardCharsets.UTF_8)
                        .params(params))
                .call()
                .content();
    }
}
