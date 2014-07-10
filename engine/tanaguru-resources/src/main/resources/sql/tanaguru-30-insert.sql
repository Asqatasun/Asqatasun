SET foreign_key_checks=0;

INSERT IGNORE INTO `LEVEL` (`Id_Level`, `Cd_Level`, `Label`, `Description`, `Rank`) VALUES
(1, 'LEVEL_1', 'Level 1', NULL, 1),
(2, 'LEVEL_2', 'Level 2', NULL, 2),
(3, 'LEVEL_3', 'Level 3', NULL, 3);

INSERT IGNORE INTO `PARAMETER_FAMILY` (`Id_Parameter_Family`, `Cd_Parameter_Family`, `Description`, `Long_Label`, `Short_Label`) VALUES
(1, 'CRAwL', 'This parameter family handles all the parameters needed by the crawler component', 'crawl parameters', 'crawl params'),
(2, 'GENERAL', 'This parameter family handles all the general parameters of the audit', 'general parameters', 'gen params'),
(3, 'RULES', 'This parameter family handles all the parameters needed by the rules', 'rules parameters', 'rules params');

INSERT IGNORE INTO `PARAMETER_ELEMENT` (`Id_Parameter_Element`, `Cd_Parameter_Element`, `Id_Parameter_Family`, `Long_Label`, `Short_Label`) VALUES
(1, 'MAX_DOCUMENTS', 1, 'Maximum number of downloaded pages', 'max pages'),
(2, 'EXCLUSION_REGEXP', 1, 'Regulard expression to exclude urls', 'exclusion regex'),
(3, 'DEPTH', 1, 'Maximum depth of the crawl', 'max depth'),
(4, 'MAX_DURATION', 1, 'Maximum duration of the crawl', 'max duration'),
(5, 'LEVEL', 2, 'Audit level (includes the referential code)', 'Audit level'),
(6, 'DATA_TABLE_MARKER', 3, 'Data Table HTML marker (id or class)', 'Data table marker'),
(7, 'PRESENTATION_TABLE_MARKER', 3, 'Presentation Table HTML marker (id or class)', 'Presentation table marker'),
(8, 'PROXY_HOST', 1, 'proxy host', 'proxy host'),
(9, 'PROXY_PORT', 1, 'proxy port', 'proxy port'),
(38, 'DECORATIVE_IMAGE_MARKER', 3, 'Decorative image HTML marker (id or class)', 'Decorative image marker'),
(39, 'INFORMATIVE_IMAGE_MARKER', 3, 'Informative image HTML marker (id or class)', 'Informative image marker'), 
(40, 'ALTERNATIVE_CONTRAST_MECHANISM', 3, 'The page embeds a mechanism that displays text with a correct ratio', 'Alternative Contrast Mechanism'),
(41, 'CONSIDER_COOKIES',1,'consider cookies','consider cookies while crawling'),
(42, 'INCLUSION_REGEXP', 1, '', 'inclusion regex');

INSERT IGNORE INTO `PARAMETER` (`Id_Parameter_Element`, `Parameter_Value`, `Is_Default`) VALUES
(1, '50000', b'0'),
(2, '', b'1'),
(3, '20', b'1'),
(3, '0', b'0'),
(4, '604800', b'0'),
(1, '100', b'0'),
(1, '10000', b'1'),
(1, '20000', b'0'),
(6, '', b'1'),
(7, '', b'1'),
(8, '', b'1'),
(9, '', b'1'), 
(4, '86400', b'1'), 
(38, '', b'1'),
(39, '', b'1'), 
(40, 'true', b'0'), 
(40, 'false', b'1'),
(41, 'true', b'1'),
(41, 'false', b'0'),
(42, '', b'1'); 

SET foreign_key_checks=1;
