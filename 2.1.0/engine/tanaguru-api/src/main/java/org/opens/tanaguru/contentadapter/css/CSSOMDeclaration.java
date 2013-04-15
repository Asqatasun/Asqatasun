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

/**
 * 
 * @author jkowalczyk
 */
public interface CSSOMDeclaration {

    /**
     *
     * @return the property
     */
    String getProperty();

    /**
     *
     * @return a collection of rules
     */
    List<CSSOMRule> getRule();

    /**
     *
     * @return the unit
     */
    short getUnit();

    /**
     *
     * @return the preperty value
     */
    String getValue();

    /**
     *
     * @param property
     *            the property to set
     */
    void setProperty(String property);

    /**
     *
     *
     * @param rule
     *            the list of rules to set
     */
    void setRule(List<CSSOMRule> rule);

    /**
     *
     * @param unit
     *            the unit to set
     */
    void setUnit(short unit);

    /**
     *
     * @param value
     *            the value to set
     */
    void setValue(String value);

}