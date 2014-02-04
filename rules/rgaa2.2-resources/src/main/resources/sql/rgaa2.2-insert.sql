SET foreign_key_checks=0;

--
-- Contenu de la table `reference`
--
INSERT IGNORE INTO `REFERENCE` (`ID_REFERENCE`, `CD_REFERENCE`, `DESCRIPTION`, `LABEL`, `URL`, `RANK`) VALUES
(2, 'RGAA22', NULL, 'Rgaa 2.2', 'www.references.modernisation.gouv.fr/sites/default/files/RGAA_v2.2.1.pdf', 2);

--
-- Contenu de la table `level`
--
INSERT IGNORE INTO `LEVEL` (`Id_Level`, `Cd_Level`, `Label`, `Description`, `Rank`) VALUES
(4, 'A', 'Facile', NULL, 40),
(5, 'AA', 'Moyen', NULL, 50),
(6, 'AAA', 'Difficile', NULL, 60);


--
-- Contenu de la table `theme`
--
INSERT IGNORE INTO `THEME` (`ID_THEME`, `CD_THEME`, `DESCRIPTION`, `LABEL`, `RANK`) VALUES
(101, 'RGAA22-1', 'http://rgaa.net/-Cadres-.html', 'Cadres', 1),
(102, 'RGAA22-2', 'http://rgaa.net/-Couleurs-.html', 'Couleurs', 2),
(103, 'RGAA22-3', 'http://rgaa.net/-Formulaires-.html', 'Formulaires', 3),
(104, 'RGAA22-4', 'http://rgaa.net/-Images-.html', 'Images', 4),
(105, 'RGAA22-5', 'http://rgaa.net/-Multimedia-.html', 'Multimédia', 5),
(106, 'RGAA22-6', 'http://rgaa.net/-Navigation-.html', 'Navigation', 6),
(107, 'RGAA22-7', 'http://rgaa.net/-Presentation-.html', 'Présentation', 7),
(108, 'RGAA22-8', 'http://rgaa.net/-Scripts-.html', 'Scripts', 8),
(109, 'RGAA22-9', 'http://rgaa.net/-Standards-.html', 'Standards', 9),
(110, 'RGAA22-10', 'http://rgaa.net/-Structure-.html', 'Structure', 10),
(111, 'RGAA22-11', 'http://rgaa.net/-Tableaux-.html', 'Tableaux', 11),
(112, 'RGAA22-12', 'http://rgaa.net/-Textes-.html', 'Textes', 12);

--
-- Contenu de la table `criterion`
--
INSERT IGNORE INTO `CRITERION` (`ID_CRITERION`, `reference_ID_REFERENCE`, `theme_ID_THEME`, `CD_CRITERION`, `DESCRIPTION`, `LABEL`, `URL`, `RANK`) VALUES
(2001, 2, 101, 'Rgaa22-0101', '', '1.1', 'http://rgaa.net/1-1-Absence-de-cadres-non-titres.html', 2010101),
(2002, 2, 101, 'Rgaa22-0102', '', '1.2', 'http://rgaa.net/Pertinence-des-titres-donnes-aux.html', 2010201),
(2003, 2, 102, 'Rgaa22-0201', '', '2.1', 'http://rgaa.net/Presence-d-un-autre-moyen-que-la.html', 2020101),
(2004, 2, 102, 'Rgaa22-0202', '', '2.2', 'http://rgaa.net/Presence-d-un-autre-moyen-que-la,4.html', 2020201),
(2005, 2, 102, 'Rgaa22-0203', '', '2.3', 'http://rgaa.net/Presence-d-un-moyen-de.html', 2020301),
(2006, 2, 102, 'Rgaa22-0204', '', '2.4', 'http://rgaa.net/Presence-d-un-moyen-de,6.html', 2020401),
(2007, 2, 102, 'Rgaa22-0205', '', '2.5', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du.html', 2020501),
(2008, 2, 102, 'Rgaa22-0206', '', '2.6', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,8.html', 2020601),
(2009, 2, 102, 'Rgaa22-0207', '', '2.7', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,9.html', 2020701),
(2010, 2, 102, 'Rgaa22-0208', '', '2.8', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,10.html',2020801),
(2011, 2, 102, 'Rgaa22-0209', '', '2.9', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,11.html', 2020901),
(2012, 2, 102, 'Rgaa22-0210', '', '2.10', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,12.html', 2021001),
(2013, 2, 102, 'Rgaa22-0211', '', '2.11', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,13.html', 2021101),
(2014, 2, 102, 'Rgaa22-0212', '', '2.12', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,14.html', 2021201),
(2015, 2, 102, 'Rgaa22-0213', '', '2.13', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,15.html', 2021301),
(2016, 2, 102, 'Rgaa22-0214', '', '2.14', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,16.html', 2021401),
(2017, 2, 102, 'Rgaa22-0215', '', '2.15', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,17.html', 2021501),
(2018, 2, 102, 'Rgaa22-0216', '', '2.16', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,18.html', 2021601),
(2019, 2, 103, 'Rgaa22-0301', '', '3.1', 'http://rgaa.net/Possibilite-d-identifier-les.html', 2030101),
(2020, 2, 103, 'Rgaa22-0302', '', '3.2', 'http://rgaa.net/Presence-d-information-prealable.html', 2030201),
(2021, 2, 103, 'Rgaa22-0303', '', '3.3', 'http://rgaa.net/Positionnement-correct-des.html',  2030301),
(2022, 2, 103, 'Rgaa22-0304', '', '3.4', 'http://rgaa.net/Regroupement-d-elements-de.html', 2030401),
(2023, 2, 103, 'Rgaa22-0305', '', '3.5', 'http://rgaa.net/Absence-d-element-fieldset-sans.html', 2030501),
(2024, 2, 103, 'Rgaa22-0306', '', '3.6', 'http://rgaa.net/Pertinence-du-contenu-de-l-element.html', 2030601),
(2025, 2, 103, 'Rgaa22-0307', '', '3.7', 'http://rgaa.net/Regroupement-d-elements-option.html', 2030701),
(2026, 2, 103, 'Rgaa22-0308', '', '3.8', 'http://rgaa.net/Presence-d-un-attribut-label-sur-l.html', 2030801),
(2027, 2, 103, 'Rgaa22-0309', '', '3.9', 'http://rgaa.net/Pertinence-du-contenu-de-l.html', 2030901),
(2028, 2, 103, 'Rgaa22-0310', '', '3.10', 'http://rgaa.net/Absence-d-element-de-formulaire.html', 2031001),
(2029, 2, 103, 'Rgaa22-0311', '', '3.11', 'http://rgaa.net/Absence-d-element-de-formulaire,29.html', 2031101),
(2030, 2, 103, 'Rgaa22-0312', '', '3.12', 'http://rgaa.net/Pertinence-des-etiquettes-d.html',  2031201),
(2031, 2, 103, 'Rgaa22-0313', '', '3.13', 'http://rgaa.net/Presence-d-informations-ou-de.html',  2031301),
(2032, 2, 103, 'Rgaa22-0314', '', '3.14', 'http://rgaa.net/Presence-de-mecanismes-permettant.html',  2031401),
(2033, 2, 103, 'Rgaa22-0315', '', '3.15', 'http://rgaa.net/Presence-de-mecanismes-permettant,33.html',  2031501),
(2034, 2, 103, 'Rgaa22-0316', '', '3.16', 'http://rgaa.net/Presence-d-une-page-d-aide-ou-d-un.html',  2031601),
(2035, 2, 104, 'Rgaa22-0401', '', '4.1', 'http://rgaa.net/Presence-de-l-attribut-alt.html',  2040101),
(2036, 2, 104, 'Rgaa22-0402', '', '4.2', 'http://rgaa.net/Pertinence-de-l-alternative.html',  2040201),
(2037, 2, 104, 'Rgaa22-0403', '', '4.3', 'http://rgaa.net/Pertinence-de-l-alternative,37.html',  2040301),
(2038, 2, 104, 'Rgaa22-0404', '', '4.4', 'http://rgaa.net/Pertinence-de-l-alternative,38.html',  2040401),
(2039, 2, 104, 'Rgaa22-0405', '', '4.5', 'http://rgaa.net/Pertinence-de-l-alternative-vide.html',  2040501),
(2040, 2, 104, 'Rgaa22-0406', '', '4.6', 'http://rgaa.net/Longueur-du-contenu-des.html',  2040601),
(2041, 2, 104, 'Rgaa22-0407', '', '4.7', 'http://rgaa.net/Existence-d-une-description-longue.html',  2040701),
(2042, 2, 104, 'Rgaa22-0408', '', '4.8', 'http://rgaa.net/Pertinence-de-la-description.html',  2040801),
(2043, 2, 104, 'Rgaa22-0409', '', '4.9', 'http://rgaa.net/Presence-de-l-attribut-longdesc.html', 2040901),
(2044, 2, 104, 'Rgaa22-0410', '', '4.10', 'http://rgaa.net/Presence-d-une-information-de.html',  2041001),
(2045, 2, 104, 'Rgaa22-0411', '', '4.11', 'http://rgaa.net/Coherence-dans-l-identification.html',  2041101),
(2046, 2, 105, 'Rgaa22-0501', '', '5.1', 'http://rgaa.net/Acces-a-une-information.html',  2050101),
(2047, 2, 105, 'Rgaa22-0502', '', '5.2', 'http://rgaa.net/Presence-de-la-transcription.html',  2050201),
(2048, 2, 105, 'Rgaa22-0503', '', '5.3', 'http://rgaa.net/Pertinence-de-la-transcription.html',  2050301),
(2049, 2, 105, 'Rgaa22-0504', '', '5.4', 'http://rgaa.net/Presence-d-une-description-audio.html', 2050401),
(2050, 2, 105, 'Rgaa22-0505', '', '5.5', 'http://rgaa.net/Pertinence-de-la-description-audio.html',  2050501),
(2051, 2, 105, 'Rgaa22-0506', '', '5.6', 'http://rgaa.net/Possibilite-de-controler-l.html',  2050601),
(2052, 2, 105, 'Rgaa22-0507', '', '5.7', 'http://rgaa.net/Presence-d-une-description-audio,52.html',  2050701),
(2053, 2, 105, 'Rgaa22-0508', '', '5.8', 'http://rgaa.net/Presence-d-une-description-audio,53.html',  2050801),
(2054, 2, 105, 'Rgaa22-0509', '', '5.9', 'http://rgaa.net/Presence-du-sous-titrage.html',  2050901),
(2055, 2, 105, 'Rgaa22-0510', '', '5.10', 'http://rgaa.net/Pertinence-du-sous-titrage.html',  2051001),
(2056, 2, 105, 'Rgaa22-0511', '', '5.11', 'http://rgaa.net/Presence-d-une-alternative-aux.html',  2051101),
(2057, 2, 105, 'Rgaa22-0512', '', '5.12', 'http://rgaa.net/Presence-d-une-alternative-aux,57.html',  2051201),
(2058, 2, 105, 'Rgaa22-0513', '', '5.13', 'http://rgaa.net/Absence-d-elements-provoquant-des.html',  2051301),
(2059, 2, 105, 'Rgaa22-0514', '', '5.14', 'http://rgaa.net/Absence-de-code-javascript,59.html',  2051401),
(2060, 2, 105, 'Rgaa22-0515', '', '5.15', 'http://rgaa.net/Absence-de-mise-en-forme.html',  2051501),
(2061, 2, 105, 'Rgaa22-0516', '', '5.16', 'http://rgaa.net/Compatibilite-des-elements.html',  2051601),
(2062, 2, 105, 'Rgaa22-0517', '', '5.17', 'http://rgaa.net/Absence-totale-de-changements.html',  2051701),
(2063, 2, 105, 'Rgaa22-0518', '', '5.18', 'http://rgaa.net/Presence-du-sous-titrage,63.html',  2051801),
(2064, 2, 105, 'Rgaa22-0519', '', '5.19', 'http://rgaa.net/Absence-de-l-element-blink.html',  2051901),
(2065, 2, 105, 'Rgaa22-0520', '', '5.20', 'http://rgaa.net/Absence-d-elements-provoquant-des,65.html',  2052001),
(2066, 2, 105, 'Rgaa22-0521', '', '5.21', 'http://rgaa.net/Absence-de-code-javascript,66.html',  2052101),
(2067, 2, 105, 'Rgaa22-0522', '', '5.22', 'http://rgaa.net/Absence-de-mise-en-forme,67.html',  2052201),
(2068, 2, 105, 'Rgaa22-0523', '', '5.23', 'http://rgaa.net/Absence-d-element-marquee.html',  2052301),
(2069, 2, 105, 'Rgaa22-0524', '', '5.24', 'http://rgaa.net/Absence-d-elements-affichant-des.html',  2052401),
(2070, 2, 105, 'Rgaa22-0525', '', '5.25', 'http://rgaa.net/Absence-de-code-javascript,70.html',  2052501),
(2071, 2, 105, 'Rgaa22-0526', '', '5.26', 'http://rgaa.net/Absence-de-mise-en-forme,71.html',  2052601),
(2072, 2, 105, 'Rgaa22-0527', '', '5.27', 'http://rgaa.net/Independance-du-peripherique-d.html',  2052701),
(2073, 2, 105, 'Rgaa22-0528', '', '5.28', 'http://rgaa.net/Presence-d-une-alternative-aux,73.html', 2052801),
(2074, 2, 105, 'Rgaa22-0529', '', '5.29', 'http://rgaa.net/Absence-d-elements-declenchant-la.html',  2052901),
(2075, 2, 105, 'Rgaa22-0530', '', '5.30', 'http://rgaa.net/Absence-d-element-bgsound.html', 2053001),
(2076, 2, 105, 'Rgaa22-0531', '', '5.31', 'http://rgaa.net/Presence-de-version-en-langue-des.html',  2053101),
(2077, 2, 105, 'Rgaa22-0532', '', '5.32', 'http://rgaa.net/Pertinence-de-la-version-en-langue.html',  2053201),
(2078, 2, 105, 'Rgaa22-0533', '', '5.33', 'http://rgaa.net/Niveau-sonore-de-la-piste-de.html',  2053301),
(2079, 2, 105, 'Rgaa22-0534', '', '5.34', 'http://rgaa.net/Presence-d-un-mecanisme-pour.html', 2053401),
(2080, 2, 106, 'Rgaa22-0601', '', '6.1', 'http://rgaa.net/Acces-aux-liens-textuels-doublant.html',  2060101),
(2081, 2, 106, 'Rgaa22-0602', '', '6.2', 'http://rgaa.net/Presence-d-un-avertissement.html',  2060201),
(2082, 2, 106, 'Rgaa22-0603', '', '6.3', 'http://rgaa.net/Presence-d-un-avertissement,82.html',  2060301),
(2083, 2, 106, 'Rgaa22-0604', '', '6.4', 'http://rgaa.net/Presence-d-un-avertissement,83.html',2060401),
(2084, 2, 106, 'Rgaa22-0605', '', '6.5', 'http://rgaa.net/Absence-d-ouverture-de-nouvelles.html',  2060501),
(2085, 2, 106, 'Rgaa22-0606', '', '6.6', 'http://rgaa.net/Absence-de-piege-lors-de-la.html', 2060601),
(2086, 2, 106, 'Rgaa22-0607', '', '6.7', 'http://rgaa.net/Absence-d-element-meta-provoquant.html', 2060701),
(2087, 2, 106, 'Rgaa22-0608', '', '6.8', 'http://rgaa.net/Absence-de-code-javascript.html', 2060801),
(2088, 2, 106, 'Rgaa22-0609', '', '6.9', 'http://rgaa.net/Absence-d-elements-provoquant-un.html', 2060901),
(2089, 2, 106, 'Rgaa22-0610', '', '6.10', 'http://rgaa.net/Absence-d-element-meta-provoquant,89.html', 2061001),
(2090, 2, 106, 'Rgaa22-0611', '', '6.11', 'http://rgaa.net/Absence-de-code-javascript,90.html', 2061101),
(2091, 2, 106, 'Rgaa22-0612', '', '6.12', 'http://rgaa.net/Absence-d-elements-provoquant-une.html', 2061201),
(2092, 2, 106, 'Rgaa22-0613', '', '6.13', 'http://rgaa.net/Possibilite-d-identifier-la.html',  2061301),
(2093, 2, 106, 'Rgaa22-0614', '', '6.14', 'http://rgaa.net/Possibilite-d-identifier-la,93.html',  2061401),
(2094, 2, 106, 'Rgaa22-0615', '', '6.15', 'http://rgaa.net/Coherence-de-la-destination-ou-de.html', 2061501),
(2095, 2, 106, 'Rgaa22-0616', '', '6.16', 'http://rgaa.net/Absence-de-liens-sans-intitule.html',  2061601),
(2096, 2, 106, 'Rgaa22-0617', '', '6.17', 'http://rgaa.net/Presence-d-une-page-contenant-le.html', 2061701),
(2097, 2, 106, 'Rgaa22-0618', '', '6.18', 'http://rgaa.net/Coherence-du-plan-du-site.html',  2061801),
(2098, 2, 106, 'Rgaa22-0619', '', '6.19', 'http://rgaa.net/Presence-d-un-acces-a-la-page.html',  2061901),
(2099, 2, 106, 'Rgaa22-0620', '', '6.20', 'http://rgaa.net/Presence-d-un-fil-d-ariane.html',  2062001),
(2100, 2, 106, 'Rgaa22-0621', '', '6.21', 'http://rgaa.net/Presence-de-menus-ou-de-barres-de.html',  2062101),
(2101, 2, 106, 'Rgaa22-0622', '', '6.22', 'http://rgaa.net/Coherence-de-la-position-des-menus.html',  2062201),
(2102, 2, 106, 'Rgaa22-0623', '', '6.23', 'http://rgaa.net/Coherence-de-la-presentation-des.html',  2062301),
(2103, 2, 106, 'Rgaa22-0624', '', '6.24', 'http://rgaa.net/Navigation-au-clavier-dans-un.html',  2062401),
(2104, 2, 106, 'Rgaa22-0625', '', '6.25', 'http://rgaa.net/Presence-d-un-avertissement,104.html',  2062501),
(2105, 2, 106, 'Rgaa22-0626', '', '6.26', 'http://rgaa.net/Presence-des-informations-de.html',  2062601),
(2106, 2, 106, 'Rgaa22-0627', '', '6.27', 'http://rgaa.net/Presence-des-informations-de-poids.html',  2062701),
(2107, 2, 106, 'Rgaa22-0628', '', '6.28', 'http://rgaa.net/Presence-des-informations-de,107.html',  2062801),
(2108, 2, 106, 'Rgaa22-0629', '', '6.29', 'http://rgaa.net/Presence-de-regroupement-pour-les.html',  2062901),
(2109, 2, 106, 'Rgaa22-0630', '', '6.30', 'http://rgaa.net/Presence-d-un-balisage-permettant.html',  2063001),
(2110, 2, 106, 'Rgaa22-0631', '', '6.31', 'http://rgaa.net/Presence-de-liens-d-evitement-ou-d.html',  2063101),
(2111, 2, 106, 'Rgaa22-0632', '', '6.32', 'http://rgaa.net/Coherence-des-liens-d-evitement-ou.html',  2063201),
(2112, 2, 106, 'Rgaa22-0633', '', '6.33', 'http://rgaa.net/Ordre-des-liens-d-evitement-ou-d.html',  2063301),
(2113, 2, 106, 'Rgaa22-0634', '', '6.34', 'http://rgaa.net/Presence-d-un-moteur-de-recherche.html',  2063401),
(2114, 2, 106, 'Rgaa22-0635', '', '6.35', 'http://rgaa.net/Possibilite-de-naviguer-facilement.html',  2063501),
(2115, 2, 106, 'Rgaa22-0636', '', '6.36', 'http://rgaa.net/Presence-d-une-indication-de-la.html',  2063601),
(2116, 2, 107, 'Rgaa22-0701', '', '7.1', 'http://rgaa.net/Absence-de-generation-de-contenus.html',  2070101),
(2117, 2, 107, 'Rgaa22-0702', '', '7.2', 'http://rgaa.net/Absence-d-alteration-de-la.html',  2070201),
(2118, 2, 107, 'Rgaa22-0703', '', '7.3', 'http://rgaa.net/Lisibilite-des-informations.html',  2070301),
(2119, 2, 107, 'Rgaa22-0704', '', '7.4', 'http://rgaa.net/Absence-d-espaces-utilises-pour.html', 2070401),
(2120, 2, 107, 'Rgaa22-0705', '', '7.5', 'http://rgaa.net/Absence-de-definition-d-une.html',  2070501),
(2121, 2, 107, 'Rgaa22-0706', '', '7.6', 'http://rgaa.net/Possibilite-de-remplacer-les.html',  2070601),
(2122, 2, 107, 'Rgaa22-0707', '', '7.7', 'http://rgaa.net/Possibilite-de-remplacer-les,122.html',  2070701),
(2123, 2, 107, 'Rgaa22-0708', '', '7.8', 'http://rgaa.net/Absence-d-attributs-ou-d-elements.html', 2070801),
(2124, 2, 107, 'Rgaa22-0709', '', '7.9', 'http://rgaa.net/Absence-d-elements-HTML-utilises-a.html', 2070901),
(2125, 2, 107, 'Rgaa22-0710', '', '7.10', 'http://rgaa.net/Maintien-de-la-distinction.html', 2071001),
(2126, 2, 107, 'Rgaa22-0711', '', '7.11', 'http://rgaa.net/Absence-de-suppression-de-l-effet.html',  2071101),
(2127, 2, 107, 'Rgaa22-0712', '', '7.12', 'http://rgaa.net/Absence-de-justification-du-texte.html',  2071201),
(2128, 2, 107, 'Rgaa22-0713', '', '7.13', 'http://rgaa.net/Lisibilite-du-document-en-cas-d.html',  2071301),
(2129, 2, 107, 'Rgaa22-0714', '', '7.14', 'http://rgaa.net/Absence-d-unites-absolues-ou-de.html',  2071401),
(2130, 2, 107, 'Rgaa22-0715', '', '7.15', 'http://rgaa.net/Absence-d-apparition-de-barre-de.html',  2071501),
(2131, 2, 107, 'Rgaa22-0716', '', '7.16', 'http://rgaa.net/Largeur-des-blocs-de-textes.html',  2071601),
(2132, 2, 107, 'Rgaa22-0717', '', '7.17', 'http://rgaa.net/Valeur-de-l-espace-entre-les.html',  2071701),
(2133, 2, 107, 'Rgaa22-0718', '', '7.18', 'http://rgaa.net/Restitution-correcte-dans-les.html', 2071801),
(2134, 2, 108, 'Rgaa22-0801', '', '8.1', 'http://rgaa.net/Mise-a-jour-des-alternatives-aux.html',  2080101),
(2135, 2, 108, 'Rgaa22-0802', '', '8.2', 'http://rgaa.net/Universalite-du-gestionnaire-d.html', 2080201),
(2136, 2, 108, 'Rgaa22-0803', '', '8.3', 'http://rgaa.net/Universalite-des-gestionnaires-d.html', 2080301),
(2137, 2, 108, 'Rgaa22-0804', '', '8.4', 'http://rgaa.net/Possibilite-de-desactiver-toute.html', 2080401),
(2138, 2, 108, 'Rgaa22-0805', '', '8.5', 'http://rgaa.net/Absence-de-changements-de-contexte.html', 2080501),
(2139, 2, 108, 'Rgaa22-0806', '', '8.6', 'http://rgaa.net/Ordre-d-acces-au-clavier-aux.html', 2080601),
(2140, 2, 108, 'Rgaa22-0807', '', '8.7', 'http://rgaa.net/Utilisation-correcte-du-role-des.html', 2080701),
(2141, 2, 108, 'Rgaa22-0808', '', '8.8', 'http://rgaa.net/Presence-d-une-alternative-au-code.html', 2080801),
(2142, 2, 108, 'Rgaa22-0809', '', '8.9', 'http://rgaa.net/Absence-de-suppression-du-focus.html', 2080901),
(2143, 2, 108, 'Rgaa22-0810', '', '8.10', 'http://rgaa.net/Absence-de-limite-de-temps-pour.html', 2080101),
(2144, 2, 108, 'Rgaa22-0811', '', '8.11', 'http://rgaa.net/Absence-de-perte-d-informations.html', 2080111),
(2145, 2, 108, 'Rgaa22-0812', '', '8.12', 'http://rgaa.net/Presence-d-une-alternative-au-code,145.html', 2080121),
(2146, 2, 108, 'Rgaa22-0813', '', '8.13', 'http://rgaa.net/Accessibilite-des-contenus.html', 2080131),
(2147, 2, 109, 'Rgaa22-0901', '', '9.1', 'http://rgaa.net/Presence-de-la-declaration-d.html', 2090101),
(2148, 2, 109, 'Rgaa22-0902', '', '9.2', 'http://rgaa.net/Conformite-de-la-position-de-la.html', 2090201),
(2149, 2, 109, 'Rgaa22-0903', '', '9.3', 'http://rgaa.net/Conformite-syntaxique-de-la.html', 2090301),
(2150, 2, 109, 'Rgaa22-0904', '', '9.4', 'http://rgaa.net/Validite-du-code-HTML-XHTML-au.html', 2090401),
(2151, 2, 109, 'Rgaa22-0905', '', '9.5', 'http://rgaa.net/Absence-de-composants-obsoletes.html',  2090501),
(2152, 2, 109, 'Rgaa22-0906', '', '9.6', 'http://rgaa.net/Presence-d-un-titre-dans-la-page.html', 2090601),
(2153, 2, 109, 'Rgaa22-0907', '', '9.7', 'http://rgaa.net/Pertinence-du-titre-de-la-page.html', 2090701),
(2154, 2, 109, 'Rgaa22-0908', '', '9.8', 'http://rgaa.net/Presence-d-une-langue-de.html', 2090801),
(2155, 2, 110, 'Rgaa22-1001', '', '10.1', 'http://rgaa.net/Presence-d-au-moins-un-titre-de.html', 2100101),
(2156, 2, 110, 'Rgaa22-1002', '', '10.2', 'http://rgaa.net/Pertinence-du-contenu-des-titres.html', 2100201),
(2157, 2, 110, 'Rgaa22-1003', '', '10.3', 'http://rgaa.net/Absence-d-interruption-dans-la.html', 2100301),
(2158, 2, 110, 'Rgaa22-1004', '', '10.4', 'http://rgaa.net/Presence-d-une-hierarchie-de.html', 2100401),
(2159, 2, 110, 'Rgaa22-1005', '', '10.5', 'http://rgaa.net/Absence-de-simulation-visuelle-de.html', 2100501),
(2160, 2, 110, 'Rgaa22-1006', '', '10.6', 'http://rgaa.net/Utilisation-systematique-de-listes.html', 2100601),
(2161, 2, 110, 'Rgaa22-1007', '', '10.7', 'http://rgaa.net/Balisage-correct-des-listes-de.html', 2100701),
(2162, 2, 110, 'Rgaa22-1008', '', '10.8', 'http://rgaa.net/Balisage-correct-des-citations.html', 2100801),
(2163, 2, 110, 'Rgaa22-1009', '', '10.9', 'http://rgaa.net/Balisage-correct-des-abreviations.html', 2100901),
(2164, 2, 110, 'Rgaa22-1010', '', '10.10', 'http://rgaa.net/Balisage-correct-des-acronymes.html', 2101001),
(2165, 2, 110, 'Rgaa22-1011', '', '10.11', 'http://rgaa.net/Pertinence-de-la-version-non.html', 2101101),
(2166, 2, 110, 'Rgaa22-1012', '', '10.12', 'http://rgaa.net/Pertinence-de-la-version-complete.html', 2101201),
(2167, 2, 110, 'Rgaa22-1013', '', '10.13', 'http://rgaa.net/Accessibilite-des-documents.html', 2101301),
(2168, 2, 111, 'Rgaa22-1101', '', '11.1', 'http://rgaa.net/Presence-des-balises-th-pour.html', 2110101),
(2169, 2, 111, 'Rgaa22-1102', '', '11.2', 'http://rgaa.net/Presence-d-une-relation-entre-les.html', 2110201),
(2170, 2, 111, 'Rgaa22-1103', '', '11.3',  'http://rgaa.net/Presence-d-une-relation-entre-les,170.html', 2110301),
(2171, 2, 111, 'Rgaa22-1104', '', '11.4', 'http://rgaa.net/Absence-des-elements-propres-aux.html',  2110401),
(2172, 2, 111, 'Rgaa22-1105', '', '11.5', 'http://rgaa.net/Absence-de-tableaux-de-donnees-ou.html',  2110501),
(2173, 2, 111, 'Rgaa22-1106', '', '11.6', 'http://rgaa.net/Linearisation-correcte-des.html',  2110601),
(2174, 2, 111, 'Rgaa22-1107', '', '11.7', 'http://rgaa.net/Presence-d-un-titre-pour-les.html', 2110701),
(2175, 2, 111, 'Rgaa22-1108', '', '11.8', 'http://rgaa.net/Presence-d-un-resume-pour-les.html', 2110801),
(2176, 2, 111, 'Rgaa22-1109', '', '11.9', 'http://rgaa.net/Pertinence-du-titre-du-tableau-de.html', 2110901),
(2177, 2, 111, 'Rgaa22-1110', '', '11.10', 'http://rgaa.net/Pertinence-du-resume-du-tableau-de.html', 2111001),
(2178, 2, 112, 'Rgaa22-1201', '', '12.1', 'http://rgaa.net/Presence-de-l-indication-des.html', 2120101),
(2179, 2, 112, 'Rgaa22-1202', '', '12.2', 'http://rgaa.net/Presence-de-l-indication-des,179.html', 2120201),
(2180, 2, 112, 'Rgaa22-1203', '', '12.3', 'http://rgaa.net/Equivalence-de-l-information-mise.html', 2120301),
(2181, 2, 112, 'Rgaa22-1204', '', '12.4', 'http://rgaa.net/Presence-de-liens-ou-de.html', 2120401),
(2182, 2, 112, 'Rgaa22-1205', '', '12.5', 'http://rgaa.net/Absence-de-syntaxes-cryptiques-par.html',  2120501),
(2183, 2, 112, 'Rgaa22-1206', '', '12.6', 'http://rgaa.net/Presence-d-informations-sur-les.html',  2120601),
(2184, 2, 112, 'Rgaa22-1207', '', '12.7', 'http://rgaa.net/Presence-d-un-moyen-de,184.html', 2120701),
(2185, 2, 112, 'Rgaa22-1208', '', '12.8', 'http://rgaa.net/Presence-d-un-autre-moyen-que-la,185.html', 2120801),
(2186, 2, 112, 'Rgaa22-1209', '', '12.9', 'http://rgaa.net/Presence-d-un-autre-moyen-que-la,186.html', 2120901),
(2187, 2, 112, 'Rgaa22-1210', '', '12.10', 'http://rgaa.net/Utilisation-d-un-style-de.html', 2121001);

--
-- Contenu de la table `test`
--
INSERT IGNORE INTO `TEST` (`Id_Test`, `Cd_Test`, `Description`, `Label`, `Rank`, `Weight`, `Rule_Archive_Name`, `Rule_Class_Name`, `Id_Criterion`, `Id_Decision_Level`, `Id_Level`, `Id_Scope`, `Rule_Design_Url`,`No_Process`) VALUES
(2001, 'Rgaa22-01011', 'http://rgaa.net/1-1-Absence-de-cadres-non-titres.html', '1.1', 2010101, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule01011', 2001, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-1-1',b'1'),
(2002, 'Rgaa22-01021', 'http://rgaa.net/Pertinence-des-titres-donnes-aux.html', '1.2', 2010201, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule01021', 2002, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-1-2',b'1'),
(2003, 'Rgaa22-02011', 'http://rgaa.net/Presence-d-un-autre-moyen-que-la.html', '2.1', 2020101, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule02011', 2003, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-2-1',b'1'),
(2004, 'Rgaa22-02021', 'http://rgaa.net/Presence-d-un-autre-moyen-que-la,4.html', '2.2', 2020201, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule02021', 2004, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-2-2',b'1'),
(2005, 'Rgaa22-02031', 'http://rgaa.net/Presence-d-un-moyen-de.html', '2.3', 2020301, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule02031', 2005, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-2-3',b'1'),
(2006, 'Rgaa22-02041', 'http://rgaa.net/Presence-d-un-moyen-de,6.html', '2.4', 2020401, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule02041', 2006, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-2-4',b'1'),
(2007, 'Rgaa22-02051', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du.html', '2.5', 2020501, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule02051', 2007, NULL, 5, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-2-5',b'1'),
(2008, 'Rgaa22-02061', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,8.html', '2.6', 2020601, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule02061', 2008, NULL, 5, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-2-6',b'1'),
(2009, 'Rgaa22-02071', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,9.html', '2.7', 2020701, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule02071', 2009, NULL, 5, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-2-7',b'1'),
(2010, 'Rgaa22-02081', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,10.html', '2.8', 2020801, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule02081', 2010, NULL, 5, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-2-8',b'1'),
(2011, 'Rgaa22-02091', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,11.html', '2.9', 2020901, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule02091', 2011, NULL, 5, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-2-9',b'1'),
(2012, 'Rgaa22-02101', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,12.html', '2.10', 2021001, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule02101', 2012, NULL, 5, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-2-10',b'1'),
(2013, 'Rgaa22-02111', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,13.html', '2.11', 2021101, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule02111', 2013, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-2-11',b'1'),
(2014, 'Rgaa22-02121', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,14.html', '2.12', 2021201, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule02121', 2014, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-2-12',b'1'),
(2015, 'Rgaa22-02131', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,15.html', '2.13', 2021301, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule02131', 2015, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-2-13',b'1'),
(2016, 'Rgaa22-02141', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,16.html', '2.14', 2021401, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule02141', 2016, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-2-14',b'1'),
(2017, 'Rgaa22-02151', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,17.html', '2.15', 2021501, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule02151', 2017, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-2-15',b'1'),
(2018, 'Rgaa22-02161', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,18.html', '2.16', 2021601, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule02161', 2018, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-2-16',b'1'),
(2019, 'Rgaa22-03011', 'http://rgaa.net/Possibilite-d-identifier-les.html', '3.1', 2030101, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule03011', 2019, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-3-1',b'1'),
(2020, 'Rgaa22-03021', 'http://rgaa.net/Presence-d-information-prealable.html', '3.2', 2030201, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule03021', 2020, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-3-2',b'1'),
(2021, 'Rgaa22-03031', 'http://rgaa.net/Positionnement-correct-des.html', '3.3', 2030301, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule03031', 2021, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-3-3',b'1'),
(2022, 'Rgaa22-03041', 'http://rgaa.net/Regroupement-d-elements-de.html', '3.4', 2030401, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule03041', 2022, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-3-4',b'1'),
(2023, 'Rgaa22-03051', 'http://rgaa.net/Absence-d-element-fieldset-sans.html', '3.5', 2030501, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule03051', 2023, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-3-5',b'1'),
(2024, 'Rgaa22-03061', 'http://rgaa.net/Pertinence-du-contenu-de-l-element.html', '3.6', 2030601, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule03061', 2024, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-3-6',b'1'),
(2025, 'Rgaa22-03071', 'http://rgaa.net/Regroupement-d-elements-option.html', '3.7', 2030701, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule03071', 2025, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-3-7',b'1'),
(2026, 'Rgaa22-03081', 'http://rgaa.net/Presence-d-un-attribut-label-sur-l.html', '3.8', 2030801, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule03081', 2026, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-3-8',b'1'),
(2027, 'Rgaa22-03091', 'http://rgaa.net/Pertinence-du-contenu-de-l.html', '3.9', 2030901, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule03091', 2027, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-3-9',b'1'),
(2028, 'Rgaa22-03101', 'http://rgaa.net/Absence-d-element-de-formulaire.html', '3.10', 2031001, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule03101', 2028, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-3-10',b'1'),
(2029, 'Rgaa22-03111', 'http://rgaa.net/Absence-d-element-de-formulaire,29.html', '3.11', 2031101, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule03121', 2029, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-3-11',b'1'),
(2030, 'Rgaa22-03121', 'http://rgaa.net/Pertinence-des-etiquettes-d.html', '3.12', 2031201, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule03121', 2030, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-3-12',b'1'),
(2031, 'Rgaa22-03131', 'http://rgaa.net/Presence-d-informations-ou-de.html', '3.13', 2031301, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule03131', 2031, NULL, 5, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-3-13',b'1'),
(2032, 'Rgaa22-03141', 'http://rgaa.net/Presence-de-mecanismes-permettant.html', '3.14', 2031401, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule03141', 2032, NULL, 5, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-3-14',b'1'),
(2033, 'Rgaa22-03151', 'http://rgaa.net/Presence-de-mecanismes-permettant,33.html', '3.15', 2031501, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule03151', 2033, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-3-15',b'1'),
(2034, 'Rgaa22-03161', 'http://rgaa.net/Presence-d-une-page-d-aide-ou-d-un.html', '3.16', 2031601, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule03161', 2034, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-3-16',b'1'),
(2035, 'Rgaa22-04011', 'http://rgaa.net/Presence-de-l-attribut-alt.html', '4.1', 2040101, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule04011', 2035, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-4-1',b'1'),
(2036, 'Rgaa22-04021', 'http://rgaa.net/Pertinence-de-l-alternative.html', '4.2', 2040201, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule04021', 2036, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-4-2',b'1'),
(2037, 'Rgaa22-04031', 'http://rgaa.net/Pertinence-de-l-alternative,37.html', '4.3', 2040301, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule04031', 2037, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-4-3',b'1'),
(2038, 'Rgaa22-04041', 'http://rgaa.net/Pertinence-de-l-alternative,38.html', '4.4', 2040401, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule04041', 2038, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-4-4',b'1'),
(2039, 'Rgaa22-04051', 'http://rgaa.net/Pertinence-de-l-alternative-vide.html', '4.5', 2040501, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule04051', 2039, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-4-5',b'1'),
(2040, 'Rgaa22-04061', 'http://rgaa.net/Longueur-du-contenu-des.html', '4.6', 2040601, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule04061', 2040, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-4-6',b'1'),
(2041, 'Rgaa22-04071', 'http://rgaa.net/Existence-d-une-description-longue.html', '4.7', 2040701, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule04071', 2041, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-4-7',b'1'),
(2042, 'Rgaa22-04081', 'http://rgaa.net/Pertinence-de-la-description.html', '4.8', 2040801, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule04081', 2042, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-4-8',b'1'),
(2043, 'Rgaa22-04091', 'http://rgaa.net/Presence-de-l-attribut-longdesc.html', '4.9', 2040901, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule04091', 2043, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-4-9',b'1'),
(2044, 'Rgaa22-04101', 'http://rgaa.net/Presence-d-une-information-de.html', '4.10', 2041001, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule04101', 2044, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-4-10',b'1'),
(2045, 'Rgaa22-04111', 'http://rgaa.net/Coherence-dans-l-identification.html', '4.11', 2041101, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule04111', 2045, NULL, 5, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-4-11',b'1'),
(2046, 'Rgaa22-05011', 'http://rgaa.net/Acces-a-une-information.html', '5.1', 2050101, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05011', 2046, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-1',b'1'),
(2047, 'Rgaa22-05021', 'http://rgaa.net/Presence-de-la-transcription.html', '5.2', 2050201, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05021', 2047, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-2',b'1'),
(2048, 'Rgaa22-05031', 'http://rgaa.net/Pertinence-de-la-transcription.html', '5.3', 2050301, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05031', 2048, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-3',b'1'),
(2049, 'Rgaa22-05041', 'http://rgaa.net/Presence-d-une-description-audio.html', '5.4', 2050401, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05041', 2049, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-4',b'1'),
(2050, 'Rgaa22-05051', 'http://rgaa.net/Pertinence-de-la-description-audio.html', '5.5', 2050501, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05051', 2050, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-5',b'1'),
(2051, 'Rgaa22-05061', 'http://rgaa.net/Possibilite-de-controler-l.html', '5.6', 2050601, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05061', 2051, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-6',b'1'),
(2052, 'Rgaa22-05071', 'http://rgaa.net/Presence-d-une-description-audio,52.html', '5.7', 2050701, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05071', 2052, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-7',b'1'),
(2053, 'Rgaa22-05081', 'http://rgaa.net/Presence-d-une-description-audio,53.html', '5.8', 2050801, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05081', 2053, NULL, 5, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-8',b'1'),
(2054, 'Rgaa22-05091', 'http://rgaa.net/Presence-du-sous-titrage.html', '5.9', 2050901, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05091', 2054, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-9',b'1'),
(2055, 'Rgaa22-05101', 'http://rgaa.net/Pertinence-du-sous-titrage.html', '5.10', 2051001, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05101', 2055, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-10',b'1'),
(2056, 'Rgaa22-05111', 'http://rgaa.net/Presence-d-une-alternative-aux.html', '5.11', 2051101, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05111', 2056, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-11',b'1'),
(2057, 'Rgaa22-05121', 'http://rgaa.net/Presence-d-une-alternative-aux,57.html', '5.12', 2051201, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05121', 2057, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-12',b'1'),
(2058, 'Rgaa22-05131', 'http://rgaa.net/Absence-d-elements-provoquant-des.html', '5.13', 2051301, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05131', 2058, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-13',b'1'),
(2059, 'Rgaa22-05141', 'http://rgaa.net/Absence-de-code-javascript,59.html', '5.14', 2051401, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05141', 2059, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-14',b'1'),
(2060, 'Rgaa22-05151', 'http://rgaa.net/Absence-de-mise-en-forme.html', '5.15', 2051501, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05151', 2060, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-15',b'1'),
(2061, 'Rgaa22-05161', 'http://rgaa.net/Compatibilite-des-elements.html', '5.16', 2051601, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05161', 2061, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-16',b'1'),
(2062, 'Rgaa22-05171', 'http://rgaa.net/Absence-totale-de-changements.html', '5.17', 2051701, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05171', 2062, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-17',b'1'),
(2063, 'Rgaa22-05181', 'http://rgaa.net/Presence-du-sous-titrage,63.html', '5.18', 2051801, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05181', 2063, NULL, 5, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-18',b'1'),
(2064, 'Rgaa22-05191', 'http://rgaa.net/Absence-de-l-element-blink.html', '5.19', 2051901, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05191', 2064, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-19',b'1'),
(2065, 'Rgaa22-05201', 'http://rgaa.net/Absence-d-elements-provoquant-des,65.html', '5.20', 2052001, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05201', 2065, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-20',b'1'),
(2066, 'Rgaa22-05211', 'http://rgaa.net/Absence-de-code-javascript,66.html', '5.21', 2052101, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05211', 2066, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-21',b'1'),
(2067, 'Rgaa22-05221', 'http://rgaa.net/Absence-de-mise-en-forme,67.html', '5.22', 2052201, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05221', 2067, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-22',b'1'),
(2068, 'Rgaa22-05231', 'http://rgaa.net/Absence-d-element-marquee.html', '5.23', 2052301, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05231', 2068, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-23',b'1'),
(2069, 'Rgaa22-05241', 'http://rgaa.net/Absence-d-elements-affichant-des.html', '5.24', 2052401, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05241', 2069, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-24',b'1'),
(2070, 'Rgaa22-05251', 'http://rgaa.net/Absence-de-code-javascript,70.html', '5.25', 2052501, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05251', 2070, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-25',b'1'),
(2071, 'Rgaa22-05261', 'http://rgaa.net/Absence-de-mise-en-forme,71.html', '5.26', 2052601, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05261', 2071, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-26',b'1'),
(2072, 'Rgaa22-05271', 'http://rgaa.net/Independance-du-peripherique-d.html', '5.27', 2052701, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05271', 2072, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-27',b'1'),
(2073, 'Rgaa22-05281', 'http://rgaa.net/Presence-d-une-alternative-aux,73.html', '5.28', 2052801, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05281', 2073, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-28',b'1'),
(2074, 'Rgaa22-05291', 'http://rgaa.net/Absence-d-elements-declenchant-la.html', '5.29', 2052901, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05291', 2074, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-29',b'1'),
(2075, 'Rgaa22-05301', 'http://rgaa.net/Absence-d-element-bgsound.html', '5.30', 2053001, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05301', 2075, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-30',b'1'),
(2076, 'Rgaa22-05311', 'http://rgaa.net/Presence-de-version-en-langue-des.html', '5.31', 2053101, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05311', 2076, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-31',b'1'),
(2077, 'Rgaa22-05321', 'http://rgaa.net/Pertinence-de-la-version-en-langue.html', '5.32', 2053201, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05321', 2077, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-32',b'1'),
(2078, 'Rgaa22-05331', 'http://rgaa.net/Niveau-sonore-de-la-piste-de.html', '5.33', 2053301, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05331', 2078, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-33',b'1'),
(2079, 'Rgaa22-05341', 'http://rgaa.net/Presence-d-un-mecanisme-pour.html', '5.34', 2053401, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule05341', 2079, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-5-34',b'1'),
(2080, 'Rgaa22-06011', 'http://rgaa.net/Acces-aux-liens-textuels-doublant.html', '6.1', 2060101, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06011', 2080, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-1',b'1'),
(2081, 'Rgaa22-06021', 'http://rgaa.net/Presence-d-un-avertissement.html', '6.2', 2060201, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06021', 2081, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-2',b'1'),
(2082, 'Rgaa22-06031', 'http://rgaa.net/Presence-d-un-avertissement,82.html', '6.3', 2060301, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06031', 2082, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-3',b'1'),
(2083, 'Rgaa22-06041', 'http://rgaa.net/Presence-d-un-avertissement,83.html', '6.4', 2060401, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06041', 2083, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-4',b'1'),
(2084, 'Rgaa22-06051', 'http://rgaa.net/Absence-d-ouverture-de-nouvelles.html', '6.5', 2060501, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06051', 2084, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-5',b'1'),
(2085, 'Rgaa22-06061', 'http://rgaa.net/Absence-de-piege-lors-de-la.html', '6.6', 2060601, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06061', 2085, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-6',b'1'),
(2086, 'Rgaa22-06071', 'http://rgaa.net/Absence-d-element-meta-provoquant.html', '6.7', 2060701, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06071', 2086, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-7',b'1'),
(2087, 'Rgaa22-06081', 'http://rgaa.net/Absence-de-code-javascript.html', '6.8', 2060801, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06081', 2087, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-8',b'1'),
(2088, 'Rgaa22-06091', 'http://rgaa.net/Absence-d-elements-provoquant-un.html', '6.9', 2060901, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06091', 2088, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-9',b'1'),
(2089, 'Rgaa22-06101', 'http://rgaa.net/Absence-d-element-meta-provoquant,89.html', '6.10', 2061001, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06101', 2089, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-10',b'1'),
(2090, 'Rgaa22-06111', 'http://rgaa.net/Absence-de-code-javascript,90.html', '6.11', 2061101, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06111', 2090, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-11',b'1'),
(2091, 'Rgaa22-06121', 'http://rgaa.net/Absence-d-elements-provoquant-une.html', '6.12', 2061201, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06121', 2091, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-12',b'1'),
(2092, 'Rgaa22-06131', 'http://rgaa.net/Possibilite-d-identifier-la.html', '6.13', 2061301, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06131', 2092, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-13',b'1'),
(2093, 'Rgaa22-06141', 'http://rgaa.net/Possibilite-d-identifier-la,93.html', '6.14', 2061401, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06141', 2093, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-14',b'1'),
(2094, 'Rgaa22-06151', 'http://rgaa.net/Coherence-de-la-destination-ou-de.html', '6.15', 2061501, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06151', 2094, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-15',b'1'),
(2095, 'Rgaa22-06161', 'http://rgaa.net/Absence-de-liens-sans-intitule.html', '6.16', 2061601, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06161', 2095, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-16',b'1'),
(2096, 'Rgaa22-06171', 'http://rgaa.net/Presence-d-une-page-contenant-le.html', '6.17', 2061701, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06171', 2096, NULL, 5, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-17',b'1'),
(2097, 'Rgaa22-06181', 'http://rgaa.net/Coherence-du-plan-du-site.html', '6.18', 2061801, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06181', 2097, NULL, 5, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-18',b'1'),
(2098, 'Rgaa22-06191', 'http://rgaa.net/Presence-d-un-acces-a-la-page.html', '6.19', 2061901, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06191', 2098, NULL, 5, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-19',b'1'),
(2099, 'Rgaa22-06201', 'http://rgaa.net/Presence-d-un-fil-d-ariane.html', '6.20', 2062001, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06201', 2099, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-20',b'1'),
(2100, 'Rgaa22-06211', 'http://rgaa.net/Presence-de-menus-ou-de-barres-de.html', '6.21', 2062101, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06211', 2100, NULL, 5, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-21',b'1'),
(2101, 'Rgaa22-06221', 'http://rgaa.net/Coherence-de-la-position-des-menus.html', '6.22', 2062201, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06221', 2101, NULL, 5, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-22',b'1'),
(2102, 'Rgaa22-06231', 'http://rgaa.net/Coherence-de-la-presentation-des.html', '6.23', 2062301, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06231', 2102, NULL, 5, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-23',b'1'),
(2103, 'Rgaa22-06241', 'http://rgaa.net/Navigation-au-clavier-dans-un.html', '6.24', 2062401, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06241', 2103, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-24',b'1'),
(2104, 'Rgaa22-06251', 'http://rgaa.net/Presence-d-un-avertissement,104.html', '6.25', 2062501, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06251', 2104, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-25',b'1'),
(2105, 'Rgaa22-06261', 'http://rgaa.net/Presence-des-informations-de.html', '6.26', 2062601, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06261', 2105, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-26',b'1'),
(2106, 'Rgaa22-06271', 'http://rgaa.net/Presence-des-informations-de-poids.html', '6.27', 2062701, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06271', 2106, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-27',b'1'),
(2107, 'Rgaa22-06281', 'http://rgaa.net/Presence-des-informations-de,107.html', '6.28', 2062801, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06281', 2107, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-28',b'1'),
(2108, 'Rgaa22-06291', 'http://rgaa.net/Presence-de-regroupement-pour-les.html', '6.29', 2062901, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06291', 2108, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-29',b'1'),
(2109, 'Rgaa22-06301', 'http://rgaa.net/Presence-d-un-balisage-permettant.html', '6.30', 2063001, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06301', 2109, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-30',b'1'),
(2110, 'Rgaa22-06311', 'http://rgaa.net/Presence-de-liens-d-evitement-ou-d.html', '6.31', 2063101, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06311', 2110, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-31',b'1'),
(2111, 'Rgaa22-06321', 'http://rgaa.net/Coherence-des-liens-d-evitement-ou.html', '6.32', 2063201, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06321', 2111, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-32',b'1'),
(2112, 'Rgaa22-06331', 'http://rgaa.net/Ordre-des-liens-d-evitement-ou-d.html', '6.33', 2063301, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06331', 2112, NULL, 5, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-33',b'1'),
(2113, 'Rgaa22-06341', 'http://rgaa.net/Presence-d-un-moteur-de-recherche.html', '6.34', 2063401, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06341', 2113, NULL, 5, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-34',b'1'),
(2114, 'Rgaa22-06351', 'http://rgaa.net/Possibilite-de-naviguer-facilement.html', '6.35', 2063501, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06351', 2114, NULL, 5, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-35',b'1'),
(2115, 'Rgaa22-06361', 'http://rgaa.net/Presence-d-une-indication-de-la.html', '6.36', 2063601, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule06361', 2115, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-6-36',b'1'),
(2116, 'Rgaa22-07011', 'http://rgaa.net/Absence-de-generation-de-contenus.html', '7.1', 2070101, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule07011', 2116, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-7-1',b'1'),
(2117, 'Rgaa22-07021', 'http://rgaa.net/Absence-d-alteration-de-la.html', '7.2', 2070201, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule07021', 2117, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-7-2',b'1'),
(2118, 'Rgaa22-07031', 'http://rgaa.net/Lisibilite-des-informations.html', '7.3', 2070301, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule07031', 2118, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-7-3',b'1'),
(2119, 'Rgaa22-07041', 'http://rgaa.net/Absence-d-espaces-utilises-pour.html', '7.4', 2070401, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule07041', 2119, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-7-4',b'1'),
(2120, 'Rgaa22-07051', 'http://rgaa.net/Absence-de-definition-d-une.html', '7.5', 2070501, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule07051', 2120, NULL, 5, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-7-5',b'1'),
(2121, 'Rgaa22-07061', 'http://rgaa.net/Possibilite-de-remplacer-les.html', '7.6', 2070601, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule07061', 2121, NULL, 5, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-7-6',b'1'),
(2122, 'Rgaa22-07071', 'http://rgaa.net/Possibilite-de-remplacer-les,122.html', '7.7', 2070701, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule07071', 2122, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-7-7',b'1'),
(2123, 'Rgaa22-07081', 'http://rgaa.net/Absence-d-attributs-ou-d-elements.html', '7.8', 2070801, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule07081', 2123, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-7-8',b'1'),
(2124, 'Rgaa22-07091', 'http://rgaa.net/Absence-d-elements-HTML-utilises-a.html', '7.9', 2070901, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule07091', 2124, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-7-9',b'1'),
(2125, 'Rgaa22-07101', 'http://rgaa.net/Maintien-de-la-distinction.html', '7.10', 2071001, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule07101', 2125, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-7-10',b'1'),
(2126, 'Rgaa22-07111', 'http://rgaa.net/Absence-de-suppression-de-l-effet.html', '7.11', 2071101, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule07111', 2126, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-7-11',b'1'),
(2127, 'Rgaa22-07121', 'http://rgaa.net/Absence-de-justification-du-texte.html', '7.12', 2071201, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule07121', 2127, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-7-12',b'1'),
(2128, 'Rgaa22-07131', 'http://rgaa.net/Lisibilite-du-document-en-cas-d.html', '7.13', 2071301, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule07131', 2128, NULL, 5, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-7-13',b'1'),
(2129, 'Rgaa22-07141', 'http://rgaa.net/Absence-d-unites-absolues-ou-de.html', '7.14', 2071401, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule07141', 2129, NULL, 5, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-7-14',b'1'),
(2130, 'Rgaa22-07151', 'http://rgaa.net/Absence-d-apparition-de-barre-de.html', '7.15', 2071501, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule07151', 2130, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-7-15',b'1'),
(2131, 'Rgaa22-07161', 'http://rgaa.net/Largeur-des-blocs-de-textes.html', '7.16', 2071601, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule07161', 2131, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-7-16',b'1'),
(2132, 'Rgaa22-07171', 'http://rgaa.net/Valeur-de-l-espace-entre-les.html', '7.17', 2071701, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule07171', 2132, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-7-17',b'1'),
(2133, 'Rgaa22-07181', 'http://rgaa.net/Restitution-correcte-dans-les.html', '7.18', 2071801, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule07181', 2133, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-7-18',b'1'),
(2134, 'Rgaa22-08011', 'http://rgaa.net/Mise-a-jour-des-alternatives-aux.html', '8.1', 2080101, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule08011', 2134, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-8-1',b'1'),
(2135, 'Rgaa22-08021', 'http://rgaa.net/Universalite-du-gestionnaire-d.html', '8.2', 2080201, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule08021', 2135, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-8-2',b'1'),
(2136, 'Rgaa22-08031', 'http://rgaa.net/Universalite-des-gestionnaires-d.html', '8.3', 2080301, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule08031', 2136, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-8-3',b'1'),
(2137, 'Rgaa22-08041', 'http://rgaa.net/Possibilite-de-desactiver-toute.html', '8.4', 2080401, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule08041', 2137, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-8-4',b'1'),
(2138, 'Rgaa22-08051', 'http://rgaa.net/Absence-de-changements-de-contexte.html', '8.5', 2080501, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule08051', 2138, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-8-5',b'1'),
(2139, 'Rgaa22-08061', 'http://rgaa.net/Ordre-d-acces-au-clavier-aux.html', '8.6', 2080601, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule08061', 2139, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-8-6',b'1'),
(2140, 'Rgaa22-08071', 'http://rgaa.net/Utilisation-correcte-du-role-des.html', '8.7', 2080701, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule08071', 2140, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-8-7',b'1'),
(2141, 'Rgaa22-08081', 'http://rgaa.net/Presence-d-une-alternative-au-code.html', '8.8', 2080801, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule08081', 2141, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-8-8',b'1'),
(2142, 'Rgaa22-08091', 'http://rgaa.net/Absence-de-suppression-du-focus.html', '8.9', 2080901, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule08091', 2142, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-8-9',b'1'),
(2143, 'Rgaa22-08101', 'http://rgaa.net/Absence-de-limite-de-temps-pour.html', '8.10', 2080101, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule08101', 2143, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-8-10',b'1'),
(2144, 'Rgaa22-08111', 'http://rgaa.net/Absence-de-perte-d-informations.html', '8.11', 2080111, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule08111', 2144, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-8-11',b'1'),
(2145, 'Rgaa22-08121', 'http://rgaa.net/Presence-d-une-alternative-au-code,145.html', '8.12', 2080121, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule08121', 2145, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-8-12',b'1'),
(2146, 'Rgaa22-08131', 'http://rgaa.net/Accessibilite-des-contenus.html', '8.13', 2080131, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule08131', 2146, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-8-13',b'1'),
(2147, 'Rgaa22-09011', 'http://rgaa.net/Presence-de-la-declaration-d.html', '9.1', 2090101, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule09011', 2147, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-9-1',b'1'),
(2148, 'Rgaa22-09021', 'http://rgaa.net/Conformite-de-la-position-de-la.html', '9.2', 2090201, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule09021', 2148, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-9-2',b'1'),
(2149, 'Rgaa22-09031', 'http://rgaa.net/Conformite-syntaxique-de-la.html', '9.3', 2090301, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule09031', 2149, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-9-3',b'1'),
(2150, 'Rgaa22-09041', 'http://rgaa.net/Validite-du-code-HTML-XHTML-au.html', '9.4', 2090401, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule09041', 2150, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-9-4',b'1'),
(2151, 'Rgaa22-09051', 'http://rgaa.net/Absence-de-composants-obsoletes.html', '9.5', 2090501, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule09051', 2151, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-9-5',b'1'),
(2152, 'Rgaa22-09061', 'http://rgaa.net/Presence-d-un-titre-dans-la-page.html', '9.6', 2090601, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule09061', 2152, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-9-6',b'1'),
(2153, 'Rgaa22-09071', 'http://rgaa.net/Pertinence-du-titre-de-la-page.html', '9.7', 2090701, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule09071', 2153, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-9-7',b'1'),
(2154, 'Rgaa22-09081', 'http://rgaa.net/Presence-d-une-langue-de.html', '9.8', 2090801, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule09081', 2154, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-9-8',b'1'),
(2155, 'Rgaa22-10011', 'http://rgaa.net/Presence-d-au-moins-un-titre-de.html', '10.1', 2100101, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule10011', 2155, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-10-1',b'1'),
(2156, 'Rgaa22-10021', 'http://rgaa.net/Pertinence-du-contenu-des-titres.html', '10.2', 2100201, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule10021', 2156, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-10-2',b'1'),
(2157, 'Rgaa22-10031', 'http://rgaa.net/Absence-d-interruption-dans-la.html', '10.3', 2100301, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule10031', 2157, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-10-3',b'1'),
(2158, 'Rgaa22-10041', 'http://rgaa.net/Presence-d-une-hierarchie-de.html', '10.4', 2100401, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule10041', 2158, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-10-4',b'1'),
(2159, 'Rgaa22-10051', 'http://rgaa.net/Absence-de-simulation-visuelle-de.html', '10.5', 2100501, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule10051', 2159, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-10-5',b'1'),
(2160, 'Rgaa22-10061', 'http://rgaa.net/Utilisation-systematique-de-listes.html', '10.6', 2100601, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule10061', 2160, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-10-6',b'1'),
(2161, 'Rgaa22-10071', 'http://rgaa.net/Balisage-correct-des-listes-de.html', '10.7', 2100701, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule10071', 2161, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-10-7',b'1'),
(2162, 'Rgaa22-10081', 'http://rgaa.net/Balisage-correct-des-citations.html', '10.8', 2100801, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule10081', 2162, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-10-8',b'1'),
(2163, 'Rgaa22-10091', 'http://rgaa.net/Balisage-correct-des-abreviations.html', '10.9', 2100901, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule10091', 2163, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-10-9',b'1'),
(2164, 'Rgaa22-10101', 'http://rgaa.net/Balisage-correct-des-acronymes.html', '10.10', 2101001, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule10101', 2164, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-10-10',b'1'),
(2165, 'Rgaa22-10111', 'http://rgaa.net/Pertinence-de-la-version-non.html', '10.11', 2101101, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule10111', 2165, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-10-11',b'1'),
(2166, 'Rgaa22-10121', 'http://rgaa.net/Pertinence-de-la-version-complete.html', '10.12', 2101201, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule10121', 2166, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-10-12',b'1'),
(2167, 'Rgaa22-10131', 'http://rgaa.net/Accessibilite-des-documents.html', '10.13', 2101301, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule10131', 2167, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-10-13',b'1'),
(2168, 'Rgaa22-11011', 'http://rgaa.net/Presence-des-balises-th-pour.html', '11.1', 2110101, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule11011', 2168, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-11-1',b'1'),
(2169, 'Rgaa22-11021', 'http://rgaa.net/Presence-d-une-relation-entre-les.html', '11.2', 2110201, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule11021', 2169, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-11-2',b'1'),
(2170, 'Rgaa22-11031', 'http://rgaa.net/Presence-d-une-relation-entre-les,170.html', '11.3', 2110301, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule11031', 2170, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-11-3',b'1'),
(2171, 'Rgaa22-11041', 'http://rgaa.net/Absence-des-elements-propres-aux.html', '11.4', 2110401, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule11041', 2171, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-11-4',b'1'),
(2172, 'Rgaa22-11051', 'http://rgaa.net/Absence-de-tableaux-de-donnees-ou.html', '11.5', 2110501, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule11051', 2172, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-11-5',b'1'),
(2173, 'Rgaa22-11061', 'http://rgaa.net/Linearisation-correcte-des.html', '11.6', 2110601, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule11061', 2173, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-11-6',b'1'),
(2174, 'Rgaa22-11071', 'http://rgaa.net/Presence-d-un-titre-pour-les.html', '11.7', 2110701, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule11071', 2174, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-11-7',b'1'),
(2175, 'Rgaa22-11081', 'http://rgaa.net/Presence-d-un-resume-pour-les.html', '11.8', 2110801, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule11081', 2175, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-11-8',b'1'),
(2176, 'Rgaa22-11091', 'http://rgaa.net/Pertinence-du-titre-du-tableau-de.html', '11.9', 2110901, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule11091', 2176, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-11-9',b'1'),
(2177, 'Rgaa22-11101', 'http://rgaa.net/Pertinence-du-resume-du-tableau-de.html', '11.10', 2111001, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule11101', 2177, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-11-10',b'1'),
(2178, 'Rgaa22-12011', 'http://rgaa.net/Presence-de-l-indication-des.html', '12.1', 2120101, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule12011', 2178, NULL, 5, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-12-1',b'1'),
(2179, 'Rgaa22-12021', 'http://rgaa.net/Presence-de-l-indication-des,179.html', '12.2', 2120201, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule12021', 2179, NULL, 5, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-12-2',b'1'),
(2180, 'Rgaa22-12031', 'http://rgaa.net/Equivalence-de-l-information-mise.html', '12.3', 2120301, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule12031', 2180, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-12-3',b'1'),
(2181, 'Rgaa22-12041', 'http://rgaa.net/Presence-de-liens-ou-de.html', '12.4', 2120401, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule12041', 2181, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-12-4',b'1'),
(2182, 'Rgaa22-12051', 'http://rgaa.net/Absence-de-syntaxes-cryptiques-par.html', '12.5', 2120501, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule12051', 2182, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-12-5',b'1'),
(2183, 'Rgaa22-12061', 'http://rgaa.net/Presence-d-informations-sur-les.html', '12.6', 2120601, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule12061', 2183, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-12-6',b'1'),
(2184, 'Rgaa22-12071', 'http://rgaa.net/Presence-d-un-moyen-de,184.html', '12.7', 2120701, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule12071', 2184, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-12-7',b'1'),
(2185, 'Rgaa22-12081', 'http://rgaa.net/Presence-d-un-autre-moyen-que-la,185.html', '12.8', 2120801, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule12081', 2185, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-12-8',b'1'),
(2186, 'Rgaa22-12091', 'http://rgaa.net/Presence-d-un-autre-moyen-que-la,186.html', '12.9', 2120901, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule12091', 2186, NULL, 4, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-12-9',b'1'),
(2187, 'Rgaa22-12101', 'http://rgaa.net/Utilisation-d-un-style-de.html', '12.10', 2121001, '1.0', 'rgaa2.2', 'org.opens.tanaguru.rules.rgaa22.Rgaa22Rule12101', 2187, NULL, 6, 1, 'http://www.tanaguru.org/fr/content/rgaa22-rule-12-10', b'1');

UPDATE TEST SET `No_Process`=b'0' WHERE 
Cd_Test='Rgaa22-01011' OR 
Cd_Test='Rgaa22-01021' OR 
Cd_Test='Rgaa22-02071' OR 
Cd_Test='Rgaa22-02101' OR 
Cd_Test='Rgaa22-02131' OR 
Cd_Test='Rgaa22-02161' OR 
Cd_Test='Rgaa22-03011' OR 
Cd_Test='Rgaa22-03021' OR 
Cd_Test='Rgaa22-03031' OR 
Cd_Test='Rgaa22-03041' OR 
Cd_Test='Rgaa22-03051' OR 
Cd_Test='Rgaa22-03061' OR 
Cd_Test='Rgaa22-03071' OR 
Cd_Test='Rgaa22-03081' OR 
Cd_Test='Rgaa22-03091' OR 
Cd_Test='Rgaa22-03101' OR 
Cd_Test='Rgaa22-03111' OR 
Cd_Test='Rgaa22-03121' OR 
Cd_Test='Rgaa22-03131' OR 
Cd_Test='Rgaa22-03141' OR 
Cd_Test='Rgaa22-03151' OR 
Cd_Test='Rgaa22-03161' OR
Cd_Test='Rgaa22-04051' OR
Cd_Test='Rgaa22-04061' OR
Cd_Test='Rgaa22-04071' OR
Cd_Test='Rgaa22-04081' OR
Cd_Test='Rgaa22-04091' OR
Cd_Test='Rgaa22-04101' OR
Cd_Test='Rgaa22-05111' OR
Cd_Test='Rgaa22-05121' OR
Cd_Test='Rgaa22-05161' OR
Cd_Test='Rgaa22-05191' OR
Cd_Test='Rgaa22-05231' OR
Cd_Test='Rgaa22-05271' OR
Cd_Test='Rgaa22-05281' OR
Cd_Test='Rgaa22-05291' OR
Cd_Test='Rgaa22-05301' OR
Cd_Test='Rgaa22-06011' OR
Cd_Test='Rgaa22-06021' OR
Cd_Test='Rgaa22-06031' OR
Cd_Test='Rgaa22-06131' OR
Cd_Test='Rgaa22-06141' OR
Cd_Test='Rgaa22-06151' OR
Cd_Test='Rgaa22-06161' OR
Cd_Test='Rgaa22-06251' OR
Cd_Test='Rgaa22-06261' OR
Cd_Test='Rgaa22-06271' OR
Cd_Test='Rgaa22-06281' OR
Cd_Test='Rgaa22-07011' OR
Cd_Test='Rgaa22-07081' OR
Cd_Test='Rgaa22-07091' OR
Cd_Test='Rgaa22-07111' OR
Cd_Test='Rgaa22-07181' OR
Cd_Test='Rgaa22-08051' OR
Cd_Test='Rgaa22-09061' OR
Cd_Test='Rgaa22-09071' OR
Cd_Test='Rgaa22-10011' OR
Cd_Test='Rgaa22-10021' OR
Cd_Test='Rgaa22-10071' OR
Cd_Test='Rgaa22-10131' OR
Cd_Test='Rgaa22-10111' OR
Cd_Test='Rgaa22-10121' OR
Cd_Test='Rgaa22-11011' OR
Cd_Test='Rgaa22-11021' OR
Cd_Test='Rgaa22-11031' OR
Cd_Test='Rgaa22-11041' OR
Cd_Test='Rgaa22-11061' OR
Cd_Test='Rgaa22-11071' OR
Cd_Test='Rgaa22-11081' OR
Cd_Test='Rgaa22-11091' OR
Cd_Test='Rgaa22-11101' OR
Cd_Test='Rgaa22-12031';

SET foreign_key_checks=1;
