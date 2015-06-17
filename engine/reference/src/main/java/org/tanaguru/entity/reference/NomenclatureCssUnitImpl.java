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
package org.tanaguru.entity.reference;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;
import org.tanaguru.entity.service.reference.NomenclatureCssUnit;

/**
 *
 * @author jkowalczyk
 */
@Entity
@XmlRootElement
public class NomenclatureCssUnitImpl extends NomenclatureElementImpl implements
        NomenclatureCssUnit, Serializable {

    @Column(name = "shortValue")
    private int cssShortValue;

    public NomenclatureCssUnitImpl() {
        super();
    }

    public NomenclatureCssUnitImpl(int cssShortValue, String cssStringValue) {
        super(cssStringValue);
        this.cssShortValue = cssShortValue;
    }

    @Override
    public int getCssShortValue() {
        return cssShortValue;
    }

    @Override
    public void setCssShortValue(int cssShortValue) {
        this.cssShortValue = cssShortValue;
    }
}
