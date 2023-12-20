package demo.retail.inventory.models.DTO;

import java.time.LocalDateTime;
import java.util.Objects;

public class SalesDTO {
    private String Id;
    private String productId;
    private String type;
    private Integer quantity;
    private Double discountApplied;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getDiscountApplied() {
        return discountApplied;
    }

    public void setDiscountApplied(Double discountApplied) {
        this.discountApplied = discountApplied;
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
        SalesDTO saleDTO = (SalesDTO) object;
        return Objects.equals(Id, saleDTO.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SalesDTO{");
        sb.append("Id='").append(Id).append('\'');
        sb.append(", productId='").append(productId).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", quantity=").append(quantity);
        sb.append(", discountApplied=").append(discountApplied);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append('}');
        return sb.toString();
    }
}
