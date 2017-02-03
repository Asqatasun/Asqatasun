-- -----------------------------------------------------------------
-- show last audits procedure
-- -----------------------------------------------------------------
DROP PROCEDURE IF EXISTS last_audits;

DELIMITER |
CREATE PROCEDURE `last_audits`(IN nbOccurences INT)
    BEGIN
        SET SQL_SELECT_LIMIT = nbOccurences;
        SELECT
            a.Id_Audit,
            ta.Id_Act,
            ta.Client_Ip,
            a.Dt_Creation,
            a.Status,
            wr.Url,
            wr.DTYPE,
            tc.Label,
            tu.Email1
        FROM AUDIT AS a
            LEFT JOIN WEB_RESOURCE AS wr ON (a.Id_Audit = wr.Id_Audit)
            LEFT JOIN TGSI_ACT_AUDIT AS taa ON (a.Id_Audit = taa.AUDIT_Id_Audit)
            LEFT JOIN TGSI_ACT AS ta ON (taa.ACT_Id_Act = ta.Id_Act)
            LEFT JOIN TGSI_CONTRACT AS tc ON (ta.CONTRACT_Id_Contract = tc.Id_Contract)
            LEFT JOIN TGSI_USER AS tu ON (tc.USER_Id_User = tu.Id_User)
        ORDER BY a.Id_Audit DESC;
        SET SQL_SELECT_LIMIT = Default;
    END  |
DELIMITER ;