package com.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.domain.MailDTO;
import com.web.domain.Member;
import com.web.persistence.MainRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class EmailSendService{

    @Autowired
    MainRepository mainRepo;
    
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "ihpb1727@gmail.com";



    public MailDTO createMailAndChangePassword(String memberEmail, String username){
        String str = getTempPassword();
        MailDTO dto = new MailDTO();
        dto.setAddress(memberEmail);
        dto.setTitle("21VIP SYSTEM 임시비밀번호 발급");
        dto.setMessage("안녕하세요. " + username +"님. 21VIP SYSTEM 임시비밀번호 발급해드립니다. "+ username +"님의 임시 비밀번호는 "
        + str + "입니다.");
        updatePassword(str,memberEmail);
        return dto;
    }

    public void updatePassword(String str, String memberEmail){
        String password = bCryptPasswordEncoder.encode(str);
        Member member = mainRepo.findUsernameByMemberEmail(memberEmail);
        
        if (member != null) {
            member.setPassword(password);
//            member.setPassword(str);
            mainRepo.save(member);
        }
    }


    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }
    
    public void mailSend(MailDTO mailDTO){
        System.out.println("이멜 전송 완료!");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDTO.getAddress());
        message.setFrom(EmailSendService.FROM_ADDRESS);
        message.setSubject(mailDTO.getTitle());
        message.setText(mailDTO.getMessage());

        mailSender.send(message);
    }
    
}