/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.entity.audit;

/**
 * 
 * @author jkowalczyk
 */
public interface SourceCodeRemark extends ProcessRemark {

    /**
     *
     * @return the position of the first character
     */
    int getCharacterPosition();

    /**
     *
     * @return the line number
     */
    int getLineNumber();

    /**
     *
     * @return the target
     */
    String getTarget();
    
    /**
     *
     * @return the snippet
     */
    String getSnippet();

    /**
     *
     * @param characterPosition
     *            the position of the character to set
     */
    void setCharacterPosition(int characterPosition);

    /**
     *
     * @param lineNumber
     *            the line number to set
     */
    void setLineNumber(int lineNumber);

    /**
     *
     * @param target
     *            the target to set
     */
    void setTarget(String target);
    
    /**
     *
     * @param snippet
     *            the snippet to set
     */
    void setSnippet(String snippet);

}