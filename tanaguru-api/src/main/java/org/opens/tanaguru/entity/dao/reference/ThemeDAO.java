package org.opens.tanaguru.entity.dao.reference;

import java.util.Collection;
import org.opens.tanaguru.entity.reference.Theme;
import com.adex.sdk.entity.dao.GenericDAO;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface ThemeDAO extends GenericDAO<Theme, Long> {

	Collection<Theme> retrieveAllByCode(String code);
}
