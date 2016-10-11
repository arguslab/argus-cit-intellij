// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        mavenCentral()
        maven {
            url "https://plugins.gradle.org/m2/"
        }
<#if mavenUrl != "mavenCentral">
        maven {
            url '${mavenUrl}'
        }
</#if>
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:${gradlePluginVersion}'
        classpath 'gradle.plugin.com.github.arguslab:gradle-android-jawa-plugin:${androidJawaPluginVersion}'

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        jcenter()
<#if mavenUrl != "mavenCentral">
        maven {
            url '${mavenUrl}'
        }
</#if>
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}
