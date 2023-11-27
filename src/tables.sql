/* Génération automatique de nos identifiants pour chacune de nos tables */

CREATE SEQUENCE id_user_seq;
CREATE SEQUENCE id_adh_seq;
CREATE SEQUENCE id_res_materiel_seq;

/* Table refuge */
CREATE TABLE refuge (
    mail_refuge VARCHAR(30) PRIMARY KEY,
    nom_refuge VARCHAR(30) NOT NULL,
    tel VARCHAR(12),
    secteur_geo VARCHAR(30) NOT NULL,
    date_ouverture DATE NOT NULL,
    date_fermeture DATE NOT NULL,
    nb_places_repas INTEGER NOT NULL CHECK (nb_places_repas > 0),
    nb_places_nuits INTEGER NOT NULL CHECK (nb_places_nuits > 0),
    infos_refuge VARCHAR(30) NOT NULL,
    type_paiement VARCHAR(30) NOT NULL CHECK (type_paiement IN ('espèce', 'chèque', 'carte-bleue')),
    prix_nuit NUMBER NOT NULL CHECK (prix_nuit > 0),
    CHECK (date_fermeture > date_ouverture)
);

/* Table repas */
CREATE TABLE repas (
    mail_refuge VARCHAR(30) NOT NULL,
    type_repas VARCHAR(30) NOT NULL CHECK (type_repas IN ('déjeuner', 'dîner', 'souper', 'casse-croûte')),
    prix_repas NUMBER NOT NULL CHECK (prix_repas > 0),
    PRIMARY KEY (mail_refuge, type_repas),
    FOREIGN KEY (mail_refuge) REFERENCES refuge(mail_refuge)
);

/* Table formation */
CREATE TABLE formation (
    annee_formation INTEGER CHECK (annee_formation > 0),
    rang_formation INTEGER CHECK (rang_formation > 0),
    nom_formation VARCHAR(30) NOT NULL,
    date_formation DATE NOT NULL,
    duree INTEGER NOT NULL CHECK (duree > 0),
    nb_places_formation INTEGER NOT NULL CHECK (nb_places_formation > 0),
    infos_formation VARCHAR(30) NOT NULL,
    prix_formation NUMBER NOT NULL CHECK (prix_formation > 0),
    PRIMARY KEY (annee_formation, rang_formation)
);

/* Table activite */
CREATE TABLE activite (
    activite VARCHAR(30) CHECK (activite IN ('randonnée', 'escalade', 'alpinisme', 'spéléologie', 'ski de rando', 'cascade de glace')) PRIMARY KEY
);

/* Table activites_formation */
CREATE TABLE activites_formation (
    annee_formation INTEGER NOT NULL,
    rang_formation INTEGER NOT NULL,
    activite VARCHAR(30) NOT NULL,
    PRIMARY KEY (annee_formation, rang_formation, activite),
    FOREIGN KEY (annee_formation, rang_formation) REFERENCES formation(annee_formation, rang_formation),
    FOREIGN KEY (activite) REFERENCES activite(activite)
);

/* Table categorie */
CREATE TABLE categorie (
    categorie VARCHAR(30) PRIMARY KEY
);

/* Table sous_categorie */
CREATE TABLE sous_categorie (
    sous_categorie VARCHAR(30) PRIMARY KEY,
    categorie VARCHAR(30) NOT NULL REFERENCES categorie(categorie)
);

/* Table lot */
CREATE TABLE lot (
    marque VARCHAR(30) NOT NULL,
    modele VARCHAR(30) NOT NULL,
    annee_achat INTEGER NOT NULL CHECK (annee_achat > 1970),
    sous_categorie VARCHAR(30) NOT NULL REFERENCES sous_categorie(sous_categorie),
    nb_pieces_lot INTEGER NOT NULL CHECK (nb_pieces_lot > 0),
    prix_caution INTEGER NOT NULL CHECK (prix_caution > 0),
    activite VARCHAR(30) NOT NULL,
    infos_materiel VARCHAR(30),
    annee_peremption INTEGER,
    CHECK (annee_peremption > annee_achat),
    PRIMARY KEY (marque, modele, annee_achat)
);

/* Table activites_lot */
CREATE TABLE activites_lot (
    marque VARCHAR(30) NOT NULL,
    modele VARCHAR(30) NOT NULL,
    annee_achat INTEGER NOT NULL,
    activite VARCHAR(30) NOT NULL,
    PRIMARY KEY (marque, modele, annee_achat, activite),
    FOREIGN KEY (marque, modele, annee_achat) REFERENCES lot(marque, modele, annee_achat),
    FOREIGN KEY (activite) REFERENCES activite(activite)
);

/* Table membre */
CREATE TABLE membre (
    mail_user VARCHAR(30) PRIMARY KEY,
    password VARCHAR(30) NOT NULL,
    nom_user VARCHAR(30) NOT NULL,
    prenom VARCHAR(30) NOT NULL,
    adresse VARCHAR(30) NOT NULL
);

/* Table utilisateur */
CREATE TABLE utilisateur (
    id_user INTEGER DEFAULT id_user_seq.NEXTVAL PRIMARY KEY,
    mail_user VARCHAR(30),
    FOREIGN KEY (mail_user) REFERENCES membre(mail_user)
);

/* Table adherent */
CREATE TABLE adherent (
    id_adh INTEGER DEFAULT id_adh_seq.NEXTVAL PRIMARY KEY,
    id_user INTEGER NOT NULL,
    FOREIGN KEY (id_user) REFERENCES utilisateur(id_user)
);

/* Table reservation_refuge */
CREATE TABLE reservation_refuge (
    id_user INTEGER NOT NULL,
    mail_refuge VARCHAR(30) NOT NULL,
    date_res_refuge DATE DEFAULT CURRENT_DATE,
    heure VARCHAR(5) DEFAULT TO_CHAR(SYSDATE, 'HH24:MI'),
    nb_nuits INTEGER NOT NULL,
    PRIMARY KEY (id_user, mail_refuge),
    FOREIGN KEY (id_user) REFERENCES utilisateur(id_user),
    FOREIGN KEY (mail_refuge) REFERENCES refuge(mail_refuge)
);

/* Table quantite_repas */
CREATE TABLE quantite_repas (
    id_user NUMBER NOT NULL,
    mail_refuge VARCHAR(30) NOT NULL,
    type_repas VARCHAR(30) NOT NULL,
    nb_repas INTEGER NOT NULL CHECK (nb_repas > 0),
    PRIMARY KEY (id_user, mail_refuge, type_repas),
    FOREIGN KEY (id_user, mail_refuge) REFERENCES reservation_refuge(id_user, mail_refuge),
    FOREIGN KEY (mail_refuge, type_repas) REFERENCES repas(mail_refuge, type_repas)
);

/* Table reservation_formation */
CREATE TABLE reservation_formation (
    id_adh INTEGER NOT NULL,
    annee_formation INTEGER NOT NULL,
    rang_formation INTEGER NOT NULL,
    rang_la INTEGER NOT NULL,
    PRIMARY KEY (id_adh, annee_formation, rang_formation),
    FOREIGN KEY (id_adh) REFERENCES adherent(id_adh),
    FOREIGN KEY (annee_formation, rang_formation) REFERENCES formation(annee_formation, rang_formation)
);

/* Table location_materiel */
CREATE TABLE location_materiel (
    id_res_materiel INTEGER DEFAULT id_res_materiel_seq.NEXTVAL PRIMARY KEY,
    id_adh INTEGER NOT NULL,
    date_emprunt DATE NOT NULL,
    date_retour DATE NOT NULL,
    CHECK (date_emprunt <= date_retour),
    CHECK (date_retour - date_emprunt <= 14),
    FOREIGN KEY (id_adh) REFERENCES adherent(id_adh)
);

/* Table quantite_materiel */
CREATE TABLE quantite_materiel (
    id_res_materiel NUMBER NOT NULL,
    marque VARCHAR(30) NOT NULL,
    modele VARCHAR(30) NOT NULL,
    annee_achat INTEGER NOT NULL,
    nb_pieces_res INTEGER NOT NULL CHECK (nb_pieces_res > 0),
    nb_pieces_perdues INTEGER NOT NULL,
    CHECK (nb_pieces_perdues <= nb_pieces_res),
    PRIMARY KEY (id_res_materiel, marque, modele, annee_achat),
    FOREIGN KEY (id_res_materiel) REFERENCES location_materiel(id_res_materiel),
    FOREIGN KEY (marque, modele, annee_achat) REFERENCES lot(marque, modele, annee_achat)
);
