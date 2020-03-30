-- -----------------------------------------------------------------
-- Delete all audits
--
-- /!\ this deletes absolutely ALL audits from the database
-- -----------------------------------------------------------------
DROP PROCEDURE IF EXISTS delete_all_audit;

DELIMITER |
CREATE
    DEFINER =`asqatasun`@`localhost` PROCEDURE `delete_all_audit`
    (
    )
BEGIN
    DELETE FROM AUDIT;
END |
DELIMITER ;
