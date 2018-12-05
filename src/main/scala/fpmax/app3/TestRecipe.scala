package fpmax.app3

case class TestData(consoleIn: List[String], consoleOut: List[String], randomNumbers: List[Int]) { self =>
  def nextRandomInt(): (TestData, Int) =
    (self.copy(randomNumbers = randomNumbers.tail), self.randomNumbers.head)

  def getLine(): (TestData, String) =
    (self.copy(consoleIn = consoleIn.tail), self.consoleIn.head)

  def putLine(line: String): (TestData, Unit) =
    (self.copy(consoleOut = line :: consoleOut), ())

  def showResults(): String = ???

}

case class TestRecipe[A](execute: TestData => (TestData, A))
