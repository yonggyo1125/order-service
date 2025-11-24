package org.spartahub.orderservice.application.service;

import lombok.RequiredArgsConstructor;
import org.spartahub.orderservice.domain.DeliveryDeadLineMessage;
import org.spartahub.orderservice.domain.MessageSend;
import org.spartahub.orderservice.domain.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryMessageService {
    private final DeliveryDeadLineMessage deadLineMessage;
    private final MessageSend messageSend;
    @Transactional
    public void send(UUID orderId) {
        // 주문아이디로 주문을 조회한 후 도메인 로직 실행

        Order order = new Order(); // 원래는 직접 생성하는 것이 아닌 OrderRepository에서 조회하고 처리

        order.sendDeliveryMessage(deadLineMessage, messageSend);
    }
}
