/* GÉNÉRATION AUTOMATIQUE DES IDENTIFIANTS POUR CHACUNE DE NOS TABLES */

CREATE SEQUENCE id_user_seq;
CREATE SEQUENCE id_adh_seq;
CREATE SEQUENCE id_res_materiel_seq;
CREATE SEQUENCE mail_refuge_seq;

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
    adresse VARCHAR(30) NOT NULL
);

/* Table adherent */
CREATE TABLE adherent (
    id_adh INTEGER DEFAULT id_adh_seq.NEXTVAL PRIMARY KEY,
    mail_user varchar(30) NOT NULL,
    FOREIGN KEY (mail_user) REFERENCES membre(mail_user)
);

/* Table refuge */
CREATE TABLE refuge (
    mail_refuge VARCHAR(30) PRIMARY KEY,
    nom_refuge VARCHAR(30) NOT NULL,    
    tel VARCHAR(12) NOT NULL,
    secteur_geo VARCHAR(30) NOT NULL,
    date_ouverture DATE NOT NULL,
    date_fermeture DATE NOT NULL,
    nb_places_repas INTEGER NOT NULL,
    nb_places_nuits INTEGER NOT NULL,    
    infos_refuge VARCHAR(30) NOT NULL,
    type_paiement VARCHAR(30) NOT NULL CHECK (type_paiement IN ('espèce', 'chèque', 'carte-bleue')),
    prix_nuit NUMBER NOT NULL
);

/* Table reservation de refuge*/
CREATE TABLE reservation_refuge(
    id_user NUMBER
    DEFAULT id_user_seq.NEXTVAL,
    mail_refuge varchar(30) NOT NULL,
    date_res_refuge DATE NOT NULL,
    heure VARCHAR(8),
    nb_nuits NUMBER NOT NULL,
    prix_res_refuge NUMBER NOT NULL,
    PRIMARY KEY (id_user, mail_refuge),
    FOREIGN KEY (id_user) REFERENCES utilisateur(id_user),
    FOREIGN KEY (mail_refuge) REFERENCES refuge(mail_refuge)
);

/*Table de rapas*/
CREATE TABLE repas (
    mail_refuge VARCHAR(30) NOT NULL,
    type_repas VARCHAR(20) NOT NULL,
    prix_repas NUMBER NOT NULL,
    PRIMARY KEY (mail_refuge, type_repas),
    FOREIGN KEY (mail_refuge) REFERENCES refuge(mail_refuge)
);

/*Table Quantité de repas*/
CREATE TABLE quantite_repas (
    id_user NUMBER NOT NULL,
    mail_refuge VARCHAR(30) NOT NULL,
    type_repas VARCHAR(20) NOT NULL, 
    PRIMARY KEY (id_user, mail_refuge, type_repas),
    FOREIGN KEY (id_user, mail_refuge) REFERENCES reservation_refuge(id_user, mail_refuge),
    FOREIGN KEY (mail_refuge,type_repas) REFERENCES repas(mail_refuge,type_repas)
);


CREATE TABLE lot (
    marque VARCHAR(30) NOT NULL,
    modele VARCHAR(30) NOT NULL,
    annee_achat INTEGER NOT NULL CHECK (annee_achat>1970),
    categorie VARCHAR(30) NOT NULL REFERENCES categorie(categorie),
    nb_pieces_lot INTEGER NOT NULL CHECK (nb_pieces_lot>=0),
    prix_caution INTEGER NOT NULL CHECK (prix_caution>=0),
    activite VARCHAR(30) NOT NULL,
    infos_materiel VARCHAR(30),
    annee_peremption INTEGER NOT NULL CHECK (annee_peremption>1970),
    PRIMARY KEY (marque,modele,annee_achat)
);
/* Table catégorie*/
CREATE TABLE categorie (
    categorie VARCHAR(30) NOT NULL PRIMARY KEY  
);

/*tABLE SOUS_CATEGORIE*/
CREATE TABLE sous_categorie(
    sous_categorie VARCHAR(30) PRIMARY KEY,
    categorie VARCHAR(30) NOT NULL REFERENCES categorie(categorie) 
);

/*Table de location de matériel*/
CREATE TABLE location_materiel (
    id_res_materiel INTEGER NOT NULL PRIMARY KEY,
    id_adh INTEGER NOT  NULL,
    date_emprunt DATE NOT NULL,
    date_retour DATE NOT NULL, 
    CHECK (date_retour-date_emprunt< 14),
    FOREIGN KEY(id_adh) REFERENCES adherent(id_adh)
);
/* Table Quantité matériel*/
CREATE TABLE quantite_materiel (
    id_res_materiel NUMBER NOT NULL,
    marque VARCHAR(30) NOT NULL,
    modele VARCHAR(30) NOT NULL,
    annee_achat INTEGER NOT NULL,
    nb_pieces_res INTEGER NOT NULL CHECK (nb_pieces_res > 0),
    nb_pieces_perdues INTEGER NOT NULL CHECK (nb_pieces_perdues >=0),
    PRIMARY KEY(marque,modele, annee_achat, id_res_materiel),
    FOREIGN KEY (id_res_materiel) REFERENCES location_materiel(id_res_materiel),
    FOREIGN KEY (marque, modele, annee_achat) REFERENCES lot(marque, modele, annee_achat)
);

/*Table formation*/
CREATE TABLE formation (
    annee_formation INTEGER CHECK (annee_formation > 0),
    rang_formation INTEGER CHECK (rang_formation > 0),
    nom_formation VARCHAR(30) NOT NULL,
    date_formation DATE NOT NULL,
    duree INTEGER NOT NULL CHECK (duree > 0),
    PRIMARY KEY (annee_formation, rang_formation),
    nb_places_formation INTEGER NOT NULL CHECK (nb_places_formation > 0),
    infos_formation VARCHAR(30) NOT NULL,
    prix_formation NUMBER NOT NULL CHECK (prix_formation > 0)
);
/*Table Reservation formation*/
CREATE TABLE reservation_formation (
    id_adh INTEGER NOT NULL,
    annee_formation INTEGER NOT NULL,
    rang_formation INTEGER NOT NULL,
    rang_la INTEGER NOT NULL,
    PRIMARY KEY (id_adh, annee_formation, rang_formation),
    FOREIGN KEY (id_adh) REFERENCES adherent(id_adh),
    FOREIGN KEY (annee_formation, rang_formation) REFERENCES formation(annee_formation, rang_formation)
);
/*Table activité*/
CREATE TABLE activite (
    activite VARCHAR(30) NOT NULL CHECK (activite IN ('randonnée', 'escalade', 'alpinisme', 'spéléologie', 'ski de rando', 'cascade de glace')) PRIMARY KEY
);
CREATE TABLE activites_formation (
    annee_formation INTEGER NOT NULL,
    rang_formation INTEGER NOT NULL,
    activite VARCHAR(30) NOT NULL,
    PRIMARY KEY (annee_formation, rang_formation, activite),
    FOREIGN KEY (annee_formation, rang_formation) REFERENCES formation(annee_formation, rang_formation),
    FOREIGN KEY (activite) REFERENCES activite(activite)
);
/*Table activites lot*/
CREATE TABLE activites_lot (
    marque VARCHAR(30) NOT NULL,
    modele VARCHAR(30) NOT NULL,
    annee_achat INTEGER NOT NULL CHECK (annee_achat > 0),
    activite VARCHAR(30) NOT NULL,
    PRIMARY KEY (marque, modele, annee_achat, activite),
    FOREIGN KEY (marque, modele, annee_achat) REFERENCES lot(marque, modele, annee_achat),
    FOREIGN KEY (activite) REFERENCES activite(activite)
);
