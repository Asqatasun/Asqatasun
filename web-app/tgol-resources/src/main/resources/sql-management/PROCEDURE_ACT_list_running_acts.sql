# List current acts (and eventually related audit) that are not completed
DROP PROCEDURE IF EXISTS list_running_acts;

DELIMITER |
CREATE PROCEDURE `list_running_acts`
    (
    )
BEGIN
    SELECT
        ta.Id_Act,
        ta.Status     AS "ACT Status",
        a.Id_Audit,
        a.Status      AS "AUDIT Status",
        wr.Url        AS "URL",
        a.Dt_Creation AS "AUDIT creation",
        ta.Begin_Date AS "ACT begin",
        ta.End_Date   AS "ACT end",
        tu.Email1,
        tu.First_Name,
        tu.Name
    FROM
        TGSI_ACT AS ta
            LEFT JOIN TGSI_ACT_AUDIT AS taa ON ta.Id_Act = taa.ACT_Id_Act
            LEFT JOIN AUDIT AS a ON taa.AUDIT_Id_Audit = a.Id_Audit
            LEFT JOIN WEB_RESOURCE AS wr ON (a.Id_Audit = wr.Id_Audit)
            INNER JOIN TGSI_CONTRACT AS tc ON ta.CONTRACT_Id_Contract = tc.Id_Contract
            INNER JOIN TGSI_USER AS tu ON tc.USER_Id_User = tu.Id_User
    WHERE
        ta.Status != "COMPLETED"
    ORDER BY ta.Begin_Date ASC;
END |
DELIMITER ;

