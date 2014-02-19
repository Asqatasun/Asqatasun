SET foreign_key_checks=0;

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
(1020, 1000, 1001, 'Seo-0106', 'Le site web possède-t-il un fichier décrivant le plan du site à destination des robots d\'indexation?', '01.05', ' ', 1020),
(1021, 1000, 1008, 'Seo-0801', 'Le site possède-t-il du contenu flash?', '08.01', ' ', 1021);

--
-- table `TEST`
--
INSERT IGNORE INTO `TEST` (`Id_Test`, `Cd_Test`, `Description`, `Label`, `Rank`, `Weight`, `Rule_Archive_Name`, `Rule_Class_Name`, `Id_Criterion`, `Id_Decision_Level`, `Id_Level`, `Id_Scope`,`No_Process`) VALUES
(1001, 'Seo-01011', '', '1.1.1', 10001011, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule01011', 1001, NULL, 1, 1, b'0'),
(1002, 'Seo-01012', '', '1.1.2', 10001012, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule01012', 1001, NULL, 1, 1, b'0'),
(1003, 'Seo-01013', '', '1.1.3', 10001013, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule01013', 1001, NULL, 1, 1, b'0'),
(1004, 'Seo-01021', '', '1.2.1', 10001021, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule01021', 1002, NULL, 1, 3, b'0'),
(1005, 'Seo-01031', '', '1.3.1', 10001031, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule01031', 1003, NULL, 1, 1, b'0'),
(1006, 'Seo-01041', '', '1.4.1', 10001041, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule01041', 1004, NULL, 1, 3, b'0'),
(1007, 'Seo-01051', '', '1.5.1', 10001051, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule01051', 1005, NULL, 1, 2, b'0'),
(1008, 'Seo-01061', '', '1.6.1', 10001061, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule01061', 1020, NULL, 1, 2, b'0'),
(1009, 'Seo-02011', '', '2.1.1', 10002011, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule02011', 1006, NULL, 1, 1, b'0'),
(1010, 'Seo-02012', '', '2.1.2', 10002012, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule02012', 1006, NULL, 1, 1, b'0'),
(1011, 'Seo-02013', '', '2.1.3', 10002013, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule02013', 1006, NULL, 1, 1, b'0'),
(1012, 'Seo-03011', '', '3.1.1', 10003011, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule03011', 1007, NULL, 1, 1, b'0'),
(1013, 'Seo-03012', '', '3.1.2', 10003012, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule03012', 1007, NULL, 1, 1, b'0'),
(1014, 'Seo-05011', '', '5.1.1', 10005011, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule05011', 1009, NULL, 2, 1, b'0'),
(1015, 'Seo-05012', '', '5.1.2', 10005012, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule05012', 1009, NULL, 2, 1, b'0'),
(1016, 'Seo-05013', '', '5.1.3', 10005013, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule05013', 1009, NULL, 2, 1, b'0'),
(1017, 'Seo-06011', '', '6.1.1', 10006011, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule06011', 1010, NULL, 1, 1, b'0'),
(1018, 'Seo-06021', '', '6.2.1', 10006021, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule06021', 1011, NULL, 1, 1, b'0'),
(1019, 'Seo-06031', '', '6.3.1', 10006031, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule06031', 1012, NULL, 1, 1, b'0'),
(1020, 'Seo-06041', '', '6.4.1', 10006041, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule06041', 1013, NULL, 1, 3, b'0'),
(1021, 'Seo-06051', '', '6.5.1', 10006051, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule06051', 1014, NULL, 2, 1, b'0'),
(1022, 'Seo-06052', '', '6.5.2', 10006052, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule06052', 1014, NULL, 2, 1, b'0'),
(1023, 'Seo-07011', '', '7.1.1', 10007011, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule07011', 1015, NULL, 1, 1, b'0'),
(1024, 'Seo-07012', '', '7.1.2', 10007012, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule07012', 1015, NULL, 1, 1, b'0'),
(1025, 'Seo-07021', '', '7.2.1', 10007021, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule07021', 1016, NULL, 2, 1, b'0'),
(1026, 'Seo-07051', '', '7.5.1', 10007051, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule07051', 1017, NULL, 2, 1, b'0'),
(1027, 'Seo-07061', '', '7.6.1', 10007061, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule07061', 1018, NULL, 1, 3, b'0'),
(1029, 'Seo-08011', '', '8.1.1', 10008011, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule08011', 1021, NULL, 1, 1, b'0'),
(1030, 'Seo-07013', '', '7.1.3', 10007013, '1.0', 'seo', 'org.opens.tanaguru.rules.seo.SeoRule07013', 1015, NULL, 1, 1, b'0');

SET foreign_key_checks=1;