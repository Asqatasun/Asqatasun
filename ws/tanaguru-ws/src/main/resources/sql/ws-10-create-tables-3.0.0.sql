--- -----------------------------------------------------
--- Table `WS_ROLE`
--- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `WS_ROLE` (
  `ID_ROLE` bigint(20) NOT NULL AUTO_INCREMENT ,
  `ROLE` VARCHAR(50) NOT NULL ,
  `LABEL` VARCHAR(255) NULL ,
  PRIMARY KEY (`ID_ROLE`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;
--- -----------------------------------------------------
--- Table `WS_USER`
--- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `WS_USER` (
  `ID_USER` bigint(20) NOT NULL AUTO_INCREMENT ,
  `EMAIL` VARCHAR(255) NOT NULL ,
  `PASSWORD` VARCHAR(255) NOT NULL ,
  `NAME` VARCHAR(255) NOT NULL ,
  `FIRST_NAME` VARCHAR(255) NOT NULL ,
  `ACTIVE` bit(1) DEFAULT b'1',
  `ID_ROLE` bigint(20) NOT NULL ,
  PRIMARY KEY (`ID_USER`),
  CONSTRAINT `FK_WS_USER_WS_ROLE`
    FOREIGN KEY (`ID_ROLE` )
    REFERENCES `WS_ROLE` (`ID_ROLE` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;
--- -----------------------------------------------------
--- Table `WS_INVOCATION`
--- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `WS_INVOCATION` (
  `ID_INVOCATION` bigint(20) NOT NULL AUTO_INCREMENT ,
  `HOST_NAME` VARCHAR(255) NOT NULL ,
  `HOST_IP` VARCHAR(255) NOT NULL ,
  `DT_INVOCATION` datetime NOT NULL ,
  `AUDIT_TYPE` INTEGER NOT NULL ,
  `ID_USER` bigint(20),
  `ID_AUDIT` bigint(20),
  `CATEGORY` VARCHAR(255),
  `COUNTRY` VARCHAR(255),
 PRIMARY KEY (`ID_INVOCATION`),
 CONSTRAINT `FK_WS_INVOCATION_WS_USER`
    FOREIGN KEY (`ID_USER`)
    REFERENCES `WS_USER` (`ID_USER` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
  )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

