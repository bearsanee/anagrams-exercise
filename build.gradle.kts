plugins {
    kotlin("jvm") version "2.0.10"
    id("application")
}

group = "io.beyonnex.exercise"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.11.3")
    testImplementation("org.assertj:assertj-core:3.26.3")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

sourceSets {
    main {
        kotlin {
            srcDirs("src/main/kotlin")
        }
    }
    test {
        kotlin {
            srcDirs("src/test/kotlin")
        }
    }
}

application {
    mainClass = "io.beyonnex.exercise.anagrams.MainKt"
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

