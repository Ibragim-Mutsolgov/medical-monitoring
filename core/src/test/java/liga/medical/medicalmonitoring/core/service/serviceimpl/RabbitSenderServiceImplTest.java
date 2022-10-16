package liga.medical.medicalmonitoring.core.service.serviceimpl;

import liga.medical.medicalmonitoring.core.service.RabbitSenderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.core.AmqpTemplate;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RabbitSenderServiceImplTest {

    private RabbitSenderService service;

    @Mock
    private AmqpTemplate template;

    private String message;

    private String queue;

    @BeforeEach
    void setUp() {
        service = new RabbitSenderServiceImpl(template);
        message = "Message";
        queue = "Queue";
    }

    @Test
    void sendMessage() {
        // given

        // when
        service.sendMessage(message, queue);

        // then
        verify(template).convertAndSend(queue, message);
    }
}