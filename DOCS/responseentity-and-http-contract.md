# ResponseEntity and HTTP Contract Design

## What it is

ResponseEntity lets you control:
- HTTP status code,
- headers,
- and response body.

## Why it is used

- Makes status semantics explicit.
- Helps avoid accidental 200 responses for error cases.
- Supports a consistent API contract.

## Why this is good in this project

Your backend relies on clear status outcomes for auth, not found, forbidden, and conflict scenarios.
ResponseEntity pairs naturally with global exception handling and stable payload shape.

## Contract strategy in this project

Success and error payloads should follow predictable structure.
That consistency helps frontend integration and API testing.

## Tradeoffs

- Slightly more verbose than returning plain objects.
- Worth it when API semantics matter.

## Status code semantics quick guide

- 200 OK: request succeeded and returns content.
- 201 Created: resource created.
- 204 No Content: request succeeded with no body.
- 400 Bad Request: invalid client payload/format.
- 401 Unauthorized: authentication required/invalid credentials.
- 403 Forbidden: authenticated but not allowed.
- 404 Not Found: resource does not exist.
- 409 Conflict: business conflict (for example duplicate unique field).
- 500 Internal Server Error: unexpected server failure.

## Why consistent contract matters

A predictable body shape + correct status codes helps:
- frontend integration,
- test automation,
- operational debugging.

Clients can branch behavior by status code first, then read message/data details.
