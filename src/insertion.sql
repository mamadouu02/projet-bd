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

