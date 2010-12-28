/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.crawler.processor.module;

import java.util.Set;
import org.archive.modules.seeds.SeedListener;
import org.archive.modules.seeds.SeedModule;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public abstract class TanaguruSeedModule extends SeedModule {

    @Override
    @Autowired
    public void setSeedListeners(Set<SeedListener> seedListeners) {
        this.seedListeners.addAll(seedListeners);
    }

}
