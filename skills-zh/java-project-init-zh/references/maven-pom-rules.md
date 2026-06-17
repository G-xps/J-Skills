# Maven POM 规则

## 命名规则

- `groupId` 默认使用 `com.jskills`。
- `artifactId` 使用小写短横线，例如 `j-skill-demo`。
- `base package` 从 `com.jskills` 和规范化后的项目名推导，例如 `com.jskills.jskilldemo`。
- 模块名使用 `<artifact-prefix>-<module-role>`，例如 `demo-web` 和 `demo-dao`。
- 不要使用 `org.example`、`com.example`、`example`、`demo` 或 `sample` 作为最终包名。

## 根 POM

根 `pom.xml` 同时作为父 POM 和聚合 POM：

- `packaging` 必须为 `pom`。
- 只管理 `modules`、`properties`、`dependencyManagement` 和 `pluginManagement`。
- 不得包含 Java 源代码或业务实现依赖。
- 统一声明 JDK、Spring Boot、MyBatis-Plus、PostgreSQL、Flyway、Lombok 和构建插件的版本。
- 统一声明 SpringDoc OpenAPI 2.x 版本。Spring Boot 3.4.5 默认使用 `2.8.x`；不要使用 SpringFox。
- 统一声明 Lombok 版本。默认使用当前稳定 `1.18.x` 系列；示例使用 `1.18.46`。

示例：

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

## 模块 POM

每个模块的 `pom.xml` 只声明本模块必需的依赖：

- 不要重复声明根 POM 已管理的版本。
- 不要将所有依赖都放入每个模块。
- 使用 Lombok 注解的模块必须显式声明 `org.projectlombok:lombok`，scope 为 `provided`。
- `xxx-web` 是唯一的启动模块，拥有 Spring Boot 打包和应用入口。
- `xxx-web` 声明 `springdoc-openapi-starter-webmvc-ui` 用于 Swagger UI 和 OpenAPI 文档。
- `xxx-job` 不得声明独立启动插件配置。
- `xxx-dao` 声明数据访问依赖，如 MyBatis-Plus（包括 `mybatis-plus-spring-boot3-starter` 和 `mybatis-plus-jsqlparser`）、PostgreSQL 和 Flyway。

## 启动模块打包插件

`xxx-web/pom.xml` 必须默认添加 `spring-boot-maven-plugin`，否则多模块构建出来的 jar 可能无法直接执行。

规则：

- 只在 `xxx-web` 中配置 Spring Boot 打包插件。
- 显式配置 `mainClass` 指向唯一的 Spring Boot 应用类。
- 配置 `repackage` goal 以生成可执行 jar。
- 非启动模块不得配置 `spring-boot-maven-plugin` 的 repackage。

示例：

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

## 依赖声明原则

- 按模块职责声明依赖，保持最小化。
- 父 POM 管理版本；模块 POM 声明使用。
- 不要通过父 POM 隐式给所有模块传递不必要依赖。
- Spring Boot 插件只适用于启动模块。
- SpringDoc OpenAPI 版本只在父 POM 管理；Web 模块只声明使用。
- WebFlux 项目使用 `springdoc-openapi-starter-webflux-ui`；WebMVC 项目默认使用 `springdoc-openapi-starter-webmvc-ui`。
- Lombok 版本只在父 POM 管理；模块只在需要时声明。Lombok 不得作为运行时依赖打包。
- 如果项目显式配置了 `maven-compiler-plugin` 的 `annotationProcessorPaths`，应将 Lombok 添加到注解处理器路径中，以确保命令行构建稳定。

## 禁止项

初始化期间不得引入以下替代技术栈：

- 不要使用 `spring-boot-starter-data-jpa`。
- 不要使用 Hibernate Repository 或 Spring Data Repository。
- 不要使用 H2 作为默认运行时数据库。
- 不要使用 `springfox-swagger2`、`springfox-boot-starter` 或任何 SpringFox 依赖。
- 不要将 Lombok 声明为默认运行时依赖或打包到构建产物中。
- 不要使用 `org.example`、`com.example`、`example`、`demo` 或 `sample` 作为最终 Java 包名。
- 不要为了 CRUD 便利生成单模块依赖结构。
- 不要在 `xxx-web` 以外的模块配置可执行的 Spring Boot jar 打包。