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
- It centrally declares versions for JDK, Spring Boot, MyBatis-Plus, PostgreSQL, Flyway, and related build plugins.

## Module POMs

Each module `pom.xml` declares only dependencies needed by that module:

- Do not repeat versions already managed by the root POM.
- Do not put every dependency into every module.
- `xxx-web` is the only startup module and owns Spring Boot packaging plus the application entrypoint.
- `xxx-job` must not declare independent startup plugin configuration.
- `xxx-dao` declares data access dependencies such as MyBatis-Plus, PostgreSQL, and Flyway.

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

## Forbidden Items

Do not introduce these alternative stacks during initialization:

- Do not use `spring-boot-starter-data-jpa`.
- Do not use Hibernate Repository or Spring Data Repository.
- Do not use H2 as the default runtime database.
- Do not use `org.example`, `com.example`, `example`, `demo`, or `sample` as final Java package names.
- Do not generate a single-module dependency structure for CRUD convenience.
- Do not configure executable Spring Boot jar packaging in modules other than `xxx-web`.
