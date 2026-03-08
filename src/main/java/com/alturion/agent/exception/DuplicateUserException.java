package com.alturion.agent.exception;

public class DuplicateUserException extends RuntimeException{
	
	public DuplicateUserException(String message){
		super(message);
	}

}