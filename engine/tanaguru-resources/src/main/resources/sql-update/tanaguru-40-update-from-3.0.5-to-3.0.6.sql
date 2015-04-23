SET foreign_key_checks=0;

DELETE FROM PARAMETER WHERE Id_Parameter_Element in (select Id_Parameter_Element FROM PARAMETER_ELEMENT WHERE Cd_Parameter_Element like "%PROXY%");
DELETE FROM PARAMETER_ELEMENT WHERE Cd_Parameter_Element like "%PROXY%";

SET foreign_key_checks=1;