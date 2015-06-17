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

import org.tanaguru.contentadapter.util.URLIdentifierFactory;
import org.tanaguru.entity.service.audit.EvidenceDataService;
import org.tanaguru.entity.service.audit.EvidenceElementDataService;
import org.tanaguru.entity.service.audit.PreProcessResultDataService;
import org.tanaguru.entity.service.audit.ProcessRemarkDataService;
import org.tanaguru.processing.ProcessRemarkServiceFactory;
import org.tanaguru.service.NomenclatureLoaderService;
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

    private EvidenceElementDataService evidenceElementDataService;
    public EvidenceElementDataService getEvidenceElementDataService() {
        return evidenceElementDataService;
    }

    @Autowired
    public void setEvidenceElementDataService(EvidenceElementDataService evidenceElementDataService) {
        this.evidenceElementDataService = evidenceElementDataService;
    }
    
    private ProcessRemarkDataService processRemarkDataService;
    public ProcessRemarkDataService getProcessRemarkDataService() {
        return processRemarkDataService;
    }
    
    @Autowired
    public void setProcessRemarkDataService(ProcessRemarkDataService processRemarkDataService) {
        this.processRemarkDataService = processRemarkDataService;
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
                        processRemarkDataService, 
                        evidenceElementDataService,
                        evidenceDataService),
                    nomenclatureLoaderService, 
                    urlIdentifierFactory.create(), 
                    preProcessResultDataService));
    }
}
