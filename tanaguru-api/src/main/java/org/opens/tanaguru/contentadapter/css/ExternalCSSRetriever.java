/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.contentadapter.css;

import java.util.Collection;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.StylesheetContent;

/**
 *
 * @author jkowalczyk
 */
public interface ExternalCSSRetriever {


    /**
     * 
     * @return
     *      the collection of external stylesheet for a given ssp.
     */
    Collection<StylesheetContent> getExternalCSS(SSP ssp);

}