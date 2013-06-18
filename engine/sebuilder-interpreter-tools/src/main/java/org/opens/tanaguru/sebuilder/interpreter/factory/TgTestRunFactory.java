/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2013  Open-S Company
 * 
 *  This file is part of Tanaguru.
 * 
 *  Tanaguru is free software: you can redistribute it and/or modify
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
 *  Contact us by mail: open-s AT open-s DOT com
 */

package org.opens.tanaguru.sebuilder.interpreter.factory;

import com.sebuilder.interpreter.Script;
import com.sebuilder.interpreter.TestRun;
import com.sebuilder.interpreter.factory.TestRunFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.opens.tanaguru.sebuilder.interpreter.NewPageListener;
import org.opens.tanaguru.sebuilder.interpreter.TgTestRun;
import org.opens.tanaguru.sebuilder.tools.FirefoxDriverObjectPool;

/**
 *
 * @author jkowalczyk
 */
public class TgTestRunFactory extends TestRunFactory {

    private Map<String, String> jsScriptMap;
    public Map<String, String> getJsScriptMap() {
        return jsScriptMap;
    }
    public void setJsScriptMap(Map<String, String> jsScriptMap) {
        this.jsScriptMap = jsScriptMap;
    }
    
    private Collection<NewPageListener> newPageListeners;
    public void addNewPageListener(NewPageListener newPageListener) {
        if (newPageListeners == null) {
            newPageListeners = new ArrayList<NewPageListener>();
        }
        this.newPageListeners.add(newPageListener);
    }

    public void addNewPageListeners(Collection<NewPageListener> newPageListeners) {
        if (this.newPageListeners == null) {
            this.newPageListeners = new ArrayList<NewPageListener>();
        }
        this.newPageListeners.addAll(newPageListeners);
    }

    public void removeNewPageListener(NewPageListener newPageListener) {
        if (newPageListeners != null) {
            this.newPageListeners.remove(newPageListener);
        }
    }

    public void removeNewPageListeners(Collection<NewPageListener> newPageListeners) {
        if (newPageListeners != null) {
            this.newPageListeners.removeAll(newPageListeners);
        }
    }

    private FirefoxDriverObjectPool fdop;
    public void setFirefoxDriverObjectPool(FirefoxDriverObjectPool fdop) {
        this.fdop = fdop;
    }

    @Override
    public TestRun createInstance(Script script) {
        TgTestRun testRun = new TgTestRun(script);
        testRun.addNewPageListeners(newPageListeners);
        testRun.setJsScriptMap(jsScriptMap);
//        testRun.setFirefoxDriverObjectPool(fdop);
        return testRun;
    }
    
}