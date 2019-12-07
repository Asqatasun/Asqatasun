INSERT IGNORE INTO `REFERENCE` (`Cd_Reference`, `Description`, `Label`, `Url`, `Rank`, `Id_Default_Level`) VALUES
('Rgaa30', NULL, 'Rgaa 3.0', '', 2000, 2);

-- INSERT IGNORE INTO `TGSI_REFERENTIAL` (`Code`, `Label`) VALUES
-- ('Rgaa30', 'Rgaa 3.0');

INSERT IGNORE INTO `THEME` (`Cd_Theme`, `Description`, `Label`, `Rank`) VALUES
('Rgaa30-1', NULL, 'Images', 1),
('Rgaa30-2', NULL, 'Cadres', 2),
('Rgaa30-3', NULL, 'Couleurs', 3),
('Rgaa30-4', NULL, 'Multimédia', 4),
('Rgaa30-5', NULL, 'Tableaux', 5),
('Rgaa30-6', NULL, 'Liens', 6),
('Rgaa30-7', NULL, 'Scripts', 7),
('Rgaa30-8', NULL, 'Éléments Obligatoires', 8),
('Rgaa30-9', NULL, 'Structuration de linformation', 9),
('Rgaa30-10', NULL, 'Présentation de linformation', 10),
('Rgaa30-11', NULL, 'Formulaires', 11),
('Rgaa30-12', NULL, 'Navigation', 12),
('Rgaa30-13', NULL, 'Consultation', 13);

INSERT IGNORE INTO `CRITERION` (`Cd_Criterion`, `Description`, `Label`, `Url`, `Rank`) VALUES
('Rgaa30-1-1', 'Chaque image a-t-elle une alternative textuelle ?', '1.1', '', 1),
('Rgaa30-1-2', 'Pour chaque image de décoration ayant une alternative textuelle, cette alternative est-elle vide ?', '1.2', '', 2),
('Rgaa30-1-3', 'Pour chaque image porteuse dinformation ayant une alternative textuelle, cette alternative est-elle pertinente (hors cas particuliers) ?', '1.3', '', 3),
('Rgaa30-1-4', 'Pour chaque image utilisée comme CAPTCHA ou comme image-test, ayant une alternative textuelle, cette alternative permet-elle didentifier la nature et la fonction de limage ?', '1.4', '', 4),
('Rgaa30-1-5', 'Pour chaque image utilisée comme CAPTCHA, une solution daccès alternatif au contenu ou à la fonction du CAPTCHA est-elle présente ?', '1.5', '', 5),
('Rgaa30-1-6', 'Chaque image porteuse dinformation a-t-elle, si nécessaire, une description détaillée ?', '1.6', '', 6),
('Rgaa30-1-7', 'Pour chaque image porteuse dinformation ayant une description détaillée, cette description est-elle pertinente ?', '1.7', '', 7),
('Rgaa30-1-8', 'Chaque image texte, en labsence dun mécanisme de remplacement, doit si possible être remplacée par du texte stylé. Cette règle est-elle respectée (hors cas particuliers) ?', '1.8', '', 8),
('Rgaa30-1-9', 'Chaque image texte doit si possible être remplacée par du texte stylé. Cette règle est-elle respectée (hors cas particuliers) ?', '1.9', '', 9),
('Rgaa30-1-10', 'Chaque légende dimage est-elle, si nécessaire, correctement reliée à limage correspondante ?', '1.10', '', 10),
('Rgaa30-2-1', 'Chaque cadre en ligne a-t-il un titre de cadre ?', '2.1', '', 11),
('Rgaa30-2-2', 'Pour chaque cadre en ligne ayant un titre de cadre, ce titre de cadre est-il pertinent ?', '2.2', '', 12),
('Rgaa30-3-1', 'Dans chaque page Web, linformation ne doit pas être donnée uniquement par la couleur. Cette règle est-elle respectée ?', '3.1', '', 13),
('Rgaa30-3-2', 'Dans chaque page Web, linformation ne doit pas être donnée uniquement par la couleur. Cette règle est-elle implémentée de façon pertinente ?', '3.2', '', 14),
('Rgaa30-3-3', 'Dans chaque page Web, le contraste entre la couleur du texte et la couleur de son arrière-plan est-il suffisamment élevé (hors cas particuliers) ?', '3.3', '', 15),
('Rgaa30-3-4', 'Dans chaque page Web, le contraste entre la couleur du texte et la couleur de son arrière-plan est-il amélioré (hors cas particuliers) ?', '3.4', '', 16),
('Rgaa30-4-1', 'Chaque média temporel pré-enregistré a-t-il, si nécessaire, une transcription textuelle ou une audio-description (hors cas particuliers) ?', '4.1', '', 17),
('Rgaa30-4-2', 'Pour chaque média temporel pré-enregistré ayant une transcription textuelle ou une audio-description synchronisée, celles-ci sont-elles pertinentes (hors cas particuliers) ?', '4.2', '', 18),
('Rgaa30-4-3', 'Chaque média temporel synchronisé pré-enregistré a-t-il, si nécessaire, des sous-titres synchronisés (hors cas particuliers) ?', '4.3', '', 19),
('Rgaa30-4-4', 'Pour chaque média temporel synchronisé pré-enregistré ayant des sous-titres synchronisés, ces sous-titres sont-ils pertinents ?', '4.4', '', 20),
('Rgaa30-4-5', 'Chaque média temporel en direct a-t-il, si nécessaire, des sous-titres synchronisés ou une transcription textuelle (hors cas particuliers) ?', '4.5', '', 21),
('Rgaa30-4-6', 'Pour chaque média temporel en direct ayant des sous-titres synchronisés ou une transcription textuelle, ceux-ci sont-ils pertinents ?', '4.6', '', 22),
('Rgaa30-4-7', 'Chaque média temporel pré-enregistré a-t-il, si nécessaire, une audio-description synchronisée (hors cas particuliers) ?', '4.7', '', 23),
('Rgaa30-4-8', 'Pour chaque média temporel pré-enregistré ayant une audio-description synchronisée, celle-ci est-elle pertinente ?', '4.8', '', 24),
('Rgaa30-4-9', 'Chaque média temporel pré-enregistré a-t-il, si nécessaire, une interprétation en langue des signes (hors cas particuliers) ?', '4.9', '', 25),
('Rgaa30-4-10', 'Pour chaque média temporel pré-enregistré ayant une interprétation en langue des signes, celle-ci est-elle pertinente ?', '4.10', '', 26),
('Rgaa30-4-11', 'Chaque média temporel pré-enregistré a-t-il, si nécessaire, une audio-description étendue synchronisée (hors cas particuliers) ?', '4.11', '', 27),
('Rgaa30-4-12', 'Pour chaque média temporel pré-enregistré ayant une audio-description étendue synchronisée, celle-ci est-elle pertinente ?', '4.12', '', 28),
('Rgaa30-4-13', 'Chaque média temporel synchronisé ou seulement vidéo a-t-il, si nécessaire, une transcription textuelle (hors cas particuliers) ?', '4.13', '', 29),
('Rgaa30-4-14', 'Pour chaque média temporel synchronisé ou seulement vidéo, ayant une transcription textuelle, celle-ci est-elle pertinente ?', '4.14', '', 30),
('Rgaa30-4-15', 'Chaque média temporel est-il clairement identifiable (hors cas particuliers) ?', '4.15', '', 31),
('Rgaa30-4-16', 'Chaque média non temporel a-t-il, si nécessaire, une alternative (hors cas particuliers) ?', '4.16', '', 32),
('Rgaa30-4-17', 'Pour chaque média non temporel ayant une alternative, cette alternative est-elle pertinente ?', '4.17', '', 33),
('Rgaa30-4-18', 'Chaque son déclenché automatiquement est-il contrôlable par lutilisateur ?', '4.18', '', 34),
('Rgaa30-4-19', 'Pour chaque média temporel seulement audio pré-enregistré, les dialogues sont-ils suffisamment audibles (hors cas particuliers) ?', '4.19', '', 35),
('Rgaa30-4-20', 'La consultation de chaque média temporel est-elle contrôlable par le clavier et la souris ?', '4.20', '', 36),
('Rgaa30-4-21', 'La consultation de chaque média non temporel est-elle contrôlable par le clavier et la souris ?', '4.21', '', 37),
('Rgaa30-4-22', 'Chaque média temporel et non temporel est-il compatible avec les technologies dassistance (hors cas particuliers) ?', '4.22', '', 38),
('Rgaa30-5-1', 'Chaque tableau de données complexe a-t-il un résumé ?', '5.1', '', 39),
('Rgaa30-5-2', 'Pour chaque tableau de données complexe ayant un résumé, celui-ci est-il pertinent ?', '5.2', '', 40),
('Rgaa30-5-3', 'Pour chaque tableau de mise en forme, le contenu linéarisé reste-t-il compréhensible ?', '5.3', '', 41),
('Rgaa30-5-4', 'Chaque tableau de données a-t-il un titre ?', '5.4', '', 42),
('Rgaa30-5-5', 'Pour chaque tableau de données ayant un titre, celui-ci est-il pertinent ?', '5.5', '', 43),
('Rgaa30-5-6', 'Pour chaque tableau de données, chaque en-tête de colonnes et chaque en-tête de lignes sont-ils correctement déclarés ?', '5.6', '', 44),
('Rgaa30-5-7', 'Pour chaque tableau de données, la technique appropriée permettant dassocier chaque cellule avec ses en-têtes est-elle utilisée ?', '5.7', '', 45),
('Rgaa30-5-8', 'Chaque tableau de mise en forme ne doit pas utiliser déléments propres aux tableaux de données. Cette règle est-elle respectée ?', '5.8', '', 46),
('Rgaa30-6-1', 'Chaque lien est-il explicite (hors cas particuliers) ?', '6.1', '', 47),
('Rgaa30-6-2', 'Pour chaque lien ayant un titre de lien, celui-ci est-il pertinent ?', '6.2', '', 48),
('Rgaa30-6-3', 'Chaque intitulé de lien seul est-il explicite hors contexte (hors cas particuliers) ?', '6.3', '', 49),
('Rgaa30-6-4', 'Chaque lien identique a-t-il les mêmes fonction et destination ?', '6.4', '', 50),
('Rgaa30-6-5', 'Dans chaque page Web, chaque lien, à lexception des ancres, a-t-il un intitulé ?', '6.5', '', 51),
('Rgaa30-7-1', 'Chaque script est-il, si nécessaire, compatible avec les technologies dassistance ?', '7.1', '', 52),
('Rgaa30-7-2', 'Pour chaque script ayant une alternative, cette alternative est-elle pertinente ?', '7.2', '', 53),
('Rgaa30-7-3', 'Chaque script est-il contrôlable par le clavier et la souris (hors cas particuliers) ?', '7.3', '', 54),
('Rgaa30-7-4', 'Pour chaque script qui initie un changement de contexte, lutilisateur est-il averti ou en a-t-il le contrôle ?', '7.4', '', 55),
('Rgaa30-7-5', 'Chaque script qui provoque une alerte non sollicitée est-il contrôlable par lutilisateur (hors cas particuliers) ?', '7.5', '', 56),
('Rgaa30-8-1', 'Chaque page Web est-elle définie par un type de document ?', '8.1', '', 57),
('Rgaa30-8-2', 'Pour chaque page Web, le code source est-il valide selon le type de document spécifié sauf cas particulier ?', '8.2', '', 58),
('Rgaa30-8-3', 'Dans chaque page Web, la langue par défaut est-elle présente ?', '8.3', '', 59),
('Rgaa30-8-4', 'Pour chaque page Web ayant une langue par défaut, le code de langue est-il pertinent ?', '8.4', '', 60),
('Rgaa30-8-5', 'Chaque page Web a-t-elle un titre de page ?', '8.5', '', 61),
('Rgaa30-8-6', 'Pour chaque page Web ayant un titre de page, ce titre est-il pertinent ?', '8.6', '', 62),
('Rgaa30-8-7', 'Dans chaque page Web, chaque changement de langue est-il indiqué dans le code source (hors cas particuliers) ?', '8.7', '', 63),
('Rgaa30-8-8', 'Dans chaque page Web, chaque changement de langue est-il pertinent ?', '8.8', '', 64),
('Rgaa30-8-9', 'Dans chaque page Web, les balises ne doivent pas être utilisées uniquement à des fins de présentation. Cette règle est-elle respectée ?', '8.9', '', 65),
('Rgaa30-8-10', 'Dans chaque page Web, les changements du sens de lecture sont-ils signalés ?', '8.10', '', 66),
('Rgaa30-9-1', 'Dans chaque page Web, linformation est-elle structurée par lutilisation appropriée de titres ?', '9.1', '', 67),
('Rgaa30-9-2', 'Dans chaque page Web, la structure du document est-elle cohérente ?', '9.2', '', 68),
('Rgaa30-9-3', 'Dans chaque page Web, chaque liste est-elle correctement structurée ?', '9.3', '', 69),
('Rgaa30-9-4', 'Dans chaque page Web, la première occurrence de chaque abréviation permet-elle den connaître la signification ?', '9.4', '', 70),
('Rgaa30-9-5', 'Dans chaque page Web, la signification de chaque abréviation est-elle pertinente ?', '9.5', '', 71),
('Rgaa30-9-6', 'Dans chaque page Web, chaque citation est-elle correctement indiquée ?', '9.6', '', 72),
('Rgaa30-10-1', 'Dans le site Web, des feuilles de styles sont-elles utilisées pour contrôler la présentation de linformation ?', '10.1', '', 73),
('Rgaa30-10-2', 'Dans chaque page Web, le contenu visible reste-t-il présent lorsque les feuilles de styles ou les images sont désactivées ?', '10.2', '', 74),
('Rgaa30-10-3', 'Dans chaque page Web, linformation reste-t-elle compréhensible lorsque les feuilles de styles sont désactivées ?', '10.3', '', 75),
('Rgaa30-10-4', 'Dans chaque page Web, le texte reste-t-il lisible lorsque la taille des caractères est augmentée jusquà 200%, au moins ?', '10.4', '', 76),
('Rgaa30-10-5', 'Dans chaque page Web, les déclarations CSS de couleurs de fond délément et de police sont-elles correctement utilisées?', '10.5', '', 77),
('Rgaa30-10-6', 'Dans chaque page Web, chaque lien dont la nature nest pas évidente est-il visible par rapport au texte environnant ?', '10.6', '', 78),
('Rgaa30-10-7', 'Dans chaque page Web, pour chaque élément recevant le focus, la prise de focus est-elle visible ?', '10.7', '', 79),
('Rgaa30-10-8', 'Dans chaque page Web, le choix de la couleur de fond et de police du texte est-il contrôlable par lutilisateur ?', '10.8', '', 80),
('Rgaa30-10-9', 'Pour chaque page Web, le texte ne doit pas être justifié. Cette règle est-elle respectée ?', '10.9', '', 81),
('Rgaa30-10-10', 'Pour chaque page Web, en affichage plein écran et avec une taille de police à 200%, chaque bloc de texte reste-t-il lisible sans lutilisation de la barre de défilement horizontal ?', '10.10', '', 82),
('Rgaa30-10-11', 'Pour chaque page Web, les blocs de texte ont-ils une largeur inférieure ou égale à 80 caractères (hors cas particuliers) ?', '10.11', '', 83),
('Rgaa30-10-12', 'Pour chaque page Web, lespace entre les lignes et les paragraphes est-il suffisant ?', '10.12', '', 84),
('Rgaa30-10-13', 'Pour chaque page Web, les textes cachés sont-ils correctement restitués par les technologies dassistance ?', '10.13', '', 85),
('Rgaa30-10-14', 'Dans chaque page Web, linformation ne doit pas être donnée uniquement par la forme, taille ou position. Cette règle est-elle respectée ?', '10.14', '', 86),
('Rgaa30-10-15', 'Dans chaque page Web, linformation ne doit pas être donnée par la forme, taille ou position uniquement. Cette règle est-elle implémentée de façon pertinente ?', '10.15', '', 87),
('Rgaa30-11-1', 'Chaque champ de formulaire a-t-il une étiquette ?', '11.1', '', 88),
('Rgaa30-11-2', 'Chaque étiquette associée à un champ de formulaire est-elle pertinente ?', '11.2', '', 89),
('Rgaa30-11-3', 'Dans chaque formulaire, chaque étiquette associée à un champ de formulaire ayant la même fonction et répétée plusieurs fois dans une même page ou dans un ensemble de pages est-elle cohérente ?', '11.3', '', 90),
('Rgaa30-11-4', 'Dans chaque formulaire, chaque étiquette de champ et son champ associé sont-ils accolés ?', '11.4', '', 91),
('Rgaa30-11-5', 'Dans chaque formulaire, les informations de même nature sont-elles regroupées, si nécessaire ?', '11.5', '', 92),
('Rgaa30-11-6', 'Dans chaque formulaire, chaque regroupement de champs de formulaire a-t-il une légende ?', '11.6', '', 93),
('Rgaa30-11-7', 'Dans chaque formulaire, chaque légende associée à un groupement de champs de formulaire est-elle pertinente ?', '11.7', '', 94),
('Rgaa30-11-8', 'Dans chaque formulaire, chaque liste de choix est-elle structurée de manière pertinente ?', '11.8', '', 95),
('Rgaa30-11-9', 'Dans chaque formulaire, lintitulé de chaque bouton est-il pertinent ?', '11.9', '', 96),
('Rgaa30-11-10', 'Dans chaque formulaire, le contrôle de saisie est-il utilisé de manière pertinente ?', '11.10', '', 97),
('Rgaa30-11-11', 'Dans chaque formulaire, le contrôle de saisie est-il accompagné, si possible, de suggestions facilitant la correction des erreurs de saisie ?', '11.11', '', 98),
('Rgaa30-11-12', 'Pour chaque formulaire, les données à caractère financier, juridique ou personnel peuvent-elles être modifiées, mise à jour ou récupérées par lutilisateur ?', '11.12', '', 99),
('Rgaa30-11-13', 'Pour chaque formulaire, toutes les données peuvent-elles être modifiées, mises à jour ou récupérées par lutilisateur ?', '11.13', '', 100),
('Rgaa30-11-14', 'Pour chaque formulaire, des aides à la saisie sont-elles présentes ?', '11.14', '', 101),
('Rgaa30-11-15', 'Pour chaque formulaire, chaque aide à la saisie est-elle pertinente ?', '11.15', '', 102),
('Rgaa30-12-1', 'Chaque ensemble de pages dispose-t-il de deux systèmes de navigation différents, au moins (hors cas particuliers) ?', '12.1', '', 103),
('Rgaa30-12-2', 'Dans chaque ensemble de pages, le menu ou les barres de navigation sont-ils toujours à la même place (hors cas particuliers) ?', '12.2', '', 104),
('Rgaa30-12-3', 'Dans chaque ensemble de pages, le menu et les barres de navigation ont-ils une présentation cohérente (hors cas particuliers) ?', '12.3', '', 105),
('Rgaa30-12-4', 'La page "plan du site" est-elle pertinente ?', '12.4', '', 106),
('Rgaa30-12-5', 'Dans chaque ensemble de pages, la page "plan du site" est-elle atteignable de manière identique ?', '12.5', '', 107),
('Rgaa30-12-6', 'Dans chaque ensemble de pages, le moteur de recherche est-il atteignable de manière identique ?', '12.6', '', 108),
('Rgaa30-12-7', 'Dans chaque page dune collection de pages, des liens facilitant la navigation sont-ils présents ?', '12.7', '', 109),
('Rgaa30-12-8', 'Dans chaque page web, un fil dAriane est-il présent ?', '12.8', '', 110),
('Rgaa30-12-9', 'Dans chaque page Web, le fil dAriane est-il pertinent ?', '12.9', '', 111),
('Rgaa30-12-10', 'Dans chaque page Web, les groupes de liens importants (menu, barre de navigation...) et la zone de contenu sont-ils identifiés ?', '12.10', '', 112),
('Rgaa30-12-11', 'Dans chaque page Web, des liens dévitement ou daccès rapide aux groupes de liens importants et à la zone de contenu sont-ils présents ?', '12.11', '', 113),
('Rgaa30-12-12', 'Dans chaque page Web, la page en cours de consultation est-elle indiquée dans le menu de navigation ?', '12.12', '', 114),
('Rgaa30-12-13', 'Dans chaque page Web, lordre de tabulation est-il cohérent ?', '12.13', '', 115),
('Rgaa30-12-14', 'Dans chaque page Web, la navigation ne doit pas contenir de piège au clavier. Cette règle est-elle respectée ?', '12.14', '', 116),
('Rgaa30-13-1', 'Pour chaque page Web, lutilisateur a-t-il le contrôle de chaque limite de temps modifiant le contenu (hors cas particuliers) ?', '13.1', '', 117),
('Rgaa30-13-2', 'Dans chaque page Web, pour chaque ouverture de nouvelle fenêtre, lutilisateur est-il averti ?', '13.2', '', 118),
('Rgaa30-13-3', 'Dans chaque page Web, louverture dune nouvelle fenêtre ne doit pas être déclenchée sans action de lutilisateur. Cette règle est-elle respectée ?', '13.3', '', 119),
('Rgaa30-13-4', 'Dans chaque page Web, une tâche ne doit pas requérir de limite de temps pour être réalisée, sauf si elle se déroule en temps réel ou si cette limite de temps est essentielle. Cette règle est-elle respectée ?', '13.4', '', 120),
('Rgaa30-13-5', 'Dans chaque page Web, lors dune interruption de session authentifiée, les données saisies par lutilisateur sont-elles récupérées après ré-authentification ?', '13.5', '', 121),
('Rgaa30-13-6', 'Dans chaque page Web, pour chaque fichier en téléchargement, des informations relatives à sa consultation sont-elles présentes (hors cas particuliers) ?', '13.6', '', 122),
('Rgaa30-13-7', 'Dans chaque page Web, chaque document bureautique en téléchargement possède-t-il, si nécessaire, une version accessible (hors cas particuliers) ?', '13.7', '', 123),
('Rgaa30-13-8', 'Pour chaque document bureautique ayant une version accessible, cette version offre-t-elle la même information ?', '13.8', '', 124),
('Rgaa30-13-9', 'Dans chaque page Web, les expressions inhabituelles, les expressions idiomatiques ou le jargon sont-ils explicités ?', '13.9', '', 125),
('Rgaa30-13-10', 'Dans chaque page Web, pour chaque expression inhabituelle ou limitée, idiomatique ou de jargon ayant une définition, cette définition est-elle pertinente ?', '13.10', '', 126),
('Rgaa30-13-11', 'Dans chaque page Web, chaque contenu cryptique (art ascii, émoticon, syntaxe cryptique) a-t-il une alternative ?', '13.11', '', 127),
('Rgaa30-13-12', 'Dans chaque page Web, pour chaque contenu cryptique (art ascii, émoticon, syntaxe cryptique) ayant une alternative, cette alternative est-elle pertinente ?', '13.12', '', 128),
('Rgaa30-13-13', 'Dans chaque page Web, pour chaque mot dont le sens ne peut être compris sans en connaître la prononciation, celle-ci est-elle indiquée ?', '13.13', '', 129),
('Rgaa30-13-14', 'Dans chaque page Web, chaque texte qui nécessite un niveau de lecture plus avancé que le premier cycle de lenseignement secondaire a-t-il une version alternative ?', '13.14', '', 130),
('Rgaa30-13-15', 'Dans chaque page Web, les changements brusques de luminosité ou les effets de flash sont-ils correctement utilisés ?', '13.15', '', 131),
('Rgaa30-13-16', 'Dans chaque page Web, les changements brusques de luminosité ou les effets de flash ont-ils une fréquence inférieure ou égale à 3 par seconde ?', '13.16', '', 132),
('Rgaa30-13-17', 'Dans chaque page Web, chaque contenu en mouvement ou clignotant est-il contrôlable par lutilisateur ?', '13.17', '', 133);

UPDATE `CRITERION` SET `Reference_Id_Reference` = (SELECT `Id_Reference` FROM `REFERENCE` WHERE `Cd_Reference` LIKE 'Rgaa30') WHERE `Cd_Criterion` LIKE 'Rgaa30-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa30-1') WHERE `Cd_Criterion` LIKE 'Rgaa30-1-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa30-2') WHERE `Cd_Criterion` LIKE 'Rgaa30-2-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa30-3') WHERE `Cd_Criterion` LIKE 'Rgaa30-3-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa30-4') WHERE `Cd_Criterion` LIKE 'Rgaa30-4-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa30-5') WHERE `Cd_Criterion` LIKE 'Rgaa30-5-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa30-6') WHERE `Cd_Criterion` LIKE 'Rgaa30-6-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa30-7') WHERE `Cd_Criterion` LIKE 'Rgaa30-7-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa30-8') WHERE `Cd_Criterion` LIKE 'Rgaa30-8-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa30-9') WHERE `Cd_Criterion` LIKE 'Rgaa30-9-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa30-10') WHERE `Cd_Criterion` LIKE 'Rgaa30-10-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa30-11') WHERE `Cd_Criterion` LIKE 'Rgaa30-11-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa30-12') WHERE `Cd_Criterion` LIKE 'Rgaa30-12-%';
UPDATE `CRITERION` SET `Theme_Id_Theme` = (SELECT `Id_Theme` FROM `THEME` WHERE `Cd_Theme` LIKE 'Rgaa30-13') WHERE `Cd_Criterion` LIKE 'Rgaa30-13-%';

INSERT IGNORE INTO `TEST` (`Cd_Test`, `Description`, `Label`, `Rank`, `Weight`, `Rule_Archive_Name`, `Rule_Class_Name`, `Id_Decision_Level`, `Id_Level`, `Id_Scope`, `Rule_Design_Url`, `No_Process`) VALUES
('Rgaa30-1-1-1', '', '1.1.1', 1, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010101', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-1-1.html', b'1'),
('Rgaa30-1-1-2', '', '1.1.2', 2, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010102', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-1-2.html', b'1'),
('Rgaa30-1-1-3', '', '1.1.3', 3, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010103', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-1-3.html', b'1'),
('Rgaa30-1-1-4', '', '1.1.4', 4, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010104', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-1-4.html', b'1'),
('Rgaa30-1-2-1', '', '1.2.1', 5, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010201', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-2-1.html', b'1'),
('Rgaa30-1-2-2', '', '1.2.2', 6, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010202', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-2-2.html', b'1'),
('Rgaa30-1-2-3', '', '1.2.3', 7, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010203', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-2-3.html', b'1'),
('Rgaa30-1-2-4', '', '1.2.4', 8, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010204', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-2-4.html', b'1'),
('Rgaa30-1-2-5', '', '1.2.5', 9, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010205', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-2-5.html', b'1'),
('Rgaa30-1-3-1', '', '1.3.1', 10, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010301', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-3-1.html', b'1'),
('Rgaa30-1-3-2', '', '1.3.2', 11, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010302', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-3-2.html', b'1'),
('Rgaa30-1-3-3', '', '1.3.3', 12, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010303', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-3-3.html', b'1'),
('Rgaa30-1-3-4', '', '1.3.4', 13, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010304', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-3-4.html', b'1'),
('Rgaa30-1-3-5', '', '1.3.5', 14, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010305', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-3-5.html', b'1'),
('Rgaa30-1-3-6', '', '1.3.6', 15, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010306', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-3-6.html', b'1'),
('Rgaa30-1-3-7', '', '1.3.7', 16, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010307', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-3-7.html', b'1'),
('Rgaa30-1-3-8', '', '1.3.8', 17, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010308', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-3-8.html', b'1'),
('Rgaa30-1-3-9', '', '1.3.9', 18, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010309', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-3-9.html', b'1'),
('Rgaa30-1-3-10', '', '1.3.10', 19, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010310', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-3-10.html', b'1'),
('Rgaa30-1-4-1', '', '1.4.1', 20, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010401', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-4-1.html', b'1'),
('Rgaa30-1-4-2', '', '1.4.2', 21, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010402', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-4-2.html', b'1'),
('Rgaa30-1-4-3', '', '1.4.3', 22, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010403', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-4-3.html', b'1'),
('Rgaa30-1-4-4', '', '1.4.4', 23, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010404', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-4-4.html', b'1'),
('Rgaa30-1-4-5', '', '1.4.5', 24, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010405', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-4-5.html', b'1'),
('Rgaa30-1-4-6', '', '1.4.6', 25, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010406', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-4-6.html', b'1'),
('Rgaa30-1-4-7', '', '1.4.7', 26, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010407', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-4-7.html', b'1'),
('Rgaa30-1-4-8', '', '1.4.8', 27, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010408', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-4-8.html', b'1'),
('Rgaa30-1-4-9', '', '1.4.9', 28, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010409', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-4-9.html', b'1'),
('Rgaa30-1-5-1', '', '1.5.1', 29, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010501', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-5-1.html', b'1'),
('Rgaa30-1-5-2', '', '1.5.2', 30, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010502', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-5-2.html', b'1'),
('Rgaa30-1-6-1', '', '1.6.1', 31, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010601', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-6-1.html', b'1'),
('Rgaa30-1-6-2', '', '1.6.2', 32, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010602', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-6-2.html', b'1'),
('Rgaa30-1-6-3', '', '1.6.3', 33, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010603', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-6-3.html', b'1'),
('Rgaa30-1-6-4', '', '1.6.4', 34, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010604', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-6-4.html', b'1'),
('Rgaa30-1-6-5', '', '1.6.5', 35, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010605', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-6-5.html', b'1'),
('Rgaa30-1-6-6', '', '1.6.6', 36, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010606', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-6-6.html', b'1'),
('Rgaa30-1-6-7', '', '1.6.7', 37, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010607', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-6-7.html', b'1'),
('Rgaa30-1-6-8', '', '1.6.8', 38, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010608', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-6-8.html', b'1'),
('Rgaa30-1-7-1', '', '1.7.1', 39, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010701', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-7-1.html', b'1'),
('Rgaa30-1-7-2', '', '1.7.2', 40, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010702', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-7-2.html', b'1'),
('Rgaa30-1-7-3', '', '1.7.3', 41, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010703', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-7-3.html', b'1'),
('Rgaa30-1-7-4', '', '1.7.4', 42, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010704', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-7-4.html', b'1'),
('Rgaa30-1-7-5', '', '1.7.5', 43, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010705', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-7-5.html', b'1'),
('Rgaa30-1-7-6', '', '1.7.6', 44, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010706', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-7-6.html', b'1'),
('Rgaa30-1-7-7', '', '1.7.7', 45, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010707', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-7-7.html', b'1'),
('Rgaa30-1-8-1', '', '1.8.1', 46, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010801', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-8-1.html', b'1'),
('Rgaa30-1-8-2', '', '1.8.2', 47, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010802', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-8-2.html', b'1'),
('Rgaa30-1-8-3', '', '1.8.3', 48, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010803', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-8-3.html', b'1'),
('Rgaa30-1-8-4', '', '1.8.4', 49, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010804', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-8-4.html', b'1'),
('Rgaa30-1-8-5', '', '1.8.5', 50, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010805', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-8-5.html', b'1'),
('Rgaa30-1-8-6', '', '1.8.6', 51, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010806', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-8-6.html', b'1'),
('Rgaa30-1-9-1', '', '1.9.1', 52, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010901', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-9-1.html', b'1'),
('Rgaa30-1-9-2', '', '1.9.2', 53, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010902', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-9-2.html', b'1'),
('Rgaa30-1-9-3', '', '1.9.3', 54, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010903', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-9-3.html', b'1'),
('Rgaa30-1-9-4', '', '1.9.4', 55, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010904', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-9-4.html', b'1'),
('Rgaa30-1-9-5', '', '1.9.5', 56, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010905', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-9-5.html', b'1'),
('Rgaa30-1-9-6', '', '1.9.6', 57, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule010906', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-9-6.html', b'1'),
('Rgaa30-1-10-1', '', '1.10.1', 58, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule011001', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-10-1.html', b'1'),
('Rgaa30-1-10-2', '', '1.10.2', 59, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule011002', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-10-2.html', b'1'),
('Rgaa30-1-10-3', '', '1.10.3', 60, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule011003', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-10-3.html', b'1'),
('Rgaa30-1-10-4', '', '1.10.4', 61, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule011004', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-10-4.html', b'1'),
('Rgaa30-1-10-5', '', '1.10.5', 62, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule011005', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-10-5.html', b'1'),
('Rgaa30-2-1-1', '', '2.1.1', 63, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule020101', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/02.Frames/Rule-2-1-1.html', b'1'),
('Rgaa30-2-2-1', '', '2.2.1', 64, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule020201', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/02.Frames/Rule-2-2-1.html', b'1'),
('Rgaa30-3-1-1', '', '3.1.1', 65, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule030101', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/03.Colours/Rule-3-1-1.html', b'1'),
('Rgaa30-3-1-2', '', '3.1.2', 66, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule030102', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/03.Colours/Rule-3-1-2.html', b'1'),
('Rgaa30-3-1-3', '', '3.1.3', 67, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule030103', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/03.Colours/Rule-3-1-3.html', b'1'),
('Rgaa30-3-1-4', '', '3.1.4', 68, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule030104', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/03.Colours/Rule-3-1-4.html', b'1'),
('Rgaa30-3-1-5', '', '3.1.5', 69, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule030105', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/03.Colours/Rule-3-1-5.html', b'1'),
('Rgaa30-3-1-6', '', '3.1.6', 70, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule030106', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/03.Colours/Rule-3-1-6.html', b'1'),
('Rgaa30-3-2-1', '', '3.2.1', 71, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule030201', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/03.Colours/Rule-3-2-1.html', b'1'),
('Rgaa30-3-2-2', '', '3.2.2', 72, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule030202', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/03.Colours/Rule-3-2-2.html', b'1'),
('Rgaa30-3-2-3', '', '3.2.3', 73, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule030203', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/03.Colours/Rule-3-2-3.html', b'1'),
('Rgaa30-3-2-4', '', '3.2.4', 74, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule030204', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/03.Colours/Rule-3-2-4.html', b'1'),
('Rgaa30-3-2-5', '', '3.2.5', 75, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule030205', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/03.Colours/Rule-3-2-5.html', b'1'),
('Rgaa30-3-2-6', '', '3.2.6', 76, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule030206', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/03.Colours/Rule-3-2-6.html', b'1'),
('Rgaa30-3-3-1', '', '3.3.1', 77, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule030301', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/03.Colours/Rule-3-3-1.html', b'1'),
('Rgaa30-3-3-2', '', '3.3.2', 78, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule030302', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/03.Colours/Rule-3-3-2.html', b'1'),
('Rgaa30-3-3-3', '', '3.3.3', 79, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule030303', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/03.Colours/Rule-3-3-3.html', b'1'),
('Rgaa30-3-3-4', '', '3.3.4', 80, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule030304', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/03.Colours/Rule-3-3-4.html', b'1'),
('Rgaa30-3-4-1', '', '3.4.1', 81, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule030401', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/03.Colours/Rule-3-4-1.html', b'1'),
('Rgaa30-3-4-2', '', '3.4.2', 82, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule030402', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/03.Colours/Rule-3-4-2.html', b'1'),
('Rgaa30-3-4-3', '', '3.4.3', 83, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule030403', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/03.Colours/Rule-3-4-3.html', b'1'),
('Rgaa30-3-4-4', '', '3.4.4', 84, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule030404', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/03.Colours/Rule-3-4-4.html', b'1'),
('Rgaa30-4-1-1', '', '4.1.1', 85, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule040101', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-1-1.html', b'1'),
('Rgaa30-4-1-2', '', '4.1.2', 86, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule040102', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-1-2.html', b'1'),
('Rgaa30-4-1-3', '', '4.1.3', 87, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule040103', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-1-3.html', b'1'),
('Rgaa30-4-2-1', '', '4.2.1', 88, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule040201', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-2-1.html', b'1'),
('Rgaa30-4-2-2', '', '4.2.2', 89, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule040202', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-2-2.html', b'1'),
('Rgaa30-4-2-3', '', '4.2.3', 90, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule040203', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-2-3.html', b'1'),
('Rgaa30-4-3-1', '', '4.3.1', 91, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule040301', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-3-1.html', b'1'),
('Rgaa30-4-3-2', '', '4.3.2', 92, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule040302', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-3-2.html', b'1'),
('Rgaa30-4-4-1', '', '4.4.1', 93, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule040401', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-4-1.html', b'1'),
('Rgaa30-4-5-1', '', '4.5.1', 94, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule040501', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-5-1.html', b'1'),
('Rgaa30-4-5-2', '', '4.5.2', 95, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule040502', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-5-2.html', b'1'),
('Rgaa30-4-6-1', '', '4.6.1', 96, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule040601', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-6-1.html', b'1'),
('Rgaa30-4-6-2', '', '4.6.2', 97, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule040602', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-6-2.html', b'1'),
('Rgaa30-4-7-1', '', '4.7.1', 98, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule040701', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-7-1.html', b'1'),
('Rgaa30-4-7-2', '', '4.7.2', 99, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule040702', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-7-2.html', b'1'),
('Rgaa30-4-8-1', '', '4.8.1', 100, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule040801', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-8-1.html', b'1'),
('Rgaa30-4-8-2', '', '4.8.2', 101, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule040802', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-8-2.html', b'1'),
('Rgaa30-4-9-1', '', '4.9.1', 102, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule040901', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-9-1.html', b'1'),
('Rgaa30-4-9-2', '', '4.9.2', 103, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule040902', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-9-2.html', b'1'),
('Rgaa30-4-10-1', '', '4.10.1', 104, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule041001', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-10-1.html', b'1'),
('Rgaa30-4-10-2', '', '4.10.2', 105, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule041002', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-10-2.html', b'1'),
('Rgaa30-4-11-1', '', '4.11.1', 106, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule041101', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-11-1.html', b'1'),
('Rgaa30-4-11-2', '', '4.11.2', 107, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule041102', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-11-2.html', b'1'),
('Rgaa30-4-12-1', '', '4.12.1', 108, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule041201', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-12-1.html', b'1'),
('Rgaa30-4-12-2', '', '4.12.2', 109, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule041202', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-12-2.html', b'1'),
('Rgaa30-4-13-1', '', '4.13.1', 110, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule041301', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-13-1.html', b'1'),
('Rgaa30-4-13-2', '', '4.13.2', 111, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule041302', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-13-2.html', b'1'),
('Rgaa30-4-14-1', '', '4.14.1', 112, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule041401', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-14-1.html', b'1'),
('Rgaa30-4-14-2', '', '4.14.2', 113, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule041402', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-14-2.html', b'1'),
('Rgaa30-4-15-1', '', '4.15.1', 114, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule041501', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-15-1.html', b'1'),
('Rgaa30-4-15-2', '', '4.15.2', 115, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule041502', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-15-2.html', b'1'),
('Rgaa30-4-16-1', '', '4.16.1', 116, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule041601', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-16-1.html', b'1'),
('Rgaa30-4-16-2', '', '4.16.2', 117, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule041602', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-16-2.html', b'1'),
('Rgaa30-4-17-1', '', '4.17.1', 118, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule041701', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-17-1.html', b'1'),
('Rgaa30-4-18-1', '', '4.18.1', 119, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule041801', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-18-1.html', b'1'),
('Rgaa30-4-19-1', '', '4.19.1', 120, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule041901', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-19-1.html', b'1'),
('Rgaa30-4-20-1', '', '4.20.1', 121, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule042001', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-20-1.html', b'1'),
('Rgaa30-4-20-2', '', '4.20.2', 122, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule042002', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-20-2.html', b'1'),
('Rgaa30-4-20-3', '', '4.20.3', 123, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule042003', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-20-3.html', b'1'),
('Rgaa30-4-21-1', '', '4.21.1', 124, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule042101', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-21-1.html', b'1'),
('Rgaa30-4-21-2', '', '4.21.2', 125, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule042102', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-21-2.html', b'1'),
('Rgaa30-4-22-1', '', '4.22.1', 126, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule042201', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-22-1.html', b'1'),
('Rgaa30-4-22-2', '', '4.22.2', 127, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule042202', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/04.Multimedia/Rule-4-22-2.html', b'1'),
('Rgaa30-5-1-1', '', '5.1.1', 128, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule050101', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/05.Tables/Rule-5-1-1.html', b'1'),
('Rgaa30-5-2-1', '', '5.2.1', 129, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule050201', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/05.Tables/Rule-5-2-1.html', b'1'),
('Rgaa30-5-3-1', '', '5.3.1', 130, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule050301', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/05.Tables/Rule-5-3-1.html', b'1'),
('Rgaa30-5-4-1', '', '5.4.1', 131, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule050401', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/05.Tables/Rule-5-4-1.html', b'1'),
('Rgaa30-5-5-1', '', '5.5.1', 132, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule050501', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/05.Tables/Rule-5-5-1.html', b'1'),
('Rgaa30-5-6-1', '', '5.6.1', 133, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule050601', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/05.Tables/Rule-5-6-1.html', b'1'),
('Rgaa30-5-6-2', '', '5.6.2', 134, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule050602', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/05.Tables/Rule-5-6-2.html', b'1'),
('Rgaa30-5-7-1', '', '5.7.1', 135, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule050701', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/05.Tables/Rule-5-7-1.html', b'1'),
('Rgaa30-5-7-2', '', '5.7.2', 136, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule050702', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/05.Tables/Rule-5-7-2.html', b'1'),
('Rgaa30-5-7-3', '', '5.7.3', 137, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule050703', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/05.Tables/Rule-5-7-3.html', b'1'),
('Rgaa30-5-7-4', '', '5.7.4', 138, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule050704', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/05.Tables/Rule-5-7-4.html', b'1'),
('Rgaa30-5-8-1', '', '5.8.1', 139, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule050801', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/05.Tables/Rule-5-8-1.html', b'1'),
('Rgaa30-6-1-1', '', '6.1.1', 140, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule060101', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/06.Links/Rule-6-1-1.html', b'1'),
('Rgaa30-6-1-2', '', '6.1.2', 141, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule060102', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/06.Links/Rule-6-1-2.html', b'1'),
('Rgaa30-6-1-3', '', '6.1.3', 142, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule060103', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/06.Links/Rule-6-1-3.html', b'1'),
('Rgaa30-6-1-4', '', '6.1.4', 143, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule060104', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/06.Links/Rule-6-1-4.html', b'1'),
('Rgaa30-6-1-5', '', '6.1.5', 144, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule060105', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/06.Links/Rule-6-1-5.html', b'1'),
('Rgaa30-6-2-1', '', '6.2.1', 145, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule060201', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/06.Links/Rule-6-2-1.html', b'1'),
('Rgaa30-6-2-2', '', '6.2.2', 146, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule060202', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/06.Links/Rule-6-2-2.html', b'1'),
('Rgaa30-6-2-3', '', '6.2.3', 147, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule060203', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/06.Links/Rule-6-2-3.html', b'1'),
('Rgaa30-6-2-4', '', '6.2.4', 148, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule060204', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/06.Links/Rule-6-2-4.html', b'1'),
('Rgaa30-6-2-5', '', '6.2.5', 149, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule060205', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/06.Links/Rule-6-2-5.html', b'1'),
('Rgaa30-6-3-1', '', '6.3.1', 150, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule060301', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/06.Links/Rule-6-3-1.html', b'1'),
('Rgaa30-6-3-2', '', '6.3.2', 151, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule060302', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/06.Links/Rule-6-3-2.html', b'1'),
('Rgaa30-6-3-3', '', '6.3.3', 152, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule060303', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/06.Links/Rule-6-3-3.html', b'1'),
('Rgaa30-6-3-4', '', '6.3.4', 153, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule060304', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/06.Links/Rule-6-3-4.html', b'1'),
('Rgaa30-6-3-5', '', '6.3.5', 154, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule060305', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/06.Links/Rule-6-3-5.html', b'1'),
('Rgaa30-6-4-1', '', '6.4.1', 155, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule060401', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/06.Links/Rule-6-4-1.html', b'1'),
('Rgaa30-6-4-2', '', '6.4.2', 156, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule060402', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/06.Links/Rule-6-4-2.html', b'1'),
('Rgaa30-6-4-3', '', '6.4.3', 157, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule060403', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/06.Links/Rule-6-4-3.html', b'1'),
('Rgaa30-6-4-4', '', '6.4.4', 158, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule060404', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/06.Links/Rule-6-4-4.html', b'1'),
('Rgaa30-6-4-5', '', '6.4.5', 159, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule060405', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/06.Links/Rule-6-4-5.html', b'1'),
('Rgaa30-6-5-1', '', '6.5.1', 160, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule060501', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/06.Links/Rule-6-5-1.html', b'1'),
('Rgaa30-7-1-1', '', '7.1.1', 161, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule070101', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/07.Scripts/Rule-7-1-1.html', b'1'),
('Rgaa30-7-1-2', '', '7.1.2', 162, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule070102', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/07.Scripts/Rule-7-1-2.html', b'1'),
('Rgaa30-7-1-3', '', '7.1.3', 163, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule070103', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/07.Scripts/Rule-7-1-3.html', b'1'),
('Rgaa30-7-1-4', '', '7.1.4', 164, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule070104', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/07.Scripts/Rule-7-1-4.html', b'1'),
('Rgaa30-7-1-5', '', '7.1.5', 165, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule070105', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/07.Scripts/Rule-7-1-5.html', b'1'),
('Rgaa30-7-1-6', '', '7.1.6', 166, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule070106', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/07.Scripts/Rule-7-1-6.html', b'1'),
('Rgaa30-7-2-1', '', '7.2.1', 167, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule070201', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/07.Scripts/Rule-7-2-1.html', b'1'),
('Rgaa30-7-2-2', '', '7.2.2', 168, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule070202', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/07.Scripts/Rule-7-2-2.html', b'1'),
('Rgaa30-7-3-1', '', '7.3.1', 169, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule070301', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/07.Scripts/Rule-7-3-1.html', b'1'),
('Rgaa30-7-3-2', '', '7.3.2', 170, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule070302', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/07.Scripts/Rule-7-3-2.html', b'1'),
('Rgaa30-7-3-3', '', '7.3.3', 171, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule070303', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/07.Scripts/Rule-7-3-3.html', b'1'),
('Rgaa30-7-3-4', '', '7.3.4', 172, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule070304', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/07.Scripts/Rule-7-3-4.html', b'1'),
('Rgaa30-7-4-1', '', '7.4.1', 173, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule070401', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/07.Scripts/Rule-7-4-1.html', b'1'),
('Rgaa30-7-5-1', '', '7.5.1', 174, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule070501', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/07.Scripts/Rule-7-5-1.html', b'1'),
('Rgaa30-8-1-1', '', '8.1.1', 175, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule080101', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/08.Mandatory_elements/Rule-8-1-1.html', b'1'),
('Rgaa30-8-1-2', '', '8.1.2', 176, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule080102', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/08.Mandatory_elements/Rule-8-1-2.html', b'1'),
('Rgaa30-8-1-3', '', '8.1.3', 177, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule080103', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/08.Mandatory_elements/Rule-8-1-3.html', b'1'),
('Rgaa30-8-2-1', '', '8.2.1', 178, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule080201', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/08.Mandatory_elements/Rule-8-2-1.html', b'1'),
('Rgaa30-8-2-2', '', '8.2.2', 179, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule080202', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/08.Mandatory_elements/Rule-8-2-2.html', b'1'),
('Rgaa30-8-3-1', '', '8.3.1', 180, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule080301', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/08.Mandatory_elements/Rule-8-3-1.html', b'1'),
('Rgaa30-8-4-1', '', '8.4.1', 181, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule080401', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/08.Mandatory_elements/Rule-8-4-1.html', b'1'),
('Rgaa30-8-5-1', '', '8.5.1', 182, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule080501', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/08.Mandatory_elements/Rule-8-5-1.html', b'1'),
('Rgaa30-8-6-1', '', '8.6.1', 183, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule080601', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/08.Mandatory_elements/Rule-8-6-1.html', b'1'),
('Rgaa30-8-7-1', '', '8.7.1', 184, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule080701', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/08.Mandatory_elements/Rule-8-7-1.html', b'1'),
('Rgaa30-8-8-1', '', '8.8.1', 185, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule080801', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/08.Mandatory_elements/Rule-8-8-1.html', b'1'),
('Rgaa30-8-8-2', '', '8.8.2', 186, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule080802', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/08.Mandatory_elements/Rule-8-8-2.html', b'1'),
('Rgaa30-8-9-1', '', '8.9.1', 187, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule080901', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/08.Mandatory_elements/Rule-8-9-1.html', b'1'),
('Rgaa30-8-10-1', '', '8.10.1', 188, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule081001', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/08.Mandatory_elements/Rule-8-10-1.html', b'1'),
('Rgaa30-9-1-1', '', '9.1.1', 189, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule090101', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/09.Structure_of_information/Rule-9-1-1.html', b'1'),
('Rgaa30-9-1-2', '', '9.1.2', 190, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule090102', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/09.Structure_of_information/Rule-9-1-2.html', b'1'),
('Rgaa30-9-1-3', '', '9.1.3', 191, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule090103', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/09.Structure_of_information/Rule-9-1-3.html', b'1'),
('Rgaa30-9-1-4', '', '9.1.4', 192, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule090104', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/09.Structure_of_information/Rule-9-1-4.html', b'1'),
('Rgaa30-9-2-1', '', '9.2.1', 193, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule090201', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/09.Structure_of_information/Rule-9-2-1.html', b'1'),
('Rgaa30-9-2-2', '', '9.2.2', 194, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule090202', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/09.Structure_of_information/Rule-9-2-2.html', b'1'),
('Rgaa30-9-3-1', '', '9.3.1', 195, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule090301', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/09.Structure_of_information/Rule-9-3-1.html', b'1'),
('Rgaa30-9-3-2', '', '9.3.2', 196, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule090302', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/09.Structure_of_information/Rule-9-3-2.html', b'1'),
('Rgaa30-9-3-3', '', '9.3.3', 197, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule090303', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/09.Structure_of_information/Rule-9-3-3.html', b'1'),
('Rgaa30-9-4-1', '', '9.4.1', 198, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule090401', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/09.Structure_of_information/Rule-9-4-1.html', b'1'),
('Rgaa30-9-5-1', '', '9.5.1', 199, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule090501', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/09.Structure_of_information/Rule-9-5-1.html', b'1'),
('Rgaa30-9-6-1', '', '9.6.1', 200, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule090601', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/09.Structure_of_information/Rule-9-6-1.html', b'1'),
('Rgaa30-9-6-2', '', '9.6.2', 201, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule090602', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/09.Structure_of_information/Rule-9-6-2.html', b'1'),
('Rgaa30-10-1-1', '', '10.1.1', 202, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule100101', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-1-1.html', b'1'),
('Rgaa30-10-1-2', '', '10.1.2', 203, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule100102', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-1-2.html', b'1'),
('Rgaa30-10-1-3', '', '10.1.3', 204, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule100103', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-1-3.html', b'1'),
('Rgaa30-10-2-1', '', '10.2.1', 205, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule100201', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-2-1.html', b'1'),
('Rgaa30-10-3-1', '', '10.3.1', 206, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule100301', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-3-1.html', b'1'),
('Rgaa30-10-4-1', '', '10.4.1', 207, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule100401', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-4-1.html', b'1'),
('Rgaa30-10-4-2', '', '10.4.2', 208, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule100402', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-4-2.html', b'1'),
('Rgaa30-10-4-3', '', '10.4.3', 209, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule100403', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-4-3.html', b'1'),
('Rgaa30-10-5-1', '', '10.5.1', 210, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule100501', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-5-1.html', b'1'),
('Rgaa30-10-5-2', '', '10.5.2', 211, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule100502', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-5-2.html', b'1'),
('Rgaa30-10-5-3', '', '10.5.3', 212, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule100503', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-5-3.html', b'1'),
('Rgaa30-10-6-1', '', '10.6.1', 213, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule100601', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-6-1.html', b'1'),
('Rgaa30-10-7-1', '', '10.7.1', 214, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule100701', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-7-1.html', b'1'),
('Rgaa30-10-7-2', '', '10.7.2', 215, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule100702', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-7-2.html', b'1'),
('Rgaa30-10-7-3', '', '10.7.3', 216, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule100703', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-7-3.html', b'1'),
('Rgaa30-10-8-1', '', '10.8.1', 217, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule100801', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-8-1.html', b'1'),
('Rgaa30-10-8-2', '', '10.8.2', 218, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule100802', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-8-2.html', b'1'),
('Rgaa30-10-8-3', '', '10.8.3', 219, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule100803', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-8-3.html', b'1'),
('Rgaa30-10-8-4', '', '10.8.4', 220, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule100804', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-8-4.html', b'1'),
('Rgaa30-10-9-1', '', '10.9.1', 221, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule100901', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-9-1.html', b'1'),
('Rgaa30-10-10-1', '', '10.10.1', 222, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule101001', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-10-1.html', b'1'),
('Rgaa30-10-11-1', '', '10.11.1', 223, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule101101', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-11-1.html', b'1'),
('Rgaa30-10-12-1', '', '10.12.1', 224, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule101201', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-12-1.html', b'1'),
('Rgaa30-10-12-2', '', '10.12.2', 225, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule101202', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-12-2.html', b'1'),
('Rgaa30-10-13-1', '', '10.13.1', 226, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule101301', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-13-1.html', b'1'),
('Rgaa30-10-13-2', '', '10.13.2', 227, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule101302', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-13-2.html', b'1'),
('Rgaa30-10-13-3', '', '10.13.3', 228, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule101303', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-13-3.html', b'1'),
('Rgaa30-10-14-1', '', '10.14.1', 229, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule101401', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-14-1.html', b'1'),
('Rgaa30-10-14-2', '', '10.14.2', 230, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule101402', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-14-2.html', b'1'),
('Rgaa30-10-14-3', '', '10.14.3', 231, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule101403', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-14-3.html', b'1'),
('Rgaa30-10-14-4', '', '10.14.4', 232, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule101404', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-14-4.html', b'1'),
('Rgaa30-10-15-1', '', '10.15.1', 233, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule101501', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-15-1.html', b'1'),
('Rgaa30-10-15-2', '', '10.15.2', 234, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule101502', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-15-2.html', b'1'),
('Rgaa30-10-15-3', '', '10.15.3', 235, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule101503', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-15-3.html', b'1'),
('Rgaa30-10-15-4', '', '10.15.4', 236, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule101504', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/10.Presentation_of_information/Rule-10-15-4.html', b'1'),
('Rgaa30-11-1-1', '', '11.1.1', 237, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule110101', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-1-1.html', b'1'),
('Rgaa30-11-1-2', '', '11.1.2', 238, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule110102', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-1-2.html', b'1'),
('Rgaa30-11-1-3', '', '11.1.3', 239, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule110103', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-1-3.html', b'1'),
('Rgaa30-11-2-1', '', '11.2.1', 240, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule110201', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-2-1.html', b'1'),
('Rgaa30-11-2-2', '', '11.2.2', 241, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule110202', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-2-2.html', b'1'),
('Rgaa30-11-2-3', '', '11.2.3', 242, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule110203', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-2-3.html', b'1'),
('Rgaa30-11-2-4', '', '11.2.4', 243, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule110204', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-2-4.html', b'1'),
('Rgaa30-11-3-1', '', '11.3.1', 244, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule110301', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-3-1.html', b'1'),
('Rgaa30-11-3-2', '', '11.3.2', 245, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule110302', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-3-2.html', b'1'),
('Rgaa30-11-4-1', '', '11.4.1', 246, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule110401', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-4-1.html', b'1'),
('Rgaa30-11-5-1', '', '11.5.1', 247, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule110501', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-5-1.html', b'1'),
('Rgaa30-11-6-1', '', '11.6.1', 248, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule110601', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-6-1.html', b'1'),
('Rgaa30-11-7-1', '', '11.7.1', 249, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule110701', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-7-1.html', b'1'),
('Rgaa30-11-8-1', '', '11.8.1', 250, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule110801', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-8-1.html', b'1'),
('Rgaa30-11-8-2', '', '11.8.2', 251, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule110802', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-8-2.html', b'1'),
('Rgaa30-11-8-3', '', '11.8.3', 252, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule110803', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-8-3.html', b'1'),
('Rgaa30-11-9-1', '', '11.9.1', 253, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule110901', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-9-1.html', b'1'),
('Rgaa30-11-9-2', '', '11.9.2', 254, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule110902', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-9-2.html', b'1'),
('Rgaa30-11-10-1', '', '11.10.1', 255, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule111001', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-10-1.html', b'1'),
('Rgaa30-11-10-2', '', '11.10.2', 256, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule111002', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-10-2.html', b'1'),
('Rgaa30-11-10-3', '', '11.10.3', 257, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule111003', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-10-3.html', b'1'),
('Rgaa30-11-10-4', '', '11.10.4', 258, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule111004', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-10-4.html', b'1'),
('Rgaa30-11-10-5', '', '11.10.5', 259, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule111005', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-10-5.html', b'1'),
('Rgaa30-11-10-6', '', '11.10.6', 260, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule111006', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-10-6.html', b'1'),
('Rgaa30-11-10-7', '', '11.10.7', 261, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule111007', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-10-7.html', b'1'),
('Rgaa30-11-10-8', '', '11.10.8', 262, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule111008', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-10-8.html', b'1'),
('Rgaa30-11-10-9', '', '11.10.9', 263, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule111009', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-10-9.html', b'1'),
('Rgaa30-11-11-1', '', '11.11.1', 264, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule111101', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-11-1.html', b'1'),
('Rgaa30-11-11-2', '', '11.11.2', 265, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule111102', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-11-2.html', b'1'),
('Rgaa30-11-12-1', '', '11.12.1', 266, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule111201', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-12-1.html', b'1'),
('Rgaa30-11-12-2', '', '11.12.2', 267, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule111202', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-12-2.html', b'1'),
('Rgaa30-11-13-1', '', '11.13.1', 268, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule111301', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-13-1.html', b'1'),
('Rgaa30-11-13-2', '', '11.13.2', 269, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule111302', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-13-2.html', b'1'),
('Rgaa30-11-14-1', '', '11.14.1', 270, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule111401', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-14-1.html', b'1'),
('Rgaa30-11-14-2', '', '11.14.2', 271, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule111402', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-14-2.html', b'1'),
('Rgaa30-11-14-3', '', '11.14.3', 272, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule111403', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-14-3.html', b'1'),
('Rgaa30-11-14-4', '', '11.14.4', 273, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule111404', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-14-4.html', b'1'),
('Rgaa30-11-14-5', '', '11.14.5', 274, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule111405', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-14-5.html', b'1'),
('Rgaa30-11-14-6', '', '11.14.6', 275, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule111406', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-14-6.html', b'1'),
('Rgaa30-11-15-1', '', '11.15.1', 276, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule111501', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/11.Forms/Rule-11-15-1.html', b'1'),
('Rgaa30-12-1-1', '', '12.1.1', 277, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule120101', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/12.Navigation/Rule-12-1-1.html', b'1'),
('Rgaa30-12-2-1', '', '12.2.1', 278, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule120201', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/12.Navigation/Rule-12-2-1.html', b'1'),
('Rgaa30-12-2-2', '', '12.2.2', 279, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule120202', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/12.Navigation/Rule-12-2-2.html', b'1'),
('Rgaa30-12-3-1', '', '12.3.1', 280, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule120301', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/12.Navigation/Rule-12-3-1.html', b'1'),
('Rgaa30-12-3-2', '', '12.3.2', 281, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule120302', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/12.Navigation/Rule-12-3-2.html', b'1'),
('Rgaa30-12-4-1', '', '12.4.1', 282, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule120401', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/12.Navigation/Rule-12-4-1.html', b'1'),
('Rgaa30-12-4-2', '', '12.4.2', 283, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule120402', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/12.Navigation/Rule-12-4-2.html', b'1'),
('Rgaa30-12-4-3', '', '12.4.3', 284, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule120403', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/12.Navigation/Rule-12-4-3.html', b'1'),
('Rgaa30-12-5-1', '', '12.5.1', 285, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule120501', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/12.Navigation/Rule-12-5-1.html', b'1'),
('Rgaa30-12-5-2', '', '12.5.2', 286, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule120502', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/12.Navigation/Rule-12-5-2.html', b'1'),
('Rgaa30-12-5-3', '', '12.5.3', 287, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule120503', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/12.Navigation/Rule-12-5-3.html', b'1'),
('Rgaa30-12-6-1', '', '12.6.1', 288, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule120601', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/12.Navigation/Rule-12-6-1.html', b'1'),
('Rgaa30-12-6-2', '', '12.6.2', 289, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule120602', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/12.Navigation/Rule-12-6-2.html', b'1'),
('Rgaa30-12-6-3', '', '12.6.3', 290, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule120603', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/12.Navigation/Rule-12-6-3.html', b'1'),
('Rgaa30-12-7-1', '', '12.7.1', 291, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule120701', NULL, 2, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/12.Navigation/Rule-12-7-1.html', b'1'),
('Rgaa30-12-8-1', '', '12.8.1', 292, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule120801', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/12.Navigation/Rule-12-8-1.html', b'1'),
('Rgaa30-12-9-1', '', '12.9.1', 293, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule120901', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/12.Navigation/Rule-12-9-1.html', b'1'),
('Rgaa30-12-10-1', '', '12.10.1', 294, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule121001', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/12.Navigation/Rule-12-10-1.html', b'1'),
('Rgaa30-12-10-2', '', '12.10.2', 295, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule121002', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/12.Navigation/Rule-12-10-2.html', b'1'),
('Rgaa30-12-10-3', '', '12.10.3', 296, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule121003', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/12.Navigation/Rule-12-10-3.html', b'1'),
('Rgaa30-12-10-4', '', '12.10.4', 297, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule121004', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/12.Navigation/Rule-12-10-4.html', b'1'),
('Rgaa30-12-11-1', '', '12.11.1', 298, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule121101', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/12.Navigation/Rule-12-11-1.html', b'1'),
('Rgaa30-12-11-2', '', '12.11.2', 299, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule121102', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/12.Navigation/Rule-12-11-2.html', b'1'),
('Rgaa30-12-11-3', '', '12.11.3', 300, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule121103', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/12.Navigation/Rule-12-11-3.html', b'1'),
('Rgaa30-12-11-4', '', '12.11.4', 301, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule121104', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/12.Navigation/Rule-12-11-4.html', b'1'),
('Rgaa30-12-12-1', '', '12.12.1', 302, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule121201', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/12.Navigation/Rule-12-12-1.html', b'1'),
('Rgaa30-12-13-1', '', '12.13.1', 303, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule121301', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/12.Navigation/Rule-12-13-1.html', b'1'),
('Rgaa30-12-13-2', '', '12.13.2', 304, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule121302', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/12.Navigation/Rule-12-13-2.html', b'1'),
('Rgaa30-12-14-1', '', '12.14.1', 305, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule121401', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/12.Navigation/Rule-12-14-1.html', b'1'),
('Rgaa30-13-1-1', '', '13.1.1', 306, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule130101', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/13.Consultation/Rule-13-1-1.html', b'1'),
('Rgaa30-13-1-2', '', '13.1.2', 307, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule130102', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/13.Consultation/Rule-13-1-2.html', b'1'),
('Rgaa30-13-1-3', '', '13.1.3', 308, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule130103', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/13.Consultation/Rule-13-1-3.html', b'1'),
('Rgaa30-13-1-4', '', '13.1.4', 309, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule130104', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/13.Consultation/Rule-13-1-4.html', b'1'),
('Rgaa30-13-1-5', '', '13.1.5', 310, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule130105', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/13.Consultation/Rule-13-1-5.html', b'1'),
('Rgaa30-13-2-1', '', '13.2.1', 311, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule130201', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/13.Consultation/Rule-13-2-1.html', b'1'),
('Rgaa30-13-2-2', '', '13.2.2', 312, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule130202', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/13.Consultation/Rule-13-2-2.html', b'1'),
('Rgaa30-13-2-3', '', '13.2.3', 313, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule130203', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/13.Consultation/Rule-13-2-3.html', b'1'),
('Rgaa30-13-3-1', '', '13.3.1', 314, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule130301', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/13.Consultation/Rule-13-3-1.html', b'1'),
('Rgaa30-13-4-1', '', '13.4.1', 315, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule130401', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/13.Consultation/Rule-13-4-1.html', b'1'),
('Rgaa30-13-5-1', '', '13.5.1', 316, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule130501', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/13.Consultation/Rule-13-5-1.html', b'1'),
('Rgaa30-13-6-1', '', '13.6.1', 317, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule130601', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/13.Consultation/Rule-13-6-1.html', b'1'),
('Rgaa30-13-6-2', '', '13.6.2', 318, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule130602', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/13.Consultation/Rule-13-6-2.html', b'1'),
('Rgaa30-13-6-3', '', '13.6.3', 319, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule130603', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/13.Consultation/Rule-13-6-3.html', b'1'),
('Rgaa30-13-7-1', '', '13.7.1', 320, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule130701', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/13.Consultation/Rule-13-7-1.html', b'1'),
('Rgaa30-13-8-1', '', '13.8.1', 321, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule130801', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/13.Consultation/Rule-13-8-1.html', b'1'),
('Rgaa30-13-9-1', '', '13.9.1', 322, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule130901', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/13.Consultation/Rule-13-9-1.html', b'1'),
('Rgaa30-13-10-1', '', '13.10.1', 322, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule131001', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/13.Consultation/Rule-13-10-1.html', b'1'),
('Rgaa30-13-11-1', '', '13.11.1', 324, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule131101', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/13.Consultation/Rule-13-11-1.html', b'1'),
('Rgaa30-13-12-1', '', '13.12.1', 325, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule131201', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/13.Consultation/Rule-13-12-1.html', b'1'),
('Rgaa30-13-13-1', '', '13.13.1', 326, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule131301', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/13.Consultation/Rule-13-13-1.html', b'1'),
('Rgaa30-13-14-1', '', '13.14.1', 327, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule131401', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/13.Consultation/Rule-13-14-1.html', b'1'),
('Rgaa30-13-15-1', '', '13.15.1', 328, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule131501', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/13.Consultation/Rule-13-15-1.html', b'1'),
('Rgaa30-13-15-2', '', '13.15.2', 329, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule131502', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/13.Consultation/Rule-13-15-2.html', b'1'),
('Rgaa30-13-15-3', '', '13.15.3', 330, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule131503', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/13.Consultation/Rule-13-15-3.html', b'1'),
('Rgaa30-13-16-1', '', '13.16.1', 331, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule131601', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/13.Consultation/Rule-13-16-1.html', b'1'),
('Rgaa30-13-16-2', '', '13.16.2', 332, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule131602', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/13.Consultation/Rule-13-16-2.html', b'1'),
('Rgaa30-13-16-3', '', '13.16.3', 333, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule131603', NULL, 3, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/13.Consultation/Rule-13-16-3.html', b'1'),
('Rgaa30-13-17-1', '', '13.17.1', 334, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule131701', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/13.Consultation/Rule-13-17-1.html', b'1'),
('Rgaa30-13-17-2', '', '13.17.2', 335, '1.0', 'rgaa30', 'org.asqatasun.rules.rgaa30.Rgaa30Rule131702', NULL, 1, 1, 'http://doc.asqatasun.org/en/90_Rules/rgaa3.0/13.Consultation/Rule-13-17-2.html', b'1');

UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-1-1') WHERE `Cd_Test` LIKE 'Rgaa30-1-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-1-2') WHERE `Cd_Test` LIKE 'Rgaa30-1-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-1-3') WHERE `Cd_Test` LIKE 'Rgaa30-1-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-1-4') WHERE `Cd_Test` LIKE 'Rgaa30-1-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-1-5') WHERE `Cd_Test` LIKE 'Rgaa30-1-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-1-6') WHERE `Cd_Test` LIKE 'Rgaa30-1-6-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-1-7') WHERE `Cd_Test` LIKE 'Rgaa30-1-7-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-1-8') WHERE `Cd_Test` LIKE 'Rgaa30-1-8-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-1-9') WHERE `Cd_Test` LIKE 'Rgaa30-1-9-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-1-10') WHERE `Cd_Test` LIKE 'Rgaa30-1-10-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-2-1') WHERE `Cd_Test` LIKE 'Rgaa30-2-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-2-2') WHERE `Cd_Test` LIKE 'Rgaa30-2-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-3-1') WHERE `Cd_Test` LIKE 'Rgaa30-3-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-3-2') WHERE `Cd_Test` LIKE 'Rgaa30-3-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-3-3') WHERE `Cd_Test` LIKE 'Rgaa30-3-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-3-4') WHERE `Cd_Test` LIKE 'Rgaa30-3-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-4-1') WHERE `Cd_Test` LIKE 'Rgaa30-4-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-4-2') WHERE `Cd_Test` LIKE 'Rgaa30-4-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-4-3') WHERE `Cd_Test` LIKE 'Rgaa30-4-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-4-4') WHERE `Cd_Test` LIKE 'Rgaa30-4-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-4-5') WHERE `Cd_Test` LIKE 'Rgaa30-4-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-4-6') WHERE `Cd_Test` LIKE 'Rgaa30-4-6-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-4-7') WHERE `Cd_Test` LIKE 'Rgaa30-4-7-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-4-8') WHERE `Cd_Test` LIKE 'Rgaa30-4-8-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-4-9') WHERE `Cd_Test` LIKE 'Rgaa30-4-9-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-4-10') WHERE `Cd_Test` LIKE 'Rgaa30-4-10-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-4-11') WHERE `Cd_Test` LIKE 'Rgaa30-4-11-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-4-12') WHERE `Cd_Test` LIKE 'Rgaa30-4-12-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-4-13') WHERE `Cd_Test` LIKE 'Rgaa30-4-13-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-4-14') WHERE `Cd_Test` LIKE 'Rgaa30-4-14-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-4-15') WHERE `Cd_Test` LIKE 'Rgaa30-4-15-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-4-16') WHERE `Cd_Test` LIKE 'Rgaa30-4-16-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-4-17') WHERE `Cd_Test` LIKE 'Rgaa30-4-17-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-4-18') WHERE `Cd_Test` LIKE 'Rgaa30-4-18-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-4-19') WHERE `Cd_Test` LIKE 'Rgaa30-4-19-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-4-20') WHERE `Cd_Test` LIKE 'Rgaa30-4-20-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-4-21') WHERE `Cd_Test` LIKE 'Rgaa30-4-21-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-4-22') WHERE `Cd_Test` LIKE 'Rgaa30-4-22-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-5-1') WHERE `Cd_Test` LIKE 'Rgaa30-5-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-5-2') WHERE `Cd_Test` LIKE 'Rgaa30-5-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-5-3') WHERE `Cd_Test` LIKE 'Rgaa30-5-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-5-4') WHERE `Cd_Test` LIKE 'Rgaa30-5-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-5-5') WHERE `Cd_Test` LIKE 'Rgaa30-5-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-5-6') WHERE `Cd_Test` LIKE 'Rgaa30-5-6-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-5-7') WHERE `Cd_Test` LIKE 'Rgaa30-5-7-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-5-8') WHERE `Cd_Test` LIKE 'Rgaa30-5-8-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-6-1') WHERE `Cd_Test` LIKE 'Rgaa30-6-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-6-2') WHERE `Cd_Test` LIKE 'Rgaa30-6-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-6-3') WHERE `Cd_Test` LIKE 'Rgaa30-6-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-6-4') WHERE `Cd_Test` LIKE 'Rgaa30-6-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-6-5') WHERE `Cd_Test` LIKE 'Rgaa30-6-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-7-1') WHERE `Cd_Test` LIKE 'Rgaa30-7-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-7-2') WHERE `Cd_Test` LIKE 'Rgaa30-7-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-7-3') WHERE `Cd_Test` LIKE 'Rgaa30-7-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-7-4') WHERE `Cd_Test` LIKE 'Rgaa30-7-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-7-5') WHERE `Cd_Test` LIKE 'Rgaa30-7-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-8-1') WHERE `Cd_Test` LIKE 'Rgaa30-8-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-8-2') WHERE `Cd_Test` LIKE 'Rgaa30-8-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-8-3') WHERE `Cd_Test` LIKE 'Rgaa30-8-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-8-4') WHERE `Cd_Test` LIKE 'Rgaa30-8-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-8-5') WHERE `Cd_Test` LIKE 'Rgaa30-8-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-8-6') WHERE `Cd_Test` LIKE 'Rgaa30-8-6-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-8-7') WHERE `Cd_Test` LIKE 'Rgaa30-8-7-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-8-8') WHERE `Cd_Test` LIKE 'Rgaa30-8-8-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-8-9') WHERE `Cd_Test` LIKE 'Rgaa30-8-9-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-8-10') WHERE `Cd_Test` LIKE 'Rgaa30-8-10-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-9-1') WHERE `Cd_Test` LIKE 'Rgaa30-9-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-9-2') WHERE `Cd_Test` LIKE 'Rgaa30-9-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-9-3') WHERE `Cd_Test` LIKE 'Rgaa30-9-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-9-4') WHERE `Cd_Test` LIKE 'Rgaa30-9-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-9-5') WHERE `Cd_Test` LIKE 'Rgaa30-9-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-9-6') WHERE `Cd_Test` LIKE 'Rgaa30-9-6-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-10-1') WHERE `Cd_Test` LIKE 'Rgaa30-10-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-10-2') WHERE `Cd_Test` LIKE 'Rgaa30-10-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-10-3') WHERE `Cd_Test` LIKE 'Rgaa30-10-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-10-4') WHERE `Cd_Test` LIKE 'Rgaa30-10-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-10-5') WHERE `Cd_Test` LIKE 'Rgaa30-10-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-10-6') WHERE `Cd_Test` LIKE 'Rgaa30-10-6-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-10-7') WHERE `Cd_Test` LIKE 'Rgaa30-10-7-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-10-8') WHERE `Cd_Test` LIKE 'Rgaa30-10-8-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-10-9') WHERE `Cd_Test` LIKE 'Rgaa30-10-9-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-10-10') WHERE `Cd_Test` LIKE 'Rgaa30-10-10-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-10-11') WHERE `Cd_Test` LIKE 'Rgaa30-10-11-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-10-12') WHERE `Cd_Test` LIKE 'Rgaa30-10-12-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-10-13') WHERE `Cd_Test` LIKE 'Rgaa30-10-13-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-10-14') WHERE `Cd_Test` LIKE 'Rgaa30-10-14-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-10-15') WHERE `Cd_Test` LIKE 'Rgaa30-10-15-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-11-1') WHERE `Cd_Test` LIKE 'Rgaa30-11-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-11-2') WHERE `Cd_Test` LIKE 'Rgaa30-11-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-11-3') WHERE `Cd_Test` LIKE 'Rgaa30-11-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-11-4') WHERE `Cd_Test` LIKE 'Rgaa30-11-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-11-5') WHERE `Cd_Test` LIKE 'Rgaa30-11-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-11-6') WHERE `Cd_Test` LIKE 'Rgaa30-11-6-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-11-7') WHERE `Cd_Test` LIKE 'Rgaa30-11-7-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-11-8') WHERE `Cd_Test` LIKE 'Rgaa30-11-8-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-11-9') WHERE `Cd_Test` LIKE 'Rgaa30-11-9-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-11-10') WHERE `Cd_Test` LIKE 'Rgaa30-11-10-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-11-11') WHERE `Cd_Test` LIKE 'Rgaa30-11-11-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-11-12') WHERE `Cd_Test` LIKE 'Rgaa30-11-12-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-11-13') WHERE `Cd_Test` LIKE 'Rgaa30-11-13-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-11-14') WHERE `Cd_Test` LIKE 'Rgaa30-11-14-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-11-15') WHERE `Cd_Test` LIKE 'Rgaa30-11-15-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-12-1') WHERE `Cd_Test` LIKE 'Rgaa30-12-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-12-2') WHERE `Cd_Test` LIKE 'Rgaa30-12-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-12-3') WHERE `Cd_Test` LIKE 'Rgaa30-12-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-12-4') WHERE `Cd_Test` LIKE 'Rgaa30-12-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-12-5') WHERE `Cd_Test` LIKE 'Rgaa30-12-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-12-6') WHERE `Cd_Test` LIKE 'Rgaa30-12-6-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-12-7') WHERE `Cd_Test` LIKE 'Rgaa30-12-7-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-12-8') WHERE `Cd_Test` LIKE 'Rgaa30-12-8-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-12-9') WHERE `Cd_Test` LIKE 'Rgaa30-12-9-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-12-10') WHERE `Cd_Test` LIKE 'Rgaa30-12-10-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-12-11') WHERE `Cd_Test` LIKE 'Rgaa30-12-11-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-12-12') WHERE `Cd_Test` LIKE 'Rgaa30-12-12-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-12-13') WHERE `Cd_Test` LIKE 'Rgaa30-12-13-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-12-14') WHERE `Cd_Test` LIKE 'Rgaa30-12-14-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-13-1') WHERE `Cd_Test` LIKE 'Rgaa30-13-1-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-13-2') WHERE `Cd_Test` LIKE 'Rgaa30-13-2-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-13-3') WHERE `Cd_Test` LIKE 'Rgaa30-13-3-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-13-4') WHERE `Cd_Test` LIKE 'Rgaa30-13-4-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-13-5') WHERE `Cd_Test` LIKE 'Rgaa30-13-5-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-13-6') WHERE `Cd_Test` LIKE 'Rgaa30-13-6-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-13-7') WHERE `Cd_Test` LIKE 'Rgaa30-13-7-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-13-8') WHERE `Cd_Test` LIKE 'Rgaa30-13-8-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-13-9') WHERE `Cd_Test` LIKE 'Rgaa30-13-9-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-13-10') WHERE `Cd_Test` LIKE 'Rgaa30-13-10-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-13-11') WHERE `Cd_Test` LIKE 'Rgaa30-13-11-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-13-12') WHERE `Cd_Test` LIKE 'Rgaa30-13-12-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-13-13') WHERE `Cd_Test` LIKE 'Rgaa30-13-13-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-13-14') WHERE `Cd_Test` LIKE 'Rgaa30-13-14-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-13-15') WHERE `Cd_Test` LIKE 'Rgaa30-13-15-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-13-16') WHERE `Cd_Test` LIKE 'Rgaa30-13-16-%';
UPDATE `TEST` SET `Id_Criterion` = (SELECT `Id_Criterion` FROM `CRITERION` WHERE `Cd_Criterion` LIKE 'Rgaa30-13-17') WHERE `Cd_Test` LIKE 'Rgaa30-13-17-%';

INSERT IGNORE INTO `PARAMETER` (`Id_Parameter_Element`, `Parameter_Value`, `Is_Default`) VALUES
(5, 'Rgaa30;LEVEL_1', b'0'),
(5, 'Rgaa30;LEVEL_2', b'0'),
(5, 'Rgaa30;LEVEL_3', b'0');

UPDATE TEST SET `No_Process`=b'0' WHERE
Cd_Test='Rgaa30-1-1-1' OR
Cd_Test='Rgaa30-1-1-2' OR
Cd_Test='Rgaa30-1-1-3' OR
Cd_Test='Rgaa30-1-1-4' OR
Cd_Test='Rgaa30-1-2-1' OR
Cd_Test='Rgaa30-1-2-2' OR
Cd_Test='Rgaa30-1-2-3' OR
Cd_Test='Rgaa30-1-2-4' OR
Cd_Test='Rgaa30-1-2-5' OR
Cd_Test='Rgaa30-1-3-1' OR
Cd_Test='Rgaa30-1-3-2' OR
Cd_Test='Rgaa30-1-3-3' OR
Cd_Test='Rgaa30-1-3-4' OR
Cd_Test='Rgaa30-1-3-5' OR
Cd_Test='Rgaa30-1-3-6' OR
Cd_Test='Rgaa30-1-3-7' OR
Cd_Test='Rgaa30-1-3-8' OR
Cd_Test='Rgaa30-1-3-9' OR
Cd_Test='Rgaa30-1-4-1' OR
Cd_Test='Rgaa30-1-4-2' OR
Cd_Test='Rgaa30-1-4-3' OR
Cd_Test='Rgaa30-1-4-4' OR
Cd_Test='Rgaa30-1-4-5' OR
Cd_Test='Rgaa30-1-4-6' OR
Cd_Test='Rgaa30-1-4-7' OR
Cd_Test='Rgaa30-1-4-8' OR
Cd_Test='Rgaa30-1-4-9' OR
Cd_Test='Rgaa30-1-5-1' OR
Cd_Test='Rgaa30-1-5-2' OR
Cd_Test='Rgaa30-1-6-1' OR
Cd_Test='Rgaa30-1-6-2' OR
Cd_Test='Rgaa30-1-6-3' OR
Cd_Test='Rgaa30-1-6-4' OR
Cd_Test='Rgaa30-1-6-5' OR
Cd_Test='Rgaa30-1-6-6' OR
Cd_Test='Rgaa30-1-6-7' OR
Cd_Test='Rgaa30-1-6-8' OR
Cd_Test='Rgaa30-1-7-1' OR
Cd_Test='Rgaa30-1-7-2' OR
Cd_Test='Rgaa30-1-7-3' OR
Cd_Test='Rgaa30-1-7-4' OR
Cd_Test='Rgaa30-1-7-5' OR
Cd_Test='Rgaa30-1-7-6' OR
Cd_Test='Rgaa30-1-7-7' OR
Cd_Test='Rgaa30-1-8-1' OR
Cd_Test='Rgaa30-1-8-2' OR
Cd_Test='Rgaa30-1-8-3' OR
Cd_Test='Rgaa30-1-8-4' OR
Cd_Test='Rgaa30-1-8-5' OR
Cd_Test='Rgaa30-1-8-6' OR
Cd_Test='Rgaa30-1-9-1' OR
Cd_Test='Rgaa30-1-9-2' OR
Cd_Test='Rgaa30-1-9-3' OR
Cd_Test='Rgaa30-1-9-4' OR
Cd_Test='Rgaa30-1-9-5' OR
Cd_Test='Rgaa30-1-9-6' OR
Cd_Test='Rgaa30-2-1-1' OR
Cd_Test='Rgaa30-2-2-1' OR
Cd_Test='Rgaa30-3-3-1' OR
Cd_Test='Rgaa30-3-3-2' OR
Cd_Test='Rgaa30-3-3-3' OR
Cd_Test='Rgaa30-3-3-4' OR
Cd_Test='Rgaa30-3-4-1' OR
Cd_Test='Rgaa30-3-4-2' OR
Cd_Test='Rgaa30-3-4-3' OR
Cd_Test='Rgaa30-3-4-4' OR
Cd_Test='Rgaa30-4-22-1' OR
Cd_Test='Rgaa30-4-22-2' OR
Cd_Test='Rgaa30-5-1-1' OR
Cd_Test='Rgaa30-5-2-1' OR
Cd_Test='Rgaa30-5-3-1' OR
Cd_Test='Rgaa30-5-4-1' OR
Cd_Test='Rgaa30-5-5-1' OR
Cd_Test='Rgaa30-5-6-1' OR
Cd_Test='Rgaa30-5-6-2' OR
Cd_Test='Rgaa30-5-7-1' OR
Cd_Test='Rgaa30-5-7-2' OR
Cd_Test='Rgaa30-5-7-3' OR
Cd_Test='Rgaa30-5-7-4' OR
Cd_Test='Rgaa30-5-8-1' OR
Cd_Test='Rgaa30-6-1-1' OR
Cd_Test='Rgaa30-6-1-2' OR
Cd_Test='Rgaa30-6-1-3' OR
Cd_Test='Rgaa30-6-1-4' OR
Cd_Test='Rgaa30-6-1-5' OR
Cd_Test='Rgaa30-6-2-1' OR
Cd_Test='Rgaa30-6-2-2' OR
Cd_Test='Rgaa30-6-2-3' OR
Cd_Test='Rgaa30-6-2-4' OR
Cd_Test='Rgaa30-6-2-5' OR
Cd_Test='Rgaa30-6-3-1' OR
Cd_Test='Rgaa30-6-3-2' OR
Cd_Test='Rgaa30-6-3-3' OR
Cd_Test='Rgaa30-6-3-4' OR
Cd_Test='Rgaa30-6-3-5' OR
Cd_Test='Rgaa30-6-4-1' OR
Cd_Test='Rgaa30-6-4-2' OR
Cd_Test='Rgaa30-6-4-3' OR
Cd_Test='Rgaa30-6-4-4' OR
Cd_Test='Rgaa30-6-4-5' OR
Cd_Test='Rgaa30-6-5-1' OR
Cd_Test='Rgaa30-7-4-1' OR
Cd_Test='Rgaa30-8-1-1' OR
Cd_Test='Rgaa30-8-1-2' OR
Cd_Test='Rgaa30-8-1-3' OR
Cd_Test='Rgaa30-8-3-1' OR
Cd_Test='Rgaa30-8-4-1' OR
Cd_Test='Rgaa30-8-5-1' OR
Cd_Test='Rgaa30-8-6-1' OR
Cd_Test='Rgaa30-8-7-1' OR
Cd_Test='Rgaa30-8-8-1' OR
Cd_Test='Rgaa30-8-8-2' OR
Cd_Test='Rgaa30-8-9-1' OR
Cd_Test='Rgaa30-9-1-1' OR
Cd_Test='Rgaa30-9-1-2' OR
Cd_Test='Rgaa30-9-1-4' OR
Cd_Test='Rgaa30-10-1-1' OR
Cd_Test='Rgaa30-10-1-2' OR
Cd_Test='Rgaa30-10-4-1' OR
Cd_Test='Rgaa30-10-4-2' OR
Cd_Test='Rgaa30-10-7-1' OR
Cd_Test='Rgaa30-10-8-3' OR
Cd_Test='Rgaa30-10-8-4' OR
Cd_Test='Rgaa30-10-13-1' OR
Cd_Test='Rgaa30-11-1-1' OR
Cd_Test='Rgaa30-11-1-2' OR
Cd_Test='Rgaa30-11-1-3' OR
Cd_Test='Rgaa30-11-2-1' OR
Cd_Test='Rgaa30-11-2-2' OR
Cd_Test='Rgaa30-11-3-1' OR
Cd_Test='Rgaa30-11-3-2' OR
Cd_Test='Rgaa30-11-4-1' OR
Cd_Test='Rgaa30-11-5-1' OR
Cd_Test='Rgaa30-11-6-1' OR
Cd_Test='Rgaa30-11-7-1' OR
Cd_Test='Rgaa30-11-8-1' OR
Cd_Test='Rgaa30-11-8-2' OR
Cd_Test='Rgaa30-11-8-3' OR
Cd_Test='Rgaa30-11-9-1' OR
Cd_Test='Rgaa30-11-9-2' OR
Cd_Test='Rgaa30-11-10-1' OR
Cd_Test='Rgaa30-11-10-4' OR
Cd_Test='Rgaa30-11-10-7' OR
Cd_Test='Rgaa30-11-11-1' OR
Cd_Test='Rgaa30-11-11-2' OR
Cd_Test='Rgaa30-11-12-1' OR
Cd_Test='Rgaa30-11-12-2' OR
Cd_Test='Rgaa30-11-13-1' OR
Cd_Test='Rgaa30-11-13-2' OR
Cd_Test='Rgaa30-11-14-1' OR
Cd_Test='Rgaa30-11-14-4' OR
Cd_Test='Rgaa30-11-15-1' OR
Cd_Test='Rgaa30-12-1-1' OR
Cd_Test='Rgaa30-12-2-1' OR
Cd_Test='Rgaa30-12-2-2' OR
Cd_Test='Rgaa30-12-3-1' OR
Cd_Test='Rgaa30-12-3-2' OR
Cd_Test='Rgaa30-12-5-1' OR
Cd_Test='Rgaa30-12-5-2' OR
Cd_Test='Rgaa30-12-5-3' OR
Cd_Test='Rgaa30-12-6-1' OR
Cd_Test='Rgaa30-12-6-2' OR
Cd_Test='Rgaa30-12-6-3' OR
Cd_Test='Rgaa30-12-7-1' OR
Cd_Test='Rgaa30-12-11-4' OR
Cd_Test='Rgaa30-13-1-2' OR
Cd_Test='Rgaa30-13-2-1' OR
Cd_Test='Rgaa30-13-2-2' OR
Cd_Test='Rgaa30-13-2-3' OR
Cd_Test='Rgaa30-13-6-1' OR
Cd_Test='Rgaa30-13-6-2' OR
Cd_Test='Rgaa30-13-6-3' OR
Cd_Test='Rgaa30-13-7-1';