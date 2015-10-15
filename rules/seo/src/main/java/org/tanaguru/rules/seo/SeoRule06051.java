/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This program is free software: you can redistribute it and/or modify
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
package org.tanaguru.rules.seo;

import org.tanaguru.ruleimplementation.AbstractPageRuleWithCheckerImplementation;
import org.tanaguru.rules.elementchecker.doctype.DoctypePresenceChecker;

/**
 * For each Web page, is the &lt;a href="http://www.braillenet.org/accessibilite/referentiel-aw21-en/glossaire.php#mDTD"&gt;
 * document type&lt;/a&gt; (doctype tag) available?
 * @author jkowalczyk
 */
public class SeoRule06051 extends AbstractPageRuleWithCheckerImplementation {

    /**
     *
     */
    public SeoRule06051() {
        super(new DoctypePresenceChecker());
    }

}