SET foreign_key_checks=0;

INSERT IGNORE INTO `LEVEL` (`Id_Level`, `Cd_Level`, `Label`, `Description`, `Rank`) VALUES
(1, 'LEVEL_1', 'Level 1', NULL, 1),
(2, 'LEVEL_2', 'Level 2', NULL, 2),
(3, 'LEVEL_3', 'Level 3', NULL, 3);

INSERT IGNORE INTO `PARAMETER_FAMILY` (`Cd_Parameter_Family`, `Description`, `Long_Label`, `Short_Label`) VALUES
('CRAWL', 'This parameter family handles all the parameters needed by the crawler component', 'crawl parameters', 'crawl params'),
('GENERAL', 'This parameter family handles all the general parameters of the audit', 'general parameters', 'gen params'),
('RULES', 'This parameter family handles all the parameters needed by the rules', 'rules parameters', 'rules params');

INSERT IGNORE INTO `PARAMETER_ELEMENT` (`Cd_Parameter_Element`, `Long_Label`, `Short_Label`) VALUES
('MAX_DOCUMENTS', 'Maximum number of downloaded pages', 'max pages'),
('EXCLUSION_REGEXP', 'Regulard expression to exclude urls', 'exclusion regex'),
('DEPTH', 'Maximum depth of the crawl', 'max depth'),
('MAX_DURATION', 'Maximum duration of the crawl', 'max duration'),
('LEVEL', 'Audit level (includes the referential code)', 'Audit level'),
('DATA_TABLE_MARKER', 'Data Table HTML marker (id or class)', 'Data table marker'),
('PRESENTATION_TABLE_MARKER', 'Presentation Table HTML marker (id or class)', 'Presentation table marker'),
('DECORATIVE_IMAGE_MARKER', 'Decorative image HTML marker (id or class)', 'Decorative image marker'),
('INFORMATIVE_IMAGE_MARKER', 'Informative image HTML marker (id or class)', 'Informative image marker'), 
('ALTERNATIVE_CONTRAST_MECHANISM', 'The page embeds a mechanism that displays text with a correct ratio', 'Alternative Contrast Mechanism'),
('CONSIDER_COOKIES', 'consider cookies','consider cookies while crawling'),
('INCLUSION_REGEXP', '', 'inclusion regex'),
('SCREEN_WIDTH', '', 'screen width'),
('SCREEN_HEIGHT', '', 'screen height'),
('COMPLEX_TABLE_MARKER', 'Complex table marker', 'Correspond to the attribute \"id\", \"class\" or \"role\" of the complex tables');

--Crawl family insertion
UPDATE `PARAMETER_ELEMENT` SET `Id_Parameter_Family` = (SELECT `Id_Parameter_Family` FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` LIKE 'CRAWL') WHERE `Cd_Parameter_Element` LIKE 'MAX_DOCUMENTS';
UPDATE `PARAMETER_ELEMENT` SET `Id_Parameter_Family` = (SELECT `Id_Parameter_Family` FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` LIKE 'CRAWL') WHERE `Cd_Parameter_Element` LIKE 'EXCLUSION_REGEXP';
UPDATE `PARAMETER_ELEMENT` SET `Id_Parameter_Family` = (SELECT `Id_Parameter_Family` FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` LIKE 'CRAWL') WHERE `Cd_Parameter_Element` LIKE 'DEPTH';
UPDATE `PARAMETER_ELEMENT` SET `Id_Parameter_Family` = (SELECT `Id_Parameter_Family` FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` LIKE 'CRAWL') WHERE `Cd_Parameter_Element` LIKE 'MAX_DURATION';
UPDATE `PARAMETER_ELEMENT` SET `Id_Parameter_Family` = (SELECT `Id_Parameter_Family` FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` LIKE 'CRAWL') WHERE `Cd_Parameter_Element` LIKE 'CONSIDER_COOKIES';
UPDATE `PARAMETER_ELEMENT` SET `Id_Parameter_Family` = (SELECT `Id_Parameter_Family` FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` LIKE 'CRAWL') WHERE `Cd_Parameter_Element` LIKE 'INCLUSION_REGEXP';

--General family insertion
UPDATE `PARAMETER_ELEMENT` SET `Id_Parameter_Family` = (SELECT `Id_Parameter_Family` FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` LIKE 'GENERAL') WHERE `Cd_Parameter_Element` LIKE 'LEVEL';
UPDATE `PARAMETER_ELEMENT` SET `Id_Parameter_Family` = (SELECT `Id_Parameter_Family` FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` LIKE 'GENERAL') WHERE `Cd_Parameter_Element` LIKE 'SCREEN_WIDTH';
UPDATE `PARAMETER_ELEMENT` SET `Id_Parameter_Family` = (SELECT `Id_Parameter_Family` FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` LIKE 'GENERAL') WHERE `Cd_Parameter_Element` LIKE 'SCREEN_HEIGHT';

--Rules family insertion
UPDATE `PARAMETER_ELEMENT` SET `Id_Parameter_Family` = (SELECT `Id_Parameter_Family` FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` LIKE 'RULES') WHERE `Cd_Parameter_Element` LIKE 'DATA_TABLE_MARKER';
UPDATE `PARAMETER_ELEMENT` SET `Id_Parameter_Family` = (SELECT `Id_Parameter_Family` FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` LIKE 'RULES') WHERE `Cd_Parameter_Element` LIKE 'PRESENTATION_TABLE_MARKER';
UPDATE `PARAMETER_ELEMENT` SET `Id_Parameter_Family` = (SELECT `Id_Parameter_Family` FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` LIKE 'RULES') WHERE `Cd_Parameter_Element` LIKE 'DECORATIVE_IMAGE_MARKER';
UPDATE `PARAMETER_ELEMENT` SET `Id_Parameter_Family` = (SELECT `Id_Parameter_Family` FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` LIKE 'RULES') WHERE `Cd_Parameter_Element` LIKE 'INFORMATIVE_IMAGE_MARKER';
UPDATE `PARAMETER_ELEMENT` SET `Id_Parameter_Family` = (SELECT `Id_Parameter_Family` FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` LIKE 'RULES') WHERE `Cd_Parameter_Element` LIKE 'ALTERNATIVE_CONTRAST_MECHANISM';
UPDATE `PARAMETER_ELEMENT` SET `Id_Parameter_Family` = (SELECT `Id_Parameter_Family` FROM `PARAMETER_FAMILY` WHERE `Cd_Parameter_Family` LIKE 'RULES') WHERE `Cd_Parameter_Element` LIKE 'COMPLEX_TABLE_MARKER';

INSERT IGNORE INTO `PARAMETER` (`Id_Parameter_Element`, `Parameter_Value`, `Is_Default`) VALUES
((SELECT `Id_Parameter_Element` FROM `PARAMETER_ELEMENT` WHERE `Cd_Parameter_Element` LIKE 'MAX_DOCUMENTS'), '50000', b'0');,
((SELECT `Id_Parameter_Element` FROM `PARAMETER_ELEMENT` WHERE `Cd_Parameter_Element` LIKE 'EXCLUSION_REGEXP'), '', b'1'),
((SELECT `Id_Parameter_Element` FROM `PARAMETER_ELEMENT` WHERE `Cd_Parameter_Element` LIKE 'DEPTH'), '20', b'1'),
((SELECT `Id_Parameter_Element` FROM `PARAMETER_ELEMENT` WHERE `Cd_Parameter_Element` LIKE 'DEPTH'), '0', b'0'),
((SELECT `Id_Parameter_Element` FROM `PARAMETER_ELEMENT` WHERE `Cd_Parameter_Element` LIKE 'MAX_DURATION'), '604800', b'0'),
((SELECT `Id_Parameter_Element` FROM `PARAMETER_ELEMENT` WHERE `Cd_Parameter_Element` LIKE 'MAX_DOCUMENTS'), '100', b'0'),
((SELECT `Id_Parameter_Element` FROM `PARAMETER_ELEMENT` WHERE `Cd_Parameter_Element` LIKE 'MAX_DOCUMENTS'), '10000', b'1'),
((SELECT `Id_Parameter_Element` FROM `PARAMETER_ELEMENT` WHERE `Cd_Parameter_Element` LIKE 'MAX_DOCUMENTS'), '20000', b'0'),
((SELECT `Id_Parameter_Element` FROM `PARAMETER_ELEMENT` WHERE `Cd_Parameter_Element` LIKE 'DATA_TABLE_MARKER'), '', b'1'),
((SELECT `Id_Parameter_Element` FROM `PARAMETER_ELEMENT` WHERE `Cd_Parameter_Element` LIKE 'PRESENTATION_TABLE_MARKER'), '', b'1'),
((SELECT `Id_Parameter_Element` FROM `PARAMETER_ELEMENT` WHERE `Cd_Parameter_Element` LIKE 'MAX_DURATION'), '86400', b'1'), 
((SELECT `Id_Parameter_Element` FROM `PARAMETER_ELEMENT` WHERE `Cd_Parameter_Element` LIKE 'DECORATIVE_IMAGE_MARKER'), '', b'1'),
((SELECT `Id_Parameter_Element` FROM `PARAMETER_ELEMENT` WHERE `Cd_Parameter_Element` LIKE 'INFORMATIVE_IMAGE_MARKER'), '', b'1'), 
((SELECT `Id_Parameter_Element` FROM `PARAMETER_ELEMENT` WHERE `Cd_Parameter_Element` LIKE 'ALTERNATIVE_CONTRAST_MECHANISM'), 'true', b'0'), 
((SELECT `Id_Parameter_Element` FROM `PARAMETER_ELEMENT` WHERE `Cd_Parameter_Element` LIKE 'ALTERNATIVE_CONTRAST_MECHANISM'), 'false', b'1'),
((SELECT `Id_Parameter_Element` FROM `PARAMETER_ELEMENT` WHERE `Cd_Parameter_Element` LIKE 'CONSIDER_COOKIES'), 'true', b'1'),
((SELECT `Id_Parameter_Element` FROM `PARAMETER_ELEMENT` WHERE `Cd_Parameter_Element` LIKE 'CONSIDER_COOKIES'), 'false', b'0'),
((SELECT `Id_Parameter_Element` FROM `PARAMETER_ELEMENT` WHERE `Cd_Parameter_Element` LIKE 'INCLUSION_REGEXP'), '', b'1'),
((SELECT `Id_Parameter_Element` FROM `PARAMETER_ELEMENT` WHERE `Cd_Parameter_Element` LIKE 'SCREEN_WIDTH'), '1920', b'1'),
((SELECT `Id_Parameter_Element` FROM `PARAMETER_ELEMENT` WHERE `Cd_Parameter_Element` LIKE 'SCREEN_HEIGHT'), '1080', b'1'),
((SELECT `Id_Parameter_Element` FROM `PARAMETER_ELEMENT` WHERE `Cd_Parameter_Element` LIKE 'COMPLEX_TABLE_MARKER'), '', b'1');


SET foreign_key_checks=1;