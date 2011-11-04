SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `tanaguru` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `tanaguru` ;

-- -----------------------------------------------------
-- Table `TGSI_ROLE`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `TGSI_ROLE` (
  `Id_Role` bigint(20) NOT NULL AUTO_INCREMENT ,
  `Role_Name` VARCHAR(255) NOT NULL ,
  `ROLE_Id_Role` bigint(20) NULL ,
  PRIMARY KEY (`Id_Role`) ,
  INDEX `fk_ROLE_ROLE` (`ROLE_Id_Role` ASC) ,
  CONSTRAINT `fk_ROLE_ROLE`
    FOREIGN KEY (`ROLE_Id_Role` )
    REFERENCES `TGSI_ROLE` (`Id_Role` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `TGSI_USER`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `TGSI_USER` (
  `Id_User` bigint(20) NOT NULL AUTO_INCREMENT ,
  `Email1` VARCHAR(255) NOT NULL ,
  `Password` VARCHAR(255) NOT NULL ,
  `Name` VARCHAR(255) NULL ,
  `First_Name` VARCHAR(255) NULL ,
  `Address` VARCHAR(255) NULL ,
  `Phone_Number` VARCHAR(255) NULL ,
  `Email2` VARCHAR(255) NULL ,
  `Web1` VARCHAR(2048) NULL ,
  `Web2` VARCHAR(2048) NULL ,
  `Identica_Id` VARCHAR(255) NULL ,
  `Twitter_Id` VARCHAR(255) NULL ,
  `ROLE_Id_Role` bigint(20) NOT NULL ,
  `Activated` bit(1) DEFAULT b'1',
  PRIMARY KEY (`Id_User`) ,
  UNIQUE INDEX `Id_User_UNIQUE` (`Id_User` ASC) ,
  INDEX `Email1_INDEX` (`Email1` ASC) ,
  UNIQUE INDEX `Email1_UNIQUE` (`Email1` ASC) ,
  INDEX `fk_USER_ROLE` (`ROLE_Id_Role` ASC) ,
  CONSTRAINT `fk_USER_ROLE`
    FOREIGN KEY (`ROLE_Id_Role` )
    REFERENCES `TGSI_ROLE` (`Id_Role` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `TGSI_SCOPE`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `TGSI_SCOPE` (
  `Id_Scope` bigint(20) NOT NULL AUTO_INCREMENT ,
  `Code` VARCHAR(255) NOT NULL DEFAULT 'PAGE',
  `Label` VARCHAR(255) NOT NULL DEFAULT 'Page',
  PRIMARY KEY (`Id_Scope`) ,
  UNIQUE INDEX `Scope_Code_UNIQUE` (`Code` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `TGSI_PRODUCT`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `TGSI_PRODUCT` (
  `Id_Product` bigint(20) NOT NULL AUTO_INCREMENT ,
  `Code` VARCHAR(255) NOT NULL ,
  `Label` VARCHAR(255) NULL ,
  `Description` VARCHAR(1000) NULL ,
  `SCOPE_Id_Scope` bigint(20) NOT NULL ,
  PRIMARY KEY (`Id_Product`) ,
  UNIQUE INDEX `Id_Product_UNIQUE` (`Id_Product` ASC) ,
  UNIQUE INDEX `Product_Code_UNIQUE` (`Code` ASC) ,
  INDEX `fk_PRODUCT_SCOPE` (`SCOPE_Id_Scope` ASC) ,
  CONSTRAINT `fk_PRODUCT_SCOPE`
    FOREIGN KEY (`SCOPE_Id_Scope` )
    REFERENCES `TGSI_SCOPE` (`Id_Scope` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `TGSI_CONTRACT`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `TGSI_CONTRACT` (
  `Id_Contract` bigint(20) NOT NULL AUTO_INCREMENT ,
  `Label` VARCHAR(2048) NOT NULL ,
  `Url` VARCHAR(2048) NOT NULL ,
  `Begin_Date` DATETIME NOT NULL ,
  `End_Date` DATETIME NOT NULL ,
  `Renewal_Date` DATETIME NULL ,
  `Price` FLOAT ZEROFILL NULL ,
  `USER_Id_User` bigint(20) NOT NULL ,
  `PRODUCT_Id_Product` bigint(20) NOT NULL ,
  PRIMARY KEY (`Id_Contract`) ,
  UNIQUE INDEX `Id_Contract_UNIQUE` (`Id_Contract` ASC) ,
  INDEX `FK_CONTRACT_PRODUCT` (`PRODUCT_Id_Product` ASC) ,
  INDEX `Url_INDEX` (`Url` ASC) ,
  CONSTRAINT `FK_CONTRACT_PRODUCT`
    FOREIGN KEY (`PRODUCT_Id_Product` )
    REFERENCES `TGSI_PRODUCT` (`Id_Product` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CONTRACT_USER`
    FOREIGN KEY (`USER_Id_User` )
    REFERENCES `TGSI_USER` (`Id_User` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `TGSI_ACT`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `TGSI_ACT` (
  `Id_Act` bigint(20) NOT NULL AUTO_INCREMENT ,
  `Begin_Date` DATETIME NOT NULL ,
  `End_Date` DATETIME NULL ,
  `Status` VARCHAR(255) NOT NULL ,
  `CONTRACT_Id_Contract` bigint(20) NOT NULL ,
  `SCOPE_Id_Scope` bigint(20) NOT NULL ,
  `Client_Ip` VARCHAR(16) NOT NULL DEFAULT "0.0.0.0",
  PRIMARY KEY (`Id_Act`) ,
  UNIQUE INDEX `id_Act_UNIQUE` (`Id_Act` ASC) ,
  INDEX `fk_ACT_SCOPE` (`SCOPE_Id_Scope` ASC) ,
  INDEX `fk_ACT_CONTRACT` (`CONTRACT_Id_Contract` ASC) ,
  CONSTRAINT `fk_ACT_CONTRACT`
    FOREIGN KEY (`CONTRACT_Id_Contract`)
    REFERENCES `TGSI_CONTRACT` (`Id_Contract`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ACT_SCOPE`
    FOREIGN KEY (`SCOPE_Id_Scope`)
    REFERENCES `TGSI_SCOPE` (`Id_Scope`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `TGSI`.`TGSI_ACT_WEB_RESOURCE`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `TGSI_ACT_WEB_RESOURCE` (
  `ACT_Id_Act` bigint(20) NOT NULL ,
  `WEB_RESOURCE_Id_Web_Resource` bigint(20) NOT NULL ,
  INDEX `fk_ACT_WEB_RESOURCE` (`ACT_Id_Act` ASC) ,
  CONSTRAINT `fk_TGSI_ACT_WEB_RESOURCE_ACT`
    FOREIGN KEY (`ACT_Id_Act` )
    REFERENCES `TGSI_ACT` (`Id_Act` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TGSI_ACT_WEB_RESOURCE_WEB_RESOURCE`
    FOREIGN KEY (`WEB_RESOURCE_Id_Web_Resource` )
    REFERENCES `WEB_RESOURCE` (`Id_Web_Resource` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

-- -----------------------------------------------------
-- Table `TGSI_RESTRICTION_ELEMENT`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `TGSI_RESTRICTION_ELEMENT` (
  `Id_Restriction_Element` bigint(20) NOT NULL AUTO_INCREMENT ,
  `Cd_Restriction_Element` VARCHAR(255) NOT NULL ,
  `Description` VARCHAR(1000) NULL ,
  PRIMARY KEY (`Id_Restriction_Element`) ,
  UNIQUE INDEX `Cd_Restriction_Element_UNIQUE` (`Cd_Restriction_Element` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

-- -----------------------------------------------------
-- Table `TGSI_RESTRICTION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TGSI_RESTRICTION` (
  `Id_Restriction` bigint(20) NOT NULL AUTO_INCREMENT ,
  `Id_Restriction_Element` bigint(20) NOT NULL ,
  `Restriction_Value` VARCHAR(255) NULL ,
  PRIMARY KEY (`Id_Restriction`) ,
  INDEX `fk_TGSi_RESTRICTION_RESTRICTION_ELEMENT` (`Id_Restriction_Element` ASC) ,
  CONSTRAINT `fk_RESTRICTION_RESTRICTION_ELEMENT`
    FOREIGN KEY (`Id_Restriction_Element` )
    REFERENCES `TGSI_RESTRICTION_ELEMENT` (`Id_Restriction_Element` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

-- -----------------------------------------------------
-- Table `TGSI_PRODUCT_RESTRICTION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TGSI_CONTRACT_RESTRICTION` (
  `RESTRICTION_Id_Restriction` bigint(20) NOT NULL ,
  `CONTRACT_Id_Contract` bigint(20) NOT NULL ,
  INDEX `fk_TGSI_CONTRACT_RESTRICTION_TGSI_CONTRACT` (`CONTRACT_Id_Contract` ASC) ,
  INDEX `fk_TGSI_CONTRACT_RESTRICTION_TGSI_RESTRICTION` (`RESTRICTION_Id_Restriction` ASC) ,
  CONSTRAINT `fk_TGSI_CONTRACT_RESTRICTION_TGSI_PRODUCT`
    FOREIGN KEY (`CONTRACT_Id_Contract` )
    REFERENCES `TGSI_CONTRACT` (`Id_Contract` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TGSI_CONTRACT_RESTRICTION_TGSI_RESTRICTION`
    FOREIGN KEY (`RESTRICTION_Id_Restriction` )
    REFERENCES `TGSI_RESTRICTION` (`Id_Restriction` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;