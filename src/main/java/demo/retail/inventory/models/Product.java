package demo.retail.inventory.models;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Document("products")
public class Product {
    @Id
    private String id;
    @Indexed(unique = true)
    @Field("code")
    private String code;
    private String description;
    private String brand;
    @Field(name = "cost", targetType = FieldType.DECIMAL128)
    private BigDecimal cost;
    private String vendor;
    private String batch;
    @Field(name = "unitPrice", targetType = FieldType.DECIMAL128)
    private BigDecimal unitPrice;
    private Integer wholesaleQuantity;
    @Field(name = "wholesaleDiscount", targetType = FieldType.DECIMAL128)
    private BigDecimal wholesaleDiscount;
    private Double wholesalePercentage;
    @Field(name = "retailDiscount", targetType = FieldType.DECIMAL128)
    private BigDecimal retailDiscount;
    private Double retailPercentage;
    private LocalDate sellByDate;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @CreatedDate
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

    public Integer getWholesaleQuantity() {
        return wholesaleQuantity;
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

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public void setWholesaleQuantity(Integer wholesaleQuantity) {
        this.wholesaleQuantity = wholesaleQuantity;
    }

    public LocalDate getSellByDate() {
        return sellByDate;
    }

    public void setSellByDate(LocalDate sellByDate) {
        this.sellByDate = sellByDate;
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
        return Objects.equals(code, product.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Product{");
        sb.append("id='").append(id).append('\'');
        sb.append(", code='").append(code).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", brand='").append(brand).append('\'');
        sb.append(", cost=").append(cost);
        sb.append(", vendor='").append(vendor).append('\'');
        sb.append(", batch='").append(batch).append('\'');
        sb.append(", unitPrice=").append(unitPrice);
        sb.append(", wholesaleQuantity=").append(wholesaleQuantity);
        sb.append(", wholesaleDiscount=").append(wholesaleDiscount);
        sb.append(", wholesalePercentage=").append(wholesalePercentage);
        sb.append(", retailDiscount=").append(retailDiscount);
        sb.append(", retailPercentage=").append(retailPercentage);
        sb.append(", sellByDate=").append(sellByDate);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", createdAt=").append(createdAt);
        sb.append('}');
        return sb.toString();
    }
}
