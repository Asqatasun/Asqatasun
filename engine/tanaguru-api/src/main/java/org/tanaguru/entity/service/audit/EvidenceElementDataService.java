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
package org.tanaguru.entity.service.audit;

import java.util.Collection;
import org.tanaguru.entity.audit.Evidence;
import org.tanaguru.entity.audit.EvidenceElement;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.sdk.entity.service.GenericDataService;

/**
 * 
 * @author jkowalczyk
 */
public interface EvidenceElementDataService extends
		GenericDataService<EvidenceElement, Long> {

    /**
     * 
     * @param processRemark
     * @return
     *      the collection of evidenceElement for the given ProcessRemark
     */
    Collection<EvidenceElement> findAllByProcessRemark(
            ProcessRemark processRemark);

    /**
     * 
     * @param value
     * @return
     */
    EvidenceElement getEvidenceElement(String value);
    
    /**
     * 
     * @param value
     * @param evidence
     * @return 
     */
    EvidenceElement getEvidenceElement(String value, Evidence evidence);
    
    /**
     * 
     * @param processRemark
     * @param value
     * @param evidence
     * @return 
     */
    EvidenceElement getEvidenceElement(ProcessRemark processRemark, String value, Evidence evidence);

}