CASIP Backend - Gestión de Técnicos de Campo

Backend de servicios REST para la aplicación móvil de CASIP Corporación S.A. Este componente expone la lógica de negocio y gestiona la persistencia de datos para los perfiles de Técnico de Campo y Supervisor, cumpliendo con los objetivos de la entrega TB1.

🚀 Tecnologías Principales

Framework: Spring Boot 3.3.x

Lenguaje: Java 21

Gestor de dependencias: Maven

Base de Datos NoSQL: Firebase Cloud Firestore (Admin SDK)

Arquitectura Cloud (Proyectada): AWS API Gateway + AWS Lambda

📋 Requisitos Previos

Para levantar este proyecto en un entorno local, necesitas tener instalado:

Java 21 JDK o superior.

Maven 3.8.x o superior (se incluye el Maven Wrapper mvnw en el proyecto).

Git.

⚙️ Configuración del Entorno (Firebase)

El sistema utiliza Firestore como base de datos principal para el registro de órdenes, usuarios y evidencias. Para que la conexión funcione, debes configurar las credenciales de servicio de Firebase:

Solicita al administrador del proyecto (E3) el archivo de credenciales serviceAccountKey.json.

IMPORTANTE: Nunca subas este archivo al repositorio. Ya se encuentra excluido en el .gitignore.

Coloca el archivo serviceAccountKey.json en el directorio src/main/resources/.

El sistema lo leerá automáticamente al arrancar mediante la clase de configuración de Firebase.

💻 Instalación y Ejecución Local

1. Clonar el repositorio:
git clone https://github.com/jhonZevallos/casip-backend.git
cd casip-backend

2. Limpiar e instalar dependencias:
./mvnw clean install
(Nota para Windows: ejecuta .\mvnw clean install)

3. Ejecutar la aplicación:
./mvnw spring-boot:run

El servidor backend iniciará localmente en http://localhost:8080.

📁 Estructura del Proyecto

El código fuente sigue una arquitectura en capas orientada a microservicios para facilitar su futura migración a funciones Serverless:

src/main/java/pe/edu/upc/casip/
├── config/       # Inicialización y configuración de Firebase Admin SDK
├── controllers/  # Controladores REST que exponen los endpoints a la app móvil (L1)
├── models/       # Entidades NoSQL (Usuario, Orden, Evidencia)
├── repositories/ # Clases DAO para el acceso y consultas en Firestore
└── services/     # Reglas de negocio, asignación de órdenes y actualizaciones

📡 Endpoints Principales (Alcance TB1 - Funcionalidades L1)

POST /api/auth/login - Autenticación de Técnico / Supervisor (HU01).

GET /api/ordenes - Consulta de órdenes del día para técnicos y supervisores (HU02, HU12).

GET /api/ordenes/{id} - Consulta del detalle específico de una orden (HU03).

POST /api/ordenes - Creación de nuevas órdenes por parte del supervisor (HU09).

PUT /api/ordenes/{id} - Asignación de técnico (HU10) y actualización de estados (HU06).

👥 Equipo de Desarrollo (Frentes F06 / F08)

E3 - Backend / Cloud Developer: Arquitectura, modelo de datos, servicios REST e integración.

E2 - Mobile Developer: Integración de endpoints con la aplicación Android.
