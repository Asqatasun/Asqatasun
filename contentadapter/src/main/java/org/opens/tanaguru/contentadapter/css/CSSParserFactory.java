/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.contentadapter.css;

/**
 *
 * @author enzolalay
 */
public class CSSParserFactory {

    public static CSSParser create() {
        return new CSSParserImpl();
    }
}
