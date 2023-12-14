package com.web;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.web.domain.Voucher;
import com.web.persistence.VoucherRepository;
import com.web.service.VoucherService;

@SpringBootTest
public class VoucherTest {
	
	@Autowired
	private VoucherRepository vRepo;
	
	//startDate:연간VIP서비스시작기간 endDate:연간서비스종료일자
	private Date startDate = dateTryCatch("2023-02-01");
	private Date endDate = dateTryCatch("2024-01-31");
	
	
	//Date 데이터 변환 메서드 (String -> Date)
	public Date dateTryCatch(String dateString) {		
		 try {
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            Date stringToDate = dateFormat.parse(dateString);
	            return stringToDate;

	        } catch (ParseException e) {
	            e.printStackTrace();
	            return null;
	        }
	}
	
	
	
	//바우처 15자리 일련번호 생성기
	public String generateVoucherCode(Long voucherSeq, String name) {
		//특정문자열을 코드로 변환
		//생일-다이닝 : "dining"
		//생일-코스메틱 : "cosmetic"
		//생일-전시회 : "exhibition"
		//블랙S-요트 : "yacht"
		//블랙S-골프 : "golf"
		//블랙S-호텔 : "hotel"
		
	     UUID nameUUID = UUID.nameUUIDFromBytes(name.getBytes());
         System.out.println("Name-based UUID: " + nameUUID);
         
         // UUID를 문자열로 변환하고 '-' 제거
         String uuidString = nameUUID.toString().replaceAll("-", "");

         // 앞에서부터 11자리 추출
         String shortenedUUID = uuidString.substring(0, 11);
         //바우처시퀀스를 00XX 형태로 네자리로 변환
         String formattedVoucherSeq = String.format("%04d", voucherSeq);
         
         //스트링변환메서드String.valueOf();
         return shortenedUUID+formattedVoucherSeq;
    }
	

	
//	@Test
//	public void createVoucherTypeG() {
//		//type:G -- 생일바우처 
//		
//		String VoucherType = "G";
//		int VoucherAmount = 5; //여기서 생성할 바우처의 개수를 지정가능
//		 
//	    
//		//다이닝 5장
//		for (int i = 0; i < VoucherAmount; i++) {
//			Voucher voucher = new Voucher();
//			voucher.setVoucherType(VoucherType);
//			voucher.setVoucherService("생일바우처");
//			voucher.setVoucherServiceName("다이닝이용권");
//			voucher.setVoucherStartDate(startDate);
//			voucher.setVoucherEndDate(endDate);
//			
//			
//			// 엔터티를 먼저 저장한 후에 voucherSeq를 얻을 수 있음
//		    Voucher savedVoucher = vRepo.save(voucher);
//			
//		    // 저장된 엔터티의 voucherSeq를 이용하여 voucherCode를 생성
//		    savedVoucher.setVoucherCode(VoucherType+generateVoucherCode(savedVoucher.getVoucherSeq(), "dining"));
//		    
//		    // 다시 저장
//		    vRepo.save(savedVoucher);
//		}
//		
//		//코스메틱 5장
//		for (int i = 0; i < VoucherAmount; i++) {
//			Voucher voucher = new Voucher();
//			voucher.setVoucherType(VoucherType);
//			voucher.setVoucherService("생일바우처");
//			voucher.setVoucherServiceName("코스메틱상품권");
//			voucher.setVoucherStartDate(startDate);
//			voucher.setVoucherEndDate(endDate);
//			
//			// 엔터티를 먼저 저장한 후에 voucherSeq를 얻을 수 있음
//		    Voucher savedVoucher = vRepo.save(voucher);
//
//		    // 저장된 엔터티의 voucherSeq를 이용하여 voucherCode를 생성
//		    savedVoucher.setVoucherCode(VoucherType+generateVoucherCode(savedVoucher.getVoucherSeq(), "cosmetic"));
//		    
//		    // 다시 저장
//		    vRepo.save(savedVoucher);
//		}
//		
//		//전시회 5장
//		for (int i = 0; i < VoucherAmount; i++) {
//			Voucher voucher = new Voucher();
//			voucher.setVoucherType(VoucherType);
//			voucher.setVoucherService("생일바우처");
//			voucher.setVoucherServiceName("전시회관람권");
//			voucher.setVoucherStartDate(startDate);
//			voucher.setVoucherEndDate(endDate);
//			
//			// 엔터티를 먼저 저장한 후에 voucherSeq를 얻을 수 있음
//		    Voucher savedVoucher = vRepo.save(voucher);
//
//		    // 저장된 엔터티의 voucherSeq를 이용하여 voucherCode를 생성
//		    savedVoucher.setVoucherCode(VoucherType+generateVoucherCode(savedVoucher.getVoucherSeq(), "exhibition"));
//		    
//		    // 다시 저장
//		    vRepo.save(savedVoucher);
//		}					
//	}
	
//	@Test
//	public void createVoucherTypeS() {
//		//type:S  블랙 스페셜 바우처
//		
//		String VoucherType = "S";
//		int VoucherAmount = 3; //여기서 생성할 바우처의 개수를 지정가능
//	    
//		//프라이빗요트투어
//		for (int i = 0; i < VoucherAmount; i++) {
//			Voucher voucher = new Voucher();
//			voucher.setVoucherType(VoucherType);
//			voucher.setVoucherService("블랙서비스S");
//			voucher.setVoucherServiceName("프라이빗요트투어이용권");
//			voucher.setVoucherStartDate(startDate);
//			voucher.setVoucherEndDate(endDate);
//			
//			// 엔터티를 먼저 저장한 후에 voucherSeq를 얻을 수 있음
//		    Voucher savedVoucher = vRepo.save(voucher);
//		    savedVoucher.setVoucherCode(VoucherType+generateVoucherCode(savedVoucher.getVoucherSeq(), "yacht"));    
//		    vRepo.save(savedVoucher);
//		}
//		
//		//프라이빗골프
//		for (int i = 0; i < VoucherAmount; i++) {
//			Voucher voucher = new Voucher();
//			voucher.setVoucherType(VoucherType);
//			voucher.setVoucherService("블랙서비스S");
//			voucher.setVoucherServiceName("프라이빗골프레슨권");
//			voucher.setVoucherStartDate(startDate);
//			voucher.setVoucherEndDate(endDate);
//			
//			// 엔터티를 먼저 저장한 후에 voucherSeq를 얻을 수 있음
//		    Voucher savedVoucher = vRepo.save(voucher);
//		    savedVoucher.setVoucherCode(VoucherType+generateVoucherCode(savedVoucher.getVoucherSeq(), "golf"));    
//		    vRepo.save(savedVoucher);
//		}
//		
//		
//		//호텔스위트룸패키지
//		for (int i = 0; i < VoucherAmount; i++) {
//			Voucher voucher = new Voucher();
//			voucher.setVoucherType(VoucherType);
//			voucher.setVoucherService("블랙서비스S");
//			voucher.setVoucherServiceName("호텔스위트룸패키지");
//			voucher.setVoucherStartDate(startDate);
//			voucher.setVoucherEndDate(endDate);
//			
//			// 엔터티를 먼저 저장한 후에 voucherSeq를 얻을 수 있음
//		    Voucher savedVoucher = vRepo.save(voucher);
//		    savedVoucher.setVoucherCode(VoucherType+generateVoucherCode(savedVoucher.getVoucherSeq(), "hotel"));    
//		    vRepo.save(savedVoucher);
//		}		
//	}
//	
//	@Test
//	public void createVoucherTypeA() {
//		//type:A 는 1티어 바우처
//			
//	}
//	
//	@Test
//	public void createVoucherTypeB() {
//		//type:B 는 2티어바우처
//			
//	}
	
	//Voucher 테이블의 모든 레코드 삭제하기
//	@Test
//	public void deleteVoucher() {
//		try {
//			vRepo.deleteAll();
//		} catch (Exception e) {
//			//e.printStackTrace();
//			System.out.println("삭제할 값이 없습니다.");
//		}
//		
//	}
	
	
	@Test
	public void test() {
		
		Voucher hehe = new Voucher();
		hehe = vRepo.findByVoucherCode("Ge9827b747000007");

		System.out.println(hehe.getVoucherServiceName()+"/"+hehe.getVoucherService());
	}
	
	
	

}
