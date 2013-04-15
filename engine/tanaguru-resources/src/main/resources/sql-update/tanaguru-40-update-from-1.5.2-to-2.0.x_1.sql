use $myDatabaseName;

ALTER TABLE WEB_RESOURCE ADD COLUMN Rank INT DEFAULT 0;

delimiter |
CREATE DEFINER=`$myDatabaseUser`@`localhost` PROCEDURE `addRankValueToPage`()
BEGIN

  DECLARE done INT DEFAULT 0;
  DECLARE v_Id_Web_Resource BIGINT(20);
  DECLARE pages CURSOR FOR SELECT Id_Web_Resource FROM WEB_RESOURCE WHERE DTYPE= 'PageImpl' AND Id_Audit is not null;

  DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;

  OPEN pages;
  REPEAT
    FETCH pages INTO v_Id_Web_Resource;
    UPDATE WEB_RESOURCE SET Rank=1 WHERE Id_Web_Resource=v_Id_Web_Resource;
  UNTIL done END REPEAT;
  CLOSE pages;

END  |
delimiter ;

delimiter |
CREATE DEFINER=`$myDatabaseUser`@`localhost` PROCEDURE `addRankValueToPageFromSite`()
BLOCK1: BEGIN

  DECLARE v_Id_Site BIGINT(20);
  DECLARE no_more_site boolean DEFAULT FALSE;  
  DECLARE sites CURSOR FOR SELECT Id_Web_Resource FROM WEB_RESOURCE WHERE DTYPE= 'SiteImpl';
  DECLARE continue handler for not found set no_more_site := TRUE;

  OPEN sites;
      LOOP1: loop
          FETCH sites INTO v_Id_Site;
          IF no_more_site then 
	      close sites;
              leave LOOP1;
          END IF;
	  
	  BLOCK2: begin 
		DECLARE v_Id_Page BIGINT(20);
		DECLARE no_more_page boolean DEFAULT FALSE;
                DECLARE v_rank int DEFAULT 1;
                DECLARE pages CURSOR FOR SELECT Id_Web_Resource FROM WEB_RESOURCE WHERE Id_Web_Resource_Parent= v_Id_Site ORDER BY Id_Web_Resource ASC;
                DECLARE continue handler for not found set no_more_page := TRUE;
		OPEN pages;
			LOOP2: loop
                            FETCH pages INTO v_Id_Page;
    		            IF no_more_page then 
	        	        close pages;
    	                        leave LOOP2;
			    END IF;
			    UPDATE WEB_RESOURCE	SET Rank=v_rank WHERE Id_Web_Resource=v_Id_Page;
                            SET v_rank = v_rank + 1;
			END loop LOOP2;
	   END BLOCK2;
	
       END loop LOOP1;
END BLOCK1 |
delimiter ;

call addRankValueToPage();
call addRankValueToPageFromSite();
DROP PROCEDURE `addRankValueToPage`;
DROP PROCEDURE `addRankValueToPageFromSite`;