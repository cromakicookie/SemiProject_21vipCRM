package com.web.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.web.domain.dataFile;
import com.web.persistence.FileRepository;

@Service
public class FileServiceImpl {

	@Autowired
	FileRepository fr;
	
	public void insertFile(MultipartFile file) {
		try {
            // 파일을 업로드하고 DB에 저장
            dataFile uploadedFile = new dataFile();
            uploadedFile.setFileName(file.getOriginalFilename());
            fr.save(uploadedFile);
            file.transferTo(new File("uploadFile/"+file.getOriginalFilename()));
            // 실제 파일 저장 로직은 여기에 추가
            // 예: file.transferTo(new File("저장경로/" + file.getOriginalFilename()));
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
}
