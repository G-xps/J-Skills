---
name: java-project-init
description: Initialize a strict Spring Boot Maven multi-module project from scratch, or establish a Java project skeleton and project-level conventions for an almost-empty repository. Use for Maven multi-module structure, module responsibility boundaries, directory layout, base dependencies, base configuration, Flyway migration conventions, README, .gitignore, build/run instructions, and initialization-time engineering rules. Once triggered, this skill must use JDK 17, Spring Boot 3.4.5, Maven multi-module, MyBatis-Plus, PostgreSQL, Flyway, and a single xxx-web startup module. Do not convert the project into a single-module app, JPA/Hibernate Repository app, or H2 demo. Do not use for routine feature development, style cleanup, small refactors, bug fixes, single Controller/Service/DTO/Entity changes, or minor config edits in an established project.
---

# java-project-init

## Purpose

This is a heavy, low-frequency, strict Java project initialization skill.

Use it to establish the project skeleton and project-level conventions at the beginning of a project, so later development has a stable module structure, build path, and startup path. This skill is not for routine feature development.

Default stack:

- JDK 17
- Spring Boot 3.4.5
- Maven 3.13.0
- Maven multi-module
- MyBatis-Plus 3.5.12
- PostgreSQL 42.2.25
- Flyway

## Hard Constraints

After this skill triggers, follow these constraints:

- Initialize the project as a Maven multi-module project. Do not fall back to a single-module Spring Boot project.
- Use the MyBatis-Plus mapper layer. Do not use JPA, Hibernate Repository, or Spring Data Repository.
- Use PostgreSQL as the default database driver. Do not switch to H2 for demo convenience.
- Use Flyway for database migration directories. Do not place migration scripts in a single-module root resources directory.
- Create only `xxx-web` as the Spring Boot startup module. Do not add an independent startup class to `xxx-job`.
- Read `references/multi-module-structure.md`, `references/maven-pom-rules.md`, `references/config-files.md`, `references/readme-template.md`, and `references/gitignore-rules.md` before creating the project skeleton.
- If the user asks for business functionality in an empty project, first create a compliant skeleton with this skill, then implement the functionality inside that skeleton. Do not choose another stack for speed.

## When To Use

Use only when:

- The user explicitly asks to initialize a Java, Spring Boot, or Maven project.
- The current repository is empty, almost empty, or has only a few initialization files.
- The user asks to establish project-level engineering conventions, a multi-module structure, directory layout, base dependencies, startup configuration, or database migration conventions.
- The user asks to turn an unformed project into a standard Spring Boot Maven multi-module skeleton.

Do not use when:

- The task is only to add one interface, entity, DTO, service, or controller.
- The task is only to fix a bug, adjust business logic, or do a small refactor.
- The task is only to clean up Java code style.
- The task is only to edit a small amount of configuration in an existing project.
- The project already has clear engineering conventions and the user did not ask to re-initialize it.

## Workflow

1. Determine whether the project is in an initialization stage.
2. Inspect the existing build tool, Java version, package name, and directory structure.
3. If the project already has conventions, follow them. If not, use this skill's default conventions.
4. Read `references/multi-module-structure.md` and create the skeleton according to the module structure, module responsibilities, dependency rules, and directory layout.
5. Read `references/maven-pom-rules.md` and configure the root POM, module POMs, dependency management, and plugin management.
6. Read `references/config-files.md` and create `application.yml` plus `application-dev.yml`.
7. Read `references/readme-template.md` and `references/gitignore-rules.md`, then add README, `.gitignore`, and required run instructions.
8. Verify that the project has clear build and startup paths.
9. After initialization, optionally use `java-style-guide` for light cleanup of newly added Java source code.

## References

Read the relevant reference files for the initialization work:

- `references/multi-module-structure.md`: Maven module list, module responsibilities, dependency rules, directory layout, and initialization artifacts.
- `references/maven-pom-rules.md`: root POM, module POMs, naming rules, dependency management, and plugin management.
- `references/config-files.md`: `application.yml`, `application-dev.yml`, sensitive config, and minimal startup config.
- `references/readme-template.md`: required README sections and startup instructions.
- `references/gitignore-rules.md`: ignore rules for Java, Maven, IDEs, logs, and local environment files.

Do not duplicate detailed reference rules in this `SKILL.md`.

## Maven And Spring Boot Rules

- Use Maven by default.
- The root `pom.xml` acts as both the parent POM and aggregator POM. It only manages module aggregation, versions, dependency management, and plugin configuration. It must not contain business code.
- Each module `pom.xml` declares only dependencies required by that module. Do not put every dependency in every module.
- Keep dependencies minimal. Add only dependencies required for project startup.
- Add `spring-boot-starter-web` when Web capability is needed. Do not add it when the project has no Web requirement.
- Put XXL-JOB executor configuration in `xxx-web`; `xxx-web` starts it together with the Web service. `xxx-job` only provides task handling code.
- Add data access starter/driver dependencies only when data access is needed.
- When PostgreSQL is used, add Flyway-related dependencies and prefer Spring Boot managed versions.
- Provide a base `application.yml` only to activate the `dev` profile.
- Add `application-dev.yml` for minimal startup configuration.
- Prefer adding Maven Wrapper. If it is not added, say that the local Maven installation is used.

## Flyway Migration Rules

When initialization involves a database, use Flyway for database schema changes.

Rules:

- Put migration scripts in `xxx-dao/src/main/resources/db/migration/` by default.
- Use `Vversion__description.sql` names, for example `V1__init_schema.sql`.
- Migration versions only increase. Do not edit migrations that have already been released or executed.
- During initialization, create an empty migration directory if no table structure is specified. Generate concrete DDL only when the user explicitly provides business table structure.
- Do not invent business columns, indexes, constraints, or seed data.
- Keep the Flyway base configuration location in `application-dev.yml`; fill database connection details according to the project environment.
- Database schema design and migration review are not deeply covered by this skill. Delegate them to persistence or database migration skills later.

## Build And Startup Rules

After initialization, the project must have clear build and startup paths:

- `mvn package` must work, or the build command must be clearly documented.
- README must document common commands such as build and startup.
- Start only one Spring Boot service: `xxx-web`. The XXL-JOB Executor starts with `xxx-web`.
- The initialization goal is: buildable, startable, and structurally clear.
- Do not bind formatter, linter, Checkstyle, or Spotless by default unless the user explicitly asks.

## Artifacts

Create multi-module POM files, module source directories, `application.yml`, `application-dev.yml`, and the Flyway migration directory according to `references/multi-module-structure.md`.

Also add as needed:

- `.gitignore`
- `README.md`
- Maven Wrapper, or explain why it was not added

Do not create:

- Empty Controller, Service, Mapper, DTO, or Entity classes without business meaning.
- An independent Spring Boot startup class in `xxx-job`.
- Database, cache, message queue, or security framework dependencies unrelated to the current project.
- Flyway DDL or seed scripts without business basis.
- Large demo code unless the user explicitly asks for a demo.

## Relationship With java-style-guide

`java-project-init` establishes project-level rules and the engineering skeleton.

`java-style-guide` performs light cleanup for routine Java code changes.

After initialization, if new Java source code was added, use `java-style-guide` to check naming, formatting, comments, file organization, member order, readability, and lightweight code smells.

## Output Requirements

When using this skill, report:

- The chosen Java, Spring Boot, and Maven conventions.
- Which Maven modules and project skeleton were created or adjusted.
- Which base dependencies were added.
- Whether Flyway was enabled, including migration directory and naming convention.
- How to run the build command and how to start the single `xxx-web` service.
- Whether Maven Wrapper was added; if not, explain why.
- What was intentionally deferred, such as database design, security, cache, CI, or code formatting tools.
