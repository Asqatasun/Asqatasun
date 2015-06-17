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

import java.io.Serializable;
import javax.persistence.*;
import org.tanaguru.entity.reference.NomenclatureElement;
import org.tanaguru.entity.reference.NomenclatureElementImpl;
import org.tanaguru.i18n.entity.AbstractInternationalizedEntity;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@Table(name = "NOMENCLATURE_ELEMENT_I18N")
public class NomenclatureElementI18nImpl extends AbstractInternationalizedEntity<NomenclatureElement> implements
        NomenclatureElementI18n, Serializable {

    private static final long serialVersionUID = 1929357195065901917L;
    
    @ManyToOne
    @JoinColumn(name = "Id_Nomenclature_Element")
    private NomenclatureElementImpl target;
    @Column(name = "Value")
    private String value;

    public NomenclatureElementI18nImpl() {
        super();
    }

    @Override
    public NomenclatureElement getTarget() {
        return target;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setTarget(NomenclatureElement target) {
        this.target = (NomenclatureElementImpl) target;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

}