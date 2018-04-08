DROP PROCEDURE IF EXISTS delete_all_audit;
DROP PROCEDURE IF EXISTS clean_up_audit_from_contract;
DROP PROCEDURE IF EXISTS clean_up_audit_from_user_id;
DROP PROCEDURE IF EXISTS clean_up_audit_from_user_email;

delimiter |

-- -----------------------------------------------------------------
-- Delete all audits
-- -----------------------------------------------------------------
CREATE DEFINER=`$myDatabaseUser`@`localhost` PROCEDURE `delete_all_audit`()
BEGIN

    DELETE FROM AUDIT;

END  |

-- -----------------------------------------------------------------
-- Clean up audit from contract
--
-- /!\ depends on: delete_audit_from_id
-- -----------------------------------------------------------------
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

-- -----------------------------------------------------------------
-- Clean up audit from user_id
--
-- /!\ depends on: delete_audit_from_id
-- -----------------------------------------------------------------
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

-- -----------------------------------------------------------------
-- Clean up audit from user email
-- -----------------------------------------------------------------
CREATE DEFINER=`$myDatabaseUser`@`localhost` PROCEDURE `clean_up_audit_from_user_email`(
IN userEmail VARCHAR(191))
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