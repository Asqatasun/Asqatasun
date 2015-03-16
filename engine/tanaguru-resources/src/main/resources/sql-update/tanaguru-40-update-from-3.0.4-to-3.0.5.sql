use $myDatabaseName;

INSERT IGNORE INTO `PARAMETER_ELEMENT` (`Id_Parameter_Element`, `Cd_Parameter_Element`, `Id_Parameter_Family`, `Long_Label`, `Short_Label`) VALUES
(45, 'PROXY_USER', 1, 'proxy user', 'proxy user'),
(46, 'PROXY_PASSWORD', 1, 'proxy password', 'proxy password');

INSERT IGNORE INTO `PARAMETER` (`Id_Parameter_Element`, `Parameter_Value`, `Is_Default`) VALUES
(45, '', b'1'),
(46, '', b'1'); 

SET foreign_key_checks=1;