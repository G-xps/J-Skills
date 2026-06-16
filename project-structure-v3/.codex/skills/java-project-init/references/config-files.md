# Configuration File Rules

## File Location

Default configuration files live in the only startup module:

```text
xxx-web/src/main/resources/application.yml
xxx-web/src/main/resources/application-dev.yml
```

## application.yml

`application.yml` is only used to activate the `dev` profile.

Example:

```yaml
spring:
  profiles:
    active: dev
```

## application-dev.yml

`application-dev.yml` contains only the minimal configuration required for local startup.

It may contain:

- application name
- server port
- datasource placeholder configuration
- MyBatis-Plus base configuration
- Flyway base configuration
- XXL-JOB Executor base configuration

Do not include:

- real passwords, secrets, or tokens
- business configuration unrelated to initialization
- full configuration blocks for middleware that is not enabled
- production environment configuration

## Sensitive Configuration

- Use placeholders or environment-variable notes for sensitive values.
- Local defaults are allowed only for non-sensitive settings.
- Do not commit real database passwords, cloud service keys, or SMS credentials.
