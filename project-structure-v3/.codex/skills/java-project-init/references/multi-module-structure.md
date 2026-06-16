# Multi-Module Structure Reference

## Module List

Use a Maven multi-module structure by default. The repository root acts as the parent POM and aggregator project. It must not contain business source code.

Use `<artifact-prefix>-<module-role>` for module names. If the user does not provide a project prefix, derive it from `artifactId`; if it cannot be derived, use `xxx` as a placeholder and say it must be replaced.

The root `pom.xml` contains these modules by default:

```xml
<modules>
    <module>xxx-base-core</module>
    <module>xxx-common-api</module>
    <module>xxx-framework</module>
    <module>xxx-dao</module>
    <module>xxx-infra</module>
    <module>xxx-module-system</module>
    <module>xxx-job</module>
    <module>xxx-web</module>
</modules>
```

## Module Responsibilities

- `xxx-base-core`: base core module for global exceptions, unified `Result`, base utilities, constants, and other low-level shared code.
- `xxx-common-api`: shared API module for cross-module DTOs, enums, Feign/RPC interface definitions, and communication contracts.
- `xxx-framework`: technical framework module for Spring Boot starters, AOP aspects, security configuration, data permission support, and common technical capabilities.
- `xxx-dao`: data access module for Entity/DO classes, Mapper interfaces, and XML files. Persistence access must use the MyBatis-Plus mapper layer.
- `xxx-infra`: infrastructure module for Redis, MQ, OSS, SMS, email, and other third-party integrations.
- `xxx-module-system`: system business module for users, permissions, roles, authentication, and platform base capabilities. Use Controller -> Service -> dao/infra layering.
- `xxx-job`: scheduled task module for XXL-JOB handlers, task parameter models, and task orchestration. It must not contain an independent startup entrypoint.
- `xxx-web`: the only startup module. It contains the Spring Boot application entrypoint, API aggregation layer, and XXL-JOB Executor configuration.

## Module Dependency Rules

- `xxx-base-core` stays clean at the bottom and must not depend on any business or technical module.
- `xxx-common-api` may depend on `xxx-base-core`; it must not depend on `framework`, `dao`, `infra`, `module-*`, `job`, or `web`.
- `xxx-framework` may depend on `xxx-base-core` and optionally `xxx-common-api`; it must not depend on `dao`, `infra`, `module-*`, `job`, or `web`.
- `xxx-dao` may depend on `xxx-base-core` and optionally `xxx-common-api`; it must not depend on `infra`, `framework`, `module-*`, `job`, or `web`.
- `xxx-infra` may depend on `xxx-base-core` and optionally `xxx-common-api`; it must not depend on `dao`, `framework`, `module-*`, `job`, or `web`.
- `xxx-module-system` may depend on `xxx-base-core`, `xxx-common-api`, `xxx-framework`, `xxx-dao`, and `xxx-infra`.
- `xxx-job` contains task handling code only. It may depend on needed `module-*`, `xxx-framework`, `xxx-common-api`, and `xxx-base-core`; it must not depend on `xxx-web`.
- `xxx-web` is the only startup module. It may depend on `module-*`, `xxx-job`, `xxx-framework`, `xxx-common-api`, and `xxx-base-core`.
- Startup modules must use minimum dependencies according to entrypoint responsibility. Do not depend on every module for convenience.
- Circular dependencies are forbidden.

## Directory Layout

Production source code for modules with code goes under:

```text
<module>/src/main/java/<base-package>/
```

Resources go under:

```text
<module>/src/main/resources/
```

Use these package directories as needed. Do not create empty directories just to complete the list:

```text
config/
controller/
service/
service/impl/
mapper/
domain/
dto/
exception/
common/
util/
```

Rules:

- Put the main Spring Boot application class under the base package of `xxx-web`.
- Table/resource services use the Service interface + Impl class pattern by default: `service/` contains interfaces and `service/impl/` contains implementations.
- Aggregation/orchestration services may live directly under `service/` unless they need multiple implementations, cross-module contracts, or stable proxy extension.
- `mapper/` is the MyBatis-Plus data access entrypoint. Do not create it when the project has no persistence need.
- Mapper names use `XxxMapper` and extend `BaseMapper<Xxx>`. Do not create `XxxRepository` or use Spring Data Repository style.
- Flyway scripts go under `xxx-dao/src/main/resources/db/migration/` by default.
- Do not use directory layout to hide responsibility problems. Delegate complex layering decisions to an architecture skill.

## Initialization Artifacts

Create or supplement as needed:

- `pom.xml`
- `xxx-base-core/pom.xml`
- `xxx-common-api/pom.xml`
- `xxx-framework/pom.xml`
- `xxx-dao/pom.xml`
- `xxx-infra/pom.xml`
- `xxx-module-system/pom.xml`
- `xxx-job/pom.xml`
- `xxx-web/pom.xml`
- `src/main/java/<base-package>/...` for modules with source code
- `xxx-web/src/main/resources/application.yml`
- `xxx-web/src/main/resources/application-dev.yml`
- `xxx-dao/src/main/resources/db/migration/`
