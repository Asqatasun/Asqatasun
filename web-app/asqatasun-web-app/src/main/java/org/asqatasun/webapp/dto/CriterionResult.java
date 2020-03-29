/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.webapp.dto;

import org.asqatasun.entity.reference.Criterion;

/**
 * This class processes raw results and handles displayable result data at 
 * criterion level
 * 
 * @author jkowalczyk
 */
public class CriterionResult {

    private Criterion criterion;
    /**
     *
     * @return
     *          The criterion
     */

    public Criterion getCriterion() {
        return criterion;
    }

    /**
     *
     * @param criterion
     */

    public void setCriterion(Criterion criterion) {
        this.criterion = criterion;
    }

    private String criterionShortLabel;
    /**
     *
     * @return
     *          The criterion short label
     */

    public String getCriterionShortLabel() {
        return criterionShortLabel;
    }

    /**
     *
     * @param criterionShortLabel
     */

    public void setCriterionShortLabel(String criterionShortLabel) {
        this.criterionShortLabel = criterionShortLabel;
    }

    private String criterionCode;
    /**
     *
     * @return
     *      the Criterion key 
     */

    public String getCriterionCode() {
        return criterionCode;
    }

    /**
     *
     * @param criterionCode
     */

    public void setCriterionCode(String criterionCode) {
        this.criterionCode = criterionCode;
    }

    private String criterionUrl;
    /**
     *
     * @return
     *      the tested url
     */

    public String getCriterionUrl() {
        return criterionUrl;
    }

    /**
     *
     * @param criterionUrl
     */

    public void setCriterionUrl(String criterionUrl) {
        this.criterionUrl = criterionUrl;
    }

    private String resultCode;
    /**
     *
     * @return
     * The lower-case-formatted result value. This data is usefull to apply the
     * correct css on the displayed result String.
     */

    public String getResultCode() {
        return resultCode;
    }


    public void setResultCode(String resultCode) {
        this.resultCode=resultCode;
    }

    private String result;
    /**
     *
     * @return
     *          the displayable result data
     */

    public String getResult() {
        return result;
    }

    /**
     *
     * @param result
     */

    public void setResult(String result) {
        this.result = result;
    }

    /**
     * The test level
     */
    private String levelCode;
    /**
     *
     * @return
     *          the code of the level for the current test
     */

    public String getLevelCode() {
        return levelCode;
    }

    /**
     *
     * @param levelCode
     */

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    /**
     * Default constructor
     */
    public CriterionResult() {
    }

}
