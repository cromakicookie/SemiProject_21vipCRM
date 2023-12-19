package com.web.persistence;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.domain.Member;
import com.web.domain.Role;

public interface MainRepositoryCustom {
	Page<Member> findBySearchOption(Pageable pageable, String memberDept, Role memberRole, String memberName);
}
