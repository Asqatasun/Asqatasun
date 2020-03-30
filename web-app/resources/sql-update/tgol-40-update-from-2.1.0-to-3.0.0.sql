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

drop procedure if exists populate_tgsi_act_audit;
delimiter |
CREATE PROCEDURE `populate_tgsi_act_audit`()
BLOCK1: BEGIN

    DECLARE done INT DEFAULT 0;

    DECLARE v_Id_Web_Resource BIGINT(20);
    DECLARE v_Id_Act BIGINT(20);
    DECLARE v_Id_Audit bigint(20);
    DECLARE awr CURSOR FOR SELECT 
                    ACT_Id_Act,
                    WEB_RESOURCE_Id_Web_Resource
                        FROM TGSI_ACT_WEB_RESOURCE;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    OPEN awr;
    LOOP1: loop
        FETCH awr INTO v_Id_Act, v_Id_Web_Resource;
        IF done = 1 THEN
            LEAVE LOOP1;
        END IF;
        SELECT Id_Audit INTO v_Id_Audit FROM WEB_RESOURCE as wr
                            WHERE wr.Id_Web_Resource=v_Id_Web_Resource;
        INSERT INTO `TGSI_ACT_AUDIT`
            (`ACT_Id_Act`, `AUDIT_Id_Audit`) 
            VALUES (v_Id_Act, v_Id_Audit);
    END loop LOOP1;

END BLOCK1 |
delimiter ;

call populate_tgsi_act_audit();
drop procedure populate_tgsi_act_audit;
drop table `TGSI_ACT_WEB_RESOURCE`;

INSERT IGNORE INTO `TGSI_REFERENTIAL` (`Id_Referential`, `Code`, `Label`) VALUES
(4, 'AW22', 'Accessiweb 2.2 referential') ;