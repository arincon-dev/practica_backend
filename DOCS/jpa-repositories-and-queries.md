# Spring Data JPA Repositories and Queries

## What is used in this project

- Derived query methods (name-based generation).
- @Query for explicit JPQL when clarity is needed.

## Derived method examples conceptually

- existsByUsername(...)
- existsByUsernameAndPassword(...)
- findByUsernameAndPassword(...)
- deleteByUserId(...)

Spring Data parses the method name and generates the SQL/JPQL automatically.

## Why this is used

- Fast to implement standard queries.
- Reduces boilerplate for common CRUD patterns.
- Keeps repository interfaces concise.

## Why this is good in this project

The domain has many straightforward lookup checks (auth, duplicates, ownership inputs).
Derived methods are a good fit for these repetitive checks.

For custom filtering, @Query keeps intent explicit.

## Tradeoffs

- Very long derived names can hurt readability.
- Complex joins/aggregations may be clearer with explicit @Query.

## How derived methods work

Spring Data reads method names and maps them to query intent.
Examples:
- existsByUsernameAndPassword -> boolean existence check with two fields.
- deleteByUserId -> delete rows filtered by foreign key.

This allows fast development for standard repository patterns.

## When to use @Query

Use @Query when:
- method name becomes hard to read,
- query needs custom conditions not clearly expressed by naming,
- you want explicit JPQL readability in code review.

## Layer responsibility reminder

- Repository: data access and persistence query details.
- Service: business decision logic based on repository results.

Keeping this separation avoids leaking business rules into repository layer.
