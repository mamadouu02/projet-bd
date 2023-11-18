/* GÉNÉRATION AUTOMATIQUE DES IDENTIFIANTS POUR CHACUNE DE NOS TABLES */

CREATE SEQUENCE idUserSeq;
CREATE SEQUENCE idAdhSeq;
CREATE SEQUENCE idResMSeq;

/* Table Utilisateur */
CREATE TABLE Utilisateur (
    idUser INTEGER DEFAULT idUserSeq.NEXTVAL PRIMARY KEY,
    mailUser VARCHAR(30),
    idAdh INTEGER,
    FOREIGN KEY (mailUser) REFERENCES Membre(mailUser),
    FOREIGN KEY (idAdh) REFERENCES Adherent(idAdh)
);

/* Table Membre */
CREATE TABLE Membre (
    idUser INTEGER NOT NULL,
    mailUser VARCHAR(30) PRIMARY KEY,
    pwdUser VARCHAR(30) NOT NULL,
    nomUser VARCHAR(30) NOT NULL,
    prenomUser VARCHAR(30) NOT NULL,
    adresseUser VARCHAR(30) NOT NULL,
    FOREIGN KEY (idUser) REFERENCES Utilisateur(idUser)
);

/* Table Adherent */
CREATE TABLE Adherent (
    idAdh INTEGER DEFAULT idAdhSeq.NEXTVAL PRIMARY KEY,
    idUser INTEGER NOT NULL,
    FOREIGN KEY (idUser) REFERENCES Utilisateur(idUser)
);

/* Table Refuge */
CREATE TABLE Refuge (
    mailR VARCHAR(30) PRIMARY KEY,
    nomR VARCHAR(30) NOT NULL,
    telR VARCHAR(12),
    secteurGeo VARCHAR(30) NOT NULL,
    dateOuv DATE NOT NULL,
    dateFerm DATE NOT NULL CHECK (dateFerm > dateOuv),
    nbPlacesRepas INTEGER NOT NULL,
    nbPlacesNuit INTEGER NOT NULL,
    infosR VARCHAR(30) NOT NULL,
    typePaiement VARCHAR(30) NOT NULL CHECK (typePaiement IN ('espèce', 'chèque', 'carte-bleue')),
    prixNuit NUMBER
);
