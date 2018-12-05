package app4

import fpmax.app4.{AfterMessageObjects, TestData, TestIO}
import fpmax.app4.ConsoleMessage._
import org.scalatest.{Matchers, WordSpec}

class AfterMessageObjectsSpec extends WordSpec with Matchers {
  "AfterTagless version of the app" when {
    "used with a TestIO" should {
      "be easily testable" in {
        import fpmax.app4.TestIOInstances._

        val playerName = "bob"
        val testData = TestData(
          List(playerName, "2", "y", "4", "n"),
          List(),
          List(2, 3)
        )

        val result = AfterMessageObjects.mainRecipe[TestIO]().execute(testData)

        result match {
          case (resultTestData, _) => {
            resultTestData.output.reverse shouldBe List(
              WhatIsYourName,
              WelcomeToGame(playerName),
              PleaseGuess(playerName),
              YouGuessedWrong(playerName, 3),
              DoYouWantToContinue(playerName),
              PleaseGuess(playerName),
              YouGuessedRight(playerName),
              DoYouWantToContinue(playerName)
            )
          }
        }

      }
    }
  }
}
