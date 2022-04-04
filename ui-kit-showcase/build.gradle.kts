plugins {
    kotlin("js")
}

repositories {
    mavenLocal()
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers") }
}

dependencies {
    implementation(project(":ui-kit-lib"))

    implementation(devNpm("style-loader", "3.3.1"))
    implementation(devNpm("css-loader", "6.7.1"))
    implementation(devNpm("sass-loader", "12.6.0"))
    implementation(devNpm("node-sass", "7.0.1"))
    implementation(devNpm("file-loader", "6.2.0"))
    implementation(devNpm("@babel/core", "7.15.8"))
    implementation(devNpm("path-browserify", "1.0.1"))
}

kotlin {
    sourceSets.all {
        languageSettings.optIn("kotlin.RequiresOptIn")
    }
    js {
        useCommonJs()
        browser {
            runTask {
                devServer = devServer?.copy(
                    // frontend is embedded, so no point in opening a separate browser window
                    open = false,
                    port = 8080
                )
            }
            webpackTask {
                outputFileName = "${project.name}.js"
            }
            @OptIn(org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalDceDsl::class)
            dceTask {}
        }
        binaries.executable()
    }
}

// See https://github.com/webpack/webpack-cli/issues/2990
rootProject.plugins.withType<org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin> {
    rootProject.the<org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension>().apply {
        resolution("@webpack-cli/serve", "1.5.2")
    }
}