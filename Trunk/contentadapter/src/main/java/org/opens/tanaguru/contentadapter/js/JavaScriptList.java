package org.opens.tanaguru.contentadapter.js;

import java.util.List;

/**
 * 
 * @author srahmi
 */
public interface JavaScriptList {

    /**
     *
     * @param javaScript
     *            the Javascript resource to add
     */
    void addJavaScript(JSResource javaScript);

    /**
     *
     * @return the Javascript resource list
     */
    List<JSResource> getJavaScriptList();

    /**
     *
     * @param javaScriptList
     *            the Javascript list to set
     */
    void setJavaScript(List<JSResource> javaScriptList);
}
