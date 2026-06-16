# 多模块结构参考

## 模块清单

默认使用 Maven 多模块结构。根目录只作为父 POM 和聚合工程，不直接放业务代码。

模块命名使用 `<artifact-prefix>-<module-role>`。若用户没有给出项目缩写，先从 artifactId 推导；无法推导时使用 `xxx` 作为占位前缀并在输出中说明需要替换。

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

- `xxx-base-core`：基础核心模块，存放全局异常、统一响应体 `Result`、基础工具类、常量等。
- `xxx-common-api`：公共 API 模块，存放跨模块共享 DTO、枚举、Feign/RPC 接口定义等，用于模块间解耦通信。
- `xxx-framework`：技术框架层，封装 Spring Boot Starter、AOP 切面、安全配置、数据权限等通用技术能力。
- `xxx-dao`：数据访问层，集中管理 Entity/DO、Mapper 接口和 XML 文件，屏蔽底层数据库细节；持久化访问统一使用 MyBatis-Plus 的 mapper 层。
- `xxx-infra`：基础设施层，封装 Redis、MQ、OSS、短信、邮件等第三方中间件对接。
- `xxx-module-system`：系统业务模块，承载用户、权限、角色、认证等平台基础能力，采用 Controller -> Service -> dao/infra 的三层结构。
- `xxx-job`：定时任务模块，管理 XXL-JOB Handler、任务参数模型和任务编排逻辑，不包含独立启动入口。
- `xxx-web`：唯一启动模块，包含 Spring Boot 启动入口、API 聚合层和 XXL-JOB Executor 配置，对外暴露 RESTful 接口并承载定时任务执行器。

## 模块依赖规则

- `xxx-base-core` 保持底层干净，不依赖任何业务模块或技术模块。
- `xxx-common-api` 可依赖 `xxx-base-core`，不依赖 `framework`、`dao`、`infra`、`module-*`、`job` 或 `web`。
- `xxx-framework` 可依赖 `xxx-base-core`，必要时可依赖 `xxx-common-api`；不得依赖 `dao`、`infra`、`module-*`、`job` 或 `web`。
- `xxx-dao` 可依赖 `xxx-base-core`，必要时可依赖 `xxx-common-api`；不得依赖 `infra`、`framework`、`module-*`、`job` 或 `web`。
- `xxx-infra` 可依赖 `xxx-base-core`，必要时可依赖 `xxx-common-api`；不得依赖 `dao`、`framework`、`module-*`、`job` 或 `web`。
- `xxx-module-system` 可依赖 `xxx-base-core`、`xxx-common-api`、`xxx-framework`、`xxx-dao` 和 `xxx-infra`。
- `xxx-job` 只放任务处理代码，可按需依赖 `module-*`、`xxx-framework`、`xxx-common-api` 和 `xxx-base-core`，不得依赖 `xxx-web`。
- `xxx-web` 是唯一启动模块，可依赖 `module-*`、`xxx-job`、`xxx-framework`、`xxx-common-api` 和 `xxx-base-core`。
- 启动模块不得为了方便依赖所有模块，应按实际入口职责最小依赖。
- 严禁模块间循环依赖。

## 目录结构

各含代码模块的生产代码默认位于：

```text
<module>/src/main/java/<base-package>/
```

资源文件默认位于：

```text
<module>/src/main/resources/
```

Mapper XML 文件默认位于：

```text
xxx-dao/src/main/resources/mapper/<business-module>/
```

Spring Boot 项目默认采用以下包结构，按实际需要创建，避免生成空目录堆砌：

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

约定：

- 主启动类放在 `xxx-web` 的 base package 根目录下。
- 表/资源型服务默认采用 Service 接口 + Impl 实现类模式，`service/` 放接口，`service/impl/` 放对应实现。
- 聚合/编排型服务可以直接放在 `service/` 下，除非存在多实现、跨模块契约或需要稳定代理扩展。
- `mapper/` 是 MyBatis-Plus 数据访问入口；没有持久化需求时不要强行创建。
- Mapper 命名使用 `XxxMapper`，继承 `BaseMapper<Xxx>`；不得创建 `XxxRepository` 或使用 Spring Data Repository 模式。
- 每个 Mapper 都必须生成同名 XML 文件，即使当前没有自定义 SQL。
- Mapper XML 命名使用 `XxxMapper.xml`，放在 `xxx-dao/src/main/resources/mapper/<business-module>/` 下。
- Mapper XML 的 `namespace` 必须等于 Mapper 接口全限定名。
- 每个 Mapper XML 必须定义 `BaseResultMap`，显式维护表字段到实体属性的映射。
- 空 Mapper XML 也必须保持合法结构并包含 `BaseResultMap`，不要留下空的 `<mapper>`。
- `util/` 只放无状态、通用且难以归属具体领域的工具类。
- Flyway 脚本默认放在 `xxx-dao/src/main/resources/db/migration/`。
- 不用目录结构掩盖职责问题；复杂分层判断交给架构类 skill。

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

按项目需要创建或补充：

- `pom.xml`
- `xxx-base-core/pom.xml`
- `xxx-common-api/pom.xml`
- `xxx-framework/pom.xml`
- `xxx-dao/pom.xml`
- `xxx-infra/pom.xml`
- `xxx-module-system/pom.xml`
- `xxx-job/pom.xml`
- `xxx-web/pom.xml`
- 各含代码模块的 `src/main/java/<base-package>/...`
- `xxx-web/src/main/resources/application.yml`
- `xxx-web/src/main/resources/application-dev.yml`
- `xxx-dao/src/main/resources/mapper/<business-module>/XxxMapper.xml`
- `xxx-dao/src/main/resources/db/migration/`
