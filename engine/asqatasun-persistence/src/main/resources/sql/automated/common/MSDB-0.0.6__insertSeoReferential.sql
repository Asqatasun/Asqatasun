INSERT IGNORE INTO `REFERENCE` (`Cd_Reference`, `Description`, `Label`, `Url`, `Rank`, `Id_Default_Level`) VALUES
('Seo', NULL, 'Seo 1.0', '', 1000, 1);

-- INSERT IGNORE INTO `TGSI_REFERENTIAL` (`Code`, `Label`) VALUES
-- ('Seo', 'Seo referential');

INSERT IGNORE INTO `THEME` (`Cd_Theme`, `Description`, `Label`, `Rank`) VALUES
('Seo-01', NULL, 'Bases du Referencement', 01),
('Seo-02', NULL, 'Images', 02),
('Seo-03', NULL, 'Cadres', 03),
('Seo-05', NULL, 'Liens', 04),
('Seo-06', NULL, 'Eléments Obligatoires', 05),
('Seo-07', NULL, 'Structuration de linformation', 06),
('Seo-08', NULL, 'Multimedia', 07);

INSERT IGNORE INTO `CRITERION` (`Cd_Criterion`, `Description`, `Label`, `Url`, `Rank`) VALUES
('Seo-0101', '', '01.01', ' ', 1001),
('Seo-0102', '', '01.02', ' ', 1002),
('Seo-0103', '', '01.03', ' ', 1003),
('Seo-0104', '', '01.04', ' ', 1004),
('Seo-0105', '', '01.05', ' ', 1005),
('Seo-0201', '', '02.01', ' ', 1006),
('Seo-0301', '', '03.01', ' ', 1007),
('Seo-0501', '', '05.01', ' ', 1009),
('Seo-0601', '', '06.01', ' ', 1010),
('Seo-0602', '', '06.02', ' ', 1011),
('Seo-0603', '', '06.03', ' ', 1012),
('Seo-0604', '', '06.04', ' ', 1013),
('Seo-0605', '', '06.05', ' ', 1014),
('Seo-0701', '', '07.01', ' ', 1015),
('Seo-0702', '', '07.02', ' ', 1016),
('Seo-0705', '', '07.05', ' ', 1017),
('Seo-0706', '', '07.06', ' ', 1018),
('Seo-0106', '', '01.05', ' ', 1020),
('Seo-0801', '', '08.01', ' ', 1021),
('Seo-0107', '', '01.07', ' ', 1022),
('Seo-0108', '', '01.08', ' ', 1023);

UPDATE `CRITERION` SET `Reference_Id_Reference` = (SELECT `Id_Reference` FROM `REFERENCE` WHERE `Cd_Reference` LIKE 'Seo') WHERE `Cd_Criterion` LIKE 'Seo-%';

UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Seo-01') WHERE `Cd_Criterion` LIKE 'Seo-01%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Seo-02') WHERE `Cd_Criterion` LIKE 'Seo-02%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Seo-03') WHERE `Cd_Criterion` LIKE 'Seo-03%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Seo-04') WHERE `Cd_Criterion` LIKE 'Seo-04%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Seo-05') WHERE `Cd_Criterion` LIKE 'Seo-05%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Seo-06') WHERE `Cd_Criterion` LIKE 'Seo-06%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Seo-07') WHERE `Cd_Criterion` LIKE 'Seo-07%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Seo-08') WHERE `Cd_Criterion` LIKE 'Seo-08%';

--
-- table `TEST`
--
INSERT IGNORE INTO `TEST` (`Cd_Test`, `Description`, `Label`, `Rank`, `Weight`, `Rule_Archive_Name`, `Rule_Class_Name`, `Id_Decision_Level`, `Id_Level`, `Id_Scope`,`No_Process`) VALUES
('Seo-01011', '', '1.1.1', 10001011, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule01011', NULL, 1, 1, b'0'),
('Seo-01012', '', '1.1.2', 10001012, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule01012', NULL, 1, 1, b'0'),
('Seo-01013', '', '1.1.3', 10001013, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule01013', NULL, 1, 1, b'0'),
('Seo-01021', '', '1.2.1', 10001021, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule01021', NULL, 1, 3, b'0'),
('Seo-01031', '', '1.3.1', 10001031, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule01031', NULL, 2, 1, b'0'),
('Seo-01041', '', '1.4.1', 10001041, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule01041', NULL, 1, 3, b'0'),
('Seo-01051', '', '1.5.1', 10001051, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule01051', NULL, 3, 2, b'0'),
('Seo-01061', '', '1.6.1', 10001061, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule01061', NULL, 3, 2, b'0'),
('Seo-02011', '', '2.1.1', 10002011, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule02011', NULL, 2, 1, b'0'),
('Seo-02012', '', '2.1.2', 10002012, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule02012', NULL, 2, 1, b'0'),
('Seo-02013', '', '2.1.3', 10002013, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule02013', NULL, 2, 1, b'0'),
('Seo-03011', '', '3.1.1', 10003011, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule03011', NULL, 3, 1, b'0'),
('Seo-03012', '', '3.1.2', 10003012, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule03012', NULL, 3, 1, b'0'),
('Seo-05011', '', '4.1.1', 10005011, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule05011', NULL, 1, 1, b'0'),
('Seo-05012', '', '4.1.2', 10005012, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule05012', NULL, 1, 1, b'0'),
('Seo-05013', '', '4.1.3', 10005013, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule05013', NULL, 1, 1, b'0'),
('Seo-06011', '', '5.1.1', 10006011, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule06011', NULL, 1, 1, b'0'),
('Seo-06021', '', '5.2.1', 10006021, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule06021', NULL, 1, 1, b'0'),
('Seo-06031', '', '5.3.1', 10006031, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule06031', NULL, 2, 1, b'0'),
('Seo-06041', '', '5.4.1', 10006041, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule06041', NULL, 1, 3, b'0'),
('Seo-06051', '', '5.5.1', 10006051, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule06051', NULL, 3, 1, b'0'),
('Seo-06052', '', '5.5.2', 10006052, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule06052', NULL, 3, 1, b'0'),
('Seo-07011', '', '6.1.1', 10007011, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule07011', NULL, 1, 1, b'0'),
('Seo-07012', '', '6.1.2', 10007012, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule07012', NULL, 1, 1, b'0'),
('Seo-07021', '', '6.2.1', 10007021, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule07021', NULL, 2, 1, b'0'),
('Seo-07051', '', '6.5.1', 10007051, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule07051', NULL, 2, 1, b'0'),
('Seo-07061', '', '6.6.1', 10007061, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule07061', NULL, 1, 3, b'0'),
('Seo-08011', '', '7.1.1', 10008011, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule08011', NULL, 3, 1, b'0'),
('Seo-07013', '', '6.1.3', 10007013, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule07013', NULL, 1, 1, b'0'),
('Seo-01071', '', '1.7.1', 10001071, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule01071', NULL, 2, 1, b'0'),
('Seo-01081', '', '1.8.1', 10001081, '1.0', 'seo', 'org.asqatasun.rules.seo.SeoRule01081', NULL, 3, 1, b'0');

UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Seo-0101') WHERE `Cd_Test` LIKE 'Seo-0101%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Seo-0102') WHERE `Cd_Test` LIKE 'Seo-0102%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Seo-0103') WHERE `Cd_Test` LIKE 'Seo-0103%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Seo-0104') WHERE `Cd_Test` LIKE 'Seo-0104%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Seo-0105') WHERE `Cd_Test` LIKE 'Seo-0105%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Seo-0106') WHERE `Cd_Test` LIKE 'Seo-0106%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Seo-0107') WHERE `Cd_Test` LIKE 'Seo-0107%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Seo-0108') WHERE `Cd_Test` LIKE 'Seo-0108%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Seo-0201') WHERE `Cd_Test` LIKE 'Seo-0201%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Seo-0301') WHERE `Cd_Test` LIKE 'Seo-0301%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Seo-0501') WHERE `Cd_Test` LIKE 'Seo-0501%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Seo-0601') WHERE `Cd_Test` LIKE 'Seo-0601%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Seo-0602') WHERE `Cd_Test` LIKE 'Seo-0602%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Seo-0603') WHERE `Cd_Test` LIKE 'Seo-0603%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Seo-0604') WHERE `Cd_Test` LIKE 'Seo-0604%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Seo-0605') WHERE `Cd_Test` LIKE 'Seo-0605%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Seo-0701') WHERE `Cd_Test` LIKE 'Seo-0701%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Seo-0702') WHERE `Cd_Test` LIKE 'Seo-0702%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Seo-0705') WHERE `Cd_Test` LIKE 'Seo-0705%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Seo-0706') WHERE `Cd_Test` LIKE 'Seo-0706%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Seo-0801') WHERE `Cd_Test` LIKE 'Seo-0801%';

--
-- Parameters (parameter_family, parameter_element...)
--
INSERT IGNORE INTO `PARAMETER_FAMILY` (`Cd_Parameter_Family`, `Description`, `Long_Label`, `Short_Label`) VALUES
('Seo_TEST_WEIGHT_MANAGEMENT', 'This paramaters handles the test weight potentially overridden by users', 'test weight parameters', 'test weight params');

INSERT IGNORE INTO `PARAMETER_ELEMENT` (`Cd_Parameter_Element`, `Id_Parameter_Family`, `Long_Label`, `Short_Label`) VALUES
('Seo-01011', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-01011 overriden by user', 'Seo-01011 weight'),
('Seo-01012', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-01011 overriden by user', 'Seo-01011 weight'),
('Seo-01013', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-01011 overriden by user', 'Seo-01011 weight'),
('Seo-01021', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-01021 overriden by user', 'Seo-01021 weight'),
('Seo-01031', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-01031 overriden by user', 'Seo-01031 weight'),
('Seo-01041', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-01041 overriden by user', 'Seo-01041 weight'),
('Seo-01051', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-01051 overriden by user', 'Seo-01051 weight'),
('Seo-01061', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-01061 overriden by user', 'Seo-01061 weight'),
('Seo-01071', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-01071 overriden by user', 'Seo-01071 weight'),
('Seo-01081', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-01081 overriden by user', 'Seo-01081 weight'),
('Seo-02011', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-02011 overriden by user', 'Seo-02011 weight'),
('Seo-02012', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-02012 overriden by user', 'Seo-02012 weight'),
('Seo-02013', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-02013 overriden by user', 'Seo-02013 weight'),
('Seo-03011', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-03011 overriden by user', 'Seo-01011 weight'),
('Seo-03012', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-03012 overriden by user', 'Seo-03012 weight'),
('Seo-05011', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-05011 overriden by user', 'Seo-05011 weight'),
('Seo-05012', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-05012 overriden by user', 'Seo-05012 weight'),
('Seo-05013', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-05013 overriden by user', 'Seo-05013 weight'),
('Seo-06011', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-06011 overriden by user', 'Seo-06011 weight'),
('Seo-06021', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-06021 overriden by user', 'Seo-06021 weight'),
('Seo-06031', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-06031 overriden by user', 'Seo-06031 weight'),
('Seo-06041', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-06041 overriden by user', 'Seo-06041 weight'),
('Seo-06051', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-06051 overriden by user', 'Seo-06051 weight'),
('Seo-06052', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-06052 overriden by user', 'Seo-06052 weight'),
('Seo-07011', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-07011 overriden by user', 'Seo-07011 weight'),
('Seo-07012', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-07012 overriden by user', 'Seo-07012 weight'),
('Seo-07013', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-07012 overriden by user', 'Seo-07013 weight'),
('Seo-07021', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-07021 overriden by user', 'Seo-07021 weight'),
('Seo-07051', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-07051 overriden by user', 'Seo-07051 weight'),
('Seo-07061', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-07061 overriden by user', 'Seo-07061 weight'),
('Seo-08011', (SELECT Id_Parameter_Family FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` like 'Seo_TEST_WEIGHT_MANAGEMENT'), 'Weight of rule Seo-08011 overriden by user', 'Seo-08011 weight');

INSERT IGNORE INTO `PARAMETER` (`Id_Parameter_Element`, `Parameter_Value`, `Is_Default`) VALUES
(5, 'Seo;LEVEL_3', b'0'),
(5, 'Seo;LEVEL_2', b'0'),
(5, 'Seo;LEVEL_1', b'0');

--
-- OPTIONS stuff
--

-- INSERT IGNORE INTO `TGSI_OPTION_FAMILY` (`Code`, `Label`) VALUES
-- ('Seo_TEST_WEIGHT_MANAGEMENT', '');
--
-- INSERT IGNORE INTO `TGSI_OPTION` (`Code`, `Description`, `Is_Restriction`, OPTION_FAMILY_Id_Option_Family) VALUES
-- ('Seo-01011', 'Weight of rule Seo-01011 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT')),
-- ('Seo-01012', 'Weight of rule Seo-01012 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT')),
-- ('Seo-01013', 'Weight of rule Seo-01013 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT')),
-- ('Seo-01021', 'Weight of rule Seo-01021 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT')),
-- ('Seo-01031', 'Weight of rule Seo-01031 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT')),
-- ('Seo-01041', 'Weight of rule Seo-01041 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT')),
-- ('Seo-01051', 'Weight of rule Seo-01051 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT')),
-- ('Seo-01061', 'Weight of rule Seo-01061 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT')),
-- ('Seo-01071', 'Weight of rule Seo-01071 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT')),
-- ('Seo-01081', 'Weight of rule Seo-01081 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT')),
-- ('Seo-02011', 'Weight of rule Seo-02011 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT')),
-- ('Seo-02012', 'Weight of rule Seo-02012 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT')),
-- ('Seo-02013', 'Weight of rule Seo-02013 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT')),
-- ('Seo-03011', 'Weight of rule Seo-03011 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT')),
-- ('Seo-03012', 'Weight of rule Seo-03012 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT')),
-- ('Seo-05011', 'Weight of rule Seo-05011 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT')),
-- ('Seo-05012', 'Weight of rule Seo-05012 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT')),
-- ('Seo-05013', 'Weight of rule Seo-05013 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT')),
-- ('Seo-06011', 'Weight of rule Seo-06011 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT')),
-- ('Seo-06021', 'Weight of rule Seo-06021 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT')),
-- ('Seo-06031', 'Weight of rule Seo-06031 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT')),
-- ('Seo-06041', 'Weight of rule Seo-06041 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT')),
-- ('Seo-06051', 'Weight of rule Seo-06051 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT')),
-- ('Seo-06052', 'Weight of rule Seo-06052 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT')),
-- ('Seo-07011', 'Weight of rule Seo-07011 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT')),
-- ('Seo-07012', 'Weight of rule Seo-07012 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT')),
-- ('Seo-07013', 'Weight of rule Seo-07013 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT')),
-- ('Seo-07021', 'Weight of rule Seo-07021 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT')),
-- ('Seo-07051', 'Weight of rule Seo-07051 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT')),
-- ('Seo-07061', 'Weight of rule Seo-07061 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT')),
-- ('Seo-08011', 'Weight of rule Seo-08011 overriden by user', b'0', (SELECT Id_Option_Family FROM `TGSI_OPTION_FAMILY` WHERE `Code` like 'Seo_TEST_WEIGHT_MANAGEMENT'));
