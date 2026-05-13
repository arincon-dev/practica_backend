# Backend Concepts Index

This folder contains technical reference documents for Java and Spring concepts used in this backend.
The content is intended for maintainers, reviewers, and collaborators who need fast context on implementation choices.

## Scope

The documents focus on:
- what each concept does,
- where it is typically applied,
- and why it is relevant in this codebase.

## Topic Index

- [Map.of immutable response payloads](map-of.md)
- [Streams and method references (::)](streams-and-method-references.md)
- [Optional and orElseThrow](optional-and-orelsethrow.md)
- [@Transactional in service operations](transactional.md)
- [Validation and OpenAPI annotations](validation-and-schema-annotations.md)
- [Global exception handling with @RestControllerAdvice](global-exception-handling.md)
- [Spring Data JPA queries and derived methods](jpa-repositories-and-queries.md)
- [ResponseEntity and HTTP contract design](responseentity-and-http-contract.md)
- [Spring stereotypes and dependency injection](spring-stereotypes-and-di.md)
- [Controller request binding annotations](controller-request-binding.md)
- [DTO, model, and entity mapping](dto-model-entity-mapping.md)
- [Flyway migrations](flyway-migrations.md)
- [Testing with JUnit, Mockito, and AssertJ](testing-junit-mockito-assertj.md)
- [Java date and time types](java-date-and-time-types.md)
- [API contract](api-contract.md)
- [Error contract](error-contract.md)


## Maintenance Notes

- Keep each document focused on one concept.
- Prefer adding a new file for a new concept instead of expanding unrelated files.
- Update this index when files are added, renamed, or removed.
