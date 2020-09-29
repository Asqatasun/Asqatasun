--
-- Dumping data for table `NOMENCLATURE`
--
INSERT IGNORE INTO `NOMENCLATURE` (`Id_Nomenclature`, `Cd_Nomenclature`, `Description`, `Long_Label`, `Short_Label`, `Id_Nomenclature_Parent`) VALUES
(25, 'DeprecatedRepresentationTagsV3', NULL, NULL, NULL, NULL);

--
-- Dumping data for table `NOMENCLATURE_ELEMENT`
-- 1099
INSERT IGNORE INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES
('NomenclatureElementImpl', 2000, 'basefont', NULL, 25),
('NomenclatureElementImpl', 2001, 'blink', NULL, 25),
('NomenclatureElementImpl', 2002, 'center', NULL, 25),
('NomenclatureElementImpl', 2003, 'font', NULL, 25),
('NomenclatureElementImpl', 2004, 'marquee', NULL, 25),
('NomenclatureElementImpl', 2005, 's', NULL, 25),
('NomenclatureElementImpl', 2006, 'strike', NULL, 25),
('NomenclatureElementImpl', 2007, 'tt', NULL, 25),
('NomenclatureElementImpl', 2008, 'big', NULL, 25);

