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
package org.opens.tanaguru.service;

import java.util.Collection;
import javax.xml.bind.annotation.XmlTransient;
import org.opens.tanaguru.consolidator.ConsolidatorFactory;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.factory.audit.EvidenceElementFactory;
import org.opens.tanaguru.entity.factory.audit.ProcessRemarkFactory;
import org.opens.tanaguru.entity.factory.audit.SourceCodeRemarkFactory;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.service.audit.EvidenceDataService;

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
     * @param evidenceElementFactory
     */
    void setEvidenceElementFactory(EvidenceElementFactory evidenceElementFactory);

    /**
     *
     * @param processRemarkFactory
     */
    void setProcessRemarkFactory(ProcessRemarkFactory processRemarkFactory);

    /**
     *
     * @param sourceCodeRemarkFactory
     */
    void setSourceCodeRemarkFactory(SourceCodeRemarkFactory sourceCodeRemarkFactory);

    /**
     * 
     * @param consolidatorFactory
     */
    void setConsolidatorFactory(ConsolidatorFactory consolidatorFactory);

}