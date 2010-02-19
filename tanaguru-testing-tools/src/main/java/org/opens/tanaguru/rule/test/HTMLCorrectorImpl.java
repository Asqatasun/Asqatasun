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
        result = removeLowerCaseTags(dirtyHTML);
    }

    @Override
    public void setDirtyHTML(String dirtyHTML) {
        this.dirtyHTML = dirtyHTML;
    }

    @Override
    public String getCorrectorName() {
        return "Mock";
    }

     private String removeLowerCaseTags(String cleanHtml) {
        StringBuffer newCleanHtml = new StringBuffer();
        int strPtr=0;
        int tmpPtr=0;
        while (strPtr != cleanHtml.length()){
            if (cleanHtml.charAt(strPtr) == '<') {
                if (cleanHtml.charAt(strPtr+1) == '!') { //To ignore the case of <!doctype
                    newCleanHtml.append(cleanHtml.charAt(strPtr));
                    strPtr++;
                } else if (cleanHtml.charAt(strPtr+1) == '/') {
                    tmpPtr = cleanHtml.indexOf('>', strPtr);
                    newCleanHtml.append('<');
                    newCleanHtml.append('/');
                    newCleanHtml.append(cleanHtml.substring(strPtr+2, tmpPtr).toLowerCase());
                    strPtr = tmpPtr;
                } else {
                    if (cleanHtml.indexOf(' ', strPtr) < cleanHtml.indexOf('>', strPtr)) {
                        // case of self-closing tag
                        tmpPtr = cleanHtml.indexOf(' ', strPtr);
                    } else {
                        // case of classical opening tag
                        tmpPtr = cleanHtml.indexOf('>', strPtr);
                    }
                    newCleanHtml.append('<');
                    newCleanHtml.append(cleanHtml.substring(strPtr+1, tmpPtr).toLowerCase());
                    strPtr = tmpPtr;
                }
            } else {
                newCleanHtml.append(cleanHtml.charAt(strPtr));
                strPtr++;
            }
        }
        return newCleanHtml.toString();
    }
}
