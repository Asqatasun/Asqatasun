USE tanaguru;

-- MySQL dump 10.11

--
-- Dumping data for table `CRITERION`
--


--
-- Dumping data for table `DECISION_LEVEL`
--


--
-- Dumping data for table `LEVEL`
--

INSERT INTO `LEVEL` (`Id_Level`, `Cd_Level`, `Description`, `Label`, `Rank`) VALUES
(1, 'Aw20-Bz', 'Bronze', 'Bronze', 210),
(2, 'Aw20-Ar', 'Argent', 'Argent', 220),
(3, 'Aw20-Or', 'Or', 'Or', 230);

--
-- Dumping data for table `NOMENCLATURE`
--

INSERT INTO `NOMENCLATURE` (`Id_Nomenclature`, `Cd_Nomenclature`, `Description`, `Long_Label`, `Short_Label`, `Id_Nomenclature_Parent`) VALUES
(1, 'FormsButtonTypes', NULL, NULL, NULL, NULL),
(2, 'JSWindowOpen', NULL, NULL, NULL, NULL),
(3, 'NotificationNewWindowWhitelist', NULL, NULL, NULL, NULL),
(4, 'FormsButtonWhitelist', NULL, NULL, NULL, NULL),
(5, 'FormsButtonBlacklist', NULL, NULL, NULL, NULL),
(6, 'LinkTextWhitelist', NULL, NULL, NULL, NULL),
(7, 'LinkTextBlacklist', NULL, NULL, NULL, NULL),
(8, 'PossibleImageTags', NULL, NULL, NULL, NULL),
(9, 'RelativeCssUnits', NULL, NULL, NULL, NULL),
(10, 'ValidLanguageCode', NULL, NULL, NULL, NULL),
(11, 'DeprecatedRepresentationTags', NULL, NULL, NULL, NULL),
(12, 'UnexplicitPageTitle', NULL, NULL, NULL, NULL),
(13, 'ImageFileExtensions', NULL, NULL, NULL, NULL),
(14, 'MediaListNotAcceptingRelativeUnits', NULL, NULL, NULL, NULL);

--
-- Dumping data for table `NOMENCLATURE_ELEMENT`
--

INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES
('NomenclatureElementImpl', 1, 'reset', NULL, 1),
('NomenclatureElementImpl', 2, 'submit', NULL, 1),
('NomenclatureElementImpl', 3, 'button', NULL, 1),
('NomenclatureElementImpl', 4, 'window.open', NULL, 2),
('NomenclatureElementImpl', 5, 'javascript:window.open', NULL, 2),
('NomenclatureElementImpl', 6, 'new window', NULL, 3),
('NomenclatureElementImpl', 7, 'nouvelle fenêtre', NULL, 3),
('NomenclatureElementImpl', 8, 's''inscrire', NULL, 5),
('NomenclatureElementImpl', 9, 'go', NULL, 5),
('NomenclatureElementImpl', 10, 'inscrire', NULL, 5),
('NomenclatureElementImpl', 11, 'valider', NULL, 5),
('NomenclatureElementImpl', 12, 'envoyer', NULL, 5),
('NomenclatureElementImpl', 13, 'submit', NULL, 5),
('NomenclatureElementImpl', 14, 'poster', NULL, 5),
('NomenclatureElementImpl', 15, 'retour à l''accueil', NULL, 6),
('NomenclatureElementImpl', 16, 'back home', NULL, 6),
('NomenclatureElementImpl', 17, 'here', NULL, 7),
('NomenclatureElementImpl', 18, 'this link', NULL, 7),
('NomenclatureElementImpl', 19, 'there', NULL, 7),
('NomenclatureElementImpl', 20, 'en savoir plus...', NULL, 7),
('NomenclatureElementImpl', 21, 'more', NULL, 7),
('NomenclatureElementImpl', 22, 'en savoir plus', NULL, 7),
('NomenclatureElementImpl', 23, 'ici', NULL, 7),
('NomenclatureElementImpl', 24, 'plus', NULL, 7),
('NomenclatureElementImpl', 25, 'là', NULL, 7),
('NomenclatureElementImpl', 26, 'cliquez ici', NULL, 7),
('NomenclatureElementImpl', 27, 'ce lien', NULL, 7),
('NomenclatureElementImpl', 28, 'post précédent', NULL, 7),
('NomenclatureElementImpl', 29, 'lire la suite', NULL, 7),
('NomenclatureElementImpl', 30, 'click here', NULL, 7),
('NomenclatureElementImpl', 31, 'object', NULL, 8),
('NomenclatureElementImpl', 32, 'img', NULL, 8),
('NomenclatureCssUnitImpl', 33, 'pc', 22, 9),
('NomenclatureCssUnitImpl', 34, 'cm', 19, 9),
('NomenclatureCssUnitImpl', 35, 'in', 18, 9),
('NomenclatureCssUnitImpl', 36, 'pt', 21, 9),
('NomenclatureCssUnitImpl', 37, 'mm', 20, 9),
('NomenclatureElementImpl', 38, 'kk', NULL, 10),
('NomenclatureElementImpl', 39, 'th', NULL, 10),
('NomenclatureElementImpl', 40, 'cs', NULL, 10),
('NomenclatureElementImpl', 41, 'vi', NULL, 10),
('NomenclatureElementImpl', 42, 'hi', NULL, 10),
('NomenclatureElementImpl', 43, 'ur', NULL, 10),
('NomenclatureElementImpl', 44, 'af', NULL, 10),
('NomenclatureElementImpl', 45, 'tl', NULL, 10),
('NomenclatureElementImpl', 46, 'bh', NULL, 10),
('NomenclatureElementImpl', 47, 'ta', NULL, 10),
('NomenclatureElementImpl', 48, 'zh', NULL, 10),
('NomenclatureElementImpl', 49, 'mo', NULL, 10),
('NomenclatureElementImpl', 50, 'sh', NULL, 10),
('NomenclatureElementImpl', 51, 'fy', NULL, 10),
('NomenclatureElementImpl', 52, 'bn', NULL, 10),
('NomenclatureElementImpl', 53, 'sw', NULL, 10),
('NomenclatureElementImpl', 54, 'sd', NULL, 10),
('NomenclatureElementImpl', 55, 'mi', NULL, 10),
('NomenclatureElementImpl', 56, 'la', NULL, 10),
('NomenclatureElementImpl', 57, 'gv', NULL, 10),
('NomenclatureElementImpl', 58, 'no', NULL, 10),
('NomenclatureElementImpl', 59, 'or', NULL, 10),
('NomenclatureElementImpl', 60, 'tk', NULL, 10),
('NomenclatureElementImpl', 61, 'in', NULL, 10),
('NomenclatureElementImpl', 62, 'ko', NULL, 10),
('NomenclatureElementImpl', 63, 'et', NULL, 10),
('NomenclatureElementImpl', 64, 'aa', NULL, 10),
('NomenclatureElementImpl', 65, 'ja', NULL, 10),
('NomenclatureElementImpl', 66, 'rm', NULL, 10),
('NomenclatureElementImpl', 67, 'ks', NULL, 10),
('NomenclatureElementImpl', 68, 'ca', NULL, 10),
('NomenclatureElementImpl', 69, 'sn', NULL, 10),
('NomenclatureElementImpl', 70, 'en', NULL, 10),
('NomenclatureElementImpl', 71, 'nl', NULL, 10),
('NomenclatureElementImpl', 72, 'ss', NULL, 10),
('NomenclatureElementImpl', 73, 'ab', NULL, 10),
('NomenclatureElementImpl', 74, 'tg', NULL, 10),
('NomenclatureElementImpl', 75, 'st', NULL, 10),
('NomenclatureElementImpl', 76, 'da', NULL, 10),
('NomenclatureElementImpl', 77, 'bo', NULL, 10),
('NomenclatureElementImpl', 78, 'sg', NULL, 10),
('NomenclatureElementImpl', 79, 'na', NULL, 10),
('NomenclatureElementImpl', 80, 'gl', NULL, 10),
('NomenclatureElementImpl', 81, 'si', NULL, 10),
('NomenclatureElementImpl', 82, 'rw', NULL, 10),
('NomenclatureElementImpl', 83, 'uz', NULL, 10),
('NomenclatureElementImpl', 84, 'te', NULL, 10),
('NomenclatureElementImpl', 85, 'kl', NULL, 10),
('NomenclatureElementImpl', 86, 'el', NULL, 10),
('NomenclatureElementImpl', 87, 'ba', NULL, 10),
('NomenclatureElementImpl', 88, 'tn', NULL, 10),
('NomenclatureElementImpl', 89, 'br', NULL, 10),
('NomenclatureElementImpl', 90, 'ky', NULL, 10),
('NomenclatureElementImpl', 91, 'ps', NULL, 10),
('NomenclatureElementImpl', 92, 'es', NULL, 10),
('NomenclatureElementImpl', 93, 'zh', NULL, 10),
('NomenclatureElementImpl', 94, 'sr', NULL, 10),
('NomenclatureElementImpl', 95, 'mk', NULL, 10),
('NomenclatureElementImpl', 96, 'qu', NULL, 10),
('NomenclatureElementImpl', 97, 'ka', NULL, 10),
('NomenclatureElementImpl', 98, 'fr', NULL, 10),
('NomenclatureElementImpl', 99, 'ne', NULL, 10),
('NomenclatureElementImpl', 100, 'he', NULL, 10),
('NomenclatureElementImpl', 101, 'co', NULL, 10),
('NomenclatureElementImpl', 102, 'uk', NULL, 10),
('NomenclatureElementImpl', 103, 'ga', NULL, 10),
('NomenclatureElementImpl', 104, 'tt', NULL, 10),
('NomenclatureElementImpl', 105, 'ms', NULL, 10),
('NomenclatureElementImpl', 106, 'pt', NULL, 10),
('NomenclatureElementImpl', 107, 'bg', NULL, 10),
('NomenclatureElementImpl', 108, 'ku', NULL, 10),
('NomenclatureElementImpl', 109, 'to', NULL, 10),
('NomenclatureElementImpl', 110, 'so', NULL, 10),
('NomenclatureElementImpl', 111, 'ln', NULL, 10),
('NomenclatureElementImpl', 112, 'zu', NULL, 10),
('NomenclatureElementImpl', 113, 'fj', NULL, 10),
('NomenclatureElementImpl', 114, 'sk', NULL, 10),
('NomenclatureElementImpl', 115, 'is', NULL, 10),
('NomenclatureElementImpl', 116, 'ik', NULL, 10),
('NomenclatureElementImpl', 117, 'xh', NULL, 10),
('NomenclatureElementImpl', 118, 'ro', NULL, 10),
('NomenclatureElementImpl', 119, 'yi', NULL, 10),
('NomenclatureElementImpl', 120, 'be', NULL, 10),
('NomenclatureElementImpl', 121, 'ar', NULL, 10),
('NomenclatureElementImpl', 122, 'fi', NULL, 10),
('NomenclatureElementImpl', 123, 'my', NULL, 10),
('NomenclatureElementImpl', 124, 'gn', NULL, 10),
('NomenclatureElementImpl', 125, 'dz', NULL, 10),
('NomenclatureElementImpl', 126, 'su', NULL, 10),
('NomenclatureElementImpl', 127, 'hu', NULL, 10),
('NomenclatureElementImpl', 128, 'mt', NULL, 10),
('NomenclatureElementImpl', 129, 'lt', NULL, 10),
('NomenclatureElementImpl', 130, 'de', NULL, 10),
('NomenclatureElementImpl', 131, 'pl', NULL, 10),
('NomenclatureElementImpl', 132, 'tr', NULL, 10),
('NomenclatureElementImpl', 133, 'kn', NULL, 10),
('NomenclatureElementImpl', 134, 'cy', NULL, 10),
('NomenclatureElementImpl', 135, 'mg', NULL, 10),
('NomenclatureElementImpl', 136, 'tw', NULL, 10),
('NomenclatureElementImpl', 137, 'hr', NULL, 10),
('NomenclatureElementImpl', 138, 'hy', NULL, 10),
('NomenclatureElementImpl', 139, 'wo', NULL, 10),
('NomenclatureElementImpl', 140, 'li', NULL, 10),
('NomenclatureElementImpl', 141, 'ti', NULL, 10),
('NomenclatureElementImpl', 142, 'eu', NULL, 10),
('NomenclatureElementImpl', 143, 'ia', NULL, 10),
('NomenclatureElementImpl', 144, 'sv', NULL, 10),
('NomenclatureElementImpl', 145, 'iu', NULL, 10),
('NomenclatureElementImpl', 146, 'gu', NULL, 10),
('NomenclatureElementImpl', 147, 'gd', NULL, 10),
('NomenclatureElementImpl', 148, 'oc', NULL, 10),
('NomenclatureElementImpl', 149, 'fo', NULL, 10),
('NomenclatureElementImpl', 150, 'lv', NULL, 10),
('NomenclatureElementImpl', 151, 'as', NULL, 10),
('NomenclatureElementImpl', 152, 'sm', NULL, 10),
('NomenclatureElementImpl', 153, 'ug', NULL, 10),
('NomenclatureElementImpl', 154, 'sa', NULL, 10),
('NomenclatureElementImpl', 155, 'sl', NULL, 10),
('NomenclatureElementImpl', 156, 'yo', NULL, 10),
('NomenclatureElementImpl', 157, 'mr', NULL, 10),
('NomenclatureElementImpl', 158, 'vo', NULL, 10),
('NomenclatureElementImpl', 159, 'sq', NULL, 10),
('NomenclatureElementImpl', 160, 'fa', NULL, 10),
('NomenclatureElementImpl', 161, 'id', NULL, 10),
('NomenclatureElementImpl', 162, 'ru', NULL, 10),
('NomenclatureElementImpl', 163, 'az', NULL, 10),
('NomenclatureElementImpl', 164, 'ts', NULL, 10),
('NomenclatureElementImpl', 165, 'km', NULL, 10),
('NomenclatureElementImpl', 166, 'rn', NULL, 10),
('NomenclatureElementImpl', 167, 'it', NULL, 10),
('NomenclatureElementImpl', 168, 'ml', NULL, 10),
('NomenclatureElementImpl', 169, 'mn', NULL, 10),
('NomenclatureElementImpl', 170, 'ha', NULL, 10),
('NomenclatureElementImpl', 171, 'om', NULL, 10),
('NomenclatureElementImpl', 172, 'am', NULL, 10),
('NomenclatureElementImpl', 173, 'bi', NULL, 10),
('NomenclatureElementImpl', 174, 'ji', NULL, 10),
('NomenclatureElementImpl', 175, 'iw', NULL, 10),
('NomenclatureElementImpl', 176, 'jv', NULL, 10),
('NomenclatureElementImpl', 177, 'ay', NULL, 10),
('NomenclatureElementImpl', 178, 'eo', NULL, 10),
('NomenclatureElementImpl', 179, 'pa', NULL, 10),
('NomenclatureElementImpl', 180, 'ie', NULL, 10),
('NomenclatureElementImpl', 181, 'lo', NULL, 10),
('NomenclatureElementImpl', 182, 'BASEFONT', NULL, 11),
('NomenclatureElementImpl', 183, 'CENTER', NULL, 11),
('NomenclatureElementImpl', 184, 'DIR', NULL, 11),
('NomenclatureElementImpl', 185, 'FONT', NULL, 11),
('NomenclatureElementImpl', 186, 'ISINDEX', NULL, 11),
('NomenclatureElementImpl', 187, 'MENU', NULL, 11),
('NomenclatureElementImpl', 188, 'S', NULL, 11),
('NomenclatureElementImpl', 189, 'STRIKE', NULL, 11),
('NomenclatureElementImpl', 190, 'U', NULL, 11),
('NomenclatureElementImpl', 191, '', NULL, 12),
('NomenclatureElementImpl', 192, 'Bienvenue dans Adobe GoLive 6', NULL, 12),
('NomenclatureElementImpl', 193, 'Page title', NULL, 12),
('NomenclatureElementImpl', 194, 'Titre de la page', NULL, 12),
('NomenclatureElementImpl', 195, 'jpg', NULL, 13),
('NomenclatureElementImpl', 196, 'gif', NULL, 13),
('NomenclatureElementImpl', 197, 'jpeg', NULL, 13),
('NomenclatureElementImpl', 198, 'png', NULL, 13),
('NomenclatureElementImpl', 199, 'bmp', NULL, 13),
('NomenclatureElementImpl', 200, 'all', NULL, 14),
('NomenclatureElementImpl', 201, 'screen', NULL, 14),
('NomenclatureElementImpl', 202, 'projection', NULL, 14),
('NomenclatureElementImpl', 203, 'handheld', NULL, 14),
('NomenclatureElementImpl', 204, 'tv', NULL, 14);

--
-- Dumping data for table `REFERENCE`
--


--
-- Dumping data for table `RULE`
--


--
-- Dumping data for table `RULE_PACKAGE`
--


--
-- Dumping data for table `SCOPE`
--

INSERT INTO `SCOPE` (`Id_Scope`, `Code`, `Description`, `Label`) VALUES
(1, 'page', 'page', 'Page'),
(2, 'site', 'site', 'Site');

--
-- Dumping data for table `STANDARD_MESSAGE`
--

INSERT INTO `STANDARD_MESSAGE` (`Id_Standard_Message`, `Cd_Standard_Message`, `Label`, `Text`) VALUES (1,'AttributeMissing',NULL,'The attribute is missing');
INSERT INTO `STANDARD_MESSAGE` (`Id_Standard_Message`, `Cd_Standard_Message`, `Label`, `Text`) VALUES (2,'BlackListedValue',NULL,'The value is blacklisted');
INSERT INTO `STANDARD_MESSAGE` (`Id_Standard_Message`, `Cd_Standard_Message`, `Label`, `Text`) VALUES (3,'VerifyValue',NULL,'The value needs verification');
INSERT INTO `STANDARD_MESSAGE` (`Id_Standard_Message`, `Cd_Standard_Message`, `Label`, `Text`) VALUES (4,'ChildNodeMissing',NULL,'The child node is missing');
INSERT INTO `STANDARD_MESSAGE` (`Id_Standard_Message`, `Cd_Standard_Message`, `Label`, `Text`) VALUES (5,'ValueEmpty',NULL,'The value is empty');
INSERT INTO `STANDARD_MESSAGE` (`Id_Standard_Message`, `Cd_Standard_Message`, `Label`, `Text`) VALUES (6,'NotMatchExpression',NULL,'Regular expression not match');
INSERT INTO `STANDARD_MESSAGE` (`Id_Standard_Message`, `Cd_Standard_Message`, `Label`, `Text`) VALUES (7,'LengthTooLong',NULL,'Length too long');
INSERT INTO `STANDARD_MESSAGE` (`Id_Standard_Message`, `Cd_Standard_Message`, `Label`, `Text`) VALUES (8,'AccessKeyNotMatch',NULL,'AccessKey not match');
INSERT INTO `STANDARD_MESSAGE` (`Id_Standard_Message`, `Cd_Standard_Message`, `Label`, `Text`) VALUES (9,'BadUnitType',NULL,'Bad unit type');
INSERT INTO `STANDARD_MESSAGE` (`Id_Standard_Message`, `Cd_Standard_Message`, `Label`, `Text`) VALUES (10,'InvalidLanguageCode',NULL,'Invalid language code');
INSERT INTO `STANDARD_MESSAGE` (`Id_Standard_Message`, `Cd_Standard_Message`, `Label`, `Text`) VALUES (11,'DeprecatedRepresentationTagFound',NULL,'Deprecated tag found : {0} at line {1}');
INSERT INTO `STANDARD_MESSAGE` (`Id_Standard_Message`, `Cd_Standard_Message`, `Label`, `Text`) VALUES (12,'DecorativeImageWithNotEmptyAltAttribute',NULL,'A decorative image with a not empty alt attribute has been found');
INSERT INTO `STANDARD_MESSAGE` (`Id_Standard_Message`, `Cd_Standard_Message`, `Label`, `Text`) VALUES (13,'NotPertinentAltAttribute',NULL,'The alt attribute of the tag is not pertinent');
INSERT INTO `STANDARD_MESSAGE` (`Id_Standard_Message`, `Cd_Standard_Message`, `Label`, `Text`) VALUES (14,'HeaderTagNotHierarchicallyWelldefined',NULL,'The hierarchy between the title (h tags) of the page is not pertinent');


--
-- Dumping data for table `TEST`
--

INSERT INTO `TEST` (`Id_Test`, `Cd_Test`, `Description`, `Label`, `Rank`, `Rule_Archive_Name`, `Rule_Class_Name`, `Id_Criterion`, `Id_Decision_Level`, `Id_Level`, `Id_Rule`, `Id_Scope`) VALUES
(1, 'Aw20-01011', NULL, '1.1.1', 2010101, 'accessiweb2.0', 'org.opens.tanaguru.rule.accessiweb20.Aw20Rule01011', NULL, NULL, 1, NULL, 1),
(2, 'Aw20-01012', NULL, '1.1.2', 2010102, 'accessiweb2.0', 'org.opens.tanaguru.rule.accessiweb20.Aw20Rule01012', NULL, NULL, 1, NULL, 1),
(3, 'Aw20-01021', NULL, '1.2.1', 2010201, 'accessiweb2.0', 'org.opens.tanaguru.rule.accessiweb20.Aw20Rule01021', NULL, NULL, 1, NULL, 1),
(4, 'Aw20-01031', NULL, '1.3.1', 2010301, 'accessiweb2.0', 'org.opens.tanaguru.rule.accessiweb20.Aw20Rule01031', NULL, NULL, 1, NULL, 1),
(5, 'Aw20-08051', NULL, '8.5.1', 2080501, 'accessiweb2.0', 'org.opens.tanaguru.rule.accessiweb20.Aw20Rule08051', NULL, NULL, 1, NULL, 1),
(6, 'Aw20-08061', NULL, '8.6.1', 2080601, 'accessiweb2.0', 'org.opens.tanaguru.rule.accessiweb20.Aw20Rule08061', NULL, NULL, 1, NULL, 1),
(7, 'Aw20-09011', NULL, '9.1.1', 2090101, 'accessiweb2.0', 'org.opens.tanaguru.rule.accessiweb20.Aw20Rule09011', NULL, NULL, 1, NULL, 1),
(8, 'Aw20-09012', NULL, '9.1.2', 2090102, 'accessiweb2.0', 'org.opens.tanaguru.rule.accessiweb20.Aw20Rule09012', NULL, NULL, 1, NULL, 1),
(9, 'Aw20-10011', NULL, '10.1.1', 2100101, 'accessiweb2.0', 'org.opens.tanaguru.rule.accessiweb20.Aw20Rule10011', NULL, NULL, 1, NULL, 1),
(10, 'Aw20-10041', NULL, '10.4.1', 2100401, 'accessiweb2.0', 'org.opens.tanaguru.rule.accessiweb20.Aw20Rule10041', NULL, NULL, 1, NULL, 1);
(11, 'Aw20-02011', NULL, '2.1.1', 2020101, 'accessiweb2.0', 'org.opens.tanaguru.rule.accessiweb20.Aw20Rule02011', NULL, NULL, 1, NULL, 1);
(11, 'Aw20-02012', NULL, '2.1.2', 2020102, 'accessiweb2.0', 'org.opens.tanaguru.rule.accessiweb20.Aw20Rule02012', NULL, NULL, 1, NULL, 1);


--
-- Dumping data for table `THEME`
--


--
-- Dumping data for table `WEB_RESOURCE`
--
