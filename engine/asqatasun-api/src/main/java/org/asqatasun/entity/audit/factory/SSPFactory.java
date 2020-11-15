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
package org.asqatasun.entity.audit.factory;

import org.asqatasun.entity.audit.SSP;
import org.asqatasun.entity.subject.Page;
import org.asqatasun.entity.GenericFactory;
import java.util.Date;

/**
 * 
 * @author jkowalczyk
 */
public interface SSPFactory extends GenericFactory<SSP> {

    /**
     *
     * @param dateOfLoading
     * @param uri
     * @return
     *      an instance of SSP
     */
    SSP create(Date dateOfLoading, String uri);

    /**
     *
     * @param dateOfLoading
     * @param uri
     * @param sourceCode
     * @param page
     * @return
     *      an instance of SSP
     */
    SSP create(Date dateOfLoading, String uri, String sourceCode, Page page);

}
