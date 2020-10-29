package org.asqatasun.rules.rgaa40;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.asqatasun.ruleimplementation.AbstractMarkerPageRuleImplementation;
import org.asqatasun.rules.elementchecker.pertinence.AlternativePertinenceChecker;
import org.asqatasun.rules.elementselector.ElementSelector;
import org.asqatasun.rules.elementselector.ElementWithAccessibleNameSelector;

import static org.asqatasun.entity.audit.TestSolution.FAILED;
import static org.asqatasun.entity.audit.TestSolution.NEED_MORE_INFO;
import static org.asqatasun.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.RemarkMessageStore.*;

public class AbstractInformativeImagePertinenceAlternativePageRuleImplementation extends AbstractMarkerPageRuleImplementation {

    private static final String IMAGE_FILE_EXTENSION_NOM = "ImageFileExtensions";

    public AbstractInformativeImagePertinenceAlternativePageRuleImplementation(
        ElementSelector elementSelector,
        boolean failInCaseOfNotPertinent,
        String... eeList) {
        super(
            elementSelector,
            // the informative images are part of the scope
            INFORMATIVE_IMAGE_MARKER,
            // the decorative images are not part of the scope
            DECORATIVE_IMAGE_MARKER,
            new AlternativePertinenceChecker(
                IMAGE_FILE_EXTENSION_NOM,
                failInCaseOfNotPertinent? FAILED : NEED_MORE_INFO,
                failInCaseOfNotPertinent? NOT_PERTINENT_ALT_MSG : CHECK_PRESENCE_OF_ALTERNATIVE_MECHANISM_FOR_INFORMATIVE_IMG_MSG,
                CHECK_ALT_PERTINENCE_OF_INFORMATIVE_IMG_MSG,
                eeList),
            new AlternativePertinenceChecker(
                IMAGE_FILE_EXTENSION_NOM,
                NEED_MORE_INFO,
                failInCaseOfNotPertinent? CHECK_NATURE_OF_IMAGE_WITH_NOT_PERTINENT_ALT_MSG : CHECK_PRESENCE_OF_ALTERNATIVE_MECHANISM_FOR_INFORMATIVE_IMG_MSG,
                CHECK_NATURE_OF_IMAGE_AND_ALT_PERTINENCE_MSG,
                eeList));
    }
}
