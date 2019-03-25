-- ----------------------------------------------------------------
-- ------ convert utf8 DB to utf8mb4 ------------------------------
-- ----------------------------------------------------------------

-- see: https://github.com/Asqatasun/Asqatasun/issues/123
--
-- /!\ This the **webapp** part of the SQL update
-- /!\ do not forget to run the **engine** part,
--     located in engine/asqatasun-resources/src/main/resources/sql-update

-- 1. Change the character set and collation properties of the database
-- -----------------------------------------------------------------
ALTER DATABASE `asqatasun`
    CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_general_ci;

-- 2. Change the character set, collation properties
--    and maximum length of each VARCHAR or TEXT column **which is an index**
-- ----------------------------------------------------------------
ALTER TABLE `TGSI_USER`
    CHANGE `Email1` `Email1` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `TGSI_SCOPE`
    CHANGE `Code` `Code` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `TGSI_OPTION_ELEMENT`
    CHANGE `Value` `Value` varchar(191) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- 3. Change the character set and collation properties of each tables
-- ----------------------------------------------------------------
ALTER TABLE `TGSI_ROLE`
    CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `TGSI_USER`
    CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `TGSI_SCOPE`
    CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `TGSI_FUNCTIONALITY`
    CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `TGSI_CONTRACT`
    CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `TGSI_ACT`
    CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `TGSI_ACT_AUDIT`
    CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `TGSI_OPTION_FAMILY`
    CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `TGSI_OPTION`
    CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `TGSI_OPTION_ELEMENT`
    CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `TGSI_CONTRACT_OPTION_ELEMENT`
    CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `TGSI_USER_OPTION_ELEMENT`
    CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `TGSI_CONTRACT_FUNCTIONALITY`
    CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `TGSI_REFERENTIAL`
    CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `TGSI_CONTRACT_REFERENTIAL`
    CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
ALTER TABLE `TGSI_SCENARIO`
    CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- 4. Repair and optimize all tables
-- ----------------------------------------------------------------

--  with cli:
--      mysqlcheck -u root -p --auto-repair --optimize --all-databases

-- If you don't perform those repairing and optimizing, you may run into some weird bugs
-- where UPDATE statements don't  have any effect, even though no errors were thrown.
--    source:  http://hanoian.com/content/index.php/24-automate-the-converting-a-mysql-database-character-set-to-utf8mb4

-- Optimize all tables
OPTIMIZE TABLE `TGSI_ROLE`;
OPTIMIZE TABLE `TGSI_USER`;
OPTIMIZE TABLE `TGSI_SCOPE`;
OPTIMIZE TABLE `TGSI_FUNCTIONALITY`;
OPTIMIZE TABLE `TGSI_CONTRACT`;
OPTIMIZE TABLE `TGSI_ACT`;
OPTIMIZE TABLE `TGSI_ACT_AUDIT`;
OPTIMIZE TABLE `TGSI_OPTION_FAMILY`;
OPTIMIZE TABLE `TGSI_OPTION`;
OPTIMIZE TABLE `TGSI_OPTION_ELEMENT`;
OPTIMIZE TABLE `TGSI_CONTRACT_OPTION_ELEMENT`;
OPTIMIZE TABLE `TGSI_USER_OPTION_ELEMENT`;
OPTIMIZE TABLE `TGSI_CONTRACT_FUNCTIONALITY`;
OPTIMIZE TABLE `TGSI_REFERENTIAL`;
OPTIMIZE TABLE `TGSI_CONTRACT_REFERENTIAL`;
OPTIMIZE TABLE `TGSI_SCENARIO`;
