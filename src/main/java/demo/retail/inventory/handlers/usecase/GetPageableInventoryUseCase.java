package demo.retail.inventory.handlers.usecase;


import demo.retail.inventory.drivenAdapters.bus.RabbitMqPublisher;
import demo.retail.inventory.drivenAdapters.repositories.IInventoryRepository;
import demo.retail.inventory.models.DTO.InventoryDto;
import demo.retail.inventory.models.EventTypes;
import demo.retail.inventory.models.Record;
import demo.retail.inventory.models.RecordTypes;
import demo.retail.inventory.models.mapper.InventoryMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Service
@Validated
public class GetPageableInventoryUseCase implements Function<Pageable, Flux<InventoryDto>> {
    private IInventoryRepository repository;
    private RabbitMqPublisher eventBus;

    public GetPageableInventoryUseCase(IInventoryRepository repository, RabbitMqPublisher eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    public Flux<InventoryDto> apply(Pageable pageable) {
        return repository.findAllBy(pageable)
                .map(inventory -> {
                    eventBus.publishLogs(
                            new Record(
                                    EventTypes.INFO.toString(),
                                    RecordTypes.INVENTORY_GET.toString(),
                                    inventory.toString()));
                    return InventoryMapper.getInventoryDto(inventory);
                });
    }
}
