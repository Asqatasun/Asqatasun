package org.opens.tanaguru.service;

import java.util.List;
import org.opens.tanaguru.entity.audit.ProcessResult;
import javax.xml.bind.annotation.XmlTransient;
import org.opens.tanaguru.analyser.AnalyserFactory;

/**
 * 
 * @author ADEX
 */
@XmlTransient
public interface AnalyserService {// TODO Write javadoc

    /**
     *
     * @param netResultList
     * @return
     */
    float analyse(List<ProcessResult> netResultList);

    void setAnalyserFactory(AnalyserFactory analyserFactory);
}
