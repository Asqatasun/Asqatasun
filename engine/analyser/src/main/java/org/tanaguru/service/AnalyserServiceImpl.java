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

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.tanaguru.analyser.Analyser;
import org.tanaguru.analyser.AnalyserFactory;
import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.subject.WebResource;

/**
 * 
 * @author jkowalczyk
 */
public class AnalyserServiceImpl implements AnalyserService {

    private Set<AnalyserFactory> analyserFactorySet = new HashSet<AnalyserFactory>();

    public AnalyserServiceImpl() {
        super();
    }

    @Override
    @Deprecated
    public float analyse(List<ProcessResult> netResultList) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @Deprecated
    public void setAnalyser(Analyser analyser) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void analyse(WebResource webResource, Audit audit) {
        for (AnalyserFactory analyserFactory : analyserFactorySet) {
            Analyser analyser = analyserFactory.create(webResource, audit);
            analyser.run();
        }
    }

    @Override
    public void addAnalyserFactory(AnalyserFactory analyserFactory) {
        analyserFactorySet.add(analyserFactory);
    }

    @Override
    public void setAnalyserFactory(AnalyserFactory af) {
        analyserFactorySet.add(af);
    }

}