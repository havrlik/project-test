plugins {
	java
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.havrlik.test.project"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	runtimeOnly("org.postgresql:postgresql")
	implementation("org.liquibase:liquibase-core:4.26.0")
	implementation("me.paulschwarz:spring-dotenv:2.5.4")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
