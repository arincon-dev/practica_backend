# Backend Spring Boot - API de gestión de usuarios

Backend Spring Boot para gestión de usuarios y direcciones, con persistencia MySQL y migraciones Flyway.

## Este proyecto se usa junto con el frontend

Este backend puede ejecutarse y probarse por Swagger, pero la aplicación completa se usa con el frontend Angular.

Proyecto frontend relacionado:
- `../../practica_frontend/README.md`

Para el flujo completo de la práctica:
1. Arranca este backend (`http://localhost:8080`).
2. Arranca el frontend (`http://localhost:4200`).
3. Inicia sesión en el frontend y ejecuta los flujos de usuarios.

Versión en inglés: [README.en.md](README.en.md)

## 1. Requisitos previos

- Java 21
- Maven 3.9+
- MySQL 8+

## 2. Variables de entorno

La aplicación lee las credenciales de base de datos desde variables de entorno:

| Variable | Descripción | Ejemplo |
|---|---|---|
| `MYSQL_DATABASE_URL` | URL JDBC | `jdbc:mysql://localhost:3306/practica_final_backend` |
| `MYSQL_DATABASE_USER` | Usuario de base de datos | `root` |
| `MYSQL_DATABASE_PWD` | Contraseña de base de datos | `admin` |

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

## 3. Iniciar el backend

Desde esta carpeta del backend:

```bash
mvn spring-boot:run
```

Flujo alternativo con JAR:

```bash
mvn -DskipTests package
java -jar target/usuarios-0.0.1-SNAPSHOT.jar
```

## 4. URLs útiles

- API base: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/fullstack.html`

## 5. Flyway y datos semilla

Flyway se ejecuta automáticamente al arrancar.

Scripts de migración:
- `src/main/resources/db/migration/V1__create_schema_initial_tables.sql`
- `src/main/resources/db/migration/V2__fill_initial_schema.sql`
- `src/main/resources/db/migration/V3__add_is_admin_to_user.sql`
- `src/main/resources/db/migration/V4__add_unique_username.sql`

Credenciales de ejemplo incluidas en los datos semilla:
- `johnsmith` / `password123`
- `emilyjohnson` / `password456`
- `michaelbrown` / `password789`

## 6. Resumen de la API

Endpoints de usuario:
- `POST /api/v1/usuarios/iniciar-sesion`
- `GET /api/v1/usuarios/`
- `GET /api/v1/usuarios/{id}`
- `POST /api/v1/usuarios/`
- `PUT /api/v1/usuarios/{id}`
- `DELETE /api/v1/usuarios/{id}`
- `GET /api/v1/usuarios/generos`
- `GET /api/v1/usuarios/puestos-de-trabajo`

Endpoints de direcciones:
- `GET /api/v1/direcciones/usuario/{userId}`
- `GET /api/v1/direcciones/{id}`
- `POST /api/v1/direcciones/`
- `PUT /api/v1/direcciones/{id}`
- `DELETE /api/v1/direcciones/{id}`

La mayoría de los endpoints de negocio requieren estos query params:
- `nickUsuario`
- `nickContrasena`

## 7. Compilación y pruebas

```bash
mvn -DskipTests package
mvn test
```

## 8. Ejecutar con el frontend en una máquina nueva

Usa esta secuencia para un arranque limpio del stack completo:

1. Instala los requisitos previos (Java, Maven, MySQL, Node, npm).
2. Crea el esquema MySQL `practica_final_backend`.
3. Configura las variables de entorno de la BD del backend (sección 2).
4. Inicia el backend y confirma que `http://localhost:8080/fullstack.html` responde.
5. Ve a la carpeta del frontend y ejecuta:

```bash
npm install
npm start
```

6. Abre `http://localhost:4200/login`.
7. Inicia sesión con un usuario sembrado y prueba los flujos de crear, actualizar y eliminar usuarios.

## 9. Solución de problemas

### El backend no puede conectarse a MySQL
- Asegúrate de que MySQL esté en ejecución.
- Verifica las variables de entorno de URL, usuario y contraseña.
- Confirma que el esquema `practica_final_backend` exista.

### Fallo de migración Flyway
- Revisa el orden y la versión de las migraciones.
- Si hace falta, restablece el estado inconsistente del esquema local.

### El puerto 8080 ya está en uso
- Detén el proceso que entra en conflicto o configura otro `server.port`.

