package org.opens.tanaguru.entity.dao.audit;

import java.util.Collection;
import com.adex.sdk.entity.dao.GenericDAO;
import org.opens.tanaguru.entity.audit.ConsolidationRemark;
import org.opens.tanaguru.entity.audit.Evidence;
import org.opens.tanaguru.entity.audit.EvidenceElement;
import org.opens.tanaguru.entity.audit.SourceCodeRemark;

/**
 * 
 * @author jkowalczyk
 */
public interface EvidenceElementDAO extends
        GenericDAO<EvidenceElement, Long> {

    /**
     *
     * @param nomenclature
     * @param nomenclatureValue
     * @return
     */
    Collection<EvidenceElement> retrieveAll(Evidence nomenclature,
            String nomenclatureValue);

    /**
     * 
     * @param nomenclature
     * @param nomenclatureValue
     * @return
     */
    Collection<EvidenceElement> retrieveAllBySourceCodeRemark(SourceCodeRemark sourceCodeRemark);

    /**
     * 
     * @param consolidationRemark
     * @return
     */
    Collection<EvidenceElement> retrieveAllByConsolidationRemark(ConsolidationRemark consolidationRemark);
}
