/**
 * All services for controllers will be listed under this package
 */
package com.currencymarket.currencymarket.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.currencymarket.currencymarket.constant.CurrencyConst;
import com.currencymarket.currencymarket.model.Currency;
import com.currencymarket.currencymarket.model.MarketDetails;
import com.currencymarket.currencymarket.model.OutputModel;
import com.currencymarket.currencymarket.model.ResponseModel;
import com.currencymarket.currencymarket.repository.CurrencyRepository;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
// mongo db drivers
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

/**
 * Market ANalyzer will do the logic of identifying 
 * uses Mongo repositories for extracting data
 * Service will return profitable time for given currencies in a period
 * 
 * @author Varun  K R
 *
 */

@Service
public class MarketAnlyser {

	// repository to get data
	@Autowired
	private CurrencyRepository repository;
		

	@Value("${db.data.mongodb.database}")
	private String database;

	@Value("${db.data.mongodb.collection}")
	private String collection;

	private static final Logger logger = LogManager.getLogger(MarketAnlyser.class);

	/**
	 * Method to retrieve data from Mongo DB and apply logic to
	 * identify maximum profit and time period
	 * @param fromDate
	 * @param toDate
	 * @param currency
	 * @return
	 * @throws ParseException
	 */
	public ResponseModel getProfitTimes(Date fromDate, Date toDate, String currency) throws ParseException {

		try {

			logger.info("in Market analyzer service");
			/**** Connect to MongoDB ****/

			@SuppressWarnings("resource")
			MongoClient mongo = new MongoClient("localhost", 27017);
			// if database doesn't exists, MongoDB will create it for you	
			DB db = mongo.getDB("stockmarcket");

			/**** Get collection / table from 'currencymarket' ****/
			//DBCollection table = db.getCollection(collection);
			DBCollection table = db.getCollection("currencymarket");
			List<Currency> currencies=getCurrencyType(currency);

			Map<String,List<OutputModel>>outputModelMap=new HashMap<String, List<OutputModel>>();
			List<OutputModel> outputModels=new ArrayList<OutputModel>();
			/**** Get database ****/
			ResponseModel result=new ResponseModel();

			for(Currency currencyIter:currencies) {
				logger.debug("inside for"+currencyIter.getCurrency());
				// get cursor for querying
				BasicDBObject searchQuery = new BasicDBObject("date",
						new BasicDBObject("$gte",fromDate).append("$lt",toDate));

				searchQuery.put("currency", currencyIter.getCurrency());

				DBCursor cursor = table.find(searchQuery);
				/***Block will iterate through result set  ***/
				while (cursor.hasNext()) {
					DBObject queryRes =cursor.next();
					logger.debug("json obtained is "+queryRes.toString());
					Gson gson = new Gson();
					//  convert JSON file to Java object
					MarketDetails status = gson.fromJson(queryRes.toString(), MarketDetails.class);		
					// handle date in result obtained
					status.setInjectedDate(queryRes.get("date").toString());
					Collections.sort(status.getQuotes());
					// declare iterator values to 0
					int iterFirst=0;
					// declare default difference
					float defaultDif=0;
					OutputModel model = new OutputModel();
					/**** logic to identify highest profit ****/
					while(iterFirst<status.getQuotes().size()) {
						float oldPrice=status.getQuotes().get(iterFirst).getPrice();
						// comparison is only required with future values
						int iterSecond=iterFirst+1;
						/**** Compare each value difference ***/
						while(iterSecond<status.getQuotes().size()) {
							float newPrice=status.getQuotes().get(iterSecond).getPrice();
							if((newPrice-oldPrice)>defaultDif) {
								defaultDif=newPrice-oldPrice;
								//model details which will be sent as a result 
								setModel(model,status,iterFirst,iterSecond,defaultDif);

							}
							iterSecond++;
						}
						iterFirst++;

					}
					outputModels.add(model);

					if(null==outputModelMap.get(model.getCurrency())) {
						List<OutputModel> outputModelss=new ArrayList<OutputModel>();
						outputModelMap.put(model.getCurrency(), outputModelss);

					}
					//outputModelMap.get(model.getCurrency());
					outputModelMap.get(model.getCurrency()).add(model);



					//return result;
				}

			}
			result.setResults(outputModels);

			return result;


		} catch (MongoException e) {
			// catch all exceptions and make sure that UI is not affected with it
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Method which sets output model 
	 * @param model
	 * @param status
	 * @param iterFirst
	 * @param iterSecond
	 * @param defaultDif 
	 */
	private void setModel(OutputModel model, MarketDetails status, int iterFirst, int iterSecond, float defaultDif) {
		// set model with available values 
		model.setCurrency(status.getCurrency());
		model.setFromTime(status.getQuotes().get(iterFirst).getTime());
		model.setToTime(status.getQuotes().get(iterSecond).getTime());
		model.setValueProfit(defaultDif);
		model.setFrmPrice(status.getQuotes().get(iterFirst).getPrice());
		model.setToPrice(status.getQuotes().get(iterSecond).getPrice());
		model.setDate(status.getInjectedDate());


	}

	/**
	 * Method to return currencies according to input type
	 * @param currency
	 * @return
	 */
	private List<Currency> getCurrencyType(String currency) {

		// if all get all currencies and return
		List<Currency> currencies;
		if((CurrencyConst.All.equals(currency))) {
			currencies= repository.findAll();
		}
		else {
			// else get specific currency object and return
			currencies=new ArrayList<Currency>();
			Currency cur=new Currency();
			cur.setCurrency(currency);

			currencies.add(cur);
		}
		return currencies;
	}


}
