SET foreign_key_checks=0;


--
-- Contenu de la table `reference`
--
INSERT IGNORE INTO `REFERENCE` (`ID_REFERENCE`, `CD_REFERENCE`, `DESCRIPTION`, `LABEL`, `URL`, `RANK`) VALUES
(4, 'AW22', NULL, 'AccessiWeb 2.2', 'http://www.accessiweb.org/index.php/accessiweb-22-english-version.html', 4);


--
-- Dumping data for table `LEVEL`
--

INSERT IGNORE INTO `LEVEL` (`Id_Level`, `Cd_Level`, `Label`, `Description`, `Rank`) VALUES
(1, 'Bz', 'Bronze', NULL, 10),
(2, 'Ar', 'Argent', NULL, 20),
(3, 'Or', 'Or', NULL, 30);


--
-- Contenu de la table `theme`
--
INSERT IGNORE INTO `THEME` (`ID_THEME`, `CD_THEME`, `DESCRIPTION`, `LABEL`, `RANK`) VALUES
(31, 'AW22-1', NULL, 'Images', 1),
(32, 'AW22-2', NULL, 'Cadres', 2),
(33, 'AW22-3', NULL, 'Couleurs', 3),
(34, 'AW22-4', NULL, 'Multimédia', 4),
(35, 'AW22-5', NULL, 'Tableaux', 5),
(36, 'AW22-6', NULL, 'Liens', 6),
(37, 'AW22-7', NULL, 'Script', 7),
(38, 'AW22-8', NULL, 'Éléments Obligatoires', 8),
(39, 'AW22-9', NULL, 'Structuration de l''information', 9),
(40, 'AW22-10', NULL, 'Présentation de l''information', 10),
(41, 'AW22-11', NULL, 'Formulaires', 11),
(42, 'AW22-12', NULL, 'Navigation', 12),
(43, 'AW22-13', NULL, 'Consultation', 13);


--
-- Contenu de la table `criterion`
--
INSERT IGNORE INTO `CRITERION` (`ID_CRITERION`, `reference_ID_REFERENCE`, `theme_ID_THEME`, `CD_CRITERION`, `DESCRIPTION`, `LABEL`, `URL`, `RANK`) VALUES
(201, 4, 31, 'AW22-0101', '', '1.1', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-1-1 ', 201),
(202, 4, 31, 'AW22-0102', '', '1.2', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-1-2 ', 202),
(203, 4, 31, 'AW22-0103', '', '1.3', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-1-3 ', 203),
(204, 4, 31, 'AW22-0104', '', '1.4', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-1-4 ', 204),
(205, 4, 31, 'AW22-0105', '', '1.5', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-1-5 ', 205),
(206, 4, 31, 'AW22-0106', '', '1.6', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-1-6 ', 206),
(207, 4, 31, 'AW22-0107', '', '1.7', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-1-7 ', 207),
(208, 4, 31, 'AW22-0108', '', '1.8', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-1-8 ', 208),
(209, 4, 31, 'AW22-0109', '', '1.9', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-1-9 ', 209),
(210, 4, 32, 'AW22-0201', '', '2.1', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-2-1 ', 210),
(211, 4, 32, 'AW22-0202', '', '2.2', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-2-2 ', 211),
(212, 4, 33, 'AW22-0301', '', '3.1', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-3-1 ', 212),
(213, 4, 33, 'AW22-0302', '', '3.2', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-3-2 ', 213),
(214, 4, 33, 'AW22-0303', '', '3.3', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-3-3 ', 214),
(215, 4, 33, 'AW22-0304', '', '3.4', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-3-4 ', 215),
(216, 4, 34, 'AW22-0401', '', '4.1', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-4-1 ', 216),
(217, 4, 34, 'AW22-0402', '', '4.2', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-4-2 ', 219),
(218, 4, 34, 'AW22-0403', '', '4.3', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-4-3 ', 218),
(219, 4, 34, 'AW22-0404', '', '4.4', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-4-4 ', 219),
(220, 4, 34, 'AW22-0405', '', '4.5', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-4-5 ', 220),
(221, 4, 34, 'AW22-0406', '', '4.6', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-4-6 ', 221),
(222, 4, 34, 'AW22-0407', '', '4.7', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-4-7 ', 222),
(223, 4, 34, 'AW22-0408', '', '4.8', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-4-8 ', 223),
(224, 4, 34, 'AW22-0409', '', '4.9', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-4-9 ', 224),
(225, 4, 34, 'AW22-0410', '', '4.10', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-4-10 ', 225),
(226, 4, 34, 'AW22-0411', '', '4.11', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-4-11 ', 226),
(227, 4, 34, 'AW22-0412', '', '4.12', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-4-12 ', 227),
(228, 4, 34, 'AW22-0413', '', '4.13', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-4-13 ', 228),
(229, 4, 34, 'AW22-0414', '', '4.14', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-4-14 ', 229),
(230, 4, 34, 'AW22-0415', '', '4.15', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-4-15 ', 230),
(231, 4, 34, 'AW22-0416', '', '4.16', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-4-16 ', 231),
(232, 4, 34, 'AW22-0417', '', '4.17', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-4-17 ', 232),
(233, 4, 34, 'AW22-0418', '', '4.18', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-4-18 ', 233),
(234, 4, 34, 'AW22-0419', '', '4.19', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-4-19 ', 234),
(235, 4, 34, 'AW22-0420', '', '4.20', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-4-20 ', 235),
(236, 4, 34, 'AW22-0421', '', '4.21', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-4-21 ', 236),
(237, 4, 34, 'AW22-0422', '', '4.22', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-4-22 ', 237),
(238, 4, 35, 'AW22-0501', '', '5.1', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-5-1 ', 238),
(239, 4, 35, 'AW22-0502', '', '5.2', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-5-2 ', 239),
(240, 4, 35, 'AW22-0503', '', '5.3', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-5-3 ', 240),
(241, 4, 35, 'AW22-0504', '', '5.4', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-5-4 ', 241),
(242, 4, 35, 'AW22-0505', '', '5.5', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-5-5 ', 242),
(243, 4, 35, 'AW22-0506', '', '5.6', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-5-6 ', 243),
(244, 4, 35, 'AW22-0507', '', '5.7', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-5-7 ', 244),
(245, 4, 35, 'AW22-0508', '', '5.8', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-5-8 ', 245),
(246, 4, 36, 'AW22-0601', '', '6.1', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-6-1 ', 246),
(247, 4, 36, 'AW22-0602', '', '6.2', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-6-2 ', 247),
(248, 4, 36, 'AW22-0603', '', '6.3', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-6-3 ', 248),
(249, 4, 36, 'AW22-0604', '', '6.4', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-6-4 ', 249),
(250, 4, 36, 'AW22-0605', '', '6.5', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-6-5 ', 250),
(251, 4, 36, 'AW22-0606', '', '6.6', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-6-6 ', 251),
(252, 4, 37, 'AW22-0701', '', '7.1', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-7-1 ', 252),
(253, 4, 37, 'AW22-0702', '', '7.2', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-7-2 ', 253),
(254, 4, 37, 'AW22-0703', '', '7.3', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-7-3 ', 254),
(255, 4, 37, 'AW22-0704', '', '7.4', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-7-4 ', 255),
(256, 4, 37, 'AW22-0705', '', '7.5', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-7-5 ', 256),
(257, 4, 37, 'AW22-0706', '', '7.6', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-7-6 ', 257),
(258, 4, 38, 'AW22-0801', '', '8.1', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-8-1 ', 258),
(259, 4, 38, 'AW22-0802', '', '8.2', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-8-2 ', 259),
(260, 4, 38, 'AW22-0803', '', '8.3', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-8-3 ', 260),
(261, 4, 38, 'AW22-0804', '', '8.4', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-8-4 ', 261),
(262, 4, 38, 'AW22-0805', '', '8.5', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-8-5 ', 262),
(263, 4, 38, 'AW22-0806', '', '8.6', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-8-6 ', 263),
(264, 4, 38, 'AW22-0807', '', '8.7', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-8-7 ', 264),
(265, 4, 38, 'AW22-0808', '', '8.8', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-8-8 ', 265),
(266, 4, 38, 'AW22-0809', '', '8.9', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-8-9 ', 266),
(267, 4, 38, 'AW22-0810', '', '8.10', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-8-10 ', 267),
(268, 4, 39, 'AW22-0901', '', '9.1', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-9-1 ', 268),
(269, 4, 39, 'AW22-0902', '', '9.2', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-9-2 ', 269),
(270, 4, 39, 'AW22-0903', '', '9.3', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-9-3 ', 270),
(271, 4, 39, 'AW22-0904', '', '9.4', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-9-4 ', 271),
(272, 4, 39, 'AW22-0905', '', '9.5', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-9-5 ', 272),
(273, 4, 40, 'AW22-1001', '', '10.1', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-10-1 ', 273),
(274, 4, 40, 'AW22-1002', '', '10.2', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-10-2 ', 274),
(275, 4, 40, 'AW22-1003', '', '10.3', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-10-3 ', 275),
(276, 4, 40, 'AW22-1004', '', '10.4', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-10-4 ', 276),
(277, 4, 40, 'AW22-1005', '', '10.5', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-10-5 ', 277),
(278, 4, 40, 'AW22-1006', '', '10.6', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-10-6 ', 278),
(279, 4, 40, 'AW22-1007', '', '10.7', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-10-7 ', 279),
(280, 4, 40, 'AW22-1008', '', '10.8', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-10-8 ', 280),
(281, 4, 40, 'AW22-1009', '', '10.9', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-10-9 ', 281),
(282, 4, 40, 'AW22-1010', '', '10.10', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-10-10 ', 282),
(283, 4, 40, 'AW22-1011', '', '10.11', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-10-11 ', 283),
(284, 4, 40, 'AW22-1012', '', '10.12', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-10-12 ', 284),
(285, 4, 40, 'AW22-1013', '', '10.13', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-10-13 ', 285),
(286, 4, 40, 'AW22-1014', '', '10.14', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-10-14 ', 286),
(287, 4, 40, 'AW22-1015', '', '10.15', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-10-15 ', 287),
(288, 4, 41, 'AW22-1101', '', '11.1', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-11-1 ', 288),
(289, 4, 41, 'AW22-1102', '', '11.2', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-11-2 ', 289),
(290, 4, 41, 'AW22-1103', '', '11.3', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-11-3 ', 290),
(291, 4, 41, 'AW22-1104', '', '11.4', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-11-4 ', 291),
(292, 4, 41, 'AW22-1105', '', '11.5', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-11-5 ', 292),
(293, 4, 41, 'AW22-1106', '', '11.6', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-11-6 ', 293),
(294, 4, 41, 'AW22-1107', '', '11.7', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-11-7 ', 294),
(295, 4, 41, 'AW22-1108', '', '11.8', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-11-8 ', 295),
(296, 4, 41, 'AW22-1109', '', '11.9', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-11-9 ', 296),
(297, 4, 41, 'AW22-1110', '', '11.10', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-11-10 ', 297),
(298, 4, 41, 'AW22-1111', '', '11.11', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-11-11 ', 298),
(299, 4, 41, 'AW22-1112', '', '11.12', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-11-12 ', 299),
(300, 4, 41, 'AW22-1113', '', '11.13', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-11-13 ', 300),
(301, 4, 41, 'AW22-1114', '', '11.14', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-11-14 ', 301),
(302, 4, 41, 'AW22-1115', '', '11.15', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-11-15 ', 302),
(303, 4, 42, 'AW22-1201', '', '12.1', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-12-1 ', 303),
(304, 4, 42, 'AW22-1202', '', '12.2', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-12-2 ', 304),
(305, 4, 42, 'AW22-1203', '', '12.3', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-12-3 ', 305),
(306, 4, 42, 'AW22-1204', '', '12.4', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-12-4 ', 306),
(307, 4, 42, 'AW22-1205', '', '12.5', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-12-5 ', 307),
(308, 4, 42, 'AW22-1206', '', '12.6', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-12-6 ', 308),
(309, 4, 42, 'AW22-1207', '', '12.7', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-12-7 ', 309),
(310, 4, 42, 'AW22-1208', '', '12.8', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-12-8 ', 310),
(311, 4, 42, 'AW22-1209', '', '12.9', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-12-9 ', 311),
(312, 4, 42, 'AW22-1210', '', '12.10', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-12-10 ', 312),
(313, 4, 42, 'AW22-1211', '', '12.11', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-12-11 ', 313),
(314, 4, 42, 'AW22-1212', '', '12.12', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-12-12 ', 314),
(315, 4, 42, 'AW22-1213', '', '12.13', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-12-13 ', 315),
(316, 4, 42, 'AW22-1214', '', '12.14', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-12-14 ', 316),
(317, 4, 43, 'AW22-1301', '', '13.1', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-13-1 ', 319),
(318, 4, 43, 'AW22-1302', '', '13.2', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-13-2 ', 318),
(319, 4, 43, 'AW22-1303', '', '13.3', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-13-3 ', 319),
(320, 4, 43, 'AW22-1304', '', '13.4', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-13-4 ', 320),
(321, 4, 43, 'AW22-1305', '', '13.5', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-13-5 ', 321),
(322, 4, 43, 'AW22-1306', '', '13.6', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-13-6 ', 322),
(323, 4, 43, 'AW22-1307', '', '13.7', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-13-7 ', 323),
(324, 4, 43, 'AW22-1308', '', '13.8', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-13-8 ', 324),
(325, 4, 43, 'AW22-1309', '', '13.9', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-13-9 ', 325),
(326, 4, 43, 'AW22-1310', '', '13.10', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-13-10 ', 326),
(327, 4, 43, 'AW22-1311', '', '13.11', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-13-11 ', 327),
(328, 4, 43, 'AW22-1312', '', '13.12', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-13-12 ', 328),
(329, 4, 43, 'AW22-1313', '', '13.13', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-13-13 ', 329),
(330, 4, 43, 'AW22-1314', '', '13.14', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-13-14 ', 330),
(331, 4, 43, 'AW22-1315', '', '13.15', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-13-15 ', 331),
(332, 4, 43, 'AW22-1316', '', '13.16', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-13-16 ', 332),
(333, 4, 43, 'AW22-1317', '', '13.17', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#crit-13-17 ', 333);

--
-- Contenu de la table `test`
--
INSERT IGNORE INTO `TEST` (`Id_Test`, `Cd_Test`, `Description`, `Label`, `Rank`, `Weight`, `Rule_Archive_Name`, `Rule_Class_Name`, `Id_Criterion`, `Id_Decision_Level`, `Id_Level`, `Id_Scope`, `Rule_Design_Url`, `No_Process`) VALUES
(501, 'AW22-01011', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.1.1', 3010101, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01011', 201, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-1-1', b'1'),
(502, 'AW22-01012', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.1.2', 3010102, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01012', 201, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-1-2', b'1'),
(503, 'AW22-01013', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.1.3', 3010103, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01013', 201, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-1-3', b'1'),
(504, 'AW22-01014', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.1.4', 3010104, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01014', 201, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-1-4', b'1'),
(505, 'AW22-01021', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.2.1', 3010201, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01021', 202, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-2-1', b'1'),
(506, 'AW22-01022', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.2.2', 3010202, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01022', 202, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-2-2', b'1'),
(507, 'AW22-01023', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.2.3', 3010203, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01023', 202, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-2-3', b'1'),
(508, 'AW22-01024', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.2.4', 3010204, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01024', 202, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-2-4', b'1'),
(509, 'AW22-01025', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.2.5', 3010205, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01025', 202, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-2-5', b'1'),
(510, 'AW22-01031', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.3.1', 3010301, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01031', 203, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-3-1', b'1'),
(511, 'AW22-01032', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.3.2', 3010302, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01032', 203, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-3-2', b'1'),
(512, 'AW22-01033', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.3.3', 3010303, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01033', 203, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-3-3', b'1'),
(513, 'AW22-01034', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.3.4', 3010304, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01034', 203, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-3-4', b'1'),
(514, 'AW22-01035', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.3.5', 3010305, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01035', 203, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-3-5', b'1'),
(515, 'AW22-01036', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.3.6', 3010306, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01036', 203, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-3-6', b'1'),
(516, 'AW22-01037', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.3.7', 3010307, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01037', 203, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-3-7', b'1'),
(517, 'AW22-01041', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.4.1', 3010401, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01041', 204, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-4-1', b'1'),
(518, 'AW22-01042', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.4.2', 3010402, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01042', 204, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-4-2', b'1'),
(519, 'AW22-01043', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.4.3', 3010403, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01043', 204, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-4-3', b'1'),
(520, 'AW22-01044', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.4.4', 3010404, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01044', 204, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-4-4', b'1'),
(521, 'AW22-01045', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.4.5', 3010405, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01045', 204, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-4-5', b'1'),
(522, 'AW22-01046', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.4.6', 3010406, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01046', 204, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-4-6', b'1'),
(523, 'AW22-01051', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.5.1', 3010501, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01051', 205, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-5-1', b'1'),
(524, 'AW22-01052', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.5.2', 3010502, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01052', 205, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-5-2', b'1'),
(525, 'AW22-01061', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.6.1', 3010601, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01061', 206, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-6-1', b'1'),
(526, 'AW22-01062', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.6.2', 3010602, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01062', 206, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-6-2', b'1'),
(527, 'AW22-01063', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.6.3', 3010603, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01063', 206, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-6-3', b'1'),
(528, 'AW22-01064', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.6.4', 3010604, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01064', 206, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-6-4', b'1'),
(529, 'AW22-01065', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.6.5', 3010605, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01065', 206, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-6-5', b'1'),
(530, 'AW22-01071', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.7.1', 3010701, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01071', 207, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-7-1', b'1'),
(531, 'AW22-01072', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.7.2', 3010702, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01072', 207, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-7-2', b'1'),
(532, 'AW22-01073', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.7.3', 3010703, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01073', 207, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-7-3', b'1'),
(533, 'AW22-01074', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.7.4', 3010704, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01074', 207, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-7-4', b'1'),
(534, 'AW22-01081', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.8.1', 3010801, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01081', 208, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-8-1', b'1'),
(535, 'AW22-01082', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.8.2', 3010802, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01082', 208, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-8-2', b'1'),
(536, 'AW22-01083', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.8.3', 3010803, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01083', 208, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-8-3', b'1'),
(537, 'AW22-01084', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.8.4', 3010804, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01084', 208, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-8-4', b'1'),
(538, 'AW22-01085', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.8.5', 3010805, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01085', 208, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-8-5', b'1'),
(539, 'AW22-01086', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.8.6', 3010806, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01086', 208, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-8-6', b'1'),
(540, 'AW22-01091', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.9.1', 3010901, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01091', 209, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-9-1', b'1'),
(541, 'AW22-01092', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.9.2', 3010902, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01092', 209, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-9-2', b'1'),
(542, 'AW22-01093', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.9.3', 3010903, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01093', 209, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-9-3', b'1'),
(543, 'AW22-01094', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.9.4', 3010904, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01094', 209, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-9-4', b'1'),
(544, 'AW22-01095', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.9.5', 3010905, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01095', 209, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-9-5', b'1'),
(545, 'AW22-01096', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#images', '1.9.6', 3010906, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule01096', 209, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-1-9-6', b'1'),
(546, 'AW22-02011', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#cadres', '2.1.1', 3020101, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule02011', 210, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-2-1-1', b'1'),
(547, 'AW22-02012', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#cadres', '2.1.2', 3020102, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule02012', 210, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-2-1-2', b'1'),
(548, 'AW22-02021', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#cadres', '2.2.1', 3020201, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule02021', 211, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-2-2-1', b'1'),
(549, 'AW22-02022', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#cadres', '2.2.2', 3020202, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule02022', 211, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-2-2-2', b'1'),
(550, 'AW22-03011', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#couleurs', '3.1.1', 3030101, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule03011', 212, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-3-1-1', b'1'),
(551, 'AW22-03012', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#couleurs', '3.1.2', 3030102, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule03012', 212, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-3-1-2', b'1'),
(552, 'AW22-03013', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#couleurs', '3.1.3', 3030103, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule03013', 212, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-3-1-3', b'1'),
(553, 'AW22-03014', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#couleurs', '3.1.4', 3030104, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule03014', 212, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-3-1-4', b'1'),
(554, 'AW22-03015', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#couleurs', '3.1.5', 3030105, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule03015', 212, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-3-1-5', b'1'),
(555, 'AW22-03016', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#couleurs', '3.1.6', 3030106, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule03016', 212, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-3-1-6', b'1'),
(556, 'AW22-03021', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#couleurs', '3.2.1', 3030201, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule03021', 213, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-3-2-1', b'1'),
(557, 'AW22-03022', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#couleurs', '3.2.2', 3030202, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule03022', 213, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-3-2-2', b'1'),
(558, 'AW22-03023', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#couleurs', '3.2.3', 3030203, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule03023', 213, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-3-2-3', b'1'),
(559, 'AW22-03024', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#couleurs', '3.2.4', 3030204, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule03024', 213, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-3-2-4', b'1'),
(560, 'AW22-03025', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#couleurs', '3.2.5', 3030205, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule03025', 213, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-3-2-5', b'1'),
(561, 'AW22-03026', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#couleurs', '3.2.6', 3030206, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule03026', 213, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-3-2-6', b'1'),
(562, 'AW22-03031', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#couleurs', '3.3.1', 3030301, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule03031', 214, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-3-3-1', b'1'),
(563, 'AW22-03032', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#couleurs', '3.3.2', 3030302, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule03032', 214, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-3-3-2', b'1'),
(564, 'AW22-03033', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#couleurs', '3.3.3', 3030303, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule03033', 214, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-3-3-3', b'1'),
(565, 'AW22-03034', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#couleurs', '3.3.4', 3030304, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule03034', 214, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-3-3-4', b'1'),
(566, 'AW22-03041', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#couleurs', '3.4.1', 3030401, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule03041', 215, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-3-4-1', b'1'),
(567, 'AW22-03042', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#couleurs', '3.4.2', 3030402, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule03042', 215, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-3-4-2', b'1'),
(568, 'AW22-03043', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#couleurs', '3.4.3', 3030403, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule03043', 215, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-3-4-3', b'1'),
(569, 'AW22-03044', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#couleurs', '3.4.4', 3030404, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule03044', 215, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-3-4-4', b'1'),
(570, 'AW22-04011', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.1.1', 3040101, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04011', 216, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-1-1', b'1'),
(571, 'AW22-04012', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.1.2', 3040102, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04012', 216, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-1-2', b'1'),
(572, 'AW22-04013', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.1.3', 3040103, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04013', 216, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-1-3', b'1'),
(573, 'AW22-04014', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.1.4', 3040104, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04014', 216, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-1-4', b'1'),
(574, 'AW22-04021', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.2.1', 3040201, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04021', 217, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-2-1', b'1'),
(575, 'AW22-04022', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.2.2', 3040202, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04022', 217, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-2-2', b'1'),
(576, 'AW22-04023', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.2.3', 3040203, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04023', 217, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-2-3', b'1'),
(577, 'AW22-04031', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.3.1', 3040301, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04031', 218, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-3-1', b'1'),
(578, 'AW22-04041', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.4.1', 3040401, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04041', 219, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-4-1', b'1'),
(579, 'AW22-04051', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.5.1', 3040501, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04051', 220, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-5-1', b'1'),
(580, 'AW22-04052', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.5.2', 3040502, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04052', 220, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-5-2', b'1'),
(581, 'AW22-04053', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.5.3', 3040503, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04053', 220, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-5-3', b'1'),
(582, 'AW22-04061', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.6.1', 3040601, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04061', 221, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-6-1', b'1'),
(583, 'AW22-04062', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.6.2', 3040602, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04062', 221, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-6-2', b'1'),
(584, 'AW22-04071', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.7.1', 3040701, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04071', 222, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-7-1', b'1'),
(585, 'AW22-04072', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.7.2', 3040702, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04072', 222, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-7-2', b'1'),
(586, 'AW22-04081', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.8.1', 3040801, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04081', 223, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-8-1', b'1'),
(587, 'AW22-04082', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.8.2', 3040802, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04082', 223, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-8-2', b'1'),
(588, 'AW22-04091', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.9.1', 3040901, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04091', 224, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-9-1', b'1'),
(589, 'AW22-04092', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.9.2', 3040902, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04092', 224, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-9-2', b'1'),
(590, 'AW22-04101', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.10.1', 3041001, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04101', 225, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-10-1', b'1'),
(591, 'AW22-04102', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.10.2', 3041002, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04102', 225, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-10-2', b'1'),
(592, 'AW22-04111', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.11.1', 3041101, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04111', 226, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-11-1', b'1'),
(593, 'AW22-04112', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.11.2', 3041102, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04112', 226, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-11-2', b'1'),
(594, 'AW22-04121', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.12.1', 3041201, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04121', 227, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-12-1', b'1'),
(595, 'AW22-04122', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.12.2', 3041202, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04122', 227, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-12-2', b'1'),
(596, 'AW22-04131', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.13.1', 3041301, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04131', 228, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-13-1', b'1'),
(597, 'AW22-04132', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.13.2', 3041302, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04132', 228, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-13-2', b'1'),
(598, 'AW22-04133', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.13.3', 3041303, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04133', 228, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-13-3', b'1'),
(599, 'AW22-04141', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.14.1', 3041401, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04141', 229, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-14-1', b'1'),
(600, 'AW22-04142', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.14.2', 3041402, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04142', 229, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-14-2', b'1'),
(601, 'AW22-04151', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.15.1', 3041501, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04151', 230, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-15-1', b'1'),
(602, 'AW22-04152', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.15.2', 3041502, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04152', 230, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-15-2', b'1'),
(603, 'AW22-04161', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.16.1', 3041601, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04161', 231, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-16-1', b'1'),
(604, 'AW22-04162', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.16.2', 3041602, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04162', 231, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-16-2', b'1'),
(605, 'AW22-04171', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.17.1', 3041701, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04171', 232, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-17-1', b'1'),
(606, 'AW22-04181', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.18.1', 3041801, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04181', 233, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-18-1', b'1'),
(607, 'AW22-04191', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.19.1', 3041901, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04191', 234, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-19-1', b'1'),
(608, 'AW22-04201', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.20.1', 3042001, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04201', 235, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-20-1', b'1'),
(609, 'AW22-04202', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.20.2', 3042002, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04202', 235, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-20-2', b'1'),
(610, 'AW22-04203', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.20.3', 3042003, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04203', 235, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-20-3', b'1'),
(611, 'AW22-04211', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.21.1', 3042101, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04211', 236, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-21-1', b'1'),
(612, 'AW22-04212', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.21.2', 3042102, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04212', 236, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-21-2', b'1'),
(613, 'AW22-04221', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#multimedia', '4.22.1', 3042201, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule04221', 237, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-4-22-1', b'1'),
(614, 'AW22-05011', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#tableaux', '5.1.1', 3050101, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule05011', 238, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-5-1-1', b'1'),
(615, 'AW22-05021', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#tableaux', '5.2.1', 3050201, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule05021', 239, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-5-2-1', b'1'),
(616, 'AW22-05022', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#tableaux', '5.2.2', 3050202, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule05022', 239, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-5-2-2', b'1'),
(617, 'AW22-05031', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#tableaux', '5.3.1', 3050301, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule05031', 240, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-5-3-1', b'1'),
(618, 'AW22-05041', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#tableaux', '5.4.1', 3050401, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule05041', 241, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-5-4-1', b'1'),
(619, 'AW22-05051', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#tableaux', '5.5.1', 3050501, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule05051', 242, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-5-5-1', b'1'),
(620, 'AW22-05061', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#tableaux', '5.6.1', 3050601, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule05061', 243, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-5-6-1', b'1'),
(621, 'AW22-05062', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#tableaux', '5.6.2', 3050602, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule05062', 243, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-5-6-2', b'1'),
(622, 'AW22-05071', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#tableaux', '5.7.1', 3050701, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule05071', 244, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-5-7-1', b'1'),
(623, 'AW22-05072', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#tableaux', '5.7.2', 3050702, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule05072', 244, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-5-7-2', b'1'),
(624, 'AW22-05073', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#tableaux', '5.7.3', 3050703, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule05073', 244, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-5-7-3', b'1'),
(625, 'AW22-05074', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#tableaux', '5.7.4', 3050704, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule05074', 244, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-5-7-4', b'1'),
(626, 'AW22-05081', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#tableaux', '5.8.1', 3050801, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule05081', 245, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-5-8-1', b'1'),
(627, 'AW22-06011', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#liens', '6.1.1', 3060101, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule06011', 246, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-6-1-1', b'1'),
(628, 'AW22-06012', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#liens', '6.1.2', 3060102, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule06012', 246, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-6-1-2', b'1'),
(629, 'AW22-06013', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#liens', '6.1.3', 3060103, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule06013', 246, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-6-1-3', b'1'),
(630, 'AW22-06014', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#liens', '6.1.4', 3060104, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule06014', 246, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-6-1-4', b'1'),
(631, 'AW22-06021', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#liens', '6.2.1', 3060201, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule06021', 247, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-6-2-1', b'1'),
(632, 'AW22-06022', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#liens', '6.2.2', 3060202, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule06022', 247, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-6-2-2', b'1'),
(633, 'AW22-06023', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#liens', '6.2.3', 3060203, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule06023', 247, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-6-2-3', b'1'),
(634, 'AW22-06024', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#liens', '6.2.4', 3060204, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule06024', 247, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-6-2-4', b'1'),
(635, 'AW22-06031', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#liens', '6.3.1', 3060301, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule06031', 248, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-6-3-1', b'1'),
(636, 'AW22-06032', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#liens', '6.3.2', 3060302, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule06032', 248, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-6-3-2', b'1'),
(637, 'AW22-06033', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#liens', '6.3.3', 3060303, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule06033', 248, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-6-3-3', b'1'),
(638, 'AW22-06034', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#liens', '6.3.4', 3060304, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule06034', 248, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-6-3-4', b'1'),
(639, 'AW22-06041', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#liens', '6.4.1', 3060401, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule06041', 249, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-6-4-1', b'1'),
(640, 'AW22-06042', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#liens', '6.4.2', 3060403, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule06042', 249, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-6-4-2', b'1'),
(641, 'AW22-06043', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#liens', '6.4.3', 3060403, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule06043', 249, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-6-4-3', b'1'),
(642, 'AW22-06044', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#liens', '6.4.4', 3060404, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule06044', 249, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-6-4-4', b'1'),
(643, 'AW22-06051', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#liens', '6.5.1', 3060501, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule06051', 250, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-6-5-1', b'1'),
(644, 'AW22-06052', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#liens', '6.5.2', 3060502, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule06052', 250, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-6-5-2', b'1'),
(645, 'AW22-06053', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#liens', '6.5.3', 3060503, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule06053', 250, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-6-5-3', b'1'),
(646, 'AW22-06061', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#liens', '6.6.1', 3060601, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule06061', 251, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-6-6-1', b'1'),
(647, 'AW22-07011', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#script', '7.1.1', 3070101, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule07011', 252, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-7-1-1', b'1'),
(648, 'AW22-07012', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#script', '7.1.2', 3070102, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule07012', 252, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-7-1-2', b'1'),
(649, 'AW22-07021', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#script', '7.2.1', 3070201, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule07021', 253, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-7-2-1', b'1'),
(650, 'AW22-07022', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#script', '7.2.2', 3070202, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule07022', 253, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-7-2-2', b'1'),
(651, 'AW22-07031', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#script', '7.3.1', 3070301, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule07031', 254, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-7-3-1', b'1'),
(652, 'AW22-07032', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#script', '7.3.2', 3070302, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule07032', 254, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-7-3-2', b'1'),
(653, 'AW22-07033', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#script', '7.3.3', 3070303, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule07033', 254, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-7-3-3', b'1'),
(654, 'AW22-07041', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#script', '7.4.1', 3070401, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule07041', 255, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-7-4-1', b'1'),
(655, 'AW22-07042', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#script', '7.4.2', 3070402, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule07042', 255, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-7-4-2', b'1'),
(656, 'AW22-07051', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#script', '7.5.1', 3070501, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule07051', 256, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-7-5-1', b'1'),
(657, 'AW22-07061', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#script', '7.6.1', 3070601, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule07061', 257, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-7-6-1', b'1'),
(658, 'AW22-08011', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '8.1.1', 3080101, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule08011', 258, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-8-1-1', b'1'),
(659, 'AW22-08012', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '8.1.2', 3080102, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule08012', 258, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-8-1-2', b'1'),
(660, 'AW22-08013', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#elements', '8.1.3', 3080103, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule08013', 258, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-8-1-3', b'1'),
(661, 'AW22-08021', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#elements', '8.2.1', 3080201, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule08021', 259, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-8-2-1', b'1'),
(662, 'AW22-08022', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#elements', '8.2.2', 3080202, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule08022', 259, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-8-2-2', b'1'),
(663, 'AW22-08031', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '8.3.1', 3080301, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule08031', 260, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-8-3-1', b'1'),
(664, 'AW22-08041', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '8.4.1', 3080401, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule08041', 261, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-8-4-1', b'1'),
(665, 'AW22-08051', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#elements', '8.5.1', 3080501, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule08051', 262, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-8-5-1', b'1'),
(666, 'AW22-08061', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#elements', '8.6.1', 3080601, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule08061', 263, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-8-6-1', b'1'),
(667, 'AW22-08071', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#elements', '8.7.1', 3080701, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule08071', 264, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-8-7-1', b'1'),
(668, 'AW22-08081', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#elements', '8.8.1', 3080801, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule08081', 265, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-8-8-1', b'1'),
(669, 'AW22-08082', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#elements', '8.8.2', 3080802, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule08082', 265, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-8-8-2', b'1'),
(670, 'AW22-08091', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#elements', '8.9.1', 3080901, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule08091', 266, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-8-9-1', b'1'),
(671, 'AW22-08101', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#elements', '8.10.1', 2081001, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule08101', 267, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-8-10-1', b'1'),
(672, 'AW22-09011', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#structure', '9.1.1', 3090101, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule09011', 268, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-9-1-1', b'1'),
(673, 'AW22-09012', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#structure', '9.1.2', 3090102, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule09012', 268, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-9-1-2', b'1'),
(674, 'AW22-09013', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#structure', '9.1.3', 3090103, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule09013', 268, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-9-1-3', b'1'),
(675, 'AW22-09014', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#structure', '9.1.4', 3090104, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule09014', 268, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-9-1-4', b'1'),
(676, 'AW22-09021', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#structure', '9.2.1', 3090201, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule09021', 269, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-9-2-1', b'1'),
(677, 'AW22-09022', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#structure', '9.2.2', 3090202, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule09022', 269, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-9-2-2', b'1'),
(678, 'AW22-09023', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#structure', '9.2.3', 3090203, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule09023', 269, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-9-2-3', b'1'),
(679, 'AW22-09031', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#structure', '9.3.1', 3090301, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule09031', 270, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-9-3-1', b'1'),
(680, 'AW22-09032', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#structure', '9.3.2', 3090302, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule09032', 270, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-9-3-2', b'1'),
(681, 'AW22-09041', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#structure', '9.4.1', 3090401, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule09041', 271, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-9-4-1', b'1'),
(682, 'AW22-09042', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#structure', '9.4.2', 3090402, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule09042', 271, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-9-4-2', b'1'),
(683, 'AW22-09051', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#structure', '9.5.1', 3090501, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule09051', 272, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-9-5-1', b'1'),
(684, 'AW22-09052', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#structure', '9.5.2', 3090502, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule09052', 272, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-9-5-2', b'1'),
(685, 'AW22-10011', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.1.1', 3100101, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10011', 273, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-1-1', b'1'),
(686, 'AW22-10012', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.1.2', 3100102, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10012', 273, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-1-2', b'1'),
(687, 'AW22-10013', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.1.3', 3100103, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10013', 273, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-1-3', b'1'),
(688, 'AW22-10021', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.2.1', 3100201, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10021', 274, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-2-1', b'1'),
(689, 'AW22-10022', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.2.2', 3100202, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10022', 274, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-2-2', b'1'),
(690, 'AW22-10023', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.2.3', 3100203, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10023', 274, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-2-3', b'1'),
(691, 'AW22-10031', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.3.1', 3100301, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10031', 275, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-3-1', b'1'),
(692, 'AW22-10041', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.4.1', 3100401, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10041', 276, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-4-1', b'1'),
(693, 'AW22-10042', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.4.2', 3100402, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10042', 276, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-4-2', b'1'),
(694, 'AW22-10043', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.4.3', 3100403, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10043', 276, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-4-3', b'1'),
(695, 'AW22-10051', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.5.1', 3100501, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10051', 277, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-5-1', b'1'),
(696, 'AW22-10052', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.5.2', 3100502, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10052', 277, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-5-2', b'1'),
(697, 'AW22-10053', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.5.3', 3100503, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10053', 277, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-5-3', b'1'),
(698, 'AW22-10061', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.6.1', 3100601, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10061', 278, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-6-1', b'1'),
(699, 'AW22-10071', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.7.1', 3100701, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10071', 279, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-7-1', b'1'),
(700, 'AW22-10072', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.7.2', 3100702, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10072', 279, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-7-2', b'1'),
(701, 'AW22-10073', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.7.3', 3100703, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10073', 279, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-7-3', b'1'),
(702, 'AW22-10081', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.8.1', 3100801, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10081', 280, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-8-1', b'1'),
(703, 'AW22-10082', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.8.2', 3100802, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10082', 280, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-8-2', b'1'),
(704, 'AW22-10083', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.8.3', 3100803, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10083', 280, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-8-3', b'1'),
(705, 'AW22-10084', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.8.4', 3100804, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10084', 280, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-8-4', b'1'),
(706, 'AW22-10091', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.9.1', 3100901, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10091', 281, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-9-1', b'1'),
(707, 'AW22-10101', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.10.1', 3101001, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10101', 282, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-10-1', b'1'),
(708, 'AW22-10111', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.11.1', 3101101, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10111', 283, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-11-1', b'1'),
(709, 'AW22-10121', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.12.1', 3101201, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10121', 284, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-12-1', b'1'),
(710, 'AW22-10122', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.12.2', 3101202, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10122', 284, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-12-2', b'1'),
(711, 'AW22-10131', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.13.1', 3101301, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10131', 285, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-13-1', b'1'),
(712, 'AW22-10141', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.14.1', 3101401, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10141', 286, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-14-1', b'1'),
(713, 'AW22-10142', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.14.2', 3101402, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10142', 286, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-14-2', b'1'),
(714, 'AW22-10143', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.14.3', 3101403, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10143', 286, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-14-3', b'1'),
(715, 'AW22-10144', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.14.4', 3101404, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10144', 286, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-14-4', b'1'),
(716, 'AW22-10151', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.15.1', 3101501, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10151', 287, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-15-1', b'1'),
(717, 'AW22-10152', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.15.2', 3101502, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10152', 287, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-15-2', b'1'),
(718, 'AW22-10153', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.15.3', 3101503, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10153', 287, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-15-3', b'1'),
(719, 'AW22-10154', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#presentation', '10.15.4', 3101504, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule10154', 287, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-10-15-4', b'1'),
(720, 'AW22-11011', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#formulaire', '11.1.1', 3110101, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule11011', 288, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-11-1-1', b'1'),
(721, 'AW22-11012', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#formulaire', '11.1.2', 3110102, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule11012', 288, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-11-1-2', b'1'),
(722, 'AW22-11013', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#formulaire', '11.1.3', 3110103, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule11013', 288, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-11-1-3', b'1'),
(723, 'AW22-11021', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#formulaire', '11.2.1', 3110201, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule11021', 289, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-11-2-1', b'1'),
(724, 'AW22-11022', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#formulaire', '11.2.2', 3110202, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule11022', 289, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-11-2-2', b'1'),
(725, 'AW22-11031', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#formulaire', '11.3.1', 3110301, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule11031', 290, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-11-3-1', b'1'),
(726, 'AW22-11032', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#formulaire', '11.3.2', 3110302, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule11032', 290, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-11-3-2', b'1'),
(727, 'AW22-11041', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#formulaire', '11.4.1', 3110401, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule11041', 291, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-11-4-1', b'1'),
(728, 'AW22-11051', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#formulaire', '11.5.1', 3110501, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule11051', 292, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-11-5-1', b'1'),
(729, 'AW22-11061', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#formulaire', '11.6.1', 3110601, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule11061', 293, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-11-6-1', b'1'),
(730, 'AW22-11071', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#formulaire', '11.7.1', 3110701, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule11071', 294, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-11-7-1', b'1'),
(731, 'AW22-11081', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#formulaire', '11.8.1', 3110801, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule11081', 295, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-11-8-1', b'1'),
(732, 'AW22-11082', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#formulaire', '11.8.2', 3110802, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule11082', 295, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-11-8-2', b'1'),
(733, 'AW22-11083', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#formulaire', '11.8.3', 3110803, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule11083', 295, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-11-8-3', b'1'),
(734, 'AW22-11091', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#formulaire', '11.9.1', 3110901, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule11091', 296, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-11-9-1', b'1'),
(735, 'AW22-11101', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#formulaire', '11.10.1', 3111001, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule11101', 297, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-11-10-1', b'1'),
(736, 'AW22-11102', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#formulaire', '11.10.2', 3111002, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule11102', 297, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-11-10-2', b'1'),
(737, 'AW22-11103', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#formulaire', '11.10.3', 3111003, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule11103', 297, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-11-10-3', b'1'),
(738, 'AW22-11111', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#formulaire', '11.11.1', 3111101, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule11111', 298, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-11-11-1', b'1'),
(739, 'AW22-11112', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#formulaire', '11.11.2', 3111102, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule11112', 298, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-11-11-2', b'1'),
(740, 'AW22-11121', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#formulaire', '11.12.1', 3111201, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule11121', 299, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-11-12-1', b'1'),
(741, 'AW22-11122', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#formulaire', '11.12.2', 3111202, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule11122', 299, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-11-12-2', b'1'),
(742, 'AW22-11131', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#formulaire', '11.13.1', 3111301, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule11131', 300, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-11-13-1', b'1'),
(743, 'AW22-11132', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#formulaire', '11.13.2', 3111302, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule11132', 300, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-11-13-2', b'1'),
(744, 'AW22-11141', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#formulaire', '11.14.1', 3111401, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule11141', 301, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-11-14-1', b'1'),
(745, 'AW22-11142', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#formulaire', '11.14.2', 3111402, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule11142', 301, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-11-14-2', b'1'),
(746, 'AW22-11151', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#formulaire', '11.15.1', 3111501, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule11151', 302, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-11-15-1', b'1'),
(747, 'AW22-12011', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#navigation', '12.1.1', 3120101, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule12011', 303, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-12-1-1', b'1'),
(748, 'AW22-12021', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#navigation', '12.2.1', 3120201, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule12021', 304, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-12-2-1', b'1'),
(749, 'AW22-12022', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#navigation', '12.2.2', 3120202, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule12022', 304, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-12-2-2', b'1'),
(750, 'AW22-12031', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#navigation', '12.3.1', 3120301, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule12031', 305, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-12-3-1', b'1'),
(751, 'AW22-12032', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#navigation', '12.3.2', 3120302, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule12032', 305, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-12-3-2', b'1'),
(752, 'AW22-12041', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#navigation', '12.4.1', 3120401, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule12041', 306, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-12-4-1', b'1'),
(753, 'AW22-12042', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#navigation', '12.4.2', 3120402, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule12042', 306, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-12-4-2', b'1'),
(754, 'AW22-12043', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#navigation', '12.4.3', 3120403, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule12043', 306, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-12-4-3', b'1'),
(755, 'AW22-12051', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#navigation', '12.5.1', 3120501, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule12051', 307, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-12-5-1', b'1'),
(756, 'AW22-12052', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#navigation', '12.5.2', 3120502, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule12052', 307, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-12-5-2', b'1'),
(757, 'AW22-12053', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#navigation', '12.5.3', 3120503, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule12053', 307, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-12-5-3', b'1'),
(758, 'AW22-12061', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#navigation', '12.6.1', 3120601, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule12061', 308, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-12-6-1', b'1'),
(759, 'AW22-12062', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#navigation', '12.6.2', 3120602, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule12062', 308, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-12-6-2', b'1'),
(760, 'AW22-12063', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#navigation', '12.6.3', 3120603, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule12063', 308, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-12-6-3', b'1'),
(761, 'AW22-12071', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#navigation', '12.7.1', 3120701, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule12071', 309, NULL, 2, 1, 'http://www.tanaguru.org/en/content/aw22-rule-12-7-1', b'1'),
(762, 'AW22-12081', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#navigation', '12.8.1', 3120801, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule12081', 310, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-12-8-1', b'1'),
(763, 'AW22-12091', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#navigation', '12.9.1', 3120901, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule12091', 311, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-12-9-1', b'1'),
(764, 'AW22-12101', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#navigation', '12.10.1', 3121001, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule12101', 312, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-12-10-1', b'1'),
(765, 'AW22-12102', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#navigation', '12.10.2', 3121002, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule12102', 312, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-12-10-2', b'1'),
(766, 'AW22-12103', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#navigation', '12.10.3', 3121003, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule12103', 312, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-12-10-3', b'1'),
(767, 'AW22-12111', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#navigation', '12.11.1', 3121101, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule12111', 313, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-12-11-1', b'1'),
(768, 'AW22-12112', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#navigation', '12.11.2', 3121102, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule12112', 313, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-12-11-2', b'1'),
(769, 'AW22-12113', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#navigation', '12.11.3', 3121103, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule12113', 313, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-12-11-3', b'1'),
(770, 'AW22-12114', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#navigation', '12.11.4', 3121104, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule12114', 313, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-12-11-4', b'1'),
(771, 'AW22-12121', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#navigation', '12.12.1', 3121201, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule12121', 314, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-12-12-1', b'1'),
(772, 'AW22-12131', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#navigation', '12.13.1', 3121301, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule12131', 315, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-12-13-1', b'1'),
(773, 'AW22-12132', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#navigation', '12.13.2', 3121302, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule12132', 315, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-12-13-2', b'1'),
(774, 'AW22-12141', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#navigation', '12.14.1', 3121401, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule12141', 316, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-12-14-1', b'1'),
(775, 'AW22-13011', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.1.1', 3130101, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13011', 317, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-1-1', b'1'),
(776, 'AW22-13012', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.1.2', 3130102, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13012', 317, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-1-2', b'1'),
(777, 'AW22-13013', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.1.3', 3130103, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13013', 317, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-1-3', b'1'),
(778, 'AW22-13014', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.1.4', 3130104, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13014', 317, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-1-4', b'1'),
(779, 'AW22-13015', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.1.5', 3130105, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13015', 317, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-1-5', b'1'),
(780, 'AW22-13021', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.2.1', 3130201, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13021', 318, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-2-1', b'1'),
(781, 'AW22-13022', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.2.2', 3130202, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13022', 318, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-2-2', b'1'),
(782, 'AW22-13023', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.2.3', 3130203, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13023', 318, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-2-3', b'1'),
(783, 'AW22-13031', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.3.1', 3130301, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13031', 319, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-3-1', b'1'),
(784, 'AW22-13041', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.4.1', 3130401, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13041', 320, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-4-1', b'1'),
(785, 'AW22-13051', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.5.1', 3130501, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13051', 321, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-5-1', b'1'),
(786, 'AW22-13061', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.6.1', 3130601, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13061', 322, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-6-1', b'1'),
(787, 'AW22-13062', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.6.2', 3130602, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13062', 322, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-6-2', b'1'),
(788, 'AW22-13063', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.6.3', 3130603, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13063', 322, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-6-3', b'1'),
(789, 'AW22-13071', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.7.1', 3130701, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13071', 323, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-7-1', b'1'),
(790, 'AW22-13081', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.8.1', 3130801, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13081', 324, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-8-1', b'1'),
(791, 'AW22-13091', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.9.1', 3130901, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13091', 325, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-9-1', b'1'),
(792, 'AW22-13101', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.10.1', 3131001, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13101', 326, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-10-1', b'1'),
(793, 'AW22-13111', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.11.1', 3131101, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13111', 327, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-11-1', b'1'),
(794, 'AW22-13121', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.12.1', 3131201, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13121', 328, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-12-1', b'1'),
(795, 'AW22-13131', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.13.1', 3131301, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13131', 329, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-13-1', b'1'),
(796, 'AW22-13141', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.14.1', 3131401, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13141', 330, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-14-1', b'1'),
(797, 'AW22-13151', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.15.1', 3131501, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13151', 331, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-15-1', b'1'),
(798, 'AW22-13152', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.15.2', 3131502, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13152', 331, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-15-2', b'1'),
(799, 'AW22-13153', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.15.3', 3131503, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13153', 331, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-15-3', b'1'),
(800, 'AW22-13154', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.15.4', 3131504, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13154', 331, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-15-4', b'1'),
(801, 'AW22-13161', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.16.1', 3131601, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13161', 332, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-16-1', b'1'),
(802, 'AW22-13162', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.16.2', 3131602, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13162', 332, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-16-2', b'1'),
(803, 'AW22-13163', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.16.3', 3131603, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13163', 332, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-16-3', b'1'),
(804, 'AW22-13164', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.16.4', 3131604, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13164', 332, NULL, 3, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-16-4', b'1'),
(805, 'AW22-13171', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.17.1', 3131701, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13171', 333, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-17-1', b'1'),
(806, 'AW22-13172', 'http://www.accessiweb.org/index.php/accessiweb_2.2_liste_generale.html#consultation', '13.17.2', 3131702, '1.0', 'accessiweb2.2', 'org.opens.tanaguru.rules.accessiweb22.Aw22Rule13172', 333, NULL, 1, 1, 'http://www.tanaguru.org/en/content/aw22-rule-13-17-2', b'1');

UPDATE TEST SET `No_Process`=b'0' WHERE 
Cd_Test='AW22-01011' OR 
Cd_Test='AW22-01012' OR 
Cd_Test='AW22-01013' OR 
Cd_Test='AW22-01014' OR 
Cd_Test='AW22-01021' OR 
Cd_Test='AW22-01022' OR 
Cd_Test='AW22-01023' OR 
Cd_Test='AW22-01024' OR 
Cd_Test='AW22-01025' OR 
Cd_Test='AW22-01031' OR 
Cd_Test='AW22-01032' OR 
Cd_Test='AW22-01033' OR 
Cd_Test='AW22-01034' OR 
Cd_Test='AW22-01035' OR 
Cd_Test='AW22-01036' OR 
Cd_Test='AW22-01041' OR 
Cd_Test='AW22-01042' OR 
Cd_Test='AW22-01043' OR 
Cd_Test='AW22-01044' OR 
Cd_Test='AW22-01045' OR 
Cd_Test='AW22-01046' OR 
Cd_Test='AW22-01051' OR 
Cd_Test='AW22-01052' OR 
Cd_Test='AW22-01061' OR 
Cd_Test='AW22-01062' OR 
Cd_Test='AW22-01063' OR 
Cd_Test='AW22-01064' OR 
Cd_Test='AW22-01065' OR 
Cd_Test='AW22-01071' OR 
Cd_Test='AW22-01072' OR 
Cd_Test='AW22-01073' OR 
Cd_Test='AW22-01074' OR 
Cd_Test='AW22-01081' OR 
Cd_Test='AW22-01082' OR 
Cd_Test='AW22-01083' OR 
Cd_Test='AW22-01084' OR 
Cd_Test='AW22-01085' OR 
Cd_Test='AW22-01086' OR 
Cd_Test='AW22-01091' OR 
Cd_Test='AW22-01092' OR 
Cd_Test='AW22-01093' OR 
Cd_Test='AW22-01094' OR 
Cd_Test='AW22-01095' OR 
Cd_Test='AW22-01096' OR 
Cd_Test='AW22-02011' OR 
Cd_Test='AW22-02012' OR 
Cd_Test='AW22-02021' OR 
Cd_Test='AW22-02022' OR
Cd_Test='AW22-03031' OR
Cd_Test='AW22-03032' OR
Cd_Test='AW22-03033' OR
Cd_Test='AW22-03034' OR
Cd_Test='AW22-03041' OR
Cd_Test='AW22-03042' OR
Cd_Test='AW22-03043' OR
Cd_Test='AW22-03044' OR
Cd_Test='AW22-05011' OR 
Cd_Test='AW22-05021' OR 
Cd_Test='AW22-05022' OR 
Cd_Test='AW22-05031' OR 
Cd_Test='AW22-05041' OR 
Cd_Test='AW22-05051' OR 
Cd_Test='AW22-05061' OR 
Cd_Test='AW22-05062' OR 
Cd_Test='AW22-05071' OR 
Cd_Test='AW22-05072' OR 
Cd_Test='AW22-05073' OR 
Cd_Test='AW22-05074' OR 
Cd_Test='AW22-05081' OR 
Cd_Test='AW22-06011' OR 
Cd_Test='AW22-06012' OR 
Cd_Test='AW22-06013' OR 
Cd_Test='AW22-06014' OR 
Cd_Test='AW22-06021' OR 
Cd_Test='AW22-06022' OR 
Cd_Test='AW22-06023' OR 
Cd_Test='AW22-06024' OR 
Cd_Test='AW22-06031' OR 
Cd_Test='AW22-06032' OR 
Cd_Test='AW22-06033' OR 
Cd_Test='AW22-06034' OR 
Cd_Test='AW22-06041' OR 
Cd_Test='AW22-06042' OR 
Cd_Test='AW22-06043' OR 
Cd_Test='AW22-06044' OR 
Cd_Test='AW22-06051' OR 
Cd_Test='AW22-06052' OR 
Cd_Test='AW22-06053' OR 
Cd_Test='AW22-06061' OR 
Cd_Test='AW22-07051' OR 
Cd_Test='AW22-08011' OR 
Cd_Test='AW22-08012' OR 
Cd_Test='AW22-08013' OR 
Cd_Test='AW22-08031' OR 
Cd_Test='AW22-08041' OR 
Cd_Test='AW22-08051' OR 
Cd_Test='AW22-08061' OR 
Cd_Test='AW22-08071' OR 
Cd_Test='AW22-08081' OR 
Cd_Test='AW22-08082' OR 
Cd_Test='AW22-09011' OR 
Cd_Test='AW22-09012' OR 
Cd_Test='AW22-10011' OR 
Cd_Test='AW22-10012' OR 
Cd_Test='AW22-10041' OR 
Cd_Test='AW22-10083' OR 
Cd_Test='AW22-10084' OR 
Cd_Test='AW22-11011' OR 
Cd_Test='AW22-11012' OR 
Cd_Test='AW22-11013' OR 
Cd_Test='AW22-11021' OR 
Cd_Test='AW22-11022' OR 
Cd_Test='AW22-11031' OR 
Cd_Test='AW22-11041' OR 
Cd_Test='AW22-11051' OR 
Cd_Test='AW22-11061' OR 
Cd_Test='AW22-11071' OR 
Cd_Test='AW22-11081' OR 
Cd_Test='AW22-11082' OR 
Cd_Test='AW22-11083' OR 
Cd_Test='AW22-11091' OR 
Cd_Test='AW22-11101' OR 
Cd_Test='AW22-11102' OR 
Cd_Test='AW22-11103' OR 
Cd_Test='AW22-11111' OR 
Cd_Test='AW22-11112' OR 
Cd_Test='AW22-11121' OR 
Cd_Test='AW22-11122' OR 
Cd_Test='AW22-11131' OR 
Cd_Test='AW22-11132' OR 
Cd_Test='AW22-11141' OR 
Cd_Test='AW22-11142' OR 
Cd_Test='AW22-11151' OR
Cd_Test='AW22-13012' OR
Cd_Test='AW22-13154' OR
Cd_Test='AW22-13164';


SET foreign_key_checks=1;