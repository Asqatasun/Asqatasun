/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.tanaguru.webapp.presentation.factory;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.audit.AuditStatus;
import org.tanaguru.entity.service.audit.ContentDataService;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.webapp.entity.contract.Act;
import org.tanaguru.webapp.entity.decorator.tanaguru.parameterization.ParameterDataServiceDecorator;
import org.tanaguru.webapp.entity.service.statistics.StatisticsDataService;
import org.tanaguru.webapp.presentation.data.ActInfo;
import org.tanaguru.webapp.presentation.data.ActInfoImpl;
import org.tanaguru.webapp.util.TgolKeyStore;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public final class ActInfoFactory {

    private ContentDataService contentDataService;
    @Autowired
    public void setContentDataService(ContentDataService contentDataService) {
        this.contentDataService = contentDataService;
    }
    
    private StatisticsDataService statisticsDataService;
    @Autowired
    public void setStatisticsDataService(StatisticsDataService statisticsDataService) {
        this.statisticsDataService = statisticsDataService;
    }
    
    private ParameterDataServiceDecorator parameterDataService;
    @Autowired
    public void setParameterDataService(ParameterDataServiceDecorator parameterDataServiceDecorator) {
        this.parameterDataService = parameterDataServiceDecorator;
    }

    /**
     * The unique shared instance of ActInfoFactory
     */
    private static ActInfoFactory actInfoFactory;

    /**
     * Default private constructor
     */
    private ActInfoFactory(){}

    public static synchronized ActInfoFactory getInstance(){
        if (actInfoFactory == null) {
            actInfoFactory = new ActInfoFactory();
        }
        return actInfoFactory;
    }

    /**
     * 
     * @param act
     * @return an ActInfo instance that handles displayable act data
     * 
     */
    public ActInfo getActInfo(Act act) {
        ActInfoImpl actInfo = new ActInfoImpl();
        actInfo.setDate(act.getEndDate());
        if (act.getAudit() != null) {
            Audit audit = act.getAudit();

            actInfo.setAuditId(audit.getId().intValue());
            actInfo.setScope(act.getScope().getCode().name());

            WebResource wr = audit.getSubject();
            if (wr != null) {
                actInfo.setUrl(wr.getURL());
            }

            //cas automatique
            if (audit.getStatus().equals(AuditStatus.COMPLETED) || audit.getStatus().equals(AuditStatus.MANUAL_INITIALIZING)
                    || audit.getStatus().equals(
                            AuditStatus.MANUAL_ANALYSE_IN_PROGRESS)
                    || audit.getStatus().equals(AuditStatus.MANUAL_COMPLETED)) {
                actInfo.setWeightedMark(statisticsDataService.getMarkByWebResourceAndAudit(wr, false, false).intValue());
                actInfo.setRawMark(statisticsDataService.getMarkByWebResourceAndAudit(wr, true, false).intValue());
                if (actInfo.getRawMark() == -1) {
                    actInfo.setRawMark(0);
                }
                actInfo.setStatus(TgolKeyStore.COMPLETED_KEY);
            } else if (!contentDataService.hasContent(audit)) {
                actInfo.setStatus(TgolKeyStore.ERROR_LOADING_KEY);
            } else if (!contentDataService.hasAdaptedSSP(audit)) {
                actInfo.setStatus(TgolKeyStore.ERROR_ADAPTING_KEY);
            } else {
                actInfo.setStatus(TgolKeyStore.ERROR_UNKNOWN_KEY);
            }

            //cas manual
            actInfo.setManual(audit.getStatus().equals(AuditStatus.MANUAL_INITIALIZING)
                    || audit.getStatus().equals(
                            AuditStatus.MANUAL_ANALYSE_IN_PROGRESS)
                    || audit.getStatus().equals(AuditStatus.MANUAL_COMPLETED));
            if (actInfo.isManual()) {

                actInfo.setDateManual(audit.getManualAuditDateOfCreation());
                actInfo.setWeightedMarkManual(statisticsDataService
                        .getMarkByWebResourceAndAudit(wr, false, true)
                        .intValue());
                actInfo.setRawMarkManual(statisticsDataService
                        .getMarkByWebResourceAndAudit(wr, true, true)
                        .intValue());

                if (audit.getStatus().equals(AuditStatus.MANUAL_COMPLETED)) {
                    actInfo.setStatusManual(TgolKeyStore.COMPLETED_KEY);
                } else if (audit.getStatus().equals(
                        AuditStatus.MANUAL_ANALYSE_IN_PROGRESS)
                        || audit.getStatus().equals(
                                AuditStatus.MANUAL_INITIALIZING)) {
                    actInfo.setStatusManual(TgolKeyStore.ONGOING_KEY);

                } else {
                    actInfo.setStatusManual(TgolKeyStore.ERROR_UNKNOWN_KEY);
                }

            }
            setActInfoReferential(actInfo, audit);
        }

        return actInfo;
    }

    /**
     *
     * @param actSet
     * @return collection of ActInfo instances that handle displayable Act data
     *
     */
    public Collection<ActInfo> getActInfoSet(Collection<Act> actSet) {
        Set<ActInfo> actInfoSet = new LinkedHashSet<>();
        for (Act act : actSet) {
            actInfoSet.add(getActInfo(act));
        }
        return actInfoSet;
    }

    /**
     * Set the referential to the ActInfo interrogating the parameterDataService
     *
     * @param actInfo
     * @param audit
     */
    private void setActInfoReferential(ActInfo actInfo, Audit audit) {
        actInfo.setReferential(parameterDataService.getReferentialKeyFromAudit(audit));
    }

}