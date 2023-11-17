/*GÉNÉRATION AUTOMATIQUE DES IDENTIFIANTS POUR CHACUNE DE NOS TABLES*/

create sequence mailUserSeq ;
create sequence idUserSeq;
create sequence idAdhSeq;
create sequence mailRSeq;
create sequence typeRepasSeq;
create sequence anneFeq;
create sequence rangFSeq;
create sequence modeleSeq;
create sequence marqueSeq;
create sequence anneeAchatSeq;
create sequence categorieSeq;
create sequence ideResMSeq;



/*Table Membre */
create table MEMBRE (
    mailUser varchar
    DEFAULT mailUserSeq.nextval
    primary key,
    nomUser varchar(20) not null,
    prenomUser varchar(30) not null,
    adresseUser varchar not null,
    pwdUser varchar not null
);

/*Table ADHERENT */
create table ADHERENT(
    idAdh number
    DEFAULT idAdhSeq.nextval
    primary key,
    mailUser varchar not null,
    foreign key (mailUser) references MEMBRE(mailUser)
);

/* Table User : on utlisera utilisateur pour éviter des conflits de nom avec la BD existante*/
create table UTILISATEUR (
    idUser number
    DEFAULT idUserSeq.nextva
    primary key,
    mailUser varchar not null,
    idAdh number null,
    foreign key (mailUser) references MEMBRE(mailUser),
    foreign key (idAdh) references ADHERENT(idAdh)
);

/* Table refuge*/
create table REFUGE (
    mailR varchar
    default mailRSeq.nextval
    primary key,
    nomR varchar not null,
    telR varchar(12) null,
    secteurGeo varchar not null,
    dateOuv date not null,
    dateFerm date not null check (dateFerm > dateOuv),
    nbPLacesRepas number not null,
    nbPLacesNuit number not null,
    infosR varchar not null,
    typePaiement varchar not null check (typePaiement IN ('espèce', 'chèque', 'carte-bleue')),
    prixNuit double
);

