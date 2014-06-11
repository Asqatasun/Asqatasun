package org.tanaguru.jms.messaging;


import java.util.Set;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.service.parameterization.ParameterDataService;
import org.opens.tanaguru.service.AuditService;
//import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;
import org.tanaguru.jms.util.ParameterUtils;


/**
 * Session aware JMS message listener linked to audit out-coming queue.
 * 
 * 
 */
@Component
public class AuditListener implements SessionAwareMessageListener<TextMessage> {


	@Autowired
	@Qualifier("jmsAuditInTemplate")
	private JmsTemplate jmsAuditInTemplate;
	
	@Autowired
	ParameterDataService parameterDataService;
	
	@Autowired
    private AuditService auditService;
    
   public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}

	/**
	 * logger
	 */
	private static final Logger logger = Logger.getLogger(AuditListener.class);

	//private static Logger logger = LoggerFactory.getLogger(AuditListener.class);

	/**
	 * Handle a message , within a JMS session.
	 */
	@Override
	public void onMessage(TextMessage pageURL, Session session) throws JMSException {
	    System.out.println("reception du message");

		logger.info("[AUDIT][IN] A message have been received...");

		if (pageURL instanceof TextMessage) {

			System.out.println("[AUDIT][IN] A message have been received..." + pageURL.getText());
			//1- log in db message and type of audit
			//2- format message to objet audit

			logger.info("Initialize parameters...");
        	//get parameters from DB
    		ParameterUtils.initParametersMap(parameterDataService);
    		//get default set of parameters
    		Set<Parameter> parameters =  ParameterUtils.getDefaultParametersForPA();
    		//set option values
    		ParameterUtils.initializePAInputOptions("AW22;Ar", null, null, null, null, parameters);
    		
    		logger.info("Launch audit page service...");
    		
    		//launch ws 
        	Audit audit = auditService.auditPage(pageURL.getText(), parameters);
			
			//3- persist audit information on db observatoire
        	
        	Long idAudit =audit.getId();
        	
        	logger.info("Audit finished" + audit.getId());
        	
        	
			
			
	    }
	}
}
