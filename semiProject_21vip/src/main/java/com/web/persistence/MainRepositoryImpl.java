package com.web.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.domain.Member;
import com.web.domain.QMember;
import com.web.domain.Role;


public class MainRepositoryImpl extends QuerydslRepositorySupport implements MainRepositoryCustom{

	@Autowired
	private JPAQueryFactory queryFactory;
	
	public MainRepositoryImpl() {
		super(Member.class);
	}
	
	QMember member = QMember.member;

	@Override
	public Page<Member> findBySearchOption(Pageable pageable, String memberDept, Role memberRole, String memberName) {
		JPQLQuery<Member> query = queryFactory.selectFrom(member)
	            .where(eqMemberDept(memberDept), eqmemberRole(memberRole), containName(memberName));

	    JPQLQuery<Member> appliedQuery = this.getQuerydsl().applyPagination(pageable, query);

	    List<Member> members = appliedQuery.fetch();
	    long total = appliedQuery.fetchCount();

	    return new PageImpl<Member>(members, pageable, total);
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
