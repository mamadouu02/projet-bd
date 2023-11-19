# Modélisation du problème

## Analyse statique

### Propriétés
- mail_refuge, nom_refuge, tel, secteur_geo, date_ouverture, date_fermeture, nb_places_repas, nb_places_nuits, infos_refuge, type_paiement, prix_nuit, type_repas, prix_repas
- annee_formation, rang_formation, nom_formation, date_formation, duree, nb_places_formation, infos_formation, prix_formation, activite
- marque, modele, annee_achat, categorie, nb_pieces_lot, prix_caution, infos_materiel, annee_peremption
- id_user, mail_user, password, nom_user, prenom, adresse, id_adh
- date_res_refuge, heure, nb_nuits, nb_repas, prix_res_refuge
- rang_la
- id_res_materiel, nb_pieces_res, date_emprunt, date_retour, nb_pieces_perdues
- cout_res_refuge, cout_res_formation, somme_due, somme_remboursee

### Dépendances fonctionelles
- mail_refuge $\to$ nom_refuge, tel, secteur_geo, date_ouverture, date_fermeture, nb_places_repas, nb_places_nuits, infos_refuge, type_paiement, prix_nuit, type_repas
- mail_refuge, type_repas $\to$ prix_repas
- annee_formation, rang_formation $\to$ nom_formation, date_formation, duree, nb_places_formation, infos_formation, prix_formation, activite
- marque, modele, annee_achat $\to$ categorie, nb_pieces_lot, prix_caution, activite, infos_materiel, annee_peremption
- id_user $\to$ mail_user, password, nom_user, prenom, adresse, id_adh
- id_adh $\to$ id_user
- id_user, mail_refuge $\to$ date_res_refuge, heure, nb_nuits, type_repas
- id_user, mail_refuge, type_repas $\to$ nb_repas
- id_user, mail_refuge, nb_nuits, nb_repas $\to$ prix_res_refuge
- id_adh, annee_formation, rang_formation $\to$ rang_la
- id_res_materiel $\to$ id_adh, marque, modele, annee_achat, date_emprunt, date_retour
- id_res_materiel, marque, modele, annee_achat $\to$ nb_pieces_res, nb_pieces_perdues
- id_user $\to$ cout_res_refuge, cout_res_formation, somme_due, somme_remboursee

### Contraintes de valeur
- type_paiement $\in$ {espèce, chèque, carte-bleue}
- type_repas $\in$ {déjeuner, dîner, souper, casse-croûte}
- activite $\in$ {randonnée, escalade, alpinisme, spéléologie, ski de rando, cascade de glace}
- date_ouverture < date_fermeture
- annee_achat < annee_peremption
- nb_nuits ≤ nb_places_nuits
- nb_pieces_res ≤ nb_pieces_lot
- date_emprunt < date_retour + 2 semaines
- nb_pieces_perdues ≤ nb_pieces_res
- somme_remboursee ≤ somme_due
- nb_places_repas, nb_places_nuits, prix_nuit, prix_repas, rang_formation, duree, nb_places_formation, prix_formation, nb_pieces_lot, prix_caution, id_user, id_adh, prix_res_refuge, id_res_materiel, nb_pieces_res > 0
- nb_nuits, nb_repas, rang_la, nb_pieces_perdues, cout_res_refuge, cout_res_formation, somme_due, somme_remboursee ≥ 0

### Contraintes de multiplicité
- mail_refuge $\nrightarrow$ tel
- mail_refuge $\twoheadrightarrow$ type_repas
- annee_formation, rang_formation $\twoheadrightarrow$ activite
- marque, modele, annee_achat $\twoheadrightarrow$ activite
- marque, modele, annee_achat $\nrightarrow$ infos_materiel
- marque, modele, annee_achat $\nrightarrow$ annee_peremption
- categorie -|->> categorie
- id_user $\nrightarrow$ mail_user, password, nom_user, prenom, adresse
- id_user $\nrightarrow$ id_adh
- id_user, mail_refuge -|->> type_repas
- id_res_materiel $\twoheadrightarrow$ marque, modele, annee_achat

### Contraintes contextuelles
- Les catégories de matériels sont organisées en un arbre avec des sous-catégories
- Un membre peut uniquement faire des réservations dans les refuges. Seuls les adhérents peuvent s'inscrire à des formations et emprunter du matériel
- Les membres ont la possibilité de réserver pour un seul ou plusieurs repas dans la jorunée. Ils peuvent également ne réserver que des repas sans la nuitée ou inversement
- Prise en compte de la liste d'attente pour les formations
- Lors de la perte/casse de matériel, ceux-ci doivent être retirés des lots et la somme due par l'adhérent doit être calculée
- Prise en compte du droit à l'oubli

## Schéma Entités/Associations

[![Schéma Entités/Associations](./schema-ea.png "Schéma Entités/Associations")](https://lucid.app/lucidchart/d2b8bb44-d24a-4599-b568-6898c1b309ea/edit?viewport_loc=-3250%2C-644%2C3188%2C1676%2C0_0&invitationId=inv_f91eb197-61a8-4c7f-8a35-ea307b3efd57)