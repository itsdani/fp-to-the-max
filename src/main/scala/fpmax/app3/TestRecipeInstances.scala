package fpmax.app3

import fpmax.app3.TypeClasses.{Chainable, Console, Random}

object TestRecipeInstances {

    implicit val chainableTestRecipe: Chainable[TestRecipe] = new Chainable[TestRecipe] {
      override def create[A](a: A): TestRecipe[A] = ???

      override def map[A, B](fa: TestRecipe[A], f: A => B): TestRecipe[B] = ???

      override def flatMap[A, B](fa: TestRecipe[A], f: A => TestRecipe[B]): TestRecipe[B] = ???
    }

    implicit val consoleTestRecipe: Console[TestRecipe] = new Console[TestRecipe] {
      override def putLine(line: String): TestRecipe[Unit] = ???

      override def getLine(): TestRecipe[String] = ???
    }

    implicit val randomTestRecipe: Random[TestRecipe] = new Random[TestRecipe] {
      override def generateRandom(upperBound: Int): TestRecipe[Int] = ???
//        TestRecipe(t => t.randomNumbers.head)
//      TestRecipe(testData => (testData, testData.randomNumbers.head))
    }
  }
