package liga.medical.medicalmonitoring.core.service;

public interface RabbitSenderService {
    void sendMessage(String message, String queue);
}
