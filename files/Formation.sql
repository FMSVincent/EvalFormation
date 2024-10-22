-- ------------------------------------------------------------------------------
-- - Reconstruction de la base de données                                     ---
-- ------------------------------------------------------------------------------
DROP DATABASE IF EXISTS Formation;
CREATE DATABASE Formation;
USE Formation;

-- -----------------------------------------------------------------------------
-- - Construction de la table des utilisateurs                               ---
-- -----------------------------------------------------------------------------
CREATE TABLE T_Users (
	IdUser				int(4)		PRIMARY KEY AUTO_INCREMENT,
	Login				varchar(20)	NOT NULL UNIQUE,
	Password			varchar(20)	NOT NULL
) ENGINE = InnoDB;

INSERT INTO T_Users (IdUser, Login, Password) VALUES ( 1, 'Anderson' ,	'Neo' );
INSERT INTO T_Users (IdUser, Login, Password) VALUES ( 2, 'Skywalker',	'Luke' );
INSERT INTO T_Users (IdUser, Login, Password) VALUES ( 3, 'Plissken' ,	'Snake' );
INSERT INTO T_Users (IdUser, Login, Password) VALUES ( 4, 'Ripley'   ,	'Ellen' );
INSERT INTO T_Users (IdUser, Login, Password) VALUES ( 5, 'Bond'     ,	'James' );

SELECT * FROM T_Users;

-- -----------------------------------------------------------------------------
-- - Construction de la table des clients	                                 ---
-- -----------------------------------------------------------------------------

CREATE TABLE T_Customers (
	IdCustomer				int(4)		PRIMARY KEY AUTO_INCREMENT,
	name					varchar(30)	NOT NULL,
	firstName				varchar(30)	NOT NULL,
	email 					varchar(30)	NOT NULL unique,
	phone 					varchar(20)	,
	address					varchar(50)	,
	idUser					int(4)		NOT NULL,
	FOREIGN KEY (idUser)	REFERENCES T_Users(idUser)
) ENGINE = InnoDB;

-- -----------------------------------------------------------------------------
-- - Construction de la table des catégories Formations 
-- -----------------------------------------------------------------------------

CREATE TABLE T_Categories (
	IdCategory 				INT(4) 		 PRIMARY KEY AUTO_INCREMENT,
	CatName 				VARCHAR(30)  NOT NULL
) ENGINE = InnoDB;

insert into T_Categories (IdCategory, CatName) values (1 , 'Dev Web');
insert into T_Categories (IdCategory, CatName) values (2 , 'Cms');
insert into T_Categories (IdCategory, CatName) values (3 , 'Cyber Security');
insert into T_Categories (IdCategory, CatName) values (4 , 'IA');

select * from t_categories;

-- -----------------------------------------------------------------------------
-- - Construction de la table des articles en vente                         ---
-- -----------------------------------------------------------------------------

CREATE TABLE T_Formations (
	IdFormation				int(4)		PRIMARY KEY AUTO_INCREMENT,
	Name					varchar(30)	NOT NULL,
	Duration				int(4),
	Description				varchar(30)	NOT NULL,
	IsRemote				boolean,
	UnitaryPrice			float(8, 2)	NOT NULL DEFAULT 0,
	IdCategory				int(4),
	FOREIGN KEY (IdCategory)	REFERENCES T_Categories(IdCategory)
) ENGINE = InnoDB;

INSERT INTO T_Formations ( Name, Duration, Description, IsRemote, UnitaryPrice , IdCategory ) VALUES ( 'Java', 20, 'Java SE 8 : Syntax & Poo', false, 185.99, 1);
INSERT INTO T_Formations ( Name, Duration, Description, IsRemote, UnitaryPrice , IdCategory ) VALUES ( 'Java avancée', 35, 'Execeptions fichiers JDBC thread...', true, 899.99, 2);
INSERT INTO T_Formations ( Name, Duration, Description, IsRemote, UnitaryPrice , IdCategory ) VALUES ('Spring',15, 'Spring Core/Mvc/Security', true, 79.99, 3);
INSERT INTO T_Formations ( Name, Description, Duration, IsRemote, UnitaryPrice , IdCategory  ) VALUES ( 'PHP frameworks', 2, 'Symphony', false, 75.89, 4);
INSERT INTO T_Formations ( Name, Duration, Description, IsRemote, UnitaryPrice , IdCategory  ) VALUES ( 'C#', 12, 'DotNet core', true, 88.0, 3);

SELECT * FROM T_Formations;

-- -----------------------------------------------------------------------------
-- - Construction de la table des commandes                         ---
-- -----------------------------------------------------------------------------

CREATE TABLE T_Orders (
	IdOrder				int(4)		PRIMARY KEY AUTO_INCREMENT,
	Amount				float(4)	NOT NULL DEFAULT 0,
	DateOrder 			DATE		NOT NULL DEFAULT NOW(),
	IdCustomer          INT(4)   	NOT NULL,
	FOREIGN KEY(IdCustomer) REFERENCES T_Customers(IdCustomer)
) ENGINE = InnoDB;

-- -----------------------------------------------------------------------------
-- - Construction de la table d'association                         ---
-- -----------------------------------------------------------------------------

CREATE TABLE T_Order_Items (
	IdOrderItem			int(4)	PRIMARY KEY AUTO_INCREMENT,
	
	IdFormation           INT(4)   NOT NULL,
	FOREIGN KEY(IdFormation) REFERENCES T_Formations(IdFormation),
	
	Quantity			FLOAT(4) NOT NULL DEFAULT 1,
	UnitaryPrice		FLOAT(4)	NOT NULL DEFAULT 0,
	
	IdOrder             INT(4)   NOT NULL,
	FOREIGN KEY(IdOrder) REFERENCES T_Orders(IdOrder)
) ENGINE = InnoDB;