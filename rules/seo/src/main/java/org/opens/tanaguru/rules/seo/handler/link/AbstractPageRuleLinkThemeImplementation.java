/*
 * @(#)AbstractPageRuleLinkThemeImplementation.java
 *
 * Copyright  2010 SAS OPEN-S. All rights reserved.
 * OPEN-S PROPRIETARY/CONFIDENTIAL.  Use is subject to license terms.
 *
 * This file is  protected by the  intellectual  property rights
 * in  France  and  other  countries, any  applicable  copyrights  or
 * patent rights, and international treaty provisions. No part may be
 * reproduced  in  any  form  by  any  mean  without   prior  written
 * authorization of OPEN-S.
 */

package org.opens.tanaguru.rules.seo.handler.link;

import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;

/**
 *
 * @author jkowalczyk
 */
public abstract class AbstractPageRuleLinkThemeImplementation
        extends AbstractPageRuleImplementation {

    protected LinkRulesHandler linkRulesHandler;

    public AbstractPageRuleLinkThemeImplementation() {
        super();
        linkRulesHandler = LinkRulesHandler.getInstance();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        ProcessResult processResult = null;
        linkRulesHandler.setNomenclatureLoaderService(nomenclatureLoaderService);
        linkRulesHandler.setSSPHandler(sspHandler);
        linkRulesHandler.setProcessRemarkService(sspHandler.getProcessRemarkService());
        return processResult;
    }

    protected void cleanUpRule(){
        linkRulesHandler.setSSPHandler(null);
        linkRulesHandler.setProcessRemarkService(null);
        linkRulesHandler = null;
    }

}
