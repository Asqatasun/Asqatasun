SET foreign_key_checks=0;

INSERT IGNORE INTO `PARAMETER_ELEMENT` (`Cd_Parameter_Element`, `Id_Parameter_Family`, `Long_Label`, `Short_Label`) VALUES
('ROBOTS_TXT_ACTIVATION', (SELECT `Id_Parameter_Family` FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` LIKE 'CRAWL'), 'Consider robots.txt directives when crawling', 'robots txt');

INSERT IGNORE INTO `PARAMETER` (`Id_Parameter_Element`, `Parameter_Value`, `Is_Default`) VALUES
((SELECT `Id_Parameter_Element` FROM `PARAMETER_ELEMENT` WHERE `Cd_Parameter_Element` LIKE 'ROBOTS_TXT_ACTIVATION'), 'true', b'1'),
((SELECT `Id_Parameter_Element` FROM `PARAMETER_ELEMENT` WHERE `Cd_Parameter_Element` LIKE 'ROBOTS_TXT_ACTIVATION'), 'false', b'0');

SET foreign_key_checks=1;
