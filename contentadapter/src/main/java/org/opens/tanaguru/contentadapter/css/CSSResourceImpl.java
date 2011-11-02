/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.contentadapter.css;

import org.opens.tanaguru.contentadapter.util.AbstractResource;
import org.opens.tanaguru.contentadapter.RsrcLocator;
import org.w3c.css.sac.SACMediaList;

/**
 * 
 * @author jkowalczyk
 */
public class CSSResourceImpl extends AbstractResource implements CSSResource {

    private SACMediaList mediaList;
    private final String resourceName = "#StyleSheet";
    private final String defaultMediaValue = "all";
    private final int resourceType = 1;

    public CSSResourceImpl() {
        super();
    }

    public CSSResourceImpl(
            String resource,
            int lineNumber,
            RsrcLocator location) {

        super(resource, lineNumber, location);
        mediaList = new SACMediaListImpl();
        ((SACMediaListImpl) mediaList).addItem(defaultMediaValue);
    }

    public CSSResourceImpl(
            String resource,
            int lineNumber,
            RsrcLocator location,
            SACMediaList mediaList) {

        super(resource, lineNumber, location);
        this.setCssMediaList(mediaList);
    }

    @Override
    public String getRsrcName() {
        return resourceName;
    }

    @Override
    public short getRsrcType() {
        return resourceType;
    }

    @Override
    public SACMediaList getCssMediaList() {
        return mediaList;
    }

    @Override
    public void setCssMediaList(SACMediaList mediaList) {
        this.mediaList = mediaList;
    }
}
