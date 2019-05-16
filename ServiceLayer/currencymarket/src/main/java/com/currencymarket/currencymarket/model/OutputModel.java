package com.currencymarket.currencymarket.model;

/**
 * Single Currency output model
 * to hold result of search
 * @author varun
 *
 */
public class OutputModel {
	
	private String currency;
	private String date;
	private int fromTime;
	private int toTime;
	private float profit;
	private float valueProfit;
	private float frmPrice;
	private float toPrice;
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the profit
	 */
	public float getProfit() {
		return profit;
	}
	/**
	 * @param profit the profit to set
	 */
	public void setProfit(float profit) {
		this.profit = profit;
	}
	/**
	 * @return the fromTime
	 */
	public int getFromTime() {
		return fromTime;
	}
	/**
	 * @param fromTime the fromTime to set
	 */
	public void setFromTime(int fromTime) {
		this.fromTime = fromTime;
	}
	/**
	 * @return the toTime
	 */
	public int getToTime() {
		return toTime;
	}
	/**
	 * @param toTime the toTime to set
	 */
	public void setToTime(int toTime) {
		this.toTime = toTime;
	}
	/**
	 * @return the valueProfit
	 */
	public float getValueProfit() {
		return valueProfit;
	}
	/**
	 * @param valueProfit the valueProfit to set
	 */
	public void setValueProfit(float valueProfit) {
		this.valueProfit = valueProfit;
	}
	/**
	 * @return the frmPrice
	 */
	public float getFrmPrice() {
		return frmPrice;
	}
	/**
	 * @param frmPrice the frmPrice to set
	 */
	public void setFrmPrice(float frmPrice) {
		this.frmPrice = frmPrice;
	}
	/**
	 * @return the toPrice
	 */
	public float getToPrice() {
		return toPrice;
	}
	/**
	 * @param toPrice the toPrice to set
	 */
	public void setToPrice(float toPrice) {
		this.toPrice = toPrice;
	}
	

}
