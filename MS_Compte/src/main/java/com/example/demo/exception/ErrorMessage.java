package com.example.demo.exception;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data

public class ErrorMessage {
	private String code;
	private String description;
	private Map<String,String> errors=new HashMap<>();

	public ErrorMessage() {
		super();
	}

	public ErrorMessage(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public void putError(String key,String message){
		errors.put(key,message);
	}

}