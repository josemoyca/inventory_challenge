package demo.retail.inventory.handlers.usecase;

import demo.retail.inventory.drivenAdapters.repositories.IInventoryRepository;
import demo.retail.inventory.drivenAdapters.repositories.ISalesRepository;
import demo.retail.inventory.handlers.exception.ResourceNotFoundException;
import demo.retail.inventory.models.DTO.SalesDTO;
import demo.retail.inventory.models.Inventory;
import demo.retail.inventory.models.SalesTypes;
import demo.retail.inventory.models.mapper.SalesMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CreateDetailSaleUseCase {
    private ISalesRepository salesRepository;
    private IInventoryRepository inventoryRepository;

    public CreateDetailSaleUseCase(ISalesRepository salesRepository, IInventoryRepository inventoryRepository) {
        this.salesRepository = salesRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public Mono<SalesDTO> apply(SalesDTO salesDTO) {
        return inventoryRepository.findById(salesDTO.getProductId()).defaultIfEmpty(new Inventory())
                .flatMap(inventory -> {
                    if (inventory.getId() == null) {
                        String message = String.format("Inventory id %s not found", salesDTO.getProductId());
                        System.out.println(message);
                        return Mono.error(new ResourceNotFoundException(message));
                    }
                    salesDTO.setType(SalesTypes.DETAIL.toString());
                    salesDTO.setDiscountApplied(inventory.getProduct().getRetailPercentage());

                    System.out.println("creating sale for retail");
                    return salesRepository.save(SalesMapper.getSales(salesDTO));
                })
                .map(SalesMapper::getSalesDTO);
    }
}
