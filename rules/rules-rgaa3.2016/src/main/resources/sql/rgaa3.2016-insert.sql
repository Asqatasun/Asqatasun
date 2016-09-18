INSERT IGNORE INTO `REFERENCE` (`Cd_Reference`, `Description`, `Label`, `Url`, `Rank`, `Id_Default_Level`) VALUES
('Rgaa32016', NULL, 'RGAA 3.2016', '', 2000, 1);

INSERT IGNORE INTO `TGSI_REFERENTIAL` (`Code`, `Label`) VALUES
('Rgaa32016', 'RGAA 3.2016');

INSERT IGNORE INTO `THEME` (`Cd_Theme`, `Description`, `Label`, `Rank`) VALUES
('Rgaa32016-1', NULL, 'Images', 1),
('Rgaa32016-2', NULL, 'Cadres', 2),
('Rgaa32016-3', NULL, 'Couleurs', 3),
('Rgaa32016-4', NULL, 'Multimédia', 4),
('Rgaa32016-5', NULL, 'Tableaux', 5),
('Rgaa32016-6', NULL, 'Liens', 6),
('Rgaa32016-7', NULL, 'Scripts', 7),
('Rgaa32016-8', NULL, 'Éléments obligatoires', 8),
('Rgaa32016-9', NULL, 'Structuration de l’information', 9),
('Rgaa32016-10', NULL, 'Présentation de l’information', 10),
('Rgaa32016-11', NULL, 'Formulaires', 11),
('Rgaa32016-12', NULL, 'Navigation', 12),
('Rgaa32016-13', NULL, 'Consultation', 13);

INSERT IGNORE INTO `CRITERION` (`Cd_Criterion`, `Description`, `Label`, `Url`, `Rank`) VALUES
('Rgaa32016-1-1', 'Chaque image a-t-elle une alternative textuelle ?', '1.1', '', 1),
('Rgaa32016-1-2', 'Pour chaque image de décoration ayant une alternative textuelle, cette alternative est-elle vide ?', '1.2', '', 2),
('Rgaa32016-1-3', 'Pour chaque image porteuse d’information ayant une alternative textuelle, cette alternative est-elle pertinente (hors cas particuliers) ?', '1.3', '', 3),
('Rgaa32016-1-4', 'Pour chaque image utilisée comme CAPTCHA ou comme image-test, ayant une alternative textuelle, cette alternative permet-elle d’identifier la nature et la fonction de l’image ?', '1.4', '', 4),
('Rgaa32016-1-5', 'Pour chaque image utilisée comme CAPTCHA, une solution d’accès alternatif au contenu ou à la fonction du CAPTCHA est-elle présente ?', '1.5', '', 5),
('Rgaa32016-1-6', 'Chaque image porteuse d’information a-t-elle, si nécessaire, une description détaillée ?', '1.6', '', 6),
('Rgaa32016-1-7', 'Pour chaque image porteuse d’information ayant une description détaillée, cette description est-elle pertinente ?', '1.7', '', 7),
('Rgaa32016-1-8', 'Chaque image texte porteuse d’information, en l’absence d’un mécanisme de remplacement, doit si possible être remplacée par du texte stylé. Cette règle est-elle respectée (hors cas particuliers) ?', '1.8', '', 8),
('Rgaa32016-1-9', 'Chaque image texte porteuse d’information, doit si possible être remplacée par du texte stylé. Cette règle est-elle respectée (hors cas particuliers) ?', '1.9', '', 9),
('Rgaa32016-1-10', 'Chaque légende d’image est-elle, si nécessaire, correctement reliée à l’image correspondante ?', '1.10', '', 10),
('Rgaa32016-2-1', 'Chaque cadre en ligne a-t-il un titre de cadre ?', '2.1', '', 11),
('Rgaa32016-2-2', 'Pour chaque cadre en ligne ayant un titre de cadre, ce titre de cadre est-il pertinent ?', '2.2', '', 12),
('Rgaa32016-3-1', 'Dans chaque page Web, l’information ne doit pas être donnée uniquement par la couleur. Cette règle est-elle respectée ?', '3.1', '', 13),
('Rgaa32016-3-2', 'Dans chaque page Web, l’information ne doit pas être donnée uniquement par la couleur. Cette règle est-elle implémentée de façon pertinente ?', '3.2', '', 14),
('Rgaa32016-3-3', 'Dans chaque page Web, le contraste entre la couleur du texte et la couleur de son arrière-plan est-il suffisamment élevé (hors cas particuliers) ?', '3.3', '', 15),
('Rgaa32016-3-4', 'Dans chaque page Web, le contraste entre la couleur du texte et la couleur de son arrière-plan est-il amélioré (hors cas particuliers) ?', '3.4', '', 16),
('Rgaa32016-4-1', 'Chaque média temporel pré-enregistré a-t-il, si nécessaire, une transcription textuelle ou une audio-description (hors cas particuliers) ?', '4.1', '', 17),
('Rgaa32016-4-2', 'Pour chaque média temporel pré-enregistré ayant une transcription textuelle ou une audio-description synchronisée, celles-ci sont-elles pertinentes (hors cas particuliers) ?', '4.2', '', 18),
('Rgaa32016-4-3', 'Chaque média temporel synchronisé pré-enregistré a-t-il, si nécessaire, des sous-titres synchronisés (hors cas particuliers) ?', '4.3', '', 19),
('Rgaa32016-4-4', 'Pour chaque média temporel synchronisé pré-enregistré ayant des sous-titres synchronisés, ces sous-titres sont-ils pertinents ?', '4.4', '', 20),
('Rgaa32016-4-5', 'Chaque média temporel en direct a-t-il, si nécessaire, des sous-titres synchronisés ou une transcription textuelle (hors cas particuliers) ?', '4.5', '', 21),
('Rgaa32016-4-6', 'Pour chaque média temporel en direct ayant des sous-titres synchronisés ou une transcription textuelle, ceux-ci sont-ils pertinents ?', '4.6', '', 22),
('Rgaa32016-4-7', 'Chaque média temporel pré-enregistré a-t-il, si nécessaire, une audio-description synchronisée (hors cas particuliers) ?', '4.7', '', 23),
('Rgaa32016-4-8', 'Pour chaque média temporel pré-enregistré ayant une audio-description synchronisée, celle-ci est-elle pertinente ?', '4.8', '', 24),
('Rgaa32016-4-9', 'Chaque média temporel pré-enregistré a-t-il, si nécessaire, une interprétation en langue des signes (hors cas particuliers) ?', '4.9', '', 25),
('Rgaa32016-4-10', 'Pour chaque média temporel pré-enregistré ayant une interprétation en langue des signes, celle-ci est-elle pertinente ?', '4.10', '', 26),
('Rgaa32016-4-11', 'Chaque média temporel pré-enregistré a-t-il, si nécessaire, une audio-description étendue synchronisée (hors cas particuliers) ?', '4.11', '', 27),
('Rgaa32016-4-12', 'Pour chaque média temporel pré-enregistré ayant une audio-description étendue synchronisée, celle-ci est-elle pertinente ?', '4.12', '', 28),
('Rgaa32016-4-13', 'Chaque média temporel synchronisé ou seulement vidéo a-t-il, si nécessaire, une transcription textuelle (hors cas particuliers) ?', '4.13', '', 29),
('Rgaa32016-4-14', 'Pour chaque média temporel synchronisé ou seulement vidéo, ayant une transcription textuelle, celle-ci est-elle pertinente ?', '4.14', '', 30),
('Rgaa32016-4-15', 'Chaque média temporel est-il clairement identifiable (hors cas particuliers) ?', '4.15', '', 31),
('Rgaa32016-4-16', 'Chaque média non temporel a-t-il, si nécessaire, une alternative (hors cas particuliers) ?', '4.16', '', 32),
('Rgaa32016-4-17', 'Pour chaque média non temporel ayant une alternative, cette alternative est-elle pertinente ?', '4.17', '', 33),
('Rgaa32016-4-18', 'Chaque son déclenché automatiquement est-il contrôlable par l’utilisateur ?', '4.18', '', 34),
('Rgaa32016-4-19', 'Pour chaque média temporel seulement audio pré-enregistré, les dialogues sont-ils suffisamment audibles (hors cas particuliers) ?', '4.19', '', 35),
('Rgaa32016-4-20', 'La consultation de chaque média temporel est-elle, si nécessaire, contrôlable par le clavier et la souris ?', '4.20', '', 36),
('Rgaa32016-4-21', 'La consultation de chaque média non temporel est-elle contrôlable par le clavier et la souris ?', '4.21', '', 37),
('Rgaa32016-4-22', 'Chaque média temporel et non temporel est-il compatible avec les technologies d’assistance (hors cas particuliers) ?', '4.22', '', 38),
('Rgaa32016-5-1', 'Chaque tableau de données complexe a-t-il un résumé ?', '5.1', '', 39),
('Rgaa32016-5-2', 'Pour chaque tableau de données complexe ayant un résumé, celui-ci est-il pertinent ?', '5.2', '', 40),
('Rgaa32016-5-3', 'Pour chaque tableau de mise en forme, le contenu linéarisé reste-t-il compréhensible (hors cas particuliers) ?', '5.3', '', 41),
('Rgaa32016-5-4', 'Chaque tableau de données a-t-il un titre ?', '5.4', '', 42),
('Rgaa32016-5-5', 'Pour chaque tableau de données ayant un titre, celui-ci est-il pertinent ?', '5.5', '', 43),
('Rgaa32016-5-6', 'Pour chaque tableau de données, chaque en-tête de colonnes et chaque en-tête de lignes sont-ils correctement déclarés ?', '5.6', '', 44),
('Rgaa32016-5-7', 'Pour chaque tableau de données, la technique appropriée permettant d’associer chaque cellule avec ses en-têtes est-elle utilisée ?', '5.7', '', 45),
('Rgaa32016-5-8', 'Chaque tableau de mise en forme ne doit pas utiliser d’éléments propres aux tableaux de données. Cette règle est-elle respectée ?', '5.8', '', 46),
('Rgaa32016-6-1', 'Chaque lien est-il explicite (hors cas particuliers) ?', '6.1', '', 47),
('Rgaa32016-6-2', 'Pour chaque lien ayant un titre de lien, celui-ci est-il pertinent ?', '6.2', '', 48),
('Rgaa32016-6-3', 'Chaque intitulé de lien seul est-il explicite hors contexte (hors cas particuliers) ?', '6.3', '', 49),
('Rgaa32016-6-4', 'Pour chaque page Web, chaque lien identique a-t-il les mêmes fonction et destination ?', '6.4', '', 50),
('Rgaa32016-6-5', 'Dans chaque page Web, chaque lien, à l’exception des ancres, a-t-il un intitulé ?', '6.5', '', 51),
('Rgaa32016-7-1', 'Chaque script est-il, si nécessaire, compatible avec les technologies d’assistance ?', '7.1', '', 52),
('Rgaa32016-7-2', 'Pour chaque script ayant une alternative, cette alternative est-elle pertinente ?', '7.2', '', 53),
('Rgaa32016-7-3', 'Chaque script est-il contrôlable par le clavier et la souris (hors cas particuliers) ?', '7.3', '', 54),
('Rgaa32016-7-4', 'Pour chaque script qui initie un changement de contexte, l’utilisateur est-il averti ou en a-t-il le contrôle ?', '7.4', '', 55),
('Rgaa32016-7-5', 'Chaque script qui provoque une alerte non sollicitée est-il contrôlable par l’utilisateur (hors cas particuliers) ?', '7.5', '', 56),
('Rgaa32016-8-1', 'Chaque page Web est-elle définie par un type de document ?', '8.1', '', 57),
('Rgaa32016-8-2', 'Pour chaque page Web, le code source est-il valide selon le type de document spécifié (hors cas particuliers) ?', '8.2', '', 58),
('Rgaa32016-8-3', 'Dans chaque page Web, la langue par défaut est-elle présente ?', '8.3', '', 59),
('Rgaa32016-8-4', 'Pour chaque page Web ayant une langue par défaut, le code de langue est-il pertinent ?', '8.4', '', 60),
('Rgaa32016-8-5', 'Chaque page Web a-t-elle un titre de page ?', '8.5', '', 61),
('Rgaa32016-8-6', 'Pour chaque page Web ayant un titre de page, ce titre est-il pertinent ?', '8.6', '', 62),
('Rgaa32016-8-7', 'Dans chaque page Web, chaque changement de langue est-il indiqué dans le code source (hors cas particuliers) ?', '8.7', '', 63),
('Rgaa32016-8-8', 'Dans chaque page Web, chaque changement de langue est-il pertinent ?', '8.8', '', 64),
('Rgaa32016-8-9', 'Dans chaque page Web, les balises ne doivent pas être utilisées uniquement à des fins de présentation. Cette règle est-elle respectée ?', '8.9', '', 65),
('Rgaa32016-8-10', 'Dans chaque page Web, les changements du sens de lecture sont-ils signalés ?', '8.10', '', 66),
('Rgaa32016-9-1', 'Dans chaque page Web, l’information est-elle structurée par l’utilisation appropriée de titres ?', '9.1', '', 67),
('Rgaa32016-9-2', 'Dans chaque page Web, la structure du document est-elle cohérente ?', '9.2', '', 68),
('Rgaa32016-9-3', 'Dans chaque page Web, chaque liste est-elle correctement structurée ?', '9.3', '', 69),
('Rgaa32016-9-4', 'Dans chaque page Web, la première occurrence de chaque abréviation permet-elle d’en connaître la signification ?', '9.4', '', 70),
('Rgaa32016-9-5', 'Dans chaque page Web, la signification de chaque abréviation est-elle pertinente ?', '9.5', '', 71),
('Rgaa32016-9-6', 'Dans chaque page Web, chaque citation est-elle correctement indiquée ?', '9.6', '', 72),
('Rgaa32016-10-1', 'Dans le site Web, des feuilles de styles sont-elles utilisées pour contrôler la présentation de l’information ?', '10.1', '', 73),
('Rgaa32016-10-2', 'Dans chaque page Web, le contenu visible reste-t-il présent lorsque les feuilles de styles sont désactivées ?', '10.2', '', 74),
('Rgaa32016-10-3', 'Dans chaque page Web, l’information reste-t-elle compréhensible lorsque les feuilles de styles sont désactivées ?', '10.3', '', 75),
('Rgaa32016-10-4', 'Dans chaque page Web, le texte reste-t-il lisible lorsque la taille des caractères est augmentée jusqu’à 200%, au moins ?', '10.4', '', 76),
('Rgaa32016-10-5', 'Dans chaque page Web, les déclarations CSS de couleurs de fond d’élément et de police sont-elles correctement utilisées?', '10.5', '', 77),
('Rgaa32016-10-6', 'Dans chaque page Web, chaque lien dont la nature n’est pas évidente est-il visible par rapport au texte environnant ?', '10.6', '', 78),
('Rgaa32016-10-7', 'Dans chaque page Web, pour chaque élément recevant le focus, la prise de focus est-elle visible ?', '10.7', '', 79),
('Rgaa32016-10-8', 'Dans chaque page Web, le choix de la couleur de fond et de police du texte est-il contrôlable par l’utilisateur ?', '10.8', '', 80),
('Rgaa32016-10-9', 'Pour chaque page Web, le texte ne doit pas être justifié. Cette règle est-elle respectée ?', '10.9', '', 81),
('Rgaa32016-10-10', 'Pour chaque page Web, en affichage plein écran et avec une taille de police à 200%, chaque bloc de texte reste-t-il lisible sans l’utilisation de la barre de défilement horizontal ?', '10.10', '', 82),
('Rgaa32016-10-11', 'Pour chaque page Web, les blocs de texte ont-ils une largeur inférieure ou égale à 80 caractères (hors cas particuliers) ?', '10.11', '', 83),
('Rgaa32016-10-12', 'Pour chaque page Web, l’espace entre les lignes et les paragraphes est-il suffisant ?', '10.12', '', 84),
('Rgaa32016-10-13', 'Pour chaque page Web, les textes cachés sont-ils correctement affichés pour être restitués par les technologies d’assistance ?', '10.13', '', 85),
('Rgaa32016-10-14', 'Dans chaque page Web, l’information ne doit pas être donnée uniquement par la forme, taille ou position. Cette règle est-elle respectée ?', '10.14', '', 86),
('Rgaa32016-10-15', 'Dans chaque page Web, l’information ne doit pas être donnée par la forme, taille ou position uniquement. Cette règle est-elle implémentée de façon pertinente ?', '10.15', '', 87),
('Rgaa32016-11-1', 'Chaque champ de formulaire a-t-il une étiquette ?', '11.1', '', 88),
('Rgaa32016-11-2', 'Chaque étiquette associée à un champ de formulaire est-elle pertinente ?', '11.2', '', 89),
('Rgaa32016-11-3', 'Dans chaque formulaire, chaque étiquette associée à un champ de formulaire ayant la même fonction et répété plusieurs fois dans une même page ou dans un ensemble de pages est-elle cohérente ?', '11.3', '', 90),
('Rgaa32016-11-4', 'Dans chaque formulaire, chaque étiquette de champ et son champ associé sont-ils accolés ?', '11.4', '', 91),
('Rgaa32016-11-5', 'Dans chaque formulaire, les informations de même nature sont-elles regroupées, si nécessaire ?', '11.5', '', 92),
('Rgaa32016-11-6', 'Dans chaque formulaire, chaque regroupement de champs de formulaire a-t-il une légende ?', '11.6', '', 93),
('Rgaa32016-11-7', 'Dans chaque formulaire, chaque légende associée à un groupement de champs de formulaire est-elle pertinente ?', '11.7', '', 94),
('Rgaa32016-11-8', 'Dans chaque formulaire, chaque liste de choix est-elle structurée de manière pertinente ?', '11.8', '', 95),
('Rgaa32016-11-9', 'Dans chaque formulaire, l’intitulé de chaque bouton est-il pertinent ?', '11.9', '', 96),
('Rgaa32016-11-10', 'Dans chaque formulaire, le contrôle de saisie est-il utilisé de manière pertinente ?', '11.10', '', 97),
('Rgaa32016-11-11', 'Dans chaque formulaire, le contrôle de saisie est-il accompagné, si nécessaire, de suggestions facilitant la correction des erreurs de saisie ?', '11.11', '', 98),
('Rgaa32016-11-12', 'Pour chaque formulaire, les données à caractère financier, juridique ou personnel peuvent-elles être modifiées, mises à jour ou récupérées par l’utilisateur ?', '11.12', '', 99),
('Rgaa32016-11-13', 'Pour chaque formulaire, toutes les données peuvent-elles être modifiées, mises à jour ou récupérées par l’utilisateur ?', '11.13', '', 100),
('Rgaa32016-11-14', 'Pour chaque formulaire, des aides à la saisie sont-elles présentes ?', '11.14', '', 101),
('Rgaa32016-11-15', 'Pour chaque formulaire, chaque aide à la saisie est-elle pertinente ?', '11.15', '', 102),
('Rgaa32016-12-1', 'Chaque ensemble de pages dispose-t-il de deux systèmes de navigation différents, au moins (hors cas particuliers) ?', '12.1', '', 103),
('Rgaa32016-12-2', 'Dans chaque ensemble de pages, le menu et les barres de navigation sont-ils toujours à la même place (hors cas particuliers) ?', '12.2', '', 104),
('Rgaa32016-12-3', 'Dans chaque ensemble de pages, le menu et les barres de navigation ont-ils une présentation cohérente (hors cas particuliers) ?', '12.3', '', 105),
('Rgaa32016-12-4', 'La page "plan du site" est-elle pertinente ?', '12.4', '', 106),
('Rgaa32016-12-5', 'Dans chaque ensemble de pages, la page "plan du site" est-elle atteignable de manière identique ?', '12.5', '', 107),
('Rgaa32016-12-6', 'Dans chaque ensemble de pages, le moteur de recherche est-il atteignable de manière identique ?', '12.6', '', 108),
('Rgaa32016-12-7', 'Dans chaque page d’une collection de pages, des liens facilitant la navigation sont-ils présents ?', '12.7', '', 109),
('Rgaa32016-12-8', 'Dans chaque page Web, un fil d’Ariane est-il présent (hors cas particuliers) ?', '12.8', '', 110),
('Rgaa32016-12-9', 'Dans chaque page Web, le fil d’Ariane est-il pertinent ?', '12.9', '', 111),
('Rgaa32016-12-10', 'Dans chaque page Web, les groupes de liens importants (menu, barre de navigation…) et la zone de contenu sont-ils identifiés ?', '12.10', '', 112),
('Rgaa32016-12-11', 'Dans chaque page Web, des liens d’évitement ou d’accès rapide aux groupes de liens importants et à la zone de contenu sont-ils présents (hors cas particuliers) ?', '12.11', '', 113),
('Rgaa32016-12-12', 'Dans chaque page Web, la page en cours de consultation est-elle indiquée dans le menu de navigation ?', '12.12', '', 114),
('Rgaa32016-12-13', 'Dans chaque page Web, l’ordre de tabulation est-il cohérent ?', '12.13', '', 115),
('Rgaa32016-12-14', 'Dans chaque page Web, la navigation ne doit pas contenir de piège au clavier. Cette règle est-elle respectée ?', '12.14', '', 116),
('Rgaa32016-13-1', 'Pour chaque page Web, l’utilisateur a-t-il le contrôle de chaque limite de temps modifiant le contenu (hors cas particuliers) ?', '13.1', '', 117),
('Rgaa32016-13-2', 'Dans chaque page Web, pour chaque ouverture de nouvelle fenêtre, l’utilisateur est-il averti ?', '13.2', '', 118),
('Rgaa32016-13-3', 'Dans chaque page Web, l’ouverture d’une nouvelle fenêtre ne doit pas être déclenchée sans action de l’utilisateur. Cette règle est-elle respectée ?', '13.3', '', 119),
('Rgaa32016-13-4', 'Dans chaque page Web, une tâche ne doit pas requérir de limite de temps pour être réalisée, sauf si elle se déroule en temps réel ou si cette limite de temps est essentielle. Cette règle est-elle respectée ?', '13.4', '', 120),
('Rgaa32016-13-5', 'Dans chaque page Web, lors d’une interruption de session authentifiée, les données saisies par l’utilisateur sont-elles récupérées après ré-authentification ?', '13.5', '', 121),
('Rgaa32016-13-6', 'Dans chaque page Web, pour chaque fichier en téléchargement, des informations relatives à sa consultation sont-elles présentes (hors cas particuliers) ?', '13.6', '', 122),
('Rgaa32016-13-7', 'Dans chaque page Web, chaque document bureautique en téléchargement possède-t-il, si nécessaire, une version accessible ?', '13.7', '', 123),
('Rgaa32016-13-8', 'Pour chaque document bureautique ayant une version accessible, cette version offre-t-elle la même information ?', '13.8', '', 124),
('Rgaa32016-13-9', 'Dans chaque page Web, les expressions inhabituelles, les expressions idiomatiques ou le jargon sont-ils explicités ?', '13.9', '', 125),
('Rgaa32016-13-10', 'Dans chaque page Web, pour chaque expression inhabituelle ou limitée, idiomatique ou de jargon ayant une définition, cette définition est-elle pertinente ?', '13.10', '', 126),
('Rgaa32016-13-11', 'Dans chaque page Web, chaque contenu cryptique (art ASCII, émoticon, syntaxe cryptique) a-t-il une alternative ?', '13.11', '', 127),
('Rgaa32016-13-12', 'Dans chaque page Web, pour chaque contenu cryptique (art ASCII, émoticon, syntaxe cryptique) ayant une alternative, cette alternative est-elle pertinente ?', '13.12', '', 128),
('Rgaa32016-13-13', 'Dans chaque page Web, pour chaque mot dont le sens ne peut être compris sans en connaître la prononciation, celle-ci est-elle indiquée ?', '13.13', '', 129),
('Rgaa32016-13-14', 'Dans chaque page Web, chaque texte qui nécessite un niveau de lecture plus avancé que le premier cycle de l’enseignement secondaire a-t-il une version alternative ?', '13.14', '', 130),
('Rgaa32016-13-15', 'Dans chaque page Web, les changements brusques de luminosité ou les effets de flash sont-ils correctement utilisés ?', '13.15', '', 131),
('Rgaa32016-13-16', 'Dans chaque page Web, les changements brusques de luminosité ou les effets de flash ont-ils une fréquence inférieure ou égale à 3 par seconde ?', '13.16', '', 132),
('Rgaa32016-13-17', 'Dans chaque page Web, chaque contenu en mouvement ou clignotant est-il contrôlable par l’utilisateur ?', '13.17', '', 133);

UPDATE `CRITERION` SET `Reference_Id_Reference` = (SELECT `Id_Reference` FROM `REFERENCE` WHERE `Cd_Reference` LIKE 'Rgaa32016') WHERE `Cd_Criterion` LIKE 'Rgaa32016-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa32016-1') WHERE `Cd_Criterion` LIKE 'Rgaa32016-1-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa32016-2') WHERE `Cd_Criterion` LIKE 'Rgaa32016-2-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa32016-3') WHERE `Cd_Criterion` LIKE 'Rgaa32016-3-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa32016-4') WHERE `Cd_Criterion` LIKE 'Rgaa32016-4-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa32016-5') WHERE `Cd_Criterion` LIKE 'Rgaa32016-5-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa32016-6') WHERE `Cd_Criterion` LIKE 'Rgaa32016-6-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa32016-7') WHERE `Cd_Criterion` LIKE 'Rgaa32016-7-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa32016-8') WHERE `Cd_Criterion` LIKE 'Rgaa32016-8-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa32016-9') WHERE `Cd_Criterion` LIKE 'Rgaa32016-9-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa32016-10') WHERE `Cd_Criterion` LIKE 'Rgaa32016-10-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa32016-11') WHERE `Cd_Criterion` LIKE 'Rgaa32016-11-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa32016-12') WHERE `Cd_Criterion` LIKE 'Rgaa32016-12-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa32016-13') WHERE `Cd_Criterion` LIKE 'Rgaa32016-13-%';

INSERT IGNORE INTO `TEST` (`Cd_Test`, `Description`, `Label`, `Rank`, `Weight`, `Rule_Archive_Name`, `Rule_Class_Name`, `Id_Decision_Level`, `Id_Level`, `Id_Scope`, `Rule_Design_Url`, `No_Process`) VALUES
('Rgaa32016-1-1-1', '', '1.1.1', 1, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010101', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-1-2', '', '1.1.2', 2, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010102', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-1-3', '', '1.1.3', 3, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010103', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-1-4', '', '1.1.4', 4, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010104', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-2-1', '', '1.2.1', 5, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010201', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-2-2', '', '1.2.2', 6, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010202', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-2-3', '', '1.2.3', 7, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010203', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-2-4', '', '1.2.4', 8, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010204', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-2-5', '', '1.2.5', 9, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010205', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-2-6', '', '1.2.6', 10, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010206', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-3-1', '', '1.3.1', 11, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010301', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-3-2', '', '1.3.2', 12, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010302', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-3-3', '', '1.3.3', 13, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010303', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-3-4', '', '1.3.4', 14, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010304', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-3-5', '', '1.3.5', 15, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010305', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-3-6', '', '1.3.6', 16, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010306', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-3-7', '', '1.3.7', 17, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010307', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-3-8', '', '1.3.8', 18, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010308', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-3-9', '', '1.3.9', 19, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010309', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-3-10', '', '1.3.10', 20, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010310', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-3-11', '', '1.3.11', 21, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010311', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-3-12', '', '1.3.12', 22, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010312', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-3-13', '', '1.3.13', 23, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010313', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-4-1', '', '1.4.1', 24, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010401', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-4-2', '', '1.4.2', 25, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010402', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-4-3', '', '1.4.3', 26, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010403', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-4-4', '', '1.4.4', 27, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010404', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-4-5', '', '1.4.5', 28, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010405', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-4-6', '', '1.4.6', 29, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010406', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-4-7', '', '1.4.7', 30, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010407', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-4-8', '', '1.4.8', 31, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010408', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-4-9', '', '1.4.9', 32, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010409', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-4-10', '', '1.4.10', 33, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010410', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-4-11', '', '1.4.11', 34, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010411', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-4-12', '', '1.4.12', 35, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010412', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-5-1', '', '1.5.1', 36, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010501', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-5-2', '', '1.5.2', 37, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010502', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-6-1', '', '1.6.1', 38, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010601', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-6-2', '', '1.6.2', 39, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010602', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-6-3', '', '1.6.3', 40, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010603', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-6-4', '', '1.6.4', 41, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010604', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-6-5', '', '1.6.5', 42, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010605', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-6-6', '', '1.6.6', 43, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010606', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-6-7', '', '1.6.7', 44, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010607', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-6-8', '', '1.6.8', 45, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010608', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-6-9', '', '1.6.9', 46, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010609', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-6-10', '', '1.6.10', 47, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010610', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-7-1', '', '1.7.1', 48, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010701', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-7-2', '', '1.7.2', 49, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010702', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-7-3', '', '1.7.3', 50, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010703', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-7-4', '', '1.7.4', 51, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010704', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-7-5', '', '1.7.5', 52, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010705', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-7-6', '', '1.7.6', 53, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010706', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-7-7', '', '1.7.7', 54, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010707', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-7-8', '', '1.7.8', 55, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010708', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-8-1', '', '1.8.1', 56, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010801', NULL, 2, 1, '', b'1'),
('Rgaa32016-1-8-2', '', '1.8.2', 57, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010802', NULL, 2, 1, '', b'1'),
('Rgaa32016-1-8-3', '', '1.8.3', 58, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010803', NULL, 2, 1, '', b'1'),
('Rgaa32016-1-8-4', '', '1.8.4', 59, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010804', NULL, 2, 1, '', b'1'),
('Rgaa32016-1-8-5', '', '1.8.5', 60, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010805', NULL, 2, 1, '', b'1'),
('Rgaa32016-1-9-1', '', '1.9.1', 61, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010901', NULL, 3, 1, '', b'1'),
('Rgaa32016-1-9-2', '', '1.9.2', 62, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010902', NULL, 3, 1, '', b'1'),
('Rgaa32016-1-9-3', '', '1.9.3', 63, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010903', NULL, 3, 1, '', b'1'),
('Rgaa32016-1-9-4', '', '1.9.4', 64, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010904', NULL, 3, 1, '', b'1'),
('Rgaa32016-1-9-5', '', '1.9.5', 65, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule010905', NULL, 3, 1, '', b'1'),
('Rgaa32016-1-10-1', '', '1.10.1', 66, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule011001', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-10-2', '', '1.10.2', 67, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule011002', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-10-3', '', '1.10.3', 68, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule011003', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-10-4', '', '1.10.4', 69, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule011004', NULL, 1, 1, '', b'1'),
('Rgaa32016-1-10-5', '', '1.10.5', 70, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule011005', NULL, 1, 1, '', b'1'),
('Rgaa32016-2-1-1', '', '2.1.1', 71, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule020101', NULL, 1, 1, '', b'1'),
('Rgaa32016-2-2-1', '', '2.2.1', 72, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule020201', NULL, 1, 1, '', b'1'),
('Rgaa32016-3-1-1', '', '3.1.1', 73, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule030101', NULL, 1, 1, '', b'1'),
('Rgaa32016-3-1-2', '', '3.1.2', 74, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule030102', NULL, 1, 1, '', b'1'),
('Rgaa32016-3-1-3', '', '3.1.3', 75, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule030103', NULL, 1, 1, '', b'1'),
('Rgaa32016-3-1-4', '', '3.1.4', 76, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule030104', NULL, 1, 1, '', b'1'),
('Rgaa32016-3-1-5', '', '3.1.5', 77, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule030105', NULL, 1, 1, '', b'1'),
('Rgaa32016-3-1-6', '', '3.1.6', 78, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule030106', NULL, 1, 1, '', b'1'),
('Rgaa32016-3-2-1', '', '3.2.1', 79, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule030201', NULL, 1, 1, '', b'1'),
('Rgaa32016-3-2-2', '', '3.2.2', 80, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule030202', NULL, 1, 1, '', b'1'),
('Rgaa32016-3-2-3', '', '3.2.3', 81, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule030203', NULL, 1, 1, '', b'1'),
('Rgaa32016-3-2-4', '', '3.2.4', 82, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule030204', NULL, 1, 1, '', b'1'),
('Rgaa32016-3-2-5', '', '3.2.5', 83, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule030205', NULL, 1, 1, '', b'1'),
('Rgaa32016-3-2-6', '', '3.2.6', 84, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule030206', NULL, 1, 1, '', b'1'),
('Rgaa32016-3-3-1', '', '3.3.1', 85, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule030301', NULL, 2, 1, '', b'1'),
('Rgaa32016-3-3-2', '', '3.3.2', 86, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule030302', NULL, 2, 1, '', b'1'),
('Rgaa32016-3-3-3', '', '3.3.3', 87, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule030303', NULL, 2, 1, '', b'1'),
('Rgaa32016-3-3-4', '', '3.3.4', 88, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule030304', NULL, 2, 1, '', b'1'),
('Rgaa32016-3-4-1', '', '3.4.1', 89, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule030401', NULL, 3, 1, '', b'1'),
('Rgaa32016-3-4-2', '', '3.4.2', 90, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule030402', NULL, 3, 1, '', b'1'),
('Rgaa32016-3-4-3', '', '3.4.3', 91, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule030403', NULL, 3, 1, '', b'1'),
('Rgaa32016-3-4-4', '', '3.4.4', 92, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule030404', NULL, 3, 1, '', b'1'),
('Rgaa32016-4-1-1', '', '4.1.1', 93, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule040101', NULL, 1, 1, '', b'1'),
('Rgaa32016-4-1-2', '', '4.1.2', 94, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule040102', NULL, 1, 1, '', b'1'),
('Rgaa32016-4-1-3', '', '4.1.3', 95, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule040103', NULL, 1, 1, '', b'1'),
('Rgaa32016-4-2-1', '', '4.2.1', 96, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule040201', NULL, 1, 1, '', b'1'),
('Rgaa32016-4-2-2', '', '4.2.2', 97, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule040202', NULL, 1, 1, '', b'1'),
('Rgaa32016-4-2-3', '', '4.2.3', 98, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule040203', NULL, 1, 1, '', b'1'),
('Rgaa32016-4-3-1', '', '4.3.1', 99, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule040301', NULL, 1, 1, '', b'1'),
('Rgaa32016-4-3-2', '', '4.3.2', 100, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule040302', NULL, 1, 1, '', b'1'),
('Rgaa32016-4-4-1', '', '4.4.1', 101, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule040401', NULL, 1, 1, '', b'1'),
('Rgaa32016-4-5-1', '', '4.5.1', 102, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule040501', NULL, 2, 1, '', b'1'),
('Rgaa32016-4-5-2', '', '4.5.2', 103, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule040502', NULL, 2, 1, '', b'1'),
('Rgaa32016-4-6-1', '', '4.6.1', 104, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule040601', NULL, 2, 1, '', b'1'),
('Rgaa32016-4-6-2', '', '4.6.2', 105, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule040602', NULL, 2, 1, '', b'1'),
('Rgaa32016-4-7-1', '', '4.7.1', 106, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule040701', NULL, 2, 1, '', b'1'),
('Rgaa32016-4-7-2', '', '4.7.2', 107, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule040702', NULL, 2, 1, '', b'1'),
('Rgaa32016-4-8-1', '', '4.8.1', 108, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule040801', NULL, 2, 1, '', b'1'),
('Rgaa32016-4-8-2', '', '4.8.2', 109, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule040802', NULL, 2, 1, '', b'1'),
('Rgaa32016-4-9-1', '', '4.9.1', 110, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule040901', NULL, 3, 1, '', b'1'),
('Rgaa32016-4-9-2', '', '4.9.2', 111, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule040902', NULL, 3, 1, '', b'1'),
('Rgaa32016-4-10-1', '', '4.10.1', 112, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule041001', NULL, 3, 1, '', b'1'),
('Rgaa32016-4-10-2', '', '4.10.2', 113, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule041002', NULL, 3, 1, '', b'1'),
('Rgaa32016-4-11-1', '', '4.11.1', 114, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule041101', NULL, 3, 1, '', b'1'),
('Rgaa32016-4-11-2', '', '4.11.2', 115, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule041102', NULL, 3, 1, '', b'1'),
('Rgaa32016-4-12-1', '', '4.12.1', 116, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule041201', NULL, 3, 1, '', b'1'),
('Rgaa32016-4-12-2', '', '4.12.2', 117, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule041202', NULL, 3, 1, '', b'1'),
('Rgaa32016-4-13-1', '', '4.13.1', 118, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule041301', NULL, 3, 1, '', b'1'),
('Rgaa32016-4-13-2', '', '4.13.2', 119, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule041302', NULL, 3, 1, '', b'1'),
('Rgaa32016-4-14-1', '', '4.14.1', 120, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule041401', NULL, 3, 1, '', b'1'),
('Rgaa32016-4-14-2', '', '4.14.2', 121, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule041402', NULL, 3, 1, '', b'1'),
('Rgaa32016-4-15-1', '', '4.15.1', 122, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule041501', NULL, 1, 1, '', b'1'),
('Rgaa32016-4-15-2', '', '4.15.2', 123, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule041502', NULL, 1, 1, '', b'1'),
('Rgaa32016-4-16-1', '', '4.16.1', 124, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule041601', NULL, 1, 1, '', b'1'),
('Rgaa32016-4-16-2', '', '4.16.2', 125, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule041602', NULL, 1, 1, '', b'1'),
('Rgaa32016-4-17-1', '', '4.17.1', 126, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule041701', NULL, 1, 1, '', b'1'),
('Rgaa32016-4-18-1', '', '4.18.1', 127, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule041801', NULL, 1, 1, '', b'1'),
('Rgaa32016-4-19-1', '', '4.19.1', 128, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule041901', NULL, 3, 1, '', b'1'),
('Rgaa32016-4-20-1', '', '4.20.1', 129, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule042001', NULL, 1, 1, '', b'1'),
('Rgaa32016-4-20-2', '', '4.20.2', 130, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule042002', NULL, 1, 1, '', b'1'),
('Rgaa32016-4-20-3', '', '4.20.3', 131, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule042003', NULL, 1, 1, '', b'1'),
('Rgaa32016-4-21-1', '', '4.21.1', 132, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule042101', NULL, 1, 1, '', b'1'),
('Rgaa32016-4-21-2', '', '4.21.2', 133, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule042102', NULL, 1, 1, '', b'1'),
('Rgaa32016-4-22-1', '', '4.22.1', 134, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule042201', NULL, 1, 1, '', b'1'),
('Rgaa32016-4-22-2', '', '4.22.2', 135, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule042202', NULL, 1, 1, '', b'1'),
('Rgaa32016-5-1-1', '', '5.1.1', 136, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule050101', NULL, 1, 1, '', b'1'),
('Rgaa32016-5-2-1', '', '5.2.1', 137, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule050201', NULL, 1, 1, '', b'1'),
('Rgaa32016-5-3-1', '', '5.3.1', 138, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule050301', NULL, 1, 1, '', b'1'),
('Rgaa32016-5-4-1', '', '5.4.1', 139, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule050401', NULL, 1, 1, '', b'1'),
('Rgaa32016-5-5-1', '', '5.5.1', 140, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule050501', NULL, 1, 1, '', b'1'),
('Rgaa32016-5-6-1', '', '5.6.1', 141, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule050601', NULL, 1, 1, '', b'1'),
('Rgaa32016-5-6-2', '', '5.6.2', 142, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule050602', NULL, 1, 1, '', b'1'),
('Rgaa32016-5-7-1', '', '5.7.1', 143, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule050701', NULL, 1, 1, '', b'1'),
('Rgaa32016-5-7-2', '', '5.7.2', 144, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule050702', NULL, 1, 1, '', b'1'),
('Rgaa32016-5-7-3', '', '5.7.3', 145, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule050703', NULL, 1, 1, '', b'1'),
('Rgaa32016-5-7-4', '', '5.7.4', 146, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule050704', NULL, 1, 1, '', b'1'),
('Rgaa32016-5-8-1', '', '5.8.1', 147, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule050801', NULL, 1, 1, '', b'1'),
('Rgaa32016-6-1-1', '', '6.1.1', 148, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule060101', NULL, 1, 1, '', b'1'),
('Rgaa32016-6-1-2', '', '6.1.2', 149, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule060102', NULL, 1, 1, '', b'1'),
('Rgaa32016-6-1-3', '', '6.1.3', 150, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule060103', NULL, 1, 1, '', b'1'),
('Rgaa32016-6-2-1', '', '6.2.1', 151, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule060201', NULL, 1, 1, '', b'1'),
('Rgaa32016-6-2-2', '', '6.2.2', 152, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule060202', NULL, 1, 1, '', b'1'),
('Rgaa32016-6-2-3', '', '6.2.3', 153, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule060203', NULL, 1, 1, '', b'1'),
('Rgaa32016-6-3-1', '', '6.3.1', 154, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule060301', NULL, 3, 1, '', b'1'),
('Rgaa32016-6-3-2', '', '6.3.2', 155, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule060302', NULL, 3, 1, '', b'1'),
('Rgaa32016-6-3-3', '', '6.3.3', 156, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule060303', NULL, 3, 1, '', b'1'),
('Rgaa32016-6-4-1', '', '6.4.1', 157, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule060401', NULL, 1, 1, '', b'1'),
('Rgaa32016-6-4-2', '', '6.4.2', 158, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule060402', NULL, 1, 1, '', b'1'),
('Rgaa32016-6-4-3', '', '6.4.3', 159, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule060403', NULL, 1, 1, '', b'1'),
('Rgaa32016-6-5-1', '', '6.5.1', 160, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule060501', NULL, 1, 1, '', b'1'),
('Rgaa32016-7-1-1', '', '7.1.1', 161, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule070101', NULL, 1, 1, '', b'1'),
('Rgaa32016-7-1-2', '', '7.1.2', 162, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule070102', NULL, 1, 1, '', b'1'),
('Rgaa32016-7-1-3', '', '7.1.3', 163, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule070103', NULL, 1, 1, '', b'1'),
('Rgaa32016-7-1-4', '', '7.1.4', 164, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule070104', NULL, 1, 1, '', b'1'),
('Rgaa32016-7-1-5', '', '7.1.5', 165, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule070105', NULL, 1, 1, '', b'1'),
('Rgaa32016-7-1-6', '', '7.1.6', 166, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule070106', NULL, 1, 1, '', b'1'),
('Rgaa32016-7-2-1', '', '7.2.1', 167, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule070201', NULL, 1, 1, '', b'1'),
('Rgaa32016-7-2-2', '', '7.2.2', 168, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule070202', NULL, 1, 1, '', b'1'),
('Rgaa32016-7-3-1', '', '7.3.1', 169, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule070301', NULL, 1, 1, '', b'1'),
('Rgaa32016-7-3-2', '', '7.3.2', 170, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule070302', NULL, 1, 1, '', b'1'),
('Rgaa32016-7-3-3', '', '7.3.3', 171, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule070303', NULL, 1, 1, '', b'1'),
('Rgaa32016-7-4-1', '', '7.4.1', 172, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule070401', NULL, 1, 1, '', b'1'),
('Rgaa32016-7-5-1', '', '7.5.1', 173, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule070501', NULL, 3, 1, '', b'1'),
('Rgaa32016-8-1-1', '', '8.1.1', 174, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule080101', NULL, 1, 1, '', b'1'),
('Rgaa32016-8-1-2', '', '8.1.2', 175, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule080102', NULL, 1, 1, '', b'1'),
('Rgaa32016-8-1-3', '', '8.1.3', 176, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule080103', NULL, 1, 1, '', b'1'),
('Rgaa32016-8-2-1', '', '8.2.1', 177, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule080201', NULL, 1, 1, '', b'1'),
('Rgaa32016-8-2-2', '', '8.2.2', 178, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule080202', NULL, 1, 1, '', b'1'),
('Rgaa32016-8-3-1', '', '8.3.1', 179, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule080301', NULL, 1, 1, '', b'1'),
('Rgaa32016-8-4-1', '', '8.4.1', 180, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule080401', NULL, 1, 1, '', b'1'),
('Rgaa32016-8-5-1', '', '8.5.1', 181, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule080501', NULL, 1, 1, '', b'1'),
('Rgaa32016-8-6-1', '', '8.6.1', 182, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule080601', NULL, 1, 1, '', b'1'),
('Rgaa32016-8-7-1', '', '8.7.1', 183, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule080701', NULL, 2, 1, '', b'1'),
('Rgaa32016-8-8-1', '', '8.8.1', 184, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule080801', NULL, 2, 1, '', b'1'),
('Rgaa32016-8-8-2', '', '8.8.2', 185, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule080802', NULL, 2, 1, '', b'1'),
('Rgaa32016-8-9-1', '', '8.9.1', 186, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule080901', NULL, 1, 1, '', b'1'),
('Rgaa32016-8-10-1', '', '8.10.1', 187, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule081001', NULL, 1, 1, '', b'1'),
('Rgaa32016-8-10-2', '', '8.10.2', 188, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule081002', NULL, 1, 1, '', b'1'),
('Rgaa32016-9-1-1', '', '9.1.1', 189, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule090101', NULL, 1, 1, '', b'1'),
('Rgaa32016-9-1-2', '', '9.1.2', 190, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule090102', NULL, 1, 1, '', b'1'),
('Rgaa32016-9-1-3', '', '9.1.3', 191, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule090103', NULL, 1, 1, '', b'1'),
('Rgaa32016-9-1-4', '', '9.1.4', 192, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule090104', NULL, 1, 1, '', b'1'),
('Rgaa32016-9-2-1', '', '9.2.1', 193, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule090201', NULL, 1, 1, '', b'1'),
('Rgaa32016-9-2-2', '', '9.2.2', 194, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule090202', NULL, 1, 1, '', b'1'),
('Rgaa32016-9-3-1', '', '9.3.1', 195, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule090301', NULL, 1, 1, '', b'1'),
('Rgaa32016-9-3-2', '', '9.3.2', 196, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule090302', NULL, 1, 1, '', b'1'),
('Rgaa32016-9-3-3', '', '9.3.3', 197, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule090303', NULL, 1, 1, '', b'1'),
('Rgaa32016-9-4-1', '', '9.4.1', 198, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule090401', NULL, 3, 1, '', b'1'),
('Rgaa32016-9-5-1', '', '9.5.1', 199, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule090501', NULL, 3, 1, '', b'1'),
('Rgaa32016-9-6-1', '', '9.6.1', 200, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule090601', NULL, 1, 1, '', b'1'),
('Rgaa32016-9-6-2', '', '9.6.2', 201, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule090602', NULL, 1, 1, '', b'1'),
('Rgaa32016-10-1-1', '', '10.1.1', 202, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule100101', NULL, 1, 1, '', b'1'),
('Rgaa32016-10-1-2', '', '10.1.2', 203, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule100102', NULL, 1, 1, '', b'1'),
('Rgaa32016-10-1-3', '', '10.1.3', 204, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule100103', NULL, 1, 1, '', b'1'),
('Rgaa32016-10-2-1', '', '10.2.1', 205, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule100201', NULL, 1, 1, '', b'1'),
('Rgaa32016-10-3-1', '', '10.3.1', 206, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule100301', NULL, 1, 1, '', b'1'),
('Rgaa32016-10-4-1', '', '10.4.1', 207, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule100401', NULL, 2, 1, '', b'1'),
('Rgaa32016-10-4-2', '', '10.4.2', 208, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule100402', NULL, 2, 1, '', b'1'),
('Rgaa32016-10-4-3', '', '10.4.3', 209, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule100403', NULL, 2, 1, '', b'1'),
('Rgaa32016-10-5-1', '', '10.5.1', 210, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule100501', NULL, 2, 1, '', b'1'),
('Rgaa32016-10-5-2', '', '10.5.2', 211, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule100502', NULL, 2, 1, '', b'1'),
('Rgaa32016-10-5-3', '', '10.5.3', 212, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule100503', NULL, 2, 1, '', b'1'),
('Rgaa32016-10-6-1', '', '10.6.1', 213, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule100601', NULL, 1, 1, '', b'1'),
('Rgaa32016-10-7-1', '', '10.7.1', 214, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule100701', NULL, 1, 1, '', b'1'),
('Rgaa32016-10-7-2', '', '10.7.2', 215, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule100702', NULL, 1, 1, '', b'1'),
('Rgaa32016-10-7-3', '', '10.7.3', 216, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule100703', NULL, 1, 1, '', b'1'),
('Rgaa32016-10-8-1', '', '10.8.1', 217, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule100801', NULL, 3, 1, '', b'1'),
('Rgaa32016-10-8-2', '', '10.8.2', 218, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule100802', NULL, 3, 1, '', b'1'),
('Rgaa32016-10-8-3', '', '10.8.3', 219, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule100803', NULL, 3, 1, '', b'1'),
('Rgaa32016-10-8-4', '', '10.8.4', 220, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule100804', NULL, 3, 1, '', b'1'),
('Rgaa32016-10-9-1', '', '10.9.1', 221, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule100901', NULL, 3, 1, '', b'1'),
('Rgaa32016-10-10-1', '', '10.10.1', 222, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule101001', NULL, 3, 1, '', b'1'),
('Rgaa32016-10-11-1', '', '10.11.1', 223, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule101101', NULL, 3, 1, '', b'1'),
('Rgaa32016-10-12-1', '', '10.12.1', 224, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule101201', NULL, 3, 1, '', b'1'),
('Rgaa32016-10-12-2', '', '10.12.2', 225, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule101202', NULL, 3, 1, '', b'1'),
('Rgaa32016-10-13-1', '', '10.13.1', 226, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule101301', NULL, 1, 1, '', b'1'),
('Rgaa32016-10-14-1', '', '10.14.1', 227, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule101401', NULL, 1, 1, '', b'1'),
('Rgaa32016-10-14-2', '', '10.14.2', 228, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule101402', NULL, 1, 1, '', b'1'),
('Rgaa32016-10-14-3', '', '10.14.3', 229, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule101403', NULL, 1, 1, '', b'1'),
('Rgaa32016-10-14-4', '', '10.14.4', 230, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule101404', NULL, 1, 1, '', b'1'),
('Rgaa32016-10-15-1', '', '10.15.1', 231, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule101501', NULL, 1, 1, '', b'1'),
('Rgaa32016-10-15-2', '', '10.15.2', 232, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule101502', NULL, 1, 1, '', b'1'),
('Rgaa32016-10-15-3', '', '10.15.3', 233, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule101503', NULL, 1, 1, '', b'1'),
('Rgaa32016-10-15-4', '', '10.15.4', 234, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule101504', NULL, 1, 1, '', b'1'),
('Rgaa32016-11-1-1', '', '11.1.1', 235, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule110101', NULL, 1, 1, '', b'1'),
('Rgaa32016-11-1-2', '', '11.1.2', 236, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule110102', NULL, 1, 1, '', b'1'),
('Rgaa32016-11-1-3', '', '11.1.3', 237, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule110103', NULL, 1, 1, '', b'1'),
('Rgaa32016-11-1-4', '', '11.1.4', 238, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule110104', NULL, 1, 1, '', b'1'),
('Rgaa32016-11-1-5', '', '11.1.5', 239, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule110105', NULL, 1, 1, '', b'1'),
('Rgaa32016-11-2-1', '', '11.2.1', 240, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule110201', NULL, 1, 1, '', b'1'),
('Rgaa32016-11-2-2', '', '11.2.2', 241, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule110202', NULL, 1, 1, '', b'1'),
('Rgaa32016-11-2-3', '', '11.2.3', 242, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule110203', NULL, 1, 1, '', b'1'),
('Rgaa32016-11-2-4', '', '11.2.4', 243, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule110204', NULL, 1, 1, '', b'1'),
('Rgaa32016-11-3-1', '', '11.3.1', 244, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule110301', NULL, 2, 1, '', b'1'),
('Rgaa32016-11-3-2', '', '11.3.2', 245, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule110302', NULL, 2, 1, '', b'1'),
('Rgaa32016-11-4-1', '', '11.4.1', 246, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule110401', NULL, 1, 1, '', b'1'),
('Rgaa32016-11-5-1', '', '11.5.1', 247, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule110501', NULL, 1, 1, '', b'1'),
('Rgaa32016-11-6-1', '', '11.6.1', 248, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule110601', NULL, 1, 1, '', b'1'),
('Rgaa32016-11-7-1', '', '11.7.1', 249, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule110701', NULL, 1, 1, '', b'1'),
('Rgaa32016-11-8-1', '', '11.8.1', 250, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule110801', NULL, 1, 1, '', b'1'),
('Rgaa32016-11-8-2', '', '11.8.2', 251, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule110802', NULL, 1, 1, '', b'1'),
('Rgaa32016-11-8-3', '', '11.8.3', 252, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule110803', NULL, 1, 1, '', b'1'),
('Rgaa32016-11-9-1', '', '11.9.1', 253, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule110901', NULL, 1, 1, '', b'1'),
('Rgaa32016-11-9-2', '', '11.9.2', 254, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule110902', NULL, 1, 1, '', b'1'),
('Rgaa32016-11-10-1', '', '11.10.1', 255, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule111001', NULL, 1, 1, '', b'1'),
('Rgaa32016-11-10-2', '', '11.10.2', 256, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule111002', NULL, 1, 1, '', b'1'),
('Rgaa32016-11-10-3', '', '11.10.3', 257, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule111003', NULL, 1, 1, '', b'1'),
('Rgaa32016-11-10-4', '', '11.10.4', 258, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule111004', NULL, 1, 1, '', b'1'),
('Rgaa32016-11-10-5', '', '11.10.5', 259, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule111005', NULL, 1, 1, '', b'1'),
('Rgaa32016-11-10-6', '', '11.10.6', 260, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule111006', NULL, 1, 1, '', b'1'),
('Rgaa32016-11-10-7', '', '11.10.7', 261, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule111007', NULL, 1, 1, '', b'1'),
('Rgaa32016-11-10-8', '', '11.10.8', 262, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule111008', NULL, 1, 1, '', b'1'),
('Rgaa32016-11-10-9', '', '11.10.9', 263, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule111009', NULL, 1, 1, '', b'1'),
('Rgaa32016-11-10-10', '', '11.10.10', 264, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule111010', NULL, 1, 1, '', b'1'),
('Rgaa32016-11-11-1', '', '11.11.1', 265, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule111101', NULL, 2, 1, '', b'1'),
('Rgaa32016-11-11-2', '', '11.11.2', 266, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule111102', NULL, 2, 1, '', b'1'),
('Rgaa32016-11-12-1', '', '11.12.1', 267, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule111201', NULL, 2, 1, '', b'1'),
('Rgaa32016-11-12-2', '', '11.12.2', 268, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule111202', NULL, 2, 1, '', b'1'),
('Rgaa32016-11-13-1', '', '11.13.1', 269, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule111301', NULL, 3, 1, '', b'1'),
('Rgaa32016-11-13-2', '', '11.13.2', 270, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule111302', NULL, 3, 1, '', b'1'),
('Rgaa32016-11-14-1', '', '11.14.1', 271, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule111401', NULL, 3, 1, '', b'1'),
('Rgaa32016-11-14-2', '', '11.14.2', 272, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule111402', NULL, 3, 1, '', b'1'),
('Rgaa32016-11-14-3', '', '11.14.3', 273, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule111403', NULL, 3, 1, '', b'1'),
('Rgaa32016-11-14-4', '', '11.14.4', 274, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule111404', NULL, 3, 1, '', b'1'),
('Rgaa32016-11-14-5', '', '11.14.5', 275, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule111405', NULL, 3, 1, '', b'1'),
('Rgaa32016-11-14-6', '', '11.14.6', 276, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule111406', NULL, 3, 1, '', b'1'),
('Rgaa32016-11-15-1', '', '11.15.1', 277, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule111501', NULL, 3, 1, '', b'1'),
('Rgaa32016-12-1-1', '', '12.1.1', 278, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule120101', NULL, 2, 1, '', b'1'),
('Rgaa32016-12-2-1', '', '12.2.1', 279, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule120201', NULL, 2, 1, '', b'1'),
('Rgaa32016-12-2-2', '', '12.2.2', 280, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule120202', NULL, 2, 1, '', b'1'),
('Rgaa32016-12-3-1', '', '12.3.1', 281, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule120301', NULL, 2, 1, '', b'1'),
('Rgaa32016-12-3-2', '', '12.3.2', 282, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule120302', NULL, 2, 1, '', b'1'),
('Rgaa32016-12-4-1', '', '12.4.1', 283, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule120401', NULL, 2, 1, '', b'1'),
('Rgaa32016-12-4-2', '', '12.4.2', 284, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule120402', NULL, 2, 1, '', b'1'),
('Rgaa32016-12-4-3', '', '12.4.3', 285, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule120403', NULL, 2, 1, '', b'1'),
('Rgaa32016-12-5-1', '', '12.5.1', 286, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule120501', NULL, 2, 1, '', b'1'),
('Rgaa32016-12-5-2', '', '12.5.2', 287, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule120502', NULL, 2, 1, '', b'1'),
('Rgaa32016-12-5-3', '', '12.5.3', 288, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule120503', NULL, 2, 1, '', b'1'),
('Rgaa32016-12-6-1', '', '12.6.1', 289, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule120601', NULL, 2, 1, '', b'1'),
('Rgaa32016-12-6-2', '', '12.6.2', 290, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule120602', NULL, 2, 1, '', b'1'),
('Rgaa32016-12-6-3', '', '12.6.3', 291, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule120603', NULL, 2, 1, '', b'1'),
('Rgaa32016-12-7-1', '', '12.7.1', 292, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule120701', NULL, 2, 1, '', b'1'),
('Rgaa32016-12-8-1', '', '12.8.1', 293, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule120801', NULL, 3, 1, '', b'1'),
('Rgaa32016-12-9-1', '', '12.9.1', 294, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule120901', NULL, 3, 1, '', b'1'),
('Rgaa32016-12-10-1', '', '12.10.1', 295, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule121001', NULL, 1, 1, '', b'1'),
('Rgaa32016-12-10-2', '', '12.10.2', 296, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule121002', NULL, 1, 1, '', b'1'),
('Rgaa32016-12-10-3', '', '12.10.3', 297, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule121003', NULL, 1, 1, '', b'1'),
('Rgaa32016-12-10-4', '', '12.10.4', 298, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule121004', NULL, 1, 1, '', b'1'),
('Rgaa32016-12-11-1', '', '12.11.1', 299, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule121101', NULL, 1, 1, '', b'1'),
('Rgaa32016-12-11-2', '', '12.11.2', 300, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule121102', NULL, 1, 1, '', b'1'),
('Rgaa32016-12-11-3', '', '12.11.3', 301, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule121103', NULL, 1, 1, '', b'1'),
('Rgaa32016-12-11-4', '', '12.11.4', 302, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule121104', NULL, 1, 1, '', b'1'),
('Rgaa32016-12-12-1', '', '12.12.1', 303, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule121201', NULL, 3, 1, '', b'1'),
('Rgaa32016-12-13-1', '', '12.13.1', 304, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule121301', NULL, 1, 1, '', b'1'),
('Rgaa32016-12-13-2', '', '12.13.2', 305, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule121302', NULL, 1, 1, '', b'1'),
('Rgaa32016-12-14-1', '', '12.14.1', 306, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule121401', NULL, 1, 1, '', b'1'),
('Rgaa32016-13-1-1', '', '13.1.1', 307, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule130101', NULL, 1, 1, '', b'1'),
('Rgaa32016-13-1-2', '', '13.1.2', 308, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule130102', NULL, 1, 1, '', b'1'),
('Rgaa32016-13-1-3', '', '13.1.3', 309, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule130103', NULL, 1, 1, '', b'1'),
('Rgaa32016-13-1-4', '', '13.1.4', 310, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule130104', NULL, 1, 1, '', b'1'),
('Rgaa32016-13-2-1', '', '13.2.1', 311, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule130201', NULL, 1, 1, '', b'1'),
('Rgaa32016-13-2-2', '', '13.2.2', 312, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule130202', NULL, 1, 1, '', b'1'),
('Rgaa32016-13-2-3', '', '13.2.3', 313, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule130203', NULL, 1, 1, '', b'1'),
('Rgaa32016-13-3-1', '', '13.3.1', 314, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule130301', NULL, 1, 1, '', b'1'),
('Rgaa32016-13-4-1', '', '13.4.1', 315, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule130401', NULL, 3, 1, '', b'1'),
('Rgaa32016-13-5-1', '', '13.5.1', 316, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule130501', NULL, 3, 1, '', b'1'),
('Rgaa32016-13-6-1', '', '13.6.1', 317, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule130601', NULL, 1, 1, '', b'1'),
('Rgaa32016-13-6-2', '', '13.6.2', 318, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule130602', NULL, 1, 1, '', b'1'),
('Rgaa32016-13-6-3', '', '13.6.3', 319, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule130603', NULL, 1, 1, '', b'1'),
('Rgaa32016-13-7-1', '', '13.7.1', 320, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule130701', NULL, 1, 1, '', b'1'),
('Rgaa32016-13-8-1', '', '13.8.1', 321, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule130801', NULL, 1, 1, '', b'1'),
('Rgaa32016-13-9-1', '', '13.9.1', 322, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule130901', NULL, 3, 1, '', b'1'),
('Rgaa32016-13-10-1', '', '13.10.1', 323, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule131001', NULL, 3, 1, '', b'1'),
('Rgaa32016-13-11-1', '', '13.11.1', 324, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule131101', NULL, 1, 1, '', b'1'),
('Rgaa32016-13-12-1', '', '13.12.1', 325, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule131201', NULL, 1, 1, '', b'1'),
('Rgaa32016-13-13-1', '', '13.13.1', 326, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule131301', NULL, 3, 1, '', b'1'),
('Rgaa32016-13-14-1', '', '13.14.1', 327, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule131401', NULL, 3, 1, '', b'1'),
('Rgaa32016-13-15-1', '', '13.15.1', 328, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule131501', NULL, 1, 1, '', b'1'),
('Rgaa32016-13-15-2', '', '13.15.2', 329, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule131502', NULL, 1, 1, '', b'1'),
('Rgaa32016-13-15-3', '', '13.15.3', 330, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule131503', NULL, 1, 1, '', b'1'),
('Rgaa32016-13-16-1', '', '13.16.1', 331, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule131601', NULL, 3, 1, '', b'1'),
('Rgaa32016-13-16-2', '', '13.16.2', 332, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule131602', NULL, 3, 1, '', b'1'),
('Rgaa32016-13-16-3', '', '13.16.3', 333, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule131603', NULL, 3, 1, '', b'1'),
('Rgaa32016-13-17-1', '', '13.17.1', 334, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule131701', NULL, 1, 1, '', b'1'),
('Rgaa32016-13-17-2', '', '13.17.2', 335, '1.0', 'rgaa32016', 'org.asqatasun.rules.rgaa32016.Rgaa32016Rule131702', NULL, 1, 1, '', b'1');



UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-1-1') WHERE `Cd_Test` LIKE 'Rgaa32016-1-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-1-2') WHERE `Cd_Test` LIKE 'Rgaa32016-1-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-1-3') WHERE `Cd_Test` LIKE 'Rgaa32016-1-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-1-4') WHERE `Cd_Test` LIKE 'Rgaa32016-1-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-1-5') WHERE `Cd_Test` LIKE 'Rgaa32016-1-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-1-6') WHERE `Cd_Test` LIKE 'Rgaa32016-1-6-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-1-7') WHERE `Cd_Test` LIKE 'Rgaa32016-1-7-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-1-8') WHERE `Cd_Test` LIKE 'Rgaa32016-1-8-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-1-9') WHERE `Cd_Test` LIKE 'Rgaa32016-1-9-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-1-10') WHERE `Cd_Test` LIKE 'Rgaa32016-1-10-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-2-1') WHERE `Cd_Test` LIKE 'Rgaa32016-2-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-2-2') WHERE `Cd_Test` LIKE 'Rgaa32016-2-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-3-1') WHERE `Cd_Test` LIKE 'Rgaa32016-3-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-3-2') WHERE `Cd_Test` LIKE 'Rgaa32016-3-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-3-3') WHERE `Cd_Test` LIKE 'Rgaa32016-3-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-3-4') WHERE `Cd_Test` LIKE 'Rgaa32016-3-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-4-1') WHERE `Cd_Test` LIKE 'Rgaa32016-4-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-4-2') WHERE `Cd_Test` LIKE 'Rgaa32016-4-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-4-3') WHERE `Cd_Test` LIKE 'Rgaa32016-4-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-4-4') WHERE `Cd_Test` LIKE 'Rgaa32016-4-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-4-5') WHERE `Cd_Test` LIKE 'Rgaa32016-4-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-4-6') WHERE `Cd_Test` LIKE 'Rgaa32016-4-6-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-4-7') WHERE `Cd_Test` LIKE 'Rgaa32016-4-7-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-4-8') WHERE `Cd_Test` LIKE 'Rgaa32016-4-8-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-4-9') WHERE `Cd_Test` LIKE 'Rgaa32016-4-9-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-4-10') WHERE `Cd_Test` LIKE 'Rgaa32016-4-10-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-4-11') WHERE `Cd_Test` LIKE 'Rgaa32016-4-11-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-4-12') WHERE `Cd_Test` LIKE 'Rgaa32016-4-12-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-4-13') WHERE `Cd_Test` LIKE 'Rgaa32016-4-13-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-4-14') WHERE `Cd_Test` LIKE 'Rgaa32016-4-14-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-4-15') WHERE `Cd_Test` LIKE 'Rgaa32016-4-15-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-4-16') WHERE `Cd_Test` LIKE 'Rgaa32016-4-16-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-4-17') WHERE `Cd_Test` LIKE 'Rgaa32016-4-17-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-4-18') WHERE `Cd_Test` LIKE 'Rgaa32016-4-18-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-4-19') WHERE `Cd_Test` LIKE 'Rgaa32016-4-19-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-4-20') WHERE `Cd_Test` LIKE 'Rgaa32016-4-20-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-4-21') WHERE `Cd_Test` LIKE 'Rgaa32016-4-21-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-4-22') WHERE `Cd_Test` LIKE 'Rgaa32016-4-22-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-5-1') WHERE `Cd_Test` LIKE 'Rgaa32016-5-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-5-2') WHERE `Cd_Test` LIKE 'Rgaa32016-5-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-5-3') WHERE `Cd_Test` LIKE 'Rgaa32016-5-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-5-4') WHERE `Cd_Test` LIKE 'Rgaa32016-5-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-5-5') WHERE `Cd_Test` LIKE 'Rgaa32016-5-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-5-6') WHERE `Cd_Test` LIKE 'Rgaa32016-5-6-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-5-7') WHERE `Cd_Test` LIKE 'Rgaa32016-5-7-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-5-8') WHERE `Cd_Test` LIKE 'Rgaa32016-5-8-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-6-1') WHERE `Cd_Test` LIKE 'Rgaa32016-6-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-6-2') WHERE `Cd_Test` LIKE 'Rgaa32016-6-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-6-3') WHERE `Cd_Test` LIKE 'Rgaa32016-6-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-6-4') WHERE `Cd_Test` LIKE 'Rgaa32016-6-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-6-5') WHERE `Cd_Test` LIKE 'Rgaa32016-6-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-7-1') WHERE `Cd_Test` LIKE 'Rgaa32016-7-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-7-2') WHERE `Cd_Test` LIKE 'Rgaa32016-7-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-7-3') WHERE `Cd_Test` LIKE 'Rgaa32016-7-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-7-4') WHERE `Cd_Test` LIKE 'Rgaa32016-7-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-7-5') WHERE `Cd_Test` LIKE 'Rgaa32016-7-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-8-1') WHERE `Cd_Test` LIKE 'Rgaa32016-8-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-8-2') WHERE `Cd_Test` LIKE 'Rgaa32016-8-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-8-3') WHERE `Cd_Test` LIKE 'Rgaa32016-8-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-8-4') WHERE `Cd_Test` LIKE 'Rgaa32016-8-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-8-5') WHERE `Cd_Test` LIKE 'Rgaa32016-8-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-8-6') WHERE `Cd_Test` LIKE 'Rgaa32016-8-6-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-8-7') WHERE `Cd_Test` LIKE 'Rgaa32016-8-7-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-8-8') WHERE `Cd_Test` LIKE 'Rgaa32016-8-8-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-8-9') WHERE `Cd_Test` LIKE 'Rgaa32016-8-9-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-8-10') WHERE `Cd_Test` LIKE 'Rgaa32016-8-10-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-9-1') WHERE `Cd_Test` LIKE 'Rgaa32016-9-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-9-2') WHERE `Cd_Test` LIKE 'Rgaa32016-9-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-9-3') WHERE `Cd_Test` LIKE 'Rgaa32016-9-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-9-4') WHERE `Cd_Test` LIKE 'Rgaa32016-9-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-9-5') WHERE `Cd_Test` LIKE 'Rgaa32016-9-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-9-6') WHERE `Cd_Test` LIKE 'Rgaa32016-9-6-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-10-1') WHERE `Cd_Test` LIKE 'Rgaa32016-10-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-10-2') WHERE `Cd_Test` LIKE 'Rgaa32016-10-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-10-3') WHERE `Cd_Test` LIKE 'Rgaa32016-10-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-10-4') WHERE `Cd_Test` LIKE 'Rgaa32016-10-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-10-5') WHERE `Cd_Test` LIKE 'Rgaa32016-10-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-10-6') WHERE `Cd_Test` LIKE 'Rgaa32016-10-6-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-10-7') WHERE `Cd_Test` LIKE 'Rgaa32016-10-7-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-10-8') WHERE `Cd_Test` LIKE 'Rgaa32016-10-8-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-10-9') WHERE `Cd_Test` LIKE 'Rgaa32016-10-9-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-10-10') WHERE `Cd_Test` LIKE 'Rgaa32016-10-10-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-10-11') WHERE `Cd_Test` LIKE 'Rgaa32016-10-11-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-10-12') WHERE `Cd_Test` LIKE 'Rgaa32016-10-12-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-10-13') WHERE `Cd_Test` LIKE 'Rgaa32016-10-13-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-10-14') WHERE `Cd_Test` LIKE 'Rgaa32016-10-14-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-10-15') WHERE `Cd_Test` LIKE 'Rgaa32016-10-15-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-11-1') WHERE `Cd_Test` LIKE 'Rgaa32016-11-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-11-2') WHERE `Cd_Test` LIKE 'Rgaa32016-11-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-11-3') WHERE `Cd_Test` LIKE 'Rgaa32016-11-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-11-4') WHERE `Cd_Test` LIKE 'Rgaa32016-11-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-11-5') WHERE `Cd_Test` LIKE 'Rgaa32016-11-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-11-6') WHERE `Cd_Test` LIKE 'Rgaa32016-11-6-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-11-7') WHERE `Cd_Test` LIKE 'Rgaa32016-11-7-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-11-8') WHERE `Cd_Test` LIKE 'Rgaa32016-11-8-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-11-9') WHERE `Cd_Test` LIKE 'Rgaa32016-11-9-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-11-10') WHERE `Cd_Test` LIKE 'Rgaa32016-11-10-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-11-11') WHERE `Cd_Test` LIKE 'Rgaa32016-11-11-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-11-12') WHERE `Cd_Test` LIKE 'Rgaa32016-11-12-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-11-13') WHERE `Cd_Test` LIKE 'Rgaa32016-11-13-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-11-14') WHERE `Cd_Test` LIKE 'Rgaa32016-11-14-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-11-15') WHERE `Cd_Test` LIKE 'Rgaa32016-11-15-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-12-1') WHERE `Cd_Test` LIKE 'Rgaa32016-12-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-12-2') WHERE `Cd_Test` LIKE 'Rgaa32016-12-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-12-3') WHERE `Cd_Test` LIKE 'Rgaa32016-12-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-12-4') WHERE `Cd_Test` LIKE 'Rgaa32016-12-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-12-5') WHERE `Cd_Test` LIKE 'Rgaa32016-12-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-12-6') WHERE `Cd_Test` LIKE 'Rgaa32016-12-6-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-12-7') WHERE `Cd_Test` LIKE 'Rgaa32016-12-7-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-12-8') WHERE `Cd_Test` LIKE 'Rgaa32016-12-8-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-12-9') WHERE `Cd_Test` LIKE 'Rgaa32016-12-9-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-12-10') WHERE `Cd_Test` LIKE 'Rgaa32016-12-10-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-12-11') WHERE `Cd_Test` LIKE 'Rgaa32016-12-11-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-12-12') WHERE `Cd_Test` LIKE 'Rgaa32016-12-12-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-12-13') WHERE `Cd_Test` LIKE 'Rgaa32016-12-13-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-12-14') WHERE `Cd_Test` LIKE 'Rgaa32016-12-14-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-13-1') WHERE `Cd_Test` LIKE 'Rgaa32016-13-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-13-2') WHERE `Cd_Test` LIKE 'Rgaa32016-13-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-13-3') WHERE `Cd_Test` LIKE 'Rgaa32016-13-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-13-4') WHERE `Cd_Test` LIKE 'Rgaa32016-13-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-13-5') WHERE `Cd_Test` LIKE 'Rgaa32016-13-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-13-6') WHERE `Cd_Test` LIKE 'Rgaa32016-13-6-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-13-7') WHERE `Cd_Test` LIKE 'Rgaa32016-13-7-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-13-8') WHERE `Cd_Test` LIKE 'Rgaa32016-13-8-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-13-9') WHERE `Cd_Test` LIKE 'Rgaa32016-13-9-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-13-10') WHERE `Cd_Test` LIKE 'Rgaa32016-13-10-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-13-11') WHERE `Cd_Test` LIKE 'Rgaa32016-13-11-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-13-12') WHERE `Cd_Test` LIKE 'Rgaa32016-13-12-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-13-13') WHERE `Cd_Test` LIKE 'Rgaa32016-13-13-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-13-14') WHERE `Cd_Test` LIKE 'Rgaa32016-13-14-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-13-15') WHERE `Cd_Test` LIKE 'Rgaa32016-13-15-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-13-16') WHERE `Cd_Test` LIKE 'Rgaa32016-13-16-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa32016-13-17') WHERE `Cd_Test` LIKE 'Rgaa32016-13-17-%';

UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('01.Images/Rule-',                       SUBSTRING(`Cd_Test`,11), '.html') WHERE `Cd_Test` LIKE 'Rgaa32016-1-%';
UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('02.Frames/Rule-',                       SUBSTRING(`Cd_Test`,11), '.html') WHERE `Cd_Test` LIKE 'Rgaa32016-2-%';
UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('03.Colours/Rule-',                      SUBSTRING(`Cd_Test`,11), '.html') WHERE `Cd_Test` LIKE 'Rgaa32016-3-%';
UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('04.Multimedia/Rule-',                   SUBSTRING(`Cd_Test`,11), '.html') WHERE `Cd_Test` LIKE 'Rgaa32016-4-%';
UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('05.Tables/Rule-',                       SUBSTRING(`Cd_Test`,11), '.html') WHERE `Cd_Test` LIKE 'Rgaa32016-5-%';
UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('06.Links/Rule-',                        SUBSTRING(`Cd_Test`,11), '.html') WHERE `Cd_Test` LIKE 'Rgaa32016-6-%';
UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('07.Scripts/Rule-',                      SUBSTRING(`Cd_Test`,11), '.html') WHERE `Cd_Test` LIKE 'Rgaa32016-7-%';
UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('08.Mandatory_elements/Rule-',           SUBSTRING(`Cd_Test`,11), '.html') WHERE `Cd_Test` LIKE 'Rgaa32016-8-%';
UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('09.Structure_of_information/Rule-',     SUBSTRING(`Cd_Test`,11), '.html') WHERE `Cd_Test` LIKE 'Rgaa32016-9-%';
UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('10.Presentation_of_information/Rule-',  SUBSTRING(`Cd_Test`,11), '.html') WHERE `Cd_Test` LIKE 'Rgaa32016-10-%';
UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('11.Forms/Rule-',                        SUBSTRING(`Cd_Test`,11), '.html') WHERE `Cd_Test` LIKE 'Rgaa32016-11-%';
UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('12.Navigation/Rule-',                   SUBSTRING(`Cd_Test`,11), '.html') WHERE `Cd_Test` LIKE 'Rgaa32016-12-%';
UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('13.Consultation/Rule-',                 SUBSTRING(`Cd_Test`,11), '.html') WHERE `Cd_Test` LIKE 'Rgaa32016-13-%';
UPDATE `TEST` SET `Rule_Design_Url` = CONCAT('http://doc.asqatasun.org/en/90_Rules/rgaa3.2016/', `Rule_Design_Url`)     WHERE `Cd_Test` LIKE 'Rgaa32016-%';

INSERT IGNORE INTO `PARAMETER` (`Id_Parameter_Element`, `Parameter_Value`, `Is_Default`) VALUES
(5, 'Rgaa32016;LEVEL_1', b'0'),
(5, 'Rgaa32016;LEVEL_2', b'0'),
(5, 'Rgaa32016;LEVEL_3', b'0');

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-1-1-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-1-1-2';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-1-1-3';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-1-3-4';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-1-5-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-1-5-2';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-1-6-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-1-6-2';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-1-6-3';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-1-8-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-1-9-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-2-1-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-2-2-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-3-3-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-3-3-2';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-3-3-3';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-3-3-4';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-3-4-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-3-4-2';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-3-4-3';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-3-4-4';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-4-22-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-4-22-2';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-5-1-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-5-2-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-5-4-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-5-5-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-5-6-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-5-6-2';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-5-7-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-5-7-2';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-5-7-3';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-5-7-4';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-5-8-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-6-1-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-6-2-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-6-2-2';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-6-3-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-6-4-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-6-4-2';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-6-5-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-7-4-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-8-1-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-8-1-2';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-8-1-3';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-8-3-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-8-4-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-8-5-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-8-6-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-8-7-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-8-8-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-8-8-2';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-8-9-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-9-1-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-9-1-2';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-9-1-4';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-10-1-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-10-1-2';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-10-4-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-10-4-2';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-10-7-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-10-8-3';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-10-8-4';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-10-13-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-11-1-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-11-1-2';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-11-2-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-11-2-2';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-11-3-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-11-3-2';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-11-4-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-11-5-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-11-6-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-11-7-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-11-8-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-11-8-2';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-11-8-3';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-11-10-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-11-10-4';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-11-10-7';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-11-11-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-11-11-2';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-11-12-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-11-12-2';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-11-13-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-11-13-2';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-11-14-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-11-14-4';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-11-15-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-12-1-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-12-2-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-12-2-2';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-12-3-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-12-3-2';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-12-5-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-12-5-2';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-12-5-3';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-12-6-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-12-6-2';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-12-6-3';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-12-7-1';

UPDATE TEST SET `No_Process`=b'0' WHERE Cd_Test='Rgaa32016-12-11-4';

