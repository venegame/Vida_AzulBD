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
    
