/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.opens.tanaguru.service;

import java.util.Collection;
import java.util.Set;
import javax.xml.bind.annotation.XmlTransient;
import org.opens.tanaguru.contentadapter.ContentAdapterFactory;
import org.opens.tanaguru.contentadapter.ContentsAdapterFactory;
import org.opens.tanaguru.contentadapter.HTMLCleanerFactory;
import org.opens.tanaguru.contentadapter.HTMLParserFactory;
import org.opens.tanaguru.contentadapter.util.URLIdentifierFactory;
import org.opens.tanaguru.contentloader.DownloaderFactory;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.service.audit.ContentDataService;

/**
 * 
 * @author jkowalczyk
 */
@XmlTransient
public interface ContentAdapterService {// TODO Write javadoc

    /**
     * 
     * @param contentList
     * @return
     */
    Collection<Content> adaptContent(Collection<Content> contentList);

    /**
     *
     * @param contentFactory the content factory to set
     */
    void setContentFactory(ContentFactory contentFactory);

    /**
     *
     * @param writeCleanHtmlInFile
     */
    void setWriteCleanHtmlInFile(boolean writeCleanHtmlInFile);

    /**
     * 
     * @param tempFolderRootPath
     */
    void setTempFolderRootPath(String tempFolderRootPath);

    /**
     *
     * @param contentsAdapterFactory
     */
    void setContentsAdapterFactory(ContentsAdapterFactory contentsAdapterFactory);

    /**
     *
     * @param htmlCleanerFactory
     */
    void setHtmlCleanerFactory(HTMLCleanerFactory htmlCleanerFactory);

    /**
     *
     * @param htmlParserFactory
     */
    void setHtmlParserFactory(HTMLParserFactory htmlParserFactory);

    /**
     *
     * @param contentAdapterFactorySet
     */
    void setContentAdapterFactorySet(Set<ContentAdapterFactory> contentAdapterFactorySet);

    /**
     *
     * @param urlIdentifierFactory
     */
    void setUrlIdentifierFactory(URLIdentifierFactory urlIdentifierFactory);

    /**
     *
     * @param downloaderFactory
     */
    void setDownloaderFactory(DownloaderFactory downloaderFactory);

    /**
     *
     * @param contentDataService
     */
    void setContentDataService(ContentDataService contentDataService);
    
}