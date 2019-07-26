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
package org.asqatasun.entity.audit;

import org.asqatasun.entity.audit.SourceCodeRemark;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author jkowalczyk
 */
@Entity
@XmlRootElement
public class SourceCodeRemarkImpl extends ProcessRemarkImpl implements
        SourceCodeRemark {

    private static final long serialVersionUID = -4590947355988767670L;
    @Column(name = "Character_Position")
    private int characterPosition;
    @Column(name = "Line_Number")
    private int lineNumber;
    @Column(name = "Target", length = 5000)
    private String target;
    @Column(name = "Snippet", length = 16777215)
    private String snippet;
    
    public SourceCodeRemarkImpl() {
        super();
    }

    @Override
    public int getCharacterPosition() {
        return characterPosition;
    }

    @Override
    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public String getTarget() {
        return target;
    }

    @Override
    public void setCharacterPosition(int characterPosition) {
        this.characterPosition = characterPosition;
    }

    @Override
    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Override
    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String getSnippet() {
        return snippet;
    }

    @Override
    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

}