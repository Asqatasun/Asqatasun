-- -----------------------------------------------------------------
-- Remove functionality to a contract
--   Contract is identified by its id
--   Functionality is identified by its id
-- -----------------------------------------------------------------
DROP PROCEDURE IF EXISTS remove_funct_to_contract_from_contract_id;

DELIMITER |
CREATE
    DEFINER =`asqatasun`@`localhost` PROCEDURE `remove_funct_to_contract_from_contract_id`
    (
        IN contractId INT,
        IN functId    INT
    )
BEGIN
    DELETE
    FROM
        `TGSI_CONTRACT_FUNCTIONALITY`
    WHERE
        `CONTRACT_Id_Contract` = contractId
      AND `FUNCTIONALITY_Id_Functionality` = functId;
END |
DELIMITER ;
