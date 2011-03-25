package org.opens.tanaguru.service;

import java.util.List;
import org.opens.tanaguru.analyser.Analyser;
import org.opens.tanaguru.analyser.AnalyserFactory;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 * 
 * @author ADEX
 */
public class AnalyserServiceImpl implements AnalyserService {

    private AnalyserFactory analyserFactory;

    public AnalyserServiceImpl() {
        super();
    }

    public float analyse(List<ProcessResult> netResultList) {
        Analyser analyser = analyserFactory.create(netResultList);
        analyser.run();
        return analyser.getResult();
    }

    public void setAnalyserFactory(AnalyserFactory analyserFactory) {
        this.analyserFactory = analyserFactory;
    }

    @Override
    public void analyse(WebResource webResource) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void addAnalyserFactory(AnalyserFactory analyserFactory) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setAnalyser(Analyser analyser) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
