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
package org.opens.tgol.presentation.data;

/**
 * This class handles displayable data that represent statistics about themes
 * 
 * @author jkowalczyk
 */
public class FailedThemeInfoImpl implements FailedThemeInfo{

    /**
     * Default constructor
     */
    public FailedThemeInfoImpl(){}

    /**
     * Constructor
     * @param testLabel
     * @param pageCounter
     */
    public FailedThemeInfoImpl(Long themeId, Long resultCounter) {
        this.themeId = themeId;
        this.resultCounter = resultCounter;
    }

    private Long themeId;

    /**
     *
     * @return
     *          the code of the theme
     */
    @Override
    public Long getThemeId() {
        return themeId;
    }

    /**
     *
     * @param themeLabel
     */
    @Override
    public void setThemeId(Long themeId) {
        this.themeId = themeId;
    }

    private Long resultCounter;

    /**
     *
     * @return
     *          the number of result for the given theme
     */
    @Override
    public Long getResultCounter() {
        return resultCounter;
    }

    /**
     *
     * @param idWebResource
     */
    @Override
    public void setResultCounter(Long resultCounter) {
        this.resultCounter = resultCounter;
    }

}