-- -----------------------------------------------------------------
-- Creation of contract with Url, Functionalities and Referentials
-- -----------------------------------------------------------------
use $myDatabaseName;

DROP PROCEDURE IF EXISTS last_audits;

delimiter |
CREATE PROCEDURE `last_audits`(IN nbOccurences INT)
BEGIN
  Set SQL_SELECT_LIMIT = nbOccurences;  
  SELECT a.Id_Audit, ta.Id_Act, ta.Client_Ip, a.Dt_Creation, a.Status, wr.Url, wr.DTYPE, tc.Label, tu.Email1 FROM AUDIT as a 
        LEFT JOIN WEB_RESOURCE as wr on (a.Id_Audit=wr.Id_Audit) 
        LEFT JOIN TGSI_ACT_WEB_RESOURCE as tawr on (wr.Id_Web_Resource=tawr.WEB_RESOURCE_Id_Web_Resource)
        LEFT JOIN TGSI_ACT as ta on (tawr.ACT_Id_Act=ta.Id_Act)
        LEFT JOIN TGSI_CONTRACT as tc on (ta.CONTRACT_Id_Contract=tc.Id_Contract)
        LEFT JOIN TGSI_USER as tu on (tc.USER_Id_User=tu.Id_User)
            
        WHERE tc.Id_Contract=17 AND (wr.Url like "%univ-renne2%" OR wr.Url like "%impots.gouv.fr/portal/dgi/home%" OR wr.Url like "%accessibilite-numerique.org/%") ORDER BY a.Id_Audit DESC ;
  Set SQL_SELECT_LIMIT = Default;  
END  |
delimiter ;

delimiter |
CREATE PROCEDURE `resources_counter`(IN auditId INT)
BEGIN
  DECLARE wrId bigint(20);
  SELECT Id_Web_Resource INTO wrId FROM WEB_RESOURCE WHERE Id_Audit=auditId;
  SELECT count(Id_Web_Resource) FROM WEB_RESOURCE WHERE Id_Web_Resource_Parent=wrId;
        
END  |
delimiter ;