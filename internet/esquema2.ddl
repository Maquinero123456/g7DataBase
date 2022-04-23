CREATE TABLE PERSONAAUTORIZADA (ID BIGINT NOT NULL, APELLIDOS VARCHAR NOT NULL, DIRECCION VARCHAR NOT NULL, ESTADO VARCHAR, FECHAFIN DATE, FECHAINICIO DATE, FECHANACIMIENTO DATE, IDENTIFICACION VARCHAR NOT NULL UNIQUE, NOMBRE VARCHAR NOT NULL, USUARIO_ID BIGINT, PRIMARY KEY (ID));
CREATE TABLE CUENTA (IBAN VARCHAR NOT NULL, Tipo_Cuenta VARCHAR(31), SWIFT VARCHAR, CLASIFICACION VARCHAR, ESTADO BOOLEAN NOT NULL, FECHAAPERTURA DATE NOT NULL, FECHACIERRE DATE, CLIENTE_ID VARCHAR, COMISION DOUBLE, CUENTAREFERENCIA_IBAN VARCHAR, NOMBREBANCO VARCHAR NOT NULL, PAIS VARCHAR, SALDO DOUBLE NOT NULL, SUCURSAL VARCHAR, DIVISA_ABREVIATURA VARCHAR(3), PRIMARY KEY (IBAN));
CREATE TABLE USUARIO (ID BIGINT NOT NULL, ESADMINISTRATIVO BOOLEAN NOT NULL, NOMBRE VARCHAR NOT NULL UNIQUE, PASSWORD VARCHAR NOT NULL, PRIMARY KEY (ID));
CREATE TABLE CLIENTE (ID VARCHAR NOT NULL, tipoCliente VARCHAR(31), CIUDAD VARCHAR NOT NULL, CODIGOPOSTAL VARCHAR NOT NULL, DIRECCION VARCHAR NOT NULL, ESTADO VARCHAR NOT NULL, FECHAALTA DATE NOT NULL, FECHABAJA DATE, IDENTIFICACION VARCHAR NOT NULL UNIQUE, PAIS VARCHAR NOT NULL, APELLIDOS VARCHAR NOT NULL, FECHANACIMIENTO DATE, NOMBRE VARCHAR NOT NULL, USUARIO_ID BIGINT, RAZONSOCIAL VARCHAR NOT NULL, PRIMARY KEY (ID));
CREATE TABLE DEPOSITADAEN (SALDO DOUBLE NOT NULL, CUENTAREFERENCIA_IBAN VARCHAR NOT NULL, POOLEDACCOUNT_IBAN VARCHAR NOT NULL, PRIMARY KEY (CUENTAREFERENCIA_IBAN, POOLEDACCOUNT_IBAN));
CREATE TABLE TRANSACCION (IDUNICO VARCHAR NOT NULL, CANTIDAD DOUBLE NOT NULL, COMISION DOUBLE, FECHAEJECUCION DATE, FECHAINSTRUCCION DATE NOT NULL, INTERNACIONAL BOOLEAN, TIPO VARCHAR NOT NULL, DESTINO_IBAN VARCHAR, EMISOR_ABREVIATURA VARCHAR(3), ORIGEN_IBAN VARCHAR, RECEPTOR_ABREVIATURA VARCHAR(3), PRIMARY KEY (IDUNICO));
CREATE TABLE AUTORIZACION (TIPO VARCHAR NOT NULL, EMPRESA_ID VARCHAR NOT NULL, PERSONA_ID BIGINT NOT NULL, PRIMARY KEY (EMPRESA_ID, PERSONA_ID));
CREATE TABLE DIVISA (ABREVIATURA VARCHAR(3) NOT NULL, CAMBIOEURO DOUBLE NOT NULL, NOMBRE VARCHAR NOT NULL, SIMBOLO VARCHAR, PRIMARY KEY (ABREVIATURA));
ALTER TABLE PERSONAAUTORIZADA ADD CONSTRAINT FK_PERSONAAUTORIZADA_USUARIO_ID FOREIGN KEY (USUARIO_ID) REFERENCES USUARIO (ID);
ALTER TABLE CUENTA ADD CONSTRAINT FK_CUENTA_CUENTAREFERENCIA_IBAN FOREIGN KEY (CUENTAREFERENCIA_IBAN) REFERENCES CUENTA (IBAN);
ALTER TABLE CUENTA ADD CONSTRAINT FK_CUENTA_CLIENTE_ID FOREIGN KEY (CLIENTE_ID) REFERENCES CLIENTE (ID);
ALTER TABLE CUENTA ADD CONSTRAINT FK_CUENTA_DIVISA_ABREVIATURA FOREIGN KEY (DIVISA_ABREVIATURA) REFERENCES DIVISA (ABREVIATURA);
ALTER TABLE CLIENTE ADD CONSTRAINT FK_CLIENTE_USUARIO_ID FOREIGN KEY (USUARIO_ID) REFERENCES USUARIO (ID);
ALTER TABLE DEPOSITADAEN ADD CONSTRAINT FK_DEPOSITADAEN_POOLEDACCOUNT_IBAN FOREIGN KEY (POOLEDACCOUNT_IBAN) REFERENCES CUENTA (IBAN);
ALTER TABLE DEPOSITADAEN ADD CONSTRAINT FK_DEPOSITADAEN_CUENTAREFERENCIA_IBAN FOREIGN KEY (CUENTAREFERENCIA_IBAN) REFERENCES CUENTA (IBAN);
ALTER TABLE TRANSACCION ADD CONSTRAINT FK_TRANSACCION_DESTINO_IBAN FOREIGN KEY (DESTINO_IBAN) REFERENCES CUENTA (IBAN);
ALTER TABLE TRANSACCION ADD CONSTRAINT FK_TRANSACCION_ORIGEN_IBAN FOREIGN KEY (ORIGEN_IBAN) REFERENCES CUENTA (IBAN);
ALTER TABLE TRANSACCION ADD CONSTRAINT FK_TRANSACCION_EMISOR_ABREVIATURA FOREIGN KEY (EMISOR_ABREVIATURA) REFERENCES DIVISA (ABREVIATURA);
ALTER TABLE TRANSACCION ADD CONSTRAINT FK_TRANSACCION_RECEPTOR_ABREVIATURA FOREIGN KEY (RECEPTOR_ABREVIATURA) REFERENCES DIVISA (ABREVIATURA);
ALTER TABLE AUTORIZACION ADD CONSTRAINT FK_AUTORIZACION_PERSONA_ID FOREIGN KEY (PERSONA_ID) REFERENCES PERSONAAUTORIZADA (ID);
ALTER TABLE AUTORIZACION ADD CONSTRAINT FK_AUTORIZACION_EMPRESA_ID FOREIGN KEY (EMPRESA_ID) REFERENCES CLIENTE (ID);
CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT NUMERIC(38), PRIMARY KEY (SEQ_NAME));
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0);
