package com.example.demo.exception;

public class SoldeInsuffisantException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SoldeInsuffisantException() {

	}

	public SoldeInsuffisantException(String description) {
		super(description);
	}
}
