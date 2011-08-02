package org.opens.tanaguru.entity.service.reference;

import org.opens.tanaguru.entity.reference.Reference;
import com.adex.sdk.entity.service.AbstractGenericDataService;
import org.opens.tanaguru.entity.dao.reference.ReferenceDAO;

/**
 * 
 * @author ADEX
 */
public class ReferenceDataServiceImpl extends AbstractGenericDataService<Reference, Long> implements
        ReferenceDataService {

    public ReferenceDataServiceImpl() {
        super();
    }

    @Override
    public Reference read(Long key) {
        Reference entity = super.read(key);
        return entity;
    }

    @Override
    public Reference getByCode(String code) {
        return ((ReferenceDAO)entityDao).retrieveByCode(code);
    }

}