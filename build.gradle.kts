plugins {
    kotlin("js") version "1.5.31" apply false
    id("com.github.hierynomus.license") version "0.16.1"
    id("pl.allegro.tech.build.axion-release") version "1.13.6"
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
}

val uiKitVersion: String = scmVersion.version
allprojects {
    group = "com.linked-planet.ui"
    version = uiKitVersion
}

subprojects {
    apply(plugin = "com.github.hierynomus.license")
    license {
        header = rootProject.file("LICENSE-HEADER.txt")
        strictCheck = true

        exclude("**/*.properties")
        exclude("**/*.json")

        ext["year"] = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)
        ext["owner"] = "linked-planet GmbH"
        ext["email"] = "info@linked-planet.com"
    }

    tasks.register<com.hierynomus.gradle.license.tasks.LicenseCheck>("licenseCheckInSrc") {
        source = fileTree(project.projectDir).matching { include("src/**/*") }
    }
    tasks.register<com.hierynomus.gradle.license.tasks.LicenseFormat>("licenseFormatInSrc") {
        source = fileTree(project.projectDir).matching { include("src/**/*") }
    }
    tasks["license"].dependsOn("licenseCheckInSrc")
    tasks["licenseFormat"].dependsOn("licenseFormatInSrc")
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}
