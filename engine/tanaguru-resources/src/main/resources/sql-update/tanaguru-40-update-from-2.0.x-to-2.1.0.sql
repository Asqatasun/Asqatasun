
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

-- Add new Parameter elements

INSERT IGNORE INTO `PARAMETER_FAMILY` (`Id_Parameter_Family`, `Cd_Parameter_Family`, `Description`, `Long_Label`, `Short_Label`) VALUES
(4, 'Seo_TEST_WEIGHT_MANAGEMENT', 'This paramaters handles the test weight potentially overridden by users', 'test weight parameters', 'test weight params');

INSERT IGNORE INTO `PARAMETER_ELEMENT` (`Id_Parameter_Element`, `Cd_Parameter_Element`, `Id_Parameter_Family`, `Long_Label`, `Short_Label`) VALUES
(10, 'Seo-01011', 4, 'Weight of rule Seo-01011 overriden by user', 'Seo-01011 weight'),
(11, 'Seo-01012', 4, 'Weight of rule Seo-01011 overriden by user', 'Seo-01011 weight'),
(12, 'Seo-01013', 4, 'Weight of rule Seo-01011 overriden by user', 'Seo-01011 weight'),
(13, 'Seo-01021', 4, 'Weight of rule Seo-01021 overriden by user', 'Seo-01021 weight'),
(14, 'Seo-01031', 4, 'Weight of rule Seo-01031 overriden by user', 'Seo-01031 weight'),
(15, 'Seo-01041', 4, 'Weight of rule Seo-01041 overriden by user', 'Seo-01041 weight'),
(16, 'Seo-01051', 4, 'Weight of rule Seo-01051 overriden by user', 'Seo-01051 weight'),
(17, 'Seo-01061', 4, 'Weight of rule Seo-01061 overriden by user', 'Seo-01061 weight'),
(18, 'Seo-02011', 4, 'Weight of rule Seo-02011 overriden by user', 'Seo-02011 weight'),
(19, 'Seo-02012', 4, 'Weight of rule Seo-02012 overriden by user', 'Seo-02012 weight'),
(20, 'Seo-02013', 4, 'Weight of rule Seo-02013 overriden by user', 'Seo-02013 weight'),
(21, 'Seo-03011', 4, 'Weight of rule Seo-03011 overriden by user', 'Seo-01011 weight'),
(22, 'Seo-03012', 4, 'Weight of rule Seo-03012 overriden by user', 'Seo-03012 weight'),
(23, 'Seo-05011', 4, 'Weight of rule Seo-05011 overriden by user', 'Seo-05011 weight'),
(24, 'Seo-05012', 4, 'Weight of rule Seo-05012 overriden by user', 'Seo-05012 weight'),
(25, 'Seo-05013', 4, 'Weight of rule Seo-05013 overriden by user', 'Seo-05013 weight'),
(26, 'Seo-06011', 4, 'Weight of rule Seo-06011 overriden by user', 'Seo-06011 weight'),
(27, 'Seo-06021', 4, 'Weight of rule Seo-06021 overriden by user', 'Seo-06021 weight'),
(28, 'Seo-06031', 4, 'Weight of rule Seo-06031 overriden by user', 'Seo-06031 weight'),
(29, 'Seo-06041', 4, 'Weight of rule Seo-06041 overriden by user', 'Seo-06041 weight'),
(30, 'Seo-06051', 4, 'Weight of rule Seo-06051 overriden by user', 'Seo-06051 weight'),
(31, 'Seo-06052', 4, 'Weight of rule Seo-06052 overriden by user', 'Seo-06052 weight'),
(32, 'Seo-07011', 4, 'Weight of rule Seo-07011 overriden by user', 'Seo-07011 weight'),
(33, 'Seo-07012', 4, 'Weight of rule Seo-07012 overriden by user', 'Seo-07012 weight'),
(34, 'Seo-07021', 4, 'Weight of rule Seo-07021 overriden by user', 'Seo-07021 weight'),
(35, 'Seo-07051', 4, 'Weight of rule Seo-07051 overriden by user', 'Seo-07051 weight'),
(36, 'Seo-07061', 4, 'Weight of rule Seo-07061 overriden by user', 'Seo-07061 weight'),
(37, 'Seo-08011', 4, 'Weight of rule Seo-08011 overriden by user', 'Seo-08011 weight');
