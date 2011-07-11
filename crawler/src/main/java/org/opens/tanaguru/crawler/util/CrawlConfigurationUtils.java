/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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