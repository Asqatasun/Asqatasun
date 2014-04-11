package org.opens.tgol.validator;

import java.util.Collection;
import java.util.List;

import org.opens.tanaguru.entity.audit.DefiniteResultImpl;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tgol.command.ManualAuditCommand;
import org.opens.tgol.presentation.data.TestResultImpl;
import org.springframework.validation.Errors;



public class ManualAuditValidator implements org.springframework.validation.Validator{

	 private static final String GENERAL_ERROR_MSG_KEY = "generalErrorMsg";
	 private static final String NOT_ACTIVATED_ACCOUNT_KEY =
	            "accessDeniedPage.message";
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		ManualAuditCommand  manualAuditCommand = (ManualAuditCommand)target;
		checkAuditManuelExist(manualAuditCommand.getProcessResultList(), errors);
	}

	private boolean checkAuditManuelExist(List<ProcessResult> allProcessResultList,
			Errors errors) {
		
		boolean isExitStatut= true;	
		for (ProcessResult testResultImpl : allProcessResultList) {
				DefiniteResultImpl definiteResult = (DefiniteResultImpl) testResultImpl;		
			if(definiteResult.getManualDefiniteValue() == null){
							isExitStatut = false;
							errors.rejectValue(GENERAL_ERROR_MSG_KEY,
				                    NOT_ACTIVATED_ACCOUNT_KEY);
							break;
							}
						}
		return isExitStatut;
	}

}
