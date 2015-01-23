/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
package org.opens.tanaguru.ruleimplementation;

import java.util.ArrayList;

import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.entity.factory.audit.DefiniteResultFactory;
import org.opens.tanaguru.entity.factory.audit.IndefiniteResultFactory;
import org.opens.tanaguru.service.NomenclatureLoaderService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.service.ProcessRemarkService;

/**
 * This class is the top level superclass of all concrete
 * {@link RuleImplementation} classes. It encapsulates common algorithms of
 * {@link RuleImplementation} operations.
 * 
 * @author jkowalczyk
 */
public abstract class AbstractRuleImplementation implements RuleImplementation {

    protected DefiniteResultFactory definiteResultFactory;
    protected IndefiniteResultFactory indefiniteResultFactory;
    protected NomenclatureLoaderService nomenclatureLoaderService;
    protected Test test;

    public AbstractRuleImplementation() {
        super();
    }

    /**
     * This method is part of the implementation of the interface
     * {@link RuleImplementation}. It encapsulates common algorithm for the
     * consolidation operation. Because all gross results of an audit are passed
     * through the parameter set, at the method call, they always need to be
     * filtered. The filter keeps only gross results concerning the current test
     * associated with the {@link RuleImplementation} instance. Then, it calls
     * the method {@link #consolidateImpl(java.util.Collection)} which receives
     * previously filtered gross results.
     *
     * @param grossResultMap
     *            the gross result map used for consolidation.
     * @return the net result list from the consolidation operation.
     */
    @Override
    public List<ProcessResult> consolidate(
            Map<WebResource, List<ProcessResult>> grossResultMap,
            ProcessRemarkService processRemarkService) {
        Map<WebResource, List<ProcessResult>> fileteredGrossResultMap = new HashMap<WebResource, List<ProcessResult>>();
        for (Map.Entry<WebResource, List<ProcessResult>> entry : grossResultMap.entrySet()) {
            List<ProcessResult> processResultSet = new ArrayList<ProcessResult>();
            for (ProcessResult processResult : entry.getValue()) {
                if (processResult.getTest().getCode().equalsIgnoreCase(this.test.getCode())) {
                    processResultSet.add(processResult);
                }
            }
            if (!processResultSet.isEmpty()) {
                fileteredGrossResultMap.put(entry.getKey(), processResultSet);
            }
        }

        return consolidateImpl(fileteredGrossResultMap, processRemarkService);
    }

    /**
     * This method has to be implemented by concrete {@link RuleImplementation}
     * classes. It should consolidate the gross result list. It is called by the
     * main consolidation method and is integrated into the main algorithm.
     *
     * @param grossResultMap
     *            the gross result map filtered used for consolidation.
     * @return the net result list from the consolidation operation.
     */
    protected abstract List<ProcessResult> consolidateImpl(
            Map<WebResource, List<ProcessResult>> grossResultMap,
            ProcessRemarkService processRemarkService);

    @Override
    public Test getTest() {
        return test;
    }

    /**
     * This method is part of the implementation of the interface
     * {@link RuleImplementation}. It encapsulates common algorithm for the
     * processing operation. The instance of {@link SSPHandler} received as a
     * parameter concerns only one page. Thus, the result of the processing
     * operation is always a single gross result.
     *
     * @param sspHandler
     *            the SSP handler to use.
     * @return the result of the processing.
     */
    @Override
    public ProcessResult process(SSPHandler sspHandler) {
        return processImpl(sspHandler);
    }

    /**
     * This is the method that has to be implemented by concrete
     * {@link RuleImplementation} classes for processing operation. This method
     * should execute the processing of one page, through the SSP handler. It is
     * called by the main process method and is integrated into the common
     * algorithm.
     *
     * @param sspHandler
     *            the SSP handler to user.
     * @return the result of the processing.
     */
    protected abstract ProcessResult processImpl(SSPHandler sspHandler);

    @Override
    public void setDefiniteResultFactory(
            DefiniteResultFactory definiteResultFactory) {
        this.definiteResultFactory = definiteResultFactory;
    }

    @Override
    public void setIndefiniteResultFactory(
            IndefiniteResultFactory indefiniteResultFactory) {
        this.indefiniteResultFactory = indefiniteResultFactory;
    }

    @Override
    public void setNomenclatureLoaderService(
            NomenclatureLoaderService nomenclatureLoaderService) {
        this.nomenclatureLoaderService = nomenclatureLoaderService;
    }

    @Override
    public void setTest(Test test) {
        this.test = test;
    }

}
