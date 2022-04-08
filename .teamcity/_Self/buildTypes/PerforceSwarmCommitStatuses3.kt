package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.buildFeatures.testsSplit
import jetbrains.buildServer.configs.kotlin.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.triggers.perforceShelveTrigger
import jetbrains.buildServer.configs.kotlin.triggers.retryBuild

object PerforceSwarmCommitStatuses3 : BuildType({
    name = "perforce swarm commit statuses3"

    artifactRules = "+:a.txt => ./"
    buildNumberPattern = "buildfromregular%build.counter%"

    vcs {
        root(_Self.vcsRoots.UtilitiesPerforceServer)
        root(_Self.vcsRoots.HttpsGithubComChubatovaTigerChubatovaGradleTestsBackup, "+:. => gtests")
    }

    steps {
        script {
            enabled = false
            executionMode = BuildStep.ExecutionMode.RUN_ON_FAILURE
            scriptContent = "ls"
        }
        gradle {
            enabled = false
            tasks = "clean build"
            buildFile = "secondpsserver/build.gradle"
            useGradleWrapper = false
            gradleWrapperPath = "secondpsserver"
        }
        script {
            scriptContent = "ls new"
        }
        gradle {
            tasks = "clean build"
            gradleWrapperPath = ""
        }
    }

    triggers {
        perforceShelveTrigger {
        }
        retryBuild {
            branchFilter = ""
        }
    }

    features {
        testsSplit {
            enabled = false
            numberOfParts = 5
        }
        commitStatusPublisher {
            enabled = false
            publisher = swarm {
                serverUrl = "http://localhost:8114"
                username = "jetbrains"
                token = "credentialsJSON:1bf63ce2-b677-4835-9968-0c2427a18d8b"
                createSwarmTest = true
            }
        }
    }
})
