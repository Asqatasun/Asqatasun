# List current acts (and eventually related audit) that are not completed
DROP PROCEDURE IF EXISTS list_running_acts;

delimiter |
CREATE PROCEDURE `list_running_acts`()
  BEGIN
    select
      ta.Id_Act,
      ta.Status as "ACT Status",
      a.Id_Audit,
      a.Status as "AUDIT Status",
      wr.Url as "URL",
      a.Dt_Creation as "AUDIT creation",
      ta.Begin_Date as "ACT begin",
      ta.End_Date as "ACT end",
      tu.Email1,
      tu.First_Name,
      tu.Name
    FROM
      TGSI_ACT as ta
      LEFT JOIN TGSI_ACT_AUDIT as taa ON ta.Id_Act = taa.ACT_Id_Act
      LEFT JOIN AUDIT as a on taa.AUDIT_Id_Audit = a.Id_Audit
      LEFT JOIN WEB_RESOURCE as wr on (a.Id_Audit=wr.Id_Audit)
      INNER JOIN TGSI_CONTRACT as tc ON ta.CONTRACT_Id_Contract = tc.Id_Contract
      INNER JOIN TGSI_USER as tu ON tc.USER_Id_User = tu.Id_User
    WHERE
      ta.Status != "COMPLETED"
    ORDER BY ta.Begin_Date ASC
    ;
  END  |
delimiter ;

