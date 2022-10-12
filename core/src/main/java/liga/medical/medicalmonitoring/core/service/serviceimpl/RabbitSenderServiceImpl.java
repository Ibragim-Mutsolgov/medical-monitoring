package liga.medical.medicalmonitoring.core.service.serviceimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.medicalmonitoring.core.model.MessageDto;
import liga.medical.medicalmonitoring.core.service.RabbitSenderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class RabbitSenderServiceImpl implements RabbitSenderService {

    private final AmqpTemplate template;

    private final ObjectMapper mapper;

    @Override
    public void sendMessage(MessageDto dto, String queue) throws JsonProcessingException {
        String dtoToString = mapper.writeValueAsString(dto);
        template.convertAndSend(queue, dtoToString);
        log.info("Сообщение " + dtoToString + " отправлено в очередь " + queue);
    }
}
