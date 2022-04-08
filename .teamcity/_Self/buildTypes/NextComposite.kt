package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.triggers.perforceShelveTrigger

object NextComposite : BuildType({
    name = "nextComposite"

    type = BuildTypeSettings.Type.COMPOSITE
    buildNumberPattern = "${Composite.depParamRefs.buildNumber}"

    vcs {
        root(_Self.vcsRoots.HttpsGithubComAChubatovaComposite)

        showDependenciesChanges = true
    }

    triggers {
        perforceShelveTrigger {
            enabled = false
            keyword = "teamcity"

            buildParams {
                param("fromtrigger", "hi")
            }
        }
    }

    dependencies {
        dependency(Composite) {
            snapshot {
            }

            artifacts {
                artifactRules = "+:a.txt"
            }
        }
    }
})
