/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.consolidator;

import java.util.List;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.ruleimplementation.RuleImplementation;
import org.opens.tanaguru.service.ProcessRemarkService;

/**
 *
 * @author enzolalay
 */
public class ConsolidatorFactoryImpl implements ConsolidatorFactory {// TODO Write javadoc

    public Consolidator create(List<ProcessResult> grossResultList, RuleImplementation ruleImplementation, ProcessRemarkService processRemarkService) {
        return new ConsolidatorImpl(grossResultList, ruleImplementation, processRemarkService);
    }
}
