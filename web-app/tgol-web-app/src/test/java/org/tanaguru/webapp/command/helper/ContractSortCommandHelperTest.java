/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015 Tanaguru.org
 * 
 *  This file is part of Tanaguru.
 * 
 *  Tanaguru is free software: you can redistribute it and/or modify
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
 *  Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.webapp.command.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import junit.framework.TestCase;
import org.displaytag.properties.SortOrderEnum;
import org.tanaguru.webapp.command.ContractSortCommand;
import org.tanaguru.webapp.presentation.data.ActInfo;
import org.tanaguru.webapp.presentation.data.ActInfoImpl;
import org.tanaguru.webapp.presentation.data.ContractInfo;
import org.tanaguru.webapp.presentation.data.ContractInfoImpl;

/**
 *
 * @author jkowalczyk
 */
public class ContractSortCommandHelperTest extends TestCase {
    
    public ContractSortCommandHelperTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getLastAuditMarkSortValue method, of class ContractSortCommandHelper.
     */
    public void testGetLastAuditMarkSortValue() {
        System.out.println("getLastAuditMarkSortValue");

        List<ContractInfo> contractInfoSet = new ArrayList<>(); 
        ContractSortCommand csc = new ContractSortCommand();
        
        ContractInfo c1 = new ContractInfoImpl();
        ActInfo lastActInfo1 = new ActInfoImpl(); 
        lastActInfo1.setRawMark(15);
        c1.setLastActInfo(lastActInfo1);
        c1.setLabel(("A1"));
        contractInfoSet.add(c1);
        
        ContractInfo c2 = new ContractInfoImpl();
        ActInfo lastActInfo2 = new ActInfoImpl(); 
        lastActInfo2.setRawMark(95);
        c2.setLastActInfo(lastActInfo2);
        c2.setLabel(("B2"));
        contractInfoSet.add(c2);
        
        ContractInfo c3 = new ContractInfoImpl();
        ActInfo lastActInfo3 = new ActInfoImpl(); 
        lastActInfo3.setRawMark(53);
        c3.setLastActInfo(lastActInfo3);
        c3.setLabel(("C3"));
        contractInfoSet.add(c3);
        
        ContractInfo c4 = new ContractInfoImpl();
        c4.setLastActInfo(null);
        c4.setLabel(("D4"));
        contractInfoSet.add(c4);
        
        ContractInfo c5 = new ContractInfoImpl();
        c5.setLastActInfo(null);
        c5.setLabel(("A4"));
        contractInfoSet.add(c5);
        
        ContractSortCommandHelper.setSortByKey("SORTBY");
        ContractSortCommandHelper.setLastAuditMarkSortValue("MARK");
        ContractSortCommandHelper.setSortOrderKey("SORTORDER");
        Map<String, Object> map = new HashMap<>();
        map.put("SORTORDER", SortOrderEnum.ASCENDING.getCode());
        map.put("SORTBY", "MARK");

        csc.setSortOptionMap(map);
        ContractSortCommandHelper.sortContractInfoSetRegardingCommand(contractInfoSet, csc);
        
        map = new HashMap<>();
        map.put("SORTORDER", SortOrderEnum.DESCENDING.getCode());
        map.put("SORTBY", "MARK");

        csc.setSortOptionMap(map);
        ContractSortCommandHelper.sortContractInfoSetRegardingCommand(contractInfoSet, csc);
        
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

}
