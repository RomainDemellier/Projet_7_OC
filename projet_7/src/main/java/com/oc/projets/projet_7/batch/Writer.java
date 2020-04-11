package com.oc.projets.projet_7.batch;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.oc.projets.projet_7.dto.AuteurDTO;
import com.oc.projets.projet_7.entity.Emprunt;
import com.oc.projets.projet_7.entity.Emprunt;
import com.oc.projets.projet_7.repository.AuteurRepository;
import com.oc.projets.projet_7.service.AuteurService;

import javassist.expr.NewArray;
import net.bytebuddy.asm.Advice.This;

//@Component
//@StepScope
public class Writer implements ItemWriter<Emprunt> {
	
	private JavaMailSender javaMailSender;
	//private EnvoiMail envoiMail;
	
	public Writer(JavaMailSender javaMailSender) {
		// TODO Auto-generated constructor stub
		this.javaMailSender = javaMailSender;
	}
	
	@Override
	public void write(List<? extends Emprunt> items) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Dans write");
//		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		JavaMailSender javaMailSender = new JavaMailSenderImpl();
		for(Emprunt item: items) {
			if(item != null) {
				SimpleMailMessage msg = new SimpleMailMessage();
				msg.setTo("romaindemellier@gmail.com");
				msg.setSubject("Emprunt : " + item.getLivre().getTitre());
				msg.setText("Bonjour " + item.getUsager().getPrenom() + ' ' + item.getUsager().getNom() + ",\n" + 
						"Vous avez emprunté le livre : " + item.getLivre().getTitre() + '.');
				this.javaMailSender.send(msg);
			}
//			//Date date = item.getDateEmprunt();
//			//envoiMail = new EnvoiMail("Coucou");
//			String dateEmprunt = "18/03/2020";
//			if(dateEmprunt.equals(formater.format(item.getDateEmprunt())) && item.getId().equals(new Long(6))) {
//				System.out.println("Dans envoi mail");
//				SimpleMailMessage msg = new SimpleMailMessage();
//				msg.setTo("romaindemellier@gmail.com");
//				
//		        msg.setSubject("Testing from Spring Boot 2");
//		        msg.setText("Coucou");
//		        System.out.println("Dans envoi mail 2");
//		        System.out.println(this.javaMailSender);
//
//		        this.javaMailSender.send(msg);
//			}
		}
	}
}
