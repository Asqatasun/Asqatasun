-- -----------------------------------------------------------------
-- Delete audits of a given user email
--
-- /!\ depends on: delete_audit_from_id
-- -----------------------------------------------------------------
DROP PROCEDURE IF EXISTS clean_up_audit_from_user_email;

DELIMITER |
CREATE
    DEFINER =`asqatasun`@`localhost` PROCEDURE `clean_up_audit_from_user_email`
    (
        IN userEmail VARCHAR(191)
    )
BEGIN
    DECLARE done INT DEFAULT 0;
    DECLARE auditId BIGINT(20);
    DECLARE audits CURSOR FOR SELECT
                                  a.Id_Audit
                              FROM
                                  WEB_RESOURCE AS wr
                                      LEFT JOIN AUDIT AS a ON (wr.Id_Audit = a.Id_Audit)
                                      LEFT JOIN TGSI_ACT_WEB_RESOURCE AS taw
                                                ON (taw.WEB_RESOURCE_Id_Web_Resource = wr.Id_Web_Resource)
                                      LEFT JOIN TGSI_ACT AS ta ON (ta.Id_Act = taw.ACT_Id_Act)
                                      LEFT JOIN TGSI_CONTRACT AS tc ON (tc.Id_Contract = ta.CONTRACT_Id_Contract)
                                      LEFT JOIN TGSI_USER AS tu ON (tu.Id_User = tc.USER_Id_User)
                              WHERE
                                  tu.Email1 = userEmail;
    DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;

    OPEN audits;
    LOOP1:
        LOOP
            FETCH audits INTO auditId;
            IF done = 1 THEN
                LEAVE LOOP1;
            END IF;
            CALL delete_audit_from_id(auditId);
        END LOOP LOOP1;
END |
DELIMITER ;
