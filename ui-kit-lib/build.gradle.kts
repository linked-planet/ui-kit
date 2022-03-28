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

                // Atlaskit
                implementation(npm("@atlaskit/atlassian-navigation", "2.1.3"))
                implementation(npm("@atlaskit/avatar", "20.5.7"))
                implementation(npm("@atlaskit/button", "16.2.1"))
                implementation(npm("@atlaskit/checkbox", "12.3.10"))
                implementation(npm("@atlaskit/css-reset", "6.2.0"))
                implementation(npm("@atlaskit/datetime-picker", "6.2.0"))
                implementation(npm("@atlaskit/dropdown-menu", "11.1.2"))
                implementation(npm("@atlaskit/flag", "14.5.5"))
                implementation(npm("@atlaskit/icon", "21.10.5"))
                implementation(npm("@atlaskit/inline-edit", "12.1.10"))
                implementation(npm("@atlaskit/menu", "1.3.1"))
                implementation(npm("@atlaskit/page-layout", "1.2.0"))
                implementation(npm("@atlaskit/popup", "1.3.4"))
                implementation(npm("@atlaskit/select", "15.2.10"))
                implementation(npm("@atlaskit/side-navigation", "1.2.6"))
                implementation(npm("@atlaskit/tabs", "12.1.3")) // TODO: update!
                implementation(npm("@atlaskit/textfield", "5.1.8"))

                // Atlaskit dependencies
                implementation(npm("exenv", "1.2.2"))
                implementation(npm("memoize-one", "6.0.0"))
                implementation(npm("tiny-invariant", "1.2.0"))
                implementation(npm("styled-components", "3.2.6"))
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
