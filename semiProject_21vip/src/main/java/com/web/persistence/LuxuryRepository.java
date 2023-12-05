package com.web.persistence;

import org.springframework.data.repository.CrudRepository;

import com.web.domain.Luxury;

public interface LuxuryRepository extends CrudRepository<Luxury, Long> {
	
}
