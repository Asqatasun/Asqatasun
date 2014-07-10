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
package org.opens.tgol.entity.dao.statistics;

import java.math.BigInteger;
import java.util.*;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.displaytag.properties.SortOrderEnum;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.entity.statistics.*;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.sdk.entity.dao.jpa.AbstractJPADAO;
import org.opens.tgol.presentation.data.FailedPageInfo;
import org.opens.tgol.presentation.data.FailedTestInfo;
import org.opens.tgol.presentation.data.FailedThemeInfo;
import org.opens.tgol.presentation.data.PageResult;
import org.opens.tgol.presentation.factory.FailedPageInfoFactory;
import org.opens.tgol.presentation.factory.FailedTestInfoFactory;
import org.opens.tgol.presentation.factory.FailedThemeInfoFactory;
import org.opens.tgol.presentation.factory.PageResultFactory;
import org.opens.tgol.util.HttpStatusCodeFamily;

/**
 *
 * @author jkowalczyk
 */
public class StatisticsDAOImpl extends AbstractJPADAO<WebResourceStatistics, Long>
        implements StatisticsDAO {

    private static final String SELECT_STR = "SELECT ";
    private static final char COMA_CHAR = ',';
    private static final String NB_PASSED_STR = "Nb_Passed";
    private static final String NB_FAILED_STR = "Nb_Failed";
    private static final String NB_NMI_STR = "Nb_Nmi";
    private static final String NB_NA_STR = "Nb_Na";
    private static final String NB_NT_STR = "Nb_Not_Tested";
    private static final String THEME_STATISTICS_TABLE_STR = "ts.";
    private static final String ID_THEME_FIELD_STR = "th.Id_Theme";

    private static final String RETRIEVE_COUNT_BY_RESULT_TYPE_AND_THEME_QUERY = 
            " FROM THEME_STATISTICS as ts, "
            + "WEB_RESOURCE_STATISTICS as wrs "
            + "WHERE ts.Id_Web_Resource_Statistics=wrs.Id_Web_Resource_Statistics "
            + "AND wrs.Id_Web_Resource=:idWebResource "
            + "AND wrs.Id_Audit=:idAudit "
            + "AND ts.Id_Theme=:idTheme";

    private static final String RETRIEVE_COUNT_BY_RESULT_TYPE_AND_WEB_RESOURCE =
            " FROM WEB_RESOURCE_STATISTICS "
            + "WHERE Id_Web_Resource=:idWebResource "
            + "AND Id_Audit=:idAudit";

    private static final String TOP_N_BY_THEME_AND_RESULT_TYPE_QUERY =
            " FROM THEME_STATISTICS as ts, "
            + "THEME as th, "
            + "WEB_RESOURCE_STATISTICS as wrs "
            + "WHERE ts.Id_Web_Resource_Statistics=wrs.Id_Web_Resource_Statistics "
            + "AND wrs.Id_Web_Resource=:idWebResource "
            + "AND wrs.Id_Audit=:idAudit "
            + "AND th.Id_Theme=ts.Id_Theme ";

    private static final String FAILED_OCCURRENCES_STR = " wrs.Nb_Failed_Occurrences ";
    private static final String INVALID_TEST_STR = " wrs.Nb_Invalid_Test ";
    private static final String ORDER_BY_STR = " ORDER BY ";
    private static final String DESC_STR = " DESC ";
    private static final String ASC_STR = " ASC ";
    private static final String PARAMETRABLE_LIMIT_STR = " LIMIT :nbOfResult ";
    private static final String PARAMETRABLE_WINDOW_STR = "OFFSET :window ";
    private static final String URL_FIELD_STR = " w.Url ";
    private static final String ID_WEB_RESOURCE_FIELD_STR = " w.Id_Web_Resource ";

    private static final String TOP_N_INVALID_URL_QUERY =
            " FROM WEB_RESOURCE_STATISTICS as wrs, "
            + "WEB_RESOURCE as w "
            + "WHERE wrs.Id_Audit=:idAudit "
            + "AND wrs.Id_Web_Resource=w.Id_Web_Resource "
            + "AND wrs.Http_Status_Code=200 "
            + "AND w.DTYPE=\'PageImpl\' ";

    private static final String TEST_LABEL_FIELD_STR ="t.Label";
    private static final String TEST_CODE_FIELD_STR ="t.Cd_Test";
    private static final String LEVEL_CODE_FIELD_STR ="l.Cd_Level";
    private static final String TEST_STATISTICS_TABLE_STR = "ts.";
    private static final String TOP_N_BY_TEST_AND_RESULT_TYPE_QUERY = 
            " FROM TEST_STATISTICS as ts, "
            + "TEST as t, "
            + "LEVEL as l, "
            + "WEB_RESOURCE_STATISTICS as wrs "
            + "WHERE ts.Id_Web_Resource_Statistics=wrs.Id_Web_Resource_Statistics "
            + "AND wrs.Id_Web_Resource=:idWebResource "
            + "AND wrs.Id_Audit=:idAudit "
            + "AND ts.Id_Test=t.Id_Test "
            + "AND t.Id_Level=l.Id_Level ";

    private static final String MARK_FIELD_STR = " Mark ";
    private static final String RAW_MARK_FIELD_STR = " Raw_Mark ";
    private static final String RETRIEVE_MARK_QUERY =
            " FROM WEB_RESOURCE_STATISTICS "
            + "WHERE Id_Web_Resource=:idWebResource";
    private static final String IS_AUDIT_MANUAL =
            " AND Manual_Audit=:isManual";

    private static final String WEB_RESOURCE_STAT_COUNT =
            " count(Id_Web_Resource_Statistics) ";
            
    private static final String W_WRS_JOINTURE =
            " FROM WEB_RESOURCE_STATISTICS as wrs "
            + "LEFT JOIN WEB_RESOURCE as w on (w.Id_Web_Resource=wrs.Id_Web_Resource) ";

    private static final String PR_T_JOINTURE =
            "LEFT JOIN PROCESS_RESULT as pr on (w.Id_Web_Resource=pr.Id_Web_Resource) "
            + "LEFT JOIN TEST as t on (pr.Id_Test=t.Id_Test) ";

    private static final String AUDIT_AND_STATUS_CODE_CONDITION = 
            "WHERE wrs.Id_Audit=:idAudit "
            + "AND ( CAST(wrs.Http_Status_Code AS char(4)) like :httpStatusCode ";
    
    private static final String EXTRA_HTTP_STATUS_CODE_CONDITION =
             " OR CAST(wrs.Http_Status_Code AS char(4)) like :extraHttpStatusCode )";

    private static final String CONTAINING_VALUE_CONDITION = 
             " AND w.Url like :containingValue ";
    
    private static final String INVALID_TEST_CONDITION = 
             " AND t.Label=:invalidTestLabel AND pr.Definite_Value='FAILED' ";

    @Override
    protected Class<? extends WebResourceStatistics> getEntityClass() {
        return WebResourceStatisticsImpl.class;
    }
    
    protected Class<? extends CriterionStatistics> getCriterionStatisticsEntityClass() {
        return CriterionStatisticsImpl.class;
    }
    
    protected Class<? extends TestStatistics> getTestStatisticsEntityClass() {
        return TestStatisticsImpl.class;
    }
    
    protected Class<? extends ThemeStatistics> getThemeStatisticsEntityClass() {
        return ThemeStatisticsImpl.class;
    }

    /**
     * Native sql query :
     * SELECT ts.$testSolution FROM THEME_STATISTICS as ts,
     *       WEB_RESOURCE_STATISTICS as wrs
     *       WHERE ts.Id_Web_Resource_Statistics=wrs.Id_Web_Resource_Statistics
     *       AND wrs.Id_Web_Resource=:idWebResource
     *       AND wrs.Id_Audit=:idAudit
     *       AND ts.Id_Theme=:idTheme";
     *
     * where $testSolution is computed on the fly for the given the testSolution
     * passed in argument.
     *
     * @param webResource
     * @param audit
     * @param testSolution
     * @param theme
     * @return
     *      the number of elements for a given result type and theme
     */
    @Override
    public Long findResultCountByResultTypeAndTheme(
            WebResource webResource,
            Audit audit,
            TestSolution testSolution,
            Theme theme,
            boolean manualAudit) {
        StringBuilder queryString = new StringBuilder();
        queryString.append(SELECT_STR);
        queryString.append(THEME_STATISTICS_TABLE_STR);
        queryString = selectNbField(queryString, testSolution);
        queryString.append(RETRIEVE_COUNT_BY_RESULT_TYPE_AND_THEME_QUERY);
        queryString.append(" and wrs.manual_audit = ");
        queryString.append(manualAudit ? "1" : "0")
        ;
        
        Query query = entityManager.createNativeQuery(queryString.toString());
        query.setParameter("idWebResource", webResource.getId());
        query.setParameter("idAudit", audit.getId());
        query.setParameter("idTheme", theme.getId());
        try {
            return ((Integer)query.getSingleResult()).longValue();
        } catch (NoResultException e) {
            return (long) 0;
        }
    }

    /**
     * Native sql query :
     * SELECT th.Id_Theme, ts.$testSolution
     * FROM THEME_STATISTICS as ts, THEME as th, WEB_RESOURCE_STATISTICS as wrs
     *        WHERE ts.Id_Web_Resource_Statistics=wrs.Id_Web_Resource_Statistics
     *        AND wrs.Id_Web_Resource=:idWebResource
     *        AND wrs.Id_Audit=:idAudit
     *        AND th.Id_Theme=ts.Id_Theme
     *        ORDER BY $testSolution
     *        LIMIT :nbOfResult;
     * where $testSolution is computed on the fly for the given the testSolution
     * passed in argument.
     *
     * @param webResource
     * @param audit
     * @param testSolution
     * @param nbOfResult
     * @return
     *      a collection of initialised failedThemeInfo instances
     */
    @Override
    public Collection<FailedThemeInfo> findResultCountByResultTypeAndTheme(
            WebResource webResource,
            Audit audit,
            TestSolution testSolution,
            int nbOfResult) {
        StringBuilder queryString = new StringBuilder();
        queryString.append(SELECT_STR);
        queryString.append(ID_THEME_FIELD_STR);
        queryString.append(COMA_CHAR);
        queryString.append(THEME_STATISTICS_TABLE_STR);
        queryString = selectNbField(queryString, testSolution);
        queryString.append(TOP_N_BY_THEME_AND_RESULT_TYPE_QUERY);
        queryString.append(ORDER_BY_STR);
        queryString = selectNbField(queryString, testSolution);
        queryString.append(DESC_STR);
        queryString.append(PARAMETRABLE_LIMIT_STR);
        Query query = entityManager.createNativeQuery(queryString.toString());
        query.setParameter("idWebResource", webResource.getId());
        query.setParameter("idAudit", audit.getId());
        query.setParameter("nbOfResult", nbOfResult);
        
        try {
            List<Object[]> result = (List<Object[]>)query.getResultList();
            if (result.isEmpty()) {
                return Collections.EMPTY_SET;
            }
            return convertRawResultAsFailedThemeInfo(result);
        } catch (NoResultException nre) {
            return Collections.EMPTY_SET;
        }
    }

    /**
     * 
     * @param result
     * @return a collection of FailedThemeInfo from a raw result collection
     */
    private Set<FailedThemeInfo> convertRawResultAsFailedThemeInfo(Collection<Object[]> result) {
        Set<FailedThemeInfo> failedThemeInfoSet = new LinkedHashSet();
        for (Object[] obj : result) {
            FailedThemeInfo fti = FailedThemeInfoFactory.getInstance().getFailedThemeInfo(
                        ((BigInteger)obj[0]).longValue(),
                        ((Integer)obj[1]).longValue());
            failedThemeInfoSet.add(fti);
        }
        return failedThemeInfoSet;
    }
    
    /**
     * Native sql query :
     * SELECT t.Cd_Test, t.Label, ts.Nb_Failed, l.Cd_Level
     * FROM TEST_STATISTICS as ts,
     *       TEST as t,
     *       LEVEL as l,
     *       WEB_RESOURCE_STATISTICS as wrs
     *       WHERE ts.Id_Web_Resource_Statistics=wrs.Id_Web_Resource_Statistics
     *       AND wrs.Id_Web_Resource=:idWebResource
     *       AND wrs.Id_Audit=:idAudit
     *       AND ts.Id_Test=t.Id_Test 
     *       AND t.Id_Level=l.Id_Level
     *       ORDER BY ts.Nb_Failed DESC;
     * 
     * where $testSolution is computed on the fly for the given the testSolution
     * passed in argument.
     *
     * @param webResource
     * @param audit
     * @param nbOfResult
     * @return
     *       a collection of initialised failedTestInfo instances
     */
    @Override
    public Collection<FailedTestInfo> findFailedTestByOccurrence(
            WebResource webResource,
            Audit audit,
            int nbOfResult) {
        StringBuilder queryString = new StringBuilder();
        queryString.append(SELECT_STR);
        queryString.append(TEST_CODE_FIELD_STR);
        queryString.append(COMA_CHAR);
        queryString.append(TEST_LABEL_FIELD_STR);
        queryString.append(COMA_CHAR);
        queryString.append(TEST_STATISTICS_TABLE_STR);
        queryString = selectNbField(queryString, TestSolution.FAILED);
        queryString.append(COMA_CHAR);
        queryString.append(LEVEL_CODE_FIELD_STR);
        queryString.append(TOP_N_BY_TEST_AND_RESULT_TYPE_QUERY);
        queryString.append(ORDER_BY_STR);
        queryString = selectNbField(queryString, TestSolution.FAILED);
        queryString.append(DESC_STR);
        if (nbOfResult > 0) {
            queryString.append(PARAMETRABLE_LIMIT_STR);
        }
        Query query = entityManager.createNativeQuery(queryString.toString());
        query.setParameter("idWebResource", webResource.getId());
        query.setParameter("idAudit", audit.getId());
        if (nbOfResult > 0) {
            query.setParameter("nbOfResult", nbOfResult);
        }

        try {
            List<Object[]> result = (List<Object[]>)query.getResultList();
            if (result.isEmpty()) {
                return Collections.EMPTY_SET;
            }
            return convertRawResultAsFailedTestInfo(result);
        } catch (NoResultException e) {
            return Collections.EMPTY_SET;
        }
    }

    /**
     * 
     * @param result
     * @return a collection of FailedTestInfo from a raw result collection
     */
    private Set<FailedTestInfo> convertRawResultAsFailedTestInfo(Collection<Object[]> result) {
        Set<FailedTestInfo> failedTestInfoSet = new LinkedHashSet();
        for (Object[] obj : result) {
            if ((Integer)obj[2] > 0) {
                FailedTestInfo fti = FailedTestInfoFactory.getInstance().getFailedTestInfo(
                        (String)obj[0], 
                        (String)obj[1], 
                        ((Integer)obj[2]).longValue(),
                        (String)obj[3]);
                failedTestInfoSet.add(fti);
            }            
        }
        return failedTestInfoSet;
    }

    /**
     * Native sql query :
     * SELECT $testSolution
     * FROM WEB_RESOURCE_STATISTICS
     *        WHERE Id_Web_Resource=:idWebResource
     *        AND Id_Audit=:idAudit";
     * 
     * where $testSolution is computed on the fly for the given the testSolution
     * passed in argument.
     * 
     * @param webResource
     * @param audit
     * @param testSolution
     * @return
     *      the number of elements for a given testSolution.
     */
    @Override
    public Long findResultCountByResultType(
            WebResource webResource,
            Audit audit, 
            TestSolution testSolution) {
        StringBuilder queryString = new StringBuilder();
        queryString.append(SELECT_STR);
        queryString = selectNbField(queryString, testSolution);
        queryString.append(RETRIEVE_COUNT_BY_RESULT_TYPE_AND_WEB_RESOURCE);
        Query query = entityManager.createNativeQuery(queryString.toString());
        query.setParameter("idWebResource", webResource.getId());
        query.setParameter("idAudit", audit.getId());
        try {
            return ((Integer)query.getSingleResult()).longValue();
        } catch (NoResultException e) {
            return (long) 0;
        }
    }

    /**
     * Native sql query :
     * SELECT w.Url, w.Id_Web_Resource, wrs.Nb_Invalid_Test, wrs.Nb_Failed_Occurrences
     * FROM WEB_RESOURCE_STATISTICS as wrs,
     *       WEB_RESOURCE as w
     *       WHERE wrs.Id_Audit=:idAudit
     *       AND wrs.Id_Web_Resource=w.Id_Web_Resource
     *       AND w.DTYPE=\'PageImpl\' ;
     *       ORDER BY wrs.Nb_Invalid_Test DESC, wrs.Nb_Failed_Occurrences DESC
     *       LIMIT :nbOfResult;
     * 
     * @param webResource
     * @param audit
     * @param nbOfResults
     * @return
     *      a collection of initialised FailedPageInfoImpl instances sorted by test.
     */
    @Override
    public Collection<FailedPageInfo> findFailedWebResourceSortedByTest(
            WebResource webResource,
            Audit audit,
            int nbOfResults) {
        StringBuilder queryString = new StringBuilder();
        queryString.append(SELECT_STR);
        queryString.append(URL_FIELD_STR);
        queryString.append(COMA_CHAR);
        queryString.append(ID_WEB_RESOURCE_FIELD_STR);
        queryString.append(COMA_CHAR);
        queryString.append(INVALID_TEST_STR);
        queryString.append(COMA_CHAR);
        queryString.append(FAILED_OCCURRENCES_STR);
        queryString.append(TOP_N_INVALID_URL_QUERY);
        queryString.append(ORDER_BY_STR);
        queryString.append(INVALID_TEST_STR);
        queryString.append(DESC_STR);
        queryString.append(COMA_CHAR);
        queryString.append(FAILED_OCCURRENCES_STR);
        queryString.append(DESC_STR);
        queryString.append(PARAMETRABLE_LIMIT_STR);
        Query query = entityManager.createNativeQuery(queryString.toString());
        query.setParameter("idAudit", audit.getId());
        query.setParameter("nbOfResult", nbOfResults);
        
        try {
            List<Object[]> result = (List<Object[]>)query.getResultList();
            if (result.isEmpty()) {
                return Collections.EMPTY_SET;
            }
            return convertRawResultAsFailedPageInfo(result);
        } catch (NoResultException e) {
            return Collections.EMPTY_SET;
        }
    }

    /**
     * Native sql query :
     * SELECT w.Url, w.Id_Web_Resource, wrs.Nb_Invalid_Test, wrs.Nb_Failed_Occurrences
     * FROM WEB_RESOURCE_STATISTICS as wrs,
     *       WEB_RESOURCE as w
     *       WHERE wrs.Id_Audit=:idAudit
     *       AND wrs.Id_Web_Resource=w.Id_Web_Resource
     *       AND w.DTYPE=\'PageImpl\' ;
     *       ORDER BY wrs.Nb_Failed_Occurrences DESC, wrs.Nb_Invalid_Test DESC
     *       LIMIT :nbOfResult;
     *
     *
     * @param webResource
     * @param audit
     * @param nbOfResults
     * @return
     *      a collection of initialised FailedPageInfoImpl instances sorted by
     *      number of occurrences.
     */
    @Override
    public Collection<FailedPageInfo> findFailedWebResourceSortedByOccurrence(
            WebResource webResource,
            Audit audit,
            int nbOfResults) {
        StringBuilder queryString = new StringBuilder();
        queryString.append(SELECT_STR);
        queryString.append(URL_FIELD_STR);
        queryString.append(COMA_CHAR);
        queryString.append(ID_WEB_RESOURCE_FIELD_STR);
        queryString.append(COMA_CHAR);
        queryString.append(INVALID_TEST_STR);
        queryString.append(COMA_CHAR);
        queryString.append(FAILED_OCCURRENCES_STR);
        queryString.append(TOP_N_INVALID_URL_QUERY);
        queryString.append(ORDER_BY_STR);
        queryString.append(FAILED_OCCURRENCES_STR);
        queryString.append(DESC_STR);
        queryString.append(COMA_CHAR);
        queryString.append(INVALID_TEST_STR);
        queryString.append(DESC_STR);
        queryString.append(PARAMETRABLE_LIMIT_STR);
        Query query = entityManager.createNativeQuery(queryString.toString());
        query.setParameter("idAudit", audit.getId());
        query.setParameter("nbOfResult", nbOfResults);
        
        try {
            List<Object[]>  result = (List<Object[]>)query.getResultList();
            if (result.isEmpty()) {
                return Collections.EMPTY_SET;
            }
            return convertRawResultAsFailedPageInfo(result);
        } catch (NoResultException e) {
            return Collections.EMPTY_SET;
        }
    }

    /**
     * 
     * @param result
     * @return a collection of FailedPageInfo from a raw result collection
     */
    private Set<FailedPageInfo> convertRawResultAsFailedPageInfo(Collection<Object[]> result) {
        Set<FailedPageInfo> failedPageInfoSet = new LinkedHashSet();
        for (Object[] obj : result) {
            FailedPageInfo fti = FailedPageInfoFactory.getInstance().getFailedPageInfo(
                    (String)obj[0],
                    ((BigInteger)obj[1]).longValue(),
                    ((Integer)obj[2]).longValue(),
                    ((Integer)obj[3]).longValue());
            failedPageInfoSet.add(fti);
        }
        return failedPageInfoSet;
    }
    
    /**
     * Native sql query :
     * SELECT Mark
     * FROM WEB_RESOURCE_STATISTICS
     *       WHERE Id_Web_Resource=:idWebResource;
     *
     * @param idWebResource
     * @return
     *      the mark for given audit and webresource
     */
    @Override
    public Float findWeightedMarkByWebResourceAndAudit(Long idWebResource , boolean isManual) {
        StringBuilder queryString = new StringBuilder();
        queryString.append(SELECT_STR);
        queryString.append(MARK_FIELD_STR);
        queryString.append(RETRIEVE_MARK_QUERY);
        queryString.append(IS_AUDIT_MANUAL);
        Query query = entityManager.createNativeQuery(queryString.toString());
        query.setParameter("idWebResource", idWebResource);
        query.setParameter("isManual", isManual);
        try {
            Object result = query.getSingleResult();
            if (result instanceof Float) {
                return (Float)result;
            } else if (result instanceof Double) {
                return ((Double)result).floatValue();
            } else {
                return (float) 0;
            }
        } catch (NoResultException e) {
            return (float) 0;
        }
    }
    
    /**
     * Native sql query :
     * SELECT Raw_Mark
     * FROM WEB_RESOURCE_STATISTICS
     *       WHERE Id_Web_Resource=:idWebResource
     *
     * @param idWebResource
     * @return
     *      the mark for given audit and webresource
     */
    @Override
    public Float findRawMarkByWebResourceAndAudit(Long idWebResource, boolean isManual) {
        StringBuilder queryString = new StringBuilder();
        queryString.append(SELECT_STR);
        queryString.append(RAW_MARK_FIELD_STR);
        queryString.append(RETRIEVE_MARK_QUERY);
        queryString.append(IS_AUDIT_MANUAL);
        
        Query query = entityManager.createNativeQuery(queryString.toString());
        query.setParameter("idWebResource", idWebResource);
        query.setParameter("isManual", isManual);
        try {
            Object result = query.getSingleResult();
            if (result instanceof Float) {
                return (Float)result;
            } else if (result instanceof Double) {
                return ((Double)result).floatValue();
            } else {
                return (float) 0;
            }
        } catch (NoResultException e) {
            return (float) 0;
        }
    }

    /**
     * Native sql query :
     * SELECT count(Id_Web_Resource_Statistics)
     * FROM WEB_RESOURCE_STATISTICS as wrs, WEB_RESOURCE as w 
     *       WHERE wrs.Id_Audit=:idAudit 
     *       AND wrs.Id_Web_Resource=w.Id_Web_Resource 
     *       AND wrs.Http_Status_Code like :httpStatusCode
     *       AND w.Url like %:containingValue%;
     *
     * The condition applied on the web resource is optionnal, depending on
     * if the given sequence is not null or empty
     *
     * @param idAudit
     * @param httpStatusCode
     * @param containingValue
     * @return
     *      the number of webresource for given audit, webresource and http return
     *      status code.
     */
    @Override
    public Long findWebResourceCountByAuditAndHttpStatusCode(
            Long idAudit,
            HttpStatusCodeFamily httpStatusCode,  
            String invalidTestLabel,
            String containingValue) {
        boolean hasContainingValue = false;
        boolean hasInvalidTestConstraint = false;
        if (containingValue != null && !containingValue.isEmpty()) {
           hasContainingValue = true;
        }
        if (invalidTestLabel != null) {
            hasInvalidTestConstraint = true;
        }
        StringBuilder queryString = new StringBuilder();
        queryString.append(SELECT_STR);
        queryString.append(WEB_RESOURCE_STAT_COUNT);
        queryString.append(W_WRS_JOINTURE);
        if (hasInvalidTestConstraint) {
            queryString.append(PR_T_JOINTURE);
        }
        queryString.append(AUDIT_AND_STATUS_CODE_CONDITION);
        // the 4xx and 5xx and displayed together. To enable this we need to
        // add the 'like '%5%' constraints on the request in the case of a
        // HttpStatusCodeFamily.CODE4xx instance is passed as argument.
        if (httpStatusCode.equals(HttpStatusCodeFamily.f4xx)) {
            queryString.append(EXTRA_HTTP_STATUS_CODE_CONDITION);
        } else {
            queryString.append(')');
        }
        if (hasContainingValue) {
            queryString.append(CONTAINING_VALUE_CONDITION);
        }
        if (hasInvalidTestConstraint) {
            queryString.append(INVALID_TEST_CONDITION);
        }
        Query query = entityManager.createNativeQuery(queryString.toString());
        
        query.setParameter("httpStatusCode", httpStatusCode.getCode() + "%");
        if (httpStatusCode.equals(HttpStatusCodeFamily.f4xx)) {
            query.setParameter("extraHttpStatusCode", HttpStatusCodeFamily.f5xx.getCode() + "%");
        }
        query.setParameter("idAudit", idAudit);
        if (hasContainingValue) {
            query.setParameter("containingValue", "%"+containingValue+"%");
        }
        if (hasInvalidTestConstraint) {
            query.setParameter("invalidTestLabel", invalidTestLabel);
        }
        try {
            return (((BigInteger)query.getSingleResult()).longValue());
        } catch (NoResultException e) {
            return (long) 0;
        }
    }

    /**
     * Native sql query :
     * SELECT w.Url, w.Rank, wrs.Mark, wrs.Id_Web_Resource, wrs.Http_Status_Code
     * FROM WEB_RESOURCE_STATISTICS as wrs, WEB_RESOURCE as w
     *       WHERE wrs.Id_Audit=:idAudit
     *       AND wrs.Id_Web_Resource=w.Id_Web_Resource
     *       AND wrs.Http_Status_Code like :httpStatusCode
     *       AND w.Url like %:containingValue%;
     *       ORDER BY $orderValue
     *       $sortDirection
     *       LIMIT :nbOfResult,:window 
     *
     * The condition applied on the web resource is optional, depending on
     * if the given sequence is not null or empty
     *
     * The $orderValue can be set to "wrs.Mark" (if "mark" is passed as argument),
     * to "wrs.Http_Status_Code" (if "httpStatusCode is passed as arguement) and
     * to "w.Url" (default value).
     *
     * The $sortDirection can be to DESC (if "2" is passed as argument) or ASC
     * (default value).
     *
     * The limitation is disables when window is set to -1.
     *
     * @param idAudit
     * @param httpStatusCode
     * @param invalidTestLabel
     * @param window
     * @param nbOfResult
     * @param sortDirection
     * @param sortCriterion
     * @param containingValue
     * @return
     */
    @Override
    public Collection<PageResult> findWebResourceByAuditAndHttpStatusCode(
            Long idAudit,
            HttpStatusCodeFamily httpStatusCode,
            String invalidTestLabel,
            int window,
            int nbOfResult,
            SortOrderEnum sortDirection,
            String sortCriterion,
            String containingValue) {
        boolean hasContainingValue = false;
        boolean hasInvalidTestConstraint = false;
        if (containingValue != null && !containingValue.isEmpty()) {
           hasContainingValue = true;
        }
        if (invalidTestLabel != null) {
            hasInvalidTestConstraint = true;
        }
        StringBuilder queryString = new StringBuilder();
        queryString.append(SELECT_STR);
        queryString.append(URL_FIELD_STR);
        queryString.append(COMA_CHAR);
        queryString.append("w.Rank");
        queryString.append(COMA_CHAR);
        queryString.append("wrs.Mark");
        queryString.append(COMA_CHAR);
        queryString.append("wrs.Raw_Mark");
        queryString.append(COMA_CHAR);
        queryString.append("wrs.Id_Web_Resource");
        queryString.append(COMA_CHAR);
        queryString.append("wrs.Http_Status_Code");
        queryString.append(W_WRS_JOINTURE);
        if (hasInvalidTestConstraint) {
            queryString.append(PR_T_JOINTURE);
        }
        queryString.append(AUDIT_AND_STATUS_CODE_CONDITION);
        // the 4xx and 5xx and displayed together. To enable this we need to
        // add the 'like '%5%' constraints on the request in the case of a
        // HttpStatusCodeFamily.CODE4xx instance is passed as argument.
        if (httpStatusCode.equals(HttpStatusCodeFamily.f4xx)) {
            queryString.append(EXTRA_HTTP_STATUS_CODE_CONDITION);
        } else {
            queryString.append(')');
        }
        if (hasContainingValue) {
            queryString.append(CONTAINING_VALUE_CONDITION);
        }
        if (hasInvalidTestConstraint) {
            queryString.append(INVALID_TEST_CONDITION);
        }
        queryString.append(ORDER_BY_STR);
        if (sortCriterion.equalsIgnoreCase("weightedMark")) {
            queryString.append("wrs.Mark");
        } else if (sortCriterion.equalsIgnoreCase("rawMark")) {
            queryString.append("wrs.Raw_Mark");
        } else if (sortCriterion.equalsIgnoreCase("httpStatusCode")) {
            queryString.append("wrs.Http_Status_Code");
        } else if (sortCriterion.equalsIgnoreCase("rank")) {
            queryString.append("w.Rank");
        } else {
            queryString.append(URL_FIELD_STR);
        }
        if (sortDirection.equals(SortOrderEnum.DESCENDING)) {
            queryString.append(DESC_STR);
        } else {
            queryString.append(ASC_STR);
        }
        if (nbOfResult != -1) {
            queryString.append(PARAMETRABLE_LIMIT_STR);
            queryString.append(PARAMETRABLE_WINDOW_STR);
        }

        Query query = entityManager.createNativeQuery(queryString.toString());
        query.setParameter("httpStatusCode", httpStatusCode.getCode() + "%");
        if (httpStatusCode.equals(HttpStatusCodeFamily.f4xx)) {
            query.setParameter("extraHttpStatusCode", HttpStatusCodeFamily.f5xx.getCode() + "%");
        }
        query.setParameter("idAudit", idAudit);
        if (nbOfResult != -1) {
            query.setParameter("nbOfResult", nbOfResult);
            query.setParameter("window", window);
        }
        if (hasContainingValue) {
            query.setParameter("containingValue", "%"+containingValue+"%");
        }
        if (hasInvalidTestConstraint) {
            query.setParameter("invalidTestLabel", invalidTestLabel);
        }
        
        try {
            List<Object[]> result = (List<Object[]>)query.getResultList();
            if (result.isEmpty()) {
                return Collections.EMPTY_SET;
            }
            return convertRawResultAsPageResultSet(result);
        } catch (NoResultException e) {
            return Collections.EMPTY_SET;
        }
    }

    /**
     * 
     * @param result
     * @return a collection of PageResult from a raw result collection
     */
    private Set<PageResult> convertRawResultAsPageResultSet(Collection<Object[]> result) {
        Set<PageResult> failedPageInfoSet = new LinkedHashSet();
        for (Object[] obj : result) {
            
            Float weightedMark;
            // cast to deal with different sgbd interpretation
            if (obj[2] instanceof Float) {
                weightedMark = (Float)obj[2];
            } else if (obj[2] instanceof Double) {
                weightedMark = ((Double)obj[2]).floatValue();
            } else {
                weightedMark = (Float)obj[2];
            }

            Float rawMark;
            if (obj[3] instanceof Float) {
                rawMark = (Float)obj[3];
            } else if (obj[3] instanceof Double) {
                rawMark = ((Double)obj[3]).floatValue();
            } else {
                rawMark = (Float)obj[3];
            }

            PageResult fti = PageResultFactory.getInstance().getPageResult(
                    (String)obj[0],
                    (Integer)obj[1], // rank
                    weightedMark, //weighted mark
                    rawMark, // raw mark
                    ((BigInteger)obj[4]).longValue(), //webresource Id
                    ((Integer)obj[5]).toString()); // http status code
            failedPageInfoSet.add(fti);
        }
        return failedPageInfoSet;
    }
    
    /**
     * This method returns the appropriate native sql field for a given 
     * testSolution.
     *
     * @param queryString
     * @param testSolution
     * @return
     */
    private StringBuilder selectNbField(
            StringBuilder queryString,
            TestSolution testSolution) {
        switch (testSolution) {
            case PASSED:
                queryString.append(NB_PASSED_STR);
                break;
            case FAILED:
                queryString.append(NB_FAILED_STR);
                break;
            case NEED_MORE_INFO:
                queryString.append(NB_NMI_STR);
                break;
            case NOT_APPLICABLE:
                queryString.append(NB_NA_STR);
                break;
            case NOT_TESTED:
                queryString.append(NB_NT_STR);
                break;
        }
        return queryString;
    }
    
}