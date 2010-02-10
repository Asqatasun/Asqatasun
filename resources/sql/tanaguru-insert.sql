-- MySQL dump 10.11
--
-- Host: localhost    Database: tanaguru
-- ------------------------------------------------------
-- Server version	5.0.51a-3ubuntu5.4

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `AUDIT`
--


--
-- Dumping data for table `AUDIT_TEST`
--


--
-- Dumping data for table `CONTENT`
--


--
-- Dumping data for table `CRITERION`
--


--
-- Dumping data for table `DECISION_LEVEL`
--


--
-- Dumping data for table `LEVEL`
--


--
-- Dumping data for table `NOMENCLATURE`
--

INSERT INTO `NOMENCLATURE` (`Id_Nomenclature`, `Cd_Nomenclature`, `Description`, `Long_Label`, `Short_Label`, `Id_Nomenclature_Parent`) VALUES (1,'FormsButtonTypes',NULL,NULL,NULL,NULL);
INSERT INTO `NOMENCLATURE` (`Id_Nomenclature`, `Cd_Nomenclature`, `Description`, `Long_Label`, `Short_Label`, `Id_Nomenclature_Parent`) VALUES (2,'JSWindowOpen',NULL,NULL,NULL,NULL);
INSERT INTO `NOMENCLATURE` (`Id_Nomenclature`, `Cd_Nomenclature`, `Description`, `Long_Label`, `Short_Label`, `Id_Nomenclature_Parent`) VALUES (3,'NotificationNewWindowWhitelist',NULL,NULL,NULL,NULL);
INSERT INTO `NOMENCLATURE` (`Id_Nomenclature`, `Cd_Nomenclature`, `Description`, `Long_Label`, `Short_Label`, `Id_Nomenclature_Parent`) VALUES (4,'FormsButtonWhitelist',NULL,NULL,NULL,NULL);
INSERT INTO `NOMENCLATURE` (`Id_Nomenclature`, `Cd_Nomenclature`, `Description`, `Long_Label`, `Short_Label`, `Id_Nomenclature_Parent`) VALUES (5,'FormsButtonBlacklist',NULL,NULL,NULL,NULL);
INSERT INTO `NOMENCLATURE` (`Id_Nomenclature`, `Cd_Nomenclature`, `Description`, `Long_Label`, `Short_Label`, `Id_Nomenclature_Parent`) VALUES (6,'LinkTextWhitelist',NULL,NULL,NULL,NULL);
INSERT INTO `NOMENCLATURE` (`Id_Nomenclature`, `Cd_Nomenclature`, `Description`, `Long_Label`, `Short_Label`, `Id_Nomenclature_Parent`) VALUES (7,'LinkTextBlacklist',NULL,NULL,NULL,NULL);
INSERT INTO `NOMENCLATURE` (`Id_Nomenclature`, `Cd_Nomenclature`, `Description`, `Long_Label`, `Short_Label`, `Id_Nomenclature_Parent`) VALUES (8,'PossibleImageTags',NULL,NULL,NULL,NULL);
INSERT INTO `NOMENCLATURE` (`Id_Nomenclature`, `Cd_Nomenclature`, `Description`, `Long_Label`, `Short_Label`, `Id_Nomenclature_Parent`) VALUES (9,'RelativeCssUnits',NULL,NULL,NULL,NULL);
INSERT INTO `NOMENCLATURE` (`Id_Nomenclature`, `Cd_Nomenclature`, `Description`, `Long_Label`, `Short_Label`, `Id_Nomenclature_Parent`) VALUES (10,'ValidLanguageCode',NULL,NULL,NULL,NULL);

--
-- Dumping data for table `NOMENCLATURE_ELEMENT`
--

INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',1,'reset',NULL,1);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',2,'submit',NULL,1);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',3,'button',NULL,1);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',4,'window.open',NULL,2);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',5,'javascript:window.open',NULL,2);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',6,'new window',NULL,3);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',7,'nouvelle fenêtre',NULL,3);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',8,'s\'inscrire',NULL,5);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',9,'go',NULL,5);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',10,'inscrire',NULL,5);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',11,'valider',NULL,5);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',12,'envoyer',NULL,5);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',13,'submit',NULL,5);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',14,'poster',NULL,5);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',15,'retour à l\'accueil',NULL,6);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',16,'back home',NULL,6);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',17,'here',NULL,7);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',18,'this link',NULL,7);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',19,'there',NULL,7);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',20,'en savoir plus...',NULL,7);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',21,'more',NULL,7);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',22,'en savoir plus',NULL,7);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',23,'ici',NULL,7);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',24,'plus',NULL,7);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',25,'là',NULL,7);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',26,'cliquez ici',NULL,7);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',27,'ce lien',NULL,7);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',28,'post précédent',NULL,7);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',29,'lire la suite',NULL,7);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',30,'click here',NULL,7);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',31,'object',NULL,8);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',32,'img',NULL,8);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureCssUnitImpl',33,'pc',22,9);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureCssUnitImpl',34,'cm',19,9);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureCssUnitImpl',35,'in',18,9);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureCssUnitImpl',36,'pt',21,9);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureCssUnitImpl',37,'mm',20,9);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',38,'kk',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',39,'th',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',40,'cs',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',41,'vi',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',42,'hi',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',43,'ur',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',44,'af',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',45,'tl',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',46,'bh',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',47,'ta',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',48,'zh',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',49,'mo',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',50,'sh',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',51,'fy',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',52,'bn',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',53,'sw',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',54,'sd',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',55,'mi',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',56,'la',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',57,'gv',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',58,'no',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',59,'or',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',60,'tk',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',61,'in',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',62,'ko',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',63,'et',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',64,'aa',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',65,'ja',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',66,'rm',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',67,'ks',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',68,'ca',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',69,'sn',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',70,'en',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',71,'nl',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',72,'ss',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',73,'ab',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',74,'tg',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',75,'st',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',76,'da',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',77,'bo',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',78,'sg',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',79,'na',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',80,'gl',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',81,'si',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',82,'rw',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',83,'uz',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',84,'te',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',85,'kl',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',86,'el',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',87,'ba',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',88,'tn',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',89,'br',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',90,'ky',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',91,'ps',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',92,'es',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',93,'zh',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',94,'sr',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',95,'mk',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',96,'qu',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',97,'ka',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',98,'fr',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',99,'ne',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',100,'he',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',101,'co',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',102,'uk',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',103,'ga',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',104,'tt',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',105,'ms',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',106,'pt',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',107,'bg',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',108,'ku',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',109,'to',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',110,'so',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',111,'ln',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',112,'zu',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',113,'fj',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',114,'sk',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',115,'is',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',116,'ik',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',117,'xh',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',118,'ro',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',119,'yi',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',120,'be',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',121,'ar',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',122,'fi',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',123,'my',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',124,'gn',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',125,'dz',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',126,'su',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',127,'hu',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',128,'mt',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',129,'lt',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',130,'de',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',131,'pl',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',132,'tr',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',133,'kn',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',134,'cy',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',135,'mg',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',136,'tw',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',137,'hr',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',138,'hy',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',139,'wo',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',140,'li',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',141,'ti',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',142,'eu',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',143,'ia',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',144,'sv',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',145,'iu',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',146,'gu',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',147,'gd',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',148,'oc',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',149,'fo',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',150,'lv',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',151,'as',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',152,'sm',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',153,'ug',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',154,'sa',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',155,'sl',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',156,'yo',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',157,'mr',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',158,'vo',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',159,'sq',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',160,'fa',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',161,'id',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',162,'ru',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',163,'az',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',164,'ts',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',165,'km',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',166,'rn',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',167,'it',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',168,'ml',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',169,'mn',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',170,'ha',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',171,'om',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',172,'am',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',173,'bi',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',174,'ji',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',175,'iw',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',176,'jv',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',177,'ay',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',178,'eo',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',179,'pa',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',180,'ie',NULL,10);
INSERT INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES ('NomenclatureElementImpl',181,'lo',NULL,10);

--
-- Dumping data for table `PROCESS_REMARK`
--


--
-- Dumping data for table `PROCESS_RESULT`
--


--
-- Dumping data for table `REFERENCE`
--


--
-- Dumping data for table `RULE`
--


--
-- Dumping data for table `RULE_PACKAGE`
--


--
-- Dumping data for table `SCOPE`
--


--
-- Dumping data for table `STANDARD_MESSAGE`
--

INSERT INTO `STANDARD_MESSAGE` (`Id_Standard_Message`, `Cd_Standard_Message`, `Label`, `Text`) VALUES (1,'AttributeMissing',NULL,'The attribute is missing');
INSERT INTO `STANDARD_MESSAGE` (`Id_Standard_Message`, `Cd_Standard_Message`, `Label`, `Text`) VALUES (2,'BlackListedValue',NULL,'The value is blacklisted');
INSERT INTO `STANDARD_MESSAGE` (`Id_Standard_Message`, `Cd_Standard_Message`, `Label`, `Text`) VALUES (3,'VerifyValue',NULL,'The value needs verification');
INSERT INTO `STANDARD_MESSAGE` (`Id_Standard_Message`, `Cd_Standard_Message`, `Label`, `Text`) VALUES (4,'ChildNodeMissing',NULL,'The child node is missing');
INSERT INTO `STANDARD_MESSAGE` (`Id_Standard_Message`, `Cd_Standard_Message`, `Label`, `Text`) VALUES (5,'ValueEmpty',NULL,'The value is empty');
INSERT INTO `STANDARD_MESSAGE` (`Id_Standard_Message`, `Cd_Standard_Message`, `Label`, `Text`) VALUES (6,'NotMatchExpression',NULL,'Regular expression not match');
INSERT INTO `STANDARD_MESSAGE` (`Id_Standard_Message`, `Cd_Standard_Message`, `Label`, `Text`) VALUES (7,'LengthTooLong',NULL,'Length too long');
INSERT INTO `STANDARD_MESSAGE` (`Id_Standard_Message`, `Cd_Standard_Message`, `Label`, `Text`) VALUES (8,'AccessKeyNotMatch',NULL,'AccessKey not match');
INSERT INTO `STANDARD_MESSAGE` (`Id_Standard_Message`, `Cd_Standard_Message`, `Label`, `Text`) VALUES (9,'BadUnitType',NULL,'Bad unit type');
INSERT INTO `STANDARD_MESSAGE` (`Id_Standard_Message`, `Cd_Standard_Message`, `Label`, `Text`) VALUES (10,'InvalidLanguageCode',NULL,'Invalid language code');

--
-- Dumping data for table `TEST`
--

INSERT INTO `TEST` (`Id_Test`, `Cd_Test`, `Description`, `Label`, `Rank`, `Rule_Archive_Name`, `Rule_Class_Name`, `Id_Criterion`, `Id_Decision_Level`, `Id_Level`, `Id_Rule`, `Id_Scope`) VALUES (1,'0101a',NULL,NULL,1,'AccessiWeb','org.opens.tanaguru.rule.accessiweb.Rule0101a',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `TEST` (`Id_Test`, `Cd_Test`, `Description`, `Label`, `Rank`, `Rule_Archive_Name`, `Rule_Class_Name`, `Id_Criterion`, `Id_Decision_Level`, `Id_Level`, `Id_Rule`, `Id_Scope`) VALUES (2,'0101b',NULL,NULL,2,'AccessiWeb','org.opens.tanaguru.rule.accessiweb.Rule0101b',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `TEST` (`Id_Test`, `Cd_Test`, `Description`, `Label`, `Rank`, `Rule_Archive_Name`, `Rule_Class_Name`, `Id_Criterion`, `Id_Decision_Level`, `Id_Level`, `Id_Rule`, `Id_Scope`) VALUES (3,'0101e',NULL,NULL,3,'AccessiWeb','org.opens.tanaguru.rule.accessiweb.Rule0101e',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `TEST` (`Id_Test`, `Cd_Test`, `Description`, `Label`, `Rank`, `Rule_Archive_Name`, `Rule_Class_Name`, `Id_Criterion`, `Id_Decision_Level`, `Id_Level`, `Id_Rule`, `Id_Scope`) VALUES (4,'0105b',NULL,NULL,4,'AccessiWeb','org.opens.tanaguru.rule.accessiweb.Rule0105b',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `TEST` (`Id_Test`, `Cd_Test`, `Description`, `Label`, `Rank`, `Rule_Archive_Name`, `Rule_Class_Name`, `Id_Criterion`, `Id_Decision_Level`, `Id_Level`, `Id_Rule`, `Id_Scope`) VALUES (5,'0204a',NULL,NULL,5,'AccessiWeb','org.opens.tanaguru.rule.accessiweb.Rule0204a',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `TEST` (`Id_Test`, `Cd_Test`, `Description`, `Label`, `Rank`, `Rule_Archive_Name`, `Rule_Class_Name`, `Id_Criterion`, `Id_Decision_Level`, `Id_Level`, `Id_Rule`, `Id_Scope`) VALUES (6,'0601a',NULL,NULL,6,'AccessiWeb','org.opens.tanaguru.rule.accessiweb.Rule0601a',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `TEST` (`Id_Test`, `Cd_Test`, `Description`, `Label`, `Rank`, `Rule_Archive_Name`, `Rule_Class_Name`, `Id_Criterion`, `Id_Decision_Level`, `Id_Level`, `Id_Rule`, `Id_Scope`) VALUES (7,'0601b',NULL,NULL,7,'AccessiWeb','org.opens.tanaguru.rule.accessiweb.Rule0601b',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `TEST` (`Id_Test`, `Cd_Test`, `Description`, `Label`, `Rank`, `Rule_Archive_Name`, `Rule_Class_Name`, `Id_Criterion`, `Id_Decision_Level`, `Id_Level`, `Id_Rule`, `Id_Scope`) VALUES (8,'0602a',NULL,NULL,8,'AccessiWeb','org.opens.tanaguru.rule.accessiweb.Rule0602a',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `TEST` (`Id_Test`, `Cd_Test`, `Description`, `Label`, `Rank`, `Rule_Archive_Name`, `Rule_Class_Name`, `Id_Criterion`, `Id_Decision_Level`, `Id_Level`, `Id_Rule`, `Id_Scope`) VALUES (9,'0604d',NULL,NULL,9,'AccessiWeb','org.opens.tanaguru.rule.accessiweb.Rule0604d',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `TEST` (`Id_Test`, `Cd_Test`, `Description`, `Label`, `Rank`, `Rule_Archive_Name`, `Rule_Class_Name`, `Id_Criterion`, `Id_Decision_Level`, `Id_Level`, `Id_Rule`, `Id_Scope`) VALUES (10,'0605c',NULL,NULL,10,'AccessiWeb','org.opens.tanaguru.rule.accessiweb.Rule0605c',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `TEST` (`Id_Test`, `Cd_Test`, `Description`, `Label`, `Rank`, `Rule_Archive_Name`, `Rule_Class_Name`, `Id_Criterion`, `Id_Decision_Level`, `Id_Level`, `Id_Rule`, `Id_Scope`) VALUES (11,'0804a',NULL,NULL,11,'AccessiWeb','org.opens.tanaguru.rule.accessiweb.Rule0804a',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `TEST` (`Id_Test`, `Cd_Test`, `Description`, `Label`, `Rank`, `Rule_Archive_Name`, `Rule_Class_Name`, `Id_Criterion`, `Id_Decision_Level`, `Id_Level`, `Id_Rule`, `Id_Scope`) VALUES (12,'0807a',NULL,NULL,12,'AccessiWeb','org.opens.tanaguru.rule.accessiweb.Rule0807a',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `TEST` (`Id_Test`, `Cd_Test`, `Description`, `Label`, `Rank`, `Rule_Archive_Name`, `Rule_Class_Name`, `Id_Criterion`, `Id_Decision_Level`, `Id_Level`, `Id_Rule`, `Id_Scope`) VALUES (13,'1001a',NULL,NULL,13,'AccessiWeb','org.opens.tanaguru.rule.accessiweb.Rule1001a',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `TEST` (`Id_Test`, `Cd_Test`, `Description`, `Label`, `Rank`, `Rule_Archive_Name`, `Rule_Class_Name`, `Id_Criterion`, `Id_Decision_Level`, `Id_Level`, `Id_Rule`, `Id_Scope`) VALUES (14,'1005a',NULL,NULL,14,'AccessiWeb','org.opens.tanaguru.rule.accessiweb.Rule1005a',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `TEST` (`Id_Test`, `Cd_Test`, `Description`, `Label`, `Rank`, `Rule_Archive_Name`, `Rule_Class_Name`, `Id_Criterion`, `Id_Decision_Level`, `Id_Level`, `Id_Rule`, `Id_Scope`) VALUES (15,'1109a',NULL,NULL,15,'AccessiWeb','org.opens.tanaguru.rule.accessiweb.Rule1109a',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `TEST` (`Id_Test`, `Cd_Test`, `Description`, `Label`, `Rank`, `Rule_Archive_Name`, `Rule_Class_Name`, `Id_Criterion`, `Id_Decision_Level`, `Id_Level`, `Id_Rule`, `Id_Scope`) VALUES (16,'1203c',NULL,NULL,16,'AccessiWeb','org.opens.tanaguru.rule.accessiweb.Rule1203c',NULL,NULL,NULL,NULL,NULL);
INSERT INTO `TEST` (`Id_Test`, `Cd_Test`, `Description`, `Label`, `Rank`, `Rule_Archive_Name`, `Rule_Class_Name`, `Id_Criterion`, `Id_Decision_Level`, `Id_Level`, `Id_Rule`, `Id_Scope`) VALUES (17,'1303a',NULL,NULL,17,'AccessiWeb','org.opens.tanaguru.rule.accessiweb.Rule1303a',NULL,NULL,NULL,NULL,NULL);

--
-- Dumping data for table `THEME`
--


--
-- Dumping data for table `WEB_RESOURCE`
--

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-01-28 16:54:51
