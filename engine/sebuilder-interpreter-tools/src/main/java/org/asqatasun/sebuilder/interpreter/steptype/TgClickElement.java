/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2015  Asqatasun.org
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
package org.asqatasun.sebuilder.interpreter.steptype;

import com.sebuilder.interpreter.StepType;
import com.sebuilder.interpreter.TestRun;
import org.openqa.selenium.WebElement;

public class TgClickElement implements StepType {

    private static final String A_TAG = "a";
    private static final String BUTTON_TAG = "a";
    private static final String INPUT_TAG = "input";
    private static final String LABEL_TAG = "label";
    private static final String TYPE_ATTR = "type";
    private static final String HREF_ATTR = "href";
    private static final String SUBMIT_TYPE_VALUE = "submit";
    private static final String ANCHOR_KEY = "#";

    @Override
    public boolean run(TestRun ctx) {
//        return super.run(ctx);
        WebElement webElement = ctx.locator("locator").find(ctx);
        ctx.getLog().info("Click from " + webElement.getTagName() + " " + webElement.getText());
//        boolean doesClickBringToAnotherPage = doesClickBringToAnotherPage(webElement, ctx.log(), ctx.getDriver().getCurrentUrl());
        webElement.click();
//        if (doesClickBringToAnotherPage){
//            ctx.log().info("Fire New Page From a Click ");
//            ctx.fireNewPage();
//        }
        return true;
    }

//    private boolean doesClickBringToAnotherPage(WebElement we, Log log, String currentUrl) {
//        String tagName = we.getTagName();
//        if (tagName.equalsIgnoreCase(A_TAG)) {
//            return computeATag(we, log, currentUrl);
//        } else if (tagName.equalsIgnoreCase(BUTTON_TAG)) {
//            return computeButtonTag(we);
//        } else if (tagName.equalsIgnoreCase(INPUT_TAG)) {
//            return computeInputTag(we);
//        } else if (tagName.equalsIgnoreCase(LABEL_TAG)) {
//            return computeLabelTag(we);
//        }
//        return false;
//    }
//
//    private boolean computeATag(WebElement we, Log log, String currentUrl) {
//        String hrefValue = StringUtils.remove(we.getAttribute(HREF_ATTR), currentUrl);
//        log.info("Href value " + hrefValue);
//        if (StringUtils.isNotEmpty(hrefValue) && !hrefValue.startsWith(ANCHOR_KEY)) {
//            return true;
//        }
//        return false;
//    }
//
//    private boolean computeButtonTag(WebElement we) {
//        return isElementASubmit(we);
//    }
//
//    private boolean computeInputTag(WebElement we) {
//        return isElementASubmit(we);
//    }
//
//    private boolean computeLabelTag(WebElement we) {
//        return false;
//    }
//
//    private boolean isElementASubmit(WebElement we) {
//        String typeValue = we.getAttribute(TYPE_ATTR);
//        if (StringUtils.isNotEmpty(typeValue) && typeValue.equalsIgnoreCase(SUBMIT_TYPE_VALUE)) {
//            return true;
//        }
//        return false;
//    }
    
}