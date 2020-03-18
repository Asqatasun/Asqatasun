
--
-- Contenu de la table `reference`
--
INSERT IGNORE INTO `REFERENCE` (`Cd_Reference`, `Description`, `Label`, `Url`, `Rank`, `Id_Default_Level`) VALUES
('Rgaa22', NULL, 'Rgaa 2.2', 'www.references.modernisation.gouv.fr/sites/default/files/RGAA_v2.2.1.pdf', 2, 2);

--
-- Contenu de la table `TGSI_REFERENTIAL`
--
-- INSERT IGNORE INTO `TGSI_REFERENTIAL` (`Code`, `Label`) VALUES
-- ('Rgaa22', 'Rgaa 2.2');

--
-- Contenu de la table `theme`
--
INSERT IGNORE INTO `THEME` (`Cd_Theme`, `Description`, `Label`, `Rank`) VALUES
('Rgaa22-1', 'http://rgaa.net/-Cadres-.html', 'Cadres', 1),
('Rgaa22-2', 'http://rgaa.net/-Couleurs-.html', 'Couleurs', 2),
('Rgaa22-3', 'http://rgaa.net/-Formulaires-.html', 'Formulaires', 3),
('Rgaa22-4', 'http://rgaa.net/-Images-.html', 'Images', 4),
('Rgaa22-5', 'http://rgaa.net/-Multimedia-.html', 'Multimédia', 5),
('Rgaa22-6', 'http://rgaa.net/-Navigation-.html', 'Navigation', 6),
('Rgaa22-7', 'http://rgaa.net/-Presentation-.html', 'Présentation', 7),
('Rgaa22-8', 'http://rgaa.net/-Scripts-.html', 'Scripts', 8),
('Rgaa22-9', 'http://rgaa.net/-Standards-.html', 'Standards', 9),
('Rgaa22-10', 'http://rgaa.net/-Structure-.html', 'Structure', 10),
('Rgaa22-11', 'http://rgaa.net/-Tableaux-.html', 'Tableaux', 11),
('Rgaa22-12', 'http://rgaa.net/-Textes-.html', 'Textes', 12);

--
-- Contenu de la table `criterion`
--
INSERT IGNORE INTO `CRITERION` (`Cd_Criterion`, `Description`, `Label`, `Url`, `Rank`) VALUES
('Rgaa22-0101', '', '1.1', 'http://rgaa.net/1-1-Absence-de-cadres-non-titres.html', 1),
('Rgaa22-0102', '', '1.2', 'http://rgaa.net/Pertinence-des-titres-donnes-aux.html', 2),
('Rgaa22-0201', '', '2.1', 'http://rgaa.net/Presence-d-un-autre-moyen-que-la.html', 3),
('Rgaa22-0202', '', '2.2', 'http://rgaa.net/Presence-d-un-autre-moyen-que-la,4.html', 4),
('Rgaa22-0203', '', '2.3', 'http://rgaa.net/Presence-d-un-moyen-de.html', 5),
('Rgaa22-0204', '', '2.4', 'http://rgaa.net/Presence-d-un-moyen-de,6.html', 6),
('Rgaa22-0205', '', '2.5', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du.html', 7),
('Rgaa22-0206', '', '2.6', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,8.html', 8),
('Rgaa22-0207', '', '2.7', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,9.html', 9),
('Rgaa22-0208', '', '2.8', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,10.html',10),
('Rgaa22-0209', '', '2.9', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,11.html', 11),
('Rgaa22-0210', '', '2.10', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,12.html', 12),
('Rgaa22-0211', '', '2.11', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,13.html', 13),
('Rgaa22-0212', '', '2.12', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,14.html', 14),
('Rgaa22-0213', '', '2.13', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,15.html', 15),
('Rgaa22-0214', '', '2.14', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,16.html', 16),
('Rgaa22-0215', '', '2.15', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,17.html', 17),
('Rgaa22-0216', '', '2.16', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,18.html', 18),
('Rgaa22-0301', '', '3.1', 'http://rgaa.net/Possibilite-d-identifier-les.html', 19),
('Rgaa22-0302', '', '3.2', 'http://rgaa.net/Presence-d-information-prealable.html', 20),
('Rgaa22-0303', '', '3.3', 'http://rgaa.net/Positionnement-correct-des.html',  21),
('Rgaa22-0304', '', '3.4', 'http://rgaa.net/Regroupement-d-elements-de.html', 22),
('Rgaa22-0305', '', '3.5', 'http://rgaa.net/Absence-d-element-fieldset-sans.html', 23),
('Rgaa22-0306', '', '3.6', 'http://rgaa.net/Pertinence-du-contenu-de-l-element.html', 24),
('Rgaa22-0307', '', '3.7', 'http://rgaa.net/Regroupement-d-elements-option.html', 25),
('Rgaa22-0308', '', '3.8', 'http://rgaa.net/Presence-d-un-attribut-label-sur-l.html', 26),
('Rgaa22-0309', '', '3.9', 'http://rgaa.net/Pertinence-du-contenu-de-l.html', 27),
('Rgaa22-0310', '', '3.10', 'http://rgaa.net/Absence-d-element-de-formulaire.html', 28),
('Rgaa22-0311', '', '3.11', 'http://rgaa.net/Absence-d-element-de-formulaire,29.html', 29),
('Rgaa22-0312', '', '3.12', 'http://rgaa.net/Pertinence-des-etiquettes-d.html',  30),
('Rgaa22-0313', '', '3.13', 'http://rgaa.net/Presence-d-informations-ou-de.html',  31),
('Rgaa22-0314', '', '3.14', 'http://rgaa.net/Presence-de-mecanismes-permettant.html',  32),
('Rgaa22-0315', '', '3.15', 'http://rgaa.net/Presence-de-mecanismes-permettant,33.html',  33),
('Rgaa22-0316', '', '3.16', 'http://rgaa.net/Presence-d-une-page-d-aide-ou-d-un.html',  34),
('Rgaa22-0401', '', '4.1', 'http://rgaa.net/Presence-de-l-attribut-alt.html',  35),
('Rgaa22-0402', '', '4.2', 'http://rgaa.net/Pertinence-de-l-alternative.html',  36),
('Rgaa22-0403', '', '4.3', 'http://rgaa.net/Pertinence-de-l-alternative,37.html',  37),
('Rgaa22-0404', '', '4.4', 'http://rgaa.net/Pertinence-de-l-alternative,38.html',  38),
('Rgaa22-0405', '', '4.5', 'http://rgaa.net/Pertinence-de-l-alternative-vide.html',  39),
('Rgaa22-0406', '', '4.6', 'http://rgaa.net/Longueur-du-contenu-des.html',  40),
('Rgaa22-0407', '', '4.7', 'http://rgaa.net/Existence-d-une-description-longue.html',  41),
('Rgaa22-0408', '', '4.8', 'http://rgaa.net/Pertinence-de-la-description.html',  42),
('Rgaa22-0409', '', '4.9', 'http://rgaa.net/Presence-de-l-attribut-longdesc.html', 43),
('Rgaa22-0410', '', '4.10', 'http://rgaa.net/Presence-d-une-information-de.html',  44),
('Rgaa22-0411', '', '4.11', 'http://rgaa.net/Coherence-dans-l-identification.html',  45),
('Rgaa22-0501', '', '5.1', 'http://rgaa.net/Acces-a-une-information.html',  46),
('Rgaa22-0502', '', '5.2', 'http://rgaa.net/Presence-de-la-transcription.html',  47),
('Rgaa22-0503', '', '5.3', 'http://rgaa.net/Pertinence-de-la-transcription.html',  48),
('Rgaa22-0504', '', '5.4', 'http://rgaa.net/Presence-d-une-description-audio.html', 49),
('Rgaa22-0505', '', '5.5', 'http://rgaa.net/Pertinence-de-la-description-audio.html',  50),
('Rgaa22-0506', '', '5.6', 'http://rgaa.net/Possibilite-de-controler-l.html',  51),
('Rgaa22-0507', '', '5.7', 'http://rgaa.net/Presence-d-une-description-audio,52.html',  52),
('Rgaa22-0508', '', '5.8', 'http://rgaa.net/Presence-d-une-description-audio,53.html',  53),
('Rgaa22-0509', '', '5.9', 'http://rgaa.net/Presence-du-sous-titrage.html',  54),
('Rgaa22-0510', '', '5.10', 'http://rgaa.net/Pertinence-du-sous-titrage.html',  55),
('Rgaa22-0511', '', '5.11', 'http://rgaa.net/Presence-d-une-alternative-aux.html',  56),
('Rgaa22-0512', '', '5.12', 'http://rgaa.net/Presence-d-une-alternative-aux,57.html',  57),
('Rgaa22-0513', '', '5.13', 'http://rgaa.net/Absence-d-elements-provoquant-des.html',  58),
('Rgaa22-0514', '', '5.14', 'http://rgaa.net/Absence-de-code-javascript,59.html',  59),
('Rgaa22-0515', '', '5.15', 'http://rgaa.net/Absence-de-mise-en-forme.html',  60),
('Rgaa22-0516', '', '5.16', 'http://rgaa.net/Compatibilite-des-elements.html',  61),
('Rgaa22-0517', '', '5.17', 'http://rgaa.net/Absence-totale-de-changements.html',  62),
('Rgaa22-0518', '', '5.18', 'http://rgaa.net/Presence-du-sous-titrage,63.html',  63),
('Rgaa22-0519', '', '5.19', 'http://rgaa.net/Absence-de-l-element-blink.html',  64),
('Rgaa22-0520', '', '5.20', 'http://rgaa.net/Absence-d-elements-provoquant-des,65.html',  65),
('Rgaa22-0521', '', '5.21', 'http://rgaa.net/Absence-de-code-javascript,66.html',  66),
('Rgaa22-0522', '', '5.22', 'http://rgaa.net/Absence-de-mise-en-forme,67.html',  67),
('Rgaa22-0523', '', '5.23', 'http://rgaa.net/Absence-d-element-marquee.html',  68),
('Rgaa22-0524', '', '5.24', 'http://rgaa.net/Absence-d-elements-affichant-des.html',  69),
('Rgaa22-0525', '', '5.25', 'http://rgaa.net/Absence-de-code-javascript,70.html',  70),
('Rgaa22-0526', '', '5.26', 'http://rgaa.net/Absence-de-mise-en-forme,71.html',  71),
('Rgaa22-0527', '', '5.27', 'http://rgaa.net/Independance-du-peripherique-d.html',  72),
('Rgaa22-0528', '', '5.28', 'http://rgaa.net/Presence-d-une-alternative-aux,73.html', 73),
('Rgaa22-0529', '', '5.29', 'http://rgaa.net/Absence-d-elements-declenchant-la.html',  74),
('Rgaa22-0530', '', '5.30', 'http://rgaa.net/Absence-d-element-bgsound.html', 75),
('Rgaa22-0531', '', '5.31', 'http://rgaa.net/Presence-de-version-en-langue-des.html',  76),
('Rgaa22-0532', '', '5.32', 'http://rgaa.net/Pertinence-de-la-version-en-langue.html',  77),
('Rgaa22-0533', '', '5.33', 'http://rgaa.net/Niveau-sonore-de-la-piste-de.html',  78),
('Rgaa22-0534', '', '5.34', 'http://rgaa.net/Presence-d-un-mecanisme-pour.html', 79),
('Rgaa22-0601', '', '6.1', 'http://rgaa.net/Acces-aux-liens-textuels-doublant.html',  80),
('Rgaa22-0602', '', '6.2', 'http://rgaa.net/Presence-d-un-avertissement.html',  81),
('Rgaa22-0603', '', '6.3', 'http://rgaa.net/Presence-d-un-avertissement,82.html',  82),
('Rgaa22-0604', '', '6.4', 'http://rgaa.net/Presence-d-un-avertissement,83.html', 83),
('Rgaa22-0605', '', '6.5', 'http://rgaa.net/Absence-d-ouverture-de-nouvelles.html',  84),
('Rgaa22-0606', '', '6.6', 'http://rgaa.net/Absence-de-piege-lors-de-la.html', 85),
('Rgaa22-0607', '', '6.7', 'http://rgaa.net/Absence-d-element-meta-provoquant.html', 86),
('Rgaa22-0608', '', '6.8', 'http://rgaa.net/Absence-de-code-javascript.html', 87),
('Rgaa22-0609', '', '6.9', 'http://rgaa.net/Absence-d-elements-provoquant-un.html', 88),
('Rgaa22-0610', '', '6.10', 'http://rgaa.net/Absence-d-element-meta-provoquant,89.html', 89),
('Rgaa22-0611', '', '6.11', 'http://rgaa.net/Absence-de-code-javascript,90.html', 90),
('Rgaa22-0612', '', '6.12', 'http://rgaa.net/Absence-d-elements-provoquant-une.html', 91),
('Rgaa22-0613', '', '6.13', 'http://rgaa.net/Possibilite-d-identifier-la.html',  92),
('Rgaa22-0614', '', '6.14', 'http://rgaa.net/Possibilite-d-identifier-la,93.html',  93),
('Rgaa22-0615', '', '6.15', 'http://rgaa.net/Coherence-de-la-destination-ou-de.html', 94),
('Rgaa22-0616', '', '6.16', 'http://rgaa.net/Absence-de-liens-sans-intitule.html',  95),
('Rgaa22-0617', '', '6.17', 'http://rgaa.net/Presence-d-une-page-contenant-le.html', 96),
('Rgaa22-0618', '', '6.18', 'http://rgaa.net/Coherence-du-plan-du-site.html',  97),
('Rgaa22-0619', '', '6.19', 'http://rgaa.net/Presence-d-un-acces-a-la-page.html',  98),
('Rgaa22-0620', '', '6.20', 'http://rgaa.net/Presence-d-un-fil-d-ariane.html',  99),
('Rgaa22-0621', '', '6.21', 'http://rgaa.net/Presence-de-menus-ou-de-barres-de.html',  100),
('Rgaa22-0622', '', '6.22', 'http://rgaa.net/Coherence-de-la-position-des-menus.html',  101),
('Rgaa22-0623', '', '6.23', 'http://rgaa.net/Coherence-de-la-presentation-des.html',  102),
('Rgaa22-0624', '', '6.24', 'http://rgaa.net/Navigation-au-clavier-dans-un.html',  103),
('Rgaa22-0625', '', '6.25', 'http://rgaa.net/Presence-d-un-avertissement,104.html',  104),
('Rgaa22-0626', '', '6.26', 'http://rgaa.net/Presence-des-informations-de.html',  105),
('Rgaa22-0627', '', '6.27', 'http://rgaa.net/Presence-des-informations-de-poids.html',  106),
('Rgaa22-0628', '', '6.28', 'http://rgaa.net/Presence-des-informations-de,107.html',  107),
('Rgaa22-0629', '', '6.29', 'http://rgaa.net/Presence-de-regroupement-pour-les.html',  108),
('Rgaa22-0630', '', '6.30', 'http://rgaa.net/Presence-d-un-balisage-permettant.html',  109),
('Rgaa22-0631', '', '6.31', 'http://rgaa.net/Presence-de-liens-d-evitement-ou-d.html',  110),
('Rgaa22-0632', '', '6.32', 'http://rgaa.net/Coherence-des-liens-d-evitement-ou.html',  111),
('Rgaa22-0633', '', '6.33', 'http://rgaa.net/Ordre-des-liens-d-evitement-ou-d.html',  112),
('Rgaa22-0634', '', '6.34', 'http://rgaa.net/Presence-d-un-moteur-de-recherche.html',  113),
('Rgaa22-0635', '', '6.35', 'http://rgaa.net/Possibilite-de-naviguer-facilement.html',  114),
('Rgaa22-0636', '', '6.36', 'http://rgaa.net/Presence-d-une-indication-de-la.html',  115),
('Rgaa22-0701', '', '7.1', 'http://rgaa.net/Absence-de-generation-de-contenus.html',  116),
('Rgaa22-0702', '', '7.2', 'http://rgaa.net/Absence-d-alteration-de-la.html',  117),
('Rgaa22-0703', '', '7.3', 'http://rgaa.net/Lisibilite-des-informations.html',  118),
('Rgaa22-0704', '', '7.4', 'http://rgaa.net/Absence-d-espaces-utilises-pour.html', 119),
('Rgaa22-0705', '', '7.5', 'http://rgaa.net/Absence-de-definition-d-une.html',  120),
('Rgaa22-0706', '', '7.6', 'http://rgaa.net/Possibilite-de-remplacer-les.html',  121),
('Rgaa22-0707', '', '7.7', 'http://rgaa.net/Possibilite-de-remplacer-les,122.html',  122),
('Rgaa22-0708', '', '7.8', 'http://rgaa.net/Absence-d-attributs-ou-d-elements.html', 123),
('Rgaa22-0709', '', '7.9', 'http://rgaa.net/Absence-d-elements-HTML-utilises-a.html', 124),
('Rgaa22-0710', '', '7.10', 'http://rgaa.net/Maintien-de-la-distinction.html', 125),
('Rgaa22-0711', '', '7.11', 'http://rgaa.net/Absence-de-suppression-de-l-effet.html',  126),
('Rgaa22-0712', '', '7.12', 'http://rgaa.net/Absence-de-justification-du-texte.html',  127),
('Rgaa22-0713', '', '7.13', 'http://rgaa.net/Lisibilite-du-document-en-cas-d.html',  128),
('Rgaa22-0714', '', '7.14', 'http://rgaa.net/Absence-d-unites-absolues-ou-de.html',  129),
('Rgaa22-0715', '', '7.15', 'http://rgaa.net/Absence-d-apparition-de-barre-de.html',  130),
('Rgaa22-0716', '', '7.16', 'http://rgaa.net/Largeur-des-blocs-de-textes.html',  131),
('Rgaa22-0717', '', '7.17', 'http://rgaa.net/Valeur-de-l-espace-entre-les.html',  132),
('Rgaa22-0718', '', '7.18', 'http://rgaa.net/Restitution-correcte-dans-les.html', 133),
('Rgaa22-0801', '', '8.1', 'http://rgaa.net/Mise-a-jour-des-alternatives-aux.html',  134),
('Rgaa22-0802', '', '8.2', 'http://rgaa.net/Universalite-du-gestionnaire-d.html', 135),
('Rgaa22-0803', '', '8.3', 'http://rgaa.net/Universalite-des-gestionnaires-d.html', 136),
('Rgaa22-0804', '', '8.4', 'http://rgaa.net/Possibilite-de-desactiver-toute.html', 137),
('Rgaa22-0805', '', '8.5', 'http://rgaa.net/Absence-de-changements-de-contexte.html', 138),
('Rgaa22-0806', '', '8.6', 'http://rgaa.net/Ordre-d-acces-au-clavier-aux.html', 139),
('Rgaa22-0807', '', '8.7', 'http://rgaa.net/Utilisation-correcte-du-role-des.html', 140),
('Rgaa22-0808', '', '8.8', 'http://rgaa.net/Presence-d-une-alternative-au-code.html', 141),
('Rgaa22-0809', '', '8.9', 'http://rgaa.net/Absence-de-suppression-du-focus.html', 142),
('Rgaa22-0810', '', '8.10', 'http://rgaa.net/Absence-de-limite-de-temps-pour.html', 143),
('Rgaa22-0811', '', '8.11', 'http://rgaa.net/Absence-de-perte-d-informations.html', 144),
('Rgaa22-0812', '', '8.12', 'http://rgaa.net/Presence-d-une-alternative-au-code,145.html', 145),
('Rgaa22-0813', '', '8.13', 'http://rgaa.net/Accessibilite-des-contenus.html', 146),
('Rgaa22-0901', '', '9.1', 'http://rgaa.net/Presence-de-la-declaration-d.html', 147),
('Rgaa22-0902', '', '9.2', 'http://rgaa.net/Conformite-de-la-position-de-la.html', 148),
('Rgaa22-0903', '', '9.3', 'http://rgaa.net/Conformite-syntaxique-de-la.html', 149),
('Rgaa22-0904', '', '9.4', 'http://rgaa.net/Validite-du-code-HTML-XHTML-au.html', 150),
('Rgaa22-0905', '', '9.5', 'http://rgaa.net/Absence-de-composants-obsoletes.html',  151),
('Rgaa22-0906', '', '9.6', 'http://rgaa.net/Presence-d-un-titre-dans-la-page.html', 152),
('Rgaa22-0907', '', '9.7', 'http://rgaa.net/Pertinence-du-titre-de-la-page.html', 153),
('Rgaa22-0908', '', '9.8', 'http://rgaa.net/Presence-d-une-langue-de.html', 154),
('Rgaa22-1001', '', '10.1', 'http://rgaa.net/Presence-d-au-moins-un-titre-de.html', 155),
('Rgaa22-1002', '', '10.2', 'http://rgaa.net/Pertinence-du-contenu-des-titres.html', 156),
('Rgaa22-1003', '', '10.3', 'http://rgaa.net/Absence-d-interruption-dans-la.html', 157),
('Rgaa22-1004', '', '10.4', 'http://rgaa.net/Presence-d-une-hierarchie-de.html', 158),
('Rgaa22-1005', '', '10.5', 'http://rgaa.net/Absence-de-simulation-visuelle-de.html', 159),
('Rgaa22-1006', '', '10.6', 'http://rgaa.net/Utilisation-systematique-de-listes.html', 160),
('Rgaa22-1007', '', '10.7', 'http://rgaa.net/Balisage-correct-des-listes-de.html', 161),
('Rgaa22-1008', '', '10.8', 'http://rgaa.net/Balisage-correct-des-citations.html', 162),
('Rgaa22-1009', '', '10.9', 'http://rgaa.net/Balisage-correct-des-abreviations.html', 163),
('Rgaa22-1010', '', '10.10', 'http://rgaa.net/Balisage-correct-des-acronymes.html', 164),
('Rgaa22-1011', '', '10.11', 'http://rgaa.net/Pertinence-de-la-version-non.html', 165),
('Rgaa22-1012', '', '10.12', 'http://rgaa.net/Pertinence-de-la-version-complete.html', 166),
('Rgaa22-1013', '', '10.13', 'http://rgaa.net/Accessibilite-des-documents.html', 167),
('Rgaa22-1101', '', '11.1', 'http://rgaa.net/Presence-des-balises-th-pour.html', 168),
('Rgaa22-1102', '', '11.2', 'http://rgaa.net/Presence-d-une-relation-entre-les.html', 169),
('Rgaa22-1103', '', '11.3',  'http://rgaa.net/Presence-d-une-relation-entre-les,170.html', 170),
('Rgaa22-1104', '', '11.4', 'http://rgaa.net/Absence-des-elements-propres-aux.html',  171),
('Rgaa22-1105', '', '11.5', 'http://rgaa.net/Absence-de-tableaux-de-donnees-ou.html',  172),
('Rgaa22-1106', '', '11.6', 'http://rgaa.net/Linearisation-correcte-des.html',  173),
('Rgaa22-1107', '', '11.7', 'http://rgaa.net/Presence-d-un-titre-pour-les.html', 174),
('Rgaa22-1108', '', '11.8', 'http://rgaa.net/Presence-d-un-resume-pour-les.html', 175),
('Rgaa22-1109', '', '11.9', 'http://rgaa.net/Pertinence-du-titre-du-tableau-de.html', 176),
('Rgaa22-1110', '', '11.10', 'http://rgaa.net/Pertinence-du-resume-du-tableau-de.html', 177),
('Rgaa22-1201', '', '12.1', 'http://rgaa.net/Presence-de-l-indication-des.html', 178),
('Rgaa22-1202', '', '12.2', 'http://rgaa.net/Presence-de-l-indication-des,179.html', 179),
('Rgaa22-1203', '', '12.3', 'http://rgaa.net/Equivalence-de-l-information-mise.html', 180),
('Rgaa22-1204', '', '12.4', 'http://rgaa.net/Presence-de-liens-ou-de.html', 181),
('Rgaa22-1205', '', '12.5', 'http://rgaa.net/Absence-de-syntaxes-cryptiques-par.html',  182),
('Rgaa22-1206', '', '12.6', 'http://rgaa.net/Presence-d-informations-sur-les.html',  183),
('Rgaa22-1207', '', '12.7', 'http://rgaa.net/Presence-d-un-moyen-de,184.html', 184),
('Rgaa22-1208', '', '12.8', 'http://rgaa.net/Presence-d-un-autre-moyen-que-la,185.html', 185),
('Rgaa22-1209', '', '12.9', 'http://rgaa.net/Presence-d-un-autre-moyen-que-la,186.html', 186),
('Rgaa22-1210', '', '12.10', 'http://rgaa.net/Utilisation-d-un-style-de.html', 187);

UPDATE `CRITERION` SET `Reference_Id_Reference` = (SELECT `Id_Reference` FROM `REFERENCE` WHERE `Cd_Reference` LIKE 'Rgaa22') WHERE `Cd_Criterion` LIKE 'Rgaa22-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa22-1') WHERE `Cd_Criterion` LIKE 'Rgaa22-01%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa22-2') WHERE `Cd_Criterion` LIKE 'Rgaa22-02%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa22-3') WHERE `Cd_Criterion` LIKE 'Rgaa22-03%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa22-4') WHERE `Cd_Criterion` LIKE 'Rgaa22-04%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa22-5') WHERE `Cd_Criterion` LIKE 'Rgaa22-05%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa22-6') WHERE `Cd_Criterion` LIKE 'Rgaa22-06%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa22-7') WHERE `Cd_Criterion` LIKE 'Rgaa22-07%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa22-8') WHERE `Cd_Criterion` LIKE 'Rgaa22-08%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa22-9') WHERE `Cd_Criterion` LIKE 'Rgaa22-09%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa22-10') WHERE `Cd_Criterion` LIKE 'Rgaa22-10%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa22-11') WHERE `Cd_Criterion` LIKE 'Rgaa22-11%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa22-12') WHERE `Cd_Criterion` LIKE 'Rgaa22-12%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa22-13') WHERE `Cd_Criterion` LIKE 'Rgaa22-13%';

--
-- Contenu de la table `test`
--
INSERT IGNORE INTO `TEST` (`Cd_Test`, `Description`, `Label`, `Rank`, `Weight`, `Rule_Archive_Name`, `Rule_Class_Name`, `Id_Decision_Level`, `Id_Level`, `Id_Scope`, `Rule_Design_Url`,`No_Process`) VALUES
('Rgaa22-01011', 'http://rgaa.net/1-1-Absence-de-cadres-non-titres.html', '1.1', 1, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule01011', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-1-1',b'1'),
('Rgaa22-01021', 'http://rgaa.net/Pertinence-des-titres-donnes-aux.html', '1.2', 2, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule01021', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-1-2',b'1'),
('Rgaa22-02011', 'http://rgaa.net/Presence-d-un-autre-moyen-que-la.html', '2.1', 3, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule02011', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-2-1',b'1'),
('Rgaa22-02021', 'http://rgaa.net/Presence-d-un-autre-moyen-que-la,4.html', '2.2', 4, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule02021', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-2-2',b'1'),
('Rgaa22-02031', 'http://rgaa.net/Presence-d-un-moyen-de.html', '2.3', 5, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule02031', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-2-3',b'1'),
('Rgaa22-02041', 'http://rgaa.net/Presence-d-un-moyen-de,6.html', '2.4', 6, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule02041', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-2-4',b'1'),
('Rgaa22-02051', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du.html', '2.5', 7, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule02051', NULL, 2, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-2-5',b'1'),
('Rgaa22-02061', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,8.html', '2.6', 8, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule02061', NULL, 2, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-2-6',b'1'),
('Rgaa22-02071', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,9.html', '2.7', 9, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule02071', NULL, 2, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-2-7',b'1'),
('Rgaa22-02081', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,10.html', '2.8', 10, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule02081', NULL, 2, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-2-8',b'1'),
('Rgaa22-02091', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,11.html', '2.9', 11, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule02091', NULL, 2, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-2-9',b'1'),
('Rgaa22-02101', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,12.html', '2.10', 12, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule02101', NULL, 2, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-2-10',b'1'),
('Rgaa22-02111', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,13.html', '2.11', 13, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule02111', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-2-11',b'1'),
('Rgaa22-02121', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,14.html', '2.12', 14, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule02121', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-2-12',b'1'),
('Rgaa22-02131', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,15.html', '2.13', 15, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule02131', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-2-13',b'1'),
('Rgaa22-02141', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,16.html', '2.14', 16, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule02141', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-2-14',b'1'),
('Rgaa22-02151', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,17.html', '2.15', 17, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule02151', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-2-15',b'1'),
('Rgaa22-02161', 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,18.html', '2.16', 18, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule02161', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-2-16',b'1'),
('Rgaa22-03011', 'http://rgaa.net/Possibilite-d-identifier-les.html', '3.1', 19, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule03011', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-3-1',b'1'),
('Rgaa22-03021', 'http://rgaa.net/Presence-d-information-prealable.html', '3.2', 20, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule03021', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-3-2',b'1'),
('Rgaa22-03031', 'http://rgaa.net/Positionnement-correct-des.html', '3.3', 21, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule03031', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-3-3',b'1'),
('Rgaa22-03041', 'http://rgaa.net/Regroupement-d-elements-de.html', '3.4', 22, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule03041', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-3-4',b'1'),
('Rgaa22-03051', 'http://rgaa.net/Absence-d-element-fieldset-sans.html', '3.5', 23, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule03051', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-3-5',b'1'),
('Rgaa22-03061', 'http://rgaa.net/Pertinence-du-contenu-de-l-element.html', '3.6', 24, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule03061', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-3-6',b'1'),
('Rgaa22-03071', 'http://rgaa.net/Regroupement-d-elements-option.html', '3.7', 25, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule03071', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-3-7',b'1'),
('Rgaa22-03081', 'http://rgaa.net/Presence-d-un-attribut-label-sur-l.html', '3.8', 26, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule03081', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-3-8',b'1'),
('Rgaa22-03091', 'http://rgaa.net/Pertinence-du-contenu-de-l.html', '3.9', 27, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule03091', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-3-9',b'1'),
('Rgaa22-03101', 'http://rgaa.net/Absence-d-element-de-formulaire.html', '3.10', 28, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule03101', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-3-10',b'1'),
('Rgaa22-03111', 'http://rgaa.net/Absence-d-element-de-formulaire,29.html', '3.11', 29, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule03121', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-3-11',b'1'),
('Rgaa22-03121', 'http://rgaa.net/Pertinence-des-etiquettes-d.html', '3.12', 30, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule03121', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-3-12',b'1'),
('Rgaa22-03131', 'http://rgaa.net/Presence-d-informations-ou-de.html', '3.13', 31, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule03131', NULL, 2, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-3-13',b'1'),
('Rgaa22-03141', 'http://rgaa.net/Presence-de-mecanismes-permettant.html', '3.14', 32, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule03141', NULL, 2, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-3-14',b'1'),
('Rgaa22-03151', 'http://rgaa.net/Presence-de-mecanismes-permettant,33.html', '3.15', 33, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule03151', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-3-15',b'1'),
('Rgaa22-03161', 'http://rgaa.net/Presence-d-une-page-d-aide-ou-d-un.html', '3.16', 34, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule03161', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-3-16',b'1'),
('Rgaa22-04011', 'http://rgaa.net/Presence-de-l-attribut-alt.html', '4.1', 35, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule04011', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-4-1',b'1'),
('Rgaa22-04021', 'http://rgaa.net/Pertinence-de-l-alternative.html', '4.2', 36, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule04021', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-4-2',b'1'),
('Rgaa22-04031', 'http://rgaa.net/Pertinence-de-l-alternative,37.html', '4.3', 37, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule04031', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-4-3',b'1'),
('Rgaa22-04041', 'http://rgaa.net/Pertinence-de-l-alternative,38.html', '4.4', 38, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule04041', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-4-4',b'1'),
('Rgaa22-04051', 'http://rgaa.net/Pertinence-de-l-alternative-vide.html', '4.5', 39, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule04051', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-4-5',b'1'),
('Rgaa22-04061', 'http://rgaa.net/Longueur-du-contenu-des.html', '4.6', 40, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule04061', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-4-6',b'1'),
('Rgaa22-04071', 'http://rgaa.net/Existence-d-une-description-longue.html', '4.7', 41, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule04071', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-4-7',b'1'),
('Rgaa22-04081', 'http://rgaa.net/Pertinence-de-la-description.html', '4.8', 42, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule04081', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-4-8',b'1'),
('Rgaa22-04091', 'http://rgaa.net/Presence-de-l-attribut-longdesc.html', '4.9', 43, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule04091', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-4-9',b'1'),
('Rgaa22-04101', 'http://rgaa.net/Presence-d-une-information-de.html', '4.10', 44, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule04101', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-4-10',b'1'),
('Rgaa22-04111', 'http://rgaa.net/Coherence-dans-l-identification.html', '4.11', 45, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule04111', NULL, 2, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-4-11',b'1'),
('Rgaa22-05011', 'http://rgaa.net/Acces-a-une-information.html', '5.1', 46, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05011', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-1',b'1'),
('Rgaa22-05021', 'http://rgaa.net/Presence-de-la-transcription.html', '5.2', 47, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05021', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-2',b'1'),
('Rgaa22-05031', 'http://rgaa.net/Pertinence-de-la-transcription.html', '5.3', 48, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05031', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-3',b'1'),
('Rgaa22-05041', 'http://rgaa.net/Presence-d-une-description-audio.html', '5.4', 49, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05041', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-4',b'1'),
('Rgaa22-05051', 'http://rgaa.net/Pertinence-de-la-description-audio.html', '5.5', 50, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05051', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-5',b'1'),
('Rgaa22-05061', 'http://rgaa.net/Possibilite-de-controler-l.html', '5.6', 51, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05061', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-6',b'1'),
('Rgaa22-05071', 'http://rgaa.net/Presence-d-une-description-audio,52.html', '5.7', 52, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05071', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-7',b'1'),
('Rgaa22-05081', 'http://rgaa.net/Presence-d-une-description-audio,53.html', '5.8', 53, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05081', NULL, 2, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-8',b'1'),
('Rgaa22-05091', 'http://rgaa.net/Presence-du-sous-titrage.html', '5.9', 54, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05091', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-9',b'1'),
('Rgaa22-05101', 'http://rgaa.net/Pertinence-du-sous-titrage.html', '5.10', 55, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05101', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-10',b'1'),
('Rgaa22-05111', 'http://rgaa.net/Presence-d-une-alternative-aux.html', '5.11', 56, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05111', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-11',b'1'),
('Rgaa22-05121', 'http://rgaa.net/Presence-d-une-alternative-aux,57.html', '5.12', 57, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05121', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-12',b'1'),
('Rgaa22-05131', 'http://rgaa.net/Absence-d-elements-provoquant-des.html', '5.13', 58, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05131', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-13',b'1'),
('Rgaa22-05141', 'http://rgaa.net/Absence-de-code-javascript,59.html', '5.14', 59, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05141', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-14',b'1'),
('Rgaa22-05151', 'http://rgaa.net/Absence-de-mise-en-forme.html', '5.15', 60, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05151', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-15',b'1'),
('Rgaa22-05161', 'http://rgaa.net/Compatibilite-des-elements.html', '5.16', 61, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05161', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-16',b'1'),
('Rgaa22-05171', 'http://rgaa.net/Absence-totale-de-changements.html', '5.17', 62, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05171', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-17',b'1'),
('Rgaa22-05181', 'http://rgaa.net/Presence-du-sous-titrage,63.html', '5.18', 63, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05181', NULL, 2, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-18',b'1'),
('Rgaa22-05191', 'http://rgaa.net/Absence-de-l-element-blink.html', '5.19', 64, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05191', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-19',b'1'),
('Rgaa22-05201', 'http://rgaa.net/Absence-d-elements-provoquant-des,65.html', '5.20', 65, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05201', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-20',b'1'),
('Rgaa22-05211', 'http://rgaa.net/Absence-de-code-javascript,66.html', '5.21', 66, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05211', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-21',b'1'),
('Rgaa22-05221', 'http://rgaa.net/Absence-de-mise-en-forme,67.html', '5.22', 67, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05221', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-22',b'1'),
('Rgaa22-05231', 'http://rgaa.net/Absence-d-element-marquee.html', '5.23', 68, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05231', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-23',b'1'),
('Rgaa22-05241', 'http://rgaa.net/Absence-d-elements-affichant-des.html', '5.24', 69, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05241', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-24',b'1'),
('Rgaa22-05251', 'http://rgaa.net/Absence-de-code-javascript,70.html', '5.25', 70, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05251', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-25',b'1'),
('Rgaa22-05261', 'http://rgaa.net/Absence-de-mise-en-forme,71.html', '5.26', 71, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05261', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-26',b'1'),
('Rgaa22-05271', 'http://rgaa.net/Independance-du-peripherique-d.html', '5.27', 72, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05271', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-27',b'1'),
('Rgaa22-05281', 'http://rgaa.net/Presence-d-une-alternative-aux,73.html', '5.28', 73, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05281', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-28',b'1'),
('Rgaa22-05291', 'http://rgaa.net/Absence-d-elements-declenchant-la.html', '5.29', 74, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05291', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-29',b'1'),
('Rgaa22-05301', 'http://rgaa.net/Absence-d-element-bgsound.html', '5.30', 75, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05301', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-30',b'1'),
('Rgaa22-05311', 'http://rgaa.net/Presence-de-version-en-langue-des.html', '5.31', 76, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05311', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-31',b'1'),
('Rgaa22-05321', 'http://rgaa.net/Pertinence-de-la-version-en-langue.html', '5.32', 77, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05321', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-32',b'1'),
('Rgaa22-05331', 'http://rgaa.net/Niveau-sonore-de-la-piste-de.html', '5.33', 78, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05331', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-33',b'1'),
('Rgaa22-05341', 'http://rgaa.net/Presence-d-un-mecanisme-pour.html', '5.34', 79, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule05341', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-5-34',b'1'),
('Rgaa22-06011', 'http://rgaa.net/Acces-aux-liens-textuels-doublant.html', '6.1', 80, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06011', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-1',b'1'),
('Rgaa22-06021', 'http://rgaa.net/Presence-d-un-avertissement.html', '6.2', 81, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06021', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-2',b'1'),
('Rgaa22-06031', 'http://rgaa.net/Presence-d-un-avertissement,82.html', '6.3', 82, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06031', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-3',b'1'),
('Rgaa22-06041', 'http://rgaa.net/Presence-d-un-avertissement,83.html', '6.4', 83, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06041', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-4',b'1'),
('Rgaa22-06051', 'http://rgaa.net/Absence-d-ouverture-de-nouvelles.html', '6.5', 84, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06051', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-5',b'1'),
('Rgaa22-06061', 'http://rgaa.net/Absence-de-piege-lors-de-la.html', '6.6', 85, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06061', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-6',b'1'),
('Rgaa22-06071', 'http://rgaa.net/Absence-d-element-meta-provoquant.html', '6.7', 86, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06071', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-7',b'1'),
('Rgaa22-06081', 'http://rgaa.net/Absence-de-code-javascript.html', '6.8', 87, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06081', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-8',b'1'),
('Rgaa22-06091', 'http://rgaa.net/Absence-d-elements-provoquant-un.html', '6.9', 88, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06091', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-9',b'1'),
('Rgaa22-06101', 'http://rgaa.net/Absence-d-element-meta-provoquant,89.html', '6.10', 89, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06101', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-10',b'1'),
('Rgaa22-06111', 'http://rgaa.net/Absence-de-code-javascript,90.html', '6.11', 90, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06111', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-11',b'1'),
('Rgaa22-06121', 'http://rgaa.net/Absence-d-elements-provoquant-une.html', '6.12', 91, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06121', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-12',b'1'),
('Rgaa22-06131', 'http://rgaa.net/Possibilite-d-identifier-la.html', '6.13', 92, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06131', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-13',b'1'),
('Rgaa22-06141', 'http://rgaa.net/Possibilite-d-identifier-la,93.html', '6.14', 93, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06141', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-14',b'1'),
('Rgaa22-06151', 'http://rgaa.net/Coherence-de-la-destination-ou-de.html', '6.15', 94, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06151', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-15',b'1'),
('Rgaa22-06161', 'http://rgaa.net/Absence-de-liens-sans-intitule.html', '6.16', 95, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06161', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-16',b'1'),
('Rgaa22-06171', 'http://rgaa.net/Presence-d-une-page-contenant-le.html', '6.17', 96, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06171', NULL, 2, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-17',b'1'),
('Rgaa22-06181', 'http://rgaa.net/Coherence-du-plan-du-site.html', '6.18', 97, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06181', NULL, 2, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-18',b'1'),
('Rgaa22-06191', 'http://rgaa.net/Presence-d-un-acces-a-la-page.html', '6.19', 98, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06191', NULL, 2, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-19',b'1'),
('Rgaa22-06201', 'http://rgaa.net/Presence-d-un-fil-d-ariane.html', '6.20', 99, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06201', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-20',b'1'),
('Rgaa22-06211', 'http://rgaa.net/Presence-de-menus-ou-de-barres-de.html', '6.21', 100, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06211', NULL, 2, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-21',b'1'),
('Rgaa22-06221', 'http://rgaa.net/Coherence-de-la-position-des-menus.html', '6.22', 101, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06221', NULL, 2, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-22',b'1'),
('Rgaa22-06231', 'http://rgaa.net/Coherence-de-la-presentation-des.html', '6.23', 102, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06231', NULL, 2, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-23',b'1'),
('Rgaa22-06241', 'http://rgaa.net/Navigation-au-clavier-dans-un.html', '6.24', 103, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06241', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-24',b'1'),
('Rgaa22-06251', 'http://rgaa.net/Presence-d-un-avertissement,104.html', '6.25', 104, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06251', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-25',b'1'),
('Rgaa22-06261', 'http://rgaa.net/Presence-des-informations-de.html', '6.26', 105, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06261', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-26',b'1'),
('Rgaa22-06271', 'http://rgaa.net/Presence-des-informations-de-poids.html', '6.27', 106, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06271', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-27',b'1'),
('Rgaa22-06281', 'http://rgaa.net/Presence-des-informations-de,107.html', '6.28', 107, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06281', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-28',b'1'),
('Rgaa22-06291', 'http://rgaa.net/Presence-de-regroupement-pour-les.html', '6.29', 108, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06291', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-29',b'1'),
('Rgaa22-06301', 'http://rgaa.net/Presence-d-un-balisage-permettant.html', '6.30', 109, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06301', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-30',b'1'),
('Rgaa22-06311', 'http://rgaa.net/Presence-de-liens-d-evitement-ou-d.html', '6.31', 110, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06311', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-31',b'1'),
('Rgaa22-06321', 'http://rgaa.net/Coherence-des-liens-d-evitement-ou.html', '6.32', 111, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06321', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-32',b'1'),
('Rgaa22-06331', 'http://rgaa.net/Ordre-des-liens-d-evitement-ou-d.html', '6.33', 112, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06331', NULL, 2, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-33',b'1'),
('Rgaa22-06341', 'http://rgaa.net/Presence-d-un-moteur-de-recherche.html', '6.34', 113, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06341', NULL, 2, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-34',b'1'),
('Rgaa22-06351', 'http://rgaa.net/Possibilite-de-naviguer-facilement.html', '6.35', 114, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06351', NULL, 2, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-35',b'1'),
('Rgaa22-06361', 'http://rgaa.net/Presence-d-une-indication-de-la.html', '6.36', 115, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule06361', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-6-36',b'1'),
('Rgaa22-07011', 'http://rgaa.net/Absence-de-generation-de-contenus.html', '7.1', 116, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule07011', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-7-1',b'1'),
('Rgaa22-07021', 'http://rgaa.net/Absence-d-alteration-de-la.html', '7.2', 117, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule07021', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-7-2',b'1'),
('Rgaa22-07031', 'http://rgaa.net/Lisibilite-des-informations.html', '7.3', 118, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule07031', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-7-3',b'1'),
('Rgaa22-07041', 'http://rgaa.net/Absence-d-espaces-utilises-pour.html', '7.4', 119, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule07041', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-7-4',b'1'),
('Rgaa22-07051', 'http://rgaa.net/Absence-de-definition-d-une.html', '7.5', 120, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule07051', NULL, 2, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-7-5',b'1'),
('Rgaa22-07061', 'http://rgaa.net/Possibilite-de-remplacer-les.html', '7.6', 121, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule07061', NULL, 2, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-7-6',b'1'),
('Rgaa22-07071', 'http://rgaa.net/Possibilite-de-remplacer-les,122.html', '7.7', 122, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule07071', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-7-7',b'1'),
('Rgaa22-07081', 'http://rgaa.net/Absence-d-attributs-ou-d-elements.html', '7.8', 123, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule07081', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-7-8',b'1'),
('Rgaa22-07091', 'http://rgaa.net/Absence-d-elements-HTML-utilises-a.html', '7.9', 124, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule07091', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-7-9',b'1'),
('Rgaa22-07101', 'http://rgaa.net/Maintien-de-la-distinction.html', '7.10', 125, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule07101', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-7-10',b'1'),
('Rgaa22-07111', 'http://rgaa.net/Absence-de-suppression-de-l-effet.html', '7.11', 126, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule07111', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-7-11',b'1'),
('Rgaa22-07121', 'http://rgaa.net/Absence-de-justification-du-texte.html', '7.12', 127, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule07121', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-7-12',b'1'),
('Rgaa22-07131', 'http://rgaa.net/Lisibilite-du-document-en-cas-d.html', '7.13', 128, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule07131', NULL, 2, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-7-13',b'1'),
('Rgaa22-07141', 'http://rgaa.net/Absence-d-unites-absolues-ou-de.html', '7.14', 129, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule07141', NULL, 2, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-7-14',b'1'),
('Rgaa22-07151', 'http://rgaa.net/Absence-d-apparition-de-barre-de.html', '7.15', 130, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule07151', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-7-15',b'1'),
('Rgaa22-07161', 'http://rgaa.net/Largeur-des-blocs-de-textes.html', '7.16', 131, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule07161', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-7-16',b'1'),
('Rgaa22-07171', 'http://rgaa.net/Valeur-de-l-espace-entre-les.html', '7.17', 132, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule07171', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-7-17',b'1'),
('Rgaa22-07181', 'http://rgaa.net/Restitution-correcte-dans-les.html', '7.18', 133, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule07181', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-7-18',b'1'),
('Rgaa22-08011', 'http://rgaa.net/Mise-a-jour-des-alternatives-aux.html', '8.1', 134, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule08011', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-8-1',b'1'),
('Rgaa22-08021', 'http://rgaa.net/Universalite-du-gestionnaire-d.html', '8.2', 135, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule08021', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-8-2',b'1'),
('Rgaa22-08031', 'http://rgaa.net/Universalite-des-gestionnaires-d.html', '8.3', 136, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule08031', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-8-3',b'1'),
('Rgaa22-08041', 'http://rgaa.net/Possibilite-de-desactiver-toute.html', '8.4', 137, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule08041', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-8-4',b'1'),
('Rgaa22-08051', 'http://rgaa.net/Absence-de-changements-de-contexte.html', '8.5', 138, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule08051', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-8-5',b'1'),
('Rgaa22-08061', 'http://rgaa.net/Ordre-d-acces-au-clavier-aux.html', '8.6', 139, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule08061', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-8-6',b'1'),
('Rgaa22-08071', 'http://rgaa.net/Utilisation-correcte-du-role-des.html', '8.7', 140, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule08071', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-8-7',b'1'),
('Rgaa22-08081', 'http://rgaa.net/Presence-d-une-alternative-au-code.html', '8.8', 141, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule08081', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-8-8',b'1'),
('Rgaa22-08091', 'http://rgaa.net/Absence-de-suppression-du-focus.html', '8.9', 142, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule08091', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-8-9',b'1'),
('Rgaa22-08101', 'http://rgaa.net/Absence-de-limite-de-temps-pour.html', '8.10', 143, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule08101', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-8-10',b'1'),
('Rgaa22-08111', 'http://rgaa.net/Absence-de-perte-d-informations.html', '8.11', 144, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule08111', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-8-11',b'1'),
('Rgaa22-08121', 'http://rgaa.net/Presence-d-une-alternative-au-code,145.html', '8.12', 145, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule08121', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-8-12',b'1'),
('Rgaa22-08131', 'http://rgaa.net/Accessibilite-des-contenus.html', '8.13', 146, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule08131', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-8-13',b'1'),
('Rgaa22-09011', 'http://rgaa.net/Presence-de-la-declaration-d.html', '9.1', 147, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule09011', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-9-1',b'1'),
('Rgaa22-09021', 'http://rgaa.net/Conformite-de-la-position-de-la.html', '9.2', 148, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule09021', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-9-2',b'1'),
('Rgaa22-09031', 'http://rgaa.net/Conformite-syntaxique-de-la.html', '9.3', 149, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule09031', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-9-3',b'1'),
('Rgaa22-09041', 'http://rgaa.net/Validite-du-code-HTML-XHTML-au.html', '9.4', 150, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule09041', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-9-4',b'1'),
('Rgaa22-09051', 'http://rgaa.net/Absence-de-composants-obsoletes.html', '9.5', 151, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule09051', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-9-5',b'1'),
('Rgaa22-09061', 'http://rgaa.net/Presence-d-un-titre-dans-la-page.html', '9.6', 152, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule09061', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-9-6',b'1'),
('Rgaa22-09071', 'http://rgaa.net/Pertinence-du-titre-de-la-page.html', '9.7', 153, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule09071', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-9-7',b'1'),
('Rgaa22-09081', 'http://rgaa.net/Presence-d-une-langue-de.html', '9.8', 154, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule09081', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-9-8',b'1'),
('Rgaa22-10011', 'http://rgaa.net/Presence-d-au-moins-un-titre-de.html', '10.1', 155, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule10011', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-10-1',b'1'),
('Rgaa22-10021', 'http://rgaa.net/Pertinence-du-contenu-des-titres.html', '10.2', 156, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule10021', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-10-2',b'1'),
('Rgaa22-10031', 'http://rgaa.net/Absence-d-interruption-dans-la.html', '10.3', 157, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule10031', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-10-3',b'1'),
('Rgaa22-10041', 'http://rgaa.net/Presence-d-une-hierarchie-de.html', '10.4', 158, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule10041', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-10-4',b'1'),
('Rgaa22-10051', 'http://rgaa.net/Absence-de-simulation-visuelle-de.html', '10.5', 159, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule10051', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-10-5',b'1'),
('Rgaa22-10061', 'http://rgaa.net/Utilisation-systematique-de-listes.html', '10.6', 160, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule10061', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-10-6',b'1'),
('Rgaa22-10071', 'http://rgaa.net/Balisage-correct-des-listes-de.html', '10.7', 161, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule10071', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-10-7',b'1'),
('Rgaa22-10081', 'http://rgaa.net/Balisage-correct-des-citations.html', '10.8', 162, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule10081', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-10-8',b'1'),
('Rgaa22-10091', 'http://rgaa.net/Balisage-correct-des-abreviations.html', '10.9', 163, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule10091', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-10-9',b'1'),
('Rgaa22-10101', 'http://rgaa.net/Balisage-correct-des-acronymes.html', '10.10', 164, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule10101', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-10-10',b'1'),
('Rgaa22-10111', 'http://rgaa.net/Pertinence-de-la-version-non.html', '10.11', 165, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule10111', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-10-11',b'1'),
('Rgaa22-10121', 'http://rgaa.net/Pertinence-de-la-version-complete.html', '10.12', 166, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule10121', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-10-12',b'1'),
('Rgaa22-10131', 'http://rgaa.net/Accessibilite-des-documents.html', '10.13', 167, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule10131', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-10-13',b'1'),
('Rgaa22-11011', 'http://rgaa.net/Presence-des-balises-th-pour.html', '11.1', 168, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule11011', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-11-1',b'1'),
('Rgaa22-11021', 'http://rgaa.net/Presence-d-une-relation-entre-les.html', '11.2', 169, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule11021', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-11-2',b'1'),
('Rgaa22-11031', 'http://rgaa.net/Presence-d-une-relation-entre-les,170.html', '11.3', 170, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule11031', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-11-3',b'1'),
('Rgaa22-11041', 'http://rgaa.net/Absence-des-elements-propres-aux.html', '11.4', 171, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule11041', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-11-4',b'1'),
('Rgaa22-11051', 'http://rgaa.net/Absence-de-tableaux-de-donnees-ou.html', '11.5', 172, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule11051', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-11-5',b'1'),
('Rgaa22-11061', 'http://rgaa.net/Linearisation-correcte-des.html', '11.6', 173, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule11061', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-11-6',b'1'),
('Rgaa22-11071', 'http://rgaa.net/Presence-d-un-titre-pour-les.html', '11.7', 174, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule11071', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-11-7',b'1'),
('Rgaa22-11081', 'http://rgaa.net/Presence-d-un-resume-pour-les.html', '11.8', 175, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule11081', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-11-8',b'1'),
('Rgaa22-11091', 'http://rgaa.net/Pertinence-du-titre-du-tableau-de.html', '11.9', 176, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule11091', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-11-9',b'1'),
('Rgaa22-11101', 'http://rgaa.net/Pertinence-du-resume-du-tableau-de.html', '11.10', 177, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule11101', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-11-10',b'1'),
('Rgaa22-12011', 'http://rgaa.net/Presence-de-l-indication-des.html', '12.1', 178, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule12011', NULL, 2, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-12-1',b'1'),
('Rgaa22-12021', 'http://rgaa.net/Presence-de-l-indication-des,179.html', '12.2', 179, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule12021', NULL, 2, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-12-2',b'1'),
('Rgaa22-12031', 'http://rgaa.net/Equivalence-de-l-information-mise.html', '12.3', 180, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule12031', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-12-3',b'1'),
('Rgaa22-12041', 'http://rgaa.net/Presence-de-liens-ou-de.html', '12.4', 181, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule12041', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-12-4',b'1'),
('Rgaa22-12051', 'http://rgaa.net/Absence-de-syntaxes-cryptiques-par.html', '12.5', 182, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule12051', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-12-5',b'1'),
('Rgaa22-12061', 'http://rgaa.net/Presence-d-informations-sur-les.html', '12.6', 183, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule12061', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-12-6',b'1'),
('Rgaa22-12071', 'http://rgaa.net/Presence-d-un-moyen-de,184.html', '12.7', 184, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule12071', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-12-7',b'1'),
('Rgaa22-12081', 'http://rgaa.net/Presence-d-un-autre-moyen-que-la,185.html', '12.8', 185, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule12081', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-12-8',b'1'),
('Rgaa22-12091', 'http://rgaa.net/Presence-d-un-autre-moyen-que-la,186.html', '12.9', 186, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule12091', NULL, 1, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-12-9',b'1'),
('Rgaa22-12101', 'http://rgaa.net/Utilisation-d-un-style-de.html', '12.10', 187, '1.0', 'rgaa2.2', 'org.asqatasun.rules.rgaa22.Rgaa22Rule12101', NULL, 3, 1, 'http://www.old-dot-org.org/fr/content/rgaa22-rule-12-10', b'1');

UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0101') WHERE `Cd_Test` LIKE 'Rgaa22-0101%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0102') WHERE `Cd_Test` LIKE 'Rgaa22-0102%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0201') WHERE `Cd_Test` LIKE 'Rgaa22-0201%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0202') WHERE `Cd_Test` LIKE 'Rgaa22-0202%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0203') WHERE `Cd_Test` LIKE 'Rgaa22-0203%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0204') WHERE `Cd_Test` LIKE 'Rgaa22-0204%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0205') WHERE `Cd_Test` LIKE 'Rgaa22-0205%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0206') WHERE `Cd_Test` LIKE 'Rgaa22-0206%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0207') WHERE `Cd_Test` LIKE 'Rgaa22-0207%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0208') WHERE `Cd_Test` LIKE 'Rgaa22-0208%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0209') WHERE `Cd_Test` LIKE 'Rgaa22-0209%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0210') WHERE `Cd_Test` LIKE 'Rgaa22-0210%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0211') WHERE `Cd_Test` LIKE 'Rgaa22-0211%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0212') WHERE `Cd_Test` LIKE 'Rgaa22-0212%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0213') WHERE `Cd_Test` LIKE 'Rgaa22-0213%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0214') WHERE `Cd_Test` LIKE 'Rgaa22-0214%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0215') WHERE `Cd_Test` LIKE 'Rgaa22-0215%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0216') WHERE `Cd_Test` LIKE 'Rgaa22-0216%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0301') WHERE `Cd_Test` LIKE 'Rgaa22-0301%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0302') WHERE `Cd_Test` LIKE 'Rgaa22-0302%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0303') WHERE `Cd_Test` LIKE 'Rgaa22-0303%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0304') WHERE `Cd_Test` LIKE 'Rgaa22-0304%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0305') WHERE `Cd_Test` LIKE 'Rgaa22-0305%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0306') WHERE `Cd_Test` LIKE 'Rgaa22-0306%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0307') WHERE `Cd_Test` LIKE 'Rgaa22-0307%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0308') WHERE `Cd_Test` LIKE 'Rgaa22-0308%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0309') WHERE `Cd_Test` LIKE 'Rgaa22-0309%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0310') WHERE `Cd_Test` LIKE 'Rgaa22-0310%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0311') WHERE `Cd_Test` LIKE 'Rgaa22-0311%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0312') WHERE `Cd_Test` LIKE 'Rgaa22-0312%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0313') WHERE `Cd_Test` LIKE 'Rgaa22-0313%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0314') WHERE `Cd_Test` LIKE 'Rgaa22-0314%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0315') WHERE `Cd_Test` LIKE 'Rgaa22-0315%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0316') WHERE `Cd_Test` LIKE 'Rgaa22-0316%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0401') WHERE `Cd_Test` LIKE 'Rgaa22-0401%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0402') WHERE `Cd_Test` LIKE 'Rgaa22-0402%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0403') WHERE `Cd_Test` LIKE 'Rgaa22-0403%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0404') WHERE `Cd_Test` LIKE 'Rgaa22-0404%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0405') WHERE `Cd_Test` LIKE 'Rgaa22-0405%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0406') WHERE `Cd_Test` LIKE 'Rgaa22-0406%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0407') WHERE `Cd_Test` LIKE 'Rgaa22-0407%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0408') WHERE `Cd_Test` LIKE 'Rgaa22-0408%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0409') WHERE `Cd_Test` LIKE 'Rgaa22-0409%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0410') WHERE `Cd_Test` LIKE 'Rgaa22-0410%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0411') WHERE `Cd_Test` LIKE 'Rgaa22-0411%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0501') WHERE `Cd_Test` LIKE 'Rgaa22-0501%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0502') WHERE `Cd_Test` LIKE 'Rgaa22-0502%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0503') WHERE `Cd_Test` LIKE 'Rgaa22-0503%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0504') WHERE `Cd_Test` LIKE 'Rgaa22-0504%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0505') WHERE `Cd_Test` LIKE 'Rgaa22-0505%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0506') WHERE `Cd_Test` LIKE 'Rgaa22-0506%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0507') WHERE `Cd_Test` LIKE 'Rgaa22-0507%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0508') WHERE `Cd_Test` LIKE 'Rgaa22-0508%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0509') WHERE `Cd_Test` LIKE 'Rgaa22-0509%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0510') WHERE `Cd_Test` LIKE 'Rgaa22-0510%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0511') WHERE `Cd_Test` LIKE 'Rgaa22-0511%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0512') WHERE `Cd_Test` LIKE 'Rgaa22-0512%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0513') WHERE `Cd_Test` LIKE 'Rgaa22-0513%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0514') WHERE `Cd_Test` LIKE 'Rgaa22-0514%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0515') WHERE `Cd_Test` LIKE 'Rgaa22-0515%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0516') WHERE `Cd_Test` LIKE 'Rgaa22-0516%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0517') WHERE `Cd_Test` LIKE 'Rgaa22-0517%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0518') WHERE `Cd_Test` LIKE 'Rgaa22-0518%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0519') WHERE `Cd_Test` LIKE 'Rgaa22-0519%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0520') WHERE `Cd_Test` LIKE 'Rgaa22-0520%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0521') WHERE `Cd_Test` LIKE 'Rgaa22-0521%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0522') WHERE `Cd_Test` LIKE 'Rgaa22-0522%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0523') WHERE `Cd_Test` LIKE 'Rgaa22-0523%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0524') WHERE `Cd_Test` LIKE 'Rgaa22-0524%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0525') WHERE `Cd_Test` LIKE 'Rgaa22-0525%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0526') WHERE `Cd_Test` LIKE 'Rgaa22-0526%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0527') WHERE `Cd_Test` LIKE 'Rgaa22-0527%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0528') WHERE `Cd_Test` LIKE 'Rgaa22-0528%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0529') WHERE `Cd_Test` LIKE 'Rgaa22-0529%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0530') WHERE `Cd_Test` LIKE 'Rgaa22-0530%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0531') WHERE `Cd_Test` LIKE 'Rgaa22-0531%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0532') WHERE `Cd_Test` LIKE 'Rgaa22-0532%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0533') WHERE `Cd_Test` LIKE 'Rgaa22-0533%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0534') WHERE `Cd_Test` LIKE 'Rgaa22-0534%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0601') WHERE `Cd_Test` LIKE 'Rgaa22-0601%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0602') WHERE `Cd_Test` LIKE 'Rgaa22-0602%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0603') WHERE `Cd_Test` LIKE 'Rgaa22-0603%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0604') WHERE `Cd_Test` LIKE 'Rgaa22-0604%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0605') WHERE `Cd_Test` LIKE 'Rgaa22-0605%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0606') WHERE `Cd_Test` LIKE 'Rgaa22-0606%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0607') WHERE `Cd_Test` LIKE 'Rgaa22-0607%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0608') WHERE `Cd_Test` LIKE 'Rgaa22-0608%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0609') WHERE `Cd_Test` LIKE 'Rgaa22-0609%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0610') WHERE `Cd_Test` LIKE 'Rgaa22-0610%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0611') WHERE `Cd_Test` LIKE 'Rgaa22-0611%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0612') WHERE `Cd_Test` LIKE 'Rgaa22-0612%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0613') WHERE `Cd_Test` LIKE 'Rgaa22-0613%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0614') WHERE `Cd_Test` LIKE 'Rgaa22-0614%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0615') WHERE `Cd_Test` LIKE 'Rgaa22-0615%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0616') WHERE `Cd_Test` LIKE 'Rgaa22-0616%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0617') WHERE `Cd_Test` LIKE 'Rgaa22-0617%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0618') WHERE `Cd_Test` LIKE 'Rgaa22-0618%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0619') WHERE `Cd_Test` LIKE 'Rgaa22-0619%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0620') WHERE `Cd_Test` LIKE 'Rgaa22-0620%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0621') WHERE `Cd_Test` LIKE 'Rgaa22-0621%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0622') WHERE `Cd_Test` LIKE 'Rgaa22-0622%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0623') WHERE `Cd_Test` LIKE 'Rgaa22-0623%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0624') WHERE `Cd_Test` LIKE 'Rgaa22-0624%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0625') WHERE `Cd_Test` LIKE 'Rgaa22-0625%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0626') WHERE `Cd_Test` LIKE 'Rgaa22-0626%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0627') WHERE `Cd_Test` LIKE 'Rgaa22-0627%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0628') WHERE `Cd_Test` LIKE 'Rgaa22-0628%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0629') WHERE `Cd_Test` LIKE 'Rgaa22-0629%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0630') WHERE `Cd_Test` LIKE 'Rgaa22-0630%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0631') WHERE `Cd_Test` LIKE 'Rgaa22-0631%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0632') WHERE `Cd_Test` LIKE 'Rgaa22-0632%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0633') WHERE `Cd_Test` LIKE 'Rgaa22-0633%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0634') WHERE `Cd_Test` LIKE 'Rgaa22-0634%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0635') WHERE `Cd_Test` LIKE 'Rgaa22-0635%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0636') WHERE `Cd_Test` LIKE 'Rgaa22-0636%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0701') WHERE `Cd_Test` LIKE 'Rgaa22-0701%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0702') WHERE `Cd_Test` LIKE 'Rgaa22-0702%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0703') WHERE `Cd_Test` LIKE 'Rgaa22-0703%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0704') WHERE `Cd_Test` LIKE 'Rgaa22-0704%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0705') WHERE `Cd_Test` LIKE 'Rgaa22-0705%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0706') WHERE `Cd_Test` LIKE 'Rgaa22-0706%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0707') WHERE `Cd_Test` LIKE 'Rgaa22-0707%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0708') WHERE `Cd_Test` LIKE 'Rgaa22-0708%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0709') WHERE `Cd_Test` LIKE 'Rgaa22-0709%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0710') WHERE `Cd_Test` LIKE 'Rgaa22-0710%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0711') WHERE `Cd_Test` LIKE 'Rgaa22-0711%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0712') WHERE `Cd_Test` LIKE 'Rgaa22-0712%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0713') WHERE `Cd_Test` LIKE 'Rgaa22-0713%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0714') WHERE `Cd_Test` LIKE 'Rgaa22-0714%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0715') WHERE `Cd_Test` LIKE 'Rgaa22-0715%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0716') WHERE `Cd_Test` LIKE 'Rgaa22-0716%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0717') WHERE `Cd_Test` LIKE 'Rgaa22-0717%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0718') WHERE `Cd_Test` LIKE 'Rgaa22-0718%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0801') WHERE `Cd_Test` LIKE 'Rgaa22-0801%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0802') WHERE `Cd_Test` LIKE 'Rgaa22-0802%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0803') WHERE `Cd_Test` LIKE 'Rgaa22-0803%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0804') WHERE `Cd_Test` LIKE 'Rgaa22-0804%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0805') WHERE `Cd_Test` LIKE 'Rgaa22-0805%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0806') WHERE `Cd_Test` LIKE 'Rgaa22-0806%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0807') WHERE `Cd_Test` LIKE 'Rgaa22-0807%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0808') WHERE `Cd_Test` LIKE 'Rgaa22-0808%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0809') WHERE `Cd_Test` LIKE 'Rgaa22-0809%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0810') WHERE `Cd_Test` LIKE 'Rgaa22-0810%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0811') WHERE `Cd_Test` LIKE 'Rgaa22-0811%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0812') WHERE `Cd_Test` LIKE 'Rgaa22-0812%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0813') WHERE `Cd_Test` LIKE 'Rgaa22-0813%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0901') WHERE `Cd_Test` LIKE 'Rgaa22-0901%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0902') WHERE `Cd_Test` LIKE 'Rgaa22-0902%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0903') WHERE `Cd_Test` LIKE 'Rgaa22-0903%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0904') WHERE `Cd_Test` LIKE 'Rgaa22-0904%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0905') WHERE `Cd_Test` LIKE 'Rgaa22-0905%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0906') WHERE `Cd_Test` LIKE 'Rgaa22-0906%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0907') WHERE `Cd_Test` LIKE 'Rgaa22-0907%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-0908') WHERE `Cd_Test` LIKE 'Rgaa22-0908%';

UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1001') WHERE `Cd_Test` LIKE 'Rgaa22-1001%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1002') WHERE `Cd_Test` LIKE 'Rgaa22-1002%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1003') WHERE `Cd_Test` LIKE 'Rgaa22-1003%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1004') WHERE `Cd_Test` LIKE 'Rgaa22-1004%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1005') WHERE `Cd_Test` LIKE 'Rgaa22-1005%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1006') WHERE `Cd_Test` LIKE 'Rgaa22-1006%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1007') WHERE `Cd_Test` LIKE 'Rgaa22-1007%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1008') WHERE `Cd_Test` LIKE 'Rgaa22-1008%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1009') WHERE `Cd_Test` LIKE 'Rgaa22-1009%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1010') WHERE `Cd_Test` LIKE 'Rgaa22-1010%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1011') WHERE `Cd_Test` LIKE 'Rgaa22-1011%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1012') WHERE `Cd_Test` LIKE 'Rgaa22-1012%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1013') WHERE `Cd_Test` LIKE 'Rgaa22-1013%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1101') WHERE `Cd_Test` LIKE 'Rgaa22-1101%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1102') WHERE `Cd_Test` LIKE 'Rgaa22-1102%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1103') WHERE `Cd_Test` LIKE 'Rgaa22-1103%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1104') WHERE `Cd_Test` LIKE 'Rgaa22-1104%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1105') WHERE `Cd_Test` LIKE 'Rgaa22-1105%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1106') WHERE `Cd_Test` LIKE 'Rgaa22-1106%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1107') WHERE `Cd_Test` LIKE 'Rgaa22-1107%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1108') WHERE `Cd_Test` LIKE 'Rgaa22-1108%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1109') WHERE `Cd_Test` LIKE 'Rgaa22-1109%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1110') WHERE `Cd_Test` LIKE 'Rgaa22-1110%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1201') WHERE `Cd_Test` LIKE 'Rgaa22-1201%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1202') WHERE `Cd_Test` LIKE 'Rgaa22-1202%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1203') WHERE `Cd_Test` LIKE 'Rgaa22-1203%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1204') WHERE `Cd_Test` LIKE 'Rgaa22-1204%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1205') WHERE `Cd_Test` LIKE 'Rgaa22-1205%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1206') WHERE `Cd_Test` LIKE 'Rgaa22-1206%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1207') WHERE `Cd_Test` LIKE 'Rgaa22-1207%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1208') WHERE `Cd_Test` LIKE 'Rgaa22-1208%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1209') WHERE `Cd_Test` LIKE 'Rgaa22-1209%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa22-1210') WHERE `Cd_Test` LIKE 'Rgaa22-1210%';

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
Cd_Test='Rgaa22-04011' OR
Cd_Test='Rgaa22-04021' OR
Cd_Test='Rgaa22-04031' OR
Cd_Test='Rgaa22-04041' OR
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
Cd_Test='Rgaa22-09011' OR
Cd_Test='Rgaa22-09021' OR
Cd_Test='Rgaa22-09031' OR
Cd_Test='Rgaa22-09061' OR
Cd_Test='Rgaa22-09071' OR
Cd_Test='Rgaa22-09081' OR
Cd_Test='Rgaa22-10011' OR
Cd_Test='Rgaa22-10021' OR
Cd_Test='Rgaa22-10031' OR
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
Cd_Test='Rgaa22-12011' OR
Cd_Test='Rgaa22-12031';

UPDATE TEST SET Id_Level='1' WHERE Id_Level='4' And Cd_Test like "Rgaa%";
UPDATE TEST SET Id_Level='2' WHERE Id_Level='5' And Cd_Test like "Rgaa%";
UPDATE TEST SET Id_Level='3' WHERE Id_Level='6' And Cd_Test like "Rgaa%";

INSERT IGNORE INTO `PARAMETER` (`Id_Parameter_Element`, `Parameter_Value`, `Is_Default`) VALUES
(5, 'Rgaa22;LEVEL_1', b'0'),
(5, 'Rgaa22;LEVEL_2', b'0'),
(5, 'Rgaa22;LEVEL_3', b'0');

UPDATE REFERENCE SET Cd_Reference='Rgaa22' WHERE Cd_Reference='RGAA22';
-- UPDATE TGSI_REFERENTIAL SET Code='Rgaa22' WHERE Code='RGAA22';
UPDATE THEME SET Cd_Theme = REPLACE(Cd_Theme, 'RGAA22', 'Rgaa22');
UPDATE CRITERION SET Cd_Criterion = REPLACE(Cd_Criterion, 'RGAA22', 'Rgaa22');
UPDATE TEST SET Cd_Test = REPLACE(Cd_Test, 'RGAA22', 'Rgaa22');
UPDATE PROCESS_REMARK SET Message_Code = REPLACE(Message_Code, 'RGAA22', 'Rgaa22');
UPDATE PARAMETER SET Parameter_Value = REPLACE(Parameter_Value, 'RGAA22', 'Rgaa22');
