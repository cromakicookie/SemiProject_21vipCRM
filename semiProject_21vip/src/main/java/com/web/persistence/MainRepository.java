package com.web.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.web.domain.Member;

public interface MainRepository extends CrudRepository<Member, String>{

	public Member findByUsername(String username);
}
