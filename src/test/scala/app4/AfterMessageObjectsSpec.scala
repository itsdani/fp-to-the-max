package app4

import fpmax.app4.{ AfterMessageObjects, TestData, TestIO }
import fpmax.app4.ConsoleMessage._
import org.scalatest.{ Matchers, WordSpec }

class AfterMessageObjectsSpec extends WordSpec with Matchers {
  "The program" when {
    "the guess is correct" should {
      "congratulate the player" in {
        val playerName = "bob"
        val testData = TestData(
          List(playerName, "3", "n"),
          List(),
          List(2)
        )

        val (testResult, _) = AfterMessageObjects.mainRecipe[TestIO]().execute(testData)

        testResult.output.reverse shouldBe List(
          WhatIsYourName,
          WelcomeToGame(playerName),
          PleaseGuess(playerName),
          YouGuessedRight(playerName),
          DoYouWantToContinue(playerName)
        )
      }
    }

    "the guess is incorrect" should {
      "display wrong guess message" in {
        val playerName   = "bob"
        val randomNumber = 2
        val solution     = randomNumber + 1

        val testData = TestData(
          List(playerName, "1", "n"),
          List(),
          List(randomNumber)
        )

        val (testResult, _) = AfterMessageObjects.mainRecipe[TestIO]().execute(testData)

        testResult.output.reverse shouldBe List(
          WhatIsYourName,
          WelcomeToGame(playerName),
          PleaseGuess(playerName),
          YouGuessedWrong(playerName, solution),
          DoYouWantToContinue(playerName)
        )
      }
    }
  }
}
