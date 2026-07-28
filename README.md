# 🚀 Academic0 - Backend API

## 📌 Descripción

Academic0 es una API REST desarrollada con **Spring Boot** para la gestión de usuarios, cursos e inscripciones. Implementa autenticación y autorización mediante JWT, control de acceso por roles, documentación con Swagger/OpenAPI y conexión a PostgreSQL.

El proyecto sigue una arquitectura por capas que facilita el mantenimiento, escalabilidad y organización del código.

---

## 🧰 Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- PostgreSQL
- JWT (JJWT)
- MapStruct
- Maven
- Swagger / OpenAPI
- Docker & Docker Compose

---

## 🏗 Arquitectura

El proyecto sigue una arquitectura por capas:

- controllers → Endpoints REST
- services → Lógica de negocio
- repositories → Acceso a datos
- entities → Modelos de la base de datos
- dto → Objetos de transferencia de datos
- mapper → Conversión entre entidades y DTO (MapStruct)
- security → JWT, filtros y configuración de seguridad
- config → Configuración general

Flujo general:

Cliente → Controller → Service → Repository → PostgreSQL

---

## ⚙️ Instalación local

Clonar el repositorio

```bash
git clone https://github.com/usuario/Academic0.git
```

Entrar al proyecto

```bash
cd Academic0
```

Compilar

```bash
mvn clean install
```

---

## 🔐 Configuración

Modificar el archivo:

```
src/main/resources/application.properties
```

Ejemplo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/academic0
spring.datasource.username=postgres
spring.datasource.password=1234

spring.jpa.hibernate.ddl-auto=update

jwt.secret=mi_clave_super_secreta
jwt.expiration=3600000
```

---

## ▶️ Ejecutar el proyecto

Con Maven

```bash
mvn spring-boot:run
```

o

```bash
java -jar target/Academic0.jar
```

---

## 🐳 Docker

Construir el proyecto

```bash
docker-compose up --build
```

Detener

```bash
docker-compose down -v
```

---

## 📖 Documentación

Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🔐 Autenticación

La API utiliza autenticación mediante JWT.

Los endpoints protegidos requieren el encabezado:

```
Authorization: Bearer <token>
```

---

## 📡 Endpoints principales

### Auth

```
POST /auth/login
POST /auth/registro
POST /auth/refresh
```

### Usuarios

```
GET    /usuario
GET    /usuario/{id}
POST   /usuario
PUT    /usuario/{id}
DELETE /usuario/{id}
```

### Cursos

```
GET    /curso
GET    /curso/{id}
POST   /curso
PUT    /curso/{id}
DELETE /curso/{id}
```

### Inscripciones

```
GET    /inscripcion
POST   /inscripcion
DELETE /inscripcion/{id}
```

---

## 👨‍💻 Autor

Proyecto desarrollado como práctica de backend utilizando Spring Boot, Spring Security, JWT, PostgreSQL y Docker, aplicando una arquitectura REST por capas y buenas prácticas de desarrollo.
