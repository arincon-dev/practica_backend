# @Transactional in Service Operations

## What it is

@Transactional defines a transaction boundary around a method.
Inside that boundary:
- all DB writes commit together, or
- all DB writes roll back if an unchecked exception occurs.

## Why it is used

- Preserves data consistency in multi-step write operations.
- Prevents partial updates when errors happen mid-flow.

## Why this is good in this project

This backend has write flows that can involve multiple repository actions.
Examples include update and delete flows with ownership checks and related data cleanup.

@Transactional ensures those operations are atomic from business perspective.

## Typical usage rule

Place @Transactional in service layer methods that modify state.
Keep repositories focused on data access only.

## Common pitfalls

- Putting @Transactional only in controller can mix concerns.
- Expecting rollback for checked exceptions by default (rollback rules depend on exception type and config).

## Transaction lifecycle (simplified)

1. Method enters through Spring proxy.
2. Transaction opens.
3. Repository operations run.
4. If method finishes normally -> commit.
5. If runtime exception escapes -> rollback.

## Why proxy behavior matters

Spring applies @Transactional through proxies.
If a method inside the same class calls another @Transactional method directly, proxy interception may not happen.

This is why transaction boundaries are usually placed at public service methods called from outside the class.

## Project fit details

Write operations in user/address services include checks and persistence steps.
Transactional boundaries guarantee these steps behave atomically from API perspective.
