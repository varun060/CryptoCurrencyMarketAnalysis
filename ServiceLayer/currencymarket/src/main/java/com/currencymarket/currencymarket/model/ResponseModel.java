package com.currencymarket.currencymarket.model;

import java.util.List;

/**
 * Class to hold all currency results
 * from date search service
 * @author varun
 *
 */
public class ResponseModel {
	// holder which will have output details
	private List<OutputModel> results;

	public List<OutputModel> getResults() {
		return results;
	}

	public void setResults(List<OutputModel> results) {
		this.results = results;
	}

}
