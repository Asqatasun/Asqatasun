package org.opens.tanaguru.entity.service.reference;

import org.opens.tanaguru.entity.reference.Level;
import com.adex.sdk.entity.service.AbstractGenericDataService;
import org.opens.tanaguru.entity.dao.reference.LevelDAO;

/**
 * 
 * @author ADEX
 */
public class LevelDataServiceImpl extends AbstractGenericDataService<Level, Long> implements LevelDataService {

    public LevelDataServiceImpl() {
        super();
    }

    @Override
    public Level getByCode(String code) {
        return ((LevelDAO)entityDao).retrieveByCode(code);
    }

}