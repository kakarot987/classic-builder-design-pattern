# Classic Builder Pattern — Computer Configurations

This Java 17/Spring Boot demo uses `Computer.Builder` to build an immutable
`Computer` object. The required constructor parameters are CPU, RAM and
storage. Optional settings use fluent methods and have defaults.

## Run

From the repository root with Java 17 and Maven:

```bash
mvn spring-boot:run
mvn test
```

Open http://localhost:8080 for the UI.

```java
Computer pc = new Computer.Builder("Intel i9", 64, 2000)
    .gpu("RTX 4090")
    .wifi(true)
    .os("Windows 11")
    .build();
```

The REST controller also demonstrates custom and preset builds.

### Why a Builder?

Compared with an eight-argument *telescoping constructor*, fluent methods
show what each optional argument means. A Java `record` is often sufficient
for a small immutable data carrier; Lombok's `@Builder` can reduce boilerplate
when handwritten invariants are unnecessary. Use a handwritten Builder when
construction rules, defaults, and validation are worth making explicit.

Tests cover required-field validation, defaults, fluent chaining, and
immutability across builder reuse.

![Demo screenshot](img.png)
