# 多模块结构参考

## 模块清单

默认使用 Maven 多模块结构。仓库根目录作为父 POM 和聚合工程，不得包含业务源码。

模块名使用 `<artifact-prefix>-<module-role>`。如果用户没有提供项目前缀，从 `artifactId` 推导；无法推导时使用 `xxx` 作为占位前缀并在输出中说明需要替换。

根 `pom.xml` 默认包含以下模块：

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

## 模块职责

- `xxx-base-core`：基础核心模块，用于全局异常、统一 `Result`、基础工具类、常量和其他底层共享代码。
- `xxx-common-api`：公共 API 模块，用于跨模块的 DTO、枚举、Feign/RPC 接口定义和通信契约。
- `xxx-framework`：技术框架模块，用于 Spring Boot starter、AOP 切面、安全配置、数据权限支持和通用技术能力。
- `xxx-dao`：数据访问模块，用于 Entity/DO 类、Mapper 接口和 XML 文件。持久化访问必须使用 MyBatis-Plus mapper 层。MyBatis-Plus 配置（如带有 `PaginationInnerInterceptor` 的 `MybatisPlusConfig`）归属于此模块；因此 `xxx-dao` 除了 `mybatis-plus-spring-boot3-starter` 外，还需声明 `mybatis-plus-jsqlparser`。
- `xxx-infra`：基础设施模块，用于 Redis、MQ、OSS、SMS、邮件等第三方集成。
- `xxx-module-system`：系统业务模块，用于用户、权限、角色、认证和平台基础能力。使用 Controller -> Service -> dao/infra 分层。
- `xxx-job`：定时任务模块，用于 XXL-JOB 处理器、任务参数模型和任务编排。不得包含独立启动入口。
- `xxx-web`：唯一的启动模块。包含 Spring Boot 应用入口、API 聚合层和 XXL-JOB Executor 配置。

## 模块依赖规则

- `xxx-base-core` 保持在最底层，不得依赖任何业务或技术模块。
- `xxx-common-api` 可以依赖 `xxx-base-core`；不得依赖 `framework`、`dao`、`infra`、`module-*`、`job` 或 `web`。
- `xxx-framework` 可以依赖 `xxx-base-core`，可选地依赖 `xxx-common-api`；不得依赖 `dao`、`infra`、`module-*`、`job` 或 `web`。
- `xxx-dao` 可以依赖 `xxx-base-core`，可选地依赖 `xxx-common-api`；不得依赖 `infra`、`framework`、`module-*`、`job` 或 `web`。
- `xxx-infra` 可以依赖 `xxx-base-core`，可选地依赖 `xxx-common-api`；不得依赖 `dao`、`framework`、`module-*`、`job` 或 `web`。
- `xxx-module-system` 可以依赖 `xxx-base-core`、`xxx-common-api`、`xxx-framework`、`xxx-dao` 和 `xxx-infra`。
- `xxx-job` 只包含任务处理代码。可以依赖所需的 `module-*`、`xxx-framework`、`xxx-common-api` 和 `xxx-base-core`；不得依赖 `xxx-web`。
- `xxx-web` 是唯一的启动模块。可以依赖 `module-*`、`xxx-job`、`xxx-framework`、`xxx-common-api` 和 `xxx-base-core`。
- 启动模块必须按入口职责使用最小依赖，不要为了方便依赖所有模块。
- 禁止循环依赖。

## 目录结构

有代码的模块的源码目录：

```text
<module>/src/main/java/<base-package>/
```

资源目录：

```text
<module>/src/main/resources/
```

Mapper XML 文件目录：

```text
xxx-dao/src/main/resources/mapper/<business-module>/
```

按需使用以下包目录。不要为了凑齐列表而创建空目录：

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

规则：

- Spring Boot 应用启动类放在 `xxx-web` 的基础包下。
- 表/资源 Service 默认使用 Service 接口 + Impl 类模式：`service/` 放接口，`service/impl/` 放实现。
- 聚合/编排 Service 可以直接放在 `service/` 下，除非需要多实现、跨模块契约或稳定的代理扩展。
- `mapper/` 是 MyBatis-Plus 数据访问入口。项目没有持久化需求时不要创建。
- Mapper 名称使用 `XxxMapper`，继承 `BaseMapper<Xxx>`。不要创建 `XxxRepository` 或使用 Spring Data Repository 风格。
- 每个 Mapper 必须有同名 XML 文件，即使还没有自定义 SQL。
- Mapper XML 名使用 `XxxMapper.xml`，放在 `xxx-dao/src/main/resources/mapper/<business-module>/` 下。
- Mapper XML 的 `namespace` 必须等于 Mapper 接口的全限定名。
- 每个 Mapper XML 必须定义 `BaseResultMap`，为所有表字段显式配置 column-to-property 映射。
- 保持空的 Mapper XML 文件有效且准备好编写自定义 SQL，包含 `BaseResultMap`；不要留空的 `<mapper>` 体。
- Flyway 脚本默认放在 `xxx-dao/src/main/resources/db/migration/` 下。
- 不要用目录结构掩盖职责问题。复杂的分层决策交给架构 skill。

Mapper XML 示例：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.jskills.xxx.dao.mapper.UserMapper">
    <resultMap id="BaseResultMap" type="com.jskills.xxx.dao.domain.User">
        <id column="id" property="id"/>
        <result column="username" property="username"/>
        <result column="display_name" property="displayName"/>
        <result column="email" property="email"/>
        <result column="phone" property="phone"/>
        <result column="status" property="status"/>
        <result column="created_at" property="createdAt"/>
        <result column="updated_at" property="updatedAt"/>
        <result column="deleted" property="deleted"/>
    </resultMap>
</mapper>
```

## 初始化产物

按需创建或补充：

- `pom.xml`
- `xxx-base-core/pom.xml`
- `xxx-common-api/pom.xml`
- `xxx-framework/pom.xml`
- `xxx-dao/pom.xml`
- `xxx-infra/pom.xml`
- `xxx-module-system/pom.xml`
- `xxx-job/pom.xml`
- `xxx-web/pom.xml`
- `src/main/java/<base-package>/...`（有源码的模块）
- `xxx-web/src/main/resources/application.yml`
- `xxx-web/src/main/resources/application-dev.yml`
- `xxx-dao/src/main/resources/mapper/<business-module>/XxxMapper.xml`
- `xxx-dao/src/main/resources/db/migration/`
- `xxx-base-core/src/main/java/<base-package>/common/Result.java`
- `xxx-base-core/src/main/java/<base-package>/common/ResultCode.java`
- `xxx-base-core/src/main/java/<base-package>/exception/BusinessException.java`
- `GlobalExceptionHandler.java` 放在 `xxx-framework` 或 `xxx-web` 下
- `OpenApiConfig.java` 放在 `xxx-web` 或 `xxx-framework` 下