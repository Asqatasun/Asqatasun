-- -----------------------------------------------------------------
-- Add referential to a contract
--   Contract is identified by its id
--   Referential is identified by its id
-- -----------------------------------------------------------------
DROP PROCEDURE IF EXISTS add_ref_to_contract_from_contract_id;

DELIMITER |
CREATE
    DEFINER =`asqatasun`@`localhost` PROCEDURE `add_ref_to_contract_from_contract_id`
    (
        IN contractId INT,
        IN refId      INT
    )
BEGIN
    INSERT IGNORE INTO
        `TGSI_CONTRACT_REFERENTIAL` (`CONTRACT_Id_Contract`, `REFERENTIAL_Id_Referential`)
    VALUES (contractId, refId);
END |
DELIMITER ;
