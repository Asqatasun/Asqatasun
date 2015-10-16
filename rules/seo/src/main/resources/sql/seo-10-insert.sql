SET foreign_key_checks=0;

--
-- table `THEME`
--
INSERT IGNORE INTO `THEME` (`Cd_Theme`, `Description`, `Label`, `Rank`) VALUES
('Seo-01', NULL, 'Bases du Referencement', 01),
('Seo-02', NULL, 'Images', 02),
('Seo-03', NULL, 'Cadres', 03),
('Seo-05', NULL, 'Liens', 04),
('Seo-06', NULL, 'Eléments Obligatoires', 05),
('Seo-07', NULL, 'Structuration de l\'information', 06),
('Seo-08', NULL, 'Multimedia', 07);

--
-- table `REFERENCE`
--
INSERT IGNORE INTO `REFERENCE` (`Cd_Reference`, `Description`, `Label`, `Url`, `Rank`, `Id_Default_Level`) VALUES
('Seo', NULL, 'Seo Open-s', '', 1000, 1);

--
-- table `CRITERION`
--
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

UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Seo-01') WHERE `Cd_Criterion` LIKE 'Seo-01-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Seo-02') WHERE `Cd_Criterion` LIKE 'Seo-02-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Seo-03') WHERE `Cd_Criterion` LIKE 'Seo-03-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Seo-04') WHERE `Cd_Criterion` LIKE 'Seo-04-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Seo-05') WHERE `Cd_Criterion` LIKE 'Seo-05-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Seo-06') WHERE `Cd_Criterion` LIKE 'Seo-06-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Seo-07') WHERE `Cd_Criterion` LIKE 'Seo-07-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Seo-08') WHERE `Cd_Criterion` LIKE 'Seo-08-%';


--
-- table `TEST`
--
INSERT IGNORE INTO `TEST` (`Cd_Test`, `Description`, `Label`, `Rank`, `Weight`, `Rule_Archive_Name`, `Rule_Class_Name`, `Id_Decision_Level`, `Id_Level`, `Id_Scope`,`No_Process`) VALUES
('Seo-01011', '', '1.1.1', 10001011, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule01011', NULL, 1, 1, b'0'),
('Seo-01012', '', '1.1.2', 10001012, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule01012', NULL, 1, 1, b'0'),
('Seo-01013', '', '1.1.3', 10001013, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule01013', NULL, 1, 1, b'0'),
('Seo-01021', '', '1.2.1', 10001021, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule01021', NULL, 1, 3, b'0'),
('Seo-01031', '', '1.3.1', 10001031, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule01031', NULL, 2, 1, b'0'),
('Seo-01041', '', '1.4.1', 10001041, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule01041', NULL, 1, 3, b'0'),
('Seo-01051', '', '1.5.1', 10001051, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule01051', NULL, 3, 2, b'0'),
('Seo-01061', '', '1.6.1', 10001061, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule01061', NULL, 3, 2, b'0'),
('Seo-02011', '', '2.1.1', 10002011, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule02011', NULL, 2, 1, b'0'),
('Seo-02012', '', '2.1.2', 10002012, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule02012', NULL, 2, 1, b'0'),
('Seo-02013', '', '2.1.3', 10002013, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule02013', NULL, 2, 1, b'0'),
('Seo-03011', '', '3.1.1', 10003011, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule03011', NULL, 3, 1, b'0'),
('Seo-03012', '', '3.1.2', 10003012, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule03012', NULL, 3, 1, b'0'),
('Seo-05011', '', '4.1.1', 10005011, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule05011', NULL, 1, 1, b'0'),
('Seo-05012', '', '4.1.2', 10005012, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule05012', NULL, 1, 1, b'0'),
('Seo-05013', '', '4.1.3', 10005013, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule05013', NULL, 1, 1, b'0'),
('Seo-06011', '', '5.1.1', 10006011, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule06011', NULL, 1, 1, b'0'),
('Seo-06021', '', '5.2.1', 10006021, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule06021', NULL, 1, 1, b'0'),
('Seo-06031', '', '5.3.1', 10006031, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule06031', NULL, 2, 1, b'0'),
('Seo-06041', '', '5.4.1', 10006041, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule06041', NULL, 1, 3, b'0'),
('Seo-06051', '', '5.5.1', 10006051, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule06051', NULL, 3, 1, b'0'),
('Seo-06052', '', '5.5.2', 10006052, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule06052', NULL, 3, 1, b'0'),
('Seo-07011', '', '6.1.1', 10007011, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule07011', NULL, 1, 1, b'0'),
('Seo-07012', '', '6.1.2', 10007012, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule07012', NULL, 1, 1, b'0'),
('Seo-07021', '', '6.2.1', 10007021, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule07021', NULL, 2, 1, b'0'),
('Seo-07051', '', '6.5.1', 10007051, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule07051', NULL, 2, 1, b'0'),
('Seo-07061', '', '6.6.1', 10007061, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule07061', NULL, 1, 3, b'0'),
('Seo-08011', '', '7.1.1', 10008011, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule08011', NULL, 3, 1, b'0'),
('Seo-07013', '', '6.1.3', 10007013, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule07013', NULL, 1, 1, b'0'),
('Seo-01071', '', '1.7.1', 10001071, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule01071', NULL, 2, 1, b'0'),
('Seo-01081', '', '1.8.1', 10001081, '1.0', 'seo', 'org.tanaguru.rules.seo.SeoRule01081', NULL, 3, 1, b'0');

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

SET foreign_key_checks=1;
