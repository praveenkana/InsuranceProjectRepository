package com.nt.utils;

import java.io.File;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

public class EmailUtils {
	
	@Autowired
	private  JavaMailSender sender;
	
	public  void sendMail(String email,String subject,String body,File file) throws MessagingException {
		
	
		MimeMessage mimeMessage = sender.createMimeMessage();	
		MimeMessageHelper helper=new MimeMessageHelper(mimeMessage, true);
		helper.setTo(email);
		helper.setSentDate(new Date());
		helper.setSubject(subject);
		helper.setText(body);
		helper.addAttachment(file.getName(),file);
		sender.send(mimeMessage);
		
		
	}

}
