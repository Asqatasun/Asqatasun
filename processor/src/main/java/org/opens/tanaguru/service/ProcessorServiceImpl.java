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
package org.opens.tanaguru.service;

import java.util.ArrayList;
import java.util.List;
import org.opens.tanaguru.contentadapter.util.URLIdentifierFactory;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.factory.audit.EvidenceElementFactory;
import org.opens.tanaguru.entity.factory.audit.ProcessRemarkFactory;
import org.opens.tanaguru.entity.factory.audit.SourceCodeRemarkFactory;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.service.audit.EvidenceDataService;
import org.opens.tanaguru.processing.ProcessRemarkServiceFactory;
import org.opens.tanaguru.processor.Processor;
import org.opens.tanaguru.processor.ProcessorFactory;
import org.opens.tanaguru.ruleimplementation.RuleImplementation;

/**
 * 
 * @author jkowalczyk
 */
public class ProcessorServiceImpl implements ProcessorService {

    private ProcessorFactory processorFactory;
    private RuleImplementationLoaderService ruleImplementationLoaderService;
    private NomenclatureLoaderService nomenclatureLoaderService;
    private ProcessRemarkFactory processRemarkFactory;
    private SourceCodeRemarkFactory sourceCodeRemarkFactory;
    private EvidenceElementFactory evidenceElementFactory;
    private EvidenceDataService evidenceDataService;
    private URLIdentifierFactory urlIdentifierFactory;

    public ProcessorServiceImpl() {
        super();
    }

    public List<ProcessResult> process(List<Content> contentList, List<Test> testList) {
        List<ProcessResult> processResultList = new ArrayList<ProcessResult>();

        Processor processor = processorFactory.create(ProcessRemarkServiceFactory.create(processRemarkFactory, sourceCodeRemarkFactory, evidenceElementFactory, evidenceDataService), nomenclatureLoaderService, urlIdentifierFactory.create());

        for (Content content : contentList) {
            if (content instanceof SSP) {
                processor.setSSP((SSP) content);
                for (Test test : testList) {
                    RuleImplementation ruleImplementation = ruleImplementationLoaderService.loadRuleImplementation(test);
                    processor.setRuleImplementation(ruleImplementation);
                    processor.run();
                    processResultList.add(processor.getResult());
                }
            }
        }

        return processResultList;
    }

    public void setRuleImplementationLoaderService(RuleImplementationLoaderService ruleImplementationLoaderService) {
        this.ruleImplementationLoaderService = ruleImplementationLoaderService;
    }

    public void setNomenclatureLoaderService(NomenclatureLoaderService nomenclatureLoaderService) {
        this.nomenclatureLoaderService = nomenclatureLoaderService;
    }

    public void setEvidenceDataService(EvidenceDataService evidenceDataService) {
        this.evidenceDataService = evidenceDataService;
    }

    public void setEvidenceElementFactory(EvidenceElementFactory evidenceElementFactory) {
        this.evidenceElementFactory = evidenceElementFactory;
    }

    public void setProcessRemarkFactory(ProcessRemarkFactory processRemarkFactory) {
        this.processRemarkFactory = processRemarkFactory;
    }

    public void setSourceCodeRemarkFactory(SourceCodeRemarkFactory sourceCodeRemarkFactory) {
        this.sourceCodeRemarkFactory = sourceCodeRemarkFactory;
    }

    public void setProcessorFactory(ProcessorFactory processorFactory) {
        this.processorFactory = processorFactory;
    }

    public void setUrlIdentifierFactory(URLIdentifierFactory urlIdentifierFactory) {
        this.urlIdentifierFactory = urlIdentifierFactory;
    }
}
