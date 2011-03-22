/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.contentadapter.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jkowalczyk
 */
public abstract class DocumentCaseInsensitiveAdapter {


    private static final char CARRIAGE_RETURN_CHAR_1 = '\n';
    private static final char CARRIAGE_RETURN_CHAR_2 = '\r';
    private static final char EMPTY_CHAR = ' ';

    /**
     *
     * @param cleanHtml
     * @return
     */
    public static String removeLowerCaseTags(String cleanHtml) {
        StringBuilder newCleanHtml = new StringBuilder();
        int strPtr=0;
        int tmpPtr=0;
        while (strPtr != cleanHtml.length()){
            if (cleanHtml.charAt(strPtr) == '<') {
                if (cleanHtml.charAt(strPtr+1) == '!' || 
                        cleanHtml.charAt(strPtr+1) == '?' || //To ignore the case of <!doctype and <?xml
                        cleanHtml.charAt(strPtr+1) == '_') { 
                    newCleanHtml.append(cleanHtml.charAt(strPtr));
                    strPtr++;
                } else if (cleanHtml.charAt(strPtr+1) == '/') {
                    tmpPtr = cleanHtml.indexOf('>', strPtr);
                    newCleanHtml.append('<');
                    newCleanHtml.append('/');
                    newCleanHtml.append(cleanHtml.
                            substring(strPtr+2, tmpPtr).toUpperCase());
                    strPtr = tmpPtr;
                } else {
                    if (cleanHtml.indexOf(' ', strPtr) <
                            cleanHtml.indexOf('>', strPtr) &&
                            cleanHtml.indexOf(' ', strPtr) > 0) {
                        // case of self-closing tag
                        tmpPtr = cleanHtml.indexOf(' ', strPtr);
                    } else {
                        // case of classical opening tag
                        tmpPtr = cleanHtml.indexOf('>', strPtr);
                    }
                    newCleanHtml.append('<');
                    newCleanHtml.append(cleanHtml.
                            substring(strPtr+1, tmpPtr).toUpperCase());
                    strPtr = tmpPtr;
                }
            } else {
                newCleanHtml.append(cleanHtml.charAt(strPtr));
                strPtr++;
            }
        }
        String result = newCleanHtml.toString();
        newCleanHtml = null;
        return result;
    }

    /**
     *
     * @param cleanHtml
     * @return
     */
    public static String removeUpperCaseTags(String cleanHtml) {
        StringBuilder newCleanHtml = new StringBuilder();
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
                    newCleanHtml.append(cleanHtml.
                            substring(strPtr+2, tmpPtr).toLowerCase());
                    strPtr = tmpPtr;
                } else {
                    if (cleanHtml.indexOf(' ', strPtr) <
                            cleanHtml.indexOf('>', strPtr)) {
                        // case of self-closing tag
                        tmpPtr = cleanHtml.indexOf(' ', strPtr);
                    } else {
                        // case of classical opening tag
                        tmpPtr = cleanHtml.indexOf('>', strPtr);
                    }
                    newCleanHtml.append('<');
                    newCleanHtml.append(cleanHtml.
                            substring(strPtr+1, tmpPtr).toLowerCase());
                    strPtr = tmpPtr;
                }
            } else {
                newCleanHtml.append(cleanHtml.charAt(strPtr));
                strPtr++;
            }
        }
        return newCleanHtml.toString();
    }

    public static String removeDoctypeDeclaration(String html){
        int doctypeBeginTagPtr = html.indexOf("<!DOCTYPE");

        if (doctypeBeginTagPtr == -1 ) {
               doctypeBeginTagPtr = html.indexOf("<!doctype");
               if (doctypeBeginTagPtr == -1 ) {
                    return html;
               }
        }

        int doctypeEndTagPtr = html.indexOf('>', doctypeBeginTagPtr);
        StringBuilder cleanHtmlWithoutDoctype = new StringBuilder();
        if (doctypeBeginTagPtr > 0) {
            cleanHtmlWithoutDoctype.append(html, 0, doctypeBeginTagPtr-1);
        }
        cleanHtmlWithoutDoctype.append(
                html, doctypeEndTagPtr+1, html.length()-1);

        return cleanHtmlWithoutDoctype.toString();
    }

    /**
     * This method extracts the doctype declaration from the html source code
     * @param html
     * @return
     */
    public static String extractDoctypeDeclaration(String html){
        int doctypeBeginTagPtr = html.indexOf("<!DOCTYPE");

        if (doctypeBeginTagPtr == -1 ) {
           doctypeBeginTagPtr = html.indexOf("<!doctype");
               if (doctypeBeginTagPtr == -1 ) {
                return "";
            }
        }

        int doctypeEndTagPtr = html.indexOf('>', doctypeBeginTagPtr);
        String doctype = html.substring(doctypeBeginTagPtr, doctypeEndTagPtr+1).
                    trim().
                    replace(CARRIAGE_RETURN_CHAR_1,EMPTY_CHAR).
                    replace(CARRIAGE_RETURN_CHAR_2, EMPTY_CHAR);
        Pattern pattern = Pattern.compile("\\s+");
        Matcher matcher = pattern.matcher(doctype);
        doctype = matcher.replaceAll(" ");
        return doctype;
    }

}