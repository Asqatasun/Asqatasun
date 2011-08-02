package org.opens.tanaguru.entity.dao.reference;

import org.opens.tanaguru.entity.reference.Level;
import com.adex.sdk.entity.dao.GenericDAO;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface LevelDAO extends GenericDAO<Level, Long> {

    /**
     * 
     * @param code
     * @return
     */
    Level retrieveByCode(String code);

}