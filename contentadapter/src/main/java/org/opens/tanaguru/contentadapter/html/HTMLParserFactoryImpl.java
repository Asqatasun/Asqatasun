/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.contentadapter.html;

import java.util.Set;
import org.opens.tanaguru.contentadapter.ContentAdapter;
import org.opens.tanaguru.contentadapter.HTMLParser;
import org.opens.tanaguru.contentadapter.HTMLParserFactory;

/**
 *
 * @author enzolalay
 */
public class HTMLParserFactoryImpl implements HTMLParserFactory {

    public HTMLParserFactoryImpl() {
        super();
    }

    public HTMLParser create(Set<ContentAdapter> contentAdapterSet) {
        return new HTMLParserImpl(contentAdapterSet);
    }
}
