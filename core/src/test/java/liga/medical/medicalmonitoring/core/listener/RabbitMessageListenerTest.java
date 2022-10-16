package liga.medical.medicalmonitoring.core.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import liga.medical.medicalmonitoring.core.service.RabbitListenerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RabbitMessageListenerTest {

    private RabbitMessageListener listener;

    @Mock
    private RabbitListenerService service;

    @BeforeEach
    void setUp() {
        listener = new RabbitMessageListener(service);
    }

    @Test
    void getAndSendMessage() throws JsonProcessingException {
        // given
        String message = "Message";

        // when
        listener.getAndSendMessage(message);

        // then
        verify(service).routeMessage(message);
    }
}