-- -----------------------------------------------------
-- Table `tanaguru`.`CRITERION_STATISTICS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CRITERION_STATISTICS` (
  `Id_Criterion_Statistics` bigint(20) NOT NULL AUTO_INCREMENT,
  `Nb_Passed` int(11) DEFAULT NULL,
  `Nb_Failed` int(11) DEFAULT NULL,
  `Nb_Nmi` int(11) DEFAULT NULL,
  `Nb_Na` int(11) DEFAULT NULL,
  `Nb_Suspected` int(11) DEFAULT 0,
  `Nb_Detected` int(11) DEFAULT 0,
  `Nb_Not_Tested` int(11) DEFAULT 0,
  `Criterion_Result` varchar(255) DEFAULT NULL,
  `Id_Criterion` bigint(20) DEFAULT NULL,
  `Id_Web_Resource_Statistics` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id_Criterion_Statistics`),
  INDEX `fk_CRITERION_STATISTICS_CRITERION` (`Id_Criterion` ASC) ,
  CONSTRAINT `fk_CRITERION_STATISTICS_CRITERION`
    FOREIGN KEY (`Id_Criterion` )
    REFERENCES `CRITERION` (`Id_Criterion` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  INDEX `fk_CRITERION_STATISTICS_WEB_RESOURCE_STATISTICS` (`Id_Web_Resource_Statistics` ASC) ,
  CONSTRAINT `fk_CRITERION_STATISTICS_WEB_RESOURCE_STATISTICS`
    FOREIGN KEY (`Id_Web_Resource_Statistics` )
    REFERENCES `WEB_RESOURCE_STATISTICS` (`Id_Web_Resource_Statistics` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

drop procedure if exists create_criterion_statistics;
delimiter |

CREATE PROCEDURE `create_criterion_statistics`()
BLOCK1: BEGIN

  DECLARE no_more_wrs boolean DEFAULT FALSE;  
  DECLARE v_Id_Audit bigint(20);
  DECLARE v_Id_Web_Resource_Statistics bigint(20);
  DECLARE wrs CURSOR FOR SELECT Id_Web_Resource_Statistics, Id_Audit FROM WEB_RESOURCE_STATISTICS;

  DECLARE continue handler for not found set no_more_wrs := TRUE;

  OPEN wrs;
      LOOP1: loop
          FETCH wrs INTO v_Id_Web_Resource_Statistics, v_Id_Audit;
          IF no_more_wrs then 
	      close wrs;
              leave LOOP1;
          END IF;
	  
	  BLOCK2: BEGIN
            DECLARE no_more_cr boolean DEFAULT FALSE;
            DECLARE nb_Passed int(11);
            DECLARE nb_Failed int(11);
            DECLARE nb_Nmi int(11);
            DECLARE nb_Na int(11);
            DECLARE v_Id_Criterion bigint(20);
            DECLARE criterion_result varchar(255);
            DECLARE criterions CURSOR FOR SELECT DISTINCT(c.Id_Criterion) FROM CRITERION as c 
                    LEFT JOIN TEST as t on (t.Id_Criterion=c.Id_Criterion) 
                    LEFT JOIN AUDIT_TEST as at on (at.Id_Test=t.Id_Test) 
                    LEFT JOIN AUDIT as a on (at.Id_Audit=a.Id_Audit) 
                        WHERE a.Id_Audit=v_Id_Audit AND a.Status='COMPLETED';
            
            DECLARE continue handler for not found set no_more_cr := TRUE;

            OPEN criterions;
	        LOOP2: loop
                    FETCH criterions INTO v_Id_Criterion;
                    IF no_more_cr then 
                        close criterions;
                        leave LOOP2;
                    END IF;

                    SELECT COUNT(pr.Id_Process_Result) INTO nb_Passed FROM PROCESS_RESULT as pr
                        LEFT JOIN TEST as t on (t.Id_Test=pr.Id_Test)
                        LEFT JOIN CRITERION as cr on (t.Id_Criterion=cr.Id_Criterion)
                            WHERE cr.Id_Criterion=v_Id_Criterion 
                            AND Definite_Value="PASSED"
                            AND Id_Audit_Net_Result=v_Id_Audit
                            AND DTYPE="DefiniteResultImpl";

                    SELECT COUNT(pr.Id_Process_Result) INTO nb_Failed FROM PROCESS_RESULT as pr
                        LEFT JOIN TEST as t on (t.Id_Test=pr.Id_Test)
                        LEFT JOIN CRITERION as cr on (t.Id_Criterion=cr.Id_Criterion)
                        WHERE cr.Id_Criterion=v_Id_Criterion 
                            AND Definite_Value="FAILED"
                            AND Id_Audit_Net_Result=v_Id_Audit
                            AND DTYPE="DefiniteResultImpl";

                    SELECT COUNT(pr.Id_Process_Result) INTO nb_Nmi FROM PROCESS_RESULT as pr
                        LEFT JOIN TEST as t on (t.Id_Test=pr.Id_Test)
                        LEFT JOIN CRITERION as cr on (t.Id_Criterion=cr.Id_Criterion)
                            WHERE cr.Id_Criterion=v_Id_Criterion 
                            AND Definite_Value="NEED_MORE_INFO"
                            AND Id_Audit_Net_Result=v_Id_Audit
                            AND DTYPE="DefiniteResultImpl";

                    SELECT COUNT(pr.Id_Process_Result) INTO nb_Na FROM PROCESS_RESULT as pr
                        LEFT JOIN TEST as t on (t.Id_Test=pr.Id_Test)
                        LEFT JOIN CRITERION as cr on (t.Id_Criterion=cr.Id_Criterion)
                            WHERE cr.Id_Criterion=v_Id_Criterion 
                            AND Definite_Value="NOT_APPLICABLE"
                            AND Id_Audit_Net_Result=v_Id_Audit
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

                    INSERT INTO CRITERION_STATISTICS (
                            `Id_Criterion`, 
                            `Id_Web_Resource_Statistics`, 
                            `Nb_Passed`, 
                            `Nb_Failed`, 
                            `Nb_Nmi`,
                            `Nb_Na`,
                            `Criterion_Result`)
                                VALUES (
                                        v_Id_Criterion, 
                                        v_Id_Web_Resource_Statistics, 
                                        nb_Passed, 
                                        nb_Failed, 
                                        nb_Nmi, 
                                        nb_Na, 
                                        criterion_result);
                END loop LOOP2;
            END BLOCK2;
      END loop LOOP1;
END BLOCK1 |
delimiter ;


-- call create_criterion_statistics();

ALTER TABLE WEB_RESOURCE_STATISTICS ADD `Nb_Suspected` int(11) DEFAULT 0 AFTER Nb_Na;
ALTER TABLE WEB_RESOURCE_STATISTICS ADD `Nb_Detected` int(11) DEFAULT 0 AFTER Nb_Suspected;
ALTER TABLE WEB_RESOURCE_STATISTICS ADD `Nb_Not_Tested` int(11) DEFAULT 0 AFTER Nb_Detected;

ALTER TABLE TEST_STATISTICS ADD `Nb_Suspected` int(11) DEFAULT 0 AFTER Nb_Na;
ALTER TABLE TEST_STATISTICS ADD `Nb_Detected` int(11) DEFAULT 0 AFTER Nb_Suspected;
ALTER TABLE TEST_STATISTICS ADD `Nb_Not_Tested` int(11) DEFAULT 0 AFTER Nb_Detected;

ALTER TABLE THEME_STATISTICS ADD `Nb_Suspected` int(11) DEFAULT 0 AFTER Nb_Na;
ALTER TABLE THEME_STATISTICS ADD `Nb_Detected` int(11) DEFAULT 0 AFTER Nb_Suspected;
ALTER TABLE THEME_STATISTICS ADD `Nb_Not_Tested` int(11) DEFAULT 0 AFTER Nb_Detected;

ALTER TABLE TEST ADD `No_Process` bit(1) DEFAULT b'1';

--
-- Structure de la table `PRE_PROCESS_RESULT`
--

CREATE TABLE IF NOT EXISTS `PRE_PROCESS_RESULT` (
  `Id_Pre_Process_Result` bigint(20) NOT NULL AUTO_INCREMENT,
  `Pre_Process_Key` varchar(255) NOT NULL,
  `Pre_Process_Value` mediumtext DEFAULT NULL,
  `Id_Audit` bigint(20) NOT NULL,
  `Id_Web_Resource` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id_Pre_Process_Result`),
  UNIQUE KEY `Key_Wr_Audit` (`Pre_Process_Key`,`Id_Web_Resource`,`Id_Audit`),
  INDEX `fk_PRE_PROCESS_RESULT_AUDIT` (`Id_Audit` ASC) ,
  CONSTRAINT `fk_PRE_PROCESS_RESULT_AUDIT`
    FOREIGN KEY (`Id_Audit` )
    REFERENCES `AUDIT` (`Id_Audit` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  INDEX `fk_PRE_PROCESS_RESULT_WEB_RESOURCE` (`Id_Web_Resource` ASC) ,
  CONSTRAINT `fk_PRE_PROCESS_RESULT_WEB_RESOURCE`
    FOREIGN KEY (`Id_Web_Resource` )
    REFERENCES `WEB_RESOURCE` (`Id_Web_Resource` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;


INSERT IGNORE INTO `PARAMETER_ELEMENT` (`Id_Parameter_Element`, `Cd_Parameter_Element`, `Id_Parameter_Family`, `Long_Label`, `Short_Label`) VALUES
(38, 'DECORATIVE_IMAGE_MARKER', 3, 'Decorative image HTML marker (id or class)', 'Decorative image marker'),
(39, 'INFORMATIVE_IMAGE_MARKER', 3, 'Informative image HTML marker (id or class)', 'Informative image marker');

INSERT IGNORE INTO `PARAMETER` (`Id_Parameter_Element`, `Parameter_Value`, `Is_Default`) VALUES
(5, 'AW22;Ar', b'1'),
(5, 'AW22;Bz', b'0'),
(5, 'AW22;Or', b'0'),
(38, '', b'1'),
(39, '', b'1');

UPDATE `PARAMETER` SET `Is_Default` = b'0' WHERE `Parameter_Value`='AW21;Ar';

ALTER IGNORE TABLE PROCESS_REMARK ADD `Snippet` mediumtext NULL AFTER Target;
ALTER IGNORE TABLE EVIDENCE_ELEMENT MODIFY `Element_Value` mediumtext NOT NULL;

SET foreign_key_checks=0;

ALTER TABLE `TEST` DROP FOREIGN KEY `FK273C921355BF7C`;
ALTER TABLE `TEST` DROP `Id_Rule`;
DROP TABLE `RULE_PACKAGE`;
DROP TABLE `RULE`;

ALTER IGNORE TABLE PROCESS_RESULT MODIFY `Indefinite_Value` mediumtext DEFAULT NULL;
ALTER IGNORE TABLE CONTENT MODIFY `Adapted_Content` longtext DEFAULT NULL;
ALTER IGNORE TABLE CONTENT MODIFY `Source` longtext DEFAULT NULL;

SET foreign_key_checks=1;