package org.oceane.tanaguru.service;

import java.util.List;

import org.oceane.tanaguru.bean.AuditInputError;
import org.oceane.tanaguru.bean.AuditInputs;
import org.oceane.tanaguru.bean.AuditResult;

public interface AccesibiliteService {

	
	/**
	 * This method allow audit processing, all parameters should be encapsulated into {@link AuditInputs}
	 * @param auditInputs
	 * @return
	 */
	AuditResult audit(AuditInputs auditInputs);
	
	/**
	 * This method can be use to check the inputs parameters. the validation rules should be defined according the application goal
	 * @param auditInputs
	 * @return
	 */
	List<AuditInputError> checkAuditInputs(AuditInputs auditInputs);

	/**
	 * 
	 */
	void initRegistredParams();

	/**
	 * 
	 * @param param
	 * @return
	 */
	boolean isRegistredParam(String param);

	/**
	 * 
	 * @param auditInputs
	 */
	void preProcessAuditData(AuditInputs auditInputs);
}
