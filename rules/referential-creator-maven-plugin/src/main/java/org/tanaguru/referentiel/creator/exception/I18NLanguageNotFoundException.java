/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tanaguru.referentiel.creator.exception;

/**
 *
 * @author alingua
 */
public class I18NLanguageNotFoundException extends Exception {

    public I18NLanguageNotFoundException() {
    }

    public I18NLanguageNotFoundException(String message) {
        super(message);
    }

    public I18NLanguageNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public I18NLanguageNotFoundException(Throwable cause) {
        super(cause);
    }
}
