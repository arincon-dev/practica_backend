# Global Exception Handling with @RestControllerAdvice

## What it is

@RestControllerAdvice centralizes exception-to-HTTP-response translation.
Instead of handling errors in every controller method, one global handler maps domain exceptions to status codes and body shape.

## Why it is used

- Keeps controllers focused on success flow.
- Avoids duplicated try/catch blocks.
- Enforces consistent error response format.

## Why this is good in this project

This backend uses typed exceptions for authentication, authorization, duplicates, and not found cases.
Global handling provides consistent API behavior across user and address endpoints.

## Typical mapping strategy

- AuthenticationException -> 401
- ForbiddenException -> 403
- ResourceNotFoundException -> 404
- DuplicateUsernameException -> 409
- Validation errors -> 400
- Fallback -> 500

## Tradeoffs

- Centralization improves consistency, but handler design must stay disciplined.
- Overly generic handlers can hide useful debugging context if not logged properly.

## Error flow in this architecture

1. Controller calls service method.
2. Service throws typed exception for business failure.
3. @RestControllerAdvice catches that exception.
4. Handler maps it to HTTP status + stable error body.

This keeps controllers clean and makes behavior predictable.

## Why this beats local try/catch in controllers

- Avoids duplicated error formatting code.
- Enforces one response contract for all endpoints.
- Simplifies endpoint methods to success-path logic.

## Design guideline

Prefer specific handlers first, then a generic fallback.
Specific handlers preserve precise semantics.
Generic fallback prevents uncaught errors from leaking internal details.
