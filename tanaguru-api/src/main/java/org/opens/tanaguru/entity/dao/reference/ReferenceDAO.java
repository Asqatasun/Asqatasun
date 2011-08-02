package org.opens.tanaguru.entity.dao.reference;

import java.util.Collection;
import org.opens.tanaguru.entity.reference.Reference;
import com.adex.sdk.entity.dao.GenericDAO;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface ReferenceDAO extends GenericDAO<Reference, Long> {

    /**
     * 
     * @param code
     * @return
     */
    Collection<Reference> retrieveAllByCode(String code);

    /**
     *
     * @param code
     * @return
     */
    Reference retrieveByCode(String code);

}