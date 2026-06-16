# .gitignore 规则

初始化项目时，`.gitignore` 至少覆盖以下内容。

## Java 与 Maven

```gitignore
target/
*.class
```

不要忽略 Maven Wrapper：

```gitignore
!mvnw
!mvnw.cmd
!.mvn/wrapper/maven-wrapper.properties
```

## IDE

```gitignore
.idea/
*.iml
.classpath
.project
.settings/
```

## 日志与临时文件

```gitignore
logs/
*.log
*.tmp
*.temp
```

## 本地环境

```gitignore
.env
.env.*
```

如项目需要提交某个本地模板文件，应使用不含敏感值的示例文件，例如 `.env.example`。

