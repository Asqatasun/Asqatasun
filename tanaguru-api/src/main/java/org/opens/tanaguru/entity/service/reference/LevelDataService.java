package org.opens.tanaguru.entity.service.reference;

import org.opens.tanaguru.entity.reference.Level;
import com.adex.sdk.entity.service.GenericDataService;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface LevelDataService extends GenericDataService<Level, Long> {

    /**
     *
     * @param code
     *            the code to find from
     * @return the found level
     */
    Level getByCode(String code);

}