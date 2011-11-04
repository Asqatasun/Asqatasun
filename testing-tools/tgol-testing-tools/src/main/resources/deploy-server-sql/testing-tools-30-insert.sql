USE tanaguru;

INSERT INTO `TGSI_USER` (`Id_User`, `Email1`, `Password`, `ROLE_Id_Role`) VALUES
(1,'xxxxxxxxxx', 'xxxxxxxxxxx','2');

INSERT INTO `TGSI_CONTRACT` (`Label`, `Begin_Date`, `End_Date`, `Price`, `USER_Id_User`, `Url`, `PRODUCT_Id_Product`) VALUES
('Testing-Tools', '2011-02-01 00:00:00', '2050-12-31 23:59:59', '0', '1', '', '4');