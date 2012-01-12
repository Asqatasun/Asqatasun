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
package org.opens.tanaguru.crawler.framework;

import org.archive.modules.fetcher.FetchHTTP;

/**
 * @author jkowalczyk
 */
public class TanaguruFetchHTTP extends FetchHTTP{

    private static final long serialVersionUID = 5284296477962226238L;

    @Override
    public int getHttpProxyPort() {
        return super.getHttpProxyPort();
    }

    public void setHttpProxyPort(int host) {
        super.setHttpProxyPort(host);
    }

    /**
     * 
     * @param url
     * @param heritrixFileName
     * @param outputDir
     * @param crawlConfigFilePath
     * @param paramSet
     */
    public TanaguruFetchHTTP() {
        super();
    }   

}