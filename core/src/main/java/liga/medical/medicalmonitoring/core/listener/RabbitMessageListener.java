package liga.medical.medicalmonitoring.core.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import liga.medical.medicalmonitoring.core.model.NamesForQueue;
import liga.medical.medicalmonitoring.core.service.RabbitListenerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class RabbitMessageListener {

    private final RabbitListenerService service;

    @RabbitListener(queues = NamesForQueue.ROUTER_QUEUE_NAME)
    public void getAndSendMessage(String message) throws JsonProcessingException {
        log.info("The message " + message + " is received");
        service.routeMessage(message);
    }
}
