package org.opens.tanaguru.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.service.parameterization.ParameterDataService;

/**
 * Parameters initialization utilities.
 * 
 * This class defines sets of parameters used in different webservice's operations.
 * 
 * @author shamdi
 *
 */
public class ParameterUtils {
	
	/**
	 * Class logger definition
	 */
	private static final Logger LOGGER = Logger.getLogger(ParameterUtils.class);
	
	
	//Constants
	
	
	
	public static Map<String, List<Parameter>> parametersMap = null;
	
	public static void initParametersMap(ParameterDataService parameterDataService) {

		// get all parameters
		Collection<Parameter> params = parameterDataService.findAll();

		// create map for parameters identified par parent element code.
		if (parametersMap == null) {
			parametersMap = new HashMap<String, List<Parameter>>();
		}

		for (Parameter param : params) {
			if (param != null && param.getParameterElement() != null && param.getParameterElement().getParameterElementCode() != null) {
				if (!parametersMap.containsKey(param.getParameterElement().getParameterElementCode())) {
					parametersMap.put(param.getParameterElement().getParameterElementCode(), new ArrayList<Parameter>());
				}
				param.setDefaultParameterValue(Boolean.FALSE);
				parametersMap.get(param.getParameterElement().getParameterElementCode()).add(param);
			}
		}
	}
	
	/**@
	 * Common parameters definition with default values. 
	 * @return set of parameters needed in audit request.
	 */
	private static Set<Parameter> commonDefaultParameters() {
		
		Set<Parameter> parameters = new HashSet<Parameter>();
		
		parameters.add(createParameter("DATA_TABLE_MARKER", ""));
		parameters.add(createParameter("PROXY_HOST", ""));
		parameters.add(createParameter("CONSIDER_COOKIES", "true"));
		parameters.add(createParameter("ALTERNATIVE_CONTRAST_MECHANISM", "false"));
		parameters.add(createParameter("LEVEL", "AW22;Or"));
		parameters.add(createParameter("INFORMATIVE_IMAGE_MARKER", ""));
		parameters.add(createParameter("PROXY_PORT", ""));
		parameters.add(createParameter("PRESENTATION_TABLE_MARKER", ""));
		parameters.add(createParameter("DECORATIVE_IMAGE_MARKER", ""));
		
		return parameters;
	}
	
	/**
	 * Create parameter based on minimal information.
	 * @param code : parameter element code
	 * @param value : parameter value
	 * @return initialized parameter
	 */
    public static Parameter createParameter(String code, String value) {
        
       
        Parameter parameter = null;
        
        //Parameter identification
        if(parametersMap != null && parametersMap.containsKey(code)){
        	List<Parameter> parameters = parametersMap.get(code);
        	for(Parameter param : parameters){
        		if(value.equals(param.getValue())){//second level identification of parameter
        			parameter = param;
        		} 
        	}
        	if(parameter == null){ //
        		parameter = parameters.get(0);
        	}
        } 
        
		
		if (parameter != null) {
			parameter.setDefaultParameterValue(Boolean.TRUE);
			parameter.setValue(value);
		}

		return parameter;
    }
    
	/**
	 * Page audit parameters definition with default values. 
	 * @return set of parameters needed in page audit request.
	 */
	public static Set<Parameter> getDefaultParametersForPA(){
		
		Set<Parameter> parameters = commonDefaultParameters();
		
		//specific parameters to page audit
		parameters.add(createParameter("MAX_DOCUMENTS", "1"));
		parameters.add(createParameter("MAX_DURATION", "86400"));
		parameters.add(createParameter("EXCLUSION_REGEXP", ""));
		parameters.add(createParameter("DEPTH", "0"));
		
		return parameters;
	}
	
	/**
	 * Scenario audit parameters definition with default values. 
	 * @return set of parameters needed in scenario audit request.
	 */
	public static Set<Parameter> getDefaultParametersForScenario() {
		return commonDefaultParameters();
	}
}
