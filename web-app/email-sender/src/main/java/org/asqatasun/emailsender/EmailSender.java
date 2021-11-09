/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.emailsender;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author jkowalczyk
 */
@Service("emailSender")
public class EmailSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailSender.class);
    private static final String CONTENT_TYPE_KEY = "Content-Type";
    private static final String FULL_CHARSET_KEY = "text/html; charset=UTF-8";
    private static final String CHARSET_KEY = "UTF-8";
    private String smtpProtocol;

    @Value("${app.emailSender.smtp.debug:false}")
    private Boolean debug;
    @Value("${app.emailSender.smtp.from:}")
    private String from;
    @Value("${app.emailSender.smtp.host:localhost}")
    private String smtpHost;
    @Value("${app.emailSender.smtp.port:-1}")
    private int smtpPort;
    @Value("${app.emailSender.smtp.ssl.enable:false}")
    private Boolean secureProtocolEnable; // If true then "startTtlsEnable" property is not taken into account
    @Value("${app.emailSender.smtp.ssl.protocols:TLSv1.2}")
    private String secureProtocols;
    @Value("${app.emailSender.smtp.starttls.enable:false}")
    private Boolean startTtlsEnable;
    @Value("${app.emailSender.smtp.user:}")
    private String userName;
    @Value("${app.emailSender.smtp.password:}")
    private String password;


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

        // Set SMTP properties (host, port, ...)
        Properties props = new Properties();
        if (secureProtocolEnable) {
            smtpProtocol = "smtps";
            props.put("mail." + smtpProtocol + ".ssl.protocols", secureProtocols);
        }
        else if (startTtlsEnable) {
            smtpProtocol = "smtp";
            props.put("mail." + smtpProtocol + ".starttls.enable", "true");
            props.put("mail." + smtpProtocol + ".socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail." + smtpProtocol + ".socketFactory.fallback", "true");
            props.put("mail." + smtpProtocol + ".ssl.protocols", secureProtocols);
            if (smtpPort != -1) {
                props.put("mail." + smtpProtocol + ".socketFactory.port", smtpPort);
            }
        }
        else {
            smtpProtocol = "smtp";
            props.put("mail." + smtpProtocol + ".starttls.enable", "false");
        }
        props.put("mail." + smtpProtocol + ".host", smtpHost);
        props.put("mail." + smtpProtocol + ".auth", "true");
        if (smtpPort != -1) {
            props.put("mail." + smtpProtocol + ".port", smtpPort);
        }

        // Log SMTP configuration
        if(debug) {
            LOGGER.info("SMTP configuration: "+ props.toString());
        }

        // create some properties and get the default Session
        Session session = Session.getInstance(props);
        session.setDebug(debug);
        try {
            Transport t = session.getTransport(smtpProtocol);
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
