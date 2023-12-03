DROP TABLE IF EXISTS Echec CASCADE;
DROP TABLE IF EXISTS Demande CASCADE;
DROP TABLE IF EXISTS Demandeur CASCADE;
DROP TABLE IF EXISTS Aideur CASCADE;
DROP TABLE IF EXISTS Valideur CASCADE;
DROP TABLE IF EXISTS Admin CASCADE;
DROP TABLE IF EXISTS Personnes CASCADE;
DROP TABLE IF EXISTS Test CASCADE;


SELECT 'Creating tables : Start' AS 'Message';

CREATE TABLE Test (
	ID INT NOT NULL AUTO_INCREMENT,
	donne VARCHAR(30),
	info INT,
	Statut ENUM ("admin", "demandeur", "valideur", "aideur") NOT NULL,
	CONSTRAINT pkTest  PRIMARY KEY(ID)) 
;

CREATE TABLE Personnes (
	ID INT NOT NULL AUTO_INCREMENT,
	Login VARCHAR(30),
	Pass VARCHAR(30),
	Statut ENUM ("admin", "demandeur", "valideur", "aideur") NOT NULL,
	CONSTRAINT pk_Personnes  PRIMARY KEY(ID)) 
;

CREATE TABLE Admin (
	ID INT NOT NULL,
	CONSTRAINT pk_Admin  PRIMARY KEY(ID))
;

CREATE TABLE Demandeur (
	ID INT NOT NULL,
    ID_hospital INT NOT NULL,
	CONSTRAINT pk_Demandeur  PRIMARY KEY(ID))
    ENGINE=InnoDB;

CREATE TABLE Valideur (
	ID INT NOT NULL,
    ID_hospital INT NOT NULL,
    Statut ENUM ("attente", "ok", "nok") NOT NULL,
	CONSTRAINT pk_Valideur  PRIMARY KEY(ID))
;

CREATE TABLE Aideur (
	ID INT NOT NULL,
	CONSTRAINT pk_Aideur  PRIMARY KEY(ID))
    ENGINE=InnoDB;

CREATE TABLE Demande (
	ID INT NOT NULL AUTO_INCREMENT,
    ID_Demandeur INT NOT NULL,
    ID_Aideur INT,
    ID_hospital INT NOT NULL,
    Message VARCHAR(250) NOT NULL,
    Statut ENUM ('attente', 'accepté','refusé','prise','terminé') NOT NULL,
	CONSTRAINT pk_Demande  PRIMARY KEY(ID))
;

CREATE TABLE Echec (
	ID INT NOT NULL,
    Message VARCHAR(250) NOT NULL,
	CONSTRAINT pk_Echec  PRIMARY KEY(ID))
;




SELECT 'Creating tables : Foreigners Key' AS 'Message';



ALTER TABLE Admin ADD CONSTRAINT fk_Admin_Personnes  FOREIGN KEY(ID) REFERENCES Personnes (ID) ; 

ALTER TABLE Valideur ADD CONSTRAINT fk_Valideur_Personnes  FOREIGN KEY(ID) REFERENCES Personnes (ID) ; 

ALTER TABLE Demandeur ADD CONSTRAINT fk_Demandeur_Personnes  FOREIGN KEY(ID) REFERENCES Personnes (ID) ; 

ALTER TABLE Aideur ADD CONSTRAINT fk_Aideur_Personnes  FOREIGN KEY(ID) REFERENCES Personnes (ID) ; 
ALTER TABLE Demande ADD CONSTRAINT fk_Demande_Demandeur  FOREIGN KEY(ID_Demandeur) REFERENCES Demandeur (ID) ; 


ALTER TABLE Echec ADD CONSTRAINT fk_Echec_Demande  FOREIGN KEY(ID) REFERENCES Demande (ID) ; 

SELECT 'Creating tables : Done' AS 'Message';