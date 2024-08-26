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

INSERT INTO rol (id_rol, nombre_rol) VALUES (1, 'Administrador');
INSERT INTO rol (id_rol, nombre_rol) VALUES (2, 'Usuario');
INSERT INTO rol (id_rol, nombre_rol) VALUES (3, 'Moderador');
INSERT INTO rol (id_rol, nombre_rol) VALUES (4, 'Editor');
INSERT INTO rol (id_rol, nombre_rol) VALUES (5, 'Voluntario');


CREATE TABLE categoria (
  id_categoria NUMBER(11) NOT NULL,
  nombre_categoria VARCHAR2(100) NOT NULL,
  CONSTRAINT pk_categoria PRIMARY KEY (id_categoria)
);

INSERT INTO categoria (id_categoria, nombre_categoria) VALUES (1, 'Energía Sostenible');
INSERT INTO categoria (id_categoria, nombre_categoria) VALUES (2, 'Gestión de Residuos');
INSERT INTO categoria (id_categoria, nombre_categoria) VALUES (3, 'Conservación de la Biodiversidad');
INSERT INTO categoria (id_categoria, nombre_categoria) VALUES (4, 'Educación Ambiental');
INSERT INTO categoria (id_categoria, nombre_categoria) VALUES (5, 'Voluntariado');
INSERT INTO categoria (id_categoria, nombre_categoria) VALUES (6, 'Green Wolf');
INSERT INTO categoria (id_categoria, nombre_categoria) VALUES (7, 'Costa Rica por Siempre');
INSERT INTO categoria (id_categoria, nombre_categoria) VALUES (8, 'Articulo');
INSERT INTO categoria (id_categoria, nombre_categoria) VALUES (9, 'Curso');

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

INSERT INTO Usuario (id_usuario, id_rol, nombre_usuario, apellido_usuario, correo, contrasenia) VALUES 
  (1, 1, 'admin', 'admin', 'admin@vidaazul.com', 'adminpass');
INSERT INTO Usuario (id_usuario, id_rol, nombre_usuario, apellido_usuario, correo, contrasenia) VALUES 
  (2, 2, 'Sofia', 'Ramirez', 'sofia.ramirez@vidaazul.com', '123');
INSERT INTO Usuario (id_usuario, id_rol, nombre_usuario, apellido_usuario, correo, contrasenia) VALUES 
  (3, 3, 'Luis', 'Hernandez', 'luis.hernandez@vidaazul.com', '123');
INSERT INTO Usuario (id_usuario, id_rol, nombre_usuario, apellido_usuario, correo, contrasenia) VALUES 
  (4, 4, 'Ana', 'Martinez', 'ana.martinez@vidaazul.com', '123');
INSERT INTO Usuario (id_usuario, id_rol, nombre_usuario, apellido_usuario, correo, contrasenia) VALUES 
  (5, 5, 'Carlos', 'Gonzalez', 'carlos.gonzalez@vidaazul.com', '123');

CREATE TABLE comentario (
  id_comentario NUMBER(11) NOT NULL,
  id_usuario NUMBER(11),
  fecha_comentario DATE NOT NULL,
  comentario CLOB NOT NULL,
  CONSTRAINT pk_comentario PRIMARY KEY (id_comentario),
  CONSTRAINT fk_comentario_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

INSERT INTO comentario (id_comentario, id_usuario, fecha_comentario, comentario) VALUES (1, 2, TO_DATE('2024-08-17', 'YYYY-MM-DD'), 'El proyecto de paneles solares ha sido un éxito en la comunidad.');
INSERT INTO comentario (id_comentario, id_usuario, fecha_comentario, comentario) VALUES (2, 3, TO_DATE('2024-08-18', 'YYYY-MM-DD'), 'La iniciativa de reciclaje ha tenido una gran aceptación entre los vecinos.');
INSERT INTO comentario (id_comentario, id_usuario, fecha_comentario, comentario) VALUES (3, 4, TO_DATE('2024-08-19', 'YYYY-MM-DD'), 'La reforestación es crucial para mantener el equilibrio ecológico.');
INSERT INTO comentario (id_comentario, id_usuario, fecha_comentario, comentario) VALUES (4, 5, TO_DATE('2024-08-20', 'YYYY-MM-DD'), 'Las capacitaciones han generado un cambio positivo en los estudiantes.');
INSERT INTO comentario (id_comentario, id_usuario, fecha_comentario, comentario) VALUES (5, 2, TO_DATE('2024-08-21', 'YYYY-MM-DD'), 'El voluntariado en la playa fue una experiencia enriquecedora.');

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

INSERT INTO eventos (id_evento, id_categoria, nombre_evento, fecha_evento, descripcion, imagen) VALUES 
  (1, 1, 'Seminario de Energía Renovable', TO_DATE('2024-09-15', 'YYYY-MM-DD'), 'Evento para discutir las últimas innovaciones en energía renovable', 'https://cdn.inspenet.com/wp-content/uploads/2023/06/04171505/stock-photo-solar-panels-and-wind-turbine.jpeg');
INSERT INTO eventos (id_evento, id_categoria, nombre_evento, fecha_evento, descripcion, imagen) VALUES 
  (2, 2, 'Día del Reciclaje', TO_DATE('2024-10-01', 'YYYY-MM-DD'), 'Evento comunitario para fomentar la cultura del reciclaje', 'https://lh5.googleusercontent.com/proxy/cKzFVse6f_1UIjLl-lW90mEYlpnqQnRIvHLM1HExjmQ6KzEwGpwSSMqK0BKi9vBZX3cViF3CbDqGWefYTyjLEoOD_tm1cMiQ_ZVglg_iuhY6-5OdL0LbUjKT-CCbdYohVSGdqiKcKz-CVwxexO_GsY0M');
INSERT INTO eventos (id_evento, id_categoria, nombre_evento, fecha_evento, descripcion, imagen) VALUES 
  (3, 3, 'Jornada de Reforestación', TO_DATE('2024-08-25', 'YYYY-MM-DD'), 'Evento para plantar árboles en áreas desforestadas', 'https://www.bcie.org/fileadmin/_processed_/f/d/csm_REFORESTAcr24_59d0626f06.jpg');
INSERT INTO eventos (id_evento, id_categoria, nombre_evento, fecha_evento, descripcion, imagen) VALUES 
  (4, 4, 'Feria Ambiental Escolar', TO_DATE('2024-11-10', 'YYYY-MM-DD'), 'Feria educativa para concientizar a los estudiantes sobre temas ambientales', 'https://www.tec.ac.cr/hoyeneltec/sites/default/files/styles/colorbox/public/media/img/paragraph/invitacion_personalizada-02.png');
INSERT INTO eventos (id_evento, id_categoria, nombre_evento, fecha_evento, descripcion, imagen) VALUES 
  (5, 5, 'Día Internacional del Voluntariado', TO_DATE('2024-12-05', 'YYYY-MM-DD'), 'Celebración y reconocimiento a los voluntarios que han participado en los proyectos', 'https://portal.andina.pe/EDPfotografia3/Thumbnail/2022/12/04/000916870W.jpg');

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

INSERT INTO expertos (id_experto, id_categoria, nombre_experto, quienes_somos, historia_expertos, url_instagram, url_x, url_youtube, url_facebook) VALUES 
  (1, 6, 'Green Wolf Costa Rica', 'Green Wolf Costa Rica es un movimiento integral, inclusivo y sostenible, que busca la recuperación socio-ecosistémica de Costa Rica a través de la acción y alianzas intersectoriales.', 'Nacemos a partir de la preocupación de nuestro fundador, Ellian Villalobos, por la creciente contaminación de los ecosistemas costarricenses. Por esto, un 15 de diciembre de 2018 funda Green Wolf Costa Rica.', 'https://www.instagram.com/greenwolfcr/', 'https://x.com/greenwolfcr?lang=en', 'https://www.youtube.com/channel/UC6NRa0FDOb3pEx7xmX5P9fQ', 'https://www.facebook.com/GreenWolfCR');
INSERT INTO expertos (id_experto, id_categoria, nombre_experto, quienes_somos, historia_expertos, url_instagram, url_x, url_youtube, url_facebook) VALUES 
  (2, 7, 'Asociación Costa Rica por Siempre', 'Somos la Asociación Costa Rica por Siempre, una organización no gubernamental de carácter privado, creada en el 2010 como el segundo PFP del mundo, un modelo de financiamiento de proyectos para la permanencia (PFP).\r\n\r\nNos dedicamos a gestionar, invertir y movilizar recursos de Gobiernos, organismos internacionales y fundaciones privadas que buscan la conservación de la biodiversidad.', 'Nacimos bajo una alianza público-privada para apoyar al país en cumplir las metas del Convención de Diversidad Biológica (CDB) de las Naciones Unidas.', 'https://www.instagram.com/costaricaporsiempre/', 'https://x.com/CRporSiempre', 'https://www.youtube.com/channel/UCnpLXRSOKto1pOUxM5cbOQw', 'https://www.facebook.com/ACRXS');
  
CREATE TABLE galeria (
  id_imagen NUMBER(11) NOT NULL,
  id_usuario NUMBER(11),
  imagen VARCHAR2(255),
  titulo VARCHAR2(100) NOT NULL,
  CONSTRAINT pk_galeria PRIMARY KEY (id_imagen),
  CONSTRAINT fk_galeria_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

INSERT INTO galeria (id_imagen, id_usuario, imagen, titulo) VALUES 
  (1, 2, 'https://www.infobae.com/new-resizer/Ntef1OEt7AsZhUpqmF_iNBbwapo=/1200x900/filters:format(webp):quality(85)/s3.amazonaws.com/arc-wordpress-client-uploads/infobae-wp/wp-content/uploads/2019/05/31160504/DEF-Paneles-solares-escuelas-rurales-Portada.jpg', 'Instalación de Paneles Solares en Escuela Rural');
INSERT INTO galeria (id_imagen, id_usuario, imagen, titulo) VALUES 
  (2, 3, 'https://www.munitucapel.cl/include/images/news/gallery/988/_000000988_8768225dba_Tucapel.jpg', 'Punto de Reciclaje Comunitario');
INSERT INTO galeria (id_imagen, id_usuario, imagen, titulo) VALUES 
  (3, 4, 'https://loaizacomunicaciones.com/wp-content/uploads/grupo-difare-contribuye-a-la-reforestacion-de-areas-protegidas.jpg', 'Reforestación de Zonas Protegidas');
INSERT INTO galeria (id_imagen, id_usuario, imagen, titulo) VALUES 
  (4, 5, 'https://www.pactomundial.org/wp-content/uploads/2023/07/Post_Wordpress_-_1280_x_720-_4_-1024x576.webp', 'Capacitación sobre Sostenibilidad');
INSERT INTO galeria (id_imagen, id_usuario, imagen, titulo) VALUES 
  (5, 2, 'https://img.global.news.samsung.com/latin/wp-content/uploads/2018/10/VOL_12.jpg', 'Voluntariado de Limpieza de Playas');

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

INSERT INTO proyecto (id_proyecto, id_usuario, id_categoria, nombre_proyecto, detalle_proyecto, estado_proyecto, ruta_imagen) VALUES 
  (1, 2, 1, 'Instalación de Paneles Solares en Escuelas Rurales', 'Este proyecto busca implementar paneles solares en 10 escuelas rurales del país', 'En Progreso', 'https://www.ciperchile.cl/wp-content/uploads/salas-e1694193929812.png');
INSERT INTO proyecto (id_proyecto, id_usuario, id_categoria, nombre_proyecto, detalle_proyecto, estado_proyecto, ruta_imagen) VALUES 
  (2, 3, 2, 'Reciclaje Comunitario', 'Iniciativa para establecer puntos de reciclaje en comunidades urbanas', 'Completado', 'https://www.larepublica.net/storage/images/2021/04/06/20210406083621.1200.jpg');
INSERT INTO proyecto (id_proyecto, id_usuario, id_categoria, nombre_proyecto, detalle_proyecto, estado_proyecto, ruta_imagen) VALUES 
  (3, 4, 3, 'Reforestación en Zonas Protegidas', 'Reforestación de 100 hectáreas en áreas protegidas', 'Completado', 'https://www.sanisidro.gob.ar/sites/default/files/img/limpieza_en_el_rio_2.jpg');
INSERT INTO proyecto (id_proyecto, id_usuario, id_categoria, nombre_proyecto, detalle_proyecto, estado_proyecto, ruta_imagen) VALUES 
  (4, 5, 4, 'Capacitaciones en Escuelas', 'Charlas y talleres sobre sostenibilidad para estudiantes de primaria y secundaria', 'En Progreso', 'https://encolombia.com/wp-content/uploads/2019/11/importancia-de-cuidar-el-medio-ambiente-696x320.jpg');
INSERT INTO proyecto (id_proyecto, id_usuario, id_categoria, nombre_proyecto, detalle_proyecto, estado_proyecto, ruta_imagen) VALUES 
  (5, 2, 5, 'Voluntariado de Limpieza de Playas', 'Proyecto para organizar grupos de voluntarios que limpien playas en la costa', 'Completado', 'https://www.undp.org/sites/g/files/zskgke326/files/2023-09/playas.jpg');

CREATE TABLE recursos (
  id_recurso NUMBER(11) NOT NULL,
  id_categoria NUMBER(11),
  nombre_recurso VARCHAR2(100) NOT NULL,
  descripcion CLOB,
  imagen VARCHAR2(255),
  CONSTRAINT pk_recursos PRIMARY KEY (id_recurso),
  CONSTRAINT fk_recursos_categoria FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
);

INSERT INTO recursos (id_recurso, id_categoria, nombre_recurso, descripcion, imagen) VALUES 
  (1, 8, 'Paneles Solares', 'Guía completa sobre la instalación y mantenimiento de paneles solares' || CHR(10) || CHR(10) ||
  'Introducción' || CHR(10) ||
  'Los paneles solares convierten la luz del sol en electricidad, ofreciendo una fuente de energía limpia y sostenible. Esta guía te ayudará a entender cómo instalar y mantener tu sistema solar.' || CHR(10) || CHR(10) ||
  'Tipos de Paneles Solares' || CHR(10) ||
  'Monocristalinos: Alta eficiencia, mayor costo.' || CHR(10) ||
  'Policristalinos: Menor costo, eficiencia moderada.' || CHR(10) ||
  'Capa Fina: Flexible y ligero, pero menos eficiente.' || CHR(10) || CHR(10) ||
  'Instalación' || CHR(10) ||
  'Evaluación: Asegúrate de que el área tenga buena exposición al sol.' || CHR(10) ||
  'Cálculo de Energía: Revisa tus facturas eléctricas para dimensionar el sistema.' || CHR(10) ||
  'Permisos: Verifica requisitos locales.' || CHR(10) ||
  'Pasos de Instalación' || CHR(10) ||
  'Montaje de Estructuras: Fija las estructuras al techo o suelo.' || CHR(10) ||
  'Instalación de Paneles: Coloca los paneles sobre las estructuras.' || CHR(10) ||
  'Cableado: Conecta los paneles al inversor y, si es necesario, a la red eléctrica.' || CHR(10) ||
  'Configuración: Ajusta el inversor y el medidor para monitorear la producción.' || CHR(10) || CHR(10) ||
  'Mantenimiento' || CHR(10) ||
  'Limpieza: Limpia los paneles una o dos veces al año.' || CHR(10) ||
  'Inspección Visual: Revisa los paneles y cables para detectar daños.' || CHR(10) ||
  'Monitoreo: Usa aplicaciones para verificar el rendimiento del sistema.' || CHR(10) ||
  'Revisión Profesional: Realiza una inspección anual para asegurar el funcionamiento óptimo.' || CHR(10) || CHR(10) ||
  'Conclusión' || CHR(10) ||
  'Con una instalación adecuada y un mantenimiento regular, los paneles solares pueden ofrecer energía limpia y ahorrar en tus facturas de electricidad durante más de 25 años.', 
  'https://upload.wikimedia.org/wikipedia/commons/2/2c/Fixed_Tilt_Solar_panel_at_Canterbury_Municipal_Building_Canterbury_New_Hampshire.jpg');
INSERT INTO recursos (id_recurso, id_categoria, nombre_recurso, descripcion, imagen) VALUES 
  (2, 9, 'Reciclaje en Casa', 
  'Consejos prácticos para implementar un sistema de reciclaje en el hogar' || CHR(10) || CHR(10) ||
  'Introducción' || CHR(10) ||
  'El reciclaje en casa es una forma efectiva de reducir residuos y contribuir al cuidado del medio ambiente. Esta guía te ayudará a empezar con el reciclaje en tu hogar.' || CHR(10) || CHR(10) ||
  '¿Qué se Puede Reciclar?' || CHR(10) ||
  'Papel y Cartón: Incluye periódicos, revistas, cajas y envases de cartón.' || CHR(10) ||
  'Plásticos: Botellas, envases y bolsas plásticas. Verifica los símbolos de reciclaje.' || CHR(10) ||
  'Vidrio: Botellas y frascos de vidrio. Enjuaga antes de reciclar.' || CHR(10) ||
  'Metales: Latas de alimentos y bebidas. Aplasta las latas para ahorrar espacio.' || CHR(10) || CHR(10) ||
  'Cómo Empezar' || CHR(10) ||
  'Selecciona Contenedores: Usa contenedores separados para papel, plásticos, vidrio y metales.' || CHR(10) ||
  'Clasifica los Residuos: Asegúrate de separar los materiales reciclables de los no reciclables.' || CHR(10) ||
  'Limpia los Recipientes: Enjuaga los envases para evitar contaminantes.' || CHR(10) || CHR(10) ||
  'Consejos para el Reciclaje' || CHR(10) ||
  'Infórmate: Revisa las normativas locales sobre qué materiales se pueden reciclar.' || CHR(10) ||
  'Reduce y Reutiliza: Antes de reciclar, considera reducir tu consumo y reutilizar materiales.' || CHR(10) ||
  'Educación Familiar: Enseña a todos en casa cómo clasificar y manejar los residuos.' || CHR(10) || CHR(10) ||
  'Manejo de Residuos Especiales' || CHR(10) ||
  'Electrónicos: Lleva a puntos de recogida especializados.' || CHR(10) ||
  'Pilas y Baterías: Llévalas a centros de reciclaje específicos.' || CHR(10) ||
  'Residuos Peligrosos: Sigue las instrucciones locales para la disposición de productos químicos y medicamentos.' || CHR(10) || CHR(10) ||
  'Conclusión' || CHR(10) ||
  'Reciclar en casa es sencillo y ayuda a reducir el impacto ambiental. Con un poco de organización y educación, puedes contribuir significativamente al cuidado del planeta.', 
  'https://www.bbva.com/wp-content/uploads/2021/04/como-reciclar-casa-sostenibilidad-reciclaje-bbva-1024x629.jpg');
INSERT INTO recursos (id_recurso, id_categoria, nombre_recurso, descripcion, imagen) VALUES 
  (3, 8, 'Árboles Nativos', 
  'Importancia de la siembra de árboles nativos para la biodiversidad local' || CHR(10) || CHR(10) ||
  'Introducción' || CHR(10) ||
  'La siembra de árboles nativos es crucial para mantener y promover la biodiversidad local. Estos árboles, adaptados a las condiciones ambientales, ofrecen numerosos beneficios ecológicos y ambientales.' || CHR(10) || CHR(10) ||
  'Beneficios de los Árboles Nativos' || CHR(10) ||
  'Hábitat Natural: Proporcionan refugio y alimento a especies locales de fauna y flora.' || CHR(10) ||
  'Adaptación Local: Están mejor adaptados a las condiciones del suelo y clima, reduciendo la necesidad de riego y fertilizantes.' || CHR(10) ||
  'Control de Erosión: Sus raíces ayudan a estabilizar el suelo y prevenir la erosión.' || CHR(10) ||
  'Regulación del Clima: Contribuyen a la regulación de la temperatura y la calidad del aire al absorber dióxido de carbono.' || CHR(10) || CHR(10) ||
  'Cómo Contribuyen a la Biodiversidad' || CHR(10) ||
  'Sostenimiento de Ecosistemas: Los árboles nativos mantienen la estructura y función de los ecosistemas locales.' || CHR(10) ||
  'Polinización y Dispersión de Semillas: Atraen polinizadores y animales que ayudan a dispersar semillas, promoviendo la regeneración del bosque.' || CHR(10) ||
  'Interacciones Ecológicas: Fomentan relaciones simbióticas entre plantas, animales y microorganismos.' || CHR(10) || CHR(10) ||
  'Cómo Plantar Árboles Nativos' || CHR(10) ||
  'Selecciona Especies Adecuadas: Elige árboles que sean nativos de tu región y adecuados para el tipo de suelo y clima.' || CHR(10) ||
  'Preparación del Terreno: Asegúrate de que el área esté libre de malas hierbas y tenga un buen drenaje.' || CHR(10) ||
  'Plantación: Planta en la temporada adecuada y sigue las mejores prácticas de plantación para asegurar el crecimiento saludable.' || CHR(10) || CHR(10) ||
  'Consejos Adicionales' || CHR(10) ||
  'Educación y Conciencia: Informa a la comunidad sobre la importancia de los árboles nativos y promueve su siembra.' || CHR(10) ||
  'Mantenimiento: Proporciona cuidados básicos como riego y protección contra plagas durante los primeros años.' || CHR(10) ||
  'Participación Comunitaria: Organiza eventos de plantación comunitaria para involucrar a más personas en la conservación.' || CHR(10) || CHR(10) ||
  'Conclusión' || CHR(10) ||
  'La siembra de árboles nativos es fundamental para preservar la biodiversidad local y mejorar la salud ambiental. Contribuye a un ecosistema equilibrado y sostenible, beneficiando a la flora y fauna de tu área.', 
  'https://cordis.europa.eu/docs/news/images/2019-07/131589.jpg');
INSERT INTO recursos (id_recurso, id_categoria, nombre_recurso, descripcion, imagen) VALUES 
  (4, 9, 'Charlas Educativas', 
  'Accede a charlas y talleres sobre sostenibilidad ambiental' || CHR(10) || CHR(10) ||
  'Introducción' || CHR(10) ||
  'Participar en charlas y talleres sobre sostenibilidad ambiental es una excelente manera de aprender más sobre prácticas ecológicas y cómo contribuir al cuidado del planeta. Esta guía te ayudará a encontrar y acceder a estos eventos educativos.' || CHR(10) || CHR(10) ||
  '¿Dónde Encontrar Charlas y Talleres?' || CHR(10) ||
  'Eventos Locales: Revisa la agenda de eventos de tu municipio o comunidad. A menudo, se organizan talleres en centros comunitarios o escuelas.' || CHR(10) ||
  'Organizaciones Ambientales: Muchas ONGs y grupos de conservación ofrecen charlas y talleres. Consulta sus sitios web o redes sociales.' || CHR(10) ||
  'Universidades y Centros de Investigación: Las instituciones académicas suelen organizar eventos educativos sobre sostenibilidad.' || CHR(10) ||
  'Plataformas Online: Explora sitios web y aplicaciones dedicados a eventos educativos, como Eventbrite o Meetup, para encontrar talleres virtuales y presenciales.' || CHR(10) || CHR(10) ||
  'Cómo Participar' || CHR(10) ||
  'Regístrate con Anticipación: Asegúrate de inscribirte con anticipación para asegurar tu lugar en el evento.' || CHR(10) ||
  'Verifica el Formato: Algunos talleres son presenciales, mientras que otros se realizan en línea. Asegúrate de conocer el formato y los requisitos.' || CHR(10) ||
  'Prepárate: Investiga el tema del taller y prepara preguntas o temas de interés para aprovechar al máximo la experiencia.' || CHR(10) || CHR(10) ||
  'Beneficios de Participar' || CHR(10) ||
  'Conocimiento Actualizado: Obtén información actualizada sobre las últimas tendencias y prácticas en sostenibilidad ambiental.' || CHR(10) ||
  'Red de Contactos: Conecta con profesionales y otros interesados en el tema, lo que puede abrir oportunidades para colaborar en proyectos.' || CHR(10) ||
  'Habilidades Prácticas: Aprende técnicas y estrategias prácticas que puedes aplicar en tu vida diaria o en tu comunidad.' || CHR(10) || CHR(10) ||
  'Consejos Adicionales' || CHR(10) ||
  'Participa Activamente: Haz preguntas y participa en discusiones para obtener el mayor beneficio del taller.' || CHR(10) ||
  'Aplica lo Aprendido: Implementa las estrategias y conocimientos adquiridos en tu vida cotidiana o en proyectos comunitarios.' || CHR(10) ||
  'Comparte la Información: Difunde lo aprendido con amigos, familiares y colegas para promover prácticas sostenibles en tu entorno.' || CHR(10) || CHR(10) ||
  'Conclusión' || CHR(10) ||
  'Acceder a charlas y talleres sobre sostenibilidad ambiental te proporciona herramientas valiosas para contribuir al cuidado del medio ambiente. Aprovecha estas oportunidades para educarte y hacer una diferencia positiva en tu comunidad.', 
  'https://accionsocial.ucr.ac.cr/sites/default/files/noticia/imagenes-portada/2019-05/img_0472.jpg');
INSERT INTO recursos (id_recurso, id_categoria, nombre_recurso, descripcion, imagen) VALUES 
  (5, 8, 'Voluntariados Activos', 
  'Únete a los proyectos de voluntariado en tu comunidad' || CHR(10) || CHR(10) ||
  'Introducción' || CHR(10) ||
  'Participar en proyectos de voluntariado es una forma efectiva de contribuir al bienestar de tu comunidad y hacer una diferencia positiva. Esta guía te ayudará a encontrar y unirte a oportunidades de voluntariado en tu área.' || CHR(10) || CHR(10) ||
  '¿Dónde Encontrar Proyectos de Voluntariado?' || CHR(10) ||
  'Organizaciones Locales: Consulta con ONGs, centros comunitarios y asociaciones locales que suelen tener programas de voluntariado.' || CHR(10) ||
  'Redes Sociales y Sitios Web: Plataformas como Facebook, LinkedIn y sitios web de voluntariado (como Idealist o VolunteerMatch) ofrecen listados de proyectos y oportunidades.' || CHR(10) ||
  'Eventos Comunitarios: Asiste a eventos locales para conocer a organizadores de proyectos y obtener información sobre oportunidades de voluntariado.' || CHR(10) ||
  'Instituciones Educativas y Empresas: Muchas universidades y empresas tienen programas de voluntariado y pueden ofrecer oportunidades o recursos para involucrarte.' || CHR(10) || CHR(10) ||
  'Cómo Unirte a un Proyecto' || CHR(10) ||
  'Investiga las Oportunidades: Examina las diferentes opciones disponibles y elige proyectos que se alineen con tus intereses y habilidades.' || CHR(10) ||
  'Contacta a los Organizadores: Ponte en contacto con las organizaciones para obtener más detalles sobre los proyectos y el proceso de inscripción.' || CHR(10) ||
  'Completa el Registro: Sigue el proceso de inscripción que te indiquen, que puede incluir formularios, entrevistas o capacitación previa.' || CHR(10) ||
  'Participa en la Capacitación: Si el proyecto requiere capacitación, asegúrate de asistir para estar bien preparado.' || CHR(10) || CHR(10) ||
  'Beneficios de Voluntariado' || CHR(10) ||
  'Impacto Positivo: Contribuye al bienestar de tu comunidad y ayuda a resolver problemas locales.' || CHR(10) ||
  'Desarrollo Personal: Adquiere nuevas habilidades, experiencias y perspectivas mientras trabajas en proyectos significativos.' || CHR(10) ||
  'Red de Contactos: Conoce a personas con intereses similares y construye relaciones valiosas en tu comunidad.' || CHR(10) || CHR(10) ||
  'Consejos Adicionales' || CHR(10) ||
  'Compromiso y Puntualidad: Sé puntual y cumple con los compromisos para maximizar tu impacto y mantener una buena relación con los organizadores.' || CHR(10) ||
  'Comunicación: Mantén una comunicación abierta con los coordinadores del proyecto para resolver dudas y adaptar tu participación si es necesario.' || CHR(10) ||
  'Comparte tu Experiencia: Anima a otros a unirse a proyectos de voluntariado y comparte tus experiencias para inspirar a más personas.' || CHR(10) || CHR(10) ||
  'Conclusión' || CHR(10) ||
  'Unirte a proyectos de voluntariado en tu comunidad no solo beneficia a quienes reciben tu ayuda, sino que también enriquece tu vida personal y profesional. Aprovecha estas oportunidades para hacer una diferencia y fortalecer tu conexión con tu entorno local.', 
  'https://educowebmedia.blob.core.windows.net/educowebmedia/educospain/media/images/blog/manos-voluntariado.jpg'); 

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

INSERT INTO transportes (id_transporte, id_usuario, nombre_transporte, ruta_transporte, horario_transporte, precio_transporte) VALUES 
  (1, 2, 'Autobús Sostenible', 'Ruta A - Centro de la Ciudad a la Zona Verde', '06:00 - 18:00', 500);
INSERT INTO transportes (id_transporte, id_usuario, nombre_transporte, ruta_transporte, horario_transporte, precio_transporte) VALUES 
  (2, 3, 'Carro Eléctrico Compartido', 'Ruta B - Estación Central a la Playa', '08:00 - 20:00', 1600);
INSERT INTO transportes (id_transporte, id_usuario, nombre_transporte, ruta_transporte, horario_transporte, precio_transporte) VALUES 
  (3, 4, 'Bicicleta Comunitaria', 'Ruta C - Parque Central a la Universidad', 'Todo el día', 1200);
INSERT INTO transportes (id_transporte, id_usuario, nombre_transporte, ruta_transporte, horario_transporte, precio_transporte) VALUES 
  (4, 5, 'Tren Solar', 'Ruta D - Suburbio al Centro de la Ciudad', '07:00 - 19:00', 1100);
INSERT INTO transportes (id_transporte, id_usuario, nombre_transporte, ruta_transporte, horario_transporte, precio_transporte) VALUES 
  (5, 2, 'Autobús de Biodiésel', 'Ruta E - Zona Rural a la Plaza Principal', '05:00 - 17:00', 750);

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

--SPs para usuario

CREATE OR REPLACE PROCEDURE SP_INGRESAR_USUARIO (
    IDROL IN NUMBER,
    NOMBREUSUARIO IN VARCHAR2,
    APELLIDOUSUARIO IN VARCHAR2,
    CORREO IN VARCHAR2,
    CONTRASENIA IN VARCHAR2
) AS
BEGIN
    INSERT INTO USUARIO (id_rol, nombre_usuario, apellido_usuario, correo, contrasenia)
    VALUES (IDROL, NOMBREUSUARIO, APELLIDOUSUARIO, CORREO, CONTRASENIA);
    COMMIT;
END SP_INGRESAR_USUARIO;

CREATE SEQUENCE USUARIOSECUENCIA
START WITH 6
INCREMENT BY 1
NOCACHE
NOCYCLE;

CREATE OR REPLACE TRIGGER AUTOINCREMENTUSUARIOS
BEFORE INSERT ON USUARIO
FOR EACH ROW
BEGIN
    IF :NEW.ID_USUARIO IS NULL THEN
        SELECT USUARIOSECUENCIA.NEXTVAL INTO :NEW.ID_USUARIO FROM DUAL;
    END IF;
END;

CREATE OR REPLACE PROCEDURE SP_LEER_USUARIO (
    IDUSUARIO IN USRVIDA_AZUL.USUARIO.ID_USUARIO%TYPE,
    IDROL OUT USRVIDA_AZUL.USUARIO.ID_ROL%TYPE,
    NOMBREUSUARIO OUT USRVIDA_AZUL.USUARIO.NOMBRE_USUARIO%TYPE,
    APELLIDOUSUARIO OUT USRVIDA_AZUL.USUARIO.APELLIDO_USUARIO%TYPE,
    CORREOUSUARIO OUT USRVIDA_AZUL.USUARIO.CORREO%TYPE,
    CONTRASENIAUSUARIO OUT USRVIDA_AZUL.USUARIO.CONTRASENIA%TYPE
) IS
BEGIN
    SELECT ID_ROL, NOMBRE_USUARIO, APELLIDO_USUARIO, CORREO, CONTRASENIA
    INTO IDROL, NOMBREUSUARIO, APELLIDOUSUARIO, CORREOUSUARIO, CONTRASENIAUSUARIO
    FROM USRVIDA_AZUL.USUARIO
    WHERE ID_USUARIO = IDUSUARIO;
END SP_LEER_USUARIO;

CREATE OR REPLACE PROCEDURE SP_ACTUALIZAR_USUARIO (
    IDUSUARIO IN NUMBER,
    NOMBREUSUARIO     IN VARCHAR2,
    APELLIDOUSUARIO   IN VARCHAR2,
    CORREOUSUARIO IN VARCHAR2,
    CONTRASENIAUSUARIO IN VARCHAR2,
    IDROL             IN NUMBER
)
AS
BEGIN
    UPDATE USUARIO
    SET NOMBRE_USUARIO = NOMBREUSUARIO,
        APELLIDO_USUARIO = APELLIDOUSUARIO,
        CORREO = CORREOUSUARIO,
        CONTRASENIA = CONTRASENIAUSUARIO,
        ID_ROL = IDROL
    WHERE ID_USUARIO = IDUSUARIO;
    COMMIT;
END;

CREATE OR REPLACE PROCEDURE SP_ELIMINAR_USUARIO (
    IDUSUARIO IN NUMBER
) AS
BEGIN
    DELETE FROM USUARIO
    WHERE ID_USUARIO = IDUSUARIO;
    COMMIT;
END SP_ELIMINAR_USUARIO;

--SPs para roles

CREATE OR REPLACE PROCEDURE SP_INGRESAR_ROL (
    IDROL IN NUMBER,
    NOMBRERROL IN VARCHAR2
) AS
BEGIN
    INSERT INTO ROL (nombre_rol)
    VALUES (NOMBRERROL);
    COMMIT;
END SP_INGRESAR_ROL;

CREATE SEQUENCE ROLESSECUENCIA
START WITH 6
INCREMENT BY 1
NOCACHE
NOCYCLE;

CREATE OR REPLACE TRIGGER AUTOINCREMENTROLES
BEFORE INSERT ON ROL
FOR EACH ROW
BEGIN
    IF :NEW.ID_ROL IS NULL THEN
        SELECT ROLESSECUENCIA.NEXTVAL INTO :NEW.ID_ROL FROM DUAL;
    END IF;
END;

CREATE OR REPLACE PROCEDURE SP_LEER_ROL (
    IDROL IN USRVIDA_AZUL.ROL.ID_ROL%TYPE,
    NOMBREROL OUT USRVIDA_AZUL.ROL.NOMBRE_ROL%TYPE
) IS
BEGIN
    SELECT NOMBRE_ROL
    INTO NOMBREROL
    FROM USRVIDA_AZUL.ROL
    WHERE ID_ROL = IDROL;
END SP_LEER_ROL;

CREATE OR REPLACE PROCEDURE SP_ACTUALIZAR_ROL (
    IDROL IN NUMBER,
    NOMBREROL IN VARCHAR2
)
AS
BEGIN
    UPDATE ROL
    SET NOMBRE_ROL = NOMBREROL
    WHERE ID_ROL = IDROL;
    COMMIT;
END;

CREATE OR REPLACE PROCEDURE SP_ELIMINAR_ROL (
    IDROL IN NUMBER
) AS
BEGIN
    DELETE FROM ROL
    WHERE ID_ROL = IDROL;
    COMMIT;
END SP_ELIMINAR_ROL;

--SPs para Proyecto

CREATE OR REPLACE PROCEDURE SP_INGRESAR_PROYECTO (
    IDPROYECTO IN NUMBER,
    IDUSUARIO IN VARCHAR2,
    IDCATEGORIA IN VARCHAR2,
    NOMBREPROYECTO IN VARCHAR2,
    DETALLEPROYECTO IN VARCHAR2,
    ESTADOPROYECTO IN VARCHAR2,
    RUTAIMAGEN IN VARCHAR2
) AS
BEGIN
    INSERT INTO PROYECTO (ID_PROYECTO, ID_USUARIO, ID_CATEGORIA, NOMBRE_PROYECTO, DETALLE_PROYECTO, ESTADO_PROYECTO, RUTA_IMAGEN)
    VALUES (IDPROYECTO, IDUSUARIO, IDCATEGORIA, NOMBREPROYECTO, DETALLEPROYECTO, ESTADOPROYECTO, RUTAIMAGEN);
    COMMIT;
END SP_INGRESAR_PROYECTO;

CREATE SEQUENCE PROYECTOSSECUENCIA
START WITH 6
INCREMENT BY 1
NOCACHE
NOCYCLE;

CREATE OR REPLACE TRIGGER AUTOINCREMENTPROYECTOS
BEFORE INSERT ON PROYECTO
FOR EACH ROW
BEGIN
    IF :NEW.ID_PROYECTO IS NULL THEN
        SELECT PROYECTOSSECUENCIA.NEXTVAL INTO :NEW.ID_PROYECTO FROM DUAL;
    END IF;
END;

CREATE OR REPLACE PROCEDURE SP_LEER_PROYECTO (
    IDPROYECTO IN USRVIDA_AZUL.PROYECTO.ID_PROYECTO%TYPE,
    IDUSUARIO OUT USRVIDA_AZUL.PROYECTO.ID_USUARIO%TYPE,
    IDCATEGORIA OUT USRVIDA_AZUL.PROYECTO.ID_CATEGORIA%TYPE,
    NOMBREPROYECTO OUT USRVIDA_AZUL.PROYECTO.NOMBRE_PROYECTO%TYPE,
    DETALLEPROYECTO OUT USRVIDA_AZUL.PROYECTO.DETALLE_PROYECTO%TYPE,
    ESTADOPROYECTO OUT USRVIDA_AZUL.PROYECTO.ESTADO_PROYECTO%TYPE,
    RUTAIMAGEN OUT USRVIDA_AZUL.PROYECTO.RUTA_IMAGEN%TYPE
) IS
BEGIN
    SELECT ID_USUARIO, ID_CATEGORIA, NOMBRE_PROYECTO, DETALLE_PROYECTO, ESTADO_PROYECTO, RUTA_IMAGEN
    INTO IDUSUARIO, IDCATEGORIA, NOMBREPROYECTO, DETALLEPROYECTO, ESTADOPROYECTO, RUTAIMAGEN
    FROM USRVIDA_AZUL.PROYECTO
    WHERE ID_PROYECTO = IDPROYECTO;
END SP_LEER_PROYECTO;

CREATE OR REPLACE PROCEDURE SP_ACTUALIZAR_PROYECTO (
    IDPROYECTO IN USRVIDA_AZUL.PROYECTO.ID_PROYECTO%TYPE,
    IDUSUARIO OUT USRVIDA_AZUL.PROYECTO.ID_USUARIO%TYPE,
    IDCATEGORIA OUT USRVIDA_AZUL.PROYECTO.ID_CATEGORIA%TYPE,
    NOMBREPROYECTO OUT USRVIDA_AZUL.PROYECTO.NOMBRE_PROYECTO%TYPE,
    DETALLEPROYECTO OUT USRVIDA_AZUL.PROYECTO.DETALLE_PROYECTO%TYPE,
    ESTADOPROYECTO OUT USRVIDA_AZUL.PROYECTO.ESTADO_PROYECTO%TYPE,
    RUTAIMAGEN OUT USRVIDA_AZUL.PROYECTO.RUTA_IMAGEN%TYPE
)
AS
BEGIN
    UPDATE PROYECTO
    SET ID_USUARIO = IDUSUARIO,
        ID_CATEGORIA = IDCATEGORIA,
        NOMBRE_PROYECTO = NOMBREPROYECTO,
        DETALLE_PROYECTO = DETALLEPROYECTO,
        ESTADO_PROYECTO = ESTADOPROYECTO,
        RUTA_IMAGEN = RUTAIMAGEN
    WHERE ID_PROYECTO = IDPROYECTO;
    COMMIT;
END;

CREATE OR REPLACE PROCEDURE SP_ELIMINAR_PROYECTO (
    IDPROYECTO IN NUMBER
) AS
BEGIN
    DELETE FROM PROYECTO
    WHERE ID_PROYECTO = IDPROYECTO;
    COMMIT;
END SP_ELIMINAR_PROYECTO;

-- SPs Recursos

CREATE OR REPLACE PROCEDURE USRVIDA_AZUL.SP_OBTENER_RECURSOS (p_recursos OUT SYS_REFCURSOR) AS
BEGIN
    OPEN p_recursos FOR
    SELECT * FROM USRVIDA_AZUL.RECURSOS;
END;

CREATE OR REPLACE PROCEDURE SP_LEER_RECURSO (
    IDRECURSO IN USRVIDA_AZUL.RECURSOS.ID_RECURSO%TYPE,
    IDCATEGORIA OUT USRVIDA_AZUL.RECURSOS.ID_CATEGORIA%TYPE,
    NOMBRERECURSO OUT USRVIDA_AZUL.RECURSOS.NOMBRE_RECURSO%TYPE,
    DESCRIPCIONRECURSO OUT USRVIDA_AZUL.RECURSOS.DESCRIPCION%TYPE,
    IMAGENRECURSO OUT USRVIDA_AZUL.RECURSOS.IMAGEN%TYPE
) IS
BEGIN
    SELECT ID_CATEGORIA, NOMBRE_RECURSO, DESCRIPCION, IMAGEN
    INTO IDCATEGORIA, NOMBRERECURSO, DESCRIPCIONRECURSO, IMAGENRECURSO
    FROM USRVIDA_AZUL.RECURSOS
    WHERE ID_RECURSO = IDRECURSO;
END SP_LEER_RECURSO;