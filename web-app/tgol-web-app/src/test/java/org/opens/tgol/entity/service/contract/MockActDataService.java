/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2012  Open-S Company
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
package org.opens.tgol.entity.service.contract;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;
import org.opens.tanaguru.sdk.entity.factory.GenericFactory;
import org.opens.tgol.entity.contract.Act;
import org.opens.tgol.entity.contract.ActImpl;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.contract.Scope;
import org.opens.tgol.entity.contract.ScopeEnum;
import org.opens.tgol.entity.contract.TgsiScopeImpl;

/**
 *
 * @author jkowalczyk
 */
public class MockActDataService implements ActDataService {

    private ContractDataService contractDataService;
    public void setContractDataService(ContractDataService contractDataService) {
        this.contractDataService = contractDataService;
    }
    
    @Override
    public int getNumberOfAct(Contract contract) {
        return 1;
    }

    @Override
    public Collection<Act> getAllActsByContract(Contract contract) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<Act> getActsByContract(Contract contract, int nbOfActs, int sortDirection, ScopeEnum scopeEnum, boolean onlyCompleted) {
        return new ArrayList<Act>();
    }

    @Override
    public Collection<Act> getRunningActsByContract(Contract contract) {
        return new ArrayList<Act>();
    }

    @Override
    public Act getActFromWebResource(WebResource webResource) {
        Act act = new ActImpl();
        act.setWebResource(webResource);
        act.setContract(contractDataService.read(Long.valueOf(1)));
        Scope pageScope = new TgsiScopeImpl();
        pageScope.setCode(ScopeEnum.PAGE);
        Scope siteScope = new TgsiScopeImpl();
        siteScope.setCode(ScopeEnum.DOMAIN);
        if (webResource instanceof Page) {
            act.setScope(pageScope);
        } else if (webResource instanceof Site) {
            act.setScope(siteScope);
        }
        return act;
    }

    @Override
    public Act getActFromWebResource(Long webResourceId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getNumberOfActByPeriodAndIp(Contract contract, Date limitDate, String ip) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Act create() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void create(Act entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Act entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Set<Act> entitySet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<? extends Act> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Act read(Long key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Act saveOrUpdate(Act entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Act> saveOrUpdate(Set<Act> entitySet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityDao(GenericDAO<Act, Long> dao) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityFactory(GenericFactory<Act> factory) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Act update(Act entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}