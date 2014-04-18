SET foreign_key_checks=0;

INSERT IGNORE INTO `PARAMETER_ELEMENT` (`Id_Parameter_Element`, `Cd_Parameter_Element`, `Id_Parameter_Family`, `Long_Label`, `Short_Label`) VALUES
(42, 'INCLUSION_REGEXP', 1, 'Regulard expression to crawl on a specific folder', 'inclusion regex');

INSERT IGNORE INTO `PARAMETER` (`Id_Parameter_Element`, `Parameter_Value`, `Is_Default`) VALUES
(42, '', b'1');

SET foreign_key_checks=1;