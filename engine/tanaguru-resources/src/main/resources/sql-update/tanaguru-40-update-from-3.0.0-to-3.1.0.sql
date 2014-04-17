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
 ALTER TABLE  `web_resource_statistics` ADD COLUMN `Manual_Audit` IF NOT EXISTS INT NULL DEFAULT 0 
 COMMENT 'Colonne indiquant s il s agit de statistiques d un audit manuel ou automatique\n0 : automatique\n1: manuel  AFTER Http_Status_Code' ;


 
-- ---------------------------------------------------------------------------------------------------------
-- Creating the hibernate audit table of process_result
-- ---------------------------------------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS `process_result_aud` (
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
  CONSTRAINT `FK5411075EDF74E053` FOREIGN KEY (`REV`) REFERENCES `revinfo` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ---------------------------------------------------------------------------------------------------------
-- Hibernate envers technical table to refer the hibernate changes versions
-- ---------------------------------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `revinfo` (
  `REV` int(11) NOT NULL AUTO_INCREMENT,
  `REVTSTMP` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`REV`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;


