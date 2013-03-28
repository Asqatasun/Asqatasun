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

import java.util.ArrayList;
import java.util.List;

import org.opens.tanaguru.contentadapter.Resource;
import java.io.Serializable;
import org.w3c.css.sac.SACMediaList;

/**
 * 
 * @author jkowalczyk
 * @deprecated 
 */
public class CSSOMStyleSheetImpl implements CSSOMStyleSheet, Serializable {

    private static final long serialVersionUID = -6694776584183266161L;
    
    private int lineNumber;
    private List<CSSOMRule> rules;
    private short type;
    private SACMediaList mediaList;

    public CSSOMStyleSheetImpl() {
        super();
    }

    public CSSOMStyleSheetImpl(Resource resource) {
        super();
        rules = new ArrayList<CSSOMRule>();
        lineNumber = resource.getLineNumber();
        this.type = resource.getRsrcLocator().getRsrcLocatorType();
        if (resource instanceof CSSResource) {
            this.mediaList = ((CSSResource)resource).getCssMediaList();
        }
    }

    @Override
    public boolean addCSSOMRule(CSSOMRule rule) {
        return rules.add(rule);
    }

    @Override
    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public List<CSSOMRule> getRules() {
        return rules;
    }

    @Override
    public short getType() {
        return type;
    }

    @Override
    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Override
    public void setRules(List<CSSOMRule> rules) {
        this.rules = rules;
    }

    @Override
    public void setType(short type) {
        this.type = type;
    }

    @Override
    public SACMediaList getMediaList() {
        return mediaList;
    }

    @Override
    public void setMediaList(SACMediaList media) {
        this.mediaList = media;
    }

}
