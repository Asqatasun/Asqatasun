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

import org.apache.commons.httpclient.HttpConnection;
import org.apache.log4j.Logger;
import org.archive.httpclient.HttpRecorderMethod;
import org.archive.modules.CrawlURI;
import org.archive.modules.fetcher.FetchHTTP;
import org.opens.tanaguru.crawler.frontier.TanaguruBdbFrontier;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jkowalczyk
 */
public class TanaguruFetchHTTP extends FetchHTTP{

    private static final long serialVersionUID = 5284296477962226238L;

    private TanaguruBdbFrontier frontier;
    @Autowired
    public void setFrontier(TanaguruBdbFrontier frontier) {
        this.frontier = frontier;
    }
    
    @Override
    public int getHttpProxyPort() {
        return super.getHttpProxyPort();
    }

    @Override
    public void setHttpProxyPort(int host) {
        super.setHttpProxyPort(host);
    }

    /**
     * 
     */
    public TanaguruFetchHTTP() {
        super();
    }

    @Override
    protected boolean checkMidfetchAbort(CrawlURI curi, HttpRecorderMethod method, HttpConnection conn) {
        boolean checkMidfetchAbort= super.checkMidfetchAbort(curi, method, conn);
        Logger.getLogger(this.getClass()).debug("curi.isPrerequisite() "+curi.isPrerequisite());
        Logger.getLogger(this.getClass()).debug("curi.getContentType() "+curi.getContentType());
        Logger.getLogger(this.getClass()).debug("checkMidfetchAbort "+checkMidfetchAbort);
        // the counter is incremented when the curi is seen as success
        if (checkMidfetchAbort && curi.isSuccess()) {
            frontier.decrementSucceededFetchCounter();
            Logger.getLogger(this.getClass()).debug("succeeded Fetch Counter decremented due to MidfetchAbort");
        }
        return checkMidfetchAbort;
    }
    
}
