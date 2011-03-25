package org.opens.tanaguru.service;

import java.util.List;
import org.opens.tanaguru.entity.audit.ProcessResult;
import javax.xml.bind.annotation.XmlTransient;
import org.opens.tanaguru.analyser.Analyser;
import org.opens.tanaguru.analyser.AnalyserFactory;
import org.opens.tanaguru.entity.subject.WebResource;

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
    @Deprecated
    float analyse(List<ProcessResult> netResultList);

    /**
     * 
     * @param webResource
     */
    void analyse(WebResource webResource);

    /**
     * 
     * @param analyser
     */
    @Deprecated
    void setAnalyser(Analyser analyser);

    /**
     *
     * @param analyserFactory
     */
    void setAnalyserFactory(AnalyserFactory analyserFactory);

    /**
     * 
     * @param analyser
     */
    void addAnalyserFactory(AnalyserFactory analyserFactory);
}
