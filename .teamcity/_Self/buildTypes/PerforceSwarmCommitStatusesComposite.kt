package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.buildFeatures.testsSplit
import jetbrains.buildServer.configs.kotlin.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.triggers.perforceShelveTrigger

object PerforceSwarmCommitStatusesComposite : BuildType({
    name = "perforce swarm commit statuses composite"

    artifactRules = "+:a.txt => ./"
    type = BuildTypeSettings.Type.COMPOSITE
    buildNumberPattern = "buildfromregular%build.counter%"

    vcs {
        root(_Self.vcsRoots.UtilitiesPerforceServer)

        showDependenciesChanges = true
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
    }

    triggers {
        perforceShelveTrigger {
            enabled = false
        }
    }

    features {
        testsSplit {
            enabled = false
            numberOfParts = 3
        }
        commitStatusPublisher {
            publisher = swarm {
                serverUrl = "http://172.20.160.17"
                username = "nastasia.chubatova"
                token = "credentialsJSON:fe3bd14d-6b3d-49e1-a7ae-c0ea9f02df85"
            }
        }
    }

    dependencies {
        snapshot(Dep1) {
            reuseBuilds = ReuseBuilds.NO
            synchronizeRevisions = false
        }
        snapshot(Dep2) {
            reuseBuilds = ReuseBuilds.NO
            synchronizeRevisions = false
        }
        snapshot(Dep3) {
            reuseBuilds = ReuseBuilds.NO
            synchronizeRevisions = false
        }
        snapshot(Dep4) {
            reuseBuilds = ReuseBuilds.NO
            synchronizeRevisions = false
        }
        snapshot(Dep5) {
            reuseBuilds = ReuseBuilds.NO
            synchronizeRevisions = false
        }
    }
})
