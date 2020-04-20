package com.techfynder.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.techfynder.currency.model.Currency;
import com.techfynder.currency.repository.CurrencyRepository;

@SpringBootApplication
public class CurrencyConverterApplication implements CommandLineRunner{

	@Autowired
	CurrencyRepository currencyRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CurrencyConverterApplication.class, args);
	}

	 @Override
	  public void run(String... args) throws Exception {

		 currencyRepository.deleteAll();

	    // save a couple of currencies
	    currencyRepository.save(new Currency("INR", "INDIA", 76.5285));
	    currencyRepository.save(new Currency("SGD", "SINGAPORE", 1.42317));
	    currencyRepository.save(new Currency("GBP", "BRITAIN", 0.8004));
	    currencyRepository.save(new Currency("EUR", "EUROPE", 0.920154));
	    currencyRepository.save(new Currency("CAD", "CANADA", 1.40397));
	    currencyRepository.save(new Currency("HKD", "HONG KONG", 7.75));
		

	    // fetch all customers
	    System.out.println("Currency found with findAll():");
	    System.out.println("-------------------------------");
	    for (Currency currency : currencyRepository.findAll()) {
	      System.out.println(currency);
	    }
	    System.out.println();

	    // fetch an individual currency according to country....
	    System.out.println("Currency found with findByCountry('INDIA'):");
	    System.out.println("--------------------------------");
	    System.out.println(currencyRepository.findByCountry("INDIA"));
	    
	  

	   

	  }

}
