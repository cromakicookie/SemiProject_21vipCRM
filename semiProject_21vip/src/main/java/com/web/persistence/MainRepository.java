package com.web.persistence;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.web.domain.Member;

public interface MainRepository extends CrudRepository<Member, String>{

	public Member findByUsername(String username);
	public Member findUsernameByMemberEmail(String memberEmail);
	public Member findMemberNameByUsername(String username);
	public List<Member> findUsernameByMemberNameAndMemberBirth(String memberName, String memberBirth);

	@Modifying
	@Query("UPDATE Member m SET m.password = :password where m.username = :username")
	int updateMemberPassword(String username, String password);
	
	public List<Member> findAll(Sort by);
	
}
