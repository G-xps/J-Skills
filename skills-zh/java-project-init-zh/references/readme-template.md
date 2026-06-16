# README 模板

初始化项目时，`README.md` 至少包含以下内容。

## 项目简介

说明项目用途和当前初始化状态。

## 技术栈

列出默认技术栈：

- JDK 17
- Spring Boot 3.4.5
- Maven 3.13.0
- MyBatis-Plus 3.5.12
- PostgreSQL 42.2.25
- Flyway

## 模块说明

简要说明各 Maven 模块职责，保持与 `multi-module-structure.md` 一致。

## 本地启动

说明唯一启动模块是 `xxx-web`，启动命令应指向该模块。

示例：

```bash
mvn -pl xxx-web -am spring-boot:run
```

## 构建命令

示例：

```bash
mvn package
```

## 配置说明

说明：

- `application.yml` 只启用 `dev` profile。
- `application-dev.yml` 放本地启动最小配置。
- 敏感配置不应写入仓库。

## Flyway 迁移

说明迁移目录：

```text
xxx-dao/src/main/resources/db/migration/
```

说明命名规则：

```text
V版本号__描述.sql
```

