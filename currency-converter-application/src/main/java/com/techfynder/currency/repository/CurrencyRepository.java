package com.techfynder.currency.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.techfynder.currency.model.Currency;

@Repository
public interface CurrencyRepository extends MongoRepository<Currency, String> {
	
	@Query(value="{ 'country' : ?0 }",fields="{  'currency' : 1,'country':1,'rate':1,'_id':0}")
	public Currency findByCountry(String country);
	
	@Query( value="{'currency' : ?0 }",fields="{  'rate' : 1,'_id':0}")
	public Currency findByCurrency(String currency);
	
	


}