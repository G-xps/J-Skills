# J-Skills

> Java开发工程师的友好帮手，一站式涵盖底层认知、工程规范、架构思维、安全性能与质量意识的核心 Skill 集合。

---

## 技能概览

### java-project-init — 项目初始化

从零初始化一个空白 Java 仓库，严格按 Spring Boot Maven 多模块结构搭建。包含父 POM 和单模块 `xxx-web`、常用依赖（Spring Boot Starter、MyBatis-Plus、PostgreSQL Driver、Flyway、Lombok、SpringDoc OpenAPI 等）、统一响应封装骨架、全局异常处理、Flyway 迁移目录等。使用频率低但重量级，不用于日常功能开发或增量改动。

### java-database-design — 数据库表设计

专注于为 Java 后端项目设计 PostgreSQL + Flyway + MyBatis-Plus 的表结构。覆盖字段类型选择、主键策略、索引、唯一约束、审计字段（`create_time` / `update_time` / `create_by` / `update_by`）、逻辑删除、关联关系建模、业务冗余字段以及 Flyway DDL 迁移脚本的编写。不处理 Java 实体或业务代码实现。

### java-coding-standards — 编码规范指导

在已有 Java/Spring Boot/MyBatis-Plus 项目中进行功能开发、重构或添加测试时使用。覆盖分层实现规范，包括 Controller、DTO、Service、Mapper、Entity、配置、异常、日志、测试等各层面。约束包括：默认使用构造器注入、禁止 Mapper 方法的 Map 入参/出参、`@RequestParam` 和 `@PathVariable` 必须显式命名、不可变 DTO 优先使用 `record`、Service 接口命名 `IXXXService` / 实现 `XXXServiceImpl` 等。不处理项目初始化、数据库表设计或代码风格事后审查。

### java-style-guide — 代码风格审查

轻量级的事后代码风格审查技能，用中文点评。聚焦命名清晰度、注释与 Javadoc、文件组织与成员顺序、格式化、Stream/Lambda 可读性、Null 处理语义、异常与日志文本可读性、代码异味等。默认只审查当前任务中修改过的文件，不做全局格式化。不改动业务语义，不评判业务逻辑或架构正确性。

---

## 使用流程

这四个技能覆盖一个 Java 项目的完整生命周期：

```
项目初始化 (java-project-init)
    ↓
数据库表设计 (java-database-design)
    ↓
编码实现 (java-coding-standards)
    ↓
代码风格审查 (java-style-guide)
```

## 目录结构

```
J-Skills/
├── README.md
├── AGENT.md
├── LICENSE
├── skills/              # 英文版技能定义
│   ├── java-project-init/
│   ├── java-database-design/
│   ├── java-coding-standards/
│   └── java-style-guide/
└── skills-zh/           # 中文版技能定义
```
