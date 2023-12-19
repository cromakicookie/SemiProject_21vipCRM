package com.web.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.web.domain.Member;
import com.web.domain.Role;

public interface MainRepository extends CrudRepository<Member, String>, MainRepositoryCustom{

	public Member findByUsername(String username);
	public Member findUsernameByMemberEmail(String memberEmail);
	public Member findMemberNameByUsername(String username);
	public List<Member> findUsernameByMemberNameAndMemberBirth(String memberName, String memberBirth);

	@Modifying
	@Query("UPDATE Member m SET m.password = :password where m.username = :username")
	int updateMemberPassword(String username, String password);
	
	@Transactional
	@Modifying
	@Query("UPDATE Member m SET m.memberRole = :memberRole"
			+ ", m.memberEmail = :memberEmail"
			+ ", m.memberBirth = :memberBirth"
			+ ", m.memberDept = :memberDept"
			+ " where m.username = :username")
	int updateMemberModal(String username, String memberEmail, String memberBirth, String memberDept, Role memberRole);
	
	public List<Member> findAll(Sort sort);
	
	List<Member> findByMemberNameContaining(String memberName);
	
	public Page<Member> findAll(Pageable pageable);
	
}
