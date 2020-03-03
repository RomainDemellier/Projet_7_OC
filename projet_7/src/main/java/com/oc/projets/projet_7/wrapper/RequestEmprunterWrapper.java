package com.oc.projets.projet_7.wrapper;

import java.io.Serializable;

import com.oc.projets.projet_7.dto.LivreDTO;
import com.oc.projets.projet_7.dto.UsagerGetDTO;

public class RequestEmprunterWrapper implements Serializable {

	private Long usagerId;
	
	private Long livreId;
	
	public RequestEmprunterWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getUsagerId() {
		return usagerId;
	}

	public Long getLivreId() {
		return livreId;
	}

	public void setUsagerId(Long usagerId) {
		this.usagerId = usagerId;
	}

	public void setLivreId(Long livreId) {
		this.livreId = livreId;
	}
	
}
