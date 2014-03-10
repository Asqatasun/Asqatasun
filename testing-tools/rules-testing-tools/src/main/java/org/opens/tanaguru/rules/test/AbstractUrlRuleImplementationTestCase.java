/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2014  Open-S Company
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
package org.opens.tanaguru.rules.test;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import junit.framework.TestCase;
import static org.easymock.EasyMock.*;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.factory.audit.DefiniteResultFactory;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.service.ProcessRemarkService;

/**
 * This abstract test class is use to make tests on an url and not on files.
 * We use the EasyMock class to emulate (and mock) the interface implementation.
 * 
 * @author alingua
 */
public abstract class AbstractUrlRuleImplementationTestCase extends TestCase {

    protected SSPHandler mockSspHandler;
    protected ProcessRemarkService mockProcessRemarkService;
    protected SSP mockSsp;
    protected ProcessRemark mockProcessRemark;
    protected WebResource mockWebResource;
    protected DefiniteResultFactory mockDefiniteResultFactory;
    protected Test mockTest;
    protected DefiniteResult mockDefiniteResult;

    public AbstractUrlRuleImplementationTestCase(String testName) {
        super(testName);
    }

    @Override
    protected void tearDown() throws Exception {
        verify(mockSspHandler,
                mockSsp,
                mockProcessRemark,
                mockProcessRemarkService,
                mockWebResource,
                mockDefiniteResult,
                mockDefiniteResultFactory,
                mockTest);
    }

    /**
     * 
     * Call this method to initialize the mock implementation.
     * 
     * @param url
     * @param remarkMessage
     * @param solution 
     */
    protected void initMocks(String url, String remarkMessage, TestSolution solution) {
        mockSspHandler = createMock(SSPHandler.class);
        mockProcessRemarkService = createMock(ProcessRemarkService.class);
        mockSsp = createMock(SSP.class);
        mockProcessRemark = createMock(ProcessRemark.class);
        mockWebResource = createMock(WebResource.class);
        mockDefiniteResultFactory = createMock(DefiniteResultFactory.class);
        mockTest = createMock(Test.class);
        mockDefiniteResult = createMock(DefiniteResult.class);

        expect(mockSspHandler.getProcessRemarkService())
                .andReturn(mockProcessRemarkService).anyTimes();
        mockProcessRemarkService.resetService();
        expectLastCall();
        expect(mockSspHandler.getSSP()).andReturn(mockSsp).anyTimes();
        expect(mockSsp.getURI()).andReturn(url).anyTimes();
        expect(mockSspHandler.getPage()).andReturn(mockWebResource);

        if (solution.equals(TestSolution.PASSED) || solution.equals(TestSolution.NOT_APPLICABLE)) {
            expect(mockProcessRemarkService.getRemarkList())
                    .andReturn(Collections.EMPTY_LIST);
            expect(mockDefiniteResultFactory.create(mockTest,
                    mockWebResource,
                    solution,
                    Collections.EMPTY_LIST)).andReturn(mockDefiniteResult);
        } else {
            mockProcessRemarkService.addProcessRemark(solution,
                    remarkMessage);
            expectLastCall().once();
            Collection<ProcessRemark> processRemarks =
                    new HashSet<ProcessRemark>();
            processRemarks.add(mockProcessRemark);
            expect(mockProcessRemarkService.getRemarkList())
                    .andReturn(processRemarks).anyTimes();
            expect(mockDefiniteResultFactory.create(mockTest,
                    mockWebResource,
                    solution,
                    processRemarks)).andReturn(mockDefiniteResult);
        }

        replay(mockSspHandler,
                mockSsp,
                mockProcessRemark,
                mockProcessRemarkService,
                mockWebResource,
                mockDefiniteResult,
                mockDefiniteResultFactory,
                mockTest);
    }
}
