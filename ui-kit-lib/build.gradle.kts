plugins {
    kotlin("multiplatform")
    id("maven-publish")
    id("pl.allegro.tech.build.axion-release") version "1.13.6"
}

group = "com.linked-planet"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    js(LEGACY) {
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
        useCommonJs()
    }
    sourceSets {
        val jsMain by getting {
            dependencies {
                // React
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react:17.0.2-pre.257-kotlin-1.5.31")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:17.0.2-pre.257-kotlin-1.5.31")

                // Atlaskit
                implementation(npm("@atlaskit/banner", "^11.2.2"))
                implementation(npm("@atlaskit/button", "^16.1.2"))
                implementation(npm("@atlaskit/checkbox", "^7.0.0"))
                implementation(npm("@atlaskit/dropdown-menu", "10.0.0"))
                implementation(npm("@atlaskit/flag", "^11.0.0"))
                implementation(npm("@atlaskit/icon", "^17.0.1"))
                implementation(npm("@atlaskit/textfield", "^2.0.0"))
                implementation(npm("@atlaskit/textarea", "^2.0.0"))
                implementation(npm("@atlaskit/select", "^9.0.1"))
                implementation(npm("@atlaskit/modal-dialog", "^9.0.0"))
                implementation(npm("@atlaskit/table-tree", "^8.0.5"))
                implementation(npm("@atlaskit/tag", "11.2.1"))
                implementation(npm("@atlaskit/tag-group", "10.0.10"))
                implementation(npm("@atlaskit/dynamic-table", "^13.7.7"))
                implementation(npm("@atlaskit/atlassian-navigation", "^0.10.13"))
                implementation(npm("@atlaskit/inline-edit", "^10.0.33"))
                implementation(npm("@atlaskit/logo", "^12.3.5"))
                implementation(npm("@atlaskit/page-layout", "0.8.0"))
                implementation(npm("@atlaskit/avatar", "19.0.0"))
                implementation(npm("@atlaskit/lozenge", "^11.1.2"))
                implementation(npm("@atlaskit/popup", "0.6.0"))
                implementation(npm("@atlaskit/menu", "0.5.0"))
                implementation(npm("@atlaskit/tabs", "12.1.3"))
                implementation(npm("@atlaskit/datetime-picker", "11.1.5"))
                implementation(npm("styled-components", "^3.5.0-0"))
                //implementation(npm("styled-components", "^5.3.1"))
                implementation(npm("@fortawesome/fontawesome-free", "^5.15.1"))

                implementation(npm("@atlaskit/section-message", "6.1.4"))
                implementation(npm("@atlaskit/side-navigation", "1.1.2"))
                implementation(npm("@atlaskit/toggle", "12.2.1"))

                // awesome slider
                implementation(npm("react-awesome-slider", "4.1.0"))
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }

    val publicationsFromMainHost =
        listOf(jvm(), js()).map { it.name } + "kotlinMultiplatform"
    publishing {
        publications {
            matching { it.name in publicationsFromMainHost }.all {
                val targetPublication = this@all
                tasks.withType<AbstractPublishToMaven>()
                    .matching { it.publication == targetPublication }
                    .configureEach { onlyIf { findProperty("isMainHost") == "true" } }
            }
        }
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/linked-planet/libraries")
                credentials {
                    username = System.getenv("GITHUB_ACTOR")
                    password = System.getenv("GITHUB_TOKEN")
                }
            }
        }
    }
}