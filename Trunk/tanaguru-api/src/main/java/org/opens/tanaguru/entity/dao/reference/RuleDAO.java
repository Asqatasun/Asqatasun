package org.opens.tanaguru.entity.dao.reference;

import java.util.Collection;
import org.opens.tanaguru.entity.reference.Rule;
import org.opens.tanaguru.entity.reference.RulePackage;
import org.opens.tanaguru.entity.reference.Test;
import com.adex.sdk.entity.dao.GenericDAO;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface RuleDAO extends GenericDAO<Rule, Long> {

	Rule retrieve(Test test);

	Collection<Rule> retrieveAll(RulePackage owningPackage, String className);
}
