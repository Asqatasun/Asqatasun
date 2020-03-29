/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2019  Asqatasun.org
 * 
 *  This file is part of Asqatasun.
 * 
 *  Asqatasun is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * 
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.webapp.command.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.asqatasun.webapp.config.TestConfiguration;
import org.asqatasun.webapp.dto.ActInfo;
import org.asqatasun.webapp.dto.ContractInfo;
import org.asqatasun.webapp.dto.factory.ContractInfoFactory;
import org.displaytag.properties.SortOrderEnum;
import org.asqatasun.webapp.command.ContractSortCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author jkowalczyk
 */
@SpringBootTest
@ContextConfiguration(classes = TestConfiguration.class)
@ActiveProfiles({"integration"})
public class ContractSortCommandHelperTest  {


    @MockBean(name = "contractInfoFactory")
    ContractInfoFactory contractInfoFactory;

    @Autowired
    private ContractSortCommandHelper contractSortCommandHelper;

    /**
     * Test of getLastAuditMarkSortValue method, of class ContractSortCommandHelper.
     */
    @Test
    public void testGetLastAuditMarkSortValue() {
        System.out.println("getLastAuditMarkSortValue");

        List<ContractInfo> contractInfoSet = new ArrayList<>();
        ContractSortCommand csc = new ContractSortCommand();
        
        ContractInfo c1 = new ContractInfo();
        ActInfo lastActInfo1 = new ActInfo();
        lastActInfo1.setRawMark(15);
        c1.setLastActInfo(lastActInfo1);
        c1.setLabel(("A1"));
        contractInfoSet.add(c1);
        
        ContractInfo c2 = new ContractInfo();
        ActInfo lastActInfo2 = new ActInfo();
        lastActInfo2.setRawMark(95);
        c2.setLastActInfo(lastActInfo2);
        c2.setLabel(("B2"));
        contractInfoSet.add(c2);
        
        ContractInfo c3 = new ContractInfo();
        ActInfo lastActInfo3 = new ActInfo();
        lastActInfo3.setRawMark(53);
        c3.setLastActInfo(lastActInfo3);
        c3.setLabel(("C3"));
        contractInfoSet.add(c3);
        
        ContractInfo c4 = new ContractInfo();
        c4.setLastActInfo(null);
        c4.setLabel(("D4"));
        contractInfoSet.add(c4);
        
        ContractInfo c5 = new ContractInfo();
        c5.setLastActInfo(null);
        c5.setLabel(("A4"));
        contractInfoSet.add(c5);

        Map<String, Object> map = new HashMap<>();
        map.put("order-choice", SortOrderEnum.ASCENDING.getCode());
        map.put("sort-by-choice", "MARK");

        csc.setSortOptionMap(map);
        contractSortCommandHelper.sortContractInfoSetRegardingCommand(contractInfoSet, csc);

        assertTrue(contractInfoSet.get(0).equals(c1));
        assertTrue(contractInfoSet.get(1).equals(c3));
        assertTrue(contractInfoSet.get(2).equals(c2));

        map = new HashMap<>();
        map.put("order-choice", SortOrderEnum.DESCENDING.getCode());
        map.put("sort-by-choice", "MARK");

        csc.setSortOptionMap(map);
        contractSortCommandHelper.sortContractInfoSetRegardingCommand(contractInfoSet, csc);

        assertTrue(contractInfoSet.get(0).equals(c2));
        assertTrue(contractInfoSet.get(1).equals(c3));
        assertTrue(contractInfoSet.get(2).equals(c1));
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

}
