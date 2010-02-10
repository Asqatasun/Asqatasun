package org.opens.tanaguru.entity.audit;

import org.opens.tanaguru.entity.subject.Page;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface SSP extends TextContent {

    /**
     *
     * @return the string representation of the DOM
     */
    String getDOM();

    /**
     *
     * @return the javascript
     */
    String getJavascript();

    /**
     *
     * @return the page
     */
    Page getPage();

    /**
     *
     * @return the stylesheet
     */
    String getStylesheet();

    /**
     *
     * @param domString
     *            the string representation of the DOM to set
     */
    void setDOM(String domString);

    /**
     *
     * @param javascript
     *            the javascript to set
     */
    void setJavascript(String javascript);

    /**
     *
     * @param page
     *            the page to set
     */
    void setPage(Page page);

    /**
     *
     * @param stylesheet
     *            the stylesheet to set
     */
    void setStylesheet(String stylesheet);
}
