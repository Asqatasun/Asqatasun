package org.opens.tanaguru.service;

import java.util.List;
import org.opens.tanaguru.consolidator.Consolidator;
import org.opens.tanaguru.entity.audit.ProcessResult;
import javax.xml.bind.annotation.XmlTransient;
import org.opens.tanaguru.entity.reference.Test;

/**
 * 
 * @author ADEX
 */
@XmlTransient
public interface ConsolidatorService {// TODO Write javadoc

    /**
     *
     * @param grossResultList
     * @param testList
     * @return
     */
    List<ProcessResult> consolidate(List<ProcessResult> grossResultList,
            List<Test> testList);

    /**
     * 
     * @param consolidator the consolidator component to set
     */
    void setConsolidator(Consolidator consolidator);
}
