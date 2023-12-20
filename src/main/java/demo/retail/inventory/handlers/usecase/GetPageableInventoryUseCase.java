package demo.retail.inventory.handlers.usecase;


import demo.retail.inventory.drivenAdapters.repositories.IInventoryRepository;
import demo.retail.inventory.models.mapper.InventoryMapper;
import demo.retail.inventory.models.DTO.InventoryDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Service
@Validated
public class GetPageableInventoryUseCase implements Function<Pageable, Flux<InventoryDto>> {
    private IInventoryRepository repository;

    public GetPageableInventoryUseCase(IInventoryRepository repository) {
        this.repository = repository;
    }

    public Flux<InventoryDto> apply(Pageable pageable) {
        return repository.findAllBy(pageable)
                .map(InventoryMapper::getInventoryDto);
    }
}
