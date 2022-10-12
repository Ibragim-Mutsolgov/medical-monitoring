package liga.medical.medicalmonitoring.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import liga.medical.medicalmonitoring.core.model.MessageDto;

public interface RabbitSenderService {
    void sendMessage(MessageDto dto, String queue) throws JsonProcessingException;
}
