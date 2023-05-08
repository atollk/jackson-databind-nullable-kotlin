plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://oss.sonatype.org/content/repositories/snapshots")
    }

    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    api("com.fasterxml.jackson.core:jackson-databind:2.14.0-rc2")
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.21")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.hibernate.validator:hibernate-validator:6.2.5.Final")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.8.21")
    testImplementation("junit:junit:4.13.2")
    compileOnly("javax.validation:validation-api:2.0.1.Final")
    compileOnly("jakarta.validation:jakarta.validation-api:3.0.2")
}

group = "org.openapitools"
version = "0.2.6-SNAPSHOT"
description = "JsonNullable Jackson module"
java.sourceCompatibility = JavaVersion.VERSION_1_8

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}
