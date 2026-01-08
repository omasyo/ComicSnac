plugins {
    alias(libs.plugins.androidx.baselineprofile)
    id("comicsnac.android.application")
}

android {
    namespace = "com.omasyo.comicsnac"

    defaultConfig {
        applicationId = "com.omasyo.comicsnac"

        versionCode = 6
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.profileinstaller)
    "baselineProfile"(project(":baselineprofile"))
    coreLibraryDesugaring(libs.android.tools.desugar)

    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(project(":core:ui"))
    implementation(project(":feature:categories"))
    implementation(project(":feature:details"))
    implementation(project(":feature:home"))
    implementation(project(":feature:search"))
    implementation(project(":feature:settings"))

    implementation(libs.androidx.navigation.compose)

    implementation(libs.androidx.core.splashscreen)


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.androidx.datastore.preferences)

    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.test.ext.junit.ktx)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}