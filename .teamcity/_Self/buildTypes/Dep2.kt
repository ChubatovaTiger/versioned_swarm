package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script

object Dep2 : BuildType({
    name = "dep2"

    steps {
        script {
            scriptContent = """
                echo "##teamcity[testStarted name='MyTest.test2']"
                echo "##teamcity[testFailed type='comparisonFailure' name='MyTest.test2' message='failure message' details='message and stack trace' expected='expected value' actual='actual value']"
                echo "##teamcity[testFinished name='MyTest.test2']"
                echo "##teamcity[testStarted name='MyTest.test2']"
                echo "##teamcity[testFailed type='comparisonFailure' name='MyTest.test2' message='failure message' details='message and stack trace' expected='expected value' actual='actual value']"
                echo "##teamcity[testFinished name='MyTest.test2']"
                echo "##teamcity[testStarted name='MyTest.test2']"
                #echo "##teamcity[testFailed type='comparisonFailure' name='MyTest.test2' message='failure message' details='message and stack trace' expected='expected value' actual='actual value']"
                echo "##teamcity[testFinished name='MyTest.test2']"
            """.trimIndent()
        }
    }

    failureConditions {
        supportTestRetry = true
    }
})
