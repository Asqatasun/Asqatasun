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
package org.asqatasun.sebuilder.interpreter.factory;

import com.sebuilder.interpreter.Locator;
import com.sebuilder.interpreter.Script;
import com.sebuilder.interpreter.Step;
import java.io.IOException;

import com.sebuilder.interpreter.factory.StepTypeFactory;
import com.sebuilder.interpreter.factory.TestRunFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.LoggerFactory;

/**
 * Factory to create Script objects from a string, a reader or JSONObject.
 *
 * @author jkowalczyk
 */
public class TgScriptFactory {

    StepTypeFactory stepTypeFactory = new TgStepTypeFactory();

    public Script create(TestRunFactory testRunFactory) {
        Script script = new Script();
        script.testRunFactory = testRunFactory;
        return script;
    }

    public Script parseScript(String jsonString, TestRunFactory testRunFactory) throws IOException, JSONException {
        JSONObject o = new JSONObject(new JSONTokener(jsonString));
        LoggerFactory.getLogger(this.getClass()).info("parseScript");
        try {
            if (!o.get("seleniumVersion").equals("2")) {
                throw new IOException("Unsupported Selenium version: \"" + o.get("seleniumVersion") + "\".");
            }
            if (o.getInt("formatVersion") > 2) {
                throw new IOException("Unsupported Selenium script format version: \"" + o.get("formatVersion") + "\".");
            }
            JSONArray stepsA = o.getJSONArray("steps");
            Script script = create(testRunFactory);
            for (int i = 0; i < stepsA.length(); i++) {
                JSONObject stepO = stepsA.getJSONObject(i);
                Step step = new Step(stepTypeFactory.getStepTypeOfName(stepO.getString("type")));
                step.negated = stepO.optBoolean("negated", false);
                script.steps.add(step);
                JSONArray keysA = stepO.names();
                for (int j = 0; j < keysA.length(); j++) {
                    String key = keysA.getString(j);
                    if (key.equals("type") || key.equals("negated")) {
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
        } catch (JSONException e) {
            throw new IOException("Could not parse script.", e);
        }
    }

}
