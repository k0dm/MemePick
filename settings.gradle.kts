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
        mavenCentral()
    }
}

rootProject.name = "MemePick"
include(":app")
include(":features:memes")
include(":core:presentation")
include(":core:theme")
include(":data:memes-api")
include(":data:memes-imp")
include(":core:data")
include(":core:firebase")
include(":features:authentication")
include(":features:profile")
