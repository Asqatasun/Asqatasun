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

import java.util.Collection;
import javax.xml.bind.annotation.XmlTransient;
import org.tanaguru.consolidator.ConsolidatorFactory;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.reference.Test;
import org.tanaguru.entity.service.audit.EvidenceDataService;
import org.tanaguru.entity.service.audit.EvidenceElementDataService;
import org.tanaguru.entity.service.audit.ProcessRemarkDataService;

/**
 * 
 * @author jkowalczyk
 */
@XmlTransient
public interface ConsolidatorService {// TODO Write javadoc

    /**
     *
     * @param grossResultList
     * @param testList
     * @return
     */
    Collection<ProcessResult> consolidate(Collection<ProcessResult> grossResultList,
            Collection<Test> testList);

    /**
     * 
     * @param ruleImplementationLoaderService the rule implementation loader service to set
     */
    void setRuleImplementationLoaderService(RuleImplementationLoaderService ruleImplementationLoaderService);

    /**
     *
     * @param evidenceDataService
     */
    void setEvidenceDataService(EvidenceDataService evidenceDataService);

    /**
     *
     * @param evidenceElementDataService
     */
    void setEvidenceElementDataService(EvidenceElementDataService evidenceElementDataService);

    /**
     *
     * @param processRemarkDataService
     */
    void setProcessRemarkDataService(ProcessRemarkDataService processRemarkDataService);

    /**
     * 
     * @param consolidatorFactory
     */
    void setConsolidatorFactory(ConsolidatorFactory consolidatorFactory);

}