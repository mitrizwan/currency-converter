package com.techfynder.currency.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techfynder.currency.exception.CurrencyNotFoundException;
import com.techfynder.currency.model.Currency;
import com.techfynder.currency.model.CurrencyConversionDTO;
import com.techfynder.currency.service.CurrencyConverterService;

@RestController("/currency-converter")
@Validated
public class CurrencyConverterController {
	
	@Autowired
	CurrencyConverterService currencyConverterService;
	
	
	@GetMapping("/getCurrencies")
	public ResponseEntity<List<String>> getCurrencies() {
		
		return new ResponseEntity<>(currencyConverterService.getAllCurrencies(),HttpStatus.OK);
	}
	
	@PostMapping("/getCurrencyConversionRate")
	public ResponseEntity<CurrencyConversionDTO> getConversionRate(@Valid @RequestBody CurrencyConversionDTO currencyConversionDTO) {
		
		return new ResponseEntity<>(currencyConverterService.getCurrencyConversion(currencyConversionDTO),HttpStatus.OK);
		
	}
	
	@GetMapping("/getCurrenciesRates")
	public ResponseEntity<List<Currency>> getCurrenciesRates() {
		return new ResponseEntity<>(currencyConverterService.getAllCountriesCurrenciesRates(),HttpStatus.OK);
	}
	
	@GetMapping("/getCurrencyRate")
	public ResponseEntity<Currency> getCurrenciesRates(@RequestParam("country") String country) {
		Currency currency=currencyConverterService.getCurrencyRateByCountry(country);
		if(currency==null)
			throw new CurrencyNotFoundException("No currency exist for the country  :: "+country);
		else
			return new ResponseEntity<>(currency,HttpStatus.OK);

	}

}
