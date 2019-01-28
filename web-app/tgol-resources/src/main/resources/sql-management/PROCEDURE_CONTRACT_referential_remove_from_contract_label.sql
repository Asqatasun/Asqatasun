-- -----------------------------------------------------------------
-- Remove referential to a contract
--   Contract is identified by its label
--     and user email
--   Referential is identified by its id
-- -----------------------------------------------------------------
DROP PROCEDURE IF EXISTS remove_ref_to_contract_from_contract_label;

DELIMITER |
CREATE
    DEFINER =`asqatasun`@`localhost` PROCEDURE `remove_ref_to_contract_from_contract_label`
    (
        IN contractLabel VARCHAR(255),
        IN userEmail     VARCHAR(191),
        IN refId         INT
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

    DELETE
    FROM
        `TGSI_CONTRACT_REFERENTIAL`
    WHERE
        `CONTRACT_Id_Contract` = contractId
      AND `REFERENTIAL_Id_Referential` = refId;

END |
DELIMITER ;
