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

import java.util.List;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.ProcessResult;
import javax.xml.bind.annotation.XmlTransient;
import org.opens.tanaguru.contentadapter.util.URLIdentifierFactory;
import org.opens.tanaguru.entity.factory.audit.EvidenceElementFactory;
import org.opens.tanaguru.entity.factory.audit.ProcessRemarkFactory;
import org.opens.tanaguru.entity.factory.audit.SourceCodeRemarkFactory;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.service.audit.EvidenceDataService;
import org.opens.tanaguru.processor.ProcessorFactory;

/**
 * 
 * @author jkowalczyk
 */
@XmlTransient
public interface ProcessorService {// TODO Write javadoc

    /**
     *
     * @param contentList
     * @param testList
     * @return
     */
    List<ProcessResult> process(List<Content> contentList, List<Test> testList);

    /**
     *
     * @param nomenclatureLoaderService
     */
    void setNomenclatureLoaderService(NomenclatureLoaderService nomenclatureLoaderService);

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
     * @param processorFactory
     */
    void setProcessorFactory(ProcessorFactory processorFactory);

    /**
     * 
     * @param urlIdentifierFactory
     */
    void setUrlIdentifierFactory(URLIdentifierFactory urlIdentifierFactory);

}