/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2020  Asqatasun.org
 * 
 *  This file is part of Asqatasun.
 * 
 *  Asqatasun is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * 
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Contact us by mail: asqatasun AT asqatasun DOT org
 */

package org.asqatasun.rules.elementchecker.pertinence;

import org.apache.commons.lang3.StringUtils;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.rules.elementchecker.text.TextEndsWithChecker;
import org.asqatasun.rules.textbuilder.AccessibleNameElementBuilder;
import org.asqatasun.rules.textbuilder.TextAttributeOfElementBuilder;
import org.asqatasun.rules.textbuilder.TextElementBuilder;

import javax.annotation.Nullable;

/**
 * This class checks whether the content of an attribute is pertinent by verifying :
 * <ul>
 * <li>it is not empty</li>
 * <li>it is not only composed of non alphanumerical characters</li>
 * <li>it is not blacklisted</li>
 * <li>it is not identical to another text (the content of another attribute
 * for instance)</li>
  *</ul>
 */
public class AlternativePertinenceChecker extends TextPertinenceChecker {

    /* The attr element builder needed to retrieve the attribute to compare with*/
    private TextElementBuilder accessibleNameElementBuilder = new AccessibleNameElementBuilder();
    @Override
    public TextElementBuilder getTextElementBuilder() {
        if (accessibleNameElementBuilder == null) {
            accessibleNameElementBuilder = new AccessibleNameElementBuilder();
        }
        return accessibleNameElementBuilder;
    }

    /**
     * Constructor.
     * Enables to override the not pertinent solution.
     *
     *
     *
     */
    public AlternativePertinenceChecker(
            @Nullable String extensionsListNameToCompareWith,
            TestSolution alternativeNotPertinentSolution,
            String alternativeNotPertinentMessageCode,
            String manualCheckMessage,
            String... eeAttributeNameList) {
        super(true, // alway check emptiness
              null,
              null, // this attribute is about the text blacklist nomenclature
              alternativeNotPertinentSolution,
              alternativeNotPertinentMessageCode,
              manualCheckMessage,
              eeAttributeNameList);

        addExtensionChecker(extensionsListNameToCompareWith);
    }

    /**
     * Add a {@link TextEndsWithChecker} to the checker collection
     */
    private void addExtensionChecker(String extensionsListNameToCompareWith) {
        // The second check consists in verifying the element content does not only
        // contains non alphanumerical characters
        if (StringUtils.isNotBlank(extensionsListNameToCompareWith)) {
            addChecker(new TextEndsWithChecker(
                            getTextElementBuilder(),
                            extensionsListNameToCompareWith,
                            getFailureSolution(), 
                            notPertinentMessageCode,
                            getEeAttributeNames()));
        }
    }

}
