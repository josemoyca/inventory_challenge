package demo.retail.inventory.models.mapper;

import demo.retail.inventory.models.DTO.SalesDTO;
import demo.retail.inventory.models.Sales;

public class SalesMapper {
    public static Sales getSales(SalesDTO salesDTO) {
        Sales sales = new Sales();
        sales.setId(salesDTO.getId());
        sales.setProductId(salesDTO.getProductId());
        sales.setType(salesDTO.getType());
        sales.setQuantity(salesDTO.getQuantity());
        sales.setDiscountApplied(salesDTO.getDiscountApplied());
        sales.setUpdatedAt(salesDTO.getUpdatedAt());
        sales.setCreatedAt(salesDTO.getCreatedAt());
        return sales;
    }

    public static SalesDTO getSalesDTO(Sales sales) {
        SalesDTO salesDTO = new SalesDTO();
        salesDTO.setId(sales.getId());
        salesDTO.setProductId(sales.getProductId());
        salesDTO.setType(sales.getType());
        salesDTO.setQuantity(sales.getQuantity());
        salesDTO.setDiscountApplied(sales.getDiscountApplied());
        salesDTO.setUpdatedAt(sales.getUpdatedAt());
        salesDTO.setCreatedAt(sales.getCreatedAt());
        return salesDTO;
    }
}
