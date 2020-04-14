package com.oc.projets.projet_7.batch;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.COUNT;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.oc.projets.projet_7.entity.Emprunt;
import com.oc.projets.projet_7.repository.EmpruntRepository;

import net.bytebuddy.asm.Advice.This;

public class Reader implements ItemReader<Emprunt> {
	
	private EmpruntRepository empruntRepository;
	
	private List<Emprunt> emprunts = new ArrayList<>();
	
	private int count = 0;
	
//	private SimpleDateFormat formater;
//	
//	Date date;
	
	public Reader(EmpruntRepository empruntRepository, Date date) {
		// TODO Auto-generated constructor stub
		this.empruntRepository = empruntRepository;
		System.out.println("Dans Reader");
//		this.date = date;
//		System.out.println("Date : " + date);
//		this.emprunts = this.empruntRepository.findByDate(date);
	
//		this.emprunts = this.empruntRepository.findByActif(true);
	}

	@Override
	public Emprunt read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// TODO Auto-generated method stub
//		Date date = new Date();
//		//System.out.println("Date : " + date);
//		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//		try {
//			Date date2 = format.parse(format.format(date));
//			//System.out.println("Date 2 : " + date2);
//			Calendar calendar = Calendar.getInstance();
//			calendar.setTime(date2);
//			calendar.add(Calendar.DATE, -3);
//			date2 = calendar.getTime();
//			this.emprunts = this.empruntRepository.findByDateEmpruntAndActif(date2, true);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		LocalDate date = LocalDate.now().plusDays(-3);
		this.emprunts = this.empruntRepository.findByDateEmpruntAndActif(date, true);
		
		if(count < this.emprunts.size()) {
			System.out.println("Reader titre : " + this.emprunts.get(count).getLivre().getTitre());
			return this.emprunts.get(count++);
		} else {
			count = 0;
		}
		return null;
	}

}
