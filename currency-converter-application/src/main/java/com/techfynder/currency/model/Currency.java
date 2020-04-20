package com.techfynder.currency.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Document(value = "currency")
@Data
public class Currency {
	
	@Id
	@JsonInclude(value = Include.NON_EMPTY)
	private String id;
	
	private String currency;
	
	private String country;
	
	
	private Double rate;

	public Currency(String currency, String country, Double rate) {
		super();
		this.currency = currency;
		this.country = country;
		this.rate = rate;
	}

	public Currency() {
		// TODO Auto-generated constructor stub
	}

}
