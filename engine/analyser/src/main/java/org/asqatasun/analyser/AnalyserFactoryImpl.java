/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
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
package org.asqatasun.analyser;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.asqatasun.entity.audit.Audit;
import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.entity.parameterization.ParameterFamily;
import org.asqatasun.entity.service.audit.AuditDataService;
import org.asqatasun.entity.service.audit.ProcessResultDataService;
import org.asqatasun.entity.service.parameterization.ParameterDataService;
import org.asqatasun.entity.service.parameterization.ParameterFamilyDataService;
import org.asqatasun.entity.service.statistics.CriterionStatisticsDataService;
import org.asqatasun.entity.service.statistics.TestStatisticsDataService;
import org.asqatasun.entity.service.statistics.ThemeStatisticsDataService;
import org.asqatasun.entity.service.statistics.WebResourceStatisticsDataService;
import org.asqatasun.entity.service.subject.WebResourceDataService;
import org.asqatasun.entity.subject.Site;
import org.asqatasun.entity.subject.WebResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author enzolalay
 */
@Component(value = "analyserFactory")
public class AnalyserFactoryImpl implements AnalyserFactory {// TODO Write javadoc

    private final WebResourceStatisticsDataService webResourceStatisticsDataService;
    private final WebResourceDataService webResourceDataService;
    private final ThemeStatisticsDataService themeStatisticsDataService;
    private final TestStatisticsDataService testStatisticsDataService;
    private final CriterionStatisticsDataService criterionStatisticsDataService;
    private final AuditDataService auditDataService;
    private final ParameterDataService parameterDataService;
    private final ParameterFamilyDataService parameterFamilyDataService;
    private final ProcessResultDataService processResultDataService;

    private Collection<ParameterFamily> testWeightParameterFamilySet ;
    private List<String> testWeightParameterFamilyCodeList = Collections.emptyList();

    public void setTestWeightParameterFamilyCodeList(List<String> testWeightParameterFamilyCodeList) {
        this.testWeightParameterFamilyCodeList = testWeightParameterFamilyCodeList;
    }



    @Autowired
    public AnalyserFactoryImpl(
            AuditDataService auditDataService,
            WebResourceDataService webResourceDataService,
            TestStatisticsDataService testStatisticsDataService,
            ThemeStatisticsDataService themeStatisticsDataService,
            WebResourceStatisticsDataService webResourceStatisticsDataService, 
            CriterionStatisticsDataService criterionStatisticsDataService, 
            ParameterDataService parameterDataService,
            ParameterFamilyDataService parameterFamilyDataService,
            ProcessResultDataService processResultDataService) {
        this.auditDataService = auditDataService;
        this.webResourceDataService = webResourceDataService;
        this.testStatisticsDataService = testStatisticsDataService;
        this.themeStatisticsDataService = themeStatisticsDataService;
        this.webResourceStatisticsDataService = webResourceStatisticsDataService;
        this.criterionStatisticsDataService = criterionStatisticsDataService;
        this.parameterDataService = parameterDataService;
        this.parameterFamilyDataService = parameterFamilyDataService;
        this.processResultDataService = processResultDataService;
    }

    @Override
    public Analyser create(WebResource webResource, Audit audit) {
        int nbOfWebResource = 1;
        if (webResource instanceof Site) {
            nbOfWebResource = 
                    webResourceDataService.getNumberOfChildWebResource(webResource).intValue();
        }
        Analyser analyser = new AnalyserImpl(
                auditDataService,
                testStatisticsDataService,
                themeStatisticsDataService,
                webResourceStatisticsDataService,
                criterionStatisticsDataService,
                processResultDataService,
                webResource,
                getTestWeightParamSet(audit), 
                nbOfWebResource);
        return analyser;
    }
    
    /**
     * 
     * @param audit
     * @return 
     * 
     *  the collection of test weight parameters for the given audit
     */
    private Collection<Parameter> getTestWeightParamSet(Audit audit) {
        if (testWeightParameterFamilySet == null) {
            testWeightParameterFamilySet = new HashSet<ParameterFamily>();
            for (String paramFamilyCode : testWeightParameterFamilyCodeList) {
                testWeightParameterFamilySet.add(parameterFamilyDataService.getParameterFamily(paramFamilyCode));
            }
        }
        Collection<Parameter> testWeightParamSet = new HashSet<Parameter>();
        for (ParameterFamily pf : testWeightParameterFamilySet) {
                testWeightParamSet.addAll(parameterDataService.getParameterSet(pf, audit));
        }
        return testWeightParamSet;
    }
    
}
