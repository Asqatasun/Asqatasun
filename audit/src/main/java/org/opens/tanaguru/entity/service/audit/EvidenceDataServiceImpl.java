package org.opens.tanaguru.entity.service.audit;

import org.opens.tanaguru.entity.service.audit.EvidenceDataService;
import org.opens.tanaguru.entity.audit.Evidence;
import org.opens.tanaguru.entity.audit.EvidenceElement;
import org.opens.tanaguru.entity.dao.audit.EvidenceDAO;
import org.opens.tanaguru.entity.dao.reference.StandardMessageDAO;
import com.adex.sdk.entity.service.AbstractGenericDataService;

/**
 * 
 * @author jkowalczyk
 */
public class EvidenceDataServiceImpl extends AbstractGenericDataService<Evidence, Long> implements
        EvidenceDataService {

    protected StandardMessageDAO standardMessageDao;

    public EvidenceDataServiceImpl() {
        super();
    }

    public Evidence findByCode(String code) {
        return ((EvidenceDAO) entityDao).retrieveByCode(code);
    }

    @Override
    public Evidence read(Long key) {
        Evidence entity = super.read(key);
        for (EvidenceElement element : entity.getElementList()) {
        }
        return entity;
    }
}
