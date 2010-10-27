package org.opens.tanaguru.entity.service.reference;

import org.opens.tanaguru.entity.reference.Criterion;
import org.opens.tanaguru.entity.reference.Test;
import com.adex.sdk.entity.service.AbstractGenericDataService;

/**
 * 
 * @author ADEX
 */
public class CriterionDataServiceImpl extends AbstractGenericDataService<Criterion, Long> implements
        CriterionDataService {

    public CriterionDataServiceImpl() {
        super();
    }

    @Override
    public Criterion read(Long key) {
        Criterion entity = super.read(key);
//        for (Test test : entity.getTestList()) {
//        }
        return entity;
    }
}
