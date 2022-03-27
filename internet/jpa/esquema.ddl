CREATE TABLE CUENTA (IBAN VARCHAR NOT NULL, DTYPE VARCHAR(31), SWIFT VARCHAR, ESTADO BOOLEAN, FECHA_APERTURA DATE, NOMBREBANCO VARCHAR NOT NULL, PAIS VARCHAR, SALDO VARCHAR NOT NULL, SUCURSAL VARCHAR, CLASIFICACION VARCHAR, FECHA_CIERRE DATE, FK_INVOICE VARCHAR NOT NULL, COMISION DOUBLE, PRIMARY KEY (IBAN));
CREATE TABLE DIVISA (ABREVIATURA VARCHAR(3) NOT NULL, CAMBIOEURO DOUBLE NOT NULL, NOMBRE VARCHAR NOT NULL, SIMBOLO VARCHAR, PRIMARY KEY (ABREVIATURA));
CREATE TABLE DEPOSITADA_EN (SALDO DOUBLE NOT NULL, PRIMARY KEY (SALDO));
ALTER TABLE CUENTA ADD CONSTRAINT FK_CUENTA_FK_INVOICE FOREIGN KEY (FK_INVOICE) REFERENCES CUENTA (IBAN);
