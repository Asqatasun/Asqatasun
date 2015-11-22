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
package org.asqatasun.ruleimplementation;

import java.util.List;

import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.reference.Test;
import org.asqatasun.entity.subject.WebResource;
import org.asqatasun.service.NomenclatureLoaderService;
import java.util.Map;
import org.asqatasun.entity.service.audit.ProcessResultDataService;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.service.ProcessRemarkService;

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
     * @param processResultDataService
     *            the ProcessResultDataService to set
     */
    void setProcessResultDataService(ProcessResultDataService processResultDataService);

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