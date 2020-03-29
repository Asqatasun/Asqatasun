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

import org.asqatasun.entity.service.parameterization.ParameterElementDataService;
import org.asqatasun.webapp.ui.form.builder.*;
import org.asqatasun.webapp.ui.form.parameterization.builder.AuditSetUpFormFieldBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class AuditFormElementConfiguration {


    final ParameterElementDataService parameterElementDataService;

    public AuditFormElementConfiguration(ParameterElementDataService parameterElementDataService) {
        this.parameterElementDataService = parameterElementDataService;
    }

    // ****************
    // For Audit SetUp
    // ****************
    @Bean
    public NumericalFormFieldBuilderImpl depthFormFieldBuilder() {
        return ContractFormElementConfiguration.buildNumericalFormFieldBuilder(
            "20", "0", "depth", "depth-error");
    }
    @Bean
    public AuditSetUpFormFieldBuilder depthParamBuilder() {
        AuditSetUpFormFieldBuilder auditSetUpFormFieldBuilder =
            new AuditSetUpFormFieldBuilder(parameterElementDataService);
        auditSetUpFormFieldBuilder.setFormFieldBuilder(depthFormFieldBuilder());
        auditSetUpFormFieldBuilder.setParameterCode("DEPTH");
        return auditSetUpFormFieldBuilder;
    }
    @Bean
    public NumericalFormFieldBuilderImpl maxDurationFormFieldBuilder() {
        return ContractFormElementConfiguration.buildNumericalFormFieldBuilder(
            "86400", "1", "max-duration", "max-duration-error");
    }
    @Bean
    public AuditSetUpFormFieldBuilder maxDurationParamBuilder() {
        AuditSetUpFormFieldBuilder auditSetUpFormFieldBuilder =
            new AuditSetUpFormFieldBuilder(parameterElementDataService);
        auditSetUpFormFieldBuilder.setFormFieldBuilder(maxDurationFormFieldBuilder());
        auditSetUpFormFieldBuilder.setParameterCode("MAX_DURATION");
        return auditSetUpFormFieldBuilder;
    }
    @Bean
    public NumericalFormFieldBuilderImpl maxDocumentsFormFieldBuilder() {
        return ContractFormElementConfiguration.buildNumericalFormFieldBuilder(
            "1000", "1", "max-documents", "max-documents-error");
    }
    @Bean
    public AuditSetUpFormFieldBuilder maxDocumentsParamBuilder() {
        AuditSetUpFormFieldBuilder auditSetUpFormFieldBuilder =
            new AuditSetUpFormFieldBuilder(parameterElementDataService);
        auditSetUpFormFieldBuilder.setFormFieldBuilder(maxDocumentsFormFieldBuilder());
        auditSetUpFormFieldBuilder.setParameterCode("MAX_DOCUMENTS");
        return auditSetUpFormFieldBuilder;
    }
    @Bean
    public TextualFormFieldBuilderImpl exclusionRegexpFormFieldBuilder() {
        return ContractFormElementConfiguration.buildTextualFormFieldBuilder(
            "exclusion-regexp", "exclusion-regexp-error");
    }
    @Bean
    public AuditSetUpFormFieldBuilder exclusionRegexpParamBuilder() {
        AuditSetUpFormFieldBuilder auditSetUpFormFieldBuilder =
            new AuditSetUpFormFieldBuilder(parameterElementDataService);
        auditSetUpFormFieldBuilder.setFormFieldBuilder(exclusionRegexpFormFieldBuilder());
        auditSetUpFormFieldBuilder.setParameterCode("EXCLUSION_REGEXP");
        return auditSetUpFormFieldBuilder;
    }
    @Bean
    public TextualFormFieldBuilderImpl inclusionRegexpFormFieldBuilder() {
        return ContractFormElementConfiguration.buildTextualFormFieldBuilder(
            "inclusion-regexp", "inclusion-regexp-error");
    }
    @Bean
    public AuditSetUpFormFieldBuilder inclusionRegexpParamBuilder() {
        AuditSetUpFormFieldBuilder auditSetUpFormFieldBuilder =
            new AuditSetUpFormFieldBuilder(parameterElementDataService);
        auditSetUpFormFieldBuilder.setFormFieldBuilder(inclusionRegexpFormFieldBuilder());
        auditSetUpFormFieldBuilder.setParameterCode("INCLUSION_REGEXP");
        return auditSetUpFormFieldBuilder;
    }
    @Bean
    public TextualFormFieldBuilderImpl presentationTableFormFieldBuilder() {
        return ContractFormElementConfiguration.buildTextualFormFieldBuilder(
            "presentation-table", "presentation-table-error");
    }
    @Bean
    public AuditSetUpFormFieldBuilder presentationTableParamBuilder() {
        AuditSetUpFormFieldBuilder auditSetUpFormFieldBuilder =
            new AuditSetUpFormFieldBuilder(parameterElementDataService);
        auditSetUpFormFieldBuilder.setFormFieldBuilder(presentationTableFormFieldBuilder());
        auditSetUpFormFieldBuilder.setParameterCode("PRESENTATION_TABLE_MARKER");
        return auditSetUpFormFieldBuilder;
    }
    @Bean
    public TextualFormFieldBuilderImpl dataTableFormFieldBuilder() {
        return ContractFormElementConfiguration.buildTextualFormFieldBuilder(
            "data-table", "data-table-error");
    }
    @Bean
    public AuditSetUpFormFieldBuilder dataTableParamBuilder() {
        AuditSetUpFormFieldBuilder auditSetUpFormFieldBuilder =
            new AuditSetUpFormFieldBuilder(parameterElementDataService);
        auditSetUpFormFieldBuilder.setFormFieldBuilder(dataTableFormFieldBuilder());
        auditSetUpFormFieldBuilder.setParameterCode("DATA_TABLE_MARKER");
        return auditSetUpFormFieldBuilder;
    }
    @Bean
    public TextualFormFieldBuilderImpl complexTableFormFieldBuilder() {
        return ContractFormElementConfiguration.buildTextualFormFieldBuilder(
            "complex-table", "complex-table-error");
    }
    @Bean
    public AuditSetUpFormFieldBuilder complexTableParamBuilder() {
        AuditSetUpFormFieldBuilder auditSetUpFormFieldBuilder =
            new AuditSetUpFormFieldBuilder(parameterElementDataService);
        auditSetUpFormFieldBuilder.setFormFieldBuilder(complexTableFormFieldBuilder());
        auditSetUpFormFieldBuilder.setParameterCode("COMPLEX_TABLE_MARKER");
        return auditSetUpFormFieldBuilder;
    }
    @Bean
    public TextualFormFieldBuilderImpl decorativeImageFormFieldBuilder() {
        return ContractFormElementConfiguration.buildTextualFormFieldBuilder(
            "decorative-image", "decorative-image-error");
    }
    @Bean
    public AuditSetUpFormFieldBuilder decorativeImageParamBuilder() {
        AuditSetUpFormFieldBuilder auditSetUpFormFieldBuilder =
            new AuditSetUpFormFieldBuilder(parameterElementDataService);
        auditSetUpFormFieldBuilder.setFormFieldBuilder(decorativeImageFormFieldBuilder());
        auditSetUpFormFieldBuilder.setParameterCode("DECORATIVE_IMAGE_MARKER");
        return auditSetUpFormFieldBuilder;
    }
    @Bean
    public TextualFormFieldBuilderImpl informativeImageFormFieldBuilder() {
        return ContractFormElementConfiguration.buildTextualFormFieldBuilder(
            "informative-image", "informative-image-error");
    }
    @Bean
    public AuditSetUpFormFieldBuilder informativeImageParamBuilder() {
        AuditSetUpFormFieldBuilder auditSetUpFormFieldBuilder =
            new AuditSetUpFormFieldBuilder(parameterElementDataService);
        auditSetUpFormFieldBuilder.setFormFieldBuilder(informativeImageFormFieldBuilder());
        auditSetUpFormFieldBuilder.setParameterCode("INFORMATIVE_IMAGE_MARKER");
        return auditSetUpFormFieldBuilder;
    }
    @Bean
    public CheckboxElementBuilderImpl alternativeContrastMechanismPresent() {
        return ContractFormElementConfiguration.buildCheckboxElementBuilder(
            "true","alternative-contrast", "alternativeContrast-error", true, false);
    }
    @Bean
    public CheckboxFormFieldBuilderImpl alternativeContrastMechanismPresentFormFieldBuilder() {
        CheckboxFormFieldBuilderImpl builder = new CheckboxFormFieldBuilderImpl();
        builder.setI18nKey("contrast-alternative");
        builder.setErrorI18nKey("contrast-alternative-error");
        builder.setCheckboxElementBuilderList(Arrays.asList(alternativeContrastMechanismPresent()));
        return builder;
    }
    @Bean
    public AuditSetUpFormFieldBuilder alternativeContrastMechanismPresentParamBuilder() {
        AuditSetUpFormFieldBuilder auditSetUpFormFieldBuilder =
            new AuditSetUpFormFieldBuilder(parameterElementDataService);
        auditSetUpFormFieldBuilder.setFormFieldBuilder(alternativeContrastMechanismPresentFormFieldBuilder());
        auditSetUpFormFieldBuilder.setParameterCode("ALTERNATIVE_CONTRAST_MECHANISM");
        return auditSetUpFormFieldBuilder;
    }
    @Bean
    public CheckboxElementBuilderImpl considerCookiesBuilder() {
        CheckboxElementBuilderImpl builder = new CheckboxElementBuilderImpl();
        builder.setEnabled(true);
        builder.setValue("true");
        return builder;
    }
    @Bean
    public CheckboxFormFieldBuilderImpl considerCookiesFormFieldBuilder() {
        CheckboxFormFieldBuilderImpl builder = new CheckboxFormFieldBuilderImpl();
        builder.setI18nKey("consider-cookies");
        builder.setErrorI18nKey("consider-cookies-error");
        builder.setCheckboxElementBuilderList(Arrays.asList(considerCookiesBuilder()));
        return builder;
    }
    @Bean
    public AuditSetUpFormFieldBuilder considerCookiesParamBuilder() {
        AuditSetUpFormFieldBuilder auditSetUpFormFieldBuilder =
            new AuditSetUpFormFieldBuilder(parameterElementDataService);
        auditSetUpFormFieldBuilder.setFormFieldBuilder(considerCookiesFormFieldBuilder());
        auditSetUpFormFieldBuilder.setParameterCode("CONSIDER_COOKIES");
        return auditSetUpFormFieldBuilder;
    }
    @Bean
    public NumericalFormFieldBuilderImpl screenWidthFormFieldBuilder() {
        return ContractFormElementConfiguration.buildNumericalFormFieldBuilder(
            "2048", "1", "screen-width", "screen-width-error");
    }
    @Bean
    public AuditSetUpFormFieldBuilder screenWidthParamBuilder() {
        AuditSetUpFormFieldBuilder auditSetUpFormFieldBuilder =
            new AuditSetUpFormFieldBuilder(parameterElementDataService);
        auditSetUpFormFieldBuilder.setFormFieldBuilder(screenWidthFormFieldBuilder());
        auditSetUpFormFieldBuilder.setParameterCode("SCREEN_WIDTH");
        return auditSetUpFormFieldBuilder;
    }
    @Bean
    public NumericalFormFieldBuilderImpl screenHeightFormFieldBuilder() {
        return ContractFormElementConfiguration.buildNumericalFormFieldBuilder(
            "2048", "1", "screen-height", "screen-height-error");
    }
    @Bean
    public AuditSetUpFormFieldBuilder screenHeightParamBuilder() {
        AuditSetUpFormFieldBuilder auditSetUpFormFieldBuilder =
            new AuditSetUpFormFieldBuilder(parameterElementDataService);
        auditSetUpFormFieldBuilder.setFormFieldBuilder(screenHeightFormFieldBuilder());
        auditSetUpFormFieldBuilder.setParameterCode("SCREEN_HEIGHT");
        return auditSetUpFormFieldBuilder;
    }
    @Bean
    public Map <String, List<AuditSetUpFormFieldBuilder>> scenarioOptionFormFieldBuilderMap() {
        return new LinkedHashMap <String, List<AuditSetUpFormFieldBuilder>>() {
            {
                put("marker-parameters", Arrays.asList(
                    dataTableParamBuilder(),
                    presentationTableParamBuilder(),
                    complexTableParamBuilder(),
                    decorativeImageParamBuilder(),
                    informativeImageParamBuilder()));
                put("resolution-parameters", Arrays.asList(
                    screenWidthParamBuilder(),
                    screenHeightParamBuilder()));
                put("contrast-parameters", Arrays.asList(alternativeContrastMechanismPresentParamBuilder()));
            }};
    }
    @Bean
    public Map <String, List<AuditSetUpFormFieldBuilder>> pageOptionFormFieldBuilderMap() {
        return new LinkedHashMap <String, List<AuditSetUpFormFieldBuilder>>() {
            {
                put("marker-parameters", Arrays.asList(
                    dataTableParamBuilder(),
                    presentationTableParamBuilder(),
                    complexTableParamBuilder(),
                    decorativeImageParamBuilder(),
                    informativeImageParamBuilder()));
                put("resolution-parameters", Arrays.asList(
                    screenWidthParamBuilder(),
                    screenHeightParamBuilder()));
                put("contrast-parameters", Arrays.asList(alternativeContrastMechanismPresentParamBuilder()));
            }};
    }
    @Bean
    public Map <String, List<AuditSetUpFormFieldBuilder>> siteOptionFormFieldBuilderMap() {
        return new LinkedHashMap <String, List<AuditSetUpFormFieldBuilder>>() {
            {
                put("crawl-limits", Arrays.asList(
                    depthParamBuilder(),
                    maxDurationParamBuilder(),
                    maxDocumentsParamBuilder()));
                put("crawl-options", Arrays.asList(
                    inclusionRegexpParamBuilder(),
                    exclusionRegexpParamBuilder(),
                    considerCookiesParamBuilder()));
                put("marker-parameters", Arrays.asList(
                    dataTableParamBuilder(),
                    presentationTableParamBuilder(),
                    complexTableParamBuilder(),
                    decorativeImageParamBuilder(),
                    informativeImageParamBuilder()));
                put("contrast-parameters", Arrays.asList(alternativeContrastMechanismPresentParamBuilder()));
            }};
    }
    @Bean
    public Map <String, List<AuditSetUpFormFieldBuilder>> uploadOptionFormFieldBuilderMap() {
        return Collections.singletonMap("marker-parameters", Arrays.asList(
            dataTableParamBuilder(),
            presentationTableParamBuilder(),
            complexTableParamBuilder(),
            decorativeImageParamBuilder(),
            informativeImageParamBuilder()
        ));
    }

    // ****************
    // For Audit Result
    // ****************
    // To add a new referential, please add the related SelectFormFieldBuilder here
    @Autowired
    @Qualifier(value="aw22ThemeSelectFormFieldBuilder")
    FormFieldBuilder aw22ThemeSelectFormFieldBuilder;
    @Autowired
    @Qualifier(value="rgaa30ThemeSelectFormFieldBuilder")
    FormFieldBuilder rgaa30ThemeSelectFormFieldBuilder;
    @Bean
    public List <FormFieldBuilder> auditResultFormFieldBuilderList() {
        return Arrays.asList(
            aw22ThemeSelectFormFieldBuilder,
            rgaa30ThemeSelectFormFieldBuilder,
            testResultCheckboxFormFieldBuilder());
    }

    @Bean
    public CheckboxFormFieldBuilderImpl testResultCheckboxFormFieldBuilder() {
        CheckboxFormFieldBuilderImpl builder = new CheckboxFormFieldBuilderImpl();
        builder.setI18nKey("result.test-result");
        builder.setErrorI18nKey("result.test-result-error");
        builder.setCode("test-result");
        builder.setCheckboxElementBuilderList(Arrays.asList(
                passedCheckboxElementBuilder(),
                failedCheckboxElementBuilder(),
                nmiCheckboxElementBuilder(),
                naCheckboxElementBuilder(),
                ntCheckboxElementBuilder()
            ));
        return builder;
    }
    @Bean
    public CheckboxElementBuilderImpl passedCheckboxElementBuilder() {
        return ContractFormElementConfiguration.buildCheckboxElementBuilder(
            "PASSED",
            "passed",
            "passed-error",
            true,
            false);
    }
    @Bean
    public CheckboxElementBuilderImpl failedCheckboxElementBuilder() {
        return ContractFormElementConfiguration.buildCheckboxElementBuilder(
            "FAILED",
            "failed",
            "failed-error",
            true,
            true);
    }
    @Bean
    public CheckboxElementBuilderImpl nmiCheckboxElementBuilder() {
        return ContractFormElementConfiguration.buildCheckboxElementBuilder(
            "NEED_MORE_INFO",
            "nmi",
            "nmi-error",
            true,
            true);
    }
    @Bean
    public CheckboxElementBuilderImpl naCheckboxElementBuilder() {
        return ContractFormElementConfiguration.buildCheckboxElementBuilder(
            "NOT_APPLICABLE",
            "na",
            "na-error",
            true,
            false);
    }
    @Bean
    public CheckboxElementBuilderImpl ntCheckboxElementBuilder() {
        return ContractFormElementConfiguration.buildCheckboxElementBuilder(
            "NOT_TESTED",
            "nt",
            "nt-error",
            true,
            false);
    }

}
