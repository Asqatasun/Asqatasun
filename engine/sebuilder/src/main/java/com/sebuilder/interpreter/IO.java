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

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 * Utilities for reading scripts.
 *
 * @author zarkonnen
 */
public class IO {

    /**
     * @param r A Reader pointing to a JSON stream describing a script.
     * @return A script, ready to run.
     * @throws IOException If anything goes wrong with interpreting the JSON, or
     * with the Reader.
     * @throws JSONException If the JSON can't be parsed.
     */
    public static Script read(Reader r) throws IOException, JSONException {
        return parse(new JSONObject(new JSONTokener(r)));
    }

    /**
     * @param r A String pointing to a JSON stream describing a script.
     * @return A script, ready to run.
     * @throws IOException If anything goes wrong with interpreting the JSON, or
     * with the Reader.
     * @throws JSONException If the JSON can't be parsed.
     */
    public static Script read(String s) throws IOException, JSONException {
        return parse(new JSONObject(new JSONTokener(s)));
    }
    
    /**
     * @param r A String pointing to a JSON stream describing a script.
     * @return A script, ready to run.
     * @throws IOException If anything goes wrong with interpreting the JSON, or
     * with the Reader.
     * @throws JSONException If the JSON can't be parsed.
     */
    public static Script read(String s, FirefoxProfile firefoxProfile) throws IOException, JSONException {
        Script script = parse(new JSONObject(new JSONTokener(s)));
        script.setFirefoxProfile(firefoxProfile);
        return script;
    }

    /**
     * @param scriptO A JSONObject describing a script.
     * @return A script, ready to run.
     * @throws IOException If anything goes wrong with interpreting the JSON.
     */
    public static Script parse(JSONObject scriptO) throws IOException {
        try {
            if (!scriptO.get("seleniumVersion").equals("2")) {
                throw new IOException("Unsupported Selenium version: \"" + scriptO.get("seleniumVersion") + "\".");
            }
            if (scriptO.getInt("formatVersion") != 1) {
                throw new IOException("Unsupported Selenium script format version: \"" + scriptO.get("formatVersion") + "\".");
            }
            Script script = new Script();
            JSONArray stepsA = scriptO.getJSONArray("steps");
            for (int i = 0; i < stepsA.length(); i++) {
                JSONObject stepO = stepsA.getJSONObject(i);
                Script.Step step = new Script.Step(getStepTypeOfName(stepO.getString("type")));
                step.negated = stepO.optBoolean("negated", false);
                script.steps.add(step);
                JSONArray keysA = stepO.names();
                for (int j = 0; j < keysA.length(); j++) {
                    String key = keysA.getString(j);
                    if (key.equals("type")) {
                        continue;
                    }
                    if (stepO.optJSONObject(key) != null) {
                        step.locatorParams.put(key, new Locator(
                                stepO.getJSONObject(key).getString("type"),
                                stepO.getJSONObject(key).getString("value")));
                    } else {
                        step.stringParams.put(key, stepO.getString(key));
                    }
                }
            }
            return script;
        } catch (Exception e) {
            throw new IOException("Could not parse script.", e);
        }
    }
    /**
     * Mapping of the names of step types to their implementing classes, lazily
     * loaded through reflection. StepType classes must be in the
     * com.sebuilder.interpreter.steptype package and their name must be the
     * capitalized name of their type. For example, the class for "get" is at
     * com.sebuilder.interpreter.steptype.Get.
     *
     * Assert/Verify/WaitFor/Store steps use "Getter" objects that encapsulate
     * how to get the value they are about. Getters should be named eg "Title"
     * for "verifyTitle" and also be in the com.sebuilder.interpreter.steptype
     * package.
     */
    private static final HashMap<String, StepType> typesMap = new HashMap<String, StepType>();

    public static StepType getStepTypeOfName(String name) {
        try {
            if (!typesMap.containsKey(name)) {
                String className = name.substring(0, 1).toUpperCase() + name.substring(1);
                boolean rawStepType = true;
                if (name.startsWith("assert")) {
                    className = className.substring("assert".length());
                    rawStepType = false;
                }
                if (name.startsWith("verify")) {
                    className = className.substring("verify".length());
                    rawStepType = false;
                }
                if (name.startsWith("waitFor")) {
                    className = className.substring("waitFor".length());
                    rawStepType = false;
                }
                if (name.startsWith("store") && !name.equals("store")) {
                    className = className.substring("store".length());
                    rawStepType = false;
                }
                try {
                    Class c = Class.forName("com.sebuilder.interpreter.steptype." + className);
                    try {
                        Object o = c.newInstance();
                        if (name.startsWith("assert")) {
                            typesMap.put(name, new Assert((Getter) o));
                        } else if (name.startsWith("verify")) {
                            typesMap.put(name, new Verify((Getter) o));
                        } else if (name.startsWith("waitFor")) {
                            typesMap.put(name, new WaitFor((Getter) o));
                        } else if (name.startsWith("store") && !name.equals("store")) {
                            typesMap.put(name, new Store((Getter) o));
                        } else {
                            typesMap.put(name, (StepType) o);
                        }
                    } catch (InstantiationException ie) {
                        throw new RuntimeException(c.getName() + " could not be instantiated.", ie);
                    } catch (IllegalAccessException iae) {
                        throw new RuntimeException(c.getName() + " could not be instantiated.", iae);
                    } catch (ClassCastException cce) {
                        throw new RuntimeException(c.getName() + " does not extend "
                                + (rawStepType ? "StepType" : "Getter") + ".", cce);
                    }
                } catch (ClassNotFoundException cnfe) {
                    throw new RuntimeException("No implementation class for step type \"" + name + "\" could be found.", cnfe);
                }
            }

            return typesMap.get(name);
        } catch (Exception e) {
            throw new RuntimeException("Step type \"" + name + "\" is not implemented.", e);
        }
    }
}
