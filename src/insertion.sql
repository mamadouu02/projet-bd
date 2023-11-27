/* Table refuge */
INSERT INTO refuge VALUES ('Refuge0@gmail.com', 'Refuge0', '0701010101', 'montagne', DATE '2023-12-12', DATE '2024-05-12', 20, 20, NULL, 'carte-bleue', 35);
INSERT INTO refuge VALUES ('Refuge1@gmail.com', 'Refuge1', '0702010101', 'montagne', DATE '2023-12-15', DATE '2024-05-15', 35, 35, NULL, 'carte-bleue', 45);
INSERT INTO refuge VALUES ('Refuge2@gmail.com', 'Refuge2', '0703010101', 'mer', DATE '2023-12-15', DATE '2024-05-15', 40, 40, NULL, 'carte-bleue', 50);
INSERT INTO refuge VALUES ('Refuge3@gmail.com', 'Refuge3', '0704010101', 'montagne', DATE '2023-12-15', DATE '2024-05-15', 40, 40, NULL, 'carte-bleue', 55);
INSERT INTO refuge VALUES ('Refuge4@gmail.com', 'Refuge4', '0705010101', 'montagne', DATE '2023-12-15', DATE '2024-05-15', 50, 50, NULL, 'carte-bleue', 60);


/* Table repas */


/* Table formation */
INSERT INTO formation VALUES (2023, 1, 'Initiation à la Randonnée', '2023-05-15', 3, 20, 'Découverte des bases de la randonnée en montagne.', 150.00);
INSERT INTO formation VALUES (2023, 2, 'Escalade pour Débutants', '2023-06-01', 5, 15, 'Introduction à l''escalade en sécurité.', 200.00);
INSERT INTO formation VALUES (2023, 3, 'Alpinisme Avancé', '2023-07-10', 7, 5, 'Formation approfondie en alpinisme.', 300.00);
INSERT INTO formation VALUES (2023, 4, 'Spéléologie Exploratoire', '2023-08-20', 4, 12, 'Exploration des grottes et cavernes.', 180.00);
INSERT INTO formation VALUES (2023, 5, 'Ski de Randonnée', '2023-12-05', 2, 18, 'Pratique du ski en montagne avec des montées en peau de phoque.', 120.00);
INSERT INTO formation VALUES (2023, 6, 'Cascade de Glace', '2023-12-20', 3, 15, 'Apprentissage de l''escalade de glace.', 250.00);
INSERT INTO formation VALUES (2024, 1, 'Randonnée et Survie en Montagne', '2024-06-01', 5, 25, 'Techniques de survie en milieu montagnard.', 180.00);
INSERT INTO formation VALUES (2024, 2, 'Escalade de Montagne', '2024-07-15', 6, 20, 'Escalade en haute montagne.', 280.00);
INSERT INTO formation VALUES (2024, 3, 'Formation Combinée Spéléologie et Escalade', '2024-08-10', 7, 15, 'Combinaison de l''escalade et de la spéléologie.', 320.00);
INSERT INTO formation VALUES (2024, 4, 'Ski de Randonnée Avancé', '2024-12-01', 4, 15, 'Pratique avancée du ski de randonnée en haute montagne.', 200.00);

/* Table activite */
INSERT INTO activite (activite) VALUES
    ('randonnée'),
    ('escalade'),
    ('alpinisme'),
    ('spéléologie'),
    ('ski de rando'),
    ('cascade de glace');


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
INSERT INTO activites_formation VALUES (2024, 3, 'spéléologie');
INSERT INTO activites_formation VALUES (2024, 3, 'escalade');
INSERT INTO activites_formation VALUES (2024, 4, 'randonnée');

/* Table categorie */
INSERT INTO categorie (categorie) VALUES ('EPI');
INSERT INTO categorie (categorie) VALUES ('Escalade');
INSERT INTO categorie (categorie) VALUES ('Randonnée');
INSERT INTO categorie (categorie) VALUES ('Camping');
INSERT INTO categorie (categorie) VALUES ('Alpinisme');
INSERT INTO categorie (categorie) VALUES ('Accessoires');
INSERT INTO categorie (categorie) VALUES ('Vêtements');


/* Table sous_categorie */
INSERT INTO sous_categorie (sous_categorie, categorie) VALUES ('Mousqueton', 'EPI');
INSERT INTO sous_categorie (sous_categorie, categorie) VALUES ('Mousqueton symétrique', 'Mousqueton');
INSERT INTO sous_categorie (sous_categorie, categorie) VALUES ('Mousqueton HMS', 'Mousqueton');

INSERT INTO sous_categorie (sous_categorie, categorie) VALUES ('Casque', 'Escalade');
INSERT INTO sous_categorie (sous_categorie, categorie) VALUES ('Harnais', 'Escalade');

INSERT INTO sous_categorie (sous_categorie, categorie) VALUES ('Sac à dos', 'Randonnée');

INSERT INTO sous_categorie (sous_categorie, categorie) VALUES ('Tente', 'Camping');

INSERT INTO sous_categorie (sous_categorie, categorie) VALUES ('Piolets', 'Alpinisme');
INSERT INTO sous_categorie (sous_categorie, categorie) VALUES ('Crampons', 'Alpinisme');

INSERT INTO sous_categorie (sous_categorie, categorie) VALUES ('Chaussures d''escalade', 'Chaussures');
INSERT INTO sous_categorie (sous_categorie, categorie) VALUES ('Chaussures de randonnée', 'Chaussures');
INSERT INTO sous_categorie (sous_categorie, categorie) VALUES ('Chaussures', 'Vêtements');


/* Table lot */
INSERT INTO lot (marque, modele, annee_achat, categorie, nb_pieces_lot, prix_caution, activite, infos_materiel, annee_peremption) VALUES
    ('AventurePro', 'MontagneX', 2020, 'Mousqueton symétrique', 5, 50, 'escalade', 'Mousqueton symétrique de haute qualité', NULL),
    ('PleinAir', 'MaîtreDesRochers', 2021, 'Casque', 3, 30, 'escalade', 'Casque d''escalade durable avec ventilation', NULL),
    ('TechniqueEscalade', 'ProHarnais', 2019, 'Harnais', 2, 25, 'escalade', 'Harnais d''escalade professionnel pour la sécurité', NULL),
    ('RandoSac', 'SacExplorateur', 2020, 'Sac à dos', 10, 40, 'randonnée', 'Sac à dos spacieux pour la randonnée et le camping', NULL),
    ('NuitsAuCamp', 'TrailBlazer', 2021, 'Tente', 1, 60, 'camping', 'Tente légère et facile à installer pour le camping', NULL),
    ('TechAlpine', 'MaîtreDesGlaces', 2018, 'Piolets', 2, 70, 'alpinisme', 'Outils d''escalade sur glace pour l''alpinisme avancé', NULL),
    ('ÉliteMontagne', 'ProCrampons', 2019, 'Crampons', 2, 80, 'alpinisme', 'Crampons professionnels pour des conditions glacées', NULL),
    ('EscaladePro', 'ClimbChaussuresX', 2022, 'Chaussures d''escalade', 3, 90, 'escalade', 'Chaussures d''escalade haute performance pour la précision', NULL),
    ('RandoMarcheur', 'RandoBottes', 2023, 'Chaussures de randonnée', 4, 55, 'randonnée', 'Bottes de randonnée durables pour tous les terrains', NULL),
    ('PasExtreme', 'ChaussuresSommet', 2022, 'Chaussures', 5, 75, 'randonnée', 'Chaussures extérieures polyvalentes pour diverses activités', NULL),
    ('EscaladeGear', 'AscensionX', 2020, 'Mousqueton', 6, 45, 'escalade', 'Mousqueton d''escalade léger et robuste', NULL),
    ('TechSommet', 'HMSPro', 2021, 'Mousqueton HMS', 4, 65, 'escalade', 'Mousqueton à vis à haute mobilité pour la sécurité', NULL),
    ('SécuritéEscalade', 'CasqueSécurité', 2019, 'Casque', 3, 30, 'escalade', 'Casque de sécurité avec sangles réglables', NULL),
    ('MaîtreHarnais', 'ProHarnais', 2020, 'Harnais', 2, 40, 'escalade', 'Harnais d''escalade de qualité professionnelle pour le confort', NULL),
    ('ExplorateurSentier', 'TenteCamp', 2021, 'Tente', 1, 55, 'camping', 'Tente de camping compacte pour les aventures en plein air', NULL),
    ('EscaladeGlaceTech', 'PionnierGlace', 2018, 'Piolets', 2, 70, 'alpinisme', 'Piolets d''escalade sur glace pour des ascensions difficiles', NULL),
    ('TechNeige', 'CramponsNeige', 2019, 'Crampons', 2, 80, 'alpinisme', 'Crampons spécifiques pour la neige pour l''alpinisme', NULL),
    ('ConfortEscalade', 'ConfortClimb', 2022, 'Chaussures d''escalade', 3, 90, 'escalade', 'Chaussures d''escalade confortables pour des séances prolongées', NULL),
    ('MarcheurSentier', 'RandoBottes', 2023, 'Chaussures de randonnée', 4, 55, 'randonnée', 'Bottes de randonnée durables pour des treks étendus', NULL),
    ('GearPeak', 'ChaussuresSommet', 2022, 'Chaussures', 5, 75, 'randonnée', 'Chaussures polyvalentes adaptées à diverses activités de plein air', NULL);

/* Table activites_lot */


/* Table membre */
INSERT INTO membre VALUES ('bakary@bms.com', 'joelpostbad', 'Swag', 'Bakary', 'France');
INSERT INTO membre VALUES ('mobutu@psg.com', 'speedybaps', 'Bappon', 'Maurice', 'Paris');
INSERT INTO membre VALUES ('fitness@max.com', 'malveillancemax', 'Fitness', 'Marvel', 'France');
INSERT INTO membre VALUES ('bill@hotmail.com', 'microsoft', 'Gates', 'Bilal', 'Washington');
INSERT INTO membre VALUES ('jordan23@gmail.com', 'airjordan', 'Jordan', 'Michael', 'Chicago');
INSERT INTO membre VALUES ('lebron@mvp.com', 'kingjames', 'James', 'LeBron', 'Los Angeles');
INSERT INTO membre VALUES ('serenawilliams@yahoo.com', 'tennischamp', 'Williams', 'Serena', 'Florida');
INSERT INTO membre VALUES ('federer@legend.com', 'forehand123', 'Federer', 'Roger', 'Switzerland');
INSERT INTO membre VALUES ('ronaldo@goal.com', 'goalgetter7', 'Penaldo', 'Cristiano', 'Portugal');
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
INSERT INTO utilisateur(mail_user) VALUES ('bakary@bms.com');
INSERT INTO adherent(id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur(mail_user) VALUES ('mobutu@psg.com');
INSERT INTO adherent(id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur(mail_user) VALUES ('fitness@max.com');
INSERT INTO adherent(id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur(mail_user) VALUES ('bill@hotmail.com');
INSERT INTO adherent(id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur(mail_user) VALUES ('jordan23@gmail.com');
INSERT INTO adherent(id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur(mail_user) VALUES ('lebron@mvp.com');
INSERT INTO adherent(id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur(mail_user) VALUES ('serenawilliams@yahoo.com');

INSERT INTO utilisateur(mail_user) VALUES ('federer@legend.com');
INSERT INTO adherent(id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur(mail_user) VALUES ('ronaldo@goal.com');
INSERT INTO adherent(id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur(mail_user) VALUES ('messi@barca.com');
INSERT INTO adherent(id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur(mail_user) VALUES ('nadal@clay.com');
INSERT INTO adherent(id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur(mail_user) VALUES ('kobebryant@mamba.com');
INSERT INTO adherent(id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur(mail_user) VALUES ('sachin@cricket.com');

INSERT INTO utilisateur(mail_user) VALUES ('tombrady@goat.com');

INSERT INTO utilisateur(mail_user) VALUES ('venus@tennis.com');

INSERT INTO utilisateur(mail_user) VALUES ('ronaldo@brazil.com');
INSERT INTO adherent(id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur(mail_user) VALUES ('maradona@legend.com');
INSERT INTO adherent(id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur(mail_user) VALUES ('ali@boxing.com');
INSERT INTO adherent(id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur(mail_user) VALUES ('schumacher@f1.com');

INSERT INTO utilisateur(mail_user) VALUES ('nike@justdoit.com');

INSERT INTO utilisateur(mail_user) VALUES ('ruth@yankees.com');

INSERT INTO utilisateur(mail_user) VALUES ('flojo@track.com');

INSERT INTO utilisateur(mail_user) VALUES ('bolt@fast.com');
INSERT INTO adherent(id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur(mail_user) VALUES ('pele@brazil.com');
INSERT INTO adherent(id_user) VALUES (id_user_seq.CURRVAL);

INSERT INTO utilisateur(mail_user) VALUES ('magic@lakers.com');

/* Table reservation_refuge */


/* Table quantite_repas */


/* Table reservation_formation */


/* Table location_materiel */


/* Table quantite_materiel */

