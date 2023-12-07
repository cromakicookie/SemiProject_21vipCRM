package com.web.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.web.domain.Luxury;

public interface LuxuryPageRepository extends JpaRepository<Luxury, Long> {
	
	Page<Luxury> findBycustomerNumContaining(String searchKeyword, Pageable pageable);
	
	
	
	
	
}
