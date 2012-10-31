USE $tgDatabase;

ALTER TABLE `TGSI_CONTRACT` DROP FOREIGN KEY `FK_TGSI_CONTRACT_TGSI_USER`;
ALTER TABLE `TGSI_CONTRACT` 
	ADD CONSTRAINT `FK_TGSI_CONTRACT_TGSI_USER`
    FOREIGN KEY (`USER_Id_User` )
    REFERENCES `TGSI_USER` (`Id_User` )
    ON DELETE CASCADE
    ON UPDATE CASCADE;


ALTER TABLE `TGSI_CONTRACT_REFERENTIAL` DROP FOREIGN KEY `FK_TGSI_CONTRACT_REFERENTIAL_TGSI_CONTRACT`;
ALTER TABLE `TGSI_CONTRACT_REFERENTIAL` 
	ADD CONSTRAINT `FK_TGSI_CONTRACT_REFERENTIAL_TGSI_CONTRACT`
	FOREIGN KEY (`CONTRACT_Id_Contract` )
	REFERENCES `TGSI_CONTRACT` (`Id_Contract` )
	ON DELETE CASCADE
	ON UPDATE CASCADE;


ALTER TABLE `TGSI_CONTRACT_FUNCTIONALITY` DROP FOREIGN KEY `FK_TGSI_CONTRACT_FUNCTIONALITY_TGSI_CONTRACT`;
ALTER TABLE `TGSI_CONTRACT_FUNCTIONALITY` 
	ADD CONSTRAINT `FK_TGSI_CONTRACT_FUNCTIONALITY_TGSI_CONTRACT`
	FOREIGN KEY (`CONTRACT_Id_Contract` )
	REFERENCES `TGSI_CONTRACT` (`Id_Contract` )
	ON DELETE CASCADE
	ON UPDATE CASCADE;


ALTER TABLE `TGSI_CONTRACT_OPTION_ELEMENT` DROP FOREIGN KEY `FK_TGSI_CONTRACT_OPTION_ELEMENT_TGSI_CONTRACT`;
ALTER TABLE `TGSI_CONTRACT_OPTION_ELEMENT` 
	ADD CONSTRAINT `FK_TGSI_CONTRACT_OPTION_ELEMENT_TGSI_CONTRACT`
	FOREIGN KEY (`CONTRACT_Id_Contract` )
	REFERENCES `TGSI_CONTRACT` (`Id_Contract` )
	ON DELETE CASCADE
	ON UPDATE CASCADE;

ALTER TABLE `TGSI_ACT` DROP FOREIGN KEY `fk_ACT_CONTRACT`;
ALTER TABLE `TGSI_ACT` 
	ADD CONSTRAINT `fk_ACT_CONTRACT`
	FOREIGN KEY (`CONTRACT_Id_Contract` )
	REFERENCES `TGSI_CONTRACT` (`Id_Contract` )
	ON DELETE CASCADE
	ON UPDATE CASCADE;

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

ALTER TABLE `TGSI_USER` CHANGE `Name` `Name` VARCHAR( 255 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '';
ALTER TABLE `TGSI_USER` CHANGE `First_Name` `First_Name` VARCHAR( 255 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '';

INSERT IGNORE INTO `TGSI_OPTION_FAMILY` (`Id_Option_Family`, `Code`, `Label`) VALUES
(5, 'TEST_WEIGHT_MANAGEMENT', '');

INSERT IGNORE INTO `TGSI_OPTION` (`Id_Option`, `Code`, `Description`, `Is_Restriction`, OPTION_FAMILY_Id_Option_Family) VALUES
(11, 'Seo-01011', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(12, 'Seo-01012', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(13, 'Seo-01013', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(14, 'Seo-01021', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(15, 'Seo-01031', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(16, 'Seo-01041', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(17, 'Seo-01051', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(18, 'Seo-01061', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(19, 'Seo-02011', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(20, 'Seo-02012', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(21, 'Seo-02013', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(22, 'Seo-03011', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(23, 'Seo-03012', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(24, 'Seo-05011', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(25, 'Seo-05012', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(26, 'Seo-05013', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(27, 'Seo-06011', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(28, 'Seo-06021', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(29, 'Seo-06031', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(30, 'Seo-06041', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(31, 'Seo-06051', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(32, 'Seo-06052', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(33, 'Seo-07011', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(34, 'Seo-07012', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(35, 'Seo-07021', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(36, 'Seo-07051', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(37, 'Seo-07061', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(38, 'Seo-08011', 'Weight of rule Seo-01011 overriden by user', b'0', 5);
