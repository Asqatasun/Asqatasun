package org.opens.tanaguru.nomenclatureloader;

import org.opens.tanaguru.entity.reference.Nomenclature;
import org.opens.tanaguru.entity.service.reference.NomenclatureDataService;

/**
 * 
 * @author ADEX
 */
public class NomenclatureLoaderImpl implements NomenclatureLoader {

	private String code;
	private NomenclatureDataService nomenclatureDataService;
	private Nomenclature result;

	public NomenclatureLoaderImpl() {
		super();
	}

	public String getCode() {
		return code;
	}

	public Nomenclature getResult() {
		return result;
	}

	public void run() {
		result = nomenclatureDataService.findByCode(code);
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setNomenclatureDataService(
			NomenclatureDataService nomenclatureDataService) {
		this.nomenclatureDataService = nomenclatureDataService;
	}
}
