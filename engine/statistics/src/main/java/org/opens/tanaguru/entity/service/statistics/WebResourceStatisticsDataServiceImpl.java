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
package org.opens.tanaguru.entity.service.statistics;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.opens.tanaguru.entity.dao.statistics.CriterionStatisticsDAO;
import org.opens.tanaguru.entity.dao.statistics.ThemeStatisticsDAO;
import org.opens.tanaguru.entity.dao.statistics.WebResourceStatisticsDAO;
import org.opens.tanaguru.entity.reference.Criterion;
import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.entity.reference.ThemeImpl;
import org.opens.tanaguru.entity.statistics.CriterionStatistics;
import org.opens.tanaguru.entity.statistics.CriterionStatisticsImpl;
import org.opens.tanaguru.entity.statistics.ThemeStatistics;
import org.opens.tanaguru.entity.statistics.ThemeStatisticsImpl;
import org.opens.tanaguru.entity.statistics.WebResourceStatistics;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.sdk.entity.service.AbstractGenericDataService;

/**
 * 
 * @author jkowalczyk
 */
public class WebResourceStatisticsDataServiceImpl extends
		AbstractGenericDataService<WebResourceStatistics, Long> implements
		WebResourceStatisticsDataService {
	
	private CriterionStatisticsDAO criterionStatisticsDAO;
	
	private ThemeStatisticsDAO themeStatisticsDAO;

	private static BigDecimal ZERO = BigDecimal.valueOf(Double.valueOf(0.0));
	

	@Override
	public Long getResultCountByResultType(Long webresourceId,
			TestSolution testSolution) {
		return ((WebResourceStatisticsDAO) entityDao)
				.findResultCountByResultType(webresourceId, testSolution);
	}

	@Override
	public BigDecimal getWeightedResultByResultType(Long webresourceId,
			Collection<Parameter> paramSet, TestSolution testSolution,
            boolean isManualAudit) {
		return ((WebResourceStatisticsDAO) entityDao)
				.findWeightedResultCountByResultType(webresourceId, paramSet,
						testSolution, isManualAudit);
	}

	@Override
	public Long getNumberOfOccurrencesByWebResourceAndResultType(
			Long webresourceId, TestSolution testSolution, boolean isManualAudit) {
		return ((WebResourceStatisticsDAO) entityDao)
				.findNumberOfOccurrencesByWebResourceAndResultType(
						webresourceId, testSolution, isManualAudit);
	}

	@Override
	public Integer getHttpStatusCodeByWebResource(Long webresourceId) {
		return ((WebResourceStatisticsDAO) entityDao)
				.findHttpStatusCodeByWebResource(webresourceId);
	}

	@Override
	public WebResourceStatistics getWebResourceStatisticsByWebResource(
			WebResource webResource) {
		return ((WebResourceStatisticsDAO) entityDao)
				.findWebResourceStatisticsByWebResource(webResource);
	}
	
//	@Override
//	public Collection<CriterionStatistics> findCriterionStatisticsByWebResource(
//			WebResourceStatistics webResourceStatistics) {
//		Collection<CriterionStatistics> aa =((CriterionStatisticsDAO) entityDao).findCriterionStatisticsByWebResource(null);
//		return null;
//	}


	/**
	 * This method compute the mark of the audit. Here is the algorithm formula
	 * : ((1-ratioNMI) * passed/(passed+failed) + ratioNMI *
	 * needMoreInfo/(passed+failed+needMoreInfo)) *100f where ratioNMI =
	 * needMoreInfo / (passed+failed+needMoreInfo)
	 * 
	 * @param wrStatistics
	 * @return
	 */
	public WebResourceStatistics computeMark(WebResourceStatistics wrStatistics) {
		float passed = wrStatistics.getNbOfPassed();
		// page on error, mark set to -1
		if (passed == -1) {
			wrStatistics.setRawMark(Float.valueOf(-1));
			return wrStatistics;
		}
		float failed = wrStatistics.getNbOfFailed();
		float needMoreInfo = wrStatistics.getNbOfNmi();
		if (failed == 0 && passed == 0) {
			wrStatistics.setMark(Float.valueOf(0));
			return wrStatistics;
		}
		float ratioNMI = needMoreInfo / (passed + failed + needMoreInfo);
		float result = ((1 - ratioNMI) * passed / (passed + failed) + ratioNMI
				* needMoreInfo / (passed + failed + needMoreInfo)) * 100f;
		wrStatistics.setMark(result);
		return wrStatistics;
	}

	/**
	 * This method compute the raw mark of the audit. Here is the algorithm
	 * formula : passed/(passed+failed)
	 * 
	 * @param wrStatistics
	 * @return
	 */
	public WebResourceStatistics computeRawMark(
			WebResourceStatistics wrStatistics) {
		float passed = wrStatistics.getNbOfPassed();
		// page on error, mark set to -1
		if (passed == -1) {
			wrStatistics.setRawMark(Float.valueOf(-1));
			return wrStatistics;
		}
		BigDecimal weightedPassed = wrStatistics.getWeightedPassed();
		BigDecimal weightedFailed = wrStatistics.getWeightedFailed();
		if ((weightedFailed.equals(BigDecimal.ZERO) || weightedFailed
				.equals(ZERO))
				&& (weightedPassed.equals(BigDecimal.ZERO) || weightedPassed
						.equals(ZERO))) {
			wrStatistics.setRawMark(Float.valueOf(0));
			return wrStatistics;
		}

		float result = weightedPassed.divide(
				weightedPassed.add(weightedFailed), 4, RoundingMode.HALF_UP)
				.floatValue() * 100f;
		wrStatistics.setRawMark(result);
		return wrStatistics;
	}

	/**
	 * Gather the number of failed occurrence for a given web resource.
	 * 
	 * @param wrStatistics
	 * @return
	 */
	private WebResourceStatistics computeNumberOfFailedOccurrences(
			WebResourceStatistics wrStatistics, WebResource webResource,boolean isManualAudit) {
		int nbOfFailedOccurences = this
				.getNumberOfOccurrencesByWebResourceAndResultType(
						webResource.getId(), TestSolution.FAILED, isManualAudit).intValue();
		wrStatistics.setNbOfFailedOccurences(nbOfFailedOccurences);
		return wrStatistics;
	}

	/**
	 * 
	 * @param wrStatistics
	 */
	private void setWeightedResult(WebResourceStatistics wrStatistics,
			WebResource webResource) {
		// TODO: Collection à initialiser
		Collection<Parameter> paramSet = new ArrayList<Parameter>();
		BigDecimal weightedPassed = this.getWeightedResultByResultType(
				webResource.getId(), paramSet, TestSolution.PASSED, true);

		BigDecimal weightedFailed = this.getWeightedResultByResultType(
				webResource.getId(), paramSet, TestSolution.FAILED, true);

		BigDecimal weightedNa = this.getWeightedResultByResultType(
				webResource.getId(), paramSet, TestSolution.NOT_APPLICABLE, true);

		BigDecimal weightedNmi = this.getWeightedResultByResultType(
				webResource.getId(), paramSet, TestSolution.NEED_MORE_INFO, true);
		wrStatistics.setWeightedFailed(weightedFailed);
		wrStatistics.setWeightedPassed(weightedPassed);
		wrStatistics.setWeightedNmi(weightedNmi);
		wrStatistics.setWeightedNa(weightedNa);
	}

	@Override
	public WebResourceStatistics createWebResourceStatisticsForManualAudit(
			Audit audit, WebResource webResource,
			List<ProcessResult> netResultList) {
		
		// Get the WebResourceStatistics for the current webResource
		// If there is one. the create a manual else update the manual
		// WebResourceStatistics with manual_audit flag = 1
		WebResourceStatistics wrStats = null;
		
		List<WebResourceStatistics> webResourceStatisticsByWebResourceList = ((WebResourceStatisticsDAO) entityDao).findWebResourceStatisticsByWebResource(webResource, true);
		
		if (webResourceStatisticsByWebResourceList.isEmpty() || webResourceStatisticsByWebResourceList.size() ==1) {
			wrStats = this.create();	
		} else {
			int size = webResourceStatisticsByWebResourceList.size();
			WebResourceStatistics webResourceStatistics = null;
			for (int i=0; i<size; i++) {
				webResourceStatistics = webResourceStatisticsByWebResourceList.get(i);
				if (webResourceStatistics.getIsManualAuditStatistics() == 1)  {
					wrStats = webResourceStatistics;
					// assuming there is one line of manual audit
					break;
				}
			}
		}
		
		
		//get creterion_statistics by web_resources_statistics and test
		//get theme_statistics by web_resources_statistics
		
		
		
		
		Map<Criterion, CriterionStatistics> csMap = new HashMap<Criterion, CriterionStatistics>();
	    Map<Theme, ThemeStatistics> tsMap = new HashMap<Theme, ThemeStatistics>();

		int nbOfPassed = 0;
		int nbOfFailed = 0;
		int nbOfNmi = 0;
		int nbOfNa = 0;
		int nbOfDetected = 0;
		int nbOfSuspected = 0;
		int nbOfNt = 0;

		Collection<CriterionStatistics> criterionStatisticsByWr = criterionStatisticsDAO.findCriterionStatisticsByWebResource(wrStats);
		Collection<ThemeStatistics> themeStatisticsByWr = themeStatisticsDAO.findThemeStatisticsByWebResource(wrStats);
		
		System.out.println("criterionStatisticsByWr " +  criterionStatisticsByWr.size());
		System.out.println("criterionStatisticsByWr " + themeStatisticsByWr.size());
		
		fillThemeStatisticsMap (themeStatisticsByWr, tsMap);
		
		
		for (ProcessResult pr : netResultList) {
			TestSolution prResult = (TestSolution) pr.getManualValue();
			
//			((CriterionStatisticsDAO) entityDao).findCriterionStatisticsByWebResource(webResource, pr.getTest().getCriterion().getTheme().getCode(),netResultList);
			
			switch (prResult) {
			case PASSED:
				nbOfPassed++;
				break;
			case FAILED:
				nbOfFailed++;
				break;
			case NOT_APPLICABLE:
				nbOfNa++;
				break;
			case NEED_MORE_INFO:
			case DETECTED:
			case SUSPECTED_FAILED:
			case SUSPECTED_PASSED:
				nbOfNmi++;
				break;
			case NOT_TESTED:
				nbOfNt++;
				break;
			}
			Criterion criterion = pr.getTest().getCriterion();
			Theme theme = criterion.getTheme();
			
//			((CriterionStatisticsDAO)entityDao).findCriterionStatisticsByWebResources(wrStats);
			
			System.out.println(criterionStatisticsByWr);
			
			addResultToCriterionCounterMap(prResult, criterion, wrStats, csMap);
			addResultToThemeCounterMap(prResult, theme, wrStats, tsMap);
		}

			wrStats.setNbOfFailed(nbOfFailed);
			wrStats.setNbOfInvalidTest(nbOfFailed);
			wrStats.setNbOfPassed(nbOfPassed);
			wrStats.setNbOfNmi(nbOfNmi);
			wrStats.setNbOfNa(nbOfNa);
			wrStats.setNbOfDetected(nbOfDetected);
			wrStats.setNbOfSuspected(nbOfSuspected);
			wrStats.setNbOfNotTested(nbOfNt);

			setWeightedResult(wrStats, webResource);
			wrStats.setHttpStatusCode(getHttpStatusCodeByWebResource(webResource
					.getId()));

			wrStats = computeMark(wrStats);
			wrStats = computeRawMark(wrStats);
			wrStats = computeNumberOfFailedOccurrences(wrStats, webResource, true);

			wrStats.setAudit(audit);
			wrStats.setWebResource(webResource);
			wrStats.setIsManualAuditStatistics(1);
			
			// Compute criterion Result for each criterion and link each 
	        // criterionStatistics to the current webResourceStatistics
	        for (CriterionStatistics cs : csMap.values()) {
	            computeCriterionResult(cs);
	            wrStats.addCriterionStatistics(cs);
	        }
	        // Link each themeStatistics to the current webResourceStatistics
	        for (ThemeStatistics ts : tsMap.values()) {
	        	
	        	
	        	Set<ThemeStatistics> themeStatisticsSet = wrStats.getThemeStatisticsSet();
				if (! themeStatisticsSet.contains(ts)){
	        		wrStats.addThemeStatistics(ts);	
	        	} else {
	        		themeStatisticsSet.remove(ts);
	        		wrStats.addThemeStatistics(ts);
	        	}
	        		
//	        	wrStats.addThemeStatistics(ts);
	        }

			this.saveOrUpdate(wrStats);

		return wrStats;
	}
	
	private void fillThemeStatisticsMap(
			Collection<ThemeStatistics> themeStatisticsByWr,
			Map<Theme, ThemeStatistics> tsMap) {
		
		
		if (themeStatisticsByWr.isEmpty()) {
			return;
		}
		
		for (ThemeStatistics current : themeStatisticsByWr) {
			
			if (!tsMap.containsValue(current)) {
				tsMap.put(current.getTheme(), current);
			}
		}
		
	}

	/**
     * This computation is based on the priority of the results : - priority 1 :
     * Failed - priority 2 : NMI - priority 3 : Not Tested - priority 4 : Passed
     * - priority 5 : NA
     *
     * If at least one of the result type is found regarding the priority
     * definition, the criterion result is the result type
     *
     * @param crs
     * @param criterionTestListSize
     */
    private void computeCriterionResult(CriterionStatistics crs) {
        if (crs.getNbOfFailed() > 0) {  // at least one test is failed, the criterion is failed
            crs.setCriterionResult(TestSolution.FAILED);
        } else if (crs.getNbOfNmi() > 0) { // at least one test is nmi and no failed test encountered, the criterion is nmi
            crs.setCriterionResult(TestSolution.NEED_MORE_INFO);
        } else if (crs.getNbOfNotTested() > 0) {
            crs.setCriterionResult(TestSolution.NOT_TESTED);
        } else if (crs.getNbOfPassed() > 0) {
            crs.setCriterionResult(TestSolution.PASSED);
        } else if (crs.getNbOfNa() > 0) {
            crs.setCriterionResult(TestSolution.NOT_APPLICABLE);
        } else {
            crs.setCriterionResult(TestSolution.NEED_MORE_INFO);
        }
    }

	/**
    *
    * @param testSolution
    * @param criterion
    * @param wrs
    */
   private void addResultToCriterionCounterMap(
           TestSolution testSolution,
           Criterion criterion,
           WebResourceStatistics wrs,
           Map<Criterion, CriterionStatistics> csMap) {
       if (csMap.containsKey(criterion)) {
           CriterionStatistics cs = csMap.get(criterion);
           incrementCriterionCounterFromTestSolution(cs, testSolution);
       } else {
           CriterionStatistics cs = new CriterionStatisticsImpl();
           cs.setCriterion(criterion);
           incrementCriterionCounterFromTestSolution(cs, testSolution);
           csMap.put(criterion, cs);
       }
   }
   
   /**
   *
   * @param testSolution
   * @param criterion
   * @param wrs
   */
  private void addResultToThemeCounterMap(
          TestSolution testSolution,
          Theme theme,
          WebResourceStatistics wrs,
          Map<Theme, ThemeStatistics> tsMap) {
      if (tsMap.containsKey(theme)) {
          ThemeStatistics ts = tsMap.get(theme);
          incrementThemeCounterFromTestSolution(ts, testSolution);
      } else {
          ThemeStatistics ts = new ThemeStatisticsImpl();
          ts.setTheme(theme);
          incrementThemeCounterFromTestSolution(ts, testSolution);
          tsMap.put(theme, ts);
      }
  }
  
  /**
   * 
   * @param ts
   * @param testSolution 
   */
  private void incrementThemeCounterFromTestSolution(
          ThemeStatistics ts,
          TestSolution testSolution) {
      switch (testSolution) {
          case PASSED:
              ts.setNbOfPassed(ts.getNbOfPassed() + 1);
              break;
          case FAILED:
              ts.setNbOfFailed(ts.getNbOfFailed() + 1);
              break;
          case NOT_APPLICABLE:
              ts.setNbOfNa(ts.getNbOfNa() + 1);
              break;
          case NEED_MORE_INFO:
          case DETECTED:
          case SUSPECTED_FAILED:
          case SUSPECTED_PASSED:
              ts.setNbOfNmi(ts.getNbOfNmi() + 1);
              break;
          case NOT_TESTED:
              ts.setNbOfNotTested(ts.getNbOfNotTested() + 1);
              break;
      }
  }
  
  /**
   * 
   * @param cs
   * @param testSolution 
   */
  private void incrementCriterionCounterFromTestSolution(
          CriterionStatistics cs,
          TestSolution testSolution) {
      switch (testSolution) {
          case PASSED:
              cs.setNbOfPassed(cs.getNbOfPassed() + 1);
              break;
          case FAILED:
              cs.setNbOfFailed(cs.getNbOfFailed() + 1);
              break;
          case NOT_APPLICABLE:
              cs.setNbOfNa(cs.getNbOfNa() + 1);
              break;
          case NEED_MORE_INFO:
          case DETECTED:
          case SUSPECTED_FAILED:
          case SUSPECTED_PASSED:
              cs.setNbOfNmi(cs.getNbOfNmi() + 1);
              break;
          case NOT_TESTED:
              cs.setNbOfNotTested(cs.getNbOfNotTested() + 1);
              break;
      }
  }

public CriterionStatisticsDAO getCriterionStatisticsDAO() {
	return criterionStatisticsDAO;
}

public void setCriterionStatisticsDAO(
		CriterionStatisticsDAO criterionStatisticsDAO) {
	this.criterionStatisticsDAO = criterionStatisticsDAO;
}

public ThemeStatisticsDAO getThemeStatisticsDAO() {
	return themeStatisticsDAO;
}

public void setThemeStatisticsDAO(ThemeStatisticsDAO themeStatisticsDAO) {
	this.themeStatisticsDAO = themeStatisticsDAO;
}


}