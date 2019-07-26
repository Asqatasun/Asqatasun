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

import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.entity.reference.Criterion;
import org.asqatasun.entity.reference.CriterionImpl;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author jkowalczyk
 */
@Entity
@Table(name = "CRITERION_STATISTICS")
@XmlRootElement
public class CriterionStatisticsImpl 
        implements CriterionStatistics, Serializable, ResultCounter {

    private static final long serialVersionUID = 3956468354933408288L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Criterion_Statistics")
    private Long id;

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
    
    @Enumerated(EnumType.STRING)
    @Column(name = "Criterion_Result")
    private TestSolution criterionResult;

    @OneToOne
    @JoinColumn(name = "Id_Criterion")
    private CriterionImpl criterion;

    @ManyToOne
    @JoinColumn(name = "Id_Web_Resource_Statistics")
    private WebResourceStatisticsImpl webResourceStatistics;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
    public Criterion getCriterion() {
        return criterion;
    }

    @Override
    public void setCriterion(Criterion criterion) {
        this.criterion = (CriterionImpl)criterion;
    }

    @Override
    public WebResourceStatistics getWebResourceStatistics() {
        return webResourceStatistics;
    }

    @Override
    public void setWebResourceStatistics(WebResourceStatistics webResourceStatistics) {
        this.webResourceStatistics = (WebResourceStatisticsImpl)webResourceStatistics;
    }
    
    @Override
    public TestSolution getCriterionResult() {
        return criterionResult;
    }

    @Override
    public void setCriterionResult(TestSolution definiteValue) {
        this.criterionResult = definiteValue;
    }
}