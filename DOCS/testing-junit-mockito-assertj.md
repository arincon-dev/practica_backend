# Testing with JUnit 5, Mockito, and AssertJ

## Tools used

- JUnit 5 for test execution structure.
- Mockito for mocking dependencies.
- AssertJ for readable assertions.

## Key annotations and methods

- @ExtendWith(MockitoExtension.class)
- @Mock
- @InjectMocks
- given(...).willReturn(...)
- verify(...)
- assertThat(...)
- assertThatThrownBy(...)

## Why this is used

Service tests should verify business logic in isolation.
Mocking repositories/mappers keeps tests fast and focused.

## Why this is good in this project

This backend has service rules like:
- forbidden ownership access,
- authentication checks,
- duplicate username prevention,
- transactional write behavior assumptions.

Mockito-based unit tests validate these rules without a real database.

Scope clarification:
- these tests validate service decision logic and repository interactions.
- they do not validate real database transaction commit/rollback behavior.
- transaction rollback behavior should be covered with integration tests against a real persistence context.

## Common mistakes

- Over-mocking trivial behavior.
- Testing framework behavior instead of service behavior.
- Writing tests that depend on execution order.

## Imports to know

- org.junit.jupiter.api.Test
- org.junit.jupiter.api.extension.ExtendWith
- org.mockito.Mock
- org.mockito.InjectMocks
- org.mockito.junit.jupiter.MockitoExtension
- static org.mockito.BDDMockito.given
- static org.mockito.Mockito.verify
- static org.assertj.core.api.Assertions.assertThat
- static org.assertj.core.api.Assertions.assertThatThrownBy

## Test structure pattern

A useful structure for service unit tests:
1. Arrange: define input and mock behavior.
2. Act: call service method.
3. Assert: verify result, exception, and important interactions.

This keeps tests readable and consistent.

## What to assert in this project

- Success path data (mapped output correctness).
- Failure path exception type.
- Side effects (for example delete/save interactions when relevant).

Focus on business behavior, not internal implementation details.
