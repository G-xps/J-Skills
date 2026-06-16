# README Template

When initializing a project, `README.md` must contain at least these sections.

## Project Overview

Describe the project purpose and current initialization status.

## Tech Stack

List the default stack:

- JDK 17
- Spring Boot 3.4.5
- Maven 3.13.0
- MyBatis-Plus 3.5.12
- PostgreSQL 42.2.25
- Flyway

## Modules

Briefly describe each Maven module and keep the descriptions consistent with `multi-module-structure.md`.

## Local Startup

State that `xxx-web` is the only startup module and startup commands should target that module.

Example:

```bash
mvn -pl xxx-web -am spring-boot:run
```

## Build Command

Example:

```bash
mvn package
```

## Configuration

Explain:

- `application.yml` only activates the `dev` profile.
- `application-dev.yml` contains minimal local startup configuration.
- Sensitive configuration must not be committed.

## Flyway Migration

Document the migration directory:

```text
xxx-dao/src/main/resources/db/migration/
```

Document the naming rule:

```text
Vversion__description.sql
```
