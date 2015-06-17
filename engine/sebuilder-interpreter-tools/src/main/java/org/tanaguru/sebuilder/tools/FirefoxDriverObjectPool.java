/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015  Tanaguru.org
 * 
 *  This file is part of Tanaguru.
 * 
 *  Tanaguru is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * 
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.sebuilder.tools;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * FirefoxDriver Object Pool.</br>
 * The following parameters are set by spring configuration : 
 * <ul>
 * <li>LIFO</li>
 * <li>whenExhaustedAction</li>
 * <li>minIdle</li>
 * <li>maxActive</li>
 * <li>minEvictableIdleTimeMillis</li>
 * <li>timeBetweenEvictionRunsMillis</li>
 * </ul>
 * 
 * @author jkowalczyk
 */
public class FirefoxDriverObjectPool extends GenericObjectPool<FirefoxDriver> {

    @Autowired
    public FirefoxDriverObjectPool(PoolableObjectFactory pof) {
        super(pof);
    }

}