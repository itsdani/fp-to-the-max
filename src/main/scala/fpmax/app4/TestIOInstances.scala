package fpmax.app4

import Typclasses.{Chainable, Console, Random}

object TestIOInstances {

  implicit val chainableTestIO: Chainable[TestIO] = new Chainable[TestIO] {
    override def create[A](a: A): TestIO[A] =
      TestIO(testData => (testData, a))

    override def map[A, B](fa: TestIO[A], f: A => B): TestIO[B] =
      TestIO(testData => fa.execute(testData) match { case (t, a) => (t, f(a)) })

    override def flatMap[A, B](fa: TestIO[A], f: A => TestIO[B]): TestIO[B] =
      TestIO(testdata => fa.execute(testdata) match { case (t, a) => f(a).execute(t) })
  }

  implicit val consoleTestIO: Console[TestIO] = new Console[TestIO] {
    override def putLine(line: ConsoleMessage): TestIO[Unit] =
      TestIO(testdata => testdata.newOutput(line))

    override def getLine(): TestIO[String] =
      TestIO(testdata => testdata.nextInput())
  }

  implicit val randomTestIO: Random[TestIO] = new Random[TestIO] {
    override def generateRandom(upperBound: Int): TestIO[Int] =
      TestIO(testdata => testdata.nextRandom())
  }
}
