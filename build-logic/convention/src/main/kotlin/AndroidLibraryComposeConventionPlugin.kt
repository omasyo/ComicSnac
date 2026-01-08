import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import kotlin.apply

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            configureCompose(extensions.getByType<LibraryExtension>())
        }
    }

}

fun Project.configureCompose(extension: CommonExtension<*, *, *, *, *, *>) {
    pluginManager.apply {
        apply("org.jetbrains.kotlin.plugin.compose")
    }

    extension.apply {
        buildFeatures {
            compose = true
        }
    }

    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
    dependencies {
        "implementation"(libs.findLibrary("coil.compose").get())

        "implementation"(platform(libs.findLibrary("androidx.compose.bom").get()))
        "implementation"(libs.findLibrary("androidx.compose.material3").get())
        "implementation"(libs.findLibrary("androidx.compose.ui").get())
        "implementation"(libs.findLibrary("androidx.compose.ui.graphics").get())
        "implementation"(libs.findLibrary("androidx.compose.ui.tooling.preview").get())
        "implementation"(libs.findLibrary("androidx.compose.ui.util").get())

        "debugImplementation"(libs.findLibrary("androidx.compose.ui.tooling").get())
        "debugImplementation"(libs.findLibrary("androidx.compose.ui.test.manifest").get())

    }
}