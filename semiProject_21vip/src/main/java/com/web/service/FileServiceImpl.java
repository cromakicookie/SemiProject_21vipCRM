package com.web.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.web.domain.dataFile;
import com.web.persistence.FileRepository;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	FileRepository fr;
	
	@Value("${upload.dir}")
	private String uploadDir;

	@Override
	public Long uploadFile(MultipartFile file) throws IOException {

		// 파일을 업로드하고 DB에 저장
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        System.out.println(fileName);

        // 프로젝트 내부 파일 지정
        Path uploadPath = Path.of(uploadDir);

        // 디렉토리가 존재하지 않으면 생성
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // ID 바로 돌려주기...
        dataFile fileInfo = new dataFile();
        fileInfo.setFileName(fileName);
        fileInfo.setFileRoot("/uploadFile/");
        fileInfo = fr.save(fileInfo);

        Long fileNumber = fileInfo.getFileNumber();

        // 파일 복사 (try-with-resources 사용)
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace(); // 복사 중에 오류가 발생하면 예외 처리
        }

		return fileNumber;
	}

	@Override
	public dataFile getFile(Long fileId) {
		return fr.findById(fileId).get();
	}

	@Override
	public void deleteFile(Long fileId) {
		dataFile files = new dataFile();
		files.setFileNumber(fileId);
		dataFile data = fr.findById(fileId).get();

		Path uploadPath = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "static", "uploadFile",
				data.getFileName());

		try {
			Files.delete(uploadPath);
			System.out.println("삭제 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("삭제 실패");
		}
		fr.delete(files);
	}

}
