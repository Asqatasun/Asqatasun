-- -----------------------------------------------------------------
-- Add functionality to a contract
--   Contract is identified by its id
--   Functionality is identified by its id
-- -----------------------------------------------------------------
DROP PROCEDURE IF EXISTS add_funct_to_contract_from_contract_id;

DELIMITER |
CREATE
    DEFINER =`asqatasun`@`localhost` PROCEDURE `add_funct_to_contract_from_contract_id`
    (
        IN contractId INT,
        IN functId    INT
    )
BEGIN
    INSERT IGNORE INTO
        `TGSI_CONTRACT_FUNCTIONALITY` (`CONTRACT_Id_Contract`, `FUNCTIONALITY_Id_Functionality`)
    VALUES (contractId, functId);
END |
DELIMITER ;
