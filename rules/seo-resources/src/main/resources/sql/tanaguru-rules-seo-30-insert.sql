SET foreign_key_checks=0;


--
-- Dumping data for table `NOMENCLATURE`
--
INSERT IGNORE INTO `NOMENCLATURE` (`Id_Nomenclature`, `Cd_Nomenclature`, `Description`, `Long_Label`, `Short_Label`, `Id_Nomenclature_Parent`) VALUES
(7, 'LinkTextBlacklist', NULL, NULL, NULL, NULL),
(12, 'UnexplicitPageTitle', NULL, NULL, NULL, NULL),
(17, 'RecommendedDoctypeDeclarations', NULL, NULL, NULL, NULL);

INSERT IGNORE INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES
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
('NomenclatureElementImpl', 191, '', NULL, 12),
('NomenclatureElementImpl', 192, 'Bienvenue dans Adobe GoLive 6', NULL, 12),
('NomenclatureElementImpl', 193, 'Page title', NULL, 12),
('NomenclatureElementImpl', 194, 'Titre de la page', NULL, 12),
('NomenclatureElementImpl', 237, '<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">', NULL, 17),
('NomenclatureElementImpl', 238, '<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">', NULL, 17),
('NomenclatureElementImpl', 239, '<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">', NULL, 17),
('NomenclatureElementImpl', 240, '<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">', NULL, 17),
('NomenclatureElementImpl', 241, '<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">', NULL, 17),
('NomenclatureElementImpl', 242, '<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">', NULL, 17),
('NomenclatureElementImpl', 243, '<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">', NULL, 17),
('NomenclatureElementImpl', 244, '<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML Basic 1.1//EN" "http://www.w3.org/TR/xhtml-basic/xhtml-basic11.dtd">', NULL, 17),
('NomenclatureElementImpl', 245, '<!DOCTYPE math PUBLIC "-//W3C//DTD MathML 2.0//EN" "http://www.w3.org/TR/MathML2/dtd/mathml2.dtd">', NULL, 17),
('NomenclatureElementImpl', 246, '<!DOCTYPE math SYSTEM "http://www.w3.org/Math/DTD/mathml1/mathml.dtd">', NULL, 17),
('NomenclatureElementImpl', 247, '<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1 plus MathML 2.0 plus SVG 1.1//EN" "http://www.w3.org/2002/04/xhtml-math-svg/xhtml-math-svg.dtd">', NULL, 17),
('NomenclatureElementImpl', 248, '<!DOCTYPE svg:svg PUBLIC "-//W3C//DTD XHTML 1.1 plus MathML 2.0 plus SVG 1.1//EN" "http://www.w3.org/2002/04/xhtml-math-svg/xhtml-math-svg.dtd">', NULL, 17),
('NomenclatureElementImpl', 249, '<!DOCTYPE html PUBLIC "-//IETF//DTD HTML 2.0//EN">', NULL, 17),
('NomenclatureElementImpl', 250, '<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">', NULL, 17),
('NomenclatureElementImpl', 251, '<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML Basic 1.0//EN" "http://www.w3.org/TR/xhtml-basic/xhtml-basic10.dtd">', NULL, 17),
('NomenclatureElementImpl', 252, 'accueil', NULL, 12),
('NomenclatureElementImpl', 253, 'sommaire', NULL, 12),
('NomenclatureElementImpl', 254, 'bienvenue', NULL, 12),
('NomenclatureElementImpl', 255, 'welcome', NULL, 12),
('NomenclatureElementImpl', 256, 'homepage', NULL, 12),
('NomenclatureElementImpl', 257, 'home page', NULL, 12),
('NomenclatureElementImpl', 258, 'a propos', NULL, 12),
('NomenclatureElementImpl', 259, 'about', NULL, 12),
('NomenclatureElementImpl', 260, 'resultat de la recherche', NULL, 12),
('NomenclatureElementImpl', 261, 'search result', NULL, 12),
('NomenclatureElementImpl', 262, 'summary', NULL, 12),
('NomenclatureElementImpl', 263, 'index', NULL, 12),
('NomenclatureElementImpl', 264, 'home', NULL, 12),
('NomenclatureElementImpl', 265, '<!DOCTYPE HTML>', NULL, 17);

--
-- table `STANDARD_MESSAGE`
--
INSERT IGNORE INTO `STANDARD_MESSAGE` (`Id_Standard_Message`, `Cd_Standard_Message`, `Label`, `Text`) VALUES
(1, 'AttributeMissing', NULL, 'The attribute is missing'),
(2, 'BlackListedValue', NULL, 'The value is blacklisted'),
(3, 'VerifyValue', NULL, 'The value needs verification'),
(4, 'ChildNodeMissing', NULL, 'The child node is missing'),
(5, 'ValueEmpty', NULL, 'The value is empty'),
(6, 'NotMatchExpression', NULL, 'Regular expression not match'),
(7, 'LengthTooLong', NULL, 'Length too long'),
(8, 'AccessKeyNotMatch', NULL, 'AccessKey not match'),
(9, 'BadUnitType', NULL, 'Bad unit type'),
(10, 'InvalidLanguageCode', NULL, 'Invalid language code'),
(11, 'DeprecatedRepresentationTagFound', NULL, 'Deprecated tag found : {0} at line {1}'),
(12, 'DecorativeImageWithNotEmptyAltAttribute', NULL, 'A decorative image with a not empty alt attribute has been found'),
(13, 'NotPertinentAltAttribute', NULL, 'The alt attribute of the tag is not pertinent'),
(14, 'HeaderTagNotHierarchicallyWelldefined', NULL, 'The hierarchy between the title (h tags) of the page is not pertinent'),
(15, 'TitleTagMissing', NULL, 'Title tag is missing'),
(16, 'H1TagMissing', NULL, 'H1 tag is missing'),
(17, 'LinkContentMissing', NULL, 'Link content is missing'),
(18, 'TitleTagNotRelevant', NULL, 'Title tag is not relevant'),
(19, 'DoctypeMissing', NULL, 'The doctype declaration is missing'),
(20, 'WrongDoctypeDeclaration', NULL, 'The doctype declaration is not recommended by the W3C');

--
-- table `EVIDENCE`
--
INSERT IGNORE INTO `EVIDENCE` (`Id_Evidence`, `Cd_Evidence`, `Description`, `Long_Label`) VALUES
(1, 'Element-Name', NULL, NULL),
(2, 'src', NULL, NULL),
(3, 'Css-Selector', NULL, NULL),
(4, 'Previous-H-Tag-Index', NULL, NULL),
(5, 'First-H-Tag-Index', NULL, NULL),
(6, 'Node', NULL, NULL),
(7, 'href', NULL, NULL),
(8, 'Link-Title-Attribut', NULL, NULL),
(9, 'Link-Text', NULL, NULL),
(10, 'Link-Href-Attribut', NULL, NULL),
(1000, 'Url', NULL, NULL),
(1001, 'Title', NULL, NULL),
(1002, 'H1', NULL, NULL),
(1003, 'MetaDescription', NULL, NULL);

INSERT IGNORE INTO `SCOPE` (`Id_Scope`, `Code`, `Description`, `Label`) VALUES
(1, 'page', 'page', 'Page'),
(2, 'site', 'site', 'Site'),
(3, 'site&page', 'site and page', 'Site and Page');

--
-- table `THEME`
--
INSERT IGNORE INTO `THEME` (`ID_THEME`, `CD_THEME`, `DESCRIPTION`, `LABEL`, `RANK`) VALUES
(1001, 'Seo-01', NULL, 'Bases du Referencement', 01),
(1002, 'Seo-02', NULL, 'Images', 02),
(1003, 'Seo-03', NULL, 'Cadres', 03),
(1004, 'Seo-04', NULL, 'Tableaux', 04),
(1005, 'Seo-05', NULL, 'Liens', 05),
(1006, 'Seo-06', NULL, 'Eléments Obligatoires', 06),
(1007, 'Seo-07', NULL, 'Structuration de l\'information', 07),
(1009, 'Seo-09', NULL, 'Navigation', 09),
(1008, 'Seo-08', NULL, 'Multimedia', 08);

--
-- table `REFERENCE`
--
INSERT IGNORE INTO `REFERENCE` (`ID_REFERENCE`, `CD_REFERENCE`, `DESCRIPTION`, `LABEL`, `URL`, `RANK`) VALUES
(1000, 'Seo', NULL, 'Seo Open-s', '', 1000);

--
-- table `CRITERION`
--
INSERT IGNORE INTO `CRITERION` (`ID_CRITERION`, `reference_ID_REFERENCE`, `theme_ID_THEME`, `CD_CRITERION`, `DESCRIPTION`, `LABEL`, `URL`, `RANK`) VALUES
(1001, 1000, 1001, 'Seo-0101', 'Chaque page Web a-t-elle un meta description?', '01.01', ' ', 1001),
(1002, 1000, 1001, 'Seo-0102', 'Dans chaque ensemble de page, la meta description de chaque page Web est-elle unique?', '01.02', ' ', 1002),
(1003, 1000, 1001, 'Seo-0103', 'Dans chaque page web, la longueur de l\'URL est-elle inférieure ou égale à 255 caractères?', '01.03', ' ', 1003),
(1004, 1000, 1001, 'Seo-0104', 'Dans chaque ensemble de pages, chaque code source HTML est-il unique ?', '01.04', ' ', 1004),
(1005, 1000, 1001, 'Seo-0105', 'Le site web possède-t-il un fichier texte contenant des commandes à destination des robots d\'indexation?', '01.05', ' ', 1005),
(1006, 1000, 1002, 'Seo-0201', 'Chaque image a-t-elle une alternative textuelle ?', '02.01', ' ', 1006),
(1007, 1000, 1003, 'Seo-0301', 'Chaque cadre et chaque cadre en ligne a-t-il un titre de cadre ?', '03.01', ' ', 1007),
(1008, 1000, 1004, 'Seo-0401', 'Chaque tableau de données a-t-il un résumé ?', '04.01', ' ', 1008),
(1009, 1000, 1005, 'Seo-0501', 'Chaque intitulé de lien seul est-il explicite hors contexte (hors cas particuliers) ?', '05.01', ' ', 1009),
(1010, 1000, 1006, 'Seo-0601', 'Chaque page Web a-t-elle un titre de page ?', '06.01', ' ', 1010),
(1011, 1000, 1006, 'Seo-0602', 'Pour chaque page Web ayant un titre de page,  ce titre est-il pertinent ?', '06.02', ' ', 1011),
(1012, 1000, 1006, 'Seo-0603', 'Dans chaque page web, la longueur du titre est-elle inférieure ou égale à 70 caractères?', '06.03', ' ', 1012),
(1013, 1000, 1006, 'Seo-0604', 'Dans chaque ensemble de page, le titre (balise title) est-il unique?', '06.04', ' ', 1013),
(1014, 1000, 1006, 'Seo-0605', 'Chaque page Web est-elle définie par un type de document ?', '06.05', ' ', 1014),
(1015, 1000, 1007, 'Seo-0701', 'Dans chaque page Web, l\'information est-elle structurée par l\'utilisation appropriée de titres?', '07.01', ' ', 1015),
(1016, 1000, 1007, 'Seo-0702', 'Dans chaque page Web, l\'information est-elle optimisée par l\'utilisation appropriée de titres ?', '07.02', ' ', 1016),
(1017, 1000, 1007, 'Seo-0705', 'Dans chaque page web, le titre de niveau 1 (balise h1) est-il différent du titre de page (balise title) ?', '07.05', ' ', 1017),
(1018, 1000, 1007, 'Seo-0706', 'Dans chaque ensemble de page, chaque titre de niveau 1 (balise H1) est-il unique?', '07.06', ' ', 1018),
(1019, 1000, 1009, 'Seo-0901', 'Dans chaque ensemble de pages,  le menu ou les barres de navigation sont-ils toujours à la même place (hors cas particuliers) ?', '09.01', ' ', 1019),
(1020, 1000, 1001, 'Seo-0106', 'Le site web possède-t-il un fichier décrivant le plan du site à destination des robots d\'indexation?', '01.05', ' ', 1020),
(1021, 1000, 1008, 'Seo-0801', 'Le site possède-t-il du contenu flash?', '08.01', ' ', 1021);

--
-- table `TEST`
--
INSERT IGNORE INTO `TEST` (`Id_Test`, `Cd_Test`, `Description`, `Label`, `Rank`, `Weight`, `Rule_Archive_Name`, `Rule_Class_Name`, `Id_Criterion`, `Id_Decision_Level`, `Id_Level`, `Id_Rule`, `Id_Scope`) VALUES
(1001, 'Seo-01011', '', '1.1.1', 10001011, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule01011', 1001, NULL, 1, NULL, 1),
(1002, 'Seo-01012', '', '1.1.2', 10001012, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule01012', 1001, NULL, 1, NULL, 1),
(1003, 'Seo-01013', '', '1.1.3', 10001013, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule01013', 1001, NULL, 1, NULL, 1),
(1004, 'Seo-01021', '', '1.2.1', 10001021, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule01021', 1002, NULL, 1, NULL, 3),
(1005, 'Seo-01031', '', '1.3.1', 10001031, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule01031', 1003, NULL, 1, NULL, 1),
(1006, 'Seo-01041', '', '1.4.1', 10001041, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule01041', 1004, NULL, 1, NULL, 3),
(1007, 'Seo-01051', '', '1.5.1', 10001051, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule01051', 1005, NULL, 1, NULL, 2),
(1008, 'Seo-01061', '', '1.6.1', 10001061, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule01061', 1020, NULL, 1, NULL, 2),
(1009, 'Seo-02011', '', '2.1.1', 10002011, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule02011', 1006, NULL, 1, NULL, 1),
(1010, 'Seo-02012', '', '2.1.2', 10002012, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule02012', 1006, NULL, 1, NULL, 1),
(1011, 'Seo-02013', '', '2.1.3', 10002013, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule02013', 1006, NULL, 1, NULL, 1),
(1012, 'Seo-03011', '', '3.1.1', 10003011, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule03011', 1007, NULL, 1, NULL, 1),
(1013, 'Seo-03012', '', '3.1.2', 10003012, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule03012', 1007, NULL, 1, NULL, 1),
(1014, 'Seo-05011', '', '5.1.1', 10005011, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule05011', 1009, NULL, 3, NULL, 1),
(1015, 'Seo-05012', '', '5.1.2', 10005012, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule05012', 1009, NULL, 3, NULL, 1),
(1016, 'Seo-05013', '', '5.1.3', 10005013, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule05013', 1009, NULL, 3, NULL, 1),
(1017, 'Seo-06011', '', '6.1.1', 10006011, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule06011', 1010, NULL, 1, NULL, 1),
(1018, 'Seo-06021', '', '6.2.1', 10006021, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule06021', 1011, NULL, 1, NULL, 1),
(1019, 'Seo-06031', '', '6.3.1', 10006031, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule06031', 1012, NULL, 1, NULL, 1),
(1020, 'Seo-06041', '', '6.4.1', 10006041, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule06041', 1013, NULL, 1, NULL, 3),
(1021, 'Seo-06051', '', '6.5.1', 10006051, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule06051', 1014, NULL, 2, NULL, 1),
(1022, 'Seo-06052', '', '6.5.2', 10006052, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule06052', 1014, NULL, 2, NULL, 1),
(1023, 'Seo-07011', '', '7.1.1', 10007011, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule07011', 1015, NULL, 1, NULL, 1),
(1024, 'Seo-07012', '', '7.1.2', 10007012, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule07012', 1015, NULL, 1, NULL, 1),
(1025, 'Seo-07021', '', '7.2.1', 10007021, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule07021', 1016, NULL, 2, NULL, 1),
(1026, 'Seo-07051', '', '7.5.1', 10007051, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule07051', 1017, NULL, 2, NULL, 1),
(1027, 'Seo-07061', '', '7.6.1', 10007061, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule07061', 1018, NULL, 1, NULL, 3),
(1028, 'Seo-06031', '', '6.3.1', 10006031, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule06031', 1012, NULL, 1, NULL, 1),
(1029, 'Seo-08011', '', '8.1.1', 10008011, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule08011', 1021, NULL, 1, NULL, 1);

SET foreign_key_checks=1;