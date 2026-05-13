# Streams and Method References (::)

## What they are

- Stream API allows functional-style operations over collections (map, filter, collect, toList).
- Method reference (::) is shorthand for a lambda that calls one method.

Typical project pattern:
- list.stream().map(userMapper::toResponse).toList()

## Why they are used

- Expresses collection transformation clearly.
- Removes loop boilerplate.
- Makes intent explicit: transform each element from one type to another.

## Why this is good in this project

This backend repeatedly transforms:
- Entity -> Model
- Model -> DTO

Stream + mapper method references make those transformations short and consistent.

## Method reference vs lambda

- mapper::toResponse is equivalent to x -> mapper.toResponse(x)
- Method references are usually more readable when the lambda just calls one method.

## Tradeoffs

- Streams are elegant for mapping/filtering pipelines.
- For very complex logic, a classic loop may be easier to debug.

## Pipeline breakdown

For a transformation like:
list.stream().map(userMapper::toResponse).toList()

The steps are:
1. stream(): create a processing pipeline over list elements.
2. map(...): transform each element from one type to another.
3. toList(): materialize the transformed elements.

## Why this pattern is strong here

This project performs many one-to-one conversions between layers.
Those conversions are deterministic and side-effect free, which is the ideal use case for map().

## Method reference quick rules

Prefer method reference when lambda only calls one method.
Prefer lambda when additional inline logic is needed.

## Common anti-patterns

- Doing database calls inside map() body.
- Mutating external state from stream operations.
- Using streams for deeply branching business rules.
