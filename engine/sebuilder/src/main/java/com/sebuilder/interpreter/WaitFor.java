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

/**
 * Generic Wait that wraps a getter.
 *
 * @author zarkonnen
 */
public class WaitFor implements StepType {

    private final Getter getter;
    public Getter getGetter() {
        return getter;
    }

    public WaitFor(Getter getter) {
        this.getter = getter;
    }

    @Override
    public boolean run(TestRun ctx) {
        int maxWaitMs = 60000; // qqDPS Eventually get this from somewhere.
        long stopBy = System.currentTimeMillis() + maxWaitMs;
        boolean result;
        // NB: If the step is negated, a result of "true"  means that we haven't succeeded yet.
        //     If the step is normal,  a result of "false" means that we haven't succeeded yet.
        while ((result = test(ctx)) == ctx.currentStep().negated && System.currentTimeMillis() < stopBy) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return result != ctx.currentStep().negated;
    }

    private boolean test(TestRun ctx) {
        String got = getter.get(ctx);
        return getter.cmpParamName() == null
                ? Boolean.parseBoolean(got)
                : ctx.string(getter.cmpParamName()).equals(got);
    }
}
