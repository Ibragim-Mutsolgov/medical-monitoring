package liga.medical.medicalmonitoring.core.service.serviceimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.medicalmonitoring.core.dto.RabbitMessageDto;
import liga.medical.medicalmonitoring.core.dto.NamesForQueue;
import liga.medical.medicalmonitoring.core.dto.Type;
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
        RabbitMessageDto dto = mapper.readValue(message, RabbitMessageDto.class);
        Type type = dto.getType();
        if (type.name().equals("DAILY"))
            service.sendMessage(message, NamesForQueue.ROUTER_QUEUE_DAILY);
        if (type.name().equals("ALERT"))
            service.sendMessage(message, NamesForQueue.ROUTER_QUEUE_ALERT);
        if (type.name().equals("ERROR"))
            // Надо отправить в лог common-module в таблицу exception
            service.sendMessage(message, NamesForQueue.ROUTER_QUEUE_ERROR);
    }
}
