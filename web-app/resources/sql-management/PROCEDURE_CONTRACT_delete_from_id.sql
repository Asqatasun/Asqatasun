-- -----------------------------------------------------------------
-- Delete a contract from its id
--   This also deletes all associated audits
--
-- /!\ depends on: clean_up_audit_from_contract
-- -----------------------------------------------------------------
DROP PROCEDURE IF EXISTS delete_contract_from_id;
DELIMITER |
CREATE
    DEFINER =`asqatasun`@`localhost` PROCEDURE `delete_contract_from_id`
    (
        IN contractId INT
    )
BEGIN
    CALL clean_up_audit_from_contract(contractId);
    DELETE FROM TGSI_ACT WHERE CONTRACT_Id_Contract = contractId;
    DELETE FROM TGSI_CONTRACT_REFERENTIAL WHERE CONTRACT_Id_Contract = contractId;
    DELETE FROM TGSI_CONTRACT_OPTION_ELEMENT WHERE CONTRACT_Id_Contract = contractId;
    DELETE FROM TGSI_CONTRACT_FUNCTIONALITY WHERE CONTRACT_Id_Contract = contractId;
    DELETE FROM TGSI_CONTRACT WHERE Id_Contract = contractId;
END |
DELIMITER ;
