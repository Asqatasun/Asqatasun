/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tanaguru.referentiel.creator.exception;

/**
 *
 * @author alingua
 */
public class InvalidParameterException extends Exception {

    public InvalidParameterException() {
    }

    public InvalidParameterException(String message) {
        super(message);
    }

    public InvalidParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidParameterException(Throwable cause) {
        super(cause);
    }
}
