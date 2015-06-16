use $myDatabaseName;
SET foreign_key_checks=0;

-- -----------------------------------------------------
-- Column `Manual_Definite_Value` to store the manual 
-- audit value
-- -----------------------------------------------------
ALTER TABLE `PROCESS_RESULT` 
ADD COLUMN `Manual_Definite_Value` VARCHAR(255) NULL DEFAULT NULL 
COMMENT 'Manual audit overridden definite value' AFTER `Id_Test`;

-- -----------------------------------------------------
-- Column `Manual_Audit_Comment` to store the manual 
-- audit invalidation comment
-- -----------------------------------------------------
ALTER TABLE `PROCESS_RESULT` 
ADD COLUMN `Manual_Audit_Comment` VARCHAR(255) NULL DEFAULT NULL 
COMMENT 'Manual audit user comment' AFTER `Manual_Definite_Value`;

-- -----------------------------------------------------
-- Column `Manual_Audit_Dt_Creation` to store the manual 
-- audit launch dates
-- -----------------------------------------------------
ALTER TABLE `AUDIT` 
ADD COLUMN `Manual_Audit_Dt_Creation` DATETIME NULL DEFAULT NULL AFTER `Dt_Creation`;

-- ---------------------------------------------------------------------------------------------------------
-- Column `web_resource_statistics` to know if the web_resource_statistics is for manual or automatic audit 
-- ---------------------------------------------------------------------------------------------------------
 ALTER TABLE  `WEB_RESOURCE_STATISTICS` ADD COLUMN `Manual_Audit` int(11) NULL DEFAULT 0;


-- ---------------------------------------------------------------------------------------------------------
-- Hibernate envers technical table to refer the hibernate changes versions
-- ---------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `REVINFO` (
  `REV` int(11) NOT NULL AUTO_INCREMENT,
  `REVTSTMP` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`REV`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
 
-- ---------------------------------------------------------------------------------------------------------
-- Creating the hibernate audit table of process_result
-- ---------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `PROCESS_RESULT_AUD` (
  `DTYPE` varchar(31) NOT NULL,
  `Id_Process_Result` bigint(20) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `Element_Counter` int(11) DEFAULT NULL,
  `Id_Process_Result_Parent` bigint(20) DEFAULT NULL,
  `Definite_Value` varchar(255) DEFAULT NULL,
  `Manual_Audit_Comment` varchar(255) DEFAULT NULL,
  `Manual_Definite_Value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id_Process_Result`,`REV`),
  KEY `FK5411075EDF74E053` (`REV`),
  CONSTRAINT `FK5411075EDF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT IGNORE INTO `PARAMETER_ELEMENT` (`Cd_Parameter_Element`, `Long_Label`, `Short_Label`) VALUES
('INCLUSION_REGEXP', 'Regular expression to crawl on a specific folder', 'inclusion regex');

UPDATE `PARAMETER_ELEMENT` SET `Id_Parameter_Family` = (SELECT `Id_Parameter_Family` FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` LIKE 'CRAWL') WHERE `Cd_Parameter_Element` LIKE 'INCLUSION_REGEXP';

INSERT IGNORE INTO `PARAMETER` (`Parameter_Value`, `Is_Default`) VALUES
((SELECT `Id_Parameter_Element` FROM `PARAMETER_ELEMENT` WHERE `Cd_Parameter_Element` LIKE 'INCLUSION_REGEXP'), '', b'1');

ALTER IGNORE TABLE REFERENCE ADD `Id_Default_Level` bigint(20) DEFAULT 2 AFTER `Url`;

ALTER TABLE `REFERENCE`
	ADD CONSTRAINT `fk_Ref_Level` FOREIGN KEY Id_Default_Level_Index (`Id_Default_Level`)  REFERENCES `LEVEL` (`Id_Level`) ON DELETE NO ACTION;

ALTER TABLE `PARAMETER`	ADD UNIQUE KEY `Unique_Param_Element_Type_Param_value` 
        (`Parameter_Value`(255),`Id_Parameter_Element`);

ALTER TABLE `THEME` ADD UNIQUE INDEX `Cd_Theme_UNIQUE` (`Cd_Theme` ASC);
ALTER TABLE `TEST` ADD UNIQUE INDEX `Cd_Test_UNIQUE` (`Cd_Test` ASC);
ALTER TABLE `CRITERION` ADD UNIQUE INDEX `Cd_Criterion_UNIQUE` (`Cd_Criterion` ASC);
ALTER TABLE `REFERENCE` ADD UNIQUE INDEX `Cd_Reference_UNIQUE` (`Cd_Reference` ASC);

UPDATE LEVEL SET Cd_Level='LEVEL_1' WHERE Cd_Level='Bz';
UPDATE LEVEL SET Label='Level 1' WHERE Cd_Level='LEVEL_1';
UPDATE LEVEL SET Cd_Level='LEVEL_2' WHERE Cd_Level='Ar';
UPDATE LEVEL SET Label='Level 2' WHERE Cd_Level='LEVEL_2';
UPDATE LEVEL SET Cd_Level='LEVEL_3' WHERE Cd_Level='Or';
UPDATE LEVEL SET Label='Level 3' WHERE Cd_Level='LEVEL_3';

UPDATE PARAMETER SET Parameter_Value = REPLACE(Parameter_Value, ';Bz', ';LEVEL_1');
UPDATE PARAMETER SET Parameter_Value = REPLACE(Parameter_Value, ';Ar', ';LEVEL_2');
UPDATE PARAMETER SET Parameter_Value = REPLACE(Parameter_Value, ';Or', ';LEVEL_3');
UPDATE PARAMETER SET Parameter_Value = REPLACE(Parameter_Value, ';AAA', ';LEVEL_3');
UPDATE PARAMETER SET Parameter_Value = REPLACE(Parameter_Value, ';AA', ';LEVEL_2');
UPDATE PARAMETER SET Parameter_Value = REPLACE(Parameter_Value, ';A', ';LEVEL_1');

INSERT IGNORE INTO `PARAMETER_ELEMENT` (`Cd_Parameter_Element`, `Long_Label`, `Short_Label`) VALUES
('SCREEN_WIDTH', '', 'screen width'),
('SCREEN_HEIGHT', '', 'screen height');

UPDATE `PARAMETER_ELEMENT` SET `Id_Parameter_Family` = (SELECT `Id_Parameter_Family` FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` LIKE 'GENERAL') WHERE `Cd_Parameter_Element` LIKE 'SCREEN_WIDTH';
UPDATE `PARAMETER_ELEMENT` SET `Id_Parameter_Family` = (SELECT `Id_Parameter_Family` FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` LIKE 'GENERAL') WHERE `Cd_Parameter_Element` LIKE 'SCREEN_HEIGHT';

INSERT IGNORE INTO `PARAMETER` (`Id_Parameter_Element`, `Parameter_Value`, `Is_Default`) VALUES
((SELECT `Id_Parameter_Element` FROM `PARAMETER_ELEMENT` WHERE `Cd_Parameter_Element` LIKE 'SCREEN_WIDTH'), '1920', b'1'),
((SELECT `Id_Parameter_Element` FROM `PARAMETER_ELEMENT` WHERE `Cd_Parameter_Element` LIKE 'SCREEN_HEIGHT'), '1080', b'1');

SET foreign_key_checks=1;