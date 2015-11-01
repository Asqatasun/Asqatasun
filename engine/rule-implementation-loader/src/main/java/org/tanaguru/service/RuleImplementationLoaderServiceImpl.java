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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.tanaguru.service;

import java.util.HashSet;
import java.util.Set;
import org.tanaguru.entity.reference.Test;
import org.tanaguru.entity.service.audit.ProcessRemarkDataService;
import org.tanaguru.entity.service.audit.ProcessResultDataService;
import org.tanaguru.ruleimplementation.AbstractSiteRuleWithPageResultImplementation;
import org.tanaguru.ruleimplementation.RuleImplementation;
import org.tanaguru.ruleimplementationloader.RuleImplementationLoader;
import org.tanaguru.ruleimplementationloader.RuleImplementationLoaderFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author jkowalczyk
 */
public class RuleImplementationLoaderServiceImpl implements RuleImplementationLoaderService {

    private NomenclatureLoaderService nomenclatureLoaderService;
    private ProcessRemarkDataService processRemarkDataService;
    private ProcessResultDataService processResultDataService;
    private String archiveRoot;
    private RuleImplementationLoaderFactory ruleImplementationLoaderFactory;

    public RuleImplementationLoaderServiceImpl() {
        super();
    }

    @Override
    public Set<RuleImplementation> loadRuleImplementationSet(Set<Test> testSet) {
        Set<RuleImplementation> ruleImplementationSet = new HashSet<>();
        for (Test test : testSet) {
            ruleImplementationSet.add(loadRuleImplementation(test));
        }
        return ruleImplementationSet;
    }

    @Override
    public RuleImplementation loadRuleImplementation(Test test) {
        RuleImplementationLoader ruleImplementationLoader = ruleImplementationLoaderFactory.create(archiveRoot, test.getRuleArchiveName(), test.getRuleClassName());
        ruleImplementationLoader.run();
        RuleImplementation ruleImplementation = ruleImplementationLoader.getResult();
        ruleImplementation.setTest(test);
        ruleImplementation.setProcessResultDataService(processResultDataService);
        ruleImplementation.setNomenclatureLoaderService(nomenclatureLoaderService);
        
        // for specific purpose, we may need to access to ProcessRemark of
        // indefinite processResult while consolidating. 
        // yet, the ProcessResult are passed with lazy collection. 
        // The processRemarkDataService enables to retrieve the processRemark
        // of a given ProcessResult in this case
        if (ruleImplementation instanceof AbstractSiteRuleWithPageResultImplementation) {
            ((AbstractSiteRuleWithPageResultImplementation)ruleImplementation).setProcessRemarkDataService(processRemarkDataService);
        }
        
        return ruleImplementation;
    }

    @Override
    @Autowired
    public void setNomenclatureLoaderService(NomenclatureLoaderService nomenclatureService) {
        this.nomenclatureLoaderService = nomenclatureService;
    }
    
    @Autowired
    public void setProcessRemarkDataService(ProcessRemarkDataService processRemarkDataService) {
        this.processRemarkDataService = processRemarkDataService;
    }

    @Override
    public void setArchiveRoot(String archiveRoot) {
        this.archiveRoot = archiveRoot;
    }

    @Override
    @Autowired
    public void setRuleImplementationLoaderFactory(RuleImplementationLoaderFactory ruleImplementationLoaderFactory) {
        this.ruleImplementationLoaderFactory = ruleImplementationLoaderFactory;
    }

    @Override
    @Autowired
    public void setProcessResultDataService(ProcessResultDataService processResultDataService) {
        this.processResultDataService = processResultDataService;
    }

}