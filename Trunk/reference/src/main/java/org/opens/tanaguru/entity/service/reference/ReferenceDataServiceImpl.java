package org.opens.tanaguru.entity.service.reference;

import org.opens.tanaguru.entity.service.reference.*;
import org.opens.tanaguru.entity.reference.Criterion;
import org.opens.tanaguru.entity.reference.Reference;
import com.adex.sdk.entity.service.AbstractGenericDataService;

/**
 * 
 * @author ADEX
 */
public class ReferenceDataServiceImpl extends
		AbstractGenericDataService<Reference, Long> implements
		ReferenceDataService {

	public ReferenceDataServiceImpl() {
		super();
	}

	@Override
	public Reference read(Long key) {
		Reference entity = super.read(key);
		for (Criterion criterion : entity.getCriterionList()) {
		}
		return entity;
	}
}
