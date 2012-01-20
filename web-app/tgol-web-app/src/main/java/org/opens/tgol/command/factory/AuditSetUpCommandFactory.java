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
package org.opens.tgol.command.factory;

import org.opens.tgol.command.AuditSetUpCommand;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.decorator.tanaguru.parameterization.ParameterDataServiceDecorator;
import org.opens.tgol.entity.product.Restriction;
import org.opens.tgol.entity.product.ScopeEnum;
import org.opens.tgol.entity.user.User;
import org.opens.tgol.form.NumericalFormField;
import org.opens.tgol.form.SelectElement;
import org.opens.tgol.form.SelectFormField;
import org.opens.tgol.form.TextualFormField;
import org.opens.tgol.form.parameterization.AuditSetUpFormField;
import org.opens.tgol.form.parameterization.builder.AuditSetUpFormFieldBuilderImpl;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 *
 * @author jkowalczyk
 */
public final class AuditSetUpCommandFactory {

    private ParameterDataServiceDecorator parameterDataService;
    public ParameterDataServiceDecorator getParameterDataService() {
        return parameterDataService;
    }

    @Autowired
    public void setParameterDataService(ParameterDataServiceDecorator parameterDataService) {
        this.parameterDataService = parameterDataService;
    }

    private static AuditSetUpCommandFactory auditSetUpCommandFactory;

    /**
     * Factory has default constructor
     */
    private AuditSetUpCommandFactory(){}

    public static synchronized AuditSetUpCommandFactory getInstance() {
        if (auditSetUpCommandFactory == null) {
            auditSetUpCommandFactory = new AuditSetUpCommandFactory();
        }
        return auditSetUpCommandFactory;
    }
    
    /**
     * Return a initialised auditCommand object for the given contract. This object
     * handles the last values selected by the user
     * 
     * @param contract
     * @param auditSetUpFormFieldList
     * @param auditSite
     * @return
     */
    public AuditSetUpCommand getInitialisedAuditCommand (
            Contract contract,
            Map<String, List<AuditSetUpFormField>> parametersMap,
            boolean isAuditSite,
            boolean isUploadAudit,
            boolean isGuestUser) {
        AuditSetUpCommand auditSetUpCommand = new AuditSetUpCommand();
        auditSetUpCommand.setAuditSite(isAuditSite);
        auditSetUpCommand.setUploadAudit(isUploadAudit);
        boolean isDefaultSet = true;
        if (!isAuditSite) {
            if (isUploadAudit) {
                auditSetUpCommand.setFileInputList(getGroupOfFileInput(contract));
            } else {
                auditSetUpCommand.setUrlList(getGroupOfPagesUrl(contract, isGuestUser));
            }
            // the default set is about site audit. If the audit is a page audit
            // by defintion, the parameter set is not default
            isDefaultSet = false;
        }
        auditSetUpCommand.setContractId(contract.getId());
        String defaultValue;
        String lastUserValue;
        for (List<AuditSetUpFormField> apl : parametersMap.values()) {
            for (AuditSetUpFormField ap : apl) {
                defaultValue = getParameterDataService().
                        getDefaultParameter(ap.getParameterElement()).getValue();
                // override default value in case of NumericalFormField if the
                // max value, set by restriction is inferior to the default value
                if (ap.getFormField() instanceof NumericalFormField) {
                    String maxValue = ((NumericalFormField)ap.getFormField()).getMaxValue();
                    if (Integer.valueOf(maxValue).compareTo(Integer.valueOf(defaultValue))<0){
                        defaultValue = maxValue;
                    }
                }
                if (isAuditSite) {
                    lastUserValue = parameterDataService.
                            getLastParameterValueFromUser(contract.getId(), ap.getParameterElement(), ScopeEnum.DOMAIN);
                    if (lastUserValue != null && !lastUserValue.equalsIgnoreCase(defaultValue)) {
                        // we override the auditParameter with the last user value
                        auditSetUpCommand.addAuditParameterEntry(
                                ap.getParameterElement().getParameterElementCode(),
                                lastUserValue);
                        isDefaultSet = false;
                    } else {
                        auditSetUpCommand.addAuditParameterEntry(
                                ap.getParameterElement().getParameterElementCode(),
                                defaultValue);
                    }
                } else {
                    auditSetUpCommand.addAuditParameterEntry(
                            ap.getParameterElement().getParameterElementCode(),
                            defaultValue);
                }
            }
        }
        auditSetUpCommand.setIsDefaultParamSet(isDefaultSet);
        return auditSetUpCommand;
    }

    private CommonsMultipartFile[] getGroupOfFileInput(Contract contract) {
        CommonsMultipartFile[] groupOfFileInput = new CommonsMultipartFile[AuditSetUpCommand.DEFAULT_LIST_SIZE];
        return (contract.getUser() == null) ? null : groupOfFileInput;
    }

    /**
     * This methods prepares the String table passed to the jsp that handles
     * the URL filled-in by the user. Depending the status of the user
     * (authenticated or guest), the table is pre-populated.
     * @param contractId
     * @return
     */
    private List<String> getGroupOfPagesUrl(Contract contract, boolean isGuestUser) {
        User user = contract.getUser();
        List<String> groupOfPagesUrl = new LinkedList<String>();
        if (user == null) {
            return null;
        }
        for (int i = 0; i < AuditSetUpCommand.DEFAULT_LIST_SIZE; i++) {
            groupOfPagesUrl.add(new String());
        }
        // When the form is displayed for an authenticated user, it is
        // pre-populated with the Url recorded with the contract.
        if (contract.getUrl() != null && !contract.getUrl().isEmpty()
                && !isGuestUser) {
            groupOfPagesUrl.set(0, contract.getUrl());
        }
        return groupOfPagesUrl;
    }

    /**
     * Set up the parameters for the given contract.
     * We use the contract to retrieve the parameters of the last audit.
     * If these parameters are identical to the default one, or if this audit is
     * first audit for the contract, we populate each parameter value with the
     * default value. Otherwise we populate the parameters with the last one
     * set by the user.
     *
     * @param restrictionSet
     * @return
     */
    public Map<String, List<AuditSetUpFormField>> getParametersMapCopy(
            Contract contract,
            Map<String, List<AuditSetUpFormFieldBuilderImpl>> auditSetUpFormFieldBuilderMap) {

        // Copy the audit setup form field map from the builders
        Map<String, List<AuditSetUpFormField>> initialisedSetUpFormFielMap = new HashMap<String, List<AuditSetUpFormField>>();
        for (Map.Entry<String, List<AuditSetUpFormFieldBuilderImpl>> entry : auditSetUpFormFieldBuilderMap.entrySet()) {
            List<AuditSetUpFormField> setUpFormFieldList = new LinkedList<AuditSetUpFormField>();
            for (AuditSetUpFormFieldBuilderImpl seb : entry.getValue()) {
                setUpFormFieldList.add(seb.build());
            }
            initialisedSetUpFormFielMap.put(entry.getKey(), setUpFormFieldList);
        }
        Set<? extends Restriction> restrictionSet = contract.getRestrictionSet();
        if (!restrictionSet.isEmpty()) {
            for (List<AuditSetUpFormField> apl : initialisedSetUpFormFielMap.values()) {
                for (AuditSetUpFormField ap : apl) {
                    applyRestrictionToAuditSetUpFormField(ap, restrictionSet);
                }
            }
        }
        return initialisedSetUpFormFielMap;
    }

    /**
     *
     * @param ap
     * @param restrictionSet
     */
    private void applyRestrictionToAuditSetUpFormField(
            AuditSetUpFormField ap,
            Set<? extends Restriction> restrictionSet) {
        if (ap.getFormField() instanceof NumericalFormField) {
            Restriction restriction = getRestrictionFromContractRestrictionSet(
                    restrictionSet,
                    ap.getParameterElement().getParameterElementCode());
            if (restriction != null) {
                ((NumericalFormField) ap.getFormField()).setMaxValue(restriction.getValue());
                ((NumericalFormField) ap.getFormField()).setValue(restriction.getValue());
            }
        } else if (ap.getFormField() instanceof SelectFormField) {
            SelectFormField sff = ((SelectFormField) ap.getFormField());
            Restriction restriction = getRestrictionFromContractRestrictionSet(
                    restrictionSet,
                    sff.getRestrictionCode());
            if (restriction != null) {
                String[] restrictionValues = restriction.getValue().split(";");
                for (int i = 0; i < restrictionValues.length; i++) {
                    List<SelectElement> sel =
                            sff.getSelectElementMap().get(restrictionValues[i]);
                    for (SelectElement se : sel) {
                        se.setEnabled(false);
                    }
                }
            }
        } else if (ap.getFormField() instanceof TextualFormField) {
            Restriction restriction = getRestrictionFromContractRestrictionSet(
                    restrictionSet,
                    ap.getParameterElement().getParameterElementCode());
            if (restriction != null) {
                ((TextualFormField) ap.getFormField()).setValue(restriction.getValue());
            }
        }
    }

    /**
     *
     * @param restrictionSet
     * @param restrictionCode
     * @return
     */
    private Restriction getRestrictionFromContractRestrictionSet(
            Set<? extends Restriction> restrictionSet,
            String restrictionCode) {
        for (Restriction restriction : restrictionSet) {
            if (StringUtils.equals(restrictionCode, restriction.getRestrictionElement().getCode())) {
                return restriction;
            }
        }
        return null;
    }

}