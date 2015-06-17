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
package org.tanaguru.service;

import java.util.ArrayList;
import java.util.Collection;
import org.tanaguru.entity.audit.Content;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.SSP;
import org.tanaguru.entity.reference.Test;
import org.tanaguru.processor.Processor;
import org.tanaguru.processor.ProcessorFactory;
import org.tanaguru.ruleimplementation.RuleImplementation;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author jkowalczyk
 */
public class ProcessorServiceImpl implements ProcessorService {

    private ProcessorFactory processorFactory;
    public ProcessorFactory getProcessorFactory() {
        return processorFactory;
    }

    @Autowired
    public void setProcessorFactory(ProcessorFactory processorFactory) {
        this.processorFactory = processorFactory;
    }
    
    private RuleImplementationLoaderService ruleImplementationLoaderService;
    public RuleImplementationLoaderService getRuleImplementationLoaderService() {
        return ruleImplementationLoaderService;
    }
    
    @Autowired
    public void setRuleImplementationLoaderService(RuleImplementationLoaderService ruleImplementationLoaderService) {
        this.ruleImplementationLoaderService = ruleImplementationLoaderService;
    }

    public ProcessorServiceImpl() {
        super();
    }

    @Override
    public Collection<ProcessResult> process(Collection<Content> contentList, Collection<Test> testList) {
        Collection<ProcessResult> processResultList = new ArrayList<>();

        Processor processor = processorFactory.create();

        for (Content content : contentList) {
            if (content instanceof SSP) {
                processor.setSSP((SSP) content);
                for (Test test : testList) {
                    if (!test.getNoProcess()) {
                        RuleImplementation ruleImplementation = ruleImplementationLoaderService.loadRuleImplementation(test);
                        processor.setRuleImplementation(ruleImplementation);
                        processor.run();
                        processResultList.add(processor.getResult());
                    }
                }
            }
        }

        return processResultList;
    }

}