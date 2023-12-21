package demo.retail.inventory.handlers.usecase;

import demo.retail.inventory.drivenAdapters.bus.RabbitMqPublisher;
import demo.retail.inventory.drivenAdapters.repositories.IInventoryRepository;
import demo.retail.inventory.models.DTO.InventoryDto;
import demo.retail.inventory.models.EventTypes;
import demo.retail.inventory.models.Record;
import demo.retail.inventory.models.RecordTypes;
import demo.retail.inventory.models.mapper.InventoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@Service
@Validated
public class CreateBatchInventoryUseCase {

    private IInventoryRepository repository;
    private RabbitMqPublisher eventBus;

    public CreateBatchInventoryUseCase(IInventoryRepository repository, RabbitMqPublisher eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    public Flux<InventoryDto> apply(Flux<InventoryDto> inventoryDtos) {
        return repository.saveAll(inventoryDtos.map(InventoryMapper::getInventory))
                .map(inventory -> {
                    eventBus.publishLogs(
                            new Record(
                                    EventTypes.INFO.toString(),
                                    RecordTypes.INVENTORY_POST_BATCH.toString(),
                                    inventory.toString()));
                    return InventoryMapper.getInventoryDto(inventory);
                });
    }

}
