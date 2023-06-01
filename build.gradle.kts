// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id (ClassPaths.androidApplicationPath) version Versions.androidApplicationPathVersion apply false
    id (ClassPaths.androidLibraryPath) version Versions.androidLibraryPathVersion apply false
    id (ClassPaths.kotlinGradlePath) version Versions.kotlinGradleVersion apply false
}