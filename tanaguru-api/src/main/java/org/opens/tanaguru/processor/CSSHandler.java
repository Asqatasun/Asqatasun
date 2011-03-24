package org.opens.tanaguru.processor;

import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.TestSolution;
import java.util.Collection;
import org.opens.tanaguru.service.ProcessRemarkService;

public interface CSSHandler {

    /**
     *
     * @return the current CSSHandler instance
     */
    CSSHandler beginSelection();

    /**
     *
     * @param blacklist
     *            the list of prevented values
     * @return the result of the check processing
     */
    TestSolution checkRelativeUnitExists(Collection<Integer> blacklist);

    /**
     *
     * @return a collection of ProcessRemark
     */
    Collection<ProcessRemark> getRemarkList();

    /**
     *
     * @return the current CSSHandler instance
     */
    CSSHandler selectAllRules();

    /**
     *
     * @return the current CSSHandler instance
     */
    CSSHandler keepRulesWithMedia(Collection<String> mediaNames);

    /**
     *
     * @param ssp
     *            the SSP so set
     */
    void setSSP(SSP ssp);

    /**
     * This method return the number of css selectors
     * @return
     */
    public int getCssSelectorNumber();

    /**
     *
     * @param processRemarkService
     */
    public void setProcessRemarkService(ProcessRemarkService processRemarkService);
}
