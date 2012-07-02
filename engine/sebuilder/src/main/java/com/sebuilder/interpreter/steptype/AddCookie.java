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
import java.util.Date;
import org.openqa.selenium.Cookie;

public class AddCookie implements StepType {
	@Override
	public boolean run(TestRun ctx) {
		Cookie.Builder cb = new Cookie.Builder(ctx.string("name"), ctx.string("value"));
		for (String opt : ctx.string("options").split(",")) {
			String[] kv = opt.split("=", 2);
			if (kv.length == 1) { continue; }
			if (kv[0].trim().equals("path")) {
				cb.path(kv[1].trim());
			}
			if (kv[0].trim().equals("max_age")) {
				cb.expiresOn(new Date(new Date().getTime() + Long.parseLong(kv[1].trim()) * 1000l));
			}
			ctx.driver().manage().addCookie(cb.build());
		}
		ctx.driver().navigate().refresh();
		return true;
	}
}
