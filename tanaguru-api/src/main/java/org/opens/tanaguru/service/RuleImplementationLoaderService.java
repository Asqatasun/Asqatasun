package org.opens.tanaguru.service;

import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.factory.audit.DefiniteResultFactory;
import org.opens.tanaguru.entity.factory.audit.IndefiniteResultFactory;
import org.opens.tanaguru.ruleimplementation.RuleImplementation;
import java.util.Set;
import org.opens.tanaguru.ruleimplementationloader.RuleImplementationLoaderFactory;

/**
 * 
 * @author ADEX
 */
public interface RuleImplementationLoaderService {

    Set<RuleImplementation> loadRuleImplementationSet(Set<Test> testSet);

    void setDefiniteResultFactory(DefiniteResultFactory definiteResultFactory);

    void setIndefiniteResultFactory(
            IndefiniteResultFactory indefiniteResultFactory);

    void setNomenclatureLoaderService(
            NomenclatureLoaderService nomenclatureLoaderService);

    RuleImplementation loadRuleImplementation(Test test);

    void setArchiveRoot(String archiveRoot);

    void setRuleImplementationLoaderFactory(RuleImplementationLoaderFactory ruleImplementationLoaderFactory);
}
