package com.web.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.domain.dataFile;
import com.web.service.FileService;


@RestController
@RequestMapping("/file")
@Controller
public class FileController {
	
	@Autowired
	private FileService fs;
	
	@GetMapping("/{num}")
	public ResponseEntity<dataFile> getFile(@PathVariable("num") Long num){
		dataFile file = fs.getFile(num);
		return new ResponseEntity<>(file,HttpStatus.OK);
	}
	
	@PostMapping("/upload")
	public ResponseEntity<Long> upload(@RequestParam("file") MultipartFile uploadFiles) throws IOException {
		System.out.println("파일 업로드");
		Long fileNumber = fs.uploadFile(uploadFiles);
		return new ResponseEntity<>(fileNumber,HttpStatus.CREATED);
	}
}
