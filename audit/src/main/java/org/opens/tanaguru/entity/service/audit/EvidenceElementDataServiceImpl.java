package org.opens.tanaguru.entity.service.audit;

import org.opens.tanaguru.entity.audit.EvidenceElement;
import com.adex.sdk.entity.service.AbstractGenericDataService;
import java.util.Collection;
import org.opens.tanaguru.entity.audit.ConsolidationRemark;
import org.opens.tanaguru.entity.audit.SourceCodeRemark;
import org.opens.tanaguru.entity.dao.audit.EvidenceElementDAO;

/**
 * 
 * @author jkowalczyk
 */
public class EvidenceElementDataServiceImpl extends AbstractGenericDataService<EvidenceElement, Long> implements
        EvidenceElementDataService {

    public EvidenceElementDataServiceImpl() {
        super();
    }

    public Collection<EvidenceElement> findAllBySourceCodeRemark(
            SourceCodeRemark sourceCodeRemark) {
        return ((EvidenceElementDAO) entityDao).
                retrieveAllByConsolidationRemark(sourceCodeRemark);
    }

    public Collection<EvidenceElement> findAllByConsolidationRemark(
            ConsolidationRemark consolidationRemark) {
        return ((EvidenceElementDAO) entityDao).
                retrieveAllByConsolidationRemark(consolidationRemark);
    }

}
