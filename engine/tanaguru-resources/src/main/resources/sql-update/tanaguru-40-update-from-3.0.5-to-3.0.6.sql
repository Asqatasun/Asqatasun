SET foreign_key_checks=0;

DELETE FROM PARAMETER WHERE Id_Parameter_Element in (select Id_Parameter_Element FROM PARAMETER_ELEMENT WHERE Cd_Parameter_Element like "%PROXY%");
DELETE FROM PARAMETER_ELEMENT WHERE Cd_Parameter_Element like "%PROXY%";

INSERT IGNORE INTO `PARAMETER_ELEMENT` (`Cd_Parameter_Element`, `Long_Label`, `Short_Label`) VALUES
('COMPLEX_TABLE_MARKER', 'Complex table marker', 'Correspond to the attribute \"id\", \"class\" or \"role\" of the complex tables');

UPDATE `PARAMETER_ELEMENT` SET `Id_Parameter_Family` = (SELECT `Id_Parameter_Family` FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` LIKE 'RULES') WHERE `Cd_Parameter_Element` LIKE 'COMPLEX_TABLE_MARKER';

INSERT IGNORE INTO `PARAMETER` (`Id_Parameter_Element`, `Parameter_Value`, `Is_Default`) VALUES
((SELECT `Id_Parameter_Element` FROM `PARAMETER_ELEMENT` WHERE `Cd_Parameter_Element` LIKE 'COMPLEX_TABLE_MARKER'), '', b'1');

SET foreign_key_checks=1;