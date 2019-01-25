
-- ----------------------------------------------------------------
-- ------ convert utf8 DB to utf8mb4 ------------------------------
-- ----------------------------------------------------------------

-- see: https://github.com/Asqatasun/Asqatasun/issues/123
--
-- /!\ This the **engine** part of the SQL update
-- /!\ do not forget to run the **webapp** part,
--     located in web-app/tgol-resources/src/main/resources/sql-update

-- 1. Change the character set and collation properties of the database
-- -----------------------------------------------------------------
ALTER DATABASE `asqatasun`
    CHARACTER SET = utf8mb4
    COLLATE       = utf8mb4_general_ci;

-- 2. Change the character set, collation properties
--    and maximum length of each VARCHAR or TEXT column **which is an index**
-- ----------------------------------------------------------------

ALTER TABLE `THEME`                 CHANGE `Cd_Theme`               `Cd_Theme`              varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `REFERENCE`             CHANGE `Cd_Reference`           `Cd_Reference`          varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `CRITERION`             CHANGE `Cd_Criterion`           `Cd_Criterion`          varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `TEST`                  CHANGE `Cd_Test`                `Cd_Test`               varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `PROCESS_RESULT`        CHANGE `Definite_Value`         `Definite_Value`        varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `PROCESS_REMARK`        CHANGE `Issue`                  `Issue`                 varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `PRE_PROCESS_RESULT`    CHANGE `Pre_Process_Key`        `Pre_Process_Key`       varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `PARAMETER_FAMILY`      CHANGE `Cd_Parameter_Family`    `Cd_Parameter_Family`   varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `PARAMETER_ELEMENT`     CHANGE `Cd_Parameter_Element`   `Cd_Parameter_Element`  varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;


-- 3. Change the character set and collation properties of each tables
-- ----------------------------------------------------------------
ALTER TABLE `AUDIT`                        CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `WEB_RESOURCE`                 CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `CONTENT`                      CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `CONTENT_RELATIONSHIP`         CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `SCOPE`                        CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `DECISION_LEVEL`               CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `LEVEL`                        CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `THEME`                        CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `REFERENCE`                    CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `CRITERION`                    CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `TEST`                         CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `AUDIT_TEST`                   CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `EVIDENCE`                     CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `PROCESS_RESULT`               CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `PROCESS_REMARK`               CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `EVIDENCE_ELEMENT`             CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `NOMENCLATURE`                 CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `NOMENCLATURE_ELEMENT`         CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `STANDARD_MESSAGE`             CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `PRE_PROCESS_RESULT`           CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
-- ALTER TABLE `SNAPSHOT`                  CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `PARAMETER_FAMILY`             CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `PARAMETER_ELEMENT`            CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `PARAMETER`                    CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `AUDIT_PARAMETER`              CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `WEB_RESOURCE_STATISTICS`      CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `THEME_STATISTICS`             CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `CRITERION_STATISTICS`         CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `TEST_STATISTICS`              CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `REVINFO`                      CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `PROCESS_RESULT_AUD`           CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;


-- 4. Repair and optimize all tables
-- ----------------------------------------------------------------

    --  with cli:
    --      mysqlcheck -u root -p --auto-repair --optimize --all-databases

-- If you don't perform those repairing and optimizing, you may run into some weird bugs
-- where UPDATE statements don't  have any effect, even though no errors were thrown.
--    source:  http://hanoian.com/content/index.php/24-automate-the-converting-a-mysql-database-character-set-to-utf8mb4

-- Optimize all tables
OPTIMIZE TABLE `AUDIT`;
OPTIMIZE TABLE `WEB_RESOURCE`;
OPTIMIZE TABLE `CONTENT`;
OPTIMIZE TABLE `CONTENT_RELATIONSHIP`;
OPTIMIZE TABLE `SCOPE`;
OPTIMIZE TABLE `DECISION_LEVEL`;
OPTIMIZE TABLE `LEVEL`;
OPTIMIZE TABLE `THEME`;
OPTIMIZE TABLE `REFERENCE`;
OPTIMIZE TABLE `CRITERION`;
OPTIMIZE TABLE `TEST`;
OPTIMIZE TABLE `AUDIT_TEST`;
OPTIMIZE TABLE `EVIDENCE`;
OPTIMIZE TABLE `PROCESS_RESULT`;
OPTIMIZE TABLE `PROCESS_REMARK`;
OPTIMIZE TABLE `EVIDENCE_ELEMENT`;
OPTIMIZE TABLE `NOMENCLATURE`;
OPTIMIZE TABLE `NOMENCLATURE_ELEMENT`;
OPTIMIZE TABLE `STANDARD_MESSAGE`;
OPTIMIZE TABLE `PRE_PROCESS_RESULT`;
-- OPTIMIZE TABLE `SNAPSHOT`;
OPTIMIZE TABLE `PARAMETER_FAMILY`;
OPTIMIZE TABLE `PARAMETER_ELEMENT`;
OPTIMIZE TABLE `PARAMETER`;
OPTIMIZE TABLE `AUDIT_PARAMETER`;
OPTIMIZE TABLE `WEB_RESOURCE_STATISTICS`;
OPTIMIZE TABLE `THEME_STATISTICS`;
OPTIMIZE TABLE `CRITERION_STATISTICS`;
OPTIMIZE TABLE `TEST_STATISTICS`;
OPTIMIZE TABLE `REVINFO`;
OPTIMIZE TABLE `PROCESS_RESULT_AUD`;
