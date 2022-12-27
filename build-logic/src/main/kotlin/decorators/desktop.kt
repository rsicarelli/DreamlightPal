package decorators

import org.gradle.api.Project
import org.gradle.api.compose
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.compose.desktop.DesktopExtension
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

fun Project.setupDesktopApp(
    dependencyHandler: KotlinDependencyHandler.() -> Unit = {},
) {
    extensions.configure<KotlinMultiplatformExtension> {
        jvm {}
        sourceSets {
            named("jvmMain") {
                dependencies(dependencyHandler)
            }
        }
    }

    compose.configure<DesktopExtension> {
        application {
            mainClass = "app.dreamlightpal.MainKt"

            nativeDistributions {
                targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
                packageName = "DreamlightPal"
                packageVersion = "1.0.0"

                windows {
                    menu = true
                    // see https://wixtoolset.org/documentation/manual/v3/howtos/general/generate_guids.html
                    upgradeUuid = ""
                }

                macOS {
                    // Use -Pcompose.desktop.mac.sign=true to sign and notarize.
                    bundleID = "app.dreamlightpal"
                }
            }
        }
    }
}
