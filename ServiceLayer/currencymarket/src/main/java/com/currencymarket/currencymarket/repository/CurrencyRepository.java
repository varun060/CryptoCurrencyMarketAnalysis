package com.currencymarket.currencymarket.repository;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.currencymarket.currencymarket.model.Currency;

@Repository
public interface CurrencyRepository extends MongoRepository<Currency, String> {
}

