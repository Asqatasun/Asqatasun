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
    public Audit getAudit();

    /**
     *
     * @param audit
     */
    public void setAudit(Audit audit);

    /**
     *
     * @return
     */
    public Float getMark();

    /**
     *
     * @param mark
     */
    public void setMark(Float mark);

    /**
     *
     * @return
     */
    public Float getRawMark();

    /**
     *
     * @param mark
     */
    public void setRawMark(Float rawMark);

    /**
     *
     * @return
     */
    public int getNbOfFailedOccurences();

    /**
     *
     * @param nbOfFailedOccurences
     */
    public void setNbOfFailedOccurences(int nbOfFailedOccurences);

    /**
     *
     * @return
     */
    public int getNbOfInvalidTest();

    /**
     *
     * @param nbOfInvalidTest
     */
    public void setNbOfInvalidTest(int nbOfInvalidTest) ;

    /**
     *
     * @return
     */
    public WebResource getWebResource();

    /**
     * 
     * @param page
     */
    public void setWebResource(WebResource webResource);

    /**
     * 
     * @return
     */
    public Set<ThemeStatistics> getThemeStatisticsSet();

    /**
     * 
     * @param themeStatisticsSet
     */
    public void setThemeStatisticsSet(Set<ThemeStatistics> themeStatisticsSet);

    /**
     *
     * @param themeStatisticsSet
     */
    public void addThemeStatistics(ThemeStatistics themeStatistics);

    /**
     *
     * @return
     */
    public Set<TestStatistics> getTestStatisticsSet();

    /**
     *
     * @param themeStatisticsSet
     */
    public void setTestStatisticsSet(Set<TestStatistics> themeStatisticsSet);

    /**
     *
     * @param themeStatisticsSet
     */
    public void addTestStatistics(TestStatistics testStatistics);

    /**
     *
     * @return
     *      the http status code
     */
    public int getHttpStatusCode();

    /**
     * 
     * @param httpStatusCode
     */
    public void setHttpStatusCode(int httpStatusCode);
    
    /**
     *
     * @return
     */
    public BigDecimal getWeightedNa();

    /**
     *
     * @param weightedNA
     */
    public void setWeightedNa(BigDecimal weightedNa);

    /**
     *
     * @return
     */
    public BigDecimal getWeightedNmi();

    /**
     *
     * @param weightedNMI
     */
    public void setWeightedNmi(BigDecimal weightedNmi);

    /**
     *
     * @return
     */
    public BigDecimal getWeightedPassed() ;

    /**
     *
     * @param weightedPassed
     */
    public void setWeightedPassed(BigDecimal weightedPassed);

    /**
     *
     * @return
     */
    public BigDecimal getWeightedFailed();

    /**
     *
     * @param weightedFailed
     */
    public void setWeightedFailed(BigDecimal weightedFailed);

}