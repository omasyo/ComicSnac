plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "comicsnac.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidFeature") {
            id = "comicsnac.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidLibrary") {
            id = "comicsnac.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidComposeApplication") {
            id = "comicsnac.android.compose.application"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidComposeLibrary") {
            id = "comicsnac.android.compose.library"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidHilt") {
            id = "comicsnac.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("jvmLibrary") {
            id = "comicsnac.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
    }
}