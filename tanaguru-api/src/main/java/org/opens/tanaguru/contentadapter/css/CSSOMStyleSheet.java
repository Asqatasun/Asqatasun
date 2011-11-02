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
import org.w3c.css.sac.SACMediaList;

/**
 * 
 * @author jkowalczyk
 */
public interface CSSOMStyleSheet {

    /**
     *
     * @param rule
     *            the rule to add
     * @return true if the rule was successfully added, false otherwise
     */
    boolean addCSSOMRule(CSSOMRule rule);

    /**
     *
     * @return the line number
     */
    int getLineNumber();

    /**
     *
     * @return a list of rules
     */
    List<CSSOMRule> getRules();

    /**
     *
     * @return a style sheet type
     */
    short getType();

    /**
     *
     * @return a style sheet media
     */
    SACMediaList getMediaList();

    /**
     *
     * @param lineNumber
     *            the line number to set
     */
    void setLineNumber(int lineNumber);

    /**
     *
     * @param rules
     *            the list of rules to set
     */
    void setRules(List<CSSOMRule> rules);

    /**
     *
     * @param type
     *            the type to set
     */
    void setType(short type);

    /**
     *
     * @param media
     *              the media to set
     */
    void setMediaList(SACMediaList media);
}
