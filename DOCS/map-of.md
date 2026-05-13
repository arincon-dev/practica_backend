# Map.of Immutable Response Payloads

## What it is

Map.of is a Java factory method that creates a small immutable map in one line.
Example shape used in this backend:
- type
- message
- data

## Why it is used

- Keeps response payload creation concise.
- Prevents accidental mutation after creation.
- Makes the response contract visually obvious.

## Why it is good in this project

Controllers in this backend return a consistent JSON shape for success and errors.
Map.of makes that contract easy to repeat without boilerplate.

## Important behavior

- Map.of does not allow null keys or null values.
- It returns an immutable map.

Because of that, if a value can be null, you should either:
- build with a mutable map implementation, or
- ensure non-null before calling Map.of.

## Tradeoffs

- Great for small fixed maps.
- Not ideal when keys are dynamic or values can be null frequently.

## Mental model

Think of Map.of as a compact, read-only literal for small API payloads.
It is similar to saying "this response has exactly these keys" and then freezing it.

## Project scenario

In this backend, many responses follow the same contract:
- type
- message
- data

Map.of keeps that contract short and consistent when values are guaranteed non-null.

## Decision guide

Use Map.of when:
- key set is fixed,
- payload is small,
- null values are not expected.

Use HashMap/LinkedHashMap when:
- keys are conditional,
- null values may appear,
- response is built in steps.
