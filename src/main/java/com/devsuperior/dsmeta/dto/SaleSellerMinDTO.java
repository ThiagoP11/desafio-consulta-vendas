package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;

public class SaleSellerMinDTO {

    private String sallerName;
    private Double total;

    public SaleSellerMinDTO() {
    }

    public SaleSellerMinDTO(String sallerName, Double total) {
        this.sallerName = sallerName;
        this.total = total;
    }

    public SaleSellerMinDTO(Sale entity) {
        this.sallerName = entity.getSeller().getName();
        this.total = entity.getAmount();
    }

    public Double getTotal() {
        return total;
    }

    public String getSallerName() {
        return sallerName;
    }
}
