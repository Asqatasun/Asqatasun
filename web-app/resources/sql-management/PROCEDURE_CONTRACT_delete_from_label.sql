-- -----------------------------------------------------------------
-- Delete a contract from its label
--   This also deletes all associated audits
--
-- /!\ depends on: delete_contract_from_id
-- -----------------------------------------------------------------
DROP PROCEDURE IF EXISTS delete_contract_from_label;
DELIMITER |
CREATE
    DEFINER =`asqatasun`@`localhost` PROCEDURE `delete_contract_from_label`
    (
        IN contractLabel VARCHAR(255),
        IN userEmail     VARCHAR(191)
    )
BEGIN
    DECLARE contractId bigint(20);
    SELECT
        tc.Id_Contract INTO contractId
    FROM
        TGSI_CONTRACT tc
            LEFT JOIN TGSI_USER AS tu ON (tc.USER_Id_User = tu.Id_User)
    WHERE
        tc.Label LIKE contractLabel
      AND tu.Email1 LIKE userEmail;
    CALL delete_contract_from_id(contractId);
END |
DELIMITER ;
