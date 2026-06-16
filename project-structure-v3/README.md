# 由 Skill 生成

这是由 skill `java-project-init` 生成的项目示例。

# project-structure-v3

## Project Overview

This repository is initialized as a Spring Boot Maven multi-module project. The current business capability is a user CRUD API backed by PostgreSQL and Flyway migrations.

## Tech Stack

- JDK 17
- Spring Boot 3.4.5
- Maven 3.13.0
- MyBatis-Plus 3.5.12
- PostgreSQL 42.2.25
- Flyway

## Modules

- `project-structure-v3-base-core`: shared result model, exceptions, constants, and low-level utilities.
- `project-structure-v3-common-api`: shared API contracts across modules.
- `project-structure-v3-framework`: technical framework configuration such as MyBatis-Plus plugins.
- `project-structure-v3-dao`: persistence entities, mappers, and Flyway migrations.
- `project-structure-v3-infra`: infrastructure integrations.
- `project-structure-v3-module-system`: system business features, including user CRUD.
- `project-structure-v3-job`: scheduled task handlers without an independent startup class.
- `project-structure-v3-web`: the only Spring Boot startup module and API entrypoint.

## Local Startup

`project-structure-v3-web` is the only startup module.

Configure PostgreSQL through environment variables or the defaults in `application-dev.yml`:

```bash
mvn -pl project-structure-v3-web -am spring-boot:run
```

## Build Command

```bash
mvn package
```

## Configuration

- `project-structure-v3-web/src/main/resources/application.yml` only activates the `dev` profile.
- `project-structure-v3-web/src/main/resources/application-dev.yml` contains minimal local startup configuration.
- Sensitive configuration must be provided by environment variables and must not be committed.

## Flyway Migration

Migration directory:

```text
project-structure-v3-dao/src/main/resources/db/migration/
```

Naming rule:

```text
Vversion__description.sql
```

Current user table migration:

```text
V1__init_user_table.sql
```

## User API

- `POST /api/users`: create a user.
- `PUT /api/users/{id}`: update a user.
- `DELETE /api/users/{id}`: delete a user.
- `GET /api/users/{id}`: get user detail.
- `GET /api/users?pageNum=1&pageSize=10&keyword=tom`: query users by page.
