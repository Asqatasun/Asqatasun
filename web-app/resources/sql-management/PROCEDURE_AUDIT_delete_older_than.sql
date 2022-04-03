-- -----------------------------------------------------------------
-- Delete audit older than N month
--
-- /!\ needs procedure delete_audit_from_id
-- Greatly inspired by 
-- https://dba.stackexchange.com/questions/138549/mysql-loop-through-a-table-running-a-stored-procedure-on-each-entry
-- -----------------------------------------------------------------
DROP PROCEDURE IF EXISTS delete_audit_older_than;

DELIMITER |
CREATE PROCEDURE `delete_audit_older_than`(timeSpan INT)
BEGIN

    DECLARE audit_id_to_delete INT DEFAULT NULL;
    DECLARE done TINYINT DEFAULT FALSE;

    DECLARE cursor1 -- cursor1 is an arbitrary label, an identifier for the cursor
        CURSOR FOR
            SELECT Id_Audit
            FROM AUDIT
            WHERE Dt_Creation <= DATE_SUB(date(now()), INTERVAL timeSpan MONTH)
            ORDER BY Id_Audit DESC;

    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    OPEN cursor1;

    my_loop: -- loops have to have an arbitrary label; it's used to leave the loop
    LOOP
        FETCH NEXT FROM cursor1 INTO audit_id_to_delete;
        IF done THEN
            LEAVE my_loop;
        ELSE -- audit_id_to_delete will be the next values from Id_Audit in table AUDIT,
        -- so now we call the procedure with them for this "row"
            CALL delete_audit_from_id(audit_id_to_delete);
        END IF;
    END LOOP;

    CLOSE cursor1;
END |
DELIMITER ;



