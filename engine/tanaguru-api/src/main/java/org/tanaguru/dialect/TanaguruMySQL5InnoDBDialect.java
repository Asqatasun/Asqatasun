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
package org.tanaguru.dialect;

import java.sql.Types;
import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * Some entities are defined as mediumText. For any reason that type is ignored
 * by the implementation of MySQL5Dialect. This class extends the MySQL5InnoDBDialect
 * implementation to deal with the mediumtext sql type.
 *
 * @author jkowalczyk
 */
public class TanaguruMySQL5InnoDBDialect extends MySQL5InnoDBDialect {

    /**
     * Constructor
     */
    public TanaguruMySQL5InnoDBDialect() {
        super();
    }
    
    @Override
    protected void registerVarcharTypes() {
        registerColumnType( Types.VARCHAR, "longtext" );
        registerColumnType( Types.VARCHAR, 16777215, "mediumtext" );
        registerColumnType( Types.VARCHAR, 65535, "varchar($l)" );
        registerColumnType( Types.LONGVARCHAR, "longtext" );
    }
}