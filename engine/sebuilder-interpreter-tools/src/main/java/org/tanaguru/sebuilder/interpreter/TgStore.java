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
package org.tanaguru.sebuilder.interpreter;

import com.sebuilder.interpreter.Getter;
import com.sebuilder.interpreter.Store;
import com.sebuilder.interpreter.TestRun;
import org.apache.commons.lang3.StringUtils;

/**
 * Generic Store that wraps a getter.
 *
 * @author jkowalczyk
 */
public class TgStore extends Store {

    private static final String TANAGURU_GET_PAGE_VARIABLE = "tanaguru-";
    
    public TgStore(Getter getter) {
        super(getter);
    }

    @Override
    public boolean run(TestRun ctx) {
        if (ctx instanceof TgTestRun && 
                StringUtils.startsWith(ctx.string("variable"), TANAGURU_GET_PAGE_VARIABLE)) {
            ctx.getLog().debug("Fire New Page From a Store");
            ((TgTestRun)ctx).fireNewPage(ctx.string("variable"));
            return true;
        } 
        return super.run(ctx);
    }

}