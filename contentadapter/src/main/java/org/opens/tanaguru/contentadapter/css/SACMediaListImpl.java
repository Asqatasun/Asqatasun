/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.contentadapter.css;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.w3c.css.sac.SACMediaList;

/**
 * 
 * @author jkowalczyk
 */
public class SACMediaListImpl implements SACMediaList, Serializable{

    private List<String> mediaList = new ArrayList<String>();

    public SACMediaListImpl() {}

    @Override
    public int getLength() {
        return mediaList.size();
    }

    @Override
    public String item(int i) {
        return mediaList.get(i);
    }

    public void addItem (String media) {
        mediaList.add(media);
    }

}
