
package com.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.domain.MailDTO;
import com.web.domain.Member;
import com.web.service.EmailSendService;
import com.web.service.MainService;

@Controller
public class FindPwController {
	
	@Autowired
	MainService mainService;
	
	@Autowired
	EmailSendService emailSendService;
	
	 //Email과 name의 일치여부를 check하는 컨트롤러
	 @PostMapping("/check/findPw")
	    public String pw_find(Member member){
	        boolean pwFindCheck = mainService.memberEmailCheck(member.getMemberEmail(), member.getUsername());
	        if(pwFindCheck) {
	        	MailDTO dto = emailSendService.createMailAndChangePassword(member.getMemberEmail(), member.getUsername());
	        	System.out.println(dto);
		        emailSendService.mailSend(dto);
		        return "main/updatePwSuccess";
	        }
	        return "main/updatePwFail";
	    }

	//등록된 이메일로 임시비밀번호를 발송하고 발송된 임시비밀번호로 사용자의 pw를 변경하는 컨트롤러
//	    @PostMapping("/check/findPw/sendEmail")
//	    public @ResponseBody void sendEmail(String memberEmail, String username){
//	        MailDTO dto = emailSendService.createMailAndChangePassword(memberEmail, username);
//	        emailSendService.mailSend(dto);
//
//	    }
}

