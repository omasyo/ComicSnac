plugins {
    id("comicsnac.android.library")
    id("comicsnac.android.compose.library")
}

android {
    namespace = "com.omasyo.comicsnac.ui"
}

dependencies {
    implementation(project(":core:model"))

    implementation(libs.androidx.browser)
}