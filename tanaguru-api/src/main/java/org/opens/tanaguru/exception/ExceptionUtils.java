package org.opens.tanaguru.exception;

/**
 * 
 * @author ADEX
 */
public abstract class ExceptionUtils {

	public static Throwable getRootCause(Throwable throwable) {
		Throwable cause;

		cause = throwable.getCause();

		if (cause != null) {
			throwable = cause;
			while ((throwable = throwable.getCause()) != null) {
				cause = throwable;
			}
		}
		return cause;
	}

	public static boolean isApplicationException(Throwable throwable) {
		if (throwable instanceof ValidationException) {
			return true;
		} else {
			return false;
		}
	}
}