import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("java")
	id("org.springframework.boot") version "2.4.1"
	id("io.spring.dependency-management") version "1.0.10.RELEASE"
	kotlin("jvm") version "1.4.21"
	kotlin("plugin.spring") version "1.4.21"
	id("com.gorylenko.gradle-git-properties") version "2.2.4"
	id("groovy")
	id("jacoco")
	id("org.jlleitschuh.gradle.ktlint") version "9.2.1"
	id("org.jlleitschuh.gradle.ktlint-idea") version "9.2.1"
	id("org.sonarqube") version "3.0"
	id("com.palantir.docker-compose") version "0.25.0"
}

group = "com.poc"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	testImplementation("org.codehaus.groovy:groovy:3.0.6")
	testImplementation("org.spockframework:spock-bom:2.0-M3-groovy-3.0")
	testImplementation("org.spockframework:spock-core:2.0-M3-groovy-3.0")
	testImplementation("org.spockframework:spock-spring:2.0-M3-groovy-3.0")
	testImplementation("org.spockframework:spock-junit4:2.0-M3-groovy-3.0")
	testImplementation("org.hamcrest:hamcrest-core:2.2")
	testImplementation("net.bytebuddy:byte-buddy:1.10.10")
	testImplementation("org.objenesis:objenesis:3.1")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("com.h2database:h2")

	compileOnly("org.springframework.boot:spring-boot-devtools")
}

//gitProperties {
//	keys = listOf("git.build.version", "git.commit.id", "git.commit.id.abbrev", "git.commit.time", "git.tags")
//}

java {
	sourceCompatibility = JavaVersion.VERSION_11
	targetCompatibility = JavaVersion.VERSION_11
}

ktlint {
	debug.set(false)
	disabledRules.set(setOf("import-ordering", "no-blank-line-before-rbrace", "no-wildcard-imports"))
	ignoreFailures.set(true)
	verbose.set(true)
}

//sonarqube {
//	properties {
//		property("sonar.projectKey", System.getenv("SONAR_PROJECT_KEY"))
//		property("sonar.projectName", System.getenv("SONAR_PROJECT_NAME"))
//	}
//}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
