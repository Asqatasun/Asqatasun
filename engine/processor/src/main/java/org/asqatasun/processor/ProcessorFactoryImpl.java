/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
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
package org.asqatasun.processor;

import org.asqatasun.contentadapter.util.URLIdentifierFactory;
import org.asqatasun.entity.service.audit.EvidenceDataService;
import org.asqatasun.entity.service.audit.EvidenceElementDataService;
import org.asqatasun.entity.service.audit.PreProcessResultDataService;
import org.asqatasun.entity.service.audit.ProcessRemarkDataService;
import org.asqatasun.processing.ProcessRemarkServiceFactory;
import org.asqatasun.service.NomenclatureLoaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author enzolalay
 */

@Component("processorFactory")
public class ProcessorFactoryImpl implements ProcessorFactory {// TODO Write javadoc

    private NomenclatureLoaderService nomenclatureLoaderService;
    private EvidenceDataService evidenceDataService;
    private EvidenceElementDataService evidenceElementDataService;
    private ProcessRemarkDataService processRemarkDataService;
    private URLIdentifierFactory urlIdentifierFactory;
    private PreProcessResultDataService preProcessResultDataService;

    @Autowired
    public ProcessorFactoryImpl(NomenclatureLoaderService nomenclatureLoaderService,
                                EvidenceDataService evidenceDataService,
                                EvidenceElementDataService evidenceElementDataService,
                                ProcessRemarkDataService processRemarkDataService,
                                URLIdentifierFactory urlIdentifierFactory,
                                PreProcessResultDataService preProcessResultDataService) {
        this.nomenclatureLoaderService = nomenclatureLoaderService;
        this.evidenceDataService = evidenceDataService;
        this.evidenceElementDataService = evidenceElementDataService;
        this.processRemarkDataService = processRemarkDataService;
        this.urlIdentifierFactory = urlIdentifierFactory;
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
