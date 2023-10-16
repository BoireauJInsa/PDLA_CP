SET SQL_SAFE_UPDATES = 0;
DELETE FROM Test;
SET SQL_SAFE_UPDATES = 1;

INSERT INTO Test (donne, info, Statut)
 VALUES ('valeur 1', 1, "demandeur"),
		('valeur 2', 2, "admin"),
        ('valeur 3', 3, "admin");