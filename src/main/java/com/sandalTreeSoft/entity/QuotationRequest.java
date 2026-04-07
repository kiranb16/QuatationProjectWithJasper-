package com.sandalTreeSoft.entity;

import java.util.List;

public class QuotationRequest {

	private String customerName;
	private List<QuotationItem> items;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<QuotationItem> getItems() {
		return items;
	}

	public void setItems(List<QuotationItem> items) {
		this.items = items;
	}

}