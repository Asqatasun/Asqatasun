/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
package org.asqatasun.webapp.util.webapp;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * Bean that should be used instead of the {@link PropertyPlaceholderConfigurer} if you want to have
 * access to the resolved properties not obly from the Spring context. e.g. from JSP or so. 
 *
 * @author Mykola Palienko
 */
public class ExposablePropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private Map<String, String> resolvedProps;

    public ExposablePropertyPlaceholderConfigurer(){
        super();
    }

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
            Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        resolvedProps = new HashMap<>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            resolvedProps.put(keyStr, parseStringValue(props.getProperty(keyStr), props,
                    new HashSet()));
        }
    }

    public Map<String, String> getResolvedProps() {
        return Collections.unmodifiableMap(resolvedProps);
    }

}