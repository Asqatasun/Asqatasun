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
package org.asqatasun.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;

import java.util.Properties;

@Configuration
public class ExceptionResolverConfiguration {

    @Bean
    public ResponseStatusExceptionResolver responseStatusExceptionResolver() {
        return new ResponseStatusExceptionResolver();
    }


    @Bean
    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver( );
        Properties errorMaps = new Properties( );
        errorMaps.setProperty("org.asqatasun.webapp.exception.ForbiddenUserException", "access-denied");
        errorMaps.setProperty("org.asqatasun.webapp.exception.ForbiddenPageException", "access-denied");
        errorMaps.setProperty("org.asqatasun.webapp.exception.ForbiddenAuditException", "access-denied");
        errorMaps.setProperty("org.asqatasun.webapp.exception.LostInSpaceException", "oups");
        errorMaps.setProperty("org.asqatasun.webapp.exception.KrashAuditException", "oups");
        errorMaps.setProperty("org.springframework.web.bind.MissingServletRequestParameterException", "access-denied");
        errorMaps.setProperty("org.springframework.web.method.annotation.support.MethodArgumentNotValidException", "access-denied");
        resolver.setExceptionMappings(errorMaps);
        Properties statusCodeMaps = new Properties( );
        statusCodeMaps.setProperty("access-denied", "403");
        statusCodeMaps.setProperty("oups", "200");
        resolver.setStatusCodes(statusCodeMaps);
        resolver.setDefaultStatusCode(404);
        return resolver;
    }
}
