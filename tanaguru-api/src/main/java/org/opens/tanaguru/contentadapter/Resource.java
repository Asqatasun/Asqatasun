package org.opens.tanaguru.contentadapter;

import java.util.Set;

public interface Resource {

    /**
     *
     * @param resourceSet
     */
    void addAllResource(Set resourceSet);

    /**
     *
     * @return the Resource line number
     */
    int getLineNumber();

    /**
     *
     * @return
     */
    String getResource();

    /**
     * 
     * @return
     */
    Set getResourceSet();

    /**
     *
     * @return the Resource Locator
     */
    RsrcLocator getRsrcLocator();

    /**
     *
     * @return the resource name
     */
    String getRsrcName();

    /**
     *
     * @return the Resource type
     */
    short getRsrcType();

    /**
     * 
     * @param resource
     */
    void setResource(String resource);
}
