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
package org.opens.tanaguru.contentadapter;

import java.util.List;
import org.opens.tanaguru.entity.audit.Content;

/**
 *
 * @author enzolalay
 */
public class ContentsAdapterFactoryImpl implements ContentsAdapterFactory {

    public ContentsAdapterFactoryImpl() {
        super();
    }

    @Override
    public ContentsAdapter create(
            List<Content> contentList,
            boolean writeCleanHtmlInFile,
            String tempFolderRootPath,
            HTMLCleaner htmlCleaner,
            HTMLParser htmlParser) {
        return new ContentsAdapterImpl(
                contentList,
                writeCleanHtmlInFile,
                tempFolderRootPath,
                htmlCleaner,
                htmlParser);
    }

    @Override
    public ContentsAdapter create(
            List<Content> contentList, 
            boolean writeCleanHtmlInFile, 
            String tempFolderRootPath, 
            HTMLCleaner htmlCleaner, 
            HTMLParser htmlParser, 
            String ref) {
        
        // While accessiweb 2.1 rules are not based on jsoup, some specific 
        // treatment has to be done on the DOM : 
        // remove the doctype and set tags to upper case
        
        boolean removeDoctype = false;
        if (ref.equals("AW21")) {
            removeDoctype = true;
        }
        return new ContentsAdapterImpl(
                contentList,
                writeCleanHtmlInFile,
                tempFolderRootPath,
                htmlCleaner,
                htmlParser, 
                removeDoctype);
    }

}