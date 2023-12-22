package demo.retail.inventory.handlers.usecase;

import demo.retail.inventory.drivenAdapters.bus.RabbitMqPublisher;
import demo.retail.inventory.drivenAdapters.repositories.IInventoryRepository;
import demo.retail.inventory.drivenAdapters.repositories.ISalesRepository;
import demo.retail.inventory.models.DTO.SalesDTO;
import demo.retail.inventory.models.Inventory;
import demo.retail.inventory.models.Product;
import demo.retail.inventory.models.Sales;
import demo.retail.inventory.models.SalesTypes;
import demo.retail.inventory.models.mapper.SalesMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Objects;

class CreateDetailSaleUseCaseTest {
    private ISalesRepository salesRepository;
    private IInventoryRepository inventoryRepository;
    private RabbitMqPublisher publisher;
    private CreateDetailSaleUseCase useCase;

    @BeforeEach
    public void setUp() {
        salesRepository = Mockito.mock(ISalesRepository.class);
        inventoryRepository = Mockito.mock(IInventoryRepository.class);
        publisher = Mockito.mock(RabbitMqPublisher.class);
        useCase = new CreateDetailSaleUseCase(salesRepository, inventoryRepository, publisher);
    }

    @Test
    void apply() {
        //Arrange
        LocalDateTime dateTime = LocalDateTime.of(2023, Month.DECEMBER, 21, 12, 30);

        Sales sales = new Sales();
        sales.setId("2fjk4");
        sales.setProductId("1k2d");
        sales.setQuantity(1);
        sales.setType(SalesTypes.DETAIL.toString());
        sales.setCreatedAt(dateTime);

        Product product = new Product();
        product.setRetailPercentage(15.20);

        Inventory inventory = new Inventory();
        inventory.setId("1afdf");
        inventory.setAvailability(100);
        inventory.setProduct(product);

        SalesDTO salesDTO = SalesMapper.getSalesDTO(sales);

        // Act
        Mockito.when(inventoryRepository.findById("1k2d"))
                .thenReturn(Mono.just(inventory));
        Mockito.when(salesRepository.save(Mockito.any()))
                .thenReturn(Mono.just(sales));

        // Assert
        Assertions.assertEquals("2fjk4", Objects.requireNonNull(useCase.apply(salesDTO).block()).getId());
    }
}