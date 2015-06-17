INSERT IGNORE INTO `REFERENCE` (`Cd_Reference`, `Description`, `Label`, `Url`, `Rank`, `Id_Default_Level`) VALUES
('Rulescreationdemo', NULL, 'Rules creation demo', '', 2000, 1);

INSERT IGNORE INTO `TGSI_REFERENTIAL` (`Code`, `Label`) VALUES
('Rulescreationdemo', 'Rules creation demo');

INSERT IGNORE INTO `THEME` (`Cd_Theme`, `Description`, `Label`, `Rank`) VALUES
('Rulescreationdemo-1', NULL, 'Detection', 1),
('Rulescreationdemo-2', NULL, 'Select and Check', 2),
('Rulescreationdemo-3', NULL, 'Site scope rule', 3),
('Rulescreationdemo-4', NULL, 'Nomenclature based Check', 4);

INSERT IGNORE INTO `CRITERION` (`Cd_Criterion`, `Description`, `Label`, `Url`, `Rank`) VALUES
('Rulescreationdemo-1-1', 'Does each page have a h1 tag ?', '.1.1', '', 1),
('Rulescreationdemo-1-2', 'A page cannot contain iframe. Is that rule respected', '.1.2', '', 2),
('Rulescreationdemo-2-1', 'Does each link element have not  a title attribute', '.2.1', '', 3),
('Rulescreationdemo-3-1', 'Is each title tag unique on the whole site?', '.3.1', '', 4),
('Rulescreationdemo-4-1', 'Is each h1 tag unique on the whole site?', '.4.1', '', 5);

UPDATE `CRITERION` SET `Reference_Id_Reference` = (SELECT `Id_Reference` FROM `REFERENCE` WHERE `Cd_Reference` LIKE 'Rulescreationdemo') WHERE `Cd_Criterion` LIKE 'Rulescreationdemo-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rulescreationdemo-1') WHERE `Cd_Criterion` LIKE 'Rulescreationdemo-1-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rulescreationdemo-2') WHERE `Cd_Criterion` LIKE 'Rulescreationdemo-2-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rulescreationdemo-3') WHERE `Cd_Criterion` LIKE 'Rulescreationdemo-3-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rulescreationdemo-4') WHERE `Cd_Criterion` LIKE 'Rulescreationdemo-4-%';

INSERT IGNORE INTO `TEST` (`Cd_Test`, `Description`, `Label`, `Rank`, `Weight`, `Rule_Archive_Name`, `Rule_Class_Name`, `Id_Decision_Level`, `Id_Level`, `Id_Scope`, `Rule_Design_Url`, `No_Process`) VALUES
('Rulescreationdemo-1-1-1', '', '1.1', 1, '1.0', 'rulescreationdemo', 'org.tanaguru.rules.rulescreationdemo.DetectH1', NULL, 1, 1, '', b'0'),
('Rulescreationdemo-1-2-1', '', '1.2', 2, '1.0', 'rulescreationdemo', 'org.tanaguru.rules.rulescreationdemo.DetectIframe', NULL, 1, 1, '', b'0'),
('Rulescreationdemo-2-1-1', '', '2.1', 3, '1.0', 'rulescreationdemo', 'org.tanaguru.rules.rulescreationdemo.CheckWhetherEachLinkHaventTitleAttribute', NULL, 1, 1, '', b'0'),
('Rulescreationdemo-3-1-1', '', '3.1', 4, '1.0', 'rulescreationdemo', 'org.tanaguru.rules.rulescreationdemo.CheckTitleContentUnicityAtSiteLevel', NULL, 1, 3, '', b'0'),
('Rulescreationdemo-4-1-1', '', '4.1', 5, '1.0', 'rulescreationdemo', 'org.tanaguru.rules.rulescreationdemo.CheckTitleTagRelevancy', NULL, 1, 1, '', b'0');

UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rulescreationdemo-1-1') WHERE `Cd_Test` LIKE 'Rulescreationdemo-1-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rulescreationdemo-1-2') WHERE `Cd_Test` LIKE 'Rulescreationdemo-1-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rulescreationdemo-2-1') WHERE `Cd_Test` LIKE 'Rulescreationdemo-2-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rulescreationdemo-3-1') WHERE `Cd_Test` LIKE 'Rulescreationdemo-3-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rulescreationdemo-4-1') WHERE `Cd_Test` LIKE 'Rulescreationdemo-4-1-%';

INSERT IGNORE INTO `PARAMETER` (`Id_Parameter_Element`, `Parameter_Value`, `Is_Default`) VALUES
(5, 'Rulescreationdemo;LEVEL_1', b'0'),
(5, 'Rulescreationdemo;LEVEL_2', b'0'),
(5, 'Rulescreationdemo;LEVEL_3', b'0');

