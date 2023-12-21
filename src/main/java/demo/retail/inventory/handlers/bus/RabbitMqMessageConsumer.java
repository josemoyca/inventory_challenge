package demo.retail.inventory.handlers.bus;

import com.google.gson.Gson;
import demo.retail.inventory.config.RabbitConfig;
import demo.retail.inventory.drivenAdapters.repositories.IInventoryRepository;
import demo.retail.inventory.models.Movement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.rabbitmq.Receiver;

import java.time.LocalDateTime;

@Component
public class RabbitMqMessageConsumer implements CommandLineRunner {

    @Autowired
    private Receiver receiver;

    @Autowired
    private Gson gson;
    @Autowired
    private IInventoryRepository inventoryRepository;

    @Override
    public void run(String... args) throws Exception {
        receiver.consumeAutoAck(RabbitConfig.QUEUE_AVAILABILITY)
                .map(message -> {
                    Movement movement = gson
                            .fromJson(new String(message.getBody()),
                                    Movement.class);

                    System.out.println("Activado proceso de actualizacion de existencia:  " + movement.getInventoryId());
                    inventoryRepository.findById(movement.getInventoryId())
                            .flatMap(inventory -> {
                                int availability = inventory.getAvailability() - movement.getQuantity();
                                inventory.setAvailability(availability);
                                inventory.setUpdatedAt(LocalDateTime.now());
                                System.out.println("Descontando inventario:  " + inventory.getAvailability());
                                return inventoryRepository.save(inventory);
                            }).subscribe();

                    System.out.println("Inventario descontado:  " + movement.getQuantity());

                    System.out.println("Movimiento registrado:  " + movement);
                    return movement;
                }).subscribe();
    }

}
