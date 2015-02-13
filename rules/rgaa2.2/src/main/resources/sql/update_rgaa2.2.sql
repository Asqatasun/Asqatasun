SET foreign_key_checks=0;

UPDATE REFERENCE SET Cd_Reference='Rgaa22' WHERE Cd_Reference='RGAA22';
UPDATE TGSI_REFERENTIAL SET Code='Rgaa22' WHERE Code='RGAA22';
UPDATE THEME SET Cd_Theme = REPLACE(Cd_Theme, 'RGAA22', 'Rgaa22');
UPDATE CRITERION SET Cd_Criterion = REPLACE(Cd_Criterion, 'RGAA22', 'Rgaa22');
UPDATE TEST SET Cd_Test = REPLACE(Cd_Test, 'RGAA22', 'Rgaa22');
UPDATE PROCESS_REMARK SET Message_Code = REPLACE(Message_Code, 'RGAA22', 'Rgaa22');
UPDATE PARAMETER SET Parameter_Value = REPLACE(Parameter_Value, 'RGAA22', 'Rgaa22');

UPDATE TEST SET Id_Level='1' WHERE Id_Level='4' And Cd_Test like "Rgaa%";
UPDATE TEST SET Id_Level='2' WHERE Id_Level='5' And Cd_Test like "Rgaa%";
UPDATE TEST SET Id_Level='3' WHERE Id_Level='6' And Cd_Test like "Rgaa%";

SET foreign_key_checks=1;