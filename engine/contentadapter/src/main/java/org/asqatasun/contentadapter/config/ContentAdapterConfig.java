package org.asqatasun.contentadapter.config;

import org.asqatasun.contentadapter.util.AdaptationActionVoter;
import org.asqatasun.contentadapter.util.AdaptationActionVoterImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Created by meskoj on 14/05/16.
 */
@Configuration
public class ContentAdapterConfig {

    @Value("${refToXmlize:AW21}")
    private String refToXmlize;

    @Value("${refToParseHtml:AW21;AW22;RGAA22}")
    private String refToParseHtml;

    @Bean(name = "xmlizeVoter")
    public AdaptationActionVoter xmlizerConfigurer() {
        return initializeVoter(refToXmlize.split(";"));
    }

    @Bean(name = "parseHtmlVoter")
    public AdaptationActionVoter refToParseHtmlConfigurer() {
        return initializeVoter(refToParseHtml.split(";"));
    }

    private AdaptationActionVoter initializeVoter(String[] dataToSet) {
        AdaptationActionVoterImpl actionVoter = new AdaptationActionVoterImpl();
        actionVoter.setAuthorizedValues(Arrays.asList(dataToSet));
        return actionVoter;
    }

}
