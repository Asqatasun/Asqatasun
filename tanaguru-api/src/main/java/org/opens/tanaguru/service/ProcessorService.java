package org.opens.tanaguru.service;

import java.util.List;
import org.opens.tanaguru.processor.Processor;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.ProcessResult;
import javax.xml.bind.annotation.XmlTransient;
import org.opens.tanaguru.entity.reference.Test;

/**
 * 
 * @author ADEX
 */
@XmlTransient
public interface ProcessorService {// TODO Write javadoc

    /**
     *
     * @param contentList
     * @param testList
     * @return
     */
    List<ProcessResult> process(List<Content> contentList, List<Test> testList);

    /**
     * 
     * @param processor the processor component to set
     */
    void setProcessor(Processor processor);
}
