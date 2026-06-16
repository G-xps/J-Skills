# Maven POM 规则

## 命名规则

- `groupId` 默认使用 `com.jskills`。
- `artifactId` 使用小写短横线，例如 `j-skill-demo`。
- `base package` 从 `com.jskills` 和规范化后的项目名推导，例如 `com.jskills.jskilldemo`。
- 模块名使用 `<artifact-prefix>-<module-role>`，例如 `demo-web`、`demo-dao`。
- 不得使用 `org.example`、`com.example`、`example`、`demo`、`sample` 这类演示包名作为最终包名。

## 根 POM

根 `pom.xml` 同时作为父 POM 和聚合 POM：

- `packaging` 必须为 `pom`。
- 只管理 `modules`、`properties`、`dependencyManagement` 和 `pluginManagement`。
- 不包含 Java 源码，不放业务依赖实现。
- 统一声明 JDK、Spring Boot、MyBatis-Plus、PostgreSQL、Flyway 等版本。

## 子模块 POM

各模块 `pom.xml` 只声明本模块需要的依赖：

- 不重复声明已由根 POM 管理的版本号。
- 不把所有依赖堆到所有模块。
- `xxx-web` 是唯一可启动模块，负责 Spring Boot 打包和启动入口。
- `xxx-job` 不声明独立启动插件配置。
- `xxx-dao` 声明 MyBatis-Plus、PostgreSQL、Flyway 等数据访问相关依赖。

## 启动模块打包插件

`xxx-web/pom.xml` 必须默认添加 `spring-boot-maven-plugin`，否则多模块项目打包后的 jar 可能无法直接启动。

约定：

- 只在 `xxx-web` 配置 Spring Boot 打包插件。
- 显式配置 `mainClass`，指向唯一 Spring Boot 启动类。
- 配置 `repackage` goal，生成可执行 jar。
- 其他非启动模块不得配置 `spring-boot-maven-plugin` 的 repackage。

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

- 依赖按模块职责最小化声明。
- 父 POM 管版本，子模块管使用。
- 禁止通过父 POM 让所有模块隐式获得不需要的依赖。
- Spring Boot 插件只应作用于可启动模块。

## 禁止项

初始化项目时不得引入以下替代技术栈：

- 不使用 `spring-boot-starter-data-jpa`。
- 不使用 Hibernate Repository 或 Spring Data Repository。
- 不使用 H2 作为默认运行数据库。
- 不使用 `org.example`、`com.example`、`example`、`demo`、`sample` 这类演示包名作为最终 Java 包名。
- 不为了演示 CRUD 生成单模块依赖结构。
- 不在非 `xxx-web` 模块配置 Spring Boot 可执行 jar 打包。
