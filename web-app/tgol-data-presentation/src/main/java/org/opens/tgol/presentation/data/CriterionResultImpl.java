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

import org.opens.tanaguru.entity.reference.Criterion;

/**
 * This class processes raw results and handles displayable result data at 
 * criterion level
 * 
 * @author jkowalczyk
 */
public class CriterionResultImpl implements CriterionResult{

    private Criterion criterion;
    /**
     *
     * @return
     *          The criterion
     */
    @Override
    public Criterion getCriterion() {
        return criterion;
    }

    /**
     *
     * @param criterion
     */
    @Override
    public void setCriterion(Criterion criterion) {
        this.criterion = criterion;
    }

    private String criterionShortLabel;
    /**
     *
     * @return
     *          The criterion short label
     */
    @Override
    public String getCriterionShortLabel() {
        return criterionShortLabel;
    }

    /**
     *
     * @param criterionShortLabel
     */
    @Override
    public void setCriterionShortLabel(String criterionShortLabel) {
        this.criterionShortLabel = criterionShortLabel;
    }

    private String criterionCode;
    /**
     *
     * @return
     *      the Criterion key 
     */
    @Override
    public String getCriterionCode() {
        return criterionCode;
    }

    /**
     *
     * @param criterionCode
     */
    @Override
    public void setCriterionCode(String criterionCode) {
        this.criterionCode = criterionCode;
    }

    private String criterionUrl;
    /**
     *
     * @return
     *      the tested url
     */
    @Override
    public String getCriterionUrl() {
        return criterionUrl;
    }

    /**
     *
     * @param criterionUrl
     */
    @Override
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
    @Override
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public void setResultCode(String resultCode) {
        this.resultCode=resultCode;
    }

    private String result;
    /**
     *
     * @return
     *          the displayable result data
     */
    @Override
    public String getResult() {
        return result;
    }

    /**
     *
     * @param result
     */
    @Override
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
    @Override
    public String getLevelCode() {
        return levelCode;
    }

    /**
     *
     * @param levelCode
     */
    @Override
    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    /**
     * Default constructor
     */
    public CriterionResultImpl() {
    }

}