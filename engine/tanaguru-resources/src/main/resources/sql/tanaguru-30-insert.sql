USE tanaguru;

SET foreign_key_checks=0;

INSERT IGNORE INTO `PARAMETER_FAMILY` (`Id_Parameter_Family`, `Cd_Parameter_Family`, `Description`, `Long_Label`, `Short_Label`) VALUES
(1, 'CRAWL', 'This parameter family handles all the parameters needed by the crawler component', 'crawl parameters', 'crawl params'),
(2, 'GENERAL', 'This parameter family handles all the general parameters of the audit', 'general parameters', 'gen params'),
(3, 'RULES', 'This parameter family handles all the parameters needed by the rules', 'rules parameters', 'rules params');

INSERT IGNORE INTO `PARAMETER_ELEMENT` (`Id_Parameter_Element`, `Cd_Parameter_Element`, `Id_Parameter_Family`, `Long_Label`, `Short_Label`) VALUES
(1, 'MAX_DOCUMENTS', 1, 'Maximum number of downloaded pages', 'max pages'),
(2, 'EXCLUSION_REGEXP', 1, 'Regulard expression to exclude urls', 'exclusion regex'),
(3, 'DEPTH', 1, 'Maximum depth of the crawl', 'max depth'),
(4, 'MAX_DURATION', 1, 'Maximum duration of the crawl', 'max duration'),
(5, 'LEVEL', 2, 'Audit level (includes the referential code)', 'Audit level'),
(6, 'DATA_TABLE_MARKER', 3, 'Data Table HTML marker (id or class)', 'Data table marker'),
(7, 'PRESENTATION_TABLE_MARKER', 3, 'Presentation Table HTML marker (id or class)', 'Presentation table marker');

INSERT IGNORE INTO `PARAMETER` (`Id_Parameter_Element`, `Parameter_Value`, `Is_Default`) VALUES
(1, '50000', b'1'),
(2, '', b'1'),
(3, '20', b'1'),
(3, '0', b'0'),
(4, '604800', b'1'),
(5, 'Seo;Or', b'1'),
(5, 'Seo;Ar', b'0'),
(5, 'Seo;Bz', b'0'),
(1, '100', b'0'),
(1, '10000', b'0'),
(1, '20000', b'0'),
(6, '', b'1'),
(7, '', b'1');

SET foreign_key_checks=1;