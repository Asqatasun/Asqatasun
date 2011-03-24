/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.contentadapter.util;

/**
 *
 * @author enzolalay
 */
public class URLIdentifierFactoryImpl implements URLIdentifierFactory {

    public URLIdentifier create() {
        return new URLIdentifierImpl();
    }
}
