package com.currencymarket.currencymarket.model;

import java.util.List;
/**
 * Object to store result which is being sent 
 * from service
 * @author varun
 *
 */
public class CurrencyItems {
	
	//holder of all currencies
	private List<Currency> result;

	public List<Currency> getResult() {
		return result;
	}

	public void setResult(List<Currency> result) {
		this.result = result;
	}

}
