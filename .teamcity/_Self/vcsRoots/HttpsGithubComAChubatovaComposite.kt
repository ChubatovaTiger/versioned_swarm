package _Self.vcsRoots

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

object HttpsGithubComAChubatovaComposite : GitVcsRoot({
    name = "https://github.com/AChubatova/composite"
    url = "https://github.com/AChubatova/composite"
    branch = "refs/heads/master"
})
