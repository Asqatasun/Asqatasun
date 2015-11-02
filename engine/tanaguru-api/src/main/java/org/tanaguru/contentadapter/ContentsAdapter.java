/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
package org.tanaguru.contentadapter;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.tanaguru.entity.audit.Content;

/**
 * 
 * @author jkowalczyk
 */
public interface ContentsAdapter {

    /**
     *
     * @return the result
     */
    Collection<Content> getResult();

    /**
     *
     */
    void run();

    /**
     *
     * @param contentAdapterSet
     */
    void setContentAdapterSet(Set<ContentAdapter> contentAdapterSet);

    /**
     *
     * @param contentList
     */
    void setContentList(List<Content> contentList);

    /**
     *
     * @param htmlCleaner
     */
    void setHTMLCleaner(HTMLCleaner htmlCleaner);

    /**
     * 
     * @param htmlParser
     */
    void setHTMLParser(HTMLParser htmlParser);

    /**
     *
     * @param writeCleanHtmlInFile
     */
    void setWriteCleanHtmlInFile(Boolean writeCleanHtmlInFile);
    
}