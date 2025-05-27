import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.time.LocalDate
import java.time.format.DateTimeFormatter

plugins {
    id("org.springframework.boot") version "3.4.4"
    id("io.spring.dependency-management") version "1.1.4"
    java
    idea
}

group = "dev.christopherbell"

// dynamic versioning
val buildNumber = System.getenv("BUILD_NUMBER") ?: "0"
val dateVersion = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
version = "$dateVersion.$buildNumber"

description = "cbell-website"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("com.azure:azure-data-tables:12.5.3")
    implementation("org.jsoup:jsoup:1.20.1")
    implementation("org.mapstruct:mapstruct:1.6.3")
    implementation("io.jsonwebtoken:jjwt:0.12.6")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.8")
    implementation("org.postgresql:postgresql:42.7.5")

    compileOnly("org.projectlombok:lombok:1.18.38")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}

springBoot {
    buildInfo()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
