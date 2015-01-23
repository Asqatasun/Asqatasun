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

import java.util.List;

import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.entity.factory.audit.DefiniteResultFactory;
import org.opens.tanaguru.entity.factory.audit.IndefiniteResultFactory;
import org.opens.tanaguru.service.NomenclatureLoaderService;
import java.util.Map;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.service.ProcessRemarkService;

/**
 * 
 * @author jkowalczyk
 */
public interface RuleImplementation {


    /**
     * 
     * @param grossResultMap
     * @param processRemarkService
     * @return
     *         the list of processResults
     */
    List<ProcessResult> consolidate(
            Map<WebResource,
            List<ProcessResult>> grossResultMap,
            ProcessRemarkService processRemarkService);

    /**
     *
     * @return the test
     */
    Test getTest();

    /**
     *
     * @param sspHandler
     *            the SSP handler to use
     * @return the result of the processing
     */
    ProcessResult process(SSPHandler sspHandler);

    /**
     *
     * @param definiteResultFactory
     *            the definite result factory to set
     */
    void setDefiniteResultFactory(DefiniteResultFactory definiteResultFactory);

    /**
     *
     * @param indefiniteResultFactory
     *            the indefinite result factory to set
     */
    void setIndefiniteResultFactory(
            IndefiniteResultFactory indefiniteResultFactory);

    /**
     *
     * @param nomemclatureLoaderService
     *          the nomenclature loader service
     */
    void setNomenclatureLoaderService(
            NomenclatureLoaderService nomemclatureLoaderService);

    /**
     *
     * @param test
     *            the test to set
     */
    void setTest(Test test);

}