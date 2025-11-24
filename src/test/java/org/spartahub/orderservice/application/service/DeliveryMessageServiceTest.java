package org.spartahub.orderservice.application.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
public class DeliveryMessageServiceTest {
    @Autowired
    DeliveryMessageService service;

    @Test
    void deliveryMessageTest() {
        service.send(UUID.randomUUID());
    }
}
