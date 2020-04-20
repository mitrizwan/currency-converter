package com.techfynder.currency.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.techfynder.currency.model.Currency;
import com.techfynder.currency.model.CurrencyConversionDTO;
import com.techfynder.currency.repository.CurrencyRepository;
import com.techfynder.currency.service.CurrencyConverterService;

@SpringBootTest
class CurrencyConverterServiceTest {

	@Autowired
	CurrencyConverterService currencyConverterService;
	
	@MockBean
	CurrencyRepository currencyRepository;
	
	
	@Test
	void testGetAllCurrenciesWhenRepositoryReturnNull() {
		when(currencyRepository.findAll())
        .thenReturn(null);
		
		assertEquals(new ArrayList<Currency>(), currencyConverterService.getAllCurrencies());
	
	}
	
	@Test
	void testGetAllCurrenciesWhenRepositoryReturnEmptyCurrency() {
		Currency currency=new Currency();
		List<Currency> list=new ArrayList<>();
		list.add(currency);
		
		when(currencyRepository.findAll())
        .thenReturn(list);
		
		assertEquals(null, currencyConverterService.getAllCurrencies().get(0));
	
	}


	@Test
	void testGetCurrencyConversionWhenInvalidCurrencyFrom() {
		
		when(currencyRepository.findByCurrency("INVALID"))
        .thenReturn(null);
		
		when(currencyRepository.findByCurrency("VALID"))
        .thenReturn(new Currency());
		
		CurrencyConversionDTO currencyConversionDTO=new CurrencyConversionDTO();
		currencyConversionDTO.setCurrencyFrom("INVALID");
		currencyConversionDTO.setCurrencyTo("VALID");
		currencyConversionDTO.setDate("");
		currencyConversionDTO.setAmount(10.2);
//		currencyConverterService.getCurrencyConversion(currencyConversionDTO);
		
		
		Exception exception = assertThrows(RuntimeException.class, () -> {
			currencyConverterService.getCurrencyConversion(currencyConversionDTO);
	    });
	 
	    String expectedMessage = "INVALID";
	    String actualMessage = exception.getMessage();
	    
	    assertTrue(actualMessage.contains(expectedMessage));
	}

}
