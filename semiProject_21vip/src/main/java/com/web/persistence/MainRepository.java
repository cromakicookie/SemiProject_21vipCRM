package com.web.persistence;

import org.springframework.data.repository.CrudRepository;

import com.web.domain.User;

public interface MainRepository extends CrudRepository<User, String>{

}
