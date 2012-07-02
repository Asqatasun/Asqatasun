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
 * Interface to plug into the generic Assert/Verify/Store steps that does the
 * work of actually getting the relevant variable.
 *
 * @author zarkonnen
 */
public interface Getter {

    /**
     * @param ctx Current test run.
     * @return The value this getter gets, eg the page title.
     */
    public String get(TestRun ctx);

    /**
     * @return The name of the parameter to compare the result of the get to, or
     * null if the get returns a boolean "true"/"false".
     */
    public String cmpParamName();
}
