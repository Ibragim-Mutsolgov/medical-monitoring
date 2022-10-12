package liga.medical.medicalmonitoring.core.service.serviceimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.medicalmonitoring.core.model.MessageDto;
import liga.medical.medicalmonitoring.core.model.NamesForQueue;
import liga.medical.medicalmonitoring.core.model.Status;
import liga.medical.medicalmonitoring.core.service.RabbitListenerService;
import liga.medical.medicalmonitoring.core.service.RabbitSenderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class RabbitListenerServiceImpl implements RabbitListenerService {

    private final ObjectMapper mapper;

    private final RabbitSenderService service;

    @Override
    public void routeMessage(String message) throws JsonProcessingException {
        MessageDto dto = mapper.readValue(message, MessageDto.class);
        Status status = dto.getStatus();
        switch (status) {
            case daily:
                service.sendMessage(dto, NamesForQueue.ROUTER_QUEUE_DAILY);
                break;
            case alert:
                service.sendMessage(dto, NamesForQueue.ROUTER_QUEUE_ALERT);
                break;
            case error:
                service.sendMessage(dto, NamesForQueue.ROUTER_QUEUE_ERROR);
                break;
            default:
                log.info("Не удается распознать к какому типу относиться сообщение");
        }
    }
}
