package com.oc.projets.projet_7.batch;

import java.text.SimpleDateFormat;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.oc.projets.projet_7.entity.Emprunt;
import com.oc.projets.projet_7.entity.Livre;

@Component
@StepScope
public class Processor implements ItemProcessor<Emprunt, Emprunt> {

	@Override
	public Emprunt process(Emprunt item) throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		String dateEmprunt = "18/03/2020";
		if(dateEmprunt.equals(formater.format(item.getDateEmprunt())) && item.getId().equals(new Long(6))) {
		return item;
		} else {
			return null;
		}
	}

}
