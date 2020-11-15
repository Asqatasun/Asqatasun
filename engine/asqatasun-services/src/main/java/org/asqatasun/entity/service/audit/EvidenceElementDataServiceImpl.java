/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
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
package org.asqatasun.entity.service.audit;

import java.util.Collection;
import org.asqatasun.entity.audit.Evidence;
import org.asqatasun.entity.audit.EvidenceElement;
import org.asqatasun.entity.audit.ProcessRemark;
import org.asqatasun.entity.audit.factory.EvidenceElementFactory;
import org.asqatasun.entity.dao.audit.EvidenceElementDAO;
import org.asqatasun.entity.service.AbstractGenericDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 
 * @author jkowalczyk
 */
@Service("evidenceElementDataService")
public class EvidenceElementDataServiceImpl extends AbstractGenericDataService<EvidenceElement, Long> implements
        EvidenceElementDataService {

    @Autowired
    @Qualifier("evidenceElementFactory")
    protected EvidenceElementFactory entityFactory;

    public EvidenceElementDataServiceImpl() {
        super();
    }

    @Override
    public Collection<EvidenceElement> findAllByProcessRemark(
            ProcessRemark processRemark) {
        return ((EvidenceElementDAO) entityDao).
                retrieveAllByProcessRemark(processRemark);
    }

    @Override
    public EvidenceElement getEvidenceElement(String value) {
        return entityFactory.create(value);
    }

    @Override
    public EvidenceElement getEvidenceElement(ProcessRemark processRemark, String value, Evidence evidence) {
        return entityFactory.create(processRemark, value, evidence);
    }
    
    @Override
    public EvidenceElement getEvidenceElement(String value, Evidence evidence) {
        return entityFactory.create(value, evidence);
    }

}
