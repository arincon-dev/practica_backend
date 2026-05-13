# Optional and orElseThrow

## What it is

Spring Data methods like findById often return Optional<T>.
Optional represents "value may exist or may not exist" explicitly.

orElseThrow converts "missing value" into a controlled exception.

## Why it is used

- Avoids null checks spread across the code.
- Forces explicit handling of absence.
- Keeps failure semantics clear.

## Why this is good in this project

This backend has domain-level failures such as:
- invalid credentials,
- resource not found,
- forbidden resource access.

Using Optional + orElseThrow maps absence to a precise domain exception that is later converted into HTTP status by the global exception handler.

## Typical pattern

1. Query repository with Optional return.
2. orElseThrow with domain-specific exception.
3. Let global handler map exception to HTTP response.

## Tradeoffs

- More explicit than null, but can be verbose if overused in local variables.
- Best for API boundaries and repository/service transitions.

## Mental model

Optional is not a container to pass everywhere. It is mainly a boundary signal:
"this lookup may not return anything."

orElseThrow then turns that absence into explicit control flow.

## Exception design connection

In this project, absence maps to business meaning.
Examples:
- missing user/address id -> ResourceNotFoundException,
- missing credential lookup -> AuthenticationException.

This keeps failure paths typed and consistent with global exception mapping.

## orElse vs orElseGet vs orElseThrow

- orElse(value): eager value creation.
- orElseGet(supplier): lazy value creation.
- orElseThrow(supplier): fail explicitly if empty.

For service-layer lookup failures, orElseThrow is typically the clearest choice.
