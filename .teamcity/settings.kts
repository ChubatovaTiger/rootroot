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

    buildType(id74411tmpl)
    buildType(id74411)
    buildType(id74411tmplBr1)

    template(Deployteamplate)

    params {
        password("tigertoken", "credentialsJSON:3d2d86bb-65ab-4885-8d3e-802ac1783e93")
    }
}

object id74411 : BuildType({
    id("74411")
    name = "74411"

    params {
        param("branch_name", "br1")
    }

    vcs {
        root(HttpsGithubComChubatovaTigerCommitstatuses)
        root(DslContext.settingsRoot, "+:. => versioned")
    }
})

object id74411tmpl : BuildType({
    templates(Deployteamplate)
    id("74411tmpl")
    name = "74411-tmpl"

    vcs {
        root(HttpsGithubComChubatovaTigerCommitstatuses)
    }
})

object id74411tmplBr1 : BuildType({
    templates(Deployteamplate)
    id("74411tmplBr1")
    name = "74411-tmpl-br1"

    params {
        param("branch_name", "br1-1")
    }

    vcs {
        root(HttpsGithubComChubatovaTigerCommitstatuses)
    }
})

object Deployteamplate : Template({
    name = "deployteamplate"

    enablePersonalBuilds = false
    type = BuildTypeSettings.Type.DEPLOYMENT
    maxRunningBuilds = 1

    params {
        param("branch_name", "main")
    }
})

object HttpsGithubComChubatovaTigerCommitstatuses : GitVcsRoot({
    name = "https://github.com/ChubatovaTiger/commitstatuses"
    url = "git@github.com:ChubatovaTiger/commitstatuses.git"
    branch = "%branch_name%"
    branchSpec = "+:refs/heads/*"
    authMethod = uploadedKey {
        uploadedKey = "id_rsa_tc_tiger"
        passphrase = "credentialsJSON:c6ac2c5c-1b3d-44c0-bd98-04d75dda453a"
    }
})
