--- -----------------------------------------------------
--- Table `WS_ROLE`
--- -----------------------------------------------------
INSERT INTO `evalaccess`.`ws_role` (`ID_ROLE`, `ROLE`, `LABEL`) VALUES ('1', 'ADMIN', 'Administrator ');
INSERT INTO `evalaccess`.`ws_role` (`ID_ROLE`, `ROLE`, `LABEL`) VALUES ('2', 'USER', 'Known user');
INSERT INTO `evalaccess`.`ws_role` (`ID_ROLE`, `ROLE`, `LABEL`) VALUES ('3', 'GUEST', 'Guest');
--- -----------------------------------------------------
--- Table `WS_USER`
--- -----------------------------------------------------
INSERT INTO `evalaccess`.`ws_user` (`ID_USER`, `EMAIL`, `PASSWORD`, `NAME`, `FIRST_NAME`, `ACTIVE`, `ID_ROLE`) VALUES ('1', 'fhalna@oceaneconsulting.com', '098f6bcd4621d373cade4e832627b4f6', 'HALNA', 'Frederic', 1, 1);
INSERT INTO `evalaccess`.`ws_user` (`ID_USER`, `EMAIL`, `PASSWORD`, `NAME`, `FIRST_NAME`, `ACTIVE`, `ID_ROLE`) VALUES ('2', 'shamdi@oceaneconsulting.com', '098f6bcd4621d373cade4e832627b4f6', 'HAMDI', 'Sofien', 1, 2);
--- -----------------------------------------------------
--- Table `WS_INVOCATION`
--- -----------------------------------------------------
--- No inserts needed
--- -----------------------------------------------------
