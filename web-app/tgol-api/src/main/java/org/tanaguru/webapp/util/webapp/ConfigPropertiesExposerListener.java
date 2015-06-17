/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
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
package org.tanaguru.webapp.util.webapp;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Listener that exposes properties configured by {@link ExposablePropertyPaceholderConfigurer} to
 * the web application context.
 *
 * @author jkowalczyk
 */
public class ConfigPropertiesExposerListener implements ServletContextListener {

    public static final String DEFAULT_PROPERTIES_BEAN_NAME = "exposedPropertiesConfigurer";
    public static final String DEFAULT_CONTEXT_PROPERTY = "configProperties";
    private String propertiesBeanName = DEFAULT_PROPERTIES_BEAN_NAME;
    private String contextProperty = DEFAULT_CONTEXT_PROPERTY;
    private static final Logger LOGGER = Logger.getLogger(ConfigPropertiesExposerListener.class);

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
         // TODO add ability to configure non default values via serveltContexParams
        ServletContext servletContext = sce.getServletContext();
         WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        ExposablePropertyPlaceholderConfigurer configurer =
                (ExposablePropertyPlaceholderConfigurer) context.getBean(propertiesBeanName);
        sce.getServletContext().setAttribute(contextProperty, configurer.getResolvedProps());
    }

}