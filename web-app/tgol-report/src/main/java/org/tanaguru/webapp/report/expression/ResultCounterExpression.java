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
package org.tanaguru.webapp.report.expression;

import ar.com.fdvs.dj.domain.CustomExpression;
import java.util.Map;
import org.apache.log4j.Logger;
import org.tanaguru.webapp.presentation.data.ResultCounter;

/**
 *
 * @author jkowalczyk
 */
public class ResultCounterExpression implements CustomExpression {

    private static final long serialVersionUID = 1174999656431046383L;

    public static final String RESULT_COUNTER_FIELD_KEY = "resultCounter";

    public static final int PASSED_INDEX = 0;
    public static final int FAILED_INDEX = 1;
    public static final int SUS_PASSED_INDEX = 2;
    public static final int SUS_FAILED_INDEX = 3;
    public static final int NMI_INDEX = 4;
    public static final int NA_INDEX = 5;
    
    private int resultType;

    /**
     * Default constructor
     */
    public ResultCounterExpression(int resultType) {
        this.resultType = resultType;
    }

    public Object evaluate(Map fields, Map variables, Map parameters) {
        ResultCounter resultCounter = (ResultCounter) fields.get(RESULT_COUNTER_FIELD_KEY);
        switch (resultType) {
            case PASSED_INDEX:
                if (resultCounter.getPassedCount() < 0) {
                    return "";
                }
                return String.valueOf(resultCounter.getPassedCount());
            case FAILED_INDEX:
                if (resultCounter.getFailedCount() < 0) {
                    return "";
                }
                return String.valueOf(resultCounter.getFailedCount());
            case SUS_FAILED_INDEX:
                if (resultCounter.getSuspectedFailedCount() < 0) {
                    return "";
                }
                return String.valueOf(resultCounter.getSuspectedFailedCount());
            case SUS_PASSED_INDEX:
                if (resultCounter.getSuspectedPassedCount() < 0) {
                    return "";
                }
                return String.valueOf(resultCounter.getSuspectedPassedCount());
            case NMI_INDEX:
                if (resultCounter.getNmiCount() < 0) {
                    return "";
                }
                Logger.getLogger(this.getClass()).info(resultCounter.getNmiCount());
                return String.valueOf(resultCounter.getNmiCount());
            case NA_INDEX:
                if (resultCounter.getNaCount() < 0) {
                    return "";
                }
                return String.valueOf(resultCounter.getNaCount());
        }
        return String.valueOf(resultCounter.getPassedCount());
    }

    @Override
    public String getClassName() {
        return String.class.getName();
    }

}