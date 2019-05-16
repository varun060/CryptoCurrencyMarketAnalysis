package com.currencymarket.currencymarket.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Model class to manage currencies
 * This is a Document mapper with Mongo
 * All Currency types will be mapped in it
 * @author varun
 *
 */
@Document
public class Currency {
	
	private String currency;

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
