package demo.retail.inventory.models;

public enum SalesTypes {
    WHOLESALE("Wholesale"),
    DETAIL("Detail");
    private final String name;

    SalesTypes(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name.toUpperCase();
    }
}
