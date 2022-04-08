package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.buildFeatures.testsSplit
import jetbrains.buildServer.configs.kotlin.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.triggers.perforceShelveTrigger

object AShelve_6 : BuildType({
    id("AShelve")
    name = "perforce swarm commit statuses"

    artifactRules = "+:a.txt => ./"
    buildNumberPattern = "buildfromregular%build.counter%"

    vcs {
        root(_Self.vcsRoots.UtilitiesPerforceServer)
        root(_Self.vcsRoots.HttpsGithubComChubatovaTigerChubatovaGradleTestsBackup, ". => gtests")
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
            enabled = false
            scriptContent = "ls"
        }
        gradle {
            tasks = "clean build"
            buildFile = "gtests/build.gradle"
            useGradleWrapper = false
        }
    }

    triggers {
        perforceShelveTrigger {
        }
    }

    features {
        testsSplit {
            numberOfParts = 5
        }
        commitStatusPublisher {
            publisher = swarm {
                serverUrl = "http://localhost:8114"
                username = "jetbrains"
                token = "credentialsJSON:1bf63ce2-b677-4835-9968-0c2427a18d8b"
                createSwarmTest = true
            }
        }
    }

    dependencies {
        snapshot(PerforceSwarmCommitStatuses1perforceSwarmCommitStatuses2perforceSwarmCommitStatuses3perforceSwarmCommitStatuses4) {
        }
        snapshot(PerforceSwarmCommitStatuses3) {
        }
    }
})
