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
package org.tanaguru.rules.accessiweb21;
import org.tanaguru.rules.accessiweb21.marker.AbstractPageRuleWithMarkerImplementation;
import org.w3c.dom.Node;

/**
 * Does each data table (table tag) have a summary attribute?
 * 
 * @author jkowalczyk
 */
public class Aw21Rule05011 extends AbstractPageRuleWithMarkerImplementation {

    private static final String HTML_ELEMENT = "TABLE";
    private static final String PARAMETER_ELEMENT = "DATA_TABLE_MARKER";
    private static final String DECIDABLE_FAILED_REMARK_MSG = "SummaryMissing";
    private static final String NOT_DECIDABLE_FAILED_REMARK_MSG = "SummaryMissingCheckDataTable";
    private static final String NOT_DECIDABLE_PASSED_REMARK_MSG = "SummaryPresentCheckDataTable";

    public Aw21Rule05011() {
        super(
                HTML_ELEMENT,
                PARAMETER_ELEMENT,
                DECIDABLE_FAILED_REMARK_MSG,
                NOT_DECIDABLE_FAILED_REMARK_MSG,
                NOT_DECIDABLE_PASSED_REMARK_MSG);
    }

    @Override
    public boolean doProcess(Node node) {
        return (node.getAttributes().getNamedItem(NodeAndAttributeKeyStore.SUMMARY_ATTR) != null) ? true : false;
    }

}