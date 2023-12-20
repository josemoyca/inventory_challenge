package demo.retail.inventory.handlers.usecase;

import demo.retail.inventory.drivenAdapters.repositories.IInventoryRepository;
import demo.retail.inventory.models.mapper.InventoryMapper;
import demo.retail.inventory.models.DTO.InventoryDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@Service
@Validated
public class CreateBatchInventoryUseCase {

    private IInventoryRepository repository;

    public CreateBatchInventoryUseCase(IInventoryRepository repository) {
        this.repository = repository;
    }

    public Flux<InventoryDto> apply(Flux<InventoryDto> inventoryDtos) {
        return repository.saveAll(inventoryDtos.map(InventoryMapper::getInventory))
                .map(InventoryMapper::getInventoryDto);
    }

}
