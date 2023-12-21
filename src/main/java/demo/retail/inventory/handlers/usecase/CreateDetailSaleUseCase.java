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
public class CreateDetailSaleUseCase {
    private ISalesRepository salesRepository;
    private IInventoryRepository inventoryRepository;
    private RabbitMqPublisher eventBus;

    public CreateDetailSaleUseCase(ISalesRepository salesRepository, IInventoryRepository inventoryRepository, RabbitMqPublisher eventBus) {
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
                        eventBus.publishLogs(
                                new Record(
                                        EventTypes.ERROR.toString(),
                                        RecordTypes.RETAIL_SALE.toString(),
                                        salesDTO.toString(),
                                        message));
                        return Mono.error(new ResourceNotFoundException(message));
                    }
                    if (salesDTO.getQuantity() > inventory.getAvailability())
                        return buildBadRequestError("La cantidad pedida (%s) es mayor a lo disponible (%s) para este producto %s",
                                salesDTO,
                                inventory.getAvailability(),
                                inventory);

                    salesDTO.setType(SalesTypes.DETAIL.toString());
                    salesDTO.setDiscountApplied(inventory.getProduct().getRetailPercentage());

                    System.out.println("creating sale for retail");
                    return salesRepository.save(SalesMapper.getSales(salesDTO));
                })
                .map(sales -> {
                    eventBus.publishMessage(
                            new Movement(
                                    sales.getProductId(),
                                    RecordTypes.RETAIL_SALE.toString(),
                                    sales.getQuantity(),
                                    sales.getCreatedAt().toString()));
                    return SalesMapper.getSalesDTO(sales);
                });
    }

    private Mono<Sales> buildBadRequestError(String format, SalesDTO salesDTO, Integer inventory, Inventory inventory1) {
        String message = String.format(
                format,
                salesDTO.getQuantity(),
                inventory,
                inventory1.getId());

        System.out.println(message);

        eventBus.publishLogs(
                new Record(
                        EventTypes.ERROR.toString(),
                        RecordTypes.WHOLESALE.toString(),
                        salesDTO.toString(),
                        message));
        return Mono.error(new ResourceBadRequestException(message));
    }
}
