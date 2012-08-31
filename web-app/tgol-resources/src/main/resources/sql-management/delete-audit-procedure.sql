-- -----------------------------------------------------------------
-- Deletion of contract 
-- -----------------------------------------------------------------
use $myDatabaseName;

DROP PROCEDURE IF EXISTS delete_audit_from_id;
DROP PROCEDURE IF EXISTS delete_all_audit;
DROP PROCEDURE IF EXISTS clean_up_audit_from_contract;
DROP PROCEDURE IF EXISTS clean_up_audit_from_user_id;
DROP PROCEDURE IF EXISTS clean_up_audit_from_user_email;

delimiter |

CREATE DEFINER=`$myDatabaseUser`@`localhost` PROCEDURE `delete_all_audit`()
BEGIN

    DELETE FROM AUDIT;

END  |

CREATE DEFINER=`$myDatabaseUser`@`localhost` PROCEDURE `delete_audit_from_id`(
IN auditId INT)
BEGIN

    UPDATE TGSI_ACT as ta 
        LEFT JOIN TGSI_ACT_WEB_RESOURCE as tawr on (ta.Id_Act=tawr.ACT_Id_Act) 
        LEFT JOIN WEB_RESOURCE as w on (w.Id_Web_Resource=tawr.WEB_RESOURCE_Id_Web_Resource) 
            SET Status='DELETED' 
                WHERE w.Id_Audit=auditId;

    DELETE FROM AUDIT WHERE Id_Audit=auditId;

END  |

CREATE DEFINER=`$myDatabaseUser`@`localhost` PROCEDURE `clean_up_audit_from_contract`(
IN contractId INT)
BEGIN

    DECLARE done INT DEFAULT 0;
    DECLARE auditId BIGINT(20);

    DECLARE audits CURSOR FOR SELECT a.Id_Audit FROM WEB_RESOURCE as wr 
        LEFT JOIN AUDIT as a on (wr.Id_Audit=a.Id_Audit) 
        LEFT JOIN TGSI_ACT_WEB_RESOURCE as taw on (taw.WEB_RESOURCE_Id_Web_Resource=wr.Id_Web_Resource) 
        LEFT JOIN TGSI_ACT as ta on (ta.Id_Act=taw.ACT_Id_Act) 
            WHERE ta.CONTRACT_Id_Contract=contractId;

    DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;

    OPEN audits;
    LOOP1: loop
        FETCH audits INTO auditId;
        IF done = 1 THEN
            LEAVE LOOP1;
        END IF;
        call delete_audit_from_id(auditId);
    END loop LOOP1;

END |

CREATE DEFINER=`$myDatabaseUser`@`localhost` PROCEDURE `clean_up_audit_from_user_id`(
IN userId INT)
BEGIN

    DECLARE done INT DEFAULT 0;
    DECLARE auditId BIGINT(20);

    DECLARE audits CURSOR FOR SELECT a.Id_Audit FROM WEB_RESOURCE as wr 
        LEFT JOIN AUDIT as a on (wr.Id_Audit=a.Id_Audit) 
        LEFT JOIN TGSI_ACT_WEB_RESOURCE as taw on (taw.WEB_RESOURCE_Id_Web_Resource=wr.Id_Web_Resource) 
        LEFT JOIN TGSI_ACT as ta on (ta.Id_Act=taw.ACT_Id_Act)
        LEFT JOIN TGSI_CONTRACT as tc on (tc.Id_Contract=ta.CONTRACT_Id_Contract)
            WHERE tc.USER_Id_User=userId;

    DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;

    OPEN audits;
    LOOP1: loop
        FETCH audits INTO auditId;
        IF done = 1 THEN
            LEAVE LOOP1;
        END IF;
        call delete_audit_from_id(auditId);
    END loop LOOP1;

END |

CREATE DEFINER=`$myDatabaseUser`@`localhost` PROCEDURE `clean_up_audit_from_user_email`(
IN userEmail VARCHAR(255))
BEGIN

    DECLARE done INT DEFAULT 0;
    DECLARE auditId BIGINT(20);
    DECLARE userId BIGINT(20);

    DECLARE audits CURSOR FOR SELECT a.Id_Audit FROM WEB_RESOURCE as wr 
        LEFT JOIN AUDIT as a on (wr.Id_Audit=a.Id_Audit) 
        LEFT JOIN TGSI_ACT_WEB_RESOURCE as taw on (taw.WEB_RESOURCE_Id_Web_Resource=wr.Id_Web_Resource) 
        LEFT JOIN TGSI_ACT as ta on (ta.Id_Act=taw.ACT_Id_Act)
        LEFT JOIN TGSI_CONTRACT as tc on (tc.Id_Contract=ta.CONTRACT_Id_Contract)
        LEFT JOIN TGSI_USER as tu on (tu.Id_User=tc.USER_Id_User)
            WHERE tu.Email1=userEmail;

    DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;

    OPEN audits;
    LOOP1: loop
        FETCH audits INTO auditId;
        IF done = 1 THEN
            LEAVE LOOP1;
        END IF;
        call delete_audit_from_id(auditId);
    END loop LOOP1;

END |

delimiter ;