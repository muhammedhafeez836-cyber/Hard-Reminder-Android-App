import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
}

val keystoreProperties = Properties()
val keystorePropertiesFile = rootProject.file("keystore.properties")
val hasKeystore = if (keystorePropertiesFile.exists()) {
    keystorePropertiesFile.inputStream().use { keystoreProperties.load(it) }
    true
} else {
    System.getenv("RELEASE_STORE_FILE") != null
}

android {
    namespace = "com.hardreminder.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.hardreminder.app"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    signingConfigs {
        if (hasKeystore) {
            create("release") {
                val storeFilePath = (keystoreProperties["storeFile"] as String?)
                    ?: System.getenv("RELEASE_STORE_FILE")
                val storePassword = (keystoreProperties["storePassword"] as String?)
                    ?: System.getenv("RELEASE_STORE_PASSWORD")
                val keyAlias = (keystoreProperties["keyAlias"] as String?)
                    ?: System.getenv("RELEASE_KEY_ALIAS")
                val keyPassword = (keystoreProperties["keyPassword"] as String?)
                    ?: System.getenv("RELEASE_KEY_PASSWORD")

                storeFile = file(checkNotNull(storeFilePath) { "Missing release keystore file path." })
                this.storePassword = checkNotNull(storePassword) { "Missing release keystore password." }
                this.keyAlias = checkNotNull(keyAlias) { "Missing release key alias." }
                this.keyPassword = checkNotNull(keyPassword) { "Missing release key password." }
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            if (hasKeystore) {
                signingConfig = signingConfigs.getByName("release")
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(platform("androidx.compose:compose-bom:2024.06.00"))

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.2")
    implementation("androidx.activity:activity-compose:1.9.0")

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")

    implementation("androidx.navigation:navigation-compose:2.7.7")

    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    implementation("com.google.android.material:material:1.12.0")
}
