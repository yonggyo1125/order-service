package org.spartahub.orderservice.ifrastructure.slack;

import org.junit.jupiter.api.Test;
import org.spartahub.orderservice.domain.MessageSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class SlackMessageSendTest {
    @Autowired
    MessageSend messageSend;

    @Test
    void messageSendTest() {
       messageSend.send("테스트 메세지, 잘 전송이 되나요?");
    }
}
