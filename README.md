# Backend Spring Boot - User Management API

Spring Boot backend for user and address management, with MySQL persistence and Flyway migrations.

## 1. Prerequisites

- Java 21
- Maven 3.9+
- MySQL 8+

## 2. Environment variables

The application reads database credentials from environment variables:

| Variable | Description | Example |
|---|---|---|
| `MYSQL_DATABASE_URL` | JDBC URL | `jdbc:mysql://localhost:3306/practica_final_backend` |
| `MYSQL_DATABASE_USER` | Database user | `root` |
| `MYSQL_DATABASE_PWD` | Database password | `admin` |

### PowerShell

```powershell
$env:MYSQL_DATABASE_URL="jdbc:mysql://localhost:3306/practica_final_backend"
$env:MYSQL_DATABASE_USER="root"
$env:MYSQL_DATABASE_PWD="admin"
```

### CMD

```cmd
set MYSQL_DATABASE_URL=jdbc:mysql://localhost:3306/practica_final_backend
set MYSQL_DATABASE_USER=root
set MYSQL_DATABASE_PWD=admin
```

### Linux/macOS

```bash
export MYSQL_DATABASE_URL="jdbc:mysql://localhost:3306/practica_final_backend"
export MYSQL_DATABASE_USER="root"
export MYSQL_DATABASE_PWD="admin"
```

## 3. Start backend

From this backend folder:

```bash
mvn spring-boot:run
```

Alternative JAR flow:

```bash
mvn -DskipTests package
java -jar target/usuarios-0.0.1-SNAPSHOT.jar
```

## 4. Useful URLs

- API base: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/fullstack.html`

## 5. Flyway and seed data

Flyway runs automatically at startup.

Migration scripts:
- `src/main/resources/db/migration/V1__create_schema_initial_tables.sql`
- `src/main/resources/db/migration/V2__fill_initial_schema.sql`
- `src/main/resources/db/migration/V3__add_is_admin_to_user.sql`
- `src/main/resources/db/migration/V4__add_unique_username.sql`

Sample login credentials from seed data:
- `johnsmith` / `password123`
- `emilyjohnson` / `password456`
- `michaelbrown` / `password789`

## 6. API overview

User endpoints:
- `POST /api/v1/usuarios/iniciar-sesion`
- `GET /api/v1/usuarios/`
- `GET /api/v1/usuarios/{id}`
- `POST /api/v1/usuarios/`
- `PUT /api/v1/usuarios/{id}`
- `DELETE /api/v1/usuarios/{id}`
- `GET /api/v1/usuarios/generos`
- `GET /api/v1/usuarios/puestos-de-trabajo`

Address endpoints:
- `GET /api/v1/direcciones/usuario/{userId}`
- `GET /api/v1/direcciones/{id}`
- `POST /api/v1/direcciones/`
- `PUT /api/v1/direcciones/{id}`
- `DELETE /api/v1/direcciones/{id}`

Most business endpoints require query params:
- `nickUsuario`
- `nickContrasena`

## 7. Build and test

```bash
mvn -DskipTests package
mvn test
```

## 8. Run with frontend on a new machine

Use this sequence for a clean full-stack startup:

1. Install prerequisites (Java, Maven, MySQL, Node, npm).
2. Create MySQL schema `practica_final_backend`.
3. Set backend DB environment variables (section 2).
4. Start backend and confirm `http://localhost:8080/fullstack.html` is reachable.
5. Move to frontend folder and run:

```bash
npm install
npm start
```

6. Open `http://localhost:4200/login`.
7. Sign in with a seeded user and test create/update/delete user flows.

## 9. Troubleshooting

### Backend cannot connect to MySQL
- Ensure MySQL is running.
- Verify URL, user, and password environment variables.
- Confirm schema `practica_final_backend` exists.

### Flyway migration failure
- Check migration ordering and versions.
- Reset inconsistent local schema state if needed.

### Port 8080 already in use
- Stop the conflicting process, or set a different `server.port`.

