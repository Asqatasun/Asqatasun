/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
package org.tanaguru.entity.audit;

/**
 *
 * @author jkowalczyk
 */
public interface DefiniteResult extends ProcessResult {

    /**
     *
     * @return the definite value
     */
    TestSolution getDefiniteValue();

    /**
     *
     * @param testSolution the definite value to set
     */
    void setDefiniteValue(TestSolution testSolution);

    /**
     *
     * @return the definite manual audit value
     */
    TestSolution getManualDefiniteValue();

    /**
     *
     * @param manualDefiniteValue the definite manual value to set
     */
    void setManualDefiniteValue(TestSolution manualDefiniteValue);

    /**
     *
     * @return the manual audit comment
     */
    String getManualAuditComment();

    /**
     *
     * @param manualAuditcomment the manual audit comment to set
     */
    void setManualAuditComment(String manualAuditcomment);
}
