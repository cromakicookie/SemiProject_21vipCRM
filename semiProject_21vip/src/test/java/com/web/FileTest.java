package com.web;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FileTest {
	
	
	@Test
	public void Testtt() {
		Path filePath = Path.of("경로/파일명");

        try (FileChannel channel = FileChannel.open(filePath, StandardOpenOption.READ)) {
            // 파일 핸들이 정상적으로 열렸을 때의 처리
            System.out.println("파일 핸들 확인: 열림");
        } catch (Exception e) {
            // 파일이 열려 있지 않을 때의 처리
            System.out.println("파일 핸들 확인: 닫힘");
        }
	}
}
