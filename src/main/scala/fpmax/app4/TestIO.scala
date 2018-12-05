package fpmax.app4

case class TestData(input: List[String], output: List[ConsoleMessage], randoms: List[Int]) { self =>
  def nextInput(): (TestData, String) =
    (self.copy(input = self.input.drop(1)), self.input.head)

  def nextRandom(): (TestData, Int) =
    (self.copy(randoms = self.randoms.drop(1)), self.randoms.head)

  def newOutput(line: ConsoleMessage): (TestData, Unit) =
    (self.copy(output = line :: self.output), ())

  def showOutputs(): String =
    output.map(_.en).reverse.mkString("\n")
}

case class TestIO[A](execute: TestData => (TestData, A))
