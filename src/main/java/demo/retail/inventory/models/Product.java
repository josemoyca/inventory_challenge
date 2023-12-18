package demo.retail.inventory.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Document("Product")
public class Product {
    @Id
    private String id;
    private String code;
    private String description;
    private String brand;
    private BigDecimal unitPrice;
    private BigDecimal wholesaleAmount;
    private BigDecimal wholesaleDiscount;
    private Double wholesalePercentage;
    private BigDecimal retailAmount;
    private BigDecimal retailDiscount;
    private Double retailPercentage;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getWholesaleAmount() {
        return wholesaleAmount;
    }

    public void setWholesaleAmount(BigDecimal wholesaleAmount) {
        this.wholesaleAmount = wholesaleAmount;
    }

    public BigDecimal getWholesaleDiscount() {
        return wholesaleDiscount;
    }

    public void setWholesaleDiscount(BigDecimal wholesaleDiscount) {
        this.wholesaleDiscount = wholesaleDiscount;
    }

    public Double getWholesalePercentage() {
        return wholesalePercentage;
    }

    public void setWholesalePercentage(Double wholesalePercentage) {
        this.wholesalePercentage = wholesalePercentage;
    }

    public BigDecimal getRetailAmount() {
        return retailAmount;
    }

    public void setRetailAmount(BigDecimal retailAmount) {
        this.retailAmount = retailAmount;
    }

    public BigDecimal getRetailDiscount() {
        return retailDiscount;
    }

    public void setRetailDiscount(BigDecimal retailDiscount) {
        this.retailDiscount = retailDiscount;
    }

    public Double getRetailPercentage() {
        return retailPercentage;
    }

    public void setRetailPercentage(Double retailPercentage) {
        this.retailPercentage = retailPercentage;
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
        Product product = (Product) object;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Product{");
        sb.append("id='").append(id).append('\'');
        sb.append(", code='").append(code).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", brand='").append(brand).append('\'');
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", wholesaleAmount=").append(wholesaleAmount);
        sb.append(", wholesaleDiscount=").append(wholesaleDiscount);
        sb.append(", wholesalePercentage=").append(wholesalePercentage);
        sb.append(", retailAmount=").append(retailAmount);
        sb.append(", retailDiscount=").append(retailDiscount);
        sb.append(", retailPercentage=").append(retailPercentage);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append('}');
        return sb.toString();
    }
}
