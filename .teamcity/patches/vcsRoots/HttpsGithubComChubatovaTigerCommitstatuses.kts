package patches.vcsRoots

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the vcsRoot with id = 'HttpsGithubComChubatovaTigerCommitstatuses'
accordingly, and delete the patch script.
*/
changeVcsRoot(RelativeId("HttpsGithubComChubatovaTigerCommitstatuses")) {
    val expected = GitVcsRoot({
        id("HttpsGithubComChubatovaTigerCommitstatuses")
        name = "https://github.com/ChubatovaTiger/commitstatuses"
        url = "https://git@github.com/ChubatovaTiger/rootroot.git"
        branch = "%branch_name%"
        branchSpec = "+:refs/heads/*"
        authMethod = password {
            userName = "ChubatovaTiger"
            password = "credentialsJSON:6d0e3a4f-5b43-4ca9-adba-25e4e4e8b8a4"
        }
    })

    check(this == expected) {
        "Unexpected VCS root settings"
    }

    (this as GitVcsRoot).apply {
        url = "https://git@github.com/ChubatovaTiger/commitstatuses.git"
    }

}
