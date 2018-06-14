package com.manage.model;

import javax.xml.bind.annotation.XmlElement;

public class PagingRequest {
private boolean paged;
	
	private int page;
	private int limit;
	
	@XmlElement
	private String orderingBy;
	
	@XmlElement
	private String ordering;
	
	public PagingRequest() {
		this.paged = true;
		this.page = 1;
		this.limit = 10;
	}
	
	public boolean isPaged() {
		return paged;
	}

	public void setPaged(boolean paged) {
		this.paged = paged;
	}

    
    public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getOrderingBy() {
		return orderingBy;
	}

	public void setOrderingBy(String orderingBy) {
		this.orderingBy = orderingBy;
	}

	public String getOrdering() {
		return ordering;
	}

	public void setOrdering(String ordering) {
		this.ordering = ordering;
	}

	public boolean ascOrdering() {
		if ("desc".equalsIgnoreCase(ordering)) {
			return false;
		} else {
			return true;
		}
    }
}