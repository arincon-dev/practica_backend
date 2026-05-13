# DTO, Model, and Entity Mapping

## Concepts

- Entity: persistence representation (database-focused).
- Model: business object used inside services.
- DTO: API boundary shape for requests/responses.

## Why mapping is used

Mapping separates concerns:
- API shape can evolve without breaking persistence internals.
- DB schema details stay out of controllers.
- Sensitive fields can be hidden in response DTOs.

## Why this is good in this project

This backend converts across layers frequently:
- entity to model in repositories/services,
- model to response DTO in controllers,
- request DTO to model before service logic.

That improves maintainability and reduces accidental data leakage.

## Method reference usage in mapping

List transformations are often written as:
- list.stream().map(userMapper::toResponse).toList()

This keeps mapping concise and readable.

## Common mistakes

- Returning entities directly in controllers.
- Putting mapper logic inside services/controllers repeatedly.
- Forgetting to update mapper methods when DTOs change.

## End-to-end mapping flow

Write flow:
1. Request DTO enters controller.
2. Mapper converts DTO to model.
3. Service applies business rules.
4. Repository persists entity.

Read flow:
1. Repository returns entity.
2. Mapper converts entity to model.
3. Controller maps model to response DTO.

This flow keeps each layer focused and predictable.

## Consistency checklist for future changes

When adding a field:
1. Add field to DTO/model/entity where appropriate.
2. Update mapper conversion methods.
3. Update validation annotations and tests.

Skipping mapper updates is a common source of silent bugs.
