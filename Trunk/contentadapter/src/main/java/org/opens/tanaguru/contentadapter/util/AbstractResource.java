package org.opens.tanaguru.contentadapter.util;

import org.opens.tanaguru.contentadapter.Resource;
import org.opens.tanaguru.contentadapter.RsrcLocator;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author ADEX
 */
public abstract class AbstractResource implements Resource {

    protected int lineNumber;
    protected RsrcLocator location;
    protected String resource;
    protected Set resourceSet;

    public AbstractResource() {
        super();
        this.resourceSet = new HashSet();
    }

    public AbstractResource(String resource, int lineNumber,
            RsrcLocator location) {
        super();
        this.resource = resource;
        this.lineNumber = lineNumber;
        this.location = location;
        this.resourceSet = new HashSet();
    }

    public void addAllResource(Set resourceSet) {
        this.resourceSet.addAll(resourceSet);
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getResource() {
        return resource;
    }

    public Set getResourceSet() {
        return resourceSet;
    }

    public RsrcLocator getRsrcLocator() {
        return location;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
