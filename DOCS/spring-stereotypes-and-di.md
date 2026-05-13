# Spring Stereotypes and Dependency Injection

## Covered annotations

- @Component
- @Service
- @Repository
- @RestController
- @Autowired

## What they do

- @Component registers a class as a Spring-managed bean.
- @Service marks business-layer classes.
- @Repository marks data-access classes and participates in Spring data exception translation.
- @RestController marks HTTP controllers that return JSON bodies by default.
- @Autowired injects dependencies managed by Spring.

## Why this is used

These annotations let Spring create and connect objects automatically.
That removes manual object wiring and keeps layers decoupled.

## Why this is good in this project

This backend is layered:
- controllers for HTTP,
- services for business logic,
- repositories for persistence.

Stereotypes make each class role explicit and readable.

## Constructor injection vs field injection

Both work, but constructor injection is generally preferred because:
- dependencies are explicit,
- classes are easier to test,
- required dependencies can be final.

## Common mistakes

- Putting business logic in controllers instead of services.
- Injecting too many dependencies in one class (often a design smell).
- Using @Autowired everywhere without checking if constructor injection would be cleaner.

## Imports to know

- org.springframework.stereotype.Component
- org.springframework.stereotype.Service
- org.springframework.stereotype.Repository
- org.springframework.web.bind.annotation.RestController
- org.springframework.beans.factory.annotation.Autowired

## Bean lifecycle mental model

At startup, Spring scans packages, finds stereotype annotations, creates beans, and stores them in the application context.
When one bean depends on another, Spring injects the dependency automatically.

This gives inversion of control: the framework manages object creation and wiring.

## Why this helps testing

Clear dependency boundaries make classes easier to test.
In unit tests, dependencies can be mocked instead of creating full framework context.
