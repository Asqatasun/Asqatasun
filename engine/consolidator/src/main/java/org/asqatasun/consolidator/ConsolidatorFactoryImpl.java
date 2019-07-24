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
package org.asqatasun.consolidator;

import java.util.Collection;
import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.service.audit.EvidenceDataService;
import org.asqatasun.entity.service.audit.EvidenceElementDataService;
import org.asqatasun.entity.service.audit.ProcessRemarkDataService;
import org.asqatasun.processing.ProcessRemarkServiceFactory;
import org.asqatasun.ruleimplementation.RuleImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Based on the design pattern factory, create instances of ConsolidatorFactory
 * @author enzolalay
 */
@Component(value = "consolidatorFactory")
public class ConsolidatorFactoryImpl implements ConsolidatorFactory {

    private ProcessRemarkDataService processRemarkDataService;
    private EvidenceElementDataService evidenceElementDataService;
    private EvidenceDataService evidenceDataService;

    @Autowired
    public ConsolidatorFactoryImpl(
        ProcessRemarkDataService processRemarkDataService,
        EvidenceElementDataService evidenceElementDataService,
        EvidenceDataService evidenceDataService) {
        this.processRemarkDataService = processRemarkDataService;
        this.evidenceElementDataService = evidenceElementDataService;
        this.evidenceDataService = evidenceDataService;
    }

    /**
     *
     * @param grossResultList
     * @param ruleImplementation
     * @return
     *      an instance of ConsolidatorFactory
     */
    @Override
    public Consolidator create(
            Collection<ProcessResult> grossResultList,
            RuleImplementation ruleImplementation) {
        return new ConsolidatorImpl(
                grossResultList,
                ruleImplementation,
            ProcessRemarkServiceFactory.create(
                processRemarkDataService,
                evidenceElementDataService,
                evidenceDataService));
    }

}
