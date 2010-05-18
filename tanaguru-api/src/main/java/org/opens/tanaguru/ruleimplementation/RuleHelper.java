package org.opens.tanaguru.ruleimplementation;

import java.util.Collection;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;

/**
 * This class is a utility class
 *
 * @author ADEX
 * @version 1.0.0
 */
public abstract class RuleHelper {

    /**
     *
     * @param testSolutionCollection
     *            the test solution collection to synthesize
     * @return the synthesis of the test solution collection
     */
    public static TestSolution synthesizeTestSolutionCollection(Collection<TestSolution> testSolutionCollection) {
        boolean hasPassed = false;
        boolean hasFailed = false;
        boolean hasNMI = false;
        for (TestSolution result : testSolutionCollection) {
            switch (result) {
                case PASSED:
                    hasPassed = true;
                    break;
                case FAILED:
                    hasFailed = true;
                    break;
                case NEED_MORE_INFO:
                    hasNMI = true;
                    break;
                default:
            }
        }

        if (hasFailed) {
            return TestSolution.FAILED;
        }
        if (hasNMI) {
            return TestSolution.NEED_MORE_INFO;
        }
        if (hasPassed) {
            return TestSolution.PASSED;
        }
        return TestSolution.NOT_APPLICABLE;
    }

    /**
     *
     * @param processResultCollection
     *            the process result collection to synthesize
     * @return the synthesis of the process result collection
     */
    public static TestSolution synthesizeProcessResultCollection(
            Collection<ProcessResult> processResultCollection) {
        boolean hasPassed = false;
        boolean hasFailed = false;
        boolean hasNMI = false;
        for (ProcessResult testResult : processResultCollection) {
            DefiniteResult definiteResult = (DefiniteResult) testResult;
            switch (definiteResult.getDefiniteValue()) {
                case PASSED:
                    hasPassed = true;
                    break;
                case FAILED:
                    hasFailed = true;
                    break;
                case NEED_MORE_INFO:
                    hasNMI = true;
                    break;
                default:
            }
        }

        if (hasFailed) {
            return TestSolution.FAILED;
        }
        if (hasNMI) {
            return TestSolution.NEED_MORE_INFO;
        }
        if (hasPassed) {
            return TestSolution.PASSED;
        }
        return TestSolution.NOT_APPLICABLE;
    }
}
