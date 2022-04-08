plugins {
    kotlin("js")
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(project(":ui-kit-lib"))

    implementation(devNpm("style-loader", "3.3.1"))
    implementation(devNpm("css-loader", "6.7.1"))
    implementation(devNpm("sass-loader", "12.6.0"))
    implementation(devNpm("node-sass", "7.0.1"))
    implementation(devNpm("file-loader", "6.2.0"))
    implementation(devNpm("@babel/core", "7.17.9"))
    implementation(devNpm("path-browserify", "1.0.1"))
    implementation(devNpm("process", "0.11.10"))
}

kotlin {
    sourceSets.all {
        languageSettings.optIn("kotlin.RequiresOptIn")
    }
    js {
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
        useCommonJs()
    }
}

tasks {
    register("gatherShowcaseSources") {
        doLast {
            val files = fileTree(layout.projectDirectory.dir("src/main/kotlin/com/linkedplanet/uikit")).files
            val showcaseSourcesFileName = "showcase-sources.txt"
            val showcaseSourcesFile = File("${buildDir}/processedResources/js/main/$showcaseSourcesFileName")

            if (!showcaseSourcesFile.exists()) {
                showcaseSourcesFile.parentFile.mkdirs()
                showcaseSourcesFile.createNewFile()
            }

            files.forEach { file ->
                val fileContent = file.readText()
                showcaseSourcesFile.appendText(fileContent)
            }
        }
    }
}
tasks["processResources"].dependsOn("gatherShowcaseSources")
