
ALTER TABLE TEST ADD Weight numeric(2,1) UNSIGNED NOT NULL DEFAULT '1.0' AFTER RANK;
ALTER TABLE WEB_RESOURCE_STATISTICS ADD `Weighted_Passed` numeric(10,1) UNSIGNED DEFAULT NULL AFTER Nb_Na;
ALTER TABLE WEB_RESOURCE_STATISTICS ADD `Weighted_Failed` numeric(10,1) UNSIGNED DEFAULT NULL AFTER Weighted_Passed;
ALTER TABLE WEB_RESOURCE_STATISTICS ADD `Weighted_Nmi` numeric(10,1) UNSIGNED DEFAULT NULL AFTER Weighted_Failed;
ALTER TABLE WEB_RESOURCE_STATISTICS ADD `Weighted_Na` numeric(10,1) UNSIGNED DEFAULT NULL AFTER Weighted_Nmi;

DROP PROCEDURE IF EXISTS add_result_weight_to_webresource_statistics;

delimiter |

CREATE PROCEDURE `add_result_weight_to_webresource_statistics`()
BLOCK1: BEGIN

    DECLARE done INT DEFAULT 0;
    DECLARE v_Nb_Passed INT(11);
    DECLARE v_Nb_Failed INT(11);
    DECLARE v_Nb_Nmi INT(11);
    DECLARE v_Nb_Na INT(11);
    DECLARE v_Id_Web_Resource_Statistics BIGINT(20);
    DECLARE wrs CURSOR FOR SELECT 
                    Id_Web_Resource_Statistics, 
                    Nb_Passed, 
                    Nb_Failed, 
                    Nb_Nmi, 
                    Nb_Na 
                        FROM WEB_RESOURCE_STATISTICS;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    OPEN wrs;
    LOOP1: loop
        FETCH wrs INTO v_Id_Web_Resource_Statistics, v_Nb_Passed, v_Nb_Failed, v_Nb_Nmi, v_Nb_Na;
        IF done = 1 THEN
            LEAVE LOOP1;
        END IF;
        INSERT INTO `WEB_RESOURCE_STATISTICS` 
            (`Id_Web_Resource_Statistics`, `Weighted_Passed`, `Weighted_Failed`, `Weighted_Nmi`, `Weighted_Na`) 
            VALUES (v_Id_Web_Resource_Statistics,v_Nb_Passed, v_Nb_Failed, v_Nb_Nmi, v_Nb_Na) 
                ON DUPLICATE KEY UPDATE 
                    `Weighted_Passed`=VALUES(`Weighted_Passed`),
                    `Weighted_Failed`=VALUES(`Weighted_Failed`),
                    `Weighted_Nmi`=VALUES(`Weighted_Nmi`),
                    `Weighted_Na`=VALUES(`Weighted_Na`);

    END loop LOOP1;

END BLOCK1 |

delimiter ;

call add_result_weight_to_webresource_statistics();
DROP PROCEDURE IF EXISTS `add_result_weight_to_webresource_statistics`;

