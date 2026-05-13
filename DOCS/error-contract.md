# Error Contract

This document defines how backend failures are represented at HTTP and payload level.

## Standard error body

All handled errors use this shape:
```json
{
  "type": "ERROR",
  "message": "...",
  "data": null
}
```

## Status mapping

### 400 Bad Request

Returned for payload or format problems:
- Bean validation failures (`@Valid`, `@NotBlank`, `@NotNull`)
- Malformed JSON body

Typical message examples:
- `username: must not be blank`
- `Malformed JSON request body`

### 401 Unauthorized

Returned when credentials are invalid for secured operations.

Exception source:
- `AuthenticationException`

### 403 Forbidden

Returned when credentials are valid but the user is not allowed to access/modify the target resource.

Exception source:
- `ForbiddenException`

### 404 Not Found

Returned when requested entities do not exist (user/address/etc.).

Exception source:
- `ResourceNotFoundException`

### 409 Conflict

Returned for unique/business conflicts.

Exception source:
- `DuplicateUsernameException`

### 500 Internal Server Error

Returned for unhandled/unknown failures.

Exception source:
- generic `Exception`

## Important integration note

`POST /api/v1/usuarios/iniciar-sesion` does not throw auth exceptions for bad credentials.
It returns HTTP 200 with:
- `data = true` for valid credentials
- `data = false` for invalid credentials

Frontend code should not treat this endpoint as 401-based authentication flow.

## Parsing guidance for clients

1. Check HTTP status first.
2. If non-2xx, parse `type`, `message`, `data` from body.
3. Treat `message` as display/log text; do not hardcode UI logic to full message text.
4. Drive behavior by status code and endpoint context.

## Versioning note

If status mappings or body fields change, update:
- this file
- `DOCS/api-contract.md`
- Postman collection in `notes/practica_backend.postman_collection.json`
