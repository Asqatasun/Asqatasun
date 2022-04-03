-- -----------------------------------------------------------------
-- Delete audit from id
-- -----------------------------------------------------------------
DROP PROCEDURE IF EXISTS delete_audit_from_id;

DELIMITER |
CREATE PROCEDURE `delete_audit_from_id` (IN auditId INT)
BEGIN
    UPDATE ACT
        LEFT JOIN ACT_AUDIT AS aa
            ON ACT.Id_Act = aa.ACT_Id_Act
        LEFT JOIN AUDIT AS a 
            ON aa.AUDIT_Id_Audit = a.Id_Audit
    SET
        ACT.Status = 'DELETED'
    WHERE
        a.Id_Audit = auditId;

    DELETE FROM AUDIT WHERE Id_Audit = auditId;
END |
DELIMITER ;










