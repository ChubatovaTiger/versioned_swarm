package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script

object Dep1 : BuildType({
    name = "dep1"

    steps {
        script {
            scriptContent = """
                echo "##teamcity[testStarted name='MyTest.test1']"
                echo "##teamcity[testFailed type='comparisonFailure' name='MyTest.test1' message='failure message' details='message and stack trace' expected='expected value' actual='actual value']"
                echo "##teamcity[testFinished name='MyTest.test1']"
                echo "##teamcity[testStarted name='MyTest.test1']"
                echo "##teamcity[testFailed type='comparisonFailure' name='MyTest.test1' message='failure message' details='message and stack trace' expected='expected value' actual='actual value']"
                echo "##teamcity[testFinished name='MyTest.test1']"
                echo "##teamcity[testStarted name='MyTest.test1']"
                #echo "##teamcity[testFailed type='comparisonFailure' name='MyTest.test1' message='failure message' details='message and stack trace' expected='expected value' actual='actual value']"
                echo "##teamcity[testFinished name='MyTest.test1']"
            """.trimIndent()
        }
    }

    failureConditions {
        supportTestRetry = true
    }
})
