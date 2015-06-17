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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.processor;

import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.SSP;
import org.tanaguru.ruleimplementation.RuleImplementation;

/**
 * 
 * @author jkowalczyk
 */
public class ProcessorImpl implements Processor {

    private ProcessResult result;
    private RuleImplementation ruleImplementation;
    private SSPHandler sspHandler;

    public ProcessorImpl(SSPHandler sspHandler) {
        super();
        this.sspHandler = sspHandler;
    }

    @Override
    public ProcessResult getResult() {
        return result;
    }

    @Override
    public RuleImplementation getRuleImplementation() {
        return ruleImplementation;
    }

    @Override
    public SSP getSSP() {
        return sspHandler.getSSP();
    }

    @Override
    public void run() {
        result = ruleImplementation.process(sspHandler);
    }

    @Override
    public void setRuleImplementation(RuleImplementation ruleImplementation) {
        this.ruleImplementation = ruleImplementation;
    }

    @Override
    public void setSSP(SSP ssp) {
        sspHandler.setSSP(ssp);
    }

    @Override
    public void setSSPHandler(SSPHandler sspHandler) {
        this.sspHandler = sspHandler;
    }

}