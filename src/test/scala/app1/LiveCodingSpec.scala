package app1
import org.scalatest.{Matchers, WordSpec}

class LiveCodingSpec extends WordSpec with Matchers {
  "Tests" when {
    "they are executed" should {
      "compile" in {
      }

      "not fail" in {
        1 shouldBe 1
      }
    }
  }
}
