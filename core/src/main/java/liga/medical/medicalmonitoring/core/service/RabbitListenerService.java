package liga.medical.medicalmonitoring.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface RabbitListenerService {

    void routeMessage(String message) throws JsonProcessingException;
}
