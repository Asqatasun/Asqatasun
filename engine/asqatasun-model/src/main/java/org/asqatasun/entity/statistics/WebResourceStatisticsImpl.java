/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
package org.asqatasun.entity.statistics;

import org.asqatasun.entity.audit.Audit;
import org.asqatasun.entity.audit.AuditImpl;
import org.asqatasun.entity.subject.WebResource;
import org.asqatasun.entity.subject.WebResourceImpl;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author jkowalczyk
 */
@Entity
@Table(name = "WEB_RESOURCE_STATISTICS")
@XmlRootElement
public class WebResourceStatisticsImpl 
        implements WebResourceStatistics, Serializable, ResultCounter{

    private static final long serialVersionUID = -4723351714043709585L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Web_Resource_Statistics")
    private Long id;

    @Column(name = "Mark")
    private Float mark;

    @Column(name = "Raw_Mark")
    private Float rawMark;

    @Column(name="Nb_Passed")
    private int nbOfPassed=0;

    @Column(name="Nb_Failed")
    private int nbOfFailed=0;

    @Column(name="Nb_Nmi")
    private int nbOfNmi=0;

    @Column(name="Nb_Na")
    private int nbOfNa=0;
    
    @Column(name="Nb_Suspected")
    private int nbOfSuspected=0;
    
    @Column(name="Nb_Detected")
    private int nbOfDetected=0;
    
    @Column(name="Nb_Not_Tested")
    private int nbOfNotTested=0;
    
    @Column(name="Weighted_Passed", precision=10, scale=1)
    private BigDecimal weightedPassed = BigDecimal.ZERO;

    @Column(name="Weighted_Failed", precision=10, scale=1)
    private BigDecimal weightedFailed = BigDecimal.ZERO;

    @Column(name="Weighted_Nmi", precision=10, scale=1)
    private BigDecimal weightedNmi = BigDecimal.ZERO;

    @Column(name="Weighted_Na", precision=10, scale=1)
    private BigDecimal weightedNa = BigDecimal.ZERO;

    @Column(name="Nb_Invalid_Test")
    private int nbOfInvalidTest=0;

    @Column(name="Nb_Failed_Occurrences")
    private int nbOfFailedOccurences=0;

    @Column(name="Http_Status_Code")
    private int httpStatusCode=-1;

    @ManyToOne
    @JoinColumn(name = "Id_Web_Resource")
    private WebResourceImpl webResource;
    
    @Column(name="Manual_Audit")
    private int isManualAuditStatistics=0;

    @ManyToOne
    @JoinColumn(name = "Id_Audit")
    private AuditImpl audit;

    @OneToMany(mappedBy = "webResourceStatistics", cascade = {CascadeType.PERSIST})
    protected Set<ThemeStatisticsImpl> themeStatisticsSet = new LinkedHashSet<>();

    @OneToMany(mappedBy = "webResourceStatistics", cascade = {CascadeType.PERSIST})
    protected Set<TestStatisticsImpl> testStatisticsSet = new LinkedHashSet<>();
    
    @OneToMany(mappedBy = "webResourceStatistics", cascade = {CascadeType.PERSIST})
    protected Set<CriterionStatisticsImpl> criterionStatisticsSet = new LinkedHashSet<>();

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Float getMark() {
        return mark;
    }

    @Override
    public void setMark(Float mark) {
        this.mark = mark;
    }

    @Override
    public Float getRawMark() {
        return rawMark;
    }

    @Override
    public void setRawMark(Float rawMark) {
        this.rawMark = rawMark;
    }

    @Override
    public int getNbOfFailed() {
        return nbOfFailed;
    }

    @Override
    public void setNbOfFailed(int nbOfFailed) {
        this.nbOfFailed = nbOfFailed;
    }

    @Override
    public int getNbOfNa() {
        return nbOfNa;
    }

    @Override
    public void setNbOfNa(int nbOfNa) {
        this.nbOfNa = nbOfNa;
    }

    @Override
    public int getNbOfNmi() {
        return nbOfNmi;
    }

    @Override
    public void setNbOfNmi(int nbOfNmi) {
        this.nbOfNmi = nbOfNmi;
    }

    @Override
    public int getNbOfPassed() {
        return nbOfPassed;
    }

    @Override
    public void setNbOfPassed(int nbOfPassed) {
        this.nbOfPassed = nbOfPassed;
    }

    @Override
    public int getNbOfSuspected() {
        return this.nbOfSuspected;
    }

    @Override
    public void setNbOfSuspected(int nbOfSuspected) {
        this.nbOfSuspected = nbOfSuspected;
    }

    @Override
    public int getNbOfDetected() {
        return this.nbOfDetected;
    }
    
    @Override
    public void setNbOfDetected(int nbOfDetected) {
        this.nbOfDetected = nbOfDetected;
    }

    @Override
    public int getNbOfNotTested() {
        return this.nbOfNotTested;
    }

    @Override
    public void setNbOfNotTested(int nbOfNotTested) {
        this.nbOfNotTested = nbOfNotTested;
    }
    
    @Override
    public int getNbOfFailedOccurences() {
        return nbOfFailedOccurences;
    }

    @Override
    public void setNbOfFailedOccurences(int nbOfFailedOccurences) {
        this.nbOfFailedOccurences = nbOfFailedOccurences;
    }

    @Override
    public int getNbOfInvalidTest() {
        return nbOfInvalidTest;
    }

    @Override
    public void setNbOfInvalidTest(int nbOfInvalidTest) {
        this.nbOfInvalidTest = nbOfInvalidTest;
    }

    @Override
    public Audit getAudit() {
        return audit;
    }

    @Override
    public void setAudit(Audit audit) {
        this.audit = (AuditImpl)audit;
    }

    @Override
    public WebResource getWebResource() {
        return webResource;
    }

    @Override
    public void setWebResource(WebResource webResource) {
        this.webResource = (WebResourceImpl)webResource;
    }

    @Override
    public Set<ThemeStatistics> getThemeStatisticsSet() {
        return (Set<ThemeStatistics>)(LinkedHashSet)themeStatisticsSet;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setThemeStatisticsSet(Set<ThemeStatistics> themeStatisticsSet) {
        for (ThemeStatistics ts : themeStatisticsSet) {
            this.themeStatisticsSet.add((ThemeStatisticsImpl)ts);
        }
    }

    @Override
    public void addThemeStatistics(ThemeStatistics themeStatistics) {
        themeStatistics.setWebResourceStatistics(this);
        this.themeStatisticsSet.add((ThemeStatisticsImpl) themeStatistics);
    }

    @Override
    public Set<TestStatistics> getTestStatisticsSet() {
        return (Set<TestStatistics>)(LinkedHashSet)testStatisticsSet;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void setTestStatisticsSet(Set<TestStatistics> testStatisticsSet) {
        for (TestStatistics ts : testStatisticsSet) {
            this.testStatisticsSet.add((TestStatisticsImpl)ts);
        }
    }

    @Override
    public void addTestStatistics(TestStatistics testStatistics) {
        testStatistics.setWebResourceStatistics(this);
        this.testStatisticsSet.add((TestStatisticsImpl) testStatistics);
    }

    
    @Override
    public Set<CriterionStatistics> getCriterionStatisticsSet() {
        return (Set<CriterionStatistics>)(LinkedHashSet)criterionStatisticsSet;
    }

    @Override
    public void setCriterionStatisticsSet(Set<CriterionStatistics> criterionStatisticsSet) {
        for (CriterionStatistics cs : criterionStatisticsSet) {
            this.criterionStatisticsSet.add((CriterionStatisticsImpl)cs);
        }
    }

    @Override
    public void addCriterionStatistics(CriterionStatistics criterionStatistics) {
        criterionStatistics.setWebResourceStatistics(this);
        this.criterionStatisticsSet.add((CriterionStatisticsImpl) criterionStatistics);
    }
    
    @Override
    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    @Override
    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    @Override
    public BigDecimal getWeightedNa() {
        return weightedNa;
    }

    @Override
    public void setWeightedNa(BigDecimal weightedNa) {
        this.weightedNa = weightedNa;
    }

    @Override
    public BigDecimal getWeightedNmi() {
        return weightedNmi;
    }

    @Override
    public void setWeightedNmi(BigDecimal weightedNmi) {
        this.weightedNmi = weightedNmi;
    }

    @Override
    public BigDecimal getWeightedPassed() {
        return weightedPassed;
    }

    @Override
    public void setWeightedPassed(BigDecimal weightedPassed) {
        this.weightedPassed = weightedPassed;
    }

    @Override
    public BigDecimal getWeightedFailed() {
        return weightedFailed;
    }

    @Override
    public void setWeightedFailed(BigDecimal weightedFailed) {
        this.weightedFailed = weightedFailed;
    }

    @Override
    public int getIsManualAuditStatistics() {
		return isManualAuditStatistics;
	}

    @Override
    public void setIsManualAuditStatistics(int isManualAuditStatistics) {
		this.isManualAuditStatistics = isManualAuditStatistics;
	}

}