package com.web.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.web.domain.Luxury;

public interface LuxuryPageRepository extends JpaRepository<Luxury, Long> {
	
//	Page<Luxury> findByCustomerNumContaining(String searchKeyword, Pageable pageable);
	
	Page<Luxury> findByluxuryBrandNameEquals(String brand, Pageable pageable);
	
//	Page<Luxury> findByCustomerNumContainingAndLuxuryBrandNameEquals(String searchKeyword, Pageable pageable, String brand);
	
}
