package com.oc.projets.projet_7.batch;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.oc.projets.projet_7.entity.Emprunt;
import com.oc.projets.projet_7.entity.Livre;

import javassist.expr.NewArray;

@Component
@StepScope
public class Processor implements ItemProcessor<Emprunt, SimpleMailMessage> {

	@Override
	public SimpleMailMessage process(Emprunt item) throws Exception {
		// TODO Auto-generated method stub
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo("romaindemellier@gmail.com");
		msg.setSubject("Emprunt : " + item.getLivre().getTitre());
		msg.setText("Bonjour " + item.getUsager().getPrenom() + ' ' + item.getUsager().getNom() + ",\n" + 
				"Vous avez emprunté le livre : " + item.getLivre().getTitre() + '.');
		return msg;
//		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
//		String date = formater.format(new Date());
//		if(date.equals(formater.format(item.getDateEmprunt()))) {
//			msg.setTo("romaindemellier@gmail.com");
//			msg.setSubject("Emprunt : " + item.getLivre().getTitre());
//			msg.setText("Bonjour " + item.getUsager().getPrenom() + ' ' + item.getUsager().getNom() + ",\n" + 
//					"Vous avez emprunté le livre : " + item.getLivre().getTitre() + '.');
//			return msg;
//		} else {
//			return null;
//		}
	}

}
