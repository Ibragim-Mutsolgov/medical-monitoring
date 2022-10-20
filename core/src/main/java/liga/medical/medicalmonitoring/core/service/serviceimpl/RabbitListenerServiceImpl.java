package liga.medical.medicalmonitoring.core.service.serviceimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.medicalmonitoring.core.model.MessageDto;
import liga.medical.medicalmonitoring.core.model.NamesForQueue;
import liga.medical.medicalmonitoring.core.model.Status;
import liga.medical.medicalmonitoring.core.service.RabbitListenerService;
import liga.medical.medicalmonitoring.core.service.RabbitSenderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RabbitListenerServiceImpl implements RabbitListenerService {

    private final ObjectMapper mapper;

    private final RabbitSenderService service;

    @Override
    public void routeMessage(String message) throws JsonProcessingException {
        MessageDto dto = mapper.readValue(message, MessageDto.class);
        Status status = dto.getStatus();
        if (status.name().equals("daily"))
            service.sendMessage(message, NamesForQueue.ROUTER_QUEUE_DAILY);
        if (status.name().equals("alert"))
            service.sendMessage(message, NamesForQueue.ROUTER_QUEUE_ALERT);
        if (status.name().equals("error"))
            service.sendMessage(message, NamesForQueue.ROUTER_QUEUE_ERROR);
    }
}
