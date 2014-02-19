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
package org.opens.tanaguru.entity.statistics;

import java.math.BigDecimal;
import java.util.Set;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.sdk.entity.Entity;

/**
 *
 * @author jkowalczyk
 */
public interface WebResourceStatistics extends Entity, ResultCounter {

    /**
     *
     * @return
     */
    Audit getAudit();

    /**
     *
     * @param audit
     */
    void setAudit(Audit audit);

    /**
     *
     * @return
     */
    Float getMark();

    /**
     *
     * @param mark
     */
    void setMark(Float mark);

    /**
     *
     * @return
     */
    Float getRawMark();

    /**
     *
     * @param rawMark
     */
    void setRawMark(Float rawMark);

    /**
     *
     * @return
     */
    int getNbOfFailedOccurences();

    /**
     *
     * @param nbOfFailedOccurences
     */
    void setNbOfFailedOccurences(int nbOfFailedOccurences);

    /**
     *
     * @return
     */
    int getNbOfInvalidTest();

    /**
     *
     * @param nbOfInvalidTest
     */
    void setNbOfInvalidTest(int nbOfInvalidTest) ;

    /**
     *
     * @return
     */
    WebResource getWebResource();

    /**
     * 
     * @param webResource
     */
    void setWebResource(WebResource webResource);

    /**
     * 
     * @return
     */
    Set<ThemeStatistics> getThemeStatisticsSet();

    /**
     * 
     * @param themeStatisticsSet
     */
    void setThemeStatisticsSet(Set<ThemeStatistics> themeStatisticsSet);

    /**
     *
     * @param themeStatistics
     */
    void addThemeStatistics(ThemeStatistics themeStatistics);

    /**
     *
     * @return
     */
    Set<TestStatistics> getTestStatisticsSet();

    /**
     *
     * @param themeStatisticsSet
     */
    void setTestStatisticsSet(Set<TestStatistics> themeStatisticsSet);

    /**
     *
     * @param testStatistics
     */
    void addTestStatistics(TestStatistics testStatistics);
    
    /**
     *
     * @return
     */
    Set<CriterionStatistics> getCriterionStatisticsSet();

    /**
     *
     * @param criterionStatisticsSet
     */
    void setCriterionStatisticsSet(Set<CriterionStatistics> criterionStatisticsSet);

    /**
     *
     * @param criterionStatistics
     */
    void addCriterionStatistics(CriterionStatistics criterionStatistics);

        /**
     *
     * @return
     */
    BigDecimal getWeightedNa();

    /**
     * 
     * @param weightedNa 
     */
    void setWeightedNa(BigDecimal weightedNa);

    /**
     *
     * @return
     */
    BigDecimal getWeightedNmi();

    /**
     * 
     * @param weightedNmi 
     */
    void setWeightedNmi(BigDecimal weightedNmi);

    /**
     *
     * @return
     */
    BigDecimal getWeightedPassed() ;

    /**
     *
     * @param weightedPassed
     */
    void setWeightedPassed(BigDecimal weightedPassed);

    /**
     *
     * @return
     */
    BigDecimal getWeightedFailed();

    /**
     *
     * @param weightedFailed
     */
    void setWeightedFailed(BigDecimal weightedFailed);
    
    /**
     *
     * @return
     *      the http status code
     */
    int getHttpStatusCode();

    /**
     * 
     * @param httpStatusCode
     */
    void setHttpStatusCode(int httpStatusCode);
    
}