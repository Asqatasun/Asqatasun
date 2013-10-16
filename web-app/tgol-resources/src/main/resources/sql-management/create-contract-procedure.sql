-- -----------------------------------------------------------------
-- Creation of contract with Url, Functionalities and Referentials
-- -----------------------------------------------------------------
use $myDatabaseName;

DROP PROCEDURE IF EXISTS create_contract;

delimiter |
CREATE DEFINER=`$myDatabaseUser`@`localhost` PROCEDURE `create_contract`(
IN idUser INT, 
IN label VARCHAR(255), 
IN url VARCHAR(1024), 
IN ref1 INT, 
IN ref2 INT, 
IN ref3 INT, 
IN ref4 INT, 
IN funct1 INT, 
IN funct2 INT, 
IN funct3 INT,
IN funct4 INT,
IN maxDoc VARCHAR(5))
BEGIN

  DECLARE v_Id_Option_Element bigint(20);
  DECLARE v_Id_Option_Element2 bigint(20);
  DECLARE contractId bigint(20);

  INSERT INTO TGSI_CONTRACT (Label, Begin_Date, End_Date, USER_Id_User) values (label, date(now()),
                date_add(date(now()), interval 1 year), idUser);

  SELECT LAST_INSERT_ID() INTO contractId; 

  IF url IS NOT NULL AND LENGTH(url)>0 
    THEN 
        INSERT IGNORE INTO `TGSI_OPTION_ELEMENT` (`OPTION_Id_Option`, `Value`) VALUES
            (10, url);	
        select Id_Option_Element into v_Id_Option_Element FROM TGSI_OPTION_ELEMENT toe WHERE toe.OPTION_Id_Option=10 AND toe.Value=Url;
        INSERT IGNORE INTO `TGSI_CONTRACT_OPTION_ELEMENT` (`CONTRACT_Id_Contract`, `OPTION_ELEMENT_Id_Option_Element`) VALUES
	    (contractId,v_Id_Option_Element);
  END IF;
  
  IF maxDoc IS NOT NULL
    THEN 
        INSERT IGNORE INTO `TGSI_OPTION_ELEMENT` (`OPTION_Id_Option`, `Value`) VALUES
            (3, maxDoc);	
        select Id_Option_Element into v_Id_Option_Element2 FROM TGSI_OPTION_ELEMENT toe WHERE toe.OPTION_Id_Option=3 AND toe.Value=maxDoc;
    ELSE 
        select Id_Option_Element into v_Id_Option_Element2 FROM TGSI_OPTION_ELEMENT toe WHERE toe.OPTION_Id_Option=3 AND toe.Value="1000";
  END IF;

  INSERT IGNORE INTO `TGSI_CONTRACT_OPTION_ELEMENT` (`CONTRACT_Id_Contract`, `OPTION_ELEMENT_Id_Option_Element`) VALUES
                    (contractId,v_Id_Option_Element2);

  IF ref1 IS NOT NULL 
    THEN 
        INSERT IGNORE INTO `TGSI_CONTRACT_REFERENTIAL` (`CONTRACT_Id_Contract`, `REFERENTIAL_Id_Referential`) VALUES
	    (contractId,1);
  END IF;            

  IF ref2 IS NOT NULL 
    THEN 
        INSERT IGNORE INTO `TGSI_CONTRACT_REFERENTIAL` (`CONTRACT_Id_Contract`, `REFERENTIAL_Id_Referential`) VALUES
	    (contractId,2);
  END IF;

  IF ref3 IS NOT NULL 
    THEN 
        INSERT IGNORE INTO `TGSI_CONTRACT_REFERENTIAL` (`CONTRACT_Id_Contract`, `REFERENTIAL_Id_Referential`) VALUES
	    (contractId,3);
  END IF;

  IF ref4 IS NOT NULL 
    THEN 
        INSERT IGNORE INTO `TGSI_CONTRACT_REFERENTIAL` (`CONTRACT_Id_Contract`, `REFERENTIAL_Id_Referential`) VALUES
	    (contractId,4);
  END IF;

  IF funct1 IS NOT NULL 
    THEN 
        INSERT IGNORE INTO `TGSI_CONTRACT_FUNCTIONALITY` (`CONTRACT_Id_Contract`, `FUNCTIONALITY_Id_Functionality`) VALUES
	    (contractId,funct1);
  END IF;

  IF funct2 IS NOT NULL 
    THEN 
        INSERT IGNORE INTO `TGSI_CONTRACT_FUNCTIONALITY` (`CONTRACT_Id_Contract`, `FUNCTIONALITY_Id_Functionality`) VALUES
	    (contractId,funct2);
  END IF;

  IF funct3 IS NOT NULL 
    THEN 
        INSERT IGNORE INTO `TGSI_CONTRACT_FUNCTIONALITY` (`CONTRACT_Id_Contract`, `FUNCTIONALITY_Id_Functionality`) VALUES
	    (contractId,funct3);
  END IF;

  IF funct4 IS NOT NULL 
    THEN 
        INSERT IGNORE INTO `TGSI_CONTRACT_FUNCTIONALITY` (`CONTRACT_Id_Contract`, `FUNCTIONALITY_Id_Functionality`) VALUES
	    (contractId,funct4);
  END IF;
    
END  |
delimiter ;