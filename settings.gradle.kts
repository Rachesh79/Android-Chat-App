pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral() // For ImagePicker library, this line is enough. Although, it has been published on jitpack as well
        maven ("https://jitpack.io")   //Make sure to add this in your project for uCrop - an internal library
    }
}

rootProject.name = "Free Chat"
include(":app")
