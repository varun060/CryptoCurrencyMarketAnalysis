/**
 * Package for all controllers
 */
package com.currencymarket.currencymarket.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.currencymarket.currencymarket.constant.CurrencyConst;
import com.currencymarket.currencymarket.model.Currency;
import com.currencymarket.currencymarket.model.CurrencyItems;
import com.currencymarket.currencymarket.model.ResponseModel;
import com.currencymarket.currencymarket.repository.CurrencyRepository;
import com.currencymarket.currencymarket.service.MarketAnlyser;

/**
 * Class to handle requests from UI
 * It passes relevant parameters from UI to
 * Service classes
 * @author Varun K R
 *
 */
@RestController
public class CurrencyController {

	// repository access variable
	@Autowired
	private CurrencyRepository repository;
	// service access variable
	@Autowired
	private MarketAnlyser analyser;
	// log declaration
	 private static final Logger logger = LogManager.getLogger(CurrencyController.class);
	/**
	 * Method to handle request get all currencies
	 * in market
	 * it directly access repository and returns currencies in it
	 * @return CurrencyItems
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping("/getAll")
	public CurrencyItems getCurrencies() {
		logger.info("in controller");
		// Getting all currency names from repository
		List<Currency> currencies= repository.findAll();
		//Forming model to be sent 
		CurrencyItems result=new CurrencyItems();
		result.setResult(currencies);
		logger.info("in controller"+currencies.size());
		return result;
	}

	/**
	 * Method for receiving details from UI 
	 * and will pass to service foe result manipulation
	 * @param fromDate
	 * @param toDate
	 * @param currency
	 * @return
	 * @throws ParseException
	 */
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/get/{fromDate}/{toDate}/{currency}", method = RequestMethod.GET)
	public ResponseModel getProfitTime(@PathVariable String fromDate, @PathVariable String toDate,@PathVariable String currency) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat (CurrencyConst.DATEFORMAT);		
		return analyser.getProfitTimes(simpleDateFormat.parse(fromDate),simpleDateFormat.parse(toDate),currency);

	}

}