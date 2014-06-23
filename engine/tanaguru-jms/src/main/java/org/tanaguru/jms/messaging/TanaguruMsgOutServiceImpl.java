package org.tanaguru.jms.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.opens.tanaguru.messagin.TanaguruMsgOutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;


/**
 * 
 * @author OCEANE
 *
 */
@Component
public class TanaguruMsgOutServiceImpl implements TanaguruMsgOutService {

private final static Logger LOGGER = LoggerFactory.getLogger(TanaguruMsgOutServiceImpl.class);

	@Autowired
	private JmsTemplate jmsTanaguruOutTemplate;

	@Override
	public boolean send(final String urlSite) {
		  
		try {
		jmsTanaguruOutTemplate.send(new MessageCreator() {
		      public Message createMessage(final Session session) throws JMSException {
		        return session.createTextMessage(urlSite);
		      }
		    });
		} catch (JmsException e) {
			LOGGER.error("Error during sending message to tanaguruQueueOut ", e);
			return false;
		}
		return true;
		  }
		
	@Override
	public void sendAndSave(String xmlContent, String fileName, String id) {
		// TODO Auto-generated method stub
	}
	
}
