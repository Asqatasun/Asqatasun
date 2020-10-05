INSERT IGNORE INTO `REFERENCE` (`Cd_Reference`, `Description`, `Label`, `Url`, `Rank`, `Id_Default_Level`) VALUES
('Rgaa40', NULL, 'Rgaa 4.0', '', 2000, 2);

-- INSERT IGNORE INTO `TGSI_REFERENTIAL` (`Code`, `Label`) VALUES
-- ('Rgaa40', 'Rgaa 4.0');

INSERT IGNORE INTO `THEME` (`Cd_Theme`, `Description`, `Label`, `Rank`) VALUES
('Rgaa40-1', NULL, 'Images', 1),
('Rgaa40-2', NULL, 'Cadres', 2),
('Rgaa40-3', NULL, 'Couleurs', 3),
('Rgaa40-4', NULL, 'Multimedia', 4),
('Rgaa40-5', NULL, 'Tableaux', 5),
('Rgaa40-6', NULL, 'Liens', 6),
('Rgaa40-7', NULL, 'Scripts', 7),
('Rgaa40-8', NULL, 'Éléments obligatoires', 8),
('Rgaa40-9', NULL, 'Structuration de l’information', 9),
('Rgaa40-10', NULL, 'Présentation de l’information', 10),
('Rgaa40-11', NULL, 'Formulaires', 11),
('Rgaa40-12', NULL, 'Navigation', 12),
('Rgaa40-13', NULL, 'Consultation', 13);

INSERT IGNORE INTO `CRITERION` (`Cd_Criterion`, `Description`, `Label`, `Url`, `Rank`) VALUES
('Rgaa40-1-1', 'Chaque image porteuse d’information a-t-elle une alternative textuelle ?', '1.1', '', 1),
('Rgaa40-1-2', 'Chaque image de décoration est-elle correctement ignorée par les technologies d’assistance ?', '1.2', '', 2),
('Rgaa40-1-3', 'Pour chaque image porteuse d’information ayant une alternative textuelle, cette alternative est-elle pertinente (hors cas particuliers) ?', '1.3', '', 3),
('Rgaa40-1-4', 'Pour chaque image utilisée comme CAPTCHA ou comme image-test, ayant une alternative textuelle, cette alternative permet-elle d’identifier la nature et la fonction de l’image ?', '1.4', '', 4),
('Rgaa40-1-5', 'Pour chaque image utilisée comme CAPTCHA, une solution d’accès alternatif au contenu ou à la fonction du CAPTCHA est-elle présente ?', '1.5', '', 5),
('Rgaa40-1-6', 'Chaque image porteuse d’information a-t-elle, si nécessaire, une description détaillée ?', '1.6', '', 6),
('Rgaa40-1-7', 'Pour chaque image porteuse d’information ayant une description détaillée, cette description est-elle pertinente ?', '1.7', '', 7),
('Rgaa40-1-8', 'Chaque image texte porteuse d’information, en l’absence d’un mécanisme de remplacement, doit si possible être remplacée par du texte stylé. Cette règle est-elle respectée (hors cas particuliers) ?', '1.8', '', 8),
('Rgaa40-1-9', 'Chaque légende d’image est-elle, si nécessaire, correctement reliée à l’image correspondante ?', '1.9', '', 9),
('Rgaa40-2-1', 'Chaque cadre a-t-il un titre de cadre ?', '2.1', '', 10),
('Rgaa40-2-2', 'Pour chaque cadre ayant un titre de cadre, ce titre de cadre est-il pertinent ?', '2.2', '', 11),
('Rgaa40-3-1', 'Dans chaque page web, l’information ne doit pas être donnée uniquement par la couleur. Cette règle est-elle respectée ?', '3.1', '', 12),
('Rgaa40-3-2', 'Dans chaque page web, le contraste entre la couleur du texte et la couleur de son arrière-plan est-il suffisamment élevé (hors cas particuliers) ?', '3.2', '', 13),
('Rgaa40-3-3', 'Dans chaque page web, les couleurs utilisées dans les composants d’interface ou les éléments graphiques porteurs d’informations sont-elles suffisamment contrastées (hors cas particuliers) ?', '3.3', '', 14),
('Rgaa40-4-1', 'Chaque média temporel pré-enregistré a-t-il, si nécessaire, une transcription textuelle ou une audiodescription (hors cas particuliers) ?', '4.1', '', 15),
('Rgaa40-4-2', 'Pour chaque média temporel pré-enregistré ayant une transcription textuelle ou une audiodescription synchronisée, celles-ci sont-elles pertinentes (hors cas particuliers) ?', '4.2', '', 16),
('Rgaa40-4-3', 'Chaque média temporel synchronisé pré-enregistré a-t-il, si nécessaire, des sous-titres synchronisés (hors cas particuliers) ?', '4.3', '', 17),
('Rgaa40-4-4', 'Pour chaque média temporel synchronisé pré-enregistré ayant des sous-titres synchronisés, ces sous-titres sont-ils pertinents ?', '4.4', '', 18),
('Rgaa40-4-5', 'Chaque média temporel pré-enregistré a-t-il, si nécessaire, une audiodescription synchronisée (hors cas particuliers) ?', '4.5', '', 19),
('Rgaa40-4-6', 'Pour chaque média temporel pré-enregistré ayant une audiodescription synchronisée, celle-ci est-elle pertinente ?', '4.6', '', 20),
('Rgaa40-4-7', 'Chaque média temporel est-il clairement identifiable (hors cas particuliers) ?', '4.7', '', 21),
('Rgaa40-4-8', 'Chaque média non temporel a-t-il, si nécessaire, une alternative (hors cas particuliers) ?', '4.8', '', 22),
('Rgaa40-4-9', 'Pour chaque média non temporel ayant une alternative, cette alternative est-elle pertinente ?', '4.9', '', 23),
('Rgaa40-4-10', 'Chaque son déclenché automatiquement est-il contrôlable par l’utilisateur ?', '4.10', '', 24),
('Rgaa40-4-11', 'La consultation de chaque média temporel est-elle, si nécessaire, contrôlable par le clavier et tout dispositif de pointage ?', '4.11', '', 25),
('Rgaa40-4-12', 'La consultation de chaque média non temporel est-elle contrôlable par le clavier et tout dispositif de pointage ?', '4.12', '', 26),
('Rgaa40-4-13', 'Chaque média temporel et non temporel est-il compatible avec les technologies d’assistance (hors cas particuliers) ?', '4.13', '', 27),
('Rgaa40-5-1', 'Chaque tableau de données complexe a-t-il un résumé ?', '5.1', '', 28),
('Rgaa40-5-2', 'Pour chaque tableau de données complexe ayant un résumé, celui-ci est-il pertinent ?', '5.2', '', 29),
('Rgaa40-5-3', 'Pour chaque tableau de mise en forme, le contenu linéarisé reste-t-il compréhensible (hors cas particuliers) ?', '5.3', '', 30),
('Rgaa40-5-4', 'Pour chaque tableau de données ayant un titre, le titre est-il correctement associé au tableau de données ?', '5.4', '', 31),
('Rgaa40-5-5', 'Pour chaque tableau de données ayant un titre, celui-ci est-il pertinent ?', '5.5', '', 32),
('Rgaa40-5-6', 'Pour chaque tableau de données, chaque en-tête de colonnes et chaque en-tête de lignes sont-ils correctement déclarés ?', '5.6', '', 33),
('Rgaa40-5-7', 'Pour chaque tableau de données, la technique appropriée permettant d’associer chaque cellule avec ses en-têtes est-elle utilisée (hors cas particuliers) ?', '5.7', '', 34),
('Rgaa40-5-8', 'Chaque tableau de mise en forme ne doit pas utiliser d’éléments propres aux tableaux de données. Cette règle est-elle respectée ?', '5.8', '', 35),
('Rgaa40-6-1', 'Chaque lien est-il explicite (hors cas particuliers) ?', '6.1', '', 36),
('Rgaa40-6-2', 'Dans chaque page web, chaque lien, à l’exception des ancres, a-t-il un intitulé ?', '6.2', '', 37),
('Rgaa40-7-1', 'Chaque script est-il, si nécessaire, compatible avec les technologies d’assistance ?', '7.1', '', 38),
('Rgaa40-7-2', 'Pour chaque script ayant une alternative, cette alternative est-elle pertinente ?', '7.2', '', 39),
('Rgaa40-7-3', 'Chaque script est-il contrôlable par le clavier et par tout dispositif de pointage (hors cas particuliers) ?', '7.3', '', 40),
('Rgaa40-7-4', 'Pour chaque script qui initie un changement de contexte, l’utilisateur est-il averti ou en a-t-il le contrôle ?', '7.4', '', 41),
('Rgaa40-7-5', 'Dans chaque page web, les messages de statut sont-ils correctement restitués par les technologies d’assistance ?', '7.5', '', 42),
('Rgaa40-8-1', 'Chaque page web est-elle définie par un type de document ?', '8.1', '', 43),
('Rgaa40-8-2', 'Pour chaque page web, le code source généré est-il valide selon le type de document spécifié (hors cas particuliers) ?', '8.2', '', 44),
('Rgaa40-8-3', 'Dans chaque page web, la langue par défaut est-elle présente ?', '8.3', '', 45),
('Rgaa40-8-4', 'Pour chaque page web ayant une langue par défaut, le code de langue est-il pertinent ?', '8.4', '', 46),
('Rgaa40-8-5', 'Chaque page web a-t-elle un titre de page ?', '8.5', '', 47),
('Rgaa40-8-6', 'Pour chaque page web ayant un titre de page, ce titre est-il pertinent ?', '8.6', '', 48),
('Rgaa40-8-7', 'Dans chaque page web, chaque changement de langue est-il indiqué dans le code source (hors cas particuliers) ?', '8.7', '', 49),
('Rgaa40-8-8', 'Dans chaque page web, le code de langue de chaque changement de langue est-il valide et pertinent ?', '8.8', '', 50),
('Rgaa40-8-9', 'Dans chaque page web, les balises ne doivent pas être utilisées uniquement à des fins de présentation. Cette règle est-elle respectée ?', '8.9', '', 51),
('Rgaa40-8-10', 'Dans chaque page web, les changements du sens de lecture sont-ils signalés ?', '8.10', '', 52),
('Rgaa40-9-1', 'Dans chaque page web, l’information est-elle structurée par l’utilisation appropriée de titres ?', '9.1', '', 53),
('Rgaa40-9-2', 'Dans chaque page web, la structure du document est-elle cohérente (hors cas particuliers) ?', '9.2', '', 54),
('Rgaa40-9-3', 'Dans chaque page web, chaque liste est-elle correctement structurée ?', '9.3', '', 55),
('Rgaa40-9-4', 'Dans chaque page web, chaque citation est-elle correctement indiquée ?', '9.4', '', 56),
('Rgaa40-10-1', 'Dans le site web, des feuilles de styles sont-elles utilisées pour contrôler la présentation de l’information ?', '10.1', '', 57),
('Rgaa40-10-2', 'Dans chaque page web, le contenu visible reste-t-il présent lorsque les feuilles de styles sont désactivées ?', '10.2', '', 58),
('Rgaa40-10-3', 'Dans chaque page web, l’information reste-t-elle compréhensible lorsque les feuilles de styles sont désactivées ?', '10.3', '', 59),
('Rgaa40-10-4', 'Dans chaque page web, le texte reste-t-il lisible lorsque la taille des caractères est augmentée jusqu’à 200%, au moins (hors cas particuliers) ?', '10.4', '', 60),
('Rgaa40-10-5', 'Dans chaque page web, les déclarations CSS de couleurs de fond d’élément et de police sont-elles correctement utilisées ?', '10.5', '', 61),
('Rgaa40-10-6', 'Dans chaque page web, chaque lien dont la nature n’est pas évidente est-il visible par rapport au texte environnant ?', '10.6', '', 62),
('Rgaa40-10-7', 'Dans chaque page web, pour chaque élément recevant le focus, la prise de focus est-elle visible ?', '10.7', '', 63),
('Rgaa40-10-8', 'Pour chaque page web, les contenus cachés ont-ils vocation à être ignorés par les technologies d’assistance ?', '10.8', '', 64),
('Rgaa40-10-9', 'Dans chaque page web, l’information ne doit pas être donnée uniquement par la forme, taille ou position. Cette règle est-elle respectée ?', '10.9', '', 65),
('Rgaa40-10-10', 'Dans chaque page web, l’information ne doit pas être donnée par la forme, taille ou position uniquement. Cette règle est-elle implémentée de façon pertinente ?', '10.10', '', 66),
('Rgaa40-10-11', 'Pour chaque page web, les contenus peuvent-ils être présentés sans avoir recours à la fois à un défilement vertical pour une fenêtre ayant une hauteur de 256px ou une largeur de 320px (hors cas particuliers) ?', '10.11', '', 67),
('Rgaa40-10-12', 'Dans chaque page web, les propriétés d’espacement du texte peuvent-elles être redéfinies par l’utilisateur sans perte de contenu ou de fonctionnalité (hors cas particuliers) ?', '10.12', '', 68),
('Rgaa40-10-13', 'Dans chaque page web, les contenus additionnels apparaissant à la prise de focus ou au survol d’un composant d’interface sont-ils contrôlables par l’utilisateur (hors cas particuliers) ?', '10.13', '', 69),
('Rgaa40-10-14', 'Dans chaque page web, les contenus additionnels apparaissant via les styles CSS uniquement peuvent-ils être rendus visibles au clavier et par tout dispositif de pointage ?', '10.14', '', 70),
('Rgaa40-11-1', 'Chaque champ de formulaire a-t-il une étiquette ?', '11.1', '', 71),
('Rgaa40-11-2', 'Chaque étiquette associée à un champ de formulaire est-elle pertinente (hors cas particuliers) ?', '11.2', '', 72),
('Rgaa40-11-3', 'Dans chaque formulaire, chaque étiquette associée à un champ de formulaire ayant la même fonction et répété plusieurs fois dans une même page ou dans un ensemble de pages est-elle cohérente ?', '11.3', '', 73),
('Rgaa40-11-4', 'Dans chaque formulaire, chaque étiquette de champ et son champ associé sont-ils accolés (hors cas particuliers) ?', '11.4', '', 74),
('Rgaa40-11-5', 'Dans chaque formulaire, les champs de même nature sont-ils regroupés, si nécessaire ?', '11.5', '', 75),
('Rgaa40-11-6', 'Dans chaque formulaire, chaque regroupement de champs de formulaire a-t-il une légende ?', '11.6', '', 76),
('Rgaa40-11-7', 'Dans chaque formulaire, chaque légende associée à un regroupement de champs de même nature est-elle pertinente ?', '11.7', '', 77),
('Rgaa40-11-8', 'Dans chaque formulaire, les items de même nature d’une liste de choix sont-ils regroupées de manière pertinente ?', '11.8', '', 78),
('Rgaa40-11-9', 'Dans chaque formulaire, l’intitulé de chaque bouton est-il pertinent (hors cas particuliers) ?', '11.9', '', 79),
('Rgaa40-11-10', 'Dans chaque formulaire, le contrôle de saisie est-il utilisé de manière pertinente (hors cas particuliers) ?', '11.10', '', 80),
('Rgaa40-11-11', 'Dans chaque formulaire, le contrôle de saisie est-il accompagné, si nécessaire, de suggestions facilitant la correction des erreurs de saisie ?', '11.11', '', 81),
('Rgaa40-11-12', 'Pour chaque formulaire qui modifie ou supprime des données, ou qui transmet des réponses à un test ou à un examen, ou dont la validation a des conséquences financières ou juridiques, la saisie des données vérifie-t-elle une de ces conditions ?', '11.12', '', 82),
('Rgaa40-11-13', 'La finalité d’un champ de saisie peut-elle être déduite pour faciliter le remplissage automatique des champs avec les données de l’utilisateur ?', '11.13', '', 83),
('Rgaa40-12-1', 'Chaque ensemble de pages dispose-t-il de deux systèmes de navigation différents, au moins (hors cas particuliers) ?', '12.1', '', 84),
('Rgaa40-12-2', 'Dans chaque ensemble de pages, le menu et les barres de navigation sont-ils toujours à la même place (hors cas particuliers) ?', '12.2', '', 85),
('Rgaa40-12-3', 'La page « plan du site » est-elle pertinente ?', '12.3', '', 86),
('Rgaa40-12-4', 'Dans chaque ensemble de pages, la page « plan du site » est-elle atteignable de manière identique ?', '12.4', '', 87),
('Rgaa40-12-5', 'Dans chaque ensemble de pages, le moteur de recherche est-il atteignable de manière identique ?', '12.5', '', 88),
('Rgaa40-12-6', 'Les zones de regroupement de contenus présentes dans plusieurs pages web (zones d’en-tête, de navigation principale, de contenu principal, de pied de page et de moteur de recherche) peuvent-elles être atteintes ou évitées ?', '12.6', '', 89),
('Rgaa40-12-7', 'Dans chaque page web, un lien d’évitement ou d’accès rapide à la zone de contenu principal est-il présent (hors cas particuliers) ?', '12.7', '', 90),
('Rgaa40-12-8', 'Dans chaque page web, l’ordre de tabulation est-il cohérent ?', '12.8', '', 91),
('Rgaa40-12-9', 'Dans chaque page web, la navigation ne doit pas contenir de piège au clavier. Cette règle est-elle respectée ?', '12.9', '', 92),
('Rgaa40-12-10', 'Dans chaque page web, les raccourcis clavier n’utilisant qu’une seule touche (lettre minuscule ou majuscule, ponctuation, chiffre ou symbole) sont-ils contrôlables par l’utilisateur ?', '12.10', '', 93),
('Rgaa40-12-11', 'Dans chaque page web, les contenus additionnels apparaissant au survol, à la prise de focus ou à l’activation d’un composant d’interface sont-ils si nécessaire atteignables au clavier ?', '12.11', '', 94),
('Rgaa40-13-1', 'Pour chaque page web, l’utilisateur a-t-il le contrôle de chaque limite de temps modifiant le contenu (hors cas particuliers) ?', '13.1', '', 95),
('Rgaa40-13-2', 'Dans chaque page web, l’ouverture d’une nouvelle fenêtre ne doit pas être déclenchée sans action de l’utilisateur. Cette règle est-elle respectée ?', '13.2', '', 96),
('Rgaa40-13-3', 'Dans chaque page web, chaque document bureautique en téléchargement possède-t-il, si nécessaire, une version accessible (hors cas particuliers) ?', '13.3', '', 97),
('Rgaa40-13-4', 'Pour chaque document bureautique ayant une version accessible, cette version offre-t-elle la même information ?', '13.4', '', 98),
('Rgaa40-13-5', 'Dans chaque page web, chaque contenu cryptique (art ASCII, émoticon, syntaxe cryptique) a-t-il une alternative ?', '13.5', '', 99),
('Rgaa40-13-6', 'Dans chaque page web, pour chaque contenu cryptique (art ASCII, émoticon, syntaxe cryptique) ayant une alternative, cette alternative est-elle pertinente ?', '13.6', '', 100),
('Rgaa40-13-7', 'Dans chaque page web, les changements brusques de luminosité ou les effets de flash sont-ils correctement utilisés ?', '13.7', '', 101),
('Rgaa40-13-8', 'Dans chaque page web, chaque contenu en mouvement ou clignotant est-il contrôlable par l’utilisateur ?', '13.8', '', 102),
('Rgaa40-13-9', 'Dans chaque page web, le contenu proposé est-il consultable quelle que soit l’orientation de l’écran (portait ou paysage) (hors cas particuliers) ?', '13.9', '', 103),
('Rgaa40-13-10', 'Dans chaque page web, les fonctionnalités utilisables ou disponibles au moyen d’un geste complexe peuvent-elles être également disponibles au moyen d’un geste simple (hors cas particuliers) ?', '13.10', '', 104),
('Rgaa40-13-11', 'Dans chaque page web, les actions déclenchées au moyen d’un dispositif de pointage sur un point unique de l’écran peuvent-elles faire l’objet d’une annulation (hors cas particuliers) ?', '13.11', '', 105),
('Rgaa40-13-12', 'Dans chaque page web, les fonctionnalités qui impliquent un mouvement de l’appareil ou vers l’appareil peuvent-elles être satisfaites de manière alternative (hors cas particuliers) ?', '13.12', '', 106);

UPDATE `CRITERION` SET `Reference_Id_Reference` = (SELECT `Id_Reference` FROM `REFERENCE` WHERE `Cd_Reference` LIKE 'Rgaa40') WHERE `Cd_Criterion` LIKE 'Rgaa40-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa40-1') WHERE `Cd_Criterion` LIKE 'Rgaa40-1-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa40-2') WHERE `Cd_Criterion` LIKE 'Rgaa40-2-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa40-3') WHERE `Cd_Criterion` LIKE 'Rgaa40-3-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa40-4') WHERE `Cd_Criterion` LIKE 'Rgaa40-4-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa40-5') WHERE `Cd_Criterion` LIKE 'Rgaa40-5-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa40-6') WHERE `Cd_Criterion` LIKE 'Rgaa40-6-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa40-7') WHERE `Cd_Criterion` LIKE 'Rgaa40-7-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa40-8') WHERE `Cd_Criterion` LIKE 'Rgaa40-8-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa40-9') WHERE `Cd_Criterion` LIKE 'Rgaa40-9-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa40-10') WHERE `Cd_Criterion` LIKE 'Rgaa40-10-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa40-11') WHERE `Cd_Criterion` LIKE 'Rgaa40-11-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa40-12') WHERE `Cd_Criterion` LIKE 'Rgaa40-12-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa40-13') WHERE `Cd_Criterion` LIKE 'Rgaa40-13-%';

INSERT IGNORE INTO `TEST` (`Cd_Test`, `Description`, `Label`, `Rank`, `Weight`, `Rule_Archive_Name`, `Rule_Class_Name`, `Id_Decision_Level`, `Id_Level`, `Id_Scope`, `Rule_Design_Url`, `No_Process`) VALUES
('Rgaa40-1-1-1', '', '1.1.1', 1, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010101', NULL, 1, 1, '', b'0'),
('Rgaa40-1-1-2', '', '1.1.2', 2, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010102', NULL, 1, 1, '', b'0'),
('Rgaa40-1-1-3', '', '1.1.3', 3, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010103', NULL, 1, 1, '', b'0'),
('Rgaa40-1-1-4', '', '1.1.4', 4, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010104', NULL, 1, 1, '', b'0'),
('Rgaa40-1-1-5', '', '1.1.5', 5, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010105', NULL, 1, 1, '', b'0'),
('Rgaa40-1-1-6', '', '1.1.6', 6, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010106', NULL, 1, 1, '', b'0'),
('Rgaa40-1-1-7', '', '1.1.7', 7, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010107', NULL, 1, 1, '', b'0'),
('Rgaa40-1-1-8', '', '1.1.8', 8, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010108', NULL, 1, 1, '', b'0'),
('Rgaa40-1-2-1', '', '1.2.1', 9, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010201', NULL, 1, 1, '', b'0'),
('Rgaa40-1-2-2', '', '1.2.2', 10, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010202', NULL, 1, 1, '', b'0'),
('Rgaa40-1-2-3', '', '1.2.3', 11, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010203', NULL, 1, 1, '', b'0'),
('Rgaa40-1-2-4', '', '1.2.4', 12, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010204', NULL, 1, 1, '', b'0'),
('Rgaa40-1-2-5', '', '1.2.5', 13, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010205', NULL, 1, 1, '', b'0'),
('Rgaa40-1-2-6', '', '1.2.6', 14, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010206', NULL, 1, 1, '', b'0'),
('Rgaa40-1-3-1', '', '1.3.1', 15, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010301', NULL, 1, 1, '', b'0'),
('Rgaa40-1-3-2', '', '1.3.2', 16, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010302', NULL, 1, 1, '', b'0'),
('Rgaa40-1-3-3', '', '1.3.3', 17, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010303', NULL, 1, 1, '', b'0'),
('Rgaa40-1-3-4', '', '1.3.4', 18, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010304', NULL, 1, 1, '', b'0'),
('Rgaa40-1-3-5', '', '1.3.5', 19, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010305', NULL, 1, 1, '', b'0'),
('Rgaa40-1-3-6', '', '1.3.6', 20, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010306', NULL, 1, 1, '', b'0'),
('Rgaa40-1-3-7', '', '1.3.7', 21, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010307', NULL, 1, 1, '', b'0'),
('Rgaa40-1-3-8', '', '1.3.8', 22, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010308', NULL, 1, 1, '', b'0'),
('Rgaa40-1-3-9', '', '1.3.9', 23, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010309', NULL, 1, 1, '', b'0'),
('Rgaa40-1-4-1', '', '1.4.1', 24, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010401', NULL, 1, 1, '', b'0'),
('Rgaa40-1-4-2', '', '1.4.2', 25, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010402', NULL, 1, 1, '', b'0'),
('Rgaa40-1-4-3', '', '1.4.3', 26, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010403', NULL, 1, 1, '', b'0'),
('Rgaa40-1-4-4', '', '1.4.4', 27, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010404', NULL, 1, 1, '', b'0'),
('Rgaa40-1-4-5', '', '1.4.5', 28, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010405', NULL, 1, 1, '', b'0'),
('Rgaa40-1-4-6', '', '1.4.6', 29, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010406', NULL, 1, 1, '', b'0'),
('Rgaa40-1-4-7', '', '1.4.7', 30, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010407', NULL, 1, 1, '', b'0'),
('Rgaa40-1-5-1', '', '1.5.1', 31, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010501', NULL, 1, 1, '', b'0'),
('Rgaa40-1-5-2', '', '1.5.2', 32, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010502', NULL, 1, 1, '', b'0'),
('Rgaa40-1-6-1', '', '1.6.1', 33, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010601', NULL, 1, 1, '', b'0'),
('Rgaa40-1-6-2', '', '1.6.2', 34, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010602', NULL, 1, 1, '', b'0'),
('Rgaa40-1-6-3', '', '1.6.3', 35, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010603', NULL, 1, 1, '', b'0'),
('Rgaa40-1-6-4', '', '1.6.4', 36, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010604', NULL, 1, 1, '', b'0'),
('Rgaa40-1-6-5', '', '1.6.5', 37, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010605', NULL, 1, 1, '', b'0'),
('Rgaa40-1-6-6', '', '1.6.6', 38, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010606', NULL, 1, 1, '', b'0'),
('Rgaa40-1-6-7', '', '1.6.7', 39, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010607', NULL, 1, 1, '', b'0'),
('Rgaa40-1-6-8', '', '1.6.8', 40, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010608', NULL, 1, 1, '', b'0'),
('Rgaa40-1-6-9', '', '1.6.9', 41, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010609', NULL, 1, 1, '', b'0'),
('Rgaa40-1-6-10', '', '1.6.10', 42, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010610', NULL, 1, 1, '', b'0'),
('Rgaa40-1-7-1', '', '1.7.1', 43, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010701', NULL, 1, 1, '', b'0'),
('Rgaa40-1-7-2', '', '1.7.2', 44, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010702', NULL, 1, 1, '', b'0'),
('Rgaa40-1-7-3', '', '1.7.3', 45, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010703', NULL, 1, 1, '', b'0'),
('Rgaa40-1-7-4', '', '1.7.4', 46, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010704', NULL, 1, 1, '', b'0'),
('Rgaa40-1-7-5', '', '1.7.5', 47, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010705', NULL, 1, 1, '', b'0'),
('Rgaa40-1-7-6', '', '1.7.6', 48, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010706', NULL, 1, 1, '', b'0'),
('Rgaa40-1-8-1', '', '1.8.1', 49, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010801', NULL, 2, 1, '', b'0'),
('Rgaa40-1-8-2', '', '1.8.2', 50, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010802', NULL, 2, 1, '', b'0'),
('Rgaa40-1-8-3', '', '1.8.3', 51, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010803', NULL, 2, 1, '', b'0'),
('Rgaa40-1-8-4', '', '1.8.4', 52, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010804', NULL, 2, 1, '', b'0'),
('Rgaa40-1-8-5', '', '1.8.5', 53, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010805', NULL, 2, 1, '', b'0'),
('Rgaa40-1-9-1', '', '1.9.1', 54, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010901', NULL, 1, 1, '', b'0'),
('Rgaa40-1-9-2', '', '1.9.2', 55, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010902', NULL, 1, 1, '', b'0'),
('Rgaa40-1-9-3', '', '1.9.3', 56, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010903', NULL, 1, 1, '', b'0'),
('Rgaa40-1-9-4', '', '1.9.4', 57, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010904', NULL, 1, 1, '', b'0'),
('Rgaa40-1-9-5', '', '1.9.5', 58, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule010905', NULL, 1, 1, '', b'0'),
('Rgaa40-2-1-1', '', '2.1.1', 59, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule020101', NULL, 1, 1, '', b'0'),
('Rgaa40-2-2-1', '', '2.2.1', 60, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule020201', NULL, 1, 1, '', b'0'),
('Rgaa40-3-1-1', '', '3.1.1', 61, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule030101', NULL, 1, 1, '', b'0'),
('Rgaa40-3-1-2', '', '3.1.2', 62, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule030102', NULL, 1, 1, '', b'0'),
('Rgaa40-3-1-3', '', '3.1.3', 63, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule030103', NULL, 1, 1, '', b'0'),
('Rgaa40-3-1-4', '', '3.1.4', 64, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule030104', NULL, 1, 1, '', b'0'),
('Rgaa40-3-1-5', '', '3.1.5', 65, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule030105', NULL, 1, 1, '', b'0'),
('Rgaa40-3-1-6', '', '3.1.6', 66, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule030106', NULL, 1, 1, '', b'0'),
('Rgaa40-3-2-1', '', '3.2.1', 67, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule030201', NULL, 2, 1, '', b'0'),
('Rgaa40-3-2-2', '', '3.2.2', 68, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule030202', NULL, 2, 1, '', b'0'),
('Rgaa40-3-2-3', '', '3.2.3', 69, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule030203', NULL, 2, 1, '', b'0'),
('Rgaa40-3-2-4', '', '3.2.4', 70, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule030204', NULL, 2, 1, '', b'0'),
('Rgaa40-3-2-5', '', '3.2.5', 71, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule030205', NULL, 2, 1, '', b'0'),
('Rgaa40-3-3-1', '', '3.3.1', 72, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule030301', NULL, 2, 1, '', b'0'),
('Rgaa40-3-3-2', '', '3.3.2', 73, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule030302', NULL, 2, 1, '', b'0'),
('Rgaa40-3-3-3', '', '3.3.3', 74, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule030303', NULL, 2, 1, '', b'0'),
('Rgaa40-3-3-4', '', '3.3.4', 75, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule030304', NULL, 2, 1, '', b'0'),
('Rgaa40-4-1-1', '', '4.1.1', 76, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule040101', NULL, 1, 1, '', b'0'),
('Rgaa40-4-1-2', '', '4.1.2', 77, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule040102', NULL, 1, 1, '', b'0'),
('Rgaa40-4-1-3', '', '4.1.3', 78, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule040103', NULL, 1, 1, '', b'0'),
('Rgaa40-4-2-1', '', '4.2.1', 79, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule040201', NULL, 1, 1, '', b'0'),
('Rgaa40-4-2-2', '', '4.2.2', 80, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule040202', NULL, 1, 1, '', b'0'),
('Rgaa40-4-2-3', '', '4.2.3', 81, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule040203', NULL, 1, 1, '', b'0'),
('Rgaa40-4-3-1', '', '4.3.1', 82, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule040301', NULL, 1, 1, '', b'0'),
('Rgaa40-4-3-2', '', '4.3.2', 83, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule040302', NULL, 1, 1, '', b'0'),
('Rgaa40-4-4-1', '', '4.4.1', 84, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule040401', NULL, 1, 1, '', b'0'),
('Rgaa40-4-5-1', '', '4.5.1', 85, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule040501', NULL, 2, 1, '', b'0'),
('Rgaa40-4-5-2', '', '4.5.2', 86, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule040502', NULL, 2, 1, '', b'0'),
('Rgaa40-4-6-1', '', '4.6.1', 87, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule040601', NULL, 2, 1, '', b'0'),
('Rgaa40-4-6-2', '', '4.6.2', 88, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule040602', NULL, 2, 1, '', b'0'),
('Rgaa40-4-7-1', '', '4.7.1', 89, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule040701', NULL, 1, 1, '', b'0'),
('Rgaa40-4-8-1', '', '4.8.1', 90, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule040801', NULL, 1, 1, '', b'0'),
('Rgaa40-4-8-2', '', '4.8.2', 91, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule040802', NULL, 1, 1, '', b'0'),
('Rgaa40-4-9-1', '', '4.9.1', 92, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule040901', NULL, 1, 1, '', b'0'),
('Rgaa40-4-10-1', '', '4.10.1', 93, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule041001', NULL, 1, 1, '', b'0'),
('Rgaa40-4-11-1', '', '4.11.1', 94, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule041101', NULL, 1, 1, '', b'0'),
('Rgaa40-4-11-2', '', '4.11.2', 95, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule041102', NULL, 1, 1, '', b'0'),
('Rgaa40-4-11-3', '', '4.11.3', 96, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule041103', NULL, 1, 1, '', b'0'),
('Rgaa40-4-12-1', '', '4.12.1', 97, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule041201', NULL, 1, 1, '', b'0'),
('Rgaa40-4-12-2', '', '4.12.2', 98, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule041202', NULL, 1, 1, '', b'0'),
('Rgaa40-4-13-1', '', '4.13.1', 99, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule041301', NULL, 1, 1, '', b'0'),
('Rgaa40-4-13-2', '', '4.13.2', 100, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule041302', NULL, 1, 1, '', b'0'),
('Rgaa40-5-1-1', '', '5.1.1', 101, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule050101', NULL, 1, 1, '', b'0'),
('Rgaa40-5-2-1', '', '5.2.1', 102, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule050201', NULL, 1, 1, '', b'0'),
('Rgaa40-5-3-1', '', '5.3.1', 103, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule050301', NULL, 1, 1, '', b'0'),
('Rgaa40-5-4-1', '', '5.4.1', 104, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule050401', NULL, 1, 1, '', b'0'),
('Rgaa40-5-5-1', '', '5.5.1', 105, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule050501', NULL, 1, 1, '', b'0'),
('Rgaa40-5-6-1', '', '5.6.1', 106, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule050601', NULL, 1, 1, '', b'0'),
('Rgaa40-5-6-2', '', '5.6.2', 107, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule050602', NULL, 1, 1, '', b'0'),
('Rgaa40-5-6-3', '', '5.6.3', 108, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule050603', NULL, 1, 1, '', b'0'),
('Rgaa40-5-7-1', '', '5.7.1', 109, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule050701', NULL, 1, 1, '', b'0'),
('Rgaa40-5-7-2', '', '5.7.2', 110, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule050702', NULL, 1, 1, '', b'0'),
('Rgaa40-5-7-3', '', '5.7.3', 111, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule050703', NULL, 1, 1, '', b'0'),
('Rgaa40-5-7-4', '', '5.7.4', 112, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule050704', NULL, 1, 1, '', b'0'),
('Rgaa40-5-7-5', '', '5.7.5', 113, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule050705', NULL, 1, 1, '', b'0'),
('Rgaa40-5-8-1', '', '5.8.1', 114, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule050801', NULL, 1, 1, '', b'0'),
('Rgaa40-6-1-1', '', '6.1.1', 115, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule060101', NULL, 1, 1, '', b'0'),
('Rgaa40-6-1-2', '', '6.1.2', 116, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule060102', NULL, 1, 1, '', b'0'),
('Rgaa40-6-1-3', '', '6.1.3', 117, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule060103', NULL, 1, 1, '', b'0'),
('Rgaa40-6-1-4', '', '6.1.4', 118, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule060104', NULL, 1, 1, '', b'0'),
('Rgaa40-6-1-5', '', '6.1.5', 119, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule060105', NULL, 1, 1, '', b'0'),
('Rgaa40-6-2-1', '', '6.2.1', 120, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule060201', NULL, 1, 1, '', b'0'),
('Rgaa40-7-1-1', '', '7.1.1', 121, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule070101', NULL, 1, 1, '', b'0'),
('Rgaa40-7-1-2', '', '7.1.2', 122, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule070102', NULL, 1, 1, '', b'0'),
('Rgaa40-7-1-3', '', '7.1.3', 123, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule070103', NULL, 1, 1, '', b'0'),
('Rgaa40-7-2-1', '', '7.2.1', 124, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule070201', NULL, 1, 1, '', b'0'),
('Rgaa40-7-2-2', '', '7.2.2', 125, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule070202', NULL, 1, 1, '', b'0'),
('Rgaa40-7-3-1', '', '7.3.1', 126, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule070301', NULL, 1, 1, '', b'0'),
('Rgaa40-7-3-2', '', '7.3.2', 127, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule070302', NULL, 1, 1, '', b'0'),
('Rgaa40-7-4-1', '', '7.4.1', 128, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule070401', NULL, 1, 1, '', b'0'),
('Rgaa40-7-5-1', '', '7.5.1', 129, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule070501', NULL, 2, 1, '', b'0'),
('Rgaa40-7-5-2', '', '7.5.2', 130, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule070502', NULL, 2, 1, '', b'0'),
('Rgaa40-7-5-3', '', '7.5.3', 131, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule070503', NULL, 2, 1, '', b'0'),
('Rgaa40-8-1-1', '', '8.1.1', 132, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule080101', NULL, 1, 1, '', b'0'),
('Rgaa40-8-1-2', '', '8.1.2', 133, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule080102', NULL, 1, 1, '', b'0'),
('Rgaa40-8-1-3', '', '8.1.3', 134, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule080103', NULL, 1, 1, '', b'0'),
('Rgaa40-8-2-1', '', '8.2.1', 135, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule080201', NULL, 1, 1, '', b'0'),
('Rgaa40-8-3-1', '', '8.3.1', 136, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule080301', NULL, 1, 1, '', b'0'),
('Rgaa40-8-4-1', '', '8.4.1', 137, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule080401', NULL, 1, 1, '', b'0'),
('Rgaa40-8-5-1', '', '8.5.1', 138, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule080501', NULL, 1, 1, '', b'0'),
('Rgaa40-8-6-1', '', '8.6.1', 139, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule080601', NULL, 1, 1, '', b'0'),
('Rgaa40-8-7-1', '', '8.7.1', 140, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule080701', NULL, 2, 1, '', b'0'),
('Rgaa40-8-8-1', '', '8.8.1', 141, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule080801', NULL, 2, 1, '', b'0'),
('Rgaa40-8-9-1', '', '8.9.1', 142, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule080901', NULL, 1, 1, '', b'0'),
('Rgaa40-8-10-1', '', '8.10.1', 143, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule081001', NULL, 1, 1, '', b'0'),
('Rgaa40-8-10-2', '', '8.10.2', 144, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule081002', NULL, 1, 1, '', b'0'),
('Rgaa40-9-1-1', '', '9.1.1', 145, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule090101', NULL, 1, 1, '', b'0'),
('Rgaa40-9-1-2', '', '9.1.2', 146, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule090102', NULL, 1, 1, '', b'0'),
('Rgaa40-9-1-3', '', '9.1.3', 147, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule090103', NULL, 1, 1, '', b'0'),
('Rgaa40-9-2-1', '', '9.2.1', 148, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule090201', NULL, 1, 1, '', b'0'),
('Rgaa40-9-3-1', '', '9.3.1', 149, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule090301', NULL, 1, 1, '', b'0'),
('Rgaa40-9-3-2', '', '9.3.2', 150, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule090302', NULL, 1, 1, '', b'0'),
('Rgaa40-9-3-3', '', '9.3.3', 151, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule090303', NULL, 1, 1, '', b'0'),
('Rgaa40-9-4-1', '', '9.4.1', 152, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule090401', NULL, 1, 1, '', b'0'),
('Rgaa40-9-4-2', '', '9.4.2', 153, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule090402', NULL, 1, 1, '', b'0'),
('Rgaa40-10-1-1', '', '10.1.1', 154, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule100101', NULL, 1, 1, '', b'0'),
('Rgaa40-10-1-2', '', '10.1.2', 155, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule100102', NULL, 1, 1, '', b'0'),
('Rgaa40-10-1-3', '', '10.1.3', 156, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule100103', NULL, 1, 1, '', b'0'),
('Rgaa40-10-2-1', '', '10.2.1', 157, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule100201', NULL, 1, 1, '', b'0'),
('Rgaa40-10-3-1', '', '10.3.1', 158, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule100301', NULL, 1, 1, '', b'0'),
('Rgaa40-10-4-1', '', '10.4.1', 159, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule100401', NULL, 2, 1, '', b'0'),
('Rgaa40-10-4-2', '', '10.4.2', 160, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule100402', NULL, 2, 1, '', b'0'),
('Rgaa40-10-4-3', '', '10.4.3', 161, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule100403', NULL, 2, 1, '', b'0'),
('Rgaa40-10-5-1', '', '10.5.1', 162, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule100501', NULL, 2, 1, '', b'0'),
('Rgaa40-10-5-2', '', '10.5.2', 163, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule100502', NULL, 2, 1, '', b'0'),
('Rgaa40-10-5-3', '', '10.5.3', 164, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule100503', NULL, 2, 1, '', b'0'),
('Rgaa40-10-6-1', '', '10.6.1', 165, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule100601', NULL, 1, 1, '', b'0'),
('Rgaa40-10-7-1', '', '10.7.1', 166, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule100701', NULL, 1, 1, '', b'0'),
('Rgaa40-10-8-1', '', '10.8.1', 167, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule100801', NULL, 1, 1, '', b'0'),
('Rgaa40-10-9-1', '', '10.9.1', 168, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule100901', NULL, 1, 1, '', b'0'),
('Rgaa40-10-9-2', '', '10.9.2', 169, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule100902', NULL, 1, 1, '', b'0'),
('Rgaa40-10-9-3', '', '10.9.3', 170, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule100903', NULL, 1, 1, '', b'0'),
('Rgaa40-10-9-4', '', '10.9.4', 171, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule100904', NULL, 1, 1, '', b'0'),
('Rgaa40-10-10-1', '', '10.10.1', 172, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule101001', NULL, 1, 1, '', b'0'),
('Rgaa40-10-10-2', '', '10.10.2', 173, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule101002', NULL, 1, 1, '', b'0'),
('Rgaa40-10-10-3', '', '10.10.3', 174, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule101003', NULL, 1, 1, '', b'0'),
('Rgaa40-10-10-4', '', '10.10.4', 175, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule101004', NULL, 1, 1, '', b'0'),
('Rgaa40-10-11-1', '', '10.11.1', 176, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule101101', NULL, 2, 1, '', b'0'),
('Rgaa40-10-11-2', '', '10.11.2', 177, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule101102', NULL, 2, 1, '', b'0'),
('Rgaa40-10-12-1', '', '10.12.1', 178, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule101201', NULL, 2, 1, '', b'0'),
('Rgaa40-10-13-1', '', '10.13.1', 179, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule101301', NULL, 2, 1, '', b'0'),
('Rgaa40-10-13-2', '', '10.13.2', 180, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule101302', NULL, 2, 1, '', b'0'),
('Rgaa40-10-13-3', '', '10.13.3', 181, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule101303', NULL, 2, 1, '', b'0'),
('Rgaa40-10-14-1', '', '10.14.1', 182, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule101401', NULL, 1, 1, '', b'0'),
('Rgaa40-10-14-2', '', '10.14.2', 183, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule101402', NULL, 1, 1, '', b'0'),
('Rgaa40-11-1-1', '', '11.1.1', 184, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule110101', NULL, 1, 1, '', b'0'),
('Rgaa40-11-1-2', '', '11.1.2', 185, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule110102', NULL, 1, 1, '', b'0'),
('Rgaa40-11-1-3', '', '11.1.3', 186, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule110103', NULL, 1, 1, '', b'0'),
('Rgaa40-11-2-1', '', '11.2.1', 187, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule110201', NULL, 1, 1, '', b'0'),
('Rgaa40-11-2-2', '', '11.2.2', 188, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule110202', NULL, 1, 1, '', b'0'),
('Rgaa40-11-2-3', '', '11.2.3', 189, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule110203', NULL, 1, 1, '', b'0'),
('Rgaa40-11-2-4', '', '11.2.4', 190, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule110204', NULL, 1, 1, '', b'0'),
('Rgaa40-11-2-5', '', '11.2.5', 191, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule110205', NULL, 1, 1, '', b'0'),
('Rgaa40-11-2-6', '', '11.2.6', 192, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule110206', NULL, 1, 1, '', b'0'),
('Rgaa40-11-3-1', '', '11.3.1', 193, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule110301', NULL, 2, 1, '', b'0'),
('Rgaa40-11-3-2', '', '11.3.2', 194, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule110302', NULL, 2, 1, '', b'0'),
('Rgaa40-11-4-1', '', '11.4.1', 195, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule110401', NULL, 2, 1, '', b'0'),
('Rgaa40-11-4-2', '', '11.4.2', 196, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule110402', NULL, 2, 1, '', b'0'),
('Rgaa40-11-4-3', '', '11.4.3', 197, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule110403', NULL, 2, 1, '', b'0'),
('Rgaa40-11-5-1', '', '11.5.1', 198, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule110501', NULL, 1, 1, '', b'0'),
('Rgaa40-11-6-1', '', '11.6.1', 199, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule110601', NULL, 1, 1, '', b'0'),
('Rgaa40-11-7-1', '', '11.7.1', 200, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule110701', NULL, 1, 1, '', b'0'),
('Rgaa40-11-8-1', '', '11.8.1', 201, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule110801', NULL, 1, 1, '', b'0'),
('Rgaa40-11-8-2', '', '11.8.2', 202, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule110802', NULL, 1, 1, '', b'0'),
('Rgaa40-11-8-3', '', '11.8.3', 203, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule110803', NULL, 1, 1, '', b'0'),
('Rgaa40-11-9-1', '', '11.9.1', 204, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule110901', NULL, 1, 1, '', b'0'),
('Rgaa40-11-9-2', '', '11.9.2', 205, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule110902', NULL, 1, 1, '', b'0'),
('Rgaa40-11-10-1', '', '11.10.1', 206, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule111001', NULL, 1, 1, '', b'0'),
('Rgaa40-11-10-2', '', '11.10.2', 207, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule111002', NULL, 1, 1, '', b'0'),
('Rgaa40-11-10-3', '', '11.10.3', 208, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule111003', NULL, 1, 1, '', b'0'),
('Rgaa40-11-10-4', '', '11.10.4', 209, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule111004', NULL, 1, 1, '', b'0'),
('Rgaa40-11-10-5', '', '11.10.5', 210, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule111005', NULL, 1, 1, '', b'0'),
('Rgaa40-11-10-6', '', '11.10.6', 211, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule111006', NULL, 1, 1, '', b'0'),
('Rgaa40-11-10-7', '', '11.10.7', 212, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule111007', NULL, 1, 1, '', b'0'),
('Rgaa40-11-11-1', '', '11.11.1', 213, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule111101', NULL, 2, 1, '', b'0'),
('Rgaa40-11-11-2', '', '11.11.2', 214, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule111102', NULL, 2, 1, '', b'0'),
('Rgaa40-11-12-1', '', '11.12.1', 215, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule111201', NULL, 2, 1, '', b'0'),
('Rgaa40-11-12-2', '', '11.12.2', 216, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule111202', NULL, 2, 1, '', b'0'),
('Rgaa40-11-13-1', '', '11.13.1', 217, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule111301', NULL, 2, 1, '', b'0'),
('Rgaa40-12-1-1', '', '12.1.1', 218, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule120101', NULL, 2, 1, '', b'0'),
('Rgaa40-12-2-1', '', '12.2.1', 219, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule120201', NULL, 2, 1, '', b'0'),
('Rgaa40-12-3-1', '', '12.3.1', 220, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule120301', NULL, 2, 1, '', b'0'),
('Rgaa40-12-3-2', '', '12.3.2', 221, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule120302', NULL, 2, 1, '', b'0'),
('Rgaa40-12-3-3', '', '12.3.3', 222, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule120303', NULL, 2, 1, '', b'0'),
('Rgaa40-12-4-1', '', '12.4.1', 223, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule120401', NULL, 2, 1, '', b'0'),
('Rgaa40-12-4-2', '', '12.4.2', 224, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule120402', NULL, 2, 1, '', b'0'),
('Rgaa40-12-4-3', '', '12.4.3', 225, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule120403', NULL, 2, 1, '', b'0'),
('Rgaa40-12-5-1', '', '12.5.1', 226, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule120501', NULL, 2, 1, '', b'0'),
('Rgaa40-12-5-2', '', '12.5.2', 227, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule120502', NULL, 2, 1, '', b'0'),
('Rgaa40-12-5-3', '', '12.5.3', 228, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule120503', NULL, 2, 1, '', b'0'),
('Rgaa40-12-6-1', '', '12.6.1', 229, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule120601', NULL, 1, 1, '', b'0'),
('Rgaa40-12-7-1', '', '12.7.1', 230, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule120701', NULL, 1, 1, '', b'0'),
('Rgaa40-12-7-2', '', '12.7.2', 231, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule120702', NULL, 1, 1, '', b'0'),
('Rgaa40-12-8-1', '', '12.8.1', 232, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule120801', NULL, 1, 1, '', b'0'),
('Rgaa40-12-8-2', '', '12.8.2', 233, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule120802', NULL, 1, 1, '', b'0'),
('Rgaa40-12-9-1', '', '12.9.1', 234, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule120901', NULL, 1, 1, '', b'0'),
('Rgaa40-12-10-1', '', '12.10.1', 235, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule121001', NULL, 1, 1, '', b'0'),
('Rgaa40-12-11-1', '', '12.11.1', 236, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule121101', NULL, 1, 1, '', b'0'),
('Rgaa40-13-1-1', '', '13.1.1', 237, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule130101', NULL, 1, 1, '', b'0'),
('Rgaa40-13-1-2', '', '13.1.2', 238, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule130102', NULL, 1, 1, '', b'0'),
('Rgaa40-13-1-3', '', '13.1.3', 239, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule130103', NULL, 1, 1, '', b'0'),
('Rgaa40-13-1-4', '', '13.1.4', 240, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule130104', NULL, 1, 1, '', b'0'),
('Rgaa40-13-2-1', '', '13.2.1', 241, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule130201', NULL, 1, 1, '', b'0'),
('Rgaa40-13-3-1', '', '13.3.1', 242, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule130301', NULL, 1, 1, '', b'0'),
('Rgaa40-13-4-1', '', '13.4.1', 243, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule130401', NULL, 1, 1, '', b'0'),
('Rgaa40-13-5-1', '', '13.5.1', 244, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule130501', NULL, 1, 1, '', b'0'),
('Rgaa40-13-6-1', '', '13.6.1', 245, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule130601', NULL, 1, 1, '', b'0'),
('Rgaa40-13-7-1', '', '13.7.1', 246, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule130701', NULL, 1, 1, '', b'0'),
('Rgaa40-13-7-2', '', '13.7.2', 247, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule130702', NULL, 1, 1, '', b'0'),
('Rgaa40-13-7-3', '', '13.7.3', 248, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule130703', NULL, 1, 1, '', b'0'),
('Rgaa40-13-8-1', '', '13.8.1', 249, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule130801', NULL, 1, 1, '', b'0'),
('Rgaa40-13-8-2', '', '13.8.2', 250, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule130802', NULL, 1, 1, '', b'0'),
('Rgaa40-13-9-1', '', '13.9.1', 251, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule130901', NULL, 2, 1, '', b'0'),
('Rgaa40-13-10-1', '', '13.10.1', 252, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule131001', NULL, 1, 1, '', b'0'),
('Rgaa40-13-10-2', '', '13.10.2', 253, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule131002', NULL, 1, 1, '', b'0'),
('Rgaa40-13-11-1', '', '13.11.1', 254, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule131101', NULL, 1, 1, '', b'0'),
('Rgaa40-13-12-1', '', '13.12.1', 255, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule131201', NULL, 1, 1, '', b'0'),
('Rgaa40-13-12-2', '', '13.12.2', 256, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule131202', NULL, 1, 1, '', b'0'),
('Rgaa40-13-12-3', '', '13.12.3', 257, '1.0', 'rgaa40', 'org.asqatasun.rules.rgaa40.Rgaa40Rule131203', NULL, 1, 1, '', b'0');

UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-1-1') WHERE `Cd_Test` LIKE 'Rgaa40-1-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-1-2') WHERE `Cd_Test` LIKE 'Rgaa40-1-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-1-3') WHERE `Cd_Test` LIKE 'Rgaa40-1-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-1-4') WHERE `Cd_Test` LIKE 'Rgaa40-1-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-1-5') WHERE `Cd_Test` LIKE 'Rgaa40-1-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-1-6') WHERE `Cd_Test` LIKE 'Rgaa40-1-6-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-1-7') WHERE `Cd_Test` LIKE 'Rgaa40-1-7-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-1-8') WHERE `Cd_Test` LIKE 'Rgaa40-1-8-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-1-9') WHERE `Cd_Test` LIKE 'Rgaa40-1-9-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-2-1') WHERE `Cd_Test` LIKE 'Rgaa40-2-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-2-2') WHERE `Cd_Test` LIKE 'Rgaa40-2-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-3-1') WHERE `Cd_Test` LIKE 'Rgaa40-3-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-3-2') WHERE `Cd_Test` LIKE 'Rgaa40-3-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-3-3') WHERE `Cd_Test` LIKE 'Rgaa40-3-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-4-1') WHERE `Cd_Test` LIKE 'Rgaa40-4-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-4-2') WHERE `Cd_Test` LIKE 'Rgaa40-4-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-4-3') WHERE `Cd_Test` LIKE 'Rgaa40-4-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-4-4') WHERE `Cd_Test` LIKE 'Rgaa40-4-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-4-5') WHERE `Cd_Test` LIKE 'Rgaa40-4-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-4-6') WHERE `Cd_Test` LIKE 'Rgaa40-4-6-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-4-7') WHERE `Cd_Test` LIKE 'Rgaa40-4-7-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-4-8') WHERE `Cd_Test` LIKE 'Rgaa40-4-8-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-4-9') WHERE `Cd_Test` LIKE 'Rgaa40-4-9-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-4-10') WHERE `Cd_Test` LIKE 'Rgaa40-4-10-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-4-11') WHERE `Cd_Test` LIKE 'Rgaa40-4-11-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-4-12') WHERE `Cd_Test` LIKE 'Rgaa40-4-12-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-4-13') WHERE `Cd_Test` LIKE 'Rgaa40-4-13-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-5-1') WHERE `Cd_Test` LIKE 'Rgaa40-5-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-5-2') WHERE `Cd_Test` LIKE 'Rgaa40-5-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-5-3') WHERE `Cd_Test` LIKE 'Rgaa40-5-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-5-4') WHERE `Cd_Test` LIKE 'Rgaa40-5-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-5-5') WHERE `Cd_Test` LIKE 'Rgaa40-5-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-5-6') WHERE `Cd_Test` LIKE 'Rgaa40-5-6-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-5-7') WHERE `Cd_Test` LIKE 'Rgaa40-5-7-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-5-8') WHERE `Cd_Test` LIKE 'Rgaa40-5-8-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-6-1') WHERE `Cd_Test` LIKE 'Rgaa40-6-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-6-2') WHERE `Cd_Test` LIKE 'Rgaa40-6-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-7-1') WHERE `Cd_Test` LIKE 'Rgaa40-7-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-7-2') WHERE `Cd_Test` LIKE 'Rgaa40-7-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-7-3') WHERE `Cd_Test` LIKE 'Rgaa40-7-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-7-4') WHERE `Cd_Test` LIKE 'Rgaa40-7-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-7-5') WHERE `Cd_Test` LIKE 'Rgaa40-7-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-8-1') WHERE `Cd_Test` LIKE 'Rgaa40-8-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-8-2') WHERE `Cd_Test` LIKE 'Rgaa40-8-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-8-3') WHERE `Cd_Test` LIKE 'Rgaa40-8-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-8-4') WHERE `Cd_Test` LIKE 'Rgaa40-8-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-8-5') WHERE `Cd_Test` LIKE 'Rgaa40-8-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-8-6') WHERE `Cd_Test` LIKE 'Rgaa40-8-6-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-8-7') WHERE `Cd_Test` LIKE 'Rgaa40-8-7-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-8-8') WHERE `Cd_Test` LIKE 'Rgaa40-8-8-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-8-9') WHERE `Cd_Test` LIKE 'Rgaa40-8-9-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-8-10') WHERE `Cd_Test` LIKE 'Rgaa40-8-10-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-9-1') WHERE `Cd_Test` LIKE 'Rgaa40-9-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-9-2') WHERE `Cd_Test` LIKE 'Rgaa40-9-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-9-3') WHERE `Cd_Test` LIKE 'Rgaa40-9-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-9-4') WHERE `Cd_Test` LIKE 'Rgaa40-9-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-10-1') WHERE `Cd_Test` LIKE 'Rgaa40-10-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-10-2') WHERE `Cd_Test` LIKE 'Rgaa40-10-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-10-3') WHERE `Cd_Test` LIKE 'Rgaa40-10-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-10-4') WHERE `Cd_Test` LIKE 'Rgaa40-10-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-10-5') WHERE `Cd_Test` LIKE 'Rgaa40-10-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-10-6') WHERE `Cd_Test` LIKE 'Rgaa40-10-6-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-10-7') WHERE `Cd_Test` LIKE 'Rgaa40-10-7-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-10-8') WHERE `Cd_Test` LIKE 'Rgaa40-10-8-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-10-9') WHERE `Cd_Test` LIKE 'Rgaa40-10-9-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-10-10') WHERE `Cd_Test` LIKE 'Rgaa40-10-10-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-10-11') WHERE `Cd_Test` LIKE 'Rgaa40-10-11-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-10-12') WHERE `Cd_Test` LIKE 'Rgaa40-10-12-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-10-13') WHERE `Cd_Test` LIKE 'Rgaa40-10-13-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-10-14') WHERE `Cd_Test` LIKE 'Rgaa40-10-14-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-11-1') WHERE `Cd_Test` LIKE 'Rgaa40-11-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-11-2') WHERE `Cd_Test` LIKE 'Rgaa40-11-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-11-3') WHERE `Cd_Test` LIKE 'Rgaa40-11-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-11-4') WHERE `Cd_Test` LIKE 'Rgaa40-11-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-11-5') WHERE `Cd_Test` LIKE 'Rgaa40-11-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-11-6') WHERE `Cd_Test` LIKE 'Rgaa40-11-6-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-11-7') WHERE `Cd_Test` LIKE 'Rgaa40-11-7-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-11-8') WHERE `Cd_Test` LIKE 'Rgaa40-11-8-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-11-9') WHERE `Cd_Test` LIKE 'Rgaa40-11-9-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-11-10') WHERE `Cd_Test` LIKE 'Rgaa40-11-10-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-11-11') WHERE `Cd_Test` LIKE 'Rgaa40-11-11-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-11-12') WHERE `Cd_Test` LIKE 'Rgaa40-11-12-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-11-13') WHERE `Cd_Test` LIKE 'Rgaa40-11-13-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-12-1') WHERE `Cd_Test` LIKE 'Rgaa40-12-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-12-2') WHERE `Cd_Test` LIKE 'Rgaa40-12-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-12-3') WHERE `Cd_Test` LIKE 'Rgaa40-12-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-12-4') WHERE `Cd_Test` LIKE 'Rgaa40-12-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-12-5') WHERE `Cd_Test` LIKE 'Rgaa40-12-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-12-6') WHERE `Cd_Test` LIKE 'Rgaa40-12-6-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-12-7') WHERE `Cd_Test` LIKE 'Rgaa40-12-7-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-12-8') WHERE `Cd_Test` LIKE 'Rgaa40-12-8-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-12-9') WHERE `Cd_Test` LIKE 'Rgaa40-12-9-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-12-10') WHERE `Cd_Test` LIKE 'Rgaa40-12-10-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-12-11') WHERE `Cd_Test` LIKE 'Rgaa40-12-11-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-13-1') WHERE `Cd_Test` LIKE 'Rgaa40-13-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-13-2') WHERE `Cd_Test` LIKE 'Rgaa40-13-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-13-3') WHERE `Cd_Test` LIKE 'Rgaa40-13-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-13-4') WHERE `Cd_Test` LIKE 'Rgaa40-13-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-13-5') WHERE `Cd_Test` LIKE 'Rgaa40-13-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-13-6') WHERE `Cd_Test` LIKE 'Rgaa40-13-6-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-13-7') WHERE `Cd_Test` LIKE 'Rgaa40-13-7-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-13-8') WHERE `Cd_Test` LIKE 'Rgaa40-13-8-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-13-9') WHERE `Cd_Test` LIKE 'Rgaa40-13-9-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-13-10') WHERE `Cd_Test` LIKE 'Rgaa40-13-10-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-13-11') WHERE `Cd_Test` LIKE 'Rgaa40-13-11-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa40-13-12') WHERE `Cd_Test` LIKE 'Rgaa40-13-12-%';

INSERT IGNORE INTO `PARAMETER` (`Id_Parameter_Element`, `Parameter_Value`, `Is_Default`) VALUES
(5, 'Rgaa40;LEVEL_1', b'0'),
(5, 'Rgaa40;LEVEL_2', b'0'),
(5, 'Rgaa40;LEVEL_3', b'0');

-- Set Rule_Design_Url (markdown format, gitlab.com)
UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('01.Images/Rule-',                       SUBSTRING(`Cd_Test`,8), '.md') WHERE `Cd_Test` LIKE 'Rgaa40-1-%';
UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('02.Frames/Rule-',                       SUBSTRING(`Cd_Test`,8), '.md') WHERE `Cd_Test` LIKE 'Rgaa40-2-%';
UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('03.Colours/Rule-',                      SUBSTRING(`Cd_Test`,8), '.md') WHERE `Cd_Test` LIKE 'Rgaa40-3-%';
UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('04.Multimedia/Rule-',                   SUBSTRING(`Cd_Test`,8), '.md') WHERE `Cd_Test` LIKE 'Rgaa40-4-%';
UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('05.Tables/Rule-',                       SUBSTRING(`Cd_Test`,8), '.md') WHERE `Cd_Test` LIKE 'Rgaa40-5-%';
UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('06.Links/Rule-',                        SUBSTRING(`Cd_Test`,8), '.md') WHERE `Cd_Test` LIKE 'Rgaa40-6-%';
UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('07.Scripts/Rule-',                      SUBSTRING(`Cd_Test`,8), '.md') WHERE `Cd_Test` LIKE 'Rgaa40-7-%';
UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('08.Mandatory_elements/Rule-',           SUBSTRING(`Cd_Test`,8), '.md') WHERE `Cd_Test` LIKE 'Rgaa40-8-%';
UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('09.Structure_of_information/Rule-',     SUBSTRING(`Cd_Test`,8), '.md') WHERE `Cd_Test` LIKE 'Rgaa40-9-%';
UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('10.Presentation_of_information/Rule-',  SUBSTRING(`Cd_Test`,8), '.md') WHERE `Cd_Test` LIKE 'Rgaa40-10-%';
UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('11.Forms/Rule-',                        SUBSTRING(`Cd_Test`,8), '.md') WHERE `Cd_Test` LIKE 'Rgaa40-11-%';
UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('12.Navigation/Rule-',                   SUBSTRING(`Cd_Test`,8), '.md') WHERE `Cd_Test` LIKE 'Rgaa40-12-%';
UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('13.Consultation/Rule-',                 SUBSTRING(`Cd_Test`,8), '.md') WHERE `Cd_Test` LIKE 'Rgaa40-13-%';
UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('https://gitlab.com/asqatasun/Asqatasun/-/blob/master/documentation/en/90_Rules/rgaa4.0/', `Rule_Design_Url`)     WHERE `Cd_Test` LIKE 'Rgaa40-%';

-- Set Rule_Design_Url (html format, documentation website)
    -- UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('01.Images/Rule-',                       SUBSTRING(`Cd_Test`,8), '.html') WHERE `Cd_Test` LIKE 'Rgaa40-1-%';
    -- UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('02.Frames/Rule-',                       SUBSTRING(`Cd_Test`,8), '.html') WHERE `Cd_Test` LIKE 'Rgaa40-2-%';
    -- UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('03.Colours/Rule-',                      SUBSTRING(`Cd_Test`,8), '.html') WHERE `Cd_Test` LIKE 'Rgaa40-3-%';
    -- UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('04.Multimedia/Rule-',                   SUBSTRING(`Cd_Test`,8), '.html') WHERE `Cd_Test` LIKE 'Rgaa40-4-%';
    -- UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('05.Tables/Rule-',                       SUBSTRING(`Cd_Test`,8), '.html') WHERE `Cd_Test` LIKE 'Rgaa40-5-%';
    -- UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('06.Links/Rule-',                        SUBSTRING(`Cd_Test`,8), '.html') WHERE `Cd_Test` LIKE 'Rgaa40-6-%';
    -- UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('07.Scripts/Rule-',                      SUBSTRING(`Cd_Test`,8), '.html') WHERE `Cd_Test` LIKE 'Rgaa40-7-%';
    -- UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('08.Mandatory_elements/Rule-',           SUBSTRING(`Cd_Test`,8), '.html') WHERE `Cd_Test` LIKE 'Rgaa40-8-%';
    -- UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('09.Structure_of_information/Rule-',     SUBSTRING(`Cd_Test`,8), '.html') WHERE `Cd_Test` LIKE 'Rgaa40-9-%';
    -- UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('10.Presentation_of_information/Rule-',  SUBSTRING(`Cd_Test`,8), '.html') WHERE `Cd_Test` LIKE 'Rgaa40-10-%';
    -- UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('11.Forms/Rule-',                        SUBSTRING(`Cd_Test`,8), '.html') WHERE `Cd_Test` LIKE 'Rgaa40-11-%';
    -- UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('12.Navigation/Rule-',                   SUBSTRING(`Cd_Test`,8), '.html') WHERE `Cd_Test` LIKE 'Rgaa40-12-%';
    -- UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('13.Consultation/Rule-',                 SUBSTRING(`Cd_Test`,8), '.html') WHERE `Cd_Test` LIKE 'Rgaa40-13-%';
    -- UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('https://doc.asqatasun.org/en/90_Rules/rgaa4.0/', `Rule_Design_Url`)     WHERE `Cd_Test` LIKE 'Rgaa40-%';

-- Disable all rgaa4 tests
UPDATE TEST SET `No_Process`=b'1' WHERE Cd_Test LIKE ('Rgaa40-%');

-- Enable only implemented tests
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-1-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-1-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-1-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-1-4';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-1-5';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-1-6';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-1-7';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-1-8';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-2-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-2-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-2-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-2-4';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-2-5';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-2-6';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-3-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-3-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-3-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-3-4';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-3-5';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-3-6';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-3-7';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-3-8';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-3-9';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-4-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-4-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-4-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-4-4';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-4-5';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-4-6';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-4-7';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-5-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-5-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-6-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-6-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-6-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-6-4';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-6-5';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-6-6';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-6-7';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-6-8';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-6-9';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-6-10';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-7-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-7-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-7-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-7-4';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-7-5';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-7-6';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-8-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-8-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-8-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-8-4';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-8-5';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-9-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-9-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-9-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-9-4';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-1-9-5';
UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-2-1-1';
UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-2-2-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-3-1-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-3-1-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-3-1-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-3-1-4';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-3-1-5';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-3-1-6';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-3-2-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-3-2-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-3-2-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-3-2-4';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-3-2-5';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-3-3-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-3-3-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-3-3-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-3-3-4';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-4-1-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-4-1-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-4-1-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-4-2-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-4-2-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-4-2-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-4-3-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-4-3-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-4-4-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-4-5-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-4-5-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-4-6-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-4-6-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-4-7-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-4-8-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-4-8-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-4-9-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-4-10-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-4-11-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-4-11-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-4-11-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-4-12-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-4-12-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-4-13-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-4-13-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-5-1-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-5-2-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-5-3-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-5-4-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-5-5-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-5-6-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-5-6-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-5-6-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-5-7-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-5-7-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-5-7-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-5-7-4';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-5-7-5';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-5-8-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-6-1-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-6-1-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-6-1-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-6-1-4';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-6-1-5';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-6-2-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-7-1-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-7-1-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-7-1-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-7-2-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-7-2-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-7-3-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-7-3-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-7-4-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-7-5-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-7-5-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-7-5-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-8-1-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-8-1-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-8-1-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-8-2-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-8-3-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-8-4-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-8-5-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-8-6-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-8-7-1';
UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-8-8-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-8-9-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-8-10-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-8-10-2';
UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-9-1-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-9-1-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-9-1-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-9-2-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-9-3-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-9-3-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-9-3-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-9-4-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-9-4-2';
UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-10-1-1';
UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-10-1-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-10-1-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-10-2-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-10-3-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-10-4-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-10-4-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-10-4-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-10-5-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-10-5-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-10-5-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-10-6-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-10-7-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-10-8-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-10-9-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-10-9-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-10-9-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-10-9-4';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-10-10-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-10-10-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-10-10-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-10-10-4';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-10-11-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-10-11-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-10-12-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-10-13-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-10-13-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-10-13-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-10-14-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-10-14-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-1-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-1-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-1-3';
UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-2-1';
UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-2-2';
UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-2-3';
UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-2-4';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-2-5';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-2-6';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-3-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-3-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-4-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-4-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-4-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-5-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-6-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-7-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-8-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-8-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-8-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-9-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-9-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-10-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-10-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-10-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-10-4';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-10-5';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-10-6';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-10-7';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-11-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-11-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-12-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-12-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-11-13-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-12-1-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-12-2-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-12-3-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-12-3-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-12-3-3';
UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-12-4-1';
UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-12-4-2';
UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-12-4-3';
UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-12-5-1';
UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-12-5-2';
UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-12-5-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-12-6-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-12-7-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-12-7-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-12-8-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-12-8-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-12-9-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-12-10-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-12-11-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-13-1-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-13-1-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-13-1-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-13-1-4';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-13-2-1';
UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-13-3-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-13-4-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-13-5-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-13-6-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-13-7-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-13-7-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-13-7-3';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-13-8-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-13-8-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-13-9-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-13-10-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-13-10-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-13-11-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-13-12-1';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-13-12-2';
-- UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa40-13-12-3';
