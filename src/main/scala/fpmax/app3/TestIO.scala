package fpmax.app3

case class TestData(input: List[String], output: List[String], randoms: List[Int]) { self =>
  def nextInput(): (TestData, String) =
    (self.copy(input = self.input.drop(1)), self.input.head)

  def nextRandom(): (TestData, Int) =
    (self.copy(randoms = self.randoms.drop(1)), self.randoms.head)

  def newOutput(line: String): (TestData, Unit) =
    (self.copy(output = line :: self.output), ())

  def showOutputs(): String =
    output.reverse.mkString("\n")
}

case class TestIO[A](execute: TestData => (TestData, A))
