package org.opens.tanaguru.service;

import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.factory.audit.DefiniteResultFactory;
import org.opens.tanaguru.entity.factory.audit.IndefiniteResultFactory;
import org.opens.tanaguru.ruleimplementation.RuleImplementation;
import org.opens.tanaguru.ruleimplementationloader.RuleImplementationLoader;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author ADEX
 */
public class RuleImplementationLoaderServiceImpl implements
        RuleImplementationLoaderService {

    private DefiniteResultFactory definiteResultFactory;
    private IndefiniteResultFactory indefiniteResultFactory;
    private NomenclatureLoaderService nomenclatureLoaderService;
    private RuleImplementationLoader ruleImplementationLoader;

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
    public void setNomenclatureLoaderService(
            NomenclatureLoaderService nomenclatureService) {
        this.nomenclatureLoaderService = nomenclatureService;
    }

    @Override
    public void setRuleImplementationLoader(
            RuleImplementationLoader ruleImplementationLoader) {
        this.ruleImplementationLoader = ruleImplementationLoader;
    }

    @Override
    public RuleImplementation loadRuleImplementation(Test test) {
        ruleImplementationLoader.setArchiveName(test.getRuleArchiveName());
        ruleImplementationLoader.setClassName(test.getRuleClassName());
        ruleImplementationLoader.run();

        RuleImplementation ruleImplementation = ruleImplementationLoader.getResult();
        ruleImplementation.setTest(test);
        ruleImplementation.setDefiniteResultFactory(definiteResultFactory);
        ruleImplementation.setIndefiniteResultFactory(indefiniteResultFactory);
        ruleImplementation.setNomenclatureLoaderService(nomenclatureLoaderService);

        return ruleImplementation;
    }
}
