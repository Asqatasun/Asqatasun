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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.contentloader;

import java.util.Map;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.util.factory.DateFactory;

/**
 *
 * @author enzolalay
 */
public interface ContentLoaderFactory {

    /**
     * 
     * @param contentFactory
     * @param downloader
     * @param dateFactory
     * @param fileMap
     * @return
     *      an instance of ContentLoader
     */
    ContentLoader create(
            ContentFactory contentFactory,
            Downloader downloader,
            DateFactory dateFactory,
            Map<String, String> fileMap);

}