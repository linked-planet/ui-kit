plugins {
    kotlin("js")
    id("org.jetbrains.dokka") version "1.6.10"
    id("maven-publish")
    id("signing")
}

repositories {
    mavenCentral()
}

// ATTENTION!
// ----------
// See recommended kotlin library versions for kotlin version: https://kotlinlang.org/docs/releases.html#release-details
// See the official bom (pom.xml) for other library versions: https://repo1.maven.org/maven2/org/jetbrains/kotlin-wrappers/kotlin-wrappers-bom/
dependencies {
    // Kotlin
    api(kotlin("stdlib-js", "1.7.21"))
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.6.4")
    api("org.jetbrains.kotlinx:kotlinx-html-js:0.8.0")

    // React & Redux
    api("org.jetbrains.kotlin-wrappers:kotlin-react:18.2.0-pre.456")
    api("org.jetbrains.kotlin-wrappers:kotlin-react-dom:18.2.0-pre.456")
    api("org.jetbrains.kotlin-wrappers:kotlin-react-router-dom:6.3.0-pre.456")
    api("org.jetbrains.kotlin-wrappers:kotlin-redux:4.1.2-pre.456")
    api("org.jetbrains.kotlin-wrappers:kotlin-react-redux:7.2.6-pre.456")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-styled:5.3.6-pre.456") // <-- implementation as it's for internal use only

    // Emotion for styling
    api("org.jetbrains.kotlin-wrappers:kotlin-emotion:11.10.5-pre.456")

    // Moment
    api("io.github.samgarasx:kotlin-moment:2.27.0-pre.12-kotlin-1.5.21")

    // Atlaskit
    implementation(npm("@atlaskit/atlassian-navigation", "2.1.3"))
    implementation(npm("@atlaskit/avatar", "20.5.7"))
    implementation(npm("@atlaskit/badge", "15.0.3"))
    implementation(npm("@atlaskit/banner", "11.4.9"))
    implementation(npm("@atlaskit/button", "16.2.1"))
    implementation(npm("@atlaskit/calendar", "12.1.10"))
    implementation(npm("@atlaskit/checkbox", "12.3.10"))
    implementation(npm("@atlaskit/code", "14.3.4"))
    implementation(npm("@atlaskit/css-reset", "6.2.0"))
    implementation(npm("@atlaskit/datetime-picker", "6.2.0"))
    implementation(npm("@atlaskit/dropdown-menu", "11.1.2"))
    implementation(npm("@atlaskit/dynamic-table", "14.5.2"))
    implementation(npm("@atlaskit/table-tree", "9.1.9"))
    implementation(npm("@atlaskit/empty-state", "7.3.8"))
    implementation(npm("@atlaskit/flag", "14.5.5"))
    implementation(npm("@atlaskit/form", "8.5.2"))
    implementation(npm("@atlaskit/icon", "21.10.5"))
    implementation(npm("@atlaskit/lozenge", "11.1.8"))
    implementation(npm("@atlaskit/modal-dialog", "12.2.5"))
    implementation(npm("@atlaskit/page-layout", "1.2.0"))
    implementation(npm("@atlaskit/pagination", "14.1.6"))
    implementation(npm("@atlaskit/panel", "0.4.7"))
    implementation(npm("@atlaskit/popup", "1.3.4"))
    implementation(npm("@atlaskit/select", "15.2.10"))
    implementation(npm("@atlaskit/side-navigation", "1.2.6"))
    implementation(npm("@atlaskit/tabs", "12.1.3")) // TODO: update!
    implementation(npm("@atlaskit/tag", "11.3.0"))
    implementation(npm("@atlaskit/tag-group", "10.0.11"))
    implementation(npm("@atlaskit/textarea", "4.3.2"))
    implementation(npm("@atlaskit/textfield", "5.1.8"))
    implementation(npm("@atlaskit/toggle", "12.4.5"))

    // Misc
    implementation(npm("react-joyride", "2.4.0"))
    implementation(npm("react-tooltip", "4.2.21"))
    implementation(npm("mime-types", "2.1.34"))

    // Monaco Editor for LPEditor
    implementation(npm("@monaco-editor/react", "4.4.5"))

    // Awesome Slider
    implementation(npm("react-awesome-slider", "4.1.0"))

    // Dev
    implementation(devNpm("style-loader", "3.3.1"))
    implementation(devNpm("css-loader", "6.7.1"))
    implementation(devNpm("sass-loader", "12.6.0"))
    implementation(devNpm("file-loader", "6.2.0"))
    implementation(devNpm("sass", "1.50.0"))
    implementation(devNpm("path-browserify", "1.0.1"))
    implementation(devNpm("process", "0.11.10"))
}

kotlin {
    js {
        browser {}
        useCommonJs()
    }
}

tasks {
    register("javadocJar", Jar::class) {
        dependsOn("dokkaHtml")
        archiveClassifier.set("javadoc")
        from("$buildDir/javadoc")
    }
    register("sourcesJar", Jar::class) {
        archiveClassifier.set("sources")
        from("src/main/kotlin")
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["kotlin"])
            artifact(tasks.getByName<Zip>("javadocJar"))
            artifact(tasks.getByName<Zip>("sourcesJar"))

            pom {
                name.set("UI-Kit")
                description.set("Provides several ui components and functionality used throughout our projects.")
                url.set("https://linked-planet.github.io/ui-kit/")
                inceptionYear.set("2022")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        name.set("Simon Jahreiss")
                        email.set("simon.jahreiss@linked-planet.com")
                        url.set("https://github.com/sjahreis")
                        organization.set("linked-planet GmbH")
                        organizationUrl.set("https://linked-planet.com")
                    }
                    developer {
                        name.set("Philipp Karlsson")
                        email.set("philipp.karlsson@linked-planet.com")
                        url.set("https://github.com/betacore")
                        organization.set("linked-planet GmbH")
                        organizationUrl.set("https://linked-planet.com")
                    }
                    developer {
                        name.set("Alexander Weickmann")
                        email.set("alexander.weickmann@linked-planet.com")
                        url.set("https://github.com/weickmanna")
                        organization.set("linked-planet GmbH")
                        organizationUrl.set("https://linked-planet.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/linked-planet/ui-kit.git")
                    developerConnection.set("scm:git:ssh://github.com/linked-planet/ui-kit.git")
                    url.set("https://github.com/linked-planet/ui-kit")
                }
            }
        }
    }
}

signing {
    isRequired = !project.version.toString().endsWith("-SNAPSHOT") && !project.hasProperty("skipSigning")
    if (project.findProperty("signingKey") != null) {
        useInMemoryPgpKeys(
            findProperty("signingKey").toString(),
            findProperty("signingPassword").toString()
        )
    } else {
        useGpgCmd()
    }
    sign(publishing.publications["maven"])
}
