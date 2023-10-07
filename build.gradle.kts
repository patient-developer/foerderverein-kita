plugins {
    id("org.springframework.boot") version "3.1.4"
    id("io.spring.dependency-management") version "1.1.3"
    java
}

group "kbh"
version "1.1.15"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencyManagement {
    dependencies {
        dependencySet("org.springframework.boot:3.1.4") {
            entry("spring-boot-starter")
            entry("spring-boot-starter-test")
            entry("spring-boot-starter-data-jpa")
            entry("spring-boot-starter-web")
            entry("spring-boot-starter-thymeleaf")
            entry("spring-boot-starter-security")
            entry("spring-boot-starter-validation")
            entry("spring-boot-starter-webflux")
            entry("spring-boot-starter-mail")
            entry("spring-boot-devtools")
        }
        dependencySet("org.projectlombok:1.18.30") {
            entry("lombok")
        }
        dependencySet("org.junit.jupiter:5.10.0") {
            entry("junit-jupiter-api")
            entry("junit-jupiter-engine")
        }
        dependencySet("org.mapstruct:1.5.5.Final") {
            entry("mapstruct")
            entry("mapstruct-processor")
        }
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.boot:spring-boot-devtools")

    implementation("org.projectlombok:lombok")
    implementation("org.mapstruct:mapstruct")

    implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect:3.2.1")
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6:3.1.2.RELEASE")

    implementation("org.webjars:jquery:3.7.1")
    implementation("org.webjars:bootstrap:5.2.3")
    implementation("org.webjars:webjars-locator:0.46")
    implementation("org.mariadb.jdbc:mariadb-java-client:3.1.2")
    implementation("com.sun.mail:jakarta.mail:2.0.1")

    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.mapstruct:mapstruct-processor")

    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
