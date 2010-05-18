package org.opens.tanaguru.entity.dao.reference;

import java.util.Collection;
import org.opens.tanaguru.entity.reference.RulePackage;
import com.adex.sdk.entity.dao.GenericDAO;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface RulePackageDAO extends GenericDAO<RulePackage, Long> {

	Collection<RulePackage> retrieveAllByPackageName(String packageName);
}
