plugins {
    kotlin("jvm") version "1.3.72"
}

val fuelVersion = "2.2.3"
val klaxonVersion = "5.2"

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.github.kittinunf.fuel:fuel:$fuelVersion")
    implementation("com.beust:klaxon:$klaxonVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")

}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    test {
        useJUnitPlatform()
    }
}