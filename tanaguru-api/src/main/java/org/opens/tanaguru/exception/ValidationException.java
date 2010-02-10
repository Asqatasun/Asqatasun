package org.opens.tanaguru.exception;

/**
 * 
 * @author ADEX
 */
public class ValidationException extends Exception {

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}
}