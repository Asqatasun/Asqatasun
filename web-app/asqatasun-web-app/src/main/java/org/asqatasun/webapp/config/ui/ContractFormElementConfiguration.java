/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
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
package org.asqatasun.webapp.config.ui;

import org.asqatasun.entity.service.option.OptionDataService;
import org.asqatasun.webapp.ui.form.builder.*;
import org.asqatasun.webapp.ui.form.parameterization.builder.ContractOptionFormFieldBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class ContractFormElementConfiguration {

    final OptionDataService optionDataService;

    public ContractFormElementConfiguration(OptionDataService optionDataService) {
        this.optionDataService = optionDataService;
    }

    @Bean
    public SelectElementBuilder markContractSelectElementBuilder() {
        return buildSelectElementBuilder(
            "mark",
            "last-audit-score-option",
            "last-audit-score-option-error",
            false,
            true);
    }
    @Bean
    public SelectElementBuilder alphabeticalContractSelectElementBuilder() {
        return buildSelectElementBuilder(
            "label",
            "label-option",
            "label-option-error",
            true,
            true);
    }
    @Bean
    public SelectElementBuilder dateContractSelectElementBuilder() {
        return buildSelectElementBuilder(
            "date",
            "last-audit-date-option",
            "last-audit-date-option-error",
            false,
            true);
    }
    @Bean
    public SelectFormFieldBuilder displayContractElementSelectFormFieldBuilder() {
        return buildSelectFormFieldBuilder(
            "sort-by-choice",
            "sort-by-choice-error",
            Collections.singletonMap(
                "sort-by-choice",
                Arrays.asList(
                    dateContractSelectElementBuilder(),
                    alphabeticalContractSelectElementBuilder(),
                    markContractSelectElementBuilder()))
        );
    }
    @Bean
    public SelectElementBuilder ascendingContractSelectElementBuilder() {
        return buildSelectElementBuilder(
            "1",
            "ascending-option",
            "ascending-option-error",
            true,
            true);
    }
    @Bean
    public SelectElementBuilder descendingContractSelectElementBuilder() {
        return buildSelectElementBuilder(
            "2",
            "descending-option",
            "descending-option-error",
            false,
            true);
    }
    @Bean
    public SelectFormFieldBuilder displayContractOrderSelectFormFieldBuilder() {
        return buildSelectFormFieldBuilder(
            "order-choice",
            "order-choice-error",
            Collections.singletonMap(
                "order-choice",
                Arrays.asList(
                    ascendingContractSelectElementBuilder(),
                    descendingContractSelectElementBuilder()))
        );
    }
    @Bean
    public FormFieldBuilder inclusionRegexpContractFormFieldBuilder() {
        TextualFormFieldBuilder builder =
            buildTextualFormFieldBuilder("label-inclusion-choice", "label-inclusion-choice-error");
        builder.setValue("");
        return builder;
    }
    @Bean
    public FormFieldBuilder exclusionRegexpContractFormFieldBuilder() {
        TextualFormFieldBuilder builder =
            buildTextualFormFieldBuilder("label-exclusion-choice", "label-exclusion-choice-error");
        builder.setValue("");
        return builder;
    }
    @Bean
    public List<FormFieldBuilder> displayOptionFieldsBuilderList() {
        return Arrays.asList(
            displayContractElementSelectFormFieldBuilder(),
            displayContractOrderSelectFormFieldBuilder(),
            inclusionRegexpContractFormFieldBuilder(),
            exclusionRegexpContractFormFieldBuilder());
    }

    @Bean
    public HashMap <String, List <ContractOptionFormFieldBuilder>> contractOptionFormFieldBuilderMap() {
        HashMap<String, List <ContractOptionFormFieldBuilder>> map = new HashMap <>();
        map.put("general-options", Arrays.asList(
            nbMaxAuditsPerContractOptionBuilder(),
            nbMaxDomainAuditsPerContractOptionBuilder(),
            nbMaxPagesAuditsPerContractOptionBuilder(),
            nbMaxUploadAuditsPerContractOptionBuilder(),
            nbMaxScenarioAuditsPerContractOptionBuilder(),
            nbMaxActsToDisplayPerContractOptionBuilder()
        ));
        map.put("crawl-options", Arrays.asList(
            depthOptionBuilder(),
            maxDurationOptionBuilder(),
            maxDocumentsOptionBuilder(),
            exclusionRegexpOptionBuilder()
        ));
        return map;
    }

    @Bean
    public NumericalFormFieldBuilderImpl nbMaxAuditsPerContractFormFieldBuilder() {
        return buildNumericalFormFieldBuilder(
            "10000",
            "0",
            "nb-max-audits-per-contract",
            "nb-max-audits-per-contract-error");
    }

    @Bean
    public ContractOptionFormFieldBuilder nbMaxAuditsPerContractOptionBuilder() {
        return buildContractOptionFormFieldBuilder(
            "ACT_LIMITATION",
            nbMaxAuditsPerContractFormFieldBuilder());
    }

    @Bean
    public NumericalFormFieldBuilderImpl nbMaxDomainAuditsPerContractFormFieldBuilder() {
        return buildNumericalFormFieldBuilder(
            "10000",
            "0",
            "nb-max-domain-audits-per-contract",
            "nb-max-domain-audits-per-contract-error");
    }

    @Bean
    public ContractOptionFormFieldBuilder nbMaxDomainAuditsPerContractOptionBuilder() {
        return buildContractOptionFormFieldBuilder(
            "DOMAIN_ACT_LIMITATION",
            nbMaxDomainAuditsPerContractFormFieldBuilder());
    }

    @Bean
    public NumericalFormFieldBuilderImpl nbMaxPagesAuditsPerContractFormFieldBuilder() {
        return buildNumericalFormFieldBuilder(
            "10000",
            "0",
            "nb-max-pages-audits-per-contract",
            "nb-max-pages-audits-per-contract-error");
    }

    @Bean
    public ContractOptionFormFieldBuilder nbMaxPagesAuditsPerContractOptionBuilder() {
        return buildContractOptionFormFieldBuilder(
            "PAGES_ACT_LIMITATION",
            nbMaxPagesAuditsPerContractFormFieldBuilder());
    }

    @Bean
    public NumericalFormFieldBuilderImpl nbMaxUploadAuditsPerContractFormFieldBuilder() {
        return buildNumericalFormFieldBuilder(
            "10000",
            "0",
            "nb-max-upload-audits-per-contract",
            "nb-max-upload-audits-per-contract-error");
    }

    @Bean
    public ContractOptionFormFieldBuilder nbMaxUploadAuditsPerContractOptionBuilder() {
        return buildContractOptionFormFieldBuilder(
            "UPLOAD_ACT_LIMITATION",
            nbMaxUploadAuditsPerContractFormFieldBuilder());
    }


    @Bean
    public NumericalFormFieldBuilderImpl nbMaxScenarioAuditsPerContractFormFieldBuilder() {
        return buildNumericalFormFieldBuilder(
            "10000",
            "0",
            "nb-max-scenario-audits-per-contract",
            "nb-max-scenario-audits-per-contract-error");
    }

    @Bean
    public ContractOptionFormFieldBuilder nbMaxScenarioAuditsPerContractOptionBuilder() {
        return buildContractOptionFormFieldBuilder(
            "SCENARIO_ACT_LIMITATION",
            nbMaxScenarioAuditsPerContractFormFieldBuilder());
    }

    @Bean
    public NumericalFormFieldBuilderImpl nbMaxActsToDisplayPerContractFormFieldBuilder() {
        return buildNumericalFormFieldBuilder(
            "10000",
            "0",
            "nb-max-acts-per-contract",
            "nb-max-acts-per-contract-error");
    }

    @Bean
    public ContractOptionFormFieldBuilder nbMaxActsToDisplayPerContractOptionBuilder() {
        return buildContractOptionFormFieldBuilder(
            "NB_OF_AUDIT_TO_DISPLAY",
            nbMaxActsToDisplayPerContractFormFieldBuilder());
    }

    @Bean
    public NumericalFormFieldBuilderImpl depthOptionFormFieldBuilder() {
        return buildNumericalFormFieldBuilder(
            "20",
            "0",
            "depth",
            "depth-error");
    }

    @Bean
    public ContractOptionFormFieldBuilder depthOptionBuilder() {
        return buildContractOptionFormFieldBuilder(
            "DEPTH",
            depthOptionFormFieldBuilder());
    }

    @Bean
    public NumericalFormFieldBuilderImpl maxDurationOptionFormFieldBuilder() {
        return buildNumericalFormFieldBuilder(
            "86400",
            "1",
            "max-duration",
            "max-duration-error");
    }

    @Bean
    public ContractOptionFormFieldBuilder maxDurationOptionBuilder() {
        return buildContractOptionFormFieldBuilder(
            "MAX_DURATION",
            maxDurationOptionFormFieldBuilder());
    }

    @Bean
    public NumericalFormFieldBuilderImpl maxDocumentsOptionFormFieldBuilder() {
        return buildNumericalFormFieldBuilder(
            "10000",
            "1",
            "max-documents",
            "max-documents-error");
    }

    @Bean
    public ContractOptionFormFieldBuilder maxDocumentsOptionBuilder() {
        return buildContractOptionFormFieldBuilder(
            "MAX_DOCUMENTS",
            maxDocumentsOptionFormFieldBuilder());
    }

    @Bean
    public TextualFormFieldBuilderImpl exclusionRegexpOptionFormFieldBuilder() {
        return buildTextualFormFieldBuilder(
            "exclusion-regexp",
            "exclusion-regexp-error");
    }
    @Bean
    public ContractOptionFormFieldBuilder exclusionRegexpOptionBuilder() {
        return buildContractOptionFormFieldBuilder(
            "EXCLUSION_REGEXP",
            exclusionRegexpOptionFormFieldBuilder());
    }
    @Bean
    public List<FormFieldBuilder> contractManagementOptionFieldsBuilderList() {
        return Arrays.asList(
            displayContractOrderSelectFormFieldBuilder(),
            inclusionRegexpContractFormFieldBuilder(),
            exclusionRegexpContractFormFieldBuilder());
    }
    public static TextualFormFieldBuilderImpl buildTextualFormFieldBuilder(String i18nKey, String errorI18nKey) {
        TextualFormFieldBuilderImpl builder = new TextualFormFieldBuilderImpl();
        builder.setI18nKey(i18nKey);
        builder.setErrorI18nKey(errorI18nKey);
        return builder;
    }
    public static SelectElementBuilderImpl buildSelectElementBuilder(
        String value, String i18nKey, String errorI18nKey, Boolean isDefault, Boolean isEnabled) {
        SelectElementBuilderImpl builder = new SelectElementBuilderImpl();
        builder.setValue(value);
        builder.setI18nKey(i18nKey);
        builder.setErrorI18nKey(errorI18nKey);
        builder.setDefault(isDefault);
        builder.setEnabled(isEnabled);
        return builder;
    }
    public static SelectElementBuilderImpl buildSelectElementBuilder
        (String value, String i18nKey, String errorI18nKey, String defaultCode, Boolean isDefault, Boolean isEnabled) {
        SelectElementBuilderImpl builder = buildSelectElementBuilder(value, i18nKey, errorI18nKey, isDefault, isEnabled);
        builder.setDefaultCode(defaultCode);
        return builder;
    }
    public static CheckboxElementBuilderImpl buildCheckboxElementBuilder
        (String value, String i18nKey, String errorI18nKey, Boolean isEnabled, Boolean isSelected) {
        CheckboxElementBuilderImpl builder = new CheckboxElementBuilderImpl();
        builder.setValue(value);
        builder.setI18nKey(i18nKey);
        builder.setErrorI18nKey(errorI18nKey);
        builder.setEnabled(isEnabled);
        builder.setSelected(isSelected);
        return builder;
    }
    public static SelectFormFieldBuilderImpl buildSelectFormFieldBuilder
        (String i18nKey, String errorI18nKey, Map <String,List<SelectElementBuilder>> elementBuilderMap) {
        SelectFormFieldBuilderImpl builder = new SelectFormFieldBuilderImpl();
        builder.setSelectElementBuilderMap(elementBuilderMap);
        builder.setI18nKey(i18nKey);
        builder.setErrorI18nKey(errorI18nKey);
        return builder;
    }
    public static SelectFormFieldBuilderImpl buildSelectFormFieldBuilder
        (String i18nKey, String errorI18nKey, Map <String,List<SelectElementBuilder>> elementBuilderMap, String value) {
        SelectFormFieldBuilderImpl builder = buildSelectFormFieldBuilder(i18nKey, errorI18nKey, elementBuilderMap);
        builder.setValue(value);
        return builder;
    }
    public static NumericalFormFieldBuilderImpl buildNumericalFormFieldBuilder
        (String maxValue, String minValue, String i18nKey, String errorI18nKey) {
        NumericalFormFieldBuilderImpl builder = new NumericalFormFieldBuilderImpl();
        builder.setMaxValue(maxValue);
        builder.setMinValue(minValue);
        builder.setI18nKey(i18nKey);
        builder.setErrorI18nKey(errorI18nKey);
        return builder;
    }
    public static NumericalFormFieldBuilderImpl buildNumericalFormFieldBuilder(
        String minValue, String maxValue, String value, String i18nKey, String errorI18nKey) {
        NumericalFormFieldBuilderImpl builder = buildNumericalFormFieldBuilder(minValue, maxValue, i18nKey, errorI18nKey);
        builder.setValue(value);
        return builder;
    }
    private ContractOptionFormFieldBuilder buildContractOptionFormFieldBuilder
        (String optionCode, AbstractGenericFormFieldBuilder formfielBuilder) {
        ContractOptionFormFieldBuilder builder = new ContractOptionFormFieldBuilder(optionDataService);
        builder.setOptionCode(optionCode);
        builder.setFormFieldBuilder(formfielBuilder);
        return builder;
    }
}
