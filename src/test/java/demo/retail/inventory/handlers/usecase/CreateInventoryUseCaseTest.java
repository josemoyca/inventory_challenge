package demo.retail.inventory.handlers.usecase;

import demo.retail.inventory.drivenAdapters.repositories.IInventoryRepository;
import demo.retail.inventory.models.DTO.InventoryDto;
import demo.retail.inventory.models.Inventory;
import demo.retail.inventory.models.Product;
import demo.retail.inventory.models.mapper.InventoryMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;

import java.util.Objects;

class CreateInventoryUseCaseTest {
    private IInventoryRepository inventoryRepository;
    private CreateInventoryUseCase useCase;

    @BeforeEach
    void setUp() {
        inventoryRepository = Mockito.mock(IInventoryRepository.class);
        useCase = new CreateInventoryUseCase(inventoryRepository);
    }

    @Test
    @DisplayName("Create inventory Ok Test")
    void apply() {
        // Arrange
        Product product = new Product();

        Inventory inventory = new Inventory();
        inventory.setId("1afdf");
        inventory.setProduct(product);

        InventoryDto inventoryDto = InventoryMapper.getInventoryDto(inventory);
        // Act
        Mockito.when(inventoryRepository.save(Mockito.any()))
                .thenReturn(Mono.just(inventory));

        // Assert
        Assertions.assertEquals(
                "1afdf",
                Objects.requireNonNull(
                        useCase.apply(inventoryDto).block()
                ).getId()
        );
    }
}