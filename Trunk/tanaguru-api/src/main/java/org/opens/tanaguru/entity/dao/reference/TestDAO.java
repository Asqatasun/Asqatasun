package org.opens.tanaguru.entity.dao.reference;

import org.opens.tanaguru.entity.reference.Criterion;
import org.opens.tanaguru.entity.reference.Reference;
import org.opens.tanaguru.entity.reference.Test;
import com.adex.sdk.entity.dao.GenericDAO;
import java.util.List;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface TestDAO extends GenericDAO<Test, Long> {

	/**
	 * 
	 * @param reference
	 *            the reference of the tests to find
	 * @return the collection of tests found
	 */
	List<Test> retrieveAll(Reference reference);

	List<Test> retrieveAll(String code, Criterion criterion);

	List<Test> retrieveAllByCode(String[] codeArray);
}
