package org.opens.tanaguru.service;

import java.util.List;
import org.opens.tanaguru.analyser.Analyser;
import org.opens.tanaguru.entity.audit.ProcessResult;

/**
 * 
 * @author ADEX
 */
public class AnalyserServiceImpl implements AnalyserService {

    private Analyser analyser;

    public AnalyserServiceImpl() {
        super();
    }

    public float analyse(List<ProcessResult> netResultList) {
        analyser.setNetResultList(netResultList);
        analyser.run();
        return analyser.getResult();
    }

    public void setAnalyser(Analyser analyser) {
        this.analyser = analyser;
    }
}
