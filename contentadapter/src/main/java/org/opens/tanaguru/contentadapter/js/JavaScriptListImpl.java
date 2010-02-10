package org.opens.tanaguru.contentadapter.js;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author ADEX
 */
public class JavaScriptListImpl implements JavaScriptList {

    private List<JSResource> javaScriptList;

    private JavaScriptListImpl() {
        super();
        this.javaScriptList = new ArrayList<JSResource>();
    }

    public void addJavaScript(JSResource javaScript) {
        javaScriptList.add(javaScript);
    }

    public List<JSResource> getJavaScriptList() {
        return javaScriptList;
    }

    public void setJavaScript(List<JSResource> javaScriptList) {
        this.javaScriptList = javaScriptList;
    }
}
