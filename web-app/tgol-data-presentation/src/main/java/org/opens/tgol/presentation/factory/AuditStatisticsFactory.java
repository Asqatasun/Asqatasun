/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2014  Open-S Company
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
package org.opens.tgol.presentation.factory;

import java.util.*;
import org.apache.commons.lang3.StringUtils;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.entity.service.audit.AuditDataService;
import org.opens.tanaguru.entity.service.parameterization.ParameterDataService;
import org.opens.tanaguru.entity.service.reference.ThemeDataService;
import org.opens.tanaguru.entity.service.statistics.CriterionStatisticsDataService;
import org.opens.tanaguru.entity.statistics.CriterionStatistics;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tgol.entity.decorator.tanaguru.subject.WebResourceDataServiceDecorator;
import org.opens.tgol.entity.service.contract.ActDataService;
import org.opens.tgol.presentation.data.AuditStatistics;
import org.opens.tgol.presentation.data.AuditStatisticsImpl;
import org.opens.tgol.presentation.data.ResultCounter;
import org.opens.tgol.util.HttpStatusCodeFamily;
import org.opens.tgol.util.TgolKeyStore;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class AuditStatisticsFactory {

    private ActDataService actDataService;
    @Autowired
    public void setActDataService(ActDataService actDataService) {
        this.actDataService = actDataService;
    }
    
    private WebResourceDataServiceDecorator webResourceDataService;
    @Autowired
    public void setWebResourceDataService(WebResourceDataServiceDecorator webResourceDataServiceDecorator) {
        this.webResourceDataService = webResourceDataServiceDecorator;
    }
    
    private AuditDataService auditDataService;
    @Autowired
    public void setAuditDataService(AuditDataService auditDataService) {
        this.auditDataService = auditDataService;
    }
    
    private ParameterDataService parameterDataService;
    @Autowired
    public void setParameterDataService(ParameterDataService parameterDataService) {
        this.parameterDataService = parameterDataService;
    }
    
    private CriterionStatisticsDataService criterionStatisticsDataService;
    @Autowired
    public void setCriterionStatisticsDataService(CriterionStatisticsDataService criterionStatisticsDataService) {
        this.criterionStatisticsDataService = criterionStatisticsDataService;
    }

    private Map<String, Collection<Theme>> fullThemeMapByRef = null;
    @Autowired
    public final void setThemeDataService(ThemeDataService themeDataService) {
        Collection<Theme> themeList = themeDataService.findAll();
        if (fullThemeMapByRef == null) {
            fullThemeMapByRef = new HashMap();
        }
        // we retrieve the theme from the criterion. To display a theme, it has
        // to be associated with a criterion
        for (Theme theme : themeList) {
            if (!theme.getCriterionList().isEmpty()) {
                String referenceCode = theme.getCriterionList().iterator().next().getReference().getCode();
                if (!fullThemeMapByRef.containsKey(referenceCode)) {
                    Collection<Theme> themeListByRef = new ArrayList();
                    themeListByRef.add(theme);
                    fullThemeMapByRef.put(referenceCode, themeListByRef);
                } else {
                    fullThemeMapByRef.get(referenceCode).add(theme);
                }
            }
        }
        for (Collection<Theme> entry : fullThemeMapByRef.values()) {
            sortThemeList(entry);
        }
    }

    /**
     * The unique shared instance of ContractInfoFactory
     */
    private static AuditStatisticsFactory auditStatisticsFactory;

    /**
     * Default private constructor
     */
    protected AuditStatisticsFactory() {
    }

    public static synchronized AuditStatisticsFactory getInstance() {
        if (auditStatisticsFactory == null) {
            auditStatisticsFactory = new AuditStatisticsFactory();
        }
        return auditStatisticsFactory;
    }

    /**
     * This methods creates an instance of auditStatistics (audit meta-data)
     * with the URL, the date, the number of components (in case of site audit )
     * and the mark of the audit
     *
     * @param webResource
     * @param parametersToDisplay
     * @param displayScope
     * @param isAuditManual
     * @return
     */
    public AuditStatistics getAuditStatistics(
            WebResource webResource,
            Map<String, String> parametersToDisplay,
            String displayScope,
            boolean isAuditManual) {

        AuditStatistics auditStats = new AuditStatisticsImpl();

        auditStats.setUrl(webResource.getURL());
        auditStats.setSnapshotUrl(webResource.getURL());
        auditStats.setRawMark(markFormatter(webResource, true, isAuditManual));
        auditStats.setWeightedMark(markFormatter(webResource, false, isAuditManual));

        Audit audit;
        if (webResource instanceof Site) {
            auditStats.setPageCounter(webResourceDataService.getChildWebResourceCount(webResource).intValue());
            audit = webResource.getAudit();
            auditStats.setAuditedPageCounter(webResourceDataService.getWebResourceCountByAuditAndHttpStatusCode(
                    audit.getId(),
                    HttpStatusCodeFamily.f2xx,
                    null,
                    null).intValue());
        } else if (webResource.getParent() != null) {
            audit = webResource.getParent().getAudit();
        } else {
            audit = webResource.getAudit();
        }

        auditStats.setDate(audit.getDateOfCreation());
        auditStats.setAuditScope(
                actDataService.getActFromAudit(audit).getScope().getCode());

        ResultCounter resultCounter = auditStats.getResultCounter();

        resultCounter.setPassedCount(0);
        resultCounter.setFailedCount(0);
        resultCounter.setNmiCount(0);
        resultCounter.setNaCount(0);
        resultCounter.setNtCount(0);

        auditStats.setCounterByThemeMap(addCounterByThemeMap(audit, webResource, resultCounter, displayScope, isAuditManual));
        auditStats.setParametersMap(getAuditParameters(audit, parametersToDisplay));
        return auditStats;
    }

    /**
     * Returns the theme for a given id The collection of themes is loaded on
     * the application startup to avoid multiple accesses to the database.
     *
     * @param id
     * @return
     */
    public Theme getTheme(Long id) {
        for (Collection<Theme> entry : fullThemeMapByRef.values()) {
            for (Theme theme : entry) {
                if (theme.getId().equals(id)) {
                    return theme;
                }
            }
        }
        return null;
    }

    /**
     * Retrieves all the parameters of the audit and prepares the data depending
     * on the wished displayed parameters of the view.
     *
     *
     * @param audit
     * @param parametersToDisplay
     * @return
     */
    private Map<String, String> getAuditParameters(
            Audit audit,
            Map<String, String> parametersToDisplay) {
        Map<String, String> auditParameters = new LinkedHashMap();
        Set<Parameter> auditParamSet = 
                parameterDataService.getParameterSetFromAudit(audit);
        // to ensure compatibility with audit that have been launched before
        // the parameter management has been integrated
        if (auditParamSet.isEmpty()) {
            return auditParameters;
        }
        for (Map.Entry<String, String> entry : parametersToDisplay.entrySet()) {
            for (Parameter param : auditParamSet) {
                if (entry.getKey().equals(param.getParameterElement().getParameterElementCode())) {
                    auditParameters.put(
                            entry.getValue(),
                            param.getValue());
                }
            }
        }
        if (!parametersToDisplay.isEmpty()) {
            auditParameters.put(TgolKeyStore.REFERENTIAL_PARAM_KEY,
                    parameterDataService.getReferentialKeyFromAudit(audit));
        }
        return auditParameters;
    }

    /**
     * This methods converts the mark of the audit as a string
     *
     * @param webresource
     * @param isRawMark
     * @return
     */
    private String markFormatter(
            WebResource webresource,
            boolean isRawMark,
            boolean isManual) {
        Float mark = webResourceDataService.getMarkByWebResourceAndAudit(webresource, isRawMark, isManual);
        if (mark == -1) {
            return "0";
        }
        return String.valueOf(mark.intValue());
    }

    /**
     * Retrieve the number of results by type for each themes of the audit and
     * populate a map with the Theme as key and a ResultCounter instance as
     * value.
     *
     * @param audit
     * @param webResource
     * @param globalResultCounter
     * @param displayScope
     * @param isAuditManual
     * @return
     */
    private Map<Theme, ResultCounter> addCounterByThemeMap(
            Audit audit,
            WebResource webResource,
            ResultCounter globalResultCounter,
            String displayScope,
            boolean isAuditManual) {
        Map<Theme, ResultCounter> counterByThemeMap = new LinkedHashMap();

        for (Theme theme : getThemeListFromAudit(audit)) {

            ResultCounter themeResultCounter = null;
            if (StringUtils.equalsIgnoreCase(displayScope, TgolKeyStore.TEST_DISPLAY_SCOPE_VALUE)) {
                themeResultCounter = getResultCounterByThemeForTest(webResource, audit, theme, isAuditManual);
            } else if (StringUtils.equalsIgnoreCase(displayScope, TgolKeyStore.CRITERION_DISPLAY_SCOPE_VALUE)) {
                themeResultCounter = getResultCounterByThemeForCriterion(webResource, theme);
            }
            if (themeResultCounter != null) {
                globalResultCounter.setPassedCount(themeResultCounter.getPassedCount() + globalResultCounter.getPassedCount());
                globalResultCounter.setFailedCount(themeResultCounter.getFailedCount() + globalResultCounter.getFailedCount());
                globalResultCounter.setNmiCount(themeResultCounter.getNmiCount() + globalResultCounter.getNmiCount());
                globalResultCounter.setNaCount(themeResultCounter.getNaCount() + globalResultCounter.getNaCount());
                globalResultCounter.setNtCount(themeResultCounter.getNtCount() + globalResultCounter.getNtCount());
                counterByThemeMap.put(theme, themeResultCounter);
            }
        }
        // in case of initial manual audit, the sum of passed, failed, nmi and na
        // must be equals to 0. In this case, the count of the nt must be 
        // set to the total number of test. Could be replace by initiliasing 
        // the manual statistics input when creating the audit.
        if (isAuditManual && 
                globalResultCounter.getPassedCount() == 0 && 
                globalResultCounter.getNaCount() == 0 && 
                globalResultCounter.getNmiCount() == 0 && 
                globalResultCounter.getFailedCount() == 0 ) {
            Collection<Test> testList = auditDataService.getAuditWithTest(audit.getId()).getTestList();
            globalResultCounter.setNtCount(testList.size());
            Theme theme;
            for (Test test : testList) {
                theme = test.getCriterion().getTheme();
                if (counterByThemeMap.containsKey(theme)) {
                    counterByThemeMap.get(theme).setNtCount(counterByThemeMap.get(theme).getNtCount()+1);
                }
            }
        }
        return counterByThemeMap;
    }

    /**
     *
     * @param webResource
     * @param audit
     * @param theme
     * @return
     */
    private ResultCounter getResultCounterByThemeForTest(
            WebResource webResource,
            Audit audit,
            Theme theme,
            boolean manualAudit) {
        ResultCounter resultCounter
                = ResultCounterFactory.getInstance().getResultCounter();
        resultCounter.setPassedCount(webResourceDataService.getResultCountByResultTypeAndTheme(
                webResource, audit, TestSolution.PASSED, theme, manualAudit).intValue());
        resultCounter.setFailedCount(webResourceDataService.getResultCountByResultTypeAndTheme(
                webResource, audit, TestSolution.FAILED, theme, manualAudit).intValue());
        resultCounter.setNmiCount(webResourceDataService.getResultCountByResultTypeAndTheme(
                webResource, audit, TestSolution.NEED_MORE_INFO, theme, manualAudit).intValue());
        resultCounter.setNaCount(webResourceDataService.getResultCountByResultTypeAndTheme(
                webResource, audit, TestSolution.NOT_APPLICABLE, theme, manualAudit).intValue());
        resultCounter.setNtCount(webResourceDataService.getResultCountByResultTypeAndTheme(
                webResource, audit, TestSolution.NOT_TESTED, theme, manualAudit).intValue());
        return resultCounter;
    }

    /**
     *
     * @param webResource
     * @param theme
     * @return
     */
    private ResultCounter getResultCounterByThemeForCriterion(
            WebResource webResource,
            Theme theme) {
        ResultCounter resultCounter =
                ResultCounterFactory.getInstance().getResultCounter();
        int passedCount = 0;
        int failedCount = 0;
        int nmiCount = 0;
        int naCount = 0;
        int ntCount = 0;
        Collection<CriterionStatistics> csc =
                criterionStatisticsDataService.getCriterionStatisticsByWebResource(
                webResource,
                theme.getCode(),
                new ArrayList<String>());
        for (CriterionStatistics cs : csc) {
            switch (cs.getCriterionResult()) {
                case FAILED:
                    failedCount++;
                    break;
                case PASSED:
                    passedCount++;
                    break;
                case NEED_MORE_INFO:
                    nmiCount++;
                    break;
                case NOT_APPLICABLE:
                    naCount++;
                    break;
                case NOT_TESTED:
                    ntCount++;
                    break;
            }
        }
        resultCounter.setPassedCount(passedCount);
        resultCounter.setFailedCount(failedCount);
        resultCounter.setNmiCount(nmiCount);
        resultCounter.setNaCount(naCount);
        resultCounter.setNtCount(ntCount);
        return resultCounter;
    }

    /**
     *
     * @return the list of themes for a given audit
     */
    private Collection<Theme> getThemeListFromAudit(Audit audit) {
        String ref = parameterDataService.getReferentialKeyFromAudit(audit);
        if (ref != null) {
            return fullThemeMapByRef.get(ref);
        } else {
            return fullThemeMapByRef.get(fullThemeMapByRef.keySet().iterator().next());
        }
    }

    /**
     * Sorts the theme list given the theme ranks.
     *
     * @param themeList
     */
    private void sortThemeList(Collection<Theme> themeList) {
        Collections.sort((List<Theme>) themeList, new Comparator<Theme>() {

            @Override
            public int compare(Theme t1, Theme t2) {
                return Integer.valueOf(t1.getRank()).compareTo(t2.getRank());
            }
        });
    }

}