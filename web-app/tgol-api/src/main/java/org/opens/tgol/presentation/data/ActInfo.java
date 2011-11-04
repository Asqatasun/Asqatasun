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

import java.util.Date;

/**
 * This interface handles displayable actInfo data
 *
 * @author jkowalczyk
 */
public interface ActInfo {

    /**
     *
     * @return
     *      the date of the act
     */
    public Date getDate();

    /**
     * Sets the date of the act
     *
     * @param date
     */
    public void setDate(Date date);

    /**
     *
     * @return
     *      the Url of the act
     */
    public String getUrl();

    /**
     * Sets the Url of the act
     *
     * @param url
     */
    public void setUrl(String url);

    /**
     *
     * @return
     *      the scope of the act
     */
    public String getScope();

    /**
     * Sets the scope of the act
     *
     * @param scope
     */
    public void setScope(String scope);

    /**
     *
     * @return
     *      the weighted mark of the act
     */
    public int getWeightedMark();

    /**
     * Sets the weighted act of the act
     * 
     * @param weightedMark
     */
    public void setWeightedMark(int weightedMark);

    /**
     *
     * @return
     *      the raw mark of the act
     */
    public int getRawMark();

    /**
     * Sets the raw mark of the act
     * 
     * @param rawMark
     */
    public void setRawMark(int rawMark);

    /**
     *
     * @return
     *      the id of the webresource associated with the act
     */
    public int getWebresourceId();

    /**
     * Sets the id of the webresource associated with the act
     *
     * @param webresourceId
     */
    public void setWebresourceId(int webresourceId);

    /**
     *
     * @return
     *      the status of the act
     */
    public String getStatus();

    /**
     * Sets the status of the act
     * 
     * @param status
     */
    public void setStatus(String status);

}