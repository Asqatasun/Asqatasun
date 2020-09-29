--
-- Dumping data for table `NOMENCLATURE`
--
INSERT IGNORE INTO `NOMENCLATURE` (`Id_Nomenclature`, `Cd_Nomenclature`, `Description`, `Long_Label`, `Short_Label`, `Id_Nomenclature_Parent`) VALUES
(26, 'DeprecatedRepresentationAttributesV3', NULL, NULL, NULL, NULL);

--
-- Dumping data for table `NOMENCLATURE_ELEMENT`
--
INSERT IGNORE INTO `NOMENCLATURE_ELEMENT` (`DTYPE`, `Id_Nomenclature_Element`, `Label`, `shortValue`, `Id_Nomenclature`) VALUES
('NomenclatureElementImpl', 2009, 'align', NULL, 26),
('NomenclatureElementImpl', 2010, 'alink', NULL, 26),
('NomenclatureElementImpl', 2011, 'background', NULL, 26),
('NomenclatureElementImpl', 2012, 'bgcolor', NULL, 26),
('NomenclatureElementImpl', 2013, 'border', NULL, 26),
('NomenclatureElementImpl', 2014, 'cellpadding', NULL, 26),
('NomenclatureElementImpl', 2015, 'cellspacing', NULL, 26),
('NomenclatureElementImpl', 2016, 'char', NULL, 26),
('NomenclatureElementImpl', 2017, 'charoff', NULL, 26),
('NomenclatureElementImpl', 2018, 'clear', NULL, 26),
('NomenclatureElementImpl', 2019, 'compact', NULL, 26),
('NomenclatureElementImpl', 2020, 'color', NULL, 26),
('NomenclatureElementImpl', 2021, 'frameborder', NULL, 26),
('NomenclatureElementImpl', 2022, 'hspace', NULL, 26),
('NomenclatureElementImpl', 2023, 'link', NULL, 26),
('NomenclatureElementImpl', 2024, 'marginheight', NULL, 26),
('NomenclatureElementImpl', 2025, 'marginwidth', NULL, 26),
('NomenclatureElementImpl', 2026, 'text', NULL, 26),
('NomenclatureElementImpl', 2027, 'valign', NULL, 26),
('NomenclatureElementImpl', 2028, 'vlink', NULL, 26),
('NomenclatureElementImpl', 2029, 'vspace', NULL, 26),
('NomenclatureElementImpl', 2030, 'size', NULL, 26);




