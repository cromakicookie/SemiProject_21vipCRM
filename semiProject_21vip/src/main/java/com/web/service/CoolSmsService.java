package com.web.service;

import org.springframework.stereotype.Service;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Service
public class CoolSmsService {

	private static final String API_KEY = "NCSGXGABW6RZ14BI";
    private static final String SECRET_KEY = "EIXUMFQLDIV1HA5AOLJQ36MBJ3XHUDLS";

    public void sendSms(String from, String to, String text) {
        DefaultMessageService messageService = NurigoApp.INSTANCE.initialize(API_KEY, SECRET_KEY, "https://api.coolsms.co.kr");
        Message message = new Message();
        message.setFrom(from);
        message.setTo(to);
        message.setText(text);

        try {
            messageService.send(message);
        } catch (NurigoMessageNotReceivedException exception) {
            System.out.println(exception.getFailedMessageList());
            System.out.println(exception.getMessage());
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}

