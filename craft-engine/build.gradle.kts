dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.6-R0.1-SNAPSHOT") //Vanilla
    compileOnly("net.momirealms:craft-engine-core:26.4-SNAPSHOT")
    compileOnly("net.momirealms:craft-engine-bukkit:26.4-SNAPSHOT")
    compileOnly(project(":common"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}