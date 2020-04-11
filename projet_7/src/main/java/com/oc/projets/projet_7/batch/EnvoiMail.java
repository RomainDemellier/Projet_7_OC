package com.oc.projets.projet_7.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class EnvoiMail {

	private String message = "";
	
//	@Autowired
//	private JavaMailSender javaMailSender;
	
	public EnvoiMail() {
		
	}
	
	public EnvoiMail(String message) {
		this.message = message;
	}
	
	public void envoiMsg() {
		JavaMailSender javaMailSender = new JavaMailSenderImpl();
		System.out.println("Dans envoi mail");
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo("romaindemellier@gmail.com");
		
        msg.setSubject("Testing from Spring Boot");
        msg.setText("Coucou");
        System.out.println("Dans envoi mail 2");
        System.out.println(javaMailSender);

        javaMailSender.send(msg);
        System.out.println("Dans envoi mail fin");
	}
}
