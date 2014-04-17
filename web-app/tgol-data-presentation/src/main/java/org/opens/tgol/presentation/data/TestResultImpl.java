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

import java.util.ArrayList;
import java.util.List;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.reference.Test;

/**
 * This class processes raw results and handles displayable remarks data
 * @author jkowalczyk
 */
public class TestResultImpl implements TestResult{

	
    private List<RemarkInfos> remarkInfosList = new ArrayList<RemarkInfos>();
    
    /**
     *
     * @return
     *          the list of remark info for the given test
     */
    @Override
    public List<RemarkInfos> getRemarkInfosList() {
        return remarkInfosList;
    }

    private String[] testEvidenceRepresentationOrder;
    @Override
    public void setTestEvidenceRepresentationOrder(String testEvidenceRepresentationOrder) {
        this.testEvidenceRepresentationOrder = testEvidenceRepresentationOrder.split(";");
    }
    private String manualStatus;
	private String comment;
	@Override
    public String getManualStatus() {
		return manualStatus;
	}
	
	public void setManualStatus(String manualStatus) {
		this.manualStatus = manualStatus;
	}
	@Override
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
    public String[] getTestEvidenceRepresentationOrder() {
        return testEvidenceRepresentationOrder;
    }

    private int testRepresentationType;
    @Override
    public int getTestRepresentationType() {
        return testRepresentationType;
    }

    private String testRepresentation;
    /**
     *
     * @return
     *          the test representation needed to display test results
     */
    @Override
    public String getTestRepresentation() {
        return testRepresentation;
    }

    /**
     *
     * @param testRepresentationType
     */
    @Override
    public void setTestRepresentation(int testRepresentationType) {
        this.testRepresentation = REPRESENTATION_FILE_PREFIX +
                testRepresentationType +
                REPRESENTATION_FILE_SUFFIX;
        this.testRepresentationType = testRepresentationType;
    }

    private Test test;
    /**
     *
     * @return
     *          The test 
     */
    @Override
    public Test getTest() {
        return test;
    }

    /**
     *
     * @param test
     */
    @Override
    public void setTest(Test test) {
        this.test = test;
    }

    private String testShortLabel;
    /**
     *
     * @return
     *          The test short label
     */
    @Override
    public String getTestShortLabel() {
        return testShortLabel;
    }

    /**
     *
     * @param testShortLabel
     */
    @Override
    public void setTestShortLabel(String testShortLabel) {
        this.testShortLabel = testShortLabel;
    }

    private String testCode;
    /**
     *
     * @return
     *      the test key 
     */
    @Override
    public String getTestCode() {
        return testCode;
    }

    /**
     *
     * @param testCode
     */
    @Override
    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    private String testUrl;
    /**
     *
     * @return
     *      the tested url
     */
    @Override
    public String getTestUrl() {
        return testUrl;
    }

    /**
     *
     * @param testUrl
     */
    @Override
    public void setTestUrl(String testUrl) {
        this.testUrl = testUrl;
    }

    private int elementCounter = 0;
    /**
     *
     * @return
     *          the number of encountered elements
     */
    @Override
    public int getElementCounter() {
        return elementCounter;
    }

    /**
     *
     * @param elementCounter
     */
    @Override
    public void setElementCounter(int elementCounter) {
        this.elementCounter = elementCounter;
    }

    private ResultCounter resultCounter;
    @Override
    public ResultCounter getResultCounter() {
        return resultCounter;
    }

    @Override
    public void setResultCounter(ResultCounter resultCounter) {
        this.resultCounter = resultCounter;
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

    private String ruleDesignUrl;
    /**
     *
     * @return
     *          the rule design url that explains the algorithm for the current
     *          test
     */
    @Override
    public String getRuleDesignUrl() {
        return ruleDesignUrl;
    }

    /**
     *
     * @param ruleDesignUrl
     */
    @Override
    public void setRuleDesignUrl(String ruleDesignUrl) {
        this.ruleDesignUrl = ruleDesignUrl;
    }

    boolean isTruncated = false;
    @Override
    public boolean getIsTruncated() {
        return isTruncated;
    }

    @Override
    public void setTruncated(boolean isTruncated) {
        this.isTruncated = isTruncated;
    }
    
    /**
     * Default constructor
     */
    public TestResultImpl() {
    }
    
    private List<DefiniteResult> history;

	public List<DefiniteResult> getHistory() {
		return history;
	}

	public void setHistory(List<DefiniteResult> history) {
		this.history = history;
	}

	@Override
	public List<ProcessResult> getHistory(ProcessResult processResult) {
		//TODO :YNE: afficher l'historique pour chaque processResult
		//TODO :YNE: essayer de déporter ce code sur un service et/ou module dédier à hibernate-envers
		
//		AuditReader auditReader = AuditReaderFactory.get(ProcessResultImpl);
		
		return null;
	}

}