SET foreign_key_checks=0;

UPDATE REFERENCE SET Cd_Reference='Aw22' WHERE Cd_Reference='AW22';
UPDATE TGSI_REFERENTIAL SET Code='Aw22' WHERE Code='AW22';
UPDATE THEME SET Cd_Theme = REPLACE(Cd_Theme, 'AW22', 'Aw22');
UPDATE CRITERION SET Cd_Criterion = REPLACE(Cd_Criterion, 'AW22', 'Aw22');
UPDATE TEST SET Cd_Test = REPLACE(Cd_Test, 'AW22', 'Aw22');
UPDATE PROCESS_REMARK SET Message_Code = REPLACE(Message_Code, 'AW22', 'Aw22');
UPDATE PARAMETER SET Parameter_Value = REPLACE(Parameter_Value, 'AW22', 'Aw22');

UPDATE TEST SET Rank='3081001' WHERE Cd_Test='Aw22-08101';
SET foreign_key_checks=1;