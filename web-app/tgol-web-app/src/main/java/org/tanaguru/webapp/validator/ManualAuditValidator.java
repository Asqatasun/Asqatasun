package org.tanaguru.webapp.validator;

import java.util.List;

import org.tanaguru.entity.audit.DefiniteResultImpl;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.webapp.command.ManualAuditCommand;
import org.springframework.validation.Errors;

public class ManualAuditValidator implements org.springframework.validation.Validator {

    private static final String GENERAL_ERROR_MSG_KEY = "generalErrorMsg";
    private static final String RESULT_ABSENCE_VALUES = 
            "edit-contract.absenceManualVAlues";
    private static final String RESULT_ABSENCE_VALUES_OVER_TEN = 
            "edit-contract.absenceManualVAluesOverTen";

    @Override
    public boolean supports(Class<?> clazz) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ManualAuditCommand manualAuditCommand = (ManualAuditCommand) target;
        checkAuditManuelExist(manualAuditCommand.getProcessResultList(), errors);
    }

    private boolean checkAuditManuelExist(List<ProcessResult> allProcessResultList,
            Errors errors) {
        StringBuilder msg = new StringBuilder();
        int i = 0;
        boolean isExitStatut = true;
        for (ProcessResult testResultImpl : allProcessResultList) {
            DefiniteResultImpl definiteResult = (DefiniteResultImpl) testResultImpl;
            if (definiteResult.getManualDefiniteValue() == null) {
                isExitStatut = false;
                if (i < 11) {
                    msg.append(testResultImpl.getTest().getLabel());
                    msg.append("/");
                }
                i++;

            }
        }
        String[] arg = {msg.toString()};

        if (i > 0 && i < 11) {
            errors.rejectValue(GENERAL_ERROR_MSG_KEY, RESULT_ABSENCE_VALUES, arg, "{0}");
        } else if (i != 0) {
            errors.rejectValue(GENERAL_ERROR_MSG_KEY, RESULT_ABSENCE_VALUES_OVER_TEN, arg, "{0}");
        }

        return isExitStatut;
    }

}
