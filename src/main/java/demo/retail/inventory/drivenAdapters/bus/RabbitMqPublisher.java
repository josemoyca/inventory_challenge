package demo.retail.inventory.drivenAdapters.bus;

import com.google.gson.Gson;
import demo.retail.inventory.config.RabbitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.OutboundMessage;
import reactor.rabbitmq.Sender;

import java.nio.charset.StandardCharsets;

@Component
public class RabbitMqPublisher {

    @Autowired
    private Sender sender;

    @Autowired
    private Gson gson;

    public void publishMessage(Object object) {
        System.out.println("Publish message: " + object);
        sender
                .send(Mono.just(new OutboundMessage(RabbitConfig.EXCHANGE_NAME,
                        RabbitConfig.ROUTING_MOVEMENTS_KEY_NAME, gson.toJson(object).getBytes(StandardCharsets.UTF_8)))).subscribe();
    }

    public void publishLogs(Object object) {
        System.out.println("Publish logs: " + object);
        sender
                .send(Mono.just(new OutboundMessage(RabbitConfig.EXCHANGE_NAME,
                        RabbitConfig.ROUTING_RECORD_KEY_NAME, gson.toJson(object).getBytes(StandardCharsets.UTF_8)))).subscribe();
    }

}
