-- -----------------------------------------------------------------
-- Creation of contract with Url, Functionalities and Referentials
-- -----------------------------------------------------------------
use $myDatabaseName;

-- Example of call:
--  call contract_create($UserId, \"$SiteLabel\", \"$URL\", $referential, $audit-page, $audit-site, $audit-file, $audit-scenario, $audit-manual, $maxDoc);
--  call contract_create(1, "Wikipedia RGAA3", "http://en.wikipedia.org/", "RGAA3", 1, 1, 1, 1, 1, 100);
--  call contract_create(1, "Wikipedia SEO", "http://en.wikipedia.org/", "SEO", 0, 1, 0, 0, 0, 1000);

DROP PROCEDURE IF EXISTS contract_create;

delimiter |
CREATE DEFINER=`$myDatabaseUser`@`localhost` PROCEDURE `contract_create`(
    IN idUser INT, 
    IN label VARCHAR(255), 
    IN url VARCHAR(1024), 
    IN referential VARCHAR(10), 
    IN audit_page BOOLEAN, 
    IN audit_site BOOLEAN, 
    IN audit_file BOOLEAN, 
    IN audit_scenario BOOLEAN, 
    IN audit_manual BOOLEAN, 
    IN maxDoc VARCHAR(5)
)
BEGIN

    -- hard-coded values from table TGSI_REFERENTIAL
    DECLARE referential_id_RGAA3 int DEFAULT 3;
    DECLARE referential_id_SEO int DEFAULT 4;
    
    -- hard-coded values from table TGSI_FUNCTIONALITY
    DECLARE audit_type_id_page int DEFAULT 1;
    DECLARE audit_type_id_site int DEFAULT 2;
    DECLARE audit_type_id_file int DEFAULT 3;
    DECLARE audit_type_id_scenario int DEFAULT 4;
    DECLARE audit_type_id_manual int DEFAULT 5;

    DECLARE v_Id_Option_Element bigint(20);
    DECLARE v_Id_Option_Element2 bigint(20);
    DECLARE contractId bigint(20);

    -- Contract
    INSERT INTO TGSI_CONTRACT (Label, Begin_Date, End_Date, USER_Id_User) values (label, date(now()),
                  date_add(date(now()), interval 1 year), idUser);

    SELECT LAST_INSERT_ID() INTO contractId; 

    -- URL
    IF url IS NOT NULL AND LENGTH(url)>0 THEN 
        INSERT IGNORE INTO `TGSI_OPTION_ELEMENT` (`OPTION_Id_Option`, `Value`) VALUES
            (10, url);
        select Id_Option_Element into v_Id_Option_Element FROM TGSI_OPTION_ELEMENT toe WHERE toe.OPTION_Id_Option=10 AND toe.Value=url;
        INSERT IGNORE INTO `TGSI_CONTRACT_OPTION_ELEMENT` (`CONTRACT_Id_Contract`, `OPTION_ELEMENT_Id_Option_Element`) VALUES
            (contractId,v_Id_Option_Element);
    END IF;

    -- maxdoc
    IF maxDoc IS NOT NULL THEN 
        INSERT IGNORE INTO `TGSI_OPTION_ELEMENT` (`OPTION_Id_Option`, `Value`) VALUES
            (3, maxDoc);	
        select Id_Option_Element into v_Id_Option_Element2 FROM TGSI_OPTION_ELEMENT toe WHERE toe.OPTION_Id_Option=3 AND toe.Value=maxDoc;
      ELSE 
        select Id_Option_Element into v_Id_Option_Element2 FROM TGSI_OPTION_ELEMENT toe WHERE toe.OPTION_Id_Option=3 AND toe.Value="1000";
    END IF;

    INSERT IGNORE INTO `TGSI_CONTRACT_OPTION_ELEMENT` (`CONTRACT_Id_Contract`, `OPTION_ELEMENT_Id_Option_Element`) VALUES
        (contractId,v_Id_Option_Element2);

    -- Referential
    IF referential = "SEO" THEN 
        INSERT IGNORE INTO `TGSI_CONTRACT_REFERENTIAL` (`CONTRACT_Id_Contract`, `REFERENTIAL_Id_Referential`) VALUES
	    (contractId, referential_id_SEO);
    ELSEIF referential = "RGAA3" THEN
        INSERT IGNORE INTO `TGSI_CONTRACT_REFERENTIAL` (`CONTRACT_Id_Contract`, `REFERENTIAL_Id_Referential`) VALUES
	    (contractId, referential_id_RGAA3);
    END IF;            

    -- Audit: page
    IF audit_page THEN 
        INSERT IGNORE INTO `TGSI_CONTRACT_FUNCTIONALITY` (`CONTRACT_Id_Contract`, `FUNCTIONALITY_Id_Functionality`) VALUES
            (contractId, audit_type_id_page);
    END IF;

    -- Audit: site
    IF audit_site THEN 
        INSERT IGNORE INTO `TGSI_CONTRACT_FUNCTIONALITY` (`CONTRACT_Id_Contract`, `FUNCTIONALITY_Id_Functionality`) VALUES
            (contractId, audit_type_id_site);
    END IF;

    -- Audit: file
    IF audit_file THEN 
        INSERT IGNORE INTO `TGSI_CONTRACT_FUNCTIONALITY` (`CONTRACT_Id_Contract`, `FUNCTIONALITY_Id_Functionality`) VALUES
            (contractId, audit_type_id_file);
    END IF;

    -- Audit: scenario
    IF audit_scenario THEN 
        INSERT IGNORE INTO `TGSI_CONTRACT_FUNCTIONALITY` (`CONTRACT_Id_Contract`, `FUNCTIONALITY_Id_Functionality`) VALUES
            (contractId, audit_type_id_scenario);
    END IF;

    -- Audit: manual
    IF audit_manual THEN 
        INSERT IGNORE INTO `TGSI_CONTRACT_FUNCTIONALITY` (`CONTRACT_Id_Contract`, `FUNCTIONALITY_Id_Functionality`) VALUES
            (contractId, audit_type_id_manual);
    END IF;
    
END  |
delimiter ;