-- Insertar datos en la tabla Tipos_Identificacion
INSERT INTO Tipos_Identificacion (Nombre_Tipo_Identificacion) VALUES ('Cédula de ciudadanía');
INSERT INTO Tipos_Identificacion (Nombre_Tipo_Identificacion) VALUES ('Cédula de extranjería');
INSERT INTO Tipos_Identificacion (Nombre_Tipo_Identificacion) VALUES ('Numero Único de Identificación Personal (NUIP)');
INSERT INTO Tipos_Identificacion (Nombre_Tipo_Identificacion) VALUES ('Pasaporte');
INSERT INTO Tipos_Identificacion (Nombre_Tipo_Identificacion) VALUES ('Permiso Especial');
INSERT INTO Tipos_Identificacion (Nombre_Tipo_Identificacion) VALUES ('Permiso por Protección Personal (PPT)');
INSERT INTO Tipos_Identificacion (Nombre_Tipo_Identificacion) VALUES ('Numero de Identificación Tributaria (NIT)');

-- Insertar datos en la tabla Tipos_Persona
INSERT INTO Tipos_Persona (Nombre_Tipo_Persona) VALUES ('Persona Natural');
INSERT INTO Tipos_Persona (Nombre_Tipo_Persona) VALUES ('Persona Juridica');

-- Insertar datos en la tabla Tipos_Solicitud
INSERT INTO tipos_solicitud (nombre_tipo_solicitud) values ('Peticion');
INSERT INTO tipos_solicitud (nombre_tipo_solicitud) values ('Queja');
INSERT INTO tipos_solicitud (nombre_tipo_solicitud) values ('Reclamo');
INSERT INTO tipos_solicitud (nombre_tipo_solicitud) values ('Sugerencia');

-- Insertar datos en la tabla estados_solicitud
INSERT INTO estados_solicitud(nombre_estado_solicitud) values ('Pendiente');
INSERT INTO estados_solicitud(nombre_estado_solicitud) values ('Finalizado');
INSERT INTO estados_solicitud(nombre_estado_solicitud) values ('Rechazado');
INSERT INTO estados_solicitud(nombre_estado_solicitud) values ('Cancelado');

-- Insertar Datos en la tabla Dependencias
INSERT INTO dependencias (nombre_dependencia) values ('Secretaria de Salud');
INSERT INTO dependencias (nombre_dependencia) values ('Secretaria de Educación');
INSERT INTO dependencias (nombre_dependencia) values ('Secretaria de Gobierno');
INSERT INTO dependencias (nombre_dependencia) values ('Dirección Financiera');
INSERT INTO dependencias (nombre_dependencia) values ('Secretaria de Planeación');
INSERT INTO dependencias (nombre_dependencia) values ('UMATA');
INSERT INTO dependencias (nombre_dependencia) values ('N\A');

-- Insertar Datos en la tabla Categorias
INSERT INTO categorias (id_dependencia, nombre_categoria) values (1,'Salud publica');
INSERT INTO categorias (id_dependencia, nombre_categoria) values (1,'afiliación');
INSERT INTO categorias (id_dependencia, nombre_categoria) values (1,'infraestructura hospitalaria');
INSERT INTO categorias (id_dependencia, nombre_categoria) values (2,'Programa de alimentación escolar (PAE)');
INSERT INTO categorias (id_dependencia, nombre_categoria) values (2,'Infraestructura educativa');
INSERT INTO categorias (id_dependencia, nombre_categoria) values (2,'Cobertura educativa');
INSERT INTO categorias (id_dependencia, nombre_categoria) values (2,'Educación continua');
INSERT INTO categorias (id_dependencia, nombre_categoria) values (2,'Educación Superior');
INSERT INTO categorias (id_dependencia, nombre_categoria) values (3,'Seguridad');
INSERT INTO categorias (id_dependencia, nombre_categoria) values (3,'Victimas');
INSERT INTO categorias (id_dependencia, nombre_categoria) values (3,'Contratación');
INSERT INTO categorias (id_dependencia, nombre_categoria) values (4,'Impuestos');
INSERT INTO categorias (id_dependencia, nombre_categoria) values (4,'Pagos proveedores');
INSERT INTO categorias (id_dependencia, nombre_categoria) values (4,'Presupuesto');
INSERT INTO categorias (id_dependencia, nombre_categoria) values (4,'Contabilidad');
INSERT INTO categorias (id_dependencia, nombre_categoria) values (5,'Infraestructura');
INSERT INTO categorias (id_dependencia, nombre_categoria) values (5,'Certificados');
INSERT INTO categorias (id_dependencia, nombre_categoria) values (5,'Gestión del riesgo');
INSERT INTO categorias (id_dependencia, nombre_categoria) values (6,'Asistencia técnica');
INSERT INTO categorias (id_dependencia, nombre_categoria) values (6,'medio ambiente');