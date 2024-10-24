plugins {
	java
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web
	implementation("org.springframework.boot:spring-boot-starter-web")

	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-autoconfigure
	implementation("org.springframework.boot:spring-boot-autoconfigure:3.3.4")

	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	testImplementation(platform("org.junit:junit-bom:5.10.0"))
	testImplementation("org.junit.jupiter:junit-jupiter")
	compileOnly ("org.projectlombok:lombok:1.18.34")
	annotationProcessor ("org.projectlombok:lombok:1.18.34")

	testCompileOnly ("org.projectlombok:lombok:1.18.34")
	testAnnotationProcessor ("org.projectlombok:lombok:1.18.34")

	// https://mvnrepository.com/artifact/org.springframework.data/spring-data-jpa
	implementation("org.springframework.data:spring-data-jpa:3.3.5")

	// https://mvnrepository.com/artifact/org.postgresql/postgresql
	implementation("org.postgresql:postgresql:42.7.4")

	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.3.4")

	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-configuration-processor
	implementation("org.springframework.boot:spring-boot-configuration-processor:3.3.4")

	implementation("com.h2database:h2:2.3.232")


}

tasks.withType<Test> {
	useJUnitPlatform()
}
