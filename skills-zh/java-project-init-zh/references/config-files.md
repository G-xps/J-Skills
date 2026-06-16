# 配置文件规则

## 文件位置

默认配置文件位于唯一启动模块：

```text
xxx-web/src/main/resources/application.yml
xxx-web/src/main/resources/application-dev.yml
```

## application.yml

`application.yml` 只用于指定默认启用 `dev` profile。

示例：

```yaml
spring:
  profiles:
    active: dev
```

## application-dev.yml

`application-dev.yml` 只放项目启动所需的最小配置。

可包含：

- 应用名
- 服务端口
- 数据源占位配置
- MyBatis-Plus 基础配置
- Flyway 基础配置
- XXL-JOB Executor 基础配置

不要包含：

- 真实密码、密钥、Token
- 与初始化无关的业务配置
- 未启用中间件的完整配置块
- 生产环境配置

## 敏感配置

- 敏感值使用占位符或环境变量说明。
- 本地开发默认值只能用于无风险配置。
- 不把真实数据库密码、云服务密钥、短信密钥写入仓库。

## MyBatis-Plus

`application-dev.yml` 必须配置 Mapper XML 扫描路径：

```yaml
mybatis-plus:
  mapper-locations: classpath*:/mapper/**/*.xml
```

该配置与 `xxx-dao/src/main/resources/mapper/<business-module>/XxxMapper.xml` 配套使用。
