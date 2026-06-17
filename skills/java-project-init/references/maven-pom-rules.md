# Maven POM Rules

## Naming Rules

- `groupId` defaults to `com.jskills`.
- `artifactId` uses lowercase kebab-case, for example `j-skill-demo`.
- `base package` is derived from `com.jskills` and the normalized project name, for example `com.jskills.jskilldemo`.
- Module names use `<artifact-prefix>-<module-role>`, for example `demo-web` and `demo-dao`.
- Do not use demo package names such as `org.example`, `com.example`, `example`, `demo`, or `sample` as final package names.

## Root POM

The root `pom.xml` acts as both parent POM and aggregator POM:

- `packaging` must be `pom`.
- It only manages `modules`, `properties`, `dependencyManagement`, and `pluginManagement`.
- It must not contain Java source code or business implementation dependencies.
- It centrally declares versions for JDK, Spring Boot, MyBatis-Plus, PostgreSQL, Flyway, Lombok, and related build plugins.
- It centrally declares the SpringDoc OpenAPI 2.x version. Spring Boot 3.4.5 defaults to `2.8.x`; do not use SpringFox.
- It centrally declares the Lombok version. Default to the current stable `1.18.x` line; the example uses `1.18.46`.

Example:

```xml
<properties>
    <lombok.version>1.18.46</lombok.version>
</properties>

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

## Module POMs

Each module `pom.xml` declares only dependencies needed by that module:

- Do not repeat versions already managed by the root POM.
- Do not put every dependency into every module.
- Modules that use Lombok annotations must explicitly declare `org.projectlombok:lombok` with `provided` scope.
- `xxx-web` is the only startup module and owns Spring Boot packaging plus the application entrypoint.
- `xxx-web` declares `springdoc-openapi-starter-webmvc-ui` for Swagger UI and OpenAPI documentation.
- `xxx-job` must not declare independent startup plugin configuration.
- `xxx-dao` declares data access dependencies such as MyBatis-Plus (including `mybatis-plus-spring-boot3-starter` and `mybatis-plus-jsqlparser`), PostgreSQL, and Flyway.

## Startup Module Packaging Plugin

`xxx-web/pom.xml` must add `spring-boot-maven-plugin` by default; otherwise, the jar produced by a multi-module build may not be directly executable.

Rules:

- Configure the Spring Boot packaging plugin only in `xxx-web`.
- Explicitly configure `mainClass` to point to the only Spring Boot application class.
- Configure the `repackage` goal to produce an executable jar.
- Non-startup modules must not configure `spring-boot-maven-plugin` repackage.

Example:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <version>${spring-boot.version}</version>
            <configuration>
                <mainClass>com.jskills.xxx.XxxApplication</mainClass>
            </configuration>
            <executions>
                <execution>
                    <goals>
                        <goal>repackage</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

## Dependency Declaration Principles

- Declare dependencies according to module responsibility and keep them minimal.
- The parent POM manages versions; module POMs declare usage.
- Do not use the parent POM to implicitly give every module unnecessary dependencies.
- The Spring Boot plugin should apply only to the startup module.
- The SpringDoc OpenAPI version is managed only in the parent POM; the Web module only declares usage.
- Use `springdoc-openapi-starter-webflux-ui` only for WebFlux projects. WebMVC projects use `springdoc-openapi-starter-webmvc-ui` by default.
- The Lombok version is managed only in the parent POM; modules declare it only when needed. Lombok must not be packaged as a runtime dependency.
- If the project explicitly configures `maven-compiler-plugin` `annotationProcessorPaths`, add Lombok to the annotation processor path so command-line builds are stable.

## Forbidden Items

Do not introduce these alternative stacks during initialization:

- Do not use `spring-boot-starter-data-jpa`.
- Do not use Hibernate Repository or Spring Data Repository.
- Do not use H2 as the default runtime database.
- Do not use `springfox-swagger2`, `springfox-boot-starter`, or any other SpringFox dependency.
- Do not declare Lombok as a default runtime dependency or package it into build artifacts.
- Do not use `org.example`, `com.example`, `example`, `demo`, or `sample` as final Java package names.
- Do not generate a single-module dependency structure for CRUD convenience.
- Do not configure executable Spring Boot jar packaging in modules other than `xxx-web`.

