/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.emailsender;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author jkowalczyk
 */
public class EmailSender {

    private static final Logger LOGGER = Logger.getLogger(EmailSender.class);
    private static final String SMTP_HOST = "localhost";
    private static final String CONTENT_TYPE_KEY = "Content-Type";
    private static final String FULL_CHARSET_KEY = "text/html; charset=UTF-8";
    private static final String CHARSET_KEY = "UTF-8";
    private String smtpHost = SMTP_HOST;

    public void setSmtpHost(String smptHost) {
        this.smtpHost = smptHost;
    }
    private String userName = "";

    public void setUserName(String userName) {
        this.userName = userName;
    }
    private String from = "";

    public void setFrom(String from) {
        this.from = from;
    }
    private String password = "";

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @param emailFrom
     * @param emailToSet
     * @param emailBccSet (can be null)
     * @param replyTo (can be null)
     * @param emailSubject
     * @param emailContent
     */
    public void sendEmail(
            String emailFrom, 
            Set<String> emailToSet,
            Set<String> emailBccSet,
            String replyTo,
            String emailSubject, 
            String emailContent) {
        boolean debug = false;

        // Set the host smtp address
        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        // create some properties and get the default Session
        Session session = Session.getInstance(props);
        session.setDebug(debug);
        try {
            Transport t = session.getTransport("smtp");
            t.connect(smtpHost, userName, password);

            // create a message
            MimeMessage msg = new MimeMessage(session);

            // set the from and to address
            InternetAddress addressFrom;
            try {
                // Used default from address is passed one is null or empty or
                // blank
                addressFrom = (StringUtils.isNotBlank(emailFrom)) ? new InternetAddress(
                        emailFrom) : new InternetAddress(from);
                msg.setFrom(addressFrom);
                Address[] recipients = new InternetAddress[emailToSet.size()];
                int i = 0;
                for (String emailTo : emailToSet) {
                    recipients[i] = new InternetAddress(emailTo);
                    i++;
                }
                
                msg.setRecipients(Message.RecipientType.TO, recipients);

                
                if (CollectionUtils.isNotEmpty(emailBccSet)) {
                    Address[] bccRecipients = new InternetAddress[emailBccSet.size()];
                    i = 0;
                    for (String emailBcc : emailBccSet) {
                        bccRecipients[i] = new InternetAddress(emailBcc);
                        i++;
                    }
                    msg.setRecipients(Message.RecipientType.BCC, bccRecipients);
                }

                if (StringUtils.isNotBlank(replyTo)) {
                    Address[] replyToRecipients = {new InternetAddress(replyTo)};
                    msg.setReplyTo(replyToRecipients);
                }

                // Setting the Subject
                msg.setSubject(emailSubject, CHARSET_KEY);

                // Setting content and charset (warning: both declarations of
                // charset are needed)
                msg.setHeader(CONTENT_TYPE_KEY, FULL_CHARSET_KEY);
                LOGGER.debug("emailContent  " + emailContent);
                msg.setContent(emailContent, FULL_CHARSET_KEY);
                try {
                    LOGGER.debug("emailContent from message object "
                            + msg.getContent().toString());
                } catch (IOException ex) {
                    LOGGER.error(ex.getMessage());
                } catch (MessagingException ex) {
                    LOGGER.error(ex.getMessage());
                }
                for (Address addr :  msg.getAllRecipients()) {
                    LOGGER.debug("addr " + addr);
                }
                t.sendMessage(msg, msg.getAllRecipients());
            } catch (AddressException ex) {
                LOGGER.warn("AddressException " + ex.getMessage());
                LOGGER.warn("AddressException " + ex.getStackTrace());
            }
        } catch (NoSuchProviderException e) {
            LOGGER.warn("NoSuchProviderException " + e.getMessage());
            LOGGER.warn("NoSuchProviderException " + e.getStackTrace());
        } catch (MessagingException e) {
            LOGGER.warn("MessagingException " + e.getMessage());
            LOGGER.warn("MessagingException " + e.getStackTrace());
        }
    }
}