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
import org.tanaguru.entity.reference.Level;
import org.tanaguru.entity.reference.LevelImpl;
import org.tanaguru.i18n.entity.AbstractInternationalizedEntity;
import org.tanaguru.sdk.entity.i18n.Language;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@Table(name = "LEVEL_I18N")
public class LevelI18nImpl extends AbstractInternationalizedEntity<Level>
        implements LevelI18n, Serializable {

    private static final long serialVersionUID = 8315456347802456004L;
    
    @Column(name = "Description")
    private String description;
    @Column(name = "Label")
    private String label;
    @ManyToOne
    @JoinColumn(name = "Id_Level")
    private LevelImpl target;

    public LevelI18nImpl() {
        super();
    }

    public LevelI18nImpl(Language language, Level target, String label,
            String description) {
        super(language, target);
        this.label = label;
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public Level getTarget() {
        return target;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public void setTarget(Level target) {
        this.target = (LevelImpl) target;
    }

}