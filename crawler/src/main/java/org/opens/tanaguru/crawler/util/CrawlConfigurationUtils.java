/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.crawler.util;

import java.util.Map;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.w3c.dom.Document;

/**
 *
 * @author jkowalczyk
 */
public class CrawlConfigurationUtils {

    private static CrawlConfigurationUtils crawlConfigurationUtils = null;
    Map<String, HeritrixConfigurationModifier> paramModifierMap;
    HeritrixConfigurationModifier urlModifier;

    private CrawlConfigurationUtils() {}

    public static synchronized CrawlConfigurationUtils getInstance() {
        if (crawlConfigurationUtils == null) {
            crawlConfigurationUtils = new CrawlConfigurationUtils();
        }
        return crawlConfigurationUtils;
    }

    public Document modifyHeritrixParameter (Document document, Parameter parameter) {
        HeritrixConfigurationModifier hpf =
                paramModifierMap.get(parameter.getParameterElement().getParameterElementCode());
        return modifyValue(hpf, document, parameter.getValue());
    }

    public Document modifyValue (HeritrixConfigurationModifier hcm, Document document, String value) {
        if (hcm != null && value != null && !value.isEmpty() && !value.equals("-1")) {
            return hcm.modifyDocument(document, value);
        } else {
            return document;
        }
    }

    public void setParameterModifierMap(Map<String, HeritrixConfigurationModifier> paramModifierMap) {
        this.paramModifierMap = paramModifierMap;
    }

    public void setUrlModifier(HeritrixConfigurationModifier urlModifier) {
        this.urlModifier = urlModifier;
    }

    /**
     *
     * @return
     *      the HeritrixConfigurationModifier used to specify the Url the audit
     * is about.
     */
    public HeritrixConfigurationModifier getUrlModifier() {
        return this.urlModifier;
    }
        
}