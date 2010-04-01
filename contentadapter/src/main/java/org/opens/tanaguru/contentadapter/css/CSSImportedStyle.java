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
public interface CSSImportedStyle {

    /**
     *
     * @return the path of the imported styleSheet
     */
    String getPath();

    /**
     * Set the path of the imported styleSheet
     * @param path
     */
    void setPath(String path);

    /**
     *
     * @return the sac media list of the imported styleSheet
     */
    SACMediaList getSACMediaList();

    /**
     * Set the sac media list of the imported styleSheet
     * @param sacMediaList
     */
    void setSACMediaList (SACMediaList sacMediaList);

}
