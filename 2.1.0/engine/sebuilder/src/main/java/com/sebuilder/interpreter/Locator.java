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
package com.sebuilder.interpreter;

import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;

/**
 * A Selenium locator.
 *
 * @author zarkonnen
 */
public class Locator {

    private Type type;
    public Type getType() {
        return type;
    }

    private String value;
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }

    public Locator(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    public Locator(String type, String value) {
        this.type = Type.ofName(type);
        this.value = value;
    }

    public Locator(Locator l) {
        type = l.type;
        value = l.value;
    }

    public WebElement find(TestRun ctx) {
        return type.find(value, ctx);
    }

    public List<WebElement> findElements(TestRun ctx) {
        return type.findElements(value, ctx);
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject o = new JSONObject();
        o.put("type", type.toString());
        o.put("value", value);
        return o;
    }

    @Override
    public String toString() {
        try {
            return toJSON().toString();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public enum Type {

        ID {

            @Override
            public WebElement find(String value, TestRun ctx) {
                return ctx.getDriver().findElementById(value);
            }

            @Override
            public List<WebElement> findElements(String value, TestRun ctx) {
                return ctx.getDriver().findElementsById(value);
            }
        },
        NAME {

            @Override
            public WebElement find(String value, TestRun ctx) {
                return ctx.getDriver().findElementByName(value);
            }

            @Override
            public List<WebElement> findElements(String value, TestRun ctx) {
                return ctx.getDriver().findElementsByName(value);
            }
        },
        LINK_TEXT {

            @Override
            public WebElement find(String value, TestRun ctx) {
                return ctx.getDriver().findElementByLinkText(value);
            }

            @Override
            public List<WebElement> findElements(String value, TestRun ctx) {
                return ctx.getDriver().findElementsByLinkText(value);
            }
        },
        CSS_SELECTOR {

            @Override
            public WebElement find(String value, TestRun ctx) {
                return ctx.getDriver().findElementByCssSelector(value);
            }

            @Override
            public List<WebElement> findElements(String value, TestRun ctx) {
                return ctx.getDriver().findElementsByCssSelector(value);
            }
        },
        XPATH {

            @Override
            public WebElement find(String value, TestRun ctx) {
                return ctx.getDriver().findElementByXPath(value);
            }

            @Override
            public List<WebElement> findElements(String value, TestRun ctx) {
                return ctx.getDriver().findElementsByXPath(value);
            }
        };

        public abstract WebElement find(String value, TestRun ctx);

        public abstract List<WebElement> findElements(String value, TestRun ctx);

        @Override
        public String toString() {
            return name().toLowerCase().replace("_", " ");
        }

        public static Type ofName(String name) {
            return Type.valueOf(name.toUpperCase().replace(" ", "_"));
        }
    }
}
