/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
package org.tanaguru.consolidator;

import java.util.Collection;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.ruleimplementation.RuleImplementation;
import org.tanaguru.service.ProcessRemarkService;

/**
 *
 * @author jkowalczyk
 */
public interface ConsolidatorFactory {

    /**
     * Consolidate the results from the gross result list for a given rule
     * implementation.
     * 
     * @param grossResultList
     * @param ruleImplementation
     * @param processRemarkService
     * @return
     */
    Consolidator create(Collection<ProcessResult> grossResultList, RuleImplementation ruleImplementation, ProcessRemarkService processRemarkService);

}