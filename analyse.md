# Analyse statique

## Propriétés
- mailR, nomR, telR, secteurGeo, dateOuverture, dateFermeture, placesRepas, placesNuitee, infosR, typePaiement, prixNuitee, prixDejeuner, prixDiner, prixSouper, prixCasseCroute
- anneeF, rangF, nomF, dateF, dureeF, placesF, infosF, prixF, activite

## Dépendances fonctionelles
- mailR $\to$ nomR, telR, secteurGeo, dateOuverture, dateFermeture, placesRepas, placesNuitee, infosR, typePaiement, prixNuitee, prixDejeuner, prixDiner, prixSouper, prixCasseCroute
- anneeF, rangF $\to$ nomF, dateF, dureeF, placesF, infosF, prixF, activite

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

## Contraintes contextuelles
