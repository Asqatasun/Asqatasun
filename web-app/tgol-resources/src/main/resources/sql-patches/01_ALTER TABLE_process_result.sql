ALTER TABLE `evalaccess`.`process_result` 
ADD COLUMN `defenitive_value_manual` VARCHAR(191) NULL DEFAULT NULL
COMMENT 'Status des tests manuels' AFTER `Id_Test`;