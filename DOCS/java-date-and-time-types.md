# Java Date and Time Types in API and Persistence

## Types used in this project

- java.util.Date for date/time values in model and DTO layers.
- java.time.LocalTime for time-only values (for example breakfast time).

## Why this matters

Date/time type mismatches are a common source of bugs.
Typical issues include:
- timezone surprises,
- SQL type mismatches,
- serialization format mismatches.

## Persistence mapping notes

For entity fields using java.util.Date:
- @Temporal(TemporalType.DATE) maps to SQL DATE.
- @Temporal(TemporalType.TIMESTAMP) maps to SQL DATETIME/TIMESTAMP.

LocalTime maps naturally to SQL TIME in JPA/Hibernate.

## Why this is good in this project

The assignment model distinguishes:
- birth_date (date-only),
- created_at (timestamp),
- breakfast_time (time-only, nullable).

Using explicit mapping avoids accidental datetime precision changes.

## Common mistakes

- Mixing java.sql.Date and java.util.Date inconsistently across DTO/model/entity.
- Forgetting @Temporal when using java.util.Date in entities.
- Assuming date-only values carry timezone context.

## API format considerations

For JSON APIs:
- date-only fields should be represented as YYYY-MM-DD.
- time-only fields should be represented as HH:mm:ss.

Keeping format stable avoids frontend parsing ambiguity.

## Conversion discipline

Choose one type family per layer and keep it consistent.
In this project:
- DTO/model use java.util.Date and LocalTime.
- entity uses explicit temporal mapping for Date fields.

Inconsistency between java.sql.Date and java.util.Date often creates mapper or compile friction.
