-- -----------------------------------------------------------------
-- show last audits procedure
-- -----------------------------------------------------------------
use $myDatabaseName;

DROP PROCEDURE IF EXISTS last_audits;

delimiter |
CREATE PROCEDURE `last_audits`(IN nbOccurences INT)
BEGIN
  Set SQL_SELECT_LIMIT = nbOccurences;  
  SELECT a.Id_Audit, ta.Id_Act, ta.Client_Ip, a.Dt_Creation, a.Status, wr.Url, wr.DTYPE, tc.Label, tu.Email1 FROM AUDIT as a 
        LEFT JOIN WEB_RESOURCE as wr on (a.Id_Audit=wr.Id_Audit) 
        LEFT JOIN TGSI_ACT_AUDIT as taa on (a.Id_Audit=taa.AUDIT_Id_Audit)
        LEFT JOIN TGSI_ACT as ta on (taa.ACT_Id_Act=ta.Id_Act)
        LEFT JOIN TGSI_CONTRACT as tc on (ta.CONTRACT_Id_Contract=tc.Id_Contract)
        LEFT JOIN TGSI_USER as tu on (tc.USER_Id_User=tu.Id_User)
        ORDER BY a.Id_Audit DESC ;
  Set SQL_SELECT_LIMIT = Default;  
END  |
delimiter ;