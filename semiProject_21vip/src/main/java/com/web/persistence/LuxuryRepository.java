package com.web.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.web.domain.Luxury;

public interface LuxuryRepository extends JpaRepository<Luxury, Long> {
	
	Page<Luxury> findByCustomerCustomerNumContaining(String searchKeyword, Pageable pageable);
	
	Page<Luxury> findByluxuryBrandNameEquals(String brand, Pageable pageable);
	
	Page<Luxury> findByCustomerCustomerNumContainingAndLuxuryBrandName(String searchKeyword, Pageable pageable, String brand);
	
	
	
	
}
