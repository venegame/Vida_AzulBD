CREATE TABLESPACE VidaAzul
DATAFILE 'C:\Oracle\oradata\ORCL\VidaAzul.DBF' SIZE 100M 
AUTOEXTEND ON NEXT 10M MAXSIZE 500M 
EXTENT MANAGEMENT LOCAL;

CREATE USER vidaazul IDENTIFIED BY vidaazul
DEFAULT TABLESPACE VidaAzul
QUOTA UNLIMITED ON VidaAzul;

GRANT CONNECT, RESOURCE TO vidaazul;
GRANT CREATE SESSION TO vidaazul;
GRANT CREATE TABLE TO vidaazul;
GRANT CREATE VIEW TO vidaazul;
GRANT CREATE PROCEDURE TO vidaazul;
GRANT CREATE SEQUENCE TO vidaazul;
GRANT ALL PRIVILEGES TO vidaazul;
GRANT EXECUTE ON DBMS_CRYPTO TO vidaazul;

--Tablas
CREATE TABLE rol (
  id_rol NUMBER(11) NOT NULL,
  nombre_rol VARCHAR2(100) NOT NULL,
  CONSTRAINT pk_rol PRIMARY KEY (id_rol)
);

CREATE TABLE categoria (
  id_categoria NUMBER(11) NOT NULL,
  nombre_categoria VARCHAR2(100) NOT NULL,
  CONSTRAINT pk_categoria PRIMARY KEY (id_categoria)
);

CREATE TABLE usuario (
  id_usuario NUMBER(11) NOT NULL,
  id_rol NUMBER(11),
  nombre_usuario VARCHAR2(100) NOT NULL,
  apellido_usuario VARCHAR2(100) NOT NULL,
  correo VARCHAR2(150) NOT NULL,
  contrasenia VARCHAR2(100) NOT NULL,
  CONSTRAINT pk_usuario PRIMARY KEY (id_usuario),
  CONSTRAINT fk_usuario_rol FOREIGN KEY (id_rol) REFERENCES rol(id_rol)
);

CREATE TABLE comentario (
  id_comentario NUMBER(11) NOT NULL,
  id_usuario NUMBER(11),
  fecha_comentario DATE NOT NULL,
  comentario CLOB NOT NULL,
  CONSTRAINT pk_comentario PRIMARY KEY (id_comentario),
  CONSTRAINT fk_comentario_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE eventos (
  id_evento NUMBER(11) NOT NULL,
  id_categoria NUMBER(11),
  nombre_evento VARCHAR2(100) NOT NULL,
  fecha_evento DATE NOT NULL,
  descripcion CLOB,
  imagen VARCHAR2(255),
  CONSTRAINT pk_eventos PRIMARY KEY (id_evento),
  CONSTRAINT fk_eventos_categoria FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

CREATE TABLE expertos (
  id_experto NUMBER(11) NOT NULL,
  id_categoria NUMBER(11),
  nombre_experto VARCHAR2(100) NOT NULL,
  quienes_somos CLOB,
  historia_expertos CLOB,
  url_instagram VARCHAR2(255),
  url_x VARCHAR2(255),
  url_youtube VARCHAR2(255),
  url_facebook VARCHAR2(255),
  CONSTRAINT pk_expertos PRIMARY KEY (id_experto),
  CONSTRAINT fk_expertos_categoria FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

CREATE TABLE galeria (
  id_imagen NUMBER(11) NOT NULL,
  id_usuario NUMBER(11),
  imagen VARCHAR2(255),
  titulo VARCHAR2(100) NOT NULL,
  CONSTRAINT pk_galeria PRIMARY KEY (id_imagen),
  CONSTRAINT fk_galeria_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE proyecto (
  id_proyecto NUMBER(11) NOT NULL,
  id_usuario NUMBER(11),
  id_categoria NUMBER(11),
  nombre_proyecto VARCHAR2(100) NOT NULL,
  detalle_proyecto CLOB,
  estado_proyecto VARCHAR2(50),
  ruta_imagen VARCHAR2(255) NOT NULL,
  CONSTRAINT pk_proyecto PRIMARY KEY (id_proyecto),
  CONSTRAINT fk_proyecto_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
  CONSTRAINT fk_proyecto_categoria FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

CREATE TABLE recursos (
  id_recurso NUMBER(11) NOT NULL,
  id_categoria NUMBER(11),
  nombre_recurso VARCHAR2(100) NOT NULL,
  descripcion CLOB,
  imagen VARCHAR2(255),
  CONSTRAINT pk_recursos PRIMARY KEY (id_recurso),
  CONSTRAINT fk_recursos_categoria FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

CREATE TABLE transportes (
  id_transporte NUMBER(11) NOT NULL,
  id_usuario NUMBER(11),
  nombre_transporte VARCHAR2(100) NOT NULL,
  ruta_transporte VARCHAR2(255),
  horario_transporte VARCHAR2(100),
  precio_transporte NUMBER(10,2),
  CONSTRAINT pk_transportes PRIMARY KEY (id_transporte),
  CONSTRAINT fk_transportes_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

--Procedimientos almacenados
CREATE OR REPLACE PROCEDURE sp_create_evento(
    p_id_categoria IN eventos.id_categoria%TYPE,
    p_nombre_evento IN eventos.nombre_evento%TYPE,
    p_fecha_evento IN eventos.fecha_evento%TYPE,
    p_descripcion IN eventos.descripcion%TYPE,
    p_imagen IN eventos.imagen%TYPE
) IS
BEGIN
    INSERT INTO eventos (id_categoria, nombre_evento, fecha_evento, descripcion, imagen)
    VALUES (p_id_categoria, p_nombre_evento, p_fecha_evento, p_descripcion, p_imagen);
    COMMIT;
END;

CREATE OR REPLACE PROCEDURE sp_get_evento(
    p_id_evento IN eventos.id_evento%TYPE,
    p_evento OUT SYS_REFCURSOR
) IS
BEGIN
    OPEN p_evento FOR
    SELECT * FROM eventos
    WHERE id_evento = p_id_evento;
END;

CREATE OR REPLACE PROCEDURE sp_update_evento(
    p_id_evento IN eventos.id_evento%TYPE,
    p_id_categoria IN eventos.id_categoria%TYPE,
    p_nombre_evento IN eventos.nombre_evento%TYPE,
    p_fecha_evento IN eventos.fecha_evento%TYPE,
    p_descripcion IN eventos.descripcion%TYPE,
    p_imagen IN eventos.imagen%TYPE
) IS
BEGIN
    UPDATE eventos
    SET id_categoria = p_id_categoria,
        nombre_evento = p_nombre_evento,
        fecha_evento = p_fecha_evento,
        descripcion = p_descripcion,
        imagen = p_imagen
    WHERE id_evento = p_id_evento;
    COMMIT;
END;

CREATE OR REPLACE PROCEDURE sp_delete_evento(
    p_id_evento IN eventos.id_evento%TYPE
) IS
BEGIN
    DELETE FROM eventos
    WHERE id_evento = p_id_evento;
    COMMIT;
END;


--Funciones

SET SERVEROUTPUT ON; 

-- Funcion para obtener usuario por correo
CREATE OR REPLACE FUNCTION FUNC_obtener_usuario_por_correo (
    p_email IN VARCHAR2
) RETURN SYS_REFCURSOR
IS
    v_cursor SYS_REFCURSOR;
BEGIN
    OPEN v_cursor FOR
    SELECT *
    FROM usuario
    WHERE correo = p_email;
    
    RETURN v_cursor;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('No se encontró un usuario con el correo proporcionado.');
        RETURN NULL;
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Ocurrió un error inesperado: ' || SQLERRM);
        RETURN NULL;
END;

-- Funcion para validar el rol del usuario 
CREATE OR REPLACE FUNCTION FUNC_validar_rol_de_usuario (
    p_user_id IN NUMBER,
    p_role_name IN VARCHAR2
) RETURN BOOLEAN
IS
    v_count NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_count
    FROM usuario u
    JOIN rol r ON u.id_rol = r.id_rol
    WHERE u.id_usuario = p_user_id
    AND r.nombre_rol = p_role_name;

    RETURN v_count > 0;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('No se encontró el usuario o el rol.');
        RETURN FALSE;
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Ocurrió un error inesperado: ' || SQLERRM);
        RETURN FALSE;
END;

-- Funcion para obtener proyectos por categoria
CREATE OR REPLACE FUNCTION FUNC_obtener_proyectos_por_categoria (
    p_category_id IN NUMBER
) RETURN SYS_REFCURSOR
IS
    v_cursor SYS_REFCURSOR;
BEGIN
    OPEN v_cursor FOR
    SELECT *
    FROM proyecto
    WHERE id_categoria = p_category_id;

    RETURN v_cursor;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('No se encontraron proyectos para la categoría proporcionada.');
        RETURN NULL;
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Ocurrió un error inesperado: ' || SQLERRM);
        RETURN NULL;
END;

-- Funcion para contar proyectos por usuario 
CREATE OR REPLACE FUNCTION FUNC_contar_proyectos_por_usuario (
    p_user_id IN NUMBER
) RETURN NUMBER
IS
    v_count NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_count
    FROM proyecto
    WHERE id_usuario = p_user_id;

    RETURN v_count;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('No se encontraron proyectos para el usuario proporcionado.');
        RETURN 0;
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Ocurrió un error inesperado: ' || SQLERRM);
        RETURN 0;
END;


-- Funcion para obtener eventos a futuros
CREATE OR REPLACE FUNCTION FUNC_obtener_eventos_proximos (
    p_date IN DATE
) RETURN SYS_REFCURSOR
IS
    v_cursor SYS_REFCURSOR;
BEGIN
    OPEN v_cursor FOR
    SELECT *
    FROM eventos
    WHERE fecha_evento >= p_date;

    RETURN v_cursor;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('No se encontraron eventos para la fecha proporcionada.');
        RETURN NULL;
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Ocurrió un error inesperado: ' || SQLERRM);
        RETURN NULL;
END;


-- Funcion para obtener transporte por id
CREATE OR REPLACE FUNCTION FUNC_obtener_transporte_por_id (
    p_transporte_id IN NUMBER
) RETURN SYS_REFCURSOR
IS
    v_cursor SYS_REFCURSOR;
BEGIN
    OPEN v_cursor FOR
    SELECT *
    FROM transportes
    WHERE id_transporte = p_transporte_id;

    RETURN v_cursor;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('No se encontró transporte con el ID proporcionado.');
        RETURN NULL;
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Ocurrió un error inesperado: ' || SQLERRM);
        RETURN NULL;
END;

-- Funcion para obtener comentarios
CREATE OR REPLACE FUNCTION FUNC_obtener_comentarios (
    p_comentarios_id IN NUMBER
) RETURN SYS_REFCURSOR
IS
    v_cursor SYS_REFCURSOR;
BEGIN
    OPEN v_cursor FOR
    SELECT *
    FROM comentario
    WHERE id_comentario = p_comentarios_id;

    RETURN v_cursor;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('No se encontraron comentarios para el ID proporcionado.');
        RETURN NULL;
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Ocurrió un error inesperado: ' || SQLERRM);
        RETURN NULL;
END;

--Función de insertar comentarios
CREATE OR REPLACE FUNCTION FUNC_insertar_comentario (
    p_usuario_id IN NUMBER,
    p_comentario IN VARCHAR2
) RETURN NUMBER
IS
    v_comentario_id NUMBER;
BEGIN
    INSERT INTO comentario (id_usuario, fecha_comentario, comentario)
    VALUES (p_usuario_id, SYSDATE, p_comentario);
    
    SELECT id_comentario INTO v_comentario_id 
    FROM comentario 
    WHERE ROWNUM = 1 AND id_usuario = p_usuario_id
    ORDER BY fecha_comentario DESC;

    RETURN v_comentario_id;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Ocurrió un error al insertar el comentario: ' || SQLERRM);
        RETURN NULL;
END;


-- Función para cifrar los datos
CREATE OR REPLACE FUNCTION FT_Encrypt_Key (
    p_data IN VARCHAR2
) RETURN RAW IS
    l_key RAW(32) := UTL_RAW.CAST_TO_RAW('1234567890ABCDEF1234567890ABCDEF');
    l_encrypted RAW(32767);
BEGIN
    l_encrypted := DBMS_CRYPTO.ENCRYPT(
        src => UTL_RAW.CAST_TO_RAW(p_data),
        typ => DBMS_CRYPTO.DES_CBC_PKCS5,
        key => l_key
    );
    
    RETURN l_encrypted;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
        RETURN NULL;
END FT_Encrypt_Key;


-- Función para descifrar los datos
CREATE OR REPLACE FUNCTION FT_Decrypt_Key (
    p_encrypted_key IN RAW
) RETURN VARCHAR2 IS
    l_key RAW(32) := UTL_RAW.CAST_TO_RAW('1234567890ABCDEF1234567890ABCDEF');
    l_decrypted RAW(32767);
    l_decrypted_v VARCHAR2(32767);
BEGIN
    l_decrypted := DBMS_CRYPTO.DECRYPT(
        src => p_encrypted_key,
        typ => DBMS_CRYPTO.DES_CBC_PKCS5,
        key => l_key
    );
    
    l_decrypted_v := UTL_RAW.CAST_TO_VARCHAR2(l_decrypted);

    RETURN l_decrypted_v;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
        RETURN NULL;
END FT_Decrypt_Key;


--Triggers
-- Trigger para cifrar la contraseña antes de la inserción en la tabla usuario
CREATE OR REPLACE TRIGGER encrypt_id_paciente
BEFORE INSERT ON usuario
FOR EACH ROW
BEGIN
    :NEW.contrasenia := RAWTOHEX(FT_Encrypt_Key(:NEW.contrasenia));
END;


--Trigger validar inserción de usuario
CREATE OR REPLACE TRIGGER TRG_validar_insercion_usuario
BEFORE INSERT ON usuario
FOR EACH ROW
DECLARE
    v_count NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_count
    FROM usuario
    WHERE correo = :NEW.correo;

    IF v_count > 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'El correo del usuario ya existe.');
    END IF;
END;


--Trigger asegurar si la categoría asociada a un recurso existe antes de permitir 
--la inserción o actualización del recurso
CREATE OR REPLACE TRIGGER TRG_asegurar_integridad_categoria_recurso
BEFORE INSERT OR UPDATE ON recursos
FOR EACH ROW
DECLARE
    v_count NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_count
    FROM categoria
    WHERE id_categoria = :NEW.id_categoria;

    IF v_count = 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'La categoría especificada no existe.');
    END IF;
END;

