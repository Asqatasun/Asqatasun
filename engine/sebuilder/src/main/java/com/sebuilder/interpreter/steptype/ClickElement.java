/*
 * Copyright 2012 Sauce Labs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sebuilder.interpreter.steptype;

import com.sebuilder.interpreter.StepType;
import com.sebuilder.interpreter.TestRun;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.openqa.selenium.WebElement;

public class ClickElement implements StepType {

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
        WebElement webElement = ctx.locator("locator").find(ctx);
        ctx.log().info("Click from "  + webElement.getTagName() + " " + webElement.getText());
//        boolean doesClickBringToAnotherPage = doesClickBringToAnotherPage(webElement, ctx.log(), ctx.getDriver().getCurrentUrl());
        webElement.click();
//        if (doesClickBringToAnotherPage){
//            ctx.log().info("Fire New Page From a Click ");
//            ctx.fireNewPage();
//        }
        return true;
    }
    
    private boolean doesClickBringToAnotherPage(WebElement we, Log log, String currentUrl) {
        String tagName = we.getTagName();
        if (tagName.equalsIgnoreCase(A_TAG)) {
            return computeATag(we, log, currentUrl);
        } else if (tagName.equalsIgnoreCase(BUTTON_TAG)) {
            return computeButtonTag(we);
        } else if (tagName.equalsIgnoreCase(INPUT_TAG)) {
            return computeInputTag(we);
        } else if (tagName.equalsIgnoreCase(LABEL_TAG)) {
            return computeLabelTag(we);
        }
        return false;
    }

    private boolean computeATag (WebElement we, Log log, String currentUrl) {
        String hrefValue = StringUtils.remove(we.getAttribute(HREF_ATTR), currentUrl);
        log.info("Href value "  + hrefValue);
        if (StringUtils.isNotEmpty(hrefValue) && !hrefValue.startsWith(ANCHOR_KEY)) {
            return true;
        }
        return false;
    }
    
    private boolean computeButtonTag (WebElement we) {
        return isElementASubmit(we);
    }
    
    private boolean computeInputTag(WebElement we) {
        return isElementASubmit(we);
    }
    
    private boolean computeLabelTag(WebElement we) {
        return false;
    }
    
    private boolean isElementASubmit(WebElement we) {
        String typeValue = we.getAttribute(TYPE_ATTR);
        if (StringUtils.isNotEmpty(typeValue) && typeValue.equalsIgnoreCase(SUBMIT_TYPE_VALUE)) {
            return true;
        }
        return false;
    }
}