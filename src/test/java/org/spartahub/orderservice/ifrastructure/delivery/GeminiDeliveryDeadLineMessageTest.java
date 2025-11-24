package org.spartahub.orderservice.ifrastructure.delivery;

import org.junit.jupiter.api.Test;
import org.spartahub.orderservice.domain.DeliveryDeadLineMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class GeminiDeliveryDeadLineMessageTest {
    @Autowired
    DeliveryDeadLineMessage deadLineMessage;

    @Test
    void makeMessageTest() {
        String message = deadLineMessage.makeMessage();
        System.out.println(message);
    }
}
