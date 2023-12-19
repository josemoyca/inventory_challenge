package demo.retail.inventory.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document("sales")
public class Sales {
    @Id
    private String Id;
    private String productId;
    private String type;
    private String quantity;
    private String discountApplied;
    @LastModifiedDate
    private String updatedAt;
    @CreatedDate
    private String createdAt;

    public Sales() {
    }

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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDiscountApplied() {
        return discountApplied;
    }

    public void setDiscountApplied(String discountApplied) {
        this.discountApplied = discountApplied;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Sales sales = (Sales) object;
        return Objects.equals(Id, sales.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Sales{");
        sb.append("Id='").append(Id).append('\'');
        sb.append(", productId='").append(productId).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", quantity='").append(quantity).append('\'');
        sb.append(", discountApplied='").append(discountApplied).append('\'');
        sb.append(", updatedAt='").append(updatedAt).append('\'');
        sb.append(", createdAt='").append(createdAt).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
