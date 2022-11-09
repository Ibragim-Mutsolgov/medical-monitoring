package liga.medical.medicalmonitoring.core.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import liga.medical.medicalmonitoring.core.dto.NamesForQueue;
import liga.medical.medicalmonitoring.core.service.RabbitListenerService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RabbitMessageListener {

    private final RabbitListenerService service;

    @RabbitListener(queues = NamesForQueue.ROUTER_QUEUE_NAME)
    public void getAndSendMessage(String message) throws JsonProcessingException {
        service.routeMessage(message);
    }
}
