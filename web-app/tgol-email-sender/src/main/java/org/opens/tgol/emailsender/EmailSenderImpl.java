/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tgol.emailsender;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

/**
 *
 * @author jkowalczyk
 */
public class EmailSenderImpl implements EmailSender {
    
    private static final Logger LOGGER = Logger.getLogger(EmailSenderImpl.class);
    private static final String SMTP_HOST_KEY = "mail.smtp.host";
    private static final String SMTP_HOST = "localhost";
    private static final String CONTENT_TYPE_KEY = "Content-Type";
    private static final String FULL_CHARSET_KEY = "text/plain; charset=UTF-8";
    private static final String CHARSET_KEY = "UTF-8";

    /**
     * 
     * @param emailFrom
     * @param emailToSet
     * @param mailContent
     */
    @Override
    public void sendEmail(
            String emailFrom,
            Set<String> emailToSet,
            String emailSubject,
            String emailContent) {
        boolean debug = false;

        //Set the host smtp address
        Properties props = new Properties();
        props.put(SMTP_HOST_KEY, SMTP_HOST);

        // create some properties and get the default Session
        Session session = Session.getDefaultInstance(props, null);
        session.setDebug(debug);

        // create a message
        MimeMessage msg = new MimeMessage(session);

        // set the from and to address
        InternetAddress addressFrom;
        try {
            addressFrom = new InternetAddress(emailFrom);
            msg.setFrom(addressFrom);
            Address[] recipients = new InternetAddress[emailToSet.size()];
            int i=0;
            for (String emailTo : emailToSet) {
                recipients[i] = new InternetAddress(emailTo);
                i++;
            }
            msg.setRecipients(Message.RecipientType.TO, recipients);

            // Setting the Subject
            msg.setSubject(emailSubject, CHARSET_KEY);

            // Setting content and charset (warning: both declarations of charset are needed)
            msg.setHeader(CONTENT_TYPE_KEY, FULL_CHARSET_KEY);
            LOGGER.debug("emailContent  " +  emailContent);
            msg.setContent(emailContent, FULL_CHARSET_KEY);
            try {
                LOGGER.debug("emailContent from message object " + msg.getContent().toString());
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(EmailSenderImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
                java.util.logging.Logger.getLogger(EmailSenderImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

            Transport.send(msg);
        } catch (AddressException ex) {
            LOGGER.warn(ex.getMessage());
        } catch (MessagingException ex) {
            LOGGER.warn(ex.getMessage());
        }
    }

}