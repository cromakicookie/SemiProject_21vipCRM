package com.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.persistence.FileRepository;

@Service
public class FileServiceImpl {

	@Autowired
	FileRepository fr;
	
	
	
}
