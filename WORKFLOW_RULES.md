# WORKFLOW_RULES.md

## Planning Phase
- Identify affected files and required dependencies.
- Check current Gradle/JDK configuration before proposing changes.

## Implementation Phase
- Make minimal, targeted edits.
- Prefer Compose BOM versions and existing dependency patterns.
- Avoid experimental APIs unless explicitly opted in at call site.

## Validation Phase
- Run `./gradlew assembleDebug`.
- If failure occurs, fix the first error only, then re-run.

## Documentation Update Phase
- Update `AGENTS.md` and `ERRORS_AND_FIXES.md` when a new recurring issue appears.

## Stop/Ask Rules
- Ask for JDK path before editing `gradle.properties` if unknown.

