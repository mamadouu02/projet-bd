# Analyse statique

## Diagramme Entités-Associations

Lien pour UML : https://lucid.app/lucidchart/d2b8bb44-d24a-4599-b568-6898c1b309ea/edit?viewport_loc=-3250%2C-644%2C3188%2C1676%2C0_0&invitationId=inv_f91eb197-61a8-4c7f-8a35-ea307b3efd57

## Dépendances fonctionelles

### REFUGE
- emailR $\to$ nomR, numTR, secteurGeo, dateO, dateF, nbPlaceRepas, nbPlaceNuit, textPrez, typePaiement, prixNuit, typeRepas
- emailR, typeRepas $\to$ prixRepas

### FORMATION
- numRang, annee $\to$ nomF, dateD, duree, nbPlaceDispo, prixF, typeActi

### MATERIEL
- marque, modele, anneeAchat $\to$ catMat, nbPiece, caution, typeActi, textInfo, anneePeremption, nomCategorie

### USER
- idUser

### MEMBRE
- emailMemb $\to$ nom, prenom, addrPost, pswd, (restePayer)

### ADHERENT
- idAdh $\to$ emailMemb

### RESERVATION REFUGE
- idRezRefuge $\to$ idUser,date, heure, emailR, nbNuit
- idRezRefuge, typeRepas $\to$ nbRepas

### RESERVATION FORMATION
- numRang, annee, idAdh $\to$ rangListAtt

### RESERVATION MATERIEL
- idRezLocation $\to$ idAdh, dateRecup, dateRetour, nbPiecePerdu

## Contraintes de valeur

### REFUGE
- typePaiment $\in$ {"espèce","chèque","catre-bleu"}
- typeRepas $\in$ {"petit-dejeuner","déjeuner","casse-croûte","diner"}
- numTR [NULL]

### FORMATION
- typeActi $\in$ {"randonée","escalade","alpinisme","spéléologie","ski de rando","cascade de glace"}
- rangListAtt > 0

### RESERVATION REFUGE
- nbRepas [NULL]
- nbNuit  [NULL]

## Contraintes de multiplicité

### 'Arbre de sous catégorie'
- sousCat $\not \twoheadrightarrow$ sousCat
- sousCat $\not \twoheadrightarrow$ marque,modele,anneeAchat
- ou 
- sousCat $\not \twoheadrightarrow$ sousCat,marque,modele,anneeAchat

### 'Un idUser est associer a un membre sauf si RGPD'
- idUser $\nrightarrow$ emailMemb

### 'Certain menbre sont des ahdérent'
- emailMemb $\nrightarrow$ idAdh

### 'Reservation Différent Materiel'
- idRezLocation $\twoheadrightarrow$ nbPieceRez, marque,modele,anneeAchat

## Contraintes contextuelles
- 'Seul les Adherent peuvent reserver des refuges'
- 'Un adherent peut etre en liste d'attente pour le reservation d'une formation'
- 'Lors de la perte d'une piece de materiel elle doit etre retirer du lots'


