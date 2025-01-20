_______________________________________________________________________________________________________________

***Foro Hub API***

_______________________________________________________________________________________________________________

Esta es una API REST desarrollada con Spring Boot que incluye autenticación mediante tokens JWT (JSON Web Tokens). 
La aplicación permite gestionar entidades como usuarios, cursos y tópicos en un foro. 
Este README describe la funcionalidad del proyecto y proporciona instrucciones para configurarlo y ejecutarlo.

_______________________________________________________________________________________________________________
**Características principales**

Autenticación JWT: Los usuarios inician sesión para recibir un token JWT que les permite acceder a las rutas protegidas.

Gestión de usuarios, cursos y tópicos: CRUD completo para las entidades principales.

Seguridad: Integración de Spring Security con filtros personalizados para la validación de tokens JWT.

Arquitectura modular: Uso de servicios, controladores y entidades separados.

_______________________________________________________________________________________________________________
**Tecnologías utilizadas**

<Java 17>

<Spring Boot 3.x>

<Spring Security>

<Spring Data JPA>

<Hibernate (ORM)>

<MySQL (Base de datos)>

<Maven (Gestor de dependencias)>

<Auth0 Java JWT (Generación y verificación de tokens JWT)>

_______________________________________________________________________________________________________________
**Configuración del proyecto**

1. Clonar el repositorio

git clone https://github.com/tu-usuario/foro-hub-api.git
cd foro-hub-api

2. Configurar las propiedades

Edita el archivo application.properties o application.yml con los valores de tu configuración:
_______________________________________________________________________________________________________________

**Configuración de la base de datos**

spring.datasource.url=jdbc:mysql://localhost:3306/foro_hub
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
_______________________________________________________________________________________________________________

**Configuración de seguridad**

api.security.token.secret=tu_clave_secreta

3. Crear la base de datos

Crea una base de datos en MySQL llamada foro_hub:

CREATE DATABASE foro_hub;

4. Ejecutar la aplicación

Usa Maven para compilar y ejecutar la aplicación:

mvn spring-boot:run 
La API estará disponible en http://localhost:9090
_______________________________________________________________________________________________________________

**Endpoints principales**

-Autenticación

-POST /auth/login

-Solicita credenciales del usuario y devuelve un token JWT.


**Ejemplo de request body:**

{
  "nombre": "usuarioEjemplo",
  "contrasena": "password123"
}


**Ejemplo de respuesta:**

{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}


**Cursos**

GET /api/curso: Lista todos los cursos.

POST /api/curso: Crea un nuevo curso (requiere token JWT).

GET /api/curso/{id}: Obtiene detalles de un curso por su ID.

DELETE /api/curso/{id}: Elimina un curso por su ID.


**Tópicos**

POST /topico: Crea un nuevo tópico (requiere token JWT).

GET /topico: Lista todos los tópicos.

GET /topico/{id}: Obtiene detalles de un tópico por su ID.

PUT /topico/{id}: Actualiza un tópico (requiere token JWT).

DELETE /topico/{id}: Elimina un tópico por su ID.
_______________________________________________________________________________________________________________

**Seguridad**

El acceso a los endpoints protegidos requiere un token JWT. Asegúrate de incluir el token en el encabezado de la solicitud:

Authorization: Bearer <tu_token_jwt>
_______________________________________________________________________________________________________________

**Próximos pasos**

Implementar paginación y búsqueda para los endpoints.

Agregar pruebas unitarias y de integración.

Mejorar la gestión de errores y validaciones.

