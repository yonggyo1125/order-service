package org.spartahub.orderservice.ifrastructure.delivery;

import org.junit.jupiter.api.Test;
import org.spartahub.orderservice.domain.DeadLineMessage;
import org.spartahub.orderservice.domain.DeliveryDeadLineMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
public class GeminiDeliveryDeadLineMessageTest {
    @Autowired
    DeliveryDeadLineMessage deadLineMessage;

    @Test
    void makeMessageTest() {
        DeadLineMessage _message = DeadLineMessage
                .builder()
                .orderNo(UUID.randomUUID())
                .ordererName("김말숙")
                .ordererEmail("msk@seafood.world")
                .orderDate(LocalDateTime.of(2025, 12, 8, 0, 0, 0))
                .orderMemo("12월 12일 3시까지는 보내주세요!")
                .estimateTime(36.0)
                .orderProducts("마른 오징어 50박스")
                .startHub("경기 북부 센터")
                .stopoverHub("대전광역시 센터,부산광역시 센터")
                .arrivalAddress("부산시 사하구 낙동대로 1번길 1 해산물월드")
                .staffName("고길동")
                .staffEmail("kdk@sparta.world")
                .build();
        String message = deadLineMessage.makeMessage(_message);
        System.out.println(message);
    }
}
