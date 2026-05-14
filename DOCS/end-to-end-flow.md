# End-to-End Flow (Backend View)

This document describes the full runtime flow from backend perspective.

## 1. Login endpoint

1. Client calls `POST /api/v1/usuarios/iniciar-sesion` with:
   - `username`
   - `password`
2. Service validates credentials against repository.
3. Backend returns HTTP 200 with envelope data:
   - `true` for valid credentials
   - `false` for invalid credentials

## 2. Authenticated business calls

Most business endpoints require query params:
- `nickUsuario`
- `nickContrasena`

Services validate credentials before continuing with business logic.

## 3. Users read flow

1. Controller receives request and delegates to service.
2. Service authenticates and loads entities.
3. Mapper converts model/entity layers to response DTO.
4. Controller returns envelope (`type`, `message`, `data`).

## 4. User create/update flow

1. Controller validates request DTO (`@Valid`).
2. Service authenticates user context.
3. Service checks business rules:
   - duplicate username,
   - required gender,
   - optional/valid job title.
4. Service persists entity inside transactional boundary.
5. Controller returns mapped response DTO.

## 5. Address create/update/delete flow

1. Controller validates address DTO.
2. Service authenticates and resolves ownership.
3. Service enforces ownership and main-address rules.
4. When setting main address, service unmarks previous main addresses for same user.
5. Service persists changes transactionally and returns mapped response.

## 6. Error flow

1. Service throws typed exception for business failure.
2. `@RestControllerAdvice` maps exception to HTTP status.
3. Global handler returns standard error envelope.

Typical statuses:
- 400 validation/format
- 401 authentication
- 403 forbidden ownership
- 404 resource not found
- 409 conflict
- 500 unexpected error

## 7. Data lifecycle

- Flyway runs migrations at startup.
- Schema and seed data are applied in version order.
- Runtime operations rely on migrated schema and constraints.

## Related Docs

- [API contract](api-contract.md)
- [Error contract](error-contract.md)
- [Global exception handling with @RestControllerAdvice](global-exception-handling.md)
- [@Transactional in service operations](transactional.md)
- [Flyway migrations](flyway-migrations.md)
