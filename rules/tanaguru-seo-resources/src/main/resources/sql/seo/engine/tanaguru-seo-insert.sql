SET foreign_key_checks=0;

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

SET foreign_key_checks=1;