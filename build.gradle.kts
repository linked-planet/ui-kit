plugins {
    kotlin("js") version "1.5.31" apply false
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
    id("com.github.hierynomus.license") version "0.16.1"
}

allprojects {
    group = "com.linked-planet.ui"
    version = "1.0-SNAPSHOT"
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
        sonatype()
    }
}
