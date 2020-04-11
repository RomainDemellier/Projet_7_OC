package com.oc.projets.projet_7.batch;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.hibernate.hql.internal.ast.tree.InitializeableNode;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.database.AbstractPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.oc.projets.projet_7.entity.Emprunt;
import com.oc.projets.projet_7.entity.Emprunt;
import com.oc.projets.projet_7.repository.EmpruntRepository;
import com.oc.projets.projet_7.service.EmpruntService;
import com.sun.xml.bind.marshaller.Messages;

import net.bytebuddy.asm.Advice.This;

@Component
@StepScope
public class JpaRepositoryItemReader<Emprunt> extends AbstractPagingItemReader<Emprunt> {

	private JpaRepository<Emprunt, Long> repository;
	
	private Sort sort;
	
	public JpaRepositoryItemReader(JpaRepository<Emprunt, Long> repository) {
		// TODO Auto-generated constructor stub
		this.repository = repository;
	}
	
	@Override
	protected void doReadPage() {
		// TODO Auto-generated method stub
		if(this.results == null) {
			this.results = new CopyOnWriteArrayList<>();
		} else {
			this.results.clear();
		}
		Page<Emprunt> page = this.repository.findAll(PageRequest.of(getPage(), getPageSize()));
		if(page.iterator().hasNext()) {
			Emprunt emprunt = page.iterator().next();
		}
		if(!page.getContent().isEmpty()) {
			this.results.addAll(page.getContent());
		}
		
	}

	@Override
	protected void doJumpToPage(int itemIndex) {
		// TODO Auto-generated method stub
		
	}

	
}
