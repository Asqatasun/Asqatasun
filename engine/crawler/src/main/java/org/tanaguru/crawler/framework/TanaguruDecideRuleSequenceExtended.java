/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tanaguru.crawler.framework;

import java.util.List;
import org.apache.log4j.Logger;
import org.archive.modules.CrawlURI;
import org.archive.modules.deciderules.DecideResult;
import org.archive.modules.deciderules.DecideRule;
import org.archive.modules.deciderules.DecideRuleSequence;

/**
 *
 * @author alingua
 */
public class TanaguruDecideRuleSequenceExtended extends DecideRuleSequence {

    @Override
    public DecideResult innerDecide(CrawlURI uri) {
        DecideRule decisiveRule = null;
        int decisiveRuleNumber = -1;
        DecideResult result = DecideResult.NONE;
        List<DecideRule> rules = getRules();
        int max = rules.size();

        for (int i = 0; i < max; i++) {
            DecideRule rule = rules.get(i);
            if (rule.onlyDecision(uri) != result) {
                DecideResult r = rule.decisionFor(uri);
                Logger.getLogger(this.getClass()).debug("DecideRule #" + i + " "
                        + rule.getClass().getName() + " returned " + r + " for url: " + uri);
                if (r != DecideResult.NONE) {
                    result = r;
                    decisiveRule = rule;
                    decisiveRuleNumber = i;
                }
            }
        }
        if (fileLogger != null) {
            fileLogger.info(decisiveRuleNumber + " " + decisiveRule.getClass().getSimpleName() + " " + result + " " + uri);
        }

        return result;
    }
}