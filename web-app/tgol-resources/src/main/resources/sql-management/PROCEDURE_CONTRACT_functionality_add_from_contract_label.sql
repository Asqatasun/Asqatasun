-- -----------------------------------------------------------------
-- Add functionality to a contract
--   Contract is identified by its label
--     and user email
--   Functionality is identified by its id
-- -----------------------------------------------------------------
DROP PROCEDURE IF EXISTS add_funct_to_contract_from_contract_label;

DELIMITER |
CREATE
    DEFINER =`asqatasun`@`localhost` PROCEDURE `add_funct_to_contract_from_contract_label`
    (
        IN contractLabel VARCHAR(255),
        IN userEmail     VARCHAR(191),
        IN functId       INT
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

    INSERT IGNORE INTO
        `TGSI_CONTRACT_FUNCTIONALITY`
        (`CONTRACT_Id_Contract`, `FUNCTIONALITY_Id_Functionality`)
    VALUES (contractId, functId);
END |
DELIMITER ;
