use $myDatabaseName;

INSERT IGNORE INTO `PARAMETER_ELEMENT` (`Cd_Parameter_Element`, `Long_Label`, `Short_Label`) VALUES
('PROXY_USER', 'proxy user', 'proxy user'),
('PROXY_PASSWORD', 'proxy password', 'proxy password');

UPDATE `PARAMETER_ELEMENT` SET `Id_Parameter_Family` = (SELECT `Id_Parameter_Family` FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` LIKE 'CRAWL') WHERE `Cd_Parameter_Element` LIKE 'PROXY_USER';
UPDATE `PARAMETER_ELEMENT` SET `Id_Parameter_Family` = (SELECT `Id_Parameter_Family` FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` LIKE 'CRAWL') WHERE `Cd_Parameter_Element` LIKE 'PROXY_PASSWORD';

INSERT IGNORE INTO `PARAMETER` (`Id_Parameter_Element`, `Parameter_Value`, `Is_Default`) VALUES
((SELECT `Id_Parameter_Element` FROM `PARAMETER_ELEMENT` WHERE `Cd_Parameter_Element` LIKE 'PROXY_USER'), '', b'1'),
((SELECT `Id_Parameter_Element` FROM `PARAMETER_ELEMENT` WHERE `Cd_Parameter_Element` LIKE 'PROXY_PASSWORD'), '', b'1'); 

SET foreign_key_checks=1;