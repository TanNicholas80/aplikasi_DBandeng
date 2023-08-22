buildscript {
    val agp_version by extra("8.1.0")
    val agp_version1 by extra("8.0.2")
    val agp_version2 by extra("8.0.0")
    repositories {
        jcenter()
    }
}



// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.4.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
}
