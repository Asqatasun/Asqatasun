package org.opens.tanaguru.entity.service.reference;

import org.opens.tanaguru.entity.reference.StandardMessage;
import org.opens.tanaguru.entity.dao.reference.StandardMessageDAO;
import com.adex.sdk.entity.service.AbstractGenericDataService;

/**
 * 
 * @author ADEX
 */
public class StandardMessageDataServiceImpl extends AbstractGenericDataService<StandardMessage, Long> implements
        StandardMessageDataService {

    public StandardMessageDataServiceImpl() {
        super();
    }

    public StandardMessage findByCode(String code) {
        return ((StandardMessageDAO) entityDao).findByCode(code);
    }
}
