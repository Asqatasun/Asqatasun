package org.asqatasun.rules.rgaa40;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.asqatasun.ruleimplementation.AbstractMarkerPageRuleImplementation;
import org.asqatasun.rules.elementchecker.element.AccessibleNamePresenceChecker;
import org.asqatasun.rules.elementselector.ElementSelector;

import static org.asqatasun.entity.audit.TestSolution.*;
import static org.asqatasun.entity.audit.TestSolution.NEED_MORE_INFO;
import static org.asqatasun.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.RemarkMessageStore.*;

public class AbstractInformativeImagePresenceAlternativePageRuleImplementation extends AbstractMarkerPageRuleImplementation {

    public AbstractInformativeImagePresenceAlternativePageRuleImplementation(
        ElementSelector elementSelector,
        boolean failInCaseOfAbsence,
        String... eeList) {
        super(elementSelector,
            // the informative images are part of the scope
            INFORMATIVE_IMAGE_MARKER,
            // the decorative images are not part of the scope
            DECORATIVE_IMAGE_MARKER,
            new AccessibleNamePresenceChecker(
                new ImmutablePair<>(PASSED, null),
                failInCaseOfAbsence? new ImmutablePair<>(FAILED, ALT_MISSING_MSG) : new ImmutablePair<>(NEED_MORE_INFO, CHECK_PRESENCE_OF_ALTERNATIVE_MECHANISM_FOR_INFORMATIVE_IMG_MSG),
                eeList),
            new AccessibleNamePresenceChecker(
                new ImmutablePair<>(NEED_MORE_INFO, CHECK_NATURE_OF_IMAGE_WITH_TEXTUAL_ALTERNATIVE_MSG),
                new ImmutablePair<>(NEED_MORE_INFO, CHECK_NATURE_OF_IMAGE_WITHOUT_TEXTUAL_ALTERNATIVE_MSG),
                eeList));
    }
}
