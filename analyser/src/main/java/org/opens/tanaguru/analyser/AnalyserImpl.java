package org.opens.tanaguru.analyser;

import java.util.List;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;

/**
 * 
 * @author ADEX
 */
public class AnalyserImpl implements Analyser {

    private List<ProcessResult> netResultList;
    private float result;

    public AnalyserImpl() {
        super();
    }

    public List<ProcessResult> getNetResultList() {
        return netResultList;
    }

    public float getResult() {
        return result;
    }

    public void run() {
        float passed = 0;
        float notApplicable = 0;
        float needMoreInfo = 0;
        float failed = 0;

        for (ProcessResult testResult : netResultList) {
            switch ((TestSolution) testResult.getValue()) {
                case PASSED:
                    passed++;
                    break;
                case NOT_APPLICABLE:
                    notApplicable++;
                    break;
                case NEED_MORE_INFO:
                    needMoreInfo++;
                    break;
                case FAILED:
                    failed++;
                    break;

                default:
            }
        }

        result = ((passed + notApplicable) / (netResultList.size() - needMoreInfo)) * 100f;
    }

    public void setNetResultList(List<ProcessResult> netResultList) {
        this.netResultList = netResultList;
    }
}
