/** package for holding all models**/
package com.currencymarket.currencymarket.model;



import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Document Mapper class 
 * between java and mongo which will make it easy
 * when converting Mongo DB object to Java object
 * @author varun
 *
 */

public class MarketDetails {

	@SerializedName("_id")
	@Expose
	private Id id;
	@SerializedName("currency")
	@Expose
	private String currency;
	// variable to hold date variable
	private String injectedDate;
	@SerializedName("quotes")
	@Expose
	private List<Quote> quotes = null;

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public List<Quote> getQuotes() { return quotes; }

	public void setQuotes(List<Quote> quotes) { this.quotes = quotes; }

	public String getInjectedDate() {
		return injectedDate;
	}

	public void setInjectedDate(String injectedDate) {
		this.injectedDate = injectedDate;
	}


}
