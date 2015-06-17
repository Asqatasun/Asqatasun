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
package org.tanaguru.referentiel.creator;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author alingua
 */
public class VelocityParametersContext {

    private List<String> classRule = new LinkedList();
    private String referentielLabel;
    private String classString;
    private String testCode;
    private String referentiel;
    private String packageString;
    private String destinationFolder;
    private String refDescriptor;
    private Set<Integer> themes = new LinkedHashSet();

    public String getClassString() {
        return this.classString;
    }

    public void setClassString(String classString) {
        this.classString = classString;
    }

    public String getRefDescriptor() {
        return refDescriptor;
    }

    public void setRefDescriptor(String refDescriptor) {
        this.refDescriptor = refDescriptor;
    }

    public String getTestCode() {
        return this.testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getPackageString() {
        return packageString;
    }

    public void setPackageString(String packageString) {
        this.packageString = packageString;
    }

    public String getReferentiel() {
        return referentiel;
    }

    public void setReferentiel(String referentiel) {
        this.referentiel = referentiel;
    }

    public String getReferentielLabel() {
        return referentielLabel;
    }

    public void setReferentielLabel(String referentielLabel) {
        this.referentielLabel = referentielLabel;
    }

    public List<String> getClassRule() {
        return classRule;
    }

    public void setClassRule(List<String> classRule) {
        this.classRule = classRule;
    }

    public Set<Integer> getThemes() {
        return themes;
    }

    public void setThemes(Set<Integer> themes) {
        this.themes = themes;
    }

    public String getDestinationFolder() {
        return destinationFolder;
    }

    public void setDestinationFolder(String destinationFolder) {
        this.destinationFolder = destinationFolder;
    }
}
