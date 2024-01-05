package com.web.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.domain.Member;
import com.web.domain.dataFile;
import com.web.service.FileService;
import com.web.service.MainService;

@RestController
@RequestMapping("/file")
@Controller
public class FileController {

	@Autowired
	private FileService fs;

	@Autowired
	private MainService ms;

	@GetMapping("/{num}")
	public ResponseEntity<dataFile> getFile(@PathVariable("num") Long num) {
		dataFile file = fs.getFile(num);
		return new ResponseEntity<>(file, HttpStatus.OK);
	}

	@PostMapping("/upload")
	public ResponseEntity<Long> upload(@RequestParam("file") MultipartFile uploadFiles,
			@RequestParam("username") String username) throws IOException {
		System.out.println("파일 업로드");
		Long fileNumber = fs.uploadFile(uploadFiles);
		ms.updateFile(username, fileNumber);
		return new ResponseEntity<>(fileNumber, HttpStatus.CREATED);
	}

	@PostMapping("/upload/user")
	public ResponseEntity<Member> uploadUser(@RequestBody Member mem) {
		System.out.println("유저 수정");
		Member member = new Member();
		member.setMemberEmail(mem.getMemberEmail());
		member.setUsername(mem.getUsername());
		member.setPassword(mem.getPassword());
		ms.updateMember(member);
		member = ms.getMember(member);
		return new ResponseEntity<>(member, HttpStatus.CREATED);
	}
}
