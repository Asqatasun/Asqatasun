SET foreign_key_checks=0;

INSERT IGNORE INTO `OPTION_VALUE` (`Id_Option`, `Code`, `Description`, `Is_Restriction`, OPTION_FAMILY_Id_Option_Family) VALUES
(16, 'ROBOTS_TXT_ACTIVATION', 'This option determines whether the crawl respects the robots.txt directives', b'1', 1);

SET foreign_key_checks=1;
