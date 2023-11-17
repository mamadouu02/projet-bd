# Modélisation du problème

## Analyse statique

### Propriétés
- mailR, nomR, telR, secteurGeo, dateOuverture, dateFermeture, placesRepas, placesNuitee, infosR, typePaiement, prixNuitee, typeRepas, prixRepas
- anneeF, rangF, nomF, dateF, dureeF, placesF, infosF, prixF, activite
- marque, modele, anneeAchat, categorie, nbPiecesLot, prixCaution, infosMateriel, anneePeremption
- idUser, mailUser, pwdUser, nomUser, prenomUser, adresseUser, idAdh
- dateResR, heureResR, nbNuits, nbRepas, prixResR
- rangLA
- idResM, nbPiecesRes, dateEmprunt, dateRetour, nbPiecesManquantes
- coutResR, coutResF, sommeDue, sommeRemboursee

### Dépendances fonctionelles
- mailR $\to$ nomR, telR, secteurGeo, dateOuverture, dateFermeture, placesRepas, placesNuitee, infosR, typePaiement, prixNuitee, typeRepas
- mailR, typeRepas $\to$ prixRepas
- anneeF, rangF $\to$ nomF, dateF, dureeF, placesF, infosF, prixF, activite
- marque, modele, anneeAchat $\to$ categorie, nbPiecesLot, prixCaution, activite, infosMateriel, anneePeremption
- idUser $\to$ mailUser, pwdUser, nomUser, prenomUser, adresseUser, idAdh
- idAdh $\to$ idUser
- idUser, mailR $\to$ dateResR, heureResR, nbNuits, typeRepas
- idUser, mailR, typeRepas $\to$ nbRepas
- idUser, mailR, nbNuits, nbRepas $\to$ prixResR
- idAdh, anneeF, rangF $\to$ rangLA
- idResM $\to$ idAdh, marque, modele, anneeAchat, dateEmprunt, dateRetour
- idResM, marque, modele, anneeAchat $\to$ nbPiecesRes, nbPiecesManquantes
- idUser $\to$ coutResR, coutResF, sommeDue, sommeRemboursee

### Contraintes de valeur
- typePaiement $\in$ {espèce, chèque, carte-bleue}
- typeRepas $\in$ {déjeuner, dîner, souper, casse-croûte}
- activite $\in$ {randonnée, escalade, alpinisme, spéléologie, ski de rando, cascade de glace}
- dateOuverture < dateFermeture
- anneeAchat < anneePeremption
- nbNuits ≤ placesNuitees
- nbPiecesRes ≤ nbPiecesLot
- dateEmprunt < dateRetour + 2 semaines
- nbPiecesManquantes ≤ nbPiecesRes
- sommeRemboursee ≤ sommeDue
- placesRepas, placesNuitee, prixNuitee, prixRepas, rangF, dureeF, placesF, prixF, nbPiecesLot, prixCaution, idUser, idAdh, prixResR, idResM, nbPiecesRes,  > 0
- nbNuits, nbRepas, rangLA, nbPiecesManquantes, coutResR, coutResF, sommeDue, sommeRemboursee ≥ 0

### Contraintes de multiplicité
- mailR $\nrightarrow$ telR
- mailR $\twoheadrightarrow$ typeRepas
- anneeF, rangF $\twoheadrightarrow$ activite
- marque, modele, anneeAchat $\twoheadrightarrow$ activite
- marque, modele, anneeAchat $\nrightarrow$ infosMateriel
- marque, modele, anneeAchat $\nrightarrow$ anneePeremption
- categorie $\ntwoheadrightarrow$ categorie
- idUser $\nrightarrow$ mailUser, pwdUser, nomUser, prenomUser, adresseUser
- idUser $\nrightarrow$ idAdh
- idUser, mailR $\ntwoheadrightarrow$ typeRepas
- idResM $\twoheadrightarrow$ marque, modele, anneeAchat

### Contraintes contextuelles
- Les catégories de matériels sont organisées en un arbre avec des sous-catégories
- Un membre peut uniquement faire des réservations dans les refuges. Seuls les adhérents peuvent s'inscrire à des formations et emprunter du matériel
- Les membres ont la possibilité de réserver pour un seul ou plusieurs repas dans la jorunée. Ils peuvent également ne réserver que des repas sans la nuitéé ou inversement
- Prise en compte de la liste d'attente pour les formations
- Lors de la perte/casse de matériel, ceux-ci doivent être retirés des lots et la somme due par l'adhérent doit être calculée
- Prise en compte du droit à l'oubli

## Schéma Entités/Associations

https://lucid.app/lucidchart/d2b8bb44-d24a-4599-b568-6898c1b309ea/edit?viewport_loc=-3250%2C-644%2C3188%2C1676%2C0_0&invitationId=inv_f91eb197-61a8-4c7f-8a35-ea307b3efd57
