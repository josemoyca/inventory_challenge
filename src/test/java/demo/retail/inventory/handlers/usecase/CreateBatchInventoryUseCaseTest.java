package demo.retail.inventory.handlers.usecase;

import demo.retail.inventory.drivenAdapters.bus.RabbitMqPublisher;
import demo.retail.inventory.drivenAdapters.repositories.IInventoryRepository;
import demo.retail.inventory.models.*;
import demo.retail.inventory.models.DTO.InventoryDto;
import demo.retail.inventory.models.Record;
import demo.retail.inventory.models.mapper.InventoryMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

import java.util.Objects;

class CreateBatchInventoryUseCaseTest {
    private IInventoryRepository inventoryRepository;
    private RabbitMqPublisher publisher;
    private CreateBatchInventoryUseCase useCase;

    @BeforeEach
    void setUp() {
        inventoryRepository = Mockito.mock(IInventoryRepository.class);
        publisher = Mockito.mock(RabbitMqPublisher.class);
        useCase = new CreateBatchInventoryUseCase(inventoryRepository, publisher);
    }

    @Test
    @DisplayName("Create inventory by batch Ok Test")
    void apply() {
        // Arrange
        Product product = new Product();

        Inventory inventory = new Inventory();
        inventory.setId("1afdfs");
        inventory.setProduct(product);

        InventoryDto inventoryDto = InventoryMapper.getInventoryDto(inventory);
        // Act
        Mockito.when(inventoryRepository.saveAll((Publisher<Inventory>) Mockito.any()))
                .thenReturn(Flux.just(inventory));

        // Assert
        Assertions.assertEquals(
                "1afdfs",
                Objects.requireNonNull(
                        useCase.apply(Flux.just(inventoryDto)).blockFirst()
                ).getId()
        );
    }
}