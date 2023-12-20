package demo.retail.inventory.models.mapper;

import demo.retail.inventory.models.DTO.InventoryDto;
import demo.retail.inventory.models.Inventory;
import demo.retail.inventory.models.Product;

public class InventoryMapper {
    public static InventoryDto getInventoryDto(Inventory savedInventory) {
        Product product = new Product();
        product.setId(savedInventory.getProduct().getId());
        product.setCode(savedInventory.getProduct().getCode());
        product.setDescription(savedInventory.getProduct().getDescription());
        product.setBrand(savedInventory.getProduct().getBrand());
        product.setCost(savedInventory.getProduct().getCost());
        product.setBatch(savedInventory.getProduct().getBatch());
        product.setVendor(savedInventory.getProduct().getVendor());
        product.setUnitPrice(savedInventory.getProduct().getUnitPrice());
        product.setWholesaleDiscount(savedInventory.getProduct().getWholesaleDiscount());
        product.setWholesalePercentage(savedInventory.getProduct().getWholesalePercentage());
        product.setWholesaleQuantity(savedInventory.getProduct().getWholesaleQuantity());
        product.setRetailDiscount(savedInventory.getProduct().getRetailDiscount());
        product.setRetailPercentage(savedInventory.getProduct().getRetailPercentage());
        product.setSellByDate(savedInventory.getProduct().getSellByDate());
        product.setUpdatedAt(savedInventory.getProduct().getUpdatedAt());
        product.setCreatedAt(savedInventory.getProduct().getCreatedAt());

        InventoryDto dto = new InventoryDto();
        dto.setId(savedInventory.getId());
        dto.setProduct(product);
        dto.setAvailability(savedInventory.getAvailability());
        dto.setMaxStockLevel(savedInventory.getMaxStockLevel());
        dto.setMinStockLevel(savedInventory.getMinStockLevel());
        dto.setReorderPoint(savedInventory.getReorderPoint());
        dto.setUpdatedAt(savedInventory.getUpdatedAt());
        dto.setCreatedAt(savedInventory.getCreatedAt());
        return dto;
    }

    public static Inventory getInventory(InventoryDto inventoryDto) {
        Product product = new Product();
        product.setId(inventoryDto.getProduct().getId());
        product.setCode(inventoryDto.getProduct().getCode());
        product.setDescription(inventoryDto.getProduct().getDescription());
        product.setBrand(inventoryDto.getProduct().getBrand());
        product.setCost(inventoryDto.getProduct().getCost());
        product.setBatch(inventoryDto.getProduct().getBatch());
        product.setVendor(inventoryDto.getProduct().getVendor());
        product.setUnitPrice(inventoryDto.getProduct().getUnitPrice());
        product.setWholesaleDiscount(inventoryDto.getProduct().getWholesaleDiscount());
        product.setWholesalePercentage(inventoryDto.getProduct().getWholesalePercentage());
        product.setWholesaleQuantity(inventoryDto.getProduct().getWholesaleQuantity());
        product.setRetailDiscount(inventoryDto.getProduct().getRetailDiscount());
        product.setRetailPercentage(inventoryDto.getProduct().getRetailPercentage());
        product.setSellByDate(inventoryDto.getProduct().getSellByDate());
        product.setUpdatedAt(inventoryDto.getProduct().getUpdatedAt());
        product.setCreatedAt(inventoryDto.getProduct().getCreatedAt());

        Inventory inventory = new Inventory();
        inventory.setId(inventoryDto.getId());
        inventory.setProduct(product);
        inventory.setAvailability(inventoryDto.getAvailability());
        inventory.setMaxStockLevel(inventoryDto.getMaxStockLevel());
        inventory.setMinStockLevel(inventoryDto.getMinStockLevel());
        inventory.setReorderPoint(inventoryDto.getReorderPoint());
        inventory.setUpdatedAt(inventoryDto.getUpdatedAt());
        inventory.setCreatedAt(inventoryDto.getCreatedAt());
        return inventory;
    }
}
