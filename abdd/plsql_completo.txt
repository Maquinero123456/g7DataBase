/*  
    Ejercicio 2 - b) Modificar los procedimientos de alta de clientes y personas autorizadas. 
    El parametro P_ID se ignorara y se tomara su valor de las secuencias 
    (SQ_CLIENTE y SQ_PERSONA).

    Creamos las secuencias que se incrementan de uno en uno
    con la intencion de asignar un ID unico a cada cliente 
    y persona autorizada. Al crear uno nuevo, se le asignara
    la secuencia actual en la BD y se incrementa uno.    
*/
CREATE SEQUENCE SQ_CLIENTE START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE SQ_PERSONA START WITH 1
INCREMENT BY 1;


-----------------------------------------------------------
--                    EJERCICIO 1
-----------------------------------------------------------
/*  a) Creamos un paquete de gestion de clientes, 
    declarando las excepciones necesarias y los respectivos
    procedimientos de los siguientes apartados.
*/
CREATE OR REPLACE PACKAGE PK_GESTION_CLIENTES IS
    CuentasAlta EXCEPTION;
    ModificarCuenta EXCEPTION;
    CuentasBaja EXCEPTION;
    AutorizadoExiste EXCEPTION;
    AutorizadoModificar EXCEPTION;
    AutorizadoEliminar EXCEPTION;
    TipoException EXCEPTION;
    AutorizacionNoExiste EXCEPTION;
    PROCEDURE ALTA_CLIENTE(IDENT VARCHAR2, TIPO VARCHAR2, ESTADO CHAR, FECHABAJA DATE, DIRE VARCHAR2, CIU VARCHAR2, CP VARCHAR2, PAIS VARCHAR2, RAZON VARCHAR2, NOM VARCHAR2, APE VARCHAR2, FECHANAC DATE);
    PROCEDURE MODIFICAR_CLIENTE(IDENT VARCHAR2, EST CHAR, BAJA DATE, DIREC VARCHAR2, CIU VARCHAR2, COD INTEGER, PA VARCHAR2);
    PROCEDURE BAJA_CLIENTE(PK INT);
    PROCEDURE ADD_AUTORIZADO(TIPO VARCHAR2, PK INT, EMP INT, IDENTIFICACION VARCHAR2, NOMBRE VARCHAR2, APELLIDOS VARCHAR2, DIRECCION VARCHAR2, FECHA_NACIMIENTO DATE, ESTADO CHAR, FECHAFIN DATE);
    PROCEDURE MODIFICAR_AUTORIZADO(PK INT, EMP INT, NOM VARCHAR2, APEL VARCHAR2, DIREC VARCHAR2, NACI DATE, EST CHAR, FIN DATE, TIP VARCHAR2);
    PROCEDURE ELIMINAR_AUTORIZADO(PK INT, EMP INT);
    
END PK_GESTION_CLIENTES;



-- Creamos cuerpo del paquete
CREATE OR REPLACE PACKAGE BODY PK_GESTION_CLIENTES IS

    /*  1. RF2) Alta del cliente: 
        Se busca dar de alta un cliente (tanto empresa como individual) en el sistema 
            - Si el cliente existe -> se hace UPDATE de su campo ESTADO a '1' indicando el alta
              y se actualiza la fecha de alta a la actual

            - Si el cliente no existe -> se creara en la base de datos con los datos pasados por 
              parametros en el procedimiento, teniendo en cuenta si se trata de un
              individual o una empresa. En caso de que el tipo no sea "JURIDICO" O "FISICO"
              saltara la correspondiente excepcion
    */
    PROCEDURE ALTA_CLIENTE(IDENT VARCHAR2, TIPO VARCHAR2, ESTADO CHAR, FECHABAJA DATE, DIRE VARCHAR2, CIU VARCHAR2, CP VARCHAR2, PAIS VARCHAR2, RAZON VARCHAR2, NOM VARCHAR2, APE VARCHAR2, FECHANAC DATE) IS
    existe INTEGER;
    ID_UNICO  INTEGER;
    BEGIN
        SELECT COUNT(*) INTO existe FROM CLIENTE WHERE IDENTIFICACION = IDENT;
        IF existe=1 THEN
            UPDATE CLIENTE SET ESTADO = '1', FECHA_ALTA = SYSDATE WHERE IDENTIFICACION = IDENT;
        ELSE 
            IF (TIPO = 'JURIDICO') THEN
                ID_UNICO := SQ_CLIENTE.NEXTVAL;
                INSERT INTO CLIENTE VALUES(ID_UNICO, IDENT, TIPO, ESTADO, SYSDATE, FECHABAJA, DIRE, CIU, CP, PAIS);
                INSERT INTO EMPRESA VALUES(ID_UNICO, RAZON);
            ELSIF (TIPO = 'FISICO') THEN
                 ID_UNICO := SQ_CLIENTE.NEXTVAL;
                INSERT INTO CLIENTE VALUES(ID_UNICO, IDENT, TIPO, ESTADO, SYSDATE, FECHABAJA, DIRE, CIU, CP, PAIS);
                INSERT INTO INDIVIDUAL VALUES(ID_UNICO, NOM, APE, FECHANAC);
            ELSE RAISE TipoException;
            END IF;
        END IF;
    END;

    /*  2. RF3) Modificar Cliente:
        Procedimiento para modificar los datos de un Cliente en la base de datos
        dada su identificacion. Si el cliente existe, se hace un UPDATE con los datos
        pasados como parametros en el procedimiento.

        Si el cliente no existe, salta la correspondiente excepcion de que no se pudo modificar
    
    */
    PROCEDURE MODIFICAR_CLIENTE(IDENT VARCHAR2, EST CHAR, BAJA DATE, DIREC VARCHAR2, CIU VARCHAR2, COD INTEGER, PA VARCHAR2) IS
    clienteExiste INTEGER;
    BEGIN
        SELECT COUNT(*) INTO clienteExiste FROM CLIENTE WHERE IDENTIFICACION = IDENT;
        IF clienteExiste=1 THEN
            UPDATE CLIENTE SET ESTADO = EST, FECHA_BAJA = BAJA, DIRECCION = DIREC, CIUDAD = CIU, CODIGOPOSTAL = COD, PAIS = PA WHERE IDENTIFICACION = IDENT;
        ELSE RAISE ModificarCuenta;
        END IF;
    END;


    /*  3. RF4) Baja de un Cliente:
        Procedimiento para dar de baja al cliente. Esto no significa que el cliente sea
        eliminado de la BD, si no que su ESTADO pasa a '0'.
            - Si el cliente exitse Y su cuenta esta de alta, entonces se actualiza a '0'

            - En caso contrario salta la respectiva excepcion
    */
    PROCEDURE BAJA_CLIENTE(PK INT) IS
    cuentasAlta INTEGER;
    clienteExiste INTEGER;
    BEGIN
        SELECT COUNT(*) INTO cuentasAlta FROM CUENTA_FINTECH WHERE ESTADO = '1' AND CLIENTE_ID = PK;
        SELECT COUNT(*) INTO clienteExiste FROM CLIENTE WHERE IDENTIFICACION = PK;
        IF cuentasAlta=1 AND clienteExiste=1 THEN
            UPDATE CLIENTE SET ESTADO = '0', FECHA_BAJA = SYSDATE WHERE ID = PK;
        ELSE RAISE CuentasBaja;
        END IF;
    END;

    /*  4. RF6) Aniadir autorizados:
        Procedimiento para anadir un autorizado a la(s) cuentas de una persona Juridica (empresa)
            - Solo hay dos tipos de autorizacion; CONSULTA u OPERACION, si no se indica alguno de estos
              saltara la respectiva excepcion
            

    */
    PROCEDURE ADD_AUTORIZADO(TIPO VARCHAR2, PK INT, EMP INT, IDENTIFICACION VARCHAR2, NOMBRE VARCHAR2, APELLIDOS VARCHAR2, DIRECCION VARCHAR2, FECHA_NACIMIENTO DATE, ESTADO CHAR, FECHAFIN DATE) AS
    personaAutorizadaExiste INTEGER;
    empresaExiste INTEGER;
    autorizacionNoExiste INTEGER;
    ID_UNICO  INTEGER;
    BEGIN
        IF(TIPO NOT LIKE 'CONSULTA' OR TIPO NOT LIKE 'OPERACION') THEN
            RAISE TipoException;
        END IF;
        SELECT COUNT(*) INTO personaAutorizadaExiste FROM PERSONA_AUTORIZADA WHERE ID = PK;
        SELECT COUNT(*) INTO empresaExiste FROM EMPRESA WHERE ID = EMP;
	SELECT COUNT(*) INTO autorizacionNoExiste FROM AUTORIZACION WHERE PERSONA_AUTORIZADA_ID = PK AND EMPRESA_ID = EMP;
        IF personaAutorizadaExiste=1 AND empresaExiste=1 AND autorizacionNoExiste=0 THEN
            INSERT INTO AUTORIZACION VALUES(TIPO, PK, EMP);
        ELSIF autorizacionNoExiste = 0 THEN
            ID_UNICO := SQ_PERSONA.NEXTVAL;
            INSERT INTO PERSONA_AUTORIZADA VALUES (ID_UNICO, IDENTIFICACION, NOMBRE, APELLIDOS, DIRECCION, FECHA_NACIMIENTO, ESTADO, SYSDATE, FECHAFIN);
            INSERT INTO AUTORIZACION VALUES(TIPO, ID_UNICO, EMP);
        END IF;
    END;


    /*  5. RF7) Modificar Persona Autorizada:
        Procedimiento para modificar los datos de una Persona Autorizada ya existente en la Base de datos.
        Estas personas deben estar en alguna Empresa, y también se puede cambiar su autorizacion.

            - Si el tipo de autorizacion no es vacio, CONSULTA u OPERACION, saltara la respectiva excepcion de Tipo.

            - Si la persona y la empresa existen, y esta autorizada en ella, se hace UPDATE de los datos pasados por parametro
            a la tabla de Persona Autorizada, y de la autorizacion en la propia empresa.

            - En caso de que no exista la empresa, o la pertsona, o no haya autorizacion NO se hace UPDATE y salta la correspondiente
            excepcion de AutorizadoModificar
    
    */
    PROCEDURE MODIFICAR_AUTORIZADO(PK INT, EMP INT, NOM VARCHAR2, APEL VARCHAR2, DIREC VARCHAR2, NACI DATE, EST CHAR, FIN DATE, TIP VARCHAR2) IS
    personaAutorizadaExiste INTEGER;
    empresaExiste INTEGER;
    autorizacionExiste INTEGER;
    BEGIN
        IF(TIP NOT LIKE 'CONSULTA' OR TIP NOT LIKE 'OPERACION') THEN
            RAISE TipoException;
        END IF;
        SELECT COUNT(*) INTO personaAutorizadaExiste FROM PERSONA_AUTORIZADA WHERE ID = PK;
        SELECT COUNT(*) INTO empresaExiste FROM EMPRESA WHERE ID = EMP;
        SELECT COUNT(*) INTO autorizacionExiste FROM AUTORIZACION WHERE PERSONA_AUTORIZADA_ID = PK AND EMPRESA_ID = EMP;
        IF personaAutorizadaExiste=1 AND empresaExiste=1 AND autorizacionExiste=1 THEN
            UPDATE PERSONA_AUTORIZADA SET NOMBRE = NOM, APELLIDOS = APEL, DIRECCION = DIREC, FECHA_NACIMIENTO = NACI, ESTADO = EST, FECHAFIN = FIN WHERE ID = PK;
            UPDATE AUTORIZACION SET TIPO = TIP WHERE PERSONA_AUTORIZADA_ID = PK AND EMPRESA_ID = EMP;
        ELSE RAISE AutorizadoModificar;
        END IF;
    END;


    /*  6. RF8) Eliminar autorizados de una empresa:
        Procedimiento para eliminar a personas autorizadas de una empresa, esto NO significa que se eliminen de la BD.
        Lo que se borra es la Autorizacion en la empresa.

            - Si existe la autorizacion de la persona PK y la empresa EMP, entonces se borra.

            
    
    */
    PROCEDURE ELIMINAR_AUTORIZADO(PK INT, EMP INT) AS
    autorizacionExiste INTEGER;
    personaExiste INTEGER;
    BEGIN
	SELECT COUNT(*) INTO personaExiste FROM AUTORIZACION WHERE PERSONA_AUTORIZADA_ID = PK;
        SELECT COUNT(*) INTO autorizacionExiste FROM AUTORIZACION WHERE PERSONA_AUTORIZADA_ID LIKE PK AND EMPRESA_ID LIKE EMP;
        IF autorizacionExiste=1 THEN
            DELETE FROM AUTORIZACION WHERE PERSONA_AUTORIZADA_ID = PK AND EMPRESA_ID = EMP;
        ELSE RAISE AutorizacionNoExiste;
        END IF;
        IF personaExiste=1 THEN
            UPDATE PERSONA_AUTORIZADA SET ESTADO = 'BORRADO', FECHAFIN = SYSDATE WHERE ID = PK;
        ELSE RAISE AutorizadoEliminar;
        END IF;
    END;


END PK_GESTION_CLIENTES;
/

/*  b) Creamos un paquete de gestion para las cuentas, 
    declarando las excepciones necesarias y los respectivos
    procedimientos de los siguientes apartados.
*/
CREATE OR REPLACE PACKAGE PK_GESTION_CUENTAS AS
    ClienteException EXCEPTION;
    CuentaException EXCEPTION;
    ReferenciaException EXCEPTION;
    SaldoException EXCEPTION;
    PROCEDURE APERTURA_CUENTA_POOLED(ID_CLIENTE INTEGER, IBN VARCHAR2, SWFT VARCHAR2, ESTA CHAR, FECHADECIERRRE DATE, CLASI VARCHAR2);
    PROCEDURE APERTURA_CUENTA_SEGREGATED(ID_CLIENTE INTEGER, IBN VARCHAR2, SWFT VARCHAR2, ESTA CHAR, FECHADECIERRRE DATE, CLASI VARCHAR2, COMISION DECIMAL, REFERENCIA VARCHAR2);
    PROCEDURE CIERRE_CUENTA_POOLED(IBN VARCHAR2);
    PROCEDURE CIERRE_CUENTA_SEGREGADA(IBN VARCHAR2);
    
END PK_GESTION_CUENTAS;
/

-- Creamos el body del apartado b
CREATE OR REPLACE PACKAGE BODY PK_GESTION_CUENTAS AS

    /* 1. RF5) Apertura de una cuenta agrupada (Pooled), para ello, el cliente NO puede estar dado de baja, en caso contrario
    saltara la respectiva excepcion de Cliente. Al igual que si la cuenta ya existe, esta no se creara y dara excepcion Cuenta.

    En el caso correcto, se creara la cuenta, luego la cuenta fintech y finalmente la correspondiente pooled.
    */
    PROCEDURE APERTURA_CUENTA_POOLED(ID_CLIENTE INTEGER, IBN VARCHAR2, SWFT VARCHAR2, ESTA CHAR, FECHADECIERRRE DATE, CLASI VARCHAR2) IS
    clienteNoAlta INTEGER;
    cuentaExiste INTEGER;
    BEGIN
        SELECT COUNT(*) INTO clienteNoAlta FROM CLIENTE WHERE ID = ID_CLIENTE AND ESTADO LIKE '1';
        IF clienteNoAlta=0 THEN
            RAISE ClienteException;
        END IF;
        SELECT COUNT(*) INTO cuentaExiste FROM CUENTA WHERE IBAN LIKE IBN;
        IF cuentaExiste=1 THEN
            RAISE CuentaException;
        END IF;

        INSERT INTO CUENTA VALUES(IBN, SWFT);
        INSERT INTO CUENTA_FINTECH VALUES(IBN, ESTA, SYSDATE, FECHADECIERRE, CLASI, ID_CLIENTE);
        INSERT INTO POOLED_ACCOUNT VALUES(IBN);

    END;

    /* 1. RF5) Apertura de una cuenta segregada, para ello, el cliente NO puede estar dado de baja, en caso contrario
    saltara la respectiva excepcion de Cliente. Al igual que si la cuenta ya existe, esta no se creara y dara excepcion Cuenta.
    Al ser segregada necesitara que exista una cuenta referencia.

    En el caso correcto, se creara la cuenta, luego la cuenta fintech y finalmente la correspondiente segregada.
    */
    PROCEDURE APERTURA_CUENTA_SEGREGATED(ID_CLIENTE INTEGER, IBN VARCHAR2, SWFT VARCHAR2, ESTA CHAR, FECHADECIERRRE DATE, CLASI VARCHAR2, COMISION DECIMAL, REFERENCIA VARCHAR2) IS
    clienteNoAlta INTEGER;
    cuentaExiste INTEGER;
    cuentaRefNoExiste INTEGER;
    BEGIN
        SELECT COUNT(*) INTO clienteNoAlta FROM CLIENTE WHERE ID = ID_CLIENTE AND ESTADO LIKE '1';
        IF clienteNoAlta=0 THEN
            RAISE ClienteException;
        END IF;
        SELECT COUNT(*) INTO cuentaExiste FROM CUENTA WHERE IBAN LIKE IBN;
        IF cuentaExiste=1 THEN
            RAISE CuentaException;
        END IF;
        SELECT COUNT(*) INTO cuentaRefNoExiste FROM CUENTA WHERE IBAN LIKE REFERENCIA;
        IF cuentaRefNoExiste=0 THEN
            RAISE ReferenciaException;
        END IF;
        INSERT INTO CUENTA VALUES(IBN, SWFT);
        INSERT INTO CUENTA_FINTECH VALUES(IBN, ESTA, SYSDATE, FECHADECIERRE, CLASI, ID_CLIENTE);
        INSERT INTO SEGREGADA VALUES(IBN, COMISION, REFERENCIA);

    END;


    /* 2. RF9) Cierre de una cuenta Pooled, para ello, debe tener 0 en todos los saldos de sus divisas.
    NO se elimina la cuenta, si no que se coloca el estado y la fecha de cierre correspondientes.

        - Si la cuenta no existe, no ocurre nada y salta excepcion

        - Si todos los saldos son 0, en cada una de las divisas, se coloca el ESTADO como '0' y la fecha de cierre
        como la de la hora en la que se ejecuta el procedimiento

        - En caso de que el saldo no sea 0, salta la excepcion
    */
    PROCEDURE CIERRE_CUENTA_POOLED(IBN VARCHAR2) IS
    cuentaNoExiste INTEGER;
    saldo FLOAT;
    BEGIN       
        SELECT COUNT(*) INTO cuentaNoExiste FROM CUENTA WHERE IBAN LIKE IBN;
        IF cuentaNoExiste = 0 THEN
            RAISE CuentaException;
        END IF;
        SELECT SUM(SALDO) INTO saldo FROM DEPOSITADA_EN WHERE POOLED_ACCOUNT_IBAN LIKE IBN;
        IF saldo = 0 THEN
            UPDATE CUENTA_FINTECH SET ESTADO = '0', FECHA_CIERRE = SYSDATE WHERE IBAN = IBN;
        ELSE RAISE SaldoException;
        END IF;
    END;

    /* 2. RF9) Cierre de una cuenta segregada, para ello, debe tener 0 en su saldo
    NO se elimina la cuenta, si no que se coloca el estado y la fecha de cierre correspondientes.

        - Si la cuenta no existe, no ocurre nada y salta excepcion

        - Si todos los saldos son 0, en cada una de las divisas, se coloca el ESTADO como '0' y la fecha de cierre
        como la de la hora en la que se ejecuta el procedimiento

        - En caso de que el saldo no sea 0, salta la excepcion
    */
    PROCEDURE CIERRE_CUENTA_SEGREGADA(IBN VARCHAR2) IS
    cuentaNoExiste INTEGER;
    saldo FLOAT;
    BEGIN 
        SELECT COUNT(*) INTO cuentaNoExiste FROM CUENTA WHERE IBAN LIKE IBN;
        IF cuentaNoExiste = 0 THEN
            RAISE CuentaException;
        END IF;
        SELECT SALDO INTO saldo FROM CUENTA_REFERENCIA WHERE IBAN LIKE (SELECT CUENTA_REFERENCIA_IBAN FROM SEGREGADA WHERE IBAN LIKE IBN);
        IF saldo = 0 THEN
            UPDATE CUENTA_FINTECH SET ESTADO = '0', FECHA_CIERRE = SYSDATE WHERE IBAN = IBN;
        ELSE RAISE SaldoException;
        END IF;
    END;

END PK_GESTION_CUENTAS;


-----------------------------------------------------------
--                    EJERCICIO 2
-----------------------------------------------------------
/*  a. RF11) Crear una vista denominada V_SALDOS que permita ver los datos para cada cliente de todas sus
    cuentas con sus saldos. Si es necesario se pueden crear 2 vistas, una para clientes individuales y otra para
    autorizados.
*/
CREATE OR REPLACE VIEW V_SALDO_INDIVIDUAL(IDENTIFICACION, IBAN, SALDO, ABREVIATURA, CAMBIOEURO) AS 
SELECT INDIVIDUAL.ID, CUENTA_REFERENCIA.IBAN, CUENTA_REFERENCIA.SALDO, CUENTA_REFERENCIA.DIVISA_ABREVIATURA, DIVISA.CAMBIOEURO
FROM INDIVIDUAL, CUENTA_FINTECH, SEGREGADA, CUENTA_REFERENCIA, DIVISA WHERE INDIVIDUAL.ID = CUENTA_FINTECH.CLIENTE_ID AND CUENTA_FINTECH.IBAN = SEGREGADA.IBAN AND SEGREGADA.CUENTA_REFERENCIA_IBAN = SEGREGADA.IBAN AND DIVISA.ABREVIATURA = CUENTA_REFERENCIA.DIVISA_ABREVIATURA;

CREATE OR REPLACE VIEW V_SALDO_AUTORIZADO(IDENTIFICACION, IBAN, SALDO, ABREVIATURA, CAMBIOEURO) AS 
SELECT PERSONA_AUTORIZADA.ID, CUENTA_REFERENCIA.IBAN, CUENTA_REFERENCIA.SALDO, CUENTA_REFERENCIA.DIVISA_ABREVIATURA, DIVISA.CAMBIOEURO
FROM PERSONA_AUTORIZADA, AUTORIZACION, EMPRESA, CUENTA_FINTECH, SEGREGADA, CUENTA_REFERENCIA, DIVISA WHERE AUTORIZACION.PERSONA_AUTORIZADA_ID = PERSONA_AUTORIZADA.ID AND AUTORIZACION.EMPRESA_ID = EMPRESA.ID AND EMPRESA.ID = CUENTA_FINTECH.CLIENTE_ID AND CUENTA_FINTECH.IBAN = SEGREGADA.IBAN AND SEGREGADA.CUENTA_REFERENCIA_IBAN = SEGREGADA.IBAN AND DIVISA.ABREVIATURA = CUENTA_REFERENCIA.DIVISA_ABREVIATURA;


/*  c) Crear un trigger TR_TRANSACCION que tome el valor id_unico de una secuencia. 
    Creamos una secuencia para ir llevando el numero de transacciones 
*/
CREATE SEQUENCE SQ_TRANSACCION START WITH 1
INCREMENT BY 1;

/*  Creamos un trigger de transaccion para que, antes de generar la respectiva transaccion, 
    se coja el id correspondiente a la siguiente transaccion de la secuencia anterior.
*/
CREATE OR REPLACE TRIGGER TR_TRANSACCION BEFORE INSERT ON TRANSACCION FOR EACH ROW
BEGIN
    SELECT SQ_TRANSACCION.NEXTVAL INTO :NEW.ID_UNICO FROM DUAL;
END;



<<<<<<< HEAD:abdd/plsql_completo.sql
-----------------------------------------------------------
--                    EJERCICIO 3
-----------------------------------------------------------
/*
    a) Crear una vista V_TARJETA_MENSUAL que permita ver el total de movimientos para cada cliente de cada
    una de sus tarjetas agrupado por divisa. CREATE OR REPLACE VIEW V_TARJETA_MENSUAL"
    ("IDENTIFICACION", "NUMERO_TARJETA", "GASTO", "ABREVIATURA").
*/
CREATE OR REPLACE VIEW V_TARJETA_MENSUAL(IDENTIFICACION, NUMERO_TARJETA, GASTO, ABREVIATURA) AS 
SELECT CLIENTE.IDENTIFICACION, TARJETA.NUMERO, SUM(TRANSACCION.CANTIDAD), TRANSACCION.DIVISA_ABREVIATURA FROM CLIENTE, TRANSACCION, TARJETA, CUENTA_FINTECH WHERE CLIENTE.ID = CUENTA_FINTECH.CLIENTE_ID AND CUENTA_FINTECH.IBAN = TARJETA.CUENTA_IBAN AND CUENTA_FINTECH.IBAN = TRANSACCION.CUENTA_IBAN GROUP BY TRANSACCION.DIVISA_ABREVIATURA, CLIENTE.IDENTIFICACION, TARJETA.NUMERO;
=======
CREATE OR REPLACE VIEW V_TARJETA_MENSUAL
(IDENTIFICACION, NUMERO_TARJETA, GASTO, ABREVIATURA) AS 
SELECT CLIENTE.IDENTIFICACION, MOVIMIENTOS.TARJETA_NUMERO, SUM(MOVIMIENTOS.CANTIDAD), MOVIMIENTOS.DIVISA_ABREVIATURA FROM CLIENTE, TARJETA, CUENTA_FINTECH, MOVIMIENTOS WHERE MOVIMIENTOS.STATUS LIKE 'PENDIENTE' AND MOVIMIENTOS.TARJETA_NUMERO = TARJETA.NUMERO AND TARJETA.CUENTA_IBAN = CUENTA_FINTECH.IBAN AND CUENTA_FINTECH.CLIENTE_ID = CLIENTE.ID GROUP BY MOVIMIENTOS.DIVISA_ABREVIATURA, CLIENTE.IDENTIFICACION, MOVIMIENTOS.TARJETA_NUMERO;
>>>>>>> 1f4748da3a2b261b8541860ac8de45f1345909bf:abdd/plsql_completo.txt


/*  b) Crear una vista V_PAGOS_PENDIENTES que permita ver el total de pagos pendientes (campo Estado) para
    cada cliente de cada una de sus tarjetas agrupado por divisa. CREATE OR REPLACE VIEW
    V_PAGOS_PENDIENTES" ("IDENTIFICACION", "NUMERO_TARJETA", "PENDIENTES", "ABREVIATURA").
*/
CREATE OR REPLACE VIEW V_TARJETA_PENDIENTES(IDENTIFICACION, NUMERO_TARJETA, PENDIENTES, ABREVIATURA) AS 
SELECT CLIENTE.IDENTIFICACION, MOVIMIENTOS.TARJETA_NUMERO, COUNT(MOVIMIENTOS.STATUS), MOVIMIENTOS.DIVISA_ABREVIATURA FROM CLIENTE, TARJETA, CUENTA_FINTECH, MOVIMIENTOS WHERE MOVIMIENTOS.STATUS LIKE 'PENDIENTE' AND MOVIMIENTOS.TARJETA_NUMERO = TARJETA.NUMERO AND TARJETA.CUENTA_IBAN = CUENTA_FINTECH.IBAN AND CUENTA_FINTECH.CLIENTE_ID = CLIENTE.ID GROUP BY MOVIMIENTOS.DIVISA_ABREVIATURA, CLIENTE.IDENTIFICACION, MOVIMIENTOS.TARJETA_NUMERO;


/*  c) Crear un procedimiento P_COBRO que recorra todos los Movimientos de tarjeta y para aquellos con un
    Estado Pendiente y modo debito, cambie su Estado a Cobrado y actualice el Saldo de las cuentas
    convenientemente.
*/
CREATE OR REPLACE PROCEDURE P_COBRO AS
        ibanCuenta VARCHAR2(20);
        tipoCuenta VARCHAR2(20);
        saldoCuenta FLOAT;
        saldoException EXCEPTION;
        tipoException EXCEPTION;
    BEGIN 
        FOR i IN (SELECT * FROM MOVIMIENTOS WHERE MODOOPERACION LIKE 'DEBITO' AND STATUS LIKE 'PENDIENTE') LOOP
            SELECT CUENTA_IBAN INTO ibanCuenta FROM TARJETA WHERE NUMERO = i.TARJETA_NUMERO;
            SELECT CLASIFICACION INTO tipoCuenta FROM CUENTA_FINTECH WHERE IBAN = ibanCuenta;
            IF tipoCuenta = 'SEGREGADA' THEN
                SELECT CUENTA_REFERENCIA_IBAN INTO ibanCuenta FROM SEGREGADA WHERE IBAN LIKE ibanCuenta;
                SELECT SALDO INTO saldoCuenta FROM CUENTA_REFERENCIA WHERE IBAN LIKE ibanCuenta;
                IF saldoCuenta < i.CANTIDAD THEN
                    RAISE saldoException;
                END IF;
                UPDATE CUENTA_REFERENCIA SET SALDO = saldoCuenta-i.CANTIDAD WHERE IBAN LIKE ibanCuenta;
                UPDATE MOVIMIENTOS SET STATUS = 'COBRADO' WHERE ID LIKE i.ID;
            ELSIF tipoCuenta = 'POOLED' THEN
                SELECT IBAN, SALDO INTO ibanCuenta,saldoCuenta FROM CUENTA_REFERENCIA WHERE IBAN IN (SELECT CUENTA_REFERENCIA_IBAN FROM DEPOSITADA_EN WHERE POOLED_ACCOUNT_IBAN LIKE ibanCuenta) AND DIVISA_ABREVIATURA LIKE i.DIVISA_ABREVIATURA;
                IF saldoCuenta < i.CANTIDAD THEN
                    RAISE saldoException;
                END IF;
                UPDATE CUENTA_REFERENCIA SET SALDO = saldoCuenta-i.CANTIDAD WHERE IBAN LIKE ibanCuenta;
                UPDATE MOVIMIENTOS SET STATUS = 'COBRADO' WHERE ID LIKE i.ID;
            ELSE
                RAISE tipoException;
            END IF;
        END LOOP;  
END;

/*  d) Crear un Job denominado J_LIQUIDAR que llame a P_COBRO el primer dia de cada mes a las 0:00
    Una vez terminado el procedimeinto de cobro, establecemos un job J_LIQUIDAR
    que tenga una repeticion mensual a las 00:00 para realizar automaticamente el procedimiento P_COBRO
*/
BEGIN
    DBMS_SCHEDULER.CREATE_JOB (
        job_name => 'J_LIQUIDAR',
        job_type => 'STORED_PROCEDURE',
        job_action => 'P_COBRO',
        start_date => sysdate,
        repeat_interval => 'FREQ=MONTHLY;BYMONTHDAY=1;BYHOUR=00',
        end_date => null,
        enabled =>true,
        comments => 'Se ejecuta el primer dia de cada mes');
END;
    


-----------------------------------------------------------
--                    EJERCICIO 4
-----------------------------------------------------------
/*  a) Crear un paquete de operativa (PK_OPERATIVA) que incorpore distintos procesos de gestion de divisas,
    pagos, movimientos y transacciones para proporcionar la funcionalidad necesaria de los distintos requisitos de
    la aplicacion.
    
    1. (RF13) Insertar transacciones. El procedimiento permitira registrar transacciones entre cuentas
    bancarias. Las cuentas pueden ser ambas de eBury o una puede ser de eBury y la otra externa. Los
    saldos deberan actualizarse adecuadamente en las cuentas de eBury y en las cuentas asociadas a las
    de eBury.
    
    2. (RF18) Cambio de divisas. El procedimiento permitira realizar un cambio de divisas en una cuenta
    agrupada (pooled). El cambio de divisas se considerara una transaccion especial donde el origen y
    destino es la misma cuenta. Para poder realizar un cambio de divisas sera necesario que la cuenta
    tenga saldos en las divisas de origen y destino. Los saldos de las cuentas asociadas con la cuenta
    agrupada debera actualizarse tambien. No sera posible realizar un cambio de divisas en cuentas
    segregadas.
*/
CREATE OR REPLACE PACKAGE PK_OPERATIVA IS
    PROCEDURE INSERT_TRANSACCION(IBAN_ORIGEN VARCHAR2, IBAN_DESTINO VARCHAR2, DINERITO DOUBLE);
    PROCEDURE CAMBIO_DIVISA(IBAN VARCHAR2, DIVISA_ORIGEN VARCHAR2, DIVISA_DESTINO VARCHAR2, DINERITO DOUBLE);
    
END PK_OPERATIVA;


CREATE OR REPLACE PACKAGE BODY PK_OPERATIVA IS
    PROCEDURE INSERT_TRANSACCION(IBAN_ORIGEN VARCHAR2, IBAN_DESTINO VARCHAR2, DINERITO DOUBLE) AS
    BEGIN
        NULL;
    END;
END PK_OPERATIVA;


/*  c) Crear un procedimiento denominado P_COBRO_APLAZADO que recorra todos los Movimientos de tarjeta y
    para aquellos con un Estado Pendiente y modo credito o aplazado, cambie su Estado a Cobrado y
    actualice el Saldo de las cuentas convenientemente.
*/
CREATE OR REPLACE PROCEDURE P_COBRO_APLAZADO AS
        ibanCuenta VARCHAR2(20);
        tipoCuenta VARCHAR2(20);
        saldoCuenta FLOAT;
        saldoException EXCEPTION;
        tipoException EXCEPTION;
    BEGIN 
        FOR i IN (SELECT * FROM MOVIMIENTOS WHERE MODOOPERACION NOT LIKE 'DEBITO' AND STATUS LIKE 'PENDIENTE') LOOP
            SELECT CUENTA_IBAN INTO ibanCuenta FROM TARJETA WHERE NUMERO = i.TARJETA_NUMERO;
            SELECT CLASIFICACION INTO tipoCuenta FROM CUENTA_FINTECH WHERE IBAN = ibanCuenta;
            IF tipoCuenta = 'SEGREGADA' THEN
                SELECT CUENTA_REFERENCIA_IBAN INTO ibanCuenta FROM SEGREGADA WHERE IBAN LIKE ibanCuenta;
                SELECT SALDO INTO saldoCuenta FROM CUENTA_REFERENCIA WHERE IBAN LIKE ibanCuenta;
                IF saldoCuenta < i.CANTIDAD THEN
                    RAISE saldoException;
                END IF;
                UPDATE CUENTA_REFERENCIA SET SALDO = saldoCuenta-i.CANTIDAD WHERE IBAN LIKE ibanCuenta;
                UPDATE MOVIMIENTOS SET STATUS = 'COBRADO' WHERE ID LIKE i.ID;
            ELSIF tipoCuenta = 'POOLED' THEN
                SELECT IBAN, SALDO INTO ibanCuenta,saldoCuenta FROM CUENTA_REFERENCIA WHERE IBAN IN (SELECT CUENTA_REFERENCIA_IBAN FROM DEPOSITADA_EN WHERE POOLED_ACCOUNT_IBAN LIKE ibanCuenta) AND DIVISA_ABREVIATURA LIKE i.DIVISA_ABREVIATURA;
                IF saldoCuenta < i.CANTIDAD THEN
                    RAISE saldoException;
                END IF;
                UPDATE CUENTA_REFERENCIA SET SALDO = saldoCuenta-i.CANTIDAD WHERE IBAN LIKE ibanCuenta;
                UPDATE MOVIMIENTOS SET STATUS = 'COBRADO' WHERE ID LIKE i.ID;
            ELSE
                RAISE tipoException;
            END IF;
        END LOOP;  
END;

BEGIN
    DBMS_SCHEDULER.CREATE_JOB (
        job_name => 'J_LIQUIDAR_APLAZADO',
        job_type => 'STORED_PROCEDURE',
        job_action => 'P_COBRO_APLAZADO',
        start_date => sysdate,
        repeat_interval => 'FREQ=MONTHLY;BYMONTHDAY=1;BYHOUR=00',
        end_date => null,
        enabled =>true,
        comments => 'Se ejecuta el primer dia de cada mes');
END;



-- Procedimiento para el apartado b, se encarga de actualizar el valor real
-- de las divisas con relacion al EURO con ayuda de la vista de cotizaciones
CREATE OR REPLACE PROCEDURE P_CAMBIO_EURO AS
    BEGIN 
        FOR i IN (SELECT * FROM V_COTIZACIONES) LOOP
            UPDATE DIVISA SET CAMBIOEURO=i.CAMBIOEURO WHERE ABREVIATURA LIKE i.ABREVIATURA;
        END LOOP;
END;


/*  b) Crear un Job que se denomine J_CAMBIO_EURO que actualice el atributo CambioEuro de la tabla DIVISA
    tomando el valor de la Vista V_COTIZACIONES. 
        - Aplica el procedimiento P_CAMBIO_EURO
        - El job se repeti en un intervalo diario a las las 00:05.
*/
BEGIN
    DBMS_SCHEDULER.CREATE_JOB (
        job_name => 'J_CAMBIO_EURO',
        job_type => 'STORED_PROCEDURE',
        job_action => 'P_CAMBIO_EURO',
        start_date => sysdate,
        repeat_interval => 'FREQ=DAILY;BYHOUR=00;BYMINUTE=5',
        end_date => null,
        enabled =>true,
        comments => 'Se ejecuta cada dia a las 00:05');
END;
