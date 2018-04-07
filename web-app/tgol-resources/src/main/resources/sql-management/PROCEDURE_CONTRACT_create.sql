-- -----------------------------------------------------------------
-- Creation of contract with Url, Functionalities and Referentials
-- -----------------------------------------------------------------

-- Example of call:
--  call contract_create($UserId, \"$SiteLabel\", \"$URL\", $referential, $audit-page, $audit-site, $audit-file, $audit-scenario, $audit-manual, $maxDoc, $timeSpan, $timeSpanUnit);
--  call contract_create(1, "Wikipedia RGAA3", "https://en.wikipedia.org/", "RGAA3", 1, 1, 1, 1, 1, 100,  NULL, NULL);
--  call contract_create(1, "Wikipedia SEO",   "https://en.wikipedia.org/", "SEO",   0, 1, 0, 0, 0, 1000, NULL, NULL);
--  call contract_create(1, "Wikipedia SEO",   "https://en.wikipedia.org/", "SEO",   0, 1, 0, 0, 0, 1000, 1,   'YEAR');
--  call contract_create(1, "Wikipedia SEO",   "https://en.wikipedia.org/", "SEO",   0, 1, 0, 0, 0, 1000, 2,   'MONTH');
--  call contract_create(1, "Wikipedia SEO",   "https://en.wikipedia.org/", "SEO",   0, 1, 0, 0, 0, 1000, 7,   'DAY');


DROP PROCEDURE IF EXISTS contract_create;

DELIMITER |
CREATE PROCEDURE `contract_create`(
    IN idUser         INT,
    IN label          VARCHAR(191),
    IN url            VARCHAR(1024),
    IN referential    VARCHAR(10),
    IN audit_page     BOOLEAN,
    IN audit_site     BOOLEAN,
    IN audit_file     BOOLEAN,
    IN audit_scenario BOOLEAN,
    IN audit_manual   BOOLEAN,
    IN maxDoc         VARCHAR(5),
    IN timeSpan       INT,
    IN timeSpanUnit   VARCHAR(5)
)

    BEGIN

        -- CONSTANTS default values
        DECLARE default_timeSpan     INT        DEFAULT  3;
        DECLARE default_timeSpanUnit VARCHAR(5) DEFAULT 'YEAR';

        -- CONSTANTS hard-coded values from table TGSI_REFERENTIAL
        DECLARE referential_id_RGAA3 INT DEFAULT 2;
        DECLARE referential_id_SEO INT DEFAULT 3;
        DECLARE referential_id_RGAA32016 INT DEFAULT 4;
        DECLARE referential_id_RGAA32017 INT DEFAULT 5;

        -- CONSTANTS hard-coded values from table TGSI_FUNCTIONALITY
        DECLARE audit_type_id_page INT DEFAULT 1;
        DECLARE audit_type_id_site INT DEFAULT 2;
        DECLARE audit_type_id_file INT DEFAULT 3;
        DECLARE audit_type_id_scenario INT DEFAULT 4;
        DECLARE audit_type_id_manual INT DEFAULT 5;

        -- CONSTANTS hard-coded values from table TGSI_OPTION_ELEMENT
        DECLARE c_OPTION_Id_Option INT DEFAULT 3;

        -- Variables actually used
        DECLARE v_Id_Option_Element  BIGINT(20);
        DECLARE v_Id_Option_Element2 BIGINT(20);
        DECLARE contractId           BIGINT(20);
        DECLARE beginDate            DATE;
        DECLARE endDate              DATE;

        -- Duration contract unit : YEAR, MONTH or DAY
        IF timeSpanUnit != 'YEAR' AND timeSpanUnit != 'MONTH' AND timeSpanUnit != 'DAY'
        THEN
            SET timeSpan     = NULL ;
            SET timeSpanUnit = default_timeSpanUnit ;
        END IF;

        -- Duration contract
        IF timeSpan IS NULL OR timeSpan < 1
        THEN
            SET timeSpan     = default_timeSpan ;
            SET timeSpanUnit = default_timeSpanUnit ;
        END IF;

        -- Begin and End dates of contract
        SET beginDate = date(now());
        IF     timeSpanUnit = 'YEAR'    THEN
            SET endDate = date_add(date(now()), INTERVAL timeSpan YEAR);
        ELSEIF timeSpanUnit = 'MONTH'   THEN
            SET endDate = date_add(date(now()), INTERVAL timeSpan MONTH);
        ELSEIF timeSpanUnit = 'DAY'     THEN
            SET endDate = date_add(date(now()), INTERVAL timeSpan DAY);
        END IF;

        -- Contract
        INSERT INTO TGSI_CONTRACT (Label, Begin_Date, End_Date, USER_Id_User)
                           VALUES (label, beginDate,  endDate,  idUser);
        SELECT LAST_INSERT_ID()
        INTO contractId;

        -- URL
        IF url IS NOT NULL AND LENGTH(url) > 0
        THEN
            INSERT IGNORE INTO `TGSI_OPTION_ELEMENT` (`OPTION_Id_Option`, `Value`) VALUES
                (10, url);
            SELECT Id_Option_Element
            INTO v_Id_Option_Element
            FROM TGSI_OPTION_ELEMENT toe
            WHERE toe.OPTION_Id_Option = 10 AND toe.Value = url;
            INSERT IGNORE INTO `TGSI_CONTRACT_OPTION_ELEMENT` (`CONTRACT_Id_Contract`, `OPTION_ELEMENT_Id_Option_Element`)
            VALUES
                (contractId, v_Id_Option_Element);
        END IF;

        -- maxdoc
        IF maxDoc IS NULL
        THEN
            SET maxDoc = "1000";
        END IF;

        INSERT IGNORE INTO `TGSI_OPTION_ELEMENT` (`OPTION_Id_Option`, `Value`) VALUES
            (c_OPTION_Id_Option, maxDoc);
        SELECT Id_Option_Element
        INTO v_Id_Option_Element2
        FROM TGSI_OPTION_ELEMENT toe
        WHERE toe.OPTION_Id_Option = 3 AND toe.Value = maxDoc;

        INSERT IGNORE INTO `TGSI_CONTRACT_OPTION_ELEMENT` (`CONTRACT_Id_Contract`, `OPTION_ELEMENT_Id_Option_Element`)
        VALUES
            (contractId, v_Id_Option_Element2);

        -- Referential
        IF referential = "SEO"
        THEN
            INSERT IGNORE INTO `TGSI_CONTRACT_REFERENTIAL` (`CONTRACT_Id_Contract`, `REFERENTIAL_Id_Referential`)
            VALUES
                (contractId, referential_id_SEO);
        ELSEIF referential = "RGAA32017"
            THEN
                INSERT IGNORE INTO `TGSI_CONTRACT_REFERENTIAL` (`CONTRACT_Id_Contract`, `REFERENTIAL_Id_Referential`)
                VALUES
                    (contractId, referential_id_RGAA32017);
        ELSEIF referential = "RGAA32016"
            THEN
                INSERT IGNORE INTO `TGSI_CONTRACT_REFERENTIAL` (`CONTRACT_Id_Contract`, `REFERENTIAL_Id_Referential`)
                VALUES
                    (contractId, referential_id_RGAA32016);
        ELSEIF referential = "RGAA3"
            THEN
                INSERT IGNORE INTO `TGSI_CONTRACT_REFERENTIAL` (`CONTRACT_Id_Contract`, `REFERENTIAL_Id_Referential`)
                VALUES
                    (contractId, referential_id_RGAA3);
        END IF;

        -- Audit: page
        IF audit_page
        THEN
            INSERT IGNORE INTO `TGSI_CONTRACT_FUNCTIONALITY` (`CONTRACT_Id_Contract`, `FUNCTIONALITY_Id_Functionality`)
            VALUES
                (contractId, audit_type_id_page);
        END IF;

        -- Audit: site
        IF audit_site
        THEN
            INSERT IGNORE INTO `TGSI_CONTRACT_FUNCTIONALITY` (`CONTRACT_Id_Contract`, `FUNCTIONALITY_Id_Functionality`)
            VALUES
                (contractId, audit_type_id_site);
        END IF;

        -- Audit: file
        IF audit_file
        THEN
            INSERT IGNORE INTO `TGSI_CONTRACT_FUNCTIONALITY` (`CONTRACT_Id_Contract`, `FUNCTIONALITY_Id_Functionality`)
            VALUES
                (contractId, audit_type_id_file);
        END IF;

        -- Audit: scenario
        IF audit_scenario
        THEN
            INSERT IGNORE INTO `TGSI_CONTRACT_FUNCTIONALITY` (`CONTRACT_Id_Contract`, `FUNCTIONALITY_Id_Functionality`)
            VALUES
                (contractId, audit_type_id_scenario);
        END IF;

        -- Audit: manual
        IF audit_manual
        THEN
            INSERT IGNORE INTO `TGSI_CONTRACT_FUNCTIONALITY` (`CONTRACT_Id_Contract`, `FUNCTIONALITY_Id_Functionality`)
            VALUES
                (contractId, audit_type_id_manual);
        END IF;

    END  |
DELIMITER ;