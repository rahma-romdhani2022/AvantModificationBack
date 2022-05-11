package com.projet.BackendPfe.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.projet.BackendPfe.EmailSenderService;

public class SendEmail {

	@Autowired
	 EmailSenderService senderService;
	
	public void sendMail() {
	senderService.sendEmailAttachment("Email Inline Attachment", "Email Inline Attachment using MimeMessageHelper",
			"pfe4575@gmail.com", "chellymariem01@gmail.com", true/*, new File("C:/jee_workspace/sample.jpg")*/);

}
	
}
