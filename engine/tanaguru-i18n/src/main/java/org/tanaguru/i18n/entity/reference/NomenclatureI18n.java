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
package org.tanaguru.i18n.entity.reference;

import org.tanaguru.entity.reference.Nomenclature;
import org.tanaguru.sdk.entity.i18n.InternationalizedEntity;

/**
 * 
 * @author jkowalczyk
 */
public interface NomenclatureI18n extends InternationalizedEntity<Nomenclature> {

    /**
     *
     * @return the description
     */
    String getDescription();

    /**
     *
     * @return the long label
     */
    String getLongLabel();

    /**
     *
     * @return the short label
     */
    String getShortLabel();

    /**
     *
     * @param description
     *            the description to set
     */
    void setDescription(String description);

    /**
     *
     * @param longLabel
     *            the long label to set
     */
    void setLongLabel(String longLabel);

    /**
     *
     * @param shortLabel
     *            the short label to set
     */
    void setShortLabel(String shortLabel);

}