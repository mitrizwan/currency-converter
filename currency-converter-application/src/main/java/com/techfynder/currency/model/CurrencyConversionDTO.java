package com.techfynder.currency.model;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

import lombok.Data;
import lombok.Getter;

@Data
public class CurrencyConversionDTO {
	
  @NotEmpty(message = "currencyFrom must not be empty or blank.")	
  private String currencyFrom;
  
  @NotEmpty(message = "currencyTo must not be empty or blank.")	
  private String currencyTo;
  private String date;
  
  
  @Positive(message=" amount must be a positive quantity." )
  private Double amount;
  private Double netAmount;

}
