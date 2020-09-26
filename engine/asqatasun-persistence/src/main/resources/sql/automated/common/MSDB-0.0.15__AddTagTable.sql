-- --------------------------------------------------------
--
-- table `TAG`
--
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS `TAG` (
    `Id_Tag` bigint(20) NOT NULL AUTO_INCREMENT,
    `Value` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`Id_Tag`),
    UNIQUE INDEX `Value_UNIQUE` (`Value` ASC)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------
--
-- table `AUDIT_TAG`
--
-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS `AUDIT_TAG` (
    `Id_Audit` bigint(20) NOT NULL,
    `Id_Tag` bigint(20) NOT NULL,
    KEY `FK838E6E96ABC9863D` (`Id_Audit`),
    CONSTRAINT `FK838E6E96ABC9863D`
        FOREIGN KEY Id_Audit_Index (`Id_Audit`)
            REFERENCES `AUDIT` (`Id_Audit`)
            ON UPDATE NO ACTION
            ON DELETE CASCADE,
    KEY `FK838E6E96F94B364A` (`Id_Tag`),
    CONSTRAINT `FK838E6E96F94B364A`
        FOREIGN KEY Id_Tag_Index (`Id_Tag`)
            REFERENCES `TAG` (`Id_Tag`)
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
