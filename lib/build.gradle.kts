/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java library project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/6.8.3/userguide/building_java_projects.html
 */

plugins {
    // Apply the groovy plugin to also add support for Groovy (needed for Spock)
    groovy

    // Apply the java-library plugin for API and implementation separation.
    `java-library`
}

repositories {
    // Use JCenter for resolving dependencies.
    jcenter()
}

dependencies {
    // Use the latest Groovy version for Spock testing
    testImplementation("org.codehaus.groovy:groovy-all:2.5.13")

    // Use the awesome Spock testing and specification framework even with Java
    testImplementation("org.spockframework:spock-core:2.0-M4-groovy-2.5")
    testImplementation("commons-codec:commons-codec:1.15")

    implementation("com.github.serceman:jnr-fuse:0.5.5")
}

tasks.withType<Test> {
    useJUnitPlatform()
}