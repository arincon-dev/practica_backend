# API Contract

Base URL: `http://localhost:8080`

Versioned API prefix:
- `/api/v1/usuarios`
- `/api/v1/direcciones`

## Global response shape

Successful responses use:
```json
{
  "type": "OK",
  "message": "",
  "data": {}
}
```

Error responses use:
```json
{
  "type": "ERROR",
  "message": "...",
  "data": null
}
```

## Authentication input convention

Most business endpoints require:
- `nickUsuario` (query param)
- `nickContrasena` (query param)

Login endpoint uses:
- `username` (query param)
- `password` (query param)

## Endpoints

## Usuarios

### POST `/api/v1/usuarios/iniciar-sesion`

Query params:
- `username` (required)
- `password` (required)

Response data type:
- `boolean`

Notes:
- Returns HTTP 200 with `data=true/false`.

### GET `/api/v1/usuarios/`

Query params:
- `nickUsuario` (required)
- `nickContrasena` (required)

Response data type:
- `UserResponseDTO[]`

### GET `/api/v1/usuarios/{id}`

Path params:
- `id` (required)

Query params:
- `nickUsuario` (required)
- `nickContrasena` (required)

Response data type:
- `UserResponseDTO`

### POST `/api/v1/usuarios/`

Query params:
- `nickUsuario` (required)
- `nickContrasena` (required)

Body (`UserRequestDTO`):
- `username` string, required, not blank
- `password` string, required, not blank
- `name` string, required, not blank
- `firstSurname` string, required, not blank
- `secondSurname` string, optional
- `birthDate` date, optional format `YYYY-MM-DD`
- `breakfastTime` time, optional format `HH:mm:ss`
- `isAdmin` boolean, required
- `genderId` integer, required
- `jobTitleId` integer, optional

Response data type:
- `UserResponseDTO`

### PUT `/api/v1/usuarios/{id}`

Path params:
- `id` (required)

Query params:
- `nickUsuario` (required)
- `nickContrasena` (required)

Body:
- same shape as `UserRequestDTO`

Response data type:
- `UserResponseDTO`

### DELETE `/api/v1/usuarios/{id}`

Path params:
- `id` (required)

Query params:
- `nickUsuario` (required)
- `nickContrasena` (required)

Response data type:
- `boolean`

### GET `/api/v1/usuarios/generos`

Query params:
- `nickUsuario` (required)
- `nickContrasena` (required)

Response data type:
- `GenderModel[]`

### GET `/api/v1/usuarios/puestos-de-trabajo`

Query params:
- `nickUsuario` (required)
- `nickContrasena` (required)

Response data type:
- `JobTitleModel[]`

## Direcciones

### GET `/api/v1/direcciones/usuario/{userId}`

Path params:
- `userId` (required)

Query params:
- `nickUsuario` (required)
- `nickContrasena` (required)

Response data type:
- `AddressResponseDTO[]`

### GET `/api/v1/direcciones/{id}`

Path params:
- `id` (required)

Query params:
- `nickUsuario` (required)
- `nickContrasena` (required)

Response data type:
- `AddressResponseDTO`

### POST `/api/v1/direcciones/`

Query params:
- `nickUsuario` (required)
- `nickContrasena` (required)

Body (`AddressRequestDTO`):
- `streetName` string, required, not blank
- `streetNumber` integer, required
- `mainAddress` boolean, required
- `userId` integer, required

Important behavior note:
- in create flow, ownership is enforced by authenticated credentials.
- service persistence binds address ownership to authenticated user context.
- clients should send `userId` consistent with authenticated user.

Response data type:
- `AddressResponseDTO`

### PUT `/api/v1/direcciones/{id}`

Path params:
- `id` (required)

Query params:
- `nickUsuario` (required)
- `nickContrasena` (required)

Body:
- same shape as `AddressRequestDTO`

Response data type:
- `AddressResponseDTO`

### DELETE `/api/v1/direcciones/{id}`

Path params:
- `id` (required)

Query params:
- `nickUsuario` (required)
- `nickContrasena` (required)

Response data type:
- `boolean`

## Business behavior notes

- Duplicate usernames are blocked.
- Address operations enforce ownership checks.
- Only one main address is allowed per user at a time in create/update flows.

## Integration recommendation

For frontend teams, import and use:
- `notes/practica_backend.postman_collection.json`

That file should be considered the executable contract companion to this document.
