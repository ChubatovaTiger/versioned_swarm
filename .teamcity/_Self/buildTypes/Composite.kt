package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.triggers.perforceShelveTrigger
import jetbrains.buildServer.configs.kotlin.triggers.vcs

object Composite : BuildType({
    name = "composite"

    type = BuildTypeSettings.Type.COMPOSITE

    params {
        param("kwd", "cbd")
    }

    vcs {
        showDependenciesChanges = true
    }

    triggers {
        perforceShelveTrigger {
            enabled = false
            keyword = "teamcity"
        }
        vcs {
            enabled = false
            branchFilter = ""
            watchChangesInDependencies = true
        }
    }

    dependencies {
        snapshot(AShelve_6) {
        }
    }
})
