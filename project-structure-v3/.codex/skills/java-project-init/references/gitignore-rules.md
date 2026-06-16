# .gitignore Rules

When initializing a project, `.gitignore` must cover at least these entries.

## Java And Maven

```gitignore
target/
*.class
```

Do not ignore Maven Wrapper:

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

## Logs And Temporary Files

```gitignore
logs/
*.log
*.tmp
*.temp
```

## Local Environment

```gitignore
.env
.env.*
```

If a local template file must be committed, use a non-sensitive example file such as `.env.example`.
