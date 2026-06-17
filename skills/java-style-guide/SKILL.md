---
name: java-style-guide
description: Lightweight English Java source and test code style review skill. Use after Java files are modified to review naming, formatting, Javadoc and inline comments, file organization, member order, Stream readability, Null-handling readability, exception and log text readability, and lightweight code smells; by default only reviews files changed in the current task. Does not handle project initialization, coding guidance, business logic correctness, architecture, security, performance, concurrency correctness, database design, compilation, or test-result judgment.
---

# java-style-guide

## Purpose

This is a post-coding review skill for lightweight Java source and test code style cleanup. It focuses on whether code is clear, readable, and maintainable. It does not judge business logic, layering architecture, database structure, or concurrency correctness.

By default, review only Java files changed in the current task. Expand the scope only when the user explicitly asks for a global style review, style unification, or full cleanup.

## Boundary With Other Java Skills

- `java-project-init`: initializes projects, module structure, base dependencies, and the engineering skeleton.
- `java-coding-standards`: guides implementation-time layered standards and coding decisions.
- `java-style-guide`: reviews comments, naming, formatting, readability, and lightweight code smells after coding.

If a task includes both implementation and cleanup, first implement with `java-coding-standards`, then use this skill to review changed files.

## Core Principles

- Clarity over cleverness.
- Prefer the current project's existing style.
- Style suggestions serve readability; do not pursue mechanical uniformity.
- Do not make large unrelated changes only for style.
- Do not change code behavior.
- Do not present personal preference as a mandatory rule.
- Prefer maintainability over micro-optimization unless there is clear performance evidence.

## Workflow

1. Identify which Java files were changed in the current task.
2. Quickly inspect existing style in the same directory or similar file types.
3. Review and clean up only changed files.
4. Determine the most relevant review dimensions and read the corresponding reference files as needed.
5. Prioritize obvious issues that hurt readability.
6. Avoid large unrelated formatting changes.
7. Confirm that business semantics were not changed.

## Reference Selection

- Comments, Javadoc, TODO/FIXME: read `references/comments.md`.
- Class, method, variable, and constant names: read `references/naming.md`.
- File structure, member order, blank lines, line wrapping, formatting: read `references/formatting.md`.
- Short methods, early returns, intermediate variables, immutability preference, exception and log text readability: read `references/readability.md`.
- Streams, lambdas, fluent chains, and collection handling: read `references/streams.md`.
- Null semantics, `@Nullable`, `@NonNull`, and Bean Validation readability: read `references/null-handling.md`.
- Test naming, given/when/then structure, and assertion formatting: read `references/tests.md`.

If the user asks only for a general "style review" without a specific focus, read at least:

- `references/comments.md`
- `references/naming.md`
- `references/formatting.md`
- `references/readability.md`

## Quick Checklist

- Are names clear, or are there weak semantic names?
- Are important class or method Javadocs missing?
- Do inline comments explain purpose, reason, or constraints instead of repeating code?
- Do member order and blank lines help reading?
- Are Stream, lambda, or fluent chains too complex?
- Are parameter lists too long or nesting too deep?
- Should magic numbers or magic strings become named constants?
- Is there static mutable state, silent catch, or vague exception/log text?
- Are Null semantics clear?
- Is test code easy to read in terms of scenario, action, and assertion?

## Output Requirements

When this skill is used to modify code, report:

- Which files were checked.
- Which reference documents were read.
- Which style adjustments were made.
- Whether any broad formatting was intentionally avoided.

When this skill is used for review, prioritize concrete readability issues and provide actionable suggestions.
