package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.commitStatusPublisher
import jetbrains.buildServer.configs.kotlin.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'AShelve'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("AShelve")) {
    check(type == BuildTypeSettings.Type.REGULAR) {
        "Unexpected option value: type = $type"
    }
    type = BuildTypeSettings.Type.COMPOSITE

    vcs {

        check(showDependenciesChanges == false) {
            "Unexpected option value: showDependenciesChanges = $showDependenciesChanges"
        }
        showDependenciesChanges = true
    }

    features {
        remove {
            commitStatusPublisher {
                publisher = swarm {
                    serverUrl = "http://localhost:8114"
                    username = "jetbrains"
                    token = "credentialsJSON:1bf63ce2-b677-4835-9968-0c2427a18d8b"
                    createSwarmTest = false
                }
            }
        }
    }
}
