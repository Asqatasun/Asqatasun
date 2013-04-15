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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 * A Selenium 2 script. To create and run a test, instantiate a Script object,
 * add some Script.Steps to its steps, then invoke "run". If you want to be able
 * to run the script step by step, invoke "start", which will return a TestRun
 * object.
 *
 * @author zarkonnen
 */
public class Script {

    private ArrayList<Step> steps = new ArrayList<Step>();
    public ArrayList<Step> getSteps() {
        return steps;
    }
    public void addStep(Step step) {
        steps.add(step);
    }
    
    private FirefoxProfile firefoxProfile = new FirefoxProfile();
    public void setFirefoxProfile(FirefoxProfile firefoxProfile) {
        this.firefoxProfile = firefoxProfile;
    }
    
    Collection<NewPageListener> newPageListeners;
    public void addNewPageListener (NewPageListener newPageListener) {
        if (newPageListeners == null) {
            newPageListeners = new ArrayList<NewPageListener>();
        }
        this.newPageListeners.add(newPageListener);
    }
    
    public void addNewPageListeners (Collection<NewPageListener> newPageListeners) {
        if (newPageListeners == null) {
            newPageListeners = new ArrayList<NewPageListener>();
        }
        this.newPageListeners.addAll(newPageListeners);
    }
    
    public void removeNewPageListener (NewPageListener newPageListener) {
        if (newPageListeners != null) {
            this.newPageListeners.remove(newPageListener);
        }
    }
    
    public void removeNewPageListeners (Collection<NewPageListener> newPageListeners) {
        if (newPageListeners != null) {
            this.newPageListeners.removeAll(newPageListeners);
        }
    }
    
    /**
     * @return A TestRun object that can be iterated to run the script step by
     * step.
     */
    public TestRun start() {
        TestRun testRun = new TestRun(this, firefoxProfile);
        testRun.addNewPageListeners(newPageListeners);
        return testRun;
    }

    /**
     * Runs the script.
     *
     * @return Whether the run succeeded or failed.
     * @throws RuntimeException If the script encountered a problem, including a
     * failed Assertion or timed-out Wait.
     */
    public boolean run() {
        boolean result = start().finish();
        return result;
    }

    @Override
    public String toString() {
        try {
            return toJSON().toString(4);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject o = new JSONObject();
        o.put("seleniumVersion", "2");
        o.put("formatVersion", 1);
        JSONArray stepsA = new JSONArray();
        for (Step s : steps) {
            stepsA.put(s.toJSON());
        }
        return o;
    }

    /**
     * A Selenium 2 step.
     */
    public static class Step {

        /**
         * Whether the step is negated. Only relevant for Assert/Verify/WaitFor
         * steps.
         */
        boolean negated;

        public Step(StepType type) {
            this.type = type;
        }
        StepType type;
        HashMap<String, String> stringParams = new HashMap<String, String>();
        HashMap<String, Locator> locatorParams = new HashMap<String, Locator>();

        boolean isNegated() {
            return negated;
        }

        @Override
        public String toString() {
            try {
                return toJSON().toString();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

        public JSONObject toJSON() throws JSONException {
            JSONObject o = new JSONObject();
            if (type instanceof Assert) {
                o.put("type", "assert" + ((Assert) type).getGetter().getClass().getSimpleName());
            }
            if (type instanceof Verify) {
                o.put("type", "verify" + ((Verify) type).getGetter().getClass().getSimpleName());
            }
            if (type instanceof WaitFor) {
                o.put("type", "waitFor" + ((WaitFor) type).getGetter().getClass().getSimpleName());
            }
            if (type instanceof Store) {
                o.put("type", "store" + ((Store) type).getGetter().getClass().getSimpleName());
            }
            o.put("negated", negated);
            for (Map.Entry<String, String> pe : stringParams.entrySet()) {
                o.put(pe.getKey(), pe.getValue());
            }
            for (Map.Entry<String, Locator> le : locatorParams.entrySet()) {
                o.put(le.getKey(), le.getValue().toJSON());
            }

            return o;
        }
    }
    
}