# Analyse statique

## Propriétés
- mailR, nomR, telR, secteurGeo, dateOuverture, dateFermeture, placesRepas, placesNuitee, infosR, typePaiement, prixNuitee, prixDejeuner, prixDiner, prixSouper, prixCasseCroute
- anneeF, rangF, nomF, dateF, dureeF, placesF, infosF, prixF, activite
- marque, modele, anneeAchat, categorie, nbPiecesLot, prixCaution, infosMateriel, anneePeremption, nomCategorie
- idUser, mailUser, pwdUser, nomUser, prenomUser, adresseUser, idAdh

## Dépendances fonctionelles
- mailR $\to$ nomR, telR, secteurGeo, dateOuverture, dateFermeture, placesRepas, placesNuitee, infosR, typePaiement, prixNuitee, prixDejeuner, prixDiner, prixSouper, prixCasseCroute
- anneeF, rangF $\to$ nomF, dateF, dureeF, placesF, infosF, prixF, activite
- marque, modele, anneeAchat $\to$ categorie, nbPiecesLot, prixCaution, activite, infosMateriel, anneePeremption, nomCategorie
- idUser $\to$ mailUser, pwdUser, nomUser, prenomUser, adresseUser, idAdh

## Contraintes de valeur
- typePaiement $\in$ {espèce, chèque, carte-bleue}
- activite $\in$ {randonnée, escalade, alpinisme, spéléologie, ski de rando, cascade de glace}

## Contraintes de multiplicité
- mailR $\nrightarrow$ telR
- mailR $\nrightarrow$ prixDejeuner
- mailR $\nrightarrow$ prixDiner
- mailR $\nrightarrow$ prixSouper
- mailR $\nrightarrow$ prixCasseCroute
- anneeF, rangF $\twoheadrightarrow$ activite
- marque, modele, anneeAchat $\twoheadrightarrow$ activite
- marque, modele, anneeAchat $\nrightarrow$ infosMateriel
- marque, modele, anneeAchat $\nrightarrow$ anneePeremption
- idUser $\nrightarrow$ idAdh
- idUser $\nrightarrow$ ...

## Contraintes contextuelles
- Un membre peut uniquement faire des réservations dans les refuges. Seules les adhérents peuvent s'inscrire à des formations et emprunter du matériel
