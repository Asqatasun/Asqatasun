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
package org.tanaguru.processing;

import org.tanaguru.entity.service.audit.EvidenceDataService;
import org.tanaguru.entity.service.audit.EvidenceElementDataService;
import org.tanaguru.entity.service.audit.ProcessRemarkDataService;
import org.tanaguru.service.ProcessRemarkService;

/**
 *
 * @author enzolalay
 */
public class ProcessRemarkServiceFactory {

    /**
     *
     * @param processRemarkFactory
     * @param sourceCodeRemarkFactory
     * @param evidenceElementFactory
     * @param evidenceDataService
     * @return
     *      an instance of ProcessRemarkImpl
     */
    public static ProcessRemarkService create(
            ProcessRemarkDataService processRemarkDataService,
            EvidenceElementDataService evidenceElementDataService,
            EvidenceDataService evidenceDataService) {
        return new ProcessRemarkServiceImpl(
                processRemarkDataService,
                evidenceElementDataService,
                evidenceDataService);
    }

}