package demo.retail.inventory.handlers.usecase;

import demo.retail.inventory.drivenAdapters.repositories.IInventoryRepository;
import demo.retail.inventory.drivenAdapters.repositories.ISalesRepository;
import demo.retail.inventory.handlers.exception.ResourceBadRequestException;
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
public class CreateWholeSaleUseCase {

    private ISalesRepository salesRepository;
    private IInventoryRepository inventoryRepository;

    public CreateWholeSaleUseCase(ISalesRepository salesRepository, IInventoryRepository inventoryRepository) {
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

                    if (salesDTO.getQuantity() < inventory.getProduct().getWholesaleQuantity()) {
                        String message = String.format(
                                "La cantidad pedida (%s) es menor al minimo establecido para venta al mayor (%s) de este inventorio %s",
                                salesDTO.getQuantity(),
                                inventory.getProduct().getWholesaleQuantity(),
                                inventory.getId());
                        System.out.println(message);
                        return Mono.error(new ResourceBadRequestException(message));
                    }

                    salesDTO.setType(SalesTypes.WHOLESALE.toString());
                    salesDTO.setDiscountApplied(inventory.getProduct().getWholesalePercentage());
                    System.out.println("creating sale for wholesale");
                    return salesRepository.save(SalesMapper.getSales(salesDTO));
                })
                .map(SalesMapper::getSalesDTO);
    }
}
