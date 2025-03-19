plugins {
	java
	id("jacoco")
	id("org.sonarqube") version "4.0.0.2929"
	id("org.springframework.boot") version "3.3.9"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "br.com.wisefinances"
version = "0.1.5"

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(21))
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
	enabled = true
	destinationDirectory.set(file("${projectDir}/dist"))
}

tasks.named<Jar>("jar") {
	enabled = false
}

repositories {
	mavenCentral()
}

dependencies {

	// Spring Boot Starters
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-logging")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")

	// Converter de uma Classe para outra
	implementation("org.modelmapper:modelmapper:3.0.0")

	// Banco de Dados e Migração
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-database-postgresql")
	runtimeOnly("org.postgresql:postgresql")

	// Lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// Desenvolvimento
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	// Testes
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Copy> {
	duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.withType<Test> {
	useJUnitPlatform()
	finalizedBy(tasks.jacocoTestReport)
}

tasks.processTestResources {
	from("src/test/resources") {
		include("**/*.json")
	}
}

tasks.named<JacocoReport>("jacocoTestReport") {
	dependsOn(tasks.test)

	reports {
		xml.required.set(true)
		html.required.set(true)
	}

	classDirectories.setFrom(
		files(
			classDirectories.files.map {
				fileTree(it) {
					exclude(
						"**/config/**",
						"**/exceptions/**",
						"**/SmartBrainsApplication.class"
					)
				}
			}
		)
	)
}

sonar {
	properties {
		property("sonar.projectKey", "2a105fdhk4f9s8f57h2ar")
		property("sonar.organization", "WiseFinances")
		property("sonar.host.url", "https://sonarcloud.io")
		property("sonar.coverage.jacoco.xmlReportPaths", "${layout.buildDirectory.get().asFile}/reports/jacoco/test/jacocoTestReport.xml")
	}
}