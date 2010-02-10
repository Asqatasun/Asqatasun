package org.opens.tanaguru.service;

import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.factory.audit.DefiniteResultFactory;
import org.opens.tanaguru.entity.factory.audit.IndefiniteResultFactory;
import org.opens.tanaguru.entity.factory.audit.ProcessRemarkFactory;
import org.opens.tanaguru.entity.factory.audit.SourceCodeRemarkFactory;
import org.opens.tanaguru.ruleimplementation.RuleImplementation;
import org.opens.tanaguru.ruleimplementationloader.RuleImplementationLoader;
import java.util.Set;

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
            NomenclatureLoaderService nomenclatureService);

    void setProcessRemarkFactory(ProcessRemarkFactory processRemarkFactory);

    void setRuleImplementationLoader(
            RuleImplementationLoader ruleImplementationLoader);

    void setSourceCodeRemarkFactory(
            SourceCodeRemarkFactory sourceCodeRemarkFactory);

    RuleImplementation loadRuleImplementation(Test test);
}
