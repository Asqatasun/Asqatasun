USE tanaguru;

-- MySQL dump 10.11
--
-- Host: localhost    Database: tanaguru
-- ------------------------------------------------------
-- Server version	5.0.51a-3ubuntu5.4
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO,NO_KEY_OPTIONS,NO_TABLE_OPTIONS' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `AUDIT`
--

CREATE TABLE `AUDIT` (
  `Id_Audit` bigint(20) NOT NULL auto_increment,
  `Comment` varchar(255) default NULL,
  `Dt_Creation` datetime default NULL,
  `Mark` float default NULL,
  `Status` varchar(255) default NULL,
  PRIMARY KEY  (`Id_Audit`)
) DEFAULT CHARACTER SET utf8;

--
-- Table structure for table `AUDIT_TEST`
--

CREATE TABLE `AUDIT_TEST` (
  `Id_Audit` bigint(20) NOT NULL,
  `Id_Test` bigint(20) NOT NULL,
  KEY `FK838E6E96493EC9C2` (`Id_Audit`),
  KEY `FK838E6E96A17A5FA8` (`Id_Test`)
) DEFAULT CHARACTER SET utf8;

--
-- Table structure for table `CONTENT`
--

CREATE TABLE `CONTENT` (
  `DTYPE` varchar(31) NOT NULL,
  `Id_Content` bigint(20) NOT NULL auto_increment,
  `Dt_Loading` datetime NOT NULL,
  `Uri` mediumtext NOT NULL,
  `Source` longtext default NULL,
  `Adapted_Content` longtext default NULL,
  `Doctype` mediumtext default NULL,
  `Charset` tinytext default NULL,
  `Binary_Content` longblob default NULL,
  `Http_Status_Code` int(11) NOT NULL,
  `Id_Audit` bigint(20) NOT NULL,
  `Id_Page` bigint(20) default NULL,
  PRIMARY KEY  (`Id_Content`),
  KEY `FK6382C059493EC9C2` (`Id_Audit`),
  KEY `FK6382C059A8A177A1` (`Id_Page`)
) DEFAULT CHARACTER SET utf8;

--
-- Table structure for table `CONTENT_RELATIONSHIP`
--
CREATE TABLE `CONTENT_RELATIONSHIP` (
`Id` bigint(20) NOT NULL AUTO_increment,
`Id_Content_Parent` bigint(20) NOT NULL,
`Id_Content_Child` bigint(20) NOT NULL,
PRIMARY KEY (`Id`),
FOREIGN KEY (`Id_Content_Parent`) REFERENCES CONTENT(`Id_Content`),
FOREIGN KEY (`Id_Content_Child`) REFERENCES CONTENT(`Id_Content`)
) DEFAULT CHARACTER SET utf8;

--
-- Table structure for table `CRITERION`
--

CREATE TABLE `CRITERION` (
  `Id_Criterion` bigint(20) NOT NULL auto_increment,
  `Reference_Id_Reference` bigint(20) default NULL,
  `Theme_Id_Theme` bigint(20) default NULL,
  `Cd_Criterion` varchar(255) default NULL,
  `Description` varchar(255) default NULL,
  `Label` varchar(255) default NULL,
  `Url` varchar(255) default NULL,
  `Rank` int(11) default NULL,
  PRIMARY KEY  (`Id_Criterion`),
  KEY `FKBCFA1E81DA004892` (`Reference_Id_Reference`),
  KEY `FKBCFA1E81D667448E` (`Theme_Id_Theme`)
) DEFAULT CHARACTER SET utf8;

--
-- Table structure for table `DECISION_LEVEL`
--

CREATE TABLE `DECISION_LEVEL` (
  `Id_Decision_Level` bigint(20) NOT NULL auto_increment,
  `Cd_Decision_Level` varchar(255) default NULL,
  `Description` varchar(255) default NULL,
  `Label` varchar(255) NOT NULL,
  PRIMARY KEY  (`Id_Decision_Level`)
) DEFAULT CHARACTER SET utf8;

--
-- Table structure for table `LEVEL`
--

CREATE TABLE `LEVEL` (
  `Id_Level` bigint(20) NOT NULL auto_increment,
  `Cd_Level` varchar(255) default NULL,
  `Description` varchar(255) default NULL,
  `Label` varchar(255) default NULL,
  `Rank` int(11) default NULL,
  PRIMARY KEY  (`Id_Level`)
) DEFAULT CHARACTER SET utf8;

--
-- Table structure for table `NOMENCLATURE`
--
CREATE TABLE `NOMENCLATURE` (
  `Id_Nomenclature` bigint(20) NOT NULL auto_increment,
  `Cd_Nomenclature` varchar(255) default NULL,
  `Description` varchar(255) default NULL,
  `Long_Label` varchar(255) default NULL,
  `Short_Label` varchar(255) default NULL,
  `Id_Nomenclature_Parent` bigint(20) default NULL,
  PRIMARY KEY  (`Id_Nomenclature`),
  KEY `FKBF856B7795431825` (`Id_Nomenclature_Parent`)
) DEFAULT CHARACTER SET utf8;

--
-- Table structure for table `NOMENCLATURE_ELEMENT`
--
CREATE TABLE `NOMENCLATURE_ELEMENT` (
  `DTYPE` varchar(31) NOT NULL,
  `Id_Nomenclature_Element` bigint(20) NOT NULL auto_increment,
  `Label` varchar(255) NOT NULL,
  `shortValue` int(11) default NULL,
  `Id_Nomenclature` bigint(20) default NULL,
  PRIMARY KEY  (`Id_Nomenclature_Element`),
  KEY `FK44F856145FAB5EF2` (`Id_Nomenclature`)
) DEFAULT CHARACTER SET utf8;

--
-- Table structure for table `EVIDENCE`
--
CREATE TABLE `EVIDENCE` (
  `Id_Evidence` bigint(20) NOT NULL auto_increment,
  `Cd_Evidence` varchar(255) default NULL,
  `Description` varchar(255) default NULL,
  `Long_Label` varchar(255) default NULL,
  PRIMARY KEY  (`Id_Evidence`)
) DEFAULT CHARACTER SET utf8;

--
-- Table structure for table `EVIDENCE_ELEMENT`
--
CREATE TABLE `EVIDENCE_ELEMENT` (
  `DTYPE` varchar(31) NOT NULL,
  `Id_Evidence_Element` bigint(20) NOT NULL auto_increment,
  `Label` varchar(255) NOT NULL,
  `EVIDENCE_Id_Evidence` bigint(20) default NULL,
  `PROCESS_REMARK_Id_Process_Remark` bigint(20) default NULL,
  PRIMARY KEY  (`Id_Evidence_Element`),
  KEY `FK_EVIDENCE_ELEMENT_PROCESS_REMARK` (`PROCESS_REMARK_Id_Process_Remark`),
  KEY `FK_EVIDENCE_ELEMENT_EVIDENCE` (`EVIDENCE_Id_Evidence`)
) DEFAULT CHARACTER SET utf8;

--
-- Table structure for table `PROCESS_REMARK`
--

CREATE TABLE `PROCESS_REMARK` (
  `DTYPE` varchar(31) NOT NULL,
  `Id_Process_Remark` bigint(20) NOT NULL auto_increment,
  `Issue` varchar(255) default NULL,
  `Message_Code` varchar(255) default NULL,
  `Selected_Element` varchar(255) default NULL,
  `Selection_Expression` varchar(255) default NULL,
  `Character_Position` int(11) default NULL,
  `Line_Number` int(11) default NULL,
  `Target` mediumtext default NULL,
  `Id_Process_Result` bigint(20) default NULL,
  PRIMARY KEY  (`Id_Process_Remark`),
  KEY `FK1C3EA37045A988AD` (`Id_Process_Result`)
) DEFAULT CHARACTER SET utf8;

--
-- Table structure for table `PROCESS_RESULT`
--
CREATE TABLE `PROCESS_RESULT` (
  `DTYPE` varchar(31) NOT NULL,
  `Id_Process_Result` bigint(20) NOT NULL auto_increment,
  `Definite_Value` varchar(255) default NULL,
  `Element_Counter` int(20) default NULL,
  `Indefinite_Value` longtext,
  `Id_Audit_Gross_Result` bigint(20) default NULL,
  `Id_Audit_Net_Result` bigint(20) default NULL,
  `Id_Process_Result_Parent` bigint(20) default NULL,
  `Id_Web_Resource` bigint(20) NOT NULL,
  `Id_Test` bigint(20) default NULL,
  PRIMARY KEY  (`Id_Process_Result`),
  UNIQUE KEY `Id_Test` (`Id_Test`,`Id_Web_Resource`,`Id_Audit_Gross_Result`),
  UNIQUE KEY `Id_Test_2` (`Id_Test`,`Id_Web_Resource`,`Id_Audit_Net_Result`),
  KEY `FK1C41A80DFA349234` (`Id_Process_Result_Parent`),
  KEY `FK1C41A80D8146180B` (`Id_Audit_Gross_Result`),
  KEY `FK1C41A80D2E48600` (`Id_Web_Resource`),
  KEY `FK1C41A80DA17A5FA8` (`Id_Test`),
  KEY `FK1C41A80DB6D0E092` (`Id_Audit_Net_Result`)
) DEFAULT CHARACTER SET utf8;

--
-- Table structure for table `REFERENCE`
--
CREATE TABLE `REFERENCE` (
  `Id_Reference` bigint(20) NOT NULL auto_increment,
  `Cd_Reference` varchar(255) default NULL,
  `Description` varchar(255) default NULL,
  `Label` varchar(255) NOT NULL,
  `Url` varchar(255) NOT NULL,
  `Rank` int(11) default NULL,
  PRIMARY KEY  (`Id_Reference`)
) DEFAULT CHARACTER SET utf8;

--
-- Table structure for table `RULE`
--
CREATE TABLE `RULE` (
  `Id_Rule` bigint(20) NOT NULL auto_increment,
  `Class_Name` varchar(255) NOT NULL,
  `Description` varchar(255) default NULL,
  `Id_Rule_Package` bigint(20) default NULL,
  PRIMARY KEY  (`Id_Rule`),
  KEY `FK268EFCADFA0F1` (`Id_Rule_Package`)
) DEFAULT CHARACTER SET utf8;

--
-- Table structure for table `RULE_PACKAGE`
--
CREATE TABLE `RULE_PACKAGE` (
  `Id_Rule_Package` bigint(20) NOT NULL auto_increment,
  `Description` varchar(255) default NULL,
  `Package_Name` varchar(255) default NULL,
  PRIMARY KEY  (`Id_Rule_Package`)
) DEFAULT CHARACTER SET utf8;

--
-- Table structure for table `SCOPE`
--

CREATE TABLE `SCOPE` (
  `Id_Scope` bigint(20) NOT NULL auto_increment,
  `Code` varchar(255) default NULL,
  `Description` varchar(255) default NULL,
  `Label` varchar(255) NOT NULL,
  PRIMARY KEY  (`Id_Scope`)
) DEFAULT CHARACTER SET utf8;

--
-- Table structure for table `STANDARD_MESSAGE`
--

CREATE TABLE `STANDARD_MESSAGE` (
  `Id_Standard_Message` bigint(20) NOT NULL auto_increment,
  `Cd_Standard_Message` varchar(255) default NULL,
  `Label` varchar(255) default NULL,
  `Text` varchar(255) default NULL,
  PRIMARY KEY  (`Id_Standard_Message`)
) DEFAULT CHARACTER SET utf8;

--
-- Table structure for table `TEST`
--

CREATE TABLE `TEST` (
  `Id_Test` bigint(20) NOT NULL auto_increment,
  `Cd_Test` varchar(255) default NULL,
  `Description` varchar(255) default NULL,
  `Label` varchar(255) default NULL,
  `Rank` int(11) default NULL,
  `Rule_Archive_Name` varchar(255) default NULL,
  `Rule_Class_Name` varchar(255) default NULL,
  `Id_Criterion` bigint(20) default NULL,
  `Id_Decision_Level` bigint(20) default NULL,
  `Id_Level` bigint(20) default NULL,
  `Id_Rule` bigint(20) default NULL,
  `Id_Scope` bigint(20) default NULL,
  PRIMARY KEY  (`Id_Test`),
  KEY `FK273C9250C99824` (`Id_Scope`),
  KEY `FK273C9272343A84` (`Id_Level`),
  KEY `FK273C926CCA4C3E` (`Id_Criterion`),
  KEY `FK273C921355BF7C` (`Id_Rule`),
  KEY `FK273C92CCA757AD` (`Id_Decision_Level`)
) DEFAULT CHARACTER SET utf8;

--
-- Table structure for table `THEME`
--

CREATE TABLE `THEME` (
  `Id_Theme` bigint(20) NOT NULL auto_increment,
  `Cd_Theme` varchar(255) default NULL,
  `Description` varchar(255) default NULL,
  `Label` varchar(255) NOT NULL,
  `Rank` int(11) default NULL,
  PRIMARY KEY  (`Id_Theme`)
) DEFAULT CHARACTER SET utf8;

--
-- Table structure for table `WEB_RESOURCE`
--

CREATE TABLE `WEB_RESOURCE` (
  `DTYPE` varchar(31) NOT NULL,
  `Id_Web_Resource` bigint(20) NOT NULL auto_increment,
  `Label` varchar(255) default NULL,
  `Url` varchar(255) NOT NULL,
  `Id_Audit` bigint(20) default NULL,
  `Id_Web_Resource_Parent` bigint(20) default NULL,
  PRIMARY KEY  (`Id_Web_Resource`),
  KEY `FKD9A970B9493EC9C2` (`Id_Audit`),
  KEY `FKD9A970B92F70FF12` (`Id_Web_Resource_Parent`)
) DEFAULT CHARACTER SET utf8;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-01-28 16:54:51
