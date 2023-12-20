package demo.retail.inventory.handlers.usecase;

import demo.retail.inventory.drivenAdapters.repositories.IInventoryRepository;
import demo.retail.inventory.models.mapper.InventoryMapper;
import demo.retail.inventory.models.DTO.InventoryDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CreateInventoryUseCase {

    private IInventoryRepository repository;

    public CreateInventoryUseCase(IInventoryRepository repository) {
        this.repository = repository;
    }

    public Mono<InventoryDto> apply(InventoryDto inventoryDto) {
        return repository.save(InventoryMapper.getInventory(inventoryDto))
                .map(InventoryMapper::getInventoryDto);
    }

}
