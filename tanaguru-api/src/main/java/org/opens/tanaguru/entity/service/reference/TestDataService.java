package org.opens.tanaguru.entity.service.reference;

import org.opens.tanaguru.entity.reference.Reference;
import org.opens.tanaguru.entity.reference.Test;
import com.adex.sdk.entity.service.GenericDataService;
import java.util.List;

/**
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface TestDataService extends GenericDataService<Test, Long> {

	/**
	 * 
	 * @param reference
	 *            the reference of the tests to find
	 * @return the tests found
	 */
	List<Test> findAll(Reference reference);

	List<Test> findAllByCode(String[] codeArray);
}
