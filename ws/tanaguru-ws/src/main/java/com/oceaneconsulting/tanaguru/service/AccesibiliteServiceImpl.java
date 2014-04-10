package com.oceaneconsulting.tanaguru.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.service.parameterization.ParameterDataService;
import org.opens.tanaguru.entity.service.statistics.WebResourceStatisticsDataService;
import org.opens.tanaguru.service.AuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.oceaneconsulting.tanaguru.bean.AuditInputError;
import com.oceaneconsulting.tanaguru.bean.AuditInputs;
import com.oceaneconsulting.tanaguru.bean.AuditResult;
import com.oceaneconsulting.tanaguru.enumerations.AuditType;
import com.oceaneconsulting.tanaguru.util.AuditCodeErrors;
import com.oceaneconsulting.tanaguru.util.ParameterInputs;
import com.oceaneconsulting.tanaguru.util.ParameterUtils;

@Service(value = "accesibiliteServiceImpl#oceane")
public class AccesibiliteServiceImpl  implements AccesibiliteService{

	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired 
	ParameterDataService parameterDataService;
	
	@Autowired
	AuditService auditService;
	
	private List<String> registedParams;
	
	@Autowired
	WebResourceStatisticsDataService webResourceStatisticsDataService;
	
	@Override
	public AuditResult audit(AuditInputs auditInputs) {
				
				AuditResult auditResult = new AuditResult();
				// check parameters inputs
				List<AuditInputError> errors = checkAuditInputs(auditInputs);
				
				if(!CollectionUtils.isEmpty(errors)){
					auditResult.setErrors(errors);
					// exit because of previous errors
					return auditResult;
				}
		
				// everythings fine,lets continue
				
				//get parameters
				ParameterUtils.initParametersMap(parameterDataService);
				
				//Get default set of parameters
				Set<Parameter> parameters =  ParameterUtils.getDefaultParametersForPA();
				Map<String, String> map = auditInputs.getParameters(); 
				for(String key : map.keySet()){
		    		if(isRegistredParam(key)){
		    			parameters.add(ParameterUtils.createParameter(key.toUpperCase(), map.get(key)));
		    		}
		    	}
				
				Audit audit = null;
				try {
					switch (auditInputs.getAuditType()) {
					case PAGE:
					  	 audit = auditService.auditPage(auditInputs.getPageUrl(), parameters);
					  	break;
					case SCENARIO:
					  	 audit = auditService.auditScenario(auditInputs.getScenarioName(), auditInputs.getScenario(),  parameters);
					  	break;

					case SITE:
					  	 audit = auditService.auditSite(auditInputs.getPageUrl(),auditInputs.getPageUrls(), parameters);
						  		
						break;

					default:
						break;
					}
					
					// attendre 60
					if(audit != null){
						
						// set audi id
						auditResult.setAuditId(audit.getId());
						 
					}
				} catch (Exception e) {
					// lets catch all response
					logger.info(e.getMessage(),e);
					
					// 
					if(auditResult.getErrors() == null){
						auditResult.setErrors(new ArrayList<AuditInputError>());
					}
					auditResult.getErrors().add(new AuditInputError(AuditCodeErrors.TECHNICAL_ERROR, AuditCodeErrors.TECHNICAL_ERROR_TEXT,e.getMessage()));
				}
				
				
				//launch ws (unused result variable) 
		    	
		return auditResult;
	}

	@Override
	public List<AuditInputError> checkAuditInputs(AuditInputs auditInputs) {
		// list des erreurs
		List<AuditInputError> errors = new ArrayList<AuditInputError>();
	
		//  TODO check audit level if required
		
		
		//TODO check crediantial if needed (user, password or token id)
		
		// verify audit type
		AuditType auditType = auditInputs.getAuditType();
		
		if(auditType == null){
			AuditInputError error = new AuditInputError(AuditCodeErrors.UNKWNOW_AUDIT_TYPE_CODE, AuditCodeErrors.UNKWNOW_AUDIT_TYPE_TEXT,null);
			errors.add(error);
			
		}
		
		if(auditType != null){
			switch (auditType) {
			case PAGE:
				// page url;
				if(StringUtils.isEmpty(auditInputs.getPageUrl())){
					AuditInputError error = new AuditInputError(AuditCodeErrors.MISSING_AUDIT_PAGE_URL, AuditCodeErrors.MISSING_AUDIT_PAGE_URL_TEXT,null);
					errors.add(error);
				}
				break;
			case SCENARIO:
				
				if(StringUtils.isEmpty(auditInputs.getScenarioName())){
					AuditInputError error = new AuditInputError(AuditCodeErrors.MISSING_AUDIT_SCENARIO_NAME, AuditCodeErrors.MISSING_AUDIT_SCENARIO_NAME_TEXT,null);
					errors.add(error);
											
				}
				break;
			case SITE:
				// page url;
				if(StringUtils.isEmpty(auditInputs.getPageUrl())){
					AuditInputError error = new AuditInputError(AuditCodeErrors.MISSING_AUDIT_PAGE_URL, AuditCodeErrors.MISSING_AUDIT_PAGE_URL_TEXT,null);
					errors.add(error);
								
				}
				break;

			default:
				break;
			}
		}
		
		
		return errors;
	}

	
	@Override
	public void preProcessAuditData(AuditInputs auditInputs) {
		
		// required parameter
		
		// user access.
		
		return ;
	}
	
	
	@Override
	@Autowired
	public void initRegistredParams() {
		
		registedParams = new ArrayList<String>();
		registedParams.add(ParameterInputs.DATA_TABLE_MARKER);
		registedParams.add(ParameterInputs.ALTERNATIVE_CONTRAST_MECHANISM);
		registedParams.add(ParameterInputs.CONSIDER_COOKIES);
		registedParams.add(ParameterInputs.DECORATIVE_IMAGE_MARKER);
		registedParams.add(ParameterInputs.DEPTH);
		registedParams.add(ParameterInputs.EXCLUSION_REGEXP);
		registedParams.add(ParameterInputs.INFORMATIVE_IMAGE_MARKER);
		registedParams.add(ParameterInputs.LEVEL);
		registedParams.add(ParameterInputs.MAX_DOCUMENTS);
		registedParams.add(ParameterInputs.MAX_DURATION);
		registedParams.add(ParameterInputs.PROXY_HOST);
		registedParams.add(ParameterInputs.PROXY_HOST);
		
		return ;
	}
	
	
	@Override
	public boolean isRegistredParam(String param) {
		
		return registedParams.contains(param.toUpperCase());
	}
}
