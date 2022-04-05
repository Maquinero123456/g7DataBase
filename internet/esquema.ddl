CREATE TABLE CUENTA (IBAN VARCHAR NOT NULL, Tipo_Cuenta VARCHAR(31), SWIFT VARCHAR, ESTADO BOOLEAN, FECHA_APERTURA DATE, NOMBREBANCO VARCHAR NOT NULL, PAIS VARCHAR, SALDO VARCHAR NOT NULL, SUCURSAL VARCHAR, CLASIFICACION VARCHAR, FECHA_CIERRE DATE, COMISION DOUBLE, PRIMARY KEY (IBAN))
CREATE TABLE CLIENTE (ID VARCHAR NOT NULL, Tipo_Cliente VARCHAR(31), CIUDAD VARCHAR NOT NULL, CODIGOPOSTAL VARCHAR NOT NULL, DIRECCION VARCHAR NOT NULL, ESTADO BOOLEAN NOT NULL, FECHA_ALTA DATE NOT NULL, FECHA_BAJA DATE, IDENTIFICACION VARCHAR NOT NULL UNIQUE, PAIS VARCHAR NOT NULL, RAZON_SOCIAL VARCHAR NOT NULL, APELLIDOS VARCHAR NOT NULL, FECHA_NACIMIENTO DATE, NOMBRE VARCHAR NOT NULL, PRIMARY KEY (ID))
CREATE TABLE PERSONA_AUTORIZADA (ID VARCHAR NOT NULL, APELLIDOS VARCHAR NOT NULL, DIRECCION VARCHAR NOT NULL, ESTADO BOOLEAN, FECHAFIN DATE, FECHA_INICIO DATE, FECHA_NACIMIENTO DATE, IDENTIFICACION VARCHAR NOT NULL UNIQUE, NOMBRE VARCHAR NOT NULL, PRIMARY KEY (ID))
CREATE TABLE TRANSACCION (ID_UNICO VARCHAR NOT NULL, CANTIDAD DOUBLE NOT NULL, COMISION DOUBLE, INTERNACIONAL BOOLEAN, TIPO VARCHAR NOT NULL, FECHAEJECUCION DATE, FECHAINSTRUCCION DATE NOT NULL, PRIMARY KEY (ID_UNICO))
CREATE TABLE DIVISA (ABREVIATURA VARCHAR(3) NOT NULL, CAMBIOEURO DOUBLE NOT NULL, NOMBRE VARCHAR NOT NULL, SIMBOLO VARCHAR, PRIMARY KEY (ABREVIATURA))
CREATE TABLE DEPOSITADA_EN (SALDO DOUBLE NOT NULL, PRIMARY KEY (SALDO))
CREATE TABLE AUTORIZACION (TIPO VARCHAR NOT NULL, PRIMARY KEY (TIPO))
CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT NUMERIC(38), PRIMARY KEY (SEQ_NAME))
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0)
