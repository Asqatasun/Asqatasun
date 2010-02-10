package org.opens.tanaguru.entity.service.reference;

import org.opens.tanaguru.entity.service.reference.*;
import org.opens.tanaguru.entity.reference.Nomenclature;
import org.opens.tanaguru.entity.reference.NomenclatureElement;
import org.opens.tanaguru.entity.dao.reference.NomenclatureDAO;
import org.opens.tanaguru.entity.dao.reference.StandardMessageDAO;
import com.adex.sdk.entity.service.AbstractGenericDataService;

/**
 * 
 * @author ADEX
 */
public class NomenclatureDataServiceImpl extends
		AbstractGenericDataService<Nomenclature, Long> implements
		NomenclatureDataService {

	protected StandardMessageDAO standardMessageDao;

	public NomenclatureDataServiceImpl() {
		super();
	}

	public Nomenclature findByCode(String code) {
		return ((NomenclatureDAO) entityDao).retrieveByCode(code);
	}

	@Override
	public Nomenclature read(Long key) {
		Nomenclature entity = super.read(key);
		for (NomenclatureElement element : entity.getElementList()) {
		}
		return entity;
	}
}
