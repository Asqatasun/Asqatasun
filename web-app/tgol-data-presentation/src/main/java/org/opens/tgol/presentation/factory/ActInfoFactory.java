/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
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

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.AuditStatus;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tgol.entity.contract.Act;
import org.opens.tgol.entity.decorator.tanaguru.parameterization.ParameterDataServiceDecorator;
import org.opens.tgol.entity.decorator.tanaguru.subject.WebResourceDataServiceDecorator;
import org.opens.tgol.presentation.data.ActInfo;
import org.opens.tgol.presentation.data.ActInfoImpl;
import org.opens.tgol.util.TgolKeyStore;
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

    private WebResourceDataServiceDecorator webResourceDataService;
    @Autowired
    public void setWebResourceDataService(WebResourceDataServiceDecorator webResourceDataServiceDecorator) {
        this.webResourceDataService = webResourceDataServiceDecorator;
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
    public ActInfo getActInfo(Act act){
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
            
            if (audit.getStatus().equals(AuditStatus.COMPLETED)) {
                actInfo.setWeightedMark(webResourceDataService.getMarkByWebResourceAndAudit(wr, false).intValue());
                actInfo.setRawMark(webResourceDataService.getMarkByWebResourceAndAudit(wr, true).intValue());
                actInfo.setStatus(TgolKeyStore.COMPLETED_KEY);
            } else if (!contentDataService.hasContent(audit)){
                actInfo.setStatus(TgolKeyStore.ERROR_LOADING_KEY);
            } else if (!contentDataService.hasAdaptedSSP(audit)) {
                actInfo.setStatus(TgolKeyStore.ERROR_ADAPTING_KEY);
            } else {
                actInfo.setStatus(TgolKeyStore.ERROR_UNKNOWN_KEY);
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
    public Collection<ActInfo> getActInfoSet(Collection<Act> actSet){
        Set<ActInfo> actInfoSet = new LinkedHashSet<ActInfo>();
        for (Act act : actSet) {
            actInfoSet.add(getActInfo(act));
        }
        return actInfoSet;
    }

    /**
     * Set the referential to the ActInfo regarding the "LEVEL" parameter of the 
     * audit. This parameter handles level and referential. That's why its value
     * needs to be split to extract the referential data.
     * @param actInfo
     * @param audit 
     */
    private void setActInfoReferential(ActInfo actInfo, Audit audit) {
        Set<Parameter> parameterSet = parameterDataService.getParameterSetFromAudit(audit);
        for (Parameter param : parameterSet) {
            if (StringUtils.equals(param.getParameterElement().getParameterElementCode(), "LEVEL")) {
                actInfo.setReferential(param.getValue().split(";")[0]);
            }
        }
    }

}