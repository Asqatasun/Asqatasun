/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.contentadapter;

import java.util.Set;

/**
 *
 * @author enzolalay
 */
public interface HTMLParserFactory {// TODO Write javadoc

    HTMLParser create(Set<ContentAdapter> contentAdapterSet);
}
