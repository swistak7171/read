rootProject.name = "read"
rootDir.walk()
    .maxDepth(1)
    .filter { file ->
        file.isDirectory &&
        file.name != rootProject.name &&
        file.name != "buildSrc" &&
        file("${file.absolutePath}/build.gradle.kts").exists()
    }
    .forEach { file ->
        include(":${file.name}")
    }
