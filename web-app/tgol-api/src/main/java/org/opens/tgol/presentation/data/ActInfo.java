/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
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
     * @return the date of the act
     */
    Date getDate();

    /**
     * Sets the date of the act
     *
     * @param date
     */
    void setDate(Date date);

    /**
     *
     * @return the Url of the act
     */
    String getUrl();

    /**
     * Sets the Url of the act
     *
     * @param url
     */
    void setUrl(String url);

    /**
     *
     * @return the scope of the act
     */
    String getScope();

    /**
     * Sets the scope of the act
     *
     * @param scope
     */
    void setScope(String scope);

    /**
     *
     * @return the weighted mark of the act
     */
    int getWeightedMark();

    /**
     * Sets the weighted act of the act
     *
     * @param weightedMark
     */
    void setWeightedMark(int weightedMark);

    /**
     *
     * @return the raw mark of the act
     */
    int getRawMark();

    /**
     * Sets the raw mark of the act
     *
     * @param rawMark
     */
    void setRawMark(int rawMark);

    /**
     *
     * @return the id of the audit associated with the act
     */
    int getAuditId();

    /**
     * Sets the id of the audit associated with the act
     *
     * @param auditId
     */
    void setAuditId(int auditId);

    /**
     *
     * @return the status of the act
     */
    String getStatus();

    /**
     * Sets the status of the act
     *
     * @param status
     */
    void setStatus(String status);

    /**
     *
     * @return
     *
     * the referential of the current act
     */
    String getReferential();

    /**
     *
     * @param referential
     */
    void setReferential(String referential);

    /**
     * @return the boolean of manual
     *
     */
    boolean isManual();

    /**
     * @param isManual 
     * *
     */
    void setManual(boolean isManual);

    /**
     * 
     * @param statusManual 
     */
    void setStatusManual(String statusManual);

    /**
     * 
     * @return the current status of the manual audit
     */
    String getStatusManual();

    /**
     * 
     * @param rawMarkManual 
     */
    void setRawMarkManual(int rawMarkManual);

    /**
     * 
     * @return 
     */
    int getRawMarkManual();

    /**
     * 
     * @param weightedMarkManual 
     */
    void setWeightedMarkManual(int weightedMarkManual);

    /**
     * 
     * @return 
     */
    int getWeightedMarkManual();

    /**
     * 
     * @param dateManual 
     */
    void setDateManual(Date dateManual);

    /**
     * 
     * @return 
     */
    Date getDateManual();

}
