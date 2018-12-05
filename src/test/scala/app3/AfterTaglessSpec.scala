package app3
import fpmax.app3.{ AfterTagless, TestData, TestIO }
import org.scalatest.{ Matchers, WordSpec }

class AfterTaglessSpec extends WordSpec with Matchers {
  "AfterTagless version of the app" when {
    "used with a TestIO" should {
      "be easily testable" in {
        import fpmax.app3.TestIOInstances._

        val testData = TestData(
          List(
            "bob",
            "2",
            "y",
            "4",
            "n"
          ),
          List(),
          List(2, 3)
        )

        val result = AfterTagless.mainRecipe[TestIO]().execute(testData)

        result match {
          case (resultData, _) => println(resultData.showOutputs())
        }

      }
    }
  }
}
