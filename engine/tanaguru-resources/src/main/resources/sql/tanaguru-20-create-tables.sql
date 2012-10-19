SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

-- --------------------------------------------------------

--
-- Structure de la table `AUDIT`
--

CREATE TABLE IF NOT EXISTS `AUDIT` (
  `Id_Audit` bigint(20) NOT NULL AUTO_INCREMENT,
  `Comment` varchar(255) DEFAULT NULL,
  `Dt_Creation` datetime DEFAULT NULL,
  `Status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id_Audit`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `AUDIT_TEST`
--

CREATE TABLE IF NOT EXISTS `AUDIT_TEST` (
  `Id_Audit` bigint(20) NOT NULL,
  `Id_Test` bigint(20) NOT NULL,
  KEY `FK838E6E96493EC9C2` (`Id_Audit`),
  KEY `FK838E6E96A17A5FA8` (`Id_Test`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `CONTENT`
--

CREATE TABLE IF NOT EXISTS `CONTENT` (
  `DTYPE` varchar(31) NOT NULL,
  `Id_Content` bigint(20) NOT NULL AUTO_INCREMENT,
  `Dt_Loading` datetime DEFAULT NULL,
  `Http_Status_Code` int(11) NOT NULL,
  `Uri` varchar(2048) NOT NULL,
  `Binary_Content` mediumblob,
  `Adapted_Content` mediumtext,
  `Source` mediumtext,
  `Charset` varchar(255) DEFAULT NULL,
  `Doctype` varchar(512) DEFAULT NULL,
  `Id_Audit` bigint(20) DEFAULT NULL,
  `Id_Page` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id_Content`),
  KEY `FK6382C059493EC9C2` (`Id_Audit`),
  KEY `FK6382C059A8A177A1` (`Id_Page`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `CONTENT_RELATIONSHIP`
--

CREATE TABLE IF NOT EXISTS `CONTENT_RELATIONSHIP` (
  `Id_Content_Child` bigint(20) NOT NULL,
  `Id_Content_Parent` bigint(20) NOT NULL,
  PRIMARY KEY (`Id_Content_Child`,`Id_Content_Parent`),
  KEY `FKBA33205EBA71C750` (`Id_Content_Child`),
  KEY `FKBA33205E620A8494` (`Id_Content_Parent`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `CRITERION`
--

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
  KEY `FKBCFA1E81E8F67244` (`Theme_Id_Theme`),
  KEY `FKBCFA1E81D03CE506` (`Reference_Id_Reference`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `DECISION_LEVEL`
--

CREATE TABLE IF NOT EXISTS `DECISION_LEVEL` (
  `Id_Decision_Level` bigint(20) NOT NULL AUTO_INCREMENT,
  `Cd_Decision_Level` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Label` varchar(255) NOT NULL,
  PRIMARY KEY (`Id_Decision_Level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `EVIDENCE`
--

CREATE TABLE IF NOT EXISTS `EVIDENCE` (
  `Id_Evidence` bigint(20) NOT NULL AUTO_INCREMENT,
  `Cd_Evidence` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Long_Label` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id_Evidence`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `EVIDENCE_ELEMENT`
--

CREATE TABLE IF NOT EXISTS `EVIDENCE_ELEMENT` (
  `Id_Evidence_Element` bigint(20) NOT NULL AUTO_INCREMENT,
  `Element_Value` varchar(4096) NOT NULL,
  `EVIDENCE_Id_Evidence` bigint(20) DEFAULT NULL,
  `PROCESS_REMARK_Id_Process_Remark` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id_Evidence_Element`),
  KEY `FK698B98F4C94A0CBA` (`EVIDENCE_Id_Evidence`),
  KEY `FK698B98F425AD22C4` (`PROCESS_REMARK_Id_Process_Remark`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `LEVEL`
--

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
-- Structure de la table `NOMENCLATURE`
--

CREATE TABLE IF NOT EXISTS `NOMENCLATURE` (
  `Id_Nomenclature` bigint(20) NOT NULL AUTO_INCREMENT,
  `Cd_Nomenclature` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Long_Label` varchar(255) DEFAULT NULL,
  `Short_Label` varchar(255) DEFAULT NULL,
  `Id_Nomenclature_Parent` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id_Nomenclature`),
  KEY `FKBF856B7795431825` (`Id_Nomenclature_Parent`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `NOMENCLATURE_ELEMENT`
--

CREATE TABLE IF NOT EXISTS `NOMENCLATURE_ELEMENT` (
  `DTYPE` varchar(31) NOT NULL,
  `Id_Nomenclature_Element` bigint(20) NOT NULL AUTO_INCREMENT,
  `Label` varchar(255) NOT NULL,
  `shortValue` int(11) DEFAULT NULL,
  `Id_Nomenclature` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id_Nomenclature_Element`),
  KEY `FK44F856145FAB5EF2` (`Id_Nomenclature`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `PROCESS_REMARK`
--

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
  `Id_Process_Result` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id_Process_Remark`),
  KEY `FK1C3EA37045A988AD` (`Id_Process_Result`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `PROCESS_RESULT`
--

CREATE TABLE IF NOT EXISTS `PROCESS_RESULT` (
  `DTYPE` varchar(31) NOT NULL,
  `Id_Process_Result` bigint(20) NOT NULL AUTO_INCREMENT,
  `Element_Counter` int(11) DEFAULT NULL,
  `Definite_Value` varchar(255) DEFAULT NULL,
  `Indefinite_Value` varchar(4096),
  `Id_Audit_Gross_Result` bigint(20) DEFAULT NULL,
  `Id_Audit_Net_Result` bigint(20) DEFAULT NULL,
  `Id_Process_Result_Parent` bigint(20) DEFAULT NULL,
  `Id_Web_Resource` bigint(20) NOT NULL,
  `Id_Test` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id_Process_Result`),
  UNIQUE KEY `Id_Test` (`Id_Test`,`Id_Web_Resource`,`Id_Audit_Gross_Result`),
  UNIQUE KEY `Id_Test_2` (`Id_Test`,`Id_Web_Resource`,`Id_Audit_Net_Result`),
  KEY `FK1C41A80DFA349234` (`Id_Process_Result_Parent`),
  KEY `FK1C41A80D8146180B` (`Id_Audit_Gross_Result`),
  KEY `FK1C41A80D2E48600` (`Id_Web_Resource`),
  KEY `FK1C41A80DA17A5FA8` (`Id_Test`),
  KEY `FK1C41A80DB6D0E092` (`Id_Audit_Net_Result`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `REFERENCE`
--

CREATE TABLE IF NOT EXISTS `REFERENCE` (
  `Id_Reference` bigint(20) NOT NULL AUTO_INCREMENT,
  `Cd_Reference` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Label` varchar(255) NOT NULL,
  `Rank` int(11) DEFAULT NULL,
  `Url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id_Reference`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `RULE`
--

CREATE TABLE IF NOT EXISTS `RULE` (
  `Id_Rule` bigint(20) NOT NULL AUTO_INCREMENT,
  `Class_Name` varchar(255) NOT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Id_Rule_Package` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id_Rule`),
  KEY `FK268EFCADFA0F1` (`Id_Rule_Package`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `RULE_PACKAGE`
--

CREATE TABLE IF NOT EXISTS `RULE_PACKAGE` (
  `Id_Rule_Package` bigint(20) NOT NULL AUTO_INCREMENT,
  `Description` varchar(255) DEFAULT NULL,
  `Package_Name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id_Rule_Package`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `SCOPE`
--

CREATE TABLE IF NOT EXISTS `SCOPE` (
  `Id_Scope` bigint(20) NOT NULL AUTO_INCREMENT,
  `Code` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Label` varchar(255) NOT NULL,
  PRIMARY KEY (`Id_Scope`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `STANDARD_MESSAGE`
--

CREATE TABLE IF NOT EXISTS `STANDARD_MESSAGE` (
  `Id_Standard_Message` bigint(20) NOT NULL AUTO_INCREMENT,
  `Cd_Standard_Message` varchar(255) DEFAULT NULL,
  `Label` varchar(255) DEFAULT NULL,
  `Text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id_Standard_Message`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `TEST`
--

CREATE TABLE IF NOT EXISTS `TEST` (
  `Id_Test` bigint(20) NOT NULL AUTO_INCREMENT,
  `Cd_Test` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Label` varchar(255) DEFAULT NULL,
  `Rank` int(11) DEFAULT NULL,
  `Weight` decimal(2,1) UNSIGNED DEFAULT '1.0',
  `Rule_Archive_Name` varchar(255) DEFAULT NULL,
  `Rule_Class_Name` varchar(255) DEFAULT NULL,
  `Rule_Design_Url` varchar(255) DEFAULT NULL,
  `Id_Criterion` bigint(20) DEFAULT NULL,
  `Id_Decision_Level` bigint(20) DEFAULT NULL,
  `Id_Level` bigint(20) DEFAULT NULL,
  `Id_Rule` bigint(20) DEFAULT NULL,
  `Id_Scope` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id_Test`),
  KEY `FK273C9250C99824` (`Id_Scope`),
  KEY `FK273C9272343A84` (`Id_Level`),
  KEY `FK273C926CCA4C3E` (`Id_Criterion`),
  KEY `FK273C921355BF7C` (`Id_Rule`),
  KEY `FK273C92CCA757AD` (`Id_Decision_Level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `THEME`
--

CREATE TABLE IF NOT EXISTS `THEME` (
  `Id_Theme` bigint(20) NOT NULL AUTO_INCREMENT,
  `Cd_Theme` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Label` varchar(255) NOT NULL,
  `Rank` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id_Theme`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `WEB_RESOURCE`
--

CREATE TABLE IF NOT EXISTS `WEB_RESOURCE` (
  `DTYPE` varchar(31) NOT NULL,
  `Id_Web_Resource` bigint(20) NOT NULL AUTO_INCREMENT,
  `Label` varchar(255) DEFAULT NULL,
  `Mark` float DEFAULT NULL,
  `Url` varchar(2048) NOT NULL,
  `Rank` int DEFAULT 0,
  `Id_Audit` bigint(20) DEFAULT NULL,
  `Id_Web_Resource_Parent` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id_Web_Resource`),
  KEY `FKD9A970B9493EC9C2` (`Id_Audit`),
  KEY `FKD9A970B92F70FF12` (`Id_Web_Resource_Parent`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


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
-- Table `tanaguru`.`WEB_RESOURCE_STATISTICS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `WEB_RESOURCE_STATISTICS` (
  `Id_Web_Resource_Statistics` bigint(20) NOT NULL AUTO_INCREMENT,
  `Mark` float DEFAULT NULL,
  `Raw_Mark` float DEFAULT NULL,
  `Nb_Passed` int(11) DEFAULT NULL,
  `Nb_Failed` int(11) DEFAULT NULL,
  `Nb_Nmi` int(11) DEFAULT NULL,
  `Nb_Na` int(11) DEFAULT NULL,
  `Weighted_Passed` decimal(7,1) UNSIGNED DEFAULT NULL,
  `Weighted_Failed` decimal(7,1) UNSIGNED DEFAULT NULL,
  `Weighted_Nmi` decimal(7,1) UNSIGNED DEFAULT NULL,
  `Weighted_Na` decimal(7,1) UNSIGNED DEFAULT NULL,
  `Nb_Failed_Occurrences` int(11) DEFAULT NULL,
  `Nb_Invalid_Test` int(11) DEFAULT NULL,
  `Id_Audit` bigint(20) DEFAULT NULL,
  `Id_Web_Resource` bigint(20) DEFAULT NULL,
  `Http_Status_Code` int(11) DEFAULT -1,
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
-- Table `tanaguru`.`THEME_STATISTICS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `THEME_STATISTICS` (
  `Id_Theme_Statistics` bigint(20) NOT NULL AUTO_INCREMENT,
  `Nb_Passed` int(11) DEFAULT NULL,
  `Nb_Failed` int(11) DEFAULT NULL,
  `Nb_Nmi` int(11) DEFAULT NULL,
  `Nb_Na` int(11) DEFAULT NULL,
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
-- Table `tanaguru`.`TEST_STATISTICS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TEST_STATISTICS` (
  `Id_Test_Statistics` bigint(20) NOT NULL AUTO_INCREMENT,
  `Nb_Passed` int(11) DEFAULT NULL,
  `Nb_Failed` int(11) DEFAULT NULL,
  `Nb_Nmi` int(11) DEFAULT NULL,
  `Nb_Na` int(11) DEFAULT NULL,
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

--
-- Contraintes pour la table `AUDIT_TEST`
--
ALTER TABLE `AUDIT_TEST`
  ADD CONSTRAINT `FK838E6E96A17A5FA8` FOREIGN KEY Id_Test_Index (`Id_Test`) REFERENCES `TEST` (`Id_Test`),
  ADD CONSTRAINT `FK838E6E96493EC9C2` FOREIGN KEY Id_Audit_Index (`Id_Audit`) REFERENCES `AUDIT` (`Id_Audit`) ON DELETE CASCADE;

--
-- Contraintes pour la table `CONTENT`
--
ALTER TABLE `CONTENT`
  ADD CONSTRAINT `FK6382C059A8A177A1` FOREIGN KEY Id_Page_Index (`Id_Page`) REFERENCES `WEB_RESOURCE` (`Id_Web_Resource`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK6382C059493EC9C2` FOREIGN KEY Id_Audit_Index (`Id_Audit`) REFERENCES `AUDIT` (`Id_Audit`) ON DELETE CASCADE;
CREATE INDEX Uri_Index ON CONTENT (Uri);
CREATE INDEX DTYPE_Index ON CONTENT (DTYPE);
CREATE INDEX Http_Status_Code_Index ON CONTENT (Http_Status_Code);
--
-- Contraintes pour la table `CONTENT_RELATIONSHIP`
--
ALTER TABLE `CONTENT_RELATIONSHIP`
  ADD CONSTRAINT `FKBA33205E620A8494` FOREIGN KEY Id_Content_Parent_Index (`Id_Content_Parent`) REFERENCES `CONTENT` (`Id_Content`) ON UPDATE NO ACTION ON DELETE CASCADE,
  ADD CONSTRAINT `FKBA33205EBA71C750` FOREIGN KEY Id_Content_Child_Index (`Id_Content_Child`) REFERENCES `CONTENT` (`Id_Content`) ON UPDATE NO ACTION ON DELETE CASCADE;

--
-- Contraintes pour la table `CRITERION`
--
ALTER TABLE `CRITERION`
  ADD CONSTRAINT `FKBCFA1E81D03CE506` FOREIGN KEY Reference_Id_Reference_Index (`Reference_Id_Reference`) REFERENCES `REFERENCE` (`Id_Reference`),
  ADD CONSTRAINT `FKBCFA1E81E8F67244` FOREIGN KEY Theme_Id_Theme_Index (`Theme_Id_Theme`) REFERENCES `THEME` (`Id_Theme`);

--
-- Contraintes pour la table `EVIDENCE_ELEMENT`
--
ALTER TABLE `EVIDENCE_ELEMENT`
  ADD CONSTRAINT `FK698B98F425AD22C4` FOREIGN KEY PROCESS_REMARK_Id_Process_Remark_Index (`PROCESS_REMARK_Id_Process_Remark`) REFERENCES `PROCESS_REMARK` (`Id_Process_Remark`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK698B98F4C94A0CBA` FOREIGN KEY EVIDENCE_Id_Evidence_Index (`EVIDENCE_Id_Evidence`) REFERENCES `EVIDENCE` (`Id_Evidence`);

--
-- Contraintes pour la table `NOMENCLATURE`
--
ALTER TABLE `NOMENCLATURE`
  ADD CONSTRAINT `FKBF856B7795431825` FOREIGN KEY Id_Nomenclature_Parent_Index (`Id_Nomenclature_Parent`) REFERENCES `NOMENCLATURE` (`Id_Nomenclature`);

--
-- Contraintes pour la table `NOMENCLATURE_ELEMENT`
--
ALTER TABLE `NOMENCLATURE_ELEMENT`
  ADD CONSTRAINT `FK44F856145FAB5EF2` FOREIGN KEY Id_Nomenclature_Index (`Id_Nomenclature`) REFERENCES `NOMENCLATURE` (`Id_Nomenclature`);

--
-- Contraintes pour la table `PROCESS_REMARK`
--
ALTER TABLE `PROCESS_REMARK`
  ADD CONSTRAINT `FK1C3EA37045A988AD` FOREIGN KEY Id_Process_Result_Index (`Id_Process_Result`) REFERENCES `PROCESS_RESULT` (`Id_Process_Result`) ON DELETE CASCADE;
CREATE INDEX Issue_Index ON PROCESS_REMARK (Issue);

--
-- Contraintes pour la table `PROCESS_RESULT`
--
ALTER TABLE `PROCESS_RESULT`
  ADD CONSTRAINT `FK1C41A80DB6D0E092` FOREIGN KEY Id_Audit_Net_Result_Index (`Id_Audit_Net_Result`) REFERENCES `AUDIT` (`Id_Audit`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK1C41A80D2E48600` FOREIGN KEY Id_Web_Resource_Index (`Id_Web_Resource`) REFERENCES `WEB_RESOURCE` (`Id_Web_Resource`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK1C41A80D8146180B` FOREIGN KEY Id_Audit_Gross_Result_Index (`Id_Audit_Gross_Result`) REFERENCES `AUDIT` (`Id_Audit`)ON DELETE CASCADE,
  ADD CONSTRAINT `FK1C41A80DA17A5FA8` FOREIGN KEY Id_Test_Index (`Id_Test`) REFERENCES `TEST` (`Id_Test`),
  ADD CONSTRAINT `FK1C41A80DFA349234` FOREIGN KEY Id_Process_Result_Parent_Index (`Id_Process_Result_Parent`) REFERENCES `PROCESS_RESULT` (`Id_Process_Result`);
CREATE INDEX Definite_Value_Index ON PROCESS_RESULT (Definite_Value);

--
-- Contraintes pour la table `RULE`
--
ALTER TABLE `RULE`
  ADD CONSTRAINT `FK268EFCADFA0F1` FOREIGN KEY Id_Rule_Package_Index (`Id_Rule_Package`) REFERENCES `RULE_PACKAGE` (`Id_Rule_Package`);

--
-- Contraintes pour la table `TEST`
--
ALTER TABLE `TEST`
  ADD CONSTRAINT `FK273C92CCA757AD` FOREIGN KEY Id_Decision_Level_Index (`Id_Decision_Level`) REFERENCES `DECISION_LEVEL` (`Id_Decision_Level`),
  ADD CONSTRAINT `FK273C921355BF7C` FOREIGN KEY Id_Rule_Index (`Id_Rule`) REFERENCES `RULE` (`Id_Rule`),
  ADD CONSTRAINT `FK273C9250C99824` FOREIGN KEY Id_Scope_Index (`Id_Scope`) REFERENCES `SCOPE` (`Id_Scope`),
  ADD CONSTRAINT `FK273C926CCA4C3E` FOREIGN KEY Id_Criterion_Index (`Id_Criterion`) REFERENCES `CRITERION` (`Id_Criterion`),
  ADD CONSTRAINT `FK273C9272343A84` FOREIGN KEY Id_Level_Index (`Id_Level`) REFERENCES `LEVEL` (`Id_Level`);

--
-- Index pour la table `THEME`
--
CREATE INDEX Cd_Theme_Index ON THEME(Cd_Theme);

--
-- Contraintes pour la table `WEB_RESOURCE`
--
ALTER TABLE `WEB_RESOURCE`
  ADD CONSTRAINT `FKD9A970B92F70FF12` FOREIGN KEY Id_Web_Resource_Parent_Index (`Id_Web_Resource_Parent`) REFERENCES `WEB_RESOURCE` (`Id_Web_Resource`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FKD9A970B9493EC9C2` FOREIGN KEY Id_Audit_Index (`Id_Audit`) REFERENCES `AUDIT` (`Id_Audit`) ON DELETE CASCADE ON UPDATE CASCADE;
CREATE INDEX Url_Index ON WEB_RESOURCE (Url);
CREATE INDEX DTYPE_Index ON WEB_RESOURCE (DTYPE);