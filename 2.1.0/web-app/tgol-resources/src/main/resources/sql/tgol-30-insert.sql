USE $tgDatabase;

INSERT IGNORE INTO `TGSI_ROLE` (`Id_Role`, `Role_Name`) VALUES
(1, 'ROLE_GUEST'),
(2, 'ROLE_USER');

INSERT IGNORE INTO `TGSI_ROLE` (`Id_Role`, `Role_Name`, `ROLE_Id_Role`) VALUES
(3, 'ROLE_ADMIN', '2');

INSERT IGNORE INTO `TGSI_SCOPE` (`Id_Scope`, `Code`, `Label`) VALUES
(1, 'GROUPOFPAGES', 'Group of pages'),
(2, 'DOMAIN', 'Domain'),
(3, 'PAGE', 'Page'),
(4, 'FILE', 'File'),
(5, 'GROUPOFFILES', 'Group of files'), 
(6, 'SCENARIO', 'Scenario');

INSERT IGNORE INTO `TGSI_FUNCTIONALITY` (`Id_Functionality`, `Code`, `Label`, `Description`) VALUES
(1, 'PAGES', 'Audit Pages (Up to 10)', ''),
(2, 'DOMAIN', 'Audit Domain ', ''),
(3, 'UPLOAD', 'Audit Upload', ''), 
(4, 'SCENARIO', 'Audit Scenario', '');

INSERT IGNORE INTO `TGSI_REFERENTIAL` (`Id_Referential`, `Code`, `Label`) VALUES
(1, 'AW21', 'Accessiweb 2.1 referential'), 
(2, 'Seo', 'Seo referential');

INSERT IGNORE INTO `TGSI_OPTION_FAMILY` (`Id_Option_Family`, `Code`, `Label`) VALUES
(1, 'CRAWL', ''),
(2, 'ACT_RESTRICTION', ''),
(3, 'ACT_MANAGEMENT', ''),
(4, 'CONTRACT_MANAGEMENT', ''),
(5, 'Seo_TEST_WEIGHT_MANAGEMENT', '');

INSERT IGNORE INTO `TGSI_OPTION` (`Id_Option`, `Code`, `Description`, `Is_Restriction`, OPTION_FAMILY_Id_Option_Family) VALUES
(1, 'ACT_LIMITATION', 'The act limitation unit is the number of acts', b'1', 2),
(2, 'ACT_BY_IP_LIMITATION', 'The act by ip limitation unit is the number of acts in a period for a given ip.
The period is expressed in seconds and the format is \"nb_of_acts/period\"', b'1', 2),
(3, 'MAX_DOCUMENTS', 'This restriction limits the max number of crawled documents',b'1', 1),
(4, 'FORDIDDEN_REFERENTIAL', 'This restriction forbids the access to a referential', b'1', 1),
(5, 'DEPTH', 'This restriction limits the depth of the crawl', b'1', 1),
(6, 'MAX_DURATION', 'This restriction limits the duration of the crawl', b'1', 1),
(7, 'EXCLUSION_REGEXP', 'This restriction applies an exclusion rule on crawled Urls', b'1', 1),
(8, 'ACT_LIFETIME','This restriction determines the lifetime of each associated with the contract', b'1', 3),
(9, 'NB_OF_AUDIT_TO_DISPLAY','This restriction determines the number of audit results that can be displayed on the contract page', b'1', 4), 
(10,'DOMAIN', 'Domain associated with a contract', b'1', 4),
(11, 'Seo-01011', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(12, 'Seo-01012', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(13, 'Seo-01013', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(14, 'Seo-01021', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(15, 'Seo-01031', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(16, 'Seo-01041', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(17, 'Seo-01051', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(18, 'Seo-01061', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(19, 'Seo-02011', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(20, 'Seo-02012', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(21, 'Seo-02013', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(22, 'Seo-03011', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(23, 'Seo-03012', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(24, 'Seo-05011', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(25, 'Seo-05012', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(26, 'Seo-05013', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(27, 'Seo-06011', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(28, 'Seo-06021', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(29, 'Seo-06031', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(30, 'Seo-06041', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(31, 'Seo-06051', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(32, 'Seo-06052', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(33, 'Seo-07011', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(34, 'Seo-07012', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(35, 'Seo-07021', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(36, 'Seo-07051', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(37, 'Seo-07061', 'Weight of rule Seo-01011 overriden by user', b'0', 5),
(38, 'Seo-08011', 'Weight of rule Seo-01011 overriden by user', b'0', 5);

INSERT IGNORE INTO `TGSI_OPTION_ELEMENT` (`Id_Option_Element`,`OPTION_Id_Option`, `Value`) VALUES
(1, 1, '5'),
(2, 2, '5/3600'),
(3, 3, '100'),
(4, 3, '10000'),
(5, 3, '20000'),
(6, 4, 'Seo'),
(7, 8, '-1'),
(8, 8, '5'),
(9, 8, '30'),
(10, 8, '365'),
(11, 9, '5'),
(12, 9, '10'),
(13, 9, '50'),
(14, 9, '100');