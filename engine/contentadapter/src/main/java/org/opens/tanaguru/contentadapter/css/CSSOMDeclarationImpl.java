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

import java.util.List;

import java.io.Serializable;

/**
 * 
 * @author jkowalczyk
 * @deprecated 
 */
public class CSSOMDeclarationImpl implements CSSOMDeclaration, Serializable {

    private static final long serialVersionUID = -9067686770791179384L;
    
    private String property;
    private List<CSSOMRule> rule;
    private short unit;
    private String value;

    public CSSOMDeclarationImpl() {
        super();
    }

    public CSSOMDeclarationImpl(String property, String value, short unit) {
        super();
        this.property = property;
        this.value = value;
        this.unit = unit;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CSSOMDeclarationImpl other = (CSSOMDeclarationImpl) obj;
        if (property == null) {
            if (other.property != null) {
                return false;
            }
        } else if (!property.equals(other.property)) {
            return false;
        }
        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
            return false;
        }
        return true;
    }

    @Override
    public String getProperty() {
        return property;
    }

    @Override
    public List<CSSOMRule> getRule() {
        return rule;
    }

    @Override
    public short getUnit() {
        return unit;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    public void setRule(List<CSSOMRule> rule) {
        this.rule = rule;
    }

    @Override
    public void setUnit(short unit) {
        this.unit = unit;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

}