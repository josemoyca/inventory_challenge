package demo.retail.inventory.mapper;

import demo.retail.inventory.models.DTO.InventoryDto;
import demo.retail.inventory.models.Inventory;

public class InventoryMapper {
    public static InventoryDto getInventoryDto(Inventory savedInventory) {
        InventoryDto dto = new InventoryDto();
        dto.setProduct(savedInventory.getProduct());
        dto.setAvailability(savedInventory.getAvailability());
        dto.setMaxStockLevel(savedInventory.getMaxStockLevel());
        dto.setMinStockLevel(savedInventory.getMinStockLevel());
        dto.setReorderPoint(savedInventory.getReorderPoint());
        return dto;
    }

    public static Inventory getInventory(InventoryDto inventoryDto) {
        Inventory inventory = new Inventory();
        inventory.setProduct(inventoryDto.getProduct());
        inventory.setAvailability(inventoryDto.getAvailability());
        inventory.setMaxStockLevel(inventoryDto.getMaxStockLevel());
        inventory.setMinStockLevel(inventoryDto.getMinStockLevel());
        inventory.setReorderPoint(inventoryDto.getReorderPoint());
        return inventory;
    }
}
