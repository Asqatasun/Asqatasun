SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- --------------------------------------------------------
--
-- table `AUDIT`
--
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS `AUDIT` (
  `Id_Audit` bigint(20) NOT NULL AUTO_INCREMENT,
  `Comment` varchar(255) DEFAULT NULL,
  `Dt_Creation` datetime DEFAULT NULL,
  `Manual_Audit_Dt_Creation` datetime DEFAULT NULL,
  `Status` varchar(255) DEFAULT NULL, 
   PRIMARY KEY (`Id_Audit`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


-- --------------------------------------------------------
--
-- table `WEB_RESOURCE`
--
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS `WEB_RESOURCE` (
  `DTYPE` varchar(31) NOT NULL,
  `Id_Web_Resource` bigint(20) NOT NULL AUTO_INCREMENT,
  `Label` varchar(255) DEFAULT NULL,
  `Url` varchar(2048) NOT NULL,
  `Rank` int DEFAULT 0,
  `Id_Audit` bigint(20) DEFAULT NULL,
  `Id_Web_Resource_Parent` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id_Web_Resource`),
  KEY `FKD9A970B9493EC9C2` (`Id_Audit`),
  CONSTRAINT `FKD9A970B9493EC9C2` 
      FOREIGN KEY Id_Audit_Index (`Id_Audit`) 
      REFERENCES `AUDIT` (`Id_Audit`) 
      ON DELETE CASCADE 
      ON UPDATE CASCADE,
  KEY `FKD9A970B92F70FF12` (`Id_Web_Resource_Parent`),
  CONSTRAINT `FKD9A970B92F70FF12` 
      FOREIGN KEY Id_Web_Resource_Parent_Index (`Id_Web_Resource_Parent`) 
      REFERENCES `WEB_RESOURCE` (`Id_Web_Resource`) 
      ON DELETE CASCADE 
      ON UPDATE CASCADE,
  INDEX `Url_Index` (`Url` ASC),
  INDEX `DTYPE_Index` (`DTYPE` ASC)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


-- --------------------------------------------------------
--
-- table `CONTENT`
--
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CONTENT` (
  `DTYPE` varchar(31) NOT NULL,
  `Id_Content` bigint(20) NOT NULL AUTO_INCREMENT,
  `Dt_Loading` datetime DEFAULT NULL,
  `Http_Status_Code` int(11) NOT NULL,
  `Uri` varchar(2048) NOT NULL,
  `Binary_Content` mediumblob,
  `Adapted_Content` longtext,
  `Source` longtext,
  `Charset` varchar(255) DEFAULT NULL,
  `Doctype` varchar(512) DEFAULT NULL,
  `Id_Audit` bigint(20) DEFAULT NULL,
  `Id_Page` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id_Content`),
  KEY `FK6382C059493EC9C2` (`Id_Audit`),
  CONSTRAINT `FK6382C059493EC9C2` 
      FOREIGN KEY Id_Audit_Index (`Id_Audit`) 
      REFERENCES `AUDIT` (`Id_Audit`) 
      ON UPDATE NO ACTION
      ON DELETE CASCADE,
  KEY `FK6382C059A8A177A1` (`Id_Page`),
  CONSTRAINT `FK6382C059A8A177A1` 
      FOREIGN KEY Id_Page_Index (`Id_Page`) 
      REFERENCES `WEB_RESOURCE` (`Id_Web_Resource`) 
      ON UPDATE NO ACTION
      ON DELETE CASCADE,
  INDEX `Uri_Index` (`Uri` ASC),
  INDEX `DTYPE_Index` (`DTYPE` ASC),
  INDEX `Http_Status_Code_Index` (`Http_Status_Code` ASC)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------
--
-- table `CONTENT_RELATIONSHIP`
--
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CONTENT_RELATIONSHIP` (
  `Id_Content_Child` bigint(20) NOT NULL,
  `Id_Content_Parent` bigint(20) NOT NULL,
  PRIMARY KEY (`Id_Content_Child`,`Id_Content_Parent`),
  KEY `FKBA33205EBA71C750` (`Id_Content_Child`),
  CONSTRAINT `FKBA33205EBA71C750` 
      FOREIGN KEY Id_Content_Child_Index (`Id_Content_Child`) 
      REFERENCES `CONTENT` (`Id_Content`) 
      ON UPDATE NO ACTION 
      ON DELETE CASCADE,
  KEY `FKBA33205E620A8494` (`Id_Content_Parent`),
  CONSTRAINT `FKBA33205E620A8494` 
      FOREIGN KEY Id_Content_Parent_Index (`Id_Content_Parent`) 
      REFERENCES `CONTENT` (`Id_Content`) 
      ON UPDATE NO ACTION 
      ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- --------------------------------------------------------
--
-- table `SCOPE`
--
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS `SCOPE` (
  `Id_Scope` bigint(20) NOT NULL AUTO_INCREMENT,
  `Code` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Label` varchar(255) NOT NULL,
  PRIMARY KEY (`Id_Scope`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


-- --------------------------------------------------------
--
-- table `DECISION_LEVEL`
--
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS `DECISION_LEVEL` (
  `Id_Decision_Level` bigint(20) NOT NULL AUTO_INCREMENT,
  `Cd_Decision_Level` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Label` varchar(255) NOT NULL,
  PRIMARY KEY (`Id_Decision_Level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


-- --------------------------------------------------------
--
-- table `LEVEL`
--
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS `LEVEL` (
  `Id_Level` bigint(20) NOT NULL AUTO_INCREMENT,
  `Cd_Level` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Label` varchar(255) DEFAULT NULL,
  `Rank` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id_Level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


-- --------------------------------------------------------
--
-- table `THEME`
--
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS `THEME` (
  `Id_Theme` bigint(20) NOT NULL AUTO_INCREMENT,
  `Cd_Theme` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Label` varchar(255) NOT NULL,
  `Rank` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id_Theme`),
  UNIQUE INDEX `Cd_Theme_UNIQUE` (`Cd_Theme` ASC),
  INDEX `Cd_Theme_Index` (`Cd_Theme` ASC)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


-- --------------------------------------------------------
--
-- table `REFERENCE`
--
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS `REFERENCE` (
  `Id_Reference` bigint(20) NOT NULL AUTO_INCREMENT,
  `Cd_Reference` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Label` varchar(255) NOT NULL,
  `Rank` int(11) DEFAULT NULL,
  `Url` varchar(255) DEFAULT NULL,
  `Id_Default_Level` bigint(20) DEFAULT 2,
  PRIMARY KEY (`Id_Reference`),
  UNIQUE INDEX `Cd_Reference_UNIQUE` (`Cd_Reference` ASC),
  KEY `fk_Ref_Level` (`Id_Default_Level`),
      CONSTRAINT `fk_Ref_Level` 
          FOREIGN KEY Id_Default_Level_Index (`Id_Default_Level`) 
          REFERENCES `LEVEL` (`Id_Level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


-- --------------------------------------------------------
--
-- table `CRITERION`
--
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS `CRITERION` (
  `Id_Criterion` bigint(20) NOT NULL AUTO_INCREMENT,
  `Cd_Criterion` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Label` varchar(255) DEFAULT NULL,
  `Rank` int(11) DEFAULT NULL,
  `Url` varchar(255) DEFAULT NULL,
  `Reference_Id_Reference` bigint(20) DEFAULT NULL,
  `Theme_Id_Theme` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id_Criterion`),
  UNIQUE INDEX `Cd_Criterion_UNIQUE` (`Cd_Criterion` ASC),
  KEY `FKBCFA1E81E8F67244` (`Theme_Id_Theme`),
  CONSTRAINT `FKBCFA1E81E8F67244` 
      FOREIGN KEY Theme_Id_Theme_Index (`Theme_Id_Theme`) 
      REFERENCES `THEME` (`Id_Theme`),
  KEY `FKBCFA1E81D03CE506` (`Reference_Id_Reference`),
  CONSTRAINT `FKBCFA1E81D03CE506` 
      FOREIGN KEY Reference_Id_Reference_Index (`Reference_Id_Reference`) 
      REFERENCES `REFERENCE` (`Id_Reference`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


-- --------------------------------------------------------
--
-- table `TEST`
--
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS `TEST` (
  `Id_Test` bigint(20) NOT NULL AUTO_INCREMENT,
  `Cd_Test` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Label` varchar(255) DEFAULT NULL,
  `Rank` int(11) DEFAULT NULL,
  `Weight` numeric(2,1) UNSIGNED DEFAULT '1.0',
  `Rule_Archive_Name` varchar(255) DEFAULT NULL,
  `Rule_Class_Name` varchar(255) DEFAULT NULL,
  `Rule_Design_Url` varchar(255) DEFAULT NULL,
  `Id_Criterion` bigint(20) DEFAULT NULL,
  `Id_Decision_Level` bigint(20) DEFAULT NULL,
  `Id_Level` bigint(20) DEFAULT NULL,
  `Id_Rule` bigint(20) DEFAULT NULL,
  `Id_Scope` bigint(20) DEFAULT NULL,
  `No_Process` bit(1) DEFAULT b'1',
  PRIMARY KEY (`Id_Test`),
  UNIQUE INDEX `Cd_Test_UNIQUE` (`Cd_Test` ASC),
  CONSTRAINT `FK273C92CCA757AD` 
      FOREIGN KEY Id_Decision_Level_Index (`Id_Decision_Level`) 
      REFERENCES `DECISION_LEVEL` (`Id_Decision_Level`),
  CONSTRAINT `FK273C9250C99824` 
      FOREIGN KEY Id_Scope_Index (`Id_Scope`) 
      REFERENCES `SCOPE` (`Id_Scope`),
  CONSTRAINT `FK273C926CCA4C3E` 
      FOREIGN KEY Id_Criterion_Index (`Id_Criterion`) 
      REFERENCES `CRITERION` (`Id_Criterion`),
  CONSTRAINT `FK273C9272343A84` 
      FOREIGN KEY Id_Level_Index (`Id_Level`) 
      REFERENCES `LEVEL` (`Id_Level`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


-- --------------------------------------------------------
--
-- table `AUDIT_TEST`
--
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS `AUDIT_TEST` (
  `Id_Audit` bigint(20) NOT NULL,
  `Id_Test` bigint(20) NOT NULL,
  KEY `FK838E6E96493EC9C2` (`Id_Audit`),
  CONSTRAINT `FK838E6E96493EC9C2` 
      FOREIGN KEY Id_Audit_Index (`Id_Audit`) 
      REFERENCES `AUDIT` (`Id_Audit`) 
      ON UPDATE NO ACTION
      ON DELETE CASCADE,
  KEY `FK838E6E96A17A5FA8` (`Id_Test`),
  CONSTRAINT `FK838E6E96A17A5FA8` 
      FOREIGN KEY Id_Test_Index (`Id_Test`) 
      REFERENCES `TEST` (`Id_Test`)
      ON UPDATE NO ACTION
      ON DELETE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- --------------------------------------------------------
--
-- table `EVIDENCE`
--
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS `EVIDENCE` (
  `Id_Evidence` bigint(20) NOT NULL AUTO_INCREMENT,
  `Cd_Evidence` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Long_Label` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id_Evidence`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


-- --------------------------------------------------------
--
-- table `PROCESS_RESULT`
--
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS `PROCESS_RESULT` (
  `DTYPE` varchar(31) NOT NULL,
  `Id_Process_Result` bigint(20) NOT NULL AUTO_INCREMENT,
  `Element_Counter` int(11) DEFAULT NULL,
  `Definite_Value` varchar(255) DEFAULT NULL,
  `Indefinite_Value` mediumtext DEFAULT NULL,
  `Id_Audit_Gross_Result` bigint(20) DEFAULT NULL,
  `Id_Audit_Net_Result` bigint(20) DEFAULT NULL,
  `Id_Process_Result_Parent` bigint(20) DEFAULT NULL,
  `Id_Web_Resource` bigint(20) NOT NULL,
  `Id_Test` bigint(20) DEFAULT NULL,
  `Manual_Definite_Value` VARCHAR(255) NULL DEFAULT NULL,
  `Manual_Audit_Comment` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`Id_Process_Result`),
  UNIQUE KEY `Id_Test` (`Id_Test`,`Id_Web_Resource`,`Id_Audit_Gross_Result`),
  UNIQUE KEY `Id_Test_2` (`Id_Test`,`Id_Web_Resource`,`Id_Audit_Net_Result`),
  KEY `FK1C41A80DFA349234` (`Id_Process_Result_Parent`),
  CONSTRAINT `FK1C41A80DFA349234` 
      FOREIGN KEY Id_Process_Result_Parent_Index (`Id_Process_Result_Parent`) 
      REFERENCES `PROCESS_RESULT` (`Id_Process_Result`),
  KEY `FK1C41A80D8146180B` (`Id_Audit_Gross_Result`),
  CONSTRAINT `FK1C41A80D8146180B` 
      FOREIGN KEY Id_Audit_Gross_Result_Index (`Id_Audit_Gross_Result`) 
      REFERENCES `AUDIT` (`Id_Audit`)
      ON DELETE CASCADE,
  KEY `FK1C41A80D2E48600` (`Id_Web_Resource`),
  CONSTRAINT `FK1C41A80D2E48600` 
      FOREIGN KEY Id_Web_Resource_Index (`Id_Web_Resource`) 
      REFERENCES `WEB_RESOURCE` (`Id_Web_Resource`) 
      ON DELETE CASCADE,
  KEY `FK1C41A80DA17A5FA8` (`Id_Test`),
  CONSTRAINT `FK1C41A80DA17A5FA8` 
      FOREIGN KEY Id_Test_Index (`Id_Test`) 
      REFERENCES `TEST` (`Id_Test`),
  KEY `FK1C41A80DB6D0E092` (`Id_Audit_Net_Result`),
  CONSTRAINT `FK1C41A80DB6D0E092` 
      FOREIGN KEY Id_Audit_Net_Result_Index (`Id_Audit_Net_Result`) 
      REFERENCES `AUDIT` (`Id_Audit`) 
      ON DELETE CASCADE,
  INDEX `Definite_Value_Index` (`Definite_Value` ASC)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


-- --------------------------------------------------------
--
-- table `PROCESS_REMARK`
--
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS `PROCESS_REMARK` (
  `DTYPE` varchar(31) NOT NULL,
  `Id_Process_Remark` bigint(20) NOT NULL AUTO_INCREMENT,
  `Issue` varchar(255) DEFAULT NULL,
  `Message_Code` varchar(255) DEFAULT NULL,
  `Selected_Element` varchar(255) DEFAULT NULL,
  `Selection_Expression` varchar(255) DEFAULT NULL,
  `Character_Position` int(11) DEFAULT NULL,
  `Line_Number` int(11) DEFAULT NULL,
  `Target` varchar(5000) DEFAULT NULL,
  `Snippet` mediumtext DEFAULT NULL,
  `Id_Process_Result` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id_Process_Remark`),
  KEY `FK1C3EA37045A988AD` (`Id_Process_Result`),
  CONSTRAINT `FK1C3EA37045A988AD` 
      FOREIGN KEY Id_Process_Result_Index (`Id_Process_Result`) 
      REFERENCES `PROCESS_RESULT` (`Id_Process_Result`) 
      ON DELETE CASCADE,
  INDEX `Issue_Index` (`Issue` ASC)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


-- --------------------------------------------------------
--
-- table `EVIDENCE_ELEMENT`
--
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS `EVIDENCE_ELEMENT` (
  `Id_Evidence_Element` bigint(20) NOT NULL AUTO_INCREMENT,
  `Element_Value` mediumtext NOT NULL,
  `EVIDENCE_Id_Evidence` bigint(20) DEFAULT NULL,
  `PROCESS_REMARK_Id_Process_Remark` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id_Evidence_Element`),
  KEY `FK698B98F4C94A0CBA` (`EVIDENCE_Id_Evidence`),
  CONSTRAINT `FK698B98F4C94A0CBA` 
      FOREIGN KEY EVIDENCE_Id_Evidence_Index (`EVIDENCE_Id_Evidence`) 
      REFERENCES `EVIDENCE` (`Id_Evidence`),
  KEY `FK698B98F425AD22C4` (`PROCESS_REMARK_Id_Process_Remark`),
  CONSTRAINT `FK698B98F425AD22C4` 
      FOREIGN KEY PROCESS_REMARK_Id_Process_Remark_Index (`PROCESS_REMARK_Id_Process_Remark`) 
      REFERENCES `PROCESS_REMARK` (`Id_Process_Remark`) 
      ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


-- --------------------------------------------------------
--
-- table `NOMENCLATURE`
--
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS `NOMENCLATURE` (
  `Id_Nomenclature` bigint(20) NOT NULL AUTO_INCREMENT,
  `Cd_Nomenclature` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Long_Label` varchar(255) DEFAULT NULL,
  `Short_Label` varchar(255) DEFAULT NULL,
  `Id_Nomenclature_Parent` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id_Nomenclature`),
  KEY `FKBF856B7795431825` (`Id_Nomenclature_Parent`),
  CONSTRAINT `FKBF856B7795431825` 
      FOREIGN KEY Id_Nomenclature_Parent_Index (`Id_Nomenclature_Parent`) 
      REFERENCES `NOMENCLATURE` (`Id_Nomenclature`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


-- --------------------------------------------------------
--
-- table `NOMENCLATURE_ELEMENT`
--
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS `NOMENCLATURE_ELEMENT` (
  `DTYPE` varchar(31) NOT NULL,
  `Id_Nomenclature_Element` bigint(20) NOT NULL AUTO_INCREMENT,
  `Label` varchar(255) NOT NULL,
  `shortValue` int(11) DEFAULT NULL,
  `Id_Nomenclature` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id_Nomenclature_Element`),
  KEY `FK44F856145FAB5EF2` (`Id_Nomenclature`),
  CONSTRAINT `FK44F856145FAB5EF2` 
      FOREIGN KEY Id_Nomenclature_Index (`Id_Nomenclature`) 
      REFERENCES `NOMENCLATURE` (`Id_Nomenclature`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


-- --------------------------------------------------------
--
-- table `STANDARD_MESSAGE`
--
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS `STANDARD_MESSAGE` (
  `Id_Standard_Message` bigint(20) NOT NULL AUTO_INCREMENT,
  `Cd_Standard_Message` varchar(255) DEFAULT NULL,
  `Label` varchar(255) DEFAULT NULL,
  `Text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id_Standard_Message`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


-- --------------------------------------------------------
--
-- table `PRE_PROCESS_RESULT`
--
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS `PRE_PROCESS_RESULT` (
  `Id_Pre_Process_Result` bigint(20) NOT NULL AUTO_INCREMENT,
  `Pre_Process_Key` varchar(255) NOT NULL,
  `Pre_Process_Value` mediumtext DEFAULT NULL,
  `Id_Audit` bigint(20) NOT NULL,
  `Id_Web_Resource` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id_Pre_Process_Result`),
  UNIQUE KEY `Key_Wr_Audit` (`Pre_Process_Key`,`Id_Web_Resource`,`Id_Audit`),
  INDEX `fk_PRE_PROCESS_RESULT_AUDIT` (`Id_Audit` ASC) ,
  CONSTRAINT `fk_PRE_PROCESS_RESULT_AUDIT`
    FOREIGN KEY (`Id_Audit` )
    REFERENCES `AUDIT` (`Id_Audit` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  INDEX `fk_PRE_PROCESS_RESULT_WEB_RESOURCE` (`Id_Web_Resource` ASC) ,
  CONSTRAINT `fk_PRE_PROCESS_RESULT_WEB_RESOURCE`
    FOREIGN KEY (`Id_Web_Resource` )
    REFERENCES `WEB_RESOURCE` (`Id_Web_Resource` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------
--
-- table `SNAPSHOT`
--
-- --------------------------------------------------------
-- CREATE TABLE IF NOT EXISTS `SNAPSHOT` (
--  `Id_Snapshot` bigint(20) NOT NULL AUTO_INCREMENT,
--  `Snapshot_Content` mediumblob,
--  `Id_Page` bigint(20) DEFAULT NULL,
--  PRIMARY KEY (`Id_Snapshot`),
--  INDEX `fk_SNAPSHOT_WEB_RESOURCE` (`Id_Page` ASC) ,
--  CONSTRAINT `fk_SNAPSHOT_WEB_RESOURCE`
--    FOREIGN KEY (`Id_Page` )
--    REFERENCES `WEB_RESOURCE` (`Id_Web_Resource` )
--    ON DELETE CASCADE
--    ON UPDATE NO ACTION
-- ) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


-- -----------------------------------------------------
-- Table `PARAMETER_FAMILY`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `PARAMETER_FAMILY` (
  `Id_Parameter_Family` BIGINT NOT NULL AUTO_INCREMENT,
  `Cd_Parameter_Family` VARCHAR(255) NOT NULL ,
  `Description` VARCHAR(255) NULL ,
  `Long_Label` VARCHAR(255) NULL ,
  `Short_Label` VARCHAR(255) NULL ,
  PRIMARY KEY (`Id_Parameter_Family`),
  UNIQUE INDEX `Cd_Parameter_Family_UNIQUE` (`Cd_Parameter_Family` ASC)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PARAMETER_ELEMENT`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `PARAMETER_ELEMENT` (
  `Id_Parameter_Element` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `Cd_Parameter_Element` VARCHAR(255) NOT NULL ,
  `Id_Parameter_Family` BIGINT(20) NOT NULL ,
  `Short_Label` VARCHAR(255) NULL ,
  `Long_Label` VARCHAR(255) NULL ,
  PRIMARY KEY (`Id_Parameter_Element`) ,
  INDEX `fk_PARAMETER_ELEMENT_PARAMETER` (`Id_Parameter_Family` ASC) ,
  UNIQUE INDEX `Cd_Parameter_Element_UNIQUE` (`Cd_Parameter_Element` ASC) ,
  CONSTRAINT `fk_PARAMETER_ELEMENT_PARAMETER`
    FOREIGN KEY (`Id_Parameter_Family` )
    REFERENCES `PARAMETER_FAMILY` (`Id_Parameter_Family` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `PARAMETER`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `PARAMETER` (
  `Id_Parameter` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `Parameter_Value` VARCHAR(1000) NOT NULL ,
  `Id_Parameter_Element` BIGINT(20) NOT NULL ,
  `Is_Default` BIT NULL DEFAULT 0 ,
  PRIMARY KEY (`Id_Parameter`) ,
  INDEX `fk_PARAMETER_PARAMETER_ELEMENT` (`Id_Parameter_Element` ASC) ,
  UNIQUE KEY `Unique_Param_Element_Type_Param_value` (`Parameter_Value`(255), `Id_Parameter_Element`),
  CONSTRAINT `fk_PARAMETER_PARAMETER_ELEMENT`
    FOREIGN KEY (`Id_Parameter_Element` )
    REFERENCES `PARAMETER_ELEMENT` (`Id_Parameter_Element` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `AUDIT_PARAMETER_VALUE`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `AUDIT_PARAMETER` (
  `Id_Audit` BIGINT(20) NOT NULL ,
  `Id_Parameter` BIGINT(20) NOT NULL ,
  INDEX `fk_AUDIT_PARAMETER_AUDIT` (`Id_Audit` ASC) ,
  INDEX `fk_AUDIT_PARAMETER_PARAMETER` (`Id_Parameter` ASC) ,
  CONSTRAINT `fk_AUDIT_PARAMETER_AUDIT`
    FOREIGN KEY (`Id_Audit` )
    REFERENCES `AUDIT` (`Id_Audit` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_AUDIT_PARAMETER_PARAMETER`
    FOREIGN KEY (`Id_Parameter` )
    REFERENCES `PARAMETER` (`Id_Parameter` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `WEB_RESOURCE_STATISTICS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `WEB_RESOURCE_STATISTICS` (
  `Id_Web_Resource_Statistics` bigint(20) NOT NULL AUTO_INCREMENT,
  `Mark` float DEFAULT NULL,
  `Raw_Mark` float DEFAULT NULL,
  `Nb_Passed` int(11) DEFAULT NULL,
  `Nb_Failed` int(11) DEFAULT NULL,
  `Nb_Nmi` int(11) DEFAULT NULL,
  `Nb_Na` int(11) DEFAULT NULL,
  `Nb_Suspected` int(11) DEFAULT NULL,
  `Nb_Detected` int(11) DEFAULT NULL,
  `Nb_Not_Tested` int(11) DEFAULT NULL,
  `Weighted_Passed` numeric(10,1) UNSIGNED DEFAULT NULL,
  `Weighted_Failed` numeric(10,1) UNSIGNED DEFAULT NULL,
  `Weighted_Nmi` numeric(10,1) UNSIGNED DEFAULT NULL,
  `Weighted_Na` numeric(10,1) UNSIGNED DEFAULT NULL,
  `Nb_Failed_Occurrences` int(11) DEFAULT NULL,
  `Nb_Invalid_Test` int(11) DEFAULT NULL,
  `Id_Audit` bigint(20) DEFAULT NULL,
  `Id_Web_Resource` bigint(20) DEFAULT NULL,
  `Http_Status_Code` int(11) DEFAULT -1,
  `Manual_Audit` int(11) NULL DEFAULT 0, 
  PRIMARY KEY (`Id_Web_Resource_Statistics`),
  INDEX `fk_WEB_RESOURCE_STATISTICS_AUDIT` (`Id_Audit` ASC) ,
  CONSTRAINT `fk_WEB_RESOURCE_STATISTICS_AUDIT`
    FOREIGN KEY (`Id_Audit` )
    REFERENCES `AUDIT` (`Id_Audit` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  INDEX `fk_WEB_RESOURCE_STATISTICS_WEB_RESOURCE` (`Id_Web_Resource` ASC) ,
  CONSTRAINT `fk_WEB_RESOURCE_STATISTICS_WEB_RESOURCE`
    FOREIGN KEY (`Id_Web_Resource` )
    REFERENCES `WEB_RESOURCE` (`Id_Web_Resource` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

-- -----------------------------------------------------
-- Table `THEME_STATISTICS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `THEME_STATISTICS` (
  `Id_Theme_Statistics` bigint(20) NOT NULL AUTO_INCREMENT,
  `Nb_Passed` int(11) DEFAULT NULL,
  `Nb_Failed` int(11) DEFAULT NULL,
  `Nb_Nmi` int(11) DEFAULT NULL,
  `Nb_Na` int(11) DEFAULT NULL,
  `Nb_Suspected` int(11) DEFAULT NULL,
  `Nb_Detected` int(11) DEFAULT NULL,
  `Nb_Not_Tested` int(11) DEFAULT NULL,
  `Id_Theme` bigint(20) DEFAULT NULL,
  `Id_Web_Resource_Statistics` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id_Theme_Statistics`),
  INDEX `fk_THEME_STATISTICS_THEME` (`Id_Theme` ASC) ,
  CONSTRAINT `fk_THEME_STATISTICS_THEME`
    FOREIGN KEY (`Id_Theme` )
    REFERENCES `THEME` (`Id_Theme` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  INDEX `fk_THEME_STATISTICS_WEB_RESOURCE_STATISTICS` (`Id_Web_Resource_Statistics` ASC) ,
  CONSTRAINT `fk_THEME_STATISTICS_WEB_RESOURCE_STATISTICS`
    FOREIGN KEY (`Id_Web_Resource_Statistics` )
    REFERENCES `WEB_RESOURCE_STATISTICS` (`Id_Web_Resource_Statistics` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `CRITERION_STATISTICS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CRITERION_STATISTICS` (
  `Id_Criterion_Statistics` bigint(20) NOT NULL AUTO_INCREMENT,
  `Nb_Passed` int(11) DEFAULT NULL,
  `Nb_Failed` int(11) DEFAULT NULL,
  `Nb_Nmi` int(11) DEFAULT NULL,
  `Nb_Na` int(11) DEFAULT NULL,
  `Nb_Suspected` int(11) DEFAULT NULL,
  `Nb_Detected` int(11) DEFAULT NULL,
  `Nb_Not_Tested` int(11) DEFAULT NULL,
  `Criterion_Result` varchar(255) DEFAULT NULL,
  `Id_Criterion` bigint(20) DEFAULT NULL,
  `Id_Web_Resource_Statistics` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id_Criterion_Statistics`),
  INDEX `fk_CRITERION_STATISTICS_CRITERION` (`Id_Criterion` ASC) ,
  CONSTRAINT `fk_CRITERION_STATISTICS_CRITERION`
    FOREIGN KEY (`Id_Criterion` )
    REFERENCES `CRITERION` (`Id_Criterion` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  INDEX `fk_CRITERION_STATISTICS_WEB_RESOURCE_STATISTICS` (`Id_Web_Resource_Statistics` ASC) ,
  CONSTRAINT `fk_CRITERION_STATISTICS_WEB_RESOURCE_STATISTICS`
    FOREIGN KEY (`Id_Web_Resource_Statistics` )
    REFERENCES `WEB_RESOURCE_STATISTICS` (`Id_Web_Resource_Statistics` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `TEST_STATISTICS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TEST_STATISTICS` (
  `Id_Test_Statistics` bigint(20) NOT NULL AUTO_INCREMENT,
  `Nb_Passed` int(11) DEFAULT NULL,
  `Nb_Failed` int(11) DEFAULT NULL,
  `Nb_Nmi` int(11) DEFAULT NULL,
  `Nb_Na` int(11) DEFAULT NULL,
  `Nb_Suspected` int(11) DEFAULT NULL,
  `Nb_Detected` int(11) DEFAULT NULL,
  `Nb_Not_Tested` int(11) DEFAULT NULL,
  `Id_Test` bigint(20) DEFAULT NULL,
  `Id_Web_Resource_Statistics` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id_Test_Statistics`),
  INDEX `fk_THEME_STATISTICS_THEME` (`Id_Test` ASC) ,
  CONSTRAINT `fk_TEST_STATISTICS_TEST`
    FOREIGN KEY (`Id_Test` )
    REFERENCES `TEST` (`Id_Test` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  INDEX `fk_THEME_STATISTICS_WEB_RESOURCE_STATISTICS` (`Id_Web_Resource_Statistics` ASC) ,
  CONSTRAINT `fk_TEST_STATISTICS_WEB_RESOURCE_STATISTICS`
    FOREIGN KEY (`Id_Web_Resource_Statistics` )
    REFERENCES `WEB_RESOURCE_STATISTICS` (`Id_Web_Resource_Statistics` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

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