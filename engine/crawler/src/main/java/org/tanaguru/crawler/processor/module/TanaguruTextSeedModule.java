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
package org.tanaguru.crawler.processor.module;

import java.io.*;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.io.IOUtils;
import org.archive.io.ReadSource;
import org.archive.modules.CrawlURI;
import org.archive.modules.SchedulingConstants;
import org.archive.net.UURI;
import org.archive.net.UURIFactory;
import org.archive.spring.WriteTarget;
import org.archive.util.DevUtils;
import org.archive.util.iterator.LineReadingIterator;
import org.archive.util.iterator.RegexLineIterator;
import org.springframework.beans.factory.annotation.Required;

/**
 *
 * @author jkowalczyk
 */
public class TanaguruTextSeedModule extends TanaguruSeedModule implements ReadSource {

    private static final long serialVersionUID = 3L;

    private static final Logger logger =
        Logger.getLogger(TanaguruTextSeedModule.class.getName());

    /**
     * Text from which to extract seeds
     */
    protected ReadSource textSource = null;
    public ReadSource getTextSource() {
        return textSource;
    }
    @Required
    public void setTextSource(ReadSource seedsSource) {
        this.textSource = seedsSource;
    }

    public TanaguruTextSeedModule() {
    }

    /**
     * Announce all seeds from configured source to SeedListeners
     * (including nonseed lines mixed in).
     * @see org.archive.modules.seeds.SeedModule#announceSeeds()
     */
    @Override
    public void announceSeeds() {
        BufferedReader reader = new BufferedReader(textSource.obtainReader());
        try {
            announceSeedsFromReader(reader);
        } finally {
            IOUtils.closeQuietly(reader);
        }
    }

    /**
     * Announce all seeds (and nonseed possible-directive lines) from
     * the given Reader
     * @param reader
     */
    protected void announceSeedsFromReader(BufferedReader reader) {
        String s;
        Iterator<String> iter =
            new RegexLineIterator(
                    new LineReadingIterator(reader),
                    RegexLineIterator.COMMENT_LINE,
                    RegexLineIterator.NONWHITESPACE_ENTRY_TRAILING_COMMENT,
                    RegexLineIterator.ENTRY);

        while (iter.hasNext()) {
            s = iter.next();
            if(Character.isLetterOrDigit(s.charAt(0))) {
                // consider a likely URI
                seedLine(s);
            } else {
                // report just in case it's a useful directive
                nonseedLine(s);
            }
        }
        publishConcludedSeedBatch();
    }

    /**
     * Handle a read line that is probably a seed.
     *
     * @param uri String seed-containing line
     */
    protected void seedLine(String uri) {
        if (!uri.matches("[a-zA-Z][\\w+\\-]+:.*")) { // Rfc2396 s3.1 scheme,
                                                     // minus '.'
            // Does not begin with scheme, so try http://
            uri = "http://" + uri;
        }
        try {
            UURI uuri = UURIFactory.getInstance(uri);
            CrawlURI curi = new CrawlURI(uuri);
            curi.setSeed(true);
            curi.setSchedulingDirective(SchedulingConstants.MEDIUM);
            if (getSourceTagSeeds()) {
                curi.setSourceTag(curi.toString());
            }
            publishAddedSeed(curi);
        } catch (URIException e) {
            // try as nonseed line as fallback
            nonseedLine(uri);
        }
    }

    /**
     * Handle a read line that is not a seed, but may still have
     * meaning to seed-consumers (such as scoping beans).
     *
     * @param line String seed-containing line
     */
    protected void nonseedLine(String line) {
        publishNonSeedLine(line);
    }

    /**
     * Treat the given file as a source of additional seeds,
     * announcing to SeedListeners.
     *
     * @see org.archive.modules.seeds.SeedModule#actOn(java.io.File)
     */
    @Override
    public void actOn(File f) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(f));
            announceSeedsFromReader(reader);
        } catch(FileNotFoundException fnf) {
            logger.log(Level.SEVERE,"seed file source not found",fnf);
        } finally {
            IOUtils.closeQuietly(reader);
        }
    }

    /**
     * Add a new seed to scope. By default, simply appends
     * to seeds file, though subclasses may handle differently.
     *
     * <p>This method is *not* sufficient to get the new seed
     * scheduled in the Frontier for crawling -- it only
     * affects the Scope's seed record (and decisions which
     * flow from seeds).
     *
     * @param curi CandidateUri to add
     */
    @Override
    public synchronized void addSeed(final CrawlURI curi) {
        if(!(textSource instanceof WriteTarget)) {
            // TODO: do something else to log seed update
            logger.log(Level.WARNING, "nowhere to log added seed: {0}", curi);
        } else {
            // TODO: determine if this modification to seeds file means
            // TextSeedModule should (again) be Checkpointable
            try {
                Writer fw = ((WriteTarget)textSource).obtainWriter(true);
                // Write to new (last) line the URL.
                fw.write("\n");
                fw.write("# Heritrix added seed " +
                    ((curi.getVia() != null) ? "redirect from " + curi.getVia():
                        "(JMX)") + ".\n");
                fw.write(curi.toString());
                fw.flush();
                fw.close();
            } catch (IOException e) {
                DevUtils.warnHandle(e, "problem writing new seed");
            }
        }
        publishAddedSeed(curi);
    }

    @Override
    public Reader obtainReader() {
        return textSource.obtainReader();
    }

}