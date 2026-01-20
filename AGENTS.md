# AGENTS.md

## Strict Rules Before Writing Code
- Inspect existing files related to the change before editing.
- Confirm current build toolchain (Gradle + JDK) before changing build files.
- Do not introduce new dependencies unless a build error explicitly requires them.
- Prefer Compose BOM-managed versions; avoid pinning Compose artifact versions outside the BOM.
- Avoid experimental APIs unless explicitly opted in at call site.

## Commands To Always Run (after changes)
- `./gradlew assembleDebug`
- If build fails, re-run with `--stacktrace` only after fixing the primary error.

## When You Must Stop And Ask Instead Of Guessing
- When the correct JDK path is unknown.
- When a change requires modifying outside the repo root or user profile (e.g., system-wide env).
- When a dependency version is unclear and not specified in the BOM or existing build files.
- When a file change could overwrite user changes in unrelated modules.

## Coding Standards Based On What Worked
- Use Material icons via `Icons.*` from `androidx.compose.material:material-icons-extended`.
- Use `Icons.AutoMirrored.*` for back navigation icons.
- Use Compose BOM; do not hardcode Compose lib versions.
- Use `androidx.lifecycle:lifecycle-process` when referencing `ProcessLifecycleOwner`.
- Use `Intent.FLAG_GRANT_READ_URI_PERMISSION` for SAF permissions.
- Prefer `detectTapGestures` for global interaction tracking; avoid restricted pointer APIs unless needed.

## Known Pitfalls & Fixes
- Gradle wrapper jar mismatch causes `NoClassDefFoundError`.
  - Fix: regenerate wrapper using a local Gradle distribution.
- JDK 24 causes `Unsupported class file major version 68`.
  - Fix: set Gradle JDK to Android Studio JBR 17.
- Material3 theme XML missing in resources.
  - Fix: add `com.google.android.material:material` dependency.
- Icon references fail with `Unresolved reference: ArrowBack`.
  - Fix: use `Icons.AutoMirrored.Filled.ArrowBackIos` and include `material-icons-extended`.

## Verification Checklist Before Final Answers
- `./gradlew assembleDebug` passes.
- No unresolved references in Kotlin compile output.
- JDK is set to JBR 17 in `gradle.properties`.
- Dependencies align with BOM (no mismatched Compose versions).
- Wrapper runs: `./gradlew --version`.
