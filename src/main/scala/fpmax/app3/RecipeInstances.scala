package fpmax.app3
import AfterTagless.Recipe
import Typclasses.{Chainable, Console, Random}

import scala.io.StdIn.readLine

object RecipeInstances {

  implicit val chainableRecipe: Chainable[Recipe] = new Chainable[Recipe] {
    override def create[A](a: A): Recipe[A] =
      Recipe(() => a)

    override def map[A, B](fa: Recipe[A], f: A => B): Recipe[B] =
      Recipe(() => f(fa.execute()))

    override def flatMap[A, B](fa: Recipe[A], f: A => Recipe[B]): Recipe[B] =
      Recipe(() => f(fa.execute()).execute())
  }

  implicit val consoleRecipe: Console[Recipe] = new Console[Recipe] {
    override def putLine(line: String): Recipe[Unit] =
      Recipe(() => println(line))

    override def getLine(): Recipe[String] =
      Recipe(() => readLine())
  }

  implicit val randomRecipe: Random[Recipe] = new Random[Recipe] {
    override def generateRandom(upperBound: Int): Recipe[Int] =
      Recipe(() => scala.util.Random.nextInt(upperBound))
  }
}
