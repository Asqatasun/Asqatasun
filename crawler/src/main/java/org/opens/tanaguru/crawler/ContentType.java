/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.crawler;

/**
 *
 * @author jkowalczyk
 */
public enum ContentType {

    html("html"),
    css("css"),
    js("js"),
    img("image"),
    misc(""),;

    /**
     * Constructor
     * @param type
     */
    private ContentType(String type){
        this.type = type;
    }

    /**
     *
     */
    private String type;

    /**
     *
     * @return the type of the content
     */
    public String getType(){
        return type;
    }

}
