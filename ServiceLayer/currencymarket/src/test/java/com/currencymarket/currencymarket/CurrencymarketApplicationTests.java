package com.currencymarket.currencymarket;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.currencymarket.currencymarket.constant.CurrencyConst;
import com.currencymarket.currencymarket.model.ResponseModel;
import com.currencymarket.currencymarket.service.MarketAnlyser;


/**
 * Main class for writing test cases
 * @author Varun K R
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencymarketApplicationTests {

	// declare Rest service related variables
	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
	/**
	 * Test case for checking REST service which provides
	 * details of quotes for a given Crrency with 
	 * from and to date values
	 * @throws Exception
	 */
	@Test
	public void testMarketAnalysisRESTApi() throws Exception {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/get/2018-05-02/2019-05-08/ETC"), HttpMethod.GET, entity, String.class);

		String expected = "{\"results\":[{\"currency\":\"ETC\",\"date\":\"Mon May 07 10:00:00 AEST 2018\",\"fromTime\":900,\"toTime\":1700,\"profit\":0.0,\"valueProfit\":0.70000005,\"frmPrice\":1.45,\"toPrice\":2.15}]}";
		JSONAssert.assertEquals(expected, response.getBody(), false);

	}   
	/**
	 * Test case to check logic for identifying 
	 * highest profit and time period 
	 * from and to date values
	 * @throws Exception
	 */
	@Test
	public void testMarketAnalysisProfitLogic() throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat (CurrencyConst.DATEFORMAT);
		MarketAnlyser analyser=new MarketAnlyser();
		ResponseModel model=analyser.getProfitTimes(simpleDateFormat.parse("2018-05-01"), simpleDateFormat.parse("2018-05-10"), "ETC");
		
		
		float result=model.getResults().get(0).getValueProfit();
		int fromTime=model.getResults().get(0).getFromTime();
		int toTime=model.getResults().get(0).getToTime();
		// check from time, to time and profit 
		assertEquals(String.valueOf(result),"0.70000005");
		assertEquals(String.valueOf(fromTime),"900");
		assertEquals(String.valueOf(toTime),"1700");
	
	}   
	/**
	 * utility function to form URL
	 * @param uri
	 * @return
	 */
	private String createURLWithPort(String uri) {

		return "http://localhost:8102" +uri;

	}

}
