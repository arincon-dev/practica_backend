# Validation and OpenAPI Annotations

## Covered annotations

- @Valid
- @NotBlank
- @NotNull
- @Schema

## What they do

- @Valid triggers bean validation on request bodies.
- @NotBlank enforces non-empty text for required string fields.
- @NotNull enforces required non-string values.
- @Schema enriches Swagger/OpenAPI docs with examples and metadata.

## Why they are used

- Fail fast at API boundary before business logic runs.
- Keep validation rules close to DTO definition.
- Improve API discoverability and testability in Swagger.

## Why this is good in this project

This backend exposes create/update endpoints with structured payloads.
Boundary validation protects service code from malformed inputs and produces cleaner error handling.

## Layering rationale

- DTO: syntax/shape validation (required fields, blank strings).
- Service: business validation (ownership, duplicates, domain rules).

This separation keeps concerns clean.

## Request lifecycle with validation

1. JSON body is bound to DTO.
2. @Valid triggers bean validation.
3. If violations exist, request fails before service execution.
4. Global handler converts validation exception into HTTP 400 response.

This is a fail-fast strategy that protects business layer from malformed input.

## @NotBlank vs @NotNull

- @NotNull: value must be present (can still be empty string for text fields).
- @NotBlank: for strings, value must be present and contain non-whitespace characters.

Choosing the right one avoids weak validation rules.

## @Schema role

@Schema does not validate runtime behavior.
Its purpose is documentation quality in OpenAPI/Swagger:
- examples,
- formats,
- field intent.
