-- -----------------------------------------------------------------
-- Deletion of contract 
-- -----------------------------------------------------------------
use $myDatabaseName;

DROP PROCEDURE IF EXISTS delete_contract_from_id;
DROP PROCEDURE IF EXISTS delete_contract_from_label;

delimiter |
CREATE DEFINER=`$myDatabaseUser`@`localhost` PROCEDURE `delete_contract_from_id`(
IN contractId INT)
BEGIN

    call clean_up_audit_from_contract(contractId);
    DELETE FROM TGSI_ACT WHERE CONTRACT_Id_Contract=contractId;
    DELETE FROM TGSI_CONTRACT_REFERENTIAL WHERE CONTRACT_Id_Contract=contractId;
    DELETE FROM TGSI_CONTRACT_OPTION_ELEMENT WHERE CONTRACT_Id_Contract=contractId;
    DELETE FROM TGSI_CONTRACT_FUNCTIONALITY WHERE CONTRACT_Id_Contract=contractId;
    DELETE FROM TGSI_CONTRACT WHERE Id_Contract=contractId;

END  |

CREATE DEFINER=`$myDatabaseUser`@`localhost` PROCEDURE `delete_contract_from_label`(
IN contractLabel VARCHAR(255), 
IN userEmail VARCHAR(255))
BEGIN

    DECLARE contractId bigint(20);  

    SELECT tc.Id_Contract INTO contractId FROM TGSI_CONTRACT tc 
        LEFT JOIN TGSI_USER as tu on (tc.USER_Id_User=tu.Id_User)
            WHERE tc.Label like contractLabel
            AND tu.Email1 like userEmail;
    
    call delete_contract_from_id(contractId);
            
END  |

delimiter ;