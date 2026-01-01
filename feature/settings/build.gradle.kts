plugins {
    id("comicsnac.android.feature")
}

android {
    namespace = "com.omasyo.comicsnac.settings"
}

dependencies {
    implementation(libs.androidx.datastore.preferences)
}