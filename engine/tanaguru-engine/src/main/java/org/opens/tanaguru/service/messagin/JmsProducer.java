package org.opens.tanaguru.service.messagin;


import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * 
 * @author OCEANE
 *
 */
public class JmsProducer {

  private JmsTemplate jmsTemplate;

  public void sendMessageAudit(final String urlSite) {
	  
    jmsTemplate.send(new MessageCreator() {
      public Message createMessage(final Session session) throws JMSException {
        return session.createTextMessage(urlSite);
      }
    });
  }

  public JmsTemplate getJmsTemplate() {
    return jmsTemplate;
  }

  public void setJmsTemplate(final JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }
} 
