package com.web;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.config.SecurityConfig;
import com.web.domain.Member;
import com.web.domain.QMember;
import com.web.domain.Role;
import com.web.persistence.MainRepository;

@SpringBootTest
public class MainTest {
	
	@Autowired
	private JPAQueryFactory queryFactory;
	
	
	QMember member = QMember.member;
	
	@Test
	public void findBySearchOption() {
		JPQLQuery<Member> query = queryFactory.selectFrom(member)
	            .where(eqMemberDept("a"), eqmemberRole(Role.ROLE_ADMIN), containName("te"));

	    System.out.println(query);
	}
		
		
		private BooleanExpression eqMemberDept(String memberDept) {
	        if(memberDept == null || memberDept.isEmpty()) {
	            return null;
	        }
	        return member.memberDept.eq(memberDept);
	    }
		
		private BooleanExpression eqmemberRole(Role memberRole) {
			if(memberRole == null || memberRole.toString().isEmpty()) {
				return null;
			}
			return member.memberRole.eq(memberRole);
		}


	private BooleanExpression containName(String memberName) {
	    if(memberName == null || memberName.isEmpty()) {
	        return null;
	    }
	    return member.memberName.containsIgnoreCase(memberName);
	}

}
