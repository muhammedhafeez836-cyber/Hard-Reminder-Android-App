# ERRORS_AND_FIXES.md

## 1) Gradle Wrapper NoClassDefFoundError (IDownload)
- Error: `Unable to initialize main class org.gradle.wrapper.GradleWrapperMain` / `NoClassDefFoundError: org/gradle/wrapper/IDownload`
- Root cause: Using the plugin wrapper jar without required shared classes.
- Correct fix: Regenerate wrapper using a local Gradle distribution (`gradle wrapper`).
- Avoid next time: Don’t hand-copy wrapper jars; always regenerate wrapper in-project.

## 2) Gradle Wrapper Missing CLI Class
- Error: `NoClassDefFoundError: org/gradle/cli/CommandLineParser`
- Root cause: Incomplete wrapper classpath/jar set.
- Correct fix: Regenerate wrapper to include correct jar and script outputs.
- Avoid next time: Use `gradle wrapper` and don’t customize wrapper classpath.

## 3) Unsupported Class File Major Version 68
- Error: `Unsupported class file major version 68`
- Root cause: Gradle running on JDK 24 instead of Android Studio JBR.
- Correct fix: Set `org.gradle.java.home` to Android Studio JBR (17 or 21).
- Avoid next time: Pin JBR in `gradle.properties` for Android builds.

## 4) Resource Theme Not Found (Material3 XML)
- Error: `AAPT: error: resource style/Theme.Material3.DayNight.NoActionBar not found`
- Root cause: Missing Material Components dependency for XML theme.
- Correct fix: Add `com.google.android.material:material` dependency.
- Avoid next time: If using Material3 XML themes, include material components.

## 5) Missing ProcessLifecycleOwner
- Error: `Unresolved reference: ProcessLifecycleOwner`
- Root cause: Missing `androidx.lifecycle:lifecycle-process` dependency.
- Correct fix: Add `lifecycle-process` to dependencies.
- Avoid next time: Add lifecycle-process whenever using ProcessLifecycleOwner.

## 6) Icon References Unresolved
- Error: `Unresolved reference: ArrowBack / Add / Settings / Lock / Edit / Star / Warning / Delete`
- Root cause: Incorrect icon package usage and missing icon artifacts.
- Correct fix: Use `Icons.AutoMirrored.Filled.ArrowBackIos` and `Icons.Filled.*` with `material-icons-extended`.
- Avoid next time: Use Compose material icons from BOM; prefer AutoMirrored for back.

## 7) Restricted Pointer APIs
- Error: `Restricted suspending functions...` for pointer input
- Root cause: Use of restricted pointer APIs outside allowed scope.
- Correct fix: Replace with `detectTapGestures`.
- Avoid next time: Use high-level pointer APIs unless strictly necessary.

## 8) FLAG_GRANT_READ_URI_PERMISSION Unresolved
- Error: `Unresolved reference: FLAG_GRANT_READ_URI_PERMISSION`
- Root cause: Using `ContentResolver` constant instead of `Intent` constant.
- Correct fix: Use `Intent.FLAG_GRANT_READ_URI_PERMISSION`.
- Avoid next time: Use intent flags for SAF permissions.

## 9) compileSdk Warning With Older AGP
- Error/Warning: `We recommend using a newer Android Gradle plugin to use compileSdk = 35`
- Root cause: AGP version not tested with compileSdk 35.
- Correct fix: Upgrade AGP to a version tested with SDK 35.
- Avoid next time: Keep AGP aligned with compileSdk or add `android.suppressUnsupportedCompileSdk=35` if you intentionally stay on an older AGP.

## 10) Rapid Double-Back Navigates To Blank Screen
- Symptom: Pressing back quickly twice on detail/settings can land on a blank page.
- Root cause: `popBackStack()` returns false and leaves the NavHost without a destination.
- Correct fix: Use a safe back handler that navigates to `Routes.HOME` when pop fails.
