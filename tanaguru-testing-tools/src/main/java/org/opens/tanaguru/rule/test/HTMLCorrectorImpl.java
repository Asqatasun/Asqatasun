/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.rule.test;

import org.opens.tanaguru.contentadapter.HTMLCleaner;

/**
 *
 * @author lralambomanana
 */
public class HTMLCorrectorImpl implements HTMLCleaner {

    private String dirtyHTML;
    private String result;

    public HTMLCorrectorImpl() {
        super();
    }

    @Override
    public String getDirtyHTML() {
        return dirtyHTML;
    }

    @Override
    public String getResult() {
        return result;
    }

    @Override
    public void run() {
        result = dirtyHTML;
    }

    @Override
    public void setDirtyHTML(String dirtyHTML) {
        this.dirtyHTML = dirtyHTML;
    }

    @Override
    public String getCorrectorName() {
        return "Mock";
    }
}
