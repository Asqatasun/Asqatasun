package org.opens.tanaguru.entity.service.audit;

import org.opens.tanaguru.entity.audit.EvidenceElement;
import com.adex.sdk.entity.service.AbstractGenericDataService;
import java.util.Collection;
import org.opens.tanaguru.entity.audit.ProcessRemark;
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

    @Override
    public Collection<EvidenceElement> findAllByProcessRemark(
            ProcessRemark processRemark) {
        return ((EvidenceElementDAO) entityDao).
                retrieveAllByProcessRemark(processRemark);
    }

}
