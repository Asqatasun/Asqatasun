/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.contentadapter;

import java.util.Collection;

import org.asqatasun.contentadapter.util.AdaptationActionVoter;
import org.asqatasun.entity.audit.Content;
import org.asqatasun.entity.service.parameterization.ParameterDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 *
 * @author enzolalay
 */

@Component("contentsAdapterFactory")
public class ContentsAdapterFactoryImpl implements ContentsAdapterFactory {

    @Autowired(required = false)
    @Qualifier("xmlizeVoter")
    private AdaptationActionVoter xmlizeVoter;
    @Autowired(required = false)
    @Qualifier("parseHtmlVoter")
    private AdaptationActionVoter parseHtmlVoter;
    @Autowired(required = false)
    private ParameterDataService parameterDataService;

    @PostConstruct
    public void initVoters() {
        if (parseHtmlVoter != null) {
            parseHtmlVoter.setParameterDataService(parameterDataService);
        }
        if (xmlizeVoter != null) {
            xmlizeVoter.setParameterDataService(parameterDataService);
        }
    }

    @Override
    public ContentsAdapter create(
            Collection<Content> contentList,
            String tempFolderRootPath,
            HTMLCleaner htmlCleaner,
            HTMLParser htmlParser) {
        ContentsAdapterImpl contentsAdapter = new ContentsAdapterImpl(
                contentList,
                tempFolderRootPath,
                htmlCleaner,
                htmlParser);
        if (parseHtmlVoter != null) {
            contentsAdapter.setParseHtmlVoter(parseHtmlVoter);
        }
        if (xmlizeVoter != null) {
            contentsAdapter.setXmlizeVoter(xmlizeVoter);
        }
        return contentsAdapter;
    }

}
