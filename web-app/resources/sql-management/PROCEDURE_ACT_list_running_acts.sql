# List current acts (and eventually related audit) that are not completed
DROP PROCEDURE IF EXISTS list_running_acts;

DELIMITER |
create PROCEDURE `list_running_acts`()
BEGIN
    select
        ACT.Id_Act,
        ACT.Status      AS "ACT Status",
        a.Id_Audit,
        a.Status        AS "AUDIT Status",
        wr.Url          AS "URL",
        a.Dt_Creation   AS "AUDIT creation",
        ACT.Begin_Date  AS "ACT begin",
        ACT.End_Date    AS "ACT end",
        u.Email1,
        u.First_Name,
        u.Name
    FROM ACT
        LEFT JOIN ACT_AUDIT AS aa
            ON ACT.Id_Act = aa.ACT_Id_Act
        LEFT JOIN AUDIT AS a
            ON aa.AUDIT_Id_Audit = a.Id_Audit
        LEFT JOIN WEB_RESOURCE AS wr
            ON a.Id_Audit = wr.Id_Audit
        INNER JOIN CONTRACT AS c
            ON ACT.CONTRACT_Id_Contract = c.Id_Contract
        INNER JOIN USER AS u
            ON c.USER_Id_User = u.Id_User
    WHERE
        ACT.Status != "COMPLETED"
    ORDER BY ACT.Begin_Date ASC;
END |
DELIMITER ;

