/* GÉNÉRATION AUTOMATIQUE DES IDENTIFIANTS POUR CHACUNE DE NOS TABLES */

CREATE SEQUENCE id_user_seq;
CREATE SEQUENCE id_adh_seq;
CREATE SEQUENCE id_res_materiel_seq;

/* Table user */
CREATE TABLE utilisateur (
    id_user INTEGER DEFAULT id_user_seq.NEXTVAL PRIMARY KEY,
    mail_user VARCHAR(30),
    id_adh INTEGER,
    FOREIGN KEY (mail_user) REFERENCES Membre(mail_user),
    FOREIGN KEY (id_adh) REFERENCES Adherent(id_adh)
);

/* Table membre */
CREATE TABLE membre (
    id_user INTEGER NOT NULL,
    mail_user VARCHAR(30) PRIMARY KEY,
    password VARCHAR(30) NOT NULL,
    nom_user VARCHAR(30) NOT NULL,
    prenom VARCHAR(30) NOT NULL,
    adresse VARCHAR(30) NOT NULL,
    FOREIGN KEY (id_user) REFERENCES Utilisateur(id_user)
);

/* Table adherent */
CREATE TABLE adherent (
    id_adh INTEGER DEFAULT id_adh_seq.NEXTVAL PRIMARY KEY,
    id_user INTEGER NOT NULL,
    FOREIGN KEY (id_user) REFERENCES Utilisateur(id_user)
);

/* Table refuge */
CREATE TABLE refuge (
    mail_refuge VARCHAR(30) PRIMARY KEY,
    nom_refuge VARCHAR(30) NOT NULL,
    tel VARCHAR(12),
    secteur_geo VARCHAR(30) NOT NULL,
    date_ouverture DATE NOT NULL,
    date_fermeture DATE NOT NULL CHECK (date_fermeture > date_ouverture),
    nb_places_repas INTEGER NOT NULL,
    nb_places_nuits INTEGER NOT NULL,
    infos_refuge VARCHAR(30) NOT NULL,
    type_paiement VARCHAR(30) NOT NULL CHECK (type_paiement IN ('espèce', 'chèque', 'carte-bleue')),
    prix_nuit NUMBER
);


CREATE TABLE lot (
    marque VARCHAR(30) NOT NULL,
    modele VARCHAR(30) NOT NULL,
    annee_achat INTEGER NOT NULL CHECK (annee_achat>0),
    nb_pieces_lot INTEGER NOT NULL CHECK (nb_pieces_lot>=0),
    prix_caution INTEGER NOT NULL CHECK (prix_caution>=0),
    activite VARCHAR(30) NOT NULL,
    infos_materiel VARCHAR(30),
    annee_peremption INTEGER NOT NULL CHECK (annee_peremption>0),
    PRIMARY KEY (marque,modele,annee_achat)
);

CREATE TABLE quantite_materiel (
    nb_pieces_res INTEGER NOT NULL CHECK (nb_pieces_res > 0),
    nb_pieces_perdues INTEGER NOT NULL CHECK (nb_pieces_perdues >=0)
);

CREATE TABLE categorie (
    categorie VARCHAR(30) NOT NULL PRIMARY KEY  
);

CREATE TABLE location_materiel (
    id_res_materiel INTEGER NOT NULL PRIMARY KEY,
    id_adh INTEGER NOT  NULL,
    date_emprunt DATE NOT NULL,
    date_retour DATE,
    CHECK (date_retour-date_emprunt<14)
);
