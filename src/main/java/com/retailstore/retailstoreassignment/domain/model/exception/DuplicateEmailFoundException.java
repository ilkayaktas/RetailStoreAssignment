package com.retailstore.retailstoreassignment.domain.model.exception;

public class DuplicateEmailFoundException extends Exception{
	public DuplicateEmailFoundException(String message) {
		super(message);
	}
}
