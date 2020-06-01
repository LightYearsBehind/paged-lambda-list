import java.util.Date
import java.util.Properties

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.jfrog.bintray").version("1.8.5")
    id("maven-publish")
}

android {
    compileSdkVersion(29)
    buildToolsVersion = "29.0.3"

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0.0"

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.72")
    implementation("androidx.paging:paging-runtime:2.1.2")
}

tasks.register("sourcesJar", Jar::class) {
    from(android.sourceSets["main"].java.srcDirs)
    archiveClassifier.apply {
        convention("sources")
        value("sources")
    }
}

bintray {
    val properties = Properties()
    properties.load(project.rootProject.file("local.properties").inputStream())

    user = properties["bintray.user"].toString()
    key = properties["bintray.key"].toString()

    setPublications("release")
    publish = true

    pkg.apply {
        repo = "android"
        name = "paged-lambda-list"
        setLicenses("MIT")
        vcsUrl="https://github.com/LightYearsBehind/paged-lambda-list.git"
        version.apply {
            name = "1.0.0"
            desc = "Paged Lambda List 1.0.0"
            released = Date().toString()
            vcsTag = "1.0.0"
        }
    }
}

afterEvaluate {
    publishing {
        publications {
            register("release", MavenPublication::class) {
                from(components.named("release").get())
                artifact(tasks.named("sourcesJar").get())

                groupId = "com.geniewits.pagedlambdalist"
                artifactId = "paged-lambda-list"
                version = "1.0.0"
            }
        }
    }
}
