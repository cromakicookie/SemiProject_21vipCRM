package com.web.persistence;

import org.springframework.data.repository.CrudRepository;

import com.web.domain.dataFile;

public interface FileRepository extends CrudRepository<dataFile, Long>{

}
