package com.devsuperior.dsmeta.projections;

import java.time.LocalDate;

public interface SaleSellerProjection {

    Long getId();
    LocalDate getDate();
    Double getAmount();
    String getName();
}
