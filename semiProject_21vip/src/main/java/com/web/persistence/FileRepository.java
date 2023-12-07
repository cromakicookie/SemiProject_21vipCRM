package com.web.persistence;

import org.springframework.data.repository.CrudRepository;

import com.web.domain.File;

public interface FileRepository extends CrudRepository<File, Long>{

}
