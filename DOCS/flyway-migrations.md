# Flyway Migrations

## What Flyway does

Flyway applies versioned SQL migrations in order when the application starts.
Each migration has a version prefix (for example V1, V2, V3).

## Why it is used

- Keeps schema changes reproducible.
- Makes environment setup consistent.
- Avoids manual SQL drift between machines.

## Why this is good in this project

This project needs database evolution for assignment steps (for example adding is_admin and unique username constraints).
Flyway ensures those changes are tracked and replayable.

## Naming convention

Typical file format:
- V1__create_schema_initial_tables.sql
- V2__fill_initial_schema.sql

The double underscore separates version and description.

## Important rule

Avoid editing already-applied migrations in shared environments.
Create a new migration for each new change.

## Common mistakes

- Renaming existing migration files after execution.
- Mixing schema and seed data in inconsistent ways.
- Running with different DB states without checking migration history.

## Execution model

Flyway stores migration history in a metadata table.
At startup, it compares available migration files with applied versions and runs only pending migrations in order.

This is why version order and file integrity are critical.

## Safe workflow for new DB changes

1. Create a new migration with next version number.
2. Keep migration small and focused.
3. Run on clean local database.
4. Verify application startup and data behavior.

Avoid rewriting historical migrations in shared environments.
