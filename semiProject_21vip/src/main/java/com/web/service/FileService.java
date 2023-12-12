package com.web.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.web.domain.dataFile;

public interface FileService {

	dataFile getFile(Long fileId);

	void deleteFile(Long fileId);

	Long uploadFile(MultipartFile file) throws IOException;



}
