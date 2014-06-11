package org.tanaguru.jms.messaging;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author OCEANE
 *
 */
public class TestProducerConsumer {
  
	public static void main(final String[] args) {
    ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(
        new String[] {"spring-jms-example.xml" });
    
    System.out.println("envoi du message");
    
    JmsProducer jmsProducer = (JmsProducer) appContext.getBean("jmsProducer");
    jmsProducer.sendMessageAudit("http://google.com");
    
   
    System.out.println("reception du message");
    JmsConsumer jmsConsumer = (JmsConsumer) appContext.getBean("jmsConsumer");
    jmsConsumer.recevoirMessage();
    
    jmsProducer.sendMessageAudit("http://oceaneconsulting.com");
    jmsConsumer.recevoirMessage();
    
  }
}
