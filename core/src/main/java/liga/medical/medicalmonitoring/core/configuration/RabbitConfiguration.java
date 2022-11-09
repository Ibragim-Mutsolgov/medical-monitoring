package liga.medical.medicalmonitoring.core.configuration;

import liga.medical.medicalmonitoring.core.dto.NamesForQueue;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    private static final String HOST = "localhost";

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(HOST);
    }

    @Bean
    public Queue queue() {
        return new Queue(NamesForQueue.ROUTER_QUEUE_NAME);
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue daily() {
        return new Queue(NamesForQueue.ROUTER_QUEUE_DAILY);
    }

    @Bean
    public Queue alert() {
        return new Queue(NamesForQueue.ROUTER_QUEUE_ALERT);
    }

    @Bean
    public Queue error() {
        return new Queue(NamesForQueue.ROUTER_QUEUE_ERROR);
    }
}
