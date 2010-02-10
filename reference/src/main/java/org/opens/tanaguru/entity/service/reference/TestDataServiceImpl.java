package org.opens.tanaguru.entity.service.reference;

import org.opens.tanaguru.entity.reference.Reference;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.dao.reference.TestDAO;
import com.adex.sdk.entity.service.AbstractGenericDataService;
import java.util.List;

/**
 * 
 * @author ADEX
 */
public class TestDataServiceImpl extends AbstractGenericDataService<Test, Long>
		implements TestDataService {

	public TestDataServiceImpl() {
		super();
	}

	public List<Test> findAll(Reference reference) {
		return ((TestDAO) entityDao).retrieveAll(reference);
	}

	public List<Test> findAllByCode(String[] codeArray) {
		return ((TestDAO) entityDao).retrieveAllByCode(codeArray);
	}
}
