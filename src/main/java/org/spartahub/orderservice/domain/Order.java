package org.spartahub.orderservice.domain;

import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Order {

    public void sendDeliveryMessage(DeliveryDeadLineMessage generator, MessageSend messageSend) {
        // 주문 정보를 가지고 발송 시한 관련 추가 가공 처리 필요, 여기는 테스트 데이터로 대체
        // 배송 담당자 목록에서 슬랙 아이디
        List<String> slackIds = List.of("U09UD0T0D2T");

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
        // AI를 통한 발송 시한 메세지 생성
        String message = generator.makeMessage(_message);
        if (!StringUtils.hasText(message)) {
            return;
        }

        // slack 메세지 전송
        messageSend.send(slackIds, message);

    }
}
