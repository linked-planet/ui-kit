plugins {
    kotlin("multiplatform") version "1.5.31"
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
    }
    sourceSets {
        val jsMain by getting {
            dependencies {
                // React
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react:17.0.2-pre.206-kotlin-1.5.10")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:17.0.2-pre.206-kotlin-1.5.10")

                // Atlaskit
                implementation(npm("@atlaskit/css-reset", "6.2.0"))
                implementation(npm("@atlaskit/button", "16.1.2"))
                implementation(npm("@atlaskit/page-layout", "0.8.0"))
                implementation(npm("@atlaskit/side-navigation", "1.1.2"))
                implementation(npm("@atlaskit/atlassian-navigation", "1.2.1"))
                implementation(npm("@atlaskit/popup", "0.6.0"))
                implementation(npm("@atlaskit/menu", "0.5.0"))
                implementation(npm("@atlaskit/icon", "^17.0.1"))
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
