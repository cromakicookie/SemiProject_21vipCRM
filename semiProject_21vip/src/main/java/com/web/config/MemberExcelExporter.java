package com.web.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.web.domain.Member;

public class MemberExcelExporter {
	
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	
	private List<Member> listMembers;
	
	public MemberExcelExporter(List<Member> listMembers) {
		this.listMembers = listMembers;
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Members");
	}

	//컬럼명
	private void writeHeaderRow() {
		Row row = sheet.createRow(0);
		
		Cell cell = row.createCell(0);
		cell.setCellValue("사원ID");
		
		cell = row.createCell(1);
		cell.setCellValue("사원명");
		
		cell = row.createCell(2);
		cell.setCellValue("부서");
		
		cell = row.createCell(3);
		cell.setCellValue("직급");
		
		cell = row.createCell(4);
		cell.setCellValue("생년월일");
		
		cell = row.createCell(5);
		cell.setCellValue("이메일");
	}
	
	private void wirteDataRows() {
		int rowCount =1; //두번째 열부터
		
		for(Member member : listMembers) {
			Row row = sheet.createRow(rowCount++);
			
			Cell cell = row.createCell(0);
			cell.setCellValue(member.getUsername());
			
			cell = row.createCell(1);
			cell.setCellValue(member.getMemberName());
			
			cell = row.createCell(2);
			cell.setCellValue(member.getMemberDept());
			
			cell = row.createCell(3);
			cell.setCellValue(member.getMemberRole().getRole());
			
			cell = row.createCell(4);
			cell.setCellValue(member.getMemberBirth());
			
			cell = row.createCell(5);
			cell.setCellValue(member.getMemberEmail());
		}
	}
	
	public void export(HttpServletResponse response) throws IOException {
		writeHeaderRow();
		wirteDataRows();
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
	
}
