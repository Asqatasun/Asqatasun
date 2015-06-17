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
package org.tanaguru.service;

import java.util.Collection;
import java.util.Set;
import javax.xml.bind.annotation.XmlTransient;
import org.tanaguru.contentadapter.ContentAdapterFactory;
import org.tanaguru.contentadapter.ContentsAdapterFactory;
import org.tanaguru.contentadapter.HTMLCleanerFactory;
import org.tanaguru.contentadapter.HTMLParserFactory;
import org.tanaguru.contentadapter.util.URLIdentifierFactory;
import org.tanaguru.contentloader.DownloaderFactory;
import org.tanaguru.entity.audit.Content;
import org.tanaguru.entity.service.audit.ContentDataService;

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