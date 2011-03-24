/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.contentadapter.html;

import org.opens.tanaguru.contentadapter.HTMLCleaner;
import org.opens.tanaguru.contentadapter.HTMLCleanerFactory;

/**
 *
 * @author enzolalay
 */
public class HTMLCleanerFactoryImpl implements HTMLCleanerFactory {

    public HTMLCleanerFactoryImpl() {
        super();
    }

    public HTMLCleaner create() {
        return new HTMLCleanerImpl();
    }
}
