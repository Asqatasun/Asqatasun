package org.opens.tanaguru.consolidator;

import java.util.ArrayList;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.ruleimplementation.RuleImplementation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.opens.tanaguru.service.ProcessRemarkService;

/**
 * 
 * @author ADEX
 */
public class ConsolidatorImpl implements Consolidator {

    private List<ProcessResult> grossResultList;
    private Map<WebResource, List<ProcessResult>> groupedProcessResultMap;
    private boolean initialized = false;
    private List<ProcessResult> result;
    private RuleImplementation ruleImplementation;
    private ProcessRemarkService processRemarkService;

    ConsolidatorImpl(List<ProcessResult> grossResultList, RuleImplementation ruleImplementation, ProcessRemarkService processRemarkService) {
        super();
        this.grossResultList = grossResultList;
        this.ruleImplementation = ruleImplementation;
        this.processRemarkService = processRemarkService;
    }

    public List<ProcessResult> getGrossResultList() {
        return grossResultList;
    }

    public List<ProcessResult> getResult() {
        return result;
    }

    public RuleImplementation getRuleImplementation() {
        return ruleImplementation;
    }

    private void initialize() {
        if (initialized) {
            return;
        }

        groupedProcessResultMap = new HashMap<WebResource, List<ProcessResult>>();
        for (ProcessResult processResult : grossResultList) {
            WebResource webResource = processResult.getSubject();
            WebResource parent;
            List<ProcessResult> processResultList;

            parent = webResource.getParent();
            do {
                if (parent == null) {
                    processResultList = groupedProcessResultMap.get(webResource);
                    if (processResultList == null) {
                        processResultList = new ArrayList<ProcessResult>();
                        groupedProcessResultMap.put(webResource,
                                processResultList);
                    }
                } else {
                    processResultList = groupedProcessResultMap.get(parent);
                    if (processResultList == null) {
                        processResultList = new ArrayList<ProcessResult>();
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

    public void run() {
        initialize();

        result = ruleImplementation.consolidate(groupedProcessResultMap, processRemarkService);
    }

    public void setGrossResultList(List<ProcessResult> grossResultList) {
        this.grossResultList = grossResultList;
        initialized = false;
    }

    public void setRuleImplementation(RuleImplementation ruleImplementation) {
        this.ruleImplementation = ruleImplementation;
    }

    public void setProcessRemarkService(ProcessRemarkService processRemarkService) {
        this.processRemarkService = processRemarkService;
    }
}
