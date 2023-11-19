
## Conversion du modèle E/A en modèle relationnel
	
### Sous-types d'entités
- Cas 1 : duplication : reproduire les propriétés de la classe mère dans la/les classe.s fille.s
- Cas 2 : Référence à travers les jointures. Mettre les attributs communs dans la classe mère et ajouter une contrainte de référeence sur la clé de la table fille avaec le même nom que celui de la mère
- Cas 3 : Unification (regrouper les attributs de la mère et de toutes ses filles dans une même table, celle de la mère en général et y ajouter type pour distinguer les cas). Ainsi, pour une ligne donnée, on mettra `NULL` comme valeur dans la/les colonnes dont le type n'est pas concerné.

### Transformation des associations
	
- 1..1 à 1..* : La clé côté 1..1 migre vers 1..*
 
- 0..1 à 1..1 : La clé côté 0..1 migre vers 1..1
 
- 1..1 à 1..1 : La clé d'un côté migre vers l'autre selon notre choix et les requêtes.
 
- 0..1 à 1..* (entité faible) : L'assocation devient une table avec comme clé celle de  1..* et pour attributs ceux de 0..1
 
- 0..\*/1..* à 1..\*/0..* : L'assocation devient une table avec comme clé l'ensemble des clés des deux tables et a pour attributs ses attributs propres (attributs de la classe d'association).
 
### SQL

```sql
CREATE TABLE <table> ((<colonne> <type>
			[DEFAULT <expression>] <contrainte_c>+)*
			[<contrainte_t>*]);

<contrainte_c> := [CONSTRAINT <nom_c>]
		( [NOT] NULL
		| UNIQUE
		| PRIMARY KEY
		| REFERENCES <table>[(<colonne>)] [ON DELETE CASCADE]
		| CHECK (contraintes de valeurs)
		)

<contrainte_t> := [CONSTRAINT <nom_c>]
		( (UNIQUE | PRIMARY KEY)(<colonne>*)
		| FOREIGN KEY (<colonne>*)
		REFERENCES <table>[(<colonne>*)] [ON DELETE CASCADE]
		| CHECK (<condition>)
		)
```

Exemple :

```sql
CREATE TABLE Eleves (
	prenom VARCHAR(30) NOT NULL,
	nom VARCHAR (30) NOT NULL,
	email VARCHAR (30),
	OPTION VARCHAR (5),
	PRIMARY KEY (prenom, nom)
);

CREATE TABLE Notes (
	cours VARCHAR (30) NOT NULL,
	prenom VARCHAR (30) NOT NULL,
	nom VARCHAR (30) NOT NULL,
	note INTEGER,
	PRIMARY KEY(cours, prenom, nom),
	FOREIGN KEY (prenom, nom) REFERENCES Eleves(prenom, nom)
);
```

Pour générer automatiquement des identifiants :

```sql
CREATE SEQUENCE libelleSeq
```

Pour générer une nouvelle valeur, ajouter `NEXTVAL` au libellé de la séquence :

```sql
libelleSeq.NEXTVAL
```
