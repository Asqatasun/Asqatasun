package org.opens.tanaguru.ruleimplementation;

import java.util.List;
import org.opens.tanaguru.processor.*;

import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.entity.factory.audit.DefiniteResultFactory;
import org.opens.tanaguru.entity.factory.audit.IndefiniteResultFactory;
import org.opens.tanaguru.service.NomenclatureLoaderService;
import java.util.Map;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface RuleImplementation {

    List<ProcessResult> consolidate(Map<WebResource, List<ProcessResult>> grossResultMap);

    /**
     *
     * @return the test
     */
    Test getTest();

    /**
     *
     * @param sspHandler
     *            the SSP handler to use
     * @return the result of the processing
     */
    ProcessResult process(SSPHandler sspHandler);

    /**
     *
     * @param definiteResultFactory
     *            the definite result factory to set
     */
    void setDefiniteResultFactory(DefiniteResultFactory definiteResultFactory);

    /**
     *
     * @param indefiniteResultFactory
     *            the indefinite result factory to set
     */
    void setIndefiniteResultFactory(
            IndefiniteResultFactory indefiniteResultFactory);

    /**
     *
     * @param nomemclatureLoaderService
     *          the nomenclature loader service
     */
    void setNomenclatureLoaderService(
            NomenclatureLoaderService nomemclatureLoaderService);

    /**
     *
     * @param test
     *            the test to set
     */
    void setTest(Test test);
}
