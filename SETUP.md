# Hard Reminder Setup

1) Open the project in Android Studio.
2) Confirm `org.gradle.java.home` points to your JBR install in `gradle.properties`.
3) Sync Gradle and let Android Studio download dependencies.
4) Run `./gradlew --version` from the project root.
5) Run `./gradlew assembleDebug` to build the debug APK.
6) Install/run on a device and grant notification permission when prompted.
7) Enable exact alarms if Android 12+ prompts for it.
8) Disable battery optimizations for best reliability.
