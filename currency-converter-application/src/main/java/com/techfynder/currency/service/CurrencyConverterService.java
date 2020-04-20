package com.techfynder.currency.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techfynder.currency.exception.CurrencyNotFoundException;
import com.techfynder.currency.model.Currency;
import com.techfynder.currency.model.CurrencyConversionDTO;
import com.techfynder.currency.repository.CurrencyRepository;

@Service
public class CurrencyConverterService {
	@Autowired
	CurrencyRepository currencyRespository;
	
	public List<String>  getAllCurrencies(){
	   List<String> list=new ArrayList();
	   
	   List<Currency> list1=currencyRespository.findAll();
	   System.out.println(list1);
	   if(list1!=null)
	   list1.forEach(x->list.add(x.getCurrency()));
	   return list ;
	}

	public List<Currency> getAllCountriesCurrenciesRates(){
		
		List<Currency> list=currencyRespository.findAll();
		list.forEach(currency->currency.setId(null));
		return list;
	}
	
	public Currency getCurrencyRateByCountry(String country) {
		return currencyRespository.findByCountry(country);
	}
	
	public CurrencyConversionDTO getCurrencyConversion(CurrencyConversionDTO currencyConversionDTO) {
		
		Currency currencyFrom=currencyRespository.findByCurrency(currencyConversionDTO.getCurrencyFrom());
		
		Currency currencyTo=currencyRespository.findByCurrency(currencyConversionDTO.getCurrencyTo());
		
		if(currencyFrom==null)
			throw new CurrencyNotFoundException("Invalid currency value for currencyFrom ::"+currencyConversionDTO.getCurrencyFrom());

		if(currencyTo==null)
			throw new CurrencyNotFoundException("Invalid currency value for currencyTo ::"+currencyConversionDTO.getCurrencyTo());

		
		Double fromAmount=currencyFrom.getRate();
		Double toAmount=currencyTo.getRate();
		
		Double netAmount=(toAmount*currencyConversionDTO.getAmount())/fromAmount;
	
		currencyConversionDTO.setNetAmount(netAmount);
			
		return currencyConversionDTO;
		
	}
	
}
