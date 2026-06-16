---
name: java-project-init-zh
description: java-project-init 的中文版本；用于将空或接近空的 Java 仓库初始化为严格的 Spring Boot Maven 多模块项目，采用 MyBatis-Plus、PostgreSQL、Flyway 和单 xxx-web 启动模块；不用于日常功能开发、风格整理、bugfix 或已有项目的小改动。
---

# java-project-init

## 核心定位

本 skill 是重型、低频、强约束的 Java 项目初始化 skill。

它负责在项目起步阶段建立工程骨架和基础约定，让后续开发有稳定的目录结构、构建方式和启动方式。它不负责日常功能开发。

默认技术栈：

- JDK 17
- Spring Boot 3.4.5
- Maven 3.13.0
- Maven 多模块
- Mybatis-plus 3.5.12
- Postgresql 42.2.25
- Flyway

## 硬性约束

触发本 skill 后必须遵守以下约束：

- 必须按 Maven 多模块结构初始化项目，不得退化为单模块 Spring Boot 项目。
- 必须使用 MyBatis-Plus mapper 层，不得改用 JPA、Hibernate Repository 或 Spring Data Repository。
- 必须以 PostgreSQL 作为默认数据库驱动，不得为了演示方便改用 H2。
- 必须使用 Flyway 管理数据库迁移目录，不得把迁移脚本放在单模块根资源目录。
- 必须只创建 `xxx-web` 一个 Spring Boot 启动模块；`xxx-job` 不得包含独立启动类。
- 必须先读取 `references/multi-module-structure.md`、`references/maven-pom-rules.md`、`references/config-files.md`、`references/readme-template.md` 和 `references/gitignore-rules.md`，再开始创建项目骨架。
- 如果用户在空项目中直接要求实现业务功能，应先按本 skill 建立合规骨架，再在该骨架内实现功能；不得为了快速完成业务功能自行选择其他技术栈。

## 触发边界

只在以下场景使用：

- 用户明确要求初始化 Java 项目、Spring Boot 项目或 Maven 项目。
- 当前仓库是空项目、接近空项目，或只有少量初始化文件。
- 用户要求建立项目级工程规范、多模块结构、目录结构、基础依赖、启动配置或数据库迁移规范。
- 用户要求把一个未成形项目整理为标准 Spring Boot Maven 多模块骨架。

不要在以下场景使用：

- 只是新增一个接口、实体、DTO、Service 或 Controller。
- 只是修复 bug、调整业务逻辑或做小范围重构。
- 只是整理 Java 代码风格。
- 只是修改已有项目中的少量配置。
- 项目已经有明确工程规范，且用户没有要求重新初始化。

## 初始化流程

1. 先判断项目是否处于初始化阶段。
2. 识别现有构建工具、Java 版本、包名和目录结构。
3. 若项目已存在约定，优先沿用；若没有约定，按本 skill 的默认约定建立骨架。
4. 读取 `references/multi-module-structure.md`，按多模块结构、模块职责、依赖规则和目录结构创建骨架。
5. 读取 `references/maven-pom-rules.md`，配置根 POM、子模块 POM、依赖管理和插件管理。
6. 读取 `references/config-files.md`，创建 `application.yml` 与 `application-dev.yml`。
7. 读取 `references/readme-template.md` 和 `references/gitignore-rules.md`，补充 README、`.gitignore` 和必要的启动说明。
8. 验证项目至少具备构建和启动的清晰路径。
9. 初始化完成后，可使用 `java-style-guide` 对新增 Java 源码做轻量收尾。

## 参考文件

按初始化内容读取对应参考文件：

- `references/multi-module-structure.md`：Maven 模块清单、模块职责、依赖规则、目录结构和初始化产物。
- `references/maven-pom-rules.md`：根 POM、子模块 POM、命名规则、依赖管理和插件管理。
- `references/config-files.md`：`application.yml`、`application-dev.yml`、敏感配置和最小启动配置。
- `references/readme-template.md`：README 必备章节和启动说明。
- `references/gitignore-rules.md`：Java、Maven、IDE、日志和本地环境忽略规则。

主 `SKILL.md` 不重复维护参考文件细节，避免规则分散。

## Maven 与 Spring Boot 约定

- 默认使用 Maven 构建。
- 根 `pom.xml` 同时作为父 POM 和聚合 POM，只用于统一模块聚合、版本管理、依赖管理和插件配置，不包含业务代码。
- 各模块 `pom.xml` 只声明本模块必需依赖，避免所有依赖都堆在父 POM。
- 依赖保持最小化，只加入项目启动需要的依赖。
- 需要 Web 能力时加入 `spring-boot-starter-web`；没有 Web 需求时不要默认加入。
- XXL-JOB 执行器配置放在 `xxx-web`，由 `xxx-web` 随同 Web 服务一起启动；`xxx-job` 只提供任务处理代码。
- 需要数据访问时再加入对应 starter 或驱动，不提前堆依赖。
- 使用 PostgreSQL 时，默认加入 Flyway 相关依赖，并优先使用 Spring Boot 管理的依赖版本。
- 默认提供基础 `application.yml`，只用于指定启用 `dev` profile。
- 默认新增 `application-dev.yml`，只放项目启动所需的最小配置。
- Maven Wrapper 可优先添加；如果未添加，应在输出中说明使用本机 Maven。

## Flyway 数据库迁移约定

初始化涉及数据库时，默认使用 Flyway 管理数据库结构变更。

约定：

- 迁移脚本默认放在 `xxx-dao/src/main/resources/db/migration/`。
- 脚本命名使用 `V版本号__描述.sql`，例如 `V1__init_schema.sql`。
- 版本号只递增，不修改已发布或已执行过的迁移脚本。
- 初始化阶段可以创建空迁移目录；只有用户明确给出业务表结构时，才生成具体建表脚本。
- 不把业务字段、索引、约束和初始化数据凭空写入迁移脚本。
- `application-dev.yml` 中保留 Flyway 的基础配置位置，具体数据库连接信息按项目环境填写。
- 数据库结构设计和迁移脚本内容审查不在本 skill 深入展开，可交给后续持久化或数据库迁移类 skill。

## 构建与启动约定

初始化后应具备清晰的构建和启动路径：

- 至少能运行 `mvn package` 或明确说明构建命令。
- README 中应说明常用命令，例如构建和启动。
- 只启动 `xxx-web` 一个 Spring Boot 服务；XXL-JOB Executor 随 `xxx-web` 启动。
- 初始化目标以“能构建、能启动、结构清晰”为主。
- 不默认绑定具体 formatter、linter、Checkstyle 或 Spotless，除非用户明确要求。

## 初始化产物

按 `references/multi-module-structure.md` 创建多模块 POM、模块源码目录、`application.yml`、`application-dev.yml` 和 Flyway 迁移目录。

同时按项目需要补充：

- `.gitignore`
- `README.md`
- Maven Wrapper，或说明未添加

不要创建：

- 无业务意义的空 Controller、Service、Mapper、DTO 或 Entity。
- `xxx-job` 中的独立 Spring Boot 启动类。
- 与当前项目无关的数据库、缓存、消息队列、安全框架依赖。
- 没有业务依据的 Flyway 建表脚本或初始化数据脚本。
- 大量示例代码，除非用户明确要求生成 demo。

## 与 java-style-guide 的关系

`java-project-init` 负责建立项目级规则和工程骨架。

`java-style-guide` 负责日常 Java 代码修改后的轻量风格收尾。

初始化完成后，如果新增了 Java 源码，可以再使用 `java-style-guide` 检查命名、排版、注释、文件组织、成员顺序、可读性和轻量代码异味。

## 输出要求

使用本 skill 完成初始化时，应说明：

- 选择的 Java、Spring Boot 和 Maven 约定。
- 创建或调整了哪些 Maven 模块和工程骨架。
- 添加了哪些基础依赖。
- 是否启用 Flyway，以及迁移脚本目录和命名约定。
- 如何运行构建命令，以及如何启动唯一的 `xxx-web` 服务。
- 是否添加 Maven Wrapper；如果没有，说明原因。
- 哪些内容被刻意延后，例如数据库、安全、缓存、CI 或代码格式化工具。
