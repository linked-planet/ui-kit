plugins {
    kotlin("js")
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(project(":ui-kit-lib"))
    implementation(npm("@monaco-editor/react", "4.4.5", generateExternals = true))
}

kotlin {
    sourceSets.all {
        languageSettings.optIn("kotlin.RequiresOptIn")
    }
    js {
        moduleName = "ui-kit-showcase"
        browser {
            runTask {
                devServer = devServer?.copy(
                    // frontend is embedded, so no point in opening a separate browser window
                    open = false,
                    port = 8080
                )
            }
            webpackTask {
                args = mutableListOf("--stats", "verbose")
                nodeArgs = mutableListOf("--trace-exit")
                outputFileName = "${project.name}.js"
            }
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
    getByName("processResources").dependsOn("gatherShowcaseSources")

    // See https://stackoverflow.com/a/66186933 for more details
    register("copyStylesProd", Copy::class) {
        from(kotlin.sourceSets["main"].resources) { include("style/**") }
        into("${rootProject.buildDir}/js/packages/${kotlin.js().moduleName}/kotlin-dce")
    }
    register("copyStylesDev", Copy::class) {
        from(kotlin.sourceSets["main"].resources) {
            include("style/**")
        }
        into("${rootProject.buildDir}/js/packages/${kotlin.js().moduleName}/kotlin-dce-dev")
    }
    withType(org.jetbrains.kotlin.gradle.tasks.KotlinJsDce::class) {
        if (dceOptions.devMode) {
            dependsOn("copyStylesDev")
        } else {
            dependsOn("copyStylesProd")
        }
    }
}
