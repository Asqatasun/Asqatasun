package org.asqatasun.webapp.config.export;

import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.Page;
import org.asqatasun.webapp.entity.referential.Referential;
import org.asqatasun.webapp.entity.service.referential.ReferentialDataService;
import org.asqatasun.webapp.report.expression.builder.*;
import org.asqatasun.webapp.report.expression.retriever.*;
import org.asqatasun.webapp.report.layout.builder.LayoutBuilder;
import org.asqatasun.webapp.report.layout.builder.SubtitleBuilderImpl;
import org.asqatasun.webapp.report.layout.builder.TitleBuilderImpl;
import org.asqatasun.webapp.report.layout.column.builder.ElementColumnBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static ar.com.fdvs.dj.domain.constants.HorizontalAlign.*;
import static ar.com.fdvs.dj.domain.constants.VerticalAlign.MIDDLE;

@Configuration
public class ExportConfiguration {

    public static final String EXPORT_REPORT_I18N = "i18n/export-report-I18N";
    public static final String RULE_RESULT_I18N = "i18n/rule-result-I18N";
    private final ReferentialDataService referentialDataService;

    public ExportConfiguration(ReferentialDataService referentialDataService) {
        this.referentialDataService = referentialDataService;
    }

    @Bean
    @DependsOn("flyway")
    public List<String> referentialList() {
        return referentialDataService.findAll().stream()
            .map(Referential::getCode)
            .map(String::toLowerCase)
            .collect(Collectors.toList());
    }

    ///////////////////////////////////////
    // Layout
    //////////////////////////////////////
    @Bean
    public LayoutBuilder layoutBuilder() {
        LayoutBuilder builder = new LayoutBuilder();
        builder.setIgnorePagination(true);
        builder.setPrintColumnNames(true);
        builder.setUseFullPageWidth(true);
        builder.setMargin("0:0:0:0");
        builder.setPageOrientation(Page.Page_A4_Landscape());
        builder.setFieldMap(new HashMap <String, String>() {{
            put("resultCounter", "org.asqatasun.webapp.dto.ResultCounter");
            put("test", "org.asqatasun.entity.reference.Test");
            put("levelCode", "java.lang.String");
            put("resultCode", "java.lang.String");
            put("remarkInfosList", "java.util.List");
        }});
        builder.setTitleBuilder(titleBuilder());
        builder.setTitleStyle(titleStyle());
        builder.setSubtitleBuilder(subtitleBuilder());
        builder.setSubtitleStyle(subtitleStyle());
        builder.setColumnBuilderList(Arrays.asList(
            themeColumnBuilder(),
            criterionColumnBuilder(),
            testColumnBuilder(),
            testLabelColumnBuilder(),
            levelColumnBuilder(),
            resultColumnBuilder()
        ));
        builder.setFormatSpecificColumnBuilderList(Collections.singletonMap("csv", Arrays.asList(resultDetailsColumnBuilder())));
        return builder;
    }
    @Bean
    public TitleBuilderImpl titleBuilder(){
        TitleBuilderImpl builder = new TitleBuilderImpl();
        builder.setBundleName(EXPORT_REPORT_I18N);
        return builder;
    }
    @Bean
    public SubtitleBuilderImpl subtitleBuilder(){
        SubtitleBuilderImpl builder = new SubtitleBuilderImpl();
        builder.setBundleName(EXPORT_REPORT_I18N);
        builder.setRefAndLevelValueBundleList(bundleReferentialNameList());
        builder.setRefBundleName("i18n/parameters-I18N");
        builder.setLevelBundleName("i18n/parameters-I18N");
        builder.setLevelParamKey("LEVEL");
        return builder;
    }

    ///////////////////////////////////////
    // Expressions
    ///////////////////////////////////////
    @Bean
    public KeyRetriever themeKeyRetriever() {
        ThemeKeyRetrieverImpl retriever = new ThemeKeyRetrieverImpl();
        retriever.setFieldName("test");
        return retriever;
    }
    @Bean
    public KeyRetriever criterionKeyRetriever() {
        CriterionKeyRetrieverImpl retriever = new CriterionKeyRetrieverImpl();
        retriever.setFieldName("test");
        return retriever;
    }
    @Bean
    public KeyRetriever testKeyRetriever() {
        TestKeyRetrieverImpl retriever = new TestKeyRetrieverImpl();
        retriever.setFieldName("test");
        return retriever;
    }
    @Bean
    public KeyRetriever levelKeyRetriever() {
        DefaultKeyRetrieverImpl retriever = new DefaultKeyRetrieverImpl();
        retriever.setFieldName("levelCode");
        return retriever;
    }
    @Bean
    public KeyRetriever resultKeyRetriever() {
        DefaultKeyRetrieverImpl retriever = new DefaultKeyRetrieverImpl();
        retriever.setFieldName("resultCode");
        return retriever;
    }
    /////////////////////////////////////////////////////////////////////
    // I18N EXPRESSIONS
    /////////////////////////////////////////////////////////////////////

    @Bean
    public List<String> bundleThemeNameList() {
        return referentialList().stream().map(ref -> "i18n/theme-"+ref+"-I18N").collect(Collectors.toList());
    }
    @Bean
    public List<String> bundleTestNameList() {
        return referentialList().stream().map(ref -> "i18n/rule-"+ref+"-I18N").collect(Collectors.toList());
    }
    @Bean
    public List<String> bundleReferentialNameList() {
        return referentialList().stream().map(ref -> "i18n/referential-"+ref+"-I18N").collect(Collectors.toList());
    }
    @Bean
    public I18nExpressionBuilderImpl themeExpressionBuilder() {
        I18nExpressionBuilderImpl builder = new I18nExpressionBuilderImpl();
        builder.setBundleNameList(bundleThemeNameList());
        builder.setEscapeHtml(false);
        builder.setKeyRetriever(themeKeyRetriever());
        return builder;
    }
    @Bean
    public I18nExpressionBuilderImpl criterionExpressionBuilder() {
        I18nExpressionBuilderImpl builder = new I18nExpressionBuilderImpl();
        builder.setEscapeHtml(false);
        builder.setKeyRetriever(criterionKeyRetriever());
        return builder;
    }
    @Bean
    public I18nExpressionBuilderImpl testExpressionBuilder() {
        I18nExpressionBuilderImpl builder = new I18nExpressionBuilderImpl();
        builder.setBundleNameList(bundleTestNameList());
        builder.setEscapeHtml(false);
        builder.setKeyRetriever(testKeyRetriever());
        return builder;
    }
    @Bean
    public I18nExpressionBuilderImpl levelExpressionBuilder() {
        I18nExpressionBuilderImpl builder = new I18nExpressionBuilderImpl();
        builder.setBundleNameList(bundleReferentialNameList());
        builder.setEscapeHtml(false);
        builder.setKeyRetriever(levelKeyRetriever());
        return builder;
    }
    @Bean
    public I18nExpressionBuilderImpl resultExpressionBuilder() {
        I18nExpressionBuilderImpl builder = new I18nExpressionBuilderImpl();
        builder.setBundleNameList(Arrays.asList(RULE_RESULT_I18N));
        builder.setEscapeHtml(false);
        builder.setKeyRetriever(resultKeyRetriever());
        return builder;
    }
    @Bean
    public ProcessRemarkCsvExtractorExpressionBuilderImpl resultDetailsExpressionBuilder() {
        return new ProcessRemarkCsvExtractorExpressionBuilderImpl();
    }
    @Bean
    public AbstractGenericConditionStyleExpressionBuilder failedStyleExpressionBuilder() {
        ResultStyleExpressionBuilderImpl builder = new ResultStyleExpressionBuilderImpl();
        builder.setBundleName(RULE_RESULT_I18N);
        builder.setResult("failed");
        return builder;
    }
    @Bean
    public ConditionalStyleBuilderImpl failedStyleBuilder(){
        ConditionalStyleBuilderImpl builder = new ConditionalStyleBuilderImpl();
        builder.setConditionalStyleExpressionBuilder(failedStyleExpressionBuilder());
        builder.setStyle(failedStyle());
        return builder;
    }
    @Bean
    public AbstractGenericConditionStyleExpressionBuilder passedStyleExpressionBuilder() {
        ResultStyleExpressionBuilderImpl builder = new ResultStyleExpressionBuilderImpl();
        builder.setBundleName(RULE_RESULT_I18N);
        builder.setResult("passed");
        return builder;
    }
    @Bean
    public ConditionalStyleBuilderImpl passedStyleBuilder(){
        ConditionalStyleBuilderImpl builder = new ConditionalStyleBuilderImpl();
        builder.setConditionalStyleExpressionBuilder(passedStyleExpressionBuilder());
        builder.setStyle(passedStyle());
        return builder;
    }
    @Bean
    public AbstractGenericConditionStyleExpressionBuilder nmiStyleExpressionBuilder() {
        ResultStyleExpressionBuilderImpl builder = new ResultStyleExpressionBuilderImpl();
        builder.setBundleName(RULE_RESULT_I18N);
        builder.setResult("nmi");
        return builder;
    }
    @Bean
    public ConditionalStyleBuilderImpl nmiStyleBuilder(){
        ConditionalStyleBuilderImpl builder = new ConditionalStyleBuilderImpl();
        builder.setConditionalStyleExpressionBuilder(nmiStyleExpressionBuilder());
        builder.setStyle(nmiStyle());
        return builder;
    }
    @Bean
    public AbstractGenericConditionStyleExpressionBuilder naStyleExpressionBuilder() {
        ResultStyleExpressionBuilderImpl builder = new ResultStyleExpressionBuilderImpl();
        builder.setBundleName(RULE_RESULT_I18N);
        builder.setResult("na");
        return builder;
    }
    @Bean
    public ConditionalStyleBuilderImpl naStyleBuilder(){
        ConditionalStyleBuilderImpl builder = new ConditionalStyleBuilderImpl();
        builder.setConditionalStyleExpressionBuilder(naStyleExpressionBuilder());
        builder.setStyle(naStyle());
        return builder;
    }
    @Bean
    public AbstractGenericConditionStyleExpressionBuilder ntStyleExpressionBuilder() {
        ResultStyleExpressionBuilderImpl builder = new ResultStyleExpressionBuilderImpl();
        builder.setBundleName(RULE_RESULT_I18N);
        builder.setResult("nt");
        return builder;
    }
    @Bean
    public ConditionalStyleBuilderImpl ntStyleBuilder(){
        ConditionalStyleBuilderImpl builder = new ConditionalStyleBuilderImpl();
        builder.setConditionalStyleExpressionBuilder(ntStyleExpressionBuilder());
        builder.setStyle(ntStyle());
        return builder;
    }

    ///////////////////////////////////////////////////
    // columns builders
    ///////////////////////////////////////////////////
    @Bean
    public ElementColumnBuilder testColumnBuilder() {
        ElementColumnBuilder builder = new ElementColumnBuilder();
        builder.setColumnWidth(50);
        builder.setColumnTitleBundleName(EXPORT_REPORT_I18N);
        builder.setHeaderStyle(headerStyle());
        builder.setColumnTitleKey("export-report.testNumber");
        builder.setStyle(numericalStyle());
        builder.setPropertyName("testShortLabel");
        builder.setValueClassName("java.lang.String");
        return builder;
    }

    @Bean
    public ElementColumnBuilder criterionColumnBuilder() {
        ElementColumnBuilder builder = new ElementColumnBuilder();
        builder.setColumnWidth(50);
        builder.setColumnTitleBundleName(EXPORT_REPORT_I18N);
        builder.setHeaderStyle(headerStyle());
        builder.setColumnTitleKey("export-report.testCriterion");
        builder.setStyle(numericalStyle());
        builder.setCustomExpressionBuilder(criterionExpressionBuilder());
        return builder;
    }
    @Bean
    public ElementColumnBuilder themeColumnBuilder() {
        ElementColumnBuilder builder = new ElementColumnBuilder();
        builder.setColumnWidth(70);
        builder.setColumnTitleBundleName(EXPORT_REPORT_I18N);
        builder.setHeaderStyle(headerStyle());
        builder.setColumnTitleKey("export-report.testTheme");
        builder.setStyle(textualStyle());
        builder.setCustomExpressionBuilder(themeExpressionBuilder());
        return builder;
    }
    @Bean
    public ElementColumnBuilder testLabelColumnBuilder() {
        ElementColumnBuilder builder = new ElementColumnBuilder();
        builder.setColumnWidth(330);
        builder.setColumnTitleBundleName(EXPORT_REPORT_I18N);
        builder.setHeaderStyle(headerStyle());
        builder.setColumnTitleKey("export-report.testLabel");
        builder.setStyle(textualStyle());
        builder.setCustomExpressionBuilder(testExpressionBuilder());
        return builder;
    }
    @Bean
    public ElementColumnBuilder levelColumnBuilder() {
        ElementColumnBuilder builder = new ElementColumnBuilder();
        builder.setColumnWidth(60);
        builder.setColumnTitleBundleName(EXPORT_REPORT_I18N);
        builder.setHeaderStyle(headerStyle());
        builder.setColumnTitleKey("export-report.testLevel");
        builder.setStyle(textualStyle());
        builder.setCustomExpressionBuilder(levelExpressionBuilder());
        return builder;
    }
    @Bean
    public ElementColumnBuilder resultColumnBuilder() {
        ElementColumnBuilder builder = new ElementColumnBuilder();
        builder.setColumnWidth(70);
        builder.setColumnTitleBundleName(EXPORT_REPORT_I18N);
        builder.setHeaderStyle(headerStyle());
        builder.setColumnTitleKey("export-report.testResult");
        builder.setStyle(textualStyle());
        builder.setCustomExpressionBuilder(resultExpressionBuilder());
        builder.setConditionalStyleBuilderList(Arrays.asList(
            passedStyleBuilder(),
            failedStyleBuilder(),
            nmiStyleBuilder(),
            naStyleBuilder(),
            ntStyleBuilder()));
        return builder;
    }
    @Bean
    public ElementColumnBuilder resultDetailsColumnBuilder() {
        ElementColumnBuilder builder = new ElementColumnBuilder();
        builder.setColumnWidth(70);
        builder.setColumnTitleBundleName(EXPORT_REPORT_I18N);
        builder.setHeaderStyle(headerStyle());
        builder.setColumnTitleKey("export-report.testDetails");
        builder.setStyle(textualStyle());
        builder.setCustomExpressionBuilder(resultDetailsExpressionBuilder());
        return builder;
    }

    /////////////////////////////////////
    // Colors and styles
    /////////////////////////////////////
    @Bean
    public Color passedColor() {
        return new Color(0,174,0);
    }
    @Bean
    public Color failedColor() {
        return new Color(220,35,0);
    }
    @Bean
    public Color nmiColor() {
        return new Color(0,153,255);
    }
    @Bean
    public Color naColor() {
        return new Color(179,179,179);
    }
    @Bean
    public Color ntColor() {
        return new Color(0,0,0);
    }
    @Bean
    public Color headerCellColor() {
        return new Color(255,255,204);
    }
    @Bean
    public Font headerFont() {
        Font font = new Font();
        font.setFontName("DejaVu Serif");
        font.setFontSize(12);
        font.setBold(true);
        return font;
    }
    @Bean
    public Font cellFont() {
        Font font = new Font();
        font.setFontName("DejaVu Serif");
        font.setFontSize(10);
        return font;
    }
    @Bean
    public Font titleFont() {
        Font font = new Font();
        font.setFontName("DejaVu Serif");
        font.setFontSize(14);
        font.setBold(true);
        return font;
    }
    @Bean
    public Font subtitleFont() {
        Font font = new Font();
        font.setFontName("DejaVu Serif");
        font.setFontSize(11);
        return font;
    }
    @Bean
    public Style titleStyle() {
        Style style = new Style();
        style.setFont(titleFont());
        style.setHorizontalAlign(LEFT);
        style.setVerticalAlign(MIDDLE);
        return style;
    }
    @Bean
    public Style subtitleStyle() {
        Style style = new Style();
        style.setFont(subtitleFont());
        style.setHorizontalAlign(LEFT);
        style.setVerticalAlign(MIDDLE);
        return style;
    }
    @Bean
    public Style headerStyle() {
        Style style = new Style();
        style.setFont(headerFont());
        style.setHorizontalAlign(CENTER);
        style.setVerticalAlign(MIDDLE);
        style.setTransparent(false);
        style.setBackgroundColor(headerCellColor());
        return style;
    }
    @Bean
    public Style numericalStyle() {
        Style style = new Style();
        style.setFont(cellFont());
        style.setHorizontalAlign(RIGHT);
        style.setVerticalAlign(MIDDLE);
        return style;
    }
    @Bean
    public Style textualStyle() {
        Style style = new Style();
        style.setFont(cellFont());
        style.setHorizontalAlign(LEFT);
        style.setVerticalAlign(MIDDLE);
        return style;
    }
    @Bean
    public Style passedStyle() {
        Style style = numericalStyle();
        style.setTextColor(passedColor());
        return style;
    }
    @Bean
    public Style failedStyle() {
        Style style = numericalStyle();
        style.setTextColor(failedColor());
        return style;
    }
    @Bean
    public Style nmiStyle() {
        Style style = numericalStyle();
        style.setTextColor(nmiColor());
        return style;
    }
    @Bean
    public Style naStyle() {
        Style style = numericalStyle();
        style.setTextColor(naColor());
        return style;
    }
    @Bean
    public Style ntStyle() {
        Style style = numericalStyle();
        style.setTextColor(ntColor());
        return style;
    }

}
