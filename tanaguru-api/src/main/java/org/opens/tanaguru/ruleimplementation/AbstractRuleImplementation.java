package org.opens.tanaguru.ruleimplementation;

import java.util.ArrayList;
import org.opens.tanaguru.processor.*;

import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.entity.factory.audit.DefiniteResultFactory;
import org.opens.tanaguru.entity.factory.audit.IndefiniteResultFactory;
import org.opens.tanaguru.entity.factory.audit.ProcessRemarkFactory;
import org.opens.tanaguru.entity.factory.audit.SourceCodeRemarkFactory;
import org.opens.tanaguru.service.NomenclatureLoaderService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is the top level superclass of all concrete
 * {@link RuleImplementation} classes. It encapsulates common algorithms of
 * {@link RuleImplementation} operations.
 * 
 * @author ADEX
 * @version 1.0.0
 */
public abstract class AbstractRuleImplementation implements RuleImplementation {

    protected DefiniteResultFactory definiteResultFactory;
    protected IndefiniteResultFactory indefiniteResultFactory;
    protected NomenclatureLoaderService nomenclatureLoaderService;
    protected ProcessRemarkFactory processRemarkFactory;
    protected SourceCodeRemarkFactory sourceCodeRemarkFactory;
    protected Test test;

    public AbstractRuleImplementation() {
        super();
    }

    /**
     * This method is part of the implementation of the interface
     * {@link RuleImplementation}. It encapsulates common algorithm for the
     * consolidation operation. Because all gross results of an audit are passed
     * through the parameter set, at the method call, they always need to be
     * filtered. The filter keeps only gross results concerning the current test
     * associated with the {@link RuleImplementation} instance. Then, it calls
     * the method {@link #consolidateImpl(java.util.Collection)} which receives
     * previously filtered gross results.
     *
     * @param grossResultMap
     *            the gross result map used for consolidation.
     * @return the net result list from the consolidation operation.
     */
    public List<ProcessResult> consolidate(
            Map<WebResource, List<ProcessResult>> grossResultMap) {
        Map<WebResource, List<ProcessResult>> fileteredGrossResultMap = new HashMap<WebResource, List<ProcessResult>>();
        for (Map.Entry<WebResource, List<ProcessResult>> entry : grossResultMap.entrySet()) {
            List<ProcessResult> processResultSet = new ArrayList<ProcessResult>();
            for (ProcessResult processResult : entry.getValue()) {
                if (processResult.getTest().equals(this.test)) {
                    processResultSet.add(processResult);
                }
            }
            if (!processResultSet.isEmpty()) {
                fileteredGrossResultMap.put(entry.getKey(), processResultSet);
            }
        }

        return consolidateImpl(fileteredGrossResultMap);
    }

    /**
     * This method has to be implemented by concrete {@link RuleImplementation}
     * classes. It should consolidate the gross result list. It is called by the
     * main consolidation method and is integrated into the main algorithm.
     *
     * @param grossResultMap
     *            the gross result map filtered used for consolidation.
     * @return the net result list from the consolidation operation.
     */
    protected abstract List<ProcessResult> consolidateImpl(
            Map<WebResource, List<ProcessResult>> grossResultMap);

    public Test getTest() {
        return test;
    }

    /**
     * This method is part of the implementation of the interface
     * {@link RuleImplementation}. It encapsulates common algorithm for the
     * processing operation. The instance of {@link SSPHandler} received as a
     * parameter concerns only one page. Thus, the result of the processing
     * operation is always a single gross result.
     *
     * @param sspHandler
     *            the SSP handler to use.
     * @return the result of the processing.
     */
    public ProcessResult process(SSPHandler sspHandler) {
        return processImpl(sspHandler);
    }

    /**
     * This is the method that has to be implemented by concrete
     * {@link RuleImplementation} classes for processing operation. This method
     * should exectue the processing of one page, through the SSP handler. It is
     * called by the main process method and is integrated into the common
     * algorithm.
     *
     * @param sspHandler
     *            the SSP handler to user.
     * @return the result of the processing.
     */
    protected abstract ProcessResult processImpl(SSPHandler sspHandler);

    public void setDefiniteResultFactory(
            DefiniteResultFactory definiteResultFactory) {
        this.definiteResultFactory = definiteResultFactory;
    }

    public void setIndefiniteResultFactory(
            IndefiniteResultFactory indefiniteResultFactory) {
        this.indefiniteResultFactory = indefiniteResultFactory;
    }

    public void setNomenclatureLoaderService(
            NomenclatureLoaderService nomenclatureLoaderService) {
        this.nomenclatureLoaderService = nomenclatureLoaderService;
    }

    public void setProcessRemarkFactory(
            ProcessRemarkFactory processRemarkFactory) {
        this.processRemarkFactory = processRemarkFactory;
    }

    public void setSourceCodeRemarkFactory(
            SourceCodeRemarkFactory sourceCodeRemarkFactory) {
        this.sourceCodeRemarkFactory = sourceCodeRemarkFactory;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
