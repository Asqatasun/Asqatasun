/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.contentadapter.css;

import org.w3c.css.sac.SACMediaList;

/**
 *
 * @author jkowalczyk
 */
public class CSSImportedStyleImpl implements CSSImportedStyle {

    private String path = null;

    private SACMediaList sacMediaList = null;

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public SACMediaList getSACMediaList() {
        return sacMediaList;
    }

    @Override
    public void setSACMediaList(SACMediaList sacMediaList) {
        this.sacMediaList = sacMediaList;
    }

}
