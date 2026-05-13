# Controller Request Binding Annotations

## Covered annotations

- @RequestMapping
- @GetMapping, @PostMapping, @PutMapping, @DeleteMapping
- @PathVariable
- @RequestParam
- @RequestBody

## What they do

- @RequestMapping defines base path for a controller.
- HTTP method annotations map individual endpoint routes.
- @PathVariable reads values from URL path segments.
- @RequestParam reads query parameters.
- @RequestBody binds JSON payloads to Java objects.

## Why this is used

These annotations make endpoint contracts explicit and self-documenting.
They keep request parsing declarative and reduce parsing boilerplate.

## Why this is good in this project

This project uses credentials in request params and resource IDs in path variables.
The annotations clearly separate:
- route identity,
- filter/input params,
- and body payload.

## Common mistakes

- Mixing path and query params inconsistently.
- Accepting very complex request bodies without validation.
- Using ambiguous endpoint paths that overlap.

## Imports to know

- org.springframework.web.bind.annotation.RequestMapping
- org.springframework.web.bind.annotation.GetMapping
- org.springframework.web.bind.annotation.PostMapping
- org.springframework.web.bind.annotation.PutMapping
- org.springframework.web.bind.annotation.DeleteMapping
- org.springframework.web.bind.annotation.PathVariable
- org.springframework.web.bind.annotation.RequestParam
- org.springframework.web.bind.annotation.RequestBody

## Endpoint contract design tips

- Use path variables for resource identity (for example userId, addressId).
- Use query params for filters or contextual inputs.
- Use request body for structured create/update payloads.

Consistent conventions make APIs easier to consume and document.

## Request processing flow

1. Route is matched.
2. Path/query/body values are bound by annotations.
3. Validation (if @Valid is used) runs.
4. Controller calls service with normalized inputs.

