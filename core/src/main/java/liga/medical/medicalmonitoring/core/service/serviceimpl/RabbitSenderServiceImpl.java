package liga.medical.medicalmonitoring.core.service.serviceimpl;

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

    @Override
    public void sendMessage(String message, String queue) {
        template.convertAndSend(queue, message);
        log.info("Message " + message + " sent to the queue " + queue);
    }
}
