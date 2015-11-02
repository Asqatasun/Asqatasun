/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.tanaguru.entity.reference;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@Table(name = "LEVEL")
@XmlRootElement
public class LevelImpl implements Level, Serializable {

    @Column(name = "Cd_Level")
    private String code;
    @Column(name = "Description")
    private String description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Level")
    private Long id;
    @Column(name = "Label")
    private String label;
    @Column(name = "Rank")
    private int rank;

    public LevelImpl() {
        super();
    }

    public LevelImpl(String code, String label, String description) {
        this();
        this.code = code;
        this.label = label;
        this.description = description;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public int getRank() {
        return rank;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public void setRank(int rank) {
        this.rank = rank;
    }
}
