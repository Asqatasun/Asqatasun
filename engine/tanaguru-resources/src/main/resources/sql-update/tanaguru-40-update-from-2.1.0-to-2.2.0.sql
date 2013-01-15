-- -----------------------------------------------------------------
-- Creation of relation between contract and Url (through Option)
-- -----------------------------------------------------------------
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


call create_criterion_statistics();

ALTER TABLE WEB_RESOURCE_STATISTICS ADD `Nb_Suspected` int(11) DEFAULT NULL AFTER Nb_Na;
ALTER TABLE WEB_RESOURCE_STATISTICS ADD `Nb_Detected` int(11) DEFAULT NULL AFTER Nb_Suspected;
ALTER TABLE WEB_RESOURCE_STATISTICS ADD `Nb_Not_Tested` int(11) DEFAULT NULL AFTER Nb_Detected;

ALTER TABLE TEST_STATISTICS ADD `Nb_Suspected` int(11) DEFAULT NULL AFTER Nb_Na;
ALTER TABLE TEST_STATISTICS ADD `Nb_Detected` int(11) DEFAULT NULL AFTER Nb_Suspected;
ALTER TABLE TEST_STATISTICS ADD `Nb_Not_Tested` int(11) DEFAULT NULL AFTER Nb_Detected;

ALTER TABLE THEME_STATISTICS ADD `Nb_Suspected` int(11) DEFAULT NULL AFTER Nb_Na;
ALTER TABLE THEME_STATISTICS ADD `Nb_Detected` int(11) DEFAULT NULL AFTER Nb_Suspected;
ALTER TABLE THEME_STATISTICS ADD `Nb_Not_Tested` int(11) DEFAULT NULL AFTER Nb_Detected;

ALTER TABLE CRITERION_STATISTICS ADD `Nb_Suspected` int(11) DEFAULT NULL AFTER Nb_Na;
ALTER TABLE CRITERION_STATISTICS ADD `Nb_Detected` int(11) DEFAULT NULL AFTER Nb_Suspected;
ALTER TABLE CRITERION_STATISTICS ADD `Nb_Not_Tested` int(11) DEFAULT NULL AFTER Nb_Detected;