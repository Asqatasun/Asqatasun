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
package org.opens.tanaguru.processor;

import org.opens.tanaguru.contentadapter.util.URLIdentifierFactory;
import org.opens.tanaguru.entity.factory.audit.EvidenceElementFactory;
import org.opens.tanaguru.entity.factory.audit.ProcessRemarkFactory;
import org.opens.tanaguru.entity.factory.audit.SourceCodeRemarkFactory;
import org.opens.tanaguru.entity.service.audit.EvidenceDataService;
import org.opens.tanaguru.entity.service.audit.PreProcessResultDataService;
import org.opens.tanaguru.processing.ProcessRemarkServiceFactory;
import org.opens.tanaguru.service.NomenclatureLoaderService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author enzolalay
 */
public class ProcessorFactoryImpl implements ProcessorFactory {// TODO Write javadoc

    private NomenclatureLoaderService nomenclatureLoaderService;
    public NomenclatureLoaderService getNomenclatureLoaderService() {
        return nomenclatureLoaderService;
    }
    
    @Autowired
    public void setNomenclatureLoaderService(NomenclatureLoaderService nomenclatureLoaderService) {
        this.nomenclatureLoaderService = nomenclatureLoaderService;
    }
    
    private EvidenceDataService evidenceDataService;
    public EvidenceDataService getEvidenceDataService() {
        return evidenceDataService;
    }

    @Autowired
    public void setEvidenceDataService(EvidenceDataService evidenceDataService) {
        this.evidenceDataService = evidenceDataService;
    }

    private EvidenceElementFactory evidenceElementFactory;
    public EvidenceElementFactory getEvidenceElementFactory() {
        return evidenceElementFactory;
    }

    @Autowired
    public void setEvidenceElementFactory(EvidenceElementFactory evidenceElementFactory) {
        this.evidenceElementFactory = evidenceElementFactory;
    }
    
    private ProcessRemarkFactory processRemarkFactory;
    public ProcessRemarkFactory getProcessRemarkFactory() {
        return processRemarkFactory;
    }
    
    @Autowired
    public void setProcessRemarkFactory(ProcessRemarkFactory processRemarkFactory) {
        this.processRemarkFactory = processRemarkFactory;
    }

    private SourceCodeRemarkFactory sourceCodeRemarkFactory;
    public SourceCodeRemarkFactory getSourceCodeRemarkFactory() {
        return sourceCodeRemarkFactory;
    }

    @Autowired
    public void setSourceCodeRemarkFactory(SourceCodeRemarkFactory sourceCodeRemarkFactory) {
        this.sourceCodeRemarkFactory = sourceCodeRemarkFactory;
    }

    private URLIdentifierFactory urlIdentifierFactory;
    public URLIdentifierFactory getUrlIdentifierFactory() {
        return urlIdentifierFactory;
    }
    
    @Autowired
    public void setUrlIdentifierFactory(URLIdentifierFactory urlIdentifierFactory) {
        this.urlIdentifierFactory = urlIdentifierFactory;
    }
    
    private PreProcessResultDataService preProcessResultDataService;
    public PreProcessResultDataService getPreProcessResultDataService() {
        return preProcessResultDataService;
    }

    @Autowired
    public void setPreProcessResultDataService(PreProcessResultDataService preProcessResultDataService) {
        this.preProcessResultDataService = preProcessResultDataService;
    }
    
    @Override
    public Processor create() {
        return new ProcessorImpl(
                SSPHandlerFactory.create(
                    ProcessRemarkServiceFactory.create(
                        processRemarkFactory, 
                        sourceCodeRemarkFactory,
                        evidenceElementFactory,
                        evidenceDataService),
                    nomenclatureLoaderService, 
                    urlIdentifierFactory.create(), 
                    preProcessResultDataService));
    }
}
