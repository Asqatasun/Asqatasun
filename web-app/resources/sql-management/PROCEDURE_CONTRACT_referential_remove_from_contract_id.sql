-- -----------------------------------------------------------------
-- Remove referential to a contract
--   Contract is identified by its id
--   Referential is identified by its id
-- -----------------------------------------------------------------
DROP PROCEDURE IF EXISTS remove_ref_to_contract_from_contract_id;

DELIMITER |
CREATE
    DEFINER =`asqatasun`@`localhost` PROCEDURE `remove_ref_to_contract_from_contract_id`
    (
        IN contractId INT,
        IN refId      INT
    )
BEGIN
    DELETE
    FROM
        `TGSI_CONTRACT_REFERENTIAL`
    WHERE
        `CONTRACT_Id_Contract` = contractId
      AND `REFERENTIAL_Id_Referential` = refId;
END |
DELIMITER ;
