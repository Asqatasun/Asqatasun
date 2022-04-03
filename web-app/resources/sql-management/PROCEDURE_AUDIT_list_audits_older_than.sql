-- -----------------------------------------------------------------
-- list audits older than N months
-- -----------------------------------------------------------------
DROP PROCEDURE IF EXISTS list_audits_older_than;

DELIMITER |
CREATE PROCEDURE `list_audits_older_than` (IN timeSpan INT)
BEGIN
    SELECT
        a.Id_Audit,
        ACT.Id_Act,
        ACT.Client_Ip,
        a.Dt_Creation   as "Creation date",
        a.Status,
        wr.Url,
        wr.DTYPE        as "Audit type",
        c.Label         as "Contract",
        u.Email1        as Email
    FROM
        AUDIT AS a
            LEFT JOIN WEB_RESOURCE AS wr
                      ON a.Id_Audit = wr.Id_Audit
            LEFT JOIN ACT_AUDIT AS aa
                      ON a.Id_Audit = aa.AUDIT_Id_Audit
            LEFT JOIN ACT
                      ON aa.ACT_Id_Act = ACT.Id_Act
            LEFT JOIN CONTRACT AS c
                      ON ACT.CONTRACT_Id_Contract = c.Id_Contract
            LEFT JOIN USER AS u
                      ON c.USER_Id_User = u.Id_User
    WHERE
            a.Dt_Creation <= DATE_SUB(date(now()), INTERVAL timeSpan MONTH)
    ORDER BY a.Id_Audit DESC;
END |
DELIMITER ;
