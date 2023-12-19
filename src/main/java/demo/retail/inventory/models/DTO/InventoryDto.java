package demo.retail.inventory.models.DTO;

import demo.retail.inventory.models.Product;

import java.time.LocalDateTime;
import java.util.Objects;

public class InventoryDto {
    private String Id;
    private Product product;
    private Integer availability;
    private Integer minStockLevel;
    private Integer maxStockLevel;
    private Integer reorderPoint;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public InventoryDto() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    public Integer getMinStockLevel() {
        return minStockLevel;
    }

    public void setMinStockLevel(Integer minStockLevel) {
        this.minStockLevel = minStockLevel;
    }

    public Integer getMaxStockLevel() {
        return maxStockLevel;
    }

    public void setMaxStockLevel(Integer maxStockLevel) {
        this.maxStockLevel = maxStockLevel;
    }

    public Integer getReorderPoint() {
        return reorderPoint;
    }

    public void setReorderPoint(Integer reorderPoint) {
        this.reorderPoint = reorderPoint;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        InventoryDto that = (InventoryDto) object;
        return Objects.equals(Id, that.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("InventoryDto{");
        sb.append("Id='").append(Id).append('\'');
        sb.append(", product=").append(product);
        sb.append(", availability=").append(availability);
        sb.append(", minStockLevel=").append(minStockLevel);
        sb.append(", maxStockLevel=").append(maxStockLevel);
        sb.append(", reorderPoint=").append(reorderPoint);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append('}');
        return sb.toString();
    }
}
