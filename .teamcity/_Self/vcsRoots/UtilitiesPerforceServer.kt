package _Self.vcsRoots

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.vcs.PerforceVcsRoot

object UtilitiesPerforceServer : PerforceVcsRoot({
    name = "local_perforce_server"
    pollInterval = 180000
    port = "localhost:1666"
    mode = client {
        clientName = "chubatova"
    }
    userName = "Administrator"
    workspaceOptions = """
        Options:        noallwrite clobber nocompress unlocked nomodtime rmdir
        Host:           %teamcity.agent.hostname%
        SubmitOptions:  revertunchanged
        LineEnd:        local
    """.trimIndent()
})
