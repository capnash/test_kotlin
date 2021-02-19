import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs
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

version = "2020.2"

project {
    description = "The first project"

    vcsRoot(HttpsGithubComCapnashDevopstestGit)

    buildType(HelloWorld)
}

object HelloWorld : BuildType({
    name = "Hello World"

    vcs {
        root(HttpsGithubComCapnashDevopstestGit)
    }

    steps {
        script {
            scriptContent = """echo "Hello Steve""""
        }
    }

    triggers {
        vcs {
            branchFilter = ""
        }
    }
})

object HttpsGithubComCapnashDevopstestGit : GitVcsRoot({
    name = "https://github.com/capnash/devopstest.git"
    url = "https://github.com/capnash/devopstest.git"
    branch = "refs/heads/main"
    authMethod = password {
        userName = "capnash"
        password = "credentialsJSON:67a0acf4-b343-4c84-9f11-e86a0ec21eac"
    }
})
