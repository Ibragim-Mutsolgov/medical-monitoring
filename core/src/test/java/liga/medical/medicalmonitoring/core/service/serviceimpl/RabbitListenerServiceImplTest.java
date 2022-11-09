package liga.medical.medicalmonitoring.core.service.serviceimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.medicalmonitoring.core.dto.RabbitMessageDto;
import liga.medical.medicalmonitoring.core.dto.NamesForQueue;
import liga.medical.medicalmonitoring.core.dto.Type;
import liga.medical.medicalmonitoring.core.service.RabbitListenerService;
import liga.medical.medicalmonitoring.core.service.RabbitSenderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RabbitListenerServiceImplTest {

    private RabbitListenerService listenerService;

    @Mock
    private RabbitSenderService senderService;

    private ObjectMapper mapper;

    private RabbitMessageDto dto;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        listenerService = new RabbitListenerServiceImpl(mapper, senderService);
        dto = new RabbitMessageDto();
        dto.setContent("Content");
    }

    @Test
    void routeMessageWithAlert() throws JsonProcessingException {
        // given
        dto.setType(Type.ALERT);
        String message = mapper.writeValueAsString(dto);

        // when
        listenerService.routeMessage(message);

        // when
        verify(senderService).sendMessage(message, NamesForQueue.ROUTER_QUEUE_ALERT);
    }

    @Test
    void routeMessageWithDaily() throws JsonProcessingException {
        // given
        dto.setType(Type.DAILY);
        String message = mapper.writeValueAsString(dto);

        // when
        listenerService.routeMessage(message);

        // when
        verify(senderService).sendMessage(message, NamesForQueue.ROUTER_QUEUE_DAILY);
    }

    @Test
    void routeMessageWithError() throws JsonProcessingException {
        // given
        dto.setType(Type.ERROR);
        String message = mapper.writeValueAsString(dto);

        // when
        listenerService.routeMessage(message);

        // when
        verify(senderService).sendMessage(message, NamesForQueue.ROUTER_QUEUE_ERROR);
    }
}