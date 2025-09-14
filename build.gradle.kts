import java.time.LocalDate
import java.time.format.DateTimeFormatter

plugins {
    id("org.springframework.boot") version "3.4.4" apply false
    id("io.spring.dependency-management") version "1.1.4" apply false
    id("com.diffplug.spotless") version "6.25.0" apply false
    java
}

group = "dev.christopherbell"
val buildNumber = System.getenv("BUILD_NUMBER") ?: "0"
val dateVersion = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
version = "$dateVersion.$buildNumber"

subprojects {
    // Apply Spotless to all Java subprojects
    apply(plugin = "com.diffplug.spotless")

    repositories {
        mavenCentral()
    }

    plugins.withType<JavaPlugin> {
        extensions.configure<JavaPluginExtension> {
            sourceCompatibility = JavaVersion.VERSION_21
            targetCompatibility = JavaVersion.VERSION_21
        }
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.compilerArgs.add("-parameters")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }

    // Spotless code style configuration (Google Java Format)
    extensions.configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        format("misc") {
            target("**/*.md", "**/.gitignore", "**/*.gradle.kts")
            trimTrailingWhitespace()
            endWithNewline()
        }
        java {
            target("**/*.java")
            targetExclude("**/build/**", "**/.gradle/**", "**/node_modules/**")
            googleJavaFormat("1.22.0")
            // Optional: organize imports consistently
            importOrder("java", "javax", "org", "com", "dev", "^")
            removeUnusedImports()
        }
    }
}

// Convenience aggregate tasks to run Spotless across all subprojects
tasks.register("spotlessApplyAll") {
    dependsOn(subprojects.map { it.path + ":spotlessApply" })
    description = "Runs spotlessApply for all subprojects"
}

tasks.register("spotlessCheckAll") {
    dependsOn(subprojects.map { it.path + ":spotlessCheck" })
    description = "Runs spotlessCheck for all subprojects"
}
