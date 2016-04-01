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
package org.asqatasun.consolidator;

import java.util.*;
import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.subject.WebResource;
import org.asqatasun.ruleimplementation.RuleImplementation;
import org.asqatasun.service.ProcessRemarkService;

/**
 * 
 * @author jkowalczyk
 */
public class ConsolidatorImpl implements Consolidator {

    private Collection<ProcessResult> grossResultList;
    private Map<WebResource, List<ProcessResult>> groupedProcessResultMap;
    private boolean initialized = false;
    private Collection<ProcessResult> result;
    private RuleImplementation ruleImplementation;
    private ProcessRemarkService processRemarkService;

    /**
     *
     * @param grossResultList
     * @param ruleImplementation
     * @param processRemarkService
     */
    ConsolidatorImpl(
            Collection<ProcessResult> grossResultList,
            RuleImplementation ruleImplementation,
            ProcessRemarkService processRemarkService) {
        super();
        this.grossResultList = grossResultList;
        this.ruleImplementation = ruleImplementation;
        this.processRemarkService = processRemarkService;
    }

    @Override
    public Collection<ProcessResult> getGrossResultList() {
        return grossResultList;
    }

    @Override
    public Collection<ProcessResult> getResult() {
        return result;
    }

    @Override
    public RuleImplementation getRuleImplementation() {
        return ruleImplementation;
    }

    private void initialize() {
        if (initialized) {
            return;
        }

        groupedProcessResultMap = new HashMap<>();
        for (ProcessResult processResult : grossResultList) {
            WebResource webResource = processResult.getSubject();
            WebResource parent;
            List<ProcessResult> processResultList;

            parent = webResource.getParent();
            do {
                if (parent == null) {
                    processResultList = groupedProcessResultMap.get(webResource);
                    if (processResultList == null) {
                        processResultList = new ArrayList<>();
                        groupedProcessResultMap.put(webResource,
                                processResultList);
                    }
                } else {
                    processResultList = groupedProcessResultMap.get(parent);
                    if (processResultList == null) {
                        processResultList = new ArrayList<>();
                        groupedProcessResultMap.put(parent, processResultList);
                    }
                    webResource = parent;
                    parent = parent.getParent();
                }
                processResultList.add(processResult);
            } while (parent != null);
        }
        initialized = true;
    }

    @Override
    public void run() {
        initialize();

        result = ruleImplementation.consolidate(groupedProcessResultMap, processRemarkService);
    }

    @Override
    public void setGrossResultList(List<ProcessResult> grossResultList) {
        this.grossResultList = grossResultList;
        initialized = false;
    }

    @Override
    public void setRuleImplementation(RuleImplementation ruleImplementation) {
        this.ruleImplementation = ruleImplementation;
    }

    @Override
    public void setProcessRemarkService(ProcessRemarkService processRemarkService) {
        this.processRemarkService = processRemarkService;
    }

}