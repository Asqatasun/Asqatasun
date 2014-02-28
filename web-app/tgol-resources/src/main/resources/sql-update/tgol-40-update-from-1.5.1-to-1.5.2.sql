-- -----------------------------------------------------
-- Add unique pair index on TGSI_ACT_WEB_RESOURCE TABLE
-- -----------------------------------------------------
ALTER TABLE TGSI_ACT_WEB_RESOURCE ADD UNIQUE INDEX `INDEX_UNIQUE_PAIR` (`ACT_Id_Act`, `WEB_RESOURCE_Id_Web_Resource`);

-- -----------------------------------------------------
-- Table `TGSI_FUNCTIONALITY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TGSI_FUNCTIONALITY` (
  `Id_Functionality` bigint(20) NOT NULL AUTO_INCREMENT ,
  `Code` VARCHAR(45) NOT NULL ,
  `Label` VARCHAR(255) NULL ,
  `Description` VARCHAR(1000) NULL ,
  PRIMARY KEY (`Id_Functionality`) ,
  UNIQUE INDEX `UNIQUE_INDEX_TGSI_FUNCTIONALITY_Code` (`Code` ASC) 
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

-- -----------------------------------------------------
-- Table `TGSI_REFERENCE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TGSI_REFERENTIAL` (
  `Id_Referential` bigint(20) NOT NULL AUTO_INCREMENT,
  `Code` varchar(45) NOT NULL,
  `Label` varchar(255) DEFAULT NULL,
  `Description` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`Id_Referential`),
  UNIQUE INDEX `UNIQUE_INDEX_TGSI_REFERENTIAL_Code` (`Code` ASC)
) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8 
COLLATE = utf8_general_ci;

-- -----------------------------------------------------
-- Table `TGSI_SCENARIO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TGSI_SCENARIO` (
  `Id_Scenario` bigint(20) NOT NULL AUTO_INCREMENT,
  `Date_Of_Creation` DATETIME NOT NULL ,
  `Label` varchar(255) NOT NULL,
  `Content` mediumtext NOT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `CONTRACT_Id_Contract` bigint(20) NOT NULL, 
  PRIMARY KEY (`Id_Scenario`), 
  INDEX `INDEX_TGSI_SCENARIO_CONTRACT_Id_Contract` (`CONTRACT_Id_Contract` ASC),
  CONSTRAINT `FK_TGSI_SCENARIO_TGSI_CONTRACT`
    FOREIGN KEY (`CONTRACT_Id_Contract` )
    REFERENCES `TGSI_CONTRACT` (`Id_Contract` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION
) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8 
COLLATE = utf8_general_ci;

-- -----------------------------------------------------
-- Table `TGSI_OPTION_FAMILY`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `TGSI_OPTION_FAMILY` (
  `Id_Option_Family` bigint(20) NOT NULL AUTO_INCREMENT ,
  `Code` VARCHAR(45) NOT NULL ,
  `Label` VARCHAR(255) NULL ,
  `Description` VARCHAR(2048) NULL,
  PRIMARY KEY (`Id_Option_Family`) ,
  UNIQUE INDEX `UNIQUE_INDEX_TGSI_OPTION_FAMILY_Code` (`Code` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

-- -----------------------------------------------------
-- Table `TGSI_OPTION`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `TGSI_OPTION` (
  `Id_Option` bigint(20) NOT NULL AUTO_INCREMENT ,
  `Code` VARCHAR(45) NOT NULL ,
  `Label` VARCHAR(255) NULL ,
  `Description` VARCHAR(2048) NULL ,
  `Is_Restriction` bit(1) DEFAULT b'1',
  `OPTION_FAMILY_Id_Option_Family` bigint(20) NOT NULL ,
  PRIMARY KEY (`Id_Option`) ,
  UNIQUE INDEX `UNIQUE_INDEX_TGSI_OPTION_Code` (`Code` ASC), 
  CONSTRAINT `FK_TGSI_OPTION_TGSI_OPTION_FAMILY`
    FOREIGN KEY (`OPTION_FAMILY_Id_Option_Family` )
    REFERENCES `TGSI_OPTION_FAMILY` (`Id_Option_Family` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

-- -----------------------------------------------------
-- Table `TGSI_OPTION_ELEMENT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TGSI_OPTION_ELEMENT` (
  `Id_Option_Element` bigint(20) NOT NULL AUTO_INCREMENT ,
  `OPTION_Id_Option` bigint(20) NOT NULL ,
  `Value` VARCHAR(255) NULL ,
  PRIMARY KEY (`Id_Option_Element`) ,
  UNIQUE INDEX `INDEX_UNIQUE_PAIR` (`OPTION_Id_Option`, `Value`),
  CONSTRAINT `FK_TGSI_OPTION_ELEMENT_TGSI_OPTION`
    FOREIGN KEY (`OPTION_Id_Option` )
    REFERENCES `TGSI_OPTION` (`Id_Option` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

INSERT IGNORE INTO `TGSI_FUNCTIONALITY` (`Id_Functionality`, `Code`, `Label`, `Description`) VALUES
(1, 'PAGES', 'Audit Pages (Up to 10)', ''),
(2, 'DOMAIN', 'Audit Domain ', ''),
(3, 'UPLOAD', 'Audit Upload', '');

INSERT IGNORE INTO `TGSI_REFERENTIAL` (`Id_Referential`, `Code`, `Label`) VALUES
(1, 'AW21', 'Accessiweb 2.1 referential');

INSERT IGNORE INTO `TGSI_OPTION_FAMILY` (`Id_Option_Family`, `Code`, `Label`) VALUES
(1, 'CRAWL', ''),
(2, 'ACT_RESTRICTION', ''),
(3, 'ACT_MANAGEMENT', ''),
(4, 'CONTRACT_MANAGEMENT', '');

INSERT IGNORE INTO `TGSI_OPTION` (`Id_Option`, `Code`, `Description`, `Is_Restriction`, OPTION_FAMILY_Id_Option_Family) VALUES
(1, 'ACT_LIMITATION', 'The act limitation unit is the number of acts', b'1', 2),
(2, 'ACT_BY_IP_LIMITATION', 'The act by ip limitation unit is the number of acts in a period for a given ip.
The period is expressed in seconds and the format is \"nb_of_acts/period\"', b'1', 2),
(3, 'MAX_DOCUMENTS', 'This restriction limits the max number of crawled documents',b'1', 1),
(4, 'FORDIDDEN_REFERENTIAL', 'This restriction forbids the access to a referential', b'1', 1),
(5, 'DEPTH', 'This restriction limits the depth of the crawl', b'1', 1),
(6, 'MAX_DURATION', 'This restriction limits the duration of the crawl', b'1', 1),
(7, 'EXCLUSION_REGEXP', 'This restriction applies an exclusion rule on crawled Urls', b'1', 1),
(8, 'ACT_LIFETIME','This restriction determines the lifetime of each associated with the contract', b'1', 3),
(9, 'NB_OF_AUDIT_TO_DISPLAY','This restriction determines the number of audit results that can be displayed on the contract page', b'1', 4), 
(10,'DOMAIN', 'Domain associated with a contract', b'1', 4);


-- ---------------------------------------------------------------
-- Copy content of TGSI_RESTRICTION_ELEMENT TO TGSI_OPTION_ELEMENT
-- ---------------------------------------------------------------
-- INSERT INTO TGSI_OPTION (Id_Option, Code, Description)
-- SELECT Id_Restriction_Element, Cd_Restriction_Element, Description FROM TGSI_RESTRICTION_ELEMENT;

-- ---------------------------------------------------------------
-- Copy content of TGSI_RESTRICTION TO TGSI_OPTION
-- ---------------------------------------------------------------
-- INSERT INTO TGSI_OPTION_ELEMENT (Id_Option_Element, OPTION_Id_Option, Value)
-- SELECT Id_Restriction, Id_Restriction_Element, Restriction_Value FROM TGSI_RESTRICTION;

-- -----------------------------------------------------
-- Table `TGSI_CONTRACT_OPTION_ELEMENT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TGSI_CONTRACT_OPTION_ELEMENT` (
  `OPTION_ELEMENT_Id_Option_Element` bigint(20) NOT NULL ,
  `CONTRACT_Id_Contract` bigint(20) NOT NULL ,
  UNIQUE INDEX `INDEX_UNIQUE_PAIR` (`OPTION_ELEMENT_Id_Option_Element`, `CONTRACT_Id_Contract`),
  CONSTRAINT `FK_TGSI_CONTRACT_OPTION_ELEMENT_TGSI_CONTRACT`
    FOREIGN KEY (`CONTRACT_Id_Contract` )
    REFERENCES `TGSI_CONTRACT` (`Id_Contract` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_TGSI_CONTRACT_OPTION_ELEMENT_TGSI_OPTION_ELEMENT`
    FOREIGN KEY (`OPTION_ELEMENT_Id_Option_Element` )
    REFERENCES `TGSI_OPTION_ELEMENT` (`Id_Option_Element` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

-- -----------------------------------------------------
-- Table `TGSI_CONTRACT_FUNCTIONALITY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TGSI_CONTRACT_FUNCTIONALITY` (
  `CONTRACT_Id_Contract` bigint(20) NOT NULL ,
  `FUNCTIONALITY_Id_Functionality` bigint(20) NOT NULL ,
  INDEX `INDEX_TGSI_CONTRACT_FUNCTIONALITY_CONTRACT_Id_Contract` (`CONTRACT_Id_Contract` ASC) ,
  INDEX `INDEX_TGSI_CONTRACT_FUNCTIONALITY_FUNCTIONALITY_Id_Functionality` (`FUNCTIONALITY_Id_Functionality` ASC) ,
  UNIQUE INDEX `INDEX_UNIQUE_PAIR` (`FUNCTIONALITY_Id_Functionality`, `CONTRACT_Id_Contract`),
  CONSTRAINT `FK_TGSI_CONTRACT_FUNCTIONALITY_TGSI_CONTRACT`
    FOREIGN KEY (`CONTRACT_Id_Contract` )
    REFERENCES `TGSI_CONTRACT` (`Id_Contract` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_TGSI_CONTRACT_FUNCTIONALITY_TGSI_FUNCTIONALITY`
    FOREIGN KEY (`FUNCTIONALITY_Id_Functionality` )
    REFERENCES `TGSI_FUNCTIONALITY` (`Id_Functionality` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

-- -----------------------------------------------------
-- Table `TGSI_CONTRACT_REFERENTIAL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TGSI_CONTRACT_REFERENTIAL` (
  `CONTRACT_Id_Contract` bigint(20) NOT NULL ,
  `REFERENTIAL_Id_Referential` bigint(20) NOT NULL ,
  INDEX `INDEX_TGSI_CONTRACT_REFERENTIAL_CONTRACT_Id_Contract` (`CONTRACT_Id_Contract` ASC) ,
  INDEX `INDEX_TGSI_CONTRACT_REFERENTIAL_REFERENTIAL_Id_Referential` (`REFERENTIAL_Id_Referential` ASC) ,
  UNIQUE INDEX `INDEX_UNIQUE_PAIR` (`REFERENTIAL_Id_Referential`, `CONTRACT_Id_Contract`),
  CONSTRAINT `FK_TGSI_CONTRACT_REFERENTIAL_TGSI_CONTRACT`
    FOREIGN KEY (`CONTRACT_Id_Contract` )
    REFERENCES `TGSI_CONTRACT` (`Id_Contract` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_TGSI_CONTRACT_REFERENTIAL_TGSI_REFERENTIAL`
    FOREIGN KEY (`REFERENTIAL_Id_Referential` )
    REFERENCES `TGSI_REFERENTIAL` (`Id_Referential` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8 
COLLATE = utf8_general_ci;

-- -----------------------------------------------------------------
-- Copy content of TGSI_CONTRACT_RESTRICTION TO TGSI_CONTRACT_OPTION
-- -----------------------------------------------------------------
-- INSERT INTO TGSI_CONTRACT_OPTION_ELEMENT (OPTION_ELEMENT_Id_Option_Element , CONTRACT_Id_Contract)
-- SELECT RESTRICTION_Id_Restriction, CONTRACT_Id_Contract FROM TGSI_CONTRACT_RESTRICTION;

-- -----------------------------------------------------------------
-- Insertion of functionality
-- -----------------------------------------------------------------
INSERT IGNORE INTO `TGSI_FUNCTIONALITY` (`Id_Functionality`, `Code`, `Label`, `Description`) VALUES
(1, 'PAGES', 'Audit Pages (Up to 10)', ''),
(2, 'DOMAIN', 'Audit Domain ', ''),
(3, 'UPLOAD', 'Audit Upload', '');

-- -----------------------------------------------------------------
-- Insertion of referential
-- -----------------------------------------------------------------
INSERT IGNORE INTO `TGSI_REFERENTIAL` (`Id_Referential`, `Code`, `Label`) VALUES
(1, 'Aw21', 'Accessiweb 2.1 referential');

-- -----------------------------------------------------------------
-- Insertion of Option Family
-- -----------------------------------------------------------------
INSERT IGNORE INTO `TGSI_OPTION_FAMILY` (`Id_Option_Family`, `Code`, `Label`) VALUES
(1, 'CRAWL', ''),
(2, 'ACT_RESTRICTION', ''),
(3, 'ACT_MANAGEMENT', ''),
(4, 'CONTRACT_MANAGEMENT', '');

-- -----------------------------------------------------------------
-- Insertion of Option
-- -----------------------------------------------------------------
INSERT IGNORE INTO `TGSI_OPTION` (`Id_Option`, `Code`, `Description`, `Is_Restriction`, OPTION_FAMILY_Id_Option_Family) VALUES
(1, 'ACT_LIMITATION', 'The act limitation unit is the number of acts', b'1', 2),
(2, 'ACT_BY_IP_LIMITATION', 'The act by ip limitation unit is the number of acts in a period for a given ip.
The period is expressed in seconds and the format is \"nb_of_acts/period\"', b'1', 2),
(3, 'MAX_DOCUMENTS', 'This restriction limits the max number of crawled documents',b'1', 1),
(4, 'FORDIDDEN_REFERENTIAL', 'This restriction forbids the access to a referential', b'1', 1),
(5, 'DEPTH', 'This restriction limits the depth of the crawl', b'1', 1),
(6, 'MAX_DURATION', 'This restriction limits the duration of the crawl', b'1', 1),
(7, 'EXCLUSION_REGEXP', 'This restriction applies an exclusion rule on crawled Urls', b'1', 1),
(8, 'ACT_LIFETIME','This restriction determines the lifetime of each associated with the contract', b'1', 3),
(9, 'NB_OF_AUDIT_TO_DISPLAY','This restriction determines the number of audit results that can be displayed on the contract page', b'1', 4), 
(10,'DOMAIN', 'Domain associated with a contract', b'1', 4);

-- ---------------------------------------------------------------
-- Copy content of TGSI_RESTRICTION TO TGSI_OPTION
-- ---------------------------------------------------------------
INSERT INTO TGSI_OPTION_ELEMENT (Id_Option_Element, OPTION_Id_Option, Value)
SELECT Id_Restriction, Id_Restriction_Element, Restriction_Value FROM TGSI_RESTRICTION;

-- ---------------------------------------------------------------
-- Copy content of TGSI_CONTRACT_RESTRICTION TO TGSI_CONTRACT_OPTION_ELEMENT
-- ---------------------------------------------------------------
INSERT INTO TGSI_CONTRACT_OPTION_ELEMENT (CONTRACT_Id_Contract, OPTION_ELEMENT_Id_Option_Element)
SELECT CONTRACT_Id_Contract, RESTRICTION_Id_Restriction FROM TGSI_CONTRACT_RESTRICTION WHERE RESTRICTION_Id_Restriction != 9 AND RESTRICTION_Id_Restriction != 16;

-- -----------------------------------------------------------------
-- Creation of relation between contract and Url (through Option)
-- -----------------------------------------------------------------
delimiter |
CREATE PROCEDURE `create_contract_option_from_restriction`()
BEGIN

  DECLARE done INT DEFAULT 0;
  DECLARE v_Id_Audit bigint(20);
  DECLARE v_Id_Web_Resource_Statistics bigint(20);
  DECLARE audits CURSOR FOR SELECT * FROM AUDIT;
  DECLARE wrs CURSOR FOR SELECT Id_Web_Resource_Statistics, Id_Audit FROM WEB_RESOURCE_STATISTICS;

  DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;

  OPEN audits ;
  REPEAT
    FETCH wrs INTO v_Id_Web_Resource_Statistics, v_Id_Audit;
    DECLARE criterions CURSOR ;
    DECLARE v_Id_Criterion bigint(20);
    DECLARE done2 INT DEFAULT 0;
    DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done2 = 1;
    SELECT DISTINCT(c.Id_Criterion) INTO criterions FROM CRITERION as c 
            LEFT JOIN TEST as t on (t.Id_Criterion=c.Id_Criterion) 
            LEFT JOIN AUDIT_TEST as at on (at.Id_Test=t.Id_Test) 
            LEFT JOIN AUDIT as a on (at.Id_Audit=a.Id_Audit) 
            WHERE a.Id_Audit=v_Id_Audit AND a.Status='COMPLETED';
        OPEN criterions ;
            REPEAT
                FETCH criterions INTO v_Id_Criterion;
                DECLARE nb_Passed int(11);
                DECLARE nb_Failed int(11);
                DECLARE nb_Nmi int(11);
                DECLARE nb_Na int(11);
                DECLARE criterion_result varchar(255);

                SELECT COUNT(pr.Id_ProcessResult) INTO nb_Passed FROM PROCESS_RESULT as pr
                    LEFT JOIN TEST as t on (t.Id_Test=pr.Id_Test)
                    LEFT JOIN CRITERION as cr on (t.Id_Criterion=cr.Id_Criterion)
                        WHERE c.Id_Criterion=v_Id_Criterion 
                        AND Definite_Value="PASSED";
                        AND DTYPE="DefiniteResultImpl";

                SELECT COUNT(pr.Id_ProcessResult) INTO nb_Failed FROM PROCESS_RESULT as pr
                    LEFT JOIN TEST as t on (t.Id_Test=pr.Id_Test)
                    LEFT JOIN CRITERION as cr on (t.Id_Criterion=cr.Id_Criterion)
                        WHERE c.Id_Criterion=v_Id_Criterion 
                        AND Definite_Value="FAILED";
                        AND DTYPE="DefiniteResultImpl";

                SELECT COUNT(pr.Id_ProcessResult) INTO nb_Nmi FROM PROCESS_RESULT as pr
                    LEFT JOIN TEST as t on (t.Id_Test=pr.Id_Test)
                    LEFT JOIN CRITERION as cr on (t.Id_Criterion=cr.Id_Criterion)
                        WHERE c.Id_Criterion=v_Id_Criterion 
                        AND Definite_Value="NEED_MORE_INFO";
                        AND DTYPE="DefiniteResultImpl";

                SELECT COUNT(pr.Id_ProcessResult) INTO nb_Na FROM PROCESS_RESULT as pr
                    LEFT JOIN TEST as t on (t.Id_Test=pr.Id_Test)
                    LEFT JOIN CRITERION as cr on (t.Id_Criterion=cr.Id_Criterion)
                        WHERE c.Id_Criterion=v_Id_Criterion 
                        AND Definite_Value="NOT_APPLICABLE";
                        AND DTYPE="DefiniteResultImpl";

                IF nb_Failed > 0 THEN 
                        SET criterion_result="FAILED";
                    ELSEIF nb_Nmi > 0 THEN 
                        SET criterion_result="NEED_MORE_INFO";
                    ELSEIF nb_Passed > 0 THEN 
                        SET criterion_result="PASSED";
                    ELSE 
                        SET criterion_result="NOT_APPLICABLE";
                END IF;

                INSERT INTO CRITERION_STATISTICS (`Id_Criterion`, `Id_Web_Resource_Statistics`, `Nb_Passed`, `Nb_Failed`, `Nb_Nmi`,`Nb_Na`,`criterion_reuslt`)
                VALUES (v_Id_Criterion, v_Id_Web_Resource_Statistics, nb_Passed, nb_Failed, nb_Nmi, nb_Na, criterion_result);
        UNTIL done2 END REPEAT;
        CLOSE criterions ;

  UNTIL done END REPEAT;
  CLOSE audits ;

END  |
delimiter ;

call create_contract_option_from_restriction();
drop procedure create_contract_option_from_restriction;

-- -----------------------------------------------------------------
-- Creation of relation between contract and Functionality
-- -----------------------------------------------------------------
delimiter |
CREATE DEFINER=`$myDatabaseUser`@`localhost` PROCEDURE `create_contract_functionality`()
BEGIN

  DECLARE done INT DEFAULT 0;
  DECLARE a BIGINT(20);
  DECLARE axsPages CURSOR FOR SELECT c.Id_Contract FROM TGSI_CONTRACT c WHERE c.PRODUCT_ID_Product=1;
  DECLARE axsDomain CURSOR FOR SELECT c.Id_Contract FROM TGSI_CONTRACT c WHERE c.PRODUCT_ID_Product=2;
  DECLARE axsReadOnly CURSOR FOR SELECT c.Id_Contract FROM TGSI_CONTRACT c WHERE c.PRODUCT_ID_Product=3;
  DECLARE axsUpload CURSOR FOR SELECT c.Id_Contract FROM TGSI_CONTRACT c WHERE c.PRODUCT_ID_Product=7;
  DECLARE axsPagesUpload CURSOR FOR SELECT c.Id_Contract FROM TGSI_CONTRACT c WHERE c.PRODUCT_ID_Product=9;
  DECLARE axsPagesDomainUpload CURSOR FOR SELECT c.Id_Contract FROM TGSI_CONTRACT c WHERE c.PRODUCT_ID_Product=13;

  DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;

  OPEN axsPages;
  REPEAT
    FETCH axsPages INTO a;
    INSERT IGNORE INTO `TGSI_CONTRACT_FUNCTIONALITY` (`CONTRACT_Id_Contract`, `FUNCTIONALITY_Id_Functionality`) VALUES
	    (a,1);
  UNTIL done END REPEAT;
  CLOSE axsPages;

SET done = 0;

  OPEN axsDomain;
  REPEAT
    FETCH axsDomain INTO a;
    INSERT IGNORE INTO `TGSI_CONTRACT_FUNCTIONALITY` (`CONTRACT_Id_Contract`, `FUNCTIONALITY_Id_Functionality`) VALUES
	    (a,1);
    INSERT IGNORE INTO `TGSI_CONTRACT_FUNCTIONALITY` (`CONTRACT_Id_Contract`, `FUNCTIONALITY_Id_Functionality`) VALUES
	    (a,2);
  UNTIL done END REPEAT;
  CLOSE axsDomain;

SET done = 0;

  OPEN axsUpload;
  REPEAT
    FETCH axsUpload INTO a;
    INSERT IGNORE INTO `TGSI_CONTRACT_FUNCTIONALITY` (`CONTRACT_Id_Contract`, `FUNCTIONALITY_Id_Functionality`) VALUES
	    (a,3);
  UNTIL done END REPEAT;
  CLOSE axsUpload;

SET done = 0;

  OPEN axsPagesUpload;
  REPEAT
    FETCH axsPagesUpload INTO a;
    INSERT IGNORE INTO `TGSI_CONTRACT_FUNCTIONALITY` (`CONTRACT_Id_Contract`, `FUNCTIONALITY_Id_Functionality`) VALUES
	    (a,1);
    INSERT IGNORE INTO `TGSI_CONTRACT_FUNCTIONALITY` (`CONTRACT_Id_Contract`, `FUNCTIONALITY_Id_Functionality`) VALUES
	    (a,3);
  UNTIL done END REPEAT;
  CLOSE axsPagesUpload;

SET done = 0;

  OPEN axsPagesDomainUpload;
  REPEAT
    FETCH axsPagesDomainUpload INTO a;
    INSERT IGNORE INTO `TGSI_CONTRACT_FUNCTIONALITY` (`CONTRACT_Id_Contract`, `FUNCTIONALITY_Id_Functionality`) VALUES
	    (a,1);
    INSERT IGNORE INTO `TGSI_CONTRACT_FUNCTIONALITY` (`CONTRACT_Id_Contract`, `FUNCTIONALITY_Id_Functionality`) VALUES
	    (a,2);
    INSERT IGNORE INTO `TGSI_CONTRACT_FUNCTIONALITY` (`CONTRACT_Id_Contract`, `FUNCTIONALITY_Id_Functionality`) VALUES
	    (a,3);
  UNTIL done END REPEAT;
  CLOSE axsPagesDomainUpload;

END |
delimiter ;
call create_contract_functionality();
drop procedure create_contract_functionality;

-- -----------------------------------------------------------------
-- Creation of relation between contract and referential
-- -----------------------------------------------------------------
delimiter |
CREATE DEFINER=`tanaguru`@`localhost` PROCEDURE `create_contract_referential`()
BEGIN

  DECLARE done INT DEFAULT 0;
  DECLARE a BIGINT(20);
  DECLARE contract CURSOR FOR SELECT c.Id_Contract FROM TGSI_CONTRACT c;

  DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;

  OPEN contract;
  REPEAT
    FETCH contract INTO a;
    INSERT IGNORE INTO `TGSI_CONTRACT_REFERENTIAL` (`CONTRACT_Id_Contract`, `REFERENTIAL_Id_Referential`) VALUES
	    (a,1);
  UNTIL done END REPEAT;
  CLOSE contract;


END |
delimiter ;
call create_contract_referential();
drop procedure `create_contract_referential`;


SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

ALTER TABLE `TGSI_CONTRACT` CHANGE `PRODUCT_Id_Product` `PRODUCT_Id_Product` BIGINT( 20 ) NULL ;
UPDATE `TGSI_CONTRACT` SET `PRODUCT_Id_Product`=NULL;
ALTER TABLE TGSI_CONTRACT DROP FOREIGN KEY FK_CONTRACT_PRODUCT;
ALTER TABLE TGSI_CONTRACT DROP COLUMN PRODUCT_Id_Product;
ALTER TABLE TGSI_CONTRACT DROP COLUMN Url;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------------------
-- Remove tables about restriction
-- -----------------------------------------------------------------
TRUNCATE TABLE TGSI_CONTRACT_RESTRICTION;
DROP TABLE TGSI_CONTRACT_RESTRICTION;
TRUNCATE TABLE TGSI_RESTRICTION;
DROP TABLE TGSI_RESTRICTION;
TRUNCATE TABLE TGSI_RESTRICTION_ELEMENT;
DROP TABLE TGSI_RESTRICTION_ELEMENT;

ALTER TABLE CONTENT MODIFY Binary_Content MEDIUMBLOB NULL DEFAULT NULL, MODIFY Uri    VARCHAR(4096);

ALTER TABLE WEB_RESOURCE MODIFY Url VARCHAR(4096);