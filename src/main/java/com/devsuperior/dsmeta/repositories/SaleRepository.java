package com.devsuperior.dsmeta.repositories;


import com.devsuperior.dsmeta.dto.SaleSellerMinDTO;
import com.devsuperior.dsmeta.projections.SaleSellerProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleSellerMinDTO(s.seller.name, SUM(s.amount)) "+
    "FROM Sale s " +
    "WHERE s.date BETWEEN :minDate AND :maxDate " +
    "GROUP BY s.seller.name")
    List<SaleSellerMinDTO> searchSeller(LocalDate minDate, LocalDate maxDate);

    @Query(nativeQuery = true,
            value = "SELECT TB_SALES.ID, TB_SALES.DATE, SUM(TB_SALES.AMOUNT) AS amount, TB_SELLER.NAME " +
                    "FROM TB_SALES " +
                    "INNER JOIN TB_SELLER ON TB_SALES.SELLER_ID = TB_SELLER.ID " +
                    "WHERE TB_SALES.DATE BETWEEN :minDate AND :maxDate " +
                    "AND LOWER(TB_SELLER.NAME) LIKE LOWER(CONCAT('%', :name, '%')) " +
                    "GROUP BY TB_SALES.ID, TB_SALES.DATE, TB_SELLER.NAME",

            countQuery = "SELECT COUNT(*) " +
                    "FROM TB_SALES " +
                    "INNER JOIN TB_SELLER ON TB_SALES.SELLER_ID = TB_SELLER.ID " +
                    "WHERE TB_SALES.DATE BETWEEN :minDate AND :maxDate " +
                    "AND LOWER(TB_SELLER.NAME) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<SaleSellerProjection> searchSaleSeller(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);
}
