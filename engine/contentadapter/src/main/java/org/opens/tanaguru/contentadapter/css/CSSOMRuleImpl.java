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

import java.io.Serializable;
import org.w3c.css.sac.SACMediaList;

/**
 * 
 * @author jkowalczyk
 */
public class CSSOMRuleImpl implements CSSOMRule, Serializable {

    private static final long serialVersionUID = -2809426828803225130L;
    
    private List<CSSOMDeclaration> declarations = new ArrayList<CSSOMDeclaration>();
    private CSSOMStyleSheet ownerStyle;
    private List<CSSOMSelector> selectors = new ArrayList<CSSOMSelector>();
    private SACMediaList mediaList=  null;

    public CSSOMRuleImpl() {
        super();
    }

    public CSSOMRuleImpl(List<CSSOMSelector> selectors,
            List<CSSOMDeclaration> declarations, CSSOMStyleSheet ownerStyle,
            String ruleTxt) {
        super();
        this.selectors = selectors;
        this.declarations = declarations;
        this.ownerStyle = ownerStyle;
    }

    public boolean addCSSOMDeclaration(CSSOMDeclaration declaration) {
        return declarations.add(declaration);
    }

    public boolean addCSSOMSelector(CSSOMSelector selector) {
        return selectors.add(selector);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CSSOMRuleImpl other = (CSSOMRuleImpl) obj;
        if (declarations == null) {
            if (other.declarations != null) {
                return false;
            }
        } else if (!declarations.equals(other.declarations)) {
            return false;
        }
        if (selectors == null) {
            if (other.selectors != null) {
                return false;
            }
        } else if (!selectors.equals(other.selectors)) {
            return false;
        }
        return true;
    }

    public List<CSSOMDeclaration> getDeclarations() {
        return declarations;
    }

    public CSSOMStyleSheet getOwnerStyle() {
        return ownerStyle;
    }

    public List<CSSOMSelector> getSelectors() {
        return selectors;
    }

    public void setDeclarations(List<CSSOMDeclaration> declarations) {
        this.declarations = declarations;
    }

    public void setOwnerStyle(CSSOMStyleSheet ownerStyle) {
        this.ownerStyle = ownerStyle;
    }

    public void setSelectors(List<CSSOMSelector> selectors) {
        this.selectors = selectors;
    }

    @Override
    public SACMediaList getMediaList() {
        return mediaList;
    }

    @Override
    public void setMediaList(SACMediaList mediaList) {
        this.mediaList = mediaList;
    }
}
