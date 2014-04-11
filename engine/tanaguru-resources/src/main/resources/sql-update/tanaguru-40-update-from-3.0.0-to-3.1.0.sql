use $myDatabaseName;

-- -----------------------------------------------------
-- Column `Manual_Definite_Value` to store the manual 
-- audit value
-- -----------------------------------------------------
ALTER TABLE `process_result` 
ADD COLUMN `Manual_Definite_Value` VARCHAR(255) NULL DEFAULT NULL 
COMMENT 'Manual audit overridden definite value' AFTER `Id_Test`;

-- -----------------------------------------------------
-- Column `Manual_Audit_Comment` to store the manual 
-- audit invalidation comment
-- -----------------------------------------------------
ALTER TABLE `process_result` 
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
ALTER TABLE `web_resource_statistics` ADD COLUMN `Manual_Audit` INT NULL DEFAULT 0 
COMMENT 'Colonne indiquant s\'il s\'agit de statistiques d\'un audit manuel ou automatique\n0 : automatique\n1: manuel'  AFTER `Http_Status_Code` ;