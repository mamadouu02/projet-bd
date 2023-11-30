/* Table refuge */
INSERT INTO refuge VALUES ('refuge0@gmail.com', 'Refuge Montagnard', '0701010101', 'Alpes', DATE '2023-05-01', DATE '2023-10-31', 30, 20, 'Refuge situé en plein cœur des Alpes.', 'espèce', 30.00);
INSERT INTO refuge VALUES ('refuge1@gmail.com', 'Refuge Rocailleux', '0702010101', 'Pyrenées', DATE '2023-06-01', DATE '2023-09-30', 25, 15, 'Refuge avec une vue panoramique sur les Pyrénées.', 'carte-bleue', 35.00);
INSERT INTO refuge VALUES ('refuge2@gmail.com', 'Refuge Forestier', '0703010101', 'Forêt Noire', DATE '2023-04-15', DATE '2023-11-15', 40, 25, 'Refuge niché au cœur de la Forêt Noire.', 'chèque', 25.00);
INSERT INTO refuge VALUES ('refuge3@gmail.com', 'Refuge des Cimes', '0704010101', 'Jura', DATE '2023-07-01', DATE '2023-09-15', 35, 18, 'Refuge offrant une vue imprenable sur le Jura.', 'espèce', 28.00);
INSERT INTO refuge VALUES ('refuge4@gmail.com', 'Refuge Glaciaire', '0705010101', 'Alpes', DATE '2023-12-01', DATE '2024-03-31', 20, 15, 'Refuge idéal pour les passionnés de montagne en hiver.', 'carte-bleue', 40.00);


/* Table repas */
INSERT INTO repas VALUES ('refuge0@gmail.com', 'déjeuner', 10.50);
INSERT INTO repas VALUES ('refuge1@gmail.com', 'dîner', 15.75);
INSERT INTO repas VALUES ('refuge2@gmail.com', 'souper', 12.00);
INSERT INTO repas VALUES ('refuge1@gmail.com', 'casse-croûte', 5.25);
INSERT INTO repas VALUES ('refuge2@gmail.com', 'déjeuner', 11.00);


/* Table formation */
INSERT INTO formation VALUES (2023, 1, 'Initiation à la Randonnée', DATE '2023-05-15', 3, 20, 'Découverte des bases de la randonnée en montagne.', 150.00);
INSERT INTO formation VALUES (2023, 2, 'Escalade pour Débutants', DATE '2023-06-01', 5, 15, 'Introduction à l''escalade en sécurité.', 200.00);
INSERT INTO formation VALUES (2023, 3, 'Alpinisme Avancé', DATE '2023-07-10', 7, 5, 'Formation approfondie en alpinisme.', 300.00);
INSERT INTO formation VALUES (2023, 4, 'Spéléologie Exploratoire', DATE '2023-08-20', 4, 12, 'Exploration des grottes et cavernes.', 180.00);
INSERT INTO formation VALUES (2023, 5, 'Ski de Randonnée', DATE '2023-12-05', 2, 18, 'Pratique du ski en montagne avec des montées en peau de phoque.', 120.00);
INSERT INTO formation VALUES (2023, 6, 'Cascade de Glace', DATE '2023-12-20', 3, 15, 'Apprentissage de l''escalade de glace.', 250.00);
INSERT INTO formation VALUES (2024, 1, 'Randonnée et Survie en Montagne', DATE '2024-06-01', 5, 25, 'Techniques de survie en milieu montagnard.', 180.00);
INSERT INTO formation VALUES (2024, 2, 'Escalade de Montagne', DATE '2024-07-15', 6, 20, 'Escalade en haute montagne.', 280.00);
INSERT INTO formation VALUES (2024, 3, 'Ski de Randonnée Avancé', DATE '2024-07-15', 4, 15, 'Pratique avancée du ski de randonnée en haute montagne.', 200.00);
INSERT INTO formation VALUES (2024, 4, 'Formation Combinée Spéléologie et Escalade', DATE '2024-08-10', 7, 15, 'Combinaison de l''escalade et de la spéléologie.', 320.00);
INSERT INTO formation VALUES (2024, 5, 'Ski de Randonnée Avancé', DATE '2024-12-01', 4, 15, 'Pratique avancée du ski de randonnée en haute montagne.', 200.00);


/* Table activite */
INSERT INTO activite VALUES ('randonnée');
INSERT INTO activite VALUES ('escalade');
INSERT INTO activite VALUES ('alpinisme');
INSERT INTO activite VALUES ('spéléologie');
INSERT INTO activite VALUES ('ski de rando');
INSERT INTO activite VALUES ('cascade de glace');


/* Table activites_formation */
INSERT INTO activites_formation VALUES (2023, 1, 'randonnée');
INSERT INTO activites_formation VALUES (2023, 2, 'escalade');
INSERT INTO activites_formation VALUES (2023, 3, 'alpinisme');
INSERT INTO activites_formation VALUES (2023, 4, 'spéléologie');
INSERT INTO activites_formation VALUES (2023, 5, 'ski de rando');
INSERT INTO activites_formation VALUES (2023, 6, 'cascade de glace');
INSERT INTO activites_formation VALUES (2024, 1, 'randonnée');
INSERT INTO activites_formation VALUES (2024, 2, 'escalade');
INSERT INTO activites_formation VALUES (2024, 2, 'alpinisme');
INSERT INTO activites_formation VALUES (2024, 3, 'ski de rando');
INSERT INTO activites_formation VALUES (2024, 4, 'spéléologie');
INSERT INTO activites_formation VALUES (2024, 4, 'escalade');
INSERT INTO activites_formation VALUES (2024, 5, 'ski de rando');


/* Table categorie */
INSERT INTO categorie VALUES ('EPI');
INSERT INTO categorie VALUES ('Mousqueton');
INSERT INTO categorie VALUES ('Escalade');
INSERT INTO categorie VALUES ('Randonnée');
INSERT INTO categorie VALUES ('Camping');
INSERT INTO categorie VALUES ('Alpinisme');
INSERT INTO categorie VALUES ('Chaussures');
INSERT INTO categorie VALUES ('Accessoires');
INSERT INTO categorie VALUES ('Vêtements');


/* Table sous_categorie */
INSERT INTO sous_categorie VALUES ('Mousqueton', 'EPI');
INSERT INTO sous_categorie VALUES ('Mousqueton symétrique', 'Mousqueton');
INSERT INTO sous_categorie VALUES ('Mousqueton HMS', 'Mousqueton');

INSERT INTO sous_categorie VALUES ('Casque', 'Escalade');
INSERT INTO sous_categorie VALUES ('Harnais', 'Escalade');

INSERT INTO sous_categorie VALUES ('Sac à dos', 'Randonnée');

INSERT INTO sous_categorie VALUES ('Tente', 'Camping');

INSERT INTO sous_categorie VALUES ('Piolets', 'Alpinisme');
INSERT INTO sous_categorie VALUES ('Crampons', 'Alpinisme');

INSERT INTO sous_categorie VALUES ('Chaussures d''escalade', 'Chaussures');
INSERT INTO sous_categorie VALUES ('Chaussures de randonnée', 'Chaussures');
INSERT INTO sous_categorie VALUES ('Chaussures', 'Vêtements');


/* Table lot */
INSERT INTO lot VALUES ('AventurePro', 'MontagneX', 2020, 'Mousqueton symétrique', 5, 50, 'escalade', 'Mousqueton symétrique de haute qualité', NULL);
INSERT INTO lot VALUES ('PleinAir', 'MaîtreDesRochers', 2021, 'Casque', 3, 30, 'escalade', 'Casque d''escalade durable avec ventilation', NULL);
INSERT INTO lot VALUES ('TechniqueEscalade', 'ProHarnais', 2019, 'Harnais', 2, 25, 'escalade', 'Harnais d''escalade professionnel pour la sécurité', NULL);
INSERT INTO lot VALUES ('RandoSac', 'SacExplorateur', 2020, 'Sac à dos', 10, 40, 'randonnée', 'Sac à dos spacieux pour la randonnée et le camping', NULL);
INSERT INTO lot VALUES ('NuitsAuCamp', 'TrailBlazer', 2021, 'Tente', 1, 60, 'camping', 'Tente légère et facile à installer pour le camping', NULL);
INSERT INTO lot VALUES ('TechAlpine', 'MaîtreDesGlaces', 2018, 'Piolets', 2, 70, 'alpinisme', 'Outils d''escalade sur glace pour l''alpinisme avancé', NULL);
INSERT INTO lot VALUES ('ÉliteMontagne', 'ProCrampons', 2019, 'Crampons', 2, 80, 'alpinisme', 'Crampons professionnels pour des conditions glacées', NULL);
INSERT INTO lot VALUES ('EscaladePro', 'ClimbChaussuresX', 2022, 'Chaussures d''escalade', 3, 90, 'escalade', 'Chaussures d''escalade haute performance pour la précision', NULL);
INSERT INTO lot VALUES ('RandoMarcheur', 'RandoBottes', 2023, 'Chaussures de randonnée', 4, 55, 'randonnée', 'Bottes de randonnée durables pour tous les terrains', NULL);
INSERT INTO lot VALUES ('PasExtreme', 'ChaussuresSommet', 2022, 'Chaussures', 5, 75, 'randonnée', 'Chaussures extérieures polyvalentes pour diverses activités', NULL);
INSERT INTO lot VALUES ('EscaladeGear', 'AscensionX', 2020, 'Mousqueton', 6, 45, 'escalade', 'Mousqueton d''escalade léger et robuste', NULL);
INSERT INTO lot VALUES ('TechSommet', 'HMSPro', 2021, 'Mousqueton HMS', 4, 65, 'escalade', 'Mousqueton à vis à haute mobilité pour la sécurité', NULL);
INSERT INTO lot VALUES ('SécuritéEscalade', 'CasqueSécurité', 2019, 'Casque', 3, 30, 'escalade', 'Casque de sécurité avec sangles réglables', NULL);
INSERT INTO lot VALUES ('MaîtreHarnais', 'ProHarnais', 2020, 'Harnais', 2, 40, 'escalade', 'Harnais d''escalade de qualité professionnelle pour le confort', NULL);
INSERT INTO lot VALUES ('ExplorateurSentier', 'TenteCamp', 2021, 'Tente', 1, 55, 'camping', 'Tente de camping compacte pour les aventures en plein air', NULL);
INSERT INTO lot VALUES ('EscaladeGlaceTech', 'PionnierGlace', 2018, 'Piolets', 2, 70, 'alpinisme', 'Piolets d''escalade sur glace pour des ascensions difficiles', NULL);
INSERT INTO lot VALUES ('TechNeige', 'CramponsNeige', 2019, 'Crampons', 2, 80, 'alpinisme', 'Crampons spécifiques pour la neige pour l''alpinisme', NULL);
INSERT INTO lot VALUES ('ConfortEscalade', 'ConfortClimb', 2022, 'Chaussures d''escalade', 3, 90, 'escalade', 'Chaussures d''escalade confortables pour des séances prolongées', NULL);
INSERT INTO lot VALUES ('MarcheurSentier', 'RandoBottes', 2023, 'Chaussures de randonnée', 4, 55, 'randonnée', 'Bottes de randonnée durables pour des treks étendus', NULL);
INSERT INTO lot VALUES ('GearPeak', 'ChaussuresSommet', 2022, 'Chaussures', 5, 75, 'randonnée', 'Chaussures polyvalentes adaptées à diverses activités de plein air', NULL);


/* Table activites_lot */
INSERT INTO activites_lot VALUES ('AventurePro', 'MontagneX', 2020, 'escalade');
INSERT INTO activites_lot VALUES ('PleinAir', 'MaîtreDesRochers', 2021, 'escalade');
INSERT INTO activites_lot VALUES ('TechniqueEscalade', 'ProHarnais', 2019, 'escalade');
INSERT INTO activites_lot VALUES ('RandoSac', 'SacExplorateur', 2020, 'randonnée');
INSERT INTO activites_lot VALUES ('NuitsAuCamp', 'TrailBlazer', 2021, 'randonnée');
INSERT INTO activites_lot VALUES ('TechAlpine', 'MaîtreDesGlaces', 2018, 'alpinisme');
INSERT INTO activites_lot VALUES ('ÉliteMontagne', 'ProCrampons', 2019, 'alpinisme');
INSERT INTO activites_lot VALUES ('EscaladePro', 'ClimbChaussuresX', 2022, 'escalade');
INSERT INTO activites_lot VALUES ('RandoMarcheur', 'RandoBottes', 2023, 'randonnée');
INSERT INTO activites_lot VALUES ('PasExtreme', 'ChaussuresSommet', 2022, 'randonnée');
INSERT INTO activites_lot VALUES ('EscaladeGear', 'AscensionX', 2020, 'escalade');
INSERT INTO activites_lot VALUES ('TechSommet', 'HMSPro', 2021, 'escalade');
INSERT INTO activites_lot VALUES ('SécuritéEscalade', 'CasqueSécurité', 2019, 'escalade');
INSERT INTO activites_lot VALUES ('MaîtreHarnais', 'ProHarnais', 2020, 'escalade');
INSERT INTO activites_lot VALUES ('ExplorateurSentier', 'TenteCamp', 2021, 'randonnée');
INSERT INTO activites_lot VALUES ('EscaladeGlaceTech', 'PionnierGlace', 2018, 'alpinisme');
INSERT INTO activites_lot VALUES ('TechNeige', 'CramponsNeige', 2019, 'alpinisme');
INSERT INTO activites_lot VALUES ('ConfortEscalade', 'ConfortClimb', 2022, 'escalade');
INSERT INTO activites_lot VALUES ('MarcheurSentier', 'RandoBottes', 2023, 'randonnée');
INSERT INTO activites_lot VALUES ('GearPeak', 'ChaussuresSommet', 2022, 'randonnée');


/* Table membre */
INSERT INTO membre VALUES ('mobutu@psg.com', 'speedybaps', 'Bappon', 'Maurice', 'Paris');
INSERT INTO membre VALUES ('bakary@bms.com', 'joelpostbad', 'Swag', 'Bakary', 'France');
INSERT INTO membre VALUES ('fitness@max.com', 'malveillancemax', 'Fitness', 'Marvel', 'France');
INSERT INTO membre VALUES ('billy@hotmail.com', 'microsoft', 'Gates', 'Bilal', 'Washington');
INSERT INTO membre VALUES ('jordan23@gmail.com', 'airjordan', 'Jordan', 'Michael', 'Chicago');
INSERT INTO membre VALUES ('lebron@mvp.com', 'kingjames', 'James', 'LeBron', 'Los Angeles');
INSERT INTO membre VALUES ('cr7@goat.com', '7', 'Penaldo', 'Cristiano', 'Portugal');
INSERT INTO membre VALUES ('serenawilliams@yahoo.com', 'tennischamp', 'Williams', 'Serena', 'Florida');
INSERT INTO membre VALUES ('federer@legend.com', 'forehand123', 'Federer', 'Roger', 'Switzerland');
INSERT INTO membre VALUES ('messi@barca.com', 'barcapass', 'Pessi', 'Lionel', 'Argentina');
INSERT INTO membre VALUES ('nadal@clay.com', 'claycourt', 'Nadal', 'Rafael', 'Spain');
INSERT INTO membre VALUES ('kobebryant@mamba.com', 'blackmamba8', 'Bryant', 'Kobe', 'Los Angeles');
INSERT INTO membre VALUES ('sachin@cricket.com', 'masterblaster', 'Tendulkar', 'Sachin', 'India');
INSERT INTO membre VALUES ('tombrady@goat.com', 'superbowl6', 'Brady', 'Tom', 'Tampa Bay');
INSERT INTO membre VALUES ('venus@tennis.com', 'backhandqueen', 'Williams', 'Venus', 'California');
INSERT INTO membre VALUES ('ronaldo@brazil.com', 'r9phenomeno', 'Ronaldo', 'Ronaldo', 'Brazil');
INSERT INTO membre VALUES ('maradona@legend.com', 'handofgod', 'Maradona', 'Diego', 'Argentina');
INSERT INTO membre VALUES ('ali@boxing.com', 'thegreatest', 'Ali', 'Muhammad', 'Kentucky');
INSERT INTO membre VALUES ('schumacher@f1.com', 'f1champ', 'Schumacher', 'Michael', 'Germany');
INSERT INTO membre VALUES ('nike@justdoit.com', 'swoosh123', 'Knight', 'Phil', 'Oregon');
INSERT INTO membre VALUES ('ruth@yankees.com', 'bambino', 'Ruth', 'Babe', 'New York');
INSERT INTO membre VALUES ('flojo@track.com', 'olympicgold', 'Joyner-Kersee', 'Florence', 'California');
INSERT INTO membre VALUES ('bolt@fast.com', 'lightning', 'Bolt', 'Usain', 'Jamaica');
INSERT INTO membre VALUES ('pele@brazil.com', 'soccerking', 'Pelé', 'Edson', 'Brazil');
INSERT INTO membre VALUES ('magic@lakers.com', 'showtime', 'Johnson', 'Magic', 'Los Angeles');


/* Tables utilisateur et adherent */
INSERT INTO utilisateur (mail_user) VALUES ('mobutu@psg.com');
INSERT INTO adherent (id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur (mail_user) VALUES ('bakary@bms.com');
INSERT INTO adherent (id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur (mail_user) VALUES ('fitness@max.com');
INSERT INTO adherent (id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur (mail_user) VALUES ('billy@hotmail.com');
INSERT INTO adherent (id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur (mail_user) VALUES ('jordan23@gmail.com');
INSERT INTO adherent (id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur (mail_user) VALUES ('lebron@mvp.com');
INSERT INTO adherent (id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur (mail_user) VALUES ('cr7@goat.com');
INSERT INTO adherent (id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur (mail_user) VALUES ('serenawilliams@yahoo.com');

INSERT INTO utilisateur (mail_user) VALUES ('federer@legend.com');
INSERT INTO adherent (id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur (mail_user) VALUES ('messi@barca.com');
INSERT INTO adherent (id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur (mail_user) VALUES ('nadal@clay.com');
INSERT INTO adherent (id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur (mail_user) VALUES ('kobebryant@mamba.com');
INSERT INTO adherent (id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur (mail_user) VALUES ('sachin@cricket.com');

INSERT INTO utilisateur (mail_user) VALUES ('tombrady@goat.com');

INSERT INTO utilisateur (mail_user) VALUES ('venus@tennis.com');

INSERT INTO utilisateur (mail_user) VALUES ('ronaldo@brazil.com');
INSERT INTO adherent (id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur (mail_user) VALUES ('maradona@legend.com');
INSERT INTO adherent (id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur (mail_user) VALUES ('ali@boxing.com');
INSERT INTO adherent (id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur (mail_user) VALUES ('schumacher@f1.com');

INSERT INTO utilisateur (mail_user) VALUES ('nike@justdoit.com');

INSERT INTO utilisateur (mail_user) VALUES ('ruth@yankees.com');

INSERT INTO utilisateur (mail_user) VALUES ('flojo@track.com');

INSERT INTO utilisateur (mail_user) VALUES ('bolt@fast.com');
INSERT INTO adherent (id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur (mail_user) VALUES ('pele@brazil.com');
INSERT INTO adherent (id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur (mail_user) VALUES ('magic@lakers.com');


/* Table reservation_refuge */
INSERT INTO reservation_refuge VALUES (1, 'refuge0@gmail.com', TIMESTAMP '2023-12-01 18:00:00', 2);
INSERT INTO reservation_refuge VALUES (2, 'refuge1@gmail.com', TIMESTAMP '2023-12-05 20:30:00', 3);
INSERT INTO reservation_refuge VALUES (3, 'refuge2@gmail.com', TIMESTAMP '2023-12-10 16:45:00', 1);
INSERT INTO reservation_refuge VALUES (4, 'refuge1@gmail.com', TIMESTAMP '2023-12-02 21:15:00', 2);
INSERT INTO reservation_refuge VALUES (5, 'refuge2@gmail.com', TIMESTAMP '2023-12-08 19:00:00', 4);
INSERT INTO reservation_refuge VALUES (6, 'refuge0@gmail.com', TIMESTAMP '2023-12-15 19:30:00', 1);
INSERT INTO reservation_refuge VALUES (7, 'refuge1@gmail.com', TIMESTAMP '2023-12-20 22:00:00', 2);
INSERT INTO reservation_refuge VALUES (7, 'refuge3@gmail.com', TIMESTAMP '2023-08-01 22:00:00', 2);
INSERT INTO reservation_refuge VALUES (8, 'refuge2@gmail.com', TIMESTAMP '2023-12-25 18:15:00', 3);
INSERT INTO reservation_refuge VALUES (9, 'refuge1@gmail.com', TIMESTAMP '2023-12-18 20:45:00', 1);
INSERT INTO reservation_refuge VALUES (10, 'refuge2@gmail.com', TIMESTAMP '2023-12-23 21:30:00', 2);
INSERT INTO reservation_refuge VALUES (11, 'refuge1@gmail.com', TIMESTAMP '2023-12-30 19:45:00', 3);
INSERT INTO reservation_refuge VALUES (12, 'refuge2@gmail.com', TIMESTAMP '2023-12-17 20:00:00', 1);
INSERT INTO reservation_refuge VALUES (13, 'refuge0@gmail.com', TIMESTAMP '2023-12-28 22:15:00', 2);
INSERT INTO reservation_refuge VALUES (14, 'refuge1@gmail.com', TIMESTAMP '2023-12-13 18:30:00', 3);
INSERT INTO reservation_refuge VALUES (15, 'refuge2@gmail.com', TIMESTAMP '2023-12-22 19:00:00', 1);


/* Table quantite_repas */
INSERT INTO quantite_repas VALUES (1, 'refuge0@gmail.com', 'déjeuner', 2); 
INSERT INTO quantite_repas VALUES (2, 'refuge1@gmail.com', 'dîner', 1);
INSERT INTO quantite_repas VALUES (3, 'refuge2@gmail.com', 'souper', 3);
INSERT INTO quantite_repas VALUES (4, 'refuge1@gmail.com', 'casse-croûte', 4);
INSERT INTO quantite_repas VALUES (5, 'refuge2@gmail.com', 'déjeuner', 1);
INSERT INTO quantite_repas VALUES (2, 'refuge1@gmail.com', 'casse-croûte', 2);
INSERT INTO quantite_repas VALUES (3, 'refuge2@gmail.com', 'déjeuner', 1);
INSERT INTO quantite_repas VALUES (6, 'refuge0@gmail.com', 'déjeuner', 3);
INSERT INTO quantite_repas VALUES (9, 'refuge1@gmail.com', 'dîner', 2);
INSERT INTO quantite_repas VALUES (10, 'refuge2@gmail.com', 'souper', 1);
INSERT INTO quantite_repas VALUES (11, 'refuge1@gmail.com', 'casse-croûte', 2);
INSERT INTO quantite_repas VALUES (12, 'refuge2@gmail.com', 'souper', 4);
INSERT INTO quantite_repas VALUES (13, 'refuge0@gmail.com', 'déjeuner', 1);
INSERT INTO quantite_repas VALUES (14, 'refuge1@gmail.com', 'casse-croûte', 3);
INSERT INTO quantite_repas VALUES (15, 'refuge2@gmail.com', 'déjeuner', 2);


/* Table reservation_formation */
INSERT INTO reservation_formation VALUES (1, 2023, 1, 0);
INSERT INTO reservation_formation VALUES (2, 2023, 1, 0);
INSERT INTO reservation_formation VALUES (3, 2023, 1, 0);
INSERT INTO reservation_formation VALUES (4, 2023, 1, 0);

INSERT INTO reservation_formation VALUES (5, 2023, 2, 0);
INSERT INTO reservation_formation VALUES (6, 2023, 2, 0);
INSERT INTO reservation_formation VALUES (7, 2023, 2, 0);
INSERT INTO reservation_formation VALUES (8, 2023, 2, 0);

INSERT INTO reservation_formation VALUES (9, 2023, 3, 0);
INSERT INTO reservation_formation VALUES (10, 2023, 3, 0);
INSERT INTO reservation_formation VALUES (11, 2023, 3, 0);
INSERT INTO reservation_formation VALUES (12, 2023, 3, 0);

INSERT INTO reservation_formation VALUES (13, 2023, 4, 0);
INSERT INTO reservation_formation VALUES (14, 2023, 4, 0);
INSERT INTO reservation_formation VALUES (15, 2023, 4, 0);
INSERT INTO reservation_formation VALUES (16, 2023, 4, 0);

INSERT INTO reservation_formation VALUES (2, 2023, 5, 0);
INSERT INTO reservation_formation VALUES (6, 2023, 5, 0);
INSERT INTO reservation_formation VALUES (10, 2023, 5, 0);
INSERT INTO reservation_formation VALUES (14, 2023, 5, 0);

INSERT INTO reservation_formation VALUES (4, 2023, 6, 0);
INSERT INTO reservation_formation VALUES (8, 2023, 6, 0);
INSERT INTO reservation_formation VALUES (12, 2023, 6, 0);
INSERT INTO reservation_formation VALUES (16, 2023, 6, 0);

INSERT INTO reservation_formation VALUES (7, 2024, 4, 0);

/* Table location_materiel */
INSERT INTO location_materiel (id_adh, date_emprunt, date_retour) VALUES (1, DATE '2023-05-01', DATE '2023-05-08');
INSERT INTO location_materiel (id_adh, date_emprunt, date_retour) VALUES (2, DATE '2023-06-10', DATE '2023-06-17');
INSERT INTO location_materiel (id_adh, date_emprunt, date_retour) VALUES (3, DATE '2023-07-15', DATE '2023-07-29');
INSERT INTO location_materiel (id_adh, date_emprunt, date_retour) VALUES (4, DATE '2023-08-05', DATE '2023-08-12');
INSERT INTO location_materiel (id_adh, date_emprunt, date_retour) VALUES (5, DATE '2023-09-01', DATE '2023-09-14');
INSERT INTO location_materiel (id_adh, date_emprunt, date_retour) VALUES (6, DATE '2023-10-10', DATE '2023-10-24');
INSERT INTO location_materiel (id_adh, date_emprunt, date_retour) VALUES (7, DATE '2023-11-15', DATE '2023-11-22');
INSERT INTO location_materiel (id_adh, date_emprunt, date_retour) VALUES (8, DATE '2023-12-01', DATE '2023-12-08');


/* Table quantite_materiel */
INSERT INTO quantite_materiel (id_res_materiel, marque, modele, annee_achat, nb_pieces_res) VALUES (1, 'AventurePro', 'MontagneX', 2020, 1);
INSERT INTO quantite_materiel (id_res_materiel, marque, modele, annee_achat, nb_pieces_res) VALUES (1, 'PleinAir', 'MaîtreDesRochers', 2021, 2);
INSERT INTO quantite_materiel (id_res_materiel, marque, modele, annee_achat, nb_pieces_res) VALUES (2, 'TechniqueEscalade', 'ProHarnais', 2019, 1);
INSERT INTO quantite_materiel VALUES (2, 'RandoSac', 'SacExplorateur', 2020, 3, 1);
INSERT INTO quantite_materiel (id_res_materiel, marque, modele, annee_achat, nb_pieces_res) VALUES (3, 'NuitsAuCamp', 'TrailBlazer', 2021, 1);
INSERT INTO quantite_materiel (id_res_materiel, marque, modele, annee_achat, nb_pieces_res) VALUES (3, 'TechAlpine', 'MaîtreDesGlaces', 2018, 2);
INSERT INTO quantite_materiel (id_res_materiel, marque, modele, annee_achat, nb_pieces_res) VALUES (3, 'ÉliteMontagne', 'ProCrampons', 2019, 1);
INSERT INTO quantite_materiel (id_res_materiel, marque, modele, annee_achat, nb_pieces_res) VALUES (4, 'EscaladePro', 'ClimbChaussuresX', 2022, 2);
INSERT INTO quantite_materiel VALUES (5, 'RandoMarcheur', 'RandoBottes', 2023, 4, 2);
INSERT INTO quantite_materiel VALUES (6, 'PasExtreme', 'ChaussuresSommet', 2022, 3, 1);
INSERT INTO quantite_materiel (id_res_materiel, marque, modele, annee_achat, nb_pieces_res) VALUES (7, 'EscaladeGear', 'AscensionX', 2020, 1);
INSERT INTO quantite_materiel (id_res_materiel, marque, modele, annee_achat, nb_pieces_res) VALUES (8, 'TechSommet', 'HMSPro', 2021, 2);

COMMIT;