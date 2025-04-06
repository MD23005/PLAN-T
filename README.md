--PLAN-T — Sistema de Planificación Educativa

PLAN-T: es una aplicación web desarrollada para facilitar la planificación de sesiones de clase por parte de docentes.  
Permite registrar, organizar y consultar contenidos, objetivos y recursos pedagógicos de forma estructurada y accesible.

Descripción General

- Propósito: Optimizar el proceso de planificación educativa.
- Usuarios principales: Docentes, coordinadores académicos, administradores escolares.
- Beneficio: Centraliza la información de clases, mejora el seguimiento pedagógico y reduce el uso de papel.

--Tecnologías Principales

| Componente       | Tecnología              |
|------------------|--------------------------|
| Lenguaje         | Java 17                 |
| Framework        | Spring Boot             |
| Base de Datos    | PostgreSQL              |
| Interfaz Web     | Thymeleaf + HTML + CSS  |
| Contenedores     | Docker                  |

--Funcionalidades

-Autenticación: Registro y acceso de usuarios.
-Gestión de Sesiones: Creación, edición y eliminación de sesiones de clase.
-Administración de Recursos: Asociación de materiales y enlaces a cada sesión.
- Reportes: Visualización de información por docente o periodo.

--Módulos CRUD Implementados

| Módulo    | Descripción                                            |
|-----------|--------------------------------------------------------|
| Usuarios  | Gestión de registro, autenticación y roles             |
| Sesiones  | Registro detallado de cada clase (fecha, contenidos)   |
| Recursos  | Gestión de materiales y enlaces relacionados           |

--Dockerización

El sistema puede ser desplegado usando contenedores para facilitar su instalación.

- Imagen Base:`openjdk:17-jdk-slim`
- Base de Datos: PostgreSQL
- Comando para ejecutar: docker-compose up --build

  
