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
 * This class processes raw results and handles displayable remarks data
 * @author jkowalczyk
 */
public interface CriterionResult {

    static final String ELEMENT_NAME_KEY = "Element-Name";
    static final String LINE_NUMBER_KEY = "Line-Number";
    static final String REPRESENTATION_SUFFIX_KEY = "-representation-index";
    static final String REPRESENTATION_ORDER_SUFFIX_KEY = "-representation-order";

    static final String FAILED = "FAILED";
    static final String PASSED = "PASSED";
    static final String NEED_MORE_INFO = "NEED_MORE_INFO";
    static final String NOT_APPLICABLE = "NOT_APPLICABLE";

    static final String FAILED_LOWER = "failed";
    static final String PASSED_LOWER = "passed";
    static final String NEED_MORE_INFO_LOWER = "nmi";
    static final String NOT_APPLICABLE_LOWER = "na";

    static final int TABULAR_REPRESENTATION = 2;

    static final String REPRESENTATION_BUNDLE_NAME = "representation";

    static final String REPRESENTATION_FILE_PREFIX =
            "data-representation/data-representation-";
    static final String REPRESENTATION_FILE_SUFFIX = ".jsp";

    /**
     *
     * @return
     *          The criterion 
     */
    Criterion getCriterion();

    /**
     * 
     * @param criterion
     */
    void setCriterion(Criterion criterion);

    /**
     *
     * @return
     *          The criterion short label
     */
    String getCriterionShortLabel();

    /**
     *
     * @param criterionShortLabel
     */
    void setCriterionShortLabel(String criterionShortLabel);

    /**
     *
     * @return
     *      the criterion key 
     */
    String getCriterionCode();

    /**
     *
     * @param criterionCode
     */
    void setCriterionCode(String criterionCode);

    /**
     *
     * @return
     *      the criterioned url
     */
    String getCriterionUrl();

    /**
     *
     * @param criterionUrl
     */
    void setCriterionUrl(String criterionUrl);

    /**
     *
     * @return
     * The lower-case-formatted result value. This data is usefull to apply the
     * correct css on the displayed result String.
     */
    String getResultCode();

    /**
     * 
     * @param resultCode
     */
    void setResultCode(String resultCode);

    /**
     *
     * @return
     *          the displayable result data
     */
    String getResult();

    /**
     *
     * @param result
     */
    void setResult(String result);

    /**
     * 
     * @return
     *          the code of the level for the current criterion
     */
    String getLevelCode();

    /**
     *
     * @param levelCode
     */
    void setLevelCode(String levelCode);

}