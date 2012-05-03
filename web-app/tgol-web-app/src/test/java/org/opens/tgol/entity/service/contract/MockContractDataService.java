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
package org.opens.tgol.entity.service.contract;

import java.util.*;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;
import org.opens.tanaguru.sdk.entity.factory.GenericFactory;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.contract.ContractImpl;
import org.opens.tgol.entity.functionality.Functionality;
import org.opens.tgol.entity.functionality.FunctionalityImpl;
import org.opens.tgol.entity.option.Option;
import org.opens.tgol.entity.option.OptionElement;
import org.opens.tgol.entity.option.OptionElementImpl;
import org.opens.tgol.entity.option.OptionImpl;
import org.opens.tgol.entity.service.user.UserDataService;
import org.opens.tgol.entity.user.User;

/**
 *
 * @author jkowalczyk
 */
public class MockContractDataService implements ContractDataService {

    Map<Long, Contract> contractMap = new HashMap<Long, Contract>();

    public void setUserDataService(UserDataService userDataService) {
        User user = userDataService.read(Long.valueOf(1));
        initialiseContractMap(user);
    }

    public MockContractDataService(){}

    private void initialiseContractMap(User user) {
        Functionality functionality1 = new FunctionalityImpl();
        functionality1.setCode("FUNCTIONALITY1");
        functionality1.setId(Long.valueOf(1));
        addContract(Long.valueOf(1), "Test Contract 1", "http://www.test1.com", null, null, false, user, functionality1);
        
        Functionality functionality2 = new FunctionalityImpl();
        functionality2.setCode("FUNCTIONALITY2");
        functionality2.setId(Long.valueOf(2));
        addContract(Long.valueOf(2), "Test Contract 2", "http://www.test2.com", null, null, false, user, functionality2);
    }

    private Contract addContract(
            Long id,
            String label,
            String url,
            String optionCode,
            String optionElementValue,
            boolean isOptionRestriction,
            User user,
            Functionality functionality) {
        Contract contract = new ContractImpl();
        contract.setId(id);
        contract.setLabel(label);
        addOptionToContract(contract, "DOMAIN", url, false);
        contract.setUser(user);
        contract.addFunctionality(functionality);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(2010, 01, 01);
        contract.setBeginDate(calendar.getTime());
        calendar.set(2030, 01, 01);
        contract.setEndDate(calendar.getTime());
        if (optionElementValue != null && optionCode != null) {
            Option option = new OptionImpl();
            option.setIsRestriction(isOptionRestriction);
            option.setCode(optionCode);
            OptionElement optionElement = new OptionElementImpl();
            optionElement.setOption(option);
            optionElement.setValue(optionElementValue);
            contract.addOptionElement(optionElement);
        }
        contractMap.put(id, contract);
        user.addContract(contract);
        return contract;
    }

    private void addOptionToContract(
            Contract contract,
            String optionCode,
            String optionElementValue,
            boolean isOptionRestriction) {
        if (optionElementValue != null && optionCode != null) {
            Option option = new OptionImpl();
            option.setIsRestriction(isOptionRestriction);
            option.setCode(optionCode);
            OptionElement optionElement = new OptionElementImpl();
            optionElement.setOption(option);
            optionElement.setValue(optionElementValue);
            contract.addOptionElement(optionElement);
        }
    }
    
    @Override
    public Collection<Contract> getAllContractsByUser(User user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Contract create() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void create(Contract e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Contract e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long k) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Set<Contract> set) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<? extends Contract> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Contract read(Long k) {
        return contractMap.get(k);
    }

    @Override
    public Contract saveOrUpdate(Contract e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Contract> saveOrUpdate(Set<Contract> set) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityDao(GenericDAO<Contract, Long> gdao) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEntityFactory(GenericFactory<Contract> gf) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Contract update(Contract e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getUrlFromContractOption(Contract contract) {
        for (OptionElement oe : contractMap.get(contract.getId()).getOptionElementSet()) {
            if (oe.getOption().getCode().equalsIgnoreCase("DOMAIN")) {
                return oe.getValue();
            }
        }
        return "";
    }

}