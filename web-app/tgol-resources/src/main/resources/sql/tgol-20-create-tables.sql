-- -----------------------------------------------------
-- Table `TGSI_ROLE`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `TGSI_ROLE` (
  `Id_Role` bigint(20) NOT NULL AUTO_INCREMENT ,
  `Role_Name` VARCHAR(255) NOT NULL ,
  `ROLE_Id_Role` bigint(20) NULL ,
  PRIMARY KEY (`Id_Role`) ,
  INDEX `INDEX_TGSI_ROLE_ROLE_Id_Role` (`ROLE_Id_Role` ASC) ,
  CONSTRAINT `FK_TGSI_ROLE_TGSI_ROLE`
    FOREIGN KEY (`ROLE_Id_Role` )
    REFERENCES `TGSI_ROLE` (`Id_Role` )
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
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
  `Name` VARCHAR(255) NOT NULL DEFAULT '',
  `First_Name` VARCHAR(255) NOT NULL DEFAULT '' ,
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
  UNIQUE INDEX `UNIQUE_INDEX_TGSI_USER_Email1` (`Email1` ASC) ,
  INDEX `INDEX_TGSI_USER_ROLE_Id_Role` (`ROLE_Id_Role` ASC) ,
  CONSTRAINT `FK_TGSI_USER_TGSI_ROLE`
    FOREIGN KEY (`ROLE_Id_Role` )
    REFERENCES `TGSI_ROLE` (`Id_Role` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
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
  UNIQUE INDEX `UNIQUE_INDEX_TGSI_SCOPE_Code` (`Code` ASC) 
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `TGSI_FUNCTIONALITY`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `TGSI_FUNCTIONALITY` (
  `Id_Functionality` bigint(20) NOT NULL AUTO_INCREMENT ,
  `Code` VARCHAR(45) NOT NULL ,
  `Label` VARCHAR(255) NULL ,
  `Description` VARCHAR(2048) NULL ,
  PRIMARY KEY (`Id_Functionality`) ,
  UNIQUE INDEX `UNIQUE_INDEX_TGSI_FUNCTIONALITY_Code` (`Code` ASC) 
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;


-- -----------------------------------------------------
-- Table `TGSI_CONTRACT`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `TGSI_CONTRACT` (
  `Id_Contract` bigint(20) NOT NULL AUTO_INCREMENT ,
  `Label` VARCHAR(255) NOT NULL ,
  `Begin_Date` DATETIME NOT NULL ,
  `End_Date` DATETIME NOT NULL ,
  `Renewal_Date` DATETIME NULL ,
  `Price` FLOAT ZEROFILL NULL ,
  `USER_Id_User` bigint(20) NOT NULL ,
  PRIMARY KEY (`Id_Contract`) ,
  CONSTRAINT `FK_TGSI_CONTRACT_TGSI_USER`
    FOREIGN KEY (`USER_Id_User` )
    REFERENCES `TGSI_USER` (`Id_User` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION
)
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
  INDEX `INDEX_TGSI_ACT_SCOPE_Id_Scope` (`SCOPE_Id_Scope` ASC) ,
  INDEX `INDEX_TGSI_ACT_CONTRACT_Id_Contract` (`CONTRACT_Id_Contract` ASC) ,
  CONSTRAINT `FK_TGSI_ACT_TGSI_CONTRACT`
    FOREIGN KEY (`CONTRACT_Id_Contract`)
    REFERENCES `TGSI_CONTRACT` (`Id_Contract`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_TGSI_ACT_TGSI_SCOPE`
    FOREIGN KEY (`SCOPE_Id_Scope`)
    REFERENCES `TGSI_SCOPE` (`Id_Scope`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

-- -----------------------------------------------------
-- Table `TGSI`.`TGSI_ACT_AUDIT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TGSI_ACT_AUDIT` (
  `ACT_Id_Act` bigint(20) NOT NULL ,
  `AUDIT_Id_Audit` bigint(20) NOT NULL ,
  INDEX `INDEX_TGSI_ACT_AUDIT_ACT_Id_Act` (`ACT_Id_Act` ASC) ,
  INDEX `INDEX_TGSI_ACT_AUDIT_AUDIT_Id_Audit` (`AUDIT_Id_Audit` ASC) ,
  UNIQUE INDEX `INDEX_UNIQUE_PAIR` (`ACT_Id_Act`, `AUDIT_Id_Audit`),
  CONSTRAINT `FK_TGSI_ACT_AUDIT_TGSI_ACT`
    FOREIGN KEY (`ACT_Id_Act` )
    REFERENCES `TGSI_ACT` (`Id_Act` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_TGSI_ACT_AUDIT_AUDIT`
    FOREIGN KEY (`AUDIT_Id_Audit` )
    REFERENCES `AUDIT` (`Id_Audit` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
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
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_TGSI_CONTRACT_OPTION_ELEMENT_TGSI_OPTION_ELEMENT`
    FOREIGN KEY (`OPTION_ELEMENT_Id_Option_Element` )
    REFERENCES `TGSI_OPTION_ELEMENT` (`Id_Option_Element` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

-- -----------------------------------------------------
-- Table `TGSI_USER_OPTION_ELEMENT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TGSI_USER_OPTION_ELEMENT` (
  `OPTION_ELEMENT_Id_Option_Element` bigint(20) NOT NULL ,
  `USER_Id_User` bigint(20) NOT NULL ,
  UNIQUE INDEX `INDEX_UNIQUE_PAIR` (`OPTION_ELEMENT_Id_Option_Element`, `USER_Id_User`),
  CONSTRAINT `FK_TGSI_USER_OPTION_ELEMENT_TGSI_USER`
    FOREIGN KEY (`USER_Id_User` )
    REFERENCES `TGSI_USER` (`Id_User` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_TGSI_USER_OPTION_ELEMENT_TGSI_OPTION_ELEMENT`
    FOREIGN KEY (`OPTION_ELEMENT_Id_Option_Element` )
    REFERENCES `TGSI_OPTION_ELEMENT` (`Id_Option_Element` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
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
    ON DELETE CASCADE
    ON UPDATE CASCADE,
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
-- Table `TGSI_REFERENTIAL`
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
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_TGSI_CONTRACT_REFERENTIAL_TGSI_REFERENTIAL`
    FOREIGN KEY (`REFERENTIAL_Id_Referential` )
    REFERENCES `TGSI_REFERENTIAL` (`Id_Referential` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
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
    ON UPDATE CASCADE
) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8 
COLLATE = utf8_general_ci;

