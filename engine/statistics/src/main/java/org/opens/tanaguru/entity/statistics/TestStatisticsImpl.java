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

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.reference.TestImpl;

/**
 *
 * @author jkowalczyk
 */
@Entity
@Table(name = "TEST_STATISTICS")
@XmlRootElement
public class TestStatisticsImpl 
        implements TestStatistics, Serializable, ResultCounter {

    private static final long serialVersionUID = 8264819391088181375L;

    @Id
    @GeneratedValue
    @Column(name = "Id_Test_Statistics")
    private Long id;

    @Column(name="Nb_Passed")
    private int nbOfPassed=0;

    @Column(name="Nb_Failed")
    private int nbOfFailed=0;

    @Column(name="Nb_Nmi")
    private int nbOfNmi=0;

    @Column(name="Nb_Na")
    private int nbOfNa=0;

    @OneToOne
    @JoinColumn(name = "Id_Test")
    private TestImpl test;

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
    public Test getTest() {
        return test;
    }

    @Override
    public void setTest(Test test) {
        this.test = (TestImpl)test;
    }

    @Override
    public WebResourceStatistics getWebResourceStatistics() {
        return webResourceStatistics;
    }

    @Override
    public void setWebResourceStatistics(WebResourceStatistics webResourceStatistics) {
        this.webResourceStatistics = (WebResourceStatisticsImpl)webResourceStatistics;
    }

}