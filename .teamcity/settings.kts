import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2021.2"

project {

    vcsRoot(HttpsGithubComChubatovaTigerCommitstatuses)

    buildType(id74411)

    template(Deployteamplate)
}

object id74411 : BuildType({
    id("74411")
    name = "74411"

    params {
        param("branch_name", "main")
    }

    vcs {
        root(HttpsGithubComChubatovaTigerCommitstatuses)
        root(DslContext.settingsRoot, "+:. => versioned")
    }
})

object Deployteamplate : Template({
    name = "deployteamplate"

    enablePersonalBuilds = false
    type = BuildTypeSettings.Type.DEPLOYMENT
    maxRunningBuilds = 1

    params {
        param("branch_name", "abc")
    }
})

object HttpsGithubComChubatovaTigerCommitstatuses : GitVcsRoot({
    name = "https://github.com/ChubatovaTiger/commitstatuses"
    url = "https://github.com/ChubatovaTiger/commitstatuses"
    branch = "%branch_name%"
    branchSpec = "+:refs/heads/*"
    authMethod = password {
        userName = "ChubatovaTiger"
        password = "credentialsJSON:6d0e3a4f-5b43-4ca9-adba-25e4e4e8b8a4"
    }
})
