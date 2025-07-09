package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleSellerProjection;

import java.time.LocalDate;

public class SaleSellerDTO {

    private Long id;
    private LocalDate date;
    private Double totalAmount;
    private String sellerName;

    public SaleSellerDTO(Long id, LocalDate date, Double totalAmount, String sellerName) {
        this.id = id;
        this.date = date;
        this.totalAmount = totalAmount;
        this.sellerName = sellerName;
    }

    public SaleSellerDTO(SaleSellerProjection projection) {
        this.id = projection.getId();
        this.date = projection.getDate();
        this.totalAmount = projection.getAmount();
        this.sellerName = projection.getName();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public String getSellerName() {
        return sellerName;
    }
}
