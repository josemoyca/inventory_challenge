package demo.retail.inventory.handlers.usecase;

import demo.retail.inventory.drivenAdapters.bus.RabbitMqPublisher;
import demo.retail.inventory.drivenAdapters.repositories.IInventoryRepository;
import demo.retail.inventory.drivenAdapters.repositories.ISalesRepository;
import demo.retail.inventory.handlers.exception.ResourceBadRequestException;
import demo.retail.inventory.handlers.exception.ResourceNotFoundException;
import demo.retail.inventory.models.DTO.SalesDTO;
import demo.retail.inventory.models.Record;
import demo.retail.inventory.models.*;
import demo.retail.inventory.models.mapper.SalesMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CreateWholeSaleUseCase {

    private ISalesRepository salesRepository;
    private IInventoryRepository inventoryRepository;
    private RabbitMqPublisher eventBus;

    public CreateWholeSaleUseCase(ISalesRepository salesRepository, IInventoryRepository inventoryRepository, RabbitMqPublisher eventBus) {
        this.salesRepository = salesRepository;
        this.inventoryRepository = inventoryRepository;
        this.eventBus = eventBus;
    }

    public Mono<SalesDTO> apply(SalesDTO salesDTO) {
        return inventoryRepository.findById(salesDTO.getProductId()).defaultIfEmpty(new Inventory())
                .flatMap(inventory -> {
                    if (inventory.getId() == null) {
                        String message = String.format("Inventory id %s not found", salesDTO.getProductId());
                        System.out.println(message);
                        eventBus.publishMessage(
                                new Record(
                                        EventTypes.ERROR.toString(),
                                        RecordTypes.WHOLESALE.toString(),
                                        salesDTO.toString(),
                                        message));
                        return Mono.error(new ResourceNotFoundException(message));
                    }

                    if (salesDTO.getQuantity() < inventory.getProduct().getWholesaleQuantity()) {
                        String message = String.format(
                                "La cantidad pedida (%s) es menor al minimo establecido para venta al mayor (%s) de este inventorio %s",
                                salesDTO.getQuantity(),
                                inventory.getProduct().getWholesaleQuantity(),
                                inventory.getId());
                        System.out.println(message);
                        eventBus.publishMessage(
                                new Record(
                                        EventTypes.ERROR.toString(),
                                        RecordTypes.WHOLESALE.toString(),
                                        salesDTO.toString(),
                                        message));
                        return Mono.error(new ResourceBadRequestException(message));
                    }

                    salesDTO.setType(SalesTypes.WHOLESALE.toString());
                    salesDTO.setDiscountApplied(inventory.getProduct().getWholesalePercentage());
                    System.out.println("creating sale for wholesale");
                    return salesRepository.save(SalesMapper.getSales(salesDTO));
                })
                .map(sales -> {
                    eventBus.publishMessage(
                            new Movement(
                                    sales.getProductId(),
                                    RecordTypes.WHOLESALE.toString(),
                                    sales.getQuantity(),
                                    sales.getCreatedAt().toString()));
                    return SalesMapper.getSalesDTO(sales);
                });
    }
}
