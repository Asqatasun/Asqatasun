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

import java.util.HashSet;
import java.util.Set;
import org.opens.tanaguru.entity.factory.audit.DefiniteResultFactory;
import org.opens.tanaguru.entity.factory.audit.IndefiniteResultFactory;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.service.audit.ProcessRemarkDataService;
import org.opens.tanaguru.ruleimplementation.AbstractSiteRuleWithPageResultImplementation;
import org.opens.tanaguru.ruleimplementation.RuleImplementation;
import org.opens.tanaguru.ruleimplementationloader.RuleImplementationLoader;
import org.opens.tanaguru.ruleimplementationloader.RuleImplementationLoaderFactory;

/**
 * 
 * @author jkowalczyk
 */
public class RuleImplementationLoaderServiceImpl implements RuleImplementationLoaderService {

    private DefiniteResultFactory definiteResultFactory;
    private IndefiniteResultFactory indefiniteResultFactory;
    private NomenclatureLoaderService nomenclatureLoaderService;
    private ProcessRemarkDataService processRemarkDataService;
    private String archiveRoot;
    private RuleImplementationLoaderFactory ruleImplementationLoaderFactory;

    public RuleImplementationLoaderServiceImpl() {
        super();
    }

    @Override
    public Set<RuleImplementation> loadRuleImplementationSet(Set<Test> testSet) {
        Set<RuleImplementation> ruleImplementationSet = new HashSet<RuleImplementation>();
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
        ruleImplementation.setDefiniteResultFactory(definiteResultFactory);
        ruleImplementation.setIndefiniteResultFactory(indefiniteResultFactory);
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
    public void setDefiniteResultFactory(
            DefiniteResultFactory definiteResultFactory) {
        this.definiteResultFactory = definiteResultFactory;
    }

    @Override
    public void setIndefiniteResultFactory(
            IndefiniteResultFactory indefiniteResultFactory) {
        this.indefiniteResultFactory = indefiniteResultFactory;
    }

    @Override
    public void setNomenclatureLoaderService(NomenclatureLoaderService nomenclatureService) {
        this.nomenclatureLoaderService = nomenclatureService;
    }
    
    public void setProcessRemarkDataService(ProcessRemarkDataService processRemarkDataService) {
        this.processRemarkDataService = processRemarkDataService;
    }

    @Override
    public void setArchiveRoot(String archiveRoot) {
        this.archiveRoot = archiveRoot;
    }

    @Override
    public void setRuleImplementationLoaderFactory(RuleImplementationLoaderFactory ruleImplementationLoaderFactory) {
        this.ruleImplementationLoaderFactory = ruleImplementationLoaderFactory;
    }

}