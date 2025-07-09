package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devsuperior.dsmeta.dto.SaleSellerDTO;
import com.devsuperior.dsmeta.dto.SaleSellerMinDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

    public List<SaleSellerMinDTO> searchSeller(String minDate, String maxDate) {

		LocalDate today = null;
		if (minDate.isEmpty() && maxDate.isEmpty()){
			today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			LocalDate result = today.minusYears(1L);
			return repository.searchSeller(result, today);
		}

		LocalDate minDate2 = LocalDate.parse(minDate);
		LocalDate maxDate2 = LocalDate.parse(maxDate);

		return repository.searchSeller(minDate2, maxDate2);



	}

	public Page<SaleSellerDTO> searchSaleSeller(String minDate, String maxDate, String name, Pageable pageable) {
		LocalDate today = null;
		if (minDate.isEmpty() && maxDate.isEmpty() && name.isEmpty()){
			today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
			LocalDate result = today.minusYears(1L);

			Page<SaleSellerDTO> list = repository.searchSaleSeller(result, today, name, pageable)
					.map(SaleSellerDTO::new);

			return list;
		}

		LocalDate minDate2 = LocalDate.parse(minDate);
		LocalDate maxDate2 = LocalDate.parse(maxDate);

		return repository.searchSaleSeller(minDate2, maxDate2, name, pageable)
				.map(SaleSellerDTO::new);

	}
}
