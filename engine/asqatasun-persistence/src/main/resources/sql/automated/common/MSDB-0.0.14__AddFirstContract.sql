INSERT INTO `CONTRACT`(`Label`, `Begin_Date`, `End_Date`, `USER_Id_User`)
VALUES ('OPEN BAR', now(), now() + interval 3 YEAR, 1);

INSERT INTO `CONTRACT_REFERENTIAL` VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4);
INSERT INTO `CONTRACT_FUNCTIONALITY` VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5);
