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
package org.opens.tgol.presentation.factory;

import org.opens.tgol.entity.decorator.tanaguru.subject.WebResourceDataServiceDecorator;
import org.opens.tgol.entity.service.contract.ActDataService;
import org.opens.tgol.presentation.data.AuditStatistics;
import org.opens.tgol.presentation.data.AuditStatisticsImpl;
import org.opens.tgol.presentation.data.ResultCounter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.entity.service.parameterization.ParameterDataService;
import org.opens.tanaguru.entity.service.reference.ThemeDataService;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class AuditStatisticsFactory {

    private static final String PARAM_SEPARATOR =";";

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

    private ParameterDataService parameterDataService;
    @Autowired
    public void setParameterDataService(ParameterDataService parameterDataService) {
        this.parameterDataService = parameterDataService;
    }

    private Map<String, Collection<Theme>> fullThemeMapByRef = null;
    @Autowired
    public final void setThemeDataService(ThemeDataService themeDataService) {
        Collection<Theme> themeList = themeDataService.findAll();
        if (fullThemeMapByRef == null) {
            fullThemeMapByRef = new HashMap<String, Collection<Theme>>();
        }
        // we retrieve the theme from the criterion. To display a theme, it has
        // to be associated with a criterion
        for (Theme theme : themeList) {
            if (!theme.getCriterionList().isEmpty()) {
                String referenceCode = theme.getCriterionList().iterator().next().getReference().getCode();
                if (!fullThemeMapByRef.containsKey(referenceCode)) {
                    Collection<Theme> themeListByRef = new ArrayList<Theme>();
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

    private String levelParameter = "LEVEL";
    public void setLevelParameter(String levelParameter) {
        this.levelParameter = levelParameter;
    }

    private String referentialParameter = "referential";
    public String getReferentialParameter() {
        return referentialParameter;
    }


    /**
     * The unique shared instance of ContractInfoFactory
     */
    private static AuditStatisticsFactory auditStatisticsFactory;

    /**
     * Default private constructor
     */
    protected AuditStatisticsFactory(){}

    public static synchronized AuditStatisticsFactory getInstance(){
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
     * @return
     */
    public AuditStatistics getAuditStatistics (
            WebResource webResource,
            Map<String, String> parametersToDisplay) {
        AuditStatistics auditStats = new AuditStatisticsImpl();
        auditStats.setUrl(webResource.getURL());
        Audit audit = null;
        if (webResource.getAudit() != null) {
            audit = webResource.getAudit();
            auditStats.setDate(audit.getDateOfCreation());
            auditStats.setAuditScope(
                    actDataService.getActFromWebResource(webResource).getScope().getCode());
        } else if (webResource.getParent() != null) {
            audit = webResource.getParent().getAudit();
            auditStats.setDate(audit.getDateOfCreation());
            auditStats.setAuditScope(
                    actDataService.getActFromWebResource(webResource.getParent()).getScope().getCode());
        }
        auditStats.setRawMark(markFormatter(webResource, audit, true));
        auditStats.setWeightedMark(markFormatter(webResource, audit, false));

        if (webResource instanceof Site) {
            auditStats.setPageCounter(webResourceDataService.
                    getChildWebResourceCount(webResource).intValue());
        }
        ResultCounter resultCounter = auditStats.getResultCounter();
        resultCounter.setPassedCount(
                    getResultCountByResultType(webResource, audit,
                    TestSolution.PASSED));
        resultCounter.setFailedCount(
                    getResultCountByResultType(webResource, audit,
                    TestSolution.FAILED));
        resultCounter.setNmiCount(
                    getResultCountByResultType(webResource, audit,
                    TestSolution.NEED_MORE_INFO));
        resultCounter.setNaCount(
                    getResultCountByResultType(webResource, audit,
                    TestSolution.NOT_APPLICABLE));
        auditStats.setCounterByThemeMap(addCounterByThemeMap(audit,webResource));
        auditStats.setParametersMap(getAuditParameters(audit, parametersToDisplay));
        return auditStats;
    }

    /**
     * Returns the theme for a given id
     * The collection of themes is loaded on the application startup to avoid
     * multiple accesses to the database.
     * 
     * @param id
     * @return
     */
    public Theme getTheme(Long id){
        for (Collection<Theme> entry : fullThemeMapByRef.values()) {
            for (Theme theme : entry)
            if (theme.getId().equals(id)) {
                return theme;
            }
        }
        return null;
    }

    /**
     * 
     * @param webResource
     * @param audit
     * @param testSolution
     * @return
     */
    private Integer getResultCountByResultType(
            WebResource webResource,
            Audit audit,
            TestSolution testSolution) {
        return webResourceDataService.
                    getResultCountByResultType(webResource, audit,
                    testSolution).intValue();
    }

    /**
     * Retrieves all the parameters of the audit and prepares the data
     * depending on the wished displayed parameters of the view.
     *
     *
     * @param audit
     * @param parametersToDisplay
     * @return
     */
    private Map<String, String> getAuditParameters(
            Audit audit,
            Map<String, String> parametersToDisplay){
        Map<String, String> auditParameters = new LinkedHashMap<String, String>();
        Set<Parameter> auditParamSet =
                parameterDataService.getParameterSetFromAudit(audit);
        // to ensure compatibility with audit that have been launched before
        // the parameter management has been integrated
        if (auditParamSet.isEmpty()) {
            return auditParameters;
        }
        for (Map.Entry<String,String> entry : parametersToDisplay.entrySet()) {
            for (Parameter param : auditParamSet) {
                if (entry.getKey().equals(param.getParameterElement().getParameterElementCode())){
                    //specific case of level parameter
                    if (entry.getKey().equals(levelParameter)) {
                        String[] refAndLevel = param.getValue().split(PARAM_SEPARATOR);
                        auditParameters.put(
                            referentialParameter,
                            refAndLevel[0]);
                        auditParameters.put(
                            entry.getValue(),
                            refAndLevel[1]);
                    } else {
                        auditParameters.put(
                            entry.getValue(),
                            param.getValue());
                    }
                }
            }
        }
        return auditParameters;
    }

    /**
     * This methods converts the mark of the audit as a string
     * @param webresource
     * @param audit
     * @param isRawMark
     * @return
     */
    private String markFormatter(WebResource webresource, Audit audit, boolean isRawMark) {
        Float mark = webResourceDataService.getMarkByWebResourceAndAudit(webresource, audit, isRawMark);
        return String.valueOf(Float.valueOf(mark).intValue());
    }

    /**
     * Retrieve the number of results by type for each themes of the audit and
     * populate a map with the Theme as key and a ResultCounter instance as value.
     * @param webResource
     * @param audit
     * @return
     */
    private Map<Theme, ResultCounter> addCounterByThemeMap (
            Audit audit,
            WebResource webResource) {
        Map<Theme, ResultCounter> counterByThemeMap =
                new LinkedHashMap<Theme, ResultCounter>();
        for (Theme theme : getThemeListFromAudit(audit)) {
            ResultCounter siteThemeResultCounter = ResultCounterFactory.getInstance().getResultCounter();
            siteThemeResultCounter.setPassedCount(
                    webResourceDataService.getResultCountByResultTypeAndTheme(
                    webResource, audit, TestSolution.PASSED, theme).intValue());
            siteThemeResultCounter.setFailedCount(
                    webResourceDataService.getResultCountByResultTypeAndTheme(
                    webResource, audit, TestSolution.FAILED, theme).intValue());
            siteThemeResultCounter.setNmiCount(
                    webResourceDataService.getResultCountByResultTypeAndTheme(
                    webResource, audit, TestSolution.NEED_MORE_INFO, theme).intValue());
            siteThemeResultCounter.setNaCount(
                    webResourceDataService.getResultCountByResultTypeAndTheme(
                    webResource, audit, TestSolution.NOT_APPLICABLE, theme).intValue());
            counterByThemeMap.put(theme, siteThemeResultCounter);
        }
        return counterByThemeMap;
    }

    /**
     *
     * @return
     *      the list of themes for a given audit
     */
    private Collection<Theme> getThemeListFromAudit(Audit audit) {
        String ref = extractRefFromAuditParameter(audit);
        if (ref != null) {
            return fullThemeMapByRef.get(ref);
        } else {
            return fullThemeMapByRef.get(fullThemeMapByRef.keySet().iterator().next());
        }
    }

    /**
     * Extracts the referential value from the LEVEL PARAMETER.
     * This parameter is specific because two parameters are associated : the
     * referential and the level. The two values are separated by a ; and the
     * first value is the referential, the second the level.
     * @param audit
     * @return
     */
    private String extractRefFromAuditParameter(Audit audit) {
        Parameter refParameter = null;
        for (Parameter param : parameterDataService.getParameterSetFromAudit(audit)) {
            if (param.getParameterElement().getParameterElementCode().equalsIgnoreCase(levelParameter)) {
                refParameter = param;
                break;
            }
        }
        if (refParameter != null) {
            return refParameter.getValue().split(PARAM_SEPARATOR)[0];
        }
        return null;
    }

    /**
     * Sorts the theme list given the theme ranks.
     * 
     * @param themeList
     */
    private void sortThemeList (Collection<Theme> themeList) {
        Collections.sort((List<Theme>)themeList, new Comparator<Theme>() {
            @Override
            public int compare(Theme t1, Theme t2) {
                return Integer.valueOf(t1.getRank()).
                        compareTo(Integer.valueOf(t2.getRank()));
            }
        });
    }

}