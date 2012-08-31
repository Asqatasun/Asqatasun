-- -----------------------------------------------------------------
-- Add and remove Functionalities and Referential to contracts
-- -----------------------------------------------------------------
use $myDatabaseName;

DROP PROCEDURE IF EXISTS add_funct_to_contract_from_contract_id;
DROP PROCEDURE IF EXISTS remove_funct_to_contract_from_contract_id;
DROP PROCEDURE IF EXISTS add_funct_to_contract_from_contract_label;
DROP PROCEDURE IF EXISTS remove_funct_to_contract_from_contract_label;
DROP PROCEDURE IF EXISTS add_funct_to_contract_from_user_email;
DROP PROCEDURE IF EXISTS remove_funct_to_contract_from_user_email;
DROP PROCEDURE IF EXISTS add_ref_to_contract_from_contract_id;
DROP PROCEDURE IF EXISTS remove_ref_to_contract_from_contract_id;
DROP PROCEDURE IF EXISTS add_ref_to_contract_from_user_email;
DROP PROCEDURE IF EXISTS remove_ref_to_contract_from_user_email;
DROP PROCEDURE IF EXISTS add_ref_to_contract_from_contract_label;
DROP PROCEDURE IF EXISTS remove_ref_to_contract_from_contract_label;

delimiter |
CREATE DEFINER=`$myDatabaseUser`@`localhost` PROCEDURE `add_funct_to_contract_from_contract_id`(
IN contractId INT, 
IN functId INT)
BEGIN

    INSERT IGNORE INTO `TGSI_CONTRACT_FUNCTIONALITY` (`CONTRACT_Id_Contract`, `FUNCTIONALITY_Id_Functionality`) VALUES (contractId,functId);

END  |

CREATE DEFINER=`$myDatabaseUser`@`localhost` PROCEDURE `remove_funct_to_contract_from_contract_id`(
IN contractId INT, 
IN functId INT)
BEGIN

    DELETE FROM `TGSI_CONTRACT_FUNCTIONALITY` WHERE `CONTRACT_Id_Contract`=contractId AND `FUNCTIONALITY_Id_Functionality`=functId;

END  |

CREATE DEFINER=`$myDatabaseUser`@`localhost` PROCEDURE `add_funct_to_contract_from_contract_label`(
IN contractLabel VARCHAR(255), 
IN userEmail VARCHAR(255), 
IN functId INT)
BEGIN

    DECLARE contractId bigint(20);  

    SELECT tc.Id_Contract INTO contractId FROM TGSI_CONTRACT tc 
        LEFT JOIN TGSI_USER as tu on (tc.USER_Id_User=tu.Id_User)
            WHERE tc.Label like contractLabel
            AND tu.Email1 like userEmail;

    INSERT IGNORE INTO `TGSI_CONTRACT_FUNCTIONALITY` 
        (`CONTRACT_Id_Contract`, `FUNCTIONALITY_Id_Functionality`) 
        VALUES (contractId,functId);

END  |

CREATE DEFINER=`$myDatabaseUser`@`localhost` PROCEDURE `remove_funct_to_contract_from_contract_label`(
IN contractLabel VARCHAR(255), 
IN userEmail VARCHAR(255), 
IN functId INT)
BEGIN

    DECLARE contractId bigint(20);  

    SELECT tc.Id_Contract INTO contractId FROM TGSI_CONTRACT tc 
        LEFT JOIN TGSI_USER as tu on (tc.USER_Id_User=tu.Id_User) 
            WHERE tc.Label like contractLabel
            AND tu.Email1 like userEmail; 

    DELETE FROM `TGSI_CONTRACT_FUNCTIONALITY` 
            WHERE `CONTRACT_Id_Contract`=contractId 
            AND `FUNCTIONALITY_Id_Functionality`=functId;

END  |

CREATE DEFINER=`$myDatabaseUser`@`localhost` PROCEDURE `add_funct_to_contract_from_user_email`(
IN userEmail VARCHAR(255), 
IN functId INT)
BLOCK1: BEGIN

    DECLARE done INT DEFAULT 0;
    DECLARE v_Id_Contract BIGINT(20);
    DECLARE contracts CURSOR FOR SELECT tc.Id_Contract FROM TGSI_CONTRACT tc 
        LEFT JOIN TGSI_USER as tu on (tc.USER_Id_User=tu.Id_User) 
            WHERE tu.Email1=userEmail;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    OPEN contracts;
    LOOP1: loop
        FETCH contracts INTO v_Id_Contract;
        IF done = 1 THEN
            LEAVE LOOP1;
        END IF;
        INSERT IGNORE INTO `TGSI_CONTRACT_FUNCTIONALITY` 
            (`CONTRACT_Id_Contract`, `FUNCTIONALITY_Id_Functionality`) 
            VALUES (v_Id_Contract,functId);
    END loop LOOP1;

END BLOCK1 |

CREATE DEFINER=`$myDatabaseUser`@`localhost` PROCEDURE `remove_funct_to_contract_from_user_email`(
IN userEmail VARCHAR(255), 
IN functId INT)
BLOCK1: BEGIN

    DECLARE done INT DEFAULT 0;
    DECLARE v_Id_Contract BIGINT(20);
    DECLARE contracts CURSOR FOR SELECT tc.Id_Contract FROM TGSI_CONTRACT tc 
        LEFT JOIN TGSI_USER as tu on (tc.USER_Id_User=tu.Id_User) 
            WHERE tu.Email1=userEmail;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    OPEN contracts;
    LOOP1: loop
        FETCH contracts INTO v_Id_Contract;
        IF done = 1 THEN
            LEAVE LOOP1;
        END IF;
        DELETE FROM `TGSI_CONTRACT_FUNCTIONALITY` 
                WHERE `CONTRACT_Id_Contract`=v_Id_Contract 
                AND `FUNCTIONALITY_Id_Functionality`=functId;
    END loop LOOP1;

END BLOCK1 |

CREATE DEFINER=`$myDatabaseUser`@`localhost` PROCEDURE `add_ref_to_contract_from_contract_id`(
IN contractId INT, 
IN refId INT)
BEGIN

    INSERT IGNORE INTO `TGSI_CONTRACT_REFERENTIAL` (`CONTRACT_Id_Contract`, `REFERENTIAL_Id_Referential`) VALUES (contractId,refId);

END  |

CREATE DEFINER=`$myDatabaseUser`@`localhost` PROCEDURE `remove_ref_to_contract_from_contract_id`(
IN contractId INT, 
IN refId INT)
BEGIN

    DELETE FROM `TGSI_CONTRACT_REFERENTIAL` WHERE `CONTRACT_Id_Contract`=contractId AND `REFERENTIAL_Id_Referential`=refId;

END  |

CREATE DEFINER=`$myDatabaseUser`@`localhost` PROCEDURE `add_ref_to_contract_from_contract_label`(
IN contractLabel VARCHAR(255), 
IN userEmail VARCHAR(255), 
IN refId INT)
BEGIN

    DECLARE contractId bigint(20);  

    SELECT tc.Id_Contract INTO contractId FROM TGSI_CONTRACT tc 
        LEFT JOIN TGSI_USER as tu on (tc.USER_Id_User=tu.Id_User)
            WHERE tc.Label like contractLabel
            AND tu.Email1 like userEmail;

    INSERT IGNORE INTO `TGSI_CONTRACT_REFERENTIAL` 
        (`CONTRACT_Id_Contract`, `REFERENTIAL_Id_Referential`) 
        VALUES (contractId,refId);

END  |

CREATE DEFINER=`$myDatabaseUser`@`localhost` PROCEDURE `remove_ref_to_contract_from_contract_label`(
IN contractLabel VARCHAR(255), 
IN userEmail VARCHAR(255), 
IN refId INT)
BEGIN

    DECLARE contractId bigint(20);  

    SELECT tc.Id_Contract INTO contractId FROM TGSI_CONTRACT tc 
        LEFT JOIN TGSI_USER as tu on (tc.USER_Id_User=tu.Id_User) 
            WHERE tc.Label like contractLabel
            AND tu.Email1 like userEmail; 

    DELETE FROM `TGSI_CONTRACT_REFERENTIAL` 
            WHERE `CONTRACT_Id_Contract`=contractId 
            AND `REFERENTIAL_Id_Referential`=refId;

END  |

CREATE DEFINER=`$myDatabaseUser`@`localhost` PROCEDURE `add_ref_to_contract_from_user_email`(
IN userEmail VARCHAR(255), 
IN refId INT)
BLOCK1: BEGIN

    DECLARE done INT DEFAULT 0;
    DECLARE v_Id_Contract BIGINT(20);
    DECLARE contracts CURSOR FOR SELECT tc.Id_Contract FROM TGSI_CONTRACT tc 
        LEFT JOIN TGSI_USER as tu on (tc.USER_Id_User=tu.Id_User) 
            WHERE tu.Email1=userEmail;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    OPEN contracts;
    LOOP1: loop
        FETCH contracts INTO v_Id_Contract;
        IF done = 1 THEN
            LEAVE LOOP1;
        END IF;
        INSERT IGNORE INTO `TGSI_CONTRACT_REFERENTIAL` 
            (`CONTRACT_Id_Contract`, `REFERENTIAL_Id_Referential`) 
            VALUES (v_Id_Contract,refId);
    END loop LOOP1;

END BLOCK1 |

CREATE DEFINER=`$myDatabaseUser`@`localhost` PROCEDURE `remove_ref_to_contract_from_user_email`(
IN userEmail VARCHAR(255), 
IN refId INT)
BLOCK1: BEGIN

    DECLARE done INT DEFAULT 0;
    DECLARE v_Id_Contract BIGINT(20);
    DECLARE contracts CURSOR FOR SELECT tc.Id_Contract FROM TGSI_CONTRACT tc 
        LEFT JOIN TGSI_USER as tu on (tc.USER_Id_User=tu.Id_User) 
            WHERE tu.Email1=userEmail;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    OPEN contracts;
    LOOP1: loop
        FETCH contracts INTO v_Id_Contract;
        IF done = 1 THEN
            LEAVE LOOP1;
        END IF;
        DELETE FROM `TGSI_CONTRACT_REFERENTIAL` 
                WHERE `CONTRACT_Id_Contract`=v_Id_Contract 
                AND `REFERENTIAL_Id_Referential`=refId;
    END loop LOOP1;

END BLOCK1 |

delimiter ;