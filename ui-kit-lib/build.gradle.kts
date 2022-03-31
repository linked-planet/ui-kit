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
    js {
        browser {}
        useCommonJs()
    }
    sourceSets {
        val jsMain by getting {
            dependencies {
                // React
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react:17.0.2-pre.257-kotlin-1.5.31")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:17.0.2-pre.257-kotlin-1.5.31")

                // Styled
                implementation("org.jetbrains.kotlin-wrappers:kotlin-styled:5.3.1-pre.257-kotlin-1.5.31")

                // Moment
                implementation("io.github.samgarasx:kotlin-moment:2.27.0-pre.12-kotlin-1.5.21")

                // Atlaskit
                implementation(npm("@atlaskit/atlassian-navigation", "2.1.3"))
                implementation(npm("@atlaskit/avatar", "20.5.7"))
                implementation(npm("@atlaskit/banner", "11.4.9"))
                implementation(npm("@atlaskit/button", "16.2.1"))
                implementation(npm("@atlaskit/calendar", "12.1.10"))
                implementation(npm("@atlaskit/checkbox", "12.3.10"))
                implementation(npm("@atlaskit/css-reset", "6.2.0"))
                implementation(npm("@atlaskit/datetime-picker", "6.2.0"))
                implementation(npm("@atlaskit/dropdown-menu", "11.1.2"))
                implementation(npm("@atlaskit/dynamic-table", "14.5.2"))
                implementation(npm("@atlaskit/flag", "14.5.5"))
                implementation(npm("@atlaskit/icon", "21.10.5"))
                implementation(npm("@atlaskit/lozenge", "11.1.8"))
                implementation(npm("@atlaskit/modal-dialog", "12.2.5"))
                implementation(npm("@atlaskit/page-layout", "1.2.0"))
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

                // Guided tour
                implementation(npm("react-joyride", "2.4.0"))

                // Tooltips
                implementation(npm("react-tooltip", "4.2.21"))
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }

    publishing {
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
