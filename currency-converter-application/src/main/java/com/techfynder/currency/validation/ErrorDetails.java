package com.techfynder.currency.validation;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ErrorDetails {
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	    private LocalDateTime timestamp;
	    private int status;
	    private String error;

	public ErrorDetails() {
		// TODO Auto-generated constructor stub
	}
}