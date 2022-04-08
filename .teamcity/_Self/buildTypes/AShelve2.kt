package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.triggers.perforceShelveTrigger

object AShelve2 : BuildType({
    name = "a_shelve2"

    artifactRules = "+:a.txt => ./"
    buildNumberPattern = "buildfromregular%build.counter%"

    vcs {
        root(_Self.vcsRoots.UtilitiesPerforceServer)
        root(_Self.vcsRoots.HttpsGithubComAChubatovaComposite, "+:. => gitcomposite")
        root(AbsoluteId("HttpsGithubComChubatovaTigerRootroot"))
    }

    steps {
        script {
            enabled = false
            scriptContent = "ls ChubatovaGradleTests"
        }
        gradle {
            tasks = "test"
            workingDir = "firstroot"
        }
        gradle {
            name = "Gradle another root"
            executionMode = BuildStep.ExecutionMode.RUN_ON_FAILURE
            tasks = "test"
            workingDir = "secondpsserver"
        }
        script {
            executionMode = BuildStep.ExecutionMode.RUN_ON_FAILURE
            scriptContent = "echo a > a.txt"
        }
    }

    triggers {
        perforceShelveTrigger {
            enabled = false
            keyword = "teamcity"
        }
    }
})
