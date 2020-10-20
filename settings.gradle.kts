rootProject.name = "read"
rootDir.walk()
    .maxDepth(1)
    .filter { file ->
        file.name != "buildSrc" && file.isDirectory && file("${file.absolutePath}/build.gradle.kts").exists()
    }
    .forEach { file ->
        include(":${file.name}")
    }
