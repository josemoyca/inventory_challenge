package demo.retail.inventory.config;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.rabbitmq.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@Configuration
public class RabbitConfig {

    @Value("${RABBIT_SERVER_URI}")
    String rabbitServer;

    public static final String QUEUE_MOVEMENTS = "movements-queue";
    public static final String QUEUE_RECORD = "record-queue";
    public static final String EXCHANGE_NAME = "base-exchange";
    public static final String ROUTING_MOVEMENTS_KEY_NAME = "movements.routing.key";
    public static final String ROUTING_RECORD_KEY_NAME = "record.routing.key";

    @Bean
    public AmqpAdmin amqpAdmin() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(URI.create(rabbitServer));
        var amqpAdmin = new RabbitAdmin(connectionFactory);

        var exchange = new TopicExchange(EXCHANGE_NAME);
        var movementsQueue = new Queue(QUEUE_MOVEMENTS, true, false, false);
        var recordsQueue = new Queue(QUEUE_RECORD, true, false, false);

        amqpAdmin.declareExchange(exchange);

        amqpAdmin.declareQueue(movementsQueue);
        amqpAdmin.declareQueue(recordsQueue);

        amqpAdmin.declareBinding(BindingBuilder.bind(recordsQueue).to(exchange).with(ROUTING_RECORD_KEY_NAME));
        amqpAdmin.declareBinding(BindingBuilder.bind(recordsQueue).to(exchange).with(ROUTING_MOVEMENTS_KEY_NAME));
        amqpAdmin.declareBinding(BindingBuilder.bind(movementsQueue).to(exchange).with(ROUTING_MOVEMENTS_KEY_NAME));

        return amqpAdmin;
    }


    @Bean
    public ConnectionFactory connectionFactory() throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.useNio();
        connectionFactory.setUri(rabbitServer);
        connectionFactory.setVirtualHost("/");
        return connectionFactory;
    }

    @Bean
    public Mono<Connection> connectionMono(@Value("spring.application.name") String name, ConnectionFactory connectionFactory) {
        return Mono.fromCallable(() -> connectionFactory.newConnection(name)).cache();
    }

    @Bean
    public SenderOptions senderOptions(Mono<Connection> connectionMono) {
        return new SenderOptions()
                .connectionMono(connectionMono)
                .resourceManagementScheduler(Schedulers.boundedElastic());
    }

    @Bean
    public Sender sender(SenderOptions senderOptions) {
        return RabbitFlux.createSender(senderOptions);
    }


    @Bean
    public ReceiverOptions receiverOptions(Mono<Connection> connectionMono) {
        return new ReceiverOptions()
                .connectionMono(connectionMono);
    }

    @Bean
    public Receiver receiver(ReceiverOptions receiverOptions) {
        return RabbitFlux.createReceiver(receiverOptions);
    }
}
