
package com.currencymarket.currencymarket.repository;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.currencymarket.currencymarket.model.MarketDetails;

@Repository
public interface MarketDetailsRepo extends MongoRepository<MarketDetails, String> {
}

