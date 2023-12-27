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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.domain.Voucher;
import com.web.persistence.CustomerRepository;
import com.web.persistence.VoucherCRepository;
import com.web.persistence.VoucherRepository;
import com.web.service.CustomerService;
import com.web.service.VoucherService;

@SpringBootTest
public class VoucherTest {
	
	@Autowired
	private VoucherRepository vRepo;
	
	@Autowired
	private VoucherCRepository vcRepo;
	
	@Autowired
	private CustomerService cs;
	
	
//	@Test
//	public void deleteCustomer() {
//		cs.deleteCustomer("C6736");
//	}
	
	
	
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
	
	//바우처 UUID 11자리
	public String generateUUIDCode_11(String word) {
		//특정문자열을 코드로 변환
		//생일-다이닝 : "dining"
		//생일-코스메틱 : "cosmetic"
		//생일-전시회 : "exhibition"
		//블랙S-요트 : "yacht"
		//블랙S-골프 : "golf"
		//블랙S-호텔 : "hotel"
		
	     UUID nameUUID = UUID.nameUUIDFromBytes(word.getBytes());
         System.out.println("Name-based UUID: " + nameUUID);
         
         // UUID를 문자열로 변환하고 '-' 제거
         String uuidString = nameUUID.toString().replaceAll("-", "");

         // 앞에서부터 11자리 추출
         String shortenedUUID = uuidString.substring(0, 11);
       
         return shortenedUUID;
    }
//	
//	@Test
//	public void test() {
//		
//		String dining = generateUUIDCode_11("dining");
//		String cosmetic = generateUUIDCode_11("cosmetic");
//		String exhibition = generateUUIDCode_11("exhibition");
//		String yacht = generateUUIDCode_11("yacht");
//		String golf = generateUUIDCode_11("golf");
//		String hotel = generateUUIDCode_11("hotel");
//	
//		
//		System.out.println("dining : " + dining);
//		System.out.println("cosmetic : " + cosmetic);
//		System.out.println("exhibition : " + exhibition);
//		System.out.println("yacht : " + yacht);
//		System.out.println("golf : " + golf);
//		System.out.println("hotel : " + hotel);
//		
//	}
	
	
	
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
//		int VoucherAmount = 1; //여기서 생성할 바우처의 개수를 지정가능
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
//	
//	@Test
//	public void createVoucherTypeS() {
//		//type:S  블랙 스페셜 바우처
//		
//		String VoucherType = "S";
//		int VoucherAmount = 1; //여기서 생성할 바우처의 개수를 지정가능
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
//		//type:A 는 블루
//		
//		String VoucherType = "A";
//		int VoucherAmount = 1; //여기서 생성할 바우처의 개수를 지정가능
//	    
//		//다이닝 3장
//		for (int i = 0; i < VoucherAmount; i++) {
//			Voucher voucher = new Voucher();
//			voucher.setVoucherType(VoucherType);
//			voucher.setVoucherService("블루서비스A");
//			voucher.setVoucherServiceName("다이닝 이용권 : 10만원");
//			voucher.setVoucherStartDate(startDate);
//			voucher.setVoucherEndDate(endDate);	
//			
//			// 엔터티를 먼저 저장한 후에 voucherSeq를 얻을 수 있음
//		    Voucher savedVoucher = vRepo.save(voucher);
//		    savedVoucher.setVoucherCode(VoucherType+generateVoucherCode(savedVoucher.getVoucherSeq(), "dining"));		    
//		    vRepo.save(savedVoucher);
//		} 
//		
//		//문화센터 3장
//		for (int i = 0; i < VoucherAmount; i++) {
//			Voucher voucher = new Voucher();
//			voucher.setVoucherType(VoucherType);
//			voucher.setVoucherService("블루서비스A");
//			voucher.setVoucherServiceName("문화센터이용권");
//			voucher.setVoucherStartDate(startDate);
//			voucher.setVoucherEndDate(endDate);	
//			
//			// 엔터티를 먼저 저장한 후에 voucherSeq를 얻을 수 있음
//		    Voucher savedVoucher = vRepo.save(voucher);
//		    savedVoucher.setVoucherCode(VoucherType+generateVoucherCode(savedVoucher.getVoucherSeq(), "culture"));		    
//		    vRepo.save(savedVoucher);
//		} 
//		
//		//스파 3장
//		for (int i = 0; i < VoucherAmount; i++) {
//			Voucher voucher = new Voucher();
//			voucher.setVoucherType(VoucherType);
//			voucher.setVoucherService("블루서비스A");
//			voucher.setVoucherServiceName("스파이용권");
//			voucher.setVoucherStartDate(startDate);
//			voucher.setVoucherEndDate(endDate);	
//			
//			// 엔터티를 먼저 저장한 후에 voucherSeq를 얻을 수 있음
//		    Voucher savedVoucher = vRepo.save(voucher);
//		    savedVoucher.setVoucherCode(VoucherType+generateVoucherCode(savedVoucher.getVoucherSeq(), "spa"));		    
//		    vRepo.save(savedVoucher);
//		} 
//			
//	}
//	
//	@Test
//	public void createVoucherTypeB() {
//		//type:B 는 레드
//		
//		String VoucherType = "B";
//		int VoucherAmount = 1; //여기서 생성할 바우처의 개수를 지정가능
//	    
//		//다이닝 3장
//		for (int i = 0; i < VoucherAmount; i++) {
//			Voucher voucher = new Voucher();
//			voucher.setVoucherType(VoucherType);
//			voucher.setVoucherService("레드서비스B");
//			voucher.setVoucherServiceName("다이닝이용권 : 7만원");
//			voucher.setVoucherStartDate(startDate);
//			voucher.setVoucherEndDate(endDate);	
//			
//			// 엔터티를 먼저 저장한 후에 voucherSeq를 얻을 수 있음
//		    Voucher savedVoucher = vRepo.save(voucher);
//		    savedVoucher.setVoucherCode(VoucherType+generateVoucherCode(savedVoucher.getVoucherSeq(), "dining"));		    
//		    vRepo.save(savedVoucher);
//		} 
//		
//		//문화센터 3장
//		for (int i = 0; i < VoucherAmount; i++) {
//			Voucher voucher = new Voucher();
//			voucher.setVoucherType(VoucherType);
//			voucher.setVoucherService("레드서비스B");
//			voucher.setVoucherServiceName("문화센터이용권");
//			voucher.setVoucherStartDate(startDate);
//			voucher.setVoucherEndDate(endDate);	
//			
//			// 엔터티를 먼저 저장한 후에 voucherSeq를 얻을 수 있음
//		    Voucher savedVoucher = vRepo.save(voucher);
//		    savedVoucher.setVoucherCode(VoucherType+generateVoucherCode(savedVoucher.getVoucherSeq(), "culture"));		    
//		    vRepo.save(savedVoucher);
//		} 
//			
//	}
	
	//Voucher 테이블의 모든 레코드 삭제하기
//	@Test
//	public void deleteVoucher() {
//		try {
//			//vRepo.deleteAll();
//			vcRepo.deleteAll();
//		} catch (Exception e) {
//			//e.printStackTrace();
//			System.out.println("삭제할 값이 없습니다.");
//		}
//		
//	}
	
	
//	@Test
//	public void test() {
//		
//		Voucher hehe = new Voucher();
//		hehe = vRepo.findByVoucherCode("Ge9827b747000007");
//
//		System.out.println(hehe.getVoucherServiceName()+"/"+hehe.getVoucherService());
//	}
	
	
	

}
