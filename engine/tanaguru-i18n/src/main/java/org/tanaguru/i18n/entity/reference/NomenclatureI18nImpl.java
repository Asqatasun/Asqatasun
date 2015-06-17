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
import org.tanaguru.entity.reference.Nomenclature;
import org.tanaguru.entity.reference.NomenclatureImpl;
import org.tanaguru.i18n.entity.AbstractInternationalizedEntity;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@Table(name = "NOMENCLATURE_I18N")
public class NomenclatureI18nImpl extends AbstractInternationalizedEntity<Nomenclature> implements
        NomenclatureI18n, Serializable {

    private static final long serialVersionUID = -7280669153081145802L;

    @Column(name = "Description")
    private String description;
    @Column(name = "Long_Label")
    private String longLabel;
    @Column(name = "Short_Label")
    private String shortLabel;
    @ManyToOne
    @JoinColumn(name = "Id_Nomenclature")
    private NomenclatureImpl target;

    public NomenclatureI18nImpl() {
        super();
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getLongLabel() {
        return longLabel;
    }

    @Override
    public String getShortLabel() {
        return shortLabel;
    }

    @Override
    public Nomenclature getTarget() {
        return target;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setLongLabel(String longLabel) {
        this.longLabel = longLabel;
    }

    @Override
    public void setShortLabel(String shortLabel) {
        this.shortLabel = shortLabel;
    }

    @Override
    public void setTarget(Nomenclature target) {
        this.target = (NomenclatureImpl) target;
    }

}