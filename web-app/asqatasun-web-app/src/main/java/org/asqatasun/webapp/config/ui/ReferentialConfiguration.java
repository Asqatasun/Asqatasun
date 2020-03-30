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

import org.asqatasun.webapp.ui.form.builder.SelectElementBuilder;
import org.asqatasun.webapp.ui.form.builder.SelectElementBuilderImpl;
import org.asqatasun.webapp.ui.form.builder.SelectFormFieldBuilderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Configuration
public class ReferentialConfiguration {

    @Bean
    public SelectFormFieldBuilderImpl levelSelectFormFieldBuilder() {
        return ContractFormElementConfiguration.buildSelectFormFieldBuilder(
            "level",
            "level-error",
            new HashMap <String, List<SelectElementBuilder>>() {{
                put("Aw22", Arrays.asList(
                    aw22BronzeSelectElementBuilder(),
                    aw22SilverSelectElementBuilder(),
                    aw22GoldSelectElementBuilder()
                ));
                put("Rgaa30", Arrays.asList(
                    rgaa30ASelectElementBuilder(),
                    rgaa30AASelectElementBuilder(),
                    rgaa30AAASelectElementBuilder()
                ));
            }}
        );
    }
    // AW 22 select element builder
    @Bean
    public SelectElementBuilderImpl aw22BronzeSelectElementBuilder(){
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Aw22;LEVEL_1",
            "Aw22-LEVEL_1",
            "Bz-error",
            "DEFAULT_LEVEL",
            false,
            false
        );
    }
    @Bean
    public SelectElementBuilderImpl aw22SilverSelectElementBuilder(){
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Aw22;LEVEL_2",
            "Aw22-LEVEL_2",
            "LEVEL_2-error",
            "DEFAULT_LEVEL",
            false,
            false
        );
    }
    @Bean
    public SelectElementBuilderImpl aw22GoldSelectElementBuilder(){
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Aw22;LEVEL_3",
            "Aw22-LEVEL_3",
            "LEVEL_3-error",
            "DEFAULT_LEVEL",
            false,
            false
        );
    }
    @Bean
    public SelectFormFieldBuilderImpl aw22ThemeSelectFormFieldBuilder() {
        return ContractFormElementConfiguration.buildSelectFormFieldBuilder(
            "result.theme",
            "result.theme-contract-error",
            new HashMap <String, List<SelectElementBuilder>>() {{
                put("theme", Arrays.asList(
                    aw22AllThemeSelectElementBuilder(),
                    aw22ImageThemeSelectElementBuilder(),
                    aw22FrameThemeSelectElementBuilder(),
                    aw22ColorThemeSelectElementBuilder(),
                    aw22MultimediaThemeSelectElementBuilder(),
                    aw22TableThemeSelectElementBuilder(),
                    aw22LinkThemeSelectElementBuilder(),
                    aw22ScriptThemeSelectElementBuilder(),
                    aw22MandatoryElementsThemeSelectElementBuilder(),
                    aw22InfoStructureThemeSelectElementBuilder(),
                    aw22InfoPresentationThemeSelectElementBuilder(),
                    aw22FormThemeSelectElementBuilder(),
                    aw22NavigationThemeSelectElementBuilder(),
                    aw22ConsultationThemeSelectElementBuilder()
                ));
            }},
            "Aw22");
    }
    @Bean
    public SelectElementBuilderImpl aw22AllThemeSelectElementBuilder() {
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "all-theme",
            "result.all-theme",
            "result.all-theme-error",
            true,
            true);
    }
    @Bean
    public SelectElementBuilderImpl aw22ImageThemeSelectElementBuilder() {
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Aw22-1",
            "Aw22-1",
            "Aw22-1-error",
            false,
            true);
    }
    @Bean
    public SelectElementBuilderImpl aw22FrameThemeSelectElementBuilder() {
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Aw22-2",
            "Aw22-2",
            "Aw22-2-error",
            false,
            true);
    }
    @Bean
    public SelectElementBuilderImpl aw22ColorThemeSelectElementBuilder() {
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Aw22-3",
            "Aw22-3",
            "Aw22-3-error",
            false,
            true);
    }
    @Bean
    public SelectElementBuilderImpl aw22MultimediaThemeSelectElementBuilder() {
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Aw22-4",
            "Aw22-4",
            "Aw22-4-error",
            false,
            true);
    }
    @Bean
    public SelectElementBuilderImpl aw22TableThemeSelectElementBuilder() {
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Aw22-5",
            "Aw22-5",
            "Aw22-5-error",
            false,
            true);
    }
    @Bean
    public SelectElementBuilderImpl aw22LinkThemeSelectElementBuilder() {
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Aw22-6",
            "Aw22-6",
            "Aw22-6-error",
            false,
            true);
    }
    @Bean
    public SelectElementBuilderImpl aw22ScriptThemeSelectElementBuilder() {
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Aw22-7",
            "Aw22-7",
            "Aw22-7-error",
            false,
            true);
    }
    @Bean
    public SelectElementBuilderImpl aw22MandatoryElementsThemeSelectElementBuilder() {
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Aw22-8",
            "Aw22-8",
            "Aw22-8-error",
            false,
            true);
    }
    @Bean
    public SelectElementBuilderImpl aw22InfoStructureThemeSelectElementBuilder() {
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Aw22-9",
            "Aw22-9",
            "Aw22-9-error",
            false,
            true);
    }
    @Bean
    public SelectElementBuilderImpl aw22InfoPresentationThemeSelectElementBuilder() {
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Aw22-10",
            "Aw22-10",
            "Aw22-10-error",
            false,
            true);
    }
    @Bean
    public SelectElementBuilderImpl aw22FormThemeSelectElementBuilder() {
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Aw22-11",
            "Aw22-11",
            "Aw22-11-error",
            false,
            true);
    }
    @Bean
    public SelectElementBuilderImpl aw22NavigationThemeSelectElementBuilder() {
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Aw22-12",
            "Aw22-12",
            "Aw22-12-error",
            false,
            true);
    }
    @Bean
    public SelectElementBuilderImpl aw22ConsultationThemeSelectElementBuilder() {
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Aw22-13",
            "Aw22-13",
            "Aw22-13-error",
            false,
            true);
    }
    // Rgaa 30 select element builder
    @Bean
    public SelectElementBuilderImpl rgaa30ASelectElementBuilder(){
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Rgaa30;LEVEL_1",
            "Rgaa30-LEVEL_1",
            "A-error",
            "DEFAULT_LEVEL",
            false,
            false
        );
    }
    @Bean
    public SelectElementBuilderImpl rgaa30AASelectElementBuilder(){
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Rgaa30;LEVEL_2",
            "Rgaa30-LEVEL_2",
            "LEVEL_2-error",
            "DEFAULT_LEVEL",
            false,
            false
        );
    }
    @Bean
    public SelectElementBuilderImpl rgaa30AAASelectElementBuilder(){
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Rgaa30;LEVEL_3",
            "Rgaa30-LEVEL_3",
            "LEVEL_3-error",
            "DEFAULT_LEVEL",
            false,
            false
        );
    }
    @Bean
    public SelectFormFieldBuilderImpl rgaa30ThemeSelectFormFieldBuilder() {
        return ContractFormElementConfiguration.buildSelectFormFieldBuilder(
            "result.theme",
            "result.theme-error",
            new HashMap <String, List<SelectElementBuilder>>() {{
                put("theme", Arrays.asList(
                    rgaa30AllThemeSelectElementBuilder(),
                    rgaa301ThemeSelectElementBuilder(),
                    rgaa302ThemeSelectElementBuilder(),
                    rgaa303ThemeSelectElementBuilder(),
                    rgaa304ThemeSelectElementBuilder(),
                    rgaa305ThemeSelectElementBuilder(),
                    rgaa306ThemeSelectElementBuilder(),
                    rgaa307ThemeSelectElementBuilder(),
                    rgaa308ThemeSelectElementBuilder(),
                    rgaa309ThemeSelectElementBuilder(),
                    rgaa3010ThemeSelectElementBuilder(),
                    rgaa3011ThemeSelectElementBuilder(),
                    rgaa3012ThemeSelectElementBuilder(),
                    rgaa3013ThemeSelectElementBuilder()
                ));
            }},
            "Rgaa30");
    }
    @Bean
    public SelectElementBuilderImpl rgaa30AllThemeSelectElementBuilder() {
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "all-theme",
            "result.all-theme",
            "result.all-theme-error",
            true,
            true);
    }
    @Bean
    public SelectElementBuilderImpl rgaa301ThemeSelectElementBuilder() {
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Rgaa30-1",
            "Rgaa30-1",
            "",
            false,
            true);
    }
    @Bean
    public SelectElementBuilderImpl rgaa302ThemeSelectElementBuilder() {
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Rgaa30-2",
            "Rgaa30-2",
            "",
            false,
            true);
    }
    @Bean
    public SelectElementBuilderImpl rgaa303ThemeSelectElementBuilder() {
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Rgaa30-3",
            "Rgaa30-3",
            "",
            false,
            true);
    }
    @Bean
    public SelectElementBuilderImpl rgaa304ThemeSelectElementBuilder() {
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Rgaa30-4",
            "Rgaa30-4",
            "",
            false,
            true);
    }
    @Bean
    public SelectElementBuilderImpl rgaa305ThemeSelectElementBuilder() {
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Rgaa30-5",
            "Rgaa30-5",
            "",
            false,
            true);
    }
    @Bean
    public SelectElementBuilderImpl rgaa306ThemeSelectElementBuilder() {
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Rgaa30-6",
            "Rgaa30-6",
            "",
            false,
            true);
    }
    @Bean
    public SelectElementBuilderImpl rgaa307ThemeSelectElementBuilder() {
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Rgaa30-7",
            "Rgaa30-7",
            "",
            false,
            true);
    }
    @Bean
    public SelectElementBuilderImpl rgaa308ThemeSelectElementBuilder() {
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Rgaa30-8",
            "Rgaa30-8",
            "",
            false,
            true);
    }
    @Bean
    public SelectElementBuilderImpl rgaa309ThemeSelectElementBuilder() {
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Rgaa30-9",
            "Rgaa30-9",
            "",
            false,
            true);
    }
    @Bean
    public SelectElementBuilderImpl rgaa3010ThemeSelectElementBuilder() {
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Rgaa30-10",
            "Rgaa30-10",
            "",
            false,
            true);
    }
    @Bean
    public SelectElementBuilderImpl rgaa3011ThemeSelectElementBuilder() {
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Rgaa30-11",
            "Rgaa30-11",
            "",
            false,
            true);
    }
    @Bean
    public SelectElementBuilderImpl rgaa3012ThemeSelectElementBuilder() {
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Rgaa30-12",
            "Rgaa30-12",
            "",
            false,
            true);
    }
    @Bean
    public SelectElementBuilderImpl rgaa3013ThemeSelectElementBuilder() {
        return ContractFormElementConfiguration.buildSelectElementBuilder(
            "Rgaa30-13",
            "Rgaa30-13",
            "",
            false,
            true);
    }

}
