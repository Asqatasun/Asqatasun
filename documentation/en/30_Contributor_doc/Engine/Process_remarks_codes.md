# List of *Process Remarks* codes

Each Process Remark message is identified uniquely by a code.

All Process Remarks codes are declared in the source code in [Rules-commons > Rules > keystore > RemarkMessageStore.java](https://github.com/Asqatasun/Asqatasun/blob/develop/rules/rules-commons/src/main/java/org/asqatasun/rules/keystore/RemarkMessageStore.java). To see how a code is translated in plain language (English, French, Spanish...), you may have a look at [Rules-commons > Resources > i18n](https://github.com/Asqatasun/Asqatasun/tree/develop/rules/rules-commons/src/main/resources/i18n).

Here is the list of **Process Remarks codes** nicely formatted:

## General

* `ManualCheckOnElements`
* `NoPatternDetected`
* `TitleAttributeMissing`
* `IdMissing`
* `AriaLabelledbyEmpty`
* `ForMissing`
* `IdNotUnique`

## Doctype

* `DoctypeMissing`
* `BadDoctypeLocation`
* `InvalidDoctypeDeclaration`
* `DoctypeAbsentCheckHtml5AndCheckManually`
* `Html5DoctypeDetectedCheckManually`

## Images
    
* `AltMissing`
* `DecorativeElementWithNotEmptyAltAttribute`
* `DecorativeElementWithTitleAttribute`
* `CheckAlternativeOfDecorativeElement`
* `CheckNatureAndAlternativeOfElement`
* `CheckNatureOfImageAndPresenceOfAlternativeMechanism`
* `CheckPresenceOfAlternativeMechanismForInformativeImage`
* `CheckNatureOfElementWithNotEmptyAltAttribute`
* `CheckNatureOfElementWithNotEmptyTitleAttribute`
* `CheckNatureOfElementWithEmptyAltAttribute`
* `CheckNatureOfElementWithEmptyTitleAttribute`
* `NotPertinentAlt`
* `TitleNotIdenticalToAlt`
* `CheckPertinenceOfAltAttributeOfInformativeImage`
* `CheckNatureOfImageWithNotPertinentAlt`
* `CheckNatureOfElementWithEmptyAltAttribute`
* `CheckNatureOfElementWithNotEmptyAltAttribute`
* `CheckNatureOfImageAndAltPertinence`
* `CheckCaptchaAlternative`
* `CheckCaptchaAlternativeAccess`
* `CheckNatureOfImageAndLongdescDefinition`
* `CheckLongdescDefinitionOfInformativeImage`
* `CheckDescriptionPresenceOfInformativeImage`
* `CheckNatureOfImageAndDescriptionPresence`
* `CheckDescriptionPertinenceOfInformativeImage`
* `CheckAtRestitutionOfDescriptionOfInformativeImage`
* `CheckAtRestitutionOfAlternativeOfCaptcha`
* `CheckAtRestitutionOfAlternativeOfInformativeImage`
* `CheckNatureOfImageAndAtRestitutionOfAlternative`
* `CheckNatureOfImageAndDescriptionPertinence`
* `CheckNatureOfImageAndAtRestitutionOfDescription`
* `AlternativeTooLong`
* `AltSvgDetect`
* `DecorativeSvgWithoutRoleImgAttribute`
* `DecorativeSvgOrChildrenWithAriaAttribute`
* `DecorativeSvgWithNotEmptyTitleOrDescTags`
* `DecorativeSvgWithTitleAttribute`
* `SvgWithoutRoleImage`
* `InformativeSvgWithNotPertinentAlternative`
* `CheckNatureOfSvgWithNotPertinentAlternative`
* `CheckPertinenceOfAlternativeOfInformativeSvg`
* `CheckNatureOfSvgAndAlternativePertinence`
* `SuspectedInformativeSvgWithAriaAttributeDetectedOnElementOrChild`
* `SuspectedInformativeSvgWithDescOrTitleChildTag`
* `SuspectedInformativeSvgWithTitleAttributeOnElementOrChild`
* `SuspectedWellFormedDecorativeSvg`
* `CheckNatureOfImageAndStyledTextPresence`
* `CheckStyledTextPresenceOfInformativeImage`

## Frames
    
* `NotPertinentTitleOfFrame`
* `NotPertinentTitleOfIframe`
* `CheckTitleOfFramePertinence`
* `CheckTitleOfIframePertinence`
* `EmtpyTitleOfFrame`


## Form

* `NotPertinentLegend`
* `CheckLegendPertinence`
* `OptgroupWithoutLabel`
* `FieldsetWithoutLegend`
* `CheckSelectUsage`
* `CheckLabelPertinence`
* `FormElementWithoutLabel`
* `FormElementWithNotUniqueLabel`
* `CheckTitleOfFormElement`
* `NotPertinentOptgroupLabel`
* `CheckOptgroupLabelPertinence`
* `FormElementWithoutIdentifier`
* `InvalidFormField`
* `InvalidLabel`
* `InvalidInput`

## Tables

* `SummaryMissing`
* `CheckNatureOfTableWithEmptySummaryAttribute`
* `CheckNatureOfTableWithNotEmptySummaryAttribute`
* `CheckNatureOfTableWithSummaryAttribute`
* `CheckNatureOfTableWithoutSummaryAttribute`
* `CaptionMissing`
* `CaptionMissingOnComplexTable`
* `CheckNatureOfTableWithoutCaptionChildElement`
* `CheckTableWithoutCaptionChildElementIsNotComplex`
* `CheckNatureOfTableWithCaptionChildElement`
* `CheckTableWithCaptionChildElementIsComplex`
* `NotEmptySummaryForPresentationTable`
* `NotPertinentSummaryForDataTable`
* `NotPertinentCaptionForDataTable`
* `NotPertinentCaptionForComplexTable`
* `CheckSummaryPertinenceForDataTable`
* `CheckCaptionPertinenceForDataTable`
* `CheckCaptionPertinenceForComplexTable`
* `CheckNatureOfTableForNotPertinentSummary`
* `CheckNatureOfTableForNotPertinentCaption`
* `CheckTableIsComplexForNotPertinentCaption`
* `CheckNatureOfTableAndSummaryPertinence`
* `CheckNatureOfTableAndCaptionPertinence`
* `CheckTableIsComplexAndCaptionPertinence`
* `CheckNatureOfTableAndLinearisedContent`
* `CheckUsageOfHeaderForDataTable`
* `CheckNatureOfTableAndUsageOfHeaders`
* `CheckDefinitionOfHeaderForDataTable`
* `CheckNatureOfTableAndHeadersDefinition`
* `CheckLinearisedContent`
* `PresentationTableWithForbiddenMarkup`
* `PresentationTableWithoutAriaMarkup`
* `CheckTableIsPresentationWithRoleAria`
* `CheckTableIsNotPresentationWithoutRoleAria`
* `CheckTableIsDataTable`
* `CheckTableIsPresentationTable`
* `DataTableWithoutHeader`
* `HeaderDetectedCheckAllHeadersAreWellFormed`

## Mandatory elements

* `TitleTagMissing`
* `NotPertinentTitle`
* `CheckTitlePertinence`
* `LangAttributeMissingOnHtml`
* `LangAttributeMissingOnWholePage`
* `WrongLanguageDeclaration`
* `SuspectedRelevantLanguageDeclaration`
* `SuspectedIrrelevantLanguageDeclaration`
* `IrrelevantLanguageDeclaration`
* `UndetectedLanguage`
* `MalformedLanguage`
* `LangChangeMissingOnElementOrOneOfItsParent`
* `CheckManuallyShortText`
* `LinkWithoutTarget`
* `FieldsetNotWithinForm`

## Links
    
* `EmptyLink`
* `UnexplicitLinkWithContext`
* `CheckLinkWithContextPertinence`
* `CheckLinkWithoutContextPertinence`
* `CheckLinkPertinence`
* `CheckButtonTitlePertinence`
* `CheckButtonWithSameTextLeadToSameAction`
* `UnexplicitLink`
* `NotPertinentLinkTitle`
* `SuspectedNotPertinentLinkTitle`
* `SuspectedPertinentLinkTitle`
* `EmptyLinkTitle`
* `IdenticalLinkWithDifferentTarget`
* `SuspectedIdenticalLinkWithDifferentTarget`

## Scripts

* `ContextChangedScriptDetected`

## Consultation

* `NotImmediateRedirectionViaMeta`
* `OfficeDocumentDetected`
* `FileToDownloadDetectedCheckFormat`
* `FileToDownloadDetectedCheckWeight`
* `FileToDownloadDetectedCheckLang`
* `CheckManuallyLinkWithoutExtension`
* `CheckDownloadableDocumentFromForm`
* `CheckUserIsWarnedWhenNewWindowOpen`
* `CheckUserIsWarnedInCaseOfNewWindow`
* `CheckJavaScriptPromptANewWindow`

## Structure of information

* `HeaderTagNotHierarchicallyWelldefined`
* `H1TagMissing`
* `NotPertinentHeading`
* `CheckHeadingPertinence`
* `PresentationTagFound`
* `PresentationAttrFound`
* `DetectedTag`

## Presentation of information

* `HiddenTextDetected`
* `InvisibleOutlineOnFocus`
* `CheckManuallyOutlineForFormElementAndIframe`

## CSS

* `BadUnitType`
* `ContentCssPropertyDetected`

## Colors and contrast

* `BadContrast`
* `BadContrastHiddenElement`
* `BadContrastButAlternativeContrastMechanismOnPage`
* `CheckContrastManually`
* `NotTreatedBackgroundColor`
* `CheckTheContrastOfImagesWithText`

## Scripts

* `OnKeyPressMissing`
* `CheckOnClickDefinitionInJs`

## SEO

* `MetaDescriptionTagMissing`
* `MoreThanOneMetaDescriptionTag`
* `MetaDescriptionTagLengthExceedLimit`
* `UrlLengthExceedLimit`
* `TitleTagLengthExceedLimit`
* `MetaDescriptionNotUnique`
* `MetaDescriptionIdenticalTo`
* `EmptyMetaDescriptionTag`
* `CheckRelevancyMetaDescriptionTag`
* `TitleNotUnique`
* `TitleIdenticalTo`
* `H1NotUnique`
* `H1IdenticalTo`
* `SourceCodeNotUnique`
* `SourceCodeIdenticalTo`
* `RobotsTxtMissing`
* `SitemapMissing`
* `FramesetDetected`
* `notRelevantH1Tag`
* `checkRelevancyH1Tag`
* `MoreThanOneH1Tag`
* `FlashContentDetected`
* `SuspectedFlashContentDetected`
* `IdenticalH1AndTitleTags`
* `UrlParametersDetected`
* `UrlPathUnderscoreDetected`
* `CheckALinkIsAssociatedWithAServerSidedImageMap`

