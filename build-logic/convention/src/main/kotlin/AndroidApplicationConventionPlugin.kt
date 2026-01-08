import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import kotlin.apply

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.compose")

                apply("comicsnac.android.compose.application")
                apply("comicsnac.android.hilt")
            }

            configureAndroidApplication(extensions.getByType<ApplicationExtension>())
        }
    }
}


fun Project.configureAndroidApplication(extension: ApplicationExtension) {
    configureAndroid(extension)

    extension.apply {
        defaultConfig {
            targetSdk = Versions.TARGET_SDK
        }
        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
    }
}