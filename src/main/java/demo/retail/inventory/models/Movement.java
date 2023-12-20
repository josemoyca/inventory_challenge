package demo.retail.inventory.models;

import java.util.Objects;

public class Movement {
    private String id;
    private String inventoryId;
    private String type;
    private Integer quantity;
    private String movementDate;

    public Movement() {
    }

    public Movement(String inventoryId, String type, Integer quantity, String movementDate) {
        this.inventoryId = inventoryId;
        this.type = type;
        this.quantity = quantity;
        this.movementDate = movementDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
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

    public String getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(String movementDate) {
        this.movementDate = movementDate;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Movement movement = (Movement) object;
        return Objects.equals(id, movement.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Movement{");
        sb.append("id='").append(id).append('\'');
        sb.append(", inventoryId='").append(inventoryId).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", quantity=").append(quantity);
        sb.append(", movementDate='").append(movementDate).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
