package org.opens.tanaguru.entity.service.reference;

import org.opens.tanaguru.entity.service.reference.*;
import org.opens.tanaguru.entity.reference.Criterion;
import org.opens.tanaguru.entity.reference.Theme;
import com.adex.sdk.entity.service.AbstractGenericDataService;

/**
 * 
 * @author ADEX
 */
public class ThemeDataServiceImpl extends
		AbstractGenericDataService<Theme, Long> implements ThemeDataService {

	public ThemeDataServiceImpl() {
		super();
	}

	@Override
	public Theme read(Long key) {
		Theme entity = super.read(key);
		for (Criterion criterion : entity.getCriterionList()) {
		}
		return entity;
	}
}
