USE statgambler;

CREATE TABLE USER(
	ID INT AUTO_INCREMENT,
	USERNAME VARCHAR(50),
	PASSWORD VARCHAR(60),
	NAME VARCHAR(50),
    RoleID INT,
    RULETA_ID INT,
    ESTADISTICAS_PERSONALES_ID INT,
    PRIMARY KEY (ID)  
);

CREATE TABLE ROLE(
ID INT AUTO_INCREMENT,
NAME VARCHAR(15),
UserID INT,
PRIMARY KEY (ID)  
);

CREATE TABLE USER_ROLES(
Users_ID INT,
Roles_ID INT,
PRIMARY KEY (Users_ID, Roles_ID)  
);

CREATE TABLE ESTADISTICAS_PERSONALES(
	ID INT AUTO_INCREMENT,
	 DINERO_GASTADO_PRIMITIVAS FLOAT,
     DINERO_GANADO_PRIMITIVAS FLOAT,
     BENEFICIO_PRIMITIVAS FLOAT,
     APUESTAS_PRIMITIVAS_JUGADAS FLOAT,
     BENEFICIO_MEDIO_PRIMITIVAS FLOAT,
    
     DINERO_GASTADO_EUROMILLONES FLOAT,
     DINERO_GANADO_EUROMILLONES FLOAT,
     BENEFICIO_EUROMILLONES FLOAT,
     APUESTAS_EUROMILLONES_JUGADAS FLOAT,
     BENEFICIO_MEDIO_EUROMILLONES FLOAT,
    
     DINERO_GASTADO_RULETAS FLOAT,
     DINERO_GANADO_RULETAS FLOAT,
     BENEFICIO_RULETAS FLOAT,
     APUESTAS_RULETAS_JUGADAS FLOAT,
     BENEFICIO_MEDIO_RULETAS FLOAT,
    USER_ID INT,
    PRIMARY KEY ( ID )  
);

CREATE TABLE PRIMITIVA(
ID INT AUTO_INCREMENT,
RESULTADO0 INT,
RESULTADO1 INT,
RESULTADO2 INT,
RESULTADO3 INT,
RESULTADO4 INT,
RESULTADO5 INT,
COMPLEMENTARIO INT,
REINTEGRO INT,
PRIMARY KEY ( ID )  
);

CREATE TABLE EUROMILLONES(
ID INT AUTO_INCREMENT,
RESULTADO0 INT,
RESULTADO1 INT,
RESULTADO2 INT,
RESULTADO3 INT,
RESULTADO4 INT,
ESTRELLA0 INT,
ESTRELLA1 INT,
PRIMARY KEY ( ID )  
);

CREATE TABLE RULETA(
ID INT AUTO_INCREMENT,
NUMERO INT,
COLOR VARCHAR(5),
USER_ID INT,
PRIMARY KEY ( ID )  
);


ALTER TABLE USER
ADD FOREIGN KEY (RoleID) REFERENCES ROLE(ID);

ALTER TABLE ROLE
ADD FOREIGN KEY (UserID) REFERENCES USER(ID);

ALTER TABLE RULETA
ADD FOREIGN KEY (USER_ID) REFERENCES USER(ID);

ALTER TABLE USER
ADD FOREIGN KEY (RULETA_ID) REFERENCES RULETA(ID);

ALTER TABLE ESTADISTICAS_PERSONALES
ADD FOREIGN KEY (USER_ID) REFERENCES USER(ID);

ALTER TABLE USER
ADD FOREIGN KEY (ESTADISTICAS_PERSONALES_ID) REFERENCES ESTADISTICAS_PERSONALES(ID);