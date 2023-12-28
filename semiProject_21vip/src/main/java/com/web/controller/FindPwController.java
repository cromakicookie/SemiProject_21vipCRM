
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
	
	 //Email과 name가 일치할 때 메일 발송
	 @PostMapping("/findPw")
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


}

