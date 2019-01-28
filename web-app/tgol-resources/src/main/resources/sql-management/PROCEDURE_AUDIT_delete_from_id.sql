-- -----------------------------------------------------------------
-- Delete audit from id
-- -----------------------------------------------------------------
DROP PROCEDURE IF EXISTS delete_audit_from_id;

DELIMITER |
CREATE PROCEDURE `delete_audit_from_id`
    (
        IN auditId INT
    )
BEGIN
    UPDATE TGSI_ACT AS ta
        LEFT JOIN TGSI_ACT_AUDIT AS taa ON (ta.Id_Act = taa.ACT_Id_Act)
        LEFT JOIN AUDIT AS a ON (taa.AUDIT_Id_Audit = a.Id_Audit)
        -- w.Id_Web_Resource = tawr.WEB_RESOURCE_Id_Web_Resource)
    SET
        ta.Status = 'DELETED'
    WHERE
        a.Id_Audit = auditId;

    DELETE FROM AUDIT WHERE Id_Audit = auditId;
END |
DELIMITER ;
