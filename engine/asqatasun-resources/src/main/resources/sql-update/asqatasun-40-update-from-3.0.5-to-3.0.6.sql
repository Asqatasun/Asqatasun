SET foreign_key_checks=0;

DELETE FROM PARAMETER WHERE Id_Parameter_Element in (select Id_Parameter_Element FROM PARAMETER_ELEMENT WHERE Cd_Parameter_Element like "%PROXY%");
DELETE FROM PARAMETER_ELEMENT WHERE Cd_Parameter_Element like "%PROXY%";

INSERT IGNORE INTO `PARAMETER_ELEMENT` (`Cd_Parameter_Element`,`Id_Parameter_Family`, `Long_Label`, `Short_Label`) VALUES
('COMPLEX_TABLE_MARKER', (SELECT `Id_Parameter_Family` FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` LIKE 'RULES'), 'Complex table marker', 'Correspond to the attribute \"id\", \"class\" or \"role\" of the complex tables');


INSERT IGNORE INTO `PARAMETER` (`Id_Parameter_Element`, `Parameter_Value`, `Is_Default`) VALUES
((SELECT `Id_Parameter_Element` FROM `PARAMETER_ELEMENT` WHERE `Cd_Parameter_Element` LIKE 'COMPLEX_TABLE_MARKER'), '', b'1');

UPDATE TEST SET Rule_Class_Name = REPLACE(Rule_Class_Name, 'org.opens.tanaguru.', 'org.asqatasun.');

SET foreign_key_checks=1;