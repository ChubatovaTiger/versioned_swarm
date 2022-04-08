package _Self.vcsRoots

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.vcs.PerforceVcsRoot

object id172311431381666 : PerforceVcsRoot({
    id("172311431381666")
    name = "172.31.143.138:1666"
    pollInterval = 18000
    port = "172.31.143.138:1666"
    mode = client {
        clientName = "nastasia.chubatova_mac"
    }
    userName = "nastasia.chubatova"
    workspaceOptions = """
        Options:        noallwrite clobber nocompress unlocked nomodtime rmdir
        Host:           %teamcity.agent.hostname%
        SubmitOptions:  revertunchanged
        LineEnd:        local
    """.trimIndent()
})
