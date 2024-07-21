-- Crear tabla Usuario
CREATE TABLE Usuarios (
    id_usuario INT PRIMARY KEY,
    nombre_usuario VARCHAR2(80) NOT NULL,
    apellido_usuario VARCHAR2(80) NOT NULL,
    correo_usuario VARCHAR2(80) NOT NULL,
    contraseña_usuario VARCHAR2(15) NOT NULL,
    id_rol INT);
//FOREIGN KEY (id_rol) REFERENCES Rol(id_rol)

-- Crear tabla Proyecto
CREATE TABLE Proyectos (
    id_proyecto INT PRIMARY KEY,
    id_usuario INT,
    id_categoria INT,
    nombre_proyecto VARCHAR2(80) NOT NULL,
    detalle_proyecto VARCHAR2(200) NOT NULL,
    estado_proyecto VARCHAR2(30) NOT NULL);
//FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario),
//FOREIGN KEY (id_categoria) REFERENCES Categoria(id_categoria)

-- Crear tabla Rol
CREATE TABLE Roles (
    id_rol INT PRIMARY KEY,
    nombre_rol VARCHAR2(50) NOT NULL);
    
-- Crear tabla Categoría
CREATE TABLE Categoria (
    id_categoria INT PRIMARY KEY,
    nombre_categoria VARCHAR2(80) NOT NULL
);

-- Crear tabla Galeria
CREATE TABLE Galeria (
    id_imagen INT PRIMARY KEY,
    id_usuario INT NOT NULL,
    titulo_imagen VARCHAR2(250) NOT NULL,
    imagen_url VARCHAR2(250) NOT NULL);

-- Crear tabla Comentarios
CREATE TABLE Comentarios (
    id_comentario INT PRIMARY KEY,
    id_usuario INT NOT NULL,
    fecha_comentario DATE NOT NULL,
    comentario VARCHAR2(500)
);

-- Crear tabla Expertos
CREATE TABLE Expertos (
    id_experto INT PRIMARY KEY,
    nombre_experto VARCHAR2(80) NOT NULL,
    quienes_somos VARCHAR2(500) NOT NULL,
    historia_expertos VARCHAR2(500) NOT NULL,
    url_instagram VARCHAR2(300),
    url_x VARCHAR2(300),
    url_youtube VARCHAR2(300),
    url_facebook VARCHAR2(300)
);















