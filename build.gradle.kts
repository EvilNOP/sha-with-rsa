import org.gradle.api.tasks.bundling.Jar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

repositories {
    maven("http://maven.aliyun.com/nexus/content/groups/public/")
    mavenLocal()
    mavenCentral()
}

plugins {
    val kotlinterVersion = "1.16.0"
    val springBootVersion = "2.0.4.RELEASE"
    val kotlinVersion = "1.2.61"
    val dependencyManagementVersion = "1.0.6.RELEASE"

    application

    kotlin("jvm") version kotlinVersion
    kotlin("kapt") version kotlinVersion
    kotlin("plugin.allopen") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion

    id("org.springframework.boot") version springBootVersion
    id("io.spring.dependency-management") version dependencyManagementVersion

    id("org.jmailen.kotlinter") version kotlinterVersion
}

application {
    mainClassName = "io.github.evilnop.ApplicationKt"
}

group = "io.github.evilnop"
version = "0.0.1-SNAPSHOT"

tasks.withType<Jar> {
    baseName = "sha-with-rsa"
    version = "gradle"
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjsr305=strict")
        allWarningsAsErrors = true
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    val kotlinVersion = "1.2.61"
    val jacksonVersion = "2.9.4"
    val jjwtVersion = "0.9.0"

    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation(kotlin("stdlib-jdk8", kotlinVersion))
    implementation(kotlin("reflect", kotlinVersion))

    implementation("io.jsonwebtoken:jjwt:$jjwtVersion")
    implementation("org.bouncycastle:bcprov-jdk15on:1.60")

    implementation("com.fasterxml.jackson.module:jackson-modules-java8:$jacksonVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")

    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
