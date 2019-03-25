-- -----------------------------------------------------------------
-- Remove functionality to contracts
--   Contracts are identified by the user email they are associated with
--   Functionality is identified by its id
-- -----------------------------------------------------------------
DROP PROCEDURE IF EXISTS remove_funct_to_contract_from_user_email;

DELIMITER |
CREATE
    DEFINER =`asqatasun`@`localhost` PROCEDURE `remove_funct_to_contract_from_user_email`
    (
        IN userEmail VARCHAR(191),
        IN functId   INT
    )
BLOCK1:
BEGIN

    DECLARE done INT DEFAULT 0;
    DECLARE v_Id_Contract BIGINT(20);
    DECLARE contracts CURSOR FOR SELECT
                                     tc.Id_Contract
                                 FROM
                                     TGSI_CONTRACT tc
                                         LEFT JOIN TGSI_USER AS tu ON (tc.USER_Id_User = tu.Id_User)
                                 WHERE
                                     tu.Email1 = userEmail;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    OPEN contracts;
    LOOP1:
        LOOP
            FETCH contracts INTO v_Id_Contract;
            IF done = 1 THEN
                LEAVE LOOP1;
            END IF;
            DELETE
            FROM
                `TGSI_CONTRACT_FUNCTIONALITY`
            WHERE
                `CONTRACT_Id_Contract` = v_Id_Contract
              AND `FUNCTIONALITY_Id_Functionality` = functId;
        END LOOP LOOP1;

END BLOCK1 |

DELIMITER ;
