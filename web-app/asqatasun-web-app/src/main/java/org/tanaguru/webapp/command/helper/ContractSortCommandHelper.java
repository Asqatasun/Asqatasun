/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
package org.tanaguru.webapp.command.helper;

import java.util.*;
import org.apache.commons.lang3.StringUtils;
import org.displaytag.properties.SortOrderEnum;
import org.tanaguru.webapp.command.ContractSortCommand;
import org.tanaguru.webapp.command.factory.ContractSortCommandFactory;
import org.tanaguru.webapp.entity.contract.Contract;
import org.tanaguru.webapp.entity.user.User;
import org.tanaguru.webapp.form.FormField;
import org.tanaguru.webapp.form.builder.FormFieldBuilder;
import org.tanaguru.webapp.form.parameterization.helper.FormFieldHelper;
import org.tanaguru.webapp.presentation.data.ContractInfo;
import org.tanaguru.webapp.presentation.factory.ContractInfoFactory;
import org.tanaguru.webapp.util.TgolKeyStore;
import org.springframework.ui.Model;

/**
 *
 * @author jkowalczyk
 */
public final class ContractSortCommandHelper  {

    private static String lastAuditMarkSortValue;
    public static String getLastAuditMarkSortValue() {
        return lastAuditMarkSortValue;
    }

    public static void setLastAuditMarkSortValue(String lastAuditMarkSortValue) {
        ContractSortCommandHelper.lastAuditMarkSortValue = lastAuditMarkSortValue;
    }
    
    private static String lastAuditDateSortValue;
    public static String getLastAuditDateSortValue() {
        return lastAuditDateSortValue;
    }

    public static void setLastAuditDateSortValue(String lastAuditDateSortValue) {
        ContractSortCommandHelper.lastAuditDateSortValue = lastAuditDateSortValue;
    }

    private static String sortByKey;
    public static String getSortByKey() {
        return sortByKey;
    }

    public static void setSortByKey(String sortByKey) {
        ContractSortCommandHelper.sortByKey = sortByKey;
    }
    
    private static String sortOrderKey;
    public static String getSortOrderKey() {
        return sortOrderKey;
    }

    public static void setSortOrderKey(String sortOrderKey) {
        ContractSortCommandHelper.sortOrderKey = sortOrderKey;
    }

    private static String exclusionContractSortKey;
    public static String getExclusionContractSortKey() {
        return exclusionContractSortKey;
    }

    public static void setExclusionContractSortKey(String exclusionContractSortKey) {
        ContractSortCommandHelper.exclusionContractSortKey = exclusionContractSortKey;
    }
    
    private static String inclusionContractSortKey;
    public static String getInclusionContractSortKey() {
        return inclusionContractSortKey;
    }

    public static void setInclusionContractSortKey(String inclusionContractSortKey) {
        ContractSortCommandHelper.inclusionContractSortKey = inclusionContractSortKey;
    }
    
    /**
     * This methods retrieves and prepare contract info
     * 
     * @param user
     * @param csc the ContractSortCommand
     * @param displayOptionFieldsBuilderList
     * @param model
     * @return 
     */
    public static Collection<ContractInfo> prepareContractInfo (
            User user, 
            ContractSortCommand csc, 
            List<FormFieldBuilder> displayOptionFieldsBuilderList,
            Model model) {
        
        csc = prepareDataForSortConsole(user.getId(), csc, displayOptionFieldsBuilderList, model);

        List<ContractInfo> contractInfoSet = new LinkedList();
        List<String> inclusionSortOccurence;
        if (csc.getSortOptionMap().containsKey(inclusionContractSortKey))  {
            inclusionSortOccurence = Arrays.asList(csc.getSortOptionMap().get(inclusionContractSortKey).toString().split(";"));
        } else {
            inclusionSortOccurence = new ArrayList();
        }
        List<String> exclusionSortOccurence;
        if (csc.getSortOptionMap().containsKey(exclusionContractSortKey))  {
            exclusionSortOccurence = Arrays.asList(csc.getSortOptionMap().get(exclusionContractSortKey).toString().split(";"));
        } else {
            exclusionSortOccurence = new ArrayList();
        }
        for (Contract contract : user.getContractSet()) {
            if (isContractLabelIncluded(inclusionSortOccurence, contract.getLabel()) &&
                    !isContractLabelExcluded(exclusionSortOccurence, contract.getLabel())) {
                contractInfoSet.add(ContractInfoFactory.getInstance().getContractInfo(contract));
            }
        }
        if (csc.getSortOptionMap().containsKey(sortOrderKey)) {
            sortContractInfoSetRegardingCommand(contractInfoSet, csc);
        }    
        return contractInfoSet;
    }
    
    /**
     * This methods retrieves and prepare contract info
     * 
     * @param user
     * @param csc the ContractSortCommand
     * @param displayOptionFieldsBuilderList
     * @param model
     * @return 
     */
    public static Collection<Contract> prepareContract (
            User user, 
            ContractSortCommand csc, 
            List<FormFieldBuilder> displayOptionFieldsBuilderList,
            Model model) {
        
        csc = prepareDataForSortConsole(
                user.getId(), 
                csc, 
                displayOptionFieldsBuilderList, 
                model);
        List<Contract> contractSet = new LinkedList();
        List<String> inclusionSortOccurence = 
                Arrays.asList(csc.getSortOptionMap().get(inclusionContractSortKey).toString().split(";"));
        List<String> exclusionSortOccurence = 
                Arrays.asList(csc.getSortOptionMap().get(exclusionContractSortKey).toString().split(";"));
        for (Contract contract : user.getContractSet()) {
            if (isContractLabelIncluded(inclusionSortOccurence, contract.getLabel()) &&
                    !isContractLabelExcluded(exclusionSortOccurence, contract.getLabel())) {
                contractSet.add(contract);
            }
        }
        sortContractSetRegardingCommand(contractSet, csc);
        return contractSet;
    }

    private static boolean isContractLabelIncluded(List<String> inclusionList, String contractLabel) {
        if (inclusionList.isEmpty() || 
                (inclusionList.size() == 1 && 
                 StringUtils.isEmpty(inclusionList.get(0).trim()))) {
            return true;
        }
        for (String inclusion : inclusionList) {
            if (contractLabel.contains(inclusion)) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean isContractLabelExcluded(List<String> exclusionList, String contractLabel) {
        if (exclusionList.isEmpty() || 
                (exclusionList.size() == 1 && 
                 StringUtils.isEmpty(exclusionList.get(0).trim()))) {
            return false;
        }
        for (String exclusion : exclusionList) {
            if (contractLabel.contains(exclusion)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Sort a collection of scenarios by date of creation
     */
    private static class ContractInfoLabelSorter implements Comparator<ContractInfo> {
        SortOrderEnum sortOrder;
        public ContractInfoLabelSorter(int sortOrder) {
            this.sortOrder = SortOrderEnum.fromCode(sortOrder);
        }
        
        @Override
        public int compare(ContractInfo c, ContractInfo c1) {
            if (sortOrder.equals(SortOrderEnum.ASCENDING)) {
                return c1.getLabel().compareToIgnoreCase(c.getLabel());
            } else if (sortOrder.equals(SortOrderEnum.DESCENDING)){
                return c.getLabel().compareToIgnoreCase(c1.getLabel());
            }
            return c1.getLabel().compareToIgnoreCase(c.getLabel());
        }
    }
    
    /**
     * Sort a collection of scenarios by date of creation
     */
    private static class ContractLabelSorter implements Comparator<Contract> {
        SortOrderEnum sortOrder;
        public ContractLabelSorter(int sortOrder) {
            this.sortOrder = SortOrderEnum.fromCode(sortOrder);
        }
        
        @Override
        public int compare(Contract c, Contract c1) {
            if (sortOrder.equals(SortOrderEnum.ASCENDING)) {
                return c1.getLabel().compareToIgnoreCase(c.getLabel());
            } else if (sortOrder.equals(SortOrderEnum.DESCENDING)){
                return c.getLabel().compareToIgnoreCase(c1.getLabel());
            }
            return c1.getLabel().compareToIgnoreCase(c.getLabel());
        }
    }
    
    private static class ContractInfoMarkSorter implements Comparator<ContractInfo> {
        SortOrderEnum sortOrder;
        public ContractInfoMarkSorter(int sortOrder) {
            this.sortOrder = SortOrderEnum.fromCode(sortOrder);
        }
        
        @Override
        public int compare(ContractInfo c, ContractInfo c1) {
            if (sortOrder.equals(SortOrderEnum.ASCENDING)) {
                if (c1.getLastActInfo() != null 
                        && c.getLastActInfo() != null) {
                    return Integer.valueOf(c1.getLastActInfo().getRawMark())
                        .compareTo(c.getLastActInfo().getRawMark());
                } else if (c1.getLastActInfo() == null 
                        && c.getLastActInfo() == null) {
                    return c.getLabel().compareToIgnoreCase(c1.getLabel());
                } else if (c1.getLastActInfo() == null) {
                    return -1;
                } else if (c.getLastActInfo() == null) {
                    return 1;
                }
            } else if (sortOrder.equals(SortOrderEnum.DESCENDING)){
                if (c1.getLastActInfo() != null 
                        && c.getLastActInfo() != null) {
                    return Integer.valueOf(c.getLastActInfo().getRawMark())
                        .compareTo(c1.getLastActInfo().getRawMark());
                } else if (c1.getLastActInfo() == null 
                        && c.getLastActInfo() == null) {
                    return c.getLabel().compareToIgnoreCase(c1.getLabel());
                } else if (c1.getLastActInfo() == null) {
                    return -1;
                } else if (c.getLastActInfo() == null) {
                    return 1;
                }
            }
            return 0;
        }
    }
    
    private static class ContractInfoDateSorter implements Comparator<ContractInfo> {
        SortOrderEnum sortOrder;
        public ContractInfoDateSorter(int sortOrder) {
            this.sortOrder = SortOrderEnum.fromCode(sortOrder);
        }
        
        @Override
        public int compare(ContractInfo c, ContractInfo c1) {
            if (sortOrder.equals(SortOrderEnum.ASCENDING)) {
                if (c1.getLastActInfo() != null && c.getLastActInfo() != null) {
                    return c1.getLastActInfo().getDate()
                        .compareTo(
                            c.getLastActInfo().getDate());
                } else if (c1.getLastActInfo() == null && c.getLastActInfo() == null) {
                    return c.getLabel().compareTo(c1.getLabel());
                } else if (c1.getLastActInfo() == null) {
                    return -1;
                } else if (c.getLastActInfo() == null) {
                    return 1;
                }
            } else if (sortOrder.equals(SortOrderEnum.DESCENDING)){
                if (c1.getLastActInfo() != null && c.getLastActInfo() != null) {
                    return c.getLastActInfo().getDate()
                        .compareTo(
                            c1.getLastActInfo().getDate());
                } else if (c1.getLastActInfo() == null && c.getLastActInfo() == null) {
                    return c.getLabel().compareTo(c1.getLabel());
                } else if (c1.getLastActInfo() == null) {
                    return -1;
                } else if (c.getLastActInfo() == null) {
                    return 1;
                }
            }
            return 0;
        }
    }
    
    /**
     * 
     * @param contractInfoSet
     * @param csc the ContractSortCommand 
     */
    public static void sortContractInfoSetRegardingCommand(
            List<ContractInfo> contractInfoSet, 
            ContractSortCommand csc) {
        String sortByValue = csc.getSortOptionMap().get(sortByKey).toString();

        if (StringUtils.equalsIgnoreCase(sortByValue, lastAuditMarkSortValue)) {
            Collections.sort(
                contractInfoSet, 
                new ContractInfoMarkSorter(
                    Integer.valueOf(csc.getSortOptionMap().get(sortOrderKey).toString())));
        } else if (StringUtils.equalsIgnoreCase(sortByValue, lastAuditDateSortValue)) {
            Collections.sort(
                contractInfoSet, 
                new ContractInfoDateSorter(
                    Integer.valueOf(csc.getSortOptionMap().get(sortOrderKey).toString())));
        } else {
            Collections.sort(
                contractInfoSet, 
                new ContractInfoLabelSorter(Integer.valueOf(csc.getSortOptionMap().get(sortOrderKey).toString())));
        }
    }
    
    /**
     * 
     * @param contractInfoSet
     * @param contractSortCommand 
     */
    private static void sortContractSetRegardingCommand(
            List<Contract> contractSet, 
            ContractSortCommand csc) {
        // By default if the choice is not alphabetical, the contracts will be
        // sorted by alphabetical order in the second time. If the choice is 
        // alphabetical, this value will be overidden with the user value.
        int alphabeticalSortDirection = 
                    Integer.valueOf(csc.getSortOptionMap().get(sortOrderKey).toString());
        Collections.sort(
                contractSet, 
                new ContractLabelSorter(alphabeticalSortDirection));
    }
    
    /**
     * This method prepares the data to be displayed in the sort 
     * (score, alphabetical, date) console of the result page.
     */
    private static ContractSortCommand prepareDataForSortConsole(
            Long userId, 
            ContractSortCommand contractSortCommand, 
            List<FormFieldBuilder> displayOptionFieldsBuilderList,
            Model model) {
        
        ContractSortCommand cdc;
        List<FormField> formFieldList;
        
        if (contractSortCommand == null) {
            formFieldList = FormFieldHelper.getFormFieldBuilderCopy(
                    displayOptionFieldsBuilderList);
            cdc = ContractSortCommandFactory.getInstance().getInitialisedContractDisplayCommand(
                        userId,
                        formFieldList);
        } else {
            formFieldList = FormFieldHelper.getFormFieldBuilderCopy(
                    displayOptionFieldsBuilderList, 
                    contractSortCommand.getSortOptionMap());
            cdc = contractSortCommand;
        }

        model.addAttribute(TgolKeyStore.CONTRACT_SORT_FIELD_LIST_KEY, formFieldList);
        model.addAttribute(TgolKeyStore.CONTRACT_SORT_COMMAND_KEY, cdc);

        return cdc;
    }
    
}